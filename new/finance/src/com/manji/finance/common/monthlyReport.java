package com.manji.finance.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jfinal.plugin.cron4j.ITask;
import com.manji.finance.home.HomeService;
import com.manji.finance.home.model.ReportFormDo;

public class monthlyReport implements ITask{
	@Override
	public void run() {
		HomeService service =new HomeService();
        String firstDay="";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ReportFormDo report=new ReportFormDo();
        //获取前月的第一天
        Calendar  cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        firstDay=format.format(cal_1.getTime());
        report=service.MonthlyReportFormSelect(firstDay,"true");
        
        String stu=service.timerMonthlyReport(report);
        if(stu.equals("SUCCESS")){
            System.out.println(report.getSelectTime()+"月报期末结余存储存成功！");
        }else{
            System.out.println(report.getSelectTime()+"月报表期末结余储存失败！");
        }
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
