package com.manji.datahost.model.sqlserver.user;

/**
 * 订单信息
 * @author Administrator
 *
 */
public class UserOrder {
	
	private Integer order_id;
	private String buyer_name;
	private String shop_name;
	private String mobile;
	private String order_no;
	private Double order_amount;
	private String add_time;
	private Integer express_status;
	private Integer status;
	private String name;
	private Integer book_back_status;
	private Integer payment_status;
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(Integer payment_status) {
		this.payment_status = payment_status;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public Integer getExpress_status() {
		return express_status;
	}
	public void setExpress_status(Integer express_status) {
		this.express_status = express_status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBook_back_status() {
		return book_back_status;
	}
	public void setBook_back_status(Integer book_back_status) {
		this.book_back_status = book_back_status;
	}
	
}
