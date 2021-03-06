package com.manji.advert.utils;

import java.io.IOException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import okhttp3.*;

public class HttpClientUtils {

	 /**
     * httpclient请求
     */
    public final static String SUCCESS = "success";//正确状态
    public final static String ERROR = "error";//错误状态
    public final static String ATURL = "http://pay.manji.com/javaio.ashx?";

    static OkHttpClient httpClient=new OkHttpClient();




    public static void PostAplus(String url,String data) throws IOException {
        RequestBody formBody =new FormBody.Builder().add("json",data).build();

        Request request=new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    public static boolean GetAplus(String url)throws  IOException{
        boolean flag = false;
        Request request = new Request.Builder().get().url(url).build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String returnTxt=response.body().string();
            System.out.println("数据："+returnTxt);
            if(returnTxt.equals("true")){
                flag=true;
            }
        } else {
            throw new IOException("Unexpected code " + response);
        }
        return flag;
    }
    /*
   发送同步请求
    */
    public static String PostAPI(String url, String data) throws IOException {

        RequestBody formBody =new FormBody.Builder().add("json",data).build();

        Request request=new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("post请求成功");
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    /*
    发送get异步请求
     */
    public static  String GetAPI(String url) throws IOException{
    	String message = "";
    	CloseableHttpClient httpclient = null ; 
    	
        httpclient = HttpClients.custom().build(); 
    	
    	HttpGet httpget = new HttpGet(url);  
        System.out.println("executing request" + httpget.getRequestLine());  
        CloseableHttpResponse response = httpclient.execute(httpget);
		try{
			HttpEntity entity = response.getEntity(); 
			System.out.println("----------------------------------------");  
            System.out.println(response.getStatusLine());  
            if (entity != null) {  
                System.out.println("Response content length: " + entity.getContentLength());  
                System.out.println(entity+"3214242423");
                message = EntityUtils.toString(entity);
                System.out.println(message);
            } 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			response.close(); 
		}
        
        return message; 
    }
    
    public static void main(String[] args) throws IOException{
    	
    	String url ="http://192.168.0.47:9200/";
    	
    	PostAPI(url,"");
    }
}
