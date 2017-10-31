package com.manji.circlemes.controller;


import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.manji.circlemes.thread.ShopThread;
import com.manji.circlemes.thread.UserThread;


public class BatchController extends Controller{

	
	
	
	public void index(){
		
		
		
		renderText("hello,你链接输错了哦！");
	}
	
	
	
	
	public void userBatch(){
		
		System.out.println("=========userthread-start===========================");
		
		UserThread t =new UserThread();
		
		t.run();
//		
		System.out.println("=========userthread-end========================================");
		
		renderText("用户批量注册完成");
	}
	
	public void shopBatch(){
		
		System.out.println("=========shopthread-start===========================");
		
		ShopThread t =new ShopThread();
		
		t.run();
		
		System.out.println("=========shopthread-end========================================");
		
		renderText("商家批量注册完成");
	}
	
	
}
