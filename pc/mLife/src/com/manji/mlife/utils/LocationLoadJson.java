package com.manji.mlife.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class LocationLoadJson {

	
	public static void main(String args[]){
		
		getCity("102.539177_28.578691");
		
		
		
	}
	
	public static Map<String,String> getCity(String location) {
		
		Map<String, String> map =new HashMap<String,String>();
		
		String[] address = location.split("_");
		// 经度
		String lng = address[0];
		// 纬度
		String lat = address[1];
		System.out.println(location);
		
		String url="http://api.map.baidu.com/geocoder/v2/?ak=YtH9NaXoL263u2y9O4W3cox77IisDCsj&location="+lat+","+lng+"&output=json";
		
		StringBuffer bs = new StringBuffer();
		String result="";
		System.out.println(url);
		try {
			URL u = new URL(url);
			HttpURLConnection urlcon = (HttpURLConnection) u.openConnection();
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			buffer.close();
			result = bs.toString();
		} catch (MalformedURLException e) {
			result = "不支持的协议";
		} catch (IOException e) {
			result = "服务未响应";
		}
		System.out.println(result);
		JSONObject obj =JSONObject.fromObject(result);
		
		if("0".equals(obj.getString("status"))){
			
			JSONObject locationObject =obj.getJSONObject("result");
			
			JSONObject addressObject =locationObject.getJSONObject("addressComponent");
			
			String province=addressObject.getString("province");
			
			String cityname=addressObject.getString("city");
			
			String district =addressObject.getString("district");
			
			
			if(!province.equals(cityname)){
				
				province=province.replace("省", "");
				
				cityname =cityname.replace("市", "");
				cityname =cityname.replace("自治州", "");
				
				map.put("region", province);
				map.put("cityname", cityname);
				
			}else{
				
				cityname =cityname.replace("市", "");
				if(district.length()>2){
					district=district.replace("县", "");
				}
				district=district.replace("区", "");
				map.put("region", cityname);
				map.put("cityname", district);
			}
			
			System.out.println(province+cityname);
			
		}
		
		
		return map;
	
	}
	
	
	
}
