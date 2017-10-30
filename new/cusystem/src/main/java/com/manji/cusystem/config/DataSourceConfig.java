package com.manji.cusystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 配置数据源
 */
@Configuration//声明这是个配置作用的bean，替代xml配置
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    @Value("${mysql.datasource.driverClassName}")
    private String driver;
    @Value("${mysql.datasource.url}")
    private String url;
    @Value("${mysql.datasource.username}")
    private String username;
    @Value("${mysql.datasource.password}")
    private String password;
    /*@Value("${mysql.datasource.maxActive}")
    private int maxActive;
    @Value("${mysql.datasource.maxIdel}")
    private int maxIdel;
    @Value("${mysql.datasource.maxWait}")
    private long maxWait;*/

    @Bean
    public DataSource primaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);//用户名
        dataSource.setPassword(password);//密码
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

}
