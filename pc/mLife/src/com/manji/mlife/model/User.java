package com.manji.mlife.model;

/**
 * 用户类
 * @author gaochao
 * 2016年7月21日下午2:51:26
 * User
 *
 */
public class User {
	

	private String username;//用户名
	
	private String password;//密码
	
	private String checkcode;//验证码
	
	private String flag;//是否记住密码
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
