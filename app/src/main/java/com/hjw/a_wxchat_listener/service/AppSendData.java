package com.hjw.a_wxchat_listener.service;

import android.text.TextUtils;

import com.hjw.a_wxchat_listener.activity.MainActivity;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.service.constant.ServiceConstant;
import com.hjw.a_wxchat_listener.service.model.AppToWechatModel;
import com.hjw.a_wxchat_listener.service.model.QrCodeModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

public class AppSendData {

    public static <T> void sendForWxData(int what, AppToWechatModel<T> t, String logMsg) {
        if (!Utils.isNetworkConnected()) {
            LogUtils.log("没有网络连接");
            EventModel.postLog("网络未连接，请先连接网络");
            return;
        }
        if (what != ServiceConstant.GET_LOGIN_STATE && !ConnectService.isLogin) {
            ConnectService.userId = null;
            LogUtils.log("请登录微信");
            EventModel.postLog("微信未登录，请登录后重试");
            return;
        }
        if (!TextUtils.isEmpty(logMsg)) {
            LogUtils.log(logMsg);
            EventModel.postLog(logMsg);
        }
        ConnectService.getInstance().sendData(what, t);
    }

    public static void sendUnBind(){
        ConnectService.getInstance().sendData(ServiceConstant.UNBIND_SERVICE, null);
    }

    public static void sendGetHeartState() {
        if (MainActivity.isOnService) {
            ConnectService.getInstance().sendData(ServiceConstant.GET_HEART_STATE, null);
            LogUtils.log("心跳发送，检查微信是否运行");
        }
    }

    public static void sendGetLoginState() {
        sendForWxData(ServiceConstant.GET_LOGIN_STATE, null, "开始获取登录状态");
    }

    public static void sendGetLoginUser() {
        sendForWxData(ServiceConstant.GET_LOGIN_USER, null, "开始获取用户ID");
    }

    public static void sendGetEmptyQrCode() {
        sendForWxData(ServiceConstant.GET_EMPTY_QRCODE, null, "开始获取空白二维码");
    }

    public static void sendGetQrCode(String money, String desc) {
        QrCodeModel qrCodeModel = new QrCodeModel(money, desc);
        AppToWechatModel<QrCodeModel> appToWechatModel = new AppToWechatModel<QrCodeModel>(qrCodeModel);
        sendForWxData(ServiceConstant.GET_QRCODE, appToWechatModel, "开始获取收款二维码");
    }

    public static void sendGetCollectBill() {
        sendForWxData(ServiceConstant.GET_BILL_LIST, null, "开始获取收款消息");
    }
}
