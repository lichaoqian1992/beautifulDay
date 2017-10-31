package com.manji.data.model.request.service;

import com.manji.data.model.request.common.PageModel;

/**
 * 店铺信息
 * @author Administrator
 *
 */
public class OrderPageVo extends PageModel{

	private String start_time;
	private String end_time;
	private String order_no;
	private String shop_name;
	private String express_status;
	private String status;
	private String name;
	private String back_status;
	private String mobile;
	private String buyer_name;
	private Integer pageNumber;
	
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBack_status() {
		return back_status;
	}
	public void setBack_status(String back_status) {
		this.back_status = back_status;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getExpress_status() {
		return express_status;
	}
	public String getStatus() {
		return status;
	}
	public void setExpress_status(String express_status) {
		this.express_status = express_status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
}
