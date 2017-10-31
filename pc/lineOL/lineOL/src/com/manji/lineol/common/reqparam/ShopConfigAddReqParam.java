package com.manji.lineol.common.reqparam;

import java.util.Date;

public class ShopConfigAddReqParam {

	// 商户Id
	private String shopId;

	// 类型名称
	private String lineTypeName;

	// 类型别名
	private String lineTypeAs;

	// 描述
	private String descroption;

	// 初始值
	private int initalValue;
	

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getLineTypeName() {
		return lineTypeName;
	}

	public void setLineTypeName(String lineTypeName) {
		this.lineTypeName = lineTypeName;
	}

	public String getLineTypeAs() {
		return lineTypeAs;
	}

	public void setLineTypeAs(String lineTypeAs) {
		this.lineTypeAs = lineTypeAs;
	}

	public String getDescroption() {
		return descroption;
	}

	public void setDescroption(String descroption) {
		this.descroption = descroption;
	}

	public int getInitalValue() {
		return initalValue;
	}

	public void setInitalValue(int initalValue) {
		this.initalValue = initalValue;
	}

	@Override
	public String toString() {
		return "ShopConfigAddReqParam [shopId=" + shopId + ", lineTypeName=" + lineTypeName + ", lineTypeAs="
				+ lineTypeAs + ", descroption=" + descroption + ", initalValue=" + initalValue + "]";
	}
	

	
	
	
	
	
	

}
