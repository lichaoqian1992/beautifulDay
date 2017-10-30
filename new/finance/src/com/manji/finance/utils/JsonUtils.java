package com.manji.finance.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {

	public static String json(Object obj){
		
		return JSONObject.fromObject(obj).toString();
	}
	
	public static String array(Object obj){
		return JSONArray.fromObject(obj).toString();
		
	}
	
}
