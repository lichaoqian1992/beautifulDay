package com.manji.adds.service;

import java.util.List;
import java.util.Map;

import com.manji.adds.model.Adds;

public interface AddsService {

	String getSeqNo();
	
	void addAdds(Adds adds);
	
	Adds queryAdds(String ad_id);
	
	boolean updAdds(Adds adds);
	
	List<Adds> queryAddsList(Adds queryMap); 
	
	boolean updState(String ad_id,String state);
	
	Map<String,String> getCounts();
	
}
