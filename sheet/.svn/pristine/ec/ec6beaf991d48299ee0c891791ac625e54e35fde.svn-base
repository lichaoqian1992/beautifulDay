package com.manji.sheet.intercepter;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.base.UrlCode;
import com.manji.sheet.controller.IndexController;
import com.manji.sheet.model.bean.Menu;
import com.manji.sheet.model.bean.MenuInfo;
import com.manji.sheet.utils.HttpClientUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LocalIntercepter implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		
		
		
		HttpSession session = inv.getController().getSession();
		
		String token = inv.getController().getCookie("ssoToken");
		if(session == null){
			inv.getController().redirect("/");//跳转到登录页面
		}else{
			Object user = session.getAttribute("user");
			if(user == null){
				//验证token
				//System.out.println("=================================token11111:"+token+"=====================");
				String url_auth = UrlCode.URL_AUTH+"system=sheet&ssoToken="+token;
				String authData = HttpClientUtils.postMethod(url_auth);
				if(JSONObject.fromObject(authData).get("code").equals("0000")){
					JSONObject userInfo = JSONObject.fromObject(authData).getJSONObject("data").getJSONObject("userInfo");
					JSONArray menuInfo = JSONObject.fromObject(authData).getJSONObject("data").getJSONArray("menuInfo");
					inv.getController().setSessionAttr("user", userInfo);
					List<MenuInfo> menu = new IndexController().getMenu(menuInfo);

					Record menuRecord=new Record();
					menuRecord.set("acceptanceUrl","");
					menuRecord.set("alreadyUrl","");
					menuRecord.set("pushUrl","");
					for(MenuInfo menuInfoChild:menu){
						if(menuInfoChild.getSys_id().equals("1") && menuInfoChild.getTitle().equals("工单管理") ){
							for(Menu menuChild: menuInfoChild.getChild()){
								if(menuChild.getAddress().equals("01_01") || menuChild.getTitle().equals("工单受理")){
									menuRecord.set("acceptanceUrl",menuChild.getUrl());
								}else if(menuChild.getAddress().equals("01_02") || menuChild.getTitle().equals("工单处理")){
									menuRecord.set("alreadyUrl",menuChild.getUrl());
								}else if(menuChild.getAddress().equals("01_03") || menuChild.getTitle().equals("结果推送")){
									menuRecord.set("pushUrl",menuChild.getUrl());
								}
							}
						}
					}
					inv.getController().setSessionAttr("menuRecord", menuRecord);
					inv.getController().setSessionAttr("menu", menu);
					inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
				}else{
					inv.getController().redirect("/");//跳转到登录页面
				}
			}else{
				inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
			}
		}
		
	}

	
	
	//HttpSession
//	cookie token 
	
	
	
	
	
}
