package com.uservice.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2017-8-24.(YYR)
 */
@Component
@ConfigurationProperties(locations = "classpath:log.properties", prefix = "log")
public class LogConfig {

    //文件上传地址
    private String fileUploadPath;
}
