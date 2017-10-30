package com.manji.backstage.model.acct;
//4.16.2.dt_user_balance_detail(账户结算子表)
public class UserBalanceDetail {
	int id;
	String balance_hash;
	double balance_amount;
	double balance_voucher;
	double balance_point;
	String remark;
	String add_time;
	int is_del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBalance_hash() {
		return balance_hash;
	}
	public void setBalance_hash(String balance_hash) {
		this.balance_hash = balance_hash;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
