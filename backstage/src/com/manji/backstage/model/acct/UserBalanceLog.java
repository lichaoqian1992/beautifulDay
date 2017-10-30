package com.manji.backstage.model.acct;
//4.15.1.dt_user_balance_log(账户结算主表)
public class UserBalanceLog {
	int id;
	int user_id;
	String role_type;
	int role_value;
	int order_platform;
	String order_type;
	String order_no;
	String balance_hash;
	String order_title;
	String order_url;
	double balance_amount;
	double balance_voucher;
	double balance_point;
	String will_balance_date;
	String real_balance_date;
	int balance_state;
	String add_time;
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
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public int getRole_value() {
		return role_value;
	}
	public void setRole_value(int role_value) {
		this.role_value = role_value;
	}
	public int getOrder_platform() {
		return order_platform;
	}
	public void setOrder_platform(int order_platform) {
		this.order_platform = order_platform;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getBalance_hash() {
		return balance_hash;
	}
	public void setBalance_hash(String balance_hash) {
		this.balance_hash = balance_hash;
	}
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public String getOrder_url() {
		return order_url;
	}
	public void setOrder_url(String order_url) {
		this.order_url = order_url;
	}
	public double getBalance_amount() {
		return balance_amount;
	}
	public void setBalance_amount(double balance_amount) {
		this.balance_amount = balance_amount;
	}
	public double getBalance_voucher() {
		return balance_voucher;
	}
	public void setBalance_voucher(double balance_voucher) {
		this.balance_voucher = balance_voucher;
	}
	public double getBalance_point() {
		return balance_point;
	}
	public void setBalance_point(double balance_point) {
		this.balance_point = balance_point;
	}
	public String getWill_balance_date() {
		return will_balance_date;
	}
	public void setWill_balance_date(String will_balance_date) {
		this.will_balance_date = will_balance_date;
	}
	public String getReal_balance_date() {
		return real_balance_date;
	}
	public void setReal_balance_date(String real_balance_date) {
		this.real_balance_date = real_balance_date;
	}
	public int getBalance_state() {
		return balance_state;
	}
	public void setBalance_state(int balance_state) {
		this.balance_state = balance_state;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
