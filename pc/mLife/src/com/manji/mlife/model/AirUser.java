package com.manji.mlife.model;

/**
 * 飞机票乘客信息
 * @author gaochao
 * 2016年8月4日下午5:47:40
 * AirUser
 *
 */
public class AirUser {
	
	//单个乘客
	private String name;//乘客姓名
	private String phone;//手机号码
	private String certificates;//证件号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCertificates() {
		return certificates;
	}
	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	

}
