package com.manji.datahost.model.sqlserver.client;

/**
 * 安全资料
 * @author Administrator
 *
 */
public class UserSafe {

	private String password;
	private String pay_password;
	private String email;
	private String mobile;
	private String person_name;
	private String account_safe;
	private Integer user_id;
	private String nick_name;
	private String avatar;

	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAccount_safe() {
		return account_safe;
	}
	public void setAccount_safe(String account_safe) {
		this.account_safe = account_safe;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	
	
}
