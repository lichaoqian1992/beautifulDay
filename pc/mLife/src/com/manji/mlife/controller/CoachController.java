package com.manji.mlife.controller;

import java.util.HashMap;
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

import com.manji.mlife.Vo.CoachLineVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.common.StringUtil;
import com.manji.mlife.service.CoachService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;
/**
 * 汽车票
 * @author huanghan
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachController {
	private static final Logger logger =Logger.getLogger(CoachController.class);
	
	@Autowired
	private CoachService service;
	
	/**
	 * 获取汽车票起始站站点集合
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getStartStation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getStartStation() throws ApiException {
		String startJson =service.getStartStation();
		return startJson;
	}
	
	
	/**
	 * 获取汽车票到达站站点集合
	 * @param startCity
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getEndStation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getEndStation(String startCity) throws ApiException {
		String endJson =service.getEndStation(startCity);
		return endJson;
	}
	
	
	/**
	 * 查询汽车票列表
	 * @param startCity
	 * @param endCity
	 * @param date
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getLines", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getLines(String startCity, String endCity,String date) throws ApiException {
//		System.out.println(startCity+ endCity+ date);
		
		String linesJson =service.getLines(startCity, endCity, date);

		
		return linesJson;
	}
	
	/**
	 * 预订汽车票
	 * @param coachLineJson
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderTickets", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String orderTickets(String coachLineJson, HttpServletRequest request ){
		HttpSession session=request.getSession();
		String userName =(String) session.getAttribute("user_name");
		
		if(StringUtil.isNullOrEmpty(userName)){
			return "redirect:/";
		}
		
		JSONObject obj =JSONObject.fromObject(coachLineJson);
		request.setAttribute("coachLine", obj);
		request.setAttribute("coachLineJson", obj.toString());
		return "coachOrder";
	}
	
	
	@RequestMapping(value="/createOrder",method = RequestMethod.POST)
	public String createOrder(String coachLineJson, String passengersStr, String contactJson, String totalPrice, HttpServletRequest request,  RedirectAttributes attr) throws ApiException{
		logger.debug("汽车车次:" + coachLineJson + "乘客信息:" + passengersStr + "联系人信息:" + contactJson);
		if (StringUtil.isNullOrEmpty(coachLineJson) || StringUtil.isNullOrEmpty(passengersStr) || StringUtil.isNullOrEmpty(contactJson)){
			logger.warn("创建汽车票订单时参数为空!");
			return null;
		}
		
		HttpSession session = request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		if(StringUtil.isNullOrEmpty(userName)){
			return "redirect:/";
		}
		
		CoachLineVo vo =(CoachLineVo) JSONObject.toBean(JSONObject.fromObject(coachLineJson), CoachLineVo.class);
		vo.setTicketPrice(totalPrice);
		JSONObject contacter =JSONObject.fromObject(contactJson);
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("contactName", contacter.getString("contactName"));
		infoMap.put("idNumber", contacter.getString("idNumber"));
		infoMap.put("contactTel", contacter.getString("contactTel"));
		infoMap.put("passengers", passengersStr);
		infoMap.put("userName", userName);
		
		Map<String,String> billMap = service.createOrder(vo, infoMap);
		if(billMap.get("errCode").equals("1")){ // 报错或者抛出异常
			String errMsg = billMap.get("errMsg");
			attr.addAttribute("errMsg",errMsg);
			return "redirect:"+billMap.get("redirectURL"); // 错误页面
		}else{
			// 重定向参数
			long timestamp = System.currentTimeMillis();
			
			attr.addAttribute("order_cate", "OuterOrderPay");	
			attr.addAttribute("partner_channel", "mjLife");
			attr.addAttribute("ismobile", "0");
			attr.addAttribute("session", sessionId);
			attr.addAttribute("order_no", billMap.get("outerTid"));
			attr.addAttribute("order_title", billMap.get("itemName"));
			attr.addAttribute("order_money", billMap.get("totalPayCash"));
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL");//支付页面
		}
	}
}
