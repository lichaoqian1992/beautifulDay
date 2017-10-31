package com.manji.data.model.request.service;

import com.manji.data.model.request.common.PageModel;

/**
 * 商家信息
 * @author Administrator
 *
 */
public class ShopPageVo extends PageModel{

	private Integer user_id;
	private String name;
	private String msg_mobile;
	private String remark;
	private String add_time;
	private String type;
	private String area;
	private String audit_name;
	private String business_name;
	private String input_name;
	private String storeName;
	private String file_url;
	private String update_time;
	private Integer pageNumber;
	private String start_time;
	private String end_time;
	private Integer status;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg_mobile() {
		return msg_mobile;
	}
	public void setMsg_mobile(String msg_mobile) {
		this.msg_mobile = msg_mobile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAudit_name() {
		return audit_name;
	}
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getInput_name() {
		return input_name;
	}
	public void setInput_name(String input_name) {
		this.input_name = input_name;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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
