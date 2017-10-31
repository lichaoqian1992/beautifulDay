package com.manji.advert.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.Advertise;
import com.manji.advert.model.Logs;
import com.manji.advert.model.Result;
import com.manji.advert.model.param.AdvertiseParam;
import com.manji.advert.service.AdvertiseService;
import com.manji.advert.service.LogsService;
import com.manji.advert.utils.DatesUtils;

/**
 * 广告管理
 * 
 * @author Administrator
 *
 */
@Before(LoginInterceptor.class)
public class AdvertiseController extends Controller {

	private LogsService logsService = new LogsService();

	private AdvertiseService service = new AdvertiseService();

	/**
	 * 广告首页
	 * 
	 * @param
	 * 
	 * 
	 */
	public void index() {

		
		render("advertise.html");
	}

	
	
	public void query(){
		
		AdvertiseParam params =getBean(AdvertiseParam.class,"");

		//分页查询全部数据
		Page<Record> page =service.getAdvertisePage(params);
		//通过图片名称查询图片地址
		for (int i=0;i<page.getList().size();i++) {
			Record file=service.findFileByFileName(page.getList().get(i).getStr("ad_pic"));
			//存储图片路径
			if (file==null){
				page.getList().get(i).set("pic_url","");
			}else{
				page.getList().get(i).set("pic_url",file.getStr("file_address"));
			}

		}

		renderJson(page);
		
	}
	
	/**
	 * 跳转新增广告页面
	 */
	public void add() {

		render("advertise_add.html");
	}

	/**
	 * 新增广告接口
	 */
	public void insert() {
		Advertise parm=getBean(Advertise.class,"");
		//创建广告
		service.insertAdvertise(parm);
		//创建点击次数记录表
		service.insertCount();
		//获取用户
		Record user=getSessionAttr("user");
		//日志
		Record max=service.maxAdvertse();
		Logs logs=new Logs();
		logs.setUserName(user.getStr("user_name"));
		logs.setBusinessName("广告管理");
		logs.setBusinessId(max.getInt("id").toString());
		logs.setContent("添加广告");
		logs.setCreateTime(DatesUtils.time());
		logs.setOperationType("添加");
		logsService.saveLogs(logs);

		render("advertise_add.html");
	}

	/**
	 * 查看广告详情
	 */
	public void change() {
		String id=getPara("id");
		//查询广告
		Record record=service.getAdvertiseByid(id);
		//查询图片
//		Record pic=service.findFileByFileName(record.getStr("ad_pic"));
//		record.set("pic_url",pic.getStr("file_address"));
		//查询点击次数
		Record count=service.findcountByad_id(record.getInt("id"));
		record.set("count",count.getInt("ad_click"));
		//查询广告位
		Record place=service.findPlaceById(record.getInt("ad_place"));
		record.set("pl_title",place.getStr("pl_title"));
		//查询终端
		Record channel=service.findChannelByid(place.getInt("pl_channel"));
		record.set("port",channel.getStr("port"));
		renderJson(record);
	}



	/**
	 * 修改广告状态
	 */
	public void updateState() {
		String id=getPara("id");
		String state=getPara("state");
		int i=service.updateAdvertiseState(id,state);

		//获取用户
		Record user=getSessionAttr("user");
		//日志
		Logs logs=new Logs();
		logs.setUserName(user.getStr("user_name"));
		logs.setBusinessName("广告管理");
		logs.setBusinessId(id);
		logs.setContent("更新广告状态");
		logs.setCreateTime(DatesUtils.time());
		logs.setOperationType("更新");
		logsService.saveLogs(logs);

		renderJson(i);
	}


	/**
	 * 进入修改广告接口
	 */
	public void update() {
		String id=getPara("id");
		//查询广告
		Record record=service.getAdvertiseByid(id);
		//查询城市
		List<Record> codeNameList=service.codename(record.getStr("ad_code"));
		record.set("codeNameList",codeNameList);
		//查询广告位
		Record place=service.findPlaceById(record.getInt("ad_place"));
		record.set("pl",place);

		//查询终端
		Record channel=service.findChannelByid(place.getInt("pl_channel"));
		record.set("port",channel.getStr("port"));
		record.set("ch_title",channel.getStr("ch_title"));
		record.set("channel_id",channel.getInt("id"));
		renderJson(record);
	}

	/**
	 * 修改广告
	 */
	public void updAdds(){
		Advertise advertise=getBean(Advertise.class,"");
		service.updateAdvertiseState(advertise);

		//获取用户
		Record user=getSessionAttr("user");
		//日志
		Logs logs=new Logs();
		logs.setUserName(user.getStr("user_name"));
		logs.setBusinessName("广告管理");
		logs.setBusinessId(advertise.getId());
		logs.setContent("更新广告");
		logs.setCreateTime(DatesUtils.time());
		logs.setOperationType("更新");
		logsService.saveLogs(logs);

		render("advertise.html");
	}


	/**
	 * 查询对应端口下的频道
	 */
	public void findchannel(){
		String port=getPara("port");
		List<Record> recordList=service.findchannel(port);
		renderJson(recordList);
	}

	/**
	 *通过频道查询对应广告位
	 */
	public void findplace(){
		String channelid=getPara("channelid");
		List<Record> recordList=service.findplace(channelid);
		renderJson(recordList);
	}

	/**
	 * 通过图片名称模糊搜索图片
	 */
	public void findfile(){
		String filename=getPara("filename");
		List<Record> recordList=service.findfile(filename);
		renderJson(recordList);
	}

	
	public void firstRegion() {

		List<Record> first = service.findFirstRegion();

		Result r = new Result();

		if (first.size() == 0) {
			r.setCode("0001");
			r.setData("查无数据");
		} else {
			r.setCode("0000");
			r.setData(first);
		}

		renderJson(r);

	}

	public void secondRegion() {

		String provinceCode = getPara("code");
		List<Record> second = service.findSecodRegion(provinceCode);

		Result r = new Result();

		if (second.size() == 0) {
			r.setCode("0001");
			r.setData("查无数据");
		} else {
			r.setCode("0000");
			r.setData(second);
		}

		renderJson(r);
	}

	public void thirdRegion() {
		String cityCode = getPara("code");
		List<Record> third = service.findThirdRegion(cityCode);

		Result r = new Result();

		if (third.size() == 0) {
			r.setCode("0001");
			r.setData("查无数据");
		} else {
			r.setCode("0000");
			r.setData(third);
		}

		renderJson(r);

	}
	
	
	
	
	
	
	
	
	
	

}
