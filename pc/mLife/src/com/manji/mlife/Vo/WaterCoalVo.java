package com.manji.mlife.Vo;

public class WaterCoalVo {

	
	private String waterCoalItemId;
	private String waterCoalAmount;
	private String waterCoalAccount;
	private String customerName;
	private String customerAddress;
	private String contractNo;
	private String billCycle;
	
	
	public String getWaterCoalAmount() {
		return waterCoalAmount;
	}
	public void setWaterCoalAmount(String waterCoalAmount) {
		this.waterCoalAmount = waterCoalAmount;
	}
	public String getWaterCoalItemId() {
		return waterCoalItemId;
	}
	public void setWaterCoalItemId(String waterCoalItemId) {
		this.waterCoalItemId = waterCoalItemId;
	}
	public String getWaterCoalAccount() {
		return waterCoalAccount;
	}
	public void setWaterCoalAccount(String waterCoalAccount) {
		this.waterCoalAccount = waterCoalAccount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	@Override
	public String toString() {
		return "WaterCoalVo [waterCoalItemId=" + waterCoalItemId + ", waterCoalAmount=" + waterCoalAmount
				+ ", waterCoalAccount=" + waterCoalAccount + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", contractNo=" + contractNo + ", billCycle=" + billCycle + "]";
	}

	
	
	
}
