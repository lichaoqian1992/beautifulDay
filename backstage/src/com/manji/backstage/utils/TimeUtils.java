package com.manji.backstage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	
	
	public static void main(String[] args){
		
		
		
		
	}
	
	
	public static String getDate(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		long times =System.currentTimeMillis(); 
		Date date =new Date(times);
		String currDate =sdf.format(date)+"-01";
		
		return currDate;
	}
	
	public static String getCurrentTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
		long times =System.currentTimeMillis(); 
		Date date =new Date(times);
		String curTime =sdf.format(date);
		return curTime;
	}

}
