package com.manji.finance.base;

import com.jfinal.config.Routes;
import com.manji.finance.home.HomeController;
import com.manji.finance.index.IndexController;
import com.manji.finance.order.OrderController;
import com.manji.finance.recharge.RechargeController;
import com.manji.finance.system.SystemController;
import com.manji.finance.withdrawals.Pay.PayController;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsController;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsHttpController;


public class _RouteKit {

	
public static void routing(Routes route){
		
	
	route.add("/",IndexController.class,"/index");
	route.add("/home",HomeController.class,"/home");
	route.add("/withdrawals", WithdrawalsController.class,"/withdrawals");
	route.add("/withdrawalshttp", WithdrawalsHttpController.class);
	route.add("/rec",RechargeController.class,"/rec");
	route.add("/ord",OrderController.class,"/ord");
	route.add("/sys",SystemController.class,"/sys");
	route.add("/pay", PayController.class,"/pay");
	
//		route.add("/",IndexController.class,"/");
//		route.add("/user",UserController.class,"/user");
//		route.add("/rel",RelController.class,"/rel");
		
		
		
	}
	
}
