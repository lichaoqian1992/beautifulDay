package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.service.ConversationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/14.
 */
@RestController
@RequestMapping(value = "/main")
public class MainController extends BaseController{

    @Autowired
    private ConversationService service;
    /**
     * 获取今日会话数据
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/getConversation")
    public String getConversation(@Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            result = service.getConversationToday();
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }
}
