package com.hjw.a_wxchat_listener.service;

import com.hjw.a_wxchat_listener.hook.HandleResultData;
import com.hjw.a_wxchat_listener.service.constant.ClientConstant;
import com.hjw.a_wxchat_listener.service.model.CollectBillModel;
import com.hjw.a_wxchat_listener.service.model.EmptyQrCodeModel;
import com.hjw.a_wxchat_listener.service.model.QrCodeModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.service.model.UserInfoModel;

import java.util.List;

public class WechatSendData {
    public static void sendConnectSuccess(ResultData<String> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.CONNECT_SUCCESS,resultData);
    }

    public static void sendHeart(){
        ResultData<String> HeartRes = HandleResultData.handleResultDataSuccess("微信运行中");
        ConnectWechatClient.getInstance().sendData(ClientConstant.SEND_HEART_STATE,HeartRes);
    }

    public static void sendGetEmptyQrCode(ResultData<EmptyQrCodeModel> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.GET_EMPTY_QRCODE,resultData);
    }

    public static void sendGetLoginState(ResultData<Boolean> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.GET_LOGIN_STATE,resultData);
    }

    public static void sendGetLoginUser(ResultData<UserInfoModel> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.GET_LOGIN_USER,resultData);
    }

    public static void sendGetQrCode(ResultData<QrCodeModel> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.GET_QRCODE,resultData);
    }

    public static void sendCollectInfo(ResultData<CollectBillModel> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.SET_NEW_BILL,resultData);
    }

    public static void sendCollectBill(ResultData<List<CollectBillModel>> resultData){
        ConnectWechatClient.getInstance().sendData(ClientConstant.GET_BILL_LIST,resultData);
    }
}
