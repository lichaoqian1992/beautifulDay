package com.manji.finance.order;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.manji.finance.index.MyInterceptor;
import com.manji.finance.order.accountErrInfo.accountErrInfoRepository;
import com.manji.finance.order.accountErrInfo.accountErrInfoService;
import com.manji.finance.order.orderSettlement.orderSettlementService;
import com.manji.finance.order.requestParams.OrderParam;
import com.manji.finance.recharge.requestParams.RechargeParams;
import com.manji.finance.system.httplogService;
import com.manji.finance.utils.ExcelUtils;
import com.manji.finance.utils.InterfaceUtil;
import com.manji.finance.withdrawals.Http.HttpService;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


@Before(MyInterceptor.class)
public class OrderController extends Controller{
	
	private OrderService service = new OrderService();
	WithdrawalsService withdrawalsService=new WithdrawalsService();
	HttpService httpService=new HttpService();
	httplogService httplogService=new httplogService();
	accountErrInfoService accountErrInfoService=new accountErrInfoService();
	accountErrInfoRepository aAccountErrInfoRepository=new accountErrInfoRepository();
	orderSettlementService ordersettlementService=new orderSettlementService();
	public void index(){
		
		renderText("没有找到该页面");
		 
	}
	
	
	
	public void order(){
		
		
		
		render("orderList.html");
	}
	
	public void business(){
		
		
		render("businessList.html");
	}
	
	public void businessDetils(){
		
		render("businessDetils.html");
	}

	public void buyers(){

		render("buyers.html");
	}


	
	public void transfer(){
		String transaction_no = getPara("transaction_no");
		setAttr("transaction_no",transaction_no);
		render("transferList.html");
	}
	
	/**
	 * 根据订单号查询结算信息
	 */
	public void querySettle(){
		String order_no = getPara("orderNo");
		List<Record> page = service.settleInfo(order_no);
		setAttr("settleInfo",JSONArray.fromObject(page));
		render("settle.html");
	}
	
	/**
	 * 根据订单号修改结算状态
	 */
	public void updSettle(){
		String order_no = getPara("order_no");
		String role_type = getPara("role_type");
		int count = service.updSettle(order_no, role_type);
		renderJson(count);
	}


	
	/**
	 * 修改结算状态
	 */
	
	public void updSettleStatus(){
		//获取参数
		String orderNo = getPara("order_no");
		String orderType= getPara("order_type");
		//生成请求参数
		TreeMap<String,String> map = httpService.getSettleTreeMap(orderNo, orderType);
		String k = null;
		try {
			k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			if(ErrCode == 0){
				//添加到http日志表
				httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),
						jsonObject.getJSONObject("Data").getString("QuerySN"), 
						jsonObject.getJSONObject("Data").getString("TranSN"), 
						jsonObject.getJSONObject("Data").getString("ResultData"));
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if(ResultCode == 8){
					//返回修改成功的数据，并修改本地数据库状态
					String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
					//存取操作失败
				setAttr("isok","success");
				setAttr("message",ResultData);
				}
			}else{
				//请求失败
				String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
				setAttr("isok","error");
				setAttr("message",ResultData);
			}
		} catch (IOException e) {
			setAttr("isok","failed");
			
			e.printStackTrace();
		}
		renderJson();
	}
	
	/**
	 * 查询订单信息
	 */
	public void findOrders(){
		String name = getPara("name");
		String orderNo = getPara("orderNo");
		Integer status = Integer.parseInt(getPara("status"));
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		Page<Record> list = service.findOrders(name,orderNo,status,startTime,endTime,pageNum);			
		renderJson(list);
	}
	
	
	/**
	 * 模糊查询商家信息
	 */
	
	public void findBusiness(){
		String name = getPara("name");
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		Page<Record> list = service.findBusiness(name, pageNum);
		setAttr("getId",JSONArray.fromObject(list));
		renderJson(list);
	}
	
	/**
	 * 商家详细信息
	 */
	
	public void findBusinessDetails(){
		int user_id = Integer.parseInt(getPara("user_id"));
		int err_logId=getParaToInt("err_logId",-1);
		setAttr("err_logId",err_logId);
		setAttr("user_id",user_id);
		Record list = service.findBusinessDetails(user_id);
		setAttr("business",JSONArray.fromObject(list).toString());
		setAttr("businessalso",list);
		//商家账户
		List<Record> amountType = service.findType(user_id);
		setAttr("amountType",JSONArray.fromObject(amountType));
		Record list1 = service.findAccountInfo(user_id);

		//商家图片信息
		List<Record> pics = service.findPictures(user_id);
		setAttr("pictures",JSONArray.fromObject(pics));
		
		//查询(提现在途,正在处理,冻结提现)
		if(list1 != null){
			Double a =0.00;
			Double b =0.00;
			Double c =0.00;
			if(list1.get("withdrawals_id")!=null){
				a = withdrawalsService.findwithdrawalsCL(String.valueOf(user_id));
				b = withdrawalsService.findwithdrawalsDJ(String.valueOf(user_id));
				c = withdrawalsService.findwithdrawalsZT(String.valueOf(user_id));
				setAttr("CL", a);
				setAttr("DJ", b);
				setAttr("ZT", c);
			}else{
				setAttr("CL", a);
				setAttr("DJ", b);
				setAttr("ZT", c);
			}
			setAttr("account",JSONArray.fromObject(list1).toString());
		}

		render("businessDetails.html");
	}
	
	/**
	 * 商家账户
	 */
	
	public void findAccountInfo(){
		
		int id = Integer.parseInt(getPara("id"));
		Record list1 = service.findAccountInfo(id);
		//查询(提现在途,正在处理,冻结提现)
		/*Record withdrawals = withdrawalsService.getTUserWithdrawals(Integer.toString(id));
		setAttr("CL", withdrawalsService.findwithdrawalsCL(withdrawals.get("user_id").toString()));
		setAttr("DJ", withdrawalsService.findwithdrawalsDJ(withdrawals.get("user_id").toString()));
		setAttr("ZT", withdrawalsService.findwithdrawalsZT(withdrawals.get("user_id").toString()));*/
		renderJson(list1);
		
	}
	
	/**
	 * 资金流水
	 */
	
	public void findAmount(){

		//资金流水
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		Integer id = Integer.parseInt(getPara("id"));
		String  type = getPara("type");
		String  startTime = getPara("startTime");
		String  endTime = getPara("endTime");
		String wstatus=getPara("wstatus");
		Page<Record> list2 = service.findAmount(id,type,startTime,endTime,pageNum,wstatus);
		/*list2.set("add_time", list2.get("add_time").toString());*/
		setAttr("amount",JSONArray.fromObject(list2));
		renderJson(list2);
	}
	

	/**
	 * 结算订单
	 */
	
	public void findSettle(){
		
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		Integer id = Integer.parseInt(getPara("id"));
		String type = getPara("type");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		Page<Record> list3 = service.findSettle(id,type,startTime,endTime,pageNum);
		setAttr("settle",JSONArray.fromObject(list3));
		renderJson(list3);
	}
	
	/**
	 * 业务提点
	 */
	
	public void findPercentage(){
		
		
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		Integer id = Integer.parseInt(getPara("id"));
		Page<Record> list4= service.findPercentage(id,pageNum);
		setAttr("percentage",JSONArray.fromObject(list4));
		renderJson(list4);
	}
	
	/**
	 * 转账信息
	 */
	
	public void findTransfer(){
		Integer pageNum = Integer.parseInt(getPara("pageNum"));
		String transaction_no = getPara("transaction_no");
		String  startTime = getPara("startTime");
		String  endTime = getPara("endTime");

		Page<Record> list5 = service.findTransfer(transaction_no,startTime,endTime,pageNum);
		
		setAttr("transfer1",JSONObject.fromObject(list5));
		
		renderJson(list5);
		
	}
	
	/**
	 * 导出订单excel
	 */
	public void toExcel(){
		OrderParam orderParam = new OrderParam();
		String orderNo = getPara("order_no");
		orderParam.setOrder_no(orderNo);
		orderParam.setName(getPara("name"));
		orderParam.setStatus(Integer.parseInt(getPara("status")));
		orderParam.setStartTime(getPara("startTime"));
		orderParam.setEndTime(getPara("endTime"));
		ExcelUtils e = new ExcelUtils();
		String path = e.userOrdersExcel(orderParam);
		renderFile(new File(path));
	}
	
	/**
	 * 导出资金流水excel
	 */
	
	public void toAmountExcel(){
		OrderParam orderParam = new OrderParam();
		orderParam.setUser_id(Integer.parseInt(getPara("user_id")));
		orderParam.setType(getPara("type"));
		orderParam.setStartTime(getPara("startTime"));
		orderParam.setEndTime(getPara("endTime"));
		ExcelUtils e = new ExcelUtils();
		String path = e.toAmountExcel(orderParam);
		renderFile(new File(path));
	}
	
	/**
	 * 导出结算订单excel
	 */
	
	public void toSettleExcel(){
		OrderParam orderParam = new OrderParam();
		orderParam.setUser_id(Integer.parseInt(getPara("user_id")));
		orderParam.setSettlement_status(Integer.parseInt(getPara("settlement_status")));
		orderParam.setStartTime(getPara("startTime"));
		orderParam.setEndTime(getPara("endTime"));
		ExcelUtils e = new ExcelUtils();
		String path = e.AccountExcel(orderParam);
		renderFile(new File(path));
	}

	/**
	 * 用户基本信息
	 */
	public void findUserAccountAll(){

		int number=getParaToInt("number",1);
		String username=getPara("username");

		Page<Record> page=service.findUserAccountAll(username,number);
		setAttr("pageList",page);
		renderJson();
	}


	/**
	 * 查询用户详细信息
	 */
	public void fundUserInfo(){

		String userId=getPara("userId");

		int err_logId=getParaToInt("err_logId",-1);

		setAttr("err_logId",err_logId);

		Record record=service.findUserInfo(Integer.parseInt(userId));
		if (record!=null){
			record.set("status",record.get("status").toString());
			record.set("state",record.get("state").toString());
		}

		//查询(提现在途,正在处理,冻结提现)
		setAttr("CL", withdrawalsService.findwithdrawalsCL(userId));
		setAttr("DJ", withdrawalsService.findwithdrawalsDJ(userId));
		setAttr("ZT", withdrawalsService.findwithdrawalsZT(userId));

		//查询(账户余额，可提现余额,不可提现余额)
		if (record!=null){
		Double YE = withdrawalsService.getAccountinfoAmountSumByid(record.get("account_id").toString());
		Double KYE = withdrawalsService.getAllowAmountinfoAmountSumByid(record.get("account_id").toString());
		setAttr("YE", YE);
		setAttr("KYE", KYE);
		setAttr("BYE", YE - KYE);
		}else{
			setAttr("YE", 0.00);
			setAttr("KYE", 0.00);
			setAttr("BYE", 0.00);
		}

		setAttr("userInfo",record);
		render("buyersinfo.html");
	}


	public void payinfo(){

		//查询全部支付方式(用于下拉列表)
		List<Record> recordList=service.findPaymentAll();

		setAttr("payAll",recordList);
		render("payinfo.html");
	}

	/**
	 * 查拳全部在线支付记录
	 */
	public void findPaymentInfo(){

		int number=getParaToInt("number",1);
		String status=getPara("status");
		String paymentType=getPara("paymentType");
		String startTime=getPara("startTime");
		String endTime=getPara("endTime");
		String user_name=getPara("user_name");

		Page<Record> recordPage=service.findPaymentInfo(number,status,paymentType,startTime,endTime,user_name);

		setAttr("recordPage",recordPage);
		renderJson();
	}



	/**
	 *进入账户异常日志页面
	 */
	public void accountErrInfo(){

		render("accountErrInfo.html");
	}

	/**
	 * 查询金额异常日志信息
	 */
	public void findaccountErrInfo(){

		String time=getPara("time");
		int number=getParaToInt("number",1);

		Page<Record> page=accountErrInfoService.findAllLogInfo(time,number);

		setAttr("accountPage",page);
		renderJson();
	}


	/**
	 * 修改状态
	 */
	public void updateisdispoy(){
		String log_id=getPara("log_id");
		int i=accountErrInfoService.updateisdispoy(log_id);
		setAttr("i",i);
		renderJson();
	}

	/**
	 * 批量检测全部数据
	 */
	public void runExepion(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String logstartTime=formatter.format(new Date());
		int number=0;
		//获取今日0点时间
		String endTime=accountErrInfoService.getendTimeTime();

			//开始执行检测账户方法
			number=accountErrInfoService.isExepion("2016-09-01 00:00:00",endTime);

		aAccountErrInfoRepository.insertLogLog(logstartTime,formatter.format(new Date()),number);
		renderJson();
	}

	/**
	 * 进去有结算信息的商家
	 */
	public void settleShop(){

		render("settleShop.html");
	}

	/**
	 * 查询有结算信息的商家
	 */
	public void findShopBySettle(){

		int number=getParaToInt("number",1);

		String ShopName=getPara("shopname");

		Page<Record> page=ordersettlementService.findShopInfoByorder(number,ShopName);
		setAttr("pageList",page);
		renderJson();
	}

	public void settleOrder(){
		int number=getParaToInt("number",1);

		String userId=getPara("user_id");

		setAttr("userId",userId);
		render("settleOrder.html");
	}

	/**
	 * 点击单个结算商家时查询出对应结算单
	 */
	public void findAllSettlement(){

		int number=getParaToInt("number",1);

		String userId=getPara("user_id");

		Page<Record> page=ordersettlementService.findAllSettlement(number,userId);

		setAttr("pageList",page);

		renderJson();
	}




}
