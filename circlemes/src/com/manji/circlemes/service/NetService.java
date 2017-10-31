package com.manji.circlemes.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.manji.circlemes.model.common.NetRes;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.thread.ShopThread;
import com.manji.circlemes.utils.SSLClient;

import net.sf.json.JSONObject;

public class NetService {

	private static final String access_token = "YWMtC7ZrsBtBEeeqjnGBiV2cAQAAAAAAAAAAAAAAAAAAAAHabEOgwEoR5pHMqVyc6NkGAgMAAAFbRmzBDQBPGgACQjj0HqxabyGEkW5Pgz0XCzGH1-PiCAsoYyjw5Bdotg";

	private static final String Content_Type = "application/json";
	private static final String Authorization = "Bearer " + access_token;
	private static final String grant_type = "client_credentials";
	private static final String client_id = "1177161212178229";
	private static final String client_secret = "mjms";

	private static Logger logger = Logger.getLogger(NetService.class);  
	
	
	public  NetRes doNetPost(String url, String paraJson) {

//		Result r = new Result();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", Content_Type);

		NetRes nr = SSLClient.doNetPost(url, paraJson, headers);
		logger.info(JSONObject.fromObject(nr).toString());
//		if (nr.getCode() == 200) {
//			r.setCode("0000");
//			r.setData(nr.getContent());
//		} else {
//			r.setCode("0001");
//			r.setData(nr.getContent());
//		}

		return nr;
	}

	
	
	
	public  NetRes doNetPostWithAuth(String url, String paraJson) {

		Result r = new Result();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", Content_Type);
		headers.put("Authorization", Authorization);
		
		NetRes nr = SSLClient.doNetPost(url, paraJson, headers);
		
		logger.info(JSONObject.fromObject(nr).toString());
//		if (nr.getCode() == 200) {
//			r.setCode("0000");
//			r.setData(nr.getContent());
//		} else {
//			r.setCode("0001");
//			r.setData(nr.getContent());
//		}
		
//		System.out.println(JSONObject.fromObject(r).toString());
		
		return nr;

	}
	
	
	
	public  NetRes doNetGetWithAuth(String url) {
		
		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", Content_Type);
		headers.put("Authorization", Authorization);
		
		NetRes nr = SSLClient.doNetGet(url,headers);
		logger.info(JSONObject.fromObject(nr).toString());
		
		return nr;
		
	}
	
	
	
	public  NetRes doNetDeleteWithAuth(String url) {

		

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", Content_Type);
		headers.put("Authorization", Authorization);
		
		NetRes nr = SSLClient.doNetDelete(url,headers);
		
		logger.info(JSONObject.fromObject(nr).toString());
		
		
		return nr;

	}
	

}
