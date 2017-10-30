package com.manji.backstage.model.property;

//dt_banktype（支持绑定银行卡/三方账户类型信息表）

public class Scorerule {
	int id;
	int ebusiness_type;
	int param_key;
	int param_value;
	int start_count;
	String title;
	public int getStart_count() {
		return start_count;
	}
	public void setStart_count(int start_count) {
		this.start_count = start_count;
	}
	String descr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEbusiness_type() {
		return ebusiness_type;
	}
	public void setEbusiness_type(int ebusiness_type) {
		this.ebusiness_type = ebusiness_type;
	}
	public int getParam_key() {
		return param_key;
	}
	public void setParam_key(int param_key) {
		this.param_key = param_key;
	}
	public int getParam_value() {
		return param_value;
	}
	public void setParam_value(int param_value) {
		this.param_value = param_value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	
	
}
