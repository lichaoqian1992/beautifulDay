package com.manji.desystem.common.config;

import com.manji.desystem.controller.interceptor.LoginIntercetor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    LoginIntercetor localInterceptor() {
        return new LoginIntercetor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(localInterceptor());
    }
}
