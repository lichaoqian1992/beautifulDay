package com.manji.backstage.model.message;

/**
 * 系统公告      dt_notice
 * @author Administrator
 *
 */
public class Notice {

	int id;
	String title;
	String content;
	String get_user_roles;
	String get_user_groups;
	String get_user_area;
	int user_id;
	String user_role_type;
	int user_role_value;
	int state;
	String update_time;
	String remark;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGet_user_roles() {
		return get_user_roles;
	}
	public void setGet_user_roles(String get_user_roles) {
		this.get_user_roles = get_user_roles;
	}
	public String getGet_user_groups() {
		return get_user_groups;
	}
	public void setGet_user_groups(String get_user_groups) {
		this.get_user_groups = get_user_groups;
	}
	public String getGet_user_area() {
		return get_user_area;
	}
	public void setGet_user_area(String get_user_area) {
		this.get_user_area = get_user_area;
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
	
	
	
	
	
}
