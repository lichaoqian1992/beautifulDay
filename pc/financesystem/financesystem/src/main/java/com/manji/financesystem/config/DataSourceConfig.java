package com.manji.financesystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by pudding on 2017-1-13.
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.primary.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.primary.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.primary.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.primary.datasource.driverClassName"));
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.secondary.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.secondary.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.secondary.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.secondary.datasource.driverClassName"));
        return dataSource;
    }



    @Bean(name = "primaryJdbcTemplate")
    @Qualifier("primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate() {
        return new JdbcTemplate(primaryDataSource());
    }



    @Bean(name = "secondaryJdbcTemplate")
    @Qualifier("secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate() {
        return new JdbcTemplate(secondaryDataSource());
    }

}
