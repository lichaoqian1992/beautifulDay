package com.manji.advert.base;


import com.jfinal.config.Routes;
import com.manji.advert.controller.AdvertiseController;
import com.manji.advert.controller.AppController;
import com.manji.advert.controller.ChannelController;
import com.manji.advert.controller.FileController;
import com.manji.advert.controller.IndexController;
import com.manji.advert.controller.LoginController;
import com.manji.advert.controller.PCController;
import com.manji.advert.controller.RecordController;
import com.manji.advert.controller.PlaceController;
import com.manji.advert.controller.UserController;

public class _RouteKit {

	public static Routes routing(Routes route) {
		
		route.add("/",LoginController.class,"/");
		route.add("/index",IndexController.class,"/WEB-INF/html/index");
		route.add("/channel",ChannelController.class,"/WEB-INF/html/channel");
		route.add("/place",PlaceController.class,"/WEB-INF/html/place");
		route.add("/advertise",AdvertiseController.class,"/WEB-INF/html/advertise");
		route.add("/file",FileController.class,"/WEB-INF/html/file");
		route.add("/record",RecordController.class,"/WEB-INF/html/record");
		route.add("/app",AppController.class,"/WEB-INF/html/app");
		route.add("/user",UserController.class,"/WEB-INF/html/user");
		route.add("/pc",PCController.class);
		return route;
		
		
	}


}
