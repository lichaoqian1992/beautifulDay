package com.manji.orService;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.manji.orService.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableDiscoveryClient
@EnableFeignClients
public class OrServiceApplication {

	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}


	public static void main(String[] args) {
		SpringApplication.run(OrServiceApplication.class, args);
	}
}
