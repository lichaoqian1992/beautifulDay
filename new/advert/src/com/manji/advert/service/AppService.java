package com.manji.advert.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.ChannelData;
import com.manji.advert.model.PlaceData;

import net.sf.json.JSONArray;

public class AppService {

	public String getLocation(String loc) {
		
		String locStr ="";
		
		switch(loc.length()){
		
		case 6:
			
			locStr +="'00',";
			locStr +="'"+loc.substring(0, 2)+"',";
			locStr +="'"+loc.substring(0, 4)+"',";
			locStr +="'"+loc+"'";
			
			break;
		case 4:
			
			locStr +="00,";
			locStr +=loc.substring(0, 2)+",";;
			locStr +=loc;
			
			break;
			
		case 2:
			
			locStr +="00,";
			locStr +=loc;
			
			break;
		
		default :
		
			locStr ="00";
		}
		
		
		
		
		
		return locStr;
	}

	public ChannelData getChannelData(String loc, String name) {

		ChannelData cd =new ChannelData();
		
		Record channel =Db.findFirst("select * from ad_channel where ch_name =? and ch_state =1",name);
		if(channel != null){
			
			cd.setChannel(channel);
			
			
			
			List<Record> placeList =Db.find("select * from ad_place where pl_channel =? and pl_state=1 order by cast(pl_sort as integer)",channel.get("id"));
			
			List<PlaceData> places =new ArrayList<PlaceData>();
			
			for(int i =0;i <placeList.size();i++){
				
				PlaceData pd =new PlaceData();
				
				Record tPlace =placeList.get(i);
				
				pd.setPlace(tPlace);
				
//				List<Record> advertises =Db.find("select * from ad_advertise where ad_place =? and ad_state='1' and ad_code in (?)",tPlace.get("id")+"",loc);
				List<Record> advertises =Db.find("select top "+tPlace.getStr("pl_max")+" * from ad_advertise where ad_place ="+tPlace.get("id")+" and ad_state =1 and ad_code in ("+loc+") order by ad_sort");
				pd.setAdvertises(advertises);
				
				places.add(pd);
			}
			
			cd.setPlaces(places);
		}
		
		return cd;
	}

	public List<Record> getChannelList() {

		List<Record> list =Db.find("select ch_name from ad_channel where port ='APP' and ch_layer='1' and ch_state='1' order by cast(ch_sort as integer)");
		
		
		return list;
	}

	/**
	 * 点击广告记录
	 * @param type
	 * @param id
	 */
	public void setBrowseClick(String type,String id){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Record countrec=new Record().set("type",type).set("data",id).set("time",format.format(new Date()));
		Db.save("ad_countrec",countrec);
	}
	
	public void setBrowse(String type,List<ChannelData> channels){
		String txt="";
		
		for(ChannelData x : channels){
			for(PlaceData h : x.getPlaces()){
				if(h.getAdvertises().size()!=0){
					for(Record g : h.getAdvertises()){
						String jsonArr=(g.getColumns().get("id")).toString();
						txt+=jsonArr+",";
					}
				}
			}
		}
		
		txt=txt.substring(0,txt.length()-1);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Record countrec=new Record().set("type",type).set("data",txt).set("time",format.format(new Date()));
		Db.save("ad_countrec",countrec);
	}
	
	/**
	 * 手动统计访问量
	 * @param firstTime
	 * @param endTime
	 */
	public void sticsCron(String firstTime,String endTime){
		List<Record> listRecord =Db.find("select data from ad_countrec where type='view' and time > '"+firstTime+"' and time<= '"+endTime+"'");
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
	       
	        /*去重*/
	        for  ( int  i  =   0 ; i  <  stringList.size()  -   1 ; i ++ )  {
	            for  ( int  j  =  stringList.size()  -   1 ; j  >  i; j -- )  {
	                if  (stringList.get(j).equals(stringList.get(i)))  {
	                	stringList.remove(j);
	                }
	            }
	        }
	        
	       
	        for(String x : stringList){
	        	List<Record> listView =Db.find("select count(1) sum from ad_countrec where (data like '%,"+x+",%' or data like '"+x+",%' or data like '%,"+x+"' or data like '"+x+"') and type='view' and time > '"+firstTime+"' and time <= '"+endTime+"'");
				String sql = "UPDATE ad_count SET ad_view = ad_view+? WHERE ad_id = ?";
				Db.update(sql,listView.get(0).get("sum"),x);
	        }
	       
	        
	        /**
			 * click点击
			 */
	        List<Record> listClick =Db.find("select type,data,count(1) sum from ad_countrec group by data,type,time having type='click' and time > '"+firstTime+"' and time <= '"+endTime+"'");
	        for(Record x : listClick){
	        	String sql = "UPDATE ad_count SET ad_click = ad_click+? WHERE ad_id = ?";
				Db.update(sql,x.get("sum"),x.get("data"));
	        }
		}
	}
}
