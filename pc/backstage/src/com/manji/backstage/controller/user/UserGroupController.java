package com.manji.backstage.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.user.UserGroupService;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.dto.user.UserGroupCountsDto;
import com.manji.backstage.model.logger.LoginLog;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.model.user.User;
@Controller
public class UserGroupController extends BaseController{

	@Autowired
	private UserGroupService service;
	@Autowired
	private LoggersService logService;
	

	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("user");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	

	@RequestMapping(value ="/selUserPie")
	
	public String countGroupId(HttpServletRequest req){
		List<Group> list= service.countGroupId();
		
		
		req.setAttribute("userpielist",Array(list));
		return "user/user_pie_list"; 
	}
	
	
	@RequestMapping(value ="/findUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findUserGroup(HttpServletRequest req){
		Message msg = createMsg();
		List<Group> list = 	service.findUserGroup();
		msg.setStatus("0");
		msg.setResult(list);
		return Json(msg);
	}
	
	/**
	 * 1.1.1查询用户组别
	 * @param req
	 * @return
	 */
	@RequestMapping("/selUserGroup")
//	@RequestMapping(value = "/selUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String userGroupList(HttpServletRequest req){
		
		List<Group> userGroupList =service.userGroupList();

		System.out.println(userGroupList);
		req.setAttribute("usergrouplist", Array(userGroupList));
		
		return "user/user_group_list";
	}
	
	/**
	 * 查询商家
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/queryUserById", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserById(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String id =req.getParameter("id");
		
		System.out.println(id);
		
		List<Group> list =service.queryUserById(id);
		
		msg.setStatus("0");
		msg.setResult(list);
		
		return Json(msg);
		
	}
	
	
	@RequestMapping(value = "/getUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getUserGroup(HttpServletRequest req,int id){
		
		Group group =service.getUserGroup(id);
		req.setAttribute("groupinfo", Json(group));
//		req.setAttribute("id", id);
		return "user/user_group_list";
	}
	
	
	@RequestMapping("/insUserGroup")
	public String insUserGroup(HttpServletRequest req){
		
		
		return "user/user_group_add";
	}

	
	/**
	 * 1.1.2添加用户组别记录
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/addUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserGroup(HttpServletRequest req,Group group){
		
		Message msg =createMsg();
		
		service.addUserGroup(group);
		
		msg.setStatus("0");
		msg.setResult("添加成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(group),LogRemark.ADDUSERGROUP);
		return Json(msg);
	}

	/**
	 * 1.1.3修改用户组别记录
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/updUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserGroup(HttpServletRequest req,Group group){
		
		Message msg =createMsg();
		Group l =service.getUserGroup(group.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(l),LogRemark.UPDUSERGROUP);
		if(service.updUserGroup(group)){
			msg.setStatus("0");
			msg.setResult("成功");
		}else{
			msg.setStatus("1");
			msg.setResult("失败");
		}
	
		return Json(msg);
	}
	
	@RequestMapping("/readUserGroup")
	public String readUserGroup(HttpServletRequest req,int id){
		Group group =service.getUserGroup(id);
		req.setAttribute("groupinfo", Json(group));
		return "user/user_group_upd";
	}


	/**
	 * 1.1.4删除用户组别记录
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/delUserGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String delUserGroup(HttpServletRequest req,int id){
		
		Message msg =createMsg();
		Group l =service.getUserGroup(id);
		saveLog(createLog(req),LogRemark.DEL,Json(l),LogRemark.DELUSERGROUP);
		if(service.delUserGroup(id)){
			msg.setStatus("0");
			msg.setResult("操作成功");
		}else{
			msg.setStatus("1");
			msg.setResult("操作失败");
		}
		
		return "redirect:/selUserGroup";
	}
	
	
	/**
	 * 根据用户组别统计数量
	 * @param req
	 * @return
	 */
	@RequestMapping("/userGroupCounts")
	public String userGroupCounts(HttpServletRequest req){
		
		List<UserGroupCountsDto> countsList =service.userGroupCounts();
		
		List<UserGroupCountsDto> lastCountsList =service.lastUserGroupCounts();
		
		req.setAttribute("countslist", Array(countsList));
		
		req.setAttribute("lastcountslist", Array(lastCountsList));
		
		return "user/user_group_counts";
	}
	
	//用户组别调整
	/**
	 * 用户列表-组别信息
	 * @param req
	 * @return
	 */
	
	public String UserGroup(HttpServletRequest req){
		
		List<User> users =service.getUsersOnlyGroup();
		
		req.setAttribute("users", Array(users));
		
		return "user/user_group";
	}
	@RequestMapping(value = "/getUserGroupList", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getUserGroupList(HttpServletRequest req){
		Message msg =createMsg();
		
		List<Group> list =service.getUserGroupList();
		if(list.size()>0){
			msg.setStatus("0");
			msg.setResult(list);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	
	
	
}
