package com.manji.adds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.adds.model.AddsMsg;
import com.manji.adds.model.Record;
import com.manji.adds.service.RecordService;
import com.manji.adds.utils.JsonUtils;

@Controller
public class RecordController {
	
	@Autowired
	private RecordService service;
	
	/**
	 * 查询首页展示信息
	 * @return
	 */
	@RequestMapping(value = "/firstPageRecords", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String firstPageRecords(){
		
		AddsMsg msg=new AddsMsg();
		List<Record> list =service.firstPageRecords();
		msg.setResult(list);
		
		return JsonUtils.getJson(msg);
	}
	
	
	
	/**
	 * 查询所有操作记录
	 * @return
	 */
	@RequestMapping(value = "/allRecords", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String allRecords(){
		
		
		AddsMsg msg=new AddsMsg();
		List<Record> list =service.allRecords();
		msg.setResult(list);
		
		return JsonUtils.getJson(msg);
	}
	
	
	
	/**
	 * 根据条件模糊查询操作记录
	 * @param rec
	 * @return
	 */
	@RequestMapping(value = "/queryRecords", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String queryRecords(Record rec){
		
		AddsMsg msg=new AddsMsg();
		List<Record> list =service.queryRecords(rec);
		msg.setResult(list);
		
		return JsonUtils.getJson(msg);
	}
			
	@RequestMapping("/toRecord")
	public String toRecord(HttpServletRequest req){
		
		
		return "record";
	}
	
			
			
}
