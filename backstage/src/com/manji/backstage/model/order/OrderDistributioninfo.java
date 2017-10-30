package com.manji.backstage.model.order;


/**
 * 配送类订单详情 dt_order_distributioninfo
 * @author Administrator
 *
 */
public class OrderDistributioninfo {

	long id;
	long order_id;
	String name;
	String mobile;
	String area;
	String address;
	String hop_arrive_time;
	String send_time;
	String send_user_name;
	String send_user_mobile;
	String real_arrive_time;
	int sms_send_state;
	String sms_send_time;
	int send_status;
	int members;
	float longitude;
	float latitude;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHop_arrive_time() {
		return hop_arrive_time;
	}
	public void setHop_arrive_time(String hop_arrive_time) {
		this.hop_arrive_time = hop_arrive_time;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getSend_user_name() {
		return send_user_name;
	}
	public void setSend_user_name(String send_user_name) {
		this.send_user_name = send_user_name;
	}
	public String getSend_user_mobile() {
		return send_user_mobile;
	}
	public void setSend_user_mobile(String send_user_mobile) {
		this.send_user_mobile = send_user_mobile;
	}
	public String getReal_arrive_time() {
		return real_arrive_time;
	}
	public void setReal_arrive_time(String real_arrive_time) {
		this.real_arrive_time = real_arrive_time;
	}
	public int getSms_send_state() {
		return sms_send_state;
	}
	public void setSms_send_state(int sms_send_state) {
		this.sms_send_state = sms_send_state;
	}
	public String getSms_send_time() {
		return sms_send_time;
	}
	public void setSms_send_time(String sms_send_time) {
		this.sms_send_time = sms_send_time;
	}
	public int getSend_status() {
		return send_status;
	}
	public void setSend_status(int send_status) {
		this.send_status = send_status;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
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
	
	
	
	
}
