package com.manji.mlife.model;

public class Trade {
	
    private String outerid;//外部订单编码

    private String tradeno;//商品订单编号

    private String ctime;//创建时间

    private String title;//标题

    private String state;//订单状态：0-预定中，2-预定完成，待支付，1-已完成，9-已取消

    private String pstate;//支付状态 0：未支付，1：已支付

    private String ptime;//支付时间

    private String etime;//完成时间

    private String name;//姓名

    private String tel;//电话

    private String price;//面值

    private String paycash;//售价

    private String type;//类型

    private String username;//用户

    private String mjoder;//满集编码

    private String flag;//类型 0充值 1票务

    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid == null ? null : outerid.trim();
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate == null ? null : pstate.trim();
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime == null ? null : ptime.trim();
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime == null ? null : etime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getPaycash() {
        return paycash;
    }

    public void setPaycash(String paycash) {
        this.paycash = paycash == null ? null : paycash.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMjoder() {
        return mjoder;
    }

    public void setMjoder(String mjoder) {
        this.mjoder = mjoder == null ? null : mjoder.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}