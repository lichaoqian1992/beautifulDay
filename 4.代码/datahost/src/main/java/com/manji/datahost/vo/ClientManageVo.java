package com.manji.datahost.vo;

import javax.validation.constraints.NotNull;

public class ClientManageVo {

	@NotNull(message = "参数[pageSize]不能为空")
	private Integer pageSize;
	@NotNull(message = "参数[pageNumber]不能为空")
	private Integer pageNumber;
	private String reg_start_time;
	private String reg_end_time;
	private String login_start_time;
	private String login_end_time;
	private String key_word;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getReg_start_time() {
		return reg_start_time;
	}
	public void setReg_start_time(String reg_start_time) {
		this.reg_start_time = reg_start_time;
	}
	public String getReg_end_time() {
		return reg_end_time;
	}
	public void setReg_end_time(String reg_end_time) {
		this.reg_end_time = reg_end_time;
	}
	public String getLogin_start_time() {
		return login_start_time;
	}
	public void setLogin_start_time(String login_start_time) {
		this.login_start_time = login_start_time;
	}
	public String getLogin_end_time() {
		return login_end_time;
	}
	public void setLogin_end_time(String login_end_time) {
		this.login_end_time = login_end_time;
	}
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}
	
}
