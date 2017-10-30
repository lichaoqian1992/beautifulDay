package com.manji.backstage.model.oper;

//dt_advert_content(广告位放置内容表)

public class AdvertContent {
	int id;
	int advert_id;
	int advert_banner_id;
	String name;
	String file_path;
	String link_url;
	String content;
	int state;
	int is_show;
	String remark;
	String update_time;
	int user_id;
	String user_role_type;
	int user_role_value;
	int is_del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdvert_id() {
		return advert_id;
	}
	public void setAdvert_id(int advert_id) {
		this.advert_id = advert_id;
	}
	public int getAdvert_banner_id() {
		return advert_banner_id;
	}
	public void setAdvert_banner_id(int advert_banner_id) {
		this.advert_banner_id = advert_banner_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIs_show() {
		return is_show;
	}
	public void setIs_show(int is_show) {
		this.is_show = is_show;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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
		this.user_role_type = user_role_type;
	}
	public int getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(int user_role_value) {
		this.user_role_value = user_role_value;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
