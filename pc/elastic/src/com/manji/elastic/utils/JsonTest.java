package com.manji.elastic.utils;

import java.util.HashMap;
import java.util.Map;

import com.manji.elastic.model.User;

import net.sf.json.JSONObject;

public class JsonTest {

	
	
	
	
	public static void main(String[] args){
		
		User u =new User();
		
		
		u.setName("huanghan");
		u.setPwd("123456");
		u.setCity("成都");
		
		
		Map<String,String> map =new HashMap<String,String>();
		
		map.put("_index", "users");
		map.put("_type", "user");
		map.put("_id", "2");
		
		System.out.println(JSONObject.fromObject(u).toString());
		System.out.println(JSONObject.fromObject(map).toString());
		
	}
}
