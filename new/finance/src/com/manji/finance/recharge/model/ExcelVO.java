package com.manji.finance.recharge.model;

public class ExcelVO {

	private String oaNo;

    private int rechargeType;

    private String userName;

    private String idCard;
    /**充值账号*/
    private String userKey;

    private double money;
    /**是否可提现*/
    private int withDrawls;
    
    private String remark;
    
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOaNo() {
		return oaNo;
	}
	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
	public int getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getWithDrawls() {
		return withDrawls;
	}
	public void setWithDrawls(int withDrawls) {
		this.withDrawls = withDrawls;
	}
    
}
