package com.manji.datahost.datasources;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;


/**
 * mysql数据源配置（主数据源）
 * @author Administrator
 *
 */
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {

	// 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.manji.datahost.mapper.mysql";
    // mapper.xml 路径
    static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";
	
    @Value("${mysql.datasource.driverClassName}")
    private String driverClass;
    
    @Value("${mysql.datasource.url}")
    private String url;
 
    @Value("${mysql.datasource.username}")
    private String user;
 
    @Value("${mysql.datasource.password}")
    private String password;
 
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }
    
    
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
    
	
}
