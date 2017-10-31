package com.manji.advert.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.ChannelData;
import com.manji.advert.model.PlaceData;
import com.manji.advert.model.Result;
import com.manji.advert.service.AppService;

import net.sf.json.JSONArray;

/**
 * APP接口
 * @author Administrator
 *
 */
public class AppController extends Controller{

	private AppService service =new AppService();
	
	public void index(){
		renderText("app");
	}
	
	
	public void channelInfo(){
		
		String loc =getPara("code");
		String name =getPara("channel");
		
		Result r =new Result();
		if(loc == null || "".equals(loc)){
			r.setCode("0001");
			r.setData("区域编号（code）参数不能为空");
			renderJson(r);
			return;
		}
		if(name==null ||"".equals(name)){
			
			
			r.setCode("0001");
			r.setData("频道（channel）参数不能为空");
			renderJson(r);
			return;
		}
		
		String location =service.getLocation(loc);
		if(location == null || "".equals(location)){
			r.setCode("0001");
			r.setData("该区域编号"+loc+"对应无数据，请检查");
			renderJson(r);
			return;
		}
		ChannelData cd =service.getChannelData(location,name);
		
		if(cd.getChannel() == null){
			r.setCode("0001");
			r.setData("频道信息不存在，请检查参数值是否正确");
			renderJson(r);
			return;
		}
		if(cd.getPlaces() == null || cd.getPlaces().size() == 0){
			r.setCode("0001");
			r.setData("广告信息不存在，请检查参数值是否正确");
			renderJson(r);
			return;
		}
		r.setCode("0000");
		r.setData(cd);
		
		renderJson(r);
		
		
		
	}
	
	public void clickBrowse(){

		/*储存点击浏览记录*/
		String id =getPara("id");
		service.setBrowseClick("click",id);

		Result r =new Result();
		r.setCode("0000");
		r.setData(new String("SUCCESS"));
		renderJson(r);
	}
	
	public void homepage(){
		
		String loc =getPara("code");
		
		String location =service.getLocation(loc);
		
		List<Record> channelList =service.getChannelList();
		
		List<ChannelData> channels =new ArrayList<ChannelData>();
		
		for(int i=0;i<channelList.size();i++){
			
			String name =channelList.get(i).get("ch_name");
			
			ChannelData cd =service.getChannelData(location,name);
			
			channels.add(cd);
			
		}
		
		Result r =new Result();
		
		r.setCode("0000");
		r.setData(channels);
		
		service.setBrowse("view",channels);
		
		renderJson(r);
		
		
	}
	
	
	
	
}
