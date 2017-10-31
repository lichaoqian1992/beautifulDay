package com.manji.adds.service;

import java.util.List;

import com.manji.adds.model.Region;

public interface BaseService {

	
	List<Region>getProvince();
	
	List<Region> getCity(String code);
	
	List<Region> getCountry(String code);
	
	
	
	
}
