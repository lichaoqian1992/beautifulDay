package com.manji.backstage.model.shop;

//dt_business_sms(短信信息表)
public class BusinessSms {
	int id;
	int user_id;
	String user_role_type;
	int user_role_value;
	int sms_number;
	double cust_money;
	int sms_count_number;
	String remark;
	int is_del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type;
	}
	public int getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(int user_role_value) {
		this.user_role_value = user_role_value;
	}
	public int getSms_number() {
		return sms_number;
	}
	public void setSms_number(int sms_number) {
		this.sms_number = sms_number;
	}
	public double getCust_money() {
		return cust_money;
	}
	public void setCust_money(double cust_money) {
		this.cust_money = cust_money;
	}
	public int getSms_count_number() {
		return sms_count_number;
	}
	public void setSms_count_number(int sms_count_number) {
		this.sms_count_number = sms_count_number;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
