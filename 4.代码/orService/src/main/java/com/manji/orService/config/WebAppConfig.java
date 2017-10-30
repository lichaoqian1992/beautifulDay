package com.manji.orService.config;

import com.manji.orService.controller.interceptor.ParaIntercetor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    ParaIntercetor localInterceptor() {
        return new ParaIntercetor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(localInterceptor()).addPathPatterns("/sheet/**");
    }
}
