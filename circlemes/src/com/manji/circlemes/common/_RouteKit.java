package com.manji.circlemes.common;

import com.jfinal.config.Routes;
import com.manji.circlemes.controller.BatchController;
import com.manji.circlemes.controller.IndexController;
import com.manji.circlemes.controller.RelController;
import com.manji.circlemes.controller.UserController;

public class _RouteKit {

	
public static void routing(Routes route){
		
	
	
	
		route.add("/",IndexController.class,"/");
		route.add("/user",UserController.class,"/user");
		route.add("/rel",RelController.class,"/rel");
		route.add("/batch",BatchController.class,"/batch");
		
		
	}
	
}
