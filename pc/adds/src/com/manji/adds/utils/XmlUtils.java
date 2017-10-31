package com.manji.adds.utils;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class XmlUtils {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "黄瀚");
		map.put("4", "ab");
		System.out.println(MapToXml(map));
	}

	
	public static String MapToXml(Map<String,String> map){
		
		StringBuffer sb = new StringBuffer();  
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>");  
		
		Set set =map.keySet();
		
		for (Iterator it = set.iterator(); it.hasNext();) {  
			
			 String key = (String) it.next();  
			 
			 String value = map.get(key);  
			 String str ="<"+key+">"+value+"</"+key+">";
			 
			 sb.append(str);
			
		}
		sb.append("</data>");
		
		String xmltxt ="";
		xmltxt =sb.toString();
//		try {
//			xmltxt =sb.toString().getBytes("UTF-8").toString();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
		return xmltxt;
	}
	
	
}
