package com.manji.finance.system;

public class pageDo{
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	
	/*当前页*/
	private int pageNumber;
	
	/*总记录数*/
	private int allPageNumber;

	/*每一页显示页数*/
	private int pageSize;
	
	/*总页数*/
	private int numberPages;
	

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getAllPageNumber() {
		return allPageNumber;
	}

	public void setAllPageNumber(int allPageNumber) {
		this.allPageNumber = allPageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
