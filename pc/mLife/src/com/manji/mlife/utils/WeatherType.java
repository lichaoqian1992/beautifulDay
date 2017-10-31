package com.manji.mlife.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherType {
	
	/**
	 * 遍历天气类型
	 * @param obj
	 * @return
	 */
	public static JSONObject weatherType(JSONObject obj) {
		if (obj == null){
			return null;
		}
		
		JSONObject jsonObject = obj.getJSONObject("retData");
		// jsonObject.put("region", region);
		//天气类型
		String type = "";
		//当天的
		JSONObject string;
		//往后四天的
		JSONArray jsonArray = jsonObject.getJSONArray("forecast");
		for (int i = 0; i < jsonArray.size()+1; i++) {
			if(i == jsonArray.size()){
				 string=jsonObject.getJSONObject("today"); 
				 type = string.getString("type");
			}else {
				string = jsonArray.getJSONObject(i);
				type = string.getString("type");
			}
			 
			switch (type) {
				case "晴":
					string.put("code", "0");
					break;
				case "多云":
					string.put("code", "1");
					break;
				case "阴":
					string.put("code", "2");
					break;
				case "阵雨":  
					string.put("code", "3");
					break;
				case "雷阵雨":
					string.put("code", "4");
					break;
				case "雷阵雨伴有冰雹":
					string.put("code", "5");
					break;
				case "雨夹雪":
					string.put("code", "6");
					break;
				case "小雨":
					string.put("code", "7");
					break;
				case "中雨":
					string.put("code", "8");
					break;
				case "大雨":
					string.put("code", "9");
					break;
				case "暴雨":
					string.put("code", "10");
					break;
				case "大暴雨":
					string.put("code", "11");
					break;
				case "特大暴雨":
					string.put("code", "12");
					break;
				case "阵雪":
					string.put("code", "13");
					break;
				case "小雪":
					string.put("code", "14");
					break;
				case "中雪":
					string.put("code", "15");
					break;
				case "大雪":
					string.put("code", "16");
					break;
				case "暴雪":
					string.put("code", "17");
					break;
				case "雾":
					string.put("code", "18");
					break;
				case "冻雨":
					string.put("code", "19");
					break;
				case "沙尘暴":
					string.put("code", "20");
					break;
				case "浮尘":
					string.put("code", "21");
					break;
				case "扬沙":
					string.put("code", "22");
					break;
				case "强沙尘暴":
					string.put("code", "23");
					break;
				case "霾":
					string.put("code", "24");
					break;
				case "小到中雨":
					string.put("code", "25");
					break;
				case "中到大雨":
					string.put("code", "26");
					break;
				case "大到暴雨":
					string.put("code", "27");
					break;
				case "暴雨到大暴雨":
					string.put("code", "28");
					break;
				case "大暴雨到特大暴雨":
					string.put("code", "29");
					break;
				case "小到中雪":
					string.put("code", "30");
					break;
				case "中到大雪":
					string.put("code", "31");
					break;
				case "大到暴雪":
					string.put("code", "32");
					break;
				default: 
					string.put("code", "99");
					break;
			}
		}
		return obj;
	}
}
