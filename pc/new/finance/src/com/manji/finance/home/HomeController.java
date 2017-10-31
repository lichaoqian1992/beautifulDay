package com.manji.finance.home;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.home.model.pageDo;
import com.manji.finance.index.MyInterceptor;
import com.manji.finance.system.UserSmsLogDO;
import com.manji.finance.utils.ExcelUtils;



/**
 * 首页展示详情
 * @author Administrator
 *
 */
@Before(MyInterceptor.class)
public class HomeController extends Controller{

	private HomeService service =new HomeService();
	
	public void index(){
		
	}
	
	/**
	 * 信息汇总
	 */
	public void information(){
		String firstDay="";
		String newDay="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取前月的第一天
        Calendar  cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        
        firstDay=getPara("accountStartTime",format.format(cal_1.getTime()));
        newDay=getPara("accountEndTime",format.format(new Date()));
        
		setAttr("dataCountFront",service.dataCountFront(firstDay,newDay));
		setAttr("dataCountBack",service.dataCountBack(firstDay,newDay));
		setAttr("dataCountExpenditure",service.dataCountExpenditure(firstDay,newDay));
		setAttr("dataCountBalance",service.dataCountBalance(firstDay,newDay));
		//setAttr("dataCountplatformActivities",service.dataCountplatformActivities(firstDay,newDay));
		
		renderJson();
	}
	
	public void toHome(){
		information();
		daylyReport();
		MonthlyReport();
		getReportUser();
		
		render("home.html");
		
	}
	/**
	 * 用户微信支付金额汇总
	 */
	public void weixinDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.weixinDetail(Integer.parseInt(getPara("page","1")),20,scree);
		if(list!=null){
			list=orderSum(list);
		}
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("weixin.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 用户支付宝支付金额汇总
	 */
	public void zhifubaoDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.zhifubaoDetail(Integer.parseInt(getPara("page","1")),20,scree);
		if(list!=null){
			list=orderSum(list);
		}
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("zhifubao.html");
		}else{
			renderJson();
		}
	}
	
	
	/**
	 * 用户银行卡支付金额汇总
	 */
	public void cardDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.cardDetail(Integer.parseInt(getPara("page","1")),20,scree);
		if(list!=null){
			list=orderSum(list);
		}
		setAttr("refoundList",list);

		if(getPara("atr","").equals("")){
			render("card.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 用户余额支付金额汇总
	 */
	public void amountDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Map<String, Object> map=service.amountDetail(Integer.parseInt(getPara("page","1")),20,scree);
		Page<Record> list=(Page<Record>) map.get("listPage");
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("amount.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 满意券支出汇总
	 */
	public void voucherDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.voucherDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);

		
		if(getPara("atr","").equals("")){
			render("voucher.html");
		}else{
			renderJson();
		}
	}
	
	
	/**
	 * 售后/退款汇总
	 */
	public void refoundDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.refoundDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("refound.html");
		}else{
			renderJson();
		}
	}
	
	
	/**
	 * 技术服务费汇总
	 */
	public void techServiceDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.techServiceDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("techService.html");
		}else{
			renderJson();
		}
	}
	
	
	/**
	 * 金融手续费汇总
	 */
	public void financeDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.financeDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("finance.html");
		}else{
			renderJson();
		}
	}
	
	
	
	/**
	 * 保证金汇总
	 */
	public void bondDetail(){
		render("bond.html");
	}
	
	/**
	 * 平台使用服务费汇总
	 */
	public void pltServiceDetail(){
		render("pltService.html");
	}
	
	/**
	 * 商家余额汇总
	 */
	public void shopDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.shopDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("shop.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 用户余额汇总
	 */
	public void userDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.userDetail(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		
		if(getPara("atr","").equals("")){
			render("user.html");
		}else{
			renderJson();
		}
	}


	/**
	 * 预支出总额汇总
	 */
	public void advanceDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		String activeType=getPara("activeType","");

		Record record=service.advanceDetail(Integer.parseInt(getPara("page","1")),20,scree,activeType);

		setAttr("refoundList",record);

		if(getPara("atr","").equals("")){
			render("advance.html");
		}else{
			renderJson();
		}
	}

	/**
	 * 已支出总额汇总
	 */
	public void actualDetail(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		String activeType=getPara("activeType","");

		Record record=service.actualDetail(Integer.parseInt(getPara("page","1")),20,scree,activeType);

		setAttr("refoundList",record);

		if(getPara("atr","").equals("")){
			render("actual.html");
		}else{
			renderJson();
		}
	}
	
	
	/**
	 * 日报表查询
	 */
	public void daylyReport(){
		String dayTime="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取当前日期前一天  
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
        dayTime=getPara("dayTime",format.format(calendar.getTime()));

        setAttr("reportForm",service.ReportFormSelect(dayTime));
       
        renderJson();
	}
	
	
	
	/**
	 * 月报表查询
	 */
	public void MonthlyReport(){
		String monthTime="";
		String type="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取前月的第一天
        Calendar  cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
       	
		monthTime=getPara("monthTime",format.format(new Date()));
        type=getPara("type","");
       
        setAttr("monthReportForm",service.MonthlyReportFormSelect(monthTime,type));
        
        renderJson();
	}

	
	/**
	 * 每日交易汇报
	 */
	public void selectSendDayly(){
		String dayTime="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取当前日期前一天  
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
        dayTime=getPara("dayTime",format.format(calendar.getTime()));
         
        /*获取每日流水*/	
        setAttr("reportFormSendDay",service.ReportFormSelect(dayTime));
        
        setAttr("SendDaylyOrder",service.SendDaylyOrder(dayTime));
        
        renderJson();
	}
	
	/**
	 * 发送每日汇报
	 */
	public void sendDaylyReport(){
		String[] mobiles = getParaValues("mobile");
		String content=getPara("oneInfo")+getPara("towInfo");
		List<UserSmsLogDO> list=new ArrayList<UserSmsLogDO>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取前月的第一天
        Calendar  cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		
		for(int i = 0; i < mobiles.length; i++){
			int random=(int)(Math.random()*900)+100;
			UserSmsLogDO userSms=new UserSmsLogDO();
			userSms.setUserId(random);
			userSms.setMobile(mobiles[i]);
			userSms.setContent(content);
			userSms.setAddTime(format.format(new Date()));
			userSms.setSendTime(format.format(new Date()));
			userSms.setType("发送报送信息");
			userSms.setUserRoleValue(0);
			userSms.setUserRoleType("");
			userSms.setStatus(0);
			userSms.setUserIp("");
			
			list.add(userSms);
		}
		setAttr("typeReport", service.sendReport(list));
		
		renderJson();
	}
	
	/**
	 * 获得每日汇报发送人
	 */
	public void getReportUser(){
		setAttr("ReportUserList", service.getReportUserselect());
	}
	
	/**
	 * 日报月报查看详情
	 * @throws ParseException 
	 */
	public void riyueselect() throws ParseException{
		String time="";
		String dayAfter="";
		if(getPara("type","").equals("ri")){
			time=getPara("time","");
			Calendar c = Calendar.getInstance(); 
			Date date=null; 
			date = new SimpleDateFormat("yy-MM-dd").parse(time); 
			c.setTime(date); 
			int day=c.get(Calendar.DATE); 
			c.set(Calendar.DATE,day+1); 
			dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		}else if(getPara("type","").equals("yue")){
			time=getPara("time","")+"-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			Date  date = sdf.parse(time);//String-->Date
			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(date);
			lastDate.add(Calendar.MONTH,1);//减一个月
			lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
			dayAfter=sdf.format(lastDate.getTime());
		}
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		if(!getPara("type","").equals("")){
			scree.setStartTime(time);
			scree.setEndTime(dayAfter);
		}
		Page<Record> list=service.riyueselect(Integer.parseInt(getPara("page","1")),20,scree);
		list=orderSum(list);
		setAttr("refoundList",list);
		if(getPara("atr","").equals("")){
			if(getPara("type","").equals("ri")){
				setAttr("startTime",time);
				setAttr("endTime",dayAfter);
			}else if(getPara("type","").equals("yue")){
				setAttr("startTime",time);
				setAttr("endTime",dayAfter);
			}
			render("sunAnd.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 数据统计查询
	 */
	public void dataCount(){
		String firstDay="";
		String newDay="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
        //获取前月的第一天
        Calendar  cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        
        firstDay=getPara("statisticsStartTime",format.format(cal_1.getTime()));
        newDay=getPara("statisticsEndTime",format.format(new Date()));
        
        setAttr("dataCount", service.dataCount(firstDay,newDay));
        
		renderJson();
	}
	
	/*资金流水详情*/
	public void flowDetails(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		Page<Record> list=service.flowDetails(Integer.parseInt(getPara("page","1")),20,scree,Integer.parseInt(getPara("userId")),getPara("type"));
		list=orderSum(list);
		Integer listCount=service.flowDetailCount(scree,Integer.parseInt(getPara("userId")),getPara("type"));
		pageDo pagedo=this.storage(listCount);
		setAttr("refoundListChild",list);
		setAttr("pagedoChild", pagedo);
		
		renderJson();

	}
	
	/*参数存储*/
	public pageDo storage(Integer listCount){
		pageDo pagedo=new pageDo();
		pagedo.setPageNumber(Integer.parseInt(getPara("page","1")));
		pagedo.setAllPageNumber(listCount);
		pagedo.setNumberPages((listCount%20==0?listCount/20:listCount/20+1));
		return pagedo;
	}
	
	public ScreenDo storageAlso(String bankOrderNo,String orderNo,String orderType,String startTime,String endTime,String nickName,String userType){

		if(startTime!=null && endTime!=null ){
			if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
				endTime=getSpecifiedDayAfter(startTime);
			}
		}


		ScreenDo scree=new ScreenDo();
		scree.setCanceOrderNo(bankOrderNo);
		scree.setOrderNo(orderNo);
		scree.setOrderType(orderType);
		scree.setStartTime(startTime);
		scree.setEndTime(endTime);
		scree.setNickName(nickName);
		scree.setUserType(userType);
		return scree;
	}
	
	public Page<Record> orderSum(Page<Record> list){
		int i=1;
		for(Record  x : list.getList()){
			x.set("sumId",i);
			i++;
		}
		
		return list;
	}
	
	/*导出Excel*/
	public void toExcel(){
		ScreenDo scree=this.storageAlso(getPara("bankOrderNo",""),getPara("orderNo",""),getPara("orderType",""),getPara("startTime",""),getPara("endTime",""),getPara("nickName",""),getPara("userType",""));
		ExcelUtils e = new ExcelUtils();
		String path ="";
		if(getPara("dataType","").equals("zhifubao")){
			path=e.excelThirdPay(scree,"zhifubao");
		}else if(getPara("dataType","").equals("weixin")){
			path=e.excelThirdPay(scree,"weixin");
		}else if(getPara("dataType","").equals("card")){
			path=e.excelThirdPay(scree,"card");
		}else if(getPara("dataType","").equals("amount")){
			path=e.excelBalance(scree,"amount");
		}else if(getPara("dataType","").equals("techService")){
			path=e.techServiceDetail(scree);
		}else if(getPara("dataType","").equals("finance")){
			path=e.excelFinance(scree);
		}else if(getPara("dataType","").equals("voucher")){
			path=e.voucherDetailExcel(scree);
		}else if(getPara("dataType","").equals("refound")){
			path=e.refoundDetailExcel(scree);
		}else if(getPara("dataType","").equals("shop")){
			path=e.shopDetailExcel(scree);
		}else if(getPara("dataType","").equals("user")){
			path=e.userDetailExcel(scree);
		}else if(getPara("dataType","").equals("user")){
			path=e.userDetailExcel(scree);
		}else if(getPara("dataType","").equals("shopInfo")){
			path=e.userDetailExcel(scree,getPara("userId"),"shop");
		}else if(getPara("dataType","").equals("Buyer")){
			path=e.userDetailExcel(scree,getPara("userId"),"Buyer");
		}else if(getPara("dataType","").equals("riyue")){
			path=e.riyueselectExcel(scree);
		}else if(getPara("dataType","").equals("actual")){
			path=e.actualDetailExcel(scree,getPara("activeType"),getPara("allcount"));
		}else if(getPara("dataType","").equals("actual")){
			path=e.actualDetailExcel(scree,getPara("activeType"),getPara("allcount"));
		}else if(getPara("dataType","").equals("advance")){
			path=e.advanceDetailExcel(scree,getPara("activeType"),getPara("allcount"));
		}
		
		renderFile(new File(path));
	}

	/**
	 * 获得指定日期的后一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day+1);

		String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
}
