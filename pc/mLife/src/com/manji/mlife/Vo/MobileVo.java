package com.manji.mlife.Vo;

public class MobileVo {


	private String mobileNo;
	private String mobileArea;
	private String mobileAmount;
	private String mobileNum; 
	private String mobilePrice;
	private String mobileItemId;
	private String mobileItemName;
	
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMobileArea() {
		return mobileArea;
	}
	public void setMobileArea(String mobileArea) {
		this.mobileArea = mobileArea;
	}
	public String getMobileAmount() {
		return mobileAmount;
	}
	public void setMobileAmount(String mobileAmount) {
		this.mobileAmount = mobileAmount;
	}
	
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getMobilePrice() {
		return mobilePrice;
	}
	public void setMobilePrice(String mobilePrice) {
		this.mobilePrice = mobilePrice;
	}
	public String getMobileItemId() {
		return mobileItemId;
	}
	public void setMobileItemId(String mobileItemId) {
		this.mobileItemId = mobileItemId;
	}
	public String getMobileItemName() {
		return mobileItemName;
	}
	public void setMobileItemName(String mobileItemName) {
		this.mobileItemName = mobileItemName;
	}
	@Override
	public String toString() {
		return "MobileVo [mobileNo=" + mobileNo + ", mobileArea=" + mobileArea + ", mobileAmount=" + mobileAmount
				+ ", mobileNum=" + mobileNum + ", mobilePrice=" + mobilePrice + ", mobileItemId=" + mobileItemId
				+ ", mobileItemName=" + mobileItemName + "]";
	}
	
	
	
	
	
	
}
