package com.manji.backstage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.ResultCode;
import com.manji.backstage.model.oper.Attri;
import com.manji.backstage.model.oper.Category;
import com.manji.backstage.service.AttrService;
import com.manji.backstage.utils.ManjiText;
import com.manji.backstage.vo.operation.CategoryAttriVo;

import net.sf.json.JSONObject;
@Controller
public class AttrController extends BaseController{
	@Autowired
	private AttrService service;
	
	/**
	 * 页面-商品属性列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/selAttri")
	public String AttriList(HttpServletRequest req){
		
		return "operation/operation_attri_list";
	}
	
	/**
	 * AJAX-根据商品类别查询商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/queryAttri", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAttriList(HttpServletRequest req){
		
		Message msg =createMsg();
		String content =req.getParameter("content");
		String category_id =req.getParameter("category_id");
		
		List<Category> categoryList =service.getCategoryList(category_id);
		
		Map<String,String> catemenu =new HashMap<String,String>();

		List<CategoryAttriVo> attriList =new ArrayList<CategoryAttriVo>();
		
		for(int i =0;i<categoryList.size();i++){
			
			
			Category cate =categoryList.get(i);
			
			switch(cate.getClass_layer()){
			
			case 1:
				catemenu.put(cate.getId()+"", cate.getTitle());
				break;
			case 2:
				catemenu.put(cate.getId()+"", cate.getTitle());
				break;
			case 3:
				CategoryAttriVo cav =new CategoryAttriVo();
			
				String layerStr =cate.getClass_list();
				String[] layer=layerStr.split(",");
				Attri attri =service.getAttri(cate.getId(),content);
				if (null != attri) {

					cav.setFirst_id(layer[1]);
					cav.setFirst_name(catemenu.get(layer[1]));
					cav.setSecond_id(layer[2]);
					cav.setSecond_name(catemenu.get(layer[2]));
					cav.setThird_id(cate.getId()+"");
					cav.setThird_name(cate.getTitle());
					cav.setAttri_id(attri.getId());
					cav.setAttri_str(attri.getContent());
					attriList.add(cav);
				} 
				break;
			}
			
		}
		
		msg.setResult(attriList);
		
		return Json(msg);
	}
	
	/**
	 * 页面-查询单个类别商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping("/readAttri")
	public String readAttri(HttpServletRequest req){
		
		String attri_id =req.getParameter("attri_id");
		
		Attri attri =service.getAttriById(Integer.valueOf(attri_id));
		
		int category_id =attri.getCategory_id();
		
		
		String  cateString =service.getCategoryCav(category_id);
		
		String attris =ManjiText.attriJson(attri.getContent());
		
		req.setAttribute("catagoryinfo", cateString);
		req.setAttribute("attris", attris);
		req.setAttribute("attri_id", attri_id);
		
		return "operation/operation_attri_detail";
	}

	/**
	 *页面-添加商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping("/insAttri")
	public String insAttri(HttpServletRequest req){
		
		return "operation/operation_attri_add";
	}
	
	/**
	 * AJAX-添加商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/addAttri", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAttri(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String category_id =req.getParameter("category_id");
		
		String content =req.getParameter("content");
		content =ManjiText.replaceText(content);
		Attri attri =service.getContentByCateId(Integer.valueOf(category_id));
		if(attri!=null){
			msg.setStatus(ResultCode.OPERATION_ATTRI_ADD_FAULT);
			msg.setResult(ResultCode.OPERATION_ATTRI_ADD_FAULT_TEXT);
		}else{
			service.addAttri(Integer.valueOf(category_id),content);
			msg.setStatus(ResultCode.OPERATION_ATTRI_ADD_SUCCESS);
			msg.setResult(ResultCode.OPERATION_ATTRI_ADD_SUCCESS_TEXT);
		};
		return Json(msg);
	}
	
	
	/**
	 * 页面-修改商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping("/chgAttri")
	public String chgAttri(HttpServletRequest req){
		
		String attri_id =req.getParameter("attri_id");
		
		Attri attri =service.getAttriById(Integer.valueOf(attri_id));
		
		int category_id =attri.getCategory_id();
		
		String  cateString =service.getCategoryCav(category_id);
		
		String attriJson =ManjiText.attriJson(attri.getContent());
		
		req.setAttribute("attr", Json(attri));
		req.setAttribute("catagoryinfo", cateString);
		req.setAttribute("attrijson", attriJson);
		req.setAttribute("attri_id", attri.getId());
		
		return "operation/operation_attri_upd";
	}
	
	/**
	 * AJAX-修改商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/updAttri", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAttri(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String attri_id =req.getParameter("attri_id");
		
		String content =req.getParameter("content");
		content =ManjiText.replaceText(content);
		if(service.updAttri(Integer.valueOf(attri_id),content)){
			msg.setStatus(ResultCode.OPERATION_ATTRI_UPDATE_SUCCESS);
			msg.setResult(ResultCode.OPERATION_ATTRI_UPDATE_SUCCESS_TEXT);
		}else{
			msg.setStatus(ResultCode.OPERATION_ATTRI_UPDATE_FAULT);
			msg.setResult(ResultCode.OPERATION_ATTRI_UPDATE_FAULT_TEXT);
		}
		
		return Json(msg);
	}
	
	/**
	 * AJAX-删除商品属性
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/delAttri", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delAttri(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String attri_id =req.getParameter("attri_id");
		
		if(service.delAttri(Integer.valueOf(attri_id))){
			
			msg.setStatus(ResultCode.OPERATION_ATTRI_DELETE_SUCCESS);
			msg.setResult(ResultCode.OPERATION_ATTRI_DELETE_SUCCESS_TEXT);
		}else{
			msg.setStatus(ResultCode.OPERATION_ATTRI_DELETE_FAULT);
			msg.setResult(ResultCode.OPERATION_ATTRI_DELETE_FAULT_TEXT);
		}
		
		return Json(msg);
	}
}
