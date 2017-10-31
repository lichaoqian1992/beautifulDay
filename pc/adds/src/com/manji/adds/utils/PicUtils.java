package com.manji.adds.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PicUtils {
	
//	本地
//	private static String piclocation="F:/pic";
//	private static String urllocation="http://localhost:8900/upload/";
//  測試
	private static String piclocation="D:/pic";
	private static String urllocation="http://adservice.manjiwang.com/upload/";
// 	正式http://adservice.manjiwang.com/
//	private static String piclocation="D:/pic";
//	private static String urllocation="http://219.153.12.172:8090/upload/";
	
	public static void main(String[] args) {
		
		delPic("http://localhost:8900/upload/201610310001/1111.jpg");

	}

	
	public static String getOnePic(String  ad_id){
		
		String path=piclocation+"/"+ad_id;
//		System.out.println(path);
		Random random = new Random();
		
		String pic ="";
		File file = new File(path);
		
		if(file.isDirectory()){
			
			File[] flist = file.listFiles();
			//遍历文件
			
			int index =random.nextInt(flist.length);
			
			File f =flist[index];
				
			pic =urllocation+ad_id+"/"+f.getName();
//			System.out.println(pic);
			
		}
		
		return pic;
		
	}
	
	
	public static List<String> getPics(String ad_id){
		
		String path=piclocation+"/"+ad_id;
//		System.out.println(path);
		
		List<String> list =new ArrayList<String>();
		
		File file = new File(path);
		
		if(file.isDirectory()){
			
			File[] flist = file.listFiles();
			//遍历文件
			for(int i=0;i<flist.length;i++){
				File f = flist[i];
				//如果是文件
				if(f.isFile()){
					
					String pic =urllocation+ad_id+"/"+f.getName();
//					System.out.println(pic);
					list.add(pic);
					
				}
				
			}
		}
		
		return list;
		
	}
	
	public static String getTwoPics(String ad_id){
		
		String path=piclocation+"/"+ad_id;
//		System.out.println(path);
		
		List<String> list =new ArrayList<String>();
		
		File file = new File(path);
		
		if(file.isDirectory()){
			
			File[] flist = file.listFiles();
			//遍历文件
			for(int i=0;i<flist.length;i++){
				File f = flist[i];
				//如果是文件
				if(f.isFile()){
					
					String pic =urllocation+ad_id+"/"+f.getName();
//					System.out.println(pic);
					list.add(pic);
					
				}
				
			}
		}
		
		String picUrls ="";
		for(int k=0;k<list.size();k++){
			
			picUrls +=list.get(k)+",";
			
		}
		
		return picUrls;
		
	}
	
	
	
	public static boolean delPic(String url){
		
//		http://localhost:8900/upload/ad_id/.img;
		boolean flag =false;
		String path =piclocation+url.split("upload")[1];
//		System.out.println(path);
		File file = new File(path);
		
		if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	        	file.delete();  
	        	flag=true;
	        }
	    }  
		return flag;
	}

}
