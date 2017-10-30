package com.manji.backstage.model.shop;

public class CompHandle {

	
	long id;
	long complaint_id;
	int user_id;
	String role_type;
	int role_value;
	String update_time;
	String remark;
	int is_satisfaction;
	int service_type;
	double service_value;
	double service_point;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(long complaint_id) {
		this.complaint_id = complaint_id;
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
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIs_satisfaction() {
		return is_satisfaction;
	}
	public void setIs_satisfaction(int is_satisfaction) {
		this.is_satisfaction = is_satisfaction;
	}
	public int getService_type() {
		return service_type;
	}
	public void setService_type(int service_type) {
		this.service_type = service_type;
	}
	public double getService_value() {
		return service_value;
	}
	public void setService_value(double service_value) {
		this.service_value = service_value;
	}
	public double getService_point() {
		return service_point;
	}
	public void setService_point(double service_point) {
		this.service_point = service_point;
	}
	
	

	
	
}
