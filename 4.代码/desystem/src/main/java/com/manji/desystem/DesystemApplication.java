package com.manji.desystem;

import com.manji.desystem.common.util.application.BootstrapHelper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.manji.desystem.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableDiscoveryClient
@EnableFeignClients
public class DesystemApplication {

    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        if (StringUtils.isEmpty(System.getProperty("spring.profiles.active"))) {
            new BootstrapHelper(8803, "online");
        }
        SpringApplication.run(DesystemApplication.class, args);
        long end = System.currentTimeMillis();
        BootstrapHelper.log(end - begin);
    }
}
