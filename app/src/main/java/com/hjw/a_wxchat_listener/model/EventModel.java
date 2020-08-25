package com.hjw.a_wxchat_listener.model;

import com.hjw.a_wxchat_listener.manager.BusManager;
import com.hjw.a_wxchat_listener.service.model.ResultData;

import java.io.Serializable;

public class EventModel implements Serializable {

    public static final String WX_LOGIN = "wx_login";
    public static final String TCP_LOGIN = "tcp_login";
    public static final String WX_USERNAME = "wx_username";
    public static final String LOG_LIST = "log_list";

    private String tag;
    private ResultData resultData;
    private String logStr;

    public EventModel(String tag, ResultData resultData, String logStr) {
        this.tag = tag;
        this.resultData = resultData;
        this.logStr = logStr;
    }

    public EventModel(String tag, String logStr) {
        this.tag = tag;
        this.logStr = logStr;
    }

    public String getLogStr() {
        return logStr;
    }

    public void setLogStr(String logStr) {
        this.logStr = logStr;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

    public static void postLog(String log){
        BusManager.getInstance().post(new EventModel(LOG_LIST,log));
    }

    public static void postWxLoginState(ResultData resultData){
        BusManager.getInstance().post(new EventModel(WX_LOGIN,resultData,null));
    }

    public static void postTcpLoginState(ResultData resultData){
        BusManager.getInstance().post(new EventModel(TCP_LOGIN,resultData,null));
    }

    public static void postWxUserName(ResultData resultData){
        BusManager.getInstance().post(new EventModel(WX_USERNAME,resultData,null));
    }
}
