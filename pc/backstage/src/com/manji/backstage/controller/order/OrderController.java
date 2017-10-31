package com.manji.backstage.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.order.OrderAdvert;
import com.manji.backstage.model.order.OrderBackInfo;
import com.manji.backstage.model.order.OrderBookInfo;
import com.manji.backstage.model.order.OrderBusiness;
import com.manji.backstage.model.order.OrderCallback;
import com.manji.backstage.model.order.OrderComment;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.model.order.OrderDistributioninfo;
import com.manji.backstage.model.order.OrderGoodInfo;
import com.manji.backstage.model.order.OrderGoods;
import com.manji.backstage.model.order.OrderOnlinePay;
import com.manji.backstage.model.order.OrderPartner;
import com.manji.backstage.model.order.OrderSettlement;
import com.manji.backstage.model.order.OrderSms;
import com.manji.backstage.model.order.OrderVirtualinfo;
import com.manji.backstage.model.order.Orders;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.order.OrderService;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.order.OrderAdvertVo;
import com.manji.backstage.vo.order.OrderBackInfoVo;
import com.manji.backstage.vo.order.OrderBookInfoVo;
import com.manji.backstage.vo.order.OrderBusinessVo;
import com.manji.backstage.vo.order.OrderCallbackVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;
import com.manji.backstage.vo.order.OrderCommentVo;
import com.manji.backstage.vo.order.OrderDistributioninfoVo;
import com.manji.backstage.vo.order.OrderGoodInfoVo;
import com.manji.backstage.vo.order.OrderGoodsVo;
import com.manji.backstage.vo.order.OrderOnlinePayVo;
import com.manji.backstage.vo.order.OrderPartnerVo;
import com.manji.backstage.vo.order.OrderSettlementVo;
import com.manji.backstage.vo.order.OrderSmsVo;
import com.manji.backstage.vo.order.OrderVirtualinfoVo;
import com.manji.backstage.vo.order.OrdersVo;


@Controller
public class OrderController extends BaseController{

	@Autowired
	private OrderService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("order");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}

	
	@RequestMapping("/selOrderGoods")
	public String selOrderGoods(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryOrderGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderGoods(HttpServletRequest req,OrderGoodsVo vo){
		Message msg =createMsg();
		
		Page<OrderGoods> page =service.queryOrderGoods(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderGoods")
	public String insOrderGoods(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addOrderGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderGoods(HttpServletRequest req,OrderGoods og){
		Message msg =createMsg();
		service.addOrderGoods(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderGoods")
	public String readOrderGoods(HttpServletRequest req,int id){
		OrderGoods og =service.getOrderGoods(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updOrderGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderGoods(HttpServletRequest req,OrderGoods og){
		Message msg =createMsg();
		OrderGoods o =service.getOrderGoods(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updOrderGoods(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderGoods")
	public String delOrderGoods(HttpServletRequest req,long id){
		
		OrderGoods o =service.getOrderGoods(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delOrderGoods(id)){
			
		}
		return "redirect:/selOrderGoods";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderBookInfo")
	public String selOrderBookInfo(HttpServletRequest req){
		
		return "order/order_bookinfo_list";
	}
	@RequestMapping(value ="/queryOrderBookInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderBookInfo(HttpServletRequest req,OrderBookInfoVo vo){
		Message msg =createMsg();
		
		Page<OrderBookInfo> page =service.queryOrderBookInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderBookInfo")
	public String insOrderBookInfo(HttpServletRequest req){
		
		return "order/order_bookinfo_add";
	}
	@RequestMapping(value ="/addOrderBookInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderBookInfo(HttpServletRequest req,OrderBookInfo OrderBookInfo){
		Message msg =createMsg();
		service.addOrderBookInfo(OrderBookInfo);
		msg.setStatus("0");
		
		
		saveLog(createLog(req),LogRemark.ADD,Json(OrderBookInfo),LogRemark.ADDORDERBOOKINFO);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderBookInfo")
	public String readOrderBookInfo(HttpServletRequest req,int id){
		OrderBookInfo obi =service.getOrderBookInfo(id);
		req.setAttribute("orderbookinfo", Json(obi));
		return "order/order_bookinfo_upd";
	}
	@RequestMapping(value ="/updOrderBookInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderBookInfo(HttpServletRequest req,OrderBookInfo OrderBookInfo){
		Message msg =createMsg();
		OrderBookInfo obi =service.getOrderBookInfo(OrderBookInfo.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(obi),LogRemark.UPDORDERBOOKINFO);
		if(service.updOrderBookInfo(OrderBookInfo)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderBookInfo")
	public String delOrderBookInfo(HttpServletRequest req,long id){
		
		OrderBookInfo obi =service.getOrderBookInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(obi),LogRemark.DELORDERBOOKINFO);
		if(service.delOrderBookInfo(id)){
			
		}
		return "redirect:/selOrderBookInfo";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderDistributioninfo")
	public String selOrderDistributioninfo(HttpServletRequest req){
		
		return "order/order_distributioninfo_list";
	}
	@RequestMapping(value ="/queryOrderDistributioninfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderDistributioninfo(HttpServletRequest req,OrderDistributioninfoVo vo){
		Message msg =createMsg();
		
		Page<OrderDistributioninfo> page =service.queryOrderDistributioninfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderDistributioninfo")
	public String insOrderDistributioninfo(HttpServletRequest req){
		
		return "order/order_distributioninfo_add";
	}
	@RequestMapping(value ="/addOrderDistributioninfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderDistributioninfo(HttpServletRequest req,OrderDistributioninfo od){
		Message msg =createMsg();
		service.addOrderDistributioninfo(od);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(od),LogRemark.ADDORDERDISTRIBUTION);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderDistributioninfo")
	public String readOrderDistributioninfo(HttpServletRequest req,int id){
		OrderDistributioninfo od =service.getOrderDistributioninfo(id);
		req.setAttribute("orderdistributioninfo", Json(od));
		return "order/order_distributioninfo_upd";
	}
	@RequestMapping(value ="/updOrderDistributioninfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderDistributioninfo(HttpServletRequest req,OrderDistributioninfo od){
		Message msg =createMsg();
		OrderDistributioninfo o =service.getOrderDistributioninfo(od.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERDISTRIBUTION);
		if(service.updOrderDistributioninfo(od)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderDistributioninfo")
	public String delOrderDistributioninfo(HttpServletRequest req,long id){
		
		OrderDistributioninfo o =service.getOrderDistributioninfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERDISTRIBUTION);
		if(service.delOrderDistributioninfo(id)){
			
		}
		return "redirect:/selOrderDistributioninfo";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderVirtualinfo")
	public String selOrderVirtualinfo(HttpServletRequest req){
		
		return "order/order_virtualinfo_list";
	}
	@RequestMapping(value ="/queryOrderVirtualinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderVirtualinfo(HttpServletRequest req,OrderVirtualinfoVo vo){
		Message msg =createMsg();
		
		Page<OrderVirtualinfo> page =service.queryOrderVirtualinfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderVirtualinfo")
	public String insOrderVirtualinfo(HttpServletRequest req){
		
		return "order/order_virtualinfo_add";
	}
	@RequestMapping(value ="/addOrderVirtualinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderVirtualinfo(HttpServletRequest req,OrderVirtualinfo ov){
		Message msg =createMsg();
		service.addOrderVirtualinfo(ov);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ov),LogRemark.ADDORDERVIRTUALINFO);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderVirtualinfo")
	public String readOrderVirtualinfo(HttpServletRequest req,int id){
		OrderVirtualinfo ov =service.getOrderVirtualinfo(id);
		req.setAttribute("ordervirtualinfo", Json(ov));
		return "order/order_virtualinfo_upd";
	}
	@RequestMapping(value ="/updOrderVirtualinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderVirtualinfo(HttpServletRequest req,OrderVirtualinfo ov){
		Message msg =createMsg();
		OrderVirtualinfo o =service.getOrderVirtualinfo(ov.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERVIRTUALINFO);
		if(service.updOrderVirtualinfo(ov)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderVirtualinfo")
	public String delOrderVirtualinfo(HttpServletRequest req,long id){
		OrderVirtualinfo o =service.getOrderVirtualinfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERVIRTUALINFO);
		if(service.delOrderVirtualinfo(id)){
			
		}
		return "redirect:/selOrderVirtualinfo";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderGoodInfo")
	public String selOrderGoodInfo(HttpServletRequest req){
		
		return "order/order_goodinfo_list";
	}
	@RequestMapping(value ="/queryOrderGoodInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderGoodInfo(HttpServletRequest req,OrderGoodInfoVo vo){
		Message msg =createMsg();
		
		Page<OrderGoodInfo> page =service.queryOrderGoodInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderGoodInfo")
	public String insOrderGoodInfo(HttpServletRequest req){
		
		return "order/order_goodinfo_add";
	}
	@RequestMapping(value ="/addOrderGoodInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderGoodInfo(HttpServletRequest req,OrderGoodInfo ogi){
		Message msg =createMsg();
		service.addOrderGoodInfo(ogi);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ogi),LogRemark.ADDORDERGOODINFO);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderGoodInfo")
	public String readOrderGoodInfo(HttpServletRequest req,int id){
		OrderGoodInfo ogi =service.getOrderGoodInfo(id);
		req.setAttribute("ordergoodinfo", Json(ogi));
		return "order/order_goodinfo_upd";
	}
	@RequestMapping(value ="/updOrderGoodInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderGoodInfo(HttpServletRequest req,OrderGoodInfo ogi){
		Message msg =createMsg();
		OrderGoodInfo o =service.getOrderGoodInfo(ogi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODINFO);
		if(service.updOrderGoodInfo(ogi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderGoodInfo")
	public String delOrderGoodInfo(HttpServletRequest req,long id){
		OrderGoodInfo o =service.getOrderGoodInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODINFO);
		if(service.delOrderGoodInfo(id)){
			
		}
		return "redirect:/selOrderGoodInfo";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderSms")
	public String selOrderSms(HttpServletRequest req){
		
		return "order/order_sms_list";
	}
	@RequestMapping(value ="/queryOrderSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderSms(HttpServletRequest req,OrderSmsVo vo){
		Message msg =createMsg();
		
		Page<OrderSms> page =service.queryOrderSms(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderSms")
	public String insOrderSms(HttpServletRequest req){
		
		return "order/order_sms_add";
	}
	@RequestMapping(value ="/addOrderSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderSms(HttpServletRequest req,OrderSms os){
		Message msg =createMsg();
		service.addOrderSms(os);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(os),LogRemark.ADDORDERSMS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderSms")
	public String readOrderSms(HttpServletRequest req,int id){
		OrderSms os =service.getOrderSms(id);
		req.setAttribute("ordersmsinfo", Json(os));
		return "order/order_sms_upd";
	}
	@RequestMapping(value ="/updOrderSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderSms(HttpServletRequest req,OrderSms os){
		Message msg =createMsg();
		OrderSms o =service.getOrderSms(os.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERSMS);
		if(service.updOrderSms(os)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderSms")
	public String delOrderSms(HttpServletRequest req,long id){
		
		OrderSms o =service.getOrderSms(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERSMS);
		if(service.delOrderSms(id)){
			
		}
		return "redirect:/selOrderSms";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderAdvert")
	public String selOrderAdvert(HttpServletRequest req){
		
		return "order/order_advert_list";
	}
	@RequestMapping(value ="/queryOrderAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderAdvert(HttpServletRequest req,OrderAdvertVo vo){
		Message msg =createMsg();
		
		Page<OrderAdvert> page =service.queryOrderAdvert(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderAdvert")
	public String insOrderAdvert(HttpServletRequest req){
		
		return "order/order_advert_add";
	}
	@RequestMapping(value ="/addOrderAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderAdvert(HttpServletRequest req,OrderAdvert oa){
		Message msg =createMsg();
		service.addOrderAdvert(oa);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oa),LogRemark.ADDORDERADVERT);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderAdvert")
	public String readOrderAdvert(HttpServletRequest req,int id){
		OrderAdvert oa =service.getOrderAdvert(id);
		req.setAttribute("orderqdvertinfo", Json(oa));
		return "order/order_advert_upd";
	}
	@RequestMapping(value ="/updOrderAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderAdvert(HttpServletRequest req,OrderAdvert oa){
		Message msg =createMsg();
		OrderAdvert o =service.getOrderAdvert(oa.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERADVERT);
		if(service.updOrderAdvert(oa)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderAdvert")
	public String delOrderAdvert(HttpServletRequest req,long id){
		OrderAdvert o =service.getOrderAdvert(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERADVERT);
		if(service.delOrderAdvert(id)){
			
		}
		return "redirect:/selOrderAdvert";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderBusiness")
	public String selOrderBusiness(HttpServletRequest req){
		
		return "order/order_business_list";
	}
	@RequestMapping(value ="/queryOrderBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderBusiness(HttpServletRequest req,OrderBusinessVo vo){
		Message msg =createMsg();
		
		Page<OrderBusiness> page =service.queryOrderBusiness(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderBusiness")
	public String insOrderBusiness(HttpServletRequest req){
		
		return "order/order_business_add";
	}
	@RequestMapping(value ="/addOrderBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderBusiness(HttpServletRequest req,OrderBusiness ob){
		Message msg =createMsg();
		service.addOrderBusiness(ob);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ob),LogRemark.ADDORDERBUSINESS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderBusiness")
	public String readOrderBusiness(HttpServletRequest req,int id){
		OrderBusiness ob =service.getOrderBusiness(id);
		req.setAttribute("orderbusinessinfo", Json(ob));
		return "order/order_business_upd";
	}
	@RequestMapping(value ="/updOrderBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderBusiness(HttpServletRequest req,OrderBusiness ob){
		Message msg =createMsg();
		OrderBusiness o =service.getOrderBusiness(ob.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERBUSINESS);
		if(service.updOrderBusiness(ob)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderBusiness")
	public String delOrderBusiness(HttpServletRequest req,long id){
		OrderBusiness o =service.getOrderBusiness(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERBUSINESS);
		if(service.delOrderBusiness(id)){
			
		}
		return "redirect:/selOrderBusiness";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderBackInfo")
	public String selOrderBackInfo(HttpServletRequest req){
		
		return "order/order_backinfo_list";
	}
	@RequestMapping(value ="/queryOrderBackInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderBackInfo(HttpServletRequest req,OrderBackInfoVo vo){
		Message msg =createMsg();
		
		Page<OrderBackInfo> page =service.queryOrderBackInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderBackInfo")
	public String insOrderBackInfo(HttpServletRequest req){
		
		return "order/order_backinfo_add";
	}
	@RequestMapping(value ="/addOrderBackInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderBackInfo(HttpServletRequest req,OrderBackInfo obi){
		Message msg =createMsg();
		service.addOrderBackInfo(obi);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(obi),LogRemark.ADDORDERBACKINFO);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderBackInfo")
	public String readOrderBackInfo(HttpServletRequest req,int id){
		OrderBackInfo obi =service.getOrderBackInfo(id);
		req.setAttribute("orderbackinfo", Json(obi));
		return "order/order_backinfo_upd";
	}
	@RequestMapping(value ="/updOrderBackInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderBackInfo(HttpServletRequest req,OrderBackInfo obi){
		Message msg =createMsg();
		OrderBackInfo o =service.getOrderBackInfo(obi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERBACKINFO);
		if(service.updOrderBackInfo(obi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderBackInfo")
	public String delOrderBackInfo(HttpServletRequest req,long id){
		OrderBackInfo o =service.getOrderBackInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERBACKINFO);
		
		if(service.delOrderBackInfo(id)){
			
		}
		return "redirect:/selOrderBackInfo";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrders")
	public String selOrders(HttpServletRequest req){
		
		return "order/orders_list";
	}
	@RequestMapping(value ="/queryOrders", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrders(HttpServletRequest req,OrdersVo vo){
		Message msg =createMsg();
		
		Page<Orders> page =service.queryOrders(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrders")
	public String insOrders(HttpServletRequest req){
		
		return "order/orders_add";
	}
	@RequestMapping(value ="/addOrders", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrders(HttpServletRequest req,Orders orders){
		Message msg =createMsg();
		service.addOrders(orders);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(orders),LogRemark.ADDORDERS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrders")
	public String readOrders(HttpServletRequest req,int id){
		Orders orders =service.getOrders(id);
		req.setAttribute("ordersinfo", Json(orders));
		return "order/orders_upd";
	}
	@RequestMapping(value ="/updOrders", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrders(HttpServletRequest req,Orders orders){
		Message msg =createMsg();
		Orders o =service.getOrders(orders.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERS);
		if(service.updOrders(orders)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrders")
	public String delOrders(HttpServletRequest req,long id){
		
		Orders o =service.getOrders(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERS);
		if(service.delOrders(id)){
			
		}
		return "redirect:/selOrders";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderCallback")
	public String selOrderCallback(HttpServletRequest req){
		
		return "order/order_callback_list";
	}
	@RequestMapping(value ="/queryOrderCallback", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderCallback(HttpServletRequest req,OrderCallbackVo vo){
		Message msg =createMsg();
		
		Page<OrderCallback> page =service.queryOrderCallback(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderCallback")
	public String insOrderCallback(HttpServletRequest req){
		
		return "order/order_callback_add";
	}
	@RequestMapping(value ="/addOrderCallback", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderCallback(HttpServletRequest req,OrderCallback oc){
		Message msg =createMsg();
		service.addOrderCallback(oc);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oc),LogRemark.ADDORDERCALLBACK);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderCallback")
	public String readOrderCallback(HttpServletRequest req,int id){
		OrderCallback oc =service.getOrderCallback(id);
		req.setAttribute("ordercallbackinfo", Json(oc));
		return "order/order_callback_upd";
	}
	@RequestMapping(value ="/updOrderCallback", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderCallback(HttpServletRequest req,OrderCallback oc){
		Message msg =createMsg();
		OrderCallback o =service.getOrderCallback(oc.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCALLBACK);
		if(service.updOrderCallback(oc)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderCallback")
	public String delOrderCallback(HttpServletRequest req,long id){
		
		OrderCallback oc =service.getOrderCallback(id);
		saveLog(createLog(req),LogRemark.DEL,Json(oc),LogRemark.DELORDERCALLBACK);
		if(service.delOrderCallback(id)){
			
		}
		return "redirect:/selOrderCallback";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderComment")
	public String selOrderComment(HttpServletRequest req){
		
		return "order/order_comment_list";
	}
	@RequestMapping(value ="/queryOrderComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderComment(HttpServletRequest req,OrderCommentVo vo){
		Message msg =createMsg();
		
		Page<OrderComment> page =service.queryOrderComment(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderComment")
	public String insOrderComment(HttpServletRequest req){
		
		return "order/order_comment_add";
	}
	@RequestMapping(value ="/addOrderComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderComment(HttpServletRequest req,OrderComment oc){
		Message msg =createMsg();
		service.addOrderComment(oc);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oc),LogRemark.ADDORDERCOMMENT);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderComment")
	public String readOrderComment(HttpServletRequest req,int id){
		OrderComment oc =service.getOrderComment(id);
		req.setAttribute("ordercommentinfo", Json(oc));
		return "order/order_comment_upd";
	}
	@RequestMapping(value ="/updOrderComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderComment(HttpServletRequest req,OrderComment oc){
		Message msg =createMsg();
		OrderComment o =service.getOrderComment(oc.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCOMMENT);
		if(service.updOrderComment(oc)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderComment")
	public String delOrderComment(HttpServletRequest req,int id){
		OrderComment o =service.getOrderComment(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERCOMMENT);
		if(service.delOrderComment(id)){
			
		}
		return "redirect:/selOrderComment";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderSettlement")
	public String selOrderSettlement(HttpServletRequest req){
		
		return "order/order_settlement_list";
	}
	@RequestMapping(value ="/queryOrderSettlement", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderSettlement(HttpServletRequest req,OrderSettlementVo vo){
		Message msg =createMsg();
		
		Page<OrderSettlement> page =service.queryOrderSettlement(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderSettlement")
	public String insOrderSettlement(HttpServletRequest req){
		
		return "order/order_settlement_add";
	}
	@RequestMapping(value ="/addOrderSettlement", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderSettlement(HttpServletRequest req,OrderSettlement os){
		Message msg =createMsg();
		service.addOrderSettlement(os);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(os),LogRemark.ADDORDERSETTLEMENT);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderSettlement")
	public String readOrderSettlement(HttpServletRequest req,int id){
		OrderSettlement os =service.getOrderSettlement(id);
		req.setAttribute("ordersettlementinfo", Json(os));
		return "order/order_settlement_upd";
	}
	@RequestMapping(value ="/updOrderSettlement", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderSettlement(HttpServletRequest req,OrderSettlement os){
		Message msg =createMsg();
		OrderSettlement o =service.getOrderSettlement(os.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERSETTLEMENT);
		if(service.updOrderSettlement(os)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderSettlement")
	public String delOrderSettlement(HttpServletRequest req,long id){
		OrderSettlement o =service.getOrderSettlement(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERSETTLEMENT);
		if(service.delOrderSettlement(id)){
			
		}
		return "redirect:/selOrderSettlement";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderOnlinePay")
	public String selOrderOnlinePay(HttpServletRequest req){
		
		return "order/order_onlinepay_list";
	}
	@RequestMapping(value ="/queryOrderOnlinePay", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderOnlinePay(HttpServletRequest req,OrderOnlinePayVo vo){
		Message msg =createMsg();
		
		Page<OrderOnlinePay> page =service.queryOrderOnlinePay(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderOnlinePay")
	public String insOrderOnlinePay(HttpServletRequest req){
		
		return "order/order_onlinepay_add";
	}
	@RequestMapping(value ="/addOrderOnlinePay", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderOnlinePay(HttpServletRequest req,OrderOnlinePay oop){
		Message msg =createMsg();
		service.addOrderOnlinePay(oop);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oop),LogRemark.ADDORDERONLINEPAY);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderOnlinePay")
	public String readOrderOnlinePay(HttpServletRequest req,int id){
		OrderOnlinePay oop =service.getOrderOnlinePay(id);
		req.setAttribute("orderonlinepayinfo", Json(oop));
		return "order/order_onlinepay_upd";
	}
	@RequestMapping(value ="/updOrderOnlinePay", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderOnlinePay(HttpServletRequest req,OrderOnlinePay oop){
		Message msg =createMsg();
		OrderOnlinePay o =service.getOrderOnlinePay(oop.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERONLINEPAY);
		if(service.updOrderOnlinePay(oop)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderOnlinePay")
	public String delOrderOnlinePay(HttpServletRequest req,int id){
		OrderOnlinePay o =service.getOrderOnlinePay(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERONLINEPAY);
		if(service.delOrderOnlinePay(id)){
			
		}
		return "redirect:/selOrderOnlinePay";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/selOrderPartner")
	public String selOrderPartner(HttpServletRequest req){
		
		return "order/order_partner_list";
	}
	@RequestMapping(value ="/queryOrderPartner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderPartner(HttpServletRequest req,OrderPartnerVo vo){
		Message msg =createMsg();
		
		Page<OrderPartner> page =service.queryOrderPartner(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderPartner")
	public String insOrderPartner(HttpServletRequest req){
		
		return "order/order_partner_add";
	}
	@RequestMapping(value ="/addOrderPartner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderPartner(HttpServletRequest req,OrderPartner op){
		Message msg =createMsg();
		service.addOrderPartner(op);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(op),LogRemark.ADDORDERPARTNER);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderPartner")
	public String readOrderPartner(HttpServletRequest req,int id){
		OrderPartner op =service.getOrderPartner(id);
		req.setAttribute("orderpartnerinfo", Json(op));
		return "order/order_partner_upd";
	}
	@RequestMapping(value ="/updOrderPartner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderPartner(HttpServletRequest req,OrderPartner op){
		Message msg =createMsg();
		OrderPartner o =service.getOrderPartner(op.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERPARTNER);
		if(service.updOrderPartner(op)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderPartner")
	public String delOrderPartner(HttpServletRequest req,int id){
		OrderPartner o =service.getOrderPartner(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERPARTNER);
		if(service.delOrderPartner(id)){
			
		}
		return "redirect:/selOrderPartner";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	@RequestMapping("/selOrderCommentFalse")
	public String selOrderCommentFalse(HttpServletRequest req){
		
		return "order/order_commentfalse_list";
	}
	@RequestMapping(value ="/queryOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderCommentFalse(HttpServletRequest req,OrderCommentFalseVo vo){
		Message msg =createMsg();
		
		Page<OrderCommentFalse> page =service.queryOrderCommentFalse(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderCommentFalse")
	public String insOrderCommentFalse(HttpServletRequest req){
		
		return "order/order_commentfalse_add";
	}
	@RequestMapping(value ="/addOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderCommentFalse(HttpServletRequest req,OrderCommentFalse ocf){
		Message msg =createMsg();
		service.addOrderCommentFalse(ocf);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ocf),LogRemark.ADDORDERCOMMENTFALSE);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderCommentFalse")
	public String readOrderCommentFalse(HttpServletRequest req,int id){
		OrderCommentFalse ocf =service.getOrderCommentFalse(id);
		req.setAttribute("ordercommentfalseinfo", Json(ocf));
		return "order/order_commentfalse_upd";
	}
	@RequestMapping(value ="/updOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderCommentFalse(HttpServletRequest req,OrderCommentFalse ocf){
		Message msg =createMsg();
		OrderCommentFalse o =service.getOrderCommentFalse(ocf.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCOMMENTFALSE);
		if(service.updOrderCommentFalse(ocf)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderCommentFalse")
	public String delOrderCommentFalse(HttpServletRequest req,int id){
		OrderCommentFalse o =service.getOrderCommentFalse(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERCOMMENTFALSE);
		if(service.delOrderCommentFalse(id)){
			
		}
		return "redirect:/selOrderCommentFalse";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	@RequestMapping("/selOrderCommentTem")
	public String selOrderCommentTem(HttpServletRequest req){
		
		return "order/order_commenttem_list";
	}
	@RequestMapping(value ="/queryOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderCommentTem(HttpServletRequest req,OrderCommentTemVo vo){
		Message msg =createMsg();
		
		Page<OrderCommentTem> page =service.queryOrderCommentTem(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderCommentTem")
	public String insOrderCommentTem(HttpServletRequest req){
		
		return "order/order_commenttem_add";
	}
	@RequestMapping(value ="/addOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderCommentTem(HttpServletRequest req,OrderCommentTem oct){
		Message msg =createMsg();
		service.addOrderCommentTem(oct);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oct),LogRemark.ADDORDERCOMMENTTEM);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderCommentTem")
	public String readOrderCommentTem(HttpServletRequest req,int id){
		OrderCommentTem oct =service.getOrderCommentTem(id);
		req.setAttribute("ordercommentteminfo", Json(oct));
		return "order/order_commenttem_upd";
	}
	@RequestMapping(value ="/updOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderCommentTem(HttpServletRequest req,OrderCommentTem oct){
		Message msg =createMsg();
		OrderCommentTem o =service.getOrderCommentTem(oct.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCOMMENTTEM);
		if(service.updOrderCommentTem(oct)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderCommentTem")
	public String delOrderCommentTem(HttpServletRequest req,int id){
		OrderCommentTem o =service.getOrderCommentTem(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERCOMMENTTEM);
		
		if(service.delOrderCommentTem(id)){
			
		}
		return "redirect:/selOrderCommentTem";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	

	
	
	
}
