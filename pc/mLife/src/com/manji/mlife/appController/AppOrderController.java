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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.OrderVo;
import com.manji.mlife.Vo.QueryVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.model.PageBean;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.manji.mlife.service.CallbackService;
import com.manji.mlife.service.OrderService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/order")
public class AppOrderController {
	
	@Autowired
	private OrderService service;
	@Autowired
	private  SimpleCodeMapper codeMapper;
	@Autowired
	private  CallbackService   backService;
	
	@RequestMapping(value = "/getChargeBills", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getChargeBills(QueryVo vo,int page,HttpServletRequest req){
		
		String userName =(String) req.getSession().getAttribute("userName");
		String billJson=null;
		
		vo.setUserName(userName);
//		vo.setUserName("a15123888888");
		if(page==0){
			billJson  = service.getChargeBillsShowDetail(vo);
			return billJson;
		}
		PageBean<Trade> pageBean = new PageBean<Trade>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 15;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		
		totalCount = service.findCountCid(vo);
		
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		
		//从哪儿结束
		limit=begin+limit;
		
		
		List<Trade> chargeBills = service.getChargeBills(vo,begin,limit);
		
		pageBean.setList(chargeBills);
		 
		billJson = pageBean.toString();
		 
		return billJson;
	}
	
	

	@RequestMapping(value = "/getTrafficBills", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getTrafficBills(QueryVo vo , int page){
		String startCity = null;
		String orderNumber = null;
		String starttime = null;
		String endtime = null;
		String goodsType = null;
		String orderStatus = null;
		String endCity = null;
		String rechargeAccount = null;
		//如果传过来的值是空字符串，那么把他们赋值成null
		if("".equals(vo.getOrderNumber())){
			vo.setOrderNumber(orderNumber);
		}
		if("".equals(vo.getStarttime())){
			vo.setStarttime(starttime);
		}
		if("".equals(vo.getEndtime())){
			vo.setEndtime(endtime);
		}
		if("".equals(vo.getGoodsType())){
			vo.setGoodsType(goodsType);
		}
		if("".equals(vo.getOrderStatus())){
			vo.setOrderStatus(orderStatus);
		}
		if("".equals(vo.getStartCity())){
			vo.setStartCity(startCity);
		}
		if("".equals(vo.getEndCity())){
			vo.setEndCity(endCity);
		}
		if("".equals(vo.getRechargeAccount())){
			vo.setRechargeAccount(rechargeAccount);
		}
		//处理传过来的出发城市和到达城市\
		if(vo.getStartCity() == null && null == vo.getEndCity()){
			startCity = "-";
		}else if(vo.getStartCity() == null){
			startCity = "-"+vo.getEndCity();
		}else if(null == vo.getEndCity()){
			startCity = vo.getStartCity()+"-";
		}else{
			startCity = vo.getStartCity()+"-"+vo.getEndCity();
		}
		//如果订单状态没有选择，那么就设置订单状态为空
//		if("".equals(vo.getOrderStatus())){
//			vo.setOrderStatus(orderStatus);
//		}
		vo.setStartCity(startCity);
//		vo.setUserName("a15123888888");
		//如果没有传一个默认的页码，那么查询所有的数据,默认传1
		if(page == 0){
			page = 1;
		}
		PageBean<Trade> pageBean = new PageBean<Trade>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 15;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		
		totalCount = service.findCountCid2(vo);//查询票务订单的总条数
		
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		
		//从哪儿结束
		limit=begin+limit;
		String billJson=service.getTrafficBills(vo , begin , limit,pageBean);
		return billJson;
	}
	
	@RequestMapping(value = "/getChargeDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getChargeDetail(String outerId){
		
		String billJson ="";
		
		billJson=service.getChargeDetail(outerId);
		
		return billJson;
	}
	
	@RequestMapping(value = "/getTrafficDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getTrafficDetail(String tradeNo){
		
		String trafficJson ="";
		
		trafficJson=service.getTrafficDetail(tradeNo);
		
		
		return trafficJson;
	}
	
	/**
	 * 根据订单的编号查询订单的详细信息
	 * @param orderNumber
	 * @return
	 */
	@RequestMapping(value="/getDetails",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getDetails(String orderNumber){
		String result = service.getbillDetails(orderNumber);
		return result;
	}
	
	/**
	 * 票务退款|tradeNo:主单据id，orderId:子单据Id
	 * @return
	 */
	@RequestMapping(value="/payBack",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String payBack(String tradeNo , String orderId,String returnType,String type){
	
		String[] s = orderId.split(",");
		String newOrderId="";
		for(int i=0;i<s.length;i++){
			if(s[i].length()>0){
				newOrderId += s[i]+",";
			}
		}
		newOrderId=newOrderId.substring(0, newOrderId.length()-1);
		String billJson = null;
		
		String a=null;
		
		switch (type) {
		
		case "0":
			//火车退票
			
			 a = service.gettrainPayBack(tradeNo,newOrderId);//返回的信息，包括成功的信息和失败的信息（具体失败原因）

			break;
		default:
			
			//飞机退票
			
			 a = service.getairPayBack(tradeNo , newOrderId,returnType);//返回的信息，包括成功的信息和失败的信息（具体失败原因）

			break;
			
		}
		
		//退钱给满集网账户
		if(a.equals("1")){
			//退款成功;改变订单的状态为已退款
			//************主表*******************
			Trade to = service.queryBytradeNo(tradeNo);
			boolean partnerOrderBack = backService.PartnerOrderBack(to.getOuterid());
			if(partnerOrderBack){
				List<TrafficOrder> too = service.queryBychild(to.getTradeno());
				to.setState("5");
				service.updateStatus(to);
				if(too.size()>0){
					for(int i=0;i<too.size();i++){
						TrafficOrder trafficOrder = too.get(i);
						too.get(i).setSeatname("已退款");
						service.updatechild(trafficOrder);
					}
					billJson = "1";
				}
				//************从表*******************
			}
		}else{
			billJson = a;//返回给用户的信息
		}
		return billJson;
	}
	/**
	 * 对于没有成功的充值订单实现继续付款（针对于充值订单）
	 * @return
	 */
	@RequestMapping(value="/continuePay")
	public String continuePay(OrderVo vo,HttpServletRequest request, RedirectAttributes attr) throws ApiException{
		System.out.println(JSONObject.fromObject(vo));
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String redirectURL =codeMapper.getValue("redirectURL");
		String backSynURL=codeMapper.getValue("backSynURL");
		String backAsynURL=codeMapper.getValue("backAsynURL");
		//重定向参数
		Map<String,String> map =new HashMap<String,String>();
		map.put("outerId", vo.getOrder_id());
		map.put("itemName", vo.getOrder_title());
		map.put("saleAmount", vo.getOrder_money());
		map.put("backSynURL", backSynURL);
		map.put("backAsynURL", backAsynURL);
		map.put("redirectURL", redirectURL);
		System.out.println(map.get("outerId"));
		System.out.println(map.get("itemName"));
		System.out.println(map.get("saleAmount"));
		System.out.println(map.get("backSynURL"));
		System.out.println(map.get("backAsynURL"));
		System.out.println(map.get("redirectURL"));
		long timestamp = System.currentTimeMillis();
		
		attr.addAttribute("order_cate", "OuterOrderPay");	
		attr.addAttribute("partner_channel", "mjLife");
		attr.addAttribute("ismobile", "0");
		attr.addAttribute("session", sessionId);
		attr.addAttribute("order_no", map.get("outerId"));
		attr.addAttribute("order_title", map.get("itemName").replace(" ", ""));
		attr.addAttribute("order_money", map.get("saleAmount"));
		attr.addAttribute("return_url", map.get("backSynURL"));
		attr.addAttribute("notify_url", map.get("backAsynURL"));
		attr.addAttribute("timestamp", timestamp);
		
		attr.addAttribute("sign", MD5.generateSign(attr));
		return "redirect:"+map.get("redirectURL");//支付页面;
	}
	

}
