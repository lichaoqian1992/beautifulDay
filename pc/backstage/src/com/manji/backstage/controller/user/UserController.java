package com.manji.backstage.controller.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.user.BankType;
import com.manji.backstage.model.user.CompanyInfo;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserFavorites;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.user.UserService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.user.BankTypeVo;
import com.manji.backstage.vo.user.CompanyInfoVo;
import com.manji.backstage.vo.user.PersonInfoVo;
import com.manji.backstage.vo.user.RecAddrVo;
import com.manji.backstage.vo.user.UserFavoritesVo;
import com.manji.backstage.vo.user.UserVo;
@Controller
public class UserController extends BaseController{

	@Autowired
	private UserService service;
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

	
	
	
	@RequestMapping("/selUser")
	public String selUser(HttpServletRequest req){
		
		return "user/user_list";
	}
	@RequestMapping(value ="/queryUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUser(HttpServletRequest req,UserVo vo){
		Message msg =createMsg();
		
		Page<User> page =service.queryUser(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUser")
	public String insUser(HttpServletRequest req){
		
		return "user/user_add";
	}
	@RequestMapping(value ="/addUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUser(HttpServletRequest req,User user){
		Message msg =createMsg();
		user.setReg_time(TimeUtils.getCurrentTime());
		service.addUser(user);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(user),LogRemark.ADDUSER);
		return Json(msg);
		
	}
	@RequestMapping("/readUser")
	public String readUser(HttpServletRequest req,int id){
		User user =service.getUser(id);
		req.setAttribute("userinfo", Json(user));
		return "user/user_upd";
	}
	@RequestMapping(value ="/updUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUser(HttpServletRequest req,User user){
		Message msg =createMsg();
		User u =service.getUser(user.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(u),LogRemark.UPDUSER);

		if(service.updUser(user)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest req,int id){
		User u =service.getUser(id);
		saveLog(createLog(req),LogRemark.DEL,Json(u),LogRemark.DELUSER);
		if(service.delUser(id)){
			
		}
		return "redirect:/selUser";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selRecAddr")
	public String selRecAddr(HttpServletRequest req){
		
		return "user/user_recaddr_list";
	}
	@RequestMapping(value ="/queryRecAddr", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRecAddr(HttpServletRequest req,RecAddrVo vo){
		Message msg =createMsg();
		
		Page<RecAddr> page =service.queryRecAddr(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRecAddr")
	public String insRecAddr(HttpServletRequest req){
		
		return "user/user_recaddr_add";
	}
	@RequestMapping(value ="/addRecAddr", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRecAddr(HttpServletRequest req,RecAddr ra){
		Message msg =createMsg();
		ra.setAdd_time(TimeUtils.getCurrentTime());
		
		service.addRecAddr(ra);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ra),LogRemark.ADDRECADDR);
		return Json(msg);
		
	}
	@RequestMapping("/readRecAddr")
	public String readRecAddr(HttpServletRequest req,int id){
		RecAddr ra =service.getRecAddr(id);
		req.setAttribute("rainfo", Json(ra));
		return "user/user_recaddr_upd";
	}
	@RequestMapping(value ="/updRecAddr", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRecAddr(HttpServletRequest req,RecAddr ra){
		
		Message msg =createMsg();
		RecAddr r =service.getRecAddr(ra.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(r),LogRemark.UPDRECADDR);
		if(service.updRecAddr(ra)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRecAddr")
	public String delRecAddr(HttpServletRequest req,int id){
		Message msg =createMsg();
		RecAddr r =service.getRecAddr(id);
		saveLog(createLog(req),LogRemark.DEL,Json(r),LogRemark.DELRECADDR);
		if(service.delRecAddr(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selRecAddr";
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/selUserFavorites")
	public String selUserFavorites(HttpServletRequest req){
		
		return "user/user_favorites_list";
	}
	@RequestMapping(value ="/queryUserFavorites", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserFavorites(HttpServletRequest req,UserFavoritesVo vo){
		Message msg =createMsg();
		
		Page<UserFavorites> page =service.queryUserFavorites(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserFavorites")
	public String insUserFavorites(HttpServletRequest req){
		
		return "user/user_favorites_add";
	}
	@RequestMapping(value ="/addUserFavorites", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserFavorites(HttpServletRequest req,UserFavorites uf){
		Message msg =createMsg();
		uf.setAdd_times(TimeUtils.getCurrentTime());
		service.addUserFavorites(uf);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(uf),LogRemark.ADDUSERFAVORITES);
		return Json(msg);
		
	}
	@RequestMapping("/readUserFavorites")
	public String readUserFavorites(HttpServletRequest req,int id){
		UserFavorites uf =service.getUserFavorites(id);
		req.setAttribute("ufinfo", Json(uf));
		return "user/user_favorites_upd";
	}
	@RequestMapping(value ="/updUserFavorites", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserFavorites(HttpServletRequest req,UserFavorites uf){
		
		Message msg =createMsg();
		UserFavorites u =service.getUserFavorites(uf.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(u),LogRemark.UPDUSERFAVORITES);
		if(service.updUserFavorites(uf)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserFavorites")
	public String delUserFavorites(HttpServletRequest req,int id){
		Message msg =createMsg();
		UserFavorites u =service.getUserFavorites(id);
		saveLog(createLog(req),LogRemark.DEL,Json(u),LogRemark.DELUSERFAVORITES);
		if(service.delUserFavorites(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selUserFavorites";
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selPersonInfo")
	public String selPersonInfo(HttpServletRequest req){
		
		return "user/user_person_list";
	}
	@RequestMapping(value ="/queryPersonInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPersonInfo(HttpServletRequest req,PersonInfoVo vo){
		Message msg =createMsg();
		
		Page<PersonInfo> page =service.queryPersonInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insPersonInfo")
	public String insPersonInfo(HttpServletRequest req){
		
		return "user/user_person_add";
	}
	@RequestMapping(value ="/addPersonInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addPersonInfo(HttpServletRequest req,PersonInfo pi){
		Message msg =createMsg();
		pi.setPerson_brithday(TimeUtils.getCurrentTime());
		service.addPersonInfo(pi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(pi),LogRemark.ADDPERSONINFO);
		return Json(msg);
	}
	@RequestMapping("/readPersonInfo")
	public String readPersonInfo(HttpServletRequest req,int id){
		PersonInfo pi =service.getPersonInfo(id);
		req.setAttribute("personinfo", Json(pi));
		return "user/user_person_upd";
	}
	@RequestMapping(value ="/updPersonInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPersonInfo(HttpServletRequest req,PersonInfo pi){
		Message msg =createMsg();
		PersonInfo p =service.getPersonInfo(pi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(p),LogRemark.UPDPERSONINFO);
		if(service.updPersonInfo(pi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delPersonInfo")
	public String delPersonInfo(HttpServletRequest req,int id){
		PersonInfo p =service.getPersonInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(p),LogRemark.DELPERSONINFO);
		if(service.delPersonInfo(id)){
			
		}
		return "redirect:/selPersonInfo";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/selCompanyInfo")
	public String selCompanyInfo(HttpServletRequest req){
		
		return "user/user_company_list";
	}
	@RequestMapping(value ="/queryCompanyInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCompanyInfo(HttpServletRequest req,CompanyInfoVo vo){
		Message msg =createMsg();
		
		Page<CompanyInfo> page =service.queryCompanyInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insCompanyInfo")
	public String insCompanyInfo(HttpServletRequest req){
		
		return "user/user_company_add";
	}
	@RequestMapping(value ="/addCompanyInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCompanyInfo(HttpServletRequest req,CompanyInfo ci){
		Message msg =createMsg();
		service.addCompanyInfo(ci);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ci),LogRemark.ADDCOMPANYINFO);
		return Json(msg);
	}
	@RequestMapping("/readCompanyInfo")
	public String readCompanyInfo(HttpServletRequest req,int id){
		CompanyInfo ci =service.getCompanyInfo(id);
		req.setAttribute("companyinfo", Json(ci));
		
		return "user/user_company_upd";
	}
	@RequestMapping(value ="/updCompanyInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCompanyInfo(HttpServletRequest req,CompanyInfo ci){
		Message msg =createMsg();
		CompanyInfo c =service.getCompanyInfo(ci.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(c),LogRemark.UPDCOMPANYINFO);
		if(service.updCompanyInfo(ci)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delCompanyInfo")
	public String delCompanyInfo(HttpServletRequest req,int id){
		Message msg =createMsg();
		CompanyInfo c =service.getCompanyInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELCOMPANYINFO);
		if(service.delCompanyInfo(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selCompanyInfo";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selBankType")
	public String selBankType(HttpServletRequest req){
		
		return "user/user_banktype_list";
	}
	@RequestMapping(value ="/queryBankType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBankType(HttpServletRequest req,BankTypeVo vo){
		Message msg =createMsg();
		
		Page<BankType> page =service.queryBankType(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insBankType")
	public String insBankType(HttpServletRequest req){
		
		return "user/user_banktype_add";
	}
	@RequestMapping(value ="/addBankType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBankType(HttpServletRequest req,BankType bt){
		Message msg =createMsg();
		bt.setAdd_time(TimeUtils.getCurrentTime());
		service.addBankType(bt);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(bt),LogRemark.ADDBANKTYPE);
		return Json(msg);
		
	}
	@RequestMapping("/readBankType")
	public String readBankType(HttpServletRequest req,int id){
		BankType bt =service.getBankType(id);
		req.setAttribute("banktypeinfo", Json(bt));
		return "user/user_banktype_upd";
	}
	@RequestMapping(value ="/updBankType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBankType(HttpServletRequest req,BankType bt){
		Message msg =createMsg();
		BankType b =service.getBankType(bt.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(b),LogRemark.UPDBANKTYPE);
		if(service.updBankType(bt)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/delBankType")
	public String delBankType(HttpServletRequest req,int id){
		Message msg =createMsg();
		BankType b =service.getBankType(id);
		saveLog(createLog(req),LogRemark.DEL,Json(b),LogRemark.DELBANKTYPE);
		if(service.delBankType(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selBankType";
	}
}
