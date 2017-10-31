package com.manji.backstage.model.operation;

public class ApoSiteContent {
	int id;
	int site_id;
	int navigation_id;
	int content_type;
	String content_value;
	String update_time;
	String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSite_id() {
		return site_id;
	}
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	public int getNavigation_id() {
		return navigation_id;
	}
	public void setNavigation_id(int navigation_id) {
		this.navigation_id = navigation_id;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	public String getContent_value() {
		return content_value;
	}
	public void setContent_value(String content_value) {
		this.content_value = content_value;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
