package com.manji.mlife.model;

public class TrafficOrder {
	// 商品订单子单号 主键
    private String orderno;//主键

    // 订单主编号
    private String tradno;
    
    // 标题
    private String title;
    
    // 标准商品编号
    private String itemid;

    // 乘客姓名
    private String passengername;
    
    // 乘客联系号码
    private String passengertel;
    
    // 证件类型 0 身份证
    private String idcardtype;
    
    // 证件号码
    private String idcardno;
    
    // 车票号码
    private String ticketno;

    // 建议零售价，单位元，保留两位小数
    private String saleprice;

    // 实际支付的金额，单位元，保留两位小数
    private String paycash;

    private String itemprice;

    private String refundfee;

    // 费用明细
    private String feedetail;

    /**
     *  座位类型 0:二等座 1:一等座 2:特等座 3:商务座 4:无座 5:硬座 
     *  	  6:软座 7:硬卧 8:软卧; 9:高级软卧 10:火车其他座
     *        11:经济舱 12:头等舱; 21:汽车座位
     */
    private String seattype;

    // 座位类型名称	
    private String seatname;

    /**
     * 出票成功后，座次详细信息 "出发站:上海虹桥,到达站:北京南,车次:G104,成人票,01车厢,二等座,01号"
     */
    private String seatinfo;

    // 销售订单号
    private String saleorderno;

    // 车次号
    private String trainno;

    // 起始站
    private String startstation;

    // 终点站
    private String receviestation;

    // 发车时间，精确到分钟
    private String starttime;

    // 到站时间，精确到分钟，火车票与飞机票不返回，建议从车次或航班查询接口的返回数据中获取后缓存。
    private String recevietime;

    // 联系人
    private String contactname;

    // 联系人电话
    private String contacttel;

    // 订单类型
    private String ordertype;

    private String legs;

    /**
     * 保险
     */
    // 订单展示状态 0:交易中,9:出票失败,1:出票成功,6:退票中,7:退票失败,10:已退票,11:已退款
    private String insurancestate;

    // 票面价，单位元，支持两位小数
    private String insurancefaceprice;

    // 保险支付价格，单位元，支持两位小数
    private String insurancepaycash;

    // 保险价格，单位元，支持两位小数
    private String insuranceitemcash;

    // 建议零售价，单位元，支持两位小数
    private String insurancesaleprice;

    // 销售订单号
    private String insurancesaleorderno;

    // 保险单订单号。
    private String insuranceinsuranceno;

    // 保险的标准商品编号
    private String insuranceitemid;

    // 用户号码
    private String insuranceusertel;

    // 用户姓名(被保人姓名)
    private String insuranceusername;

    // 保险单号,支付购买成功后展示
    private String insuranceinsurancepolicyno;

    // 其它费用总和，单位元，支持两位小数
    private String totalotherfee;

    // 网点的采购价，单位元，支持两位小数 
    private String totalpurprice;

    // 票面价，单位元，支持两位小数
    private String totalfaceprice;

    // 实际支付的金额，单位元，支持两位小数
    private String totalpaycash;

    // 建议零售价，单位元，支持两位小数
    private String totalsaleprice;
    
    /**
     * 订单展示状态 0:交易中,1:出票成功,6:退票中,7:退票失败,
     * 			9:出票失败,10:已退票,11:已退款
     */
    private String state;
    public String getState() {
		return state;
	}

	public void setState(String state) {
        this.state = state == null ? null : state.trim();
	}

	public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getTradno() {
        return tradno;
    }

    public void setTradno(String tradno) {
        this.tradno = tradno == null ? null : tradno.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public String getPassengername() {
        return passengername;
    }

    public void setPassengername(String passengername) {
        this.passengername = passengername == null ? null : passengername.trim();
    }

    public String getPassengertel() {
        return passengertel;
    }

    public void setPassengertel(String passengertel) {
        this.passengertel = passengertel == null ? null : passengertel.trim();
    }

    public String getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype == null ? null : idcardtype.trim();
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno == null ? null : idcardno.trim();
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno == null ? null : ticketno.trim();
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice == null ? null : saleprice.trim();
    }

    public String getPaycash() {
        return paycash;
    }

    public void setPaycash(String paycash) {
        this.paycash = paycash == null ? null : paycash.trim();
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice == null ? null : itemprice.trim();
    }

    public String getRefundfee() {
        return refundfee;
    }

    public void setRefundfee(String refundfee) {
        this.refundfee = refundfee == null ? null : refundfee.trim();
    }

    public String getFeedetail() {
        return feedetail;
    }

    public void setFeedetail(String feedetail) {
        this.feedetail = feedetail == null ? null : feedetail.trim();
    }

    public String getSeattype() {
        return seattype;
    }

    public void setSeattype(String seattype) {
        this.seattype = seattype == null ? null : seattype.trim();
    }

    public String getSeatname() {
        return seatname;
    }

    public void setSeatname(String seatname) {
        this.seatname = seatname == null ? null : seatname.trim();
    }

    public String getSeatinfo() {
        return seatinfo;
    }

    public void setSeatinfo(String seatinfo) {
        this.seatinfo = seatinfo == null ? null : seatinfo.trim();
    }

    public String getSaleorderno() {
        return saleorderno;
    }

    public void setSaleorderno(String saleorderno) {
        this.saleorderno = saleorderno == null ? null : saleorderno.trim();
    }

    public String getTrainno() {
        return trainno;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno == null ? null : trainno.trim();
    }

    public String getStartstation() {
        return startstation;
    }

    public void setStartstation(String startstation) {
        this.startstation = startstation == null ? null : startstation.trim();
    }

    public String getReceviestation() {
        return receviestation;
    }

    public void setReceviestation(String receviestation) {
        this.receviestation = receviestation == null ? null : receviestation.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getRecevietime() {
        return recevietime;
    }

    public void setRecevietime(String recevietime) {
        this.recevietime = recevietime == null ? null : recevietime.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel == null ? null : contacttel.trim();
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs == null ? null : legs.trim();
    }

    public String getInsurancestate() {
        return insurancestate;
    }

    public void setInsurancestate(String insurancestate) {
        this.insurancestate = insurancestate == null ? null : insurancestate.trim();
    }

    public String getInsurancefaceprice() {
        return insurancefaceprice;
    }

    public void setInsurancefaceprice(String insurancefaceprice) {
        this.insurancefaceprice = insurancefaceprice == null ? null : insurancefaceprice.trim();
    }

    public String getInsurancepaycash() {
        return insurancepaycash;
    }

    public void setInsurancepaycash(String insurancepaycash) {
        this.insurancepaycash = insurancepaycash == null ? null : insurancepaycash.trim();
    }

    public String getInsuranceitemcash() {
        return insuranceitemcash;
    }

    public void setInsuranceitemcash(String insuranceitemcash) {
        this.insuranceitemcash = insuranceitemcash == null ? null : insuranceitemcash.trim();
    }

    public String getInsurancesaleprice() {
        return insurancesaleprice;
    }

    public void setInsurancesaleprice(String insurancesaleprice) {
        this.insurancesaleprice = insurancesaleprice == null ? null : insurancesaleprice.trim();
    }

    public String getInsurancesaleorderno() {
        return insurancesaleorderno;
    }

    public void setInsurancesaleorderno(String insurancesaleorderno) {
        this.insurancesaleorderno = insurancesaleorderno == null ? null : insurancesaleorderno.trim();
    }

    public String getInsuranceinsuranceno() {
        return insuranceinsuranceno;
    }

    public void setInsuranceinsuranceno(String insuranceinsuranceno) {
        this.insuranceinsuranceno = insuranceinsuranceno == null ? null : insuranceinsuranceno.trim();
    }

    public String getInsuranceitemid() {
        return insuranceitemid;
    }

    public void setInsuranceitemid(String insuranceitemid) {
        this.insuranceitemid = insuranceitemid == null ? null : insuranceitemid.trim();
    }

    public String getInsuranceusertel() {
        return insuranceusertel;
    }

    public void setInsuranceusertel(String insuranceusertel) {
        this.insuranceusertel = insuranceusertel == null ? null : insuranceusertel.trim();
    }

    public String getInsuranceusername() {
        return insuranceusername;
    }

    public void setInsuranceusername(String insuranceusername) {
        this.insuranceusername = insuranceusername == null ? null : insuranceusername.trim();
    }

    public String getInsuranceinsurancepolicyno() {
        return insuranceinsurancepolicyno;
    }

    public void setInsuranceinsurancepolicyno(String insuranceinsurancepolicyno) {
        this.insuranceinsurancepolicyno = insuranceinsurancepolicyno == null ? null : insuranceinsurancepolicyno.trim();
    }

    public String getTotalotherfee() {
        return totalotherfee;
    }

    public void setTotalotherfee(String totalotherfee) {
        this.totalotherfee = totalotherfee == null ? null : totalotherfee.trim();
    }

    public String getTotalpurprice() {
        return totalpurprice;
    }

    public void setTotalpurprice(String totalpurprice) {
        this.totalpurprice = totalpurprice == null ? null : totalpurprice.trim();
    }

    public String getTotalfaceprice() {
        return totalfaceprice;
    }

    public void setTotalfaceprice(String totalfaceprice) {
        this.totalfaceprice = totalfaceprice == null ? null : totalfaceprice.trim();
    }

    public String getTotalpaycash() {
        return totalpaycash;
    }

    public void setTotalpaycash(String totalpaycash) {
        this.totalpaycash = totalpaycash == null ? null : totalpaycash.trim();
    }

    public String getTotalsaleprice() {
        return totalsaleprice;
    }

    public void setTotalsaleprice(String totalsaleprice) {
        this.totalsaleprice = totalsaleprice == null ? null : totalsaleprice.trim();
    }
}