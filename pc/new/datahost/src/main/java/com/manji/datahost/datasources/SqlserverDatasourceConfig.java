package com.manji.datahost.datasources;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;



/**
 * sqlserver数据源配置
 * @author Administrator
 *
 */
@Configuration
@MapperScan(basePackages = SqlserverDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlserverSqlSessionFactory")
public class SqlserverDatasourceConfig {

	// 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.manji.datahost.mapper.sqlserver";
    // mapper.xml 路径
    static final String MAPPER_LOCATION = "classpath:mapper/sqlserver/*.xml";
	
    @Value("${sqlserver.datasource.driverClassName}")
    private String driverClass;
    
    @Value("${sqlserver.datasource.url}")
    private String url;
 
    @Value("${sqlserver.datasource.username}")
    private String user;
 
    @Value("${sqlserver.datasource.password}")
    private String password;
    
    @Bean(name = "sqlserverDataSource")
    @Primary
    public DataSource sqlserverDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    
    @Bean(name = "sqlserverTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(sqlserverDataSource());
    }
    
    @Bean(name = "sqlserverSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlserverSqlSessionFactory(@Qualifier("sqlserverDataSource") DataSource sqlserverDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(sqlserverDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SqlserverDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
	
	
}
