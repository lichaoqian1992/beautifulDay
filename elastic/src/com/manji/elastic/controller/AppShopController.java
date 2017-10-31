package com.manji.elastic.controller;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.manji.elastic.model.request.app.AppShopQuery;
import com.manji.elastic.servie.AppShopService;

import net.sf.json.JSONObject;

/**
 * APP商家查询 --------附近
 * 
 * @author Administrator
 *
 */
public class AppShopController extends Controller {

	private static Logger logger = Logger.getLogger(AppShopController.class);

	private AppShopService service = new AppShopService();

	/**
	 * 联通测试接口
	 */
	public void index() {

		renderText("appshopsb");
	}

	/**
	 * 查询商家
	 */
	public void queryShop() {

		long t1 = System.currentTimeMillis();

		AppShopQuery query = getBean(AppShopQuery.class, "");

		String returnJson = service.queryShopList(query);

		renderText(returnJson);

		long t2 = System.currentTimeMillis();

		logger.info("=======================总耗费时间:  " + (t2 - t1) + "ms");
		logger.info("queryShop参数 ：" + JSONObject.fromObject(query).toString());
		logger.info("queryShop返回 ：" + returnJson);
	}

	public void queryHotShop() {

		long t1 = System.currentTimeMillis();

		AppShopQuery query = getBean(AppShopQuery.class, "");

		String returnJson = service.queryHot(query);

		renderText(returnJson);

		long t2 = System.currentTimeMillis();

		logger.info("=======================总耗费时间:  " + (t2 - t1) + "ms");
		logger.info("queryHotShop参数 ：" + JSONObject.fromObject(query).toString());
		logger.info("queryHotShop返回 ：" + returnJson);

	}

	

}
