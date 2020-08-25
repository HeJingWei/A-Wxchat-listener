package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;

public class UserInfoModel implements Serializable {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
