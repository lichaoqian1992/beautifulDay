package com.manji.singleSign.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.service.DeptService;
import com.manji.singleSign.service.RoleService;
import com.manji.singleSign.service.SystemService;
import com.manji.singleSign.service.UserService;

import java.util.List;

public class UserController  extends Controller{

	UserService userService =new UserService();//用户业务

	DeptService deptService=new DeptService();//部门业务

	SystemService systemService=new SystemService();//项目业务

	RoleService roleService=new RoleService();//角色业务
	
	public void index(){

		List<Record> deptlist=deptService.getDeptList();//查询出全部部门
		setAttr("deptlist",deptlist);

		List<Record> systemlist=systemService.systemList();//查询出全部系统
		setAttr("systemlist",systemlist);

		render("list.html");
	}

	//通过项目查询此项目下全部角色
	public void findRoleBySystem(){
		String system_id=getPara("system");
		List<Record> recordList=roleService.findRoleBySystemid(system_id);
		setAttr("roleList",recordList);
		renderJson();
	}

	//查询全部用户ajax
	public void findAllusers(){

		int page=getParaToInt("page");
		String username=getPara("username");
		int dept_id=getParaToInt("dept_id");

		//查询出用户集合
		Page<Record> userList=userService.findAllUser(page,username,dept_id);
		setAttr("userList",userList);

		renderJson();
	}

	//新增账户
	public void saveUsers(){

		String username=getPara("username");
		String password=getPara("password");
		String dept_id=getPara("dept");
		String realName=getPara("realName","");
		String mobile=getPara("mobile","");
		String[] system=getParaValues("system");

		try{
			//新增用户
			Record i=userService.saveUser(username,password,dept_id,realName,mobile);
			//新增关联表
			if (i!=null){
				userService.saveUserRoleSys(i.get("id").toString(),system);
			}
			setAttr("isok","success");
		}catch (Exception e){
			setAttr("isok","err");
		}
		renderJson();
	}


	//进入修改窗时填充数据
	public void finduserByid(){
		String id=getPara("id");
		//查询账户基本信息
		Record record=userService.finduserByid(id);
		setAttr("user",record);
		//查询中间表信息
		List<Record> user_role=userService.findUserRoleSystemByid(id);
		setAttr("user_role",user_role);
		renderJson();
	}

	//修改账户
	public void updateUser(){
		String id=getPara("id");
		String username=getPara("username","");
		String password=getPara("password","");
		String dept_id=getPara("dept","");
		String status=getPara("status","");
		String realName=getPara("realName","");
		String mobile=getPara("mobile","");
		String[] system=getParaValues("system");
		try{
			userService.updateUsers(id,username,password,dept_id,status,realName,mobile);
			userService.updateuserRole(id,system);
			setAttr("isok","success");
		}catch (Exception e){
			setAttr("isok","err");
		}
		renderJson();
	}

	public void verificationName(){
		String username=getPara("username","");
		int id=getParaToInt("id",0);
		int usernameLen=userService.verificationName(username,id);
		renderJson(usernameLen);
	}
}
