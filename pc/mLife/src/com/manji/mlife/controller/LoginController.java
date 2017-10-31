package com.manji.mlife.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.mlife.model.SimpleCode;
import com.manji.mlife.model.User;
import com.manji.mlife.service.LoginService;
import com.manji.mlife.utils.LoadJson;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	
	private Logger logger=Logger.getLogger(LoginController.class);
	
	@Autowired
	private   LoginService logService;
	@RequestMapping("/getStatus")
	@ResponseBody
	public String getStatus(){
  		List<SimpleCode> l = logService.queryAll();
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(l);//list转化为json
		String string = jsonArray.toString();
		System.out.println(string);
		return string;
	}
	@RequestMapping("/changeStatus")
	@ResponseBody
	public String changeStatus(String value,String codename){
		String a=null;
		logService.updateStatus(value,codename);
		a = "1";
		return a;
	}
	
	/**
	 * 跳转到手机流量的界面
	 * @author gaochao
	 */
	@RequestMapping("/toMobileFlo")
	public String toMobileFlo(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		
		if(!logService.isModelOn("mobileflo")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "/toMobileFlo";
	}
	
	/**
	 * 固话宽带充值跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toGk" , method=RequestMethod.GET)
	public String toGk(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("gk")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
			return "/gk/toGk";
		
	}
	//有限电视跳转页面
	@RequestMapping( value="/toCatv",method=RequestMethod.GET)
	public String toCatv(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("tv")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "/catv/toCatv";
	}
	
	//订购飞机票跳转页面
	@RequestMapping(value="/toAir" ,method=RequestMethod.GET)
	public String  toAir(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("air")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "/air/toAir";
	}
	

	@RequestMapping(value = "/toMobile", method = RequestMethod.GET)
	public String toMobile(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("mobile")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "/mobile";
	}
	
	@RequestMapping(value = "/toFine", method = RequestMethod.GET)
	public String  toFine(HttpServletRequest request){

		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("fine")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "fine";
	}
	@RequestMapping(value = "/toCoach", method = RequestMethod.GET) 
	public String toCoach(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("coach")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "coach";
	}
	@RequestMapping(value = "/toGasCard", method = RequestMethod.GET)
	public String toGasCard(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("gascard")){
			
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "gasCard";
	}
	
	@RequestMapping(value = "/toTrain", method = RequestMethod.GET)
	public String toTrain(HttpServletRequest request){

		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("train")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "train";
	}
	
	@RequestMapping(value = "/toWaterCoal", method = RequestMethod.GET)
	public String toWaterCoal(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
			
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		if(!logService.isModelOn("watercaol")){
			request.setAttribute("errMsg", "该服务正在维护中，暂不开放使用。");
		}
		return "waterCoal";
	}
	@RequestMapping(value = "/toGame", method = RequestMethod.GET)
	public String toGame(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
			Map<String,String> map =getUserInfo(sessionId);
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		
		if(!logService.isModelOn("game")){
			session.setAttribute("errMsg", "服务暂时关闭。。。");
		}
		return "game";
	}
	
//	@RequestMapping(value = "/toCard", method = RequestMethod.GET)
//	public String toCard(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		String sessionId =request.getParameter("session");
//		
//		String err="";
//		if(null !=sessionId&&!"".equals(sessionId)){
//			
//			String userInfo = userInfo(sessionId,session,err);
//		}
//		
//		return "card";
//	}
	
	@RequestMapping(value="/toOrder", method = RequestMethod.GET)
	public String toOrder(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
//			String userInfo = userInfo(sessionId,session,err);
		}else{
			return "redirect:/";
		}
		return "order";
	}
	
	@RequestMapping(value="/toTrafficOrder", method = RequestMethod.GET)
	public String toTrafficOrder(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId =(String)session.getAttribute("sessionId");
		
//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
			
//			String userInfo = userInfo(sessionId,session,err);
		}else{
			return "redirect:/";
		}
		
		return "order_traffic";
	}
	
	@RequestMapping(value="/toManage", method = RequestMethod.GET)
	public String toManage(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId =request.getParameter("session");

//		String err="";
		if(null !=sessionId&&!"".equals(sessionId)){
			
//			String userInfo = userInfo(sessionId,session,err);
			
			Map<String,String> map =getUserInfo(sessionId);
			
			if("0".equals(map.get("errCode"))){
				session.setAttribute("user_name", map.get("user_name"));
				session.setAttribute("sessionId", sessionId);
			}
		}
		
		return "manage";
	}
	
	/**
	 * 
	 * @author gaochao
	 * @return
	 * 2016年7月21日下午2:50:51
	 * String
	 *获取用户信息接口
	 *www.manjiwang.com/tools/submit_ajax.ashx?action=GetUserInfoBySessionId&sessionid={0}
	 *用户登录接口
     *www.manjiwang.com/tools/submit_ajax.ashx?action=UserLoginForApi&username={0}&userpassword={1}
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String Login(User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		if (user == null){
			return null;
		}
		
		if (user.getCheckcode() == null){
			return "验证码为空!";
		}
		
		HttpSession session = request.getSession();
		String retMsg="";
		
		// 校验验证码
		String checkcode = (String) session.getAttribute("checkcode");
		if(!user.getCheckcode().equalsIgnoreCase(checkcode)){
			retMsg="验证码错误!";
			return retMsg;
		}
		
		// 用满集网的登录接口校验用户名和密码
		String url = logService.getUserUrl();
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("action", "UserLoginForApi");
		map.put("username", user.getUsername());
		map.put("userpassword", user.getPassword());
		String loadJSON = LoadJson.loadJSON(url, map);
		logger.debug(loadJSON);
		
		JSONObject obj = null;
		if(loadJSON == null || "不支持的协议".equals(loadJSON) || "服务未响应".equals(loadJSON)){
			retMsg="登录异常，请稍后再试！";
		}else{
			obj = JSONObject.fromObject(loadJSON);
		}

		// 判读是否拿到sessionId
		if(obj.getString("status").equals("1")){
			// 传入sessionid ,获取用户信息
			Map<String, String> userMap = new HashMap<String, String>();
			userMap = getUserInfo(obj.getString("data"));
			
			if("0".equals(userMap.get("errCode"))){
				session.setAttribute("user_name", userMap.get("user_name"));
				session.setAttribute("sessionId", userMap.get("sessionId"));
			}else{
				retMsg = obj.getString("msg");
			}
			logger.debug(retMsg.split(",")[0]);
//			session.setAttribute("user_name", retMsg.split(",")[0]);
//			session.setAttribute("sessionId", JSONObject.fromObject(loadJSON).getString("data"));
		}else{
			retMsg=obj.getString("msg");
		}
		
		if(retMsg.equals("")){
			String flag = user.getFlag();
			session.setAttribute("flag", flag);
			//set cookie
			if(flag!=null && flag.equals("1")){
			    Cookie cookie = new Cookie("cookie_user", user.getUsername()+"-"+user.getPassword());				
			    cookie.setMaxAge(60*60*24*30); //cookie 保存30天
			    response.addCookie(cookie);
			}else{	
			    Cookie cookie = new Cookie("cookie_user",user.getUsername()+"-"+null);				
			    cookie.setMaxAge(60*60*24*30); //cookie 保存30天
			    response.addCookie(cookie);				
			}
			return "success";
		}
			
		return retMsg;
	}
	
	/**
	 * 获取用户信息
	 * @author gaochao
	 * @param userId
	 * @param session 
	 * @param err 
	 * @return
	 * 2016年7月22日上午9:52:39
	 * String
	 *
	 */
	public  String userInfo(String sessionId, HttpSession session, String err){
		// 获取用户信息
		String userInfo = logService.getUserUrl();

		Map<String, String> mapInfo = new HashMap<String, String>();
		mapInfo.put("action", "GetUserInfoBySessionId");
		mapInfo.put("sessionid", sessionId);
		try {
			String loadJSON = LoadJson.loadJSON(userInfo, mapInfo);
			JSONObject obj = JSONObject.fromObject(loadJSON);
			//判读获取的信息是否可用
			if(obj.getString("status").equals("1")){
				JSONObject jsonObject = obj.getJSONObject("data");
				//判断账户是否正常
//				        "status": 0,// 账户状态,0正常,1待验证,2待审核,3锁定,4黑名单
//				        "is_del": 0,//是否删除 0正常 1删除
				if(jsonObject.getString("status").equals("0")&&jsonObject.getString("is_del").equals("0")){
					//保存用户信息
					session.setAttribute("user_name",jsonObject.getString("user_name"));
					//保存sessionId
					session.setAttribute("sessionId", sessionId);
					
				}
			}else{
				//未获取到用户的错误信息返回
			  err = obj.getString("msg");
			}
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

		return err;
	}
	
	
	
	//登录
	
	public  Map<String,String> getUserInfo(String sessionId){
		
		Map<String,String> map =new HashMap<String,String>();
		//获取用户信息
		String userInfo=logService.getUserUrl();

		Map<String, String> mapInfo=new HashMap<String,String>();
		mapInfo.put("action", "GetUserInfoBySessionId");
		mapInfo.put("sessionid", sessionId);
		try {
			String loadJSON = LoadJson.loadJSON(userInfo, mapInfo);
			
			JSONObject obj = JSONObject.fromObject(loadJSON);
			//判读获取的信息是否可用
			if(obj.getString("status").equals("1")){
				JSONObject jsonObject = obj.getJSONObject("data");
				//判断账户是否正常
//		        "status": 0,// 账户状态,0正常,1待验证,2待审核,3锁定,4黑名单
//		        "is_del": 0,//是否删除 0正常 1删除
				if(jsonObject.getString("status").equals("0")&&jsonObject.getString("is_del").equals("0")){
//					//保存用户信息
//					session.setAttribute("user_name",jsonObject.getString("user_name"));
//					//保存sessionId
//					session.setAttribute("sessionId", sessionId);
					
					map.put("errCode", "0");
					map.put("user_name", jsonObject.getString("user_name"));
					map.put("sessionId", sessionId);
					
				}
			}else{
				//未获取到用户的错误信息返回
				map.put("errCode", "1");
				map.put ("errMsg",obj.getString("msg"));
			  
			}
			
		} catch (UnsupportedEncodingException e) {
			map.put("errCode", "1");
			map.put ("errMsg","登录出错");
			e.printStackTrace();
		}

		return map;
	}
	
	
	/**
	 * 退出登录
	 * @author gaochao
	 * @param request
	 * @return
	 * 2016年7月22日上午11:22:43
	 * String
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//销毁sessionId和用户名
		session.invalidate();
	    request.getRequestDispatcher("main.jsp").forward(request, response);	
	}

}
