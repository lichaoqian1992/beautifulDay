package com.manji.orService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by pudding on 2017-9-12.(YYR)
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.manji.orService.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("配置接口")
                //版本号
                .version("1.0")
                //描述
                .description("开发过程中API随时都有可能变化，请密切关注本文档"
                        + "<br/><h1>工单信息处理</h1>"
                        + "<br/>0  表示业务执行通畅，执行成功"
                        + "<br/>1  校验，业务处理出现异常--判断 code=10001时拿出后端提示的message提示框提示即可"
                        + "<br/>101  请登录--APP判断 code=10002,跳到登录页面"
                        + "<br/>2  系统异常，后端代码抛异常了，直接联系后端开发人员即可")
                .build();
    }
}