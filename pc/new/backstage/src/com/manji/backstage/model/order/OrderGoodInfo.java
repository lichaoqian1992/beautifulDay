package com.manji.backstage.model.order;

public class OrderGoodInfo {

	long id;
	long order_id;
	int express_id;
	String express_no;
	int express_status;
	String express_time;
	String accept_name;
	String post_code;
	String telephone;
	String mobile;
	String email;
	String area;
	String address;
	int is_receipt;
	int is_deliver;

	
	
	public int getIs_receipt() {
		return is_receipt;
	}
	public void setIs_receipt(int is_receipt) {
		this.is_receipt = is_receipt;
	}
	public int getIs_deliver() {
		return is_deliver;
	}
	public void setIs_deliver(int is_deliver) {
		this.is_deliver = is_deliver;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public int getExpress_id() {
		return express_id;
	}
	public void setExpress_id(int express_id) {
		this.express_id = express_id;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public int getExpress_status() {
		return express_status;
	}
	public void setExpress_status(int express_status) {
		this.express_status = express_status;
	}
	public String getExpress_time() {
		return express_time;
	}
	public void setExpress_time(String express_time) {
		this.express_time = express_time;
	}
	public String getAccept_name() {
		return accept_name;
	}
	public void setAccept_name(String accept_name) {
		this.accept_name = accept_name;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
