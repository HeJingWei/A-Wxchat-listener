package com.hjw.a_wxchat_listener.tcp.model;

public class ConnectResponseModel<T>{
    private boolean tcpSuccess;
    private int status;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isTcpSuccess() {
        return tcpSuccess;
    }

    public void setTcpSuccess(boolean tcpSuccess) {
        this.tcpSuccess = tcpSuccess;
    }
}
