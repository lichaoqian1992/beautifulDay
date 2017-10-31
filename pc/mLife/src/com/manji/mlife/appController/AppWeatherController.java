package com.manji.mlife.appController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.mlife.common.Constants;
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
@RequestMapping("/app/weather")
public class AppWeatherController {
	/**
	 * 
	    天气查询_带历史7天和未来的4天
	 * @author gaochao
	 * @param httpUrl
	 * @param httpArg
	 * @return
	 * 2016年7月13日上午10:33:56
	 * String
	 *
	 */
	@RequestMapping(value="/getListData",method=RequestMethod.GET,produces = " text/html;charset=UTF-8")
	@ResponseBody
	public String weather(String cityname, String citycode) throws ServletException, IOException{
		if(!"".equals(citycode) && !"".equals(cityname)){
			//转编码格式为UTF-8
			String city=URLEncoder.encode(cityname, "UTF-8").replaceAll("\\+","%20");
			Map<String, String> cityip=new HashMap<String, String>();
			cityip.put("cityname", city);
			String jsoncity = WeatherLoadJson.request(Constants.WEATHER_CITYINFO_URL, cityip);
				
			JSONObject objcity = JSONObject.fromObject(jsoncity);
			System.out.println("城市信息"+objcity);
				
			String retMsg = objcity.get("retMsg").toString();
			if(retMsg.equals("success")){
				JSONObject retData = objcity.getJSONObject("retData");
				cityname = retData.getString("cityName");
				citycode = retData.getString("cityCode");
			}else{
				// region ="重庆";
				cityname = "重庆";
				citycode = Constants.DEFAULT_CITY_CODE;
			}
		} else {
			cityname = "重庆";
			citycode = Constants.DEFAULT_CITY_CODE;
		}
		
		// 查询天气城市+编码
		Map<String, String> map=new HashMap<String, String>();
		map.put("cityname", cityname);
		map.put("cityid", citycode);
		String retJson = WeatherLoadJson.request(Constants.WEATHER_CITY_CODE, map);
		JSONObject retObj = JSONObject.fromObject(retJson);
	
		String retCode = retObj.getString("errNum").toString();
		String retData= retObj.getString("retData");
		// 判断返回结果
		if("0".equals(retCode) && !"数据查询异常!".equals(retData)){
			retObj = WeatherType.weatherType(retObj);
		}
		return retObj.toString();
	}
}
