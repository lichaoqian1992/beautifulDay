package com.manji.backstage.model.logger;

public class SignLog {

	 int id;
	 int user_id;
	 String remark;
	 String add_time;
	 int get_point;
	 int continue_times;
	 float longitude;
	 float latitude;
	 String address;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public int getGet_point() {
		return get_point;
	}
	public void setGet_point(int get_point) {
		this.get_point = get_point;
	}
	public int getContinue_times() {
		return continue_times;
	}
	public void setContinue_times(int continue_times) {
		this.continue_times = continue_times;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
