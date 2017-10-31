package com.manji.mlife.common;

public class StringUtil {
    /**
     * null 安全的空字符串测试
     * 
     * @return 字符串是否为null或者空白字符
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
 
    public static boolean isNotEmpty(String str) {
        return !(str == null || str.isEmpty());
    }
    
    /**
     * 判断是否为null或empty
     * 
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }
    
    /**
     * 讲NUll转换为EMPTY
     * 
     * @param string
     * @return
     */
    public static String nullToEmpty(String string) {
        return (string == null) ? "" : string;
    }
 
    /**
     * 将empty装欢为NUll
     * 
     * @param string
     * @return
     */
    public static String emptyToNull(String string) {
        return isNullOrEmpty(string) ? null : string;
    }
 
    /**
     * 字符窜填充，用padChar填充头部
     * 
     * @param string
     * @param minLength
     * @param padChar
     * @return
     */
    public static String padStart(String string, int minLength, char padChar) {
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        sb.append(string);
        return sb.toString();
    }
 
    /**
     * 字符窜填充，用padChar填充尾部
     * 
     * @param string
     * @param minLength
     * @param padChar
     * @return
     */
    public static String padEnd(String string, int minLength, char padChar) {
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(string);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }
 
    /**
     * 首字母大写
     */
    public static String firstUpperCase(String str) {
        Character ch = str.charAt(0);
        char[] array = str.toCharArray();
        array[0] = Character.toUpperCase(ch);
        return String.valueOf(array);
    }
 
    /**
     * 首字母小写
     * 
     * @param str
     * @return
     */
    public static String firstLowerCase(String str) {
        Character ch = str.charAt(0);
        char[] array = str.toCharArray();
        array[0] = Character.toLowerCase(ch);
        return String.valueOf(array);
    }
 
    public static Object[] concatStr(Object[] obj, String str) {
        Object[] param = new Object[obj.length];
        for (int i = 0; i < obj.length; i++) {
            param[i] = obj[i].toString().concat(str);
        }
        return param;
    }
}