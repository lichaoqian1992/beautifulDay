package com.manji.backstage.utils;

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
		String url ="http://service.manji.com/AppService.asmx/UploadFile";
		
		long timestamp =System.currentTimeMillis();
		long current =timestamp/1000;
		
		String fileName ="nxch-034.jpg";
		String base64Str =Base64Utils.GetImageStr();
		
		System.out.println(base64Str);
		System.out.println("输出完成");
		String roundStr =current+base64Str+fileName+"asalt" ;
		String roundNumber=MD5util.getMD5(roundStr);
		map.put("currentDate", current+"");
		map.put("roundNumber", roundNumber);
		map.put("isResponseJson", "1");
		map.put("loginType", "Third");
		map.put("base64Str", base64Str);
		map.put("fileName", fileName);
		
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
			e.printStackTrace();
		} catch (IOException e) {
			html = "服务未响应";
			e.printStackTrace();
		}
		return html;

	}
	
}
