package com.manji.backstage.model.property;

//dt_user_oauth_app（用户第三方登录类型信息表）

public class UserOauthApp {
	int id;
	String title;
	String img_url;
	String app_id;
	String app_key;
	String remark;
	int sort_id;
	int is_mobile;
	int is_lock;
	String api_path;
	int is_del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public int getIs_mobile() {
		return is_mobile;
	}
	public void setIs_mobile(int is_mobile) {
		this.is_mobile = is_mobile;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	public String getApi_path() {
		return api_path;
	}
	public void setApi_path(String api_path) {
		this.api_path = api_path;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
