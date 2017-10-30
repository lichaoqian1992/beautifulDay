package com.manji.datahost.model.sqlserver.activities;

/**
 * 新人礼包
 * @author Administrator
 *
 */


public class PeopleActivities {

	private String Edition;
	private String Desc;
	private Integer Code;
	private boolean IsSuccess;
	private Object Data;
	
	public String getEdition() {
		return Edition;
	}
	public void setEdition(String edition) {
		Edition = edition;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public Integer getCode() {
		return Code;
	}
	public void setCode(Integer code) {
		Code = code;
	}
	public boolean isIsSuccess() {
		return IsSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		IsSuccess = isSuccess;
	}
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	
	
}
