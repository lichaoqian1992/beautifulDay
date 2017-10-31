package com.manji.elastic.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.elastic.model.Create;
import com.manji.elastic.model.ESAddress;
import com.manji.elastic.servie.FileService;
import com.manji.elastic.utils.WriteFileUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileController extends Controller{

	private FileService service =new FileService();
	
	
	public void index(){
		
		
		
		renderText("sb");
		
	}
	
	
	public void goodsGenerate(){
		
		
		
		
		
		List<Record> goods =service.generateGoods();
		
//		System.out.println(JSONObject.fromObject(goods).toString());
		
		renderText(JSONArray.fromObject(goods).toString());
		
		WriteFileUtils.writeFile(goods);
		
		renderText("Done");
	}
	
	
	

		
		
		
		
	}
	
	
	
	
	

