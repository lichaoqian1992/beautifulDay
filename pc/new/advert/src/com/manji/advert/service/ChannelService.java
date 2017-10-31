package com.manji.advert.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.param.ChannelParam;

public class ChannelService {

	public Page<Record> getChannelPage(ChannelParam params,int page) {

		StringBuffer sqlBuffer =new StringBuffer("from ad_channel where 1=1 ");
		
		if(params.getTitle()!=null&&!"".equals(params.getTitle())){
			sqlBuffer.append("and ch_title like '%"+params.getTitle()+"%'");
		}
		
		if(params.getPort()!=null&&!"".equals(params.getPort())){
			sqlBuffer.append("and port ='"+params.getPort()+"'");
		}
		
		if(params.getState()!=null&&!"".equals(params.getState())){
			sqlBuffer.append("and ch_state ='"+params.getState()+"'");
		}
		
		sqlBuffer.append("order by id");
		
		Page<Record> list =Db.paginate(page, 20, "select *", sqlBuffer.toString());
		
		
		
		
		return list;
	}

	public List<Record> getChannelList() {
		
		List<Record> list =Db.find("select id,ch_title from ad_channel order by id");
		
		return list;
	}
	
	//根据频道id查询详情
	
	public Record getChannelDetails(int id){
		
		Record record = Db.findFirst("select * from ad_channel where id="+id);
		
		return record;
		
	}

	//保存修改频道信息
	
	public int updChannel(ChannelParam c){
		
		int count = Db.update("update ad_channel set ch_name=?,ch_title=?,ch_sort=?,port=?,ch_layer=?,ch_state=?,ch_flag=?,ch_mode=?,ch_foot=?,ch_remark=? where id=?",c.getName(),c.getTitle(),c.getSort(),c.getPort(),c.getLayer(),c.getState(),c.getFlag(),c.getMode(),c.getFoot(),c.getRemark(),c.getId() );
		
		return count;
	}
	
	//保存新增频道信息
	
	public boolean addChannel(Record record){
		
		return (Db.save("ad_channel", record));
		
	}
}
