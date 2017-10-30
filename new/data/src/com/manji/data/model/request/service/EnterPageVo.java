package com.manji.data.model.request.service;

import com.manji.data.model.request.common.PageModel;

/**
 * 商家入驻信息
 * @author Administrator
 *
 */
public class EnterPageVo extends PageModel{

	private Integer state;
	private String start_time;
	private String end_time;
	private String shop_name;
	private String business_mobile;
	private String msg_mobile;
	private String mobile;
	private String user_name;
	private String storeName;
	private String audit_name;
	private String remark;
	private Integer pageNumber;
	
	
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getBusiness_mobile() {
		return business_mobile;
	}
	public void setBusiness_mobile(String business_mobile) {
		this.business_mobile = business_mobile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAudit_name() {
		return audit_name;
	}
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getMsg_mobile() {
		return msg_mobile;
	}
	public void setMsg_mobile(String msg_mobile) {
		this.msg_mobile = msg_mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
