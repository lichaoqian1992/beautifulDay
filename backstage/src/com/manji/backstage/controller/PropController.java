package com.manji.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.oper.ApoCity;
import com.manji.backstage.model.oper.ApoCounty;
import com.manji.backstage.model.oper.ApoProvince;
import com.manji.backstage.model.oper.ApoScreen;
import com.manji.backstage.model.oper.ApoSfz;
import com.manji.backstage.model.oper.ApoSite;
import com.manji.backstage.model.oper.ApoSiteContent;
import com.manji.backstage.model.oper.ApoTown;
import com.manji.backstage.model.oper.ApoVillage;
import com.manji.backstage.model.oper.Category;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.PropService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.ArticleCategory;
import com.manji.backstage.model.content.Channel;
import com.manji.backstage.model.content.ChannelField;
import com.manji.backstage.model.content.ChannelSite;
import com.manji.backstage.model.content.ChannelSpec;
import com.manji.backstage.model.property.Banktype;
import com.manji.backstage.model.property.Config;
import com.manji.backstage.model.property.Distribution;
import com.manji.backstage.model.property.EmailTemplate;
import com.manji.backstage.model.property.Express;
import com.manji.backstage.model.property.Groups;
import com.manji.backstage.model.property.MbArticle;
import com.manji.backstage.model.property.MbCategory;
import com.manji.backstage.model.property.MbChannelMatter;
import com.manji.backstage.model.property.MbGoodsMatter;
import com.manji.backstage.model.property.MbImages;
import com.manji.backstage.model.property.MbShopMatter;
import com.manji.backstage.model.property.MbYewu;
import com.manji.backstage.model.property.Medal;
import com.manji.backstage.model.property.Payment;
import com.manji.backstage.model.property.Scorerule;
import com.manji.backstage.model.property.SmsTemplate;
import com.manji.backstage.model.property.UserOauthApp;
import com.manji.backstage.model.role.RoleOperatorType;
import com.manji.backstage.model.shop.Business;
import com.manji.backstage.vo.content.ChannelFieldVo;
import com.manji.backstage.vo.content.ChannelSiteVo;
import com.manji.backstage.vo.content.ChannelSpecVo;
import com.manji.backstage.vo.content.ChannelVo;
import com.manji.backstage.vo.operation.ApoCityVo;
import com.manji.backstage.vo.operation.ApoCountyVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;
import com.manji.backstage.vo.operation.ApoScreenVo;
import com.manji.backstage.vo.operation.ApoSfzVo;
import com.manji.backstage.vo.operation.ApoSiteContentVo;
import com.manji.backstage.vo.operation.ApoSiteVo;
import com.manji.backstage.vo.operation.ApoTownVo;
import com.manji.backstage.vo.operation.ApoVillageVo;
import com.manji.backstage.vo.property.BanktypeVo;
import com.manji.backstage.vo.property.ConfigVo;
import com.manji.backstage.vo.property.DistributionVo;
import com.manji.backstage.vo.property.EmailTemplateVo;
import com.manji.backstage.vo.property.ExpressVo;
import com.manji.backstage.vo.property.GroupsVo;
import com.manji.backstage.vo.property.MbArticleVo;
import com.manji.backstage.vo.property.MbCategoryVo;
import com.manji.backstage.vo.property.MbChannelMatterVo;
import com.manji.backstage.vo.property.MbGoodsMatterVo;
import com.manji.backstage.vo.property.MbImagesVo;
import com.manji.backstage.vo.property.MbShopMatterVo;
import com.manji.backstage.vo.property.MbYewuVo;
import com.manji.backstage.vo.property.MedalVo;
import com.manji.backstage.vo.property.PaymentVo;
import com.manji.backstage.vo.property.ScoreruleVo;
import com.manji.backstage.vo.property.SmsTemplateVo;
import com.manji.backstage.vo.property.UserOauthAppVo;
import com.manji.backstage.vo.role.RoleOperatorTypeVo;
import com.manji.backstage.vo.shop.BusinessVo;

@Controller
public class PropController extends BaseController{

	@Autowired
	private PropService service;
	@Autowired
	private LoggersService logService;
	@Autowired
	private ArtiService artiService;
	
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("prop");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
//	dt_sms_template	短信发送模板信息表

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
//	dt_mail_template	邮件发送模板信息表

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
//	dt_config	系统配置信息表

	@RequestMapping("/selConfig")
	public String selConfig(HttpServletRequest req){
		
		return "property/property_config_list";
	}
	@RequestMapping("/insConfig")
	public String insConfig(HttpServletRequest req){
		return "property/property_config_add";
	}
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
	
	@RequestMapping("readConfig")
	public String readConfig(HttpServletRequest req,int id){
		System.out.println(id);
		Config config = service.getConfig(id);
		System.out.println(config);
		req.setAttribute("configinfo", Json(config));
		return "property/property_config_upd";
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
	@RequestMapping("delConfig")
	public String delConfig(HttpServletRequest req,int id){
		Message msg = createMsg();
		Config config =service.getConfig(id);
		saveLog(createLog(req),LogRemark.DEL,Json(config),LogRemark.DELCONFIG);
		if(service.delConfig(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selConfig";
	}
	
	/**
	 * 分类配置信息
	 * @return
	 */
	@RequestMapping(value="/getConfigCategory",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getConfigCategory(){
		
		Message msg =createMsg();
		List<Config> config = service.getConfigCategory();
		msg.setStatus("0");
		msg.setResult(config);
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
//	dt_distribution	配送方式信息表


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
	
//	dt_express	快递物流方式信息表

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
//	dt_payment	支付方式信息表

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
		
		System.out.println(Json(vo).toString());
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
	
	@RequestMapping("/addPayment")
	public String addPayment(HttpServletRequest req,Payment pay,@RequestParam MultipartFile file){
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = artiService.sendPostReq(fileName, base64Str);
			pay.setImg_url(address);
		}else{
			pay.setImg_url(req.getParameter("img_url"));
		}
		service.addPayment(pay);
		return "redirect:/insPayment";
	}
	
	@RequestMapping("/readPayment")
	public String readPayment(HttpServletRequest req,int id){
		Payment pay =service.getPayment(id);
		req.setAttribute("paymentinfo", Json(pay));
		return "property/property_payment_upd";
	}
		
	@RequestMapping("/updPayment")
	public String updPayment(HttpServletRequest req,Payment pay,@RequestParam MultipartFile file){
		String id = req.getParameter("id");
		Message msg = createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = artiService.sendPostReq(fileName, base64Str);
			pay.setImg_url(address);
		}else{
			pay.setImg_url(req.getParameter("imgUrl"));
		}
		if(service.updPayment(pay)){
			msg.setStatus("0");
			msg.setResult("修改成功");
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
		}
		return "redirect:/readPayment?id="+id;
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

	
	
//	dt_medal	荣誉勋章信息表

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
	
//	dt_business_type	系统业务库
	
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
	
	
	//地理信息
	

	@RequestMapping("/selApoProvince")
	public String selApoProvince(HttpServletRequest req){
		
		return "operation/operation_apoprovince_list";
	}
	@RequestMapping(value ="/queryApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoProvince(HttpServletRequest req,ApoProvinceVo vo){
		Message msg =createMsg();
		
		Page<ApoProvince> page =service.queryApoProvince(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoProvince")
	public String insApoProvince(HttpServletRequest req){
		
		return "operation/operation_apoprovince_add";
	}
	@RequestMapping(value ="/addApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoProvince(HttpServletRequest req,ApoProvince av){
		Message msg =createMsg();
		service.addApoProvince(av);
		msg.setStatus("0");
		
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOPROVINCE);
		return Json(msg);
		
	}
	@RequestMapping("/readApoProvince")
	public String readApoProvince(HttpServletRequest req,String province_id){
		ApoProvince av =service.getApoProvince(province_id);
		req.setAttribute("apoprovinceinfo", Json(av));
		return "operation/operation_apoprovince_upd";
	}
	@RequestMapping(value ="/updApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoProvince(HttpServletRequest req,ApoProvince ap){
		Message msg =createMsg();
		ApoProvince av =service.getApoProvince(ap.getProvince_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOPROVINCE);
		if(service.updApoProvince(ap)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoProvince")
	public String delApoProvince(HttpServletRequest req ,String province_id){
		Message msg =createMsg();
		
		ApoProvince av =service.getApoProvince(province_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOPROVINCE);
		
		if(service.delApoProvince(province_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoCity")
	public String selApoCity(HttpServletRequest req){
		
		return "operation/operation_apocity_list";
	}
	@RequestMapping(value ="/queryApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoCity(HttpServletRequest req,ApoCityVo vo){
		Message msg =createMsg();
		
		Page<ApoCity> page =service.queryApoCity(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoCity")
	public String insApoCity(HttpServletRequest req){
		
		return "operation/operation_apocity_add";
	}
	@RequestMapping(value ="/addApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoCity(HttpServletRequest req,ApoCity ac){
		Message msg =createMsg();
		service.addApoCity(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDAPOCITY);
		return Json(msg);
		
	}
	@RequestMapping("/readApoCity")
	public String readApoCity(HttpServletRequest req,String city_id){
		ApoCity ac =service.getApoCity(city_id);
		req.setAttribute("apocityinfo", Json(ac));
		return "operation/operation_apocity_upd";
	}
	@RequestMapping(value ="/updApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoCity(HttpServletRequest req,ApoCity av){
		Message msg =createMsg();
		ApoCity ac =service.getApoCity(av.getCity_id());
		saveLog(createLog(req),LogRemark.UPD,Json(ac),LogRemark.UPDAPOCITY);
		
		if(service.updApoCity(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoCity")
	public String delApoCity(HttpServletRequest req ,String city_id){
		Message msg =createMsg();
		
		ApoCity ac =service.getApoCity(city_id);
		saveLog(createLog(req),LogRemark.DEL,Json(ac),LogRemark.DELAPOCITY);
		
		if(service.delApoCity(city_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoCounty")
	public String selApoCounty(HttpServletRequest req){
		
		return "operation/operation_apocounty_list";
	}
	@RequestMapping(value ="/queryApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoCounty(HttpServletRequest req,ApoCountyVo vo){
		Message msg =createMsg();
		
		Page<ApoCounty> page =service.queryApoCounty(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoCounty")
	public String insApoCounty(HttpServletRequest req){
		
		return "operation/operation_apocounty_add";
	}
	@RequestMapping(value ="/addApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoCounty(HttpServletRequest req,ApoCounty ac){
		Message msg =createMsg();
		service.addApoCounty(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDAPOCOUNTY);
		return Json(msg);
		
	}
	@RequestMapping("/readApoCounty")
	public String readApoCounty(HttpServletRequest req,String county_id){
		ApoCounty av =service.getApoCounty(county_id);
		req.setAttribute("apocountyinfo", Json(av));
		return "operation/operation_apocounty_upd";
	}
	@RequestMapping(value ="/updApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoCounty(HttpServletRequest req,ApoCounty ac){
		Message msg =createMsg();
		ApoCounty av =service.getApoCounty(ac.getCounty_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOCOUNTY);
		
		if(service.updApoCounty(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoCounty")
	public String delApoCounty(HttpServletRequest req ,String county_id){
		Message msg =createMsg();
		ApoCounty av =service.getApoCounty(county_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOCOUNTY);
		
		if(service.delApoCounty(county_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoTown")
	public String selApoTown(HttpServletRequest req){
		
		return "operation/operation_apotown_list";
	}
	@RequestMapping(value ="/queryApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoTown(HttpServletRequest req,ApoTownVo vo){
		Message msg =createMsg();
		
		Page<ApoTown> page =service.queryApoTown(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoTown")
	public String insApoTown(HttpServletRequest req){
		
		return "operation/operation_apotown_add";
	}
	@RequestMapping(value ="/addApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoTown(HttpServletRequest req,ApoTown at){
		Message msg =createMsg();
		service.addApoTown(at);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(at),LogRemark.ADDAPOTOWN);
		return Json(msg);
		
	}
	@RequestMapping("/readApoTown")
	public String readApoTown(HttpServletRequest req,String town_id){
		ApoTown av =service.getApoTown(town_id);
		req.setAttribute("apotowninfo", Json(av));
		return "operation/operation_apotown_upd";
	}
	@RequestMapping(value ="/updApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoTown(HttpServletRequest req,ApoTown at){
		Message msg =createMsg();
		ApoTown av =service.getApoTown(at.getTown_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOTOWN);
		if(service.updApoTown(at)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoTown")
	public String delApoTown(HttpServletRequest req ,String town_id){
		Message msg =createMsg();
		
		ApoTown av =service.getApoTown(town_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOTOWN);
		if(service.delApoTown(town_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoVillage")
	public String selApoVillage(HttpServletRequest req){
		
		return "operation/operation_apovillage_list";
	}
	@RequestMapping(value ="/queryApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoVillage(HttpServletRequest req,ApoVillageVo vo){
		Message msg =createMsg();
		
		Page<ApoVillage> page =service.queryApoVillage(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoVillage")
	public String insApoVillage(HttpServletRequest req){
		
		return "operation/operation_apovillage_add";
	}
	@RequestMapping(value ="/addApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoVillage(HttpServletRequest req,ApoVillage av){
		Message msg =createMsg();
		service.addApoVillage(av);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOVILLAGE);
		return Json(msg);
		
	}
	@RequestMapping("/readApoVillage")
	public String readApoVillage(HttpServletRequest req,String code){
		ApoVillage av =service.getApoVillage(code);
		req.setAttribute("apovillageinfo", Json(av));
		return "operation/operation_apovillage_upd";
	}
	@RequestMapping(value ="/updApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoVillage(HttpServletRequest req,ApoVillage av){
		Message msg =createMsg();
		ApoVillage a =service.getApoVillage(av.getCode());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOVILLAGE);
		if(service.updApoVillage(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoVillage")
	public String delApoVillage(HttpServletRequest req ,String code){
		Message msg =createMsg();
		ApoVillage a =service.getApoVillage(code);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOVILLAGE);
		
		if(service.delApoVillage(code)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	@RequestMapping("/selAddress")
	public String selAddress(HttpServletRequest req){
		
		return "operation/operation_findcitybyprovince";
	}
	//显示所有省份信息
	@RequestMapping(value ="/findProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findProvince(HttpServletRequest req){
		Message msg =createMsg();
		List<ApoProvince> ap = service.findProvince();
		if(ap.size()!=0){
			msg.setStatus("0");
			msg.setResult(ap);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据province_id查询city_name
	@RequestMapping(value ="/findCityByProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findCityByProvince(String province_id) {
		Message msg = createMsg();
		List<ApoCity> ac = service.findCityByProvince(province_id);
		if(ac.size()!=0){
			msg.setStatus("0");
			msg.setResult(ac);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据city_id查询county_name
	@RequestMapping(value ="/findCountyByCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findCountyByCity(String city_id) {
		Message msg = createMsg();
		List<ApoCounty> ac = service.findCountyByCity(city_id);
		if(ac.size()!=0){
			msg.setStatus("0");
			msg.setResult(ac);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	//根据county_id查询town_name
	@RequestMapping(value ="/findTownByCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findTownByCounty(String county_id) {
		Message msg = createMsg();
		List<ApoTown> at = service.findTownByCounty(county_id);
		if(at.size()!=0){
			msg.setStatus("0");
			msg.setResult(at);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据town_id查询village_name
	@RequestMapping(value ="/findVillageByTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findVillageByTown(String town_id) {
		Message msg = createMsg();
		List<ApoVillage> at = service.findVillageByTown(town_id);
		if(at.size()!=0){
			msg.setStatus("0");
			msg.setResult(at);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据province_id查询city_name
		@RequestMapping(value ="/findCityByProvinceId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findCityByProvinceId(ApoCityVo vo) {
			Message msg = createMsg();
			Page<ApoCity> page = service.findCityByProvinceId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据city_id查询county_name
		@RequestMapping(value ="/findCountyByCityId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findCountyByCityId(ApoCountyVo vo) {
			Message msg = createMsg();
			Page<ApoCounty> page = service.findCountyByCityId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据county_id查询town_name
		@RequestMapping(value ="/findTownByCountyId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findTownByCountyId(ApoTownVo vo) {
			Message msg = createMsg();
			Page<ApoTown> page = service.findTownByCountyId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据town_id查询village_name
		@RequestMapping(value ="/findVillageByTownId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findVillageByTownId(ApoVillageVo vo) {
			Message msg = createMsg();
			Page<ApoVillage> page = service.findVillageByTownId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
	
	
	
		
		//sfz


		@RequestMapping("/selApoSfz")
		public String selApoSfz(HttpServletRequest req){
			
			return "operation/operation_aposfz_list";
		}
		@RequestMapping(value ="/queryApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryApoSfz(HttpServletRequest req,ApoSfzVo vo){
			Message msg =createMsg();
			
			Page<ApoSfz> page =service.queryApoSfz(vo);
			
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			
			return Json(msg);
		}
		@RequestMapping("/insApoSfz")
		public String insApoSfz(HttpServletRequest req){
			
			return "operation/operation_aposfz_add";
		}
		@RequestMapping(value ="/addApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addApoSfz(HttpServletRequest req,ApoSfz av){
			Message msg =createMsg();
			service.addApoSfz(av);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOSZF);
			return Json(msg);
			
		}
		@RequestMapping("/readApoSfz")
		public String readApoSfz(HttpServletRequest req,String code){
			ApoSfz av =service.getApoSfz(code);
			req.setAttribute("aposfzinfo", Json(av));
			return "operation/operation_aposfz_upd";
		}
		@RequestMapping(value ="/updApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updApoSfz(HttpServletRequest req,ApoSfz av){
			Message msg =createMsg();
			ApoSfz a =service.getApoSfz(av.getCode());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSZF);
			if(service.updApoSfz(av)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
			
		}
		@RequestMapping("/delApoSfz")
		public String delApoSfz(HttpServletRequest req ,String code){
			Message msg =createMsg();
			ApoSfz a =service.getApoSfz(code);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSZF);
			if(service.delApoSfz(code)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selApoSfz";
		}
		
		
		

		
		
		
//		站点
		@RequestMapping("/selApoSite")
		public String selApoSite(HttpServletRequest req){
			
			return "operation/operation_aposite_list";
		}
		@RequestMapping(value ="/queryApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryApoSite(HttpServletRequest req,ApoSiteVo vo){
			Message msg =createMsg();
			
			Page<ApoSite> page =service.queryApoSite(vo);
			
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			
			return Json(msg);
		}
		@RequestMapping("/insApoSite")
		public String insApoSite(HttpServletRequest req){
			
			return "operation/operation_aposite_add";
		}
		@RequestMapping(value ="/addApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addApoSite(HttpServletRequest req,ApoSite as){
			Message msg =createMsg();
			service.addApoSite(as);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDAPOSITE);
			return Json(msg);
			
		}
		@RequestMapping("/readApoSite")
		public String readApoSite(HttpServletRequest req,int id){
			ApoSite as =service.getApoSite(id);
			req.setAttribute("apositeinfo", Json(as));
			return "operation/operation_aposite_upd";
		}
		@RequestMapping(value ="/updApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updApoSite(HttpServletRequest req,ApoSite as){
			Message msg =createMsg();
			ApoSite a =service.getApoSite(as.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSITE);
			if(service.updApoSite(as)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delApoSite")
		public String delApoSite(HttpServletRequest req ,int id){
			Message msg =createMsg();
			ApoSite a =service.getApoSite(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSITE);
			if(service.delApoSite(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selApoSite";
		}
//		站点内容
		
		@RequestMapping("/selApoSiteContent")
		public String selApoSiteContent(HttpServletRequest req){
			
			return "operation/operation_apositecontent_list";
		}
		@RequestMapping(value ="/queryApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryApoSiteContent(HttpServletRequest req,ApoSiteContentVo vo){
			Message msg =createMsg();
			
			Page<ApoSiteContent> page =service.queryApoSiteContent(vo);
			
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			
			return Json(msg);
		}
		@RequestMapping("/insApoSiteContent")
		public String insApoSiteContent(HttpServletRequest req){
			
			return "operation/operation_apositecontent_add";
		}
		@RequestMapping(value ="/addApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addApoSiteContent(HttpServletRequest req,ApoSiteContent asc){
			Message msg =createMsg();
			service.addApoSiteContent(asc);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(asc),LogRemark.ADDAPOSITECONTENT);
			return Json(msg);
			
		}
		@RequestMapping("/readApoSiteContent")
		public String readApoSiteContent(HttpServletRequest req,int id){
			ApoSiteContent asc =service.getApoSiteContent(id);
			req.setAttribute("apositecontentinfo", Json(asc));
			return "operation/operation_apositecontent_upd";
		}
		@RequestMapping(value ="/updApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updApoSiteContent(HttpServletRequest req,ApoSiteContent asc){
			Message msg =createMsg();
			ApoSiteContent a =service.getApoSiteContent(asc.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSITECONTENT);
			if(service.updApoSiteContent(asc)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delApoSiteContent")
		public String delApoSiteContent(HttpServletRequest req ,int id){
			Message msg =createMsg();
			ApoSiteContent a =service.getApoSiteContent(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSITECONTENT);
			if(service.delApoSiteContent(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selApoSiteContent";
		}
//		地区站点筛选
		@RequestMapping("/selApoScreen")
		public String selApoScreen(HttpServletRequest req){
			
			return "operation/operation_aposcreen_list";
		}
		@RequestMapping(value ="/queryApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryApoScreen(HttpServletRequest req,ApoScreenVo vo){
			Message msg =createMsg();
			
			Page<ApoScreen> page =service.queryApoScreen(vo);
			
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			
			return Json(msg);
		}
		@RequestMapping("/insApoScreen")
		public String insApoScreen(HttpServletRequest req){
			
			return "operation/operation_aposcreen_add";
		}
		@RequestMapping(value ="/addApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addApoScreen(HttpServletRequest req,ApoScreen as){
			Message msg =createMsg();
			service.addApoScreen(as);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDAPOSCREEN);
			return Json(msg);
			
		}
		@RequestMapping("/readApoScreen")
		public String readApoScreen(HttpServletRequest req,int id){
			ApoScreen as =service.getApoScreen(id);
			req.setAttribute("aposcreeninfo", Json(as));
			return "operation/operation_aposcreen_upd";
		}
		@RequestMapping(value ="/updApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updApoScreen(HttpServletRequest req,ApoScreen as){
			Message msg =createMsg();
			ApoScreen a =service.getApoScreen(as.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSCREEN);
			if(service.updApoScreen(as)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delApoScreen")
		public String delApoScreen(HttpServletRequest req ,int id){
			Message msg =createMsg();
			ApoScreen a =service.getApoScreen(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSCREEN);
			if(service.delApoScreen(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selApoScreen";
		}
	
		

		
		@RequestMapping("/selChannel")
		public String selChannel(HttpServletRequest req){
			return "content/channel_list";
		}
		@RequestMapping(value ="/queryChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryChannel(HttpServletRequest req,ChannelVo vo){
			
			Message msg =createMsg();
			Page<Channel> page =service.queryChannel(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		@RequestMapping("/readChannel")
		public String readChannel(HttpServletRequest req,int id){
			
			Channel aru =service.getChannel(id);
			req.setAttribute("channelinfo", Json(aru));
			return "content/channel_upd";
			
		}
		@RequestMapping(value ="/updChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updChannel(HttpServletRequest req,Channel aru){
			Message msg =createMsg();
			
			Channel a =service.getChannel(aru.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDCHANNEL);
			
			if(service.updChannel(aru)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insChannel")
		public String insChannel(HttpServletRequest req){
			
			return "content/channel_add";
		}
		@RequestMapping(value ="/addChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addChannel(HttpServletRequest req,Channel aru){
			Message msg =createMsg();
			service.addChannel(aru);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDCHANNEL);
			return Json(msg);
		}
		@RequestMapping("/delChannel")
		public String delChannel(HttpServletRequest req,int id){
			Message msg =createMsg();
			Channel a =service.getChannel(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELCHANNEL);
			
			if(service.delChannel(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selChannel";
			
			
		}
		
		
		
		@RequestMapping("/selChannelField")
		public String selChannelField(HttpServletRequest req){
			return "content/channel_field_list";
		}
		@RequestMapping(value ="/queryChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryChannelField(HttpServletRequest req,ChannelFieldVo vo){
			
			Message msg =createMsg();
			Page<ChannelField> page =service.queryChannelField(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		@RequestMapping("/readChannelField")
		public String readChannelField(HttpServletRequest req,int id){
			
			ChannelField aru =service.getChannelField(id);
			req.setAttribute("channelfield", Json(aru));
			return "content/channel_field_upd";
			
		}
		@RequestMapping(value ="/updChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updChannelField(HttpServletRequest req,ChannelField aru){
			Message msg =createMsg();
			
			ChannelField a =service.getChannelField(aru.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDCHANNELFIELD);
			
			if(service.updChannelField(aru)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insChannelField")
		public String insChannelField(HttpServletRequest req){
			
			return "content/channel_field_add";
		}
		@RequestMapping(value ="/addChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addChannelField(HttpServletRequest req,ChannelField aru){
			Message msg =createMsg();
			service.addChannelField(aru);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDCHANNELFIELD);
			return Json(msg);
		}
		@RequestMapping("/delChannelField")
		public String delChannelField(HttpServletRequest req,int id){
			Message msg =createMsg();
			ChannelField a =service.getChannelField(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELCHANNELFIELD);
			
			if(service.delChannelField(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selChannelField";
			
			
		}
		
		
		
		@RequestMapping("/selChannelSpec")
		public String selChannelSpec(HttpServletRequest req){
			return "content/channel_spec_list";
		}
		@RequestMapping(value ="/queryChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryChannelSpec(HttpServletRequest req,ChannelSpecVo vo){
			
			Message msg =createMsg();
			Page<ChannelSpec> page =service.queryChannelSpec(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		@RequestMapping("/readChannelSpec")
		public String readChannelSpec(HttpServletRequest req,int id){
			
			ChannelSpec aru =service.getChannelSpec(id);
			req.setAttribute("channelspec", Json(aru));
			return "content/channel_spec_upd";
			
		}
		@RequestMapping(value ="/updChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updChannelSpec(HttpServletRequest req,ChannelSpec aru){
			Message msg =createMsg();
			
			ChannelSpec a =service.getChannelSpec(aru.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDCHANNELSPEC);
			
			if(service.updChannelSpec(aru)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insChannelSpec")
		public String insChannelSpec(HttpServletRequest req){
			
			return "content/channel_spec_add";
		}
		@RequestMapping(value ="/addChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addChannelSpec(HttpServletRequest req,ChannelSpec aru){
			Message msg =createMsg();
			service.addChannelSpec(aru);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDCHANNELSPEC);
			return Json(msg);
		}
		@RequestMapping("/delChannelSpec")
		public String delChannelSpec(HttpServletRequest req,int id){
			Message msg =createMsg();
			ChannelSpec a =service.getChannelSpec(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELCHANNELSPEC);
			
			if(service.delChannelSpec(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selChannelSpec";
			
			
		}
		
		
		
		@RequestMapping("/selChannelSite")
		public String selChannelSite(HttpServletRequest req){
			return "content/channel_site_list";
		}
		@RequestMapping(value ="/queryChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryChannelSite(HttpServletRequest req,ChannelSiteVo vo){
			
			Message msg =createMsg();
			Page<ChannelSite> page =service.queryChannelSite(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		@RequestMapping("/readChannelSite")
		public String readChannelSite(HttpServletRequest req,int id){
			
			ChannelSite aru =service.getChannelSite(id);
			req.setAttribute("channelsite", Json(aru));
			return "content/channel_site_upd";
			
		}
		@RequestMapping(value ="/updChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updChannelSite(HttpServletRequest req,ChannelSite aru){
			Message msg =createMsg();
			
			ChannelSite a =service.getChannelSite(aru.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDCHANNELSITE);
			
			if(service.updChannelSite(aru)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insChannelSite")
		public String insChannelSite(HttpServletRequest req){
			
			return "content/channel_site_add";
		}
		@RequestMapping(value ="/addChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addChannelSite(HttpServletRequest req,ChannelSite aru){
			Message msg =createMsg();
			service.addChannelSite(aru);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDCHANNELSITE);
			return Json(msg);
		}
		@RequestMapping("/delChannelSite")
		public String delChannelSite(HttpServletRequest req,int id){
			Message msg =createMsg();
			ChannelSite a =service.getChannelSite(id);
			saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELCHANNELSITE);
			
			if(service.delChannelSite(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selChannelSite";
			
			
		}
		
		
		/**
		 * 显示商品分类信息
		 */
		@RequestMapping("/selCategory")
		public String selCategory(HttpServletRequest req){
			
			return "operation/operation_attri_list";
		}
		
		/**
		 * ajax--查询商品一级分类
		 * @param req
		 * @return
		 */
		@RequestMapping(value ="/firstCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String firstCategory(HttpServletRequest req){
			
			Message msg =createMsg();
			
			List<Category> cateList =service.getFirstCategory();
			
			msg.setResult(cateList);
			
			return Json(msg);
		}
		
		/**
		 * ajax--查询商品二级分类
		 * @param req
		 * @return
		 */
		@RequestMapping(value ="/secondCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String secondCategory(HttpServletRequest req){
			
			Message msg =createMsg();
			
			String id =req.getParameter("id");
			
			List<Category> cateList =service.getSecondCategory(Integer.valueOf(id));
			
			
			msg.setResult(cateList);
			
			return Json(msg);
			
		}
		
		/**
		 * ajax--查询商品三级分类
		 * @param req
		 * @return
		 */
		@RequestMapping(value ="/thirdCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String thirdCategory(HttpServletRequest req){
			
			Message msg =createMsg();
			
			String id =req.getParameter("id");
			
			List<Category> cateList =service.getThirdCategory(Integer.valueOf(id));
			
			msg.setResult(cateList);
			
			return Json(msg);
		}
		

		
		
		
		
		
		
		//dt_mb_category(用户自定义分类模板表)
		
		@RequestMapping("/selMbCategory")
		public String selMbCategory(HttpServletRequest req){
			
			return "property/property_mbcategory_list";
		}
		@RequestMapping(value ="/queryMbCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbCategory(HttpServletRequest req,MbCategoryVo vo){
			Message msg =createMsg();
			
			Page<MbCategory> page =service.queryMbCategory(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbCategory")
		public String insMbCategory(HttpServletRequest req){
			
			return "property/property_mbcategory_add";
		}
		@RequestMapping(value ="/addMbCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addMbCategory(HttpServletRequest req,MbCategory og){
			Message msg =createMsg();
			service.addMbCategory(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDMBCATEGORY);
			return Json(msg);
			
		}
		@RequestMapping("/readMbCategory")
		public String readMbCategory(HttpServletRequest req,int id){
			MbCategory og =service.getMbCategory(id);
			req.setAttribute("mbcategory", Json(og));
			return "property/property_mbcategory_upd";
		}
		@RequestMapping(value ="/updMbCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updMbCategory(HttpServletRequest req,MbCategory og){
			Message msg =createMsg();
			MbCategory o =service.getMbCategory(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDMBCATEGORY);
			if(service.updMbCategory(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delMbCategory")
		public String delMbCategory(HttpServletRequest req,int id){
			
			MbCategory o =service.getMbCategory(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBCATEGORY);
			
			if(service.delMbCategory(id)){
				
			}
			return "redirect:/selMbCategory";
		}
		
		
		
		
		//dt_mb_article(用户发布内容模板表)
		
		@RequestMapping("/selMbArticle")
		public String selMbArticle(HttpServletRequest req){
			
			return "property/property_mbarticle_list";
		}
		@RequestMapping(value ="/queryMbArticle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbArticle(HttpServletRequest req,MbArticleVo vo){
			Message msg =createMsg();
			
			Page<MbArticle> page =service.queryMbArticle(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbArticle")
		public String insMbArticle(HttpServletRequest req){
			
			return "property/property_mbarticle_add";
		}
		
		@RequestMapping("/addMbArticle")
		public String addArticleCategory(HttpServletRequest req,MbArticle ac,@RequestParam MultipartFile file){
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file);
				String address = artiService.sendPostReq(fileName, base64Str);
				ac.setImg_url(address);
			}else{
				ac.setImg_url(req.getParameter("imgUrl"));
			}
			service.addMbArticle(ac);
			return "redirect:/insMbArticle";
		}
		
		@RequestMapping("/readMbArticle")
		public String readMbArticle(HttpServletRequest req,int id){
			MbArticle og =service.getMbArticle(id);
			req.setAttribute("mbarticle", Json(og));
			return "property/property_mbarticle_upd";
		}
		
		@RequestMapping("/updMbArticle")
		
		public String updMbArticle(HttpServletRequest req,MbArticle ac,@RequestParam MultipartFile file){
			String id = req.getParameter("id");
			Message msg = createMsg();
				if(!file.isEmpty()){
					String fileName = file.getOriginalFilename();
					String base64Str = Base64Utils.GetBase64Code(file);
					String address = artiService.sendPostReq(fileName, base64Str);
					ac.setImg_url(address);
				}else{
					ac.setImg_url(req.getParameter("imgUrl"));
				}
			if(service.updMbArticle(ac)){
				msg.setStatus("0");
				msg.setResult("修改成功");
			}else{
				msg.setStatus("1");
				msg.setResult("修改失败");
			}
			
			return "redirect:/readMbArticle?id="+id;
			
		}
		
		@RequestMapping("/delMbArticle")
		public String delMbArticle(HttpServletRequest req,int id){
			
			MbArticle o =service.getMbArticle(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBARTICLE);
			
			if(service.delMbArticle(id)){
				
			}
			return "redirect:/selMbArticle";
		}
		
		
		
		
		//dt_mb_images(内容所用图片模板表)
		
		@RequestMapping("/selMbImages")
		public String selMbImages(HttpServletRequest req){
			
			return "property/property_mbimages_list";
		}
		@RequestMapping(value ="/queryMbImages", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbImages(HttpServletRequest req,MbImagesVo vo){
			Message msg =createMsg();
			
			Page<MbImages> page =service.queryMbImages(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbImages")
		public String insMbImages(HttpServletRequest req){
			
			return "property/property_mbimages_add";
		}
		
		@RequestMapping("/addMbImages")
		public String addMbImages(HttpServletRequest req,MbImages ac,@RequestParam MultipartFile file){
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file);
				String address = artiService.sendPostReq(fileName, base64Str);
				ac.setUrl(address);
			}else{
				ac.setUrl(req.getParameter("url"));
			}
			service.addMbImages(ac);
			return "redirect:/insMbImages";
		}
		
		@RequestMapping("/readMbImages")
		public String readMbImages(HttpServletRequest req,int id){
			MbImages og =service.getMbImages(id);
			req.setAttribute("mbimages", Json(og));
			return "property/property_mbimages_upd";
		}
		
		@RequestMapping("/updMbImages")
		
		public String updMbImages(HttpServletRequest req,MbImages ac,@RequestParam MultipartFile file){
			String id = req.getParameter("id");
			Message msg = createMsg();
				if(!file.isEmpty()){
					String fileName = file.getOriginalFilename();
					String base64Str = Base64Utils.GetBase64Code(file);
					String address = artiService.sendPostReq(fileName, base64Str);
					ac.setUrl(address);
				}else{
					ac.setUrl(req.getParameter("imgUrl"));
				}
			if(service.updMbImages(ac)){
				msg.setStatus("0");
				msg.setResult("修改成功");
			}else{
				msg.setStatus("1");
				msg.setResult("修改失败");
			}
			
			return "redirect:/readMbImages?id="+id;
			
		}
		@RequestMapping("/delMbImages")
		public String delMbImages(HttpServletRequest req,int id){
			
			MbImages o =service.getMbImages(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBIMAGES);
			
			if(service.delMbImages(id)){
				
			}
			return "redirect:/selMbImages";
		}
		
		
		
		
		//dt_mb_yewu(分类所用业务模板表)
		
		@RequestMapping("/selMbYewu")
		public String selMbYewu(HttpServletRequest req){
			
			return "property/property_mbyewu_list";
		}
		@RequestMapping(value ="/queryMbYewu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbYewu(HttpServletRequest req,MbYewuVo vo){
			Message msg =createMsg();
			
			Page<MbYewu> page =service.queryMbYewu(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbYewu")
		public String insMbYewu(HttpServletRequest req){
			
			return "property/property_mbyewu_add";
		}
		@RequestMapping(value ="/addMbYewu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addMbYewu(HttpServletRequest req,MbYewu og){
			Message msg =createMsg();
			service.addMbYewu(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDMBYEWU);
			return Json(msg);
			
		}
		@RequestMapping("/readMbYewu")
		public String readMbYewu(HttpServletRequest req,int id){
			MbYewu og =service.getMbYewu(id);
			req.setAttribute("mbyewu", Json(og));
			return "property/property_mbyewu_upd";
		}
		@RequestMapping(value ="/updMbYewu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updMbYewu(HttpServletRequest req,MbYewu og){
			Message msg =createMsg();
			MbYewu o =service.getMbYewu(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDMBYEWU);
			if(service.updMbYewu(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delMbYewu")
		public String delMbYewu(HttpServletRequest req,int id){
			
			MbYewu o =service.getMbYewu(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBYEWU);
			
			if(service.delMbYewu(id)){
				
			}
			return "redirect:/selMbYewu";
		}
		
		
		
		
		//dt_mb_goods_matter(用户发布内容购买须知等模板表)
		
		@RequestMapping("/selMbGoodsMatter")
		public String selMbGoodsMatter(HttpServletRequest req){
			
			return "property/property_mbgoodsmatter_list";
		}
		@RequestMapping(value ="/queryMbGoodsMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbGoodsMatter(HttpServletRequest req,MbGoodsMatterVo vo){
			Message msg =createMsg();
			
			Page<MbGoodsMatter> page =service.queryMbGoodsMatter(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbGoodsMatter")
		public String insMbGoodsMatter(HttpServletRequest req){
			
			return "property/property_mbgoodsmatter_add";
		}
		@RequestMapping(value ="/addMbGoodsMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addMbGoodsMatter(HttpServletRequest req,MbGoodsMatter og){
			Message msg =createMsg();
			service.addMbGoodsMatter(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDMBGOODS);
			return Json(msg);
			
		}
		@RequestMapping("/readMbGoodsMatter")
		public String readMbGoodsMatter(HttpServletRequest req,int id){
			MbGoodsMatter og =service.getMbGoodsMatter(id);
			req.setAttribute("mbgoodsmatter", Json(og));
			return "property/property_mbgoodsmatter_upd";
		}
		@RequestMapping(value ="/updMbGoodsMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updMbGoodsMatter(HttpServletRequest req,MbGoodsMatter og){
			Message msg =createMsg();
			MbGoodsMatter o =service.getMbGoodsMatter(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDMBGOODS);
			if(service.updMbGoodsMatter(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delMbGoodsMatter")
		public String delMbGoodsMatter(HttpServletRequest req,int id){
			
			MbGoodsMatter o =service.getMbGoodsMatter(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBGOODS);
			
			if(service.delMbGoodsMatter(id)){
				
			}
			return "redirect:/selMbGoodsMatter";
		}
		
		
		
		
		//dt_mb_shop_matter(店铺总须知模板表)
		
		@RequestMapping("/selMbShopMatter")
		public String selMbShopMatter(HttpServletRequest req){
			
			return "property/property_mbshopmatter_list";
		}
		@RequestMapping(value ="/queryMbShopMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbShopMatter(HttpServletRequest req,MbShopMatterVo vo){
			Message msg =createMsg();
			
			Page<MbShopMatter> page =service.queryMbShopMatter(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbShopMatter")
		public String insMbShopMatter(HttpServletRequest req){
			
			return "property/property_mbshopmatter_add";
		}
		@RequestMapping(value ="/addMbShopMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addMbShopMatter(HttpServletRequest req,MbShopMatter og){
			Message msg =createMsg();
			service.addMbShopMatter(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDMBSHOP);
			return Json(msg);
			
		}
		@RequestMapping("/readMbShopMatter")
		public String readMbShopMatter(HttpServletRequest req,int id){
			MbShopMatter og =service.getMbShopMatter(id);
			req.setAttribute("mbshopmatter", Json(og));
			return "property/property_mbshopmatter_upd";
		}
		@RequestMapping(value ="/updMbShopMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updMbShopMatter(HttpServletRequest req,MbShopMatter og){
			Message msg =createMsg();
			MbShopMatter o =service.getMbShopMatter(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDMBSHOP);
			if(service.updMbShopMatter(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delMbShopMatter")
		public String delMbShopMatter(HttpServletRequest req,int id){
			
			MbShopMatter o =service.getMbShopMatter(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBSHOP);
			
			if(service.delMbShopMatter(id)){
				
			}
			return "redirect:/selMbShopMatter";
		}
		
		
		
		
		//dt_mb_channel_matter(店铺所用各业务频道须知等模板表)
		
		@RequestMapping("/selMbChannelMatter")
		public String selMbChannelMatter(HttpServletRequest req){
			
			return "property/property_mbchannelmatter_list";
		}
		@RequestMapping(value ="/queryMbChannelMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryMbChannelMatter(HttpServletRequest req,MbChannelMatterVo vo){
			Message msg =createMsg();
			
			Page<MbChannelMatter> page =service.queryMbChannelMatter(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insMbChannelMatter")
		public String insMbChannelMatter(HttpServletRequest req){
			
			return "property/property_mbchannelmatter_add";
		}
		@RequestMapping(value ="/addMbChannelMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addMbChannelMatter(HttpServletRequest req,MbChannelMatter og){
			Message msg =createMsg();
			service.addMbChannelMatter(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDMBCHANNEL);
			return Json(msg);
			
		}
		@RequestMapping("/readMbChannelMatter")
		public String readMbChannelMatter(HttpServletRequest req,int id){
			MbChannelMatter og =service.getMbChannelMatter(id);
			req.setAttribute("mbchannelmatter", Json(og));
			return "property/property_mbchannelmatter_upd";
		}
		@RequestMapping(value ="/updMbChannelMatter", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updMbChannelMatter(HttpServletRequest req,MbChannelMatter og){
			Message msg =createMsg();
			MbChannelMatter o =service.getMbChannelMatter(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDMBCHANNEL);
			if(service.updMbChannelMatter(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delMbChannelMatter")
		public String delMbChannelMatter(HttpServletRequest req,int id){
			
			MbChannelMatter o =service.getMbChannelMatter(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELMBCHANNEL);
			
			if(service.delMbChannelMatter(id)){
				
			}
			return "redirect:/selMbChannelMatter";
		}
		
		//dt_banktype（支持绑定银行卡/三方账户类型信息表）
		
		@RequestMapping("/selBanktype")
		public String selBanktype(HttpServletRequest req){
			
			return "property/property_banktype_list";
		}
		@RequestMapping(value ="/queryBanktype", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryBanktype(HttpServletRequest req,BanktypeVo vo){
			Message msg =createMsg();
			
			Page<Banktype> page =service.queryBanktype(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insBanktype")
		public String insBanktype(HttpServletRequest req){
			
			return "property/property_banktype_add";
		}
		
		@RequestMapping("/addBanktype")
		public String addBanktype(HttpServletRequest req,Banktype ac,@RequestParam MultipartFile file){
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file);
				String address = artiService.sendPostReq(fileName, base64Str);
				ac.setImg_url(address);
			}else{
				ac.setImg_url(req.getParameter("img_url"));
			}
			service.addBanktype(ac);
			saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDBANK);
			return "redirect:/insBanktype";
		}
		@RequestMapping("/readBanktype")
		public String readBanktype(HttpServletRequest req,int id){
			Banktype og =service.getBanktype(id);
			req.setAttribute("banktype", Json(og));
			return "property/property_banktype_upd";
		}

		@RequestMapping("/updBanktype")
		public String updBanktype(HttpServletRequest req,Banktype ac,@RequestParam MultipartFile file){
			String id = req.getParameter("id");
			Message msg = createMsg();
				if(!file.isEmpty()){
					String fileName = file.getOriginalFilename();
					String base64Str = Base64Utils.GetBase64Code(file);
					String address = artiService.sendPostReq(fileName, base64Str);
					ac.setImg_url(address);
				}else{
					ac.setImg_url(req.getParameter("imgUrl"));
				}
			if(service.updBanktype(ac)){
				msg.setStatus("0");
				msg.setResult("修改成功");
			}else{
				msg.setStatus("1");
				msg.setResult("修改失败");
			}
			Banktype o =service.getBanktype(ac.getId());
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.UPDBANK);
			return "redirect:/readBanktype?id="+id;
			
		}
		@RequestMapping("/delBanktype")
		public String delBanktype(HttpServletRequest req,int id){
			
			Banktype o =service.getBanktype(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBANK);
			
			if(service.delBanktype(id)){
				
			}
			return "redirect:/selBanktype";
		}
		
		
		
		//dt_user_oauth_app（用户第三方登录类型信息表）
		
		@RequestMapping("/selUserOauthApp")
		public String selUserOauthApp(HttpServletRequest req){
			
			return "property/property_oauthapp_list";
		}
		@RequestMapping(value ="/queryUserOauthApp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryUserOauthApp(HttpServletRequest req,UserOauthAppVo vo){
			Message msg =createMsg();
			
			Page<UserOauthApp> page =service.queryUserOauthApp(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insUserOauthApp")
		public String insUserOauthApp(HttpServletRequest req){
			
			return "property/property_oauthapp_add";
		}
		@RequestMapping("/addUserOauthApp")
		public String addUserOauthApp(HttpServletRequest req,UserOauthApp ac,@RequestParam MultipartFile file){
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file);
				String address = artiService.sendPostReq(fileName, base64Str);
				ac.setImg_url(address);
			}else{
				ac.setImg_url(req.getParameter("img_url"));
			}
			service.addUserOauthApp(ac);
			saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDOAUTHAPP);
			return "redirect:/insUserOauthApp";
		}
		
		@RequestMapping("/readUserOauthApp")
		public String readUserOauthApp(HttpServletRequest req,int id){
			UserOauthApp og =service.getUserOauthApp(id);
			req.setAttribute("oauthapp", Json(og));
			return "property/property_oauthapp_upd";
		}
		
		@RequestMapping("/updUserOauthApp")
		public String updUserOauthApp(HttpServletRequest req,UserOauthApp ac,@RequestParam MultipartFile file){
			String id = req.getParameter("id");
			Message msg = createMsg();
				if(!file.isEmpty()){
					String fileName = file.getOriginalFilename();
					String base64Str = Base64Utils.GetBase64Code(file);
					String address = artiService.sendPostReq(fileName, base64Str);
					ac.setImg_url(address);
				}else{
					ac.setImg_url(req.getParameter("imgUrl"));
				}
			if(service.updUserOauthApp(ac)){
				msg.setStatus("0");
				msg.setResult("修改成功");
			}else{
				msg.setStatus("1");
				msg.setResult("修改失败");
			}
			UserOauthApp o =service.getUserOauthApp(ac.getId());
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.UPDOAUTHAPP);
			return "redirect:/readUserOauthApp?id="+id;
		}
		
		@RequestMapping("/delUserOauthApp")
		public String delUserOauthApp(HttpServletRequest req,int id){
			
			UserOauthApp o =service.getUserOauthApp(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELOAUTHAPP);
			
			if(service.delUserOauthApp(id)){
				
			}
			return "redirect:/selUserOauthApp";
		}
		
		
		
		//dt_groups(系统分组信息表)
		
		@RequestMapping("/selGroups")
		public String selGroups(HttpServletRequest req){
			
			return "property/property_groups_list";
		}
		@RequestMapping(value ="/queryGroups", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryGroups(HttpServletRequest req,GroupsVo vo){
			Message msg =createMsg();
			
			Page<Groups> page =service.queryGroups(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insGroups")
		public String insGroups(HttpServletRequest req){
			
			return "property/property_groups_add";
		}
		@RequestMapping(value ="/addGroups", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addGroups(HttpServletRequest req,Groups og){
			Message msg =createMsg();
			service.addGroups(og);
			msg.setStatus("0");
			saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDOAUTHAPP);
			return Json(msg);
			
		}
		@RequestMapping("/readGroups")
		public String readGroups(HttpServletRequest req,int id){
			Groups og =service.getGroups(id);
			req.setAttribute("groups", Json(og));
			return "property/property_groups_upd";
		}
		@RequestMapping(value ="/updGroups", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updGroups(HttpServletRequest req,Groups og){
			Message msg =createMsg();
			Groups o =service.getGroups(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDOAUTHAPP);
			if(service.updGroups(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delGroups")
		public String delGroups(HttpServletRequest req,int id){
			
			Groups o =service.getGroups(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELOAUTHAPP);
			
			if(service.delGroups(id)){
				
			}
			return "redirect:/selGroups";
		}
		
		//dt_groups(信誉规则表)
		@RequestMapping("/selRules")
		public String selRules(HttpServletRequest req){
			return "property/property_rule_list";
		}
		@RequestMapping(value ="/queryRules", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryRules(HttpServletRequest req,ScoreruleVo vo){
			Message msg =createMsg();
			
			Page<Scorerule> page =service.queryRules(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insRules")
		public String insRules(HttpServletRequest req){
			
			return "property/property_rule_add";
		}
		@RequestMapping(value ="/addRules", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addRules(HttpServletRequest req,Scorerule og){
			Message msg =createMsg();
			try{
				service.addRules(og);
				msg.setStatus("0");
				saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDOAUTHAPP);
			}catch(Exception e){
				msg.setStatus("1");
				e.printStackTrace();
			}
			return Json(msg);
		}
		@RequestMapping("/readRules")
		public String readRules(HttpServletRequest req,int id){
			Scorerule og =service.getRules(id);
			req.setAttribute("Rules", Json(og));
			return "property/property_rule_upd";
		}
		@RequestMapping(value ="/updRules", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updRules(HttpServletRequest req,Scorerule og){
			Message msg =createMsg();
			Scorerule o =service.getRules(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDOAUTHAPP);
			if(service.updRules(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delRules")
		public String delRules(HttpServletRequest req,int id){
			
			Scorerule o =service.getRules(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELOAUTHAPP);
			
			if(service.delRules(id)){
				
			}
			return "redirect:/selRules";
		}
}
