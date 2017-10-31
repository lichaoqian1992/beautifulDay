package com.manji.elastic.controller;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.manji.elastic.model.request.app.AppArticleQuery;
import com.manji.elastic.model.request.app.AppShopArticleQuery;
import com.manji.elastic.servie.AppArticleService;

import net.sf.json.JSONObject;


/**
 * APP商品查询 ----
 * @author Administrator
 *
 */
public class AppArticleController extends Controller {

	private static Logger logger = Logger.getLogger(AppArticleController.class);
	
	private AppArticleService service = new AppArticleService();

	public void index() {

		renderText("apparticlesb");
		logger.info("apparticlesb");
	}

	/**
	 * 综合商品查询
	 */
	public void queryArticle() {

		long t1 = System.currentTimeMillis();

		AppArticleQuery query = getBean(AppArticleQuery.class, "");

		String returnJson = service.queryArticle(query);

		renderText(returnJson);

		long t2 = System.currentTimeMillis();
		logger.info("=======================总耗费时间:  " + (t2 - t1) + "ms");
		
		logger.info("queryArticle参数 ："+JSONObject.fromObject(query).toString());
		logger.info("queryArticle返回 ："+returnJson);

	}
	
	
	/**
	 * 好货列表
	 */
	public void satisfiedList(){
		
		long  t1 =System.currentTimeMillis();
		
		AppArticleQuery query =getBean(AppArticleQuery.class,"");
		
		String returnJson =service.querySatisfiedArticle(query);
		
		renderText(returnJson);
		
		long  t2 =System.currentTimeMillis();
		logger.info("=======================总耗费时间:  "+(t2-t1)+"ms");
		logger.info("satisfiedList参数 ："+JSONObject.fromObject(query).toString());
		logger.info("satisfiedList返回 ："+returnJson);
		
	}
	
	
	/**
	 * 同类推荐
	 */
	public void similarRecommend(){
		
		long  t1 =System.currentTimeMillis();
		AppArticleQuery query =getBean(AppArticleQuery.class,"");
		
		String returnJson =service.querySimilarArticle(query);
		
		renderText(returnJson);
		
		long  t2 =System.currentTimeMillis();
		logger.info("=======================总耗费时间:  "+(t2-t1)+"ms");
		logger.info("similarRecommend参数 ："+JSONObject.fromObject(query).toString());
		logger.info("similarRecommend返回 ："+returnJson);
	}
	

	
	/**
	 * 商家类查询
	 */
	public void articleOfShop(){
		
		long  t1 =System.currentTimeMillis();
		AppArticleQuery query =getBean(AppArticleQuery.class,"");
		
		String returnJson =service.queryArticleGroupByShop(query);
		
		renderText(returnJson);
		
		long  t2 =System.currentTimeMillis();
		logger.info("=======================总耗费时间:  "+(t2-t1)+"ms");
		logger.info("similarRecommend参数 ："+JSONObject.fromObject(query).toString());
		logger.info("similarRecommend返回 ："+returnJson);
		
	}
	
	
	
	
	
	//商家商品綜合查詢
	public void shopArticle(){
		
		long  t1 =System.currentTimeMillis();
		AppShopArticleQuery query =getBean(AppShopArticleQuery.class,"");
		
		String returnJson =service.queryArticleByShop(query);
		
		renderText(returnJson);
		
		long  t2 =System.currentTimeMillis();
		logger.info("=======================总耗费时间:  "+(t2-t1)+"ms");
		logger.info("shopArticle参数 ："+JSONObject.fromObject(query).toString());
		logger.info("shopArticle返回 ："+returnJson);
	}
	
	
	
	
	
	
	
	
}
