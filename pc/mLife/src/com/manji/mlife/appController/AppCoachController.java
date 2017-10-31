package com.manji.mlife.appController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.CoachLineVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.CoachService;
import com.manji.mlife.utils.InitUrl;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/coach")
public class AppCoachController {
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
//		
		return endJson;
	}
	
	/**
	 * 获取车次信息
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
//	/**
//	 * 预订汽车票
//	 * @param coachLineJson
//	 * @param request
//	 * @return
//	 * 暂时未使用
//	 */
//	@RequestMapping(value="/orderTickets", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
//	public String orderTickets(String coachLineJson, HttpServletRequest request ){
//		
//		HttpSession session=request.getSession();
//		String sessionId =(String) session.getAttribute("sessionId");
//		String userName =(String) session.getAttribute("userName");
//		
//		if(null ==userName||"".equals(userName)){
//			return "redirect:/";
//		}
//		
//		JSONObject obj =JSONObject.fromObject(coachLineJson);
//		request.setAttribute("coachLine", obj);
//		request.setAttribute("coachLineJson", obj.toString());
//		return "coachOrder";
//	}
//	
	
	
	/**
	 * 创建车票订单
	 * @param coachLineJson
	 * @param passengersStr
	 * @param contactJson
	 * @param request
	 * @param attr
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String createAppCoachBill(String coachLineJson, String passengersStr, String contactJson, String totalPrice, HttpServletRequest request) throws ApiException{
		
		System.out.println(coachLineJson);
		System.out.println(passengersStr);
		System.out.println(contactJson);
		
		//获取session用户信息
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");
//		
		if(null ==userName||"".equals(userName)){
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "http://life.manjiwang.com/mLife/errInfo?errMsg="+errMsg;	
		}
		
		CoachLineVo vo =(CoachLineVo) JSONObject.toBean(JSONObject.fromObject(coachLineJson), CoachLineVo.class);
		vo.setTicketPrice(totalPrice);
		JSONObject contacter =JSONObject.fromObject(contactJson);
		
		Map<String, String> infoMap =new HashMap<String, String>();
		
		infoMap.put("contactName", contacter.getString("contactName"));
		infoMap.put("idNumber", contacter.getString("idNumber"));
		infoMap.put("contactTel", contacter.getString("contactTel"));
		infoMap.put("passengers", passengersStr);
		infoMap.put("userName", userName);
		
		
		Map<String,String> billMap =service.createOrder(vo ,infoMap);
		
		if("1".equals(billMap.get("errCode"))){
			//错误信息返回
			String errMsg = billMap.get("errMsg");
			
			session.setAttribute("errMsg", errMsg);
			return "http://life.manjiwang.com/mLife/errInfo?errMsg="+errMsg;
		}else{
			//重定向参数
//			attr.addAttribute("order_cate", "OuterOrderPay");	
//			attr.addAttribute("partner_channel", "mjLife");
//			attr.addAttribute("ismobile", "1");
//			attr.addAttribute("session", sessionId);
//			attr.addAttribute("order_no", billMap.get("outerId"));
//			attr.addAttribute("order_title", billMap.get("itemName"));
//			attr.addAttribute("order_money", billMap.get("actPrice"));
//			attr.addAttribute("return_url", billMap.get("backSynURL"));
//			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			
			long timestamp = System.currentTimeMillis();
			Map<String,String> map =new HashMap<String,String>();
			map.put("order_cate", "OuterOrderPay");
			map.put("partner_channel", "mjLife");
			map.put("ismobile", "1");
			map.put("session", sessionId);
			map.put("order_no", billMap.get("outerTid"));
			map.put("order_title", billMap.get("itemName").replace(" ", ""));
			// attr.addAttribute("order_money", "1");
			map.put("order_money", billMap.get("totalPayCash"));
			map.put("return_url", billMap.get("backSynURL"));
			map.put("notify_url", billMap.get("backAsynURL"));
			map.put("timestamp", timestamp+"");
			map.put("sign", MD5.toSign(map));
//			return "redirect:"+billMap.get("redirectURL");//支付页面
			return InitUrl.getUrl(billMap.get("redirectURL"),map);
		}
	}
}
