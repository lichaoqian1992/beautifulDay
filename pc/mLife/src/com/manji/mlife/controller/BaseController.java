package com.manji.mlife.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manji.mlife.common.MD5;
import com.manji.mlife.service.CallbackService;
import com.manji.mlife.utils.LoadJson;
import com.qianmi.open.api.QianmiResponse;

import net.sf.json.JSONObject;

/**
 * 
 * @author gaochao
 * 2016年7月8日下午4:19:04
 * BaseController
 *
 */
@Controller
@RequestMapping("/base")
public class BaseController {
	
	 private static final Logger logger =Logger.getLogger(BaseController.class);
	
	@Autowired
	private  CallbackService callbackService;
	
	/**
	 * 向满集网申请退款接口
	 * @author gaochao
	 * 2016年9月13日上午11:42:42
	 * void
	 *
	 */
	public boolean PartnerOrderBack(String outerId){
		boolean ret=false;
		String payUrl =callbackService.getPayUrl();
		String signKey =callbackService.getSignKey();
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("action", "PartnerOrderBack");
		infoMap.put("partner_channel", "mjLife");
		infoMap.put("order_no", outerId);
		infoMap.put("sign", MD5.sign("PartnerOrderBack", "mjLife", outerId,signKey));
		
		String loadJSON = null;
		JSONObject obj = null;
		try {
			loadJSON = LoadJson.loadJSON(payUrl, infoMap);
			obj = JSONObject.fromObject(loadJSON);
			if (obj != null && "0".equals(obj.getString("ErrCode"))) {
				ret=true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.debug("出现未知异常 ");
			e.printStackTrace();
		}

		logger.debug("------同步并返回满集的数据 " + obj);
		return ret;
	}
	
	/**
	 * @author gaochao
	 * @param response
	 * @return
	 * 2016年7月8日下午3:28:21
	 * String 错误信息
	 */
    public String handleError(QianmiResponse response) {
    	String errorMsg="错误编码:"+response.getSubCode() + " 错误描述:" + response.getSubMsg();
        return errorMsg;
    }
	
}
