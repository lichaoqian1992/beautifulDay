package com.manji.financesystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by pudding on 2017-1-12.
 */
@ConfigurationProperties("spring.thymeleaf")
public class ThymeleafProperties {
    public static final String DEFAULT_PREFIX = "classpath:/templates/**";

    public static final String DEFAULT_SUFFIX = ".html";

    private String prefix = DEFAULT_PREFIX;

    private String suffix=  DEFAULT_SUFFIX;

    private String mode="HTML5";

    private String encoding="UTF-8";

    private String contentType="text/html";

    private boolean cache=true;

}
