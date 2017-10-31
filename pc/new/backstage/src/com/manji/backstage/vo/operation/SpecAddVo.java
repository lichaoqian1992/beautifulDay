package com.manji.backstage.vo.operation;

import java.util.List;

public class SpecAddVo {

	int category_id;
	String category_name;
//	List<SpecVo> specList;
	String json;
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
//	public List<SpecVo> getSpecList() {
//		return specList;
//	}
//	public void setSpecList(List<SpecVo> specList) {
//		this.specList = specList;
//	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	
}
