package com.manji.cusystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Administrator on 2017/8/30.
 */
@Configuration //声明这是个配置作用的bean，替代xml配置
@PropertySource("classpath:application.properties") //表明资源文件的路径
public class FeignConfig {


}
