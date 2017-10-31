package com.manji.mlife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.TrainLineVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.TrainService;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.TrainSeat;

import net.sf.json.JSONObject;
/**
 * 火车票订购
 * @author huanghan
 *
 */
@Controller
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	private TrainService service;
	/**
	 * 查询火车站列表
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getStation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getStation() throws ApiException{

		String stationJson =service.getStation();
		
		return stationJson;

	}	
	
	/**
	 * 查询火车票信息
	 * @param startCity
	 * @param endCity
	 * @param date
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value ="/getTicket", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getTicket(String startCity, String endCity, String date) throws ApiException{
		
		String ticketJson =service.getTicket(startCity, endCity, date);
		
		return ticketJson;
	}
	
	
	@RequestMapping(value ="/getInsuranceItems", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getInsuranceItems() throws ApiException{
		
		String ticketJson =service.getInsurance();
		
		return ticketJson;
	}
	
	/**
	 * 选中车次，填写乘客信息
	 * @param trainLineJson
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/orderTickets" , method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String orderTickets(@RequestParam("trainLineJson")String trainLineJson, String date,HttpServletRequest request){
		HttpSession session=request.getSession();
		// String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		JSONObject obj =JSONObject.fromObject(trainLineJson);
		List<TrainSeat> list =obj.getJSONArray("trainSeats");
		request.setAttribute("trainLine", obj);
		request.setAttribute("trainSeats", list);
		request.setAttribute("trainDate", date);
		request.setAttribute("trainLineJson", trainLineJson);
		
		return "trainOrder";
	}
	
	
	@RequestMapping(value="/createOrder",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String createOrder(String trainLineJson, String passengers,String contactJson,String totalPrice,
			HttpServletRequest request, RedirectAttributes attr) throws ApiException{
	
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		TrainLineVo vo =(TrainLineVo) JSONObject.toBean(JSONObject.fromObject(trainLineJson), TrainLineVo.class);
		//联系人参数
		JSONObject conObj =JSONObject.fromObject(contactJson);
		System.out.println(conObj);
		String contactName =conObj.getString("contactName");
		String contactTel =conObj.getString("contactTel");
		String insuranceId =conObj.getString("insuranceId");
		
		//火车票行程参数
		JSONObject trainObj =JSONObject.fromObject(contactJson);
		String from =trainObj.getString("from");
		String to =trainObj.getString("to");
		String date =trainObj.getString("date");
		
		vo.setFrom(from);
		vo.setTo(to);
		vo.setDate(date);
		
		
		Map<String,String> infoMap =new HashMap<String,String>();
		infoMap.put("contactName", contactName);
		infoMap.put("contactTel", contactTel);
		infoMap.put("userName", userName);
		infoMap.put("passenger", passengers);
		//这样处理之后，保险的字段在没有购买保险的时候就默认为空，不传值到后台，不然传到后台的就是“不购买保险”,会出现错误
		if(!"不购买保险".equals(insuranceId)){
			infoMap.put("insuranceId", insuranceId);
			 
		}
		
		Map<String, String> billMap =service.createOrder(vo ,infoMap,totalPrice);
			if("1".equals(billMap.get("errCode"))){
				attr.addAttribute("errMsg",billMap.get("errMsg"));
				return "redirect:"+billMap.get("redirectURL");//错误页面
			}else{
				System.out.println(sessionId);
				System.out.println(billMap.get("outerTid"));
				System.out.println(billMap.get("itemName"));
				System.out.println(billMap.get("totalPayCash"));
				System.out.println(billMap.get("backSynURL"));
				System.out.println(billMap.get("backAsynURL"));
				System.out.println(billMap.get("redirectURL"));
				long timestamp = System.currentTimeMillis();
				
				attr.addAttribute("order_cate", "OuterOrderPay");	
				attr.addAttribute("partner_channel", "mjLife");
				attr.addAttribute("ismobile", "0");
				attr.addAttribute("session", sessionId);
				attr.addAttribute("order_no", billMap.get("outerTid"));
				attr.addAttribute("order_title", billMap.get("itemName").replace(" ", ""));
				attr.addAttribute("order_money", billMap.get("totalPayCash"));
				attr.addAttribute("return_url", billMap.get("backSynURL"));
				attr.addAttribute("notify_url", billMap.get("backAsynURL"));
				attr.addAttribute("timestamp", timestamp);
				
				attr.addAttribute("sign", MD5.generateSign(attr));
				System.out.println("支付地址："+billMap.get("redirectURL"));
				return "redirect:"+billMap.get("redirectURL");//支付页面
			}
		}
	
	
	
	
	public String cancleOrder(String orderId){
		

		
		
		return "";
	}
	
	public String queryOrder(){
		return "";
	}
	
	
	
	
	

}
