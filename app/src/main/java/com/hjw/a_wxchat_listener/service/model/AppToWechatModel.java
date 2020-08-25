package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;

public class AppToWechatModel<T> implements Serializable {

    public AppToWechatModel() {
    }

    public AppToWechatModel(T t) {
        this.t = t;
    }

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
