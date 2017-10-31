package com.manji.finance.home;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.InformationDo;
import com.manji.finance.home.model.ReportFormDo;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.home.model.orderDo;
import com.manji.finance.system.UserSmsLogDO;

public class HomeService {
	private HomeRepository repository =new HomeRepository();
	private PaymentDetailsRepository PaymentDetails =new PaymentDetailsRepository();
	
	/*信息总汇前四条*/
	public List<InformationDo> dataCountFront(String firstDay,String newDay){
		return repository.dataCountFront(firstDay,newDay);
	}
	
	/*信息总汇后四条*/
	public List<InformationDo> dataCountBack(String firstDay,String newDay){
		return repository.dataCountBack(firstDay,newDay);
	}
	
	/*信息总汇支出*/
	public List<InformationDo> dataCountExpenditure(String firstDay,String newDay){
		return repository.dataCountExpenditure(firstDay,newDay);
	}
	
	/*信息总汇余额*/
	public List<InformationDo> dataCountBalance(String firstDay,String newDay){
		return repository.dataCountBalance(firstDay,newDay);
	}

	/*平台活动支出汇总*/
	public List<InformationDo> dataCountplatformActivities(String firstDay,String newDay){
		return repository.dataCountplatformActivities(firstDay,newDay);
	}
	
	/*日报表查询*/
	public ReportFormDo ReportFormSelect(String firstDay){
		return repository.ReportFormSelect(firstDay);
	}
	
	/*月报表查询*/
	public ReportFormDo MonthlyReportFormSelect(String firstDay,String type){
		return repository.MonthlyReportFormSelect(firstDay,type);
	}
	
	/*数据统计*/
	public List<Record> dataCount(String firstDay,String newDay){
		return repository.dataCount(firstDay,newDay);
	}
	
	/*每日交易汇报(订单详情)*/
	public orderDo SendDaylyOrder(String firstDay){
		return repository.SendDaylyOrder(firstDay);
	}
	
	/*查询发送汇报人*/
	public List<Record> getReportUserselect(){
		return repository.getReportUserselect();
	}
	
	/*发送汇报*/
	public String sendReport(List<UserSmsLogDO> list){
		return repository.sendReport(list);
	}

	/*定时器（月报表）*/
	public String timerMonthlyReport(ReportFormDo report){
		return repository.timerMonthlyReport(report);
	}
	
	/**********************信息总汇详情******************************/
	
	/*售后退款*/
	public Page<Record> refoundDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.refoundDetail(pageNumber, pageSize,scree);
	}
	
	/*满意卷退款*/
	public Page<Record> voucherDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.voucherDetail(pageNumber, pageSize,scree);
	}
	
	/*金融手续费*/
	public Page<Record> financeDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.financeDetail(pageNumber, pageSize,scree);
	}

	/*用户账户余额*/
	public Page<Record> userDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.userDetail(pageNumber, pageSize,scree);
	}
	
	/*商家账户余额*/
	public Page<Record> shopDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.shopDetail(pageNumber, pageSize,scree);
	}

	/*微信*/
	public Page<Record> weixinDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.weixinDetail(pageNumber, pageSize,scree);
	}

	/*支付宝*/
	public Page<Record> zhifubaoDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.zhifubaoDetail(pageNumber, pageSize,scree);
	}
	
	/*银行卡*/
	public Page<Record> cardDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.cardDetail(pageNumber, pageSize,scree);
	}
	
	/*资金流水明细*/
	public Page<Record> flowDetails(int pageNumber, int pageSize,ScreenDo scree,int userID,String type){
		return PaymentDetails.flowDetails(pageNumber, pageSize,scree,userID,type);
	}
	
	/*资金流水明细总数*/
	public Integer flowDetailCount(ScreenDo scree,int userID,String type){
		return PaymentDetails.flowDetailCount(scree,userID,type);
	}
	
	/*技术服务费汇总*/
	public Page<Record> techServiceDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.techServiceDetail(pageNumber, pageSize,scree);
	}
	
	/*余额支付*/
	public Map<String, Object> amountDetail(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.amountDetail(pageNumber,pageSize,scree);
	}

	/*日报月报详情*/
	public Page<Record> riyueselect(int pageNumber, int pageSize,ScreenDo scree){
		return PaymentDetails.riyueselect(pageNumber,pageSize,scree);
	}

	/*已支出明细*/
	public Record advanceDetail(int pageNumber, int pageSize,ScreenDo scree,String activeType){
		return PaymentDetails.advanceDetail(pageNumber,pageSize,scree,activeType);
	}

	/*已支出明细*/
	public Record actualDetail(int pageNumber, int pageSize,ScreenDo scree,String activeType){
		return PaymentDetails.actualDetail(pageNumber,pageSize,scree,activeType);
	}
	
}
