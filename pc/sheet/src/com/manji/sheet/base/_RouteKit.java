package com.manji.sheet.base;


import com.jfinal.config.Routes;
import com.manji.sheet.controller.*;


public class _RouteKit {

	public static Routes routing(Routes route) {
		
		route.add("/",IndexController.class,"/WEB-INF/html/index");
		route.add("/base",BaseController.class,"/WEB-INF/html/base");
		route.add("/manage",ManageController.class,"/WEB-INF/html/sheet");
		route.add("/shopApp",ShopController.class,"/WEB-INF/shopApp");
		route.add("/service",serviceController.class,"/WEB-INF/shopApp/customer");
		route.add("/sheet",SheetController.class,"/WEB-INF/html/sheet");
		route.add("/buyersApp",BuyersAppController.class,"/WEB-INF/app");
		route.add("/shopPC",PCController.class);
		route.add("/buyerPc",BuyerPcController.class);
		route.add("/err",ErrController.class,"/WEB-INF");
		route.add("/evaluate",EvaluateController.class);
		return route;
		
		
	}


}
