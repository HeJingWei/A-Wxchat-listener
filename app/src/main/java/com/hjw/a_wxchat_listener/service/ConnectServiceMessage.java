package com.hjw.a_wxchat_listener.service;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.os.Handler;
import android.os.Message;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.net.RequestManager;
import com.hjw.a_wxchat_listener.service.constant.ClientConstant;
import com.hjw.a_wxchat_listener.service.model.CollectBillModel;
import com.hjw.a_wxchat_listener.service.model.EmptyQrCodeModel;
import com.hjw.a_wxchat_listener.service.model.QrCodeModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.service.model.UserInfoModel;
import com.hjw.a_wxchat_listener.tcp.TcpClient;
import com.hjw.a_wxchat_listener.tcp.TcpReceive;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.SpUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ConnectServiceMessage {

    public static TcpClient mTcpClient;

    @SuppressLint("HandlerLeak")
    static Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1001:
                    Utils.openApp(ContextManager.getApplicationContext(), Constant.package_name);
                    break;
                    
                case 1002:



                    AppSendData.sendGetHeartState();
                    break;
            }
        }
    };

    public static void receiveMessage(int what, ResultData resultData) {
        if (resultData != null) {
            if (resultData.isSuccess()) {
                switch (what) {
                    case ClientConstant.CONNECT_SUCCESS:
                        LogUtils.log("微信连接成功");
                        EventModel.postLog("与微信连接成功");
                        AppSendData.sendGetLoginState();
                        break;
                    case ClientConstant
                            .GET_LOGIN_STATE:
                        Boolean b = (Boolean) resultData.getData();
                        ConnectService.isLogin = b;
                        if (ConnectService.isLogin) {
                            LogUtils.log("微信登录成功");
                            EventModel.postLog("微信已登录");
                        }
                        handler.sendEmptyMessageDelayed(1001, 1300);
                        AppSendData.sendGetLoginUser();
                        EventModel.postWxLoginState(resultData);
                        break;
                    case ClientConstant
                            .GET_LOGIN_USER:
                        UserInfoModel userInfoModel = (UserInfoModel) resultData.getData();
                        LogUtils.log("获取用户userName：" + userInfoModel.getUserName());
                        ConnectService.userId = userInfoModel.getUserName();
                        EventModel.postLog("获取用户ID成功");
                        EventModel.postWxUserName(resultData);
                        mTcpClient = TcpClient.connectSocket();
                        break;
                    case ClientConstant
                            .GET_EMPTY_QRCODE:
                        EmptyQrCodeModel emptyQrCodeModel = (EmptyQrCodeModel) resultData.getData();
                        LogUtils.log("空收款二维码: " + JSON.toJSONString(emptyQrCodeModel));
                        EventModel.postLog("获取空收款二维码成功: " + emptyQrCodeModel.getQrCode());
                        mTcpClient.sendWxData(TcpReceive.taskId, resultData, Constant.TCP_EmptyQrCode);
                        break;
                    case ClientConstant
                            .GET_QRCODE:
                        QrCodeModel qrCodeModel = (QrCodeModel) resultData.getData();
                        LogUtils.log("收款二维码: " + JSON.toJSONString(qrCodeModel));
                        EventModel.postLog("获取收款二维码成功: " + qrCodeModel.getQrCode());
                        mTcpClient.sendWxData(TcpReceive.taskId, resultData, Constant.TCP_GetQrCode);
                        break;
                    case ClientConstant
                            .GET_BILL_LIST:
                        List<CollectBillModel> collectBillModels = (List<CollectBillModel>) resultData.getData();
                        LogUtils.log("收款账单: " + JSON.toJSONString(collectBillModels));
                        EventModel.postLog("获取收款账单成功: " + JSON.toJSONString(collectBillModels));
                        mTcpClient.handleCollects(TcpReceive.taskId, resultData, Constant.TCP_GetCollectBill);
                        break;
                    case ClientConstant.SET_NEW_BILL:
                        CollectBillModel collectStr = (CollectBillModel) resultData.getData();
                        LogUtils.log("微信新收款消息: " + JSON.toJSONString(collectStr));
                        EventModel.postLog("微信新收款消息: " + JSON.toJSONString(collectStr));
                        SpUtils.setPubTime(collectStr.getPubTime());
                        List<CollectBillModel> data = new ArrayList<>();
                        data.add(collectStr);
                        RequestManager.postNewCollect(data);
                        break;
                    case ClientConstant.SEND_HEART_STATE:
                        LogUtils.log(resultData.getData().toString());
                        handler.sendEmptyMessageDelayed(1002,15000);
                        break;
                }
            } else {
                LogUtils.log("what:" + what + "  微信端返回result failed : " + resultData.getErrorMsg());
                handleFail(what, resultData);
            }
        } else {
            LogUtils.log("what:" + what + "  微信端返回result null : ");
        }
    }

    private static void handleFail(int what, ResultData resultData) {
        switch (what) {
            case ClientConstant
                    .GET_LOGIN_USER:
                EventModel.postLog("获取用户ID失败,请重新上线");
                break;
            case ClientConstant
                    .GET_EMPTY_QRCODE:
                LogUtils.log("获取空收款二维码失败: " + resultData.getErrorMsg());
                EventModel.postLog("获取空收款二维码失败: " + resultData.getErrorMsg());
                mTcpClient.sendWxData(TcpReceive.taskId, resultData, Constant.TCP_EmptyQrCode);
                break;
            case ClientConstant
                    .GET_QRCODE:
                LogUtils.log("获取收款二维码失败: " + resultData.getErrorMsg());
                EventModel.postLog("获取收款二维码失败: " + resultData.getErrorMsg());
                mTcpClient.sendWxData(TcpReceive.taskId, resultData, Constant.TCP_GetQrCode);
                break;
            case ClientConstant
                    .GET_BILL_LIST:
                LogUtils.log("获取收款账单失败: " + resultData.getErrorMsg());
                EventModel.postLog("获取收款账单失败: " + resultData.getErrorMsg());
                mTcpClient.sendWxData(TcpReceive.taskId, resultData, Constant.TCP_GetCollectBill);
                break;
            case ClientConstant.SET_NEW_BILL:
                LogUtils.log("获取新收款消息失败: " + resultData.getErrorMsg());
                EventModel.postLog("获取新收款消息失败: " + resultData.getErrorMsg());
                break;
        }
    }
}
