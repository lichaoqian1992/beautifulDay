package com.manji.datahost.service.mysql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.mysql.OtherInfoMapper;
import com.manji.datahost.model.mysql.OtherInfo;
import com.manji.datahost.service.mysql.OtherInfoService;

@Service
public class OtherInfoServiceImpl implements OtherInfoService{
	
	@Autowired
	private OtherInfoMapper mapper;

	@Override
	public OtherInfo getOtherInfo(String mobile) {

		return mapper.getOtherInfo(mobile);
	}


}
