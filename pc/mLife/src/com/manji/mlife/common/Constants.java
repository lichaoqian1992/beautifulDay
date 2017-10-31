package com.manji.mlife.common;

public class Constants {
	/**
	 * 天气预报使用常量
	 */
	// 根据所在IP获取城市信息，请求URL
	// 原来请求地址http://apis.baidu.com/showapi_open_bus/ip/ip
	public static final String WEATHER_IP_URL = "http://apis.baidu.com/showapi_open_bus/weather_showapi/ip2weather";
	
	// 根据城市信息获取城市名 + 城市代码,请求URL
	public static final String WEATHER_CITYINFO_URL = "http://apis.baidu.com/apistore/weatherservice/cityinfo";
	 
	// 根据城市代码获取天气信息，请求URL
	public static final String WEATHER_CITY_CODE = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
	
	// 默认天气查询城市(重庆)代码
	public static final String DEFAULT_CITY_CODE = "101040100";
}
