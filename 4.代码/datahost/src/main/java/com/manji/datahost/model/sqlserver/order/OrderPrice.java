package com.manji.datahost.model.sqlserver.order;

/**
 * 订单价格信息
 * @author Administrator
 *
 */
public class OrderPrice {
	
	private Integer id;
	private Double express_fee;
	private Double voucher;
	private Double real_amount;
	private String add_time;
	private String order_no;
	private Double goods_price;
	private Integer status;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Double goods_price) {
		this.goods_price = goods_price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getExpress_fee() {
		return express_fee;
	}
	public void setExpress_fee(Double express_fee) {
		this.express_fee = express_fee;
	}
	public Double getVoucher() {
		return voucher;
	}
	public void setVoucher(Double voucher) {
		this.voucher = voucher;
	}
	public Double getReal_amount() {
		return real_amount;
	}
	public void setReal_amount(Double real_amount) {
		this.real_amount = real_amount;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
}
