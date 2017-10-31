package com.manji.backstage.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.dto.shop.ShopGroupCountsDto;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.shop.Group;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.shop.ShopGroupService;
import com.manji.backstage.vo.base.Message;
@Controller
public class ShopGroupController extends BaseController{

	@Autowired
	private ShopGroupService service;
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

	
	//1.商家组别管理
	
	
		/**
		 * 1.1商家组别列表
		 * @param req
		 * @return
		 */
	
		@RequestMapping(value ="/selShopPie")
			
			public String countShopGroup(HttpServletRequest req){
				List<Group> list= service.countShopGroup();
				
				req.setAttribute("shoppielist",Array(list));
				return "shop/shop_pie_list"; 
			}

		/**
		 * 查询id,title
		 * @param req
		 * @return
		 */
		@RequestMapping(value ="/findShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String findUserGroup(HttpServletRequest req){
			Message msg = createMsg();
			List<Group> list = 	service.findShopGroup();
			msg.setStatus("0");
			msg.setResult(list);
			return Json(msg);
		}
		
	
	
		@RequestMapping("/selShopGroup")
		public String selShopGroups(HttpServletRequest req){
			
			
			List<Group> shopGroups =service.selShopGroups();
			
			
			req.setAttribute("shopgroups", Array(shopGroups));
			
			return "shop/shop_group_list";
		}
		
	
		/**
		 * 查询商家
		 * @param req
		 * @return
		 */
		@RequestMapping(value ="/queryShopById", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryShopById(HttpServletRequest req){
			
			Message msg =createMsg();
			
			String id =req.getParameter("id");
			
			System.out.println(id);
			
			List<Group> list =service.queryShopById(id);
			
			msg.setStatus("0");
			msg.setResult(list);
			
			return Json(msg);
			
		}
		
	



		/**
		 * 1.1.1增加商家组别
		 * @param req
		 * @param group
		 * @return
		 */
		@RequestMapping("/insShopGroup")
		public String insShopGroup(HttpServletRequest req){
			
			
			return "shop/shop_group_add";
		}

		
		/**
		 * 1.1.2添加商家组别记录
		 * @param group
		 * @return
		 */
		@RequestMapping(value = "/addShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addShopGroup(HttpServletRequest req,Group group){
			
			Message msg =createMsg();
			
			service.addShopGroup(group);
			
			msg.setStatus("0");
			msg.setResult("添加成功");
			saveLog(createLog(req),LogRemark.ADD,Json(group),LogRemark.ADDSHOPGROUPS);
			return Json(msg);
		}
		
		
		/**
		 * 1.1.2修改商家组别
		 * @param req
		 * @param group
		 * @return
		 */
		@RequestMapping(value = "/updShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updShopGroup(HttpServletRequest req,Group group){
			Group g =service.getShopGroup(group.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(g),LogRemark.UPDSHOPGROUPS);
			Message msg =createMsg();
			
			if(service.updShopGroup(group)){
				msg.setStatus("0");
				msg.setResult("成功");
			}else{
				msg.setStatus("1");
				msg.setResult("失败");
			}
		
			return Json(msg);
		}
		
		@RequestMapping("/chgShopGroup")
		public String getShopGroup(HttpServletRequest req,int id){
			Group group =service.getShopGroup(id);
			req.setAttribute("shopinfo", Json(group));
			return "shop/shop_group_upd";
		}
		
		//删除商家组别
		
		@RequestMapping(value = "/delShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		public String delShopGroup(HttpServletRequest req,int id){
			
			Message msg =createMsg();
			Group g =service.getShopGroup(id);
			saveLog(createLog(req),LogRemark.DEL,Json(g),LogRemark.DELSHOPGROUPS);
			if(service.delShopGroup(id)){
				msg.setStatus("0");
				msg.setResult("操作成功");
			}else{
				msg.setStatus("1");
				msg.setResult("操作失败");
			}
			
			return "redirect:/selShopGroup";
		}
		
		
		//1.2商家组别统计
		@RequestMapping("/shopGroupCounts")
		public String shopGroupCounts(HttpServletRequest req){
			
			List<ShopGroupCountsDto>  countsList =service.shopGroupCounts();
			List<ShopGroupCountsDto>  lastCountsList =service.lastShopGroupCounts();
			
			req.setAttribute("countslist", countsList);
			req.setAttribute("lastcountslist", lastCountsList);
			
			return "shop/shop_group_counts";
		}
		@RequestMapping(value = "/getShopGroupList", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String getShopGroupList(HttpServletRequest req){
			Message msg =createMsg();
			List<Group> list =service.getShopGroupList();
			if(list.size()>0){
				msg.setStatus("0");
				msg.setResult(list);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
	
	
}
