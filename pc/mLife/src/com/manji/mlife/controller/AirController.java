package com.manji.mlife.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.common.MD5;
import com.manji.mlife.model.Items;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.manji.mlife.service.AirService;
import com.manji.mlife.service.DBService;
import com.qianmi.open.api.ApiException;

import com.qianmi.open.api.domain.elife.AirSeat;
import com.qianmi.open.api.domain.elife.Airline;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * @author gaochao
 * 2016年7月7日下午2:22:31
 * AirController
 * 订购飞机票
 */
@Controller
@RequestMapping("/air")
public class AirController{
	 //平台公共service
	@Autowired	
    private DBService service;
	@Autowired
	private AirService airService;
	/**
	 * //查询航线列表
	 * @author gaochao
	 * @param from
	 * @param to
	 * @param date
	 * @param request
	 * @return
	 * 2016年7月11日上午10:04:11
	 * String
	 * @throws ApiException 
	 *
	 */
	@RequestMapping(value="/airLineslist" ,method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String airLineslist(String from, String to, String date , String start , String end)  
	{   
		//查询航线列表
		String string=airService.getAirLineslist(from,to,date,start,end);
		
		
		return string;
	
	}
	@RequestMapping(value="/getAirport",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String getAirport(String startCity , String endCity){
		String airInfo = "";
		String info = "";
		//输入的那个就查询那个
		if(startCity != null && startCity !=""){
			info = startCity;
		}
		if(endCity != null && endCity !=""){
			info = endCity;
		}
		airInfo = airService.getAirport(info);
		//把String转换成Json
		/*if(airInfo != null && airInfo.length()>0){
			
		}*/
		return airInfo;
	}
	/***
	 * 
	 * 订单预定
	 * 
	 */
	@RequestMapping("/airReserve")
	public String airReserve(String  airlines, int airSeat, String date,HttpServletRequest request){
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		//父级航班信息String -->Object
		JSONObject fromObject = JSONObject.fromObject(airlines);
		//JSONObject--->Airline
		Airline airs = (Airline)JSONObject.toBean(fromObject,Airline.class);//将建json对象转换为Bean对象
		JSONArray jsonArray = fromObject.getJSONArray("airSeats");
		//子级航班信息 航班舱位信息
		 JSONObject object = (JSONObject) jsonArray.get(airSeat);
		AirSeat airSeat2 = (AirSeat)JSONObject.toBean(object,AirSeat.class);//将建json对象转换为Bean对象
		//建机费
		Double adultFuelTax = airs.getAdultFuelTax();
		//成人燃油费
		Double adultAirportTax = airs.getAdultAirportTax();
		//舱位票面价
		Integer parPrice = airSeat2.getParPrice();
		//单张票价
		Double totlPrice=adultFuelTax+adultAirportTax+parPrice; 
		request.setAttribute("airline", airs);
		request.setAttribute("airSeat", airSeat2);
		request.setAttribute("totlPrice",totlPrice);
		request.setAttribute("date",date);
		return "/air/airReserve";
	}
	
	
	/**
	 * qianmi.elife.air.order.create（飞机票订单生成）
	 * @author gaochao
	 * @param seatCode
	 * @param passagers
	 * @param itemId
	 * @param contactName
	 * @param flightNo
	 * @param date
	 * @param from
	 * @param to
	 * @param companyCode
	 * @param contactTel
	 * @return
	 * @throws ApiException
	 * 2016年7月11日上午10:03:42
	 * String
	 *
	 */
	@RequestMapping(value="/airOrderCreate" ,method=RequestMethod.POST)
	public String airOrderCreate( Items items,HttpServletRequest request) throws ApiException{
		
		HttpSession session=request.getSession();
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
	
		//（飞机票订单生成）
		
		Map<String, String> billMap= airService.getAirOrderCreate(items,userName,session);
		
		//保存订单到页面展示
		if(billMap.size()!=0){
			request.setAttribute("flightCompanyName", items.getFlightCompanyName());
			request.setAttribute("seatMsg", items.getSeatMsg());
			request.setAttribute("flightNo", items.getFlightNo());
			session.setAttribute("billMap", billMap);
			return "air/airOrderinfo";

		}
		request.setAttribute("Error","飞机票预定失败，请查看航班余票或填写的信息");
		return "create-bill-fail";
	}
	
	/***
	// qianmi.elife.air.order.pay（支付飞机票订单）
	//支付预定过的飞机票订单：
	//1.支付需在预定成功后半小时内完成，否则自动取消预定。
	//2.支付状态前提：预定完成（state-2），未支付（billstate-0）且未过期。
	 * @author gaochao
	 * @param tradeNo
	 * @return
	 * @throws ApiException
	 * 2016年7月11日下午3:32:23
	 * String
	 *
	 */
	@RequestMapping(value="/airOrderPay" ,method=RequestMethod.GET)
	public String airOrderPay(HttpServletRequest request,RedirectAttributes attr) throws ApiException{
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		Map<String, String>  billMap = (Map<String, String>) session.getAttribute("billMap");
		
		String string = billMap.get("outerTid");
		System.out.println(billMap.get("itemName"));
		System.out.println(billMap.get("totalPayCash"));
		System.out.println(billMap.get("backSynURL"));
		System.out.println(billMap.get("backAsynURL"));
		System.out.println(billMap.get("redirectURL"));
		//向满集支付平台付款
		//重定向参数
		
		long timestamp = System.currentTimeMillis();
		
		attr.addAttribute("order_cate", "OuterOrderPay");	
		attr.addAttribute("partner_channel", "mjLife");
		attr.addAttribute("ismobile", "0");
		attr.addAttribute("session", sessionId);
		attr.addAttribute("order_no", string);
		attr.addAttribute("order_title", billMap.get("itemName"));
		//attr.addAttribute("order_money", "1");
		attr.addAttribute("order_money", billMap.get("totalPayCash"));
		attr.addAttribute("return_url", billMap.get("backSynURL"));
		attr.addAttribute("notify_url", billMap.get("backAsynURL"));
		attr.addAttribute("timestamp", timestamp);
		attr.addAttribute("sign", MD5.generateSign(attr));
		return "redirect:"+billMap.get("redirectURL");//支付页面
	}
	
	
	
	/**
	 * 	// qianmi.elife.air.order.cancel（取消飞机票订单）
	 *  //交易中，取消整笔订单，状态前提：预定中（state-2），未支付（billstate-0）。
	 * @author gaochao
	 * @param tradeNo
	 * @return
	 * @throws ApiException
	 * 2016年7月11日下午2:35:05
	 * String
	 *
	 */
	@RequestMapping(value="/airOrderCancel" ,method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String airOrderCancel(String tradeNo,HttpServletRequest request) throws ApiException{
		//取消订单+更改流水订单状态
		String s = airService.getAirOrderCancelRequest(tradeNo);
		
		return s;
	}	
	
	
	
	/**
	 *
    //qianmi.elife.air.order.refund（退订飞机票订单）
    //tradeNo	String 	必须 	T150813161105440		订单主编号
    //returnType	String 	必须 	3		退票类型:1-退废票,3-退票
    //orderNos	String 	必须 	"P141222161001880","P141222161001880"		订单子单编号集合
	 * @author gaochao
	 * @param tradeNo
	 * @param returnType
	 * @param orderNos
	 * @return
	 * @throws ApiException
	 * 2016年7月11日下午4:00:00
	 * String
	 *
	 */
/*	@RequestMapping(value="/airOrderRefund" ,method=RequestMethod.GET)
	@ResponseBody
	public String airOrderRefund(String tradeNo ,String returnType,String[] orderNos,HttpServletRequest request
		) throws ApiException{
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		//退订飞机票订单
		String state = airService.AirOrderRefundRequest(tradeNo,returnType,orderNos);
		//向满集申请退款
		if("1".equals(state)){
			//退款成功
			Trade trade = service.queryTradeByNoTradeNo(tradeNo);
			
			boolean partnerOrderBack = PartnerOrderBack(trade.getOuterid());
			
			if(partnerOrderBack){
				
				trade.setState("9");
				
				service.updateTradeSelective(trade);
				 
			    List<TrafficOrder> trafficOrders = service.queryTrafficBills(trade.getTradeno());
			    
			    for (int i = 0; i < trafficOrders.size(); i++) {
					  //批量更改飞机票订单状态
					  
					  TrafficOrder trafficOrder = trafficOrders.get(i);
					  
					  trafficOrder.setState("9");
					  trafficOrder.setSeatname("退款成功");
					  //upddate 修改票务
					  service.updateTrafficSelective(trafficOrder);
			     }
			}
		}
		return state;
   }*/
	
}
