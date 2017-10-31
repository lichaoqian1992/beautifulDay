package com.manji.cl.controller;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.params.AddShareParam;
import com.manji.cl.params.ShareAndUserParam;
import com.manji.cl.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/8/29.
 * 分享
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController {

    @Autowired
    private ShareService service;
    /**
     * 查询我的分享（当月/当日）
     * @return
     */
    @RequestMapping(value = "/myShare")
    @ResponseBody
    public String myShare(@Valid ShareAndUserParam param, BindingResult bindingResult) throws ParseException {

        BaseResult result = new BaseResult();
        if(bindingResult.hasErrors()){
            result.setCode("404");
            result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
            result.setResult("");
        }else{

            result = service.myShare(param.getId(),param.getType());
        }
        return JSONObject.toJSON(result).toString();

    }
    @RequestMapping(value = "/addShare")
    @ResponseBody
    public String addShare(@Valid AddShareParam param,BindingResult bindingResult){

        BaseResult result = new BaseResult();


        return null;
    }
}
