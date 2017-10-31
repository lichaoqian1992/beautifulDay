package com.manji.adds.mapper;

import java.util.List;

import com.manji.adds.model.AddsBean;
import com.manji.adds.model.InfBean;
import com.manji.adds.model.Place;

public interface InfMapper {

	
	List<InfBean> getAddsById(AddsBean bean);
	
	List<InfBean> getAddsById2(AddsBean bean);
	
	List<Place> getPlaces(Place queryMap);
	
	void addCount(String ad_id);
	
	List queryCounts();
	
	void addCounts(String ad_id,int count);
	
	boolean deleteJnl();
	
	
}
