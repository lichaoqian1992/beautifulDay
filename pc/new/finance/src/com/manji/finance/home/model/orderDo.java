package com.manji.finance.home.model;

public class orderDo{
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	private int allOrderNum;
	
	private Double allOrderMoney;

	private int successOrderNum;
	
	private Double successOrderMoney;

	public int getAllOrderNum() {
		return allOrderNum;
	}

	public void setAllOrderNum(int allOrderNum) {
		this.allOrderNum = allOrderNum;
	}

	public Double getAllOrderMoney() {
		return allOrderMoney;
	}

	public void setAllOrderMoney(Double allOrderMoney) {
		this.allOrderMoney = allOrderMoney;
	}

	public int getSuccessOrderNum() {
		return successOrderNum;
	}

	public void setSuccessOrderNum(int successOrderNum) {
		this.successOrderNum = successOrderNum;
	}

	public Double getSuccessOrderMoney() {
		return successOrderMoney;
	}

	public void setSuccessOrderMoney(Double successOrderMoney) {
		this.successOrderMoney = successOrderMoney;
	}

}
