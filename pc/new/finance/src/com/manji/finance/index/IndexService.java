package com.manji.finance.index;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DecriptUtil;

public class IndexService {
	
	IndexRepository index = new IndexRepository();
	
	/**
	 * 校验登录
	 * @param userName
	 * @param password
	 * @return 
	 */
	public List<Record> findUser(String userName,String password){
		
		//加密密码
		String newPass = DecriptUtil.SHA1(password);
		
		return index.findUser(userName, newPass);
	
	}
	/**
	 * 查询权限页面
	 * @param userName
	 * @return
	 */
	public List<Record> findMenu(String userName){
		
		return index.findMenu(userName);
	}

}
