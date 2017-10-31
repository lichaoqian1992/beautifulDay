package com.manji.mlife.appController;

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
import com.manji.mlife.utils.InitUrl;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.TrainSeat;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/train")
public class AppTrainController {

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
	@RequestMapping(value="/orderTickets" , method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String orderTickets(@RequestParam("trainLineJson")String trainLineJson, String date,HttpServletRequest request){
		
		JSONObject obj =JSONObject.fromObject(trainLineJson);
		List<TrainSeat> list =obj.getJSONArray("trainSeats");
		request.setAttribute("trainLine", obj);
		request.setAttribute("trainSeats", list);
		request.setAttribute("trainDate", date);
		request.setAttribute("trainLineJson", trainLineJson);
		
		return "trainOrder";
	}
	
	
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String createOrder(String trainLineJson, String passengers,String contactJson, String trainJson,String totalPrice,
			HttpServletRequest request, RedirectAttributes attr) throws ApiException{
	
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");
		
		if(null ==userName||"".equals(userName)){
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "http://life.manjiwang.com/mLife/errInfo?errMsg="+errMsg;
		}
		
		System.out.println(trainLineJson);
		
		
		TrainLineVo vo =(TrainLineVo) JSONObject.toBean(JSONObject.fromObject(trainLineJson), TrainLineVo.class);
		//联系人参数
		JSONObject contactObj =JSONObject.fromObject(contactJson);
		
		String contactName =contactObj.getString("contactName");
		String contactTel =contactObj.getString("contactTel");
		String insuranceId="";
		if(null !=contactObj.getString("insuranceId")&&"0".equals(contactObj.getString("insuranceId"))){
			insuranceId =contactObj.getString("insuranceId");
		}
		
		//火车票行程参数
		JSONObject trainObj =JSONObject.fromObject(trainJson);
		System.out.println(trainJson);
		
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
		infoMap.put("insuranceId", insuranceId);
		
		Map<String, String> billMap =service.createOrder(vo ,infoMap,totalPrice);
		
		if("1".equals(billMap.get("errCode"))){
			String errMsg =billMap.get("errMsg");
			session.setAttribute("errMsg", errMsg);
			return "http://life.manjiwang.com/mLife/errInfo?errMsg="+errMsg;
		}else{
		
//			attr.addAttribute("partner_channel", "mjLife");
//			attr.addAttribute("ismobile", "1");
//			attr.addAttribute("session", sessionId);
//			attr.addAttribute("order_no", billMap.get("outerTid"));
//			attr.addAttribute("order_title", billMap.get("itemName"));
//			attr.addAttribute("order_money", billMap.get("actPrice"));
//			attr.addAttribute("return_url", billMap.get("backSynURL"));
//			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
//			
//			return "redirect:"+billMap.get("redirectURL");//支付页面
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
			return InitUrl.getUrl(billMap.get("redirectURL"),map);
			
		}
	}
	
}
