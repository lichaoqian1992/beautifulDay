package com.manji.mlife.model;

public class ChargeOrder {
    private String billid;
    private String outerid;

    //充值账号
    private String rechargeaccount;
    
    // 销售金额，单位元，保留3位小数
    private String saleamount;
    
	// 订单利润，单位元，保留3位小数
    private String orderprofit;

    // 订单生成时间，格式：yyyy-MM-dd hh:mm:ss
    private String ordertime;

    // 订单处理时间，格式：yyyy-MM-dd hh:mm:ss
    private String operatetime;

    // 订单付款状态 0 未付款1 已付款rechargestate;
    private String paystate;

    // 订单充值状态 0充值中 1成功 9撤销
    private String rechargestate;

    // 商品类型：1:实物商品 2:直充商品 3:卡密商品 4:话费充值 6:支付和金币充值
    private String classtype;

    // 商品成本价(进价)，单位元，保留3位小数
    private String itemcost;

    // 订单成本(进价)，单位元，保留3位小数，orderCost=itemCost*itemNum
    private String ordercost;

    // 撤销原因
    private String revokemessage;

    // 面值，单位元，保留3位小数
    private String faceprice;

    // 商品数量
    private String itemnum;

    // 商品名称
    private String itemname;

    // 供货商编号
    private String supid;

    // 供货商QQ
    private String supqq;

    // 供货商用户名
    private String supnickname;

    // 供应商联系人姓名
    private String supcontactuser;

    // 供应商手机号
    private String supmobile;
    
    // 游戏区(游戏充值)
    private String gamearea;

    // 游戏服(游戏充值)
    private String gameserver;

    // 卡密接受手机号码(卡密商品)
    private String receivemobile;

    // 支付金额(支付宝充值快速到账使用此字段)
    private String actprice;

    // 支付宝的充值码,需要Base64解码
    private String extpay;

	// 战网账号
    private String battleaccount;
	
    // 模板编号
    private String tplid;

	// 过期时间，格式yyyy-MM-dd hh:mm:ss
    private String expiredate;

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid == null ? null : billid.trim();
    }

    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid == null ? null : outerid.trim();
    }

    public String getRechargeaccount() {
        return rechargeaccount;
    }

    public void setRechargeaccount(String rechargeaccount) {
        this.rechargeaccount = rechargeaccount == null ? null : rechargeaccount.trim();
    }

    public String getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(String saleamount) {
        this.saleamount = saleamount == null ? null : saleamount.trim();
    }

    public String getOrderprofit() {
        return orderprofit;
    }

    public void setOrderprofit(String orderprofit) {
        this.orderprofit = orderprofit == null ? null : orderprofit.trim();
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime == null ? null : ordertime.trim();
    }

    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime == null ? null : operatetime.trim();
    }

    public String getPaystate() {
        return paystate;
    }

    public void setPaystate(String paystate) {
        this.paystate = paystate == null ? null : paystate.trim();
    }

    public String getRechargestate() {
        return rechargestate;
    }

    public void setRechargestate(String rechargestate) {
        this.rechargestate = rechargestate == null ? null : rechargestate.trim();
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype == null ? null : classtype.trim();
    }

    public String getItemcost() {
        return itemcost;
    }

    public void setItemcost(String itemcost) {
        this.itemcost = itemcost == null ? null : itemcost.trim();
    }

    public String getOrdercost() {
        return ordercost;
    }

    public void setOrdercost(String ordercost) {
        this.ordercost = ordercost == null ? null : ordercost.trim();
    }

    public String getRevokemessage() {
        return revokemessage;
    }

    public void setRevokemessage(String revokemessage) {
        this.revokemessage = revokemessage == null ? null : revokemessage.trim();
    }

    public String getFaceprice() {
        return faceprice;
    }

    public void setFaceprice(String faceprice) {
        this.faceprice = faceprice == null ? null : faceprice.trim();
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum == null ? null : itemnum.trim();
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public String getSupid() {
        return supid;
    }

    public void setSupid(String supid) {
        this.supid = supid == null ? null : supid.trim();
    }

    public String getSupqq() {
        return supqq;
    }

    public void setSupqq(String supqq) {
        this.supqq = supqq == null ? null : supqq.trim();
    }

    public String getSupnickname() {
        return supnickname;
    }

    public void setSupnickname(String supnickname) {
        this.supnickname = supnickname == null ? null : supnickname.trim();
    }

    public String getSupcontactuser() {
        return supcontactuser;
    }

    public void setSupcontactuser(String supcontactuser) {
        this.supcontactuser = supcontactuser == null ? null : supcontactuser.trim();
    }

    public String getSupmobile() {
        return supmobile;
    }

    public void setSupmobile(String supmobile) {
        this.supmobile = supmobile == null ? null : supmobile.trim();
    }

    public String getGamearea() {
        return gamearea;
    }

    public void setGamearea(String gamearea) {
        this.gamearea = gamearea == null ? null : gamearea.trim();
    }

    public String getGameserver() {
        return gameserver;
    }

    public void setGameserver(String gameserver) {
        this.gameserver = gameserver == null ? null : gameserver.trim();
    }

    public String getReceivemobile() {
        return receivemobile;
    }

    public void setReceivemobile(String receivemobile) {
        this.receivemobile = receivemobile == null ? null : receivemobile.trim();
    }

    public String getActprice() {
        return actprice;
    }

    public void setActprice(String actprice) {
        this.actprice = actprice == null ? null : actprice.trim();
    }

    public String getExtpay() {
        return extpay;
    }

    public void setExtpay(String extpay) {
        this.extpay = extpay == null ? null : extpay.trim();
    }

    public String getBattleaccount() {
        return battleaccount;
    }

    public void setBattleaccount(String battleaccount) {
        this.battleaccount = battleaccount == null ? null : battleaccount.trim();
    }

    public String getTplid() {
        return tplid;
    }

    public void setTplid(String tplid) {
        this.tplid = tplid == null ? null : tplid.trim();
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate == null ? null : expiredate.trim();
    }
}