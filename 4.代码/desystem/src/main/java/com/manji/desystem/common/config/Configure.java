package com.manji.desystem.common.config;

import com.manji.desystem.common.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public final class Configure {
	private static final Logger logger = LoggerFactory.getLogger(Configure.class);
	/**
	 * 配置参数集合
	 */
	private static Map<String, String> allConfigMap = new HashMap<String, String>();

	static {
		String profiles = System.getProperty("spring.profiles.active", "dev");
		logger.info("=========================>>>>在什么环境启动？===>"+profiles);
		String fileName = "config/config-" + profiles +  ".properties";
		allConfigMap = PropertiesUtil.getPropMap(fileName);
	}
	/**
	 * @return the configMap
	 */
	public static Map<String, String> getConfigMap() {
		return allConfigMap;
	}
	
	public static String getValue(String key){
		return allConfigMap.get(key);
	}
	
	/**
	 * 获取所有配置文件参数集合
	 */
	public static void setAllConfigMap(Map<String, String> paramsMap) {
		allConfigMap = paramsMap;
	}

	/**
	 * 获取所有配置Map集合
	 * @return Map<String, String>
	 */
	public static Map<String, String> getAllConfigMap() {
		return allConfigMap;
	}

	public static String getDeptId(){
		return PropertiesUtil.getValue("deptId");
	}

}
