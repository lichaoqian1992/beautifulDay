package com.manji.mlife.Vo;

public class QueryVo {
	
	
	private String starttime;
	private String endtime;
	private String goodsType;
	private String orderNumber;
	private String rechargeAccount;
	private String orderStatus;
	private String userName;
	private String startCity;
	private String endCity;
	
	
	
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getRechargeAccount() {
		return rechargeAccount;
	}
	public void setRechargeAccount(String rechargeAccount) {
		this.rechargeAccount = rechargeAccount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	@Override
	public String toString() {
		return "QueryVo [starttime=" + starttime + ", endtime=" + endtime + ", goodsType=" + goodsType
				+ ", orderNumber=" + orderNumber + ", rechargeAccount=" + rechargeAccount + ", orderStatus="
				+ orderStatus + ", userName=" + userName + ", startCity=" + startCity + ", endCity=" + endCity + "]";
	}

	
	
	
	
	
	

}
