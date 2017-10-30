package com.manji.advert.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.Logs;
import com.manji.advert.model.Result;
import com.manji.advert.model.param.ChannelParam;
import com.manji.advert.service.ChannelService;
import com.manji.advert.service.LogsService;

/**
 * APP渠道
 * 
 * @author Administrator
 *
 */
@Before(LoginInterceptor.class)
public class ChannelController extends Controller {

	private static Logger logger;
	
	public ChannelController() {

		logger = Logger.getLogger(ChannelController.class);

	}
	
	
	private ChannelService service =new ChannelService();
	LogsService lservice = new LogsService();
	
	/**
	 * channel频道管理首页
	 */
	public void index() {

		
		
		
		render("channel.html");
		
	}
	
	/**
	 * 查询频道
	 */
	public void query(){
		
		ChannelParam params =getBean(ChannelParam.class,"");
		
		int pageNum = getParaToInt("page");
		Page<Record> page =service.getChannelPage(params,pageNum);
		
		Result r =new Result();
		
		if(page.getTotalRow()==0){
			
			r.setCode("0001");
			r.setData("查无数据");
		}else{
			r.setCode("0000");
			r.setData(page);
		}
		
		renderJson(r);
		
		
	}

	
	/**
	 * 频道列表
	 */
	public void simpleList(){
		
		List<Record> list =service.getChannelList();
		
		Result r =new Result();
		
		if(list.size()==0){
			
			r.setCode("0001");
			r.setData("查无数据");
		}else{
			r.setCode("0000");
			r.setData(list);
		}
		
		renderJson(r);
		
	}

	
	/**
	 * 修改频道页面
	 */
	public void change() {
		
		int id = Integer.valueOf(getPara("id"));
		
		Record record = service.getChannelDetails(id);
		
		renderJson(record);
		
	}
	
	
	
	/**
	 * 保存修改
	 */
	public void update() {
		ChannelParam c = new ChannelParam();
		c.setId(Integer.valueOf(getPara("id")));
		c.setName(getPara("name"));
		c.setTitle(getPara("title"));
		c.setSort(getPara("sort"));
		c.setPort(getPara("port"));
		c.setLayer(getPara("layer"));
		c.setState(getPara("state"));
		c.setFlag(getPara("flag"));
		c.setMode(getPara("mode"));
		c.setFoot(getPara("foot"));
		c.setRemark(getPara("remark"));
		
		int s = service.updChannel(c);
		
		//记录登录日志
		Record r = getSessionAttr("user");
		String userName = r.get("user_name").toString();
		Logs logs = new Logs();
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logs.setUserName(userName);
		logs.setBusinessName("频道管理");
		logs.setBusinessId(getPara("id"));
		logs.setOperationType("更新");
		logs.setContent(userName+"在"+d+"修改了频道信息");
		logs.setCreateTime(d);
		lservice.saveLogs(logs);
		
		renderJson(s);
	}
	
	/**
	 * 跳转新增频道页面
	 */
	public void add() {

		
		render("channel_add.html");
		
		
	}

	/**
	 * 新增频道信息
	 */
	public void insert() {

		Record r = new Record();
		r.set("ch_name", getPara("name"));
		r.set("ch_title", getPara("title"));
		r.set("ch_sort", getPara("sort"));
		r.set("port", getPara("port"));
		r.set("ch_layer", getPara("layer"));
		r.set("ch_flag", getPara("flag"));
		r.set("ch_state", getPara("state"));
		r.set("ch_mode", getPara("mode"));
		r.set("ch_foot", getPara("foot"));
		r.set("ch_remark", getPara("remark"));
		
		
		boolean b = service.addChannel(r);
		
		//记录登录日志
		Record u = getSessionAttr("user");
		String userName = u.get("user_name").toString();
		Logs logs = new Logs();
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logs.setUserName(userName);
		logs.setBusinessName("频道管理");
		logs.setBusinessId(r.get("id").toString());
		logs.setOperationType("添加");
		logs.setContent(userName+"在"+d+"新增了频道信息");
		logs.setCreateTime(d);
		lservice.saveLogs(logs);
		
		renderJson(b);
	}

	
	
}
