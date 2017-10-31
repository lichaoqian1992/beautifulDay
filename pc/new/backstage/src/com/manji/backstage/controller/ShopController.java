package com.manji.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.shop.BusinessGroupshop;
import com.manji.backstage.model.shop.BusinessSms;
import com.manji.backstage.model.shop.Cooperate;
import com.manji.backstage.model.shop.OtherInfo;
import com.manji.backstage.model.shop.ScopeInfo;
import com.manji.backstage.model.shop.ShopBrand;
import com.manji.backstage.model.shop.ShopExpensesInfo;
import com.manji.backstage.model.shop.ShopInfo;
import com.manji.backstage.model.shop.ShopWuliumuban;
import com.manji.backstage.model.shop.ShopWuliumubanItem;
import com.manji.backstage.model.shop.ShopZisong;
import com.manji.backstage.model.shop.TempShopInfo;
import com.manji.backstage.model.shop.UserRoleShopinfoGroup;
import com.manji.backstage.model.shop.UserRoleShopinfoGrouprelation;
import com.manji.backstage.model.shop.UserRoleShopinfoMatter;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.ShopService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.vo.shop.BusinessGroupshopVo;
import com.manji.backstage.vo.shop.BusinessSmsVo;
import com.manji.backstage.vo.shop.CooperateVo;
import com.manji.backstage.vo.shop.OtherInfoVo;
import com.manji.backstage.vo.shop.ScopeInfoVo;
import com.manji.backstage.vo.shop.ShopBrandVo;
import com.manji.backstage.vo.shop.ShopExpensesInfoVo;
import com.manji.backstage.vo.shop.ShopInfoVo;
import com.manji.backstage.vo.shop.ShopWuliumubanItemVo;
import com.manji.backstage.vo.shop.ShopWuliumubanVo;
import com.manji.backstage.vo.shop.ShopZisongVo;
import com.manji.backstage.vo.shop.TempShopInfoVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoGroupVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoGrouprelationVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoMatterVo;

@Controller
public class ShopController extends BaseController{

	@Autowired
	private ShopService service;
	@Autowired
	private LoggersService logService;
	@Autowired
	private ArtiService articService;
	
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("shop");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
	
	

	
//	dt_user_role_shopinfo	用户商家信息表

	
	@RequestMapping("/selShopInfo")
	public String selShopInfo(HttpServletRequest req){
		
		return "shop/shop_shopinfo_list";
	}
	@RequestMapping(value ="/queryShopInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopInfo(HttpServletRequest req,ShopInfoVo vo){
		Message msg =createMsg();
		
		Page<ShopInfo> page =service.queryShopInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readShopInfo")
	public String readShopInfo(HttpServletRequest req,int id,ShopInfoVo vo){
		ShopInfo si =service.getShopInfo(id);
		req.setAttribute("shopinfo", Json(si));
		return "shop/shop_shopinfo_upd";
	}
	@RequestMapping(value ="/updShopInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopInfo(HttpServletRequest req,ShopInfo si){
		Message msg =createMsg();
		/*ShopInfo agt =service.getShopInfo(si.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPINFO);*/
		System.out.println(si);
		if(service.updShopInfo(si)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		System.out.println("22222");
		return Json(msg);
		
	}
	@RequestMapping("/insShopInfo")
	public String insShopInfo(HttpServletRequest req){
		return "shop/shop_shopinfo_add";
	}
	@RequestMapping(value ="/addShopInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopInfo(HttpServletRequest req,ShopInfo si){
		Message msg = createMsg();
		service.addShopInfo(si);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(si),LogRemark.ADDSHOPINFO);
		return Json(msg);
	}
	@RequestMapping("/delShopInfo")
	public String delShopInfo(HttpServletRequest req,int id){
		ShopInfo agt =service.getShopInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELSHOPINFO);
		if(service.delShopInfo(id)){
			
		}
		return "redirect:/selShopInfo";
	}
	
//	dt_user_role_shopinfo_temp	用户商家信息修改林纯表

	@RequestMapping("/selTempShopInfo")
	public String selTempShopInfo(HttpServletRequest req){
		
		return "shop/shop_tempinfo_list";
	}
	@RequestMapping(value ="/queryTempShopInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryTempShopInfo(HttpServletRequest req,TempShopInfoVo vo){
		Message msg =createMsg();
		
		Page<TempShopInfo> page =service.queryTempShopInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readTempShopInfo")
	public String readTempShopInfo(HttpServletRequest req,int id){
		TempShopInfo tsi =service.getTempShopInfo(id);
		req.setAttribute("tempshopinfo", Json(tsi));
		
		return "shop/shop_tempinfo_upd";
	}
	@RequestMapping(value ="/updTempShopInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updTempShopInfo(HttpServletRequest req,TempShopInfo tsi,MultipartFile file,MultipartFile file1){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			tsi.setPc_logo(url);
		}else{
			tsi.setPc_logo(req.getParameter("icon"));
		}
		if(!file1.isEmpty()){
			String fileName = file1.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file1);
			String url = articService.sendPostReq(fileName, base64Str);
			tsi.setWap_logo(url);
		}else{
			tsi.setWap_logo(req.getParameter("icon1"));
		}
		TempShopInfo agt =service.getTempShopInfo(tsi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPTEMP);
		if(service.updTempShopInfo(tsi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readTempShopInfo?id="+tsi.getId();
		
	}
	@RequestMapping("/insTempShopInfo")
	public String insTempShopInfo(HttpServletRequest req){
		return "shop/shop_tempinfo_add";
	}
	@RequestMapping(value="/addTempShopInfo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addTempShopInfo(HttpServletRequest req,TempShopInfo tsi,MultipartFile file,MultipartFile file1){
		Message msg = createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			tsi.setPc_logo(url);
		}else{
			tsi.setPc_logo(req.getParameter("icon"));
		}
		if(!file1.isEmpty()){
			String fileName = file1.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file1);
			String url = articService.sendPostReq(fileName, base64Str);
			tsi.setWap_logo(url);
		}else{
			tsi.setWap_logo(req.getParameter("icon1"));
		}
		service.addTempShopInfo(tsi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(tsi),LogRemark.ADDSHOPTEMP);
		return "redirect:/selTempShopInfo?id="+tsi.getId();
	}
	@RequestMapping("/delTempShopInfo")
	public String delTempShopInfo(HttpServletRequest req,int id){
		TempShopInfo agt =service.getTempShopInfo(id);
		if(service.delTempShopInfo(id)){
			
		}
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELSHOPTEMP);
		return "redirect:/selTempShopInfo";
	}
	
	
	
//	dt_business_user	用户业务申购信息表

	@RequestMapping("/selCooperate")
	public String selCooperate(HttpServletRequest req){
		
		return "shop/shop_cooperate_list";
	}
	@RequestMapping(value ="/queryCooperate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCooperate(HttpServletRequest req,CooperateVo vo){
		Message msg =createMsg();
		
		Page<Cooperate> page =service.queryCooperate(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insCooperate")
	public String insCooperate(HttpServletRequest req){
		
		return "shop/shop_cooperate_add";
	}
	@RequestMapping(value ="/addCooperate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCooperate(HttpServletRequest req,Cooperate coo){
		Message msg =createMsg();
		service.addCooperate(coo);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(coo),LogRemark.ADDBUSINESSUSER);

		return Json(msg);
	}
	@RequestMapping("/readCooperate")
	public String readCooperate(HttpServletRequest req,int id){
		Cooperate coo =service.getCooperate(id);
		req.setAttribute("cooperateinfo", Json(coo));
		return "shop/shop_cooperate_upd";
	}
	@RequestMapping(value ="/updCooperate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCooperate(HttpServletRequest req,Cooperate coo){
		Message msg =createMsg();
		Cooperate agt =service.getCooperate(coo.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDBUSINESSUSER);
		if(service.updCooperate(coo)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delCooperate")
	public String delCooperate(HttpServletRequest req,int id){
		Message msg =createMsg();

		Cooperate agt =service.getCooperate(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELBUSINESSUSER);
		if(service.delCooperate(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selCooperate";
	}

	
	
//	dt_user_role_shopinfo_other	用户商家其他信息素材表

	@RequestMapping("/selOtherInfo")
	public String selOtherInfo(HttpServletRequest req){
		
		return "shop/shop_otherinfo_list";
	}
	@RequestMapping(value ="/queryOtherInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOtherInfo(HttpServletRequest req,OtherInfoVo vo){
		Message msg =createMsg();
		
		Page<OtherInfo> page =service.queryOtherInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insOtherInfo")
	public String insOtherInfo(HttpServletRequest req){
		
		return "shop/shop_otherinfo_add";
	}
	@RequestMapping(value ="/addOtherInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOtherInfo(HttpServletRequest req,OtherInfo oi){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(oi),LogRemark.ADDSHOPOTHER);
		service.addOtherInfo(oi);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/readOtherInfo")
	public String readOtherInfo(HttpServletRequest req,int id){
		OtherInfo oi =service.getOtherInfo(id);
		req.setAttribute("otherinfo", Json(oi));
		return "shop/shop_otherinfo_upd";
	}
	@RequestMapping(value ="/updOtherInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOtherInfo(HttpServletRequest req,OtherInfo oi){
		Message msg =createMsg();
		OtherInfo agt =service.getOtherInfo(oi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPOTHER);
		if(service.updOtherInfo(oi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOtherInfo")
	public String delOtherInfo(HttpServletRequest req,int id){
		Message msg =createMsg();
		OtherInfo agt =service.getOtherInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELSHOPOTHER);
		if(service.delOtherInfo(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selOtherInfo";
	}
	
//	dt_user_role_shopinfo_scope	用户商家经营分为表

	
	@RequestMapping("/selScopeInfo")
	public String selScopeInfo(HttpServletRequest req){
		
		return "shop/shop_scopeinfo_list";
	}
	@RequestMapping(value ="/queryScopeInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryScopeInfo(HttpServletRequest req,ScopeInfoVo vo){
		Message msg =createMsg();
		
		Page<ScopeInfo> page =service.queryScopeInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insScopeInfo")
	public String insScopeInfo(HttpServletRequest req){
		
		return "shop/shop_scopeinfo_add";
	}
	@RequestMapping(value ="/addScopeInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addScopeInfo(HttpServletRequest req,ScopeInfo si){
		Message msg =createMsg();
		service.addScopeInfo(si);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(si),LogRemark.ADDSHOPSCOPE);
		return Json(msg);
	}
	@RequestMapping("/readScopeInfo")
	public String readScopeInfo(HttpServletRequest req,int id){
		ScopeInfo si =service.getScopeInfo(id);
		req.setAttribute("scopeinfo", Json(si));
		return "shop/shop_scopeinfo_upd";
	}
	@RequestMapping(value ="/updScopeInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updScopeInfo(HttpServletRequest req,ScopeInfo si){
		Message msg =createMsg();
		ScopeInfo agt =service.getScopeInfo(si.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPSCOPE);
		if(service.updScopeInfo(si)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delScopeInfo")
	public String delScopeInfo(HttpServletRequest req,int id){
		Message msg =createMsg();
		ScopeInfo agt =service.getScopeInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELSHOPSCOPE);
		if(service.delScopeInfo(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selScopeInfo";
	}
	

	//dt_shop_brand(商家品牌)
	
	@RequestMapping("/selShopBrand")
	public String selShopBrand(HttpServletRequest req){
		
		return "shop/shop_brand_list";
	}
	@RequestMapping(value ="/queryShopBrand", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopBrand(HttpServletRequest req,ShopBrandVo vo){
		Message msg =createMsg();
		
		Page<ShopBrand> page =service.queryShopBrand(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insShopBrand")
	public String insShopBrand(HttpServletRequest req){
		
		return "shop/shop_brand_add";
	}
	@RequestMapping(value ="/addShopBrand", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addShopBrand(HttpServletRequest req,ShopBrand og,MultipartFile file,MultipartFile file1){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setLogo(url);
		}else{
			og.setLogo(req.getParameter("icon"));
		}
		if(!file1.isEmpty()){
			String fileName = file1.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file1);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setAuthorize_pics(url);
		}else{
			og.setAuthorize_pics(req.getParameter("icon1"));
		}
		service.addShopBrand(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDSHOPBRAND);
		return "redirect:/selShopBrand";
		
	}
	@RequestMapping("/readShopBrand")
	public String readShopBrand(HttpServletRequest req,int id){
		ShopBrand og =service.getShopBrand(id);
		req.setAttribute("shopbrand", Json(og));
		return "shop/shop_brand_upd";
	}
	@RequestMapping(value ="/updShopBrand", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updShopBrand(HttpServletRequest req,ShopBrand og,MultipartFile file,MultipartFile file1){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setLogo(url);
		}else{
			og.setLogo(req.getParameter("icon"));
		}
		if(!file1.isEmpty()){
			String fileName = file1.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file1);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setAuthorize_pics(url);
		}else{
			og.setAuthorize_pics(req.getParameter("icon1"));
		}
		ShopBrand o =service.getShopBrand(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDSHOPBRAND);
		if(service.updShopBrand(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readShopBrand?id="+og.getId();
	}
	@RequestMapping("/delShopBrand")
	public String delShopBrand(HttpServletRequest req,int id){
		
		ShopBrand o =service.getShopBrand(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELSHOPBRAND);
		
		if(service.delShopBrand(id)){
			
		}
		return "redirect:/selShopBrand";
	}
	
	
	
	//dt_shop_wuliumuban(商家物流模板)
	
	@RequestMapping("/selShopWuliumuban")
	public String selShopWuliumuban(HttpServletRequest req){
		
		return "shop/shop_wuliu_list";
	}
	@RequestMapping(value ="/queryShopWuliumuban", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopWuliumuban(HttpServletRequest req,ShopWuliumubanVo vo){
		Message msg =createMsg();
		
		Page<ShopWuliumuban> page =service.queryShopWuliumuban(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insShopWuliumuban")
	public String insShopWuliumuban(HttpServletRequest req){
		
		return "shop/shop_wuliu_add";
	}
	@RequestMapping(value ="/addShopWuliumuban", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopWuliumuban(HttpServletRequest req,ShopWuliumuban og){
		Message msg =createMsg();
		service.addShopWuliumuban(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDWULIU);
		return Json(msg);
		
	}
	@RequestMapping("/readShopWuliumuban")
	public String readShopWuliumuban(HttpServletRequest req,int id){
		ShopWuliumuban og =service.getShopWuliumuban(id);
		req.setAttribute("wuliu", Json(og));
		return "shop/shop_wuliu_upd";
	}
	@RequestMapping(value ="/updShopWuliumuban", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopWuliumuban(HttpServletRequest req,ShopWuliumuban og){
		Message msg =createMsg();
		ShopWuliumuban o =service.getShopWuliumuban(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDWULIU);
		if(service.updShopWuliumuban(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delShopWuliumuban")
	public String delShopWuliumuban(HttpServletRequest req,int id){
		
		ShopWuliumuban o =service.getShopWuliumuban(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELWULIU);
		
		if(service.delShopWuliumuban(id)){
			
		}
		return "redirect:/selShopWuliumuban";
	}
	
	
	
	//dt_shop_wuliumuban_item(商家物流模板明细)
	
	@RequestMapping("/selShopWuliumubanItem")
	public String selShopWuliumubanItem(HttpServletRequest req){
		
		return "shop/shop_wuliuitem_list";
	}
	@RequestMapping(value ="/queryShopWuliumubanItem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopWuliumubanItem(HttpServletRequest req,ShopWuliumubanItemVo vo){
		Message msg =createMsg();
		
		Page<ShopWuliumubanItem> page =service.queryShopWuliumubanItem(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insShopWuliumubanItem")
	public String insShopWuliumubanItem(HttpServletRequest req){
		
		return "shop/shop_wuliuitem_add";
	}
	@RequestMapping(value ="/addShopWuliumubanItem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopWuliumubanItem(HttpServletRequest req,ShopWuliumubanItem og){
		Message msg =createMsg();
		service.addShopWuliumubanItem(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDWULIUITEM);
		return Json(msg);
		
	}
	@RequestMapping("/readShopWuliumubanItem")
	public String readShopWuliumubanItem(HttpServletRequest req,int id){
		ShopWuliumubanItem og =service.getShopWuliumubanItem(id);
		req.setAttribute("wuliuitem", Json(og));
		return "shop/shop_wuliuitem_upd";
	}
	@RequestMapping(value ="/updShopWuliumubanItem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopWuliumubanItem(HttpServletRequest req,ShopWuliumubanItem og){
		Message msg =createMsg();
		ShopWuliumubanItem o =service.getShopWuliumubanItem(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDWULIUITEM);
		if(service.updShopWuliumubanItem(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delShopWuliumubanItem")
	public String delShopWuliumubanItem(HttpServletRequest req,int id){
		
		ShopWuliumubanItem o =service.getShopWuliumubanItem(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELWULIUITEM);
		
		if(service.delShopWuliumubanItem(id)){
			
		}
		return "redirect:/selShopWuliumubanItem";
	}
	
	
	
	//dt_shop_zisong(商家自送)
	
	@RequestMapping("/selShopZisong")
	public String selShopZisong(HttpServletRequest req){
		
		return "shop/shop_zisong_list";
	}
	@RequestMapping(value ="/queryShopZisong", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopZisong(HttpServletRequest req,ShopZisongVo vo){
		Message msg =createMsg();
		
		Page<ShopZisong> page =service.queryShopZisong(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insShopZisong")
	public String insShopZisong(HttpServletRequest req){
		
		return "shop/shop_zisong_add";
	}
	@RequestMapping(value ="/addShopZisong", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopZisong(HttpServletRequest req,ShopZisong og){
		Message msg =createMsg();
		service.addShopZisong(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDZISONG);
		return Json(msg);
		
	}
	@RequestMapping("/readShopZisong")
	public String readShopZisong(HttpServletRequest req,int id){
		ShopZisong og =service.getShopZisong(id);
		req.setAttribute("zisong", Json(og));
		return "shop/shop_zisong_upd";
	}
	@RequestMapping(value ="/updShopZisong", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopZisong(HttpServletRequest req,ShopZisong og){
		Message msg =createMsg();
		ShopZisong o =service.getShopZisong(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDZISONG);
		if(service.updShopZisong(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delShopZisong")
	public String delShopZisong(HttpServletRequest req,int id){
		
		ShopZisong o =service.getShopZisong(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELZISONG);
		
		if(service.delShopZisong(id)){
			
		}
		return "redirect:/selShopZisong";
	}
	
	
	
	//dt_shop_expensesInfo（商家配送、退货信息表）
	
	@RequestMapping("/selShopExpensesInfo")
	public String selShopExpensesInfo(HttpServletRequest req){
		
		return "shop/shop_expensesinfo_list";
	}
	@RequestMapping(value ="/queryShopExpensesInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopExpensesInfo(HttpServletRequest req,ShopExpensesInfoVo vo){
		Message msg =createMsg();
		
		Page<ShopExpensesInfo> page =service.queryShopExpensesInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insShopExpensesInfo")
	public String insShopExpensesInfo(HttpServletRequest req){
		
		return "shop/shop_expensesinfo_add";
	}
	@RequestMapping(value ="/addShopExpensesInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopExpensesInfo(HttpServletRequest req,ShopExpensesInfo og){
		Message msg =createMsg();
		service.addShopExpensesInfo(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDEXPENSES);
		return Json(msg);
		
	}
	@RequestMapping("/readShopExpensesInfo")
	public String readShopExpensesInfo(HttpServletRequest req,int id){
		ShopExpensesInfo og =service.getShopExpensesInfo(id);
		req.setAttribute("expensesinfo", Json(og));
		return "shop/shop_expensesinfo_upd";
	}
	@RequestMapping(value ="/updShopExpensesInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopExpensesInfo(HttpServletRequest req,ShopExpensesInfo og){
		Message msg =createMsg();
		ShopExpensesInfo o =service.getShopExpensesInfo(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDEXPENSES);
		if(service.updShopExpensesInfo(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delShopExpensesInfo")
	public String delShopExpensesInfo(HttpServletRequest req,int id){
		
		ShopExpensesInfo o =service.getShopExpensesInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELEXPENSES);
		
		if(service.delShopExpensesInfo(id)){
			
		}
		return "redirect:/selShopExpensesInfo";
	}
	
	
	
	//dt_business_sms(短信信息表)
	
	@RequestMapping("/selBusinessSms")
	public String selBusinessSms(HttpServletRequest req){
		
		return "shop/shop_businesssms_list";
	}
	@RequestMapping(value ="/queryBusinessSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBusinessSms(HttpServletRequest req,BusinessSmsVo vo){
		Message msg =createMsg();
		
		Page<BusinessSms> page =service.queryBusinessSms(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insBusinessSms")
	public String insBusinessSms(HttpServletRequest req){
		
		return "shop/shop_businesssms_add";
	}
	@RequestMapping(value ="/addBusinessSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBusinessSms(HttpServletRequest req,BusinessSms og){
		Message msg =createMsg();
		service.addBusinessSms(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDBUSINESSSMS);
		return Json(msg);
		
	}
	@RequestMapping("/readBusinessSms")
	public String readBusinessSms(HttpServletRequest req,int id){
		BusinessSms og =service.getBusinessSms(id);
		req.setAttribute("businesssms", Json(og));
		return "shop/shop_businesssms_upd";
	}
	@RequestMapping(value ="/updBusinessSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBusinessSms(HttpServletRequest req,BusinessSms og){
		Message msg =createMsg();
		BusinessSms o =service.getBusinessSms(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDBUSINESSSMS);
		if(service.updBusinessSms(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delBusinessSms")
	public String delBusinessSms(HttpServletRequest req,int id){
		
		BusinessSms o =service.getBusinessSms(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBUSINESSSMS);
		
		if(service.delBusinessSms(id)){
			
		}
		return "redirect:/selBusinessSms";
	}
	
	
	
	//dt_business_groupshop(托管店铺开关表)
	
	@RequestMapping("/selBusinessGroupshop")
	public String selBusinessGroupshop(HttpServletRequest req){
		
		return "shop/shop_groupshop_list";
	}
	@RequestMapping(value ="/queryBusinessGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBusinessGroupshop(HttpServletRequest req,BusinessGroupshopVo vo){
		Message msg =createMsg();
		
		Page<BusinessGroupshop> page =service.queryBusinessGroupshop(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insBusinessGroupshop")
	public String insBusinessGroupshop(HttpServletRequest req){
		
		return "shop/shop_groupshop_add";
	}
	@RequestMapping(value ="/addBusinessGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBusinessGroupshop(HttpServletRequest req,BusinessGroupshop og){
		Message msg =createMsg();
		service.addBusinessGroupshop(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDBUSISHOP);
		return Json(msg);
		
	}
	@RequestMapping("/readBusinessGroupshop")
	public String readBusinessGroupshop(HttpServletRequest req,int id){
		BusinessGroupshop og =service.getBusinessGroupshop(id);
		req.setAttribute("groupshop", Json(og));
		return "shop/shop_groupshop_upd";
	}
	@RequestMapping(value ="/updBusinessGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBusinessGroupshop(HttpServletRequest req,BusinessGroupshop og){
		Message msg =createMsg();
		BusinessGroupshop o =service.getBusinessGroupshop(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDBUSISHOP);
		if(service.updBusinessGroupshop(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delBusinessGroupshop")
	public String delBusinessGroupshop(HttpServletRequest req,int id){
		
		BusinessGroupshop o =service.getBusinessGroupshop(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBUSISHOP);
		
		if(service.delBusinessGroupshop(id)){
			
		}
		return "redirect:/selBusinessGroupshop";
	}
	
	
	
	//dt_user_role_shopinfo_group(商家集团信身份表)
	
	@RequestMapping("/selUserRoleShopinfoGroup")
	public String selUserRoleShopinfoGroup(HttpServletRequest req){
		
		return "shop/shop_shopinfogroup_list";
	}
	@RequestMapping(value ="/queryUserRoleShopinfoGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleShopinfoGroup(HttpServletRequest req,UserRoleShopinfoGroupVo vo){
		Message msg =createMsg();
		
		Page<UserRoleShopinfoGroup> page =service.queryUserRoleShopinfoGroup(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleShopinfoGroup")
	public String insUserRoleShopinfoGroup(HttpServletRequest req){
		
		return "shop/shop_shopinfogroup_add";
	}
	@RequestMapping(value ="/addUserRoleShopinfoGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleShopinfoGroup(HttpServletRequest req,UserRoleShopinfoGroup og){
		Message msg =createMsg();
		service.addUserRoleShopinfoGroup(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDINFOGROUP);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleShopinfoGroup")
	public String readUserRoleShopinfoGroup(HttpServletRequest req,int id){
		UserRoleShopinfoGroup og =service.getUserRoleShopinfoGroup(id);
		req.setAttribute("shopinfo", Json(og));
		return "shop/shop_shopinfogroup_upd";
	}
	@RequestMapping(value ="/updUserRoleShopinfoGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleShopinfoGroup(HttpServletRequest req,UserRoleShopinfoGroup og){
		Message msg =createMsg();
		UserRoleShopinfoGroup o =service.getUserRoleShopinfoGroup(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDINFOGROUP);
		if(service.updUserRoleShopinfoGroup(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleShopinfoGroup")
	public String delUserRoleShopinfoGroup(HttpServletRequest req,int id){
		
		UserRoleShopinfoGroup o =service.getUserRoleShopinfoGroup(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELINFOGROUP);
		
		if(service.delUserRoleShopinfoGroup(id)){
			
		}
		return "redirect:/selUserRoleShopinfoGroup";
	}
	
	
	
	//dt_user_role_shopinfo_grouprelation(集团托管商家店铺关联表)
	
	@RequestMapping("/selUserRoleShopinfoGrouprelation")
	public String selUserRoleShopinfoGrouprelation(HttpServletRequest req){
		
		return "shop/shop_grouprelation_list";
	}
	@RequestMapping(value ="/queryUserRoleShopinfoGrouprelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleShopinfoGrouprelation(HttpServletRequest req,UserRoleShopinfoGrouprelationVo vo){
		Message msg =createMsg();
		
		Page<UserRoleShopinfoGrouprelation> page =service.queryUserRoleShopinfoGrouprelation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleShopinfoGrouprelation")
	public String insUserRoleShopinfoGrouprelation(HttpServletRequest req){
		
		return "shop/shop_grouprelation_add";
	}
	@RequestMapping(value ="/addUserRoleShopinfoGrouprelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleShopinfoGrouprelation(HttpServletRequest req,UserRoleShopinfoGrouprelation og){
		Message msg =createMsg();
		service.addUserRoleShopinfoGrouprelation(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDGROUPREL);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleShopinfoGrouprelation")
	public String readUserRoleShopinfoGrouprelation(HttpServletRequest req,int id){
		UserRoleShopinfoGrouprelation og =service.getUserRoleShopinfoGrouprelation(id);
		req.setAttribute("grouprelation", Json(og));
		return "shop/shop_grouprelation_upd";
	}
	@RequestMapping(value ="/updUserRoleShopinfoGrouprelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleShopinfoGrouprelation(HttpServletRequest req,UserRoleShopinfoGrouprelation og){
		Message msg =createMsg();
		UserRoleShopinfoGrouprelation o =service.getUserRoleShopinfoGrouprelation(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDGROUPREL);
		if(service.updUserRoleShopinfoGrouprelation(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleShopinfoGrouprelation")
	public String delUserRoleShopinfoGrouprelation(HttpServletRequest req,int id){
		
		UserRoleShopinfoGrouprelation o =service.getUserRoleShopinfoGrouprelation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELGROUPREL);
		
		if(service.delUserRoleShopinfoGrouprelation(id)){
			
		}
		return "redirect:/selUserRoleShopinfoGrouprelation";
	}
	
	
	
	//dt_user_role_shopinfo_matter(用户商家附加信息素材表)
	
	@RequestMapping("/selUserRoleShopinfoMatter")
	public String selUserRoleShopinfoMatter(HttpServletRequest req){
		
		return "shop/shop_matter_list";
	}
	@RequestMapping(value ="/queryUserRoleShopinfoMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleShopinfoMatter(HttpServletRequest req,UserRoleShopinfoMatterVo vo){
		Message msg =createMsg();
		
		Page<UserRoleShopinfoMatter> page =service.queryUserRoleShopinfoMatter(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleShopinfoMatter")
	public String insUserRoleShopinfoMatter(HttpServletRequest req){
		
		return "shop/shop_matter_add";
	}
	@RequestMapping(value ="/addUserRoleShopinfoMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleShopinfoMatter(HttpServletRequest req,UserRoleShopinfoMatter og){
		Message msg =createMsg();
		service.addUserRoleShopinfoMatter(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDINFOMATTER);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleShopinfoMatter")
	public String readUserRoleShopinfoMatter(HttpServletRequest req,int id){
		UserRoleShopinfoMatter og =service.getUserRoleShopinfoMatter(id);
		req.setAttribute("shopmatter", Json(og));
		return "shop/shop_matter_upd";
	}
	@RequestMapping(value ="/updUserRoleShopinfoMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleShopinfoMatter(HttpServletRequest req,UserRoleShopinfoMatter og){
		Message msg =createMsg();
		UserRoleShopinfoMatter o =service.getUserRoleShopinfoMatter(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDINFOMATTER);
		if(service.updUserRoleShopinfoMatter(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleShopinfoMatter")
	public String delUserRoleShopinfoMatter(HttpServletRequest req,int id){
		
		UserRoleShopinfoMatter o =service.getUserRoleShopinfoMatter(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELINFOMATTER);
		
		if(service.delUserRoleShopinfoMatter(id)){
			
		}
		return "redirect:/selUserRoleShopinfoMatter";
	}
	
	
	
	@RequestMapping(value="/findExpress",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findGroupList(HttpServletRequest req){
		Message msg = createMsg();
		List<ShopWuliumuban> agentGroups =service.findExpress();
		msg.setStatus("0");
		msg.setResult(agentGroups);
		return Json(msg);
		
	}
	
	@RequestMapping(value="/findChannelList",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findChannelList(HttpServletRequest req){
		Message msg = createMsg();
		List<Cooperate> agentGroups =service.findChannelList();
		msg.setStatus("0");
		msg.setResult(agentGroups);
		return Json(msg);
		
	}
	
}
