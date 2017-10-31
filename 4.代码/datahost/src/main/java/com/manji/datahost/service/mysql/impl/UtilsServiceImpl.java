package com.manji.datahost.service.mysql.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.mysql.UtilsMapper;
import com.manji.datahost.model.mysql.CallRegister;
import com.manji.datahost.model.mysql.SheetInfo;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.service.mysql.UtilsService;

@Service
public class UtilsServiceImpl implements UtilsService{
	
	@Autowired
	private UtilsMapper mapper;

	@Override
	public Page<CallRegister> callRegister(Integer pageNumber, Integer pageSize, String mobile) {
		
		Page<CallRegister> page = new Page<CallRegister>();
		page.setPageNumber(pageNumber/pageSize+1);
		int count = mapper.countCallRegister(mobile);
		List<CallRegister> list = mapper.callRegister(pageNumber,pageSize,mobile);
		page.transform(count, pageSize, list);
		return page;
	}

	@Override
	public Page<SheetInfo> sheetInfo(String mobile, Integer pageNumber, Integer pageSize) {
		
		Page<SheetInfo> page = new Page<SheetInfo>();
		page.setPageNumber(pageNumber);
		int count = mapper.countSheetInfo(mobile);
		List<SheetInfo> list = mapper.sheetInfo(mobile, pageNumber, pageSize);
		page.transform(count, pageSize, list);
		return page;
	}

	@Override
	public Integer isInvolved(Integer order_id) {

		return mapper.isInvolved(order_id);
	}


}
