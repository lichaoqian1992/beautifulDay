package com.manji.msgservice;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.manji.msgservice.common.utils.BootstrapHelper;

@SpringBootApplication
@MapperScan(basePackages="com.manji.msgservice.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableTransactionManagement//开启事物支持
public class MsgApplication {
	public static void main(String[] args){
		long begin = System.currentTimeMillis();
		if(StringUtils.isEmpty(System.getProperty("spring.profiles.active"))){
			new BootstrapHelper(8905,"dev");
		}
		SpringApplication.run(MsgApplication.class, args);
		long end = System.currentTimeMillis();
		BootstrapHelper.log(end - begin);
	}
}
