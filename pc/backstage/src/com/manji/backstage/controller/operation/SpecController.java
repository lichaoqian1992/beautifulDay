package com.manji.backstage.controller.operation;

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

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.operation.SpecService;
import com.manji.backstage.utils.ManjiText;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.operation.Category;
import com.manji.backstage.model.operation.CategorySpec;
import com.manji.backstage.model.operation.Spec;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.operation.SpecListVo;
import com.manji.backstage.vo.operation.SpecVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.manji.backstage.vo.operation.CategorySpecVo;
import com.manji.backstage.vo.operation.SpecAddVo;
@Controller
public class SpecController extends BaseController{

	@Autowired
	private SpecService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("operation");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	/**
	 * 页面--规格列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/selSpec")
	public String specList(HttpServletRequest req){
		
		return "operation/operation_spec_list";
	}
	
	/**
	 * ajax--查询商品规格列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/querySpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySpecList(HttpServletRequest req){
		
		String category_id =req.getParameter("category_id");
		
		Message msg =createMsg();
		
		List<Category> categoryList =service.getCategoryList(category_id);
		
		Map<String,String> cateMenu =new HashMap<String,String>();
		
		List<CategorySpecVo> specList =new ArrayList<CategorySpecVo>();
		
		for(int i =0;i<categoryList.size();i++){
			
			Category cate =categoryList.get(i);
			
			switch(cate.getClass_layer()){
				
			case 1:
				cateMenu.put(cate.getId()+"", cate.getTitle());
				break;
			case 2:
				cateMenu.put(cate.getId()+"", cate.getTitle());
				break;
			case 3:
				
				CategorySpecVo csv =new CategorySpecVo();
				
				String layerStr =cate.getClass_list();
				
				String[] layer=layerStr.split(",");
				
				csv.setFirstId(layer[1]);
				csv.setFirstName(cateMenu.get(layer[1]));
				csv.setSecondId(layer[2]);
				csv.setSecondName(cateMenu.get(layer[2]));
				csv.setThirdId(cate.getId()+"");
				csv.setThirdName(cate.getTitle());
				
				List<Spec> specs =service.getSpecs(cate.getId());
				
				String  specStr="";
				
				for(int k=0;k<specs.size();k++){
					if(k!=0){
						specStr+=",";
					}
					Spec s =specs.get(k);
					specStr+=s.getTitle();
				}
				
				csv.setSpecStr(specStr);
				specList.add(csv);
				
				break;
			}
		}
		
		msg.setResult(Array(specList));
		
		return Json(msg);
	}
	
	/**
	 * 页面--查看商品规格详情
	 * @param req
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/readSpec")
	public String readSpec(HttpServletRequest req,int category_id){
		
		List<Spec> specs =service.getSpecs(category_id);
		
		String categoryJson =service.getCategorySV(category_id);
		
		List<SpecListVo> specList =new ArrayList<SpecListVo>();
		
		for(int i=0;i<specs.size();i++){
			SpecListVo slv =new SpecListVo();
			
			Spec s =specs.get(i);
			
			slv.setId(s.getId());
			slv.setName(s.getTitle());

			List<Spec> secondSpecs =service.getSecondSpecs(s.getId());
			
			slv.setSpecList(secondSpecs);
			
			specList.add(slv);
		}
		
		req.setAttribute("catagorymap", categoryJson);
		req.setAttribute("speclist", Array(specList));
		req.setAttribute("category_id", category_id);
		
		return "operation/operation_spec_detail";
	}
	
	
	/**
	 * 页面--修改商品规格
	 * @param req
	 * @return
	 */
	@RequestMapping("/chgSpec")
	public String chgSpec(HttpServletRequest req){

		String category_id =req.getParameter("category_id");
//		System.out.println(category_id);
		List<Spec> specs =service.getSpecs(Integer.valueOf(category_id));
		String categoryJson =service.getCategorySV(Integer.valueOf(category_id));
		
		
		List<SpecListVo> specList =new ArrayList<SpecListVo>();
		
		for(int i=0;i<specs.size();i++){
			SpecListVo slv =new SpecListVo();
			
			Spec s =specs.get(i);
			
			slv.setId(s.getId());
			slv.setName(s.getTitle());

			List<Spec> secondSpecs =service.getSecondSpecs(s.getId());
			
			slv.setSpecList(secondSpecs);
			
			specList.add(slv);
		}
		
		req.setAttribute("catagorymap", categoryJson);
		req.setAttribute("speclist", Array(specList));
		req.setAttribute("category_id", category_id);
		
		return "operation/operation_spec_upd";
	}
	
	
	
	
	
	
	
	/**
	 * ajax--修改规格
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/updSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSpec(HttpServletRequest req){
		
		Message msg =createMsg();
		String id=req.getParameter("id");
		String title =req.getParameter("title");
		if(id ==null||"".equals(title)){
			msg.setStatus("1");
			msg.setResult("修改失败");
			return Json(msg);
		}
		
		
		if(service.updSpecTitle(id,title)){
			msg.setStatus("0");
			msg.setResult(title);
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
		}
		
		return Json(msg);
	}
	
	
	@RequestMapping(value ="/addMainSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addMainSpec(HttpServletRequest req){
		
		Message msg =createMsg();

		String title =req.getParameter("name");
		String values =req.getParameter("values");
		
		values =ManjiText.replaceText(values);
		System.out.println(values);
		String category_id =req.getParameter("categoryid");
		
		Spec s =new Spec();
		s.setTitle(title);
		s.setParent_id(0);
		s.setSort_id(99);
		s.setType(1);
		s.setRemark("");
		service.addMainSpec(s);
		int id =s.getId();
		
		String[] value =values.split(",");
		
		for(int i=0;i<value.length;i++){
		
			Spec ss =new Spec();
			ss.setParent_id(id);
			ss.setSort_id(99);
			ss.setImg_url("");
			ss.setTitle(value[i]);
			
			service.addSecondSpec(ss);
			
		}
		
		CategorySpec cs =new CategorySpec();
		
		cs.setCategory_id(Integer.valueOf(category_id));
		cs.setSpec_id(id);
		
		service.insertCategorySpec(Integer.valueOf(category_id), id);
		
		msg.setStatus("0");
		msg.setResult(s);
		
		return Json(msg);
	}
	
	
	
	@RequestMapping(value ="/addSecondSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSecondSpec (HttpServletRequest req){
		
		Message msg =createMsg();
		
		String spec_id =req.getParameter("spec_id");
		String title =req.getParameter("title");
		System.out.println(spec_id+title);
		Spec s =new Spec();
		s.setParent_id(Integer.valueOf(spec_id));
		s.setTitle(title);
		s.setSort_id(99);
		s.setImg_url("");
		
		if("".equals(title)){
			msg.setStatus("1");
			msg.setResult("新增失败");
		}
		
		if(service.addSecondSpec(s)){
			msg.setStatus("0");
			msg.setResult("新增成功");
		}else{
			msg.setStatus("1");
			msg.setResult("新增失败");
		}
		
		return Json(msg);
	}
	
	
	/**
	 *页面--新增商品规格 
	 * @param req
	 * @return
	 */
	@RequestMapping("/insSpec")
	public String insSpec(HttpServletRequest req){
		
		return "operation/operation_spec_add";
	}
	
	
	
	
	/**
	 * ajax--新增规格
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/addSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSpec(HttpServletRequest req,SpecAddVo vo){
		Message msg =createMsg();
		System.out.println(Json(vo));
		
		JSONObject obj =JSONObject.fromObject(vo.getJson());
		
		JSONArray picObj =obj.getJSONArray("img_list");
		JSONArray otherObj= obj.getJSONArray("other_list");
		
		int category_id=vo.getCategory_id();
		
		
		if(service.checkCate(category_id)){
			msg.setStatus("1");
			msg.setResult("该分类下已存在规格，请到分类修改中修改。");
			return Json(msg);
			
		}
		
		if(picObj!=null){
			for(int i=0;i<picObj.size();i++){
				JSONObject oobj =picObj.getJSONObject(i);
				String title =oobj.getString("name");
				String nums =oobj.getString("num");
				nums =ManjiText.replaceText(nums);
				
				
				
				Spec s =new Spec();
				s.setTitle(title);
				s.setParent_id(0);
				s.setSort_id(99);
				s.setType(1);
				s.setRemark("");
				
				service.addMainSpec(s);
				
				int mainId =s.getId();
				System.out.println(mainId);
				
				String[] valuesArray =nums.split(",");
				
				for(int k=0;k<valuesArray.length;k++){
					
					Spec ss =new Spec();
					
					ss.setSort_id(99);
					ss.setImg_url("");
					ss.setTitle(valuesArray[k]);
					ss.setParent_id(mainId);
					service.addSecondSpec(ss);
					
				}
				
				
				service.insertCategorySpec(category_id, mainId);
			}
			
			
		}else{
			msg.setStatus("2");
			msg.setResult("图片规格必须有一个！");
			return Json(msg);
		}
		
		
		if(otherObj!=null){
			
			for(int i=0;i<otherObj.size();i++){
				JSONObject oobj =otherObj.getJSONObject(i);
				String title =oobj.getString("name");
				String nums =oobj.getString("num");
				nums =ManjiText.replaceText(nums);
				
				Spec s =new Spec();
				s.setTitle(title);
				s.setParent_id(0);
				s.setSort_id(99);
				s.setType(1);
				s.setRemark("");
				
				service.addMainSpec(s);
				
				int mainId =s.getId();
				System.out.println(mainId);
				String[] valuesArray =nums.split(",");
				
				for(int k=0;k<valuesArray.length;k++){
					
					Spec ss =new Spec();
					
					ss.setSort_id(99);
					ss.setImg_url("");
					ss.setTitle(valuesArray[k]);
					ss.setParent_id(mainId);
					service.addSecondSpec(ss);
				}
				
				service.insertCategorySpec(category_id, mainId);
			}
		}
		
		msg.setStatus("0");
		return Json(msg);
	}
	
	
	
	/**
	 * ajax--删除规格
	 * @return
	 */
	@RequestMapping(value ="/delMainSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delMainSpec(HttpServletRequest req,int category_id,int spec_id){
		Message msg =createMsg();
		
		CategorySpec cs =new CategorySpec();
		cs.setCategory_id(category_id);
		cs.setSpec_id(spec_id);

		if(service.delMainSpec(cs)){
			
			msg.setStatus("0");
			msg.setResult("删除成功");
		}else{
			msg.setStatus("1");
			msg.setResult("删除失败");
		}
		
		return Json(msg);
	}
	
	
	@RequestMapping(value ="/delSecondSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delSecondSpec(HttpServletRequest req,int spec_id){
		Message msg =createMsg();
		
		Spec s =new Spec();
		s.setId(spec_id);
		
		if(service.delSecondSpec(s)){
			msg.setStatus("0");
			msg.setResult("删除成功");
		}else{
			msg.setStatus("1");
			msg.setResult("删除失败");
		}
		
		return Json(msg);
	}
	
}
