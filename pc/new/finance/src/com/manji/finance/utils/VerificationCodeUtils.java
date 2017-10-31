package com.manji.finance.utils;

public class VerificationCodeUtils {

	/**
     * 生成一个6位的验证码
     * @return
     */
    public static String getYzm(){

        int a = (int)((Math.random()*9+1)*100000);
        return a+"";
    }
}
