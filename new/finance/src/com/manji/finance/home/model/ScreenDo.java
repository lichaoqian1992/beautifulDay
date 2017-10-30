package com.manji.finance.home.model;

import java.util.Date;

public class ScreenDo{
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	
	/*订单号*/
	private String orderNo;
	
	/*退单号*/
	private String canceOrderNo;

	/*订单类型*/
	private String orderType;
	
	/*查询开始时间段*/
	private String startTime;
	
	/*查询结束时间段*/
	private String endTime;
	
	/*用户名*/
	private String nickName;
	
	/*用户状态*/
	private String userType;
	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCanceOrderNo() {
		return canceOrderNo;
	}

	public void setCanceOrderNo(String canceOrderNo) {
		this.canceOrderNo = canceOrderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	
	
}
