package com.manji.backstage.model.account;



/**
 * 账户信息    dt_user_accountinfo
 * @author Administrator
 *
 */
public class Account {
	
	int id;
	int user_id;
	String role_type;
	int role_value;
	double amount;
	double point;
	int reputation;
	double credit;
	int voucher;
	int is_fastpay;
	int state;
	String remark;
	String update_time;
	double float_amount;
	double float_voucher;
	double float_point;
	double allow_amount;
	double allow_voucher;
	double withdrawals_commission;
	
	
	
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getVoucher() {
		return voucher;
	}
	public void setVoucher(int voucher) {
		this.voucher = voucher;
	}
	public int getIs_fastpay() {
		return is_fastpay;
	}
	public void setIs_fastpay(int is_fastpay) {
		this.is_fastpay = is_fastpay;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public double getFloat_amount() {
		return float_amount;
	}
	public void setFloat_amount(double float_amount) {
		this.float_amount = float_amount;
	}
	public double getFloat_voucher() {
		return float_voucher;
	}
	public void setFloat_voucher(double float_voucher) {
		this.float_voucher = float_voucher;
	}
	public double getFloat_point() {
		return float_point;
	}
	public void setFloat_point(double float_point) {
		this.float_point = float_point;
	}
	public double getAllow_amount() {
		return allow_amount;
	}
	public void setAllow_amount(double allow_amount) {
		this.allow_amount = allow_amount;
	}
	public double getAllow_voucher() {
		return allow_voucher;
	}
	public void setAllow_voucher(double allow_voucher) {
		this.allow_voucher = allow_voucher;
	}
	public double getWithdrawals_commission() {
		return withdrawals_commission;
	}
	public void setWithdrawals_commission(double withdrawals_commission) {
		this.withdrawals_commission = withdrawals_commission;
	}
	
	
	
	
	
	

}
