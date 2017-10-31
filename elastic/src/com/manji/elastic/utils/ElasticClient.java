package com.manji.elastic.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.manji.elastic.base.IndexFinal;
import com.manji.elastic.model.User;

import net.sf.json.JSONObject;

public class ElasticClient {

	private static final String ES_NAME = "elastic";
	private static final String ES_PASSWORD = "changeme";

	private static final String BASIC_URL = IndexFinal.EsUrl;
	private static final String BASIC_LOC = IndexFinal.EsLocation;

	private static final int BASIC_PORT = 9200;

	private static CloseableHttpClient client;
	private static HttpClientContext context;

	static {

		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(ES_NAME, ES_PASSWORD);

		CredentialsProvider credsProvider = new BasicCredentialsProvider();

		credsProvider.setCredentials(new AuthScope(BASIC_LOC, BASIC_PORT), creds);

		PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
		poolManager.setMaxTotal(300);
		poolManager.setDefaultMaxPerRoute(50);
//		poolManager.set
		client = HttpClients.custom().setConnectionManager(poolManager).build();
		
		context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);

	}

	
	/**
	 * GET方法
	 * @param url
	 * @return
	 */
	public static String getMethod(String url) {
		String result= "";
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		
		HttpGet method =new HttpGet(httpUrl);
		
		method.setHeader("Content-Type","application/json");
		method.setHeader("charset","UTF-8");
		
		
		try {
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
//                System.out.println(EntityUtils.toString(ent));
                result =EntityUtils.toString(ent);
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
		
	}
	
	
	
	/**
	 * PUT方法
	 * @param url
	 */
	public static void putMethod(String url){
		
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		HttpPut method =new HttpPut(httpUrl);
		
		method.addHeader("Content-Type","application/json"); 
		method.setHeader("charset","UTF-8");
		method.addHeader("Accept", "application/json");
		try {
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * PUT传递参数方法
	 * @param url
	 * @param jsonStr
	 */
	public static void putMethodWithJson(String url,String jsonStr){
		
		String newStr ="";

		newStr =jsonStr;
		
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		HttpPut method =new HttpPut(httpUrl);
		
		method.addHeader("Content-Type","application/json"); 
		method.setHeader("charset","UTF-8");
		method.addHeader("Accept", "application/json");
		try {
			
			StringEntity input = null;
		 
            input = new StringEntity(newStr,"UTF-8");
                
		 	method.setEntity(input);
		 
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void postMethod(String url) {
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		
		HttpPost method =new HttpPost(httpUrl);

		try {
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	
	
	
	public static void postMethodWithFile(String url){
		
		
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		
		HttpPost method =new HttpPost(httpUrl);
		
		method.addHeader("Content-Type","application/json"); 
		method.setHeader("charset","UTF-8");
		method.addHeader("Accept", "application/json");

		try {
			
			StringEntity input = null;
			 
            input = new StringEntity(FileUtils.readFileContent(""),"utf-8");
                
		 	method.setEntity(input);
			
			
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static String postMethodWithQeuryBody(String url,String queryBody){
		
		String retStr ="";
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		
		HttpPost method =new HttpPost(httpUrl);
		
		method.addHeader("Content-Type","application/json"); 
		method.setHeader("charset","UTF-8");
		method.addHeader("Accept", "application/json");

		try {
			
//			String queryBody ="{\"query\": { \"match\": {\"article_category_index\":\"羽绒服\"} },\"size\": 20}";
			
			StringEntity input = null;
			 
            input = new StringEntity(queryBody,"utf-8");
                
		 	method.setEntity(input);
			
			
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
//		    System.out.println("Target auth state: " + targetAuthState.getState());
//		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
//		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
		    	retStr =EntityUtils.toString(ent);
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		return retStr;
		
	}
	
	/**
	 * delete方法
	 */
	public void deleteMethod(String url) {
		String httpUrl = BASIC_URL + url;
		System.out.println(httpUrl);
		
		HttpDelete method =new HttpDelete(httpUrl);
		
		try {
			CloseableHttpResponse res =client.execute(method,context);

		    AuthState targetAuthState = context.getTargetAuthState();
		    System.out.println("Target auth state: " + targetAuthState.getState());
		    System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		    System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
			
		    HttpEntity ent =res.getEntity();
		    
		    if (ent != null) {  
		    	
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	
	
	public static void main(String[] args){
		System.out.println("============start==================");
		
//		getMethod("");
//		getMethod("users/user/3");
//		//列出所有索引
//		String url ="_search/";
//		getMethod(url);
//		putMethod("users");
		
//		putMethodWithJson();
		
//		User u =new User();
//		u.setName("huanghan");
//		u.setPwd("123456");
//		u.setCity("重庆");
//		
//		
		
//		Map<String,String> mm =new HashMap<String,String>()
		
//		putMethodWithJson("goods/goods",JSONObject.fromObject().toString());
//		
//		getMethod("goods/goods/10503");
		
		
//		postMethodWithFile("_bulk");
//		getMethod("mmdata/goods/10503");
		
//		getMethod("goods/goods/_search?q=zhaiyao:ddddd");
		
		
//		postMethodWithFile("_bulk");
//		getMethod("test/dt_article_screen/91777");
		
//		getMethod("mmdata/goods/_search?q=shop_name:官方");
		
//		postMethodWithQeuryBody("test/dt_article_screen/91953");
		
		
		
//		String str ="";
		
		
//		String str ="{{\"query\": {\"bool\": {\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"100\",\"lte\":\"200\" }}}] }}}";
//		String str ="{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"article_category_index\": \"衬衫\" } }],\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"100\",\"lte\":\"200\" }}}] }}}";
		
		
		String str ="{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"article_category_index\": \"衬衫\" } }],\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"100\",\"lte\":\"200\" }}}] }}}";
		
//		String str ="{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"name\": \"满集\" } }]}},\"sort\": { \"review_score\": \"desc\" },\"size\": "+"20"+",\"from\": "+"0"+"}";
		
//		System.out.println(postMethodWithQeuryBody("dt_user_role_shopinfo/_search",str));
		putMethod("article/analyze?analyzer=ik&pretty=true&text=钢琴");
//		category_all 
		
		
		System.out.println("=============end===================");
		
	}
}
