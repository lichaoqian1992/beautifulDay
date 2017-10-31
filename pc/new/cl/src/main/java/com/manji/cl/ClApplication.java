package com.manji.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Administrator on 2017/8/29.
 */
@SpringBootApplication //@EnableAutoConfiguration、@ComponentScan和@Configuration的合集。@EnableScheduling:表明是一个任务
@ServletComponentScan
@EnableDiscoveryClient //服务于eureka
@EnableFeignClients//启动feign服务
public class ClApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ClApplication.class, args);
    }
}
