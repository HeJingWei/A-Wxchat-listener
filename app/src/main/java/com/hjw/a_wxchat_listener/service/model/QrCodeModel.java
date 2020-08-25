package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;

public class QrCodeModel implements Serializable {
    private String qrCode;
    private String money;
    private String desc;

    public QrCodeModel(){

    }

    public QrCodeModel(String qrCode) {
        this.qrCode = qrCode;
    }

    public QrCodeModel(String money, String desc) {
        this.money = money;
        this.desc = desc;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
