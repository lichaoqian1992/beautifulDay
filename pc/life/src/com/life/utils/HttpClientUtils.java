package com.life.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	
	
	public String postUrl(File file,String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String xx = "";
		try{
	        // 要上传的文件的路径
	        // 把一个普通参数和文件上传给下面这个地址 是一个servlet
	        HttpPost httpPost =new HttpPost(url);
	        // 把文件转换成流对象FileBody
	        
	        FileBody bin =new FileBody(file); 
	        StringBody uploadFileName =new StringBody(bin.getFilename(), ContentType.create("text/plain", Consts.UTF_8));
	        //以浏览器兼容模式运行，防止文件名乱码。 
	           HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
	                .addPart("uploadFile", bin)//uploadFile对应服务端类的同名属性<File类型>
	                .addPart("uploadFileName", uploadFileName)//uploadFileName对应服务端类的同名属性<String类型>
	                .setCharset(CharsetUtils.get("UTF-8")).build();
	 
	        httpPost.setEntity(reqEntity);
	 
	        System.out.println("发起请求的页面地址 "+ httpPost.getRequestLine());
	        // 发起请求 并返回请求的响应
	        CloseableHttpResponse response = httpClient.execute(httpPost);
	        try{
	            System.out.println("----------------------------------------");
	            // 打印响应状态
	            System.out.println(response.getStatusLine());
	            // 获取响应对象
	            HttpEntity resEntity = response.getEntity();
	            if(resEntity !=null) {
	                // 打印响应长度
	                System.out.println("Response content length: "
	                        + resEntity.getContentLength());
	                // 打印响应内容
	                xx = EntityUtils.toString(resEntity,
	                        Charset.forName("UTF-8"));
	                System.out.println(EntityUtils.toString(resEntity,
	                        Charset.forName("UTF-8")));
	            }
	            // 销毁
	            EntityUtils.consume(resEntity);
	        }finally{
	            response.close();
	        }
	    }finally{
	        httpClient.close();
	    }
		
		return xx;
	}

}
