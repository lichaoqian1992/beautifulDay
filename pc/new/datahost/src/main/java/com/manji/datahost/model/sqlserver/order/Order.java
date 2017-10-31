package com.manji.datahost.model.sqlserver.order;

/**
 * 订单外呼
 * @author Administrator
 *
 */
public class Order {
	
	private String buyer_name;
	private String shop_name;
	private String mobile;
	private String order_no;
	private Integer order_id;
	private Double order_amount;
	private String add_time;
	private Integer express_status;
	private String name;
	private Integer book_back_status;
	private Integer diff_time;
	private Integer is_deliver;
	private String is_involved;
	private Integer count_involved;
	private Integer global_status;
	private Integer business_status;
	public Integer getGlobal_status() {
		return global_status;
	}
	public void setGlobal_status(Integer global_status) {
		this.global_status = global_status;
	}
	public Integer getBusiness_status() {
		return business_status;
	}
	public void setBusiness_status(Integer business_status) {
		this.business_status = business_status;
	}
	public Integer getCount_involved() {
		return count_involved;
	}
	public void setCount_involved(Integer count_involved) {
		this.count_involved = count_involved;
	}
	public String getIs_involved() {
		return is_involved;
	}
	public void setIs_involved(String is_involved) {
		this.is_involved = is_involved;
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
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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
	public Integer getDiff_time() {
		return diff_time;
	}
	public void setDiff_time(Integer diff_time) {
		this.diff_time = diff_time;
	}
	public Integer getIs_deliver() {
		return is_deliver;
	}
	public void setIs_deliver(Integer is_deliver) {
		this.is_deliver = is_deliver;
	}
	
	
}   
    