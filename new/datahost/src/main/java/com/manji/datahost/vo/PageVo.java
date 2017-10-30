package com.manji.datahost.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PageVo {

	@NotNull(message = "参数[user_id]不能为空",groups=USERID.class)
	private Integer user_id;
	@NotBlank(message = "参数[type]不能为空",groups=TYPE.class)
	private String type;
	@NotNull(message = "参数[pageNumber]不能为空",groups=PAGENUMBER.class)
	private Integer pageNumber;
	@NotNull(message = "参数[pageSize]不能为空",groups=PAGESIZE.class)
	private Integer pageSize;
	@NotBlank(message = "参数[mobile]不能为空",groups=MOBILE.class)
	private String mobile;
	private String start_time;
	private String end_time;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public interface MOBILE{};  
	public interface PAGENUMBER{};  
	public interface PAGESIZE{};
	public interface TYPE{};
	public interface USERID{};
}
