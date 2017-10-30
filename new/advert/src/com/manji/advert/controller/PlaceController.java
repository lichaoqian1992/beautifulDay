package com.manji.advert.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.Logs;
import com.manji.advert.model.Result;
import com.manji.advert.model.param.PlaceParam;
import com.manji.advert.service.LogsService;
import com.manji.advert.service.PlaceService;

/**
 * APP广告位
 * 
 * @author Administrator
 *
 */
@Before(LoginInterceptor.class)
public class PlaceController extends Controller {

	private static Logger logger;

	private PlaceService service = new PlaceService();
	LogsService lservice = new LogsService();
	
	public PlaceController() {

		logger = Logger.getLogger(PlaceController.class);

	}

	/**
	 * 广告为管理首页
	 */
	public void index() {

		render("place.html");

	}

	public void query() {

		PlaceParam params = getBean(PlaceParam.class, "");
		int pageNum = getParaToInt("page");
		Page<Record> page = service.getPlacePage(params,pageNum);

		Result r = new Result();

		if (page.getTotalRow() == 0) {

			r.setCode("0001");
			r.setData("查无数据");
		} else {
			r.setCode("0000");
			r.setData(page);
		}

		renderJson(r);

	}

	/**
	 * 跳转新增广告位页面
	 */
	public void add() {

		render("place_add.html");
	}


	/**
	 * 跳转修改广告位页面
	 */
	public void change() {
		
		int id = Integer.valueOf(getPara("id"));
		
		Record record = service.getPlace(id);
		
		renderJson(record);
	}

	/**
	 * 修改广告位接口
	 */
	public void update() {
		
		PlaceParam p = new PlaceParam();
		p.setId(Integer.valueOf(getPara("id")));
		p.setChannel(Integer.valueOf(getPara("channel")));
		p.setSort(getPara("sort"));
		p.setTitle(getPara("title"));
		p.setMax(getPara("max"));
		p.setWpercen(getPara("wpercen"));
		p.setHpercen(getPara("hpercen"));
		p.setState(getPara("state"));
		p.setRemark(getPara("remark"));
		
		int s = service.updPlace(p);
		
		//记录登录日志
		Record r = getSessionAttr("user");
		String userName = r.get("user_name").toString();
		Logs logs = new Logs();
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logs.setUserName(userName);
		logs.setBusinessName("广告位管理");
		logs.setBusinessId(getPara("id"));
		logs.setOperationType("更新");
		logs.setContent(userName+"在"+d+"修改了频道信息");
		logs.setContent(userName+"在"+d+"修改了广告位信息");
		logs.setCreateTime(d);
		lservice.saveLogs(logs);
		
		renderJson(s);
	}

	/**
	 * 新增广告位接口
	 */
	
	public void insert(){
		Record r = new Record();
		r.set("pl_channel", getPara("channel"));
		r.set("pl_sort", getPara("sort"));
		r.set("pl_title", getPara("title"));
		r.set("pl_max", getPara("max"));
		r.set("pl_wpercen", getPara("wpercen"));
		r.set("pl_hpercen", getPara("hpercen"));
		r.set("pl_state", getPara("state"));
		r.set("pl_remark", getPara("remark"));
		
		boolean b = service.addPlace(r);
		
		//记录登录日志
		Record u = getSessionAttr("user");
		String userName = u.get("user_name").toString();
		Logs logs = new Logs();
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logs.setUserName(userName);
		logs.setBusinessName("广告位管理");
		logs.setBusinessId(r.get("id").toString());
		logs.setOperationType("添加");
		logs.setContent(userName+"在"+d+"新增了广告位信息");
		logs.setCreateTime(d);
		lservice.saveLogs(logs);
		
		renderJson(b);
	}
}
