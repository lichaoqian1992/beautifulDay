package com.manji.backstage.datasource;

public class DataSourceContextHolder {

	 private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  

	 public static void setDSType(String dataSourceType) {
		 contextHolder.set(dataSourceType);  
	 }
	 
	 public static String getDSType() {
		 return ((String) contextHolder.get());  
	 }
	 
	 public static void clearDSType() {
		 contextHolder.remove();  
	 }
	 
	 
}
