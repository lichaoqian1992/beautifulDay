package com.manji.financesystem.utils;

import com.alibaba.fastjson.JSONObject;
import com.manji.financesystem.requestParam.HttpRequestParam;
import com.manji.financesystem.secondaryDomain.entity.extra.HttpResponseDO;
import okhttp3.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/7.
 */
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
    public static  String GetAPI(String url) throws IOException {

        Request request = new Request.Builder().get().url(url).build();
        Response response = httpClient.newCall(request).execute();

        if(response==null){
            return "null";
        }
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 拼接URL
     * @param param
     * @return
     */
    public static HttpResponseDO getReturn(String message){
        HttpResponseDO httpResponseDO = new HttpResponseDO();
        JSONObject jsStr = JSONObject.parseObject(message);

        httpResponseDO.setErrCode(jsStr.getString("ErrCode"));
        httpResponseDO.setMessage(jsStr.getString("Message"));
        httpResponseDO.setAction(jsStr.getJSONObject("Data").getString("Action"));
        httpResponseDO.setQuerySN(jsStr.getJSONObject("Data").getString("QuerySN"));
        httpResponseDO.setTranSN(jsStr.getJSONObject("Data").getString("TranSN"));
        httpResponseDO.setResultCode(jsStr.getJSONObject("Data").getString("ResultCode"));
        httpResponseDO.setResultData(jsStr.getJSONObject("Data").getString("ResultData"));
        httpResponseDO.setRawStr(jsStr.getJSONObject("Data").getString("RawStr"));
        /**调用接口结束*/
        return httpResponseDO;
    }
    /**
     * 充值的时候返回的数据
     * @return
     */
    public static HttpResponseDO getReturnMessage(HttpRequestParam param) throws IOException {
        /**调用接口开始,拼接好接口的链接地址，直接传过去*/
        StringBuffer url = new StringBuffer("http://pay.manji.com/javaio.ashx?");
        StringBuffer sign = new StringBuffer();
        sign.append("accessKey=").append(param.getAccessKey())
            .append("&action=").append(param.getAction())
            .append("&money=").append(param.getMoney())
            .append("&noncestr=").append(param.getNonceStr())
            .append("&roleType=").append(param.getRoleType())
            .append("&roleValue=").append(param.getRoleValue())
            .append("&tranSN=").append(param.getTranSN())
            .append("&userId=").append(param.getUserId())
            .append("&withdraw=").append(param.getWithdraw());
        //加密参数
        String getUrl = MD5Utils.getMD5(sign.toString()+"javamanager");//签名的值

        url.append(sign).append("&sign=").append(getUrl);//整个访问的链接地址
        System.out.println(url.toString());
        String message =  GetAPI(url.toString());
        HttpResponseDO httpResponseDO = getReturn(message);
        return httpResponseDO;
    }
    /**
     * 撤销充值的时候返回的数据
     * @return
     */
    public static HttpResponseDO getRollBackMessage(HttpRequestParam param) throws IOException {
        /**调用接口开始,拼接好接口的链接地址，直接传过去*/
        StringBuffer url = new StringBuffer("http://pay.manji.com/javaio.ashx?");
        StringBuffer sign = new StringBuffer();
        sign.append("accessKey=").append(param.getAccessKey())
                .append("&action=").append(param.getAction())
                .append("&money=").append(param.getMoney())
                .append("&noncestr=").append(param.getNonceStr())
                .append("&rechargeTranSN=").append(param.getTranSN())
                .append("&roleType=").append(param.getRoleType())
                .append("&roleValue=").append(param.getRoleValue())
                .append("&tranSN=").append(param.getTranSN())
                .append("&userId=").append(param.getUserId())
                .append("&withdraw=").append(param.getWithdraw());
        //加密参数
        String getUrl = MD5Utils.getMD5(sign.toString()+"javamanager");//签名的值

        url.append(sign).append("&sign=").append(getUrl);//整个访问的链接地址
        System.out.println(url.toString());
        String message =  GetAPI(url.toString());
        HttpResponseDO httpResponseDO = getReturn(message);
        return httpResponseDO;
    }
}
