package com.manji.finance.withdrawals.Param;

public class ReconciliationParam {

	private String ntbnbr;//企业网银编号
	
	private String trscod;//交易码
	
	private String commid;//通讯报文编号
	
	private String tx_date;//商户交易日期
	
	private String merch_prod;//商户二级分类
	
	private String merch_serial;//商户交易流水号
	
	private String type;//对账方式

	public String getNtbnbr() {
		return ntbnbr;
	}

	public void setNtbnbr(String ntbnbr) {
		this.ntbnbr = ntbnbr;
	}

	public String getTrscod() {
		return trscod;
	}

	public void setTrscod(String trscod) {
		this.trscod = trscod;
	}

	public String getCommid() {
		return commid;
	}

	public void setCommid(String commid) {
		this.commid = commid;
	}

	public String getTx_date() {
		return tx_date;
	}

	public void setTx_date(String tx_date) {
		this.tx_date = tx_date;
	}

	public String getMerch_prod() {
		return merch_prod;
	}

	public void setMerch_prod(String merch_prod) {
		this.merch_prod = merch_prod;
	}

	public String getMerch_serial() {
		return merch_serial;
	}

	public void setMerch_serial(String merch_serial) {
		this.merch_serial = merch_serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
