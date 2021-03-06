package com.manji.sheet.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.intercepter.LocalIntercepter;
import com.manji.sheet.service.BaseService;
import com.manji.sheet.utils.WorkerNumberUtil;
/**
 * 基础配置控制层
 * @author Administrator
 *
 */
@Before(LocalIntercepter.class)
public class BaseController extends Controller{

	BaseService service = new BaseService();
	public void index(){
		render("type_index.html");
	}
	/**
	 * 工单类型
	 */
	public void toType(){
		String code = getPara("code");
		setAttr("title", "工单类型");
		if(code.equals("01") || code.equals("02") || code.equals("03")){
			render("type_select.html");
		}else{
			render("type_index.html");
		}
	}
	/**
	 * 交易纠纷
	 */
	public void toDispute(){
		render("type_dispute.html");

	}
	/**
	 * 举报类型
	 */
	public void findJB(){
		setAttr("title", "举报工单");
		render("type_select.html");
	}
	/**
	 * 查询所有工单层级为1 的工单类型
	 */
	public void findSheetType(){
		
		List<Record> list = service.findSheetType();
		renderJson(list);
		
	}
	/**
	 * 根据工单类型的code查询下面的子分类
	 */
	@SuppressWarnings("static-access")
	public void findSheetTypeByCode(){
		String title = "";
		String code = getPara("code");
		List<Record> list = new WorkerNumberUtil().getcodeReport(code);
		setAttr("info", list);
		setAttr("code", code);
		if(code.equals("07")){
			title = "评价纠纷工单";
		}else if(code.equals("06")){
			title = "交易纠纷工单";
		}else if(code.equals("05")){
			title = "建议工单";
		}else if(code.equals("04")){
			title = "咨询工单";
		}else if(code.equals("03")){
			title = "举报满集员工工单";
		}else if(code.equals("02")){
			title = "举报商家工单";
		}else if(code.equals("01")){
			title = "举报商品工单";
		}else if(code == null || "".equals(code)){
			title = "举报工单";
		}
		setAttr("title", title);
		render("type_dispute.html");
	}
	/**
	 * 根据工单类型编号禁用或者启用工单类型
	 */
	public void disable(){
		String message = "";
		int id = getParaToInt("id");
		int status = getParaToInt("status");
		int num = service.disable(id, status,"dt_sheet_type");
		if(num > 0 ){
			message = "SUCCESS";
		}else{
			message = "ERROR";
		}
		setAttr("message", message);
		renderJson();
	}

	/**
	 * 新增举报类型
	 */
	public void addSheetType(){
		String code = getPara("code");
		String title = getPara("title");
		int sort = getParaToInt("sort");
		Boolean flag = service.addSheetType(code, title, sort);
		if(flag){
			setAttr("message", "SUCCESS");
		}else{
			setAttr("message", "FAIL");
		}
		renderJson();
	}
	/**
	 * 修改举报类型
	 */
	public void updateSheetType(){
		
		int id = getParaToInt("id");
		String title = getPara("title");
		int sort = getParaToInt("sort");
		int num = service.updateSheetType(id, title, sort);
		if(num > 0){
			setAttr("message", "SUCCESS");
		}else{
			setAttr("message", "FAIL");
		}
		renderJson();
	}
	/**
	 * 部门列表
	 */
	public void toDept(){
		List<Record> list = service.findDept(3,"","");
		setAttr("info", list);
		setAttr("title", "部门管理");
		render("department_list.html");
	}
	/**
	 * 查询部门信息
	 */
	public void findDept(){
		
		int status = getParaToInt("status");
		String type = getPara("type"); 
		String name = getPara("name");
		List<Record> list = service.findDept(status,type,name);
		renderJson(list);
	}
	/**
	 * 禁用或启用部门
	 */
	public void isDone(){
		String message = "";
		int id = getParaToInt("id");
		int status = getParaToInt("status");
		int num = service.disable(id, status,"dt_base_dept");
		if(num > 0 ){
			message = "SUCCESS";
		}else{
			message = "ERROR";
		}
		setAttr("message", message);
		renderJson();
	}
	
	
	public void updateDept(){
		
		String id = getPara("id");
		String name = getPara("name").replace("*", "");
		String type = getPara("type");
		int sort = 1;
		if(type.equals("监督")){
			sort = 1;
		}else{
			sort = 2;
		}
		int num = service.updateDept(id, name, sort);
		if(num > 0){
			setAttr("message", "SUCCESS");
		}else{
			setAttr("message", "FAIL");
		}
		renderJson();
		
	}
}
