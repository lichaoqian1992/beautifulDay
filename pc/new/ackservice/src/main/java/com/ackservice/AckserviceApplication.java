package com.ackservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableTransactionManagement
public class AckserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AckserviceApplication.class, args);
	}
}
