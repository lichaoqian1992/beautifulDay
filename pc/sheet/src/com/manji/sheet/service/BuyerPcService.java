package com.manji.sheet.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.json.JFinalJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.controller.BuyerPcController;
import com.manji.sheet.model.bean.SheetInfo;
import com.manji.sheet.model.common.SheetResult;
import com.manji.sheet.model.enums.ResultEmuns;
import com.manji.sheet.model.enums.StatusEnums;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.utils.DatesUtils;
import com.manji.sheet.utils.WorkerNumberUtil;

public class BuyerPcService {

	private static final Logger log = Logger.getLogger(BuyerPcService.class);
	
	/*
	 * 客服系统 -建议
	 * */
	/**
	 * 查询我的建议
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult mySheet(String userId,String pageNum,String type){
		
		SheetResult result = new SheetResult();
		//判断参数
		if(userId == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			return result;
		}
		if(pageNum == null || pageNum.equals("")){
			result.setCode("403");
			result.setMessage("参数page_num不能为空！");
			return result;
		}
		if(type == null || type.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_type不能为空！");
			return result;
		}
		log.info("=====调用mySheet接口开始，调用开始时间："+new DatesUtils().time()+"参数值：userId="+userId+",pageNum="+pageNum+"=============");
		
		String sql = "from dt_sheets s inner join dt_sheet_type t on s.type_code like '05_%' and s.sponsor_id="+userId+" and s.sponsor_type=1 and s.type_code=t.code inner join dt_sheet_info i on s.id=i.sheet_id order by s.start_time desc"; 
		Page<Record> list = Db.paginate(Integer.parseInt(pageNum), 10, "select s.*,t.title,t.short_title,t.merge_title,i.descr,i.pics", sql);
		
		if(list != null && list.getList().size() > 0){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(list);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
		}
		log.info("=====调用mySheet接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
		
	}
	/**
	 * 保存工单信息
	 * @param userId
	 * @param code
	 * @param descr
	 * @param pics
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult saveSheet(String userId,String code,String descr,String pics){
		
		SheetResult result = new SheetResult();
		//校验参数
		if(userId == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			return result;
		}
		if(code== null || code.equals("")){
			result.setCode("403");
			result.setMessage("参数code不能为空！");
			
			return result;
		}
		if(descr  == null || descr.equals("")){
			result.setCode("403");
			result.setMessage("参数descr不能为空！");
			return result;
		}
		
		log.info("==========调用addSheet接口开始，开始时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		//处理参数
		SheetInfoParam param = new SheetInfoParam();
		param.setSponsor_id(userId);
		param.setReportTitle("FEB");
		param.setReportType(code);
		param.setDescr(descr);
		param.setPics(pics);
		
		String stu = saveMainSheet(param);
		//返回消息
		if(!stu.equals("")){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(stu);
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
			result.setResult("系统错误！");
		}
		log.info("=====调用addSheet接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
		
	}
	/**
	 * 保存工单信息
	 * @return
	 */
	public String saveMainSheet(SheetInfoParam param){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String stu="",status = "";
		String userId = param.getSponsor_id();//用户id
		String type = param.getReportTitle();//工单类型
		String code = param.getReportType();//二级工单类型code  01_02
		//存用户名称，根据用户id查询用户名称
		Record user = Db.findFirst("select * from dt_users where id=?",userId);
		String user_name = user.getStr("user_name");
		String mobile = user.getStr("mobile");
		String nickName = user.getStr("nick_name");
		//根据工单的类型确定工单的状态
		//举报商品：RGD,举报店铺：RSH,举报员工：RMJ,咨询：ADV,建议：FEB,交易纠纷：TRD,评价纠纷：EVD
		if(type.equals("RMJ") || type.equals("FEB")){
			status = "3";//处理中
		}else{
			status = "1";
		}
		/*工单主表*/
		Record sheet = new Record();
		sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(type));
		sheet.set("status",status);
		sheet.set("type_code",code);
		sheet.set("sponsor_id",userId);
		if(!type.equals("TRD")){
			sheet.set("sponsor",user_name);
		}else{
			sheet.set("sponsor",nickName);
		}
		
		sheet.set("sponsor_type","1");
		sheet.set("sponsor_contact",mobile);
		sheet.set("start_time",sdf.format(new Date()));
		sheet.set("source_code","01");//01表示用户PC
		sheet.set("priority_level","1");
		sheet.set("is_push","0");
		boolean s = Db.save("dt_sheets",sheet);
	
		if(s){
			
			stu = saveOther(sheet, param, user_name);
			
		}
		
		return stu;
	}
	/**
	 * 保存从表的数据
	 * @return
	 */
	public String saveOther(Record sheet , SheetInfoParam sheetInfo , String user_name){
		
		String stu = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/*保存工单资料表*/
		Record sheetData = new Record();
		sheetData.set("sheet_id",sheet.get("id"));
		sheetData.set("descr",sheetInfo.getDescr());
		sheetData.set("pics",sheetInfo.getPics());
		sheetData.set("add_time",sdf1.format(new Date()));
		sheetData.set("submitter",user_name);
		sheetData.set("submitter_id",sheetInfo.getSponsor_id());
		sheetData.set("submitter_type","Buyer");
		Db.save("dt_sheet_info",sheetData);

	/*工单业务关系表*/
		Record sheetRelation = new Record();
		
		sheetRelation.set("sheet_id",sheet.get("id"));
		sheetRelation.set("order_id",sheetInfo.getOrderId());
		sheetRelation.set("back_order_id",sheetInfo.getBackOrderId());
		sheetRelation.set("shop_user_id",sheetInfo.getShopId());
		sheetRelation.set("shop_id",sheetInfo.getShopInfo());
		sheetRelation.set("article_id",sheetInfo.getArticleId());
		Db.save("dt_sheet_business",sheetRelation);
	/*工单详情表    只有是举报员工和建议的时候才保存这张表*/
		if(sheetInfo.getReportTitle().equals("RMJ") || sheetInfo.getReportTitle().equals("FEB")){
			String exe_dept = "计划部";
			String exe_dept_id = "1";
			if(sheetInfo.getReportTitle().equals("RMJ")){
				exe_dept = "监察部";
				exe_dept_id = "2";
			}
			Record details = new Record();
			details.set("sheet_id",sheet.get("id"));
			details.set("exe_dept",exe_dept);
			details.set("exe_dept_id",exe_dept_id);
			details.set("sup_dept","监察部");
			details.set("sup_dept_id","2");
			Db.save("dt_sheet_detail",details);
		}
	/*工单流程记录*/
		String descr = "";
		String[] code = sheetInfo.getReportType().split("_");
		if(code[0].equals("01") || code[0].equals("02") || code[0].equals("03")){
			descr = "客户提交举报单";
		}else if(code[0].equals("04")){
			descr = "客户提交咨询单";
		}else if(code[0].equals("05")){
			descr = "客户提交建议单";
		}else if(code[0].equals("06")){
			descr = "客户提交纠纷单";
		}
		Record flowLog = new Record();
		flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf.format(new Date())).set("opr_type", 1).set("result", "待受理").set("descr", descr);
		Db.save("dt_sheet_flowlog",flowLog);
		if(sheetInfo.getReportTitle().equals("RMJ") || sheetInfo.getReportTitle().equals("FEB")){
			Record flowLog2 = new Record();
			flowLog2.set("sheet_id", sheet.get("id")).set("opr_time", sdf.format(new Date())).set("opr_type", 3).set("result", "处理中").set("descr", "平台已受理");
			Db.update("update dt_sheet_flowlog set is_see=1 where is_see=0 and opr_type=1 and sheet_id=?",sheet.get("id").toString());
			Db.save("dt_sheet_flowlog",flowLog2);
		}
		
	
		stu = sheet.get("id").toString();
		return stu;
	}
	/**
	 * 查询工单的详情
	 * @param sheet_id
	 * @return
	 */
	@SuppressWarnings({ "static-access" })
	public SheetResult findSheetDetail(String sheet_id){
		
		SheetResult result = new SheetResult();
		
		if(sheet_id == null || sheet_id.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_id不能为空！");
			return result;
		}
		log.info("=======调用sheettDetail接口开始，调用时间："+new DatesUtils().time()+"参数值：sheet_id="+sheet_id+"============");
		
		//1.查询工单信息
		StringBuffer sql = new StringBuffer();
		sql.append("select s.*,t.code,t.title,t.short_title,t.merge_title from dt_sheets s inner join ")
			.append(" dt_sheet_type t on s.type_code=t.code and s.id="+sheet_id);
		//2.查询工单信息
		Record sheet = Db.findFirst(sql.toString());
		//3.查询工单仲裁结果
		Record details = Db.findFirst("select d.content,d.pics as dpics from dt_sheet_detail d where sheet_id="+sheet_id);
		//4.查询流程信息
		List<Record> flow = Db.find("select * from dt_sheet_flowlog where sheet_id="+sheet_id);
		//5.查询一下资料信息
		List<Record> info = Db.find("select * from dt_sheet_info where sheet_id="+sheet_id);
		//6.查询订单信息
		Record orderInfo = Db.findFirst("select s.id as shopId,s.name,o.id,o.order_no,o.user_id,o.shop_user_id,o.order_no,o.add_time,o.payment_status,o.payable_amount,o.order_amount,o.real_amount,o.status,o.express_fee from dt_orders o inner join dt_user_role_shopinfo s on o.shop_user_id=s.user_id and o.id=(select order_id from dt_sheet_business where sheet_id="+sheet_id+")");
		
		sheet.set("flow", flow).set("details", details).set("info", info).set("orderInfo", orderInfo);
		
		result.setCode("200");
		result.setMessage(ResultEmuns.getMessageByCode("200"));
		result.setResult(sheet);
		
		log.info("=======调用sheettDetail接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"============");
		
		return result;
	}
	
	/*
	 * 客服系统 -交易纠纷
	 * */
	/**
	 * 查询举报、建议、交易纠纷的类型
	 * @param sheetType
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult findSheetType(String type){
		
		SheetResult result = new SheetResult();
		//判断参数是否为空
		if(type   == null || type.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_type不能为空！");
			return result;
		}
		
		log.info("==========调用findSheetType接口开始，开始时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		List<Record> list = Db.find("select * from dt_sheet_type where short_title='"+type+"' and layer!=1 and status =1");
		 
		if(list != null && list.size() > 0){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(list);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult(list);
		}
		log.info("=======调用findSheetType接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"============");
		
		return result;
	}
	/**
	 * 添加资料
	 * @param user_id
	 * @param sheet_id
	 * @param descr
	 * @param pics
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult addData(String sheet_id,String descr,String pics){
		
		SheetResult result = new SheetResult();
		//校验参数
		if(sheet_id   == null || sheet_id.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_id不能为空！");
			
			return result; 
		}
		if(descr   == null || descr.equals("")){
			result.setCode("403");
			result.setMessage("参数descr不能为空！");
			
			return result;
		}
				
		log.info("==========调用addData接口开始，开始时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		//根据sheet_id 查询最初的资料信息
		Record info = Db.findFirst("select * from dt_sheet_info where sheet_id="+sheet_id);
		
		Record data = new Record().set("sheet_id", sheet_id).set("descr", descr).set("pics", pics)
									.set("add_time", new DatesUtils().time()).set("submitter", info.get("submitter"))
									.set("submitter_id", info.get("submitter_id")).set("submitter_type", info.get("submitter_type"));
		boolean flag = Db.save("dt_sheet_info", data);
		
		if(flag){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult("SUCCESS");
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
			result.setResult("ERROR");
		}
		
		log.info("==========调用addData接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		return result;
	}
	/**
	 * 自行解决
	 * @param userId
	 * @param sheet_id
	 * @param reason
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult resovled(String sheet_id,String reason){
		
		SheetResult result = new SheetResult();
		
		//校验参数
		if(sheet_id   == null || sheet_id.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_id不能为空！");
			
			return result; 
		}
		if(reason   == null || reason.equals("")){
			result.setCode("403");
			result.setMessage("参数reason不能为空！");
			
			return result; 
		}
		
		log.info("==========调用resovled接口开始，开始时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		//1.修改出发表里面的数据
		int a = 0,c=0;
		//查询是否处罚过了
		Record details = Db.findFirst("select * from dt_sheet_detail where sheet_id=?",sheet_id);
		
		if(details != null){
			//说明此时该工单已经受理了,故修改详情表中的数据
			int b = Db.update("update dt_sheet_detail set content=? where sheet_id=?",reason,sheet_id);
			a = Db.update("update dt_sheets set status=6 where id=? and status != 6",sheet_id);
		}else{
			//未受理
			a = Db.update("update dt_sheets set status=6,is_push=1 where id=? and status != 6",sheet_id);
		}
		//商家、用户自己解决问题了
		Record flow = new Record().set("sheet_id", sheet_id).set("opr_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
								.set("opr_type", "7").set("result", StatusEnums.getMessageByCode("7")).set("descr", reason);
		boolean flag = Db.save("dt_sheet_flowlog", flow);
		if(a > 0 && flag){
			c = Db.update("update dt_sheet_flowlog set is_see=1 where sheet_id=?",sheet_id);
		}
		if(c > 0){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult("SUCCESS");
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
			result.setResult("ERROR");
		}
		log.info("==========调用resovled接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		return result;
		
	}
	/**
	 * 获取可申请的订单列表
	 * @param userId
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult findOrderList(String userId, String pageNum) {
		
		SheetResult result = new SheetResult();
		
		//校验参数
		if(userId   == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			
			return result;
		}
		if(pageNum   == null || pageNum.equals("")){
			result.setCode("403");
			result.setMessage("参数page_num不能为空！");
			
			return result; 
		}
		
		log.info("==========调用findOrderList接口开始，开始时间："+new DatesUtils().time()+"请求参数：userId="+userId+"pageNum="+pageNum+"==============");
		
		//查询可以申请交易纠纷的订单列表
		//1.查询订单相关信息
		Page<Record> order = Db.paginate(Integer.parseInt(pageNum), 10, "select s.name,o.id,o.order_no,o.add_time,o.payment_status,o.payable_amount,o.order_amount,o.status,o.express_fee", "from dt_orders o inner join dt_user_role_shopinfo s on o.shop_user_id=s.user_id and o.payment_status!=1 and o.user_id="+userId+" order by o.add_time desc");
		//2.查询商品相关信息
		for(int i=0;i<order.getList().size();i++){
			String order_id = order.getList().get(i).get("id").toString();
			List<Record> goods = Db.find("select article_id,order_id,goods_id,goods_title,img_url,spec_text,goods_price,real_price,quantity from dt_order_goods where order_id="+order_id);
			Record goodInfo = Db.findFirst("select * from dt_order_goodinfo where order_id="+order_id);
			String type = "",accept_name = "";
			if(goodInfo != null){
				if(order.getList().get(i).get("status").toString().equals("1")){//生成订单
					if(order.getList().get(i).get("payment_status").toString().equals("2")){//已支付
						if(goodInfo.get("express_status").toString().equals("2")){//已发货
							type= "待收货";
						}
					}
				}else if(order.getList().get(i).get("status").toString().equals("2")){//确认订单
					if(order.getList().get(i).get("payment_status").toString().equals("2")){//已支付
						if(goodInfo.get("express_status").toString().equals("2")){//已发货
							type= "待收货";
						}
					}
				}else if(order.getList().get(i).get("status").toString().equals("3")){//完成订单
					if(order.getList().get(i).get("payment_status").toString().equals("2")){//已支付
						type= "交易完成";
					}
				}
				accept_name = goodInfo.get("accept_name");
			}
			//判断商品是不是赠品
			for(int j=0;j<goods.size();j++){
				Record gift = Db.findFirst(" select * from  dt_activity_specify where gift_from=1 and article_id=? and from_id=?",goods.get(j).get("article_id"),goods.get(j).get("goods_id"));
				if(gift != null){
					goods.get(j).set("goods_type", "赠品");
				}else{
					goods.get(j).set("goods_type", "商品");
				}
			}
			order.getList().get(i).set("goods", goods).set("accept_name", accept_name).set("type",type);
		}
		
		if(order != null && order.getList().size() > 0){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(order);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("ERROR");
		}
		log.info("==========调用findOrderList接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		return result;
	}
	/**
	 * 获取订单信息
	 * @param order_id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult findOrderInfo(String order_id){
		
		SheetResult result = new SheetResult();
		
		//校验参数
		if(order_id   == null || order_id.equals("")){
			result.setCode("403");
			result.setMessage("参数order_id不能为空！");
			
			return result;
		}
		log.info("==========调用findOrderList接口开始，开始时间："+new DatesUtils().time()+"请求参数：order_id="+order_id+"==============");
		//订单信息
		Record orderInfo = Db.findFirst("select s.name,o.id,o.order_no,o.add_time,o.payment_status,o.payable_amount,o.order_amount,o.real_amount,o.status,o.express_fee from dt_orders o inner join dt_user_role_shopinfo s on o.shop_user_id=s.user_id and o.id="+order_id);
		//商品信息
		List<Record> goods = Db.find("select order_id,goods_id,goods_title,img_url,spec_text,goods_price,real_price,quantity from dt_order_goods where order_id="+order_id);
		
		orderInfo.set("goods", goods);
		
		result.setCode("200");
		result.setMessage(ResultEmuns.getMessageByCode("200"));
		result.setResult(orderInfo);
		
		log.info("==========调用findOrderList接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		return result;
		
	}
	/**
	 * 查询已申请的交易纠纷订单
	 * @param userId
	 * @param status
	 * @param dateTime
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult findSheet(String userId, String status, String dateTime, String pageNum) {
		
		SheetResult result = new SheetResult();
		
		//校验参数
		if(userId   == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			
			return result;
		}
		if(status   == null || status.equals("")){
			result.setCode("403");
			result.setMessage("参数status不能为空！");
			
			return result;
		}
		if(dateTime   == null || dateTime.equals("")){
			result.setCode("403");
			result.setMessage("参数date_time不能为空！");
			
			return result;
		}
		if(pageNum   == null || pageNum.equals("")){
			result.setCode("403");
			result.setMessage("参数page_num不能为空！");
			
			return result;
		}
		
		log.info("==========调用findSheet接口开始，开始时间："+new DatesUtils().time()+"请求参数：orderId="+userId+"status="+status+"dateTime="+dateTime+"pageNum="+pageNum+"============");
		Page<Record> sheet = null;
		//查询数据 
		if(dateTime.equals("within")){
			//三个月以内
			sheet = Db.paginate(Integer.parseInt(pageNum), 10, "select s.*,t.title,t.short_title,t.merge_title ", "from dt_sheets s inner join dt_sheet_type t on s.type_code=t.code and t.status=1 and s.start_time>=(Select DateAdd(Month,-3,getdate()) as three_time) and s.status="+status+" and s.sponsor_id="+userId+" and s.type_code like '06_%' order by s.start_time desc");
			
		}else if(dateTime.equals("before")){
			//三个月以前
			sheet = Db.paginate(Integer.parseInt(pageNum), 10, "select s.*,t.title,t.short_title,t.merge_title ", "from dt_sheets s inner join dt_sheet_type t on s.type_code=t.code and t.status=1 and s.start_time<(Select DateAdd(Month,-3,getdate()) as three_time) and s.status="+status+" and s.sponsor_id="+userId+"  and s.type_code like '06_%' order by s.start_time desc");
		}
		
		for(int i=0;i<sheet.getList().size();i++){
			int id = Integer.parseInt(sheet.getList().get(i).get("id").toString());
			//获取、店铺信息和商品信息
			//订单信息
			Record orderInfo = Db.findFirst("select s.name,o.id,o.order_no,o.add_time,o.payment_status,o.payable_amount,o.order_amount,o.real_amount,o.status,o.express_fee from dt_orders o inner join dt_user_role_shopinfo s on o.shop_user_id=s.user_id and o.id=(select order_id from dt_sheet_business where sheet_id=?)",id);
			//商品信息
			List<Record> goods = Db.find("select article_id,order_id,goods_id,goods_title,img_url,spec_text,goods_price,real_price,quantity from dt_order_goods where order_id=(select order_id from dt_sheet_business where sheet_id=?)",id);
			//判断商品是不是赠品
			for(int j=0;j<goods.size();j++){
				Record gift = Db.findFirst(" select * from  dt_activity_specify where gift_from=1 and article_id=? and from_id=?",goods.get(j).get("article_id"),goods.get(j).get("goods_id"));
				if(gift != null){
					goods.get(j).set("goods_type", "赠品");
				}else{
					goods.get(j).set("goods_type", "商品");
				}
			}
			Record goodInfo = Db.findFirst("select * from dt_order_goodinfo where order_id=(select order_id from dt_sheet_business where sheet_id=?)",id);
			
			sheet.getList().get(i).set("orderInfo", orderInfo).set("goods", goods).set("accept_name", goodInfo.get("accept_name"));
			
		}
		
		if(sheet != null && sheet.getList().size()>0){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(sheet);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("暂无数据");
		}
		log.info("==========调用findSheet接口结束，结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"===============");
		
		return result;
	}
	/**
	 * 新增交易纠纷
	 * @param userId
	 * @param orderId
	 * @param goodsId
	 * @param code
	 * @param descr
	 * @param name
	 * @param mobile
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult addSheetDisputes(String userId, String orderId, String code, String descr, String pics,
			String name, String mobile) {
		
		SheetResult result = new SheetResult();
		
		//校验参数
		if(userId   == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			
			return result;
		}
		if(orderId == null || orderId.equals("")){
			result.setCode("403");
			result.setMessage("参数order_id不能为空！");
			
			return result;
		}
		if(code   == null || code.equals("")){
			result.setCode("403");
			result.setMessage("参数code不能为空！");
			
			return result;
		}
		if(descr   == null || descr.equals("")){
			result.setCode("403");
			result.setMessage("参数descr不能为空！");
			
			return result;
		}
		if(name == null || name.equals("")){
			result.setCode("403");
			result.setMessage("参数user_name不能为空！");
			
			return result;
		}
		if(mobile == null || mobile.equals("")){
			result.setCode("403");
			result.setMessage("参数mobile不能为空！");
			
			return result;
		}
		
		log.info("==========调用addSheetDisputes接口开始，开始时间："+new DatesUtils().time()+"请求参数：orderId="+userId+"userId="+userId+"code="+code+"name="+name+"mobile="+mobile+"==========");
		
		Record orderInfo = Db.findFirst("select s.id as shopId,s.name,o.id,o.shop_user_id,o.order_no,o.add_time,o.payment_status,o.payable_amount,o.order_amount,o.real_amount,o.status,o.express_fee from dt_orders o inner join dt_user_role_shopinfo s on o.shop_user_id=s.user_id and o.id="+orderId);
		//处理参数
		SheetInfoParam param = new SheetInfoParam();
		param.setSponsor_id(userId);
		param.setReportTitle("TRD");
		param.setReportType(code);
		param.setDescr(descr);
		param.setPics(pics);
		param.setSponsor_contact(mobile);
		param.setSponsor(name);
		param.setOrderId(Integer.parseInt(orderId));
		param.setShopInfo(orderInfo.getInt("shopId"));
		param.setShopId(orderInfo.getInt("shop_user_id"));
		
		String stu = saveMainSheet(param);
		//返回消息
		if(!stu.equals("")){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(stu);
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
			result.setResult("系统错误！");
		}
		log.info("=====调用addSheet接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
	}
	
	
	
	/*
	 * 客服系统 -举报
	 * */
	
	/**
	 * 我要举报
	 * @param pageNumber
	 * @param sponsor_id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult reportAll(Integer pageNumber,Integer sponsor_id,String short_title){
		
		
		SheetResult result = new SheetResult();
		
		if(pageNumber == null){
			pageNumber = 1;
		}
		if(sponsor_id == null || "".equals(sponsor_id)){
			result.setCode("403");
			result.setMessage("参数sponsor_id不能为空！");
			return result;
		}
		if(short_title == null || "".equals(short_title)){
			result.setCode("403");
			result.setMessage("参数short_title不能为空！");
			return result;

		}
		
		log.info("==========调用reportAll接口开始，开始时间："+new DatesUtils().time()+"请求参数：short_title="+short_title+" sponsor_id="+sponsor_id+" pageNumber="+pageNumber+"==========");
		
		StringBuffer sql = new StringBuffer(" from dt_sheets s left join dt_sheet_type t on s.type_code = t.code where s.sheet_no like '"+short_title+"%' and sponsor_id="+sponsor_id);
		
		Page<Record> page = Db.paginate(pageNumber, 10, "select s.id,s.sheet_no,s.start_time,s.status,t.title", sql.toString());
		
		if(page != null){
			
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(page);
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
		}
		log.info("=====调用reportAll接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
	}
	
	/**
	 * 我要举报详情
	 * @param sheet_id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult reportDetail(String sheet_id){
		
		SheetResult result = new SheetResult();
		
		if(sheet_id == null || sheet_id.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_id不能为空！");
			return result;
		}
		
		log.info("==========调用reportDetail接口开始，开始时间："+new DatesUtils().time()+"请求参数： sheet_id="+sheet_id+"==========");

		//1.查询工单信息
		String sql = "select id,status,sponsor_id,sheet_no from dt_sheets where id="+sheet_id;
		Record sheet = Db.findFirst(sql);
		if(sheet != null){
			
			//2.查询流程信息
			List<Record> flow = Db.find("select opr_time,result from dt_sheet_flowlog where sheet_id="+sheet_id);
			sheet.set("flow", flow);
			
			//3.举报类型
			String reportType = Db.queryStr("select title from dt_sheet_type where code=(select type_code from dt_sheets where id="+sheet_id+")");
			sheet.set("reportType", reportType);
			
			//4.举报内容信息
			String short_title = sheet.get("sheet_no").toString().substring(0,3);
			if("RGD".equals(short_title)){
				Record reportGoods = Db.findFirst("select a.title,a.img_url,s.name from dt_article  a  left join dt_user_role_shopinfo s on a.user_role_value=s.id where a.id=(select article_id from dt_sheet_business where sheet_id ="+sheet_id+")");
				sheet.set("reportGoods", reportGoods);
			}else if("RSH".equals(short_title)){
				Record reportShop = Db.findFirst("select name,pc_logo from dt_user_role_shopinfo where id=(select shop_id from dt_sheet_business where sheet_id="+sheet_id+")");
				sheet.set("reportShop", reportShop);
			}else{
				sheet.set("reportMj", "满集员工");
			}
			
			//5.问题描述
			List<Record> reportInfo = Db.find("select s.id,s.sheet_no,i.descr,i.pics,convert(varchar(19),i.add_time,20) add_time,i.submitter from dt_sheets s,dt_sheet_info i where s.id=i.sheet_id and s.id=? order by i.add_time desc",sheet_id);
			sheet.set("reportInfo", reportInfo);
			
			//6.仲裁结果
			String status = sheet.get("status").toString();
			if("2".equals(status) || "5".equals(status)){
				Record reportArb = Db.findFirst("select content,pics from dt_sheet_detail where sheet_id = "+sheet_id);
				sheet.set("reportArb", reportArb);
			}
			
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(sheet);
		} else {
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("暂无数据");
		}
		log.info("=====调用reportDetail接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
	}
	
	/**
	 * 保存举报信息
	 * @param userId
	 * @param code
	 * @param descr
	 * @param pics
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult saveReportSheet(String userId,String code,String descr,String pics,Integer shop_id,Integer article_id){
		
		SheetResult result = new SheetResult();
		//校验参数
		if(userId == null || userId.equals("")){
			result.setCode("403");
			result.setMessage("参数user_id不能为空！");
			return result;
		}
		if(code== null || code.equals("")){
			result.setCode("403");
			result.setMessage("参数code不能为空！");
			
			return result;
		}
		if(descr  == null || descr.equals("")){
			result.setCode("403");
			result.setMessage("参数descr不能为空！");
			return result;
		}
		
		log.info("==========调用reportDetail接口开始，开始时间："+new DatesUtils().time()+"请求参数： userId="+userId+" code"+code+" descr"+descr+" pics"+pics+" shop_id"+shop_id+" article_id"+article_id+"==========");
		
		String short_title = Db.queryStr("select short_title from dt_sheet_type where code=?",code);
		//处理参数
		SheetInfoParam param = new SheetInfoParam();
		
		param.setSponsor_id(userId);
		param.setReportTitle(short_title);
		param.setReportType(code);
		param.setDescr(descr);
		param.setPics(pics);
		if(shop_id != null){
			param.setShopInfo(shop_id);
		}
		if(article_id != null){
			param.setArticleId(article_id);
		}
		
		String stu = saveMainSheet(param);
		//返回消息
		if(!stu.equals("")){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(stu);
		}else{
			result.setCode("500");
			result.setMessage(ResultEmuns.getMessageByCode("500"));
			result.setResult("系统错误！");
		}
		log.info("=====调用saveReportSheet接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");
		
		return result;
		
	}
	
	/**
	 * 获取举报类型
	 * @param short_title
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult reportType(String short_title){
		
		SheetResult result = new SheetResult();
		
		if(short_title == null || short_title.equals("")){
			result.setCode("403");
			result.setMessage("参数sheet_id不能为空！");
			return result;
		}
		
		log.info("==========调用reportType接口开始，开始时间："+new DatesUtils().time()+"请求参数：  short_title"+short_title+"==========");

		String sql = "select id,code,title,short_title from dt_sheet_type where layer=2 and short_title=?";
		List<Record> records = Db.find(sql,short_title);
		
		if(records != null){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(records);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("暂无数据");
		}
		log.info("=====调用reportType接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");

		return result;
	}
	
	/**
	 * 获取商品信息
	 * @param article_id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult reportGoods(String article_id){
		
		SheetResult result = new SheetResult();
		
		if(article_id == null || article_id.equals("")){
			result.setCode("403");
			result.setMessage("参数article_id不能为空！");
			return result;
		}
		log.info("==========调用reportGoods接口开始，开始时间："+new DatesUtils().time()+"请求参数：  article_id"+article_id+"==========");

		String sql = "select a.id,a.title,a.img_url,s.name from dt_article a left join dt_user_role_shopinfo s on a.user_role_value=s.id where a.id=?";
		Record record = Db.findFirst(sql,article_id);
		
		if(record != null){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(record);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("暂无数据");
		}
		log.info("=====调用reportGoods接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");

		return result;
	}
	
	/**
	 * 获取商家信息
	 * @param article_id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SheetResult reportShop(String shop_id){
		
		SheetResult result = new SheetResult();
		
		if(shop_id == null || shop_id.equals("")){
			result.setCode("403");
			result.setMessage("参数shop_id不能为空！");
			return result;
		}
		log.info("==========调用reportShop接口开始，开始时间："+new DatesUtils().time()+"请求参数：  shop_id"+shop_id+"==========");

		String sql = "select id,name,pc_logo from dt_user_role_shopinfo where id=?";
		Record record = Db.findFirst(sql,shop_id);
		
		if(record != null){
			result.setCode("200");
			result.setMessage(ResultEmuns.getMessageByCode("200"));
			result.setResult(record);
		}else{
			result.setCode("404");
			result.setMessage(ResultEmuns.getMessageByCode("404"));
			result.setResult("暂无数据");
		}
		log.info("=====调用reportShop接口结束，调用结束时间："+new DatesUtils().time()+"日志信息："+JFinalJson.getJson().toJson(result).toString()+"=============");

		return result;
	}
}
