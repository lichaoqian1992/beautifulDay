package com.manji.mlife.service;

import java.util.List;
import java.util.Map;

import com.manji.mlife.model.SimpleCode;

public interface LoginService {

	String getUserUrl();
	
	boolean isModelOn(String modelName);

	List<SimpleCode> queryAll();

	void updateStatus(String value,String codename);
	
	Map<String, String> getUserInfoBySession(String sessionId);
}
