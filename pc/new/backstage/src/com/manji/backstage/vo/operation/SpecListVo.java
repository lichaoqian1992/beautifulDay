package com.manji.backstage.vo.operation;

import java.util.List;

import com.manji.backstage.model.oper.Spec;

public class SpecListVo {

	int Id;
	String name;
	List<Spec> specList;
	
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Spec> getSpecList() {
		return specList;
	}
	public void setSpecList(List<Spec> specList) {
		this.specList = specList;
	}
	
	
	
}


	
	
	

