package com.manji.sheet.utils;

import java.io.IOException;
import java.util.Map;

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

public class HttpClientUtils {

	private static CloseableHttpClient client;
//	private static HttpClientContext context;

	static {

		PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
		poolManager.setMaxTotal(300);
		poolManager.setDefaultMaxPerRoute(50);
		client = HttpClients.custom().setConnectionManager(poolManager).build();

//		context = HttpClientContext.create();

	}

	/**
	 * post提交请求
	 * @param req_url
	 */
	public static String postMethod(String req_url) {

		String temp = "";

		HttpPost method = new HttpPost(req_url);

		method.setHeader("Content-Type", "application/json");
		method.setHeader("charset", "UTF-8");

		try {
//			CloseableHttpResponse res = client.execute(method, context);
			CloseableHttpResponse res = client.execute(method);
			HttpEntity ent = res.getEntity();

			if (ent != null) {

				temp = EntityUtils.toString(ent);
			}

		} catch (IOException e) {

			e.printStackTrace();
			
		}

		//System.out.println(temp);
		return temp;
	}
	/*public static String postMethod2(String req_url,Map<String,String> context) {

		String temp = "";

		HttpPost method = new HttpPost(req_url);

		method.setHeader("Content-Type", "application/json");
		method.setHeader("charset", "UTF-8");

		try {
//			CloseableHttpResponse res = client.execute(method, context);
			CloseableHttpResponse res = client.execute(method,context);
			HttpEntity ent = res.getEntity();

			if (ent != null) {

				temp = EntityUtils.toString(ent);
			}

		} catch (IOException e) {

			e.printStackTrace();
			
		}

		//System.out.println(temp);
		return temp;
	}*/
	
	

	public static void main(String[] args) {

		postMethod("http://localhost:8080/singleSign/login?name=huanghan&password=123");

	}
}
