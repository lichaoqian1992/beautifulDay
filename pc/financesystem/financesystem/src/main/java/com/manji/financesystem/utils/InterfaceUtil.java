package com.manji.financesystem.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karben on 16/8/31.
 */
public class InterfaceUtil {
    /**
     * httpclient请求
     */
    public final static String SUCCESS = "success";//正确状态
    public final static String ERROR = "error";//错误状态
    public final static String ATURL = "http://pay.manji.com/javaio.ashx?";

    static OkHttpClient httpClient=new OkHttpClient();




    public static void PostAplus(String url,String data) throws IOException{
        RequestBody formBody =new FormBody.Builder().add("json",data).build();

        Request request=new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    public static boolean GetAplus(String url,Map<String,Object> map)throws  IOException{
        String params=JSON.toJSONString(map);
        String getUrl=url+"&result="+params;
        System.out.println("get请求数据："+getUrl);
        boolean flag = false;
        Request request = new Request.Builder().get().url(getUrl).build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String returnTxt=response.body().string();
            System.out.println("接收到的数据："+returnTxt);
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
    public static  String GetAPI(String url, TreeMap<String,String> map) throws IOException {
        String params="";
        for(String key:map.keySet()){
            params+="&"+key+"="+map.get(key);
        }
        String newparams=params.substring(1,params.length());
        String getUrl=url+newparams;
        String sign=MD5Utils.getMD5(newparams+"javamanager");
        String uu=getUrl+"&sign="+sign;


        final Object[] objs = new Object[1];
        Request request = new Request.Builder().get().url(uu).build();
        Response response = httpClient.newCall(request).execute();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                objs[0]=null;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print("请求发送成功");
                 objs[0]=response;
            }
        });
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
     * 验证发送请求方
     */
    public static boolean checkRequest(JSONObject body){
        return true;
    }
}
