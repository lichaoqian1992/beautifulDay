package com.manji.cl.controller;

import com.manji.cl.feigns.DataHostFeignService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/29.
 * 业务员管理
 */
@Controller
@RequestMapping(value = "/clerk")
public class ClerkController {

    @Autowired
    private DataHostFeignService service;

    @RequestMapping(value = "/find")
    @ResponseBody
    public String find(){

        String str = service.sayHiFromClientOne();
        System.out.println(str+"=============");

        return str;
    }
    @RequestMapping(value = "/getSession")
    @ResponseBody
    public String getSession(@Param("sessionId") String sessionId){

        String str = service.getAccount(sessionId);
        System.out.println(str+"=============");

        return str;
    }
}
