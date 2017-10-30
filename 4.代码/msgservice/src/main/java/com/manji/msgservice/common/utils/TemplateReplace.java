package com.manji.msgservice.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manji.msgservice.common.exception.BusinessDealException;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateReplace {

    /**
     * 根据参数json 和 模版String 返回完整内容
     * @param jsonStr 是用户传的参数---->> 格式 {username:"张三",webname:"6p"}
     * @param tempStr 模版内容---->> 尊敬的{username}，您在{webname}的订单已发货，订单号为：{orderno}，请注意查收。
     * @return
     * @throws Exception
     */
    public static String getReplaceString(String jsonStr,String tempStr)throws Exception{
        //将tempStr中的 所有"{ }"中的key获取出来 放到一个 List<String>中
        List<String> tempKeys = getTempKeys(tempStr);
        if(tempKeys.size() == 0){
            return tempStr;
        }
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        for (String key: tempKeys) {
            //判断相应key,json中是否有。
            String kValue = jsonObject.getString(key);
            if(StringUtils.isBlank(kValue)){
                throw new BusinessDealException("模版参数【"+ key + "】不允许为空");
            }
            tempStr = tempStr.replace("{"+key+"}",kValue);
        }
        return tempStr;
    }

    /**
     * 获取模版中的 key集合
     * @param tempStr  模版内容---->> 尊敬的{username}，您在{webname}的订单已发货，订单号为：{orderno}，请注意查收。
     * @return
     * @throws Exception
     */
    public static List<String> getTempKeys(String tempStr)throws Exception{
        List<String> list=new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(tempStr);
        while(matcher.find()){
            list.add(matcher.group());
        }
        return list;
    }










    public static void main(String[] args) {
        /*String s = "尊敬的{username}，您在{webname}的订单已发货，订单号为：{orderno}，请注意查收。";
        String[] arr = s.replaceAll("[^\\{\\w*\\}]","").split("(?<=\\})(?=\\{)");

        System.out.println(arr.length);*/

        String test = "尊敬的}，您在}的订单已发货，订单号为：}，请注意查收。";
        List<String> ls=new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(test);
        while(matcher.find()){
            ls.add(matcher.group());
        }
        System.out.println(ls.size());
        for (String string : ls) {
            System.out.println(string);
        }
    }
}
