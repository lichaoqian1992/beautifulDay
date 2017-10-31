package com.manji.elastic.controller;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.manji.elastic.model.request.pc.PcArticleQuery;
import com.manji.elastic.servie.PcArticleService;

import net.sf.json.JSONObject;

public class PcArticleController extends Controller{

	private static Logger logger = Logger.getLogger(PcArticleController.class);
	
	private PcArticleService service =new PcArticleService();
	
	public void index(){
		
		renderText("pcarticlesb");
	}
	
	
	/**
	 * 综合商品查询
	 */
	public void queryArticle() {

		long t1 = System.currentTimeMillis();

		PcArticleQuery query = getBean(PcArticleQuery.class, "");

		String returnJson = service.queryArticle(query);

		renderText(returnJson);

		long t2 = System.currentTimeMillis();
		logger.info("=======================总耗费时间:  " + (t2 - t1) + "ms");
		logger.info("queryArticle参数 ："+JSONObject.fromObject(query).toString());
		logger.info("queryArticle返回 ："+returnJson);
	}
	
	
}
