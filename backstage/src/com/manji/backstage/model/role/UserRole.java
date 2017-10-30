package com.manji.backstage.model.role;
//2.8.4.dt_user_role（用户角色信息表）
public class UserRole {
	int id;
	int user_id;
	String role_type;
	int role_value;
	int operator_type_id;
	String add_time;
	String expired_time;
	int state;
	String update_time;
	String remark;
	int is_online;
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
	public int getOperator_type_id() {
		return operator_type_id;
	}
	public void setOperator_type_id(int operator_type_id) {
		this.operator_type_id = operator_type_id;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getExpired_time() {
		return expired_time;
	}
	public void setExpired_time(String expired_time) {
		this.expired_time = expired_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	
}
