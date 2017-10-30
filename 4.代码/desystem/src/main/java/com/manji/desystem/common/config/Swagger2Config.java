package com.manji.desystem.common.config;

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
                .apis(RequestHandlerSelectors.basePackage("com.manji.desystem.controller.businesslogic"))
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
                        + "<br/><h1>用户登录信息处理</h1>"
                        + "<br/><h1>状态码说明(http状态码200的情况下)</h1>"
                        + "<br/>0  成功"
                        + "<br/>1  业务出错"
                        + "<br/>2  服务暂不可用"
                        + "<br/>3  未知的方法"
                        + "<br/>100  参数错误"
                        + "<br/>101  未知用户"
                        + "<br/>102  非法操作"
                        + "<br/>103  图片上传错误"
                        + "<br/>104  校验，业务处理出现异常")
                .build();
    }
}