package com.manji.datahost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@ServletComponentScan
@MapperScan("com.manji.datahost.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableDiscoveryClient
@EnableFeignClients
@Configuration 
@EnableAutoConfiguration 
@EnableEurekaClient
public class DataHostApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DataHostApplication.class, args);
	}
}
