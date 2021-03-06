package com.life.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hslf.record.Environment;

import net.sf.json.JSONObject;

public class PostUrlUtils {
	
//	public static void main(String[] args){
//		
//	
//		Map<String,String> params =new HashMap<String,String>();
//		String url ="http://service.manji.com/AppService.asmx/UploadFile";
////		String url ="http://192.168.0.24:58917/AppService.asmx/UploadFile";
//		
//		long timestamp =System.currentTimeMillis();
//		long current =timestamp/1000;
//		
//		String fileName ="nxch-034.jpg";
//		String base64Str =Base64Utils.GetImageStr();
//
//		String roundStr =current+base64Str+fileName+"asalt" ;
//
//		String roundNumber=MD5util.getMD5(roundStr);
//		
//		try {
//			base64Str =URLEncoder.encode(base64Str,"UTF-8");
//			System.out.println(base64Str);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//		params.put("currentDate", current+"");
//		params.put("roundNumber", roundNumber.toLowerCase());
//		params.put("isResponseJson", "1");
//		params.put("loginType", "Third");
//		params.put("base64Str", base64Str);
//		params.put("fileName", fileName);
//		
//		
//		System.out.println(postHttpRequest(url,params));
//		
//		
//	}
	
	
	
	public static void main(String[] args){
		//测试地址
		String url ="http://servicetest.manji.com/AppService.asmx/UserMessageGet";
		//正式地址
//		String url ="service2.manjiwang.com/AppService.asmx/UserMessageGet";
		
		long currentDate =System.currentTimeMillis();
		
		String sessionId ="e11ff9b2-b06e-424e-b9d7-5bd3af42e379";
		
		String roundNumber=MD5util.getMD5(currentDate+sessionId).toLowerCase();
		
		int isResponseJson =1;
		
		String loginType ="Third";
		Map<String,String> params =new HashMap<String,String>();
		
		params.put("sessionID", sessionId);
		params.put("currentDate", currentDate+"");
		params.put("roundNumber", roundNumber);
		params.put("isResponseJson", isResponseJson+"");
		params.put("loginType", loginType);
		params.put("sessionID", sessionId);
		
//		String url ="http://www.manjiwang.com/tools/submit_ajax.ashx?action=GetUserInfoBySessionId&SessionID=d2215942-8be9-482c-9882-247f0a8929ae";
//		String sessionId ="d2215942-8be9-482c-9882-247f0a8929ae";
//		Map<String,String> params =new HashMap<String,String>();
//		
//		params.put("action", "GetUserInfoBySessionId");
		
		params.put("sessionID", sessionId);
		System.out.println(postHttpRequest(url,params));
		
	}
	
	public static String postHttpRequest(String path,Map<String,String> params){
		String html="";
		
		String param = "";
		
		
		if (params != null && params.size() > 0) {
			// 第一种：普遍使用，二次取值
			
			String and = "&";
			String cs = "";
			for (String key : params.keySet()) {

				cs += key + "=" + params.get(key) + and;
			}

			param +=cs;
			param = param.substring(0, param.length() - 1);
			
			System.out.println(param);
			
		}
		
		StringBuffer bs = new StringBuffer();
		
		PrintWriter out = null;
        BufferedReader in = null;
        
		try {
			System.out.println(path);
			URL u = new URL(path);
			HttpURLConnection urlcon =  (HttpURLConnection)u.openConnection();
			
			 // 设置通用的请求属性
			urlcon.setRequestProperty("accept", "*/*");
			urlcon.setRequestProperty("connection", "Keep-Alive");
			urlcon.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
//			urlcon.setConnectTimeout(5000);  
			urlcon.setReadTimeout(30000);
			urlcon.setDoOutput(true);
			urlcon.setDoInput(true);
			//urlcon.setFixedLengthStreamingMode(0);
			out = new PrintWriter(urlcon.getOutputStream());
            // 发送请求参数
            out.print(new String(param.getBytes("utf-8")));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            System.out.println("========"+urlcon.getInputStream()+"==============");
            in = new BufferedReader(
                    new InputStreamReader(urlcon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                html += line;
            }
			
			
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
