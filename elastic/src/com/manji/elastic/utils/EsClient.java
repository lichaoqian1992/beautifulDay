package com.manji.elastic.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class EsClient {

	private static final String ES_NAME ="elastic";
	private static final String ES_PASSWORD ="changeme";
	
	private static final String BASIC_URL ="http://192.168.0.47:9200/";
	private static final String BASIC_LOC ="192.168.0.47";
	private static final int BASIC_PORT =9200;
	
	
	
	private static CloseableHttpClient client;
	private static HttpClientContext context;
	
	static{
		
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(ES_NAME, ES_PASSWORD);  
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();  
		
		credsProvider.setCredentials( new AuthScope(BASIC_LOC, BASIC_PORT), creds); 
		
		PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
		poolManager.setMaxTotal(300);
		poolManager.setDefaultMaxPerRoute(50);
		client = HttpClients.custom().setConnectionManager(poolManager).build();
		
		context = HttpClientContext.create();
	    context.setCredentialsProvider(credsProvider);
		
		
	}
	
	
	
	
	
	
	public void httpclient(String url){
		
		
//		CloseableHttpClient client = HttpClients.createDefault(); 
		
		
		String httpUrl =BASIC_URL+url;
		
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
                System.out.println("Response content length: " + ent.getContentLength());  
                System.out.println(ent+"3214242423");
                
                System.out.println(EntityUtils.toString(ent));
            } 
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args){
		
		EsClient c =new EsClient();
		
		String url ="";
		
		c.httpclient(url);
		
	}
}
