package com.manji.finance.home.model;

public class ReportFormDo{
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	
	/*资金流入*/
	private Double CapitalLiuRu;
	/*资金流出*/
	private Double CapitalLiuChu;
	/*初期结余*/
	private Double OpeningBalance;
	/*初末结余*/
	private Double BeginningBalance;
	/*查询时间*/
	private String selectTime;
	
	public Double getBeginningBalance() {
		return BeginningBalance;
	}
	public void setBeginningBalance(Double beginningBalance) {
		BeginningBalance = beginningBalance;
	}
	public String getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}
	public Double getCapitalLiuRu() {
		return CapitalLiuRu;
	}
	public void setCapitalLiuRu(Double capitalLiuRu) {
		CapitalLiuRu = capitalLiuRu;
	}
	public Double getCapitalLiuChu() {
		return CapitalLiuChu;
	}
	public void setCapitalLiuChu(Double capitalLiuChu) {
		CapitalLiuChu = capitalLiuChu;
	}
	public Double getOpeningBalance() {
		return OpeningBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		OpeningBalance = openingBalance;
	}

	
}
