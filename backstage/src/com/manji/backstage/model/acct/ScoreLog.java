package com.manji.backstage.model.acct;



/**
 * 账户信息    dt_user_accountinfo
 * @author Administrator
 *
 */
public class ScoreLog {
	
	int id;
	int shop_id;
	int ebusiness_type;
	int param_value;
	String createtime;
	String remark;
	String object_number;
	int erule_type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getEbusiness_type() {
		return ebusiness_type;
	}
	public void setEbusiness_type(int ebusiness_type) {
		this.ebusiness_type = ebusiness_type;
	}
	public int getParam_value() {
		return param_value;
	}
	public void setParam_value(int param_value) {
		this.param_value = param_value;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getObject_number() {
		return object_number;
	}
	public void setObject_number(String object_number) {
		this.object_number = object_number;
	}
	public int getErule_type() {
		return erule_type;
	}
	public void setErule_type(int erule_type) {
		this.erule_type = erule_type;
	}
	
	
}
