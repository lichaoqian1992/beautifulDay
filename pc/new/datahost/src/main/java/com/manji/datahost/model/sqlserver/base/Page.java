package com.manji.datahost.model.sqlserver.base;

import java.util.List;

public class Page<T> {
	
	private Integer pageNumber;
	private Integer totalCount;
	private Integer pageCount;
	List<T> dataList;
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void transform(Integer totalCount,Integer pageSize,List<T> dataList){
		
		this.totalCount =totalCount;
		
		if(totalCount%pageSize==0){
			
			this.pageCount =totalCount/pageSize;
			
		}else{
			
			this.pageCount =totalCount/pageSize+1;
			
		}
		
		this.dataList =dataList;
		
	}
}
