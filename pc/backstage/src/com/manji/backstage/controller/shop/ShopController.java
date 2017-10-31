package com.manji.backstage.controller.shop;


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
import com.manji.backstage.model.shop.Business;
import com.manji.backstage.model.shop.BuyerInfo;
import com.manji.backstage.model.shop.CompContent;
import com.manji.backstage.model.shop.CompHandle;
import com.manji.backstage.model.shop.Complaint;
import com.manji.backstage.model.shop.Cooperate;
import com.manji.backstage.model.shop.OtherInfo;
import com.manji.backstage.model.shop.ScopeInfo;
import com.manji.backstage.model.shop.ShopInfo;
import com.manji.backstage.model.shop.TempShopInfo;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.shop.ShopService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.shop.BusinessVo;
import com.manji.backstage.vo.shop.BuyerInfoVo;
import com.manji.backstage.vo.shop.CompContentVo;
import com.manji.backstage.vo.shop.CompHandleVo;
import com.manji.backstage.vo.shop.ComplaintVo;
import com.manji.backstage.vo.shop.CooperateVo;
import com.manji.backstage.vo.shop.OtherInfoVo;
import com.manji.backstage.vo.shop.ScopeInfoVo;
import com.manji.backstage.vo.shop.ShopInfoVo;
import com.manji.backstage.vo.shop.TempShopInfoVo;
@Controller
public class ShopController extends BaseController{

	@Autowired
	private ShopService service;
	@Autowired
	private LoggersService logService;
	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("agent");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
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
		ShopInfo agt =service.getShopInfo(si.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPINFO);
		if(service.updShopInfo(si)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
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
	@RequestMapping(value ="/updTempShopInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updTempShopInfo(HttpServletRequest req,TempShopInfo tsi){
		Message msg =createMsg();
		TempShopInfo agt =service.getTempShopInfo(tsi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDSHOPTEMP);
		if(service.updTempShopInfo(tsi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insTempShopInfo")
	public String insTempShopInfo(HttpServletRequest req){
		return "shop/shop_tempinfo_add";
	}
	@RequestMapping("/addTempShopInfo")
	@ResponseBody
	public String addTempShopInfo(HttpServletRequest req,TempShopInfo tsi){
		Message msg = createMsg();
		service.addTempShopInfo(tsi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(tsi),LogRemark.ADDSHOPTEMP);
		return Json(msg);
	}
	@RequestMapping("/delTempShopInfo")
	public String delTempShopInfo(HttpServletRequest req,int id){
		TempShopInfo agt =service.getTempShopInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELSHOPTEMP);
		return "redirect:/selTempShopInfo";
	}
	
	
	@RequestMapping("/selBusiness")
	public String selBusiness(HttpServletRequest req){
		
		return "shop/shop_business_list";
	}
	@RequestMapping(value ="/queryBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBusiness(HttpServletRequest req,BusinessVo vo){
		Message msg =createMsg();
		
		Page<Business> page =service.queryBusiness(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insBusiness")
	public String insBusiness(HttpServletRequest req){
		
		return "shop/shop_business_add";
	}
	@RequestMapping(value ="/addBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBusiness(HttpServletRequest req,Business bus){
		Message msg =createMsg();
		service.addBusiness(bus);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(bus),LogRemark.ADDBUSINESSTYPE);

		return Json(msg);
	}
	@RequestMapping("/readBusiness")
	public String readBusiness(HttpServletRequest req,int id){
		Business bus =service.getBusiness(id);
		req.setAttribute("businessinfo", Json(bus));
		return "shop/shop_business_upd";
	}
	@RequestMapping(value ="/updBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBusiness(HttpServletRequest req,Business bus){
		Message msg =createMsg();
		Business agt =service.getBusiness(bus.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDBUSINESSTYPE);
		if(service.updBusiness(bus)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/delBusiness")
	public String delBusiness(HttpServletRequest req,int id){
		Message msg =createMsg();
		Business agt =service.getBusiness(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELBUSINESSTYPE);
		if(service.delBusiness(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selBusiness";
	}
	
	
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
	@RequestMapping("/selBuyerInfo")
	public String selBuyerInfo(HttpServletRequest req){
		
		return "shop/shop_buyerinfo_list";
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
		return "shop/shop_buyerinfo_upd";
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
		
		return "shop/shop_buyerinfo_add";
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
	
	
	
	
	@RequestMapping("/selComplaint")
	public String selComplaint(HttpServletRequest req){
		
		return "shop/complaint_list";
		
	}
	@RequestMapping(value ="/queryComplaint", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryComplaint(HttpServletRequest req,ComplaintVo vo){
		Message msg =createMsg();
		
		Page<Complaint> page =service.queryComplaint(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readComplaint")
	public String readComplaint(HttpServletRequest req,int id){
		Complaint c =service.getComplaint(id);
		req.setAttribute("complaint", Json(c));
		return "shop/complaint_upd";
		
	}
	@RequestMapping(value ="/updComplaint", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updComplaint(HttpServletRequest req,Complaint c){
		Message msg =createMsg();
		Complaint agt =service.getComplaint(c.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDCOMPLAINT);
		if(service.updComplaint(c)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insComplaint")
	public String insComplaint(HttpServletRequest req){
		
		return "shop/complaint_add";
	}
	@RequestMapping(value ="/addComplaint", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addComplaint(HttpServletRequest req,Complaint c){
		Message msg =createMsg();
		service.addComplaint(c);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(c),LogRemark.ADDCOMPLAINT);
		return Json(msg);
	}
	@RequestMapping("/delComplaint")
	public String delComplaint(HttpServletRequest req,int id){
		Message msg =createMsg();
		Complaint agt =service.getComplaint(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMPLAINT);
		if(service.delComplaint(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selComplaint";
	}
	
	
	
	@RequestMapping("/selCompContent")
	public String selCompContent(HttpServletRequest req){
		
		return "shop/complaint_content_list";
	}
	@RequestMapping(value ="/queryCompContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCompContent(HttpServletRequest req,CompContentVo vo){
		Message msg =createMsg();
		
		Page<CompContent> page =service.queryCompContent(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readCompContent")
	public String readCompContent(HttpServletRequest req,int id){
		CompContent cc =service.getCompContent(id);
		req.setAttribute("compcontent", Json(cc));
		return "shop/complaint_content_upd";
		
	}
	@RequestMapping(value ="/updCompContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCompContent(HttpServletRequest req,CompContent cc){
		Message msg =createMsg();
		CompContent agt =service.getCompContent(cc.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDCOMCONTENT);
		if(service.updCompContent(cc)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insCompContent")
	public String insCompContent(HttpServletRequest req){
		return "shop/complaint_content_add";
	}
	@RequestMapping(value ="/addCompContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCompContent(HttpServletRequest req,CompContent cc){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(cc),LogRemark.ADDCOMCONTENT);
		service.addCompContent(cc);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/delCompContent")
	public String delCompContent(HttpServletRequest req,int id){
		Message msg =createMsg();
		CompContent agt =service.getCompContent(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMCONTENT);
		if(service.delCompContent(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selCompContent";
	}
	
	
	
	
	@RequestMapping("/selCompHandle")
	public String selCompHandle(HttpServletRequest req){
		
		return "shop/complaint_handle_list";
	}
	@RequestMapping(value ="/queryCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCompHandle(HttpServletRequest req,CompHandleVo vo){
		Message msg =createMsg();
		
		Page<CompHandle> page =service.queryCompHandle(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readCompHandle")
	public String readCompHandle(HttpServletRequest req,int id){
		CompHandle ch =service.getCompHandle(id);
		req.setAttribute("comphandle", Json(ch));
		return "shop/complaint_handle_upd";
	}
	@RequestMapping(value ="/updCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCompHandle(HttpServletRequest req,CompHandle ch){
		Message msg =createMsg();
		CompHandle agt =service.getCompHandle(ch.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDCOMHANDLE);
		System.out.println(Json(ch));
		if(service.updCompHandle(ch)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insCompHandle")
	public String insCompHandle(HttpServletRequest req){
		
		return "shop/complaint_handle_add";
	}
	@RequestMapping(value ="/addCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCompHandle(HttpServletRequest req,CompHandle ch){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(ch),LogRemark.ADDCOMHANDLE);
		service.addCompHandle(ch);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/delCompHandle")
	public String delCompHandle(HttpServletRequest req,int id){
		Message msg =createMsg();
		CompHandle agt =service.getCompHandle(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMHANDLE);
		if(service.delCompHandle(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selCompHandle";
	}
	
	
}
