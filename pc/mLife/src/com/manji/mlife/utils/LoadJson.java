package com.manji.mlife.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LoadJson {

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url="http://wthrcdn.etouch.cn/weather_mini";
		Map<String, String> map=new HashMap<String, String>();
		map.put("city", "重庆");
		String json = LoadJson.loadJSON(url, map);
		System.out.println(json);
	}
	// 调用接口返回json数据
	public static String loadJSON(String url, Map<String, String> parameter) throws UnsupportedEncodingException {
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
