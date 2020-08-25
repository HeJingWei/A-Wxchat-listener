package com.hjw.a_wxchat_listener.tcp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.service.AppSendData;
import com.hjw.a_wxchat_listener.tcp.model.ConnectResponseModel;
import com.hjw.a_wxchat_listener.tcp.model.LogicTypeModel;
import com.hjw.a_wxchat_listener.tcp.model.TcpBaseModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;

public class TcpReceive {
    public static String taskId;

    public static void handle(String data) {
        try {
            TcpBaseModel baseModel =  JSONObject.parseObject(data,TcpBaseModel.class);
            switch (baseModel.getTcpType()){
                case TcpBaseModel.POST:
                    handleLogicType(baseModel.getData(),baseModel.getTaskId());
                    break;
                case TcpBaseModel.RESPONSE:
                    handleResponse(baseModel.getData());
                    break;
            }
        } catch (JSONException e) {
            LogUtils.logError(e);
            LogUtils.log("接收的数据格式错误");
        } catch (Exception e) {
            LogUtils.log("接收数据出错");
            LogUtils.logError(e);
        }
    }

    private static void handleLogicType(String data,String task) {
        EventModel.postLog("接受到TCP推送消息：" + data);
        LogicTypeModel logic =  JSONObject.parseObject(data,LogicTypeModel.class);
        taskId = task;
        switch (logic.getLogic_type()){
            case Constant.TCP_GetCollectBill:
                AppSendData.sendGetCollectBill();
                break;
            case Constant.TCP_EmptyQrCode:
                AppSendData.sendGetEmptyQrCode();
                break;
            case Constant.TCP_GetQrCode:
                AppSendData.sendGetQrCode(logic.getAmount(), logic.getDes());
                break;
        }
    }

    private static void handleResponse(String data){
        ConnectResponseModel connectResponseModel =  JSONObject.parseObject(data,ConnectResponseModel.class);
        if (connectResponseModel.isTcpSuccess()){
            LogUtils.log("TCP接收消息成功");
//            EventModel.postLog("TCP接收消息成功");
            switch (connectResponseModel.getStatus()){
                case 1://心跳数据
                    LogUtils.log("TCP返回data："+ connectResponseModel.getData());
//                    EventModel.postLog("TCP返回data："+ connectResponseModel.getData());
                    break;
            }
        }
    }
}
