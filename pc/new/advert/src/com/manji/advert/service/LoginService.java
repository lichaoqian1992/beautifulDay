package com.manji.advert.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class LoginService {

	public List<Record> findUser(String userName,String password){
		
		List<Record> user  = Db.use("circle").find("select * from ad_user where password=? and user_name=?",password,userName);
		return user;
		
	}
	/**
	 * 查询菜单
	 * @return
	 */
	public List<Record> findMenu(){
		
		List<Record> list = Db.use("circle").find("select * from ad_menu a inner join ad_menu_role b on a.type='main' and a.id=b.menu_id  order by a.id");
		 if(list != null && list.size()>0){
			 return list;
		 }
		return null;
	}
}
