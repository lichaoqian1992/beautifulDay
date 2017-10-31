package com.manji.elastic.controller;

import com.jfinal.core.Controller;
import com.manji.elastic.utils.ElasticClient;

public class IndexController extends Controller {

	public void index() {

		render("index.html");

	}

	
	
	
	public void send(){
		
		
		String urlStr =getPara("str");
		
		if(urlStr==null){
			urlStr="";
		}
		
		
		renderJson(ElasticClient.getMethod(urlStr));
	}
}
