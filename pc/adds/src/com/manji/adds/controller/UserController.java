package com.manji.adds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.adds.model.AddsMsg;
import com.manji.adds.model.User;
import com.manji.adds.service.UserService;
import com.manji.adds.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	/**
	 * 新增用户
	 * @param userInfo
	 * @return
	 */
//	@RequestMapping(value = "/addUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
//	@ResponseBody 
	
	/**
	 * 得到所有用户
	 * @return
	 */

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getAllUsers(HttpServletRequest req){
		
		AddsMsg msg =new AddsMsg();
		
		List<User> list =service.getAllUsers();
		
		System.out.println(JSONArray.fromObject(list).toString());
		
		if(null !=list)
		{
			msg.setResult(list);
		}else
		{
			msg.setErrCode("1");
			msg.setErrInfo("查询出错。");
		}
		
		return JsonUtils.getJson(msg);
	}
	
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)

	public String addUser(HttpServletRequest req,User userInfo){

		System.out.println(JSONObject.fromObject(userInfo));
		AddsMsg msg =new AddsMsg();
		
		service.addUser(userInfo);
		
		msg.setResult("新增成功");
	
		return "redirect:/toUser";
	}
	
	
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	
	@RequestMapping(value = "/updUser", method = RequestMethod.POST)

	public String updUser(HttpServletRequest req,User userInfo){
		
		System.out.println(JSONObject.fromObject(userInfo));
		
		AddsMsg msg =new AddsMsg();
		
		if(service.updUser(userInfo))
		{
			
			msg.setResult("更新成功");
		}else
		{
			
			msg.setErrCode("1");
			msg.setErrInfo("更新失败");
		}
		
		return "redirect:/toUser";
	}
	
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/delUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String delUser(int id){
		
		System.out.println(id);
		
		AddsMsg msg =new AddsMsg();
		
		if(service.delUser(id))
		{
			
			msg.setResult("删除成功");
		}else
		{
			
			msg.setErrCode("1");
			msg.setErrInfo("删除失败");
		}
		
		return JsonUtils.getJson(msg);
	}
	
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getUserById(int id){
		
		User userInfo =new User();
		
		userInfo =service.getUserById(id);
		
		
		return JsonUtils.getJson(userInfo);
	}
	
	
	@RequestMapping("/toUser")
	public String toUser( HttpServletRequest req){
		return "user";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser( HttpServletRequest req){
		return "user_add";
	}
	
		
}
