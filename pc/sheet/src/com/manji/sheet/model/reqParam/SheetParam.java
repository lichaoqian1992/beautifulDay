package com.manji.sheet.model.reqParam;

public class SheetParam {

	
	private long id;
	
	private String sheet_no;//工单编号
	private String status;//工单状态
	private String sheet_type;//工单类型
	private String subtype_id;//发起人类型id
	private String sheet_from;//工单来源
	private String sponsor_id;//发起人id
	private String sponsor;//发起人姓名
	private String sponsor_type;//发起人类型
	private String sponsor_contact;//发起人联系方式
	private String start_time;//发起时间
	private String sheet_from_message;
	private String sheet_from_id;
	private int priority_level;
	private int is_push;

	
	private int index;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSheet_no() {
		return sheet_no;
	}


	public void setSheet_no(String sheet_no) {
		this.sheet_no = sheet_no;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSheet_type() {
		return sheet_type;
	}


	public void setSheet_type(String sheet_type) {
		this.sheet_type = sheet_type;
	}


	public String getSubtype_id() {
		return subtype_id;
	}


	public void setSubtype_id(String subtype_id) {
		this.subtype_id = subtype_id;
	}


	public String getSheet_from() {
		return sheet_from;
	}


	public void setSheet_from(String sheet_from) {
		this.sheet_from = sheet_from;
	}


	public String getSponsor_id() {
		return sponsor_id;
	}


	public void setSponsor_id(String sponsor_id) {
		this.sponsor_id = sponsor_id;
	}


	public String getSponsor() {
		return sponsor;
	}


	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}


	public String getSponsor_type() {
		return sponsor_type;
	}


	public void setSponsor_type(String sponsor_type) {
		this.sponsor_type = sponsor_type;
	}


	public String getSponsor_contact() {
		return sponsor_contact;
	}


	public void setSponsor_contact(String sponsor_contact) {
		this.sponsor_contact = sponsor_contact;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getSheet_from_message() {
		return sheet_from_message;
	}


	public void setSheet_from_message(String sheet_from_message) {
		this.sheet_from_message = sheet_from_message;
	}


	public String getSheet_from_id() {
		return sheet_from_id;
	}


	public void setSheet_from_id(String sheet_from_id) {
		this.sheet_from_id = sheet_from_id;
	}


	public int getPriority_level() {
		return priority_level;
	}


	public void setPriority_level(int priority_level) {
		this.priority_level = priority_level;
	}


	public int getIs_push() {
		return is_push;
	}


	public void setIs_push(int is_push) {
		this.is_push = is_push;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	
	
	
	
}
