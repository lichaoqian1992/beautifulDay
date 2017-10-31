package com.manji.mlife.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 百度天气接口的公共类
 * @author gaochao
 * 2016年7月13日上午11:16:59
 * WeatherLoadJson
 *
 */
public class WeatherLoadJson {
	// 请求apiKey
	private static final String apiKey = "78d99fe676174d4dbe9b82bce0581f68";
	
	private static final Logger logger =Logger.getLogger(WeatherLoadJson.class);
	
	/**
	 * @param url 请求接口
	 * @param parameter 参数
	 * @return 返回结果
	 */
	public static String request(String url, Map<String, String> parameter) {
		String fullUrl = "";
		if (parameter != null && parameter.size() > 0) {
			// 第一种：普遍使用，二次取值
			String wh = "?";
			String and = "&";
			String cs = "";
			for (String key : parameter.keySet()) {
				cs += key + "=" + parameter.get(key) + and;
			}

			fullUrl = url + wh + cs;
			fullUrl = fullUrl.substring(0, fullUrl.length() - 1);
		}
		
		BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        URL WeatherUrl = new URL(fullUrl);
	        HttpURLConnection connection = (HttpURLConnection) WeatherUrl.openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey", apiKey);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        result = sbf.toString();
	    } catch (Exception e) {
	    	logger.error(e.getMessage(),e);
	    } finally {
	    	 try {
				reader.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
	    }
	    return result;
	}
		
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		String url="http://apis.baidu.com/apistore/weatherservice/recentweathers";
//		Map<String, String> map=new HashMap<String, String>();
//		//String httpArg = "cityname=%E5%8C%97%E4%BA%AC&cityid=101010100";
//		map.put("cityname", "%E5%8C%97%E4%BA%AC");
//		map.put("cityid", "101010100");
//		String json = WeatherLoadJson.request(url, map);
//		JSONObject obj = JSONObject.fromObject(json);
//	    System.out.println(obj);
//	}
}
