package com.manji.datahost.model.sqlserver.user;
/**
 * 用户地址
 * @author Administrator
 *
 */
public class UserAddress {

	private Integer user_id;
	private String accept_name;
	private String mobile;
	private String address;
	private String post_code;
	private Integer is_default;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getAccept_name() {
		return accept_name;
	}
	public void setAccept_name(String accept_name) {
		this.accept_name = accept_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public Integer getIs_default() {
		return is_default;
	}
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
	
}
