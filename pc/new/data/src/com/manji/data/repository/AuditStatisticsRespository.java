package com.manji.data.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class AuditStatisticsRespository {

	
	/**
	 * 当天时间
	 * @return
	 */
	public String calendarDate(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,0);
		String today = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		return today;
	}
	
	/**
	 * 统计商家审核信息
	 * @return
	 */
	public List<Record> shopAudit(){
		
		String sql = "select remark,update_time from dt_user_role_shopinfo where remark like '%Time%' and convert(varchar,update_time,120) like '%"+calendarDate()+"%'";
/*		String sql = "select remark,update_time from dt_user_role_shopinfo order by id desc";
*/		
		List<Record> record	= Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计入驻和店铺审核信息
	 * @return
	 */
	public List<Record> enterAudit(){
		
		String sql = "select remark,update_time from dt_user_role_shopinfo_temp where remark like '%Time%' and convert(varchar,update_time,120) like '%"+calendarDate()+"%'";
		
		//where convert(varchar,update_time,120) like '%2016-08-31%'
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计商品审核信息
	 * @return
	 */
	public List<Record> goodsAudit(){
		
		String sql = "select remark,update_time from dt_article  where remark like '%Time%' and convert(varchar,update_time,120) like '%"+calendarDate()+"%'";
		
		List<Record> record	= Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计公司审核信息
	 * @return
	 */
	public List<Record> companyAudit(){
		
		String sql = "select update_remark,update_time from dt_user_safeprotect where safe_type='COMPANY' and update_remark like '%Time%' and convert(varchar,update_time,120) like '%"+calendarDate()+"%'";
		
		List<Record> record = Db.find(sql);
		
		return record; 
	}
	
	/**
	 * 统计经营范围  
	 * @return
	 */
	public List<Record> scopeAudit(){
		
		String sql = "select remark from dt_user_role_shopinfo_scope  where remark like '%Time%' and convert(varchar,update_time,120) like '%"+calendarDate()+"%'";
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
	
	/**
	 * 测试查询当天的信息
	 * @return
	 */
	public List<Record> testAudit(){
		
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar1.add(Calendar.DATE, 1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		String tomarrow = new SimpleDateFormat("yyyy-MM-dd").format(calendar1.getTime());
//		String yesterday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		
		String aa = "2017-05-30";
		String bb = "2017-06-06";
		
		String sql = "select remark,update_time from dt_user_role_shopinfo_temp where update_time > '"+aa+"'"+" and update_time < '"+bb+"' order by id desc";
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
	
	/**
	 * 审核统计
	 * @param as
	 * @param select
	 * @return
	 */
	public Page<Record> auditInfo(Integer pageNumber,Integer size,String select){
		
		String sql = "select distinct t.*,p.person_name ";
		
		Page<Record> page = Db.paginate(pageNumber, size, sql, select);
		
		return page;
	}
	
	/**
	 * 统计审核
	 * @param pageNumber
	 * @param size
	 * @param select
	 * @return
	 */
	public Page<Record> auditSta(Integer pageNumber,Integer size,String select){
		
		String sql = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, size, sql, select);
		
		return page;
		
	}
	
}
