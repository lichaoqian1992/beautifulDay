package com.manji.backstage.model.logger;

public class AuditLog {

	
	int id;
	int user_id;
	int audit_list_id;
	String type;
	String add_time;
	String login_ip;
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
	public int getAudit_list_id() {
		return audit_list_id;
	}
	public void setAudit_list_id(int audit_list_id) {
		this.audit_list_id = audit_list_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	
	

	
}
