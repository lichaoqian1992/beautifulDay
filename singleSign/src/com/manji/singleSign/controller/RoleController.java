package com.manji.singleSign.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.Result;
import com.manji.singleSign.model.bean.Role;
import com.manji.singleSign.model.query.RoleVo;
import com.manji.singleSign.service.MenuService;
import com.manji.singleSign.service.RoleService;

import net.sf.json.JSONObject;

public class RoleController  extends Controller{

	
	private RoleService service =new RoleService();
	
	private MenuService serviceM =new MenuService();
	
	public void index(){
		
		List<Record> sysList =service.getSysList();
		
		setAttr("sysList",sysList);
		RoleVo rv = new RoleVo();
		rv.setIndex("1");
		Page<Record> list =service.queryRoles(rv);
		
		setAttr("systemList",list);
		
		List<Record> roleList = service.role();
		setAttr("roleList", roleList);
		
		//获取角色信息
		List<Record> roleListM = serviceM.role();
		setAttr("roleListM", roleListM);
		
		List<Record> sysListM =serviceM.getSysList();
		setAttr("sysListM",sysListM);
		
		render("list.html");
		
	}
	
	public void addMenu(){
		
		//获取角色信息
		List<Record> roleList = service.role();
		setAttr("roleList", roleList);
		
		List<Record> sysList =service.getSysList();
		setAttr("sysList",sysList);
		
		render("role_add.html");
	}
	/**
	 * 修改权限
	 */
	public void editRole(){
		String role_id = getPara("role_id");
		String sys_id = getPara("sys_id");
		//获取角色信息
		Record role = service.findRole(role_id);
		//查询权限信息
		List<Record> menu_role = service.findMenu(role_id);
		//查询系统信息
		List<Record> sys =service.getSysList();
		//查询系统信息
		List<Record> menu =service.getMenu(sys_id);
		
		setAttr("role", role);
		setAttr("menu_role", menu_role);
		setAttr("sys", sys);
		setAttr("menu", menu);
		render("role_edit.html");
	}
	/**
	 * 添加权限
	 */
	public void addRole(){
		
		String idList = getPara("idList");
		String role_name = getPara("role_name");
		String name = getPara("name");
		String sys_id = getPara("sys_id");
		
		int a = service.add(idList,sys_id,role_name,name);
		if(a > 0){
			setAttr("message", "SUCCESS");
		}else{
			setAttr("message", "ERROR");
		}
		renderJson();
	}
	/**
	 * 修改权限
	 */
	public void updateRole(){
		String idList = getPara("idList");
		String role_id = getPara("role_id");
		String role_name = getPara("role_name");
		String name = getPara("name");
		String sys_id = getPara("sys_id");
		int a = service.updateRole(role_id,idList,sys_id,role_name,name);
		if(a > 0){
			setAttr("message", "SUCCESS");
		}else{
			setAttr("message", "ERROR");
		}
		renderJson();
		
	}
	public void query(){
		
		RoleVo rv =getBean(RoleVo.class,"");
		
		Page<Record> page =service.queryRoles(rv);
		renderJson(page);
		
		/*Result r =new Result();
		
		if(page.getTotalRow()!=0){
			
			r.setCode("0000");
			r.setData(JSONObject.fromObject(page));
		}else{
			
			r.setCode("0001");
			r.setData("查无数据");
		}
		
		renderJson(r);*/
	}
	
	public void add(){
		
		Role role =getBean(Role.class,"");
		
		service.addRole(role);
		
		redirect("/role/index");
		/*Result r =new Result();
		
		r.setCode("0000");
		r.setData("新增成功");
		
		renderJson(r);*/
		
	}
	
	public void detail(){
		
		String id =getPara("id");
		
		Result r =new Result();
		
		Record role =service.getRole(id);
		
		r.setCode("0000");
		r.setData(role);
		
		renderJson(r);
		
		
	}
	
	
	
	
}
