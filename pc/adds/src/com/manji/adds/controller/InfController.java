package com.manji.adds.controller;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manji.adds.service.InfService;
import com.manji.adds.utils.JsonUtils;

import net.sf.json.JSONArray;


@Controller
public class InfController {

	
	@Autowired
	private InfService service;
	

	
	@RequestMapping("/v1")
	public void  output(HttpServletRequest req, HttpServletResponse res){
		
		String channel =req.getParameter("channel");//请求渠道
		String type =req.getParameter("type");//请求端类型
		String region =req.getParameter("region");
		String output =req.getParameter("output");//输出方式
		
		if(null ==output){
			output="";
		}
		
		String msg ="";
		
		String regions =service.getRegions(region);
		
		List<Map> list =service.getOuterMap(channel,type,regions);
		
//		
//		switch(output){
//		case "url":
//			msg=addMap.get("url");
//			break;
//		case "xml":
//			msg=XmlUtils.MapToXml(addMap);
//			break;
//		default:
//			msg=JSONArray.fromObject(addsList).toString();
//			res.setContentType("text/html"); 
//			
//		}
		msg=JSONArray.fromObject(list).toString();
//		res.setContentType("text/html"); 
		
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/addCount")
	public void addCount(HttpServletRequest req){
		
		String ad_id=req.getParameter("ad_id");
		System.out.println(ad_id);
		if(null==ad_id){
			return;
		}
		
		service.addCount(ad_id);
		
		
	}
	
	
	@RequestMapping("/v2")
	public void appOutput(HttpServletRequest req, HttpServletResponse res){
		
		String channel =req.getParameter("channel");//请求渠道
		String typeEnum =req.getParameter("type");//请求端类型
		String region =req.getParameter("region");
		String output =req.getParameter("output");//输出方式
		
		
		String type =switchType(typeEnum);
		
		if(null ==output){
			output="";
		}
		
		String msg ="";
		
		if(null ==region||"".equals(region)){
			region="00";
		}
		
		String regions =service.getRegions(region);
		
		List<Map> list =service.getOuterMap(channel,type,regions);
		

		msg=JSONArray.fromObject(list).toString();
		
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static String switchType(String typeEnum){
		
		String type ="";
		
		switch(typeEnum){
		
		case "首页":
			type ="0";
			break;
		case "餐饮美食":
			type ="1";
			break;
		case "休闲娱乐":
			type ="2";
			break;
		case "酒店住宿":
			type ="3";
			break;
		case "旅游观光":
			type ="4";
			break;
		case "日用百货":
			type ="5";
			break;
		case "外卖服务":
			type ="6";
			break;
		case "农村电商":
			type ="7";
			break;
		case "海外购":
			type ="8";
			break;
		case "食品茶酒":
			type="9";
			break;
		case "农特产品":
			type="10";
			break;
		case "个护美妆":
			type="11";
			break;
		case "遥控玩具":
			type="12";
			break;
		case "运动户外":
			type="13";
			break;
		case "办公用品":
			type="14";
			break;
		case "珠宝首饰":
			type="15";
			break;
		default:
			type="0";
			break;
		}
		
		
		
		return type;
	}
	
	
	@RequestMapping("/v1.2")
	public void queryFloor(HttpServletRequest req,HttpServletResponse res){
		
		
		String channel =req.getParameter("channel");//请求渠道
		String floor =req.getParameter("floor");//请求端类型
		String region =req.getParameter("region");
		
		
		String type="";
		
		if(null==region ||"".equals(region)){
			region="00";
		}
		
		switch(floor){
		case "F1":
			type="2";
			break;
		case "F2":
			type="3";
			break;
		case "F3":
			type="4";
			break;
		case "F4":
			type="5";
			break;
		case "F5":
			type="6";
			break;
		case "F6":
			type="7";
			break;
		case "F7":
			type="8";
			break;
		
		default :
			type="2";
			break;
		}
		
		String msg ="";
		
		String regions =service.getRegions(region);
		
		List<Map> list =service.getOuterMap(channel,type,regions);
		

		msg=JSONArray.fromObject(list).toString();
		
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
