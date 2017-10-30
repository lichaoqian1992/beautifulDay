package com.manji.backstage.model.role;
//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
public class RoleOperatorType {
	int id;
	String type_name;
	int user_id;
	String role_type;
	int role_type_value;
	int is_sys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
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
	public int getRole_type_value() {
		return role_type_value;
	}
	public void setRole_type_value(int role_type_value) {
		this.role_type_value = role_type_value;
	}
	public int getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(int is_sys) {
		this.is_sys = is_sys;
	}
	
}
