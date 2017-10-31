package com.manji.mlife.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.WaterCoalVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.common.StringUtil;
import com.manji.mlife.service.WaterCoalService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;
/**
 * 水电气缴费控制层
 * @author huanghan
 *
 */
@Controller
@RequestMapping("/waterCoal")
public class WaterCoalController {
	private static final Logger logger =Logger.getLogger(WaterCoalController.class);
	
	@Autowired
	private WaterCoalService service;
	
	/**
	 * 查询缴费公司名称 
	 * @param province
	 * @param city
	 * @param mode
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getProps", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getProps(String province, String city, String mode) throws ApiException{
		String propJson = "";
		if (StringUtil.isNullOrEmpty(province) || StringUtil.isNullOrEmpty(city) || StringUtil.isNullOrEmpty(mode)){
			logger.warn("调用接口getProps时参数为空!");
			return null;
		}
		
		propJson = service.queryProps(province, city, mode);
		if(propJson == ""){
		    propJson = "[{\"itemName\":\"此条件查询无记录\"}]"; //[{"itemName":"台州自来水公司","itemId":"64101901"}]
		}
		return propJson;
	}	
	
	/**
	 * 查询账单信息
	 * @param itemId
	 * @param account
	 * @param request
	 * @return
	 * @throws ApiException  
	 */
	@RequestMapping(value = "/queryWaterCoalBill", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String queryBill(String account,String itemId) throws ApiException{
		if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(itemId)){
			logger.warn("调用接口queryWaterCoalBill时参数为空!");
			return null;
		}
		
		String billJson =service.queryBill(account,itemId);
		if (logger.isDebugEnabled()){
			logger.debug(billJson);
		}
		return billJson;
	}
	
	/**
	 * 生成账单并发送给满集平台
	 * @param itemId
	 * @param account
	 * @param price
	 * @param attr
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping("/createBill")
	public String createBill(WaterCoalVo vo, HttpServletRequest request, RedirectAttributes attr) throws ApiException{
		String message = "";
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(StringUtil.isNullOrEmpty(userName)){
			return "redirect:/";
		}
		
		// 生成订单信息
		Map<String,String> billMap = service.createBill(vo,userName);
		if (billMap == null){
			return "redirect:/";
		}
		
		// 获取返回的错误信息
		if (billMap.size() > 0 && billMap.get("errCode").equals("1")){
			message = billMap.get("errMsg");
			attr.addAttribute("errMsg",message);
			return "redirect:"+billMap.get("redirectURL");//支付页面
		}else{
			// 重定向参数
			long timestamp = System.currentTimeMillis();
			
			attr.addAttribute("order_cate", "OuterOrderPay");	
			attr.addAttribute("partner_channel", "mjLife");
			attr.addAttribute("ismobile", "0");
			attr.addAttribute("session", sessionId);
			attr.addAttribute("order_no", billMap.get("outerId"));
			attr.addAttribute("order_title", billMap.get("itemName"));
			attr.addAttribute("order_money", billMap.get("saleAmount"));
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL"); // 支付页面
		}
		
	}
}
