package com.manji.lineol.model;

import java.io.Serializable;

public class Shops implements Serializable{

	private static final long serialVersionUID = 7397307590061064716L;

	private int id;

	//商户Id
	private String shopId;

	//开关
	private int onOff;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public int getOnOff() {
		return onOff;
	}

	public void setOnOff(int onOff) {
		this.onOff = onOff;
	}

	@Override
	public String toString() {
		return "Shops [id=" + id + ", shopId=" + shopId + ", onOff=" + onOff + "]";
	}
	
	

}
