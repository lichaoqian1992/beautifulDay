package com.manji.messageserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Created by pudding on 2016-12-12.
 */

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
//extends SpringBootServletInitializer
public class ApplicationMain {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(ApplicationMain.class);
//    }
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class);
    }
}
