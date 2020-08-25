package com.hjw.a_wxchat_listener.tcp.model;

public class TcpBaseModel {
    public static final String POST = "post";
    public static final String RESPONSE = "response";

    private String tcpType;
    private String data;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTcpType() {
        return tcpType;
    }

    public void setTcpType(String tcpType) {
        this.tcpType = tcpType;
    }
}
