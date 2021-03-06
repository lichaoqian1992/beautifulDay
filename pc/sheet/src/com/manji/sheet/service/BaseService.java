package com.manji.sheet.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseService {

	/**
	 * 查询工单类型大类，，，层级为1的
	 * @return
	 */
	public List<Record> findSheetType(){
		
		return Db.find("select * from dt_sheet_type where layer=1 order by sort_id");

	}
	/**
	 * 根据工单类型编号禁用或者启用工单类型
	 * @param id       工单类型编号
	 * @param status   启用/禁用
	 * @return
	 */
	public int disable(int id,int status,String table_name){
		
		return Db.update("update "+table_name+" set status=? where id=?",status,id);
		
	}
	/**
	 * 新增工单类型二级分类
	 * @param code       父类的code
	 * @param title		新增分类的标题
	 * @param sort		新增分类的排序
	 * @return
	 */
	public boolean addSheetType(String code,String title,int sort){
		Boolean flag = false;
		String[] pcode = {};
		//1.先判断数据库是否已存在
		Record record = new Record();
		Record sheetType = Db.findFirst("select * from dt_sheet_type where code=?",code);
		//2.查询是否存在该名称的工单类型
		List<Record> list = Db.find("select * from dt_sheet_type where code like '"+code+"_%'");
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).get("title").toString().equals(title)){
					//重复了
					return flag;
				}else{
					pcode = list.get(i).getStr("code").split("_");
				}
			}
		}
		record.set("code", (Integer.parseInt(pcode[1])+1)>9?code+"_"+(Integer.parseInt(pcode[1])+1):code+"_0"+(Integer.parseInt(pcode[1])+1))
		.set("title", title).set("short_title", sheetType.get("short_title")).set("merge_title", sheetType.get("merge_title")+"-"+title)
		.set("layer", 2).set("sort_id", sort).set("status", 1);
		flag = Db.save("dt_sheet_type", record);
		return flag;	
	}
	/**
	 * 根据id修改举报类型信息
	 * @param id
	 * @param title
	 * @param sort
	 * @return
	 */
	public int updateSheetType(int id,String title,int sort){
		String sql = "update dt_sheet_type set title='"+title+"',sort_id="+sort+",merge_title=(select title from dt_sheet_type where short_title=(select short_title from dt_sheet_type where id="+id+") and layer=1)+'-"+title+"' where id="+id;
		
		return Db.update(sql);
	}
	
	public int updateDept(String id,String title,int type){
		String sql = "update dt_base_dept set name='"+title+"',type="+type+" where id="+id;
		
		return Db.update(sql);
	}
	/**
	 * 查询部门信息
	 * @param status      部门状态    启用/禁用
	 * @param type        部门类型    执行/督查
	 * @param name		     部门名称
	 * @return
	 */
	public List<Record> findDept(int status,String type,String name){
		StringBuffer sql = new StringBuffer("select *,convert(varchar(10),type) type,convert(varchar(10),status) status from dt_base_dept where 1=1");
		if(status != 3){
			sql.append(" and status="+status);
		}
		if(type != null && !type.equals("") && !type.equals("3")){
			sql.append(" and type='"+type+"'");
		}	
		if(name != null && !"".equals(name)){
			sql.append(" and name like '%"+name+"%'");
		}
		return Db.find(sql.toString());
	}
}
