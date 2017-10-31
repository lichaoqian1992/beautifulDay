package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseService {

	
	public List<Record> getDeptList(){
		
		
		List<Record> list =Db.find("select id,dept_name from sso_dept where status =1 order by id");
		
		return list;
	}
	
	public List<Record> getSysList(){
		
		List<Record> list =Db.find("select id,system_name from sso_system where status =1 order by id");
		
		return list;
		
	}
	
	
	
	
}
