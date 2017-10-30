package com.manji.finance.recharge;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.manji.finance.index.MyInterceptor;
import com.manji.finance.recharge.model.RechargeDetail;
import com.manji.finance.recharge.requestParams.RechargeParams;
import com.manji.finance.utils.ExcelUtils;
import com.manji.finance.utils.TimeUtils;
import com.manji.finance.utils.VerificationCodeUtils;
import com.manji.finance.withdrawals.Pay.PayUtil;
import com.manji.finance.withdrawals.Pay.ReconciliationSercive;

@Before(MyInterceptor.class)
public class RechargeController extends Controller{

	private RechargeService service =new RechargeService();
	
	public void index(){
		
		render("launch.html");
	}
	/**
	 * 充值申请
	 */
	public void apply(){
		
		render("apply.html");
	}
	/**
	 * 发起充值
	 */
	public void launch(){
		
		render("launch.html");
	}
	/**
	 * 充值审核
	 */
	public void judge(){
		
		render("judge.html");
	}
	/**
	 * 充值确认
	 */
	public void comfirm(){
		
		render("comfirm.html");
	}
	/**
	 * 充值记录
	 */
	public void record(){
		
		render("record.html");
		
	}
	/**
	 * 发起充值页面相关操作*******开始***************************************************************************
	 */
	/**
	 * 添加充值申请
	 */
	public void addOAInfo(){
		getModel(RechargeDetail.class).save();
		
		
	}
	/**
	 * 查询还未提交申请的充值信息
	 */
	public void findRechargeInfo(){
		//1.查询的时候传一个充值类型，用于区分OA充值还是临时充值
		String rechargeType;
		//System.out.println(getPara("rechargeType").toString());
		rechargeType = getPara("rechargeType").toString();
		List<Record> list = service.findRechargeInfo(rechargeType);
		renderJson(list);
	}
	/**
	 * 删除充值信息
	 */
	public void delRechargeInfoByOrderNo(){
		String id = getPara("id");
		Boolean flag = service.delRechargeInfoByOrderNo(id);
		if(flag){
			setAttr("aa","{\"message \": 删除成功}");
		}else{
			setAttr("aa","{\"message \": 删除失败}");
		}
		renderJson();
	}
	/**
	 * 根据账号查询姓名和身份证
	 */
	public void findUserInfo(){
		
		String message = service.findUserInfo(getPara("accountName"));
		
		setAttr("message", message);
		renderJson();
	}
	/**
	 * 根据金额查询审批人
	 */
	public void findRoleByMoney(){
		
		double money = Double.parseDouble(getPara("money"));
		List<Record> r = service.findRoleByMoney(money);
		renderJson(r);
	}
	/**
	 * 保存充值信息
	 * @throws ParseException 
	 */
	public void saveRechargeInfo() throws ParseException{
		
		RechargeDetail r = getModel(RechargeDetail.class,"");
		if(r.get("addOrUpdate").equals("添加") && r.get("ID") == null){
			//添加操作
			int a = service.saveRechargeInfo(r,getPara("personRelease"));
			if(a > 0){
				setAttr("aa","SUCCESS");
			}else{
				setAttr("aa","ERROR");
			}
		}else{
			//修改操作
			int count = service.updateRechargeInfo(r);
			if(count > 0){
				setAttr("aa","SUCCESS");
			}else{
				setAttr("aa","ERROR");
			}
		}	
		renderJson();
	}
	/**
	 * 发送短信验证码给指定的人
	 */
	public void getYzm(){
		RechargeParams param = getBean(RechargeParams.class,"");
		//1.获取验证码
		String yzm = VerificationCodeUtils.getYzm();
		try {
			String status = service.getYzm(param,yzm);
			if(status == "SUCCESS"){
				setSessionAttr("yzm", yzm);
				setAttr("aa","SUCCESS");
			}else if(status == "ERROR"){
				setAttr("aa","ERROR");
			}else if(status == "FULL"){
				setAttr("aa","FULL");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderJson();
		
	}
	/**
	 * 提交充值申请，只是修改审批状态和充值状态
	 */
	public void updateCheckStatus(){
		String type = getPara("type");
		String idList = getPara("idList");
		String operator = getPara("operator");
		String yzm = getPara("yzm");
		String sYzm = getSessionAttr("yzm");
		if(type.equals("临时充值")){
			if(yzm.equals(sYzm)){
				int count = service.updateCheckStatus(idList,operator);
				if(count > 0){
					setAttr("aa","SUCCESS");
					
				}else{
					setAttr("aa","ERROR");
					
				}
			}else{
				setAttr("aa","YZMERROR");
				
			}
		}else{
			int count = service.updateCheckStatus(idList,operator);
			if(count > 0){
				setAttr("aa","SUCCESS");
				
			}else{
				setAttr("aa","ERROR");
			}
		}
		renderJson();
	}
	/**
	 * 文件上传
	 */
	public void uploadExcel(){
		UploadFile f = this.getFile(); 
		String fileName = f.getOriginalFileName();
		String path = f.getUploadPath()+"/"+fileName;
		//System.out.println(path);//
		Record user = getSessionAttr("user");
        String status = service.uploadExcel(path,user);
       
    	render("launch.html");
       
	}
	//发起充值页面相关操作******************结束*********************************************************
	/**
	 * 充值申请页面相关操作*******开始***************************************************************************
	 */
	/**
	 * 查询充值申请
	 * @throws ParseException 
	 */
	public void findApplyRec() throws ParseException{
		RechargeParams param = getBean(RechargeParams.class,"");
		Page<Record> list = service.findRecByCondition(param);
		
		renderJson(list);
	}
	public void rechargePayOA(){
		
		render("rechargePayOA.html");
	}
	public void rechargePayNoOA(){
		
		render("rechargePayNoOA.html");
	}
	/**
	 * 根据单号查询信息
	 * @throws ParseException 
	 */
	public void findByOrderNo() throws ParseException{
		RechargeParams param = getBean(RechargeParams.class,"");
		Page<Record> list = service.findRecByCondition(param);
		
		renderJson(list);
	}
	/**
	 * 修改退回的临时充值申请
	 */
	public void updateRecBack(){
		RechargeParams param = getBean(RechargeParams.class,"");
		//1.判断验证码
		String sYzm = getSessionAttr("yzm");
		String yzm = param.getYzm();
		if(sYzm.equals(yzm)){
			int count = service.updateRecBack(param);
			if(count>0){
				setAttr("aa", "SUCCESS");
			}else{
				setAttr("aa", "FAIL");
			}
		}else{
			setAttr("aa", "ERROR");
		}
		renderJson();
				
	}
	/**
	 * 作废充值单
	 */
	public void cancelOrder(){
		String orderNo = getPara("orderNo");
		String reason = getPara("reason");
		String creater = getPara("creater");
		int count = service.cancelOrder(orderNo,reason,creater);
		if(count>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	//充值申请页面相关********************结束*****************************************************************
	/**
	 * 充值审核相关**********************开始*****************************************************************
	 */
	/**
	 * 撤回充值单
	 */
	public void backOrder(){
		String orderNo = getPara("orderNo");
		String reason = getPara("reason");
		String creater = getPara("creater");
		int count = service.backOrder(orderNo,reason,creater);
		if(count>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	/**
	 * 同意或者批量同意充值申请
	 */
	public void batchAgree(){
		
		String orderNoList = getPara("orderList");
		String creater = getPara("creater");
		int count = service.batchAgree(orderNoList,creater);
		if(count>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	//充值审核页面相关***********结束***************************************************************************
	/**
	 * 确认充值申请，实现对账号的金额的变动
	 * @throws IOException 
	 */
	//@RequiresRoles(value={"cashier"},logical=Logical.OR)//指定那个角色才可以使用这个方法,logical=Logical.OR表示，如果前面的value有多个，只要有一个就行了
	public void confirmRec() throws IOException{
		//感觉这里还不如直接判断登录人的角色是不是cashier，如果不是，直接返回没得权限
		Record u =(Record) getSessionAttr("user");
		if(u.get("T_CONFIG_ID").toString().equals("7")){
			String orderNoList = getPara("orderList");
			String accountNameList = getPara("accountNameList");
			String creater = getPara("creater");
			String message = service.confirmRec(orderNoList,accountNameList,creater);
			//成功5条，失败1条，失败单号：，失败原因：密集充值
			setAttr("aa", message);
		}else{
			setAttr("aa", "您没有操作权限，只能出纳操作！");
		}
		renderJson();
	}
	/**
	 * 充值记录页面相关操作*******开始***************************************************************************
	 */
	/**
	 * 查询充值记录
	 * @throws ParseException 
	 */
	public void findRecord() throws ParseException{
		RechargeParams param = getBean(RechargeParams.class,"");
		Page<Record> list = service.findRecByCondition(param);
		
		renderJson(list);
	}
	/**
	 * 查询当日和当月的充值总金额
	 */
	public void findAcount(){
		String message = service.findAcount();
		setAttr("aa", message);
		renderJson();
	}
	/**
	 * 查询充值订单详情
	 */
	public void findOrderDetail(){
		
		String orderNo = getPara("orderNo");
		List<Record> list = service.findOrderDetail(orderNo);
		renderJson(list);
	}
	/**
	 * 查询充值订单操作日志
	 */
	public void findRechargeLogs(){
		
		String orderNo = getPara("orderNo");
		List<Record> list = service.findRechargeLogs(orderNo);
		renderJson(list);
	}
	/**
	 * 导出excel
	 * @throws ParseException 
	 */
	public void toExcel() throws ParseException{
		RechargeParams p = new RechargeParams();
		p.setOrderNo(getPara("orderNo"));
		p.setAccountName(getPara("userName"));
		p.setStatus(getPara("status"));
		p.setStartTime(getPara("startTime"));
		p.setEndTime(getPara("endTime"));
		p.setRechargeWay(getPara("rechargeWay"));
		String path = service.excelRecRecord(p);
		renderFile(new File(path));
	}
	/**
	 * 下载模板
	 */
	public void downLoadExcel(){
		
		//String s =  System.getProperty("catalina.home")+"\\webapps\\finance\\static\\files\\ExcelModel.xlsx";//D:\tomcat\apache-tomcat-7.0.69
		String s = "\\manji\\server\\tomcat2\\webapps\\finance\\static\\files\\ExcelModel.xlsx";
		//String s = "D:\\tomcat\\apache-tomcat-7.0.69\\webapps\\finance\\static\\files\\ExcelModel.xlsx";

		renderFile(new File(s));
		
	}

}
