package com.manji.datahost.model.sqlserver.order;

/**
 * 订单用户商家信息
 * @author Administrator
 *
 */
public class OrderDetail {
	
	private Integer order_id;
	private String accept_name;
	private String mobile;
	private String address;
	private String user_name;
	private Integer shop_user_id;
	private String shop_name;
	private String shop_mobile;
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getAccept_name() {
		return accept_name;
	}
	public void setAccept_name(String accept_name) {
		this.accept_name = accept_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getShop_user_id() {
		return shop_user_id;
	}
	public void setShop_user_id(Integer shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_mobile() {
		return shop_mobile;
	}
	public void setShop_mobile(String shop_mobile) {
		this.shop_mobile = shop_mobile;
	}
	
}
