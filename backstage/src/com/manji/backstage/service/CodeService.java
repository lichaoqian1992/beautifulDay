package com.manji.backstage.service;

import com.manji.backstage.model.SimpleCode;

public interface CodeService {

	SimpleCode getCode(String name);
	
	void updCode(SimpleCode code);
	
	
	
}
