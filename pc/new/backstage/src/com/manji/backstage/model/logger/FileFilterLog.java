package com.manji.backstage.model.logger;

public class FileFilterLog {

	int id;
	String hash_value;
	String hash_type;
	int request_ip;
	String add_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHash_value() {
		return hash_value;
	}
	public void setHash_value(String hash_value) {
		this.hash_value = hash_value;
	}
	public String getHash_type() {
		return hash_type;
	}
	public void setHash_type(String hash_type) {
		this.hash_type = hash_type;
	}
	public int getRequest_ip() {
		return request_ip;
	}
	public void setRequest_ip(int request_ip) {
		this.request_ip = request_ip;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	
	
	
}
