package com.manji.backstage.model.acct;



/**
 * 转账记录     dt_user_transaction
 * @author Administrator
 *
 */
public class Transaction {

	int id;
	int user_id;
	String user_role_type;
	int user_role_value;
	int touser_id;
	String touser_role_type;
	int touser_role_value;
	String transaction_no;
	double amount;
	int status;
	String add_time;
	String complete_time;
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
	public int getTouser_id() {
		return touser_id;
	}
	public void setTouser_id(int touser_id) {
		this.touser_id = touser_id;
	}
	public String getTouser_role_type() {
		return touser_role_type;
	}
	public void setTouser_role_type(String touser_role_type) {
		this.touser_role_type = touser_role_type;
	}
	public int getTouser_role_value() {
		return touser_role_value;
	}
	public void setTouser_role_value(int touser_role_value) {
		this.touser_role_value = touser_role_value;
	}
	public String getTransaction_no() {
		return transaction_no;
	}
	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getComplete_time() {
		return complete_time;
	}
	public void setComplete_time(String complete_time) {
		this.complete_time = complete_time;
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
