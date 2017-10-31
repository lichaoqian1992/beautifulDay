package com.manji.lineol.common.info;

import java.util.Date;

public class ShopConfigInfo extends BaseInfo{
	private static final long serialVersionUID = 818303259389754938L;
	
	private int id;

	//商户Id
	private String shopId;

	//类型名称
	private String lineTypeName;

	//类型别名
	private String lineTypeAs;

	//描述
	private String descroption;

	//初始值
	private int initalValue;

	//创建时间
	private Date createDate;

	//修改时间
	private Date modifyDate;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ShopConfigInfo [id=" + id + ", shopId=" + shopId + ", lineTypeName=" + lineTypeName + ", lineTypeAs="
				+ lineTypeAs + ", descroption=" + descroption + ", initalValue=" + initalValue + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + "]";
	}
	
	
	
	
	
	

}
