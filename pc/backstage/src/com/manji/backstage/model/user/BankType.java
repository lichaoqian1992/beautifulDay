package com.manji.backstage.model.user;

public class BankType {

	int id;
	int bank_type;
	int user_id;
	String user_role_type;
	int user_role_value;
	String bank_type_value;
	String bank_address;
	String bank_area;
	String add_time;
	int is_last_use;
	String failed_times;
	int is_del;
	String usertype;
	int status;
	String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBank_type() {
		return bank_type;
	}
	public void setBank_type(int bank_type) {
		this.bank_type = bank_type;
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
	public String getBank_type_value() {
		return bank_type_value;
	}
	public void setBank_type_value(String bank_type_value) {
		this.bank_type_value = bank_type_value;
	}
	public String getBank_address() {
		return bank_address;
	}
	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}
	public String getBank_area() {
		return bank_area;
	}
	public void setBank_area(String bank_area) {
		this.bank_area = bank_area;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public int getIs_last_use() {
		return is_last_use;
	}
	public void setIs_last_use(int is_last_use) {
		this.is_last_use = is_last_use;
	}
	public String getFailed_times() {
		return failed_times;
	}
	public void setFailed_times(String failed_times) {
		this.failed_times = failed_times;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
