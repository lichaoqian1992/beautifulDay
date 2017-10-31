package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.bean.Sys;

public class SystemService extends BaseService {

	public List<Record> systemList() {

		List<Record> list = Db.find("select * from sso_system where status =1 order by id");

		return list;

	}

	public void addSystem(Sys s) {

		Record r = new Record();

		r.set("system_name", s.getSystem_name()).set("name", s.getName()).set("status", 1);

		Db.save("sso_system", r);

	}

	public void updSystem(Sys s) {

		Db.update("update sso_system set system_name ='" + s.getSystem_name() + "' ,name='" + s.getName()
				+ "' ,status='" + s.getStatus() + "' where id='" + s.getId() + "'");

	}
	
	public Page<Record> query(String id,int pageNum,String name){
		StringBuffer sql = new StringBuffer("from sso_system where 1=1");
		if(!id.isEmpty()){
			sql.append(" and id='"+id+"'");
		}
		if(!name.isEmpty()){
			sql.append(" and name like '%"+name+"%' or system_name like '%"+name+"%'");
		}
		return Db.paginate(pageNum, 20, "select *", sql.toString());
	}

}
