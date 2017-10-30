package com.manji.datahost.model.sqlserver.notice;

public class UserNoticeRead {

	private Integer id;
	private Integer user_id;
	private String user_role_type;
	private Integer user_role_value;
	private String read_time;
	private String from_type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type;
	}
	public Integer getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(Integer user_role_value) {
		this.user_role_value = user_role_value;
	}
	public String getRead_time() {
		return read_time;
	}
	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}
	public String getFrom_type() {
		return from_type;
	}
	public void setFrom_type(String from_type) {
		this.from_type = from_type;
	}
	
}
