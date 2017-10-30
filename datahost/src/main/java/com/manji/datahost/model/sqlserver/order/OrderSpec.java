package com.manji.datahost.model.sqlserver.order;

/**
 * 订单的规格信息
 * @author Administrator
 *
 */
public class OrderSpec {

	private Integer order_id;
	private String img_url;
	private String goods_title;
	private String spec_text;
	private Double real_price;
	private Integer quantity;
	private Double all_money;
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getGoods_title() {
		return goods_title;
	}
	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}
	public String getSpec_text() {
		return spec_text;
	}
	public void setSpec_text(String spec_text) {
		this.spec_text = spec_text;
	}
	public Double getReal_price() {
		return real_price;
	}
	public void setReal_price(Double real_price) {
		this.real_price = real_price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAll_money() {
		return all_money;
	}
	public void setAll_money(Double all_money) {
		this.all_money = all_money;
	}
	
}
