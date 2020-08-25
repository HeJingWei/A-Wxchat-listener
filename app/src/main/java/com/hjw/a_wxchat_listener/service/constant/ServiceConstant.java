package com.hjw.a_wxchat_listener.service.constant;

public interface ServiceConstant {
    String APP_TO_WECHAT = "appToWechat";
    int UNBIND_SERVICE = 997;//下线
    int GET_HEART_STATE = 998; //心跳状态，确认微信是否在运行
    int GET_LOGIN_STATE = 999;//获取登录状态
    int GET_LOGIN_USER = 1000;//获取登录userName
    int GET_EMPTY_QRCODE = 1001;//获取收款二维码
    int GET_QRCODE = 1002;//获取固定金额收款二维码
    int GET_BILL_LIST = 1003;//获取收款账单
}
