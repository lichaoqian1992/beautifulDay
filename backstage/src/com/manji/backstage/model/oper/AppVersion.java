package com.manji.backstage.model.oper;

public class AppVersion {
	
	int id;
	String ver_name;
	String ver_url;
	String rel_datetime;
	String ver_category;
	int is_up;
	int count;
	String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVer_name() {
		return ver_name;
	}
	public void setVer_name(String ver_name) {
		this.ver_name = ver_name;
	}
	public String getVer_url() {
		return ver_url;
	}
	public void setVer_url(String ver_url) {
		this.ver_url = ver_url;
	}
	public String getRel_datetime() {
		return rel_datetime;
	}
	public void setRel_datetime(String rel_datetime) {
		this.rel_datetime = rel_datetime;
	}
	public String getVer_category() {
		return ver_category;
	}
	public void setVer_category(String ver_category) {
		this.ver_category = ver_category;
	}
	public int getIs_up() {
		return is_up;
	}
	public void setIs_up(int is_up) {
		this.is_up = is_up;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
}
