package com.manji.advert.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.ChannelData;
import com.manji.advert.model.PlaceData;

public class PCService {

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
		cd.setChannel(channel);
		
		
		
		List<Record> placeList =Db.find("select * from ad_place where pl_channel =? and pl_state=1 order by cast(pl_sort as integer)",channel.get("id"));
		
		List<PlaceData> places =new ArrayList<PlaceData>();
		
		for(int i =0;i <placeList.size();i++){
			
			PlaceData pd =new PlaceData();
			
			Record tPlace =placeList.get(i);
			
			pd.setPlace(tPlace);
			
//			List<Record> advertises =Db.find("select * from ad_advertise where ad_place =? and ad_state='1' and ad_code in (?)",tPlace.get("id")+"",loc);
			List<Record> advertises =Db.find("select top "+tPlace.getStr("pl_max")+" * from ad_advertise where ad_place ="+tPlace.get("id")+" and ad_state =1 and ad_code in ("+loc+") order by ad_sort");
			pd.setAdvertises(advertises);
			
			places.add(pd);
		}
		
		cd.setPlaces(places);
		
		return cd;
	}

	public List<Record> getChannelList() {

		List<Record> list =Db.find("select ch_name from ad_channel where port ='PC' and ch_layer='1' and ch_state='1'  order by cast(ch_sort as integer)");
		
		
		return list;
	}


	
	
	
	
	
}
