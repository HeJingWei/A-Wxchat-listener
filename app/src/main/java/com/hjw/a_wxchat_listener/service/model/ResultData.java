package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;

public class ResultData<T> implements Serializable {
    private boolean isSuccess;
    private String errorMsg;
    private T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
