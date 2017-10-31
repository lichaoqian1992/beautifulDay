package com.manji.backstage.controller.property;


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
import com.manji.backstage.model.property.Config;
import com.manji.backstage.model.property.EmailTemplate;
import com.manji.backstage.model.property.Express;
import com.manji.backstage.model.property.Medal;
import com.manji.backstage.model.property.Payment;
import com.manji.backstage.model.property.SmsTemplate;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.property.PropertyService;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.property.ConfigVo;
import com.manji.backstage.vo.property.DistributionVo;
import com.manji.backstage.vo.property.EmailTemplateVo;
import com.manji.backstage.vo.property.ExpressVo;
import com.manji.backstage.vo.property.MedalVo;
import com.manji.backstage.vo.property.PaymentVo;
import com.manji.backstage.vo.property.SmsTemplateVo;
import com.manji.backstage.model.property.Distribution;

@Controller
public class PropertyController extends BaseController{

	@Autowired
	private PropertyService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("property");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	@RequestMapping("/selSmsTemplate")
	public String selSmsTemplate(HttpServletRequest req){
		
		return "property/property_smstemplate_list";
	}
	@RequestMapping(value ="/querySmsTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySmsTemplate(HttpServletRequest req ,SmsTemplateVo vo){
		Message msg =createMsg();
		
		Page<SmsTemplate> page =service.querySmsTemplate(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insSmsTemplate")
	public String insSmsTemplate(HttpServletRequest req){
		
		return "property/property_smstemplate_add";
	}
	@RequestMapping(value ="/addSmsTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSmsTemplate(HttpServletRequest req,SmsTemplate st){
		Message msg =createMsg();
		
		service.addSmsTemplate(st);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(st),LogRemark.ADDSMSTEMP);
		return Json(msg);
	}
	@RequestMapping("/readSmsTemplate")
	public String readSmsTemplate(HttpServletRequest req,int id){
		
		SmsTemplate st =service.getSmsTemplate(id);
		req.setAttribute("smstemplate", Json(st));
		return "property/property_smstemplate_upd";
	}
	@RequestMapping(value ="/updSmsTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSmsTemplate(HttpServletRequest req,SmsTemplate st){
		Message msg =createMsg();
		SmsTemplate s =service.getSmsTemplate(st.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(s),LogRemark.UPDSMSTEMP);
		if(service.updSmsTemplate(st)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping(value ="/delSmsTemplate")
	public String delSmsTemplate(HttpServletRequest req,int id){
		Message msg =createMsg();
		SmsTemplate s =service.getSmsTemplate(id);
		saveLog(createLog(req),LogRemark.DEL,Json(s),LogRemark.DELSMSTEMP);
		if(service.delSmsTemplate(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selSmsTemplate";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selEmailTemplate")
	public String selEmailTemplate(HttpServletRequest req){
		
		return "property/property_emailtemplate_list";
	}
	@RequestMapping("/insEmailTemplate")
	public String insEmailTemplate(HttpServletRequest req){
		
		return "property/property_emailtemplate_add";
	}
	@RequestMapping("/readEmailTemplate")
	public String chgEmailTemplate(HttpServletRequest req,int id){
		EmailTemplate et =service.getEmailTemplate(id);
		req.setAttribute("emailtemplate", Json(et));
		return "property/property_emailtemplate_upd";
	}
	@RequestMapping(value ="/queryEmailTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryEmailTemplate(HttpServletRequest req,EmailTemplateVo vo){
		Message msg =createMsg();
		Page<EmailTemplate> page =service.queryEmailTemplate(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping(value ="/addEmailTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addEmailTemplate(HttpServletRequest req,EmailTemplate et){
		Message msg =createMsg();
		System.out.println(Json(et));
		
		service.addEmailTemplate(et);
		
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(et),LogRemark.ADDEMAILTEMP);
		return Json(msg);
	}
	
	@RequestMapping(value ="/updEmailTemplate", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updEmailTemplate(HttpServletRequest req,EmailTemplate et){
		Message msg =createMsg();
		EmailTemplate e =service.getEmailTemplate(et.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(e),LogRemark.UPDEMAILTEMP);
		if(service.updEmailTemplate(et)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delEmailTemplate")
	public String delEmailTemplate(HttpServletRequest req,int id){
		Message msg =createMsg();
		EmailTemplate e =service.getEmailTemplate(id);
		saveLog(createLog(req),LogRemark.DEL,Json(e),LogRemark.DELEMAILTEMP);
		if(service.delEmailTemplate(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selEmailTemplate";
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value ="/addConfig", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addConfig(HttpServletRequest req,Config conf){
		Message msg =createMsg();
		service.addConfig(conf);
		
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(conf),LogRemark.ADDCONFIG);
		return Json(msg);
	}
	@RequestMapping(value ="/queryConfig", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderConfig(HttpServletRequest req,ConfigVo vo){
		Message msg =createMsg();
	
		Page<Config> page =service.queryConfig(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping(value ="/updConfig", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updConfig(HttpServletRequest req,Config conf){
		Message msg =createMsg();
		Config c =service.getConfig(conf.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(c),LogRemark.UPDCONFIG);
		if(service.updConfig(conf)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	/////////////////////////////////////////////订单管理
	@RequestMapping("/selOrderConfig")
	public String selOrderConfig(HttpServletRequest req){
		
		return "property/property_orderconfig_list";
	}
	@RequestMapping("/insOrderConfig")
	public String insOrderConfig(HttpServletRequest req){
		return "property/property_orderconfig_add";
	}
	@RequestMapping("/readOrderConfig")
	public String chgOrderConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_orderconfig_upd";
	}
	@RequestMapping("/delOrderConfig")
	public String delOrderConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELORDERCONFIG);
		service.delConfig(id);
		return "redirect:/selOrderConfig";
	}
	
	

	

	/////////////////////////////////////////////角色管理
	@RequestMapping("/selRoleConfig")
	public String selRoleConfig(HttpServletRequest req){
		
		return "property/property_roleconfig_list";
	}
	@RequestMapping("/insRoleConfig")
	public String insRoleConfig(HttpServletRequest req){
		return "property/property_roleconfig_add";
	}
	@RequestMapping("/readRoleConfig")
	public String chgRoleConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_roleconfig_upd";
	}
	@RequestMapping("/delRoleConfig")
	public String delRoleConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELROLECONFIG);
		service.delConfig(id);
		return "redirect:/selRoleConfig";
	}

	/////////////////////////////////////////////提现管理
	@RequestMapping("/selWithConfig")
	public String selWithConfig(HttpServletRequest req){
		
		return "property/property_withconfig_list";
	}
	@RequestMapping("/insWithConfig")
	public String insWithConfig(HttpServletRequest req){
		return "property/property_withconfig_add";
	}
	@RequestMapping("/readWithConfig")
	public String chgWithConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_withconfig_upd";
	}
	@RequestMapping("/delWithConfig")
	public String delWithConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELWITHCONFIG);
		service.delConfig(id);
		return "redirect:/selWithConfig";
	}
	
	/////////////////////////////////////////////系统配置
	
	@RequestMapping("/selSysConfig")
	public String selSysConfig(HttpServletRequest req){
		
		return "property/property_sysconfig_list";
	}
	@RequestMapping("/insSysConfig")
	public String insSysConfig(HttpServletRequest req){
		return "property/property_sysconfig_add";
	}
	@RequestMapping("/readSysConfig")
	public String chgSysConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_sysconfig_upd";
	}
	@RequestMapping("/delSysConfig")
	public String delSysConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELSYSCONFIG);
		service.delConfig(id);
		return "redirect:/selsysConfig";
	}
	
	
	/////////////////////////////////////////////业务配置
	
	@RequestMapping("/selBusyConfig")
	public String selBusyConfig(HttpServletRequest req){
		
		return "property/property_busyconfig_list";
	}
	@RequestMapping("/insBusyConfig")
	public String insBusyConfig(HttpServletRequest req){
		return "property/property_busyconfig_add";
	}
	@RequestMapping("/readBusyConfig")
	public String chgBusyConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_busyconfig_upd";
	}
	@RequestMapping("/delBusyConfig")
	public String delBusyConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELBUSYCONFIG);
		service.delConfig(id);
		return "redirect:/selBusyConfig";
	}
	
	
	
	/////////////////////////////////////////////用户设置
	
	@RequestMapping("/selUserConfig")
	public String selUserConfig(HttpServletRequest req){
		
		return "property/property_userconfig_list";
	}
	@RequestMapping("/insUserConfig")
	public String insUserConfig(HttpServletRequest req){
		return "property/property_userconfig_add";
	}
	@RequestMapping("/readUserConfig")
	public String chgUserConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_userconfig_upd";
	}
	@RequestMapping("/delUserConfig")
	public String delUserConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELUSERCONFIG);
		service.delConfig(id);
		return "redirect:/selUserConfig";
	}
	
	
	/////////////////////////////////////////////支付配置
	
	@RequestMapping("/selPayConfig")
	public String selPayConfig(HttpServletRequest req){
		
		return "property/property_payconfig_list";
	}
	@RequestMapping("/insPayConfig")
	public String insPayConfig(HttpServletRequest req){
		return "property/property_payconfig_add";
	}
	@RequestMapping("/readPayConfig")
	public String chgPayConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_payconfig_upd";
	}
	@RequestMapping("/delPayConfig")
	public String delPayConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELPAYCONFIG);
		service.delConfig(id);
		return "redirect:/selPayConfig";
	}
	
	/////////////////////////////////////////////转账配置
	
	@RequestMapping("/selTranConfig")
	public String selTranConfig(HttpServletRequest req){
		
		return "property/property_tranconfig_list";
	}
	@RequestMapping("/insTranConfig")
	public String insTranConfig(HttpServletRequest req){
		return "property/property_tranconfig_add";
	}
	@RequestMapping("/readTranConfig")
	public String chgTranConfig(HttpServletRequest req,int id){
		Config conf =service.getConfig(id);
		req.setAttribute("config", Json(conf));
		return "property/property_tranconfig_upd";
	}
	@RequestMapping("/delTranConfig")
	public String delTranConfig(HttpServletRequest req,int id){
		Config c =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(c),LogRemark.DELTRANCONFIG);
		service.delConfig(id);
		return "redirect:/seltranConfig";
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////快递配送
	

	@RequestMapping("/selDistribution")
	public String selDistribution(HttpServletRequest req){
		
		return "property/property_distribution_list";
	}
	@RequestMapping(value ="/queryDistribution", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryDistribution(HttpServletRequest req,DistributionVo vo){
		Message msg =createMsg();
		Page<Distribution> page =service.queryDistribution(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			
		}
		return Json(msg);
	}
	@RequestMapping("/insDistribution")
	public String insDistribution(HttpServletRequest req){
		
		return "property/property_distribution_add";
	}
	@RequestMapping(value ="/addDistribution", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addDistribution(HttpServletRequest req,Distribution dist){
		
		Message msg =createMsg();
		
		service.addDistribution(dist);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(dist),LogRemark.ADDDISTRIBUTION);
		return Json(msg);
		
	}
	@RequestMapping("/readDistribution")
	public String chgDistribution(HttpServletRequest req,int id){
		Distribution dist =service.getDistribution(id);
		req.setAttribute("distinfo", Json(dist));
		return "property/property_distribution_upd";
	}
	@RequestMapping(value ="/updDistribution", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updDistribution(HttpServletRequest req,Distribution dist){
		Message msg =createMsg();
		Distribution d =service.getDistribution(dist.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(d),LogRemark.UPDDISTRIBUTION);
		if(service.updDistribution(dist)){
			msg.setStatus("0");
			
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delDistribution")
	public String delDistribution(HttpServletRequest req,int id){
		
		Message msg =createMsg();
		Distribution d =service.getDistribution(id);
		saveLog(createLog(req),LogRemark.DEL,Json(d),LogRemark.DELDISTRIBUTION);
		if(service.delDistribution(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selDistribution";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////物流
	
	@RequestMapping("/selExpress")
	public String selExpress(HttpServletRequest req){
		
		return "property/property_express_list";
		
	}
	@RequestMapping(value ="/queryExpress", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryExpress(HttpServletRequest req,ExpressVo vo){
		Message msg =createMsg();
		Page<Express> page =service.queryExpress(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insExpress")
	public String insExpress(HttpServletRequest req){
		
		return "property/property_express_add";
	}
	@RequestMapping(value ="/addExpress", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addExpress(HttpServletRequest req,Express ex){
		Message msg =createMsg();
		service.addExpress(ex);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ex),LogRemark.ADDEXPRESS);
		return Json(msg);
	}
	@RequestMapping("/readExpress")
	public String chgExpress(HttpServletRequest req,int id){
		Express ex =service.getExpress(id);
		req.setAttribute("expressinfo", Json(ex));
		return "property/property_express_upd";
	}
	@RequestMapping(value ="/updExpress", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updExpress(HttpServletRequest req,Express ex){
		Message msg =createMsg();
		Express e =service.getExpress(ex.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(e),LogRemark.UPDEXPRESS);
		if(service.updExpress(ex)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delExpress")
	public String delExpress(HttpServletRequest req,int id){
		Message msg =createMsg();
		Express e =service.getExpress(id);
		saveLog(createLog(req),LogRemark.DEL,Json(e),LogRemark.DELEXPRESS);
		if(service.delExpress(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selExpress";
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////在线支付渠道

	@RequestMapping("/selPayment")
	public String selPayment(HttpServletRequest req){
		
		return "property/property_payment_list";
	}
	@RequestMapping("/insPayment")
	public String insPayment(HttpServletRequest req){
		
		return "property/property_payment_add";
	}
	@RequestMapping(value ="/queryPayment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPayment(HttpServletRequest req,PaymentVo vo){
		Message msg =createMsg();
		Page<Payment> page =service.queryPayment(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);	
	}
	@RequestMapping(value ="/addPayment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addPayment(HttpServletRequest req,Payment pay){
		
		Message msg =createMsg();
		service.addPayment(pay);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(pay),LogRemark.ADDPAYMENT);
		return Json(msg);
	}
	@RequestMapping("/readPayment")
	public String chgPayment(HttpServletRequest req,int id){
		Payment pay =service.getPayment(id);
		req.setAttribute("paymentinfo", Json(pay));
		return "property/property_payment_upd";
	}
	@RequestMapping(value ="/updPayment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPayment(HttpServletRequest req,Payment pay){
		Message msg =createMsg();
		Payment p =service.getPayment(pay.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(p),LogRemark.UPDPAYMENT);
		if(service.updPayment(pay)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delPayment")
	public String delPayment(HttpServletRequest req,int id){
		Message msg =createMsg();
		Payment p =service.getPayment(id);
		saveLog(createLog(req),LogRemark.DEL,Json(p),LogRemark.DELPAYMENT);
		if(service.delPayment(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
//		return Json(msg);
		return "redirect:/selPayment";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////荣誉勋章

	
	
	
	@RequestMapping("/selMedal")
	public String selMedal(HttpServletRequest req){
		
		return "property/property_medal_list";
	}
	@RequestMapping("/insMedal")
	public String insMedal(HttpServletRequest req){
		
		return "property/property_medal_add";
	}
	@RequestMapping(value ="/queryMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryMedal(HttpServletRequest req ,MedalVo vo){
		Message msg =createMsg();
		Page<Medal> page =service.queryMedal(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);	
		
		
	}
	@RequestMapping(value ="/addMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addMedal(HttpServletRequest req,Medal medal){
		Message msg =createMsg();
		service.addMedal(medal);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(medal),LogRemark.ADDMEDAL);
		return Json(msg);
	}
	@RequestMapping("/readMedal")
	public String chgMedal(HttpServletRequest req,int id){
		Medal medal =service.getMedal(id);
		req.setAttribute("medalinfo", Json(medal));
		return "property/property_medal_upd";
	}
	@RequestMapping(value ="/updMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updMedal(HttpServletRequest req,Medal medal){
		Message msg =createMsg();
		Medal m =service.getMedal(medal.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMEDAL);
		if(service.updMedal(medal)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delMedal")
	public String delMedal(HttpServletRequest req,int id){
		Message msg =createMsg();
		Medal m =service.getMedal(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMEDAL);
		if(service.delMedal(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selMedal";
	}
	
	
	
	
}
