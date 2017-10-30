package com.manji.backstage.controller;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.shop.BuyerInfo;
import com.manji.backstage.model.user.CompanyInfo;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserBankType;
import com.manji.backstage.model.user.UserCode;
import com.manji.backstage.model.user.UserFavorites;
import com.manji.backstage.model.user.UserFixed;
import com.manji.backstage.model.user.UserFromValue;
import com.manji.backstage.model.user.UserGroupPrice;
import com.manji.backstage.model.user.UserOauth;
import com.manji.backstage.model.user.UserOftenUse;
import com.manji.backstage.model.user.UserRoleBuyerinfo;
import com.manji.backstage.model.user.UserWechat;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.UserService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.utils.InterfaceUtil;
import com.manji.backstage.utils.MD5util;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.shop.BuyerInfoVo;
import com.manji.backstage.vo.user.CompanyInfoVo;
import com.manji.backstage.vo.user.PersonInfoVo;
import com.manji.backstage.vo.user.RecAddrVo;
import com.manji.backstage.vo.user.UserBankTypeVo;
import com.manji.backstage.vo.user.UserCodeVo;
import com.manji.backstage.vo.user.UserFavoritesVo;
import com.manji.backstage.vo.user.UserFixedVo;
import com.manji.backstage.vo.user.UserFromValueVo;
import com.manji.backstage.vo.user.UserGroupPriceVo;
import com.manji.backstage.vo.user.UserOauthVo;
import com.manji.backstage.vo.user.UserOftenUseVo;
import com.manji.backstage.vo.user.UserRoleBuyerinfoVo;
import com.manji.backstage.vo.user.UserVo;
import com.manji.backstage.vo.user.UserWechatVo;
import com.manji.backstage.vo.user.VerifyVo;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService service;
	@Autowired
	private LoggersService logService;
	@Autowired
	private ArtiService articService;
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);
		log.setModule("user");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////用户安全
	
//	dt_user_safeprotect	用户安全保障信息表

	@RequestMapping("/selVerify")
	public String selVerify(HttpServletRequest req){
		
		return "user/user_verify_list";
	}
	@RequestMapping(value ="/queryVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryVerify(HttpServletRequest req,VerifyVo vo){
		Message msg =createMsg();
		
		Page<Verify> page =service.queryVerify(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insVerify")
	public String insVerify(HttpServletRequest req){
		
		return "user/user_verify_add";
	}
	@RequestMapping(value ="/addVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addVerify(HttpServletRequest req,Verify verify){
		Message msg =createMsg();
		verify.setUpdate_time(TimeUtils.getCurrentTime());
		service.addVerify(verify);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(verify),LogRemark.ADDVERIFY);
		return Json(msg);
		
	}
	@RequestMapping("/readVerify")
	public String readVerify(HttpServletRequest req,int id){
		Verify verify =service.getVerify(id);
		req.setAttribute("verifyinfo", Json(verify));
		return "user/user_verify_upd";
	}
	@RequestMapping(value ="/updVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updVerify(HttpServletRequest req,Verify verify){
		Message msg =createMsg();
		Verify v =service.getVerify(verify.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(v),LogRemark.UPDVERIFY);
		if(service.updVerify(verify)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delVerify")
	public String delVerify(HttpServletRequest req,int id){
		Message msg =createMsg();
		Verify v =service.getVerify(id);
		saveLog(createLog(req),LogRemark.DEL,Json(v),LogRemark.DELVERIFY);
		if(service.delVerify(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selVerify";
	}
	
	

	
//	dt_users	用户信息表


	@RequestMapping("/selUser")
	public String selUser(HttpServletRequest req){
		
		return "user/user_list";
	}
	@RequestMapping(value ="/queryUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUser(HttpServletRequest req,UserVo vo){
		Message msg =createMsg();
		System.out.println(vo.getStatus()+"==");
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
	
	
	//用户强制下线记录
	
	@RequestMapping("/downLine")
	public String downLine(HttpServletRequest req){
		String userId = req.getParameter("user_id");
		System.out.println(userId);
		String noncestr = String.valueOf(System.currentTimeMillis());
		TreeMap<String, String> map = new TreeMap<String, String>();
		String round = MD5util.getMD5(noncestr + userId).toLowerCase();
	    map.put("currentDate", noncestr);
	    map.put("isResponseJson", "1");
	    map.put("loginType", "Third");
	    map.put("roundNumber", round);
    	map.put("userId", userId);
    	String k = null;
    	Message msg = createMsg();
			try {
				k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(k);
				if ("1".equals(jsonObject.getString("State"))) {
					msg.setStatus("1");
					msg.setResult(jsonObject.getString("Message"));
				} else {
					msg.setStatus("0");
					msg.setResult("用户下线失败");
				}
			
			} catch (IOException e) {
				msg.setResult("系统错误");
				e.printStackTrace();
			}
		return "redirect:/selUser";
	}
	
	
//	dt_user_addr_book	用户收获地址新标

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
//	dt_user_favorites	用户收藏信息表

	
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
	@RequestMapping(value ="/addUserFavorites", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addUserFavorites(HttpServletRequest req,UserFavorites uf,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			uf.setPics(url);
		}else{
			uf.setPics(req.getParameter("icon"));
		}
		uf.setAdd_times(TimeUtils.getCurrentTime());
		service.addUserFavorites(uf);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(uf),LogRemark.ADDUSERFAVORITES);
		return "redirect:/selUserFavorites";
		
	}
	@RequestMapping("/readUserFavorites")
	public String readUserFavorites(HttpServletRequest req,int id){
		UserFavorites uf =service.getUserFavorites(id);
		req.setAttribute("ufinfo", Json(uf));
		return "user/user_favorites_upd";
	}
	@RequestMapping(value ="/updUserFavorites", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updUserFavorites(HttpServletRequest req,UserFavorites uf,MultipartFile file){
		
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			uf.setPics(url);
		}else{
			uf.setPics(req.getParameter("icon"));
		}
		UserFavorites u =service.getUserFavorites(uf.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(u),LogRemark.UPDUSERFAVORITES);
		if(service.updUserFavorites(uf)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readUserFavorites?id="+uf.getId();
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
	
	
//	dt_user_personinfo	用户个人信息表

	
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
	@RequestMapping(value ="/addPersonInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addPersonInfo(HttpServletRequest req,PersonInfo pi,@RequestParam MultipartFile[] file){
		Message msg =createMsg();
		int a=file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(i+1)){
					url += articService.sendPostReq(fileName, base64Str);
				}else{
					url += articService.sendPostReq(fileName, base64Str)+",";
				}
			}
			pi.setCard_pics(url);
		}else{
			pi.setCard_pics(req.getParameter("icon"));
		}
		pi.setPerson_brithday(TimeUtils.getCurrentTime());
		service.addPersonInfo(pi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(pi),LogRemark.ADDPERSONINFO);
		return "redirect:/selPersonInfo";
	}
	@RequestMapping("/readPersonInfo")
	public String readPersonInfo(HttpServletRequest req,int id){
		PersonInfo pi =service.getPersonInfo(id);
		req.setAttribute("personinfo", Json(pi));
		return "user/user_person_upd";
	}
	@RequestMapping(value ="/updPersonInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updPersonInfo(HttpServletRequest req,PersonInfo pi,@RequestParam MultipartFile[] file){
		Message msg =createMsg();
		int a=file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(i+1)){
					url += articService.sendPostReq(fileName, base64Str);
				}else{
					url += articService.sendPostReq(fileName, base64Str)+",";
				}
			}
			pi.setCard_pics(url);
		}else{
			pi.setCard_pics(req.getParameter("icon"));
		}
		PersonInfo p =service.getPersonInfo(pi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(p),LogRemark.UPDPERSONINFO);
		if(service.updPersonInfo(pi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readPersonInfo?id="+pi.getId();
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
//	dt_user_companyinfo	用户个人信息表

	
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
	@RequestMapping(value ="/addCompanyInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addCompanyInfo(HttpServletRequest req,CompanyInfo ci,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ci.setCard_pics(url);
		}else{
			ci.setCard_pics(req.getParameter("icon"));
		}
		service.addCompanyInfo(ci);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ci),LogRemark.ADDCOMPANYINFO);
		return "redirect:/selCompanyInfo";
	}
	@RequestMapping("/readCompanyInfo")
	public String readCompanyInfo(HttpServletRequest req,int id){
		CompanyInfo ci =service.getCompanyInfo(id);
		req.setAttribute("companyinfo", Json(ci));
		
		return "user/user_company_upd";
	}
	@RequestMapping(value ="/updCompanyInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updCompanyInfo(HttpServletRequest req,CompanyInfo ci,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ci.setCard_pics(url);
		}else{
			ci.setCard_pics(req.getParameter("icon"));
		}
		CompanyInfo c =service.getCompanyInfo(ci.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(c),LogRemark.UPDCOMPANYINFO);
		if(service.updCompanyInfo(ci)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readCompanyInfo?id="+ci.getId();
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
	
//	dt_user_banktype	用户绑定银行卡

	
	@RequestMapping("/selBankType")
	public String selBankType(HttpServletRequest req){
		
		return "user/user_banktype_list";
	}
	@RequestMapping(value ="/queryBankType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBankType(HttpServletRequest req,UserBankTypeVo vo){
		Message msg =createMsg();
		
		Page<UserBankType> page =service.queryUserBankType(vo);
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
	public String addBankType(HttpServletRequest req,UserBankType bt){
		Message msg =createMsg();
		bt.setAdd_time(TimeUtils.getCurrentTime());
		service.addUserBankType(bt);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(bt),LogRemark.ADDBANKTYPE);
		return Json(msg);
		
	}
	@RequestMapping("/readBankType")
	public String readBankType(HttpServletRequest req,int id){
		UserBankType bt =service.getUserBankType(id);
		req.setAttribute("banktypeinfo", Json(bt));
		return "user/user_banktype_upd";
	}
	@RequestMapping(value ="/updBankType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBankType(HttpServletRequest req,UserBankType bt){
		Message msg =createMsg();
		UserBankType b =service.getUserBankType(bt.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(b),LogRemark.UPDBANKTYPE);
		if(service.updUserBankType(bt)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/delBankType")
	public String delBankType(HttpServletRequest req,int id){
		Message msg =createMsg();
		UserBankType b =service.getUserBankType(id);
		saveLog(createLog(req),LogRemark.DEL,Json(b),LogRemark.DELBANKTYPE);
		if(service.delUserBankType(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selBankType";
	}
	
	

	
//	dt_user_role_buyerinfo	用户买家信息表

	
	@RequestMapping("/selBuyerInfo")
	public String selBuyerInfo(HttpServletRequest req){
		
		return "user/user_buyerinfo_list";
	}
	@RequestMapping(value ="/queryBuyerInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBuyerInfo(HttpServletRequest req,BuyerInfoVo vo){
		Message msg =createMsg();
		
		Page<BuyerInfo> page =service.queryBuyerInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readBuyerInfo")
	public String readBuyerInfo(HttpServletRequest req,int id){
		BuyerInfo bi =service.getBuyerInfo(id);
		req.setAttribute("buyerinfo", Json(bi));
		return "user/user_buyerinfo_upd";
	}
	@RequestMapping(value ="/updBuyerInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBuyerInfo(HttpServletRequest req,BuyerInfoVo vo){
		Message msg =createMsg();
		BuyerInfo agt =service.getBuyerInfo(vo.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDBUYERINFO);
		vo.setUpdate_time(TimeUtils.getCurrentTime());
		if(service.updBuyerInfo(vo)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insBuyerInfo")
	public String insBuyerInfo(HttpServletRequest req){
		
		return "user/user_buyerinfo_add";
	}
	@RequestMapping(value ="/addBuyerInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBuyerInfo(HttpServletRequest req,BuyerInfo bi){
		Message msg =createMsg();
		service.addBuyerInfo(bi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(bi),LogRemark.ADDBUYERINFO);
		return Json(msg);
		
	}
	@RequestMapping("/delBuyerInfo")
	public String  delBuyerInfo(HttpServletRequest req,int id){
		BuyerInfo agt =service.getBuyerInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELBUYERINFO);
		if(service.delBuyerInfo(id)){
			
		}
		return "redirect:/selBuyerInfo";
		
	}
	
	
	//2.3.6.dt_user_code(用户随机码信息表)

	@RequestMapping("/selUserCode")
	public String selUserCode(HttpServletRequest req){
		
		return "user/user_code_list";
	}
	@RequestMapping(value ="/queryUserCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserCode(HttpServletRequest req,UserCodeVo vo){
		Message msg =createMsg();
		
		Page<UserCode> page =service.queryUserCode(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserCode")
	public String insUserCode(HttpServletRequest req){
		
		return "user/user_code_add";
	}
	@RequestMapping(value ="/addUserCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserCode(HttpServletRequest req,UserCode og){
		Message msg =createMsg();
		service.addUserCode(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERCODE);
		return Json(msg);
		
	}
	@RequestMapping("/readUserCode")
	public String readUserCode(HttpServletRequest req,int id){
		UserCode og =service.getUserCode(id);
		req.setAttribute("usercode", Json(og));
		return "user/user_code_upd";
	}
	@RequestMapping(value ="/updUserCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserCode(HttpServletRequest req,UserCode og){
		Message msg =createMsg();
		UserCode o =service.getUserCode(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERCODE);
		if(service.updUserCode(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserCode")
	public String delUserCode(HttpServletRequest req,int id){
		
		UserCode o =service.getUserCode(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERCODE);
		
		if(service.delUserCode(id)){
			
		}
		return "redirect:/selUserCode";
	}
	
	
	//2.3.3.dt_user_from_value（用户标识申请信息表）
	
	@RequestMapping("/selUserFromValue")
	public String selUserFromValue(HttpServletRequest req){
		
		return "user/user_fromvalue_list";
	}
	@RequestMapping(value ="/queryUserFromValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserFromValue(HttpServletRequest req,UserFromValueVo vo){
		Message msg =createMsg();
		
		Page<UserFromValue> page =service.queryUserFromValue(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserFromValue")
	public String insUserFromValue(HttpServletRequest req){
		
		return "user/user_fromvalue_add";
	}
	@RequestMapping(value ="/addUserFromValue", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addUserFromValue(HttpServletRequest req,UserFromValue og,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setImg_url(url);
		}else{
			og.setImg_url(req.getParameter("icon"));
		}
		service.addUserFromValue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERFROMVALUE);
		return "redirect:/selUserFromValue";
		
	}
	@RequestMapping("/readUserFromValue")
	public String readUserFromValue(HttpServletRequest req,int id){
		UserFromValue og =service.getUserFromValue(id);
		req.setAttribute("userfromvalue", Json(og));
		return "user/user_fromvalue_upd";
	}
	@RequestMapping(value ="/updUserFromValue", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updUserFromValue(HttpServletRequest req,UserFromValue og,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setImg_url(url);
		}else{
			og.setImg_url(req.getParameter("icon"));
		}
		UserFromValue o =service.getUserFromValue(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERFROMVALUE);
		if(service.updUserFromValue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readUserFromValue?id="+og.getId();
	}
	@RequestMapping("/delUserFromValue")
	public String delUserFromValue(HttpServletRequest req,int id){
		
		UserFromValue o =service.getUserFromValue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERFROMVALUE);
		
		if(service.delUserFromValue(id)){
			
		}
		return "redirect:/selUserFromValue";
	}
	
	
	
	
	//2.3.5.dt_user_group_price（用户分组商品价格信息表）
	
	@RequestMapping("/selUserGroupPrice")
	public String selUserGroupPrice(HttpServletRequest req){
		
		return "user/user_groupprice_list";
	}
	@RequestMapping(value ="/queryUserGroupPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserGroupPrice(HttpServletRequest req,UserGroupPriceVo vo){
		Message msg =createMsg();
		
		Page<UserGroupPrice> page =service.queryUserGroupPrice(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserGroupPrice")
	public String insUserGroupPrice(HttpServletRequest req){
		
		return "user/user_groupprice_add";
	}
	@RequestMapping(value ="/addUserGroupPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserGroupPrice(HttpServletRequest req,UserGroupPrice og){
		Message msg =createMsg();
		service.addUserGroupPrice(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERGROUPPRICE);
		return Json(msg);
		
	}
	@RequestMapping("/readUserGroupPrice")
	public String readUserGroupPrice(HttpServletRequest req,int id){
		UserGroupPrice og =service.getUserGroupPrice(id);
		req.setAttribute("usergroupprice", Json(og));
		return "user_groupprice_upd";
	}
	@RequestMapping(value ="/updUserGroupPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserGroupPrice(HttpServletRequest req,UserGroupPrice og){
		Message msg =createMsg();
		UserGroupPrice o =service.getUserGroupPrice(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERGROUPPRICE);
		if(service.updUserGroupPrice(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserGroupPrice")
	public String delUserGroupPrice(HttpServletRequest req,int id){
		
		UserGroupPrice o =service.getUserGroupPrice(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERGROUPPRICE);
		
		if(service.delUserGroupPrice(id)){
			
		}
		return "redirect:/selUserGroupPrice";
	}
	
	
	
	
	//2.3.31.dt_user_often_use (用户经常使用信息表)
	
	@RequestMapping("/selUserOftenUse")
	public String selUserOftenUse(HttpServletRequest req){
		
		return "user/user_oftenuse_list";
	}
	@RequestMapping(value ="/queryUserOftenUse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserOftenUse(HttpServletRequest req,UserOftenUseVo vo){
		Message msg =createMsg();
		
		Page<UserOftenUse> page =service.queryUserOftenUse(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserOftenUse")
	public String insUserOftenUse(HttpServletRequest req){
		
		return "user/user_oftenuse_add";
	}
	@RequestMapping(value ="/addUserOftenUse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserOftenUse(HttpServletRequest req,UserOftenUse og){
		Message msg =createMsg();
		service.addUserOftenUse(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSEROFTENUSE);
		return Json(msg);
		
	}
	@RequestMapping("/readUserOftenUse")
	public String readUserOftenUse(HttpServletRequest req,int id){
		UserOftenUse og =service.getUserOftenUse(id);
		req.setAttribute("useroftenuse", Json(og));
		return "user/user_oftenuse_upd";
	}
	@RequestMapping(value ="/updUserOftenUse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserOftenUse(HttpServletRequest req,UserOftenUse og){
		Message msg =createMsg();
		UserOftenUse o =service.getUserOftenUse(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSEROFTENUSE);
		if(service.updUserOftenUse(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserOftenUse")
	public String delUserOftenUse(HttpServletRequest req,int id){
		
		UserOftenUse o =service.getUserOftenUse(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSEROFTENUSE);
		
		if(service.delUserOftenUse(id)){
			
		}
		return "redirect:/selUserOftenUse";
	}
	
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	@RequestMapping("/selUserWechat")
	public String selUserWechat(HttpServletRequest req){
		
		return "user/user_wechat_list";
	}
	@RequestMapping(value ="/queryUserWechat", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserWechat(HttpServletRequest req,UserWechatVo vo){
		Message msg =createMsg();
		
		Page<UserWechat> page =service.queryUserWechat(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserWechat")
	public String insUserWechat(HttpServletRequest req){
		
		return "user/user_wechat_add";
	}
	@RequestMapping(value ="/addUserWechat", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addUserWechat(HttpServletRequest req,UserWechat og,MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setHeadimg(url);
		}else{
			og.setHeadimg(req.getParameter("icon"));
		}
		service.addUserWechat(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERWECHAT);
		return "redirect:/selUserWechat";
		
	}
	@RequestMapping("/readUserWechat")
	public String readUserWechat(HttpServletRequest req,int id){
		UserWechat og =service.getUserWechat(id);
		req.setAttribute("userwechat", Json(og));
		return "user/user_wechat_upd";
	}
	@RequestMapping(value ="/updUserWechat", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updUserWechat(HttpServletRequest req,UserWechat og,MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setHeadimg(url);
		}else{
			og.setHeadimg(req.getParameter("icon"));
		}
		
		UserWechat o =service.getUserWechat(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERWECHAT);
		if(service.updUserWechat(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readUserWechat?id="+og.getId();
	}
	@RequestMapping("/delUserWechat")
	public String delUserWechat(HttpServletRequest req,int id){
		
		UserWechat o =service.getUserWechat(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERWECHAT);
		
		if(service.delUserWechat(id)){
			
		}
		return "redirect:/selUserWechat";
	}
	
	
	//dt_user_fixed（卖家批量充值记录）
	
	@RequestMapping("/selUserFixed")
	public String selUserFixed(HttpServletRequest req){
		
		return "user/user_fixed_list";
	}
	@RequestMapping(value ="/queryUserFixed", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserFixed(HttpServletRequest req,UserFixedVo vo){
		Message msg =createMsg();
		
		Page<UserFixed> page =service.queryUserFixed(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserFixed")
	public String insUserFixed(HttpServletRequest req){
		
		return "user/user_fixed_add";
	}
	@RequestMapping(value ="/addUserFixed", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserFixed(HttpServletRequest req,UserFixed og){
		Message msg =createMsg();
		service.addUserFixed(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERFIXED);
		return Json(msg);
		
	}
	@RequestMapping("/readUserFixed")
	public String readUserFixed(HttpServletRequest req,long id){
		UserFixed og =service.getUserFixed(id);
		req.setAttribute("userfixed", Json(og));
		return "user/user_fixed_upd";
	}
	@RequestMapping(value ="/updUserFixed", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserFixed(HttpServletRequest req,UserFixed og){
		Message msg =createMsg();
		UserFixed o =service.getUserFixed(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERFIXED);
		if(service.updUserFixed(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserFixed")
	public String delUserFixed(HttpServletRequest req,long id){
		
		UserFixed o =service.getUserFixed(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERFIXED);
		
		if(service.delUserFixed(id)){
			
		}
		return "redirect:/selUserFixed";
	}
	
	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
		@RequestMapping("/selUserOauth")
		public String selUserOauth(HttpServletRequest req){
			
			return "user/user_oauth_list";
		}
		@RequestMapping(value ="/queryUserOauth", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryUserOauth(HttpServletRequest req,UserOauthVo vo){
			Message msg =createMsg();
			
			Page<UserOauth> page =service.queryUserOauth(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insUserOauth")
		public String insUserOauth(HttpServletRequest req){
			
			return "user/user_oauth_add";
		}
		@RequestMapping(value ="/addUserOauth", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addUserOauth(HttpServletRequest req,UserOauth og){
			Message msg =createMsg();
			service.addUserOauth(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSEROAUTH);
			return Json(msg);
			
		}
		@RequestMapping("/readUserOauth")
		public String readUserOauth(HttpServletRequest req,int id){
			UserOauth og =service.getUserOauth(id);
			req.setAttribute("useroauth", Json(og));
			return "user/user_oauth_upd";
		}
		@RequestMapping(value ="/updUserOauth", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updUserOauth(HttpServletRequest req,UserOauth og){
			Message msg =createMsg();
			UserOauth o =service.getUserOauth(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSEROAUTH);
			if(service.updUserOauth(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delUserOauth")
		public String delUserOauth(HttpServletRequest req,int id){
			
			UserOauth o =service.getUserOauth(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSEROAUTH);
			
			if(service.delUserOauth(id)){
				
			}
			return "redirect:/selUserOauth";
		}
		
	
}
