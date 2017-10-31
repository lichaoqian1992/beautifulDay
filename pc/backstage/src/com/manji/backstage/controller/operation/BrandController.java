package com.manji.backstage.controller.operation;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.operation.BrandService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.operation.BrandVo;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.operation.Brand;



@Controller
public class BrandController extends BaseController{

	@Autowired
	private BrandService service;
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
//	/**
//	 * 页面-品牌列表
//	 * @param req
//	 * @return
//	 */
//	@RequestMapping("/selBrand")
//	public String brandList(HttpServletRequest req){
//		
//		List<Brand> brandList =service.getBrandList();
//		
//		req.setAttribute("brandlist", Array(brandList));
//		
//		return "operation/operation_brand_list";
//	}
//	
//	/**
//	 * ajax——模糊查询品牌
//	 * @param req
//	 * @param brand
//	 * @return
//	 */
//	@RequestMapping(value ="/queryBrand", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String queryBrandList(HttpServletRequest req,Brand brand){
//		
//		Message msg =createMsg();
//		
//		List<Brand> brandList =service.queryBrandList(brand);
//		
//		if(brandList.size()>0){
//			msg.setStatus("0");
//			msg.setResult(brandList);
//		}else{
//			msg.setStatus("1");
//			msg.setResult("查无结果");
//		}
//		
//		return Json(msg);
//	}
//	
	
	
	/**
	 * 页面-品牌列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/selBrand")
	public String brandList(HttpServletRequest req,BrandVo vo){
		
		return "operation/operation_brand_list";
	}
	

	/**
	 * ajax——模糊查询品牌
	 * @param req
	 * @param brand
	 * @return
	 */
	@RequestMapping(value ="/queryBrand", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBrandList(HttpServletRequest req,BrandVo vo){
		
		Message msg =createMsg();
		
		Page<Brand> page =service.getBrand(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			msg.setResult("查无结果");
		}
		
		return Json(msg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 页面——新增品牌
	 * @param req
	 * @return
	 */
	@RequestMapping("/insBrand")
	public String insBrand(HttpServletRequest req){
		
		
		return "operation/operation_brand_add";
	}
	
	/**
	 * 接口--新增品牌
	 * @param req
	 * @return
	 */

//	@RequestMapping(value ="/addBrand", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
//	@ResponseBody
	@RequestMapping(value="/addBrand" ,method = RequestMethod.POST)
	public String addBrand(HttpServletRequest req,Brand brand,@RequestParam MultipartFile file){
		
		Message msg =createMsg();
		String brandName =brand.getName();
		if("".equals(brandName)){
			return "operation/operation_brand_add";
		}
		String fileName =file.getOriginalFilename();
		String base64Str =Base64Utils.GetBase64Code(file);
		
		String address =service.sendPostReq(fileName,base64Str);
		
		brand.setLogo(address);
		String time =TimeUtils.getCurrentTime();
		brand.setAdd_time(time);
		brand.setUpdate_time(time);
		service.addBrand(brand);
		msg.setStatus("0");
		msg.setResult("插入成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(brand),LogRemark.ADDBRAND);
		
		return "operation/operation_brand_add";
	}
	
	@RequestMapping(value ="/checkBrandName", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkBrandName(HttpServletRequest req){
		
		String brandName =req.getParameter("brandname");
		
		Message msg =createMsg();
		if(service.checkBrandName(brandName)){
			msg.setStatus("1");
			msg.setResult("该品牌名已存在!");
			
		}else{
			msg.setStatus("0");
			msg.setResult("该品牌名无重复");
		}
		
		
		return Json(msg);
	}
	
	
	@RequestMapping(value ="/uploadBrandPic", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadPic(@RequestParam MultipartFile file){
		
		String fileName =file.getOriginalFilename();
		String base64Str =Base64Utils.GetBase64Code(file);
		
		String address =service.sendPostReq(fileName,base64Str);
		
		
		return address;
	}
	
	
	/**
	 * 页面——查看品牌详情
	 * @param req
	 * @return
	 */
	@RequestMapping("/readBrand")
	public String readBrand(HttpServletRequest req){
		
		String id =req.getParameter("id");
		Brand brand =service.getBrandById(Integer.valueOf(id));
		
		req.setAttribute("branditem", Json(brand));
		req.setAttribute("brand_id", id);
		return "operation/operation_brand_detail";
	}
	
	/**
	 * 页面--修改品牌详情
	 * @param req
	 * @return
	 */
	@RequestMapping("/chgBrand")
	public String chgBrand(HttpServletRequest req){
		
		String id =req.getParameter("id");
		Brand brand =service.getBrandById(Integer.valueOf(id));
		
		req.setAttribute("branditem", Json(brand));
		
		return "operation/operation_brand_upd";
	}
	
	/**
	 * 接口--修改品牌
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/updBrand", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView  updBrand(HttpServletRequest req,Brand brand,@RequestParam MultipartFile file){
		req.removeAttribute("branditem1");
		String id =req.getParameter("id");
		Brand brand1 =service.getBrandById(Integer.valueOf(id));
		
		saveLog(createLog(req),LogRemark.UPD,Json(brand1),LogRemark.UPDBRAND);
		req.setAttribute("branditem", Json(brand1));
		ModelAndView view=new ModelAndView("/operation/operation_brand_upd");
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName =file.getOriginalFilename();
			String base64Str =Base64Utils.GetBase64Code(file);
			
			String address =service.sendPostReq(fileName,base64Str);
			
			brand.setLogo(address);
			
		}
		
		if(service.updBrand(brand)){
			
			msg.setStatus("0");
			msg.setResult("修改成功");
			
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
			
		}
		view.addObject("msg",msg);
		
		return view;
	}
	
	
	
	/**
	 * 接口--删除品牌
	 * @param req
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/delBrand", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delBrand(HttpServletRequest req,String id){
		
		Message msg =createMsg();
		Brand brand1 =service.getBrandById(Integer.valueOf(id));
		
		saveLog(createLog(req),LogRemark.DEL,Json(brand1),LogRemark.DELBRAND);
		if(service.delBrand(id)){
			msg.setStatus("0");
			msg.setResult("删除成功");
		}else{
			msg.setStatus("1");
			msg.setResult("删除失败");
		}
		
		
		return Json(msg);
	}
	

	
}
