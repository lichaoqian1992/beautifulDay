package com.manji.adds.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.InfMapper;

import com.manji.adds.model.AddsBean;
import com.manji.adds.model.InfBean;
import com.manji.adds.model.Place;

import com.manji.adds.utils.JsonUtils;
import com.manji.adds.utils.PicUtils;

@Service
public class InfServiceImpl implements InfService {

	@Autowired
	private InfMapper mapper;

	@Override
	public String getRegions(String region) {

		ArrayList<String> regions =new ArrayList<String>() ;
		String codes="";
		if("00".equals(region)){
			codes="00";
		}else
		{
			codes="'";
		while(region.length()>0)
		{
			codes+=region+"','";
			
			
			region =region.substring(0, region.length()-2);
			
		}
		
		codes+="00'";
		
		}
//		System.out.println(codes);
		return codes;
	}

	@Override
	public List<Map> getOuterMap(String channel, String type, String regions) {

		List<Map> resultMap =new ArrayList<Map>();
		
		Place queryMap =new Place();
		
		queryMap.setChannel(channel);
		queryMap.setType(type);
		
		List<Place> placeList =mapper.getPlaces(queryMap);
//		System.out.println(JsonUtils.getArray(placeList));
		
		for(int i=0;i<placeList.size();i++){
			
			Place place =placeList.get(i);
			
			AddsBean bean =new AddsBean();
			
			bean.setNumber(place.getNumber());
			bean.setChannel(place.getChannel());
			bean.setPl_id(place.getPl_id());
			bean.setRegions(regions);
			System.out.println(JsonUtils.getJson(bean));
			List<InfBean> addsList =mapper.getAddsById2( bean);
			System.out.println(JsonUtils.getArray(addsList));
			
			for(int j=0;j<addsList.size();j++){
				
				if("0".equals(channel)&&"0".equals(type)&&"1".equals(place.getPl_id())){
					InfBean info =addsList.get(j);
					
					String pic_url =PicUtils.getTwoPics(info.getAd_id());
					
					addsList.get(j).setPic_url(pic_url);
				}else{
					InfBean info =addsList.get(j);
					
					String pic_url =PicUtils.getOnePic(info.getAd_id());
					
					addsList.get(j).setPic_url(pic_url);
				}
				
				
				
			}
			
			
			Map map =new HashMap();
			
			map.put("placeid", place.getPl_id());
			map.put("addslist", addsList);
			
			resultMap.add(map);
			
		}
		
		return resultMap;
	}

	@Override
	public void addCount(String ad_id) {

		mapper.addCount(ad_id);
	}

	
	

	
	
}
