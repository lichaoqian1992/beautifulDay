package com.manji.datahost.model.sqlserver.notice;

public class UserNoticeVo extends UserNotice{

	private Integer pageNumber;
	private Integer pageSize;
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
