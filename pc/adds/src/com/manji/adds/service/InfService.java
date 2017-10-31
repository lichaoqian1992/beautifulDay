package com.manji.adds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface InfService {

	
	String getRegions(String region);
	
	List<Map> getOuterMap(String channel ,String type,String regions );
	
	void addCount(String ad_id);
	

}
