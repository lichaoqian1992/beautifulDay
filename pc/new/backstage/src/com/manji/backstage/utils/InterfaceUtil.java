package com.manji.backstage.utils;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    public final static String ATURL = "http://service.manji.com/AppService.asmx/UserOffline";//测试接口地址
    //public final static String ATURL = "http://pay.manjiwang.com/javaio.ashx?";//正式接口地址

    static OkHttpClient httpClient=new OkHttpClient();




    
    /*
    发送get异步请求
     */
    public static  String GetAPI(String url, TreeMap<String,String> map) throws IOException {
        String params="";
        for(String key:map.keySet()){
            params+="&"+key+"="+map.get(key);
        }
        String newparams=params.substring(1,params.length());
        System.out.println(url);
        System.out.println(newparams);
        String getUrl=url+"?"+newparams;
     /*   String sign=MD5util.getMD5(newparams+"javamanager");
        String uu=getUrl+"&sign="+sign;*/

        System.out.println(getUrl);
        final Object[] objs = new Object[1];
        Request request = new Request.Builder().get().url(getUrl).build();
/*        Request request = new Request.Builder().get().url(uu).build();
*/        Response response = httpClient.newCall(request).execute();

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


}
