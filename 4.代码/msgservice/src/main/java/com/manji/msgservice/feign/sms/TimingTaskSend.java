package com.manji.msgservice.feign.sms;

public class TimingTaskSend {
    private String mobiles;
    private String title;
    private String content;
    private String ip;
    private Integer pass = 1;

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }
}
