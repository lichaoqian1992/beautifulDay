package com.manji.backstage.model.base;

import java.util.List;

public class Page<T> {

	int index;//请求页数
	int totalCount;//总条数
	int pageCount;//每页条数
	int pageNum=20;//每页数据数量
	List<T> dataList;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void transform(int totalCount,List<T> dataList){
		
		this.totalCount =totalCount;
		
		if(totalCount%20==0){
			
			this.pageCount =totalCount/20;
			
		}else{
			
			this.pageCount =totalCount/20+1;
			
		}
		
		
		this.dataList =dataList;
		
	}
	
}
