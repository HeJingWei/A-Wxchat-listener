package com.hjw.a_wxchat_listener.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;

import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.hook.HandleResultData;
import com.hjw.a_wxchat_listener.hook.HookTargetMethod;
import com.hjw.a_wxchat_listener.hook.HookWechat;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.service.constant.ClientConstant;
import com.hjw.a_wxchat_listener.service.constant.ServiceConstant;
import com.hjw.a_wxchat_listener.service.model.AppToWechatModel;
import com.hjw.a_wxchat_listener.service.model.QrCodeModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

public class ConnectWechatClient {
    private static ConnectWechatClient mConnectWechatClient;
    private ClientConnection mClientConnection;
    private Messenger mMessenger;
    private Messenger mClientMessenger = new Messenger(new MessengerHandler());
    private final String PackageName = "com.hjw.a_wxchat_listener";
    private final String ServiceName = "com.hjw.a_wxchat_listener.service.ConnectWechatService";

    static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            receiveMessage(msg);
        }
    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mMessenger == null) return;

            mMessenger.getBinder().unlinkToDeath(this, 0);
            mMessenger = null;

            connectToService();
        }
    };

    private class ClientConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.log("与app建立连接");
            mMessenger = new Messenger(service);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mMessenger.getBinder().linkToDeath(mDeathRecipient, 0);
                        ResultData<String> objectResultData = HandleResultData.handleResultDataSuccess("IPC连接成功");
                        WechatSendData.sendConnectSuccess(objectResultData);
                    } catch (Throwable t) {
                        LogUtils.logError(t);
                    }
                }
            }).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.log("onServiceDisconnected 与app断开连接");

        }

        @Override
        public void onBindingDied(ComponentName name) {
            LogUtils.log("onBindingDied 与app断开连接");
        }
    }

    public static ConnectWechatClient getInstance() {
        if (mConnectWechatClient == null) {
            synchronized (ConnectWechatClient.class) {
                mConnectWechatClient = new ConnectWechatClient();
            }
        }
        return mConnectWechatClient;
    }

    private ConnectWechatClient() {
        init();
    }

    private void init() {
        mClientConnection = new ClientConnection();
    }

    public void disConnect() {
        if (mClientConnection != null) {
            Context context = ContextManager.getApplicationContext();
            if (context != null) {
                context.unbindService(mClientConnection);
                LogUtils.log("微信端跨进程通信取消绑定");
            }
        }
    }

    public synchronized void connectToService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = ContextManager.getApplicationContext();
                    if (context != null) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(PackageName, ServiceName));
                        context.bindService(intent, mClientConnection, Context.BIND_AUTO_CREATE);
                    } else {
                        LogUtils.log("微信端初始化context==null");
                    }
                } catch (Throwable e) {
                    LogUtils.log("微信连接APP端失败，重新连接");
                    LogUtils.logError(e);
                    connectToService();
                }
            }
        }).start();


    }

    public <T> void sendData(final int what, final ResultData<T> t) {
        if (mMessenger == null) {

            LogUtils.log("未与服务端建立连接");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message msg = Message.obtain(null, what);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ClientConstant.WECHAT_TO_APP, t);
                    msg.setData(bundle);
                    msg.replyTo = mClientMessenger;
                    mMessenger.send(msg);
                } catch (Throwable throwable) {
                    LogUtils.log("WECHAT_TO_APP_ERROR");
                    LogUtils.logError(throwable);
                }
            }
        }).start();
    }

    public static void receiveMessage(Message message) {
        Bundle data = message.getData();
        AppToWechatModel appToWechatModel = (AppToWechatModel) data.getSerializable(ServiceConstant.APP_TO_WECHAT);
        switch (message.what) {
            case ServiceConstant.UNBIND_SERVICE:
                getInstance().disConnect();
                break;
            case ServiceConstant.GET_HEART_STATE:
                WechatSendData.sendHeart();
                break;
            case ServiceConstant
                    .GET_LOGIN_STATE:
                HookWechat.isLogin();
                break;
            case ServiceConstant
                    .GET_LOGIN_USER:
                HookWechat.getUserName();
                break;
            case ServiceConstant
                    .GET_EMPTY_QRCODE:
                HookWechat.getEmptyQrCode();
                break;
            case ServiceConstant
                    .GET_QRCODE:
                if (appToWechatModel != null && appToWechatModel.getT() != null) {
                    QrCodeModel qrCodeModel = (QrCodeModel) appToWechatModel.getT();
                    HookWechat.getQrCode(qrCodeModel.getMoney(), qrCodeModel.getDesc());
                } else {
                    LogUtils.log("get qrCodeModel == null");
                }
                break;
            case ServiceConstant
                    .GET_BILL_LIST:
                HookWechat.getCollectBill();
                break;
        }
    }
}
