package com.manji.backstage.model.other;
//dt_user_often_use (用户经常使用信息表)
public class UserOftenUse {
	int id;
	int user_id;
	int type;
	int value;
	int times;
	String update_times;
	int is_del;
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
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getUpdate_times() {
		return update_times;
	}
	public void setUpdate_times(String update_times) {
		this.update_times = update_times;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
