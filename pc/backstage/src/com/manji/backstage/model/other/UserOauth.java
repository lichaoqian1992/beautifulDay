package com.manji.backstage.model.other;
//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
public class UserOauth {
	int id;
	int user_id;
	String oauth_name;
	String oauth_access_token;
	String oauth_openid;
	String add_time;
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
	public String getOauth_name() {
		return oauth_name;
	}
	public void setOauth_name(String oauth_name) {
		this.oauth_name = oauth_name;
	}
	public String getOauth_access_token() {
		return oauth_access_token;
	}
	public void setOauth_access_token(String oauth_access_token) {
		this.oauth_access_token = oauth_access_token;
	}
	public String getOauth_openid() {
		return oauth_openid;
	}
	public void setOauth_openid(String oauth_openid) {
		this.oauth_openid = oauth_openid;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	
}
