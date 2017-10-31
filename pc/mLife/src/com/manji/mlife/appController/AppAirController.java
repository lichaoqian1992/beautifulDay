package com.manji.mlife.appController;

import java.util.HashMap;
import java.util.Iterator;
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
import com.manji.mlife.controller.BaseController;
import com.manji.mlife.model.AirAppBean;
import com.manji.mlife.model.Items;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.manji.mlife.service.AirService;
import com.manji.mlife.service.CallbackService;
import com.manji.mlife.service.OrderService;
import com.manji.mlife.utils.InitUrl;
import com.qianmi.open.api.ApiException;

import com.qianmi.open.api.domain.elife.AirSeat;
import com.qianmi.open.api.domain.elife.Airline;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author gaochao 2016年7月7日下午2:22:31 AirController 订购飞机票
 */
@Controller
@RequestMapping("/app/air")

public class AppAirController extends BaseController {
	@Autowired
	private OrderService service;
	@Autowired
	private AirService airService;
	@Autowired
	private CallbackService callbackService;

	/**
	 * //查询航线列表
	 * 
	 * @author gaochao
	 * @param from
	 * @param to
	 * @param date
	 * @param request
	 * @return 2016年7月11日上午10:04:11 String
	 * @throws ApiException
	 *
	 */
	@RequestMapping(value = "/lineslist", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String airLineslist(String from, String to, String date,String start,String end) {
		// 查询航线列表
		String string = airService.getAirLineslist(from, to, date,start,end);

		return string;

	}
	@RequestMapping(value="/getAirport",method=RequestMethod.GET)
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
		return airInfo;
	}
	/***
	 * 
	 * 订单预定
	 * 
	 */
	@RequestMapping("/reserve")
	public String airReserve(String airlines, int airSeat, String date, HttpServletRequest request) {
		// 父级航班信息String -->Object
		JSONObject fromObject = JSONObject.fromObject(airlines);
		// JSONObject--->Airline
		Airline airs = (Airline) JSONObject.toBean(fromObject, Airline.class);// 将建json对象转换为Bean对象
		JSONArray jsonArray = fromObject.getJSONArray("airSeats");
		// 子级航班信息 航班舱位信息
		JSONObject object = (JSONObject) jsonArray.get(airSeat);
		AirSeat airSeat2 = (AirSeat) JSONObject.toBean(object, AirSeat.class);// 将建json对象转换为Bean对象
		// 建机费
		Double adultFuelTax = airs.getAdultFuelTax();
		// 成人燃油费
		Double adultAirportTax = airs.getAdultAirportTax();
		// 舱位票面价
		Integer parPrice = airSeat2.getParPrice();
		// 单张票价
		Double totlPrice = adultFuelTax + adultAirportTax + parPrice;
		request.setAttribute("airline", airs);
		request.setAttribute("airSeat", airSeat2);
		request.setAttribute("totlPrice", totlPrice);
		request.setAttribute("date", date);
		return "/air/airReserve";
	}

	/**
	 * qianmi.elife.air.order.create（飞机票订单生成）
	 * 
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
	 *             2016年7月11日上午10:03:42 String
	 *
	 */
	@RequestMapping(value = "/orderCreate", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String airOrderCreate(AirAppBean bean, HttpServletRequest request) throws ApiException {
		System.out.println(JSONObject.fromObject(bean));
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String sessionId = (String) session.getAttribute("sessionId");
		if(null ==userName||"".equals(userName)){
			
			return "http://life.manjiwang.com/mLife/errInfo?errMsg=请重新登录";
			
		}

		// （飞机票订单生成）

		Map<String, String> billMap = airService.getAirAppOrderCreate( bean, userName, session);

//		// 保存订单到页面展示
//		if (billMap.size() != 0) {
//			request.setAttribute("flightCompanyName", items.getFlightCompanyName());
//			request.setAttribute("seatMsg", items.getSeatMsg());
//			request.setAttribute("flightNo", items.getFlightNo());
//			session.setAttribute("billMap", billMap);
//			return "air/airOrderinfo";
//
//		}
//		request.setAttribute("Error", "飞机票预定失败，请查看航班余票或填写的信息");
//		return "create-bill-fail";
		
		if("1".equals(billMap.get("errCode"))){
			
			String errMsg =billMap.get("errMsg");
			
			return "http://life.manjiwang.com/mLife/errInfo?errMsg="+errMsg;
		}else{
			long timestamp = System.currentTimeMillis();
			
			Map<String,String> map =new HashMap<String,String>();
			map.put("order_cate", "OuterOrderPay");
			map.put("partner_channel", "mjLife");
			map.put("ismobile", "1");
			map.put("session", sessionId);
			map.put("order_no", billMap.get("outerTid"));
			map.put("order_title", billMap.get("itemName"));
			// attr.addAttribute("order_money", "1");
			map.put("order_money", billMap.get("totalPayCash"));
			map.put("return_url", billMap.get("backSynURL"));
			map.put("notify_url", billMap.get("backAsynURL"));
			map.put("timestamp", timestamp+"");
			map.put("sign", MD5.toSign(map));
			
			System.out.println(InitUrl.getUrl(billMap.get("redirectURL"),map));
//			return "redirect:" + billMap.get("redirectURL");// 支付页面
			return InitUrl.getUrl(billMap.get("redirectURL"),map);
			
		
			
			
		}
		
	}

	/***
	 * // qianmi.elife.air.order.pay（支付飞机票订单） //支付预定过的飞机票订单：
	 * //1.支付需在预定成功后半小时内完成，否则自动取消预定。
	 * //2.支付状态前提：预定完成（state-2），未支付（billstate-0）且未过期。
	 * 
	 * @author gaochao
	 * @param tradeNo
	 * @return
	 * @throws ApiException
	 *             2016年7月11日下午3:32:23 String
	 *
	 */
	@RequestMapping(value = "/orderPay", method = RequestMethod.GET)
	public String airOrderPay(HttpServletRequest request, RedirectAttributes attr) throws ApiException {

		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		String userName = (String) session.getAttribute("user_name");

		if(null ==userName||"".equals(userName)){
			
			return "redirect:/errInfo?errMsg=请重新登录";
			
		}

		Map<String, String> billMap = (Map<String, String>) session.getAttribute("billMap");

		String string = billMap.get("outerTid");
		System.out.println(billMap.get("itemName"));
		System.out.println(billMap.get("totalPayCash"));
		System.out.println(billMap.get("backSynURL"));
		System.out.println(billMap.get("backAsynURL"));
		System.out.println(billMap.get("redirectURL"));
		// 向满集支付平台付款
		// 重定向参数
		attr.addAttribute("order_cate", "OuterOrderPay");
		attr.addAttribute("partner_channel", "mjLife");
		attr.addAttribute("ismobile", "1");
		attr.addAttribute("session", sessionId);
		attr.addAttribute("order_no", string);
		attr.addAttribute("order_title", billMap.get("itemName").replace(" ", ""));
		// attr.addAttribute("order_money", "1");
		attr.addAttribute("order_money", billMap.get("totalPayCash"));
		attr.addAttribute("return_url", billMap.get("backSynURL"));
		attr.addAttribute("notify_url", billMap.get("backAsynURL"));

		return "redirect:" + billMap.get("redirectURL");// 支付页面
	}

	/**
	 * // qianmi.elife.air.order.cancel（取消飞机票订单）
	 * //交易中，取消整笔订单，状态前提：预定中（state-2），未支付（billstate-0）。
	 * 
	 * @author gaochao
	 * @param tradeNo
	 * @return
	 * @throws ApiException
	 *             2016年7月11日下午2:35:05 String
	 *
	 */
	@RequestMapping(value = "/orderCancel", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String airOrderCancel(String tradeNo, HttpServletRequest request) throws ApiException {
		// 取消订单+更改流水订单状态
		String s = airService.getAirOrderCancelRequest(tradeNo);

		return s;
	}

	/**
	 *
	 * //qianmi.elife.air.order.refund（退订飞机票订单） //tradeNo String 必须
	 * T150813161105440 订单主编号 //returnType String 必须 3 退票类型:1-退废票,3-退票
	 * //orderNos String 必须 "P141222161001880","P141222161001880" 订单子单编号集合
	 * 
	 * @author gaochao
	 * @param tradeNo
	 * @param returnType
	 * @param orderNos
	 * @return
	 * @throws ApiException
	 *             2016年7月11日下午4:00:00 String
	 *
	 */
	@RequestMapping(value = "/orderRefund", method = RequestMethod.GET)
	@ResponseBody
	public String airOrderRefund(String tradeNo, String returnType, String[] orderNos, HttpServletRequest request)
			throws ApiException {

		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		String userName = (String) session.getAttribute("user_name");

		if (null == userName || "".equals(userName)) {
			return "http://life.manjiwang.com/mLife/errInfo?errMsg=请重新登录";
		}
		String newOrderId = null;

		for (int i = 0; i < orderNos.length; i++) {

			newOrderId += orderNos[i] + ",";
		}
		if (newOrderId != null) {

			newOrderId = newOrderId.substring(0, newOrderId.length() - 1);
		}

		String billJson = null;
		// 退订飞机票订单
		String a = service.getairPayBack(tradeNo, newOrderId, returnType);// 返回的信息，包括成功的信息和失败的信息（具体失败原因）

		if (a.equals("1")) {
			// 退款成功;改变订单的状态为已退款
			// ************主表*******************
			Trade to = service.queryBytradeNo(tradeNo);
			if (callbackService.PartnerOrderBack(to.getOuterid())) {
				List<TrafficOrder> too = service.queryBychild(to.getTradeno());
				to.setState("5");
				service.updateStatus(to);
				if (too.size() > 0) {
					for (int i = 0; i < too.size(); i++) {
						TrafficOrder trafficOrder = too.get(i);
						too.get(i).setSeatname("已退款");
						service.updatechild(trafficOrder);
					}
					billJson = "1";
				}
				// ************从表*******************
			}
		} else {
			billJson = a;// 返回给用户的信息
		}
		return billJson;
	}

}
