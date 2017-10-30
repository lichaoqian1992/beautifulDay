package com.manji.backstage.model.role;
//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
public class RoleOperatorTypeValue {
	int id;
	int type_id;
	String nav_name;
	String action_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getNav_name() {
		return nav_name;
	}
	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	
}
