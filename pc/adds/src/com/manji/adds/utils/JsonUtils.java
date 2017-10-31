package com.manji.adds.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {

	public static void main(String[] args) {
		
		
		Map<String, String>  map =new HashMap<String,String>();
		
		map.put("a", "ss");
		
		String s =getArray(map);
		System.out.println(s);

	}

	
	
	
	public static String getJson(Object obj){
		
		return JSONObject.fromObject(obj).toString();
		
	}
	
	
	public static String getArray(Object obj){
		
		return JSONArray.fromObject(obj).toString();
		
	}
	
	public static JSONObject tranTObject(String jsonStr){
		
		return JSONObject.fromObject(jsonStr);
		
	}
	
	public static JSONArray tranToArrayObject(String arrayStr){
		
		
		return JSONArray.fromObject(arrayStr);
	}
}
