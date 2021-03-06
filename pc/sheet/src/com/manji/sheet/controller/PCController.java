package com.manji.sheet.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.base.Message;
import com.manji.sheet.intercepter.PCIntercepter;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.service.BuyersAppService;
import com.manji.sheet.service.PCService;
import com.manji.sheet.utils.DatesUtils;
import com.manji.sheet.validator.pc.shop.CommentListValidator;
import com.manji.sheet.validator.pc.shop.CommentValidator;
import com.manji.sheet.validator.pc.shop.LogInfoValidator;
import com.manji.sheet.validator.pc.shop.MessageValidator;
import com.manji.sheet.validator.pc.shop.OrderValidator;
import com.manji.sheet.validator.pc.shop.ReportValidator;
import com.manji.sheet.validator.pc.shop.ReportedValidator;
import com.manji.sheet.validator.pc.shop.SheetNoValidator;
import com.manji.sheet.validator.pc.shop.UploadPisValidator;
import com.manji.sheet.validator.pc.shop.addInformationValidator;
import com.manji.sheet.validator.pc.shop.listValidator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

import org.apache.log4j.Logger;

//商家PC接口调用
@Before(PCIntercepter.class)
public class PCController extends Controller {
	
	private static Logger logger = Logger.getLogger(PCController.class);

	private static final String SUCCESS = "请求成功";
	private static final String PARA_ERROR = "参数错误";
	private static final String NO_DATA = "暂无数据";

	PCService service = new PCService();
	BuyersAppService appser = new BuyersAppService();

	public void index() {

		renderText("hahaha");
	}

	/**
	 * 商家PC详情页面(根据传入不同的short_title来显示不同页面)
	 */
	@Before(listValidator.class)

	public void getInformation() {
		
		Message msg = new Message();
		Account user = getSessionAttr("Account");
		String short_title = getPara("short_title");
		Integer shop_user_id = Integer.parseInt(user.getUser_id());
		Integer pageNum = getParaToInt("pageNum");
		if (pageNum == null) {
			pageNum = 1;
		}

		String start_time = getPara("start_time");
		String end_time = getPara("end_time");
		Integer status = getParaToInt("status");
		String sheet_no = getPara("sheet_no");
		String sessionId = getPara("sessionId");

		Page<Record> records = service.getInformation(pageNum, short_title, shop_user_id, start_time, end_time, status,
				sheet_no);
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(records);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("pageNum", pageNum);
		para.put("short_title", short_title);
		para.put("shop_user_id", shop_user_id);
		para.put("start_time", start_time);
		para.put("end_time", end_time);
		para.put("status", status);
		para.put("sessionId", sessionId);
		JSONObject obj = JSONObject.fromObject(para);
		
		String callback = getPara("callback");
		
		logger.info("调用接口 : getInformation ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 订单详情
	 */

	@Before(OrderValidator.class)
	public void getOrderInfo() {

		Message msg = new Message();

		String sheet_no = getPara("sheet_no");

		// 买家详情
		Map<String, Object> map = new HashMap<String, Object>();
		Record buyerInfo = service.buyerInfo(sheet_no);
		map.put("buyerInfo", buyerInfo);

		// 商品和运费信息 数据全为null
		Record orderAmount = service.orderAmount(sheet_no);
		map.put("orderAmount", orderAmount);

		// 商品规格和数量等信息
		List<Record> specInfo = service.specInfo(sheet_no);
		map.put("specInfo", specInfo);

		// 发货信息
		Record shipAddress = service.shipAddress(sheet_no);
		map.put("shipAddress", shipAddress);

		// 物流信息
		Record logisticsInfo = service.logisticsInfo(sheet_no);
		map.put("logisticsInfo", logisticsInfo);

		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : getOrderInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 退单详情
	 */

	@Before(OrderValidator.class)
	public void backInfo() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		String sheet_no = getPara("sheet_no");

		// 退单信息
		List<Record> backInfo = service.backInfo(sheet_no);
		map.put("backInfo", backInfo);

		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : backInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 处理流程和时间
	 */
	@Before(OrderValidator.class)
	public void dealTime() {

		Message msg = new Message();
		String sheet_no = getPara("sheet_no");
		String short_title = sheet_no.substring(0,3);
		List<Record> dealTime = service.dealTime(sheet_no);
		
		List<Object> time = new ArrayList<Object>();
		boolean flag = true;
		//增加建议的待受理时间
		for (int i=0;i<dealTime.size();i++) {
			
			if ( dealTime.size() == 1) {
				
				time.add(dealTime.get(0));	
				time.add(dealTime.get(0));	
			} else {
				
				if ("FEB".equals(short_title) && flag){
					
					time.add(dealTime.get(0));
					flag = false;
				};
				
				time.add(dealTime.get(i));
				
			}
		}
		
		//添加结束时间
		if ("已完成".equals(dealTime.get(dealTime.size()-1).get("result"))){
			
			time.add(dealTime.get(dealTime.size()-1));
		}
		if ("已解决".equals(dealTime.get(dealTime.size()-1).get("result"))){
			
			time.add(dealTime.get(dealTime.size()-1));
		}
		if (dealTime.size() > 0) {
			msg.setStatus("1");
			msg.setMessage(SUCCESS);
			msg.setResult(time);
		} else {
			msg.setStatus("2");
			msg.setMessage(NO_DATA);
		}

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : dealTime ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");

	}

	/**
	 * 我要建议详情页
	 */

	@Before(OrderValidator.class)
	public void suggestion() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		String sheet_no = getPara("sheet_no");
		Integer status = (int)service.getStatus(sheet_no);
		// 建议信息
		Record sugSheet = service.commonSheet(sheet_no);
		map.put("sugSheet", sugSheet);
		map.put("sheetStatus", status);
		// 详细描述
		List<Record> sugInfo = service.disputeInfo(sheet_no);
		map.put("sugInfo", sugInfo);
		// 仲裁结果
		
		if (status == 5) {
			
			Record sugResult = service.arbResult(sheet_no);
			map.put("sugResult", sugResult);
			
		} else {
			
			map.put("sugResult", null);
		}
		
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : suggestion ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 我要举报详情页
	 */

	@Before(ReportValidator.class)
	public void report() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();

		String sheet_no = getPara("sheet_no");
		Integer status = (int) service.getStatus(sheet_no);
		map.put("sheetStatus", status);
		// 举报的工单信息
		Record reportSheet = service.commonSheet(sheet_no);
		map.put("reportSheet", reportSheet);

		// 要举报的提交资料信息
		List<Record> reportInfo = service.disputeInfo(sheet_no);
		map.put("reportInfo", reportInfo);

		// 仲裁结果
		if (status == 5) {

			Record repResult = service.arbResult(sheet_no);
			map.put("repResult", repResult);

		} else {
			
			map.put("repResult", null);
		}
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : report ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 添加补充资料
	 */

	@Before(addInformationValidator.class)
	public void addInformation() {

		Message msg = new Message();
		Record record = new Record();
		Account user = getSessionAttr("Account");
		String descr = getPara("descr");
		String shop_name = service.getShopName(user.getUser_id());
		record.set("sheet_id", getParaToLong("sheet_id"));
		record.set("descr", descr);
		record.set("pics", getPara("pics"));
		record.set("add_time", DatesUtils.time());
		record.set("submitter", shop_name);
		record.set("submitter_id", user.getUser_id());
		record.set("submitter_type", "Shop");

		if (!"".equals(descr.trim())) {
			service.addInformation(record);
			msg.setStatus("1");
			msg.setMessage(SUCCESS);
		} else {
			msg.setStatus("3");
			msg.setMessage("请输入描述内容！");
		}
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_id", getParaToLong("sheet_id"));
		para.put("descr", descr);
		para.put("pics", getPara("pics"));
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : addInformation ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 被举报详细信息 
	 */

	@Before(ReportedValidator.class)
	public void reportedInfo() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();

		String sheet_no = getPara("sheet_no");
		Integer status = (int) service.getStatus(sheet_no);
		String short_title = sheet_no.substring(0, 3);

		// 被举报的工单信息
		Record reportedSheet = service.commonSheet(sheet_no);
		map.put("reportedSheet", reportedSheet);

		// 判断被举报内容
		if ("RGD".equals(short_title)) {

			// 获取举报的商品
			Record repObject = service.article(sheet_no);
			map.put("repObject", repObject);
			
			//订单号
			Record order_no = service.userShopInfo(sheet_no);
			String order_id = order_no == null ? null : order_no.get("id").toString();
			map.put("order_id", order_id);

		} else {
			
			map.put("repObject", null);
		}

		// 举报者提交资料信息
		List<Record> reportedInfo = service.disputeInfo(sheet_no);
		map.put("reportInfo", reportedInfo);
		map.put("sheetStatus", status);
		// 如果被举报的工单状态已完成
		if (status == 5) {

			// 被举报的处罚结果
			List<Record> punish = service.punish(sheet_no);
			map.put("punish", punish);

			// 被举报的仲裁结果
			Record arbResult = service.arbResult(sheet_no);
			map.put("arbResult", arbResult);
		} else {
			
			map.put("punish", null);
			map.put("arbResult", null);
		}

		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : reportedInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 商品详情页
	 */
	@Before(OrderValidator.class)
	public void shopDetail() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		String sheet_no = getPara("sheet_no");

		// 获取举报的商品
		Record repObject = service.article(sheet_no);

		// 获取分类(返回的class_layer数字代表层级)
		try {

			Integer category_id = repObject.get("category_id");
			String classList = service.getList(category_id);
			classList = classList.substring(1, classList.length() - 1);
			classList = "(" + classList + ")";
			List<Record> artCategory = service.articleCategory(classList);
			map.put("artCategory", artCategory);
			// 获取商品规格属性
			int article_id = repObject.get("id");
			List<Record> goodsSpec = service.goodsSpec(article_id);
			Double sell_price;
			Integer stock_quantity;
			String spec_text;
			String goods_no;
			Map<String, Object> specMap = new HashMap<>();
			Map<String, Object> map2 = new HashMap<>();
			List<Object> list = new ArrayList<>();

			for (int i = 0; i < goodsSpec.size(); i++) {

				spec_text = goodsSpec.get(i).get("spec_text").toString();
				String[] specArr = spec_text.split("，");
				for (int j = 0; j < specArr.length; j++) {
					String[] sb = specArr[j].split("：");
					for (int k = 0; k < sb.length; k++) {
						map2.put(sb[0], sb[1]);
						specMap.put("spec", map2);
					}
				}

				sell_price = Double.parseDouble(goodsSpec.get(i).get("sell_price").toString());
				stock_quantity = Integer.parseInt(goodsSpec.get(i).get("stock_quantity").toString());
				goods_no = goodsSpec.get(i).get("goods_no").toString();
				specMap.put("sell_price", sell_price);
				specMap.put("goods_no", goods_no);
				specMap.put("stock_quantity", stock_quantity);
				JSONObject specObj = JSONObject.fromObject(specMap);
				list.add(specObj);

			}
			// 商品的所有规格信息
			// 自定义分类
			map.put("shopSpec", list);
			if (service.category(article_id) != null) {
				String title = service.category(article_id);
				map.put("category", title);
			} else {
				map.put("category", "默认分类");
			}
			// 商品标题
			Record artTitle = service.article(sheet_no);
			map.put("shopTitle", artTitle.get("title"));
			// 商品卖点，品牌，属性
			Record shopGoods = service.shopGoods(article_id);
			shopGoods.remove("expenses_companyId");
			shopGoods.remove("goods_expenses");
			shopGoods.remove("expenses_tempId");
			map.put("shopGoods", shopGoods);
			// 获取物流信息
			Record tranInfo = service.tranInfo(article_id);
			JSONObject jsonObject = JSONObject.fromObject(tranInfo.get("goods_expenses"));
			int shop_id = service.userShopInfo(sheet_no).get("shop_user_id");
			String tranType = jsonObject.get("type").toString();

			if ("0".equals(tranType)) {
				
				Record logInfo = service.logisticsInfo(shop_id);
				map.put("logInfo", logInfo);
			} else if ("1".equals(tranType)) {
				
				Record shopZiSong = service.shopZiSong(shop_id);
				map.put("shopZiSong", shopZiSong);
				JSONArray jsonArray = JSONArray.fromObject(shopZiSong.get("area"));
				Integer cityCode = Integer.parseInt(shopZiSong.get("city").toString());
				String city = service.getCity(cityCode);
				Integer type = shopZiSong.get("type");
				String transType = "";
				switch (type) {
				case 0:
					transType = "自定义运费";
					break;
				case 1:
					transType = "包邮";
					break;
				default:
					transType = "";
				}
				Double free_price = Double.parseDouble(shopZiSong.get("free_price").toString());
				int area;
				String county;
				double price;
				String condition = "订单满" + free_price + "包邮,";
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = JSONObject.fromObject(jsonArray.get(i));
					area = Integer.parseInt(jsonObj.get("Area").toString());
					county = service.getCounty(area);
					price = Double.parseDouble(jsonObj.get("Price").toString());
					condition += county + price + "元,";
				}
				condition = condition.substring(0, condition.length() - 1);

				Map<String, String> map3 = new HashMap<String, String>();
				map3.put("type", transType);// 运输方式
				map3.put("city", city);// 运输范围
				map3.put("area", condition);// 运输条件
				map.put("shopTran", map3);// 返回的商家自送信息
			}
			msg.setStatus("1");
			msg.setMessage(SUCCESS);
			msg.setResult(map);

		} catch (NullPointerException e) {
			msg.setStatus("3");
			msg.setMessage(PARA_ERROR);
			e.printStackTrace();
		}

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : shopDetail ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 评价纠纷详情
	 */

	@Before(CommentListValidator.class)
	public void commentList() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();

		String sheet_no = getPara("sheet_no");
		Integer status = (int) service.getStatus(sheet_no);
		map.put("sheetStatus", status);
		// 纠纷的工单信息
		Record commentSheet = service.commonSheet(sheet_no);
		map.put("commentSheet", commentSheet);

		// 商家和用户信息和订单号
		Record userInfo = service.userInfo(sheet_no);
		map.put("userInfo", userInfo);
		Record shopInfo = service.shopInfo(sheet_no);
		map.put("shopInfo", shopInfo);
		Record order_no = service.userShopInfo(sheet_no);
		String orderNo = order_no == null ? null :order_no.get("order_no").toString();
		String orderId = order_no == null ? null :order_no.get("id").toString();
		map.put("orderNo", orderNo);
		map.put("orderId", orderId);
		
		//评价时间
		String add_time = service.getCommentTime(Integer.parseInt(order_no.get("id").toString()));
		map.put("commentTime", add_time);
		// 纠纷详情
		List<Record> commentInfo = service.disputeInfo(sheet_no);
		map.put("commentInfo", commentInfo);

		// 评判结果
		
		String punishResult = service.getPunish(sheet_no);
		String resultMsg;
		if (punishResult == null) {
			resultMsg = "维持原评价";
		} else {
			resultMsg = "屏蔽该评价";
		}
		map.put("punishResult", resultMsg);
		
		// 仲裁结果
		if (status == 2 || status == 5) {

			Record arb = service.arbResult(sheet_no);
			map.put("arb", arb);
		} else {
			
			map.put("arb", null);
		}
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : commentList ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 交易纠纷详情
	 */

	@Before(ReportValidator.class)
	public void tradeList() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();

		String sheet_no = getPara("sheet_no");
		Integer status = (int) service.getStatus(sheet_no);
		map.put("sheetStatus", status);
		// 交易纠纷工单信息  
		Record tradeSheet = service.commonSheet(sheet_no);
		map.put("tradeSheet", tradeSheet);

		// 交易纠纷内容
		List<Record> tradeInfo = service.disputeInfo(sheet_no);
		map.put("tradeInfo", tradeInfo);

		// 仲裁结果
		if (status == 5) {
			// 处罚结果执行
			List<Record> punishTrade = service.punish(sheet_no);
			map.put("punishTrade", punishTrade);
			// 处罚的商品
			Record articleTrade = service.article(sheet_no);
			map.put("articleTrade", articleTrade);
			// 仲裁结果
			Record arbTrade = service.arbResult(sheet_no);
			map.put("arbTrade", arbTrade);
		} else {
			
			map.put("punishTrade", null);
			map.put("articleTrade", null);
			map.put("arbTrade", null);
		}
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : tradeList ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 评价详情
	 */
	@Before(CommentValidator.class)
	public void commentDetail() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();

		Integer order_id = getParaToInt("order_id");

		// 用户信息和订单号
		Record orderInfo = service.ordersInfo(order_id);// 订单号，评价时间，买家昵称
		map.put("orderInfo", orderInfo);

		// 店铺评分
		Record shopCommentScore = service.shopCommentScore(order_id);
		map.put("shopCommentScore", shopCommentScore);

		// 商品评论
		List<Record> commentScore = service.comment(order_id.toString());
		map.put("commentScore", commentScore);

		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("order_id", order_id);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : commentDetail ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");

	}

	/**
	 * 发起举报
	 */

	@Before(UploadPisValidator.class)

	public void submmitReport() {
		
		Message msg = new Message();
		SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
		Account user = getSessionAttr("Account");
		Integer shop_user_id = Integer.parseInt(user.getUser_id());
		String shop_name = service.getShopName(user.getUser_id());
		String shopMobile = user.getMobile();
		String pics = getPara("pics");
		String descr = getPara("descr");
		sheetInfo.setSponsor_contact(shopMobile);
		sheetInfo.setReportUser(shop_name);
		sheetInfo.setReportUserId(shop_user_id.toString());
		sheetInfo.setPics(pics);
		String stu = service.saveShopReport(sheetInfo);

		if ("SUCCESS".equals(stu) && !"".equals(descr.trim())) {

			msg.setStatus("1");
			msg.setMessage(SUCCESS);
		} else if ("ERROR".equals(stu) && !"".equals(descr.trim())) {
			msg.setStatus("3");
			msg.setMessage("提交失败");
		} else {
			msg.setStatus("3");
			msg.setMessage("提交失败,请输入描述内容！");
		}

		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("pics", pics);
		para.put("descr", descr);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : submmitReport ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");

	}

	/**
	 * 发起建议
	 */

	@Before(UploadPisValidator.class)

	public void submmitSuggestion() {
		
		Message msg = new Message();
		SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
		Account user = getSessionAttr("Account");
		Integer shop_user_id = Integer.parseInt(user.getUser_id());
		String shopMobile = user.getMobile();
		String pics = getPara("pics");
		String descr = getPara("descr");
		String shop_name = service.getShopName(user.getUser_id());
		sheetInfo.setSponsor_contact(shopMobile);
		sheetInfo.setReportUser(shop_name);
		sheetInfo.setReportUserId(shop_user_id.toString());
		sheetInfo.setPics(pics);
		String stu = service.saveShopSuggestion(sheetInfo);

		if ("SUCCESS".equals(stu) && !"".equals(descr.trim())) {

			msg.setStatus("1");
			msg.setMessage(SUCCESS);
		} else if ("ERROR".equals(stu) && !"".equals(descr.trim())) {
			msg.setStatus("3");
			msg.setMessage("提交失败");
		} else {
			msg.setStatus("3");
			msg.setMessage("提交失败,请输入描述内容！");
		}
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("pics", pics);
		para.put("descr", descr);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : submmitSuggestion ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		
		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 根据short_title查询建议或举报类型
	 */

	@Before(listValidator.class)
	public void getSheetType() {

		String short_title = getPara("short_title");
		Map<String, Object> map = new HashMap<String, Object>();
		Message msg = new Message();

		List<Record> records = service.getSheetType(short_title);
		map.put("sheetType", records);
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("short_title", short_title);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : getSheetType ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

	/**
	 * 消息通知
	 */

	public void getMessageInfo() {

		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		Account user = getSessionAttr("Account");
		Integer pageNum = getParaToInt("pageNum");
		if (pageNum == null) {
			pageNum = 1;
		}
		Integer shop_user_id = Integer.parseInt(user.getUser_id());
		Page<Record> messageInfo = service.getMessageInfo(shop_user_id, pageNum);
		map.put("messageInfo", messageInfo);
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("pageNum", pageNum);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : getMessageInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");

	}

	/**
	 * 删除消息通知
	 */

	@Before(MessageValidator.class)
	public void delMessageInfo() {

		Message msg = new Message();
		Integer id = getParaToInt("id");

		if (service.delMessageInfo(id)) {
			msg.setStatus("1");
			msg.setMessage("删除成功");
		} else {
			msg.setStatus("3");
			msg.setMessage("删除失败");
		}
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("id", id);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : delMessageInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());

		String callback = getPara("callback");

		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");

	}
	
	/**
	 * 根据order_id获取工单状态和工单号
	 */
	
	@Before(SheetNoValidator.class)
	public void getSheetStatus(){
		
		Message msg = new Message();
		
		Integer order_id = getParaToInt("order_id");
		
		Record record = service.getSheetStatus(order_id);
		
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(record);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("order_id", order_id);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : getSheetStatus ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");
		
		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}
	
	/**
	 * 查看详细物流信息
	 */
	@Before(LogInfoValidator.class)
	public void getLogInfo(){
		
		Message msg = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String express_no = getPara("express_no");
		String sheet_no = getPara("sheet_no");
		
		//物流流程
		Record record = service.getLogInfo(express_no);
		map.put("logInfo",record);
		
		// 买家详情
		Record buyerInfo = service.buyerInfo(sheet_no);
		String shAddress = buyerInfo.getStr("address");
		map.put("shAddress", shAddress);

		// 商品规格和数量等信息
		List<Record> specInfo = service.specInfo(sheet_no);
		map.put("specInfo", specInfo);

		// 发货信息
		Record shipAddress = service.shipAddress(sheet_no);
		String telephone = shipAddress.getStr("telephone");
		String shopName = shipAddress.getStr("name");
		String fhAddress = shipAddress.getStr("address");
		map.put("telephone", telephone);
		map.put("shopName", shopName);
		map.put("fhAddress", fhAddress);

		// 物流信息
		Record logisticsInfo = service.logisticsInfo(sheet_no);
		String logistics_company = logisticsInfo.getStr("title");
		map.put("logistics_company", logistics_company);
		
		msg.setStatus("1");
		msg.setMessage(SUCCESS);
		msg.setResult(map);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("sessionId", getPara("sessionId"));
		para.put("express_no", express_no);
		para.put("sheet_no", sheet_no);
		JSONObject obj = JSONObject.fromObject(para);
		
		logger.info("调用接口 : getLogInfo ;"+"参数 : "+obj.toString()+"; 日志信息   ："+JFinalJson.getJson().toJson(msg).toString());
		
		String callback = getPara("callback");
		
		renderText(callback + "(" + JFinalJson.getJson().toJson(msg).toString() + ")");
	}

}
