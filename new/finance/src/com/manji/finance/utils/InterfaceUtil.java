package com.manji.finance.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by YYR on 16/8/31.(用户提现请求接口工具)
 */
public class InterfaceUtil {
    /**
     * httpclient请求
     */
    public final static String SUCCESS = "success";//正确状态
    public final static String ERROR = "error";//错误状态
    //public final static String ATURL = "http://pay.manji.com/javaio.ashx?";//开发接口地址
    //public final static String ATURL = "http://paytest.manji.com/javaio.ashx?";//测试接口地址
    public final static String ATURL = "http://pay.manjiwang.com/javaio.ashx?";//正式接口地址

    public final static String INDEXATURL = "http://pa.manji.com";//测试接口地址

    static OkHttpClient httpClient = new OkHttpClient();


    public static void PostAplus(String url, String data) throws IOException {
        RequestBody formBody = new FormBody.Builder().add("json", data).build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    public static boolean GetAplus(String url, Map<String, Object> map) throws IOException {
        String params = JSON.toJSONString(map);
        String getUrl = url + "&result=" + params;
        System.out.println("get请求数据：" + getUrl);
        boolean flag = false;
        Request request = new Request.Builder().get().url(getUrl).build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String returnTxt = response.body().string();
            System.out.println("接收到的数据：" + returnTxt);
            if (returnTxt.equals("true")) {
                flag = true;
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

        RequestBody formBody = new FormBody.Builder().add("json", data).build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("post请求成功");
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


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
    public static boolean checkRequest(JSONObject body) {
        return true;
    }

    /*
    发送get异步请求(无加密方式)首页统计请求
     */
    public static String IndexGetAPI(String url, TreeMap<String, String> map) throws IOException {
        String params = "";
        for (String key : map.keySet()) {
            String cKey = key;
            String value = map.get(key);
            params += "&" + cKey + "=" + value;
        }

        String getUrl=url;

        if(params!=""){
            String newparams = params.substring(1, params.length());
            getUrl = getUrl + "?" + newparams;
        }

        final Object[] objs = new Object[1];
        Request request = new Request.Builder().get().url(getUrl).build();
        Response response = httpClient.newCall(request).execute();

        if (response == null) {
            return "null";
        }
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
