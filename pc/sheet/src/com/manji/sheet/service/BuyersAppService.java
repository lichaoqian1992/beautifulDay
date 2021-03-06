package com.manji.sheet.service;

import com.jfinal.aop.Before;

import com.jfinal.plugin.activerecord.Db;

import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.manji.sheet.controller.BuyersAppController;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.enums.StatusEnums;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.utils.WorkerNumberUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class BuyersAppService extends BaseSheetService{
	
	private static Logger logger = Logger.getLogger(BuyersAppService.class);
	
	/**********************************************************************买家APP******************************************************************************/
	/**
	 * 根据商家ID获取店铺信息
	 * @param shopId
	 * @return
	 */
	public Record getShopByName(int shopId){
		StringBuffer sql=new StringBuffer("select s.id,s.user_id,s.name,isnull(a.title,'') title,isnull(mobile,'') mobile,isnull(s.wap_logo,'') logo from dt_user_role_shopinfo s left join dt_article_category a on a.id=s.main_business and s.user_id<>0 and s.is_del=0 and s.dpkg=1 where s.id=?");
		Record shopInfo = Db.findFirst(sql.toString(),shopId);
		return shopInfo;
	}

	/**
	 * 根据订单ID查询订单商家信息
	 * @param
	 * @return
	 */
	public Record getOrderInfo(String orderNo){
		StringBuffer sql=new StringBuffer("select o.*,u.name,u.user_id,u.id as shopId,convert(varchar(20),o.payment_status) payment_status,convert(varchar(20),o.status) status from dt_orders o left join dt_user_role_shopinfo u on o.shop_user_id=u.user_id and u.user_id<>0 and u.is_del=0 and u.dpkg=1 where o.order_no=?");
		Record orderInfo = Db.findFirst(sql.toString(),orderNo);
		return orderInfo;
	}
	/**
	 * 根据订单编号查询订单商品信息
	 * @param orderNo
	 * @return
	 */
	public Record getOrderGoodsInfo(String orderNo){
		
		return Db.findFirst("select * from dt_order_goodinfo where order_id=(select id from dt_orders where order_no=?)",orderNo);
	}
	/**
	 * 根据订单号查询商品id
	 * @param order_id
	 * @return
	 */
	public List<Record> getGoodsInfo(String order_id){
		return Db.find("select * from dt_article where id in (select article_id from dt_order_goods where order_id=? group by article_id)",order_id);
	}

	/**
	 * 根据商品id查询商家信息
	 * @param commodityId
	 * @return
	 */
	public Record getCommodityInfo(int commodityId){
		//select dag.id,da.title,dac.title,dsb.name,durs.name from dt_article_goods dag left join dt_article da on dag.article_id=da.id and da.is_del=0 left join dt_article_category dac on dac.id=da.category_id and dac.is_del=0 left join dt_shop_brand dsb on dsb.shop_id=da.user_id and dsb.is_del=0 and dsb.state=1 left join dt_user_role_shopinfo durs on da.user_id=durs.user_id and  durs.user_id<>0 and durs.is_del=0 and durs.dpkg=1
		StringBuffer sql=new StringBuffer("select a.id,a.category_id,a.title,a.img_url,b.title 'leimu',c.brand,d.name 'pinpai',f.name,f.user_id,f.id as shopId from dt_article a left join dt_article_category b on a.category_id=b.id left join dt_article_info c on a.id=c.article_id left join dt_brand d on c.brand=d.id and c.is_show=1 left join dt_user_role_shopinfo f on a.user_id=f.user_id where a.id=?");
		Record commodityInfo = Db.findFirst(sql.toString(),commodityId);
		return commodityInfo;
	}

	/**
	 * 查看举报或者建议
	 * @return
	 */
	public List<Record> reportInfo(String codeReport,String sponsoId){
		
		StringBuffer sql = new StringBuffer();
		 
		if(codeReport.indexOf("01") != -1 || codeReport.indexOf("02") != -1 || codeReport.indexOf("03") != -1){
			//举报CONVERT(varchar(100), GETDATE(), 20)
			sql.append("select ds.id,ds.sheet_no,convert(varchar(100),ds.start_time,20) as start_time,convert(varchar(20),ds.status) status,ds.type_code,dst.title,dst.merge_title,substring(dst.code,1,2) as ncode from dt_sheets ds , dt_sheet_type dst where ds.type_code=dst.code and dst.status=1 and (dst.code like '01%' or dst.code like '02%' or dst.code like '03%') and ds.sponsor_id=? and ds.sponsor_type='3' and ds.start_time >= dateadd(DAY,-365,GETDATE()) order by ds.start_time desc");
			
		}else{
			//select ds.id,ds.sheet_no,convert(varchar(100),ds.start_time,20) as start_time,convert(varchar(20),ds.status) status,ds.type_code,dst.title,dst.merge_title,substring(dst.code,1,2) as ncode from dt_sheets ds , dt_sheet_type dst where ds.type_code=dst.code and dst.status=1 and ds.type_code like '05%' and ds.sponsor_id=2151076 and ds.sponsor_type=3
			sql.append("select ds.id,ds.sheet_no,convert(varchar(100),ds.start_time,20) as start_time,convert(varchar(20),ds.status) status,ds.type_code,dst.title,dst.merge_title,substring(dst.code,1,2) as ncode from dt_sheets ds , dt_sheet_type dst where ds.type_code=dst.code and dst.status=1 and ds.type_code like '"+codeReport+"%' and ds.sponsor_id=? and ds.sponsor_type=3 and ds.start_time >= dateadd(DAY,-365,GETDATE()) order by ds.start_time desc");
			
		}
		List<Record> list = Db.find(sql.toString(),sponsoId);
		
		return list;
	}
	/**
	 * 补充资料
	 * @param sheetInfo
	 * @return
	 */
	public String add(SheetInfoParam sheetInfo){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stu="";
		try {
		/*保存工单资料表*/
			Record sheetData = new Record();
			sheetData.set("sheet_id", sheetInfo.getId());
			sheetData.set("descr", sheetInfo.getDescr());
			sheetData.set("pics", sheetInfo.getPics());
			sheetData.set("add_time", sdf.format(new Date()));
			sheetData.set("submitter", sheetInfo.getReportUser());
			sheetData.set("submitter_id", sheetInfo.getReportUserId());
			sheetData.set("submitter_type", sheetInfo.getReportUserType());
			Db.save("dt_sheet_info", sheetData);
			stu="SUCCESS";
		}catch(Exception e){
			stu="ERROR";
			e.printStackTrace();
		}
		return stu;
	}

	/**
     * 已解决
	 * @param sheetInfo
     * @return
     */
	public String cancle(SheetInfoParam sheetInfo){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stu="";
		try {
			/*修改工单主表*/
			Db.update("update dt_sheets set status=4 where id=?",sheetInfo.getId());
			/*新增日志*/
			stu="SUCCESS";
		}catch (Exception e){
			stu="ERROR";
			e.printStackTrace();
		}
		return stu;
	}

	/**
	 * 添加投诉信息
	 * @param sheetInfo
	 * @return
	 */
	public String saveShopReport(SheetInfoParam sheetInfo){
		
		logger.info("==============开始保存举报数据=================");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stu="";
		//存用户名称，根据用户id查询用户名称
		String user_name = Db.findFirst("select * from dt_users where id=?",sheetInfo.getSponsor_id()).getStr("user_name");;
		
		/*工单主表*/
			Record sheet = new Record();
			sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(sheetInfo.getReportTitle()));
			sheet.set("status",sheetInfo.getStatus());
			sheet.set("type_code",sheetInfo.getReportType());
			sheet.set("sponsor_id",sheetInfo.getSponsor_id());
			if(!sheetInfo.getReportTitle().equals("TRD")){
				sheet.set("sponsor",user_name);
			}else{
				sheet.set("sponsor",sheetInfo.getSponsor());
			}
			
			sheet.set("sponsor_type",sheetInfo.getSponsor_type());
			sheet.set("sponsor_contact",sheetInfo.getSponsor_contact());
			sheet.set("start_time",sdf.format(new Date()));
			sheet.set("source_code",sheetInfo.getSheetFrom());
			sheet.set("priority_level",sheetInfo.getPriority_level());
			sheet.set("is_push",sheetInfo.getIs_push());
			boolean s = Db.save("dt_sheets",sheet);

			if(s){
				stu = saveOther(sheet, sheetInfo, user_name);
				
				logger.info("==============保存数据完成，返回参数值stu="+stu+"================");
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
			details.set("pics",sheetInfo.getPics());
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
		
	
		stu = "SUCCESS";
		return stu;
	}
	/**
	 * 查询流程信息
	 * @param sheet_no
	 * @return
	 */
	public List<Record> findFlow(String sheet_no){
		
		return Db.find("select *,convert(varchar(10),opr_type) opr_type,convert(varchar(100),opr_time,20) opr_time from dt_sheet_flowlog where sheet_id=(select id from dt_sheets where sheet_no=?)",sheet_no);
		
	}
	
	/**
	 * 查询工单资料信息
	 * @param sheet_no
	 * @return
	 */
	public List<Record> findInfo(String sheet_no){
		
		return Db.find("select *,convert(varchar(100),add_time,20) add_time from dt_sheet_info where sheet_id=(select id from dt_sheets where sheet_no=?) order by dt_sheet_info.add_time desc",sheet_no);
		
	}
	/**
	 * 查询工单详细信息
	 * @param sheet_no
	 * @return
	 */
	public Record findDetails(String sheet_no){
		
		return Db.findFirst("select * from dt_sheet_detail where sheet_id=(select id from dt_sheets where sheet_no=?)",sheet_no);
		
	}
	/**
	 * 查询工单信息
	 * @param sheet_no
	 * @return
	 */
	public Record findSheet(String sheet_no){
		
		return Db.findFirst("select *,convert(varchar(20),a.status) nstatus,convert(varchar(100),start_time,20) start_time from dt_sheets a,dt_sheet_type b where a.type_code=b.code and a.sheet_no=?",sheet_no);
		
	}
	
	/**
	 * 查询店铺信息
	 * @param sheet_no
	 * @return
	 */
	public Record findShopInfo(String sheet_no){
		int shopId = Db.findFirst("select * from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?)",sheet_no).getInt("shop_id");
		return getShopByName(shopId);
		
	}
	/**
	 * 查询订单详情
	 * @param sheet_no
	 * @return
	 */
	public Record findOrder(String sheet_no){
		
		return Db.findFirst("select *,convert(varchar(20),status) status from dt_orders where id=(select order_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))",sheet_no);
	}
	/**
	 * 查询商品信息
	 * @param sheet_no
	 * @return
	 */
	public Record findGoodInfo(String sheet_no){
		int goodId = Db.findFirst("select * from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?)",sheet_no).getInt("article_id");
		return getCommodityInfo(goodId);
		
	}
	/**
	 * 修改单据状态
	 * @param
	 * @param status
	 * @return
	 */
	@Before(Tx.class)
	public int updateStatus(int sheet_id,int status,String descr){
		
		int a = 0;
		Record details = Db.findFirst("select * from dt_sheet_detail where sheet_id=?",sheet_id);
		if(details != null){
			//说明此时该工单已经受理了,故修改详情表中的数据
			int b = Db.update("update dt_sheet_detail set content=? where sheet_id=?",descr,sheet_id);
			a = Db.update("update dt_sheets set status=? where id=? and status != ?",status,sheet_id,status);
		}else{
			//未受理
			a = Db.update("update dt_sheets set status=?,is_push=1 where id=? and status != ?",status,sheet_id,status);
		}
		//商家、用户自己解决问题了
		Record flow = new Record().set("sheet_id", sheet_id).set("opr_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
								.set("opr_type", "7").set("result", StatusEnums.getMessageByCode("7")).set("descr", descr);
		boolean flag = Db.save("dt_sheet_flowlog", flow);
		if(a > 0 && flag){
			Db.update("update dt_sheet_flowlog set is_see=1 where sheet_id=?",sheet_id);
			return 1;
		}
		return  0;
	}
	/**
	 * 查询我的举报的东西的详情图片
	 * @param
	 * @return
	 */
	public Record findShopOrGoodsInfo(String sheetId,String type_code){
		if(type_code.split("_")[0].equals("01")){
			//举报商品
			return Db.findFirst("select a.id,a.category_id,a.title,a.img_url,b.title 'leimu',c.brand,d.name 'pinpai',f.name from dt_article a left join dt_article_category b on a.category_id=b.id left join dt_article_info c on a.id=c.article_id left join dt_brand d on c.brand=d.id and c.is_show=1 left join dt_user_role_shopinfo f on a.user_id=f.user_id where a.id=(select article_id from dt_sheet_business where sheet_id=?)",sheetId);
		}else if(type_code.split("_")[0].equals("02")){
			//举报商家
			Record r = Db.findFirst("select s.id,s.user_id,s.name,isnull(a.title,'') title,isnull(mobile,'') mobile,isnull(s.wap_logo,'') logo from dt_user_role_shopinfo s left join dt_article_category a on a.id=s.main_business and s.user_id<>0 and s.is_del=0 and s.dpkg=1 where s.user_id=(select shop_user_id from dt_sheet_business where sheet_id=?)",sheetId);
			if(r != null){
				return new Record().set("img_url", r.get("logo"));
			}else{
				return new Record().set("img_url", "暂无");
			}
			
		}else if(type_code.split("_")[0].equals("03")){
			//举报员工
			return new Record().set("img_url", null);
		}
		return null;
	}
	/**
	 * 查询某个订单中的商品信息
	 * @return
	 */
	public List<Record> findGoodsById(String sheet_id){
		
		
		return Db.find("select a.id,a.order_no,b.goods_id,b.goods_title,b.img_url,b.quantity from dt_orders a left join dt_order_goods b on a.id=b.order_id where a.id=(select order_id from dt_sheet_business where sheet_id=?)",sheet_id);
	}
	/**
	 * 根据订单查询商品商家信息
	 * @param order_id
	 * @return
	 */
	public List<Record> findGoodByOrderId(String order_id){
		
		return Db.find("select a.id,a.order_no,b.goods_id,b.goods_title,b.img_url,b.quantity from dt_orders a,dt_order_goods b where a.id=b.order_id and a.id=?",order_id);
	}
	/**
	 * 查询该用户的所有已发货的订单
	 * @param user_id
	 * @return
	 */
	public List<Record> findOrderList(String user_id){
		
		return Db.find("select a.id,a.shop_user_id,a.shop_user_role_type,a.shop_user_role_value,a.order_no,a.status,a.book_back_status,a.payment_status,convert(varchar(100),a.add_time,20) as add_time,b.express_status from dt_orders a,dt_order_goodinfo b where a.id=b.order_id and a.status in(1,2,3) and payment_status=2 and a.user_id=? order by a.add_time desc",user_id);
	}
	/**
	 * 查询订单是否评论
	 * @return
	 */
	public List<Record> findComment(String id){
		return Db.find("select * from dt_order_comment a where a.order_id=?",id);
	}
	public Record findSheetById(String id){
		return Db.findById("dt_sheets", id);
	}
	/**
	 * 查询已经申请过的交易纠纷
	 * @param orderId
	 * @param code
	 * @return
	 */
	public Record checkRepeat(String orderId,String code){
		
		return Db.findFirst("select * from dt_sheet_business a,dt_sheets b where a.sheet_id=b.id and a.order_id=? and b.type_code=?",orderId,code);
	}
	/**
	 * 添加资料
	 * @param account
	 * @param order_id
	 * @param descr
	 * @param pics
	 * @return
	 */
	public boolean addData(Account account,String order_id,String descr,String pics){
		
		//1.根据订单号和用户id找到工单id
		Record r = Db.findFirst("SELECT * FROM DT_SHEETS A ,dt_sheet_business B WHERE A.ID=B.sheet_id AND A.type_code='07_01' AND B.order_id=? AND A.sponsor_id=?",order_id,account.getUser_id());
		String sheet_id = r.get("sheet_id").toString();
		//2.向数据库插入数据
		Record info = new Record().set("sheet_id", sheet_id).set("descr", descr).set("pics", pics)
				.set("add_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()))
				.set("submitter", account.getNick_name()).set("submitter_id", account.getUser_id())
				.set("submitter_type", account.getUser_role_type());
		return Db.save("dt_sheet_info", info);
		
	}
}
