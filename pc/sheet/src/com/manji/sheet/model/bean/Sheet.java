package com.manji.sheet.model.bean;


/**
 * 工单主表
 * @author Administrator
 *sheet
 */
public class Sheet {
	
	private long id;
	private String sheet_no;
	private int status;
	private int type_id;
	private int subtype_id;
	private String sponsor;
	private int sponsor_type;
	private String sponsor_contact;
	private String start_time;
	private int source_id;
	private String message;
	private int priority_level;
	private int is_push;
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getSubtype_id() {
		return subtype_id;
	}
	public void setSubtype_id(int subtype_id) {
		this.subtype_id = subtype_id;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public int getSponsor_type() {
		return sponsor_type;
	}
	public void setSponsor_type(int sponsor_type) {
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
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	

}
