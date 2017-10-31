package com.manji.messageserver.utils;

import com.manji.messageserver.config.HxConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.*;
/**
 * Created by Administrator on 2016/12/16.
 */
public class DownloadFileUtil {

    public static void downLoadFile(String url) {
        OutputStream out = null;
        InputStream in = null;
        HxConfig hxConfig = new HxConfig();
        try{
            //1.处理一下url，得到压缩文件的名称
            String fileName = url.split("/")[7].split("\\?")[0];
            //2.链接远程地址
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();
            //3.判断返回的内容是否存在
            long length = entity.getContentLength();
            if(length<=0){
                System.out.println("下载文件不存在");
                return;
            }
            //4.新建一个文件
            File file = new File(hxConfig.getGetZipFileUrl()+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readlength=0;
            //5.写入文件内容
            while((readlength = in.read(buffer))>0){
                byte[] bytes = new byte[readlength];
                System.arraycopy(buffer, 0, bytes, 0, readlength);
                out.write(bytes);
            }
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                out.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
