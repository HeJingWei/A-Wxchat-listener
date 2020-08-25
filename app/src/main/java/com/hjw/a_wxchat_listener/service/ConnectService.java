package com.hjw.a_wxchat_listener.service;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;

import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.service.constant.ClientConstant;
import com.hjw.a_wxchat_listener.service.constant.ServiceConstant;
import com.hjw.a_wxchat_listener.service.model.AppToWechatModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

public class ConnectService {
    private static ConnectService mConnectService;
    private Context mContext;
    private Messenger mMessenger;

    public static boolean isLogin = false;
    public static String userId;

    private ConnectService() {

    }

    public static ConnectService getInstance() {
        if (mConnectService == null) {
            synchronized (ConnectService.class) {
                mConnectService = new ConnectService();
            }
        }
        return mConnectService;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Context getContext() {
        if (mContext == null) {
            return null;
        }
        return mContext;
    }

    public void setMessenger(Messenger messenger) {
        mMessenger = messenger;
    }

    public boolean isConnected() {
        return getMessenger() != null;
    }

    public Messenger getMessenger() {
        if (mMessenger == null) {
            LogUtils.log("Messenger == null");
            return null;
        }
        return mMessenger;
    }

    public <T> void sendData(final int what, final AppToWechatModel<T> t) {
        if (getMessenger() == null) {
            LogUtils.log("未与客户端建立连接");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message msg = Message.obtain(null, what);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ServiceConstant.APP_TO_WECHAT, t);
                    msg.setData(bundle);
                    getMessenger().send(msg);
                } catch (DeadObjectException e) {
                    LogUtils.log("微信进程不存在，重启微信");
                    EventModel.postLog("微信进程不存在，重启微信");
                    Utils.openApp(mContext, Constant.target_package_name);
                } catch (Throwable throwable) {
                    LogUtils.log("APP_TO_WECHAT_ERROR");
                    LogUtils.logError(throwable);
                }
            }
        }).start();
    }

    public void receiveMessage(Message message) {
        setMessenger(message.replyTo);
        ResultData resultData = (ResultData) message.getData().getSerializable(ClientConstant.WECHAT_TO_APP);
        ConnectServiceMessage.receiveMessage(message.what, resultData);
    }
}
