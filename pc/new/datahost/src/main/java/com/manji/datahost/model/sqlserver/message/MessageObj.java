package com.manji.datahost.model.sqlserver.message;

/**
 * 发送手机对象
 * @author Administrator
 *
 */
public class MessageObj {
	
	private Integer user_id;
	private String reg_ip;
	private String mobile;
	private String role_type;
	private Integer role_value;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getReg_ip() {
		return reg_ip;
	}
	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public Integer getRole_value() {
		return role_value;
	}
	public void setRole_value(Integer role_value) {
		this.role_value = role_value;
	}
	
}
