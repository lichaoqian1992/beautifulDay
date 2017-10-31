package com.manji.financesystem.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pudding on 2017-2-4.
 */
public class DateUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当月月第一天
     * @return
     */
    public static String getCurrentMonthFirstDayTime() {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return format.format(cal_1.getTime());
    }

    /**
     * 获取当前月最后一天
     * @return
     */
    public static String getCurrentMonthLastDayTime(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime());

    }
}
