package com;

import com.manji.tesystem.common.utils.application.BootstrapHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableDiscoveryClient //服务于eureka
@EnableFeignClients//启动feign服务
public class Application {
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		if(StringUtils.isEmpty(System.getProperty("spring.profiles.active"))){
			new BootstrapHelper(8802,"dev");
		}
		SpringApplication.run(Application.class, args);
		long end = System.currentTimeMillis();
		BootstrapHelper.log(end - begin);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
			container.addErrorPages(error404Page, error500Page);
		});
	}
}
