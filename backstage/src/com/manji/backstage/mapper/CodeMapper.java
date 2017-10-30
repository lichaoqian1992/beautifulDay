package com.manji.backstage.mapper;

import javax.annotation.Resource;

import com.manji.backstage.model.SimpleCode;
@Resource
public interface CodeMapper {

	SimpleCode getCodeByName(String name);
	
	void updateCodeByName(SimpleCode code);
	
}
