package com.manji.backstage.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.dto.login.UserRoleDto;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.service.login.AuthService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.login.PermissionVo;

@Controller
public class AuthController extends BaseController{

	@Autowired
	private AuthService service;
	
	/////users
	
	
	@RequestMapping("/selLocalUser")
	public String userlist(HttpServletRequest req){
		
		
		HttpSession session =req.getSession();
		

		String username =(String) session.getAttribute("username");
		System.out.println(username);
		List<User> userList =service.getLocalUserAndRole();
		
		req.setAttribute("localusers", Array(userList));
		return "login/login_user_list";
	}
	
	

	@RequestMapping("/readLocalUser")
	public String readLocalUser(HttpServletRequest req,Integer id){
		
		/*int userId =req.getParameter("id");*/
		
		User u =service.getLocalUser(id);
		
		req.setAttribute("localuser", Json(u));
		
//		List<Role> r =service.getLocalRoleByUser(Integer.valueOf(userId));
		
		List<RoleToUser> rl =service.getURRelation(id);
		
		req.setAttribute("localrel", Array(rl));
		return "login/login_user_upd";
	}
	
	

	@RequestMapping(value ="/updLocalUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String chgLocalUser(HttpServletRequest req,User user){

		Message msg =createMsg();
		
		if(service.updLocalUser(user)){
			
			msg.setStatus("0");
			
		}else{
			
			msg.setStatus("1");
			
		}
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/getLocalRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryLocalRoleList(HttpServletRequest req){
		
		Message msg =createMsg();
		
		List<Role> roleList =service.getLocalRole();
		
		msg.setStatus("0");
		msg.setResult(roleList);
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/delLocalRoleToUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String delLocalRoleToUser(HttpServletRequest req,Integer id){
		
		/*Integer id =req.getParameter("relid");*/
		System.out.println(id);
		Message msg =createMsg();
		
		if(service.delLocalRoleToUser(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selLocalUser";
		
	}
	
	@RequestMapping(value ="/addLocalRoleToUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addLocalRoleToUser(HttpServletRequest req,RoleToUser rtu){
		Message msg =createMsg();
		rtu.setAddTime(TimeUtils.getCurrentTime());
		service.addLocalRoleToUser(rtu);
		
		msg.setStatus("0");
		
		
		return Json(msg);
		
	}
	
	

	@RequestMapping("/insLocalUser")
	public String insertLocalUser(HttpServletRequest req){
		return "login/login_user_add";
	}
	@RequestMapping("/addLocalUser")
	@ResponseBody
	public String addLocalUser(HttpServletRequest req,User user){
		Message msg = createMsg();
		service.addLocalUser(user);
		msg.setStatus("0");
		return Json(msg);
	}
	
	@RequestMapping("/delLocalUser")
	public String delLocalUser(HttpServletRequest req,Integer id){
		service.delLocalUser(id);
		return "redirect:/selLocalUser";
	}
	
	
	@RequestMapping("/selLocalRole")
	public String selLocalRole(HttpServletRequest req){
		
		List<Role> roleList =service.getLocalRole();
		
		req.setAttribute("rolelist", Array(roleList));
		
		return "login/login_role_list";
	}
	
	@RequestMapping("/insLocalRole")
	public String insLocalRole(HttpServletRequest req){
		return "login/login_role_add";
	}
	
	@RequestMapping(value ="/addLocalRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addLocalRole(HttpServletRequest req,Role r){
		Message msg =createMsg();
		
		if(service.checkRoleName(r.getRoleName())){
			r.setAddTime(TimeUtils.getCurrentTime());
			service.addLocalRole(r);
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
			msg.setResult("重复");
		}
		
		return Json(msg);
	}
	
	
	@RequestMapping("/readLocalRole")
	public String readLocalRole(HttpServletRequest req,Integer id){
		Role roleList = service.getLocalRoleList(id);
		req.setAttribute("roleList", Json(roleList));
		List<Role> roleinfo =service.getLocalRole();
		req.setAttribute("roleinfo", Array(roleinfo));
		return "login/login_role_upd";
	}
	
	@RequestMapping(value ="/updLocalRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updLocalRole(HttpServletRequest req,Role role){
		Message msg = createMsg();
		if(service.updLocalRole(role)){
			msg.setResult("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	@RequestMapping("/delLocalRole")
	public String delLocalRole (HttpServletRequest req,Role r){
		Message msg =createMsg();
		
		if(service.delLocalRole(r.getId())){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selLocalRole";
	}
	
	
	
	
	@RequestMapping("/selLocalPermission")
	public String selLocalPermission(HttpServletRequest req){
		/*List<Permission> perList =service.queryLocalPermission();
		
		req.setAttribute("perlist", Array(perList));*/
		return "login/login_permission_list";
	}
	
	@RequestMapping(value="/queryPermission",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPermission(HttpServletRequest req,PermissionVo vo){
		Message msg = createMsg();
		Page<Permission> page = service.queryLocalPermission(vo);
		if(page.getTotalCount()!=0)	{
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	@RequestMapping("/insPermission")
	public String insPermission(HttpServletRequest req){
		return "login/login_permission_add";
	}
	
	@RequestMapping(value = "addPermission",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addPermission(HttpServletRequest req,Permission per){
		Message msg = createMsg();
		service.addPermission(per);
		msg.setStatus("0");
		return Json(msg);
	}
	
	@RequestMapping("/readPermission")
	public String readPermission(HttpServletRequest req,int id){
		Permission per = service.getPermission(id);
		req.setAttribute("perinfo", Json(per));
		return "login/login_permission_upd";
	}
	
	@RequestMapping(value ="/updPermission",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPermission(HttpServletRequest req,Permission per){
		Message msg = createMsg();
		if(service.updPermission(per)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	@RequestMapping("/delPermission")
	public String delPermission(HttpServletRequest req,int id){
		
		if(service.delPermission(id)){
			
		}
		return "redirect:/selLocalPermission";
		
			
		
	}
	
	@RequestMapping("/updLocalPerToRole")
	public String updLocalPerToRole(HttpServletRequest req,Integer roleId){
		
		Role r =service.getLocalRole(roleId);
		
		req.setAttribute("roleinfo", Json(r));
		
		/*List<Permission> perList =service.getPermission(roleId);
		
		req.setAttribute("perlist", Array(perList));*/
		
		return "login/login_per_role";
	}
	
	
	
	
	
	
	
	
	
}
