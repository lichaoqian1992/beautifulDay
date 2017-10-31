package com.manji.sheet.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.bean.MessageEvaluate;
import com.manji.sheet.utils.WorkerNumberUtil;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lcq on 2017/6/15.
 */
public class EvaluateService {

	
	private static Logger logger = Logger.getLogger(EvaluateService.class);

	/**
	 * 申请平台介入
	 * @param content
	 * @param orderId
	 * @param pics
	 * @param user
	 * @return
	 */
	@Before(Tx.class)
	public MessageEvaluate shopIntervention (String content,String orderId,String pics,String source,Account user){

		MessageEvaluate message = new MessageEvaluate();
		message.setState(1);
		message.setMessage("申请纠纷成功");


		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		boolean queryBoo;
		String short_title= WorkerNumberUtil.getcodeTitle("07_01").get("short_title").toString();//获取举报类型
		Record selShopInfo=this.selShopInfo(orderId);
		if(selShopInfo==null){
			message.setState(0);
			message.setMessage("订单ID无效");
			return message;
		}

		if(!source.equals("")){
			if(source.equals("Shop_App") || source.equals("Shop_Pc")){
				switch (source){
					case "Shop_App":
						source="04_03";
						break;
					case "Shop_Pc":
						source="02_03";
						break;
					default:
						break;
				}
			}else{
				message.setState(0);
				message.setMessage("source参数错误");
				return message;
			}
		}else{
			message.setState(0);
			message.setMessage("source:参数不能为空");
			return message;
		}


		//主表(dt_sheets)
		Record sheet = new Record();
		sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(short_title));
		sheet.set("status",1);
		sheet.set("type_code","07_01");
		sheet.set("sponsor_id",user.getUser_id());
		sheet.set("sponsor",selShopName(user.getUser_id()));
		sheet.set("sponsor_type",1);
		sheet.set("sponsor_contact",user.getMobile());
		sheet.set("start_time",sdf.format(new Date()));
		sheet.set("source_code",source);
		sheet.set("priority_level",1);
		sheet.set("is_push",0);
		queryBoo=Db.save("dt_sheets",sheet);
		if(!queryBoo){

			message.setState(0);
			message.setMessage("申请纠纷失败");
			return message;
		}

		//资料表(dt_sheet_info)
		Record sheetData = new Record();
		sheetData.set("sheet_id",sheet.get("id"));
		sheetData.set("descr",content);
		sheetData.set("pics",pics);
		sheetData.set("add_time",sdf1.format(new Date()));
		sheetData.set("submitter",selShopName(user.getUser_id()));
		sheetData.set("submitter_id",user.getUser_id());
		sheetData.set("submitter_type",user.getUser_role_type());
		queryBoo=Db.save("dt_sheet_info",sheetData);
		if(!queryBoo){

			message.setState(0);
			message.setMessage("申请纠纷失败");
			return message;
		}

		//工单业务关系表(dt_sheet_business)
		Record sheetRelation = new Record();
		sheetRelation.set("sheet_id",sheet.get("id"));
		sheetRelation.set("order_id",orderId);
		sheetRelation.set("back_order_id",0);
		sheetRelation.set("shop_user_id",selShopInfo.get("shop_user_id"));
		sheetRelation.set("shop_id",selShopInfo.get("shop_user_role_value"));
		sheetRelation.set("article_id",0);
		queryBoo=Db.save("dt_sheet_business",sheetRelation);
		if(!queryBoo){

			message.setState(0);
			message.setMessage("申请纠纷失败");
			return message;
		}

		//工单流程记录(dt_sheet_flowlog)
		Record flowLog = new Record();
		flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf2.format(new Date())).set("opr_type", 1).set("result", "待受理").set("descr", content).set("pics", pics).set("is_see", 0);
		queryBoo=Db.save("dt_sheet_flowlog",flowLog);
		if(!queryBoo) {

			message.setState(0);
			message.setMessage("申请纠纷失败");
			return message;
		}

		int updateRow=Db.update("update dt_order_comment set intervention_state=1 where order_id=? and is_lock =0",orderId);
		if(updateRow<=0){
			message.setState(0);
			message.setMessage("申请纠纷失败");
			return message;
		}

		return message;
	}

	/**
	 * 提交凭证
	 * @param content
	 * @param pics
	 * @param user
	 * @param sheetId
	 * @return
	 */
	@Before(Tx.class)
	public MessageEvaluate shopOrderCommentInterventionSubmit (String content,String pics,Account user,String sheetId){
		MessageEvaluate message = new MessageEvaluate();
		message.setState(1);
		message.setMessage("提交资料成功");

		boolean queryBoo;
		String name="";

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(user.getUser_role_type().equals("Buyer")){
			name=selUserName(user.getUser_id());
		}else{
			name=selShopName(user.getUser_id());
		}

		//资料表(dt_sheet_info)
		Record sheetData = new Record();
		sheetData.set("sheet_id",sheetId);
		sheetData.set("descr",content);
		sheetData.set("pics",pics);
		sheetData.set("add_time",sdf.format(new Date()));
		sheetData.set("submitter",name);
		sheetData.set("submitter_id",user.getUser_id());
		sheetData.set("submitter_type",user.getUser_role_type());
		queryBoo=Db.save("dt_sheet_info",sheetData);

		if(!queryBoo){

			message.setState(0);
			message.setMessage("提交资料失败");
			return message;
		}
		return message;
	}


	/**
	 * 订单评论纠纷详情
	 * @param sheetId
	 * @param user
	 * @return
	 */
	public MessageEvaluate shopInterventionDetails (String sheetId,Account user){
		MessageEvaluate message = new MessageEvaluate();
		message.setState(1);
		message.setMessage("查询成功");
		try{
			Record sheetInfo=Db.findFirst("select top 1 descr,opr_type state,opr_time time from dt_sheet_flowlog where sheet_id=? order by opr_time desc",sheetId);//查询工单状态
			if(!sheetInfo.get("state").equals("")){
				int state=Integer.parseInt(sheetInfo.get("state").toString());
				//判断纠纷状态返回相应的数据
				switch (state){
					case 1 :

						sheetInfo.set("state",0);
						sheetInfo.set("result","未受理");
						sheetInfo.set("explain","平台正在受理中，我们会在2个工作日内给出处理意见，请您耐心等待");
						break;
					case 7 :

						sheetInfo.set("state",2);
						sheetInfo.set("result","已解决");
						sheetInfo.set("explain",sheetInfo.get("descr"));
						break;
					case 6 :

						sheetInfo.set("state",2);
						List<Record> punish=Db.find("select * from dt_sheet_punish where sheet_id=?",sheetId);
						if(punish!=null && punish.size()>0){
							sheetInfo.set("result","屏蔽该评价");
						}else{
							sheetInfo.set("result","维持原评价");
						}
						String result=Db.queryStr("select content from dt_sheet_detail where sheet_id=?",sheetId);//查询仲裁结果
						sheetInfo.set("explain",result);
						break;
					case 2 :

						sheetInfo.set("state",2);
						sheetInfo.set("result","维持原评价");
						sheetInfo.set("explain",sheetInfo.get("descr"));
						break;
					default :

						sheetInfo.set("state",1);
						sheetInfo.set("result","平台正在处理中");
						sheetInfo.set("explain","我们会在5个工作日内给出处理意见，请您耐心等待");
						break;
				}
			}else{
				sheetInfo.set("result","");
				sheetInfo.set("explain","");
			}

			sheetInfo.remove("descr");

			sheetInfo.set("connection","如有疑问请致电：400-676-6999");

			List<Record> sheetInfoList=Db.find("select pics,descr content,add_time addTime, (CASE submitter_type WHEN 'Buyer' THEN 1 WHEN 'Shop' THEN 0 ELSE '' END) type, (CASE submitter_type WHEN 'Buyer' THEN '买家描述' WHEN 'Shop' THEN '卖家描述' ELSE '' END) typeName from dt_sheet_info where sheet_id=? order by add_time asc\n",sheetId);
			sheetInfo.set("contentList",sheetInfoList);

			message.setDatas(sheetInfo);

		}catch (Exception e){
			e.printStackTrace();
			message.setState(0);
			message.setMessage("查询失败");
		}
		return message;
	}

	/**
	 * 根据商家Id查询店铺名
	 * @param shopId
	 */
	public String selShopName(String shopId){
		String shop_name = Db.queryStr("select name from dt_user_role_shopinfo where user_id=?",shopId);
		return shop_name;
	}

	/**
	 * 根据商家Id查询用户名
	 * @param userId
	 */
	public String selUserName(String userId){
		String user_name = Db.queryStr("select user_name from dt_users where id=?",userId);
		return user_name;
	}

	/**
	 * 商家信息
	 * @param orderId
	 * @return
	 */
	public Record selShopInfo(String orderId){
		Record selShopInfo = new Record();
		selShopInfo=Db.findFirst("select IsNull(shop_user_id,'') shop_user_id,IsNull(shop_user_role_value,'') shop_user_role_value from dt_orders where id=?",orderId);
		return selShopInfo;
	}


	/**
	 * 根据订单号获取工单号
	 * @param orderId
	 * @param user
	 * @return
	 */
	public String sheetId(String orderId,Account user){
		String sheetId = Db.queryStr("select top 1 convert(varchar(20),id) id from dt_sheets dt left join dt_sheet_business dtb on dtb.sheet_id=dt.id where dtb.order_id=? and type_code='07_01' and sponsor_id=?",orderId,user.getUser_id());
		return sheetId;
	}

	/**
	 * 根据订单号获取工单号(用户)
	 * @param orderId
	 * @param
	 * @return
	 */
	public String sheetIdUser(String orderId){
		String sheetId = Db.queryStr("select top 1 convert(varchar(20),id) id from dt_sheets dt left join dt_sheet_business dtb on dtb.sheet_id=dt.id where dtb.order_id=? and type_code='07_01'",orderId);
		return sheetId;
	}

}