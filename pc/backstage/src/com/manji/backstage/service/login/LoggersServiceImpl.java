package com.manji.backstage.service.login;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;

import com.manji.backstage.mapper.login.LoggersMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.vo.login.LoggersVo;
@Service
public class LoggersServiceImpl implements LoggersService {
	
	@Autowired
	private LoggersMapper mapper;

	public static void setDataSource(){
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
	}
	
	public static void setManjiSource(){
		DataSourceContextHolder.setDSType(DataSourceType.MANJI);
	}

	@Override
	public void addLoggers(Loggers log) {
		setDataSource();
		mapper.addLoggers(log);
		setManjiSource();
	}

	@Override
	public Loggers getLoggers(int id) {

		setDataSource();
		
		Loggers log =mapper.getLoggers(id);
		setManjiSource();
		return log;
	}

	@Override
	public Page<Loggers> queryLoggers(LoggersVo vo) {
		
		Page<Loggers> page =new Page<Loggers>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		setDataSource();
		List<Loggers> dataList =mapper.queryLoggers(vo);
		int totalCount =mapper.countLoggers(vo);
		
		page.transform(totalCount, dataList);
		setManjiSource();
		
		return page;
	}

	@Override
	public Data addData(String json) {
		setDataSource();
		Data d =new Data();
		d.setJson(json);
		mapper.addData(d);
		setManjiSource();
		return d;
	}

	@Override
	public Data getData(int id) {
		setDataSource();
		Data d =mapper.getData(id);
		setManjiSource();
		return d;
	}
	

	
}
