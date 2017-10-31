package com.manji.data.model.request.user;

import com.manji.data.model.request.common.PageModel;

public class UserPageVo extends PageModel{

	private String status ="";
	private String user_name ="";
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
	
	
}
