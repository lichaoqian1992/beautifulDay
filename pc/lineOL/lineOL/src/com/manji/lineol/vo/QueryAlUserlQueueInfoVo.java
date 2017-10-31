package com.manji.lineol.vo;

import java.io.Serializable;

public class QueryAlUserlQueueInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String shopId;
	
	private String userId;
	
	private String currentPosition;
	
	private String number;
	
	private String typeAs;
	
	private String shopName;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTypeAs() {
		return typeAs;
	}

	public void setTypeAs(String typeAs) {
		this.typeAs = typeAs;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
	
	
	
	
	
	
	

}
