package com.manji.adds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.adds.model.AddsMsg;
import com.manji.adds.model.Place;
import com.manji.adds.service.PlaService;
import com.manji.adds.utils.JsonUtils;

@Controller
public class PlaceController {

	@Autowired
	private PlaService service;
	
	
	/**
	 * 跳转广告位首页
	 * @param req
	 * @return
	 */
	@RequestMapping("/toPlace")
	public String toPlace(HttpServletRequest req){
		
		return "place";
	}
	
	
	/**
	 * 跳转广告位新增
	 * @param req
	 * @return
	 */
	@RequestMapping("/toAddPlace")
	public String toAddPlace(HttpServletRequest req){
		
		return "place_add";
	}
	/**
	 * 跳转修改广告位页面
	 * @param pl_id
	 * @param channel
	 * @param req
	 * @return
	 */
	@RequestMapping("/toUpdPlace")
	public String toUpdPlace(Place place,HttpServletRequest req){
		
		Place oldInfo =service.queryPlace(place);
		
		req.setAttribute("placeInfo", oldInfo);
		
		return "place_upd";
	}
	
	@RequestMapping(value = "/addPlace", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String addPlace(HttpServletRequest req ,Place place){
		
		
		service.addPlace(place);
		
		
		
		
		
		return "place";
	}
	
	@RequestMapping(value = "/updPlace", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPlace(Place place, HttpServletRequest req){
		
		AddsMsg msg =new AddsMsg();
		if(service.updPlace(place)){
			msg.setErrCode("0");
			msg.setResult("广告位修改成功");
		}else{
			msg.setErrCode("1");
			msg.setErrInfo("修改失败，请重试。");
			
		}
		
		return JsonUtils.getJson(msg);
		
	}
	
	@RequestMapping(value = "/queryPlaces", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPlaces(Place place){
		
		
		AddsMsg msg =new AddsMsg();
		
		List<Place> list =service.multiQuery(place);
		
		if(null !=list)
		{
			
			msg.setResult(list);
		}else
		{
			msg.setErrCode("1");
			msg.setErrInfo("查询失败，请重新尝试");
			
		}
		
		return JsonUtils.getJson(msg);
	}
	
	@RequestMapping(value = "/queryPlace", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPlace(Place queryMap){
		
		AddsMsg msg =new AddsMsg();

		Place place =service.queryPlace(queryMap);
		
		msg.setResult(place);
		return JsonUtils.getJson(msg);
	}
	
}
