package com.manji.mlife.Vo;

public class GameVo {
	private String itemId;
	private String itemNum;
	private String rechargeAccount;
	private String rechargeAccount2;
	private String totalAmount;

	private String gameArea;
	private String gameServer;
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
	public String getRechargeAccount() {
		return rechargeAccount;
	}
	public void setRechargeAccount(String rechargeAccount) {
		this.rechargeAccount = rechargeAccount;
	}
	public String getRechargeAccount2() {
		return rechargeAccount2;
	}   
	public void setRechargeAccount2(String rechargeAccount2) {
		this.rechargeAccount2 = rechargeAccount2;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getGameArea() {
		return gameArea;
	}
	public void setGameArea(String gameArea) {
		this.gameArea = gameArea;
	}
	public String getGameServer() {
		return gameServer;
	}
	public void setGameServer(String gameServer) {
		this.gameServer = gameServer;
	}
	@Override
	public String toString() {
		return "GameVo [itemId=" + itemId + ", itemNum=" + itemNum + ", rechargeAccount=" + rechargeAccount
				+ ", rechargeAccount2=" + rechargeAccount2 + ", totalAmount=" + totalAmount + ", gameArea=" + gameArea
				+ ", gameServer=" + gameServer + "]";
	}
	
	
	
	
}
