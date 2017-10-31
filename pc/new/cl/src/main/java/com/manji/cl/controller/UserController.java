package com.manji.cl.controller;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.params.ShareAndUserParam;
import com.manji.cl.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/8/29.
 * 用户管理   主要是服务于  推广用户模块，记录业务员推广的用户数量
 * 主要功能  1.推荐用户注册  2.查询业务员推荐的用户数量
 */
@Controller
@RequestMapping(value = "/user")
@Validated
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 新增注册用户   用户扫码业务员提供的二维码注册满集网，注册成功，算该业务员的一个绩效
     * @return
     */
    @RequestMapping(value = "/registerUser")
    @ResponseBody
    public String registerUser(@Param("buyer_id") String buyer_id, @Param("user_id") String user_id){

        BaseResult result = service.registerUser(buyer_id,user_id);
        return JSONObject.toJSON(result).toString();
    }

    /**\
     * 查询业务员当月或者当天的用户量
     * @param param
     * @return
     */
    @RequestMapping(value = "/findUser")
    @ResponseBody
    public String findUser(@Valid ShareAndUserParam param, BindingResult bindingResult) throws ParseException {

        BaseResult result = new BaseResult();
        if(bindingResult.hasErrors()){
            result.setCode("404");
            result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
            result.setResult("");
        }else{

            result = service.findUser(param.getId(),param.getType());
        }
        return JSONObject.toJSON(result).toString();
    }
}
