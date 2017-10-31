package com.life.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import net.sf.json.JSONObject;

public class PicUtils {
	
	private static String TEST_URL="http://servicetest.manji.com/AppService.asmx/UploadFile";
	//private static String TEST_URL="http://service1.manjiwang.com/AppService.asmx/UploadFile";//正式环境
	
	private static String PRD_URL="http://service2.manjiwang.com/AppService.asmx/UploadFile";
	
	public static String postPic(File file){
		
		String url =TEST_URL;
		
		String fileName =file.getName();
		System.out.println(fileName);
		String base64Str =getBase64Code(file);
		
		
		Map<String,String> map =new HashMap<String,String>();
		
	
		
		long timestamp =System.currentTimeMillis();
		long current =timestamp/1000;
		
		String roundStr =current+base64Str+fileName+"asalt" ;
		
		String roundNumber=MD5util.getMD5(roundStr);
		
		try {
			base64Str =URLEncoder.encode(base64Str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		map.put("currentDate", current+"");
		map.put("roundNumber", roundNumber.toLowerCase());
		map.put("isResponseJson", "1");
		map.put("loginType", "Third");
		map.put("base64Str", base64Str);
		map.put("fileName", fileName);
		
		String html =PostUrlUtils.postHttpRequest(url,map);
		//String html = HttpClientUtils.postMethod2(url,map);	
		System.out.println(html+"=================");
		String path ="";
		if(!html.equals("服务器未响应") && !html.equals("不支持的协议")){
			JSONObject htmlObj =JSONObject.fromObject(html);
			if("1".equals(htmlObj.getString("State"))){
				
				path =htmlObj.getJSONObject("Datas").getString("Path");
				
			}
		}
		return path;
	}
	
	
	

	public static String getBase64Code(File file) {

		InputStream in = null;
		byte[] data = null;
		try {

			in = new FileInputStream(file);
			// in = file.getInputStream();
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String picCode = Base64.encode(data);

		return picCode;

	}

	public static void main(String[] args) {
		
		File file =new File("D:/test.jpg");
		
		postPic(file);
		
		
	}

}
