package com.manji.adds.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WebUrlUtils {

	
	public static void  main(String args[]){
		
		
		Map<String,String> map =new HashMap<String,String>();
		
//		String url="http://www.manji.com/tools/shop_ajax.ashx";
//		map.put("action", "QueryShop");
//		map.put("name", "满集官方旗舰店");
//		map.put("addr", "重庆市,县,梁平县");
		
		
		
		String url ="http://manji.com/AppService.asmx/";
		
		map.put("action", "QueryGoods");
		map.put("sid", "2");
		map.put("name", "裤子");
		
		try {
			System.out.println(requestHttpJson(url,map));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String requestHttpJson(String url, Map<String, String> parameter) throws UnsupportedEncodingException {
		String Newurl = "";
		String html = "";
		if (parameter != null && parameter.size() > 0) {
			// 第一种：普遍使用，二次取值
			String wh = "?";
			String and = "&";
			String cs = "";
			for (String key : parameter.keySet()) {

				cs += key + "=" + parameter.get(key) + and;
			}

			Newurl = url + wh + cs;
			Newurl = Newurl.substring(0, Newurl.length() - 1);
		}
		StringBuffer bs = new StringBuffer();
		try {
			System.out.println(Newurl);
			URL u = new URL(Newurl);
			HttpURLConnection urlcon = (HttpURLConnection) u.openConnection();
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			buffer.close();
			html = bs.toString();
		} catch (MalformedURLException e) {
			html = "不支持的协议";
		} catch (IOException e) {
			html = "服务未响应";
		}
		return html;

	}
	
}
