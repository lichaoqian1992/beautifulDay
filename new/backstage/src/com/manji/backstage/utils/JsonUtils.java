package com.manji.backstage.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {

	public static void main(String[] args) {
		
		
		Map<String, String>  map =new HashMap<String,String>();
		
		map.put("a", "aa");
		map.put("b", "bb");
		
		
		
		String s =getJson(map);
		System.out.println(s);
		
		String tempStr =s;
		
		JSONObject obj =JSONObject.fromObject(tempStr);
		
		String aStr =obj.getString("b");
		
		System.out.println(aStr);
		
		if("aa".equals(aStr)){
			System.out.println("正确");
		}else{
			System.out.println("错误");
		}
		
		
	

	}

	
	
	
	public static String getJson(Object obj){
		
		return JSONObject.fromObject(obj).toString();
		
	}
	
	
	public static String getArray(Object obj){
		
		return JSONArray.fromObject(obj).toString();
		
	}
	
	public static JSONObject tranToObject(String jsonStr){
		
		return JSONObject.fromObject(jsonStr);
		
	}
	
	public static JSONArray tranToArrayObject(String arrayStr){
		
		
		return JSONArray.fromObject(arrayStr);
	}
}
