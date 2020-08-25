package com.hjw.a_wxchat_listener.tcp.model;

import com.hjw.a_wxchat_listener.service.model.ResultData;

public class LogicTypeModel{
    private String logic_type;
    private String amount;
    private String des;
    private String taskId;
    private String userId;
    private ResultData data;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLogic_type() {
        return logic_type;
    }

    public void setLogic_type(String logic_type) {
        this.logic_type = logic_type;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ResultData getData() {
        return data;
    }

    public void setData(ResultData data) {
        this.data = data;
    }
}
