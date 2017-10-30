package com.manji.backstage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.mapper.CodeMapper;
import com.manji.backstage.model.SimpleCode;
import com.manji.backstage.service.CodeService;
@Service
public class CodeServiceImpl implements CodeService{

	
	@Autowired
	private CodeMapper mapper;

	
	public static void setDataSource(){
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
	}
	public static void setManjiSource(){
		DataSourceContextHolder.setDSType(DataSourceType.MANJI);
	}
	
	@Override
	public SimpleCode getCode(String name) {
		
		setDataSource();
		
		SimpleCode code =mapper.getCodeByName(name);
		
		setManjiSource();
		
		return code;
		
		
		
	}

	@Override
	public void updCode(SimpleCode code) {
		setDataSource();
		
		double d =Double.valueOf(code.getVal());
		
		switch(code.getName()){
		case "order" :
			
			int or_rand =(int)(1+Math.random()*(10-0+1));
			
			d+=or_rand;
			
			break;
		case "user":
			int user_rand =(int)(1+Math.random()*(5-0+1));
			
			d+=user_rand;
		
			break;
			
		case "shop":
			
			int shop_rand =(int)(1+Math.random()*(2-0+1));
			d+=shop_rand;
			
			break;
		case "amount":
			int amount_rand =(int)(1+Math.random()*(20000-5000+1));
			double dd =(double)amount_rand/100;
			
			d+=dd;
			
			break;
		default :
			
			break;
		
		}
		code.setVal(d+"");
		
		mapper.updateCodeByName(code);
		setManjiSource();
	}
	
	
	
	
	
	
	
}
