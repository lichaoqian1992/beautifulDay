package com.manji.backstage.model.other;
//2.3.2.dt_user_wechat（用户微信信息表）
public class UserWechat {
	int id;
	int user_id;
	int wechat_id;
	String open_id;
	String nick_name;
	int sex;
	String country;
	String province;
	String city;
	String headimg;
	int is_subscribe;
	String update_time;
	String from_id;
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
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
	public int getWechat_id() {
		return wechat_id;
	}
	public void setWechat_id(int wechat_id) {
		this.wechat_id = wechat_id;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public int getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(int is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	
	
}
