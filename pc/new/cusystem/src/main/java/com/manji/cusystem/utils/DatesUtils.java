package com.manji.cusystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatesUtils {

	/**
     * 获取前一天日期yyyyMMdd
     * @ 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @ calendar.set(Calendar.YEAR, 2013);
     * @ calendar.set(Calendar.MONTH, 0);
     * @ calendar.set(Calendar.DATE, 1);
     * @ 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyy-MM-dd
     */
    public static String getYestoday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取当前的日期yyyyMMdd
     */
    public static String getToday(){

		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
	public static String time(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long times = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(times);
		String time = sdf.format(date);

		return time;
	}

    /**
     * 获取明天
     * @return
     */
    public static String getTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取最后一天/月
     * @return
     * @throws ParseException 
     */
    public static String lastDayOfMonth(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		int m = Integer.parseInt(d.split("-")[1]);
		 Date date = new Date(sdf.parse(d.split("-")[0]+"-"+(m+1)+"-01").getTime()-24*60*60*1000);
        
        String lastday = sdf.format(date);  
        
		return lastday;
		
	}

	/**
	 * 获取当月第一天
	 * @return
     */
	public static String getFirstDayOfThisMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String time = sdf.format(new Date())+"-01";
		return time;
	}

	/**
	 * 获取下个月第一天
	 * @return
     */
	public static String getFirstDayOfNextMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, +1);
		String time = sdf.format(calendar.getTime())+"-01";
		return time;
	}

	public static String getLastDayOfLastMonth() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(sdf.parse(getFirstDayOfThisMonth()).getTime()-24*60*60*1000);
		return time;

	}
 // 获得本周星期一的日期  
    public static String getCurrentMonday() {  
    	Calendar c = Calendar.getInstance();
    	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
    	  if (day_of_week == 0){
    		  day_of_week = 7;
    	  }
    	  c.add(Calendar.DATE, -day_of_week + 1);
    	  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
    } 
    
    public static void main(String[] args) throws ParseException {
		
    	System.out.println(getLastDayOfLastMonth());
    	
    	Double money = 0.00;
    	double m = 0.01;
    	for(int i=1;i<=30;i++){
    		if(i != 1){
    			m = m*2;
    			money += m;
    		}else{
    			money = m;
    		}
    		//System.out.println(i+"  "+m+"   "+money);
    	}
    	System.out.println("总金额："+money);
	}
}
