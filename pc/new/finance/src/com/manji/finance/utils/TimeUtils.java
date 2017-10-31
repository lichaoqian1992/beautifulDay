package com.manji.finance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	
	//得到全时间
	public static String getFullTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		return curTime;
	}

	//精确到秒
	public static String getTimeUtilSecond() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		return curTime;

	}
//精确到天
	public static String getTimeUtilDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		return curTime;

	}
//纯数字
	public static String getPureTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		return curTime;

	}
	
	//当月第一天
	public static String getFirstDayInMonth(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		curTime +="-01";
		
		
		return curTime;
		
	}
	//明天
	public static String getTomorrow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long times = System.currentTimeMillis()+24*60*60*1000;
		Date d = new Date(times);
		String tomorrow = sdf.format(d);
		return tomorrow;
		
	}
	//昨天
		public static String getYesterday(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long times = System.currentTimeMillis()-24*60*60*1000;
			Date d = new Date(times);
			String yesteday = sdf.format(d);
			return yesteday;
			
		}
	//下个月第一天
	public static String getFirstDayNextMonth(){
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		
		String[] t =curTime.split("-");
		
		String year =t[0];
		String month =t[1];
		
		if(month.equals(12)){
			year =Integer.valueOf(year)+1+"";
			month ="0"+1;
		}else if(Integer.valueOf(month)<9){
			month ="0"+(Integer.valueOf(month)+1)+"";
		}
		
		//下个月的第一天
		String nextMonthDay =year+"-"+month+"-"+"01 ";
		return nextMonthDay;
	}
	//当月最后一天
	public static String getLastDayInMonth(){
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String curTime = sdf.format(date);
		
		String[] t =curTime.split("-");
		
		String year =t[0];
		String month =t[1];
		
		if(month.equals(12)){
			year =Integer.valueOf(year)+1+"";
			month ="0"+1;
		}else{
			month =Integer.valueOf(month)+1+"";
		}
		
		//下个月的第一天
		String tempTime =year+"-"+month+"-"+"01 ";
		String newTime ="";
		
		try {
			long nextDay =sdf.parse(tempTime).getTime();
			long lastDay =nextDay -24*60*60*1000;
			
			Date newDate = new Date(lastDay);
			
			
			newTime = sdf.format(newDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newTime;
		
	}
	
	public static void main(String[] args){
		String name = getYesterday();
		System.out.println(name.split("-")[0]+name.split("-")[1]+name.split("-")[2]);
		
	}
	

}
