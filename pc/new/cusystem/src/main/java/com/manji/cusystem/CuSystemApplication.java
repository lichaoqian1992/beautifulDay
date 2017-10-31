package com.manji.cusystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2017/8/31.
 */
@SpringBootApplication //@EnableAutoConfiguration、@ComponentScan和@Configuration的合集。@EnableScheduling:表明是一个任务
@ServletComponentScan
//@EnableZuulProxy        //启动zuul服务
@EnableDiscoveryClient //服务于eureka
@EnableFeignClients//启动feign服务
@EnableTransactionManagement//开启事物支持
public class CuSystemApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CuSystemApplication.class, args);
    }
}
