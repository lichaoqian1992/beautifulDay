package com.manji.financesystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2017-2-6.
 */
@Component
@ConfigurationProperties(locations = "classpath:config/cw.properties", prefix = "cw")
@Data
public class CwConfig {

    //文件上传地址
    private String fileUploadPath;
}
