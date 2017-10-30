package com.manji.backstage.model.user;

public class UserFavorites {
	int id;
	int type;
	int value;
	String title;
	String pics;
	String url;
	int user_id;
	String TAG;
	String add_times;
	int is_del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTAG() {
		return TAG;
	}
	public void setTAG(String tAG) {
		TAG = tAG;
	}
	public String getAdd_times() {
		return add_times;
	}
	public void setAdd_times(String add_times) {
		this.add_times = add_times;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
