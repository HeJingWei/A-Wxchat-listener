package com.hjw.a_wxchat_listener.service.constant;

public interface ClientConstant {
    String WECHAT_TO_APP = "wechatToApp";
    int CONNECT_SUCCESS = 100;//连接成功
    int SEND_HEART_STATE = 1998;//发送微信心跳，确定微信还在运行中
    int GET_LOGIN_STATE = 1999;//获取登录状态
    int GET_LOGIN_USER = 2000;//获取登录userName
    int GET_EMPTY_QRCODE = 2001;//获取收款二维码
    int GET_QRCODE = 2002;//获取固定金额收款二维码
    int GET_BILL_LIST = 2003;//获取收款账单
    int SET_NEW_BILL = 2004;//收到新的收款记录
}
