package com.manji.backstage.model.logger;

/**
 * 登陆设备日志  dt_user_online_log
 * @author Administrator
 *
 */
public class DeviceLog {

	long id;
	int user_id;
	String from_type;
	String from_device;
	String from_ip;
	String from_time;
	String from_areapoint;
	String from_area;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFrom_type() {
		return from_type;
	}
	public void setFrom_type(String from_type) {
		this.from_type = from_type;
	}
	public String getFrom_device() {
		return from_device;
	}
	public void setFrom_device(String from_device) {
		this.from_device = from_device;
	}
	public String getFrom_ip() {
		return from_ip;
	}
	public void setFrom_ip(String from_ip) {
		this.from_ip = from_ip;
	}
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getFrom_areapoint() {
		return from_areapoint;
	}
	public void setFrom_areapoint(String from_areapoint) {
		this.from_areapoint = from_areapoint;
	}
	public String getFrom_area() {
		return from_area;
	}
	public void setFrom_area(String from_area) {
		this.from_area = from_area;
	}
	
	
	
	
}
