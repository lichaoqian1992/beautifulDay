package com.manji.advert.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class IndexService {
	/**
	 * 总浏览量/总点击量
	 * @return
	 */
	public Record browseClick(){
		Record record=new Record();
		Integer sumClick=(Db.findFirst("select count(*) sumClick from ad_countrec c where c.type='click'")).get("sumClick");
		record.set("sumClick",sumClick);
		
		List<Record> listRecord =Db.find("select data from ad_countrec where type='view'");
		if(listRecord.size()!=0){
			String[] temp=null;
	        final List<String> stringList = new ArrayList<String>();
	        for(Record order : listRecord){
	            String str = order.get("data");
	            if(!str.equals("")) {
	                temp = str.split("[,]");
	                for (int a = 0; a <= temp.length - 1; a++) {
	                    stringList.add(temp[a]);
	                }
	            }
	        }
	        record.set("sumView",stringList.size());
		}else{
			record.set("sumView",0);
		}
		return record;
	}
	
	/**
	 * 总录入/有效录入
	 * @return
	 */
	public Record inputData(){
		Record record=new Record();
		Integer sumAll=(Db.findFirst("select count(1) sumAll from ad_advertise")).get("sumAll");
		Integer sumData=(Db.findFirst("select count(1) sumData from ad_advertise where ad_state=1")).get("sumData");
		record.set("sumAll",sumAll);
		record.set("sumData",sumData);
		return record;
	}
	
	/**
	 * 录入时间
	 * @return
	 */
	public Record inputTime(){
		Record record=new Record();
		Integer sumYesterday=(Db.findFirst("select count(1) sumYesterday from ad_advertise where DateDiff(dd,ad_ctime,getdate())=1")).get("sumYesterday");
		Integer sumMonth=(Db.findFirst("select count(1) sumMonth from ad_advertise where DateDiff(mm,ad_ctime,getdate())=0")).get("sumMonth");
		Integer sumAll=(Db.findFirst("select count(1) sumAll from ad_advertise")).get("sumAll");
		
		Double sumYesterdayDouble=Double.parseDouble(sumYesterday.toString());
		Double sumMonthDouble=Double.parseDouble(sumMonth.toString());
		if(sumAll!=0){
			record.set("sumYesterdayDouble",(sumYesterdayDouble/sumAll)*100);
			record.set("sumMonthDouble",(sumMonthDouble/sumAll)*100);
		}else{
			record.set("sumYesterdayDouble",0);
			record.set("sumMonthDouble",0);
		}
		
		return record;
	}
	
	/**
	 * 广告总浏览量查询
	 * @return
	 */
	public Page<Record> clickInfoAll(int page,String state,String type,String title,String place,String region){
		StringBuffer sql = new StringBuffer("from ad_advertise a left join ad_count c on a.id=c.ad_id left join ad_place p on p.id=a.ad_place where 1=1 ");
		
		if(!state.equals("")){
			sql.append("and a.ad_state="+state);
		}
		if(!type.equals("")){
			sql.append("and a.ad_type="+type);
		}
		if(!title.equals("")){
			sql.append("and a.ad_title like '%"+title+"%'");
		}
		if(!place.equals("")){
			sql.append("and a.ad_place="+place);
		}
		if(!region.equals("")){
			sql.append("and a.ad_region = '"+region+"'");
		}
		
		System.out.println("++++++="+sql.toString());
		
		return Db.paginate(page, 20, "select a.ad_title,a.ad_region,a.ad_value,a.ad_brief,a.ad_place,a.ad_ctime,a.ad_state,c.ad_view,c.ad_click,p.pl_title,a.ad_type", sql.toString());
	}
}
