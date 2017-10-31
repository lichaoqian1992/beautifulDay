package com.manji.adds.model;

//用户信息表
public class User {

	//编号
	private int id;
	//用户名
	private String username;
	//用户密码
	private String password;
	//用户权限
	private String privilege;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	
	
	
	
}
