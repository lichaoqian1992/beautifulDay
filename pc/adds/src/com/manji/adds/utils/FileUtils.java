package com.manji.adds.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	public static void makeFolder(String path){
		
		File file =new File(path);
		
		if  (!file .exists()  && !file .isDirectory())
		{       
		    System.out.println("路径："+path+"  不存在");  
		    file.mkdirs();
		    System.out.println("路径："+path+"  已添加存在");
		} else   
		{  
		    System.out.println("//路径："+path+" 已存在");  
		    
		} 
		
	}
	
	public static boolean makeFile(String path){
		
		 File file=new File(path);    
		    if(!file.exists())    
		    {    
		        try {    
		            file.createNewFile();   
		            System.out.println("文件创建成功");
		        } catch (IOException e) {    
		            e.printStackTrace();    
		            System.out.println("文件创建失败");
		            return false;
		        }    
		    }else
		    {
		    	System.out.println("文件已存在");
		    }
		    return true;
	}
	
	public static void searchFile(String path){
		
		  File file=new File(path);
		  File[] tempList = file.listFiles();
		  System.out.println("该目录下对象个数："+tempList.length);
		  for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
		    System.out.println("文     件："+tempList[i]);
		   }
		   if (tempList[i].isDirectory()) {
		    System.out.println("文件夹："+tempList[i]);
		   }
		
		  }
		
	}
	
	
}
