package com.manji.messageserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by pudding on 2016-12-16.
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getFormatStr(Date date){
        String format = dateFormat.format(date);
        return format;
    }

    public static Date getFormatDate(String date){
        try {
           return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYesterdayTime(String format ,int day) {
        String dateStr = "";
        try {
            SimpleDateFormat dft = new SimpleDateFormat(format);
            Date beginDate = new Date();
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
            dateStr = dft.format(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
}
