package com.manji.finance.withdrawals.Withdrawals;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.index.MyInterceptor;
import com.manji.finance.system.httplogService;
import com.manji.finance.utils.*;
import com.manji.finance.withdrawals.Account.UserAccountInfoService;
import com.manji.finance.withdrawals.Enums.UserRoleTypeEnums;
import com.manji.finance.withdrawals.Http.HttpService;
import com.manji.finance.withdrawals.Param.ReconciliationParam;
import com.manji.finance.withdrawals.Param.WithDrawalsParam;
import com.manji.finance.withdrawals.Pay.PayInterfaceUtil;
import com.manji.finance.withdrawals.Pay.PayService;
import com.manji.finance.withdrawals.Pay.PayUtil;
import com.manji.finance.withdrawals.Pay.PayreturnLogRepository;
import com.manji.finance.withdrawals.Pay.ReconciliationSercive;
import com.manji.finance.withdrawals.RechargeConfig.RechargeConfigService;
import com.manji.finance.withdrawals.Smslogs.SmslogsServce;
import com.manji.finance.withdrawals.Synchronization.SynchronizationWithdrawalsService;
import com.manji.finance.withdrawals.Synchronization.impl.SynchronizationWithdrawalsServiceImpl;
import com.manji.finance.withdrawals.Users.UsersService;
import com.manji.finance.withdrawals.WithdrawalsDaily.WthdrawalsDailyService;
import com.manji.finance.withdrawals.Withdrawalslog.WithdrawalslogService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 提现操作
 * @author Administrator
 *
 */
@Before(MyInterceptor.class)
public class WithdrawalsController extends Controller{


	WithdrawalsService withdrawalsService=new WithdrawalsService();

	SynchronizationWithdrawalsService synchronizationWithdrawalsService=new SynchronizationWithdrawalsServiceImpl();

	WithdrawalslogService withdrawalslogService=new WithdrawalslogService();

	UserAccountInfoService userAccountInfoService=new UserAccountInfoService();

	HttpService httpService=new HttpService();

	httplogService httplogService=new httplogService();

	SmslogsServce smslogsServce=new SmslogsServce();

	UsersService usersService=new UsersService();

	RechargeConfigService rechargeConfigService=new RechargeConfigService();

	WthdrawalsDailyService wthdrawalsDailyService=new WthdrawalsDailyService();
	
	ReconciliationSercive recService = new ReconciliationSercive();

	public void index(){

		
	}
	
	
	/**
	 * 异常提现
	 */
	public void abnormal(){
		//获取参数
		WithDrawalsParam param=getBean(WithDrawalsParam.class,"");
		//拿到集合展示于页面
		Page<Record> wlist=withdrawalsService.findabnormalWithdrawals(param);
		setAttr("page",wlist);
		render("abnormal.html");
	}


	/**
	 * 异常提现查询方法
	 */
	public void findabnormal(){
		//获取参数
		WithDrawalsParam param=getBean(WithDrawalsParam.class,"");
		//拿到集合展示于页面
		Page<Record> wlist=withdrawalsService.findabnormalWithdrawals(param);
		setAttr("page",wlist);
		renderJson();
	}


	/**
	 * 批量判定异常订单成功(异常订单判定为正常时)
	 *
	 * @return
	 */
	public void Toexamine() {
		//获取参数
		String id=getPara("id");
		String alert=getPara("alert");
		//获取当前用户
		Record user=getSessionAttr("user");
		try {
			//修改异常标示
			String  isok=withdrawalsService.updateToexamine(id);
			Record tw = withdrawalsService.getTUserWithdrawals(id);
			setAttr("isok",isok);
			//添加到操作日志记录
			withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"判定为无异常",user.getStr("REALNAME"),alert);
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		renderJson();
	}




	/**
	 * 冻结账户
	 * @return
	 */
	public void AccountStateFrozen() {
		//获取参数
		String id=getPara("id");
		String alert=getPara("remark");
		//获取当前用户
		Record user=getSessionAttr("user");
		//查询本比提现订单
		Record tw = withdrawalsService.getTUserWithdrawals(id);
		//查询账户详细信息
		Record userAccountInfo = userAccountInfoService.findAccountInfoAll(tw.get("user_id").toString(), UserRoleTypeEnums.getCodeByMsg(tw.get("user_role_type").toString()));
		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMap("AccountStateFrozen",alert,userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value").toString(),tw.get("user_id").toString());
		String k = null;
		try {
			//请求冻结商家接口
			k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
		} catch (IOException e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		JSONObject jsonObject = JSON.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			if (ErrCode == 0) {
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if (ResultCode==8){
					//获取用户
					Record users=usersService.getUserByUserid(tw.getInt("user_id"));
					//添加到HTTP日志表
					httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
					//发送短信
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
					Date d = null;
					try {
						d = dateFormat.parse(tw.getDate("add_time").toString());
					} catch (ParseException e) {
						e.printStackTrace();
						setAttr("isok","err");
					}
					String context="亲！您"+f.format(d)+"的提现申请，因为"+alert+"（原因），平台冻结了您的账户，我们将会尽快查明原因为您解冻。如有疑问致电400-6766-999咨询。[满集网]";
					smslogsServce.sendMessage(tw.getInt("user_id"),userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value"),users.getStr("reg_ip"),"冻结账户操作处理",context,0,users.getStr("mobile"));
					//添加到操作日志记录
					withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"冻结账户",user.getStr("REALNAME"),alert);
				}
				setAttr("isok","success");
			}else{
				setAttr("isok","err");
			 }
		 renderJson();
	}


	/**
	 * 解冻账户
	 */
	public void AccountStateUnFrozen() {
		//获取参数
		String id=getPara("id");
		String alert=getPara("remark");
		//获取用户
		Record user=getSessionAttr("user");
		//查询本比提现订单
		Record tw = withdrawalsService.getTUserWithdrawals(id);
		//查询账户详细信息
		Record userAccountInfo = userAccountInfoService.findAccountInfoAll(tw.get("user_id").toString(), UserRoleTypeEnums.getCodeByMsg(tw.get("user_role_type").toString()));
		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMap("AccountStateUnFrozen",alert,userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value").toString(),tw.get("user_id").toString());
		String k = null;
		try {
			//请求解冻商家接口
			k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
		} catch (IOException e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		JSONObject jsonObject = JSON.parseObject(k);
		int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
		if (ErrCode == 0) {
			int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
			if (ResultCode==8){
				//获取用户
				Record users=usersService.getUserByUserid(tw.getInt("user_id"));
				//添加到HTTP日志表
				httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
				//发送短信
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
				Date d = null;
				try {
					d = dateFormat.parse(tw.getDate("add_time").toString());
				} catch (ParseException e) {
					e.printStackTrace();
					setAttr("isok","err");
				}
				String context="亲！您"+f.format(d)+"的提现申请，因为"+alert+"（原因），平台解冻了您的账户,如有疑问致电400-6766-999咨询。[满集网]";
				smslogsServce.sendMessage(tw.getInt("user_id"),userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value"),users.getStr("reg_ip"),"解冻账户操作处理",context,0,users.getStr("mobile"));

				//添加到操作日志记录
				withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"解锁账户",user.getStr("REALNAME"),alert);
			}
			setAttr("isok","success");
		}else{
			setAttr("isok","err");
		}
		renderJson();
	}




	/**
	 * 修改提现单状态/管理员解冻提现订单
	 */
	public void Frozen(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		//获取参数
		String id=getPara("id");
		String alert=getPara("alert");
		String status=getPara("status");
		String remark=getPara("remark");
		//获取当前登录用户
		Record user=getSessionAttr("user");

		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMaps(id,alert,status);
		String k=null;
		//请求修改提现单状态接口
		try {
			k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
		} catch (IOException e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		JSONObject jsonObject = JSON.parseObject(k);
		int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
		if (ErrCode == 0) {
			int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
			if (ResultCode == 8) {
				//返回修改成功的数据，并修改本地数据库状态
				String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
				//转JSON数组
				JSONArray jsonArray = JSONArray.parseArray(ResultData);
				//存取操作失败的账号信息和原因
				StringBuffer masge = new StringBuffer();
				//短信内容
				String context="";
				for (int i = 0; i < jsonArray.size(); i++) {
					Object obj1 = jsonArray.toArray()[i];
					JSONObject jo = JSON.parseObject(obj1.toString());
					String key = jo.getString("Key");
					String val = jo.getString("Value");
					//查询本比提现订单
					Record tw = withdrawalsService.getTUserWithdrawals(key);
					//获取用户
					Record users=usersService.getUserByUserid(tw.getInt("user_id"));
					//查询账户详细信息
					Record userAccountInfo = userAccountInfoService.findAccountInfoAll(tw.get("user_id").toString(), UserRoleTypeEnums.getCodeByMsg(tw.get("user_role_type").toString()));
					//添加到http日志表
					httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
					//修改本地提现单状态
					withdrawalsService.updateStats(key, status, tw.getStr("remark") + "<br>" + TimeUtils.getTimeUtilSecond() + alert + "<br>");
					//修改成功的数据
					if (val.equals("")) {
						Date d= null;
						try {
							d = dateFormat.parse(tw.getDate("add_time").toString());
						} catch (ParseException e) {
							setAttr("isok","err");
							e.printStackTrace();
						}
						if (status.equals("0")) {
							context = "亲！您" + df.format(d) + "的提现申请，因为" + alert + "（原因），提现单已解冻。如有疑问致电400-6766-999咨询。[满集网]";
						}else if(status.equals("3")){
							context="【满集网】亲！您" + df.format(d) + "的提现申请，因为"+alert+"（原因），平台冻结了该笔提现，我们将会尽快查明原因为您解冻。如有疑问致电400-6766-999咨询。[满集网]";
						}else{
							context="【满集网】亲！您"+ df.format(d) +"的提现申请，因为"+alert+"（原因），此次申请被退回，请尽快核实修改后重新申请。如有疑问致电400-6766-999咨询。[满集网]";
						}
						//发送短信
						try {
							smslogsServce.sendMessage(tw.getInt("user_id"),userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value"),users.getStr("reg_ip"),"异常提现操作处理",context,0,users.getStr("mobile"));
						}catch (Exception e){
							setAttr("smslog","err");
						}

						//添加到操作日志记录
						withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),remark,user.getStr("REALNAME"),alert);
					} else {
						masge.append("提现订单:" + tw.getStr("withdrawals_no") + "处理失败!原因:" + val + "<br>");
					}
				}
				setAttr("isok",masge.toString());
			}
		}
		renderJson();

	}





	/**
	 * 异常提现判定页面
	 */
	public void abnormalForm(){
		//获取提现id
		String id=getPara("id");
		Record withdrawals = withdrawalsService.getTUserWithdrawals(id);
		setAttr("withdrawals",withdrawals);

		//获取此用户的全部提现
		List<Record> withdrawalsList=withdrawalsService.getTUserWithdrawalsByuserId(withdrawals.get("user_id").toString());
		setAttr("withdrawalsList",withdrawalsList);

		//判断此提现时商家(用户)发起
		if (withdrawals.getStr("user_role_type").equals("Buyer")) {
			Record buyerInfo = withdrawalsService.findBuyerInfoDO(withdrawals.get("user_id").toString());
			setAttr("buyerInfo", buyerInfo);
		} else {
			Record shopInfo = withdrawalsService.findShopInfo(withdrawals.get("user_id").toString());
			setAttr("shopInfo", shopInfo);
			//查询技术服务费
			Record business=withdrawalsService.findbusiness(withdrawals.get("user_id").toString());
			setAttr("business", business);
		}
		//查询公司信息
		setAttr("companyinfo",withdrawalsService.findCompanyinfo(withdrawals.get("user_id").toString()));
		//查询(提现在途,正在处理,冻结提现)
		setAttr("CL", withdrawalsService.findwithdrawalsCL(withdrawals.get("user_id").toString()));
		setAttr("DJ", withdrawalsService.findwithdrawalsDJ(withdrawals.get("user_id").toString()));
		setAttr("ZT", withdrawalsService.findwithdrawalsZT(withdrawals.get("user_id").toString()));

		//查询(账户余额，可提现余额,不可提现余额)
		Double YE = withdrawalsService.getAccountinfoAmountSumByid(withdrawals.get("account_id").toString());
		Double KYE = withdrawalsService.getAllowAmountinfoAmountSumByid(withdrawals.get("account_id").toString());
		setAttr("YE", YE);
		setAttr("KYE", KYE);
		setAttr("BYE", YE - KYE);


		//查询操作日志表
		List<Record> loglist=withdrawalsService.findWithdrawalslog(withdrawals.get("withdrawals_no").toString());
		setAttr("loglist",loglist);


		render("abnormalForm.html");
	}



	/**
	 * 查询金额变动表
	 */
	public void getAccountLog(){
		//获取参数
		//String  page=getPara("page");
		String userid=getPara("userid");
		String usertype=getPara("usertype");
		usertype=UserRoleTypeEnums.getCodeByMsg(usertype);
		String startTime=getPara("startTime");
		String endTime=getPara("endTime");
		String wstatus=getPara("wstatus");

		Page<Record> pageRecord=withdrawalsService.findUserAccountLogDOByUserId(userid,usertype,1,startTime,endTime,wstatus);
		setAttr("pageRecord",pageRecord);
		renderJson();
	}


	/**
	 * 查询代金券变动表
	 */
	public void getVoucherLog(){
		//获取参数
//		String  page=getPara("page");
		String userid=getPara("userid");
		String startTime=getPara("startTime");
		String endTime=getPara("endTime");

		Page<Record> pageRecord=withdrawalsService.findUserVoucherLogDOByUserId(userid,1,startTime,endTime);
		setAttr("pageRecord",pageRecord);
		renderJson();
	}





	/**
	 * 通过id查询多条提现记录
	 *
	 * @return
	 */
	public void findAllWithDrawalsByid() {
		//获取参数
		String idList[]=getParaValues("idList");
		List<Record> list = new ArrayList<Record>();
		if (idList!=null){
			for (int i = 0; i < idList.length; i++) {
				list.add(withdrawalsService.getTUserWithdrawals(idList[i]));
			}
		}

		//int id=getParaToInt("id");
		//Record rechargeConfig = rechargeConfigService.getRechargeConfigbyId(id);
		//double money = wthdrawalsDailyService.getWithdrawalsDailySum(id);

		//setAttr("rechargeConfig", rechargeConfig);
		//setAttr("money", rechargeConfig.getDouble("T_TOTAL_MONEY") - money);
		Double zje=0.0;
		for (int i=0;i<list.size();i++){
			zje+=list.get(i).getBigDecimal("total_money").doubleValue();
		}
		//通过本次提交金额查询对应审核权限
		Record conf=withdrawalsService.findconfig(zje.toString());

		//查询对应审核人权限
		Record cuser = usersService.findUserByCid(Integer.parseInt(conf.get("id").toString()));

		setAttr("shrname",cuser.getStr("REALNAME"));
		setAttr("shmoble",cuser.getStr("MOBILE"));
		setAttr("list",list);
		renderJson();
	}

	/**
	 * 查询当前用户手续费状态
	 */
	public void staticSxf(){
		String userid=getPara("userid");
		String roletype=getPara("roletype");

		Record record=withdrawalsService.findsxfStatic(userid,UserRoleTypeEnums.getCodeByMsg(roletype));
		if (record!=null){
			setAttr("w_commission",record.getInt("withdrawals_commission"));
		}
		renderJson();
	}

	/**
	 * 查询对应权限当日可审核金额（今日还可审批金额）
	 * @return
	 */
	public void findToexaminepice() {
		//获取参数
		int id=getParaToInt("id");
		Record rechargeConfig = rechargeConfigService.getRechargeConfigbyId(id);
		double money = wthdrawalsDailyService.getWithdrawalsDailySum(id);
		setAttr("rechargeConfig", rechargeConfig);
		setAttr("money", rechargeConfig.getDouble("T_TOTAL_MONEY") - money);
		renderJson();
	}






	/**
	 * 审核提现
	 */
	public void judge(){


		renderJson();

	}
	
	/**
	 * 提现详情
	 */
	public void info(){
		//获取提现id
		String id=getPara("id");
		Record withdrawals = withdrawalsService.getTUserWithdrawals(id);
		setAttr("withdrawals",withdrawals);

		//获取此用户的全部提现
		List<Record> withdrawalsList=withdrawalsService.getTUserWithdrawalsByuserId(withdrawals.get("user_id").toString());
		setAttr("withdrawalsList",withdrawalsList);

		//判断此提现时商家(用户)发起
		if (withdrawals.getStr("user_role_type").equals("Buyer")) {
			Record buyerInfo = withdrawalsService.findBuyerInfoDO(withdrawals.get("user_id").toString());
			setAttr("buyerInfo", buyerInfo);
		} else {
			Record shopInfo = withdrawalsService.findShopInfo(withdrawals.get("user_id").toString());
			setAttr("shopInfo", shopInfo);
			//查询技术服务费
			Record business=withdrawalsService.findbusiness(withdrawals.get("user_id").toString());
			setAttr("business", business);
		}
		//查询公司信息
		setAttr("companyinfo",withdrawalsService.findCompanyinfo(withdrawals.get("user_id").toString()));

		//查询(提现在途,正在处理,冻结提现)
		setAttr("CL", withdrawalsService.findwithdrawalsCL(withdrawals.get("user_id").toString()));
		setAttr("DJ", withdrawalsService.findwithdrawalsDJ(withdrawals.get("user_id").toString()));
		setAttr("ZT", withdrawalsService.findwithdrawalsZT(withdrawals.get("user_id").toString()));

		//查询(账户余额，可提现余额,不可提现余额)
		Double YE = withdrawalsService.getAccountinfoAmountSumByid(withdrawals.get("account_id").toString());
		Double KYE = withdrawalsService.getAllowAmountinfoAmountSumByid(withdrawals.get("account_id").toString());
		setAttr("YE", YE);
		setAttr("KYE", KYE);
		setAttr("BYE", YE - KYE);


		//查询操作日志表
		List<Record> loglist=withdrawalsService.findWithdrawalslog(withdrawals.get("withdrawals_no").toString());
		setAttr("loglist",loglist);



		render("info.html");
	}



	public void examine(){
		render("examine.html");
	}


	
	
	/**
	 * 提现审核页面
	 */
	public void examines(){
		//获取参数
		WithDrawalsParam param=getBean(WithDrawalsParam.class,"");
		//拿到集合展示于页面
		Page<Record> wlist=withdrawalsService.findexamineWithdrawals(param);
		setAttr("page",wlist);
		renderJson();
	}

	/**
	 * 查询全部提现审核人（对应）待审核条数
	 *
	 * @return
	 */
	public void findAllRechargeConfigCount() {
		WithDrawalsParam param = new WithDrawalsParam();
		List<Integer> dlist = new ArrayList<Integer>();
		param.setRole("8");
		Page<Record> udo = withdrawalsService.findexamineWithdrawals(param);
		dlist.add(udo.getTotalRow());
		param.setRole("4");
		udo = withdrawalsService.findexamineWithdrawals(param);
		dlist.add(udo.getTotalRow());
		param.setRole("2");
		udo = withdrawalsService.findexamineWithdrawals(param);
		dlist.add(udo.getTotalRow());
		param.setRole("3");
		udo = withdrawalsService.findexamineWithdrawals(param);
		dlist.add(udo.getTotalRow());
		setAttr("dlist",dlist);
		renderJson();
	}

	/**
	 * 查询全部提现审核人（对应）待审核总金额
	 * @return
	 */
	public void findAllRechargeConfigSumMoney() {
		List<Double> lid=withdrawalsService.getSumMony();
		setAttr("lid",lid);
		renderJson();
	}

	/**
	 * 查询当日(对应权限)已审核订单条数
	 * @return
	 */
	public void findAllRechargeConfigAudited() {
		List<Long> list = withdrawalsService.getWithdrawalsDailyCount();
		setAttr("lis",list);
		renderJson();
	}



	/**
	 * 发送验证码到审核人手机
	 * @return
	 */
	public void SendverificationCode() {
		String sum=getPara("sum");
		String msum=getPara("msum");
		String maxsum=getPara("maxsum");
		//int cid=getParaToInt("cid");
		//获取当前登录用户
		Record user=getSessionAttr("user");

		//通过本次提交金额查询对应审核权限
		Record conf=withdrawalsService.findconfig(msum);

		//查询对应审核人权限
		Record cuser = usersService.findUserByCid(Integer.parseInt(conf.get("id").toString()));

		try {
			String name = user.getStr("REALNAME");
			String mobile = user.getStr("MOBILE");
			//生成验证码
			String code = VerificationCodeUtils.getYzm();
			//推送短信
			String content = "" + name + "通过财务系统向您发出提现审批请求，本次提现共" + sum + "笔，总金额" + msum + "元，单笔最大金额" + maxsum + "元，验证码为" + code + "。如果您同意此次提现请将该短信验证码回复至" + name + "。如有疑问请向" + name + "咨询,联系电话" + mobile + "。";
			smslogsServce.sendMessage(cuser.getInt("ID"),"财务审核人",0,"","提现审批(财务)",content,0, cuser.getStr("MOBILE"));
			setSessionAttr("code", code);
			setAttr("isok","success");
			setAttr("cid",Integer.parseInt(conf.get("id").toString()));
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		renderJson();
	}



	/**
	 * 对应(审核人)审核通过的订单
	 * @return
	 */

	public void updatedetailed() {
		String newcode=getPara("newcode");
		int cid=getParaToInt("cid");
		String idlist[]=getParaValues("idlist");
		//获取验证码
		String code = getSessionAttr("code");
		//获取当前用户
		Record user =getSessionAttr("user");
		//判断验证码是否正确
		if (newcode==null||code==null||(!newcode.equals(code))) {
			setAttr("isok","Errcode");
			renderJson();
			return;
		}
		try {
			for (int i = 0; i < idlist.length; i++) {
				withdrawalsService.updatedetailed(idlist[i]);
				//通过id查询对应提现记录
				Record tw = withdrawalsService.getTUserWithdrawals(idlist[i]);
				//添加到审核记录表
				wthdrawalsDailyService.insertWithdrawalsDaily(tw.getStr("withdrawals_no"),user.getInt("ID"),tw.getBigDecimal("total_money").doubleValue(),cid);
				//添加到操作日志记录
				withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"审批提现单",user.getStr("REALNAME"),"");
			}
			setAttr("isok","success");
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		renderJson();
	}



	public void ensure(){
		render("ensure.html");
	}
	
	/**
	 * 提现确认
	 */
	public void ensures(){
		//获取参数
		WithDrawalsParam param=getBean(WithDrawalsParam.class,"");
		Page<Record> list=withdrawalsService.findensureWithdrawals(param);
		setAttr("page",list);
		renderJson();
	}

	
	/**
	 * 撤回
	 */
	public void cancel(){
		//获取参数
		String id=getPara("id");
		String alert=getPara("alert");
		//获取当前用户
		Record user=getSessionAttr("user");
		try {
			//修改成异常订单
			withdrawalsService.updateExceptions(id);
			Record tw = withdrawalsService.getTUserWithdrawals(id);
			//添加到操作日志记录
			withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"撤回提现单",user.getStr("REALNAME"),alert);
			setAttr("isok","success");
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("isok","err");
		}
		renderJson();
	}

	
	/**
	 * 确定提现
	 */
	public void approval(){
		//获取参数
		String id=getPara("id");
		String status=getPara("status");
		String remark=getPara("remark");
		//获取当前登录用户
		Record user=getSessionAttr("user");
		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMaps(id,remark,status);
		try {
			String k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			JSONObject jsonObject = JSON.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			//请求成功
			if (ErrCode == 0) {
				//添加到http日志表
				httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if (ResultCode == 8) {
					String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
					//转JSON数组
					JSONArray jsonArray = JSONArray.parseArray(ResultData);
					StringBuffer masge = new StringBuffer();
					for (int i = 0; i < jsonArray.size(); i++) {
						Object obj1 = jsonArray.toArray()[i];
						JSONObject jo = JSON.parseObject(obj1.toString());
						String key = jo.getString("Key");
						String val = jo.getString("Value");
						Record tw = withdrawalsService.getTUserWithdrawals(key);
						if (val.equals("")) {
							//修改本地状态
							withdrawalsService.updateStatsAnddetailed(key, status, tw.getStr("remark") + "<br>" + TimeUtils.getTimeUtilSecond() + remark + "<br>");
							//添加到操作记录表
							withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"确认提现单",user.getStr("REALNAME"),"");
						} else {
							masge.append("提现订单:" + tw.getStr("withdrawals_no") + "处理失败!原因:" + val + "<br>");
						}
					}
						setAttr("isok","success");
						setAttr("Message",masge.toString());
				}
			} else {
				//请求失败
				String Message = jsonObject.getString("Message");
				setAttr("isok",Message);
			}
		} catch (IOException e) {
			setAttr("isok","err");
			e.printStackTrace();
		}
		renderJson();
	}



	/**
	 * 提现成功
	 */
	public void multyApproval(){
		//获取参数
		String id=getPara("id");
		String status=getPara("status");
		String remark=getPara("remark");
		String confirmwhether=getPara("confirmwhether");
		//获取当前登录用户
		Record user=getSessionAttr("user");
		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMaps(id,remark,status);
		try {
			String k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			JSONObject jsonObject = JSON.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			//请求成功
			if (ErrCode == 0) {
				//添加到http日志表
				httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if (ResultCode == 8) {
					String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
					//转JSON数组
					JSONArray jsonArray = JSONArray.parseArray(ResultData);
					StringBuffer masge = new StringBuffer();
					for (int i = 0; i < jsonArray.size(); i++) {
						Object obj1 = jsonArray.toArray()[i];
						JSONObject jo = JSON.parseObject(obj1.toString());
						String key = jo.getString("Key");
						String val = jo.getString("Value");
						//查询当前提现单
						Record tw = withdrawalsService.getTUserWithdrawals(key);
						//查询账户详细信息
						Record userAccountInfo = userAccountInfoService.findAccountInfoAll(tw.get("user_id").toString(), UserRoleTypeEnums.getCodeByMsg(tw.get("user_role_type").toString()));
						//获取用户
						Record users=usersService.getUserByUserid(tw.getInt("user_id"));
						if (val.equals("")) {
							//修改本地状态
							withdrawalsService.updateStatsAnddetailed(key, status, tw.getStr("remark") + "<br>" + TimeUtils.getTimeUtilSecond() + remark + "<br>");
							//修改(完成,失败)状态
							withdrawalsService.updateConfirmwhether(key,confirmwhether);
							//添加到操作记录表
							withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"提现成功",user.getStr("REALNAME"),"");
							//发送短信
							SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
							String content="亲！您" + f.format(new Date()) + "的提现申请现已成功提现，本次成功提现金额为"+tw.getBigDecimal("total_money").doubleValue()+"元。如有疑问致电400-6766-999咨询。[满集网]";
							smslogsServce.sendMessage(tw.getInt("user_id"),userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value"),users.getStr("reg_ip"),"提现完成",content,0,users.getStr("mobile"));
						} else {
							masge.append("提现订单:" + tw.getStr("withdrawals_no") + "处理失败!原因:" + val + "<br>");
						}
					}
					setAttr("isok","success");
					setAttr("Message",masge.toString());
				}
			} else {
				//请求失败
				String Message = jsonObject.getString("Message");
				setAttr("isok",Message);
			}
		} catch (IOException e) {
			setAttr("isok","err");
			e.printStackTrace();
		}
		renderJson();
	}



	/**
	 * 提现失败
	 */
	public void multyApprovalerr(){
		//获取参数
		String id=getPara("id");
		String status=getPara("status");
		String remark=getPara("remark");
		String confirmwhether=getPara("confirmwhether");
		String alert=getPara("alert");
		//获取当前登录用户
		Record user=getSessionAttr("user");
		//生成请求参数
		TreeMap<String,String> map=httpService.getHttpTreeMaps(id,alert,status);
		try {
			String k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			JSONObject jsonObject = JSON.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			//请求成功
			if (ErrCode == 0) {
				//添加到http日志表
				httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if (ResultCode == 8) {
					String ResultData = jsonObject.getJSONObject("Data").getString("ResultData");
					//转JSON数组
					JSONArray jsonArray = JSONArray.parseArray(ResultData);
					StringBuffer masge = new StringBuffer();
					for (int i = 0; i < jsonArray.size(); i++) {
						Object obj1 = jsonArray.toArray()[i];
						JSONObject jo = JSON.parseObject(obj1.toString());
						String key = jo.getString("Key");
						String val = jo.getString("Value");
						//查询当前提现单
						Record tw = withdrawalsService.getTUserWithdrawals(key);
						//查询账户详细信息
						Record userAccountInfo = userAccountInfoService.findAccountInfoAll(tw.get("user_id").toString(), UserRoleTypeEnums.getCodeByMsg(tw.get("user_role_type").toString()));
						//获取用户
						Record users=usersService.getUserByUserid(tw.getInt("user_id"));
						if (val.equals("")) {
							//修改本地状态
							withdrawalsService.updateStatsAnddetailed(key, status, tw.getStr("remark") + "<br>" + TimeUtils.getTimeUtilSecond() + alert + "<br>");
							//修改(完成,失败)状态
							withdrawalsService.updateConfirmwhether(key,confirmwhether);
							//添加到操作记录表
							withdrawalslogService.insertWithdrawalslog(user.getInt("ID"),tw.getLong("id"),tw.getStr("withdrawals_no"),"提现失败",user.getStr("REALNAME"),"");
							//发送短信
							SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
							String content="亲！您" + f.format(new Date()) + "的提现申请，因为" + alert + "（原因），此次申请提现失败，请尽快核实修改后重新申请。如有疑问致电400-6766-999咨询。[满集网]";
							smslogsServce.sendMessage(tw.getInt("user_id"),userAccountInfo.getStr("role_type"),userAccountInfo.getInt("role_value"),users.getStr("reg_ip"),"提现失败",content,0,users.getStr("mobile"));
						} else {
							masge.append("提现订单:" + tw.getStr("withdrawals_no") + "处理失败!原因:" + val + "<br>");
						}
					}
					setAttr("isok","success");
					setAttr("Message",masge.toString());
				}
			} else {
				//请求失败
				String Message = jsonObject.getString("Message");
				setAttr("isok",Message);
			}
		} catch (IOException e) {
			setAttr("isok","err");
			e.printStackTrace();
		}
		renderJson();
	}
	
	/**
	 * 提现记录
	 */
	public void record(){
		render("record.html");
	}

	/**
	 * 提现记录
	 */
	public void records(){
		//获取参数
		WithDrawalsParam param=getBean(WithDrawalsParam.class,"");
		Page<Record> list=withdrawalsService.findrecordWithdrawals(param);
		setAttr("page",list);
		renderJson();

	}


	/**
	 * 提现详情处理页面(提现成功,提现失败)
	 */
	public void dispose(){
		//获取提现id
		String id=getPara("id");
		Record withdrawals = withdrawalsService.getTUserWithdrawals(id);
		setAttr("withdrawals",withdrawals);

		//获取此用户的全部提现
		List<Record> withdrawalsList=withdrawalsService.getTUserWithdrawalsByuserId(withdrawals.get("user_id").toString());
		setAttr("withdrawalsList",withdrawalsList);

		//判断此提现时商家(用户)发起
		if (withdrawals.getStr("user_role_type").equals("Buyer")) {
			Record buyerInfo = withdrawalsService.findBuyerInfoDO(withdrawals.get("user_id").toString());
			setAttr("buyerInfo", buyerInfo);
		} else {
			Record shopInfo = withdrawalsService.findShopInfo(withdrawals.get("user_id").toString());
			setAttr("shopInfo", shopInfo);
			//查询技术服务费
			Record business=withdrawalsService.findbusiness(withdrawals.get("user_id").toString());
			setAttr("business", business);
		}
		//查询公司信息
		setAttr("companyinfo",withdrawalsService.findCompanyinfo(withdrawals.get("user_id").toString()));

		//查询(提现在途,正在处理,冻结提现)
		setAttr("CL", withdrawalsService.findwithdrawalsCL(withdrawals.get("user_id").toString()));
		setAttr("DJ", withdrawalsService.findwithdrawalsDJ(withdrawals.get("user_id").toString()));
		setAttr("ZT", withdrawalsService.findwithdrawalsZT(withdrawals.get("user_id").toString()));

		//查询(账户余额，可提现余额,不可提现余额)
		Double YE = withdrawalsService.getAccountinfoAmountSumByid(withdrawals.get("account_id").toString());
		Double KYE = withdrawalsService.getAllowAmountinfoAmountSumByid(withdrawals.get("account_id").toString());
		setAttr("YE", YE);
		setAttr("KYE", KYE);
		setAttr("BYE", YE - KYE);
		//查询此订单的用户是否被冻结
		if (userAccountInfoService.getAccountInfostatc(withdrawals.getInt("account_id").toString())==1){
			setAttr("static",1);
		}else{
			setAttr("static",0);
		}


		//查询操作日志表
		List<Record> loglist=withdrawalsService.findWithdrawalslog(withdrawals.get("withdrawals_no").toString());
		setAttr("loglist",loglist);
		render("dispose.html");
	}

	/**
	 * 导出excl
	 */
	public void exclinfo(){
		WithDrawalsParam Param=getBean(WithDrawalsParam.class,"");
		ExcelUtils e = new ExcelUtils();
		String path = e.userWithdrawalsExcel(Param);
		renderFile(new File(path));
	}



	/**
	 * 账户详情
	 */
	public void infoalso(){
		int userId=Integer.parseInt(getPara("userId"));
		String usertype=getPara("usertype");
		String orders_old=getPara("orders_old");
		List<Record> acSelect=withdrawalsService.accountSelect(userId);
		Record userSelect=withdrawalsService.userSelect(userId);
		setAttr("acSelect",acSelect);
		setAttr("userSelect",userSelect);
		setAttr("orders_old",orders_old);
		render("infoalso.html");
	}

	/**
	 * 在线充值数据
	 */
	public void recSelect(){
		int userId=Integer.parseInt(getPara("userId","-1"));
		String resNO=getPara("resNO","");
		Page<Record> recSelect=withdrawalsService.recSelect(Integer.parseInt(getPara("page","1")),5,userId,resNO);
		setAttr("recSelect",recSelect);
		renderJson();
	}

	/**
	 * 提现数据
	 */
	public void widSelect(){
		int userId=Integer.parseInt(getPara("userId","-1"));
		String widNO=getPara("widNO","");
		Page<Record> widSelect=withdrawalsService.widSelect(Integer.parseInt(getPara("page","1")),5,userId,widNO);
		setAttr("widSelect",widSelect);
		renderJson();
	}


	//进入资质图片页面
	public void image(){
		String userId=getPara("userId");
		//查询营业执照
		Record record=withdrawalsService.findCompanyinfo(userId);
		setAttr("companyinfo",record);

		//查询委托证明书
		Record bankinfo=withdrawalsService.findBankinfo(userId);
		setAttr("bankinfo",bankinfo);

		//查看其它资质证件
		List<Record> ShopinfoOthers=withdrawalsService.findShopinfoOther(userId);
		setAttr("ShopinfoOthers",ShopinfoOthers);

		String t=record.getStr("license_pics");
		if (t!=null){
			String[] strArray=t.split(",");
			setAttr("strArray",strArray);
		}

		render("image.html");
	}


	//修改商家是否收手续费接口
	public void CounterFee(){
		//获取手续费状态
		String stus=getPara("stus");
		//用户ID
		String userid=getPara("userId");
		//用户类型
		String userType=getPara("usertype");
		//解析类型
		userType=UserRoleTypeEnums.getCodeByMsg(userType);
		//获取账户
		Record record=userAccountInfoService.findAccountInfoAll(userid,userType);
		//如果没有获取到账户
		if (record==null){
			setAttr("isok","err");
			renderJson();
			return;
		}
		//生成请求参数
		TreeMap<String,String> map=httpService.getCounterFeeTreeMap(record.get("id").toString(),stus);

		String k = null;
		try {
			k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
		} catch (IOException e) {
			setAttr("isok","err");
			e.printStackTrace();
			renderJson();
		}
		JSONObject jsonObject = JSON.parseObject(k);
		Integer ErrCode=null;
		try{
			 ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
		}catch (Exception e){
			setAttr("isok","err");
			e.printStackTrace();
			renderJson();
		}

		//请求成功
		if (ErrCode == 0) {
			//添加到http日志表
			httplogService.addHttplog(jsonObject.getJSONObject("Data").getString("Action"),jsonObject.getJSONObject("Data").getString("QuerySN"),jsonObject.getJSONObject("Data").getString("TranSN"),jsonObject.getJSONObject("Data").getString("ResultData"));
			int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
			if (ResultCode == 8) {
				setAttr("isok","success");
			}
		}
		renderJson();

	}






    /**
	 * 测试请求一网通
	 */
	public void testPay(){
		PayService payService=new PayService();

		String xml = payService.getXML("招商银行", "重庆分行", "6225881990011305", "果然10", 1.0, "Test");
		String PKG = payService.getJSON(xml);
		String retunjson = PayUtil.post(PKG);
		//添加代发交易基础返回参数日志
		payService.insertPayreturnLog(retunjson);
	}


	public void toFile(){
		setAttr("info", recService.findFile("","",1));
		render("toFile.html");
	}
	public void toReconciliation(){
		
		render("reconciliation_single.html");
	}
	public void toReconciliationCount(){
		
		render("reconciliation_count.html");
	}
	public void findFile(){
		
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		int page = getParaToInt("page");
		//setAttr("info", recService.findFile(startTime,endTime,page));
		renderJson(recService.findFile(startTime,endTime,page));
	}

	/**
	 * 下载压缩文件
	 */
	public void downFile(){
		
		String url = getPara("url");
		renderFile(new File(url));
		
	}
	/**
	 * 代发对总账
	 */
	public void getReconciliationCount(){
		//1.获取参数
		String xml = "";
		String date = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
		ReconciliationParam param = getBean(ReconciliationParam.class,"");
		String type = param.getType();
		String tx_date = param.getTx_date();
    	String merch_prod = param.getMerch_prod();
		if(type.equals("单笔对账")){
			//单笔对账 
	    	String merch_serial = param.getMerch_serial();
	    	xml = new ReconciliationSercive().getXml(tx_date,merch_prod,merch_serial);
		}else if(type.equals("对总账")){
			//对总账
			xml = new ReconciliationSercive().getXml2(tx_date,merch_prod);
		}
		
    	//请求的参数存入Map
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("COMMID", param.getCommid());
    	map.put("NTBNBR", param.getNtbnbr());
    	map.put("SIGTIM", date);
    	map.put("TRSCOD", param.getTrscod());
		String message = PayUtil.post(recService.buildMessage(xml,map));
		
		//System.out.println("返回的消息："+message);
		String m = recService.analysisPkg(message,type,xml);
		System.out.println(m);
		setAttr("message", m);
		renderJson();
		
	}
	/**
	 * 查询对总账的记录
	 */
	public void findCount(){
		int pageNum = getParaToInt("pageNum");
		setAttr("message", recService.findCount(pageNum));
		renderJson();
		
	}
	/**
	 * 查询单笔对账的记录
	 */
	public void findSingle(){
		int pageNum = getParaToInt("pageNum");
		setAttr("message", recService.findSingle(pageNum));
		renderJson();
		
	}









}
