package com.manji.backstage.model.order;


/**
 * 短信类订单详情 dt_order_sms
 * @author Administrator
 *
 */
public class OrderSms {

	long id;
	long order_id;
	int business_sms_id;
	String title;
	double price;
	int quantity;
	long back_order_id;
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
	public int getBusiness_sms_id() {
		return business_sms_id;
	}
	public void setBusiness_sms_id(int business_sms_id) {
		this.business_sms_id = business_sms_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getBack_order_id() {
		return back_order_id;
	}
	public void setBack_order_id(long back_order_id) {
		this.back_order_id = back_order_id;
	}
	
	
	
	
	
	
}
