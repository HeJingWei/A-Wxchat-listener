package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;

public class EmptyQrCodeModel implements Serializable {
    private String qrCode;

    public EmptyQrCodeModel(){

    }

    public EmptyQrCodeModel(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
