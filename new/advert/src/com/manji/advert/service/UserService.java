package com.manji.advert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.User;
import com.manji.advert.utils.MD5util;

public class UserService {

	/**
	 * 修改用户信息
	 * @param userName
	 * @return
	 */
	public int updateUser(User u){
		StringBuffer sql = new StringBuffer("update ad_user set");
		if(u != null){
			if(!u.getPassword().equals("")){
				sql.append(" password='"+u.getPassword()+"',");
			}
			if(!(u.getCount()+"").equals("")){
				sql.append(" login_count="+u.getCount()+",");
			}
		}
		sql.append(" create_time='"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'");
		if(!u.getUserName().equals("")){
			sql.append(" where user_name='"+u.getUserName()+"'");
		}
		System.out.println(sql.toString());
		return Db.use("circle").update(sql.toString());
	}
	/**
	 * 查询用户信息
	 * @return
	 */
	public List<Record> findUser(){
		
		return Db.use("circle").find("select * from ad_user where 1=1");
		
	}
	/**
	 * 保存用户
	 * @return
	 */
	public int saveUser(User u){
		StringBuffer sql = new StringBuffer("insert into ad_user values(");
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		if(Db.use("circle").find("select * from ad_user where user_name=?",u.getUserName()).size()>0){
			return 0;
		}else{
			if(u != null){
				if(!u.getUserName().equals("")){
					sql.append("'"+u.getUserName()+"'");
				}
				if(!u.getPassword().equals("")){
					sql.append(",'"+MD5util.getMD5(u.getPassword())+"'");
				}
				sql.append(",'"+d+"',0)");
			}
			return Db.use("circle").update(sql.toString());	
		}
	}
	/**
	 * 删除用户
	 * @return
	 */
	public int delUser(String id){
		
		
		return Db.use("circle").update("delete from ad_user where id=?",id);
		
	}
	/**
	 * 修改用户
	 * @return
	 */
	public int updateUser(String password,String userName){
		
		String newPassword = MD5util.getMD5(password);
		
		return Db.use("circle").update("update ad_user set password=? where user_name=?",newPassword,userName);
		
	}
}
