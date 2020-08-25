package com.hjw.a_wxchat_listener.service.model;

import java.io.Serializable;
import java.util.List;

public class CollectBillModel implements Serializable {
    private String time;
    private String title;
    private String des;
    private long pubTime;
    private List<Line> line;

    public CollectBillModel(String time, String title, String des,long pubTime) {
        this.time = time;
        this.title = title;
        this.des = des;
        this.pubTime = pubTime;
    }

    public List<Line> getLine() {
        return line;
    }

    public void setLine(List<Line> line) {
        this.line = line;
    }

    public long getPubTime() {
        return pubTime;
    }

    public void setPubTime(long pubTime) {
        this.pubTime = pubTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static class Line implements Serializable{
        private String key;
        private String value;

        public Line(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
