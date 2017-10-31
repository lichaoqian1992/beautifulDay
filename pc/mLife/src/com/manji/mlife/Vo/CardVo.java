package com.manji.mlife.Vo;

public class CardVo {
	private String itemId;
	private String itemNum;
	private String receiveMobile;
	private String receiveEmail;
	private String totalPrice;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getReceiveMobile() {
		return receiveMobile;
	}
	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}
	public String getReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "CardVo [itemId=" + itemId + ", itemNum=" + itemNum + ", receiveMobile=" + receiveMobile
				+ ", receiveEmail=" + receiveEmail + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
