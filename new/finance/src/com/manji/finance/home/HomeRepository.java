package com.manji.finance.home;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.enums.AccountTypeEnum;
import com.manji.finance.home.enums.UrlState;
import com.manji.finance.home.model.InformationDo;
import com.manji.finance.home.model.ReportFormDo;
import com.manji.finance.home.model.orderDo;
import com.manji.finance.system.UserSmsLogDO;
import com.manji.finance.utils.DButils;
import com.manji.finance.utils.InterfaceUtil;
import com.manji.finance.utils.SmsLogsUtils;
import net.sf.json.JSONObject;

/**
 * 首页
 * @author Administrator
 *
 */
public class HomeRepository extends DButils{
	String firstDay="";
	String newDay="";
	
	/*信息总汇前四个*/
	public List<InformationDo> dataCountFront(String firstDay,String newDay){
		/*定义条件*/
		List<InformationDo> recordPay=new ArrayList<InformationDo>();
		String payment_id="";
		
		/*支付宝支付*/
		InformationDo aliPayInfo=new InformationDo();
		payment_id="13";
		Double aliPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(transaction_money),0) ye from dt_order_online_pay where status=1 and payment_id in ("+payment_id+") and notify_time BETWEEN ? and ?",firstDay,newDay).toString());
		aliPayInfo.setAllmoney(aliPay);
		aliPayInfo.setName((AccountTypeEnum.ZFB_MODEL_PAY.getMessage()));
		aliPayInfo.setUrl(UrlState.zhifubao.getMessage());
		recordPay.add(aliPayInfo);
		
		/*微信支付*/
		payment_id="8,12";
		InformationDo weChatInfo=new InformationDo();
		Double weChat=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(transaction_money),0) ye from dt_order_online_pay where status=1 and payment_id in ("+payment_id+") and notify_time BETWEEN ? and ?",firstDay,newDay).toString());
		weChatInfo.setAllmoney(weChat);
		weChatInfo.setName(AccountTypeEnum.WX_GZH_PAY.getMessage());
		weChatInfo.setUrl(UrlState.weixin.getMessage());
		recordPay.add(weChatInfo);
		
		/*银行卡支付*/
		payment_id="11";
		InformationDo bankPayInfo=new InformationDo();
		Double bankPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(transaction_money),0) ye from dt_order_online_pay where status=1 and payment_id in ("+payment_id+") and notify_time BETWEEN ? and ?",firstDay,newDay).toString());
		bankPayInfo.setAllmoney(bankPay);
		bankPayInfo.setName(AccountTypeEnum.YHK_LFT.getMessage());
		bankPayInfo.setUrl(UrlState.card.getMessage());
		recordPay.add(bankPayInfo);
		
		/*余额支付*/
		InformationDo balanceInfo=new InformationDo();
		Double balance=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum((a.sum1-b.sum2)),0) ye from (select sum(real_amount) sum1 from dt_orders where status=3 and complete_time BETWEEN '"+firstDay+"' and '"+newDay+"') a,(select sum(transaction_money)sum2 from dt_order_online_pay where status=1 and notify_time BETWEEN '"+firstDay+"' and '"+newDay+"') b").toString());
		balanceInfo.setAllmoney(balance);
		balanceInfo.setName(AccountTypeEnum.BALANCE_PAY.getMessage());
		balanceInfo.setUrl(UrlState.amount.getMessage());
		recordPay.add(balanceInfo);
		
		
		return recordPay;
	}
	
	/*信息总汇后四个*/
	public List<InformationDo> dataCountBack(String firstDay,String newDay){
		List<InformationDo> recordPay=new ArrayList<InformationDo>();
			
		/*技术服务费*/
		InformationDo technicalInfo=new InformationDo();
		Double technical=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(cast(round((o.order_amount*b.user_percentage),2) as numeric(38,2))),0) amount from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index left join dt_users u on u.id=o.user_id left join dt_users g on g.id=o.shop_user_id left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_payment p on p.id=o.payment_id where o.status=3 and b.user_role_type='shop' and b.status=2 and o.complete_time BETWEEN ? and ?",firstDay,newDay).toString());
		technicalInfo.setAllmoney(technical);
		technicalInfo.setName(AccountTypeEnum.TECHNICAL_SERVICE_FEE.getMessage());
		technicalInfo.setUrl(UrlState.techService.getMessage());
		recordPay.add(technicalInfo);
		
		/*金融手续费汇总*/
		InformationDo withdrawalsInfo=new InformationDo();
		Double withdrawalsPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(commission),0) amount from dt_user_withdrawals where status=5 and complete_time BETWEEN ? and ?",firstDay,newDay).toString());
		withdrawalsInfo.setAllmoney(withdrawalsPay);
		withdrawalsInfo.setName(AccountTypeEnum.WITHDRAWALS_FEE.getMessage());
		withdrawalsInfo.setUrl(UrlState.finance.getMessage());
		recordPay.add(withdrawalsInfo);
		
		/*保证金汇总*/
		InformationDo marginInfo=new InformationDo();
		marginInfo.setAllmoney(0.00);
		marginInfo.setName(AccountTypeEnum.MARGIN.getMessage());
		marginInfo.setUrl(UrlState.bond.getMessage());
		recordPay.add(marginInfo);
		
		/*平台使用服务费汇总*/
		InformationDo platFormInfo=new InformationDo();
		platFormInfo.setAllmoney(0.00);
		platFormInfo.setName(AccountTypeEnum.PLATFORM.getMessage());
		platFormInfo.setUrl(UrlState.pltService.getMessage());
		recordPay.add(platFormInfo);
		
		
		return recordPay;
	}
	
	/*信息总汇(支出)*/
	public List<InformationDo> dataCountExpenditure(String firstDay,String newDay){
		List<InformationDo> recordPay=new ArrayList<InformationDo>();
		
		/*满意卷支出*/
        
		InformationDo voucherlInfo=new InformationDo();
		Double voucherPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(voucher),0) from dt_orders b where b.status=3 and complete_time BETWEEN ? and ?",firstDay,newDay).toString());
		voucherlInfo.setAllmoney(voucherPay);
		voucherlInfo.setName(AccountTypeEnum.VOUCHER.getMessage());
		voucherlInfo.setUrl(UrlState.voucher.getMessage());
		recordPay.add(voucherlInfo);
		
		/*售后退款汇总*/
		
		InformationDo shouhouInfo=new InformationDo();
		Double shouhouPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(real_back_amount),0) from dt_order_backinfo b where b.status=3 and update_time BETWEEN ? and ?",firstDay,newDay).toString());
		shouhouInfo.setAllmoney(shouhouPay);
		shouhouInfo.setName(AccountTypeEnum.SHOUHOU.getMessage());
		shouhouInfo.setUrl(UrlState.refound.getMessage());
		recordPay.add(shouhouInfo);
		
		return recordPay;
	}
	
	/*信息总汇(支出)*/
	public List<InformationDo> dataCountBalance(String firstDay,String newDay){
		List<InformationDo> recordPay=new ArrayList<InformationDo>();
		
		/*商家余额汇总*/
		InformationDo shopInfo=new InformationDo();
		Double shopPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(amount),0) from dt_user_accountinfo b where b.state=1 and role_type='shop' and update_time BETWEEN ? and ?",firstDay,newDay).toString());
		shopInfo.setAllmoney(shopPay);
		shopInfo.setName(AccountTypeEnum.SHOP_BALANCE_PAY.getMessage());
		shopInfo.setUrl(UrlState.shop.getMessage());
		recordPay.add(shopInfo);
		
		/*用户余额汇总*/
		InformationDo userInfo=new InformationDo();
		Double userPay=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(amount),0)  from dt_user_accountinfo b where b.state=1 and role_type='Buyer' and update_time BETWEEN ? and ?",firstDay,newDay).toString());
		userInfo.setAllmoney(userPay);
		userInfo.setName(AccountTypeEnum.USER_BALANCE_PAY.getMessage());
		userInfo.setUrl(UrlState.user.getMessage());
		recordPay.add(userInfo);
		
		return recordPay;
	}

	/*信息总汇(平台活动支出汇总)*/
	public List<InformationDo> dataCountplatformActivities(String firstDay,String newDay){
		List<InformationDo> recordPay=new ArrayList<InformationDo>();

		/*平台活动支出汇总*/
		InformationDo advanceInfo=new InformationDo();
		advanceInfo.setAllmoney(0);
		advanceInfo.setName(AccountTypeEnum.ADVANCE.getMessage());
		advanceInfo.setUrl(UrlState.advance.getMessage());
		recordPay.add(advanceInfo);


		InformationDo realInfo=new InformationDo();
		realInfo.setAllmoney(0);
		realInfo.setName(AccountTypeEnum.REAL.getMessage());
		realInfo.setUrl(UrlState.actual.getMessage());
		recordPay.add(realInfo);

		try{
			TreeMap<String, String> map = new TreeMap<String, String>();

			String resultData="";
			try {
				resultData = InterfaceUtil.IndexGetAPI(InterfaceUtil.INDEXATURL+"/api/CouponRecord/GetPlatformExpenditureAmount", map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultData);
				if ("1".equals(jsonObject.getString("Code"))) {
					Map<String, Object> mapJsonObjectStrval1 = JSONObject.fromObject(jsonObject.getString("Data"));
					advanceInfo.setAllmoney(Double.parseDouble(mapJsonObjectStrval1.get("advance_expenditure").toString()));
					realInfo.setAllmoney(Double.parseDouble(mapJsonObjectStrval1.get("real_expenditure").toString()));
					System.out.println("成功"+resultData);
				} else {
					System.out.println("失败"+resultData);
				}

			} catch (IOException e) {
				System.out.println("系统错误"+resultData);
				e.printStackTrace();
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return recordPay;
	}
	
	/*日报表查询*/
	public ReportFormDo ReportFormSelect(String firstDay){
		ReportFormDo reportForm=new ReportFormDo();
		
		try{
            /*资金流入*/
	            /*1.按时间查询所有支付订单*/
	            Double ordersNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(real_amount),0) from dt_orders where status=3 and DATEDIFF(day,complete_time,?)=0",firstDay).toString());
	            
	            /*2.按时间查询手续费*/
	            Double shouXuFeiNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(commission),0) from dt_user_withdrawals where status='5' and DATEDIFF(day,complete_time,?) = 0",firstDay).toString());
	            
	            /*3.技术服务费*/
	            Double jiShuNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(cast(round((o.order_amount*b.user_percentage),2) as numeric(38,2))),0) amount from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index left join dt_users u on u.id=o.user_id left join dt_users g on g.id=o.shop_user_id left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_payment p on p.id=o.payment_id where o.status=3 and b.user_role_type='shop' and b.status=2 and DATEDIFF(day,o.complete_time,?) = 0",firstDay).toString());
    
	        /*资金流出*/
	            /*1.满意卷*/
		        Double voucherNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(voucher),0) from dt_orders where status=3 and DATEDIFF(day,complete_time,?)=0",firstDay).toString());

	            /*2.提现金额*/
		        Double totalMoneyNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(total_money),0) from dt_user_withdrawals where status='5' and DATEDIFF(day,complete_time,?) = 0",firstDay).toString());

	            /*3.售后退款*/
		        Double realBackAmountNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(real_back_amount),0) from dt_order_backinfo where status='3' and DATEDIFF(day,update_time,?) = 0",firstDay).toString());
		        
		        reportForm.setCapitalLiuRu(ordersNum+shouXuFeiNum+jiShuNum);
		        reportForm.setCapitalLiuChu(voucherNum+totalMoneyNum+realBackAmountNum);
		        reportForm.setBeginningBalance(reportForm.getCapitalLiuRu()-reportForm.getCapitalLiuChu());
		        reportForm.setSelectTime(firstDay);
		        
		        BigDecimal d = new BigDecimal(reportForm.getCapitalLiuRu());  
		        reportForm.setCapitalLiuRu(d.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		        
		        BigDecimal g = new BigDecimal(reportForm.getCapitalLiuChu());  
		        reportForm.setCapitalLiuChu(g.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		        
		        BigDecimal h = new BigDecimal(reportForm.getBeginningBalance());  
		        reportForm.setBeginningBalance(h.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		        
		}catch(Exception e){
            e.printStackTrace();
        }
		
		return reportForm;
	}
	
	/*月报表查询*/
	public ReportFormDo MonthlyReportFormSelect(String firstDay,String type){
		ReportFormDo reportForm=new ReportFormDo();
        /*获取时间的第一天*/
        /*时间的第一天*/
        String fristTime="";
        /*初期结余*/
        Double cqjy=0.00;
        /*时间*/
        String seletime="";
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date=sim.parse(firstDay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(!type.equals("")){
            if(c.get(Calendar.MONTH)+1==1){
                fristTime=c.get(Calendar.YEAR)-1+"-12-1";
                seletime=c.get(Calendar.YEAR)-1+"-12";
            }else{
                fristTime=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH))+"-1";
                seletime=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH));
            }
        }else{
            fristTime=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH) + 1)+"-1";
            seletime=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH) + 1);
        }
        List<Record> riqi=mysql.find("select stage_balance from t_report where month = ?",seletime);
        if(riqi.size()!=0){
        	cqjy=Double.parseDouble((riqi.get(0).get("stage_balance")).toString());
        }else{
        	cqjy=0.00;
        }
        
        
    /*资金流入*/
        /*1.按时间查询所有支付订单*/
        Double ordersNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(real_amount),0) from dt_orders where status=3 and complete_time BETWEEN ? AND ?",fristTime,firstDay).toString());
        
        /*2.按时间查询手续费*/
        Double shouXuFeiNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(commission),0) from dt_user_withdrawals where status='5' and complete_time BETWEEN ? AND ?",fristTime,firstDay).toString());
        
        /*3.技术服务费*/
        Double jiShuNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(sum(cast(round((o.order_amount*b.user_percentage),2) as numeric(38,2))),0) amount from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index left join dt_users u on u.id=o.user_id left join dt_users g on g.id=o.shop_user_id left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_payment p on p.id=o.payment_id where o.status=3 and b.user_role_type='shop' and b.status=2 and o.complete_time BETWEEN ? and ?",fristTime,firstDay).toString());;

    /*资金流出*/
        /*1.满意卷*/
        Double voucherNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(voucher),0) from dt_orders where status=3 and complete_time BETWEEN ? AND ?",fristTime,firstDay).toString());

        /*2.提现金额*/
        Double totalMoneyNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(total_money),0) from dt_user_withdrawals where status='5' and complete_time BETWEEN ? AND ?",fristTime,firstDay).toString());

        /*3.售后退款*/
        Double realBackAmountNum=Double.parseDouble(sqlserver.queryFirst("select COALESCE(SUM(real_back_amount),0) from dt_order_backinfo where status='3' and update_time BETWEEN ? AND ?",fristTime,firstDay).toString());
        
        
        reportForm.setCapitalLiuRu(ordersNum+shouXuFeiNum+jiShuNum);
        reportForm.setCapitalLiuChu(voucherNum+totalMoneyNum+realBackAmountNum);
        reportForm.setOpeningBalance(cqjy);
        reportForm.setBeginningBalance(cqjy-reportForm.getCapitalLiuChu()+reportForm.getCapitalLiuRu());
        
        BigDecimal b = new BigDecimal(reportForm.getCapitalLiuRu());  
        reportForm.setCapitalLiuRu(b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        
        BigDecimal f = new BigDecimal(reportForm.getCapitalLiuChu());  
        reportForm.setCapitalLiuChu(f.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        
        BigDecimal d = new BigDecimal(reportForm.getBeginningBalance());  
        reportForm.setBeginningBalance(d.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        
        BigDecimal g = new BigDecimal(reportForm.getOpeningBalance());  
        reportForm.setOpeningBalance(g.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        
        reportForm.setSelectTime(seletime);
        
		return reportForm;
	}
	
	
	/*数据总汇*/
	
	public List<Record> dataCount(String firstDay,String newDay){
		List<Record> statisticsList=sqlserver.find("select order_type,sum(ISNULL(real_amount,0)) amountMoney from dt_orders where order_type in ('good','book_food','book_room') and status=3 and complete_time BETWEEN ? and ? group by order_type",firstDay,newDay);
		Double allmoney=0.00;
        DecimalFormat df = new DecimalFormat("#.####");
        for(Record list : statisticsList){
        	allmoney+=Double.parseDouble(list.get("amountMoney").toString());
        }
        if(allmoney!=0){
        	 for(Record list1 : statisticsList){
             	list1.set("percentage",df.format((Double.parseDouble(list1.get("amountMoney").toString())/allmoney)));
             }
        }
		return statisticsList;
	}
	
	/*汇报信息订单*/
	public orderDo SendDaylyOrder(String firstDay){
		List<Record> allOrderList=sqlserver.find("select count(1) allOrderNum,COALESCE(SUM(order_amount),0) allOrderMoney from dt_orders where DATEDIFF(day,complete_time,?)=0",firstDay);
		List<Record> successOrderList=sqlserver.find("select count(1) successOrderNum,COALESCE(SUM(order_amount),0) successOrderMoney from dt_orders where status=3 and DATEDIFF(day,complete_time,?)=0",firstDay);
		orderDo order=new orderDo();
		
		order.setAllOrderNum(Integer.parseInt(allOrderList.get(0).get("allOrderNum").toString()));
		order.setAllOrderMoney(Double.parseDouble(allOrderList.get(0).get("allOrderMoney").toString()));
		
		order.setSuccessOrderNum(Integer.parseInt(successOrderList.get(0).get("successOrderNum").toString()));
		order.setSuccessOrderMoney(Double.parseDouble(successOrderList.get(0).get("successOrderMoney").toString()));
		
		return order;
	}
	
	/*查询发送汇报人*/
	public List<Record> getReportUserselect(){
		List<Record> ReportUserList=mysql.find("select USER_ID,USER_NAME,MOBILE from t_systempush");
		return ReportUserList;
	}
	
	/*发送汇报*/
	public String sendReport(List<UserSmsLogDO> list){
		String stu="";
		SmsLogsUtils sms=new SmsLogsUtils();
		List<Record> submitInfo=getReportUserselect();
		if(sms.sendMessage(list)>0){
			for(Record x:submitInfo){
				String sql = "insert into t_submit_info(USER_ID,USER_NAME,MOBILE,SUBMIT_TIME,SUBMIT_INFO) values (?,?,?,?,?)";
				mysql.update(sql,x.get("USER_ID"),x.get("USER_NAME"),x.get("MOBILE"),new Date(),list.get(0).getContent());
			}
			stu="SUCCESS";
		}else{
			stu="ERROR";
		}
		return stu;
	}
	
	/*定时器（月报表）*/
	public String timerMonthlyReport(ReportFormDo report){
		String stu="";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
        Calendar c = Calendar.getInstance(); //获得当前时间
        c.add(Calendar.MONTH, -1); //减一,就是上一个月
        
        SimpleDateFormat sdfalso = new SimpleDateFormat("yyyy-M");
        Calendar calso = Calendar.getInstance(); //获得当前时间
        System.out.println(sdfalso.format(calso.getTime()));
        
        int monthUpdate=mysql.update("update t_report set final_balance=? where month=?",report.getBeginningBalance(),sdf.format(c.getTime()));
        if(monthUpdate>0){
        	int monthInser=mysql.update("INSERT into t_report(month,stage_balance,final_balance,storage_time) VALUES (?,?,?,?)",sdfalso.format(calso.getTime()),report.getBeginningBalance(),null,df.format(new Date()));
        	if(monthInser>0){
        		stu="SUCCESS";
        	}else{
        		stu="ERROR";
        	}
        }else{
        	stu="ERROR";
        } 
   
		return stu;
	}	
}
