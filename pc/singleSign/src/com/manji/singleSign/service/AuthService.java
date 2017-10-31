package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.utils.DatesUtils;
import com.manji.singleSign.utils.MD5util;

public class AuthService {

	public String generateToken(Record rec) {

		String currentTime =DatesUtils.time();
		
		String token =MD5util.getMD5(rec.get("username")+currentTime);
		
		Record tokenRec =new Record();
		
		tokenRec.set("token",token).set("user_id", rec.getInt("id")).set("time", currentTime).set("count", 0);
		
		Db.save("sso_token", tokenRec);
		
		return token;
	}

	public Record getTokenInfo(String token) {

		Record rec =Db.findFirst("select * from sso_token where token='"+token+"'");
		
		return rec;
	}

	public Record getUserInfo(int  user_id) {

		Record rec =Db.findFirst("select u.id user_id,u.username ,d.dept_name,d.id dept_id ,u.status status from sso_users u,sso_dept d where u.dept_id =d.id and u.id="+user_id);
		
		return rec;
	}

	public Record getSystemInfo(String sysName) {

		Record rec =Db.findFirst("select * from sso_system where name ='"+sysName+"'");
		
		return rec;
	}

	public Record getRoleInfo(int user_id, int sys_id) {

		Record rec =Db.findFirst("select r.* from sso_user_role a,sso_roles r where a.sys_id="+sys_id+" and a.user_id="+user_id+" and a.role_id =r.id");
		
		return rec;
	}
	
	public List<Record> getMenuInfo(int role_id){
		
		List<Record> list =Db.find("select m.* from sso_menu m,sso_role_menu a where  a.role_id ="+role_id+" and a.menu_id =m.id and m.is_del !='1' ");
		
		return list;
	}

}
