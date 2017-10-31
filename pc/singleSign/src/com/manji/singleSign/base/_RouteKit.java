package com.manji.singleSign.base;


import com.jfinal.config.Routes;
import com.manji.singleSign.controller.DeptController;
import com.manji.singleSign.controller.IndexController;
import com.manji.singleSign.controller.MenuController;
import com.manji.singleSign.controller.RoleController;
import com.manji.singleSign.controller.SystemController;
import com.manji.singleSign.controller.UserController;



public class _RouteKit {

	public static Routes routing(Routes route) {
		
		route.add("/",IndexController.class,"/WEB-INF/html/login");
		route.add("/dept",DeptController.class,"/WEB-INF/html/dept");
		route.add("/menu",MenuController.class,"/WEB-INF/html/menu");
		route.add("/role",RoleController.class,"/WEB-INF/html/role");
		route.add("/sys",SystemController.class,"/WEB-INF/html/sys");
		route.add("/user",UserController.class,"/WEB-INF/html/user");
		
		return route;
		
		
	}


}
