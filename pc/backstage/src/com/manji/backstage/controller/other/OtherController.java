package com.manji.backstage.controller.other;

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
import com.manji.backstage.model.other.OrderLog;
import com.manji.backstage.model.other.OrderQueue;
import com.manji.backstage.model.other.RoleOperatorNavigation;
import com.manji.backstage.model.other.RoleOperatorType;
import com.manji.backstage.model.other.RoleOperatorTypeValue;
import com.manji.backstage.model.other.UserBalanceDetail;
import com.manji.backstage.model.other.UserBalanceLog;
import com.manji.backstage.model.other.UserCode;
import com.manji.backstage.model.other.UserFromValue;
import com.manji.backstage.model.other.UserGroupPrice;
import com.manji.backstage.model.other.UserNoticeRead;
import com.manji.backstage.model.other.UserOauth;
import com.manji.backstage.model.other.UserOftenUse;
import com.manji.backstage.model.other.UserRole;
import com.manji.backstage.model.other.UserRoleBusiness;
import com.manji.backstage.model.other.UserRoleBuyerinfo;
import com.manji.backstage.model.other.UserWechat;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.other.OtherService;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.other.OrderLogVo;
import com.manji.backstage.vo.other.OrderQueueVo;
import com.manji.backstage.vo.other.RoleOperatorNavigationVo;
import com.manji.backstage.vo.other.RoleOperatorTypeValueVo;
import com.manji.backstage.vo.other.RoleOperatorTypeVo;
import com.manji.backstage.vo.other.UserBalanceDetailVo;
import com.manji.backstage.vo.other.UserBalanceLogVo;
import com.manji.backstage.vo.other.UserCodeVo;
import com.manji.backstage.vo.other.UserFromValueVo;
import com.manji.backstage.vo.other.UserGroupPriceVo;
import com.manji.backstage.vo.other.UserNoticeReadVo;
import com.manji.backstage.vo.other.UserOauthVo;
import com.manji.backstage.vo.other.UserOftenUseVo;
import com.manji.backstage.vo.other.UserRoleBusinessVo;
import com.manji.backstage.vo.other.UserRoleBuyerinfoVo;
import com.manji.backstage.vo.other.UserRoleVo;
import com.manji.backstage.vo.other.UserWechatVo;

@Controller
public class OtherController  extends BaseController{

	@Autowired
	private OtherService service;
	private LoggersService logService;
	
	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("order");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	//2.3.6.dt_user_code(用户随机码信息表)

	@RequestMapping("/selUserCode")
	public String selUserCode(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserCode(HttpServletRequest req,UserCode og){
		Message msg =createMsg();
		service.addUserCode(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserCode")
	public String readUserCode(HttpServletRequest req,int id){
		UserCode og =service.getUserCode(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserCode(HttpServletRequest req,UserCode og){
		Message msg =createMsg();
		UserCode o =service.getUserCode(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
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
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserCode(id)){
			
		}
		return "redirect:/selUserCode";
	}
	
	
	//2.3.3.dt_user_from_value（用户标识申请信息表）
	
	@RequestMapping("/selUserFromValue")
	public String selUserFromValue(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserFromValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserFromValue(HttpServletRequest req,UserFromValue og){
		Message msg =createMsg();
		service.addUserFromValue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserFromValue")
	public String readUserFromValue(HttpServletRequest req,int id){
		UserFromValue og =service.getUserFromValue(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserFromValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserFromValue(HttpServletRequest req,UserFromValue og){
		Message msg =createMsg();
		UserFromValue o =service.getUserFromValue(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserFromValue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserFromValue")
	public String delUserFromValue(HttpServletRequest req,int id){
		
		UserFromValue o =service.getUserFromValue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserFromValue(id)){
			
		}
		return "redirect:/selUserFromValue";
	}
	
	
	
	
	//2.3.5.dt_user_group_price（用户分组商品价格信息表）
	
	@RequestMapping("/selUserGroupPrice")
	public String selUserGroupPrice(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserGroupPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserGroupPrice(HttpServletRequest req,UserGroupPrice og){
		Message msg =createMsg();
		service.addUserGroupPrice(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserGroupPrice")
	public String readUserGroupPrice(HttpServletRequest req,int id){
		UserGroupPrice og =service.getUserGroupPrice(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserGroupPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserGroupPrice(HttpServletRequest req,UserGroupPrice og){
		Message msg =createMsg();
		UserGroupPrice o =service.getUserGroupPrice(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
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
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserGroupPrice(id)){
			
		}
		return "redirect:/selUserGroupPrice";
	}
	
	
	
	
	//2.3.31.dt_user_often_use (用户经常使用信息表)
	
	@RequestMapping("/selUserOftenUse")
	public String selUserOftenUse(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserOftenUse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserOftenUse(HttpServletRequest req,UserOftenUse og){
		Message msg =createMsg();
		service.addUserOftenUse(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserOftenUse")
	public String readUserOftenUse(HttpServletRequest req,int id){
		UserOftenUse og =service.getUserOftenUse(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserOftenUse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserOftenUse(HttpServletRequest req,UserOftenUse og){
		Message msg =createMsg();
		UserOftenUse o =service.getUserOftenUse(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
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
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserOftenUse(id)){
			
		}
		return "redirect:/selUserOftenUse";
	}
	
	
	
	
	//2.8.7.dt_user_role_buyerinfo（用户买家信息表）
	
	@RequestMapping("/selUserRoleBuyerinfo")
	public String selUserRoleBuyerinfo(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserRoleBuyerinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleBuyerinfo(HttpServletRequest req,UserRoleBuyerinfoVo vo){
		Message msg =createMsg();
		
		Page<UserRoleBuyerinfo> page =service.queryUserRoleBuyerinfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleBuyerinfo")
	public String insUserRoleBuyerinfo(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserRoleBuyerinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleBuyerinfo(HttpServletRequest req,UserRoleBuyerinfo og){
		Message msg =createMsg();
		service.addUserRoleBuyerinfo(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleBuyerinfo")
	public String readUserRoleBuyerinfo(HttpServletRequest req,int id){
		UserRoleBuyerinfo og =service.getUserRoleBuyerinfo(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserRoleBuyerinfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleBuyerinfo(HttpServletRequest req,UserRoleBuyerinfo og){
		Message msg =createMsg();
		UserRoleBuyerinfo o =service.getUserRoleBuyerinfo(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserRoleBuyerinfo(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleBuyerinfo")
	public String delUserRoleBuyerinfo(HttpServletRequest req,int id){
		
		UserRoleBuyerinfo o =service.getUserRoleBuyerinfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserRoleBuyerinfo(id)){
			
		}
		return "redirect:/selUserRoleBuyerinfo";
	}
	
	
	
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	@RequestMapping("/selUserWechat")
	public String selUserWechat(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserWechat", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserWechat(HttpServletRequest req,UserWechat og){
		Message msg =createMsg();
		service.addUserWechat(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserWechat")
	public String readUserWechat(HttpServletRequest req,int id){
		UserWechat og =service.getUserWechat(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserWechat", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserWechat(HttpServletRequest req,UserWechat og){
		Message msg =createMsg();
		UserWechat o =service.getUserWechat(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserWechat(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserWechat")
	public String delUserWechat(HttpServletRequest req,int id){
		
		UserWechat o =service.getUserWechat(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserWechat(id)){
			
		}
		return "redirect:/selUserWechat";
	}
	
	
	
	
	//4.17.1.dt_order_ queue (账户结算主表)
	
	@RequestMapping("/selOrderQueue")
	public String selOrderQueue(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryOrderQueue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderQueue(HttpServletRequest req,OrderQueueVo vo){
		Message msg =createMsg();
		
		Page<OrderQueue> page =service.queryOrderQueue(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderQueue")
	public String insOrderQueue(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addOrderQueue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderQueue(HttpServletRequest req,OrderQueue og){
		Message msg =createMsg();
		service.addOrderQueue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderQueue")
	public String readOrderQueue(HttpServletRequest req,int id){
		OrderQueue og =service.getOrderQueue(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updOrderQueue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderQueue(HttpServletRequest req,OrderQueue og){
		Message msg =createMsg();
		OrderQueue o =service.getOrderQueue(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updOrderQueue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderQueue")
	public String delOrderQueue(HttpServletRequest req,int id){
		
		OrderQueue o =service.getOrderQueue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delOrderQueue(id)){
			
		}
		return "redirect:/selOrderQueue";
	}
	
	
	
	
	//4.16.1.dt_order_log(账户结算主表)
	
	@RequestMapping("/selOrderLog")
	public String selOrderLog(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryOrderLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderLog(HttpServletRequest req,OrderLogVo vo){
		Message msg =createMsg();
		
		Page<OrderLog> page =service.queryOrderLog(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderLog")
	public String insOrderLog(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addOrderLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderLog(HttpServletRequest req,OrderLog og){
		Message msg =createMsg();
		service.addOrderLog(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderLog")
	public String readOrderLog(HttpServletRequest req,int id){
		OrderLog og =service.getOrderLog(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updOrderLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderLog(HttpServletRequest req,OrderLog og){
		Message msg =createMsg();
		OrderLog o =service.getOrderLog(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updOrderLog(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderLog")
	public String delOrderLog(HttpServletRequest req,int id){
		
		OrderLog o =service.getOrderLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delOrderLog(id)){
			
		}
		return "redirect:/selOrderLog";
	}
	
	
	
	
	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
	@RequestMapping("/selUserOauth")
	public String selUserOauth(HttpServletRequest req){
		
		return "order/order_goods_list";
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
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserOauth", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserOauth(HttpServletRequest req,UserOauth og){
		Message msg =createMsg();
		service.addUserOauth(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserOauth")
	public String readUserOauth(HttpServletRequest req,int id){
		UserOauth og =service.getUserOauth(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserOauth", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserOauth(HttpServletRequest req,UserOauth og){
		Message msg =createMsg();
		UserOauth o =service.getUserOauth(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
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
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserOauth(id)){
			
		}
		return "redirect:/selUserOauth";
	}
	
	
	
	
	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	@RequestMapping("/selUserNoticeRead")
	public String selUserNoticeRead(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserNoticeRead(HttpServletRequest req,UserNoticeReadVo vo){
		Message msg =createMsg();
		
		Page<UserNoticeRead> page =service.queryUserNoticeRead(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserNoticeRead")
	public String insUserNoticeRead(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserNoticeRead(HttpServletRequest req,UserNoticeRead og){
		Message msg =createMsg();
		service.addUserNoticeRead(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserNoticeRead")
	public String readUserNoticeRead(HttpServletRequest req,int id){
		UserNoticeRead og =service.getUserNoticeRead(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserNoticeRead(HttpServletRequest req,UserNoticeRead og){
		Message msg =createMsg();
		UserNoticeRead o =service.getUserNoticeRead(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserNoticeRead(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserNoticeRead")
	public String delUserNoticeRead(HttpServletRequest req,int id){
		
		UserNoticeRead o =service.getUserNoticeRead(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserNoticeRead(id)){
			
		}
		return "redirect:/selUserNoticeRead";
	}
	
	
	
	
	//4.16.2.dt_user_balance_detail(账户结算子表)
	
	@RequestMapping("/selUserBalanceDetail")
	public String selUserBalanceDetail(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserBalanceDetail(HttpServletRequest req,UserBalanceDetailVo vo){
		Message msg =createMsg();
		
		Page<UserBalanceDetail> page =service.queryUserBalanceDetail(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserBalanceDetail")
	public String insUserBalanceDetail(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserBalanceDetail(HttpServletRequest req,UserBalanceDetail og){
		Message msg =createMsg();
		service.addUserBalanceDetail(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserBalanceDetail")
	public String readUserBalanceDetail(HttpServletRequest req,int id){
		UserBalanceDetail og =service.getUserBalanceDetail(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserBalanceDetail(HttpServletRequest req,UserBalanceDetail og){
		Message msg =createMsg();
		UserBalanceDetail o =service.getUserBalanceDetail(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserBalanceDetail(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserBalanceDetail")
	public String delUserBalanceDetail(HttpServletRequest req,int id){
		
		UserBalanceDetail o =service.getUserBalanceDetail(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserBalanceDetail(id)){
			
		}
		return "redirect:/selUserBalanceDetail";
	}
	
	
	
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	
	@RequestMapping("/selUserBalanceLog")
	public String selUserBalanceLog(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserBalanceLog(HttpServletRequest req,UserBalanceLogVo vo){
		Message msg =createMsg();
		
		Page<UserBalanceLog> page =service.queryUserBalanceLog(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserBalanceLog")
	public String insUserBalanceLog(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserBalanceLog(HttpServletRequest req,UserBalanceLog og){
		Message msg =createMsg();
		service.addUserBalanceLog(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserBalanceLog")
	public String readUserBalanceLog(HttpServletRequest req,int id){
		UserBalanceLog og =service.getUserBalanceLog(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserBalanceLog(HttpServletRequest req,UserBalanceLog og){
		Message msg =createMsg();
		UserBalanceLog o =service.getUserBalanceLog(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserBalanceLog(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserBalanceLog")
	public String delUserBalanceLog(HttpServletRequest req,int id){
		
		UserBalanceLog o =service.getUserBalanceLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserBalanceLog(id)){
			
		}
		return "redirect:/selUserBalanceLog";
	}
	
	
	
	
	//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
	
	@RequestMapping("/selRoleOperatorType")
	public String selRoleOperatorType(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorType(HttpServletRequest req,RoleOperatorTypeVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorType> page =service.queryRoleOperatorType(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorType")
	public String insRoleOperatorType(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorType(HttpServletRequest req,RoleOperatorType og){
		Message msg =createMsg();
		service.addRoleOperatorType(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorType")
	public String readRoleOperatorType(HttpServletRequest req,int id){
		RoleOperatorType og =service.getRoleOperatorType(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorType(HttpServletRequest req,RoleOperatorType og){
		Message msg =createMsg();
		RoleOperatorType o =service.getRoleOperatorType(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updRoleOperatorType(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorType")
	public String delRoleOperatorType(HttpServletRequest req,int id){
		
		RoleOperatorType o =service.getRoleOperatorType(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delRoleOperatorType(id)){
			
		}
		return "redirect:/selRoleOperatorType";
	}
	
	
	
	
	//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
	
	@RequestMapping("/selRoleOperatorTypeValue")
	public String selRoleOperatorTypeValue(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValueVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorTypeValue> page =service.queryRoleOperatorTypeValue(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorTypeValue")
	public String insRoleOperatorTypeValue(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValue og){
		Message msg =createMsg();
		service.addRoleOperatorTypeValue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorTypeValue")
	public String readRoleOperatorTypeValue(HttpServletRequest req,int id){
		RoleOperatorTypeValue og =service.getRoleOperatorTypeValue(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValue og){
		Message msg =createMsg();
		RoleOperatorTypeValue o =service.getRoleOperatorTypeValue(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updRoleOperatorTypeValue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorTypeValue")
	public String delRoleOperatorTypeValue(HttpServletRequest req,int id){
		
		RoleOperatorTypeValue o =service.getRoleOperatorTypeValue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delRoleOperatorTypeValue(id)){
			
		}
		return "redirect:/selRoleOperatorTypeValue";
	}
	
	
	
	
	//2.8.4.dt_user_role（用户角色信息表）
	
	@RequestMapping("/selUserRole")
	public String selUserRole(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRole(HttpServletRequest req,UserRoleVo vo){
		Message msg =createMsg();
		
		Page<UserRole> page =service.queryUserRole(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRole")
	public String insUserRole(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRole(HttpServletRequest req,UserRole og){
		Message msg =createMsg();
		service.addUserRole(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRole")
	public String readUserRole(HttpServletRequest req,int id){
		UserRole og =service.getUserRole(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRole(HttpServletRequest req,UserRole og){
		Message msg =createMsg();
		UserRole o =service.getUserRole(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserRole(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRole")
	public String delUserRole(HttpServletRequest req,int id){
		
		UserRole o =service.getUserRole(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserRole(id)){
			
		}
		return "redirect:/selUserRole";
	}
	
	
	
	
	//2.8.3.dt_role_operator_navigation（角色后台管理总菜单信息表）
	
	@RequestMapping("/selRoleOperatorNavigation")
	public String selRoleOperatorNavigation(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigationVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorNavigation> page =service.queryRoleOperatorNavigation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorNavigation")
	public String insRoleOperatorNavigation(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigation og){
		Message msg =createMsg();
		service.addRoleOperatorNavigation(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorNavigation")
	public String readRoleOperatorNavigation(HttpServletRequest req,int id){
		RoleOperatorNavigation og =service.getRoleOperatorNavigation(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigation og){
		Message msg =createMsg();
		RoleOperatorNavigation o =service.getRoleOperatorNavigation(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updRoleOperatorNavigation(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorNavigation")
	public String delRoleOperatorNavigation(HttpServletRequest req,int id){
		
		RoleOperatorNavigation o =service.getRoleOperatorNavigation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delRoleOperatorNavigation(id)){
			
		}
		return "redirect:/selRoleOperatorNavigation";
	}
	
	
	
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	
	@RequestMapping("/selUserRoleBusiness")
	public String selUserRoleBusiness(HttpServletRequest req){
		
		return "order/order_goods_list";
	}
	@RequestMapping(value ="/queryUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleBusiness(HttpServletRequest req,UserRoleBusinessVo vo){
		Message msg =createMsg();
		
		Page<UserRoleBusiness> page =service.queryUserRoleBusiness(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleBusiness")
	public String insUserRoleBusiness(HttpServletRequest req){
		
		return "order/order_goods_add";
	}
	@RequestMapping(value ="/addUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleBusiness(HttpServletRequest req,UserRoleBusiness og){
		Message msg =createMsg();
		service.addUserRoleBusiness(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDORDERGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleBusiness")
	public String readUserRoleBusiness(HttpServletRequest req,int id){
		UserRoleBusiness og =service.getUserRoleBusiness(id);
		req.setAttribute("ordergoodsinfo", Json(og));
		return "order/order_goods_upd";
	}
	@RequestMapping(value ="/updUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleBusiness(HttpServletRequest req,UserRoleBusiness og){
		Message msg =createMsg();
		UserRoleBusiness o =service.getUserRoleBusiness(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERGOODS);
		if(service.updUserRoleBusiness(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleBusiness")
	public String delUserRoleBusiness(HttpServletRequest req,int id){
		
		UserRoleBusiness o =service.getUserRoleBusiness(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERGOODS);
		
		if(service.delUserRoleBusiness(id)){
			
		}
		return "redirect:/selUserRoleBusiness";
	}
	
	
	
	
	
	
}
