package com.manji.lineol.common.reqparam;

import java.util.Date;

public class ShopConfigModifyReqParam {

	// 商户Id
	private String id;

	// 商户Id
	private String shopId;

	// 类型名称
	private String lineTypeName;

	// 类型别名
	private String lineTypeAs;

	// 描述
	private String descroption;

	// 初始值
	private Integer initalValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getInitalValue() {
		return initalValue;
	}

	public void setInitalValue(Integer initalValue) {
		this.initalValue = initalValue;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "ShopConfigModifyReqParam [id=" + id + ", shopId=" + shopId + ", lineTypeName=" + lineTypeName
				+ ", lineTypeAs=" + lineTypeAs + ", descroption=" + descroption + ", initalValue=" + initalValue + "]";
	}

}
