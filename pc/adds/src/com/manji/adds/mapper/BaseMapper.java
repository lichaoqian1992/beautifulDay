package com.manji.adds.mapper;

import java.util.List;

import com.manji.adds.model.Region;

public interface BaseMapper {

	
	List<Region>getProvince();
	
	List<Region>getCity(String code);
	
	List<Region>getCountry(String code);
	
	
	
	
}
