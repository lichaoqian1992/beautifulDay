package com.manji.messageserver.controller;

import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.dao.entity.MessageDO;
import com.manji.messageserver.dao.repository.MessageDAO;
import com.manji.messageserver.requestParam.TestRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.service.TestService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * Created by pudding on 2016-12-12.
 */

@RestController
@Log
public class TestController {

    @Autowired
    private MessageDAO messageDAO;


    @Autowired
    private TestService testService;

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService CommonService;

    @RequestMapping("/test")
    public BaseResult test(TestRequestParam param) {
        return testService.test(param);
    }


    @RequestMapping("/testToken")
    public BaseResult testToken() {
        log.info(hxConfig.getClientId());
        log.info(hxConfig.getClientSecret());
        CommonService.getToken(hxConfig);
        return BaseResult.getSuccessResult("OK");
    }


    @RequestMapping("/save")
    public String testsave(){
        MessageDO messageDO=new MessageDO();
        messageDO.setCome("12321");
        messageDO.setTo("qweqwe");
        messageDO.setChatType("asdasd");
        messageDO.setMsgId("qweqwewq");
        messageDO.setTimestamp(new Date());
        messageDO.setType("123123");
        messageDAO.save(messageDO);
        return "asdasd";

    }





}
