package com.manji.backstage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.utils.JsonUtils;
import com.manji.backstage.utils.TimeUtils;

public class BaseController {

 private static final Logger logger =Logger.getLogger(BaseController.class);
	
	 
	 public static void log(String info){
		 
		 logger.info(info);
		 
	 }
	 
	 public static Message createMsg(){
		 
		 Message msg =new Message();
		 return msg;
		 
	 }
	 
	 public static String Json(Object obj){
		 log(JsonUtils.getJson(obj));
		 return JsonUtils.getJson(obj);
		 
	 }
	 
	 public static String Array(Object obj){
		 log(JsonUtils.getArray(obj));
		 return JsonUtils.getArray(obj);
		 
	 }
	 
	 
	 public static Loggers createLog(HttpServletRequest req){
		 
		 HttpSession session =req.getSession();
		 int user_id =(int) session.getAttribute("userid");
		 String user_name =(String) session.getAttribute("username");
		 
		 
		 Loggers localLog =new Loggers();
		 localLog.setUser_id(user_id);
		 localLog.setUsername(user_name);
		 localLog.setTime(TimeUtils.getCurrentTime());
		 
		 return localLog;
		 
	 }

	 public static Loggers createLog(int id,String username){
		 
		 Loggers localLog =new Loggers();
		 localLog.setUser_id(id);
		 localLog.setUsername(username);
		 localLog.setTime(TimeUtils.getCurrentTime());
		 
		 return localLog;
		 
	 }
	 
	 
	  public static void saveLog(){
			
	 }
	
	
	
}
