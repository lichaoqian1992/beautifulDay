package com.manji.datahost.model.sqlserver.message;

/**
 * 发送短信
 * @author Administrator
 *
 */
public class SendMessage {

	private Integer user_id;
	private String user_role_type;
	private Integer user_role_value;
	private String type;
	private String content;
	private String user_ip;
	private String mobile;
	private String add_time;
	private Integer send_status;
	private String send_remark;
	private String send_time;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type;
	}
	public Integer getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(Integer user_role_value) {
		this.user_role_value = user_role_value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public Integer getSend_status() {
		return send_status;
	}
	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}
	public String getSend_remark() {
		return send_remark;
	}
	public void setSend_remark(String send_remark) {
		this.send_remark = send_remark;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	
	
}
