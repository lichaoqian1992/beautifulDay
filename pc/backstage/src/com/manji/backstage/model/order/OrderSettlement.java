package com.manji.backstage.model.order;

public class OrderSettlement {

	long id;
	long order_id;
	String order_no;
	String order_type;
	int shop_user_id;
	String shop_user_role_type;
	int shop_user_role_value;
	double settlement_money;
	double percentage_money;
	double real_money;
	String add_time;
	String remark;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public int getShop_user_id() {
		return shop_user_id;
	}
	public void setShop_user_id(int shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	public String getShop_user_role_type() {
		return shop_user_role_type;
	}
	public void setShop_user_role_type(String shop_user_role_type) {
		this.shop_user_role_type = shop_user_role_type;
	}
	public int getShop_user_role_value() {
		return shop_user_role_value;
	}
	public void setShop_user_role_value(int shop_user_role_value) {
		this.shop_user_role_value = shop_user_role_value;
	}
	public double getSettlement_money() {
		return settlement_money;
	}
	public void setSettlement_money(double settlement_money) {
		this.settlement_money = settlement_money;
	}
	public double getPercentage_money() {
		return percentage_money;
	}
	public void setPercentage_money(double percentage_money) {
		this.percentage_money = percentage_money;
	}
	public double getReal_money() {
		return real_money;
	}
	public void setReal_money(double real_money) {
		this.real_money = real_money;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
}
