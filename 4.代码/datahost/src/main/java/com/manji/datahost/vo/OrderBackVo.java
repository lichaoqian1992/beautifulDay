package com.manji.datahost.vo;

import javax.validation.constraints.NotNull;

/**
 * 退单
 * @author Administrator
 *
 */
public class OrderBackVo {
	
	@NotNull(message = "参数[pageNumber]不能为空")
	private Integer pageNumber;
	@NotNull(message = "参数[pageSize]不能为空")
	private Integer pageSize;
	private Integer back_goods;
	private Integer status;
	private String start_time;
	private String end_time;
	private String order_no;
	@NotNull(message = "参数[user_id]不能为空")
	private Integer user_id;
	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
}
