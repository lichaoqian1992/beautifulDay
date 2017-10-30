package com.manji.cusystem.configure;

import com.manji.cusystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/8/29.
 */
@Configuration   //标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/con/**");  //拦截会话相关的请求
       // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/sheet/**");//拦截工单相关的请求
    }
}
