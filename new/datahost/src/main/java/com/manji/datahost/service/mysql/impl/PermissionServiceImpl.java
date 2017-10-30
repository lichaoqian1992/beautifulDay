package com.manji.datahost.service.mysql.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manji.datahost.mapper.mysql.PermissionMapper;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.MD5util;
import com.manji.datahost.utils.PostUrlUtils;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper mapper;

	@Override
	public Integer getConnector(String interface_name) {
		
		return mapper.getConnector(interface_name);
	}

	@Override
	public String sendPostReq(String fileName, String base64Str) {
		Map<String,Object> map =new HashMap<String,Object>();
		//String url = "http://192.168.0.48:8050/MJ.Basics.Core.Api/api/Other/UploadFiles";
		String url ="http://service.manji.com/AppService.asmx/UploadFile";
/*		String url ="http://service2.manjiwang.com/AppService.asmx/UploadFile";
*/		
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
		String path ="";
		JSONObject htmlObj =JSON.parseObject(html);
		if("1".equals(htmlObj.getString("State"))){
			
			path =htmlObj.getJSONObject("Datas").getString("Path");
			
		}
		return path;
	}
	
	public  String postHttpRequest(String path,Map<String,String> params){
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
			URL u = new URL(path);
			URLConnection urlcon =  u.openConnection();
			
			 // 设置通用的请求属性
			urlcon.setRequestProperty("accept", "*/*");
			urlcon.setRequestProperty("connection", "Keep-Alive");
			urlcon.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
			urlcon.setDoOutput(true);
			urlcon.setDoInput(true);
			out = new PrintWriter(urlcon.getOutputStream());
            // 发送请求参数
            out.print(new String(param.getBytes("utf-8")));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
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
		
		finally{
			try{
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return html;
		
		
	}

	
	
}
