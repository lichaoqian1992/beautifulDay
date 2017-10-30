package com.manji.backstage.datasource;


import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态链接数据源
 * @author huanghan
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	
	private final static Logger  logger  = Logger.getLogger("DynamicDataSource");   
	
	@Override
	protected Object determineCurrentLookupKey() {
		logger.info(DataSourceContextHolder. getDSType());
		 return DataSourceContextHolder. getDSType();  
		 
	}
	
	


	
}
