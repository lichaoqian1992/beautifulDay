package com.manji.backstage.model.order;

/**
 * 预定类订单详情  dt_order_bookinfo
 * @author huanghan
 *
 */
public class OrderBookInfo {

	
	long id;
	long order_id;
	String name;
	String mobile;
	String in_time;
	String out_time;
	int members;
	String info;
	int sms_send_state;
	String sms_send_time;
	
	
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
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getOut_time() {
		return out_time;
	}
	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	
	
	
}
