package com.manji.sheet.utils;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by karben on 16/8/31.(用户提现请求接口工具)
 */
public class InterfaceUtil {
    /**
     * httpclient请求
     */
    public final static String SUCCESS = "success";//正确状态
    public final static String ERROR = "error";//错误状态
    
//    public final static String ATURL = "http://service.manji.com/AppService.asmx/SendMessage";//开发地址
//    public final static String ArbiURL= "http://192.168.0.31:8002/api/ThirdParty/Arbitration";//开发仲裁
    
//    public final static String ATURL = "http://service1.manjiwang.com/AppService.asmx/SendMessage";//正式
//    public final static String ArbiURL= "http://agent.manjiwang.com/api/thirdparty/arbitration";//正式
    
    public final static String ATURL = "http://servicetest.manji.com/AppService.asmx/SendMessage";//测试接口地址
    public final static String ArbiURL= "http://agenttest.manji.com/api/thirdparty/arbitration";//30测试
    
    static OkHttpClient httpClient=new OkHttpClient();

    public static String PostAPI(String url, String sheetId,String sign) throws IOException {

        RequestBody formBody =new FormBody.Builder().add("sheetId",sheetId).add("sign", sign).build();

        Request request=new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("post请求成功");
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    public static  String GetAPI(String url, TreeMap<String,String> map) throws IOException {
        RequestBody formBody =new FormBody.Builder().add("currentDate",map.get("currentDate")).add("isResponseJson", map.get("isResponseJson")).add("loginType", map.get("loginType")).add("roundNumber", map.get("roundNumber")).add("userId", map.get("userId")).add("userRoleType", map.get("userRoleType")).add("userRoleValue", map.get("userRoleValue")).add("title", map.get("title")).add("content", map.get("content")).add("sendType", map.get("sendType")).build();

        Request request=new Request.Builder().url(url).post(formBody).build();

        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("post请求成功");
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    
}
