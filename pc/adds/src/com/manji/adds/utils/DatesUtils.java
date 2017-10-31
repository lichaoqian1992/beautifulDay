package com.manji.adds.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DatesUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(thisMonth());
	}

	
	
	
	public static String lastDay(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		long times = System.currentTimeMillis();  
        Date date = new Date(times-86400000);  
        String lastday = sdf.format(date);  
        
		return lastday;
		
	}
	
	public static String thisMonth(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
		long times = System.currentTimeMillis();  
        Date date = new Date(times);  
        String month = sdf.format(date);  

        return month;
	}
	
	public static String today(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		long times = System.currentTimeMillis();  
        Date date = new Date(times);  
        String today = sdf.format(date);  

        return today;
	}
	
	public static String time(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
		long times = System.currentTimeMillis();  
        Date date = new Date(times);  
        String time = sdf.format(date);  

        return time;
	}
	
	
	
	
}
