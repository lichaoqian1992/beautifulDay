package com.manji.singleSign.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.Code_Remark;
import com.manji.singleSign.model.Result;

public class LoginService {

	public Result checkUserLogin(String userName, String password) {
		
		Result r =new Result();
		
		Record rec =Db.findFirst("select * from sso_users where username ='"+userName+"'");
		
		if(rec ==null){
			r.setCode("0101");
			r.setData(Code_Remark.SSO_CODE_0101);
			
		}else{
			
			
			String recPassword =rec.get("password");
			
			if(!password.equals(recPassword)){
				//用户账户和密码错误
				r.setCode("0102");
				r.setData(Code_Remark.SSO_CODE_0102);
				
			}else{
				//登陆成功
				
				
				
				r.setCode("0000");
				r.setData(rec);
			}
			
		}
		
		
		
		
		
		return r;
	}
	
	
	public Record getRole(String name){
		
		return Db.findFirst("select * from sso_roles where name=? and sys_id=4",name);
	}

}
