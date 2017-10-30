package com.manji.backstage.model.acct;



/**
 * 充值记录 dt_user_recharge
 * @author Administrator
 *
 */
public class Recharge {

	int id;
	int user_id;
	String user_role_type;
	int user_role_value;
	int account_id;
	String recharge_no;
	int payment_id;
	double amount;
	int status;
	String add_time;
	String complete_time;
	String transaction_no;
	int is_del;
	double voucher;
	
	
	
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
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getRecharge_no() {
		return recharge_no;
	}
	public void setRecharge_no(String recharge_no) {
		this.recharge_no = recharge_no;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
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
	public String getTransaction_no() {
		return transaction_no;
	}
	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public double getVoucher() {
		return voucher;
	}
	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}
	
	
	
	
	
}
