package com.manji.mlife.Vo;

public class FineVo {
	
	
	private String  fineNo;
	private String  carNo;
	private String  engineId;
	private String  itemId;
	private String  itemName;
	private String  fineFee;
	private String  delayFee;
	private String  carType;
	private String  name;
	private String  mobile;
	private String  totalFee;
	
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFineNo() {
		return fineNo;
	}
	public void setFineNo(String fineNo) {
		this.fineNo = fineNo;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getFineFee() {
		return fineFee;
	}
	public void setFineFee(String fineFee) {
		this.fineFee = fineFee;
	}
	public String getDelayFee() {
		if(null ==delayFee){
			delayFee ="0.00";
		}
		return delayFee;
	}
	public void setDelayFee(String delayFee) {
		this.delayFee = delayFee;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	@Override
	public String toString() {
		return "FineVo [fineNo=" + fineNo + ", carNo=" + carNo + ", engineId=" + engineId + ", itemId=" + itemId
				+ ", fineFee=" + fineFee + ", delayFee=" + delayFee + ", carType=" + carType + ", name=" + name
				+ ", mobile=" + mobile + ", totalFee=" + totalFee + "]";
	}
	

	
}
