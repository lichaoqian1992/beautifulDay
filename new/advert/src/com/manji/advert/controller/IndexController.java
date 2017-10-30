package com.manji.advert.controller;

import java.text.NumberFormat;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.service.IndexService;
import com.manji.advert.service.LogsService;

/**
 * 这个应该是图形化界面首页
 * @author Administrator
 *
 */
@Before(LoginInterceptor.class)
public class IndexController extends Controller{

	IndexService service = new IndexService();
	LogsService Logs = new LogsService();
	
	public IndexController(){
		
		
	}
	
	public void index(){
		browseClick();
		operaRecord();
		inputData();
		inputTime();
		render("main.html");
	}
	
	/**
	 * 总浏览量/总点击量
	 */
	public void browseClick(){
		Record record=service.browseClick();
		setAttr("browseClick", record.get("sumClick"));
		setAttr("browseView", record.get("sumView"));
	}
	
	/**
	 * 总录入/有效录入
	 */
	public void inputData(){
		Record record=service.inputData();
		setAttr("sumAll", record.get("sumAll"));
		setAttr("sumData", record.get("sumData"));
	}
	
	/**
	 * 录入时间段
	 */
	public void inputTime(){
		Record record=service.inputTime();
		
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2); 
		
		setAttr("YesterdayPercentage", nf.format(record.get("sumYesterdayDouble")));
		setAttr("MonthPercentage", nf.format(record.get("sumMonthDouble")));
	}
	
	/**
	 * 操作记录
	 */
	public void operaRecord(){
		Record user = getSessionAttr("user");
		String userName=user.get("user_name");
		setAttr("logsList",Logs.findLogs(userName));
	}
	
	/**
	 * 广告总浏览量
	 */
	public void staticsAll(){
		render("statics.html");
	}
	
	/**
	 * 广告总浏览量查询
	 */
	public void clickInfoAll(){
		String state=getPara("state","");
		String type=getPara("type","");
		String title=getPara("title","");
		String place=getPara("place","");
		String region=getPara("region","");
		int page=Integer.parseInt(getPara("page"));
		Page<Record> list=service.clickInfoAll(page,state,type,title,place,region);
		renderJson(list);
	}
}
