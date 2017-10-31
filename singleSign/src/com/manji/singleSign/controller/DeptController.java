package com.manji.singleSign.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.Result;
import com.manji.singleSign.model.bean.Dept;
import com.manji.singleSign.service.DeptService;

public class DeptController extends Controller {

	private DeptService service =new DeptService();
	
	
	
	public void index() {

		
		render("list.html");

	}

	public void query() {

		int page=getParaToInt("page");
		String dept_name=getPara("dept_name");

		Page<Record> deptList =service.findAllDept(page,dept_name);
		setAttr("deptList",deptList);
		
		renderJson();
		
	}

	public void detail() {

		String deptId =getPara("id");
		
		Record dept =service.findDept(deptId);
		
		renderJson(dept);
	}


	//新增部门
	public void add() {

		Dept d =getBean(Dept.class,"");
		try {
			service.addDept(d);
			setAttr("isok","success");
		}catch (Exception e){
			setAttr("isok","err");
		}
		renderJson();
	}

	//进入修改时填充数据
	public void findDeptByid(){
		String dept_id=getPara("dept_id");
		Record record=service.findDeptById(dept_id);

		setAttr("dept",record);
		renderJson();
	}

	//执行修改
	public void update() {
		Dept d =getBean(Dept.class,"");
		try{
			service.updateDept(d);
			setAttr("isok","success");
		}catch (Exception e){
			setAttr("isok","err");
		}
		renderJson();
	}

	

}
