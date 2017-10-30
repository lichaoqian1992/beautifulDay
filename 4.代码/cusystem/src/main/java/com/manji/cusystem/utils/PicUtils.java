package com.manji.cusystem.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
public class PicUtils {

    private static String TEST_URL="http://192.168.0.48:8016/AppService.asmx/UploadFile";
    //private static String TEST_URL="http://service1.manjiwang.com/AppService.asmx/UploadFile";//正式环境

    private static String PRD_URL="http://service2.manjiwang.com/AppService.asmx/UploadFile";

    public static String postPic(MultipartFile file){

        String url =TEST_URL;

        String fileName =file.getOriginalFilename();
        System.out.println(fileName);

        String base64Str =getBase64Code(file);


        Map<String,String> map =new HashMap<String,String>();



        long timestamp =System.currentTimeMillis();
        long current =timestamp/1000;

        String roundStr =current+base64Str+fileName+"asalt" ;

        String roundNumber=MD5util.getMD5(roundStr);

        try {
            base64Str = URLEncoder.encode(base64Str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        map.put("currentDate", current+"");
        map.put("roundNumber", roundNumber.toLowerCase());
        map.put("isResponseJson", "1");
        map.put("loginType", "Third");
        map.put("base64Str", base64Str);
        map.put("fileName", fileName);

        String html =PostUrlUtils.postHttpRequest(url,map);
        //String html = HttpClientUtils.postMethod2(url,map);
        System.out.println(html+"=================");
        String path ="";
        if(!html.equals("服务器未响应") && !html.equals("不支持的协议")){
            JSONObject htmlObj =JSONObject.parseObject(html);
            if("1".equals(htmlObj.getString("State"))){

                path =htmlObj.getJSONObject("Datas").getString("Path");

            }
        }
        return path;
    }




    public static String getBase64Code(MultipartFile file) {

        InputStream in = null;
        byte[] data = null;
        try {

            in = file.getInputStream();
            // in = file.getInputStream();
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String picCode = Base64.encode(data);

        return picCode;

    }



    public static String uploadPic(MultipartFile file,String filePath){

        //文件名称
        String fileName = file.getOriginalFilename();
        //后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath + fileName;
    }
}
