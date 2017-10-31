package com.manji.mlife.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manji.mlife.common.Constants;
import com.manji.mlife.utils.GetIpAddress;
import com.manji.mlife.utils.WeatherLoadJson;
import com.manji.mlife.utils.WeatherType;

import net.sf.json.JSONObject;

/**
 * 百度天气
 * @author gaochao
 * 2016年7月13日上午10:32:27
 * Weather1Controller
 *
 */
@Controller
public class WeatherController {
	private static final Logger logger =Logger.getLogger(WeatherController.class);


	/**
	 * 天气查询_带历史7天和未来的4天
	 * @author gaochao
	 * @param httpUrl
	 * @param httpArg
	 * @return
	 * 2016年7月13日上午10:33:56
	 * String
	 *
	 */
	@RequestMapping(value="/weather",method=RequestMethod.GET,produces = " text/html;charset=UTF-8")
	public String weather(int id, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("obj");
		// 如果obj不为空，则直接返回
		if(attribute != null){
			if (id == 1){
				request.setAttribute("obj", session.getAttribute("obj"));
				// 重定向页面
				request.getRequestDispatcher("/weather/weather2.jsp").forward(request, response);
				return null;
			} else if(id == 0){
				return "/weather1";
			}
		}
		
		String cityName = "";
		String cityCode = "";
		if (id != 3){
		    // 获取访问IP的地址
			String ipAddress = GetIpAddress.getIpAddress(request);
			logger.debug("获取天气预报IP"+ipAddress);
			
			Map<String, String> mapip=new HashMap<String, String>();
			mapip.put("ip", ipAddress);
			String jsonip = WeatherLoadJson.request(Constants.WEATHER_IP_URL, mapip);
			logger.debug("通过IP请求返回json串结果:" + jsonip);
			
			JSONObject objip = JSONObject.fromObject(jsonip);
			JSONObject resBody = objip.getJSONObject("showapi_res_body");
			String ret_code = resBody.getString("ret_code");
			if(!"-1".equals(ret_code)){
				// 城市
				String cityNameStr = resBody.getJSONObject("cityInfo").getString("c5");
				// 转编码格式为UTF-8
				cityNameStr=URLEncoder.encode(cityNameStr, "UTF-8").replaceAll("\\+","%20");
				
				Map<String, String> cityInfoMap=new HashMap<String, String>();
				cityInfoMap.put("cityname", cityNameStr);
				String jsoncity = WeatherLoadJson.request(Constants.WEATHER_CITYINFO_URL, cityInfoMap);
					
				JSONObject objcity = JSONObject.fromObject(jsoncity);
				logger.debug("通过城市名称请求返回城市信息" + objcity);
					
				// 执行成功时，获取城市名称和城市代码
				String errNum = objcity.getString("errNum");
				if("0".equals(errNum)){
					JSONObject retData = objcity.getJSONObject("retData");
					cityName = retData.getString("cityName");
					cityCode = retData.getString("cityCode");
				}
			}
		} else {
			cityName = request.getParameter("cityname");
			cityCode = request.getParameter("citycode");
//			region = request.getParameter("region");
		}
		
		// 如果为空，则设置默认的城市为重庆
		if("".equals(cityName) || "".equals(cityCode)){
			cityName = "重庆";
			cityCode = Constants.DEFAULT_CITY_CODE;
 		}
		
		// 查询天气信息
		Map<String, String> reqMap=new HashMap<String, String>();
		reqMap.put("cityname", cityName);
		reqMap.put("cityid", cityCode);
		String retJson = WeatherLoadJson.request(Constants.WEATHER_CITY_CODE, reqMap);
		JSONObject retObj = JSONObject.fromObject(retJson);
		logger.debug("最终返回的天气信息:"+retObj);
	
		String retCode = retObj.getString("errNum").toString();
		String retData= retObj.getString("retData");
		// 判断返回结果
		if("0".equals(retCode) && retData != null && !"数据查询异常!".equals(retData)){
			retObj = WeatherType.weatherType(retObj);
		} else if (retData == null) {
				retData = retObj.getString("errMsg");
		}
	    session.setAttribute("obj", retObj);
		
	    if(id==0){
			return "/weather1";
		}else{
			// 重定向页面
			request.getRequestDispatcher("/weather/weather2.jsp").forward(request, response);	
		}
	    return null;
	}
}
