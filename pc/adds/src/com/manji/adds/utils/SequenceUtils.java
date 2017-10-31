package com.manji.adds.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SequenceUtils {

	public static void main(String[] args) {
		today();

		System.out.println(getSerialNo(2));
	}
	
	
	public static String today(){
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyyyyy-MM-dd HH(hh):mm:ss S E D F w W a k K z");  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		long times = System.currentTimeMillis();  
//        System.out.println(times);  
        Date date = new Date(times);  
        String tim = sdf.format(date);  
//        System.out.println(tim);  
        
		return tim;
		
	}
	
	public static String getSerialNo(int serial){
		
		String date =today();
		
		
		serial =serial+10000;
		
		String no =serial+"";
		
		String serialNo =date +"" +no.substring(1);
		
		return serialNo;
	}

}
