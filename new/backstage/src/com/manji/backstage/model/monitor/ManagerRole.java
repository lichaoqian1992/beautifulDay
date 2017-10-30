package com.manji.backstage.model.monitor;

//管理员角色信息表
public class ManagerRole {
	int id;
	String role_name;
	int role_type;
	int is_sys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getRole_type() {
		return role_type;
	}
	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}
	public int getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(int is_sys) {
		this.is_sys = is_sys;
	}
	
}
