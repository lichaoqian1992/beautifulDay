package com.manji.advert.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.Logs;

public class LogsService {
	
	/**
	 * 保存日志
	 * @param l
	 * @return
	 */
	public int saveLogs(Logs l){
		int a = 0;
		if(l != null){
			a = Db.use("circle").update("insert into ad_logs values(?,?,?,?,?,?)",l.getUserName(),l.getContent(),l.getCreateTime(),l.getBusinessName(),l.getBusinessId(),l.getOperationType());
		}
		return a;
	}
	/**
	 * 查询日志信息
	 * @return
	 */
	public Page<Record> queryLogs(String userName,String businessName,String operationType,int page){
		StringBuffer sql = new StringBuffer(" from ad_logs where 1=1");
		if(!userName.equals("")){
			sql.append(" and user_name='"+userName+"'");
		}
		if(!businessName.equals("")){
			sql.append(" and business_name like '%"+businessName+"%'");
		}
		if(!operationType.equals("")){
			if(!operationType.equals("其他")){
				sql.append(" and operation_type = '"+operationType+"'");
			}else{
				sql.append(" and operation_type = ''");
			}
		}
		sql.append(" order by create_time desc");
		return Db.use("circle").paginate(page, 20, "select *", sql.toString());
		
	}
	/**
	 * 查询当前登录人最近的10条操作记录
	 * @return
	 */
	public List<Record> findLogs(String userName){
		
		return Db.use("circle").find("select top 10 * from ad_logs where user_name=? order by create_time desc",userName);
		
	}
	
}
