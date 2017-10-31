package com.manji.finance.home.model;

public class InformationDo{
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	private double allmoney;
	
	private String Name;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(double allmoney) {
		this.allmoney = allmoney;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	

}
