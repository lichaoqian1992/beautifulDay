package com.manji.backstage.model.shop;

public class CompContent {

	long id;
	long complaint_id;
	int type;
	String content;
	String supplement_content;
	String img;
	String time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(long complaint_id) {
		this.complaint_id = complaint_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSupplement_content() {
		return supplement_content;
	}
	public void setSupplement_content(String supplement_content) {
		this.supplement_content = supplement_content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	

	
}
