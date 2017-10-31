package com.manji.datahost.vo;

import javax.validation.constraints.NotNull;

public class UserLoginVo {

	@NotNull(message = "参数[pageNumber]不能为空")
	private Integer pageNumber;
	@NotNull(message = "参数[pageSize]不能为空")
	private Integer pageSize;
	private String start_time;
	private String end_time;
	@NotNull(message = "参数[user_id]不能为空")
	private Integer user_id;
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
}
