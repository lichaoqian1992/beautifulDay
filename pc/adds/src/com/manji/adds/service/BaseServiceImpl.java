package com.manji.adds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.BaseMapper;
import com.manji.adds.model.Region;

@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseMapper mapper;

	@Override
	public List<Region> getProvince() {

//		mapper.getProvince();
		
		return mapper.getProvince();
	}

	@Override
	public List<Region> getCity(String code) {
		
		code =code+"%";
		return mapper.getCity(code);
	}

	@Override
	public List<Region> getCountry(String code) {
		code =code+"%";
		return mapper.getCountry(code);
	}
	
	
	
	
}
