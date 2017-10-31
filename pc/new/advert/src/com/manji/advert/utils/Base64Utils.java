package com.manji.advert.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream;  
import java.io.OutputStream;




public class Base64Utils {

	 public static void main(String[] args)  
	    {  
		 
		 
		 String a ="abc+bcd";
		 System.out.println(a.replace("+", " "));
	        String strImg = GetImageStr();  
	        System.out.println(strImg);  
	        GenerateImage(strImg);  
	    }  

	 public static String GetImageStr()  
	    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	        String imgFile = "C:/Users/Administrator/Pictures/lovewallpaper/nxch-034.jpg";//待处理的图片  
	        InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imgFile);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	        //对字节数组Base64编码  
	        
	        String picCode =Base64.encode(data);
//	        picCode =picCode.replace("\\+", "%2B");
//	        BASE64Encoder encoder = new BASE64Encoder();
	        
			
	        
	        return picCode;//返回Base64编码过的字节数组字符串  
	    }  
	 
	 
	 public static boolean GenerateImage(String imgStr)  
	    {   //对字节数组字符串进行Base64解码并生成图片  
	    
		 if (imgStr == null) //图像数据为空  
	            return false;  
	        
	        try   
	        {  
	            //Base64解码  
	            byte[] b = Base64.decode(imgStr);
	            for(int i=0;i<b.length;++i)  
	            {  
	                if(b[i]<0)  
	                {//调整异常数据  
	                    b[i]+=256;  
	                }  
	            }  
	            //生成jpeg图片  
	          
	            String imgFilePath = "d://222.jpg";//新生成的图片  
	            OutputStream out = new FileOutputStream(imgFilePath);      
	            out.write(b);  
	            out.flush();  
	            out.close();  
	            return true;  
	        }   
	        catch (Exception e)   
	        {  
	        	e.printStackTrace();
	            return false;  
	        }  
	    }  
	 
	 
	 
	 public static String GetBase64Code(File file){
		 
		 InputStream in = null;  
		 byte[] data = null;  
		 try   
	        {  
			 
			 	in = new FileInputStream(file);
//	            in = file.getInputStream();
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close(); 
	        }  catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
		 
		
//	        BASE64Encoder encoder = new BASE64Encoder();
//	        String picCode =encoder.encode(data);
	        
		 	String picCode =Base64.encode(data);
//		 	picCode =picCode.replace("+"," ");
 		
		return picCode;
	 }
}