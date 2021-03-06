package com.manji.sheet.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.manji.sheet.model.common.SheetResult;
import com.manji.sheet.service.BuyerPcService;
import com.manji.sheet.validator.pc.buyer.ParaValidator;

@Before(ParaValidator.class)
public class BuyerPcController extends Controller{
	
	private BuyerPcService service = new BuyerPcService();
	
	/*
	 * 客服系统 -建议
	 * */
	/**
	 * 我的工单
	 */
	public void mySheet(){
		
		//获取参数
		String userId = getPara("user_id");
		String pageNum = getPara("page_num");
		String type = getPara("sheet_type");
		String callBack = getPara("callback");
		//获取数据
		SheetResult result = service.mySheet(userId, pageNum, type);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
	}
	/**
	 * 新增建议工单
	 *
	 */
	public void addSheet(){
		
		//获取参数
		String userId = getPara("user_id");
		String code = getPara("code");
		String descr = getPara("descr");
		String pics = getPara("pics");
		String callBack = getPara("callback");

		SheetResult result = service.saveSheet(userId, code, descr, pics);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	
	/**
	 * 查询建议详情
	 */
	public void sheetDetail(){
		
		//获取参数
		String sheet_id = getPara("sheet_id");
		String callBack = getPara("callback");
		
		SheetResult result = service.findSheetDetail(sheet_id);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	
	
	/*
	 * 客服系统 -交易纠纷
	 * */
	/**
	 * 获取工单的类型
	 */
	public void findSheetType(){
		
		//获取参数
		String type = getPara("sheet_type");
		String callBack = getPara("callback");
		
		//获取工单的类型
		SheetResult result = service.findSheetType(type);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	} 
	/**
	 * 给工单补充资料
	 */
	public void addData(){
		
		//获取参数
		String sheet_id = getPara("sheet_id");
		String descr = getPara("descr");
		String pics = getPara("pics");
		String callBack = getPara("callback");
		
		//保存添加的资料
		SheetResult result = service.addData(sheet_id, descr, pics);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
	}
	/**
	 * 自行解决
	 */
	public void resovled(){
		
		String sheet_id = getPara("sheet_id");
		String reason = getPara("reason");
		String callBack = getPara("callback");
		
		SheetResult result = service.resovled(sheet_id, reason);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	/**
	 * 获取可申请的订单列表
	 */
	public void findOrderList(){
		
		String userId = getPara("user_id");
		String pageNum = getPara("page_num");
		String callBack = getPara("callback");
		
		SheetResult result = service.findOrderList(userId,pageNum);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
	}
	/**
	 * 获取已经申请的交易纠纷记录
	 */
	public void findSheet(){
		
		String userId = getPara("user_id");
		String status = getPara("status");
		String dateTime = getPara("date_time");//三个月以前：before,三个月以内：within
		String pageNum = getPara("page_num");
		String callBack = getPara("callback");
		
		SheetResult result = service.findSheet(userId,status,dateTime,pageNum);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	/**
	 * 获取订单信息
	 */
	public void findOrderInfo(){
		
		String order_id = getPara("order_id");
		String callBack = getPara("callback");
		
		SheetResult result = service.findOrderInfo(order_id);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	/**
	 * 新增交易纠纷
	 */
	public void addSheetDisputes(){
		
		String userId = getPara("user_id");
		String orderId = getPara("order_id");
		String code = getPara("code");
		String descr = getPara("descr");
		String pics = getPara("pics");
		String name = getPara("user_name");
		String mobile = getPara("mobile");
		String callBack = getPara("callback");
		
		SheetResult result = service.addSheetDisputes(userId,orderId,code,descr,pics,name,mobile);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	/*
	 * 客服系统 -举报
	 * */
	
	/**
	 * 我要举报列表
	 */
	public void reportList(){
		
		//获取参数
		Integer pageNumber = getParaToInt("pageNumber");
		Integer sponsor_id = getParaToInt("sponsor_id");
		String short_title = getPara("short_title");
		String callback = getPara("callback");
		
		SheetResult result = service.reportAll(pageNumber, sponsor_id,short_title);
		
		renderText(callback + "("+JFinalJson.getJson().toJson(result).toString()+")");
	}
	
	
	/**
	 * 举报详情
	 */
	public void reportDetail(){
		
		//获取参数
		String sheet_id = getPara("sheet_id");
		String callback = getPara("callback");
		
		SheetResult result = service.reportDetail(sheet_id);
		
		renderText(callback + "("+JFinalJson.getJson().toJson(result)+")");
	}
	
	/**
	 * 获取举报类型
	 */
	public void reportType(){
		
		//获取参数
		String short_title = getPara("short_title");
		String callback = getPara("callback");
		
		SheetResult result = service.reportType(short_title);
		
		renderText(callback + "("+JFinalJson.getJson().toJson(result)+")");
	}
	
	/**
	 * 新增举报工单
	 *
	 */
	public void addReport(){
		
		//获取参数
		String userId = getPara("user_id");
		Integer shop_id = getParaToInt("shop_id");
		Integer article_id = getParaToInt("article_id");
		String code = getPara("code");
		String descr = getPara("descr");
		String pics = getPara("pics");
		String callBack = getPara("callback");

		SheetResult result = service.saveReportSheet(userId, code, descr, pics,shop_id,article_id);
		
		renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
		
	}
	
	/**
	 * 获取举报商品信息
	 */
	public void reportGoods(){
		
		//获取参数
		String article_id = getPara("article_id");
		String callback = getPara("callback");
		
		SheetResult result = service.reportGoods(article_id);
		
		renderText(callback + "("+JFinalJson.getJson().toJson(result)+")");
	}
	
	/**
	 * 获取举报店铺信息
	 */
	public void reportShop(){
		
		//获取参数
		String shop_id = getPara("shop_id");
		String callback = getPara("callback");
		
		SheetResult result = service.reportShop(shop_id);
		
		renderText(callback + "("+JFinalJson.getJson().toJson(result)+")");
	}
}
