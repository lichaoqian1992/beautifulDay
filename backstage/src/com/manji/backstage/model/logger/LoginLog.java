package com.manji.backstage.model.logger;

public class LoginLog {

	 int id;
	 int user_id;
	 String remark;
	 String login_time;
	 String login_ip;
	 String login_from;
	 String add_time;
	 
	 
	 
	 
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public String getLogin_from() {
		return login_from;
	}
	public void setLogin_from(String login_from) {
		this.login_from = login_from;
	}
	 

}
