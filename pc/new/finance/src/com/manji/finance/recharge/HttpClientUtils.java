package com.manji.finance.recharge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.manji.finance.recharge.requestParams.HttpRequestParam;
import com.manji.finance.recharge.requestParams.HttpResponseParam;
import com.manji.finance.utils.DecriptUtil;
import com.manji.finance.utils.InterfaceUtil;
import com.manji.finance.utils.MD5Utils;
import com.manji.finance.utils.TimeUtils;
import com.manji.finance.withdrawals.Pay.PayUtil;
import com.manji.finance.withdrawals.Pay.ReconciliationSercive;

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
            System.out.println(response.body().string());
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
            } 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			response.close(); 
		}
        
        return message; 
    	
    	
    	
    		/*Request request = new Request.Builder().get().url(url).build();
            Response response = httpClient.newCall(request).execute();

            if(response==null){
                return "null";
            }
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }*/
    }
    
    /**
     * 拼接URL
     * @param
     * @return
     */
    public static HttpResponseParam getReturn(String message){
    	HttpResponseParam httpResponseDO = new HttpResponseParam();
        JSONObject jsStr = JSONObject.parseObject(message);
        if(jsStr.getString("ErrCode").equals("0") && jsStr.getJSONObject("Data").getString("ResultCode").equals("8")){
        	httpResponseDO.setErrCode(jsStr.getString("ErrCode"));
            httpResponseDO.setMessage(jsStr.getString("Message"));
            httpResponseDO.setAction(jsStr.getJSONObject("Data").getString("Action"));
            httpResponseDO.setQuerySN(jsStr.getJSONObject("Data").getString("QuerySN"));
            httpResponseDO.setTranSN(jsStr.getJSONObject("Data").getString("TranSN"));
            httpResponseDO.setResultCode(jsStr.getJSONObject("Data").getString("ResultCode"));
            httpResponseDO.setResultData(jsStr.getJSONObject("Data").getString("ResultData"));
            httpResponseDO.setRawStr(jsStr.getJSONObject("Data").getString("RawStr"));
        }else{
        	if(jsStr.getJSONObject("Data") != null){
        		httpResponseDO.setResultCode(jsStr.getJSONObject("Data").getString("ResultCode"));
            	httpResponseDO.setResultData(jsStr.getJSONObject("Data").getString("ResultData"));
        	}
        	httpResponseDO.setErrCode(jsStr.getString("ErrCode"));
            httpResponseDO.setMessage(jsStr.getString("Message"));
        }
        /**调用接口结束*/
        return httpResponseDO;
    }
    /**
     * 充值的时候返回的数据
     * @return
     */
    public static HttpResponseParam getReturnMessage(HttpRequestParam param) throws IOException {
        /**调用接口开始,拼接好接口的链接地址，直接传过去*/
        StringBuffer url = new StringBuffer(InterfaceUtil.ATURL);
        StringBuffer sign = new StringBuffer();
        if(param.getRemark() != null && !"".equals(param.getRemark())){
        	sign.append("accessKey=").append(param.getAccessKey())
            .append("&action=").append(param.getAction())
            .append("&money=").append(param.getMoney())
            .append("&noncestr=").append(param.getNonceStr())
            .append("&option=").append(param.getOption())
            .append("&remark=").append(param.getRemark())
            .append("&roleType=").append(param.getRoleType())
            .append("&roleValue=").append(param.getRoleValue())
            .append("&tranSN=").append(param.getTranSN())
            .append("&userId=").append(param.getUserId());
            
        }else{
        	sign.append("accessKey=").append(param.getAccessKey())
            .append("&action=").append(param.getAction())
            .append("&money=").append(param.getMoney())
            .append("&noncestr=").append(param.getNonceStr())
            .append("&roleType=").append(param.getRoleType())
            .append("&roleValue=").append(param.getRoleValue())
            .append("&tranSN=").append(param.getTranSN())
            .append("&userId=").append(param.getUserId())
            .append("&withdraw=").append(param.getWithdraw());
        }
        //加密参数
        String getUrl = MD5Utils.getMD5(sign.toString()+"javamanager");//签名的值

        url.append(sign).append("&sign=").append(getUrl);//整个访问的链接地址
        System.out.println(url.toString());
        String message =  GetAPI(url.toString());
        HttpResponseParam httpResponseDO = getReturn(message);
        return httpResponseDO;
    }
    
    public static void main(String[] args){
    	/*String type = "";
    	String date = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
		//单笔对账 
		String merch_date = TimeUtils.getTimeUtilDay();
    	String merch_prod = "专车";
    	String merch_serial = "old1234567890123456789";
    	String xml = new ReconciliationSercive().getXml(merch_date,merch_prod,merch_serial);
    	type = "单笔对账";
    	//对总账
    	String tx_date = TimeUtils.getTimeUtilDay();
    	String merch_prod = "出租车";
		String xml = new ReconciliationSercive().getXml2(tx_date,merch_prod);
		type = "对总账";
    	//请求的参数存入Map
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("COMMID", "001");
    	map.put("NTBNBR", "20164291");
    	map.put("SIGTIM", date);
    	map.put("TRSCOD", "CMHD");//CMDZ
		String message = PayUtil.post(new ReconciliationSercive().buildMessage(xml,map));
		
		System.out.println("返回的消息："+message);
		new ReconciliationSercive().analysisPkg(message,type);*/
    	System.out.println(Math.abs(Double.parseDouble("-100".toString())));
    }
}
