package com.manji.backstage.model.order;

public class OrderAdvert {
	long id;
	long order_id;
	int advert_banner_id;
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
	public int getAdvert_banner_id() {
		return advert_banner_id;
	}
	public void setAdvert_banner_id(int advert_banner_id) {
		this.advert_banner_id = advert_banner_id;
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
