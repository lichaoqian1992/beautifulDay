package com.manji.backstage.model.logger;

public class SmsLog {

	int id;
	int user_id;
	String user_role_type;
	int user_role_value;
	String type;
	String content;
	String user_ip;
	String Mobile;
	String add_time;
	int send_status;
	String send_remark;
	String send_time;
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
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type.trim();
	}
	public int getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(int user_role_value) {
		this.user_role_value = user_role_value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content.trim();
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip.trim();
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile.trim();
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time.trim();
	}
	public int getSend_status() {
		return send_status;
	}
	public void setSend_status(int send_status) {
		this.send_status = send_status;
	}
	public String getSend_remark() {
		return send_remark;
	}
	public void setSend_remark(String send_remark) {
		this.send_remark = send_remark.trim();
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time.trim();
	}
	
}
