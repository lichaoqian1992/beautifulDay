package com.manji.backstage.model.order;


/**
 *第三方支付信息详情 dt_order_partner 
 * 
 * @author Administrator
 *
 */
public class OrderPartner {

	int id;
	int user_id;
	String user_role_type;
	int user_role_value;
	String partner_channel;
	String order_no;
	String order_title;
	double order_money;
	int pay_status;
	String return_url;
	String notify_url;
	int notify_number;
	String notify_result;
	int notify_status;
	String add_time;
	String pay_time;
	String back_time;
	String over_time;
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
	public String getPartner_channel() {
		return partner_channel;
	}
	public void setPartner_channel(String partner_channel) {
		this.partner_channel = partner_channel;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public double getOrder_money() {
		return order_money;
	}
	public void setOrder_money(double order_money) {
		this.order_money = order_money;
	}
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public int getNotify_number() {
		return notify_number;
	}
	public void setNotify_number(int notify_number) {
		this.notify_number = notify_number;
	}
	public String getNotify_result() {
		return notify_result;
	}
	public void setNotify_result(String notify_result) {
		this.notify_result = notify_result;
	}
	public int getNotify_status() {
		return notify_status;
	}
	public void setNotify_status(int notify_status) {
		this.notify_status = notify_status;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getBack_time() {
		return back_time;
	}
	public void setBack_time(String back_time) {
		this.back_time = back_time;
	}
	public String getOver_time() {
		return over_time;
	}
	public void setOver_time(String over_time) {
		this.over_time = over_time;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
	
	
	
	
	
	
	
	
}
