package com.manji.datahost.model.sqlserver.client;
/**
 * 退单
 * @author Administrator
 *
 */
public class UserBack {

	private String name;
	private String mobile;
	private String order_no;
	private Double real_back_amount;
	private Integer back_goods;
	private Integer status;
	private String add_time;
	private Integer order_id;
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
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Double getReal_back_amount() {
		return real_back_amount;
	}
	public void setReal_back_amount(Double real_back_amount) {
		this.real_back_amount = real_back_amount;
	}
	public Integer getBack_goods() {
		return back_goods;
	}
	public void setBack_goods(Integer back_goods) {
		this.back_goods = back_goods;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
}
