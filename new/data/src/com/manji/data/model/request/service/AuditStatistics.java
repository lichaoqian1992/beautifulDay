package com.manji.data.model.request.service;

import com.manji.data.model.request.common.PageModel;

public class AuditStatistics extends PageModel{

	private long id;
	private String examine_name;
	private String examine_time;
	private Integer examine_type;
	private Integer is_adopt;
	private Integer pageNumber;
	private String start_time;
	private String end_time;
	
	
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
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExamine_name() {
		return examine_name;
	}
	public void setExamine_name(String examine_name) {
		this.examine_name = examine_name;
	}
	public String getExamine_time() {
		return examine_time;
	}
	public void setExamine_time(String examine_time) {
		this.examine_time = examine_time;
	}
	public Integer getExamine_type() {
		return examine_type;
	}
	public void setExamine_type(Integer examine_type) {
		this.examine_type = examine_type;
	}
	public Integer getIs_adopt() {
		return is_adopt;
	}
	public void setIs_adopt(Integer is_adopt) {
		this.is_adopt = is_adopt;
	}
	
	
	
}
