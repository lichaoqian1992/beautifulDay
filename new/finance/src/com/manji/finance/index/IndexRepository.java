package com.manji.finance.index;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

public class IndexRepository extends DButils{
	
	public List<Record> findUser(String userName,String password){
		
		return mysql.find("select * from t_user where USERNAME=? and PASSWORD=?",userName,password);
	}
	/**
	 * 查询权限页面
	 * @param userName
	 * @return
	 */
	public List<Record> findMenu(String userName){
		
		return mysql.find("select * from t_menu_role,t_menu b  where role_id=(select T_CONFIG_ID from t_user where USERNAME='"+userName+"') and menu_id=b.id order by sort");
	}

}
