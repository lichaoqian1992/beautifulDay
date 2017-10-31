package com.manji.cl.controller;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.dao.User;
import com.manji.cl.feigns.BServiceFeignService;
import com.manji.cl.utils.MD5util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/8/29.
 * 登录控制层
 * 主要功能：1.登录  2.退出登录
 */
@Controller
public class LoginController {

    @Autowired
    private BServiceFeignService feignService;

    @RequestMapping(value = "/")
    public String home(){

        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(@Param("username") String username, @Param("password") String password, HttpServletRequest request, HttpServletResponse response){

        try{
            HttpSession session = request.getSession();
            User user = new User();

            //1.准备调用接口的参数数据
            String code = "0003";//我的项目代码
            String sign = new MD5util().getMD5(username+password+code+"manji");//密钥
            //2.开始调请求口
            String str = feignService.checkUser(username,new MD5util().getMD5(password),code,sign);
            System.out.println(str+"===============");
            //3.处理返回值
            JSONObject obj = (JSONObject) JSONObject.parse(str);


            session.setAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("++++++++++异常++++++++"+e.toString()+"+++++++++++++++++++++++++++++");
        }

        return "test";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }
}
