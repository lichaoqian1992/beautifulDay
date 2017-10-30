package com.manji.data.model.request.service;

import com.manji.data.model.request.common.PageModel;

/**
 * 店铺信息
 * @author Administrator
 *
 */
public class ScopePageVo extends PageModel{

	private String name;
	private String audit_name;
	private Integer pageNumber;
	private String start_time;
	private String end_time;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAudit_name() {
		return audit_name;
	}
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
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
	
	
	
}
