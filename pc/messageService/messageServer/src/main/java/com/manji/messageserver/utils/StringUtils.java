package com.manji.messageserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by luobairan on 2016/7/28.
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    public static boolean isNotEmpty(String str) {
        return (!(isEmpty(str)));
    }

    public static boolean isNotBlank(String str) {
        return (!(isBlank(str)));
    }

    public static String clean(String str) {
        return ((str == null) ? "" : str.trim());
    }

    public static String trim(String str) {
        return ((str == null) ? null : str.trim());
    }

    public static String getCurrentDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
