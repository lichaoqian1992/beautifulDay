package com.manji.data.base;

import com.jfinal.config.Routes;
import com.manji.data.controller.business.BusinessController;
import com.manji.data.controller.operate.OperateController;
import com.manji.data.controller.service.IndexController;
import com.manji.data.controller.service.ShopInfoCenterController;

public class _RouteKit {

	public static Routes routing(Routes route) {
		route.add("/",IndexController.class,"/WEB-INF/html/index");
		route.add("/shop",ShopInfoCenterController.class,"/data");
		route.add("/business",BusinessController.class,"/WEB-INF/html/business");
		route.add("/operate",OperateController.class,"/WEB-INF/html/operate");
		return route;

	}
}
