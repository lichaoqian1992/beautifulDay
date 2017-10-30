package com.manji.data.controller.service;


import java.io.File;
import java.util.List;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.service.AuditStatistics;
import com.manji.data.model.request.service.EnterPageVo;
import com.manji.data.model.request.service.GoodsPageVo;
import com.manji.data.model.request.service.OrderPageVo;
import com.manji.data.model.request.service.ShopPageVo;
import com.manji.data.model.request.service.StorePageVo;
import com.manji.data.repository.AuditStatisticsRespository;
import com.manji.data.repository.InputData;
import com.manji.data.service.ShopInfoCenterService;
import com.manji.data.utils.AuditInput;
import com.manji.data.utils.ExcelUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class ShopInfoCenterController extends Controller{
	
	private ShopInfoCenterService service = new ShopInfoCenterService();
	
	public void yyy(){
		
		renderText("Hello World");
	}
	
	public void selData(){
		
		render("index.html");
	}
	
	public void selEnter(){
		
		render("enter.html");
	}
	
	public void selGoods(){
		
		render("goods.html");
	}
	
	public void selStore(){
	
		render("store.html");
	}
	
	public void selCompany(){
		
		render("company.html");
	}
	
	public void selScope(){
		
		render("scope.html");
	}
	
	public void selCards(){
		
		render("cards.html");
	}
	
	public void selOrder(){
		
		render("order.html");
	}
	
	public void selAudit(){
		
		render("audit.html");
	}
	
	
	/**
	 * 获取商家信息(客户中心)
	 */
	public void getShopInfo(){
		
		ShopPageVo vo =getBean(ShopPageVo.class,"");
		
		Page<Record> page = service.getShopInfo(vo);
		
		setAttr("shopInfo",page);
		
		renderJson();
	}
	
	/**
	 * 获取入驻信息（客户中心）
	 */
	public void getEnterInfo(){
		
		EnterPageVo vo = getBean(EnterPageVo.class,"");
		
		//以下方法用于手动导入数据
		
//		InputData id= new InputData();
//		id.enterInput();
//		id.goodsInput();
//		id.companyInput();
//		id.scopeInput();
		 
		
		Page<Record> page = service.getEnterInfo(vo);
		
		setAttr("enterInfo",page);
		
		renderJson();
		
	}
	
	/**
	 * 商品审核
	 */
	public void getGoodsInfo(){
		
		GoodsPageVo vo = getBean(GoodsPageVo.class,"");
		
		Page<Record> page = service.getGoodsInfo(vo);
		List<Record> record= page.getList();
		
		for(Record xx:record){
			
			String class_list = xx.get("class_list");
			
			String title = "";
			if(class_list != null && !"".equals(class_list)){
				
				class_list = class_list.substring(1,class_list.length()-1);
				String [] classArr = class_list.split(",");
				for(String yy:classArr){
					
					String catTitle = service.getCategory(yy);
					title += catTitle + ">";
				}
				title = title.substring(0,title.length()-1);
			}
			
			xx.set("title", title);
		}
	
		setAttr("goodsInfo",page);
		
		renderJson();
	}
	
	/**
	 * 公司信息变更
	 */
	public void getCompanyInfo(){
		
		String name = getPara("name");
		String shop_name = getPara("shop_name");
		String store_name = getPara("store_name");
		String audit_name = getPara("audit_name");
		Integer pageNumber = getParaToInt("pageNumber");
		
		Page<Record> page = service.getCompanyInfo(name,shop_name,store_name,audit_name,pageNumber);
		
		setAttr("companyInfo",page);
		
		renderJson();
	}
	
	/**
	 * 店铺信息变更
	 */
	public void getStoreInfo(){
		
		StorePageVo vo = getBean(StorePageVo.class,"");
		
		Page<Record> page = service.getStoreInfo(vo);
		
		setAttr("storeInfo",page);
		
		renderJson();
		
	}
	
	/**
	 * 经营范围
	 */
	public void getScopeInfo(){
		
		String name = getPara("name");
		String audit_name = getPara("audit_name");
		Integer pageNumber = getParaToInt("pageNumber");
		
		Page<Record> page = service.getScopeInfo(name, audit_name, pageNumber);
		
		setAttr("scopeInfo",page);
		
		renderJson();
	}
	
	/**
	 * 证件信息
	 */
	public void getCardInfo(){
		
		String name = getPara("name");
		String title = getPara("card_type");
		String start_time = getPara("start_time");
		String end_time = getPara("end_time");
		Integer pageNumber = getParaToInt("pageNumber");
		
		Page<Record> page = service.getCardInfo(name,title,start_time,end_time,pageNumber);
		
		setAttr("cardInfo",page);
		
		renderJson();
	}
	
	/**
	 * 订单信息
	 */
	public void getOrderInfo(){
		
		OrderPageVo vo = getBean(OrderPageVo.class,"");
		
		Page<Record> page = service.getOrderInfo(vo);
		
		setAttr("orderInfo",page);
		
		renderJson();
	}
	
	/**
	 * 审核信息
	 */
	public void getAuditInfo(){
		
		AuditStatistics as = getBean(AuditStatistics.class,"");
		
		Page<Record> page = service.auditInfo(as);
		
		setAttr("auditInfo",page);
		
		renderJson();
	}
	
	/**
	 * 审核详情
	 */
	public void getStatistics(){
		
		String start_time = getPara("start_time");
		String end_time = getPara("end_time");
		String audit_name = getPara("examine_name");
		Integer type = getParaToInt("type");
		Integer pageNumber = getParaToInt("pageNumber");
		
		Page<Record> page = service.auditSta(start_time, end_time, audit_name, type, pageNumber);
		
		setAttr("auditInfo",page);
		
		renderJson();
	}
	
	
	/**
	 * 导出商品excel
	 */
	public void toExcel(){
		
		GoodsPageVo vo = getBean(GoodsPageVo.class,"");
		
		String article_title = getPara("article_title") == null ? "" : getPara("article_title");
		String name = getPara("name") == null ? "" : getPara("name");
		String title = getPara("title") == null ? "" : getPara("title");
		String user_name = getPara("user_name") == null ? "" : getPara("user_name");
		String input_name = getPara("input_name") == null ? "" : getPara("input_name");
		String audit_name = getPara("audit_name") == null ? "" : getPara("audit_name");
		String start_time = getPara("start_time") == null ? "" : getPara("start_time");
		String end_time = getPara("end_time") == null ? "" : getPara("end_time");
		vo.setArticle_title(article_title);
		vo.setTitle(title);
		vo.setName(name);
		vo.setUser_name(user_name);
		vo.setInput_name(input_name);
		vo.setAudit_name(audit_name);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		vo.setPageNumber(1);
		
		ExcelUtils e = new ExcelUtils();
		String path = e.goodsExcel(vo);
		
		renderFile(new File(path));
	}
	
	/**
	 * 导出订单excel
	 */
	public void toOrderExcel(){
		
		OrderPageVo vo = getBean(OrderPageVo.class,"");
		String shop_name = getPara("shop_name") == null ? "" : getPara("shop_name");
		String name = getPara("name") == null ? "" : getPara("name");
		String mobile = getPara("mobile") == null ? "" : getPara("mobile");
		String buyer_name = getPara("buyer_name") == null ? "" : getPara("buyer_name");
		String express_status = getPara("express_status") == null ? "" : getPara("express_status");
		String status = getPara("status") == null ? "" : getPara("status");
		String back_status = getPara("back_status") == null ? "" : getPara("back_status");
		String start_time = getPara("start_time") == null ? "" : getPara("start_time");
		String end_time = getPara("end_time") == null ? "" : getPara("end_time");
		String order_no = getPara("order_no") == null ? "" : getPara("order_no");
		vo.setShop_name(shop_name);
		vo.setExpress_status(express_status);
		vo.setStatus(status);
		vo.setBack_status(back_status);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		vo.setName(name);
		vo.setMobile(mobile);
		vo.setBuyer_name(buyer_name);
		vo.setOrder_no(order_no);
		
		ExcelUtils e = new ExcelUtils();
		String path = e.orderExcel(vo);
		
		renderFile(new File(path));
	}
	
	/**
	 * 导出审核信息
	 */
	public void toAuditExcel(){
		
		String start_time = getPara("start_time") == null ? "" : getPara("start_time");
		String end_time = getPara("end_time") == null ? "" : getPara("end_time");
		String examine_name = getPara("examine_name") == null ? "" : getPara("examine_name");
		Integer type = getParaToInt("type");
		
		ExcelUtils e = new ExcelUtils();
		String path = e.toAuditExcel(start_time, end_time, examine_name, type);
		
		renderFile(new File(path));
		
	}
	
	/**
	 * 根据parent_id查title
	 */
	public void getTitle(){
		
		Integer parent_id = getParaToInt("parent_id");
		
		List<Record> record = service.getTitle(parent_id);
		
		setAttr("getTitle",record);
		
		renderJson();
	}
	
}
