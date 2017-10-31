package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/4.
 */
@RestController
public class LoginController{

    @Autowired
    private LoginService service;

    /**
     * 登录
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@Param("userName") String userName,@Param("password") String password,HttpServletRequest request){

        BaseResult result = service.checkUser(userName,password);
        HttpSession session = request.getSession();
        session.setAttribute("user",result.getResult());
        System.out.println("登录成功之后的sessionid:"+session.getId());
        session.setMaxInactiveInterval(24*60*60);

        return JSONObject.toJSON(result).toString();
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request){

        BaseResult result = new BaseResult();

        HttpSession session = request.getSession();
        session.removeAttribute("user");

        result.setCode("200");
        result.setContent("退出成功");

        return JSONObject.toJSON(result).toString();
    }
}
