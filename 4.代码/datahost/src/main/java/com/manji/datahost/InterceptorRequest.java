package com.manji.datahost.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorRequest extends WebMvcConfigurerAdapter{

	@Bean
	AccessInterceptor localInterceptor(){
		return new AccessInterceptor();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(localInterceptor()).addPathPatterns();
	}
	
}
