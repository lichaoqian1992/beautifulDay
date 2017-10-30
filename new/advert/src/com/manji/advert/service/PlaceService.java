package com.manji.advert.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.param.PlaceParam;

public class PlaceService {

	public Page<Record> getPlacePage(PlaceParam params,int pageNum) {

		StringBuffer sqlBuffer =new StringBuffer("from ad_place where 1=1");
		
		if(params.getState()!=null && !"".equals(params.getState())){
			sqlBuffer.append("and pl_state = '"+params.getState()+"'");
			
		}
		
		if(params.getChannel()!=0){
			sqlBuffer.append("and pl_channel = '"+params.getChannel()+"'");
			sqlBuffer.append("order by pl_sort");
		}
		
		Page<Record> page =Db.paginate(pageNum, 20, "select *", sqlBuffer.toString());
		
		return page;
	}

	
	//根据广告位id查询详情
	
	public Record getPlace(int id){
		
		Record record = Db.findFirst("select * from ad_place where id="+id);
		
		return record;
	}
	
	//保存修改广告位信息
	
	public int updPlace(PlaceParam p){
		
		int count = Db.update("update ad_place set pl_channel=?,pl_sort=?,pl_title=?,pl_max=?,pl_wpercen=?,pl_hpercen=?,pl_state=?,pl_remark=? where id=?", p.getChannel(),p.getSort(),p.getTitle(),p.getMax(),p.getWpercen(),p.getHpercen(),p.getState(),p.getRemark(),p.getId());
		
		return count;
	}
	
	//保存新增频道信息
	
	public boolean addPlace(Record record){
		
		return (Db.save("ad_place", record));
	}
	
	
}
