package com.manji.financesystem.utils;

import java.util.Random;

/**
 * 生成验证码
 * Created by Administrator on 2017/2/25.
 */
public class VerificationCodeUtils {

    /**
     * 生成一个4位的验证码
     * @return
     */
    public String getYzm(){

        int a = (int)((Math.random()*9+1)*1000);
        return a+"";
    }
}
