package com.manji.sheet.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseSheetService {

	
	
	
	
	public List<Record> histoty(){
		
		List<Record> list =Db.query("select * from ");
		
		
		return list ;
	}



	
	
	
	
	
	
	
	
}
