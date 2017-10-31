package com.manji.mlife.appController;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manji.mlife.common.FileRead;
import com.manji.mlife.service.LoginService;
import com.manji.mlife.utils.LocationLoadJson;

import net.sf.json.JSONObject;




@Controller
public class AppLoginController {

	
	@Autowired
	private LoginService service;

	@RequestMapping("/toAppFine")
	public String toAppFine(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		
		if(service.isModelOn("appfine")){
			return "appFine";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
	}
	
	@RequestMapping("/toAppMobile")
	public String toAppMobile(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		System.out.println(sessionId +JSONObject.fromObject(map));
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appmobile")){
			return "appMobile";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	
	@RequestMapping("/toAppMobileFlo")
	public String toAppMobileFlo(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appmobileflo")){
			return "appMobile";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		

	}
	
	@RequestMapping("/toAppWaterCoal")
	public String toAppWaterCoal(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appwatercoal")){
			return "appWaterCoal";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	

	
	@RequestMapping("/toAppCATV")
	public String toAppCATV(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("apptv")){
			return "appTV";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	@RequestMapping("/toAppGK")
	public String toAppGK(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appgk")){
			return "appGK";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
	}
	
	@RequestMapping("/toAppGasCard")
	public String toAppGasCard(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appgascard")){
			return "appGasCard";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	
	@RequestMapping("/toAppCoach")
	public String toAppCoach(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appcoach")){
			return "appCoach";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	
	@RequestMapping("/toAppTrain")
	public String toAppTrain(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("apptrain")){
			return "appTrain";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	
	@RequestMapping("/toAppAir")
	public String toAppAir(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		if(service.isModelOn("appair")){
			return "appAir";
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
//	@RequestMapping("/appWeather")
//	public String appWeather(HttpServletRequest request){
//		
//		HttpSession session = request.getSession();
////		String sessionId=request.getParameter("sessionId");
////		String userName=request.getParameter("userName");
////		
////		session.setAttribute("sessionId", sessionId);
////		session.setAttribute("userName", userName);
//		
//		if(service.isModelOn("appweather")){
//			return "appWeather";
//		}else{
//			String errMsg ="很抱歉，该服务暂未开启。";
//			session.setAttribute("errMsg", errMsg);
//			return "redirect:/errInfo";
//		}
//
//	}
	
	
	
//	@RequestMapping("/appWeather")
//	public void appWeather(HttpServletRequest request, HttpServletResponse response){
//		HttpSession session = request.getSession();
//		
//		String city =request.getParameter("city");
//		
//		
//		
//		String fileName = "appWeather";
//		String path = request.getRealPath("/");
//		String htmlTxt = FileRead.getFileContent(path, fileName);
//		System.out.println(htmlTxt);
//		
//		
//		htmlTxt = htmlTxt.replaceAll("#mLife_weather_city", city);
//		
//		
//		if(!service.isModelOn("appweather")){
//			
//		}
//
//		
//		try {
//			response.setContentType("text/html");
//			response.setCharacterEncoding("utf-8");
//			response.getWriter().write(htmlTxt);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	
	@RequestMapping("/errInfo")
	public void errInfo(HttpServletRequest request, HttpServletResponse res){
		
		String errMsg = (String) request.getSession().getAttribute("errMsg");
		if(null ==errMsg){
			errMsg =request.getParameter("errMsg");
		}
//		System.out.println(errMsg);
		String fileName = "appFault";	
		String path =request.getRealPath("/");
		String text =FileRead.getFileContent(path, fileName);
//		System.out.println(text);
//		try {
//			text =new String(text.getBytes(),"utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		if(errMsg == null){
			errMsg = "";
		}
		text =text.replaceAll("#mLife_errInfo", errMsg);
//		System.out.println(text);
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/toAppGame")
	public String toAppGame(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		if("0".equals(map.get("result"))){
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		}else{
			String errMsg ="登录失效，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}
		
		
		String fileName = "appGameList";	
		
		if(service.isModelOn("appgame")){
			
			return fileName;
		}else{
			String errMsg ="很抱歉，该服务暂未开启。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}

	}
	
	
//	@RequestMapping("/toAppOrder")
//	public String toAppOrder(HttpServletRequest request){
//		
//		HttpSession session = request.getSession();
//		String sessionId=request.getParameter("sessionId");
//		Map<String, String> map =service.getUserInfoBySession(sessionId);
//		if("0".equals(map.get("result"))){//"0".equals(map.get("result"))
//			session.setAttribute("sessionId", sessionId);
//			session.setAttribute("userName", map.get("userName"));
//		}else{
//			String errMsg ="登录失效，请重新登录。";
//			session.setAttribute("errMsg", errMsg);
//			return "redirect:/errInfo";
//		}
//		
//		return "appOrderBill";
//		
//
//	}
	
	@RequestMapping("/toAppOrder")
	public void toAppOrder(HttpServletRequest request,HttpServletResponse res){
		
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		String text ="";
		
		if("0".equals(map.get("result"))){//"0".equals(map.get("result"))
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		
			String fileName = "appOrderBill";	
			String path =request.getRealPath("/");
			text =FileRead.getFileContent(path, fileName);

			text =text.replaceAll("#mLife_order_username", map.get("userName"));
			text =text.replaceAll("#mLife_order_sessionId", sessionId);
//			System.out.println(text);
			
		}else{
			String errMsg ="登录失效，请重新登录。";
			
			String fileName = "appFault";	
			String path =request.getRealPath("/");
			text =FileRead.getFileContent(path, fileName);
			text =text.replaceAll("#mLife_errInfo", errMsg);

		}
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	@RequestMapping("/toAppOrderTicke")
//	public String toAppOrderTicke(HttpServletRequest request){
//		
//		HttpSession session = request.getSession();
//		String sessionId=request.getParameter("sessionId");
//		Map<String, String> map =service.getUserInfoBySession(sessionId);
//		if("0".equals(map.get("result"))){//map.get("result") == null
//			session.setAttribute("sessionId", sessionId);
//			session.setAttribute("userName", map.get("userName"));
//		}else{
//			String errMsg ="登录失效，请重新登录。";
//			session.setAttribute("errMsg", errMsg);
//			return "redirect:/errInfo";
//		}
//		
//		return "appOrderTicke";
//		
//
//	}
	
	
	@RequestMapping("/toAppOrderTicke")
	public void toAppOrderTicke(HttpServletRequest request,HttpServletResponse res){
		
		
		HttpSession session = request.getSession();
		String sessionId=request.getParameter("sessionId");
		Map<String, String> map =service.getUserInfoBySession(sessionId);
		
		String text ="";
		
		if("0".equals(map.get("result"))){//"0".equals(map.get("result"))
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("userName", map.get("userName"));
		
			String fileName = "appOrderTicke";	
			String path =request.getRealPath("/");
			text =FileRead.getFileContent(path, fileName);

			text =text.replaceAll("#mLife_order_username", map.get("userName"));
			text =text.replaceAll("#mLife_order_sessionId", sessionId);
		}else{
			String errMsg ="登录失效，请重新登录。";
			
			String fileName = "appFault";	
			String path =request.getRealPath("/");
			text =FileRead.getFileContent(path, fileName);

			text =text.replaceAll("#mLife_errInfo", errMsg);

			
			
		}
		
		try{
			res.setContentType("text/html");  
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
		
	
	
	
	@RequestMapping("/toAppWeather")
	public void toAppWeather(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String city = request.getParameter("city");
		// 获得经纬度
		System.out.println(city);
		String htmlTxt="";
		if (service.isModelOn("appweather")) {

			 Map<String,String> map =LocationLoadJson.getCity(city);
			
			String region =map.get("region");
			String cityname =map.get("cityname");

			String fileName = "appWeather";
			String path = request.getRealPath("/");
			htmlTxt = FileRead.getFileContent(path, fileName);

			htmlTxt = htmlTxt.replaceAll("#mLife_weather_region", region);
			htmlTxt = htmlTxt.replaceAll("#mLife_weather_cityname", cityname);
			
			

		} else {
			
			String fileName = "appFault";	
			String path = request.getRealPath("/");
			htmlTxt = FileRead.getFileContent(path, fileName);
			
			htmlTxt =htmlTxt.replaceAll("#mLife_errInfo", "很抱歉，该服务暂未开启。");
		}
		
		
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(htmlTxt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
