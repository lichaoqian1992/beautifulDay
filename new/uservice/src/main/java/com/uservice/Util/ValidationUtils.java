package com.uservice.Util;

import java.util.List;

/**
 * Created by pudding on 2017-8-28.(YYR)(验证请求是否合法)
 */
public class ValidationUtils {

    /**
     * 验证秘钥是否正确
     * @param pram
     * @param sign
     * @return
     */
    public static boolean Validation(List<Object> pram,String sign){
        String prams="";
        for (int i=0;i<pram.size();i++){
            prams+=pram.get(i);
        }
        prams=prams+"manji";
        String pramsSign=MD5Utils.getMD5(prams);
        if (pramsSign.equals(sign)){
            return true;
        }
        return false;
    }

}
