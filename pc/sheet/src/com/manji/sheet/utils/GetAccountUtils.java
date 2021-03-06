package com.manji.sheet.utils;

import com.manji.sheet.model.bean.Account;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by pudding on 2017-6-20.(YYR 获取账户信息)
 */
public class GetAccountUtils {

	private static Logger logger;
	
	
	//public final static String ACCOUNTURL = "http://service.manji.com/AppService.asmx/UserMessageGet";//开发地址
    public final static String ACCOUNTURL = "http://servicetest.manji.com/AppService.asmx/UserMessageGet";//测试地址
    //public final static String ACCOUNTURL = "http://service1.manjiwang.com/AppService.asmx/UserMessageGet";//正式地址


    //获取账户详细数据
    public static Account getAccount(String sessionId) {
        long currentDate =System.currentTimeMillis();
        String roundNumber=MD5util.getMD5(currentDate+sessionId).toLowerCase();
        
        int isResponseJson =1;

        String loginType ="Third";
        Map<String,String> params =new HashMap<String,String>();

        params.put("sessionID", sessionId);
        params.put("currentDate", currentDate+"");
        params.put("roundNumber", roundNumber);
        params.put("isResponseJson", isResponseJson+"");
        params.put("loginType", loginType);
//        logger.info("====================连接地址===="+ACCOUNTURL);
        String json=postHttpRequest(ACCOUNTURL,params);
        Account userinfo=new Account();
        if(null ==json){
        	return userinfo;
        }
        
        JSONObject jsonObject=JSONObject.fromObject(json);
//        logger.info("====================返回消息     ========="+ JSONObject.fromObject(jsonObject).toString());
        Integer State=jsonObject.getInt("State");
   
        //请求成功
        if (State==1){
            //获取账户信息
            JSONObject UserInfo=jsonObject.getJSONObject("Datas");
            userinfo.setUser_id(UserInfo.get("user_id").toString());
            userinfo.setUser_role_type(UserInfo.getString("user_role_type"));
            userinfo.setUser_role_value(UserInfo.get("user_role_value").toString());
            userinfo.setMobile(UserInfo.getString("mobile"));
            userinfo.setNick_name(UserInfo.getString("nick_name"));
        }
        return userinfo;
    }

    //POST请求接口
    public static String postHttpRequest(String path,Map<String,String> params){
        String html="";
        String param = "";
        if (params != null && params.size() > 0) {
            // 第一种：普遍使用，二次取值
            String and = "&";
            String cs = "";
            for (String key : params.keySet()) {

                cs += key + "=" + params.get(key) + and;
            }
            param +=cs;
            param = param.substring(0, param.length() - 1);
        }

        PrintWriter out = null;
        BufferedReader in = null;

        try {
            System.out.println(path);
            URL u = new URL(path);
            URLConnection urlcon =  u.openConnection();

            // 设置通用的请求属性
            urlcon.setRequestProperty("accept", "*/*");
            urlcon.setRequestProperty("connection", "Keep-Alive");
            urlcon.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            urlcon.setDoOutput(true);
            urlcon.setDoInput(true);
            out = new PrintWriter(urlcon.getOutputStream());
            // 发送请求参数
            out.print(new String(param.getBytes("utf-8")));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(urlcon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                html += line;
            }
        } catch (MalformedURLException e) {
            html = "不支持的协议";
            e.printStackTrace();
        } catch (IOException e) {
            html = "服务未响应";
            e.printStackTrace();
        }
        return html;
    }

}
