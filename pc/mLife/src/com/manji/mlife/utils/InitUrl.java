package com.manji.mlife.utils;

import java.util.HashMap;
import java.util.Map;

public class InitUrl {

	
	
	public static void main(String args[]){
		
		
		Map<String,String> map =new HashMap<String,String>();
		
		map.put("a", "1");
		map.put("b", "2");
		System.out.println(getUrl("localhost",map));
		
	}
	
	public static String getUrl(String url ,Map<String,String> map){
		
		
		if (map != null && map.size() > 0) {
			// 第一种：普遍使用，二次取值
			String wh = "?";
			String and = "&";
			String cs = "";
			for (String key : map.keySet()) {

				cs += key + "=" + map.get(key) + and;
			}

			url = url + wh + cs;
			url = url.substring(0, url.length() - 1);
		}
		
		
		return url;
	}
}
