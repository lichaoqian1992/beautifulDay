package com.manji.datahost.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.FlashActiveWcfStub;
import org.tempuri.FlashActiveWcfStub.ActiveArticleListDetailsDto;
import org.tempuri.FlashActiveWcfStub.AddPlatformActive;
import org.tempuri.FlashActiveWcfStub.AddPlatformActiveResponse;
import org.tempuri.FlashActiveWcfStub.ArrayOfActiveArticleListDetailsDto;
import org.tempuri.FlashActiveWcfStub.ArrayOfArticlePriceDto;
import org.tempuri.FlashActiveWcfStub.ArrayOfPurchaseCommodityDto;
import org.tempuri.FlashActiveWcfStub.ArticlePriceDto;
import org.tempuri.FlashActiveWcfStub.DeleteFlashPurchase;
import org.tempuri.FlashActiveWcfStub.DeleteFlashPurchaseResponse;
import org.tempuri.FlashActiveWcfStub.FlashPurchaseDto;
import org.tempuri.FlashActiveWcfStub.GetActiveArticleList;
import org.tempuri.FlashActiveWcfStub.GetActiveArticleListResponse;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchase;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchaseList;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchaseListResponse;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchaseResponse;
import org.tempuri.FlashActiveWcfStub.PlatArticlePriceDto;
import org.tempuri.FlashActiveWcfStub.PurchaseCommodityDto;
import org.tempuri.FlashActiveWcfStub.UpdateFlashPurchase;
import org.tempuri.FlashActiveWcfStub.UpdateFlashPurchaseResponse;
import org.tempuri.FlashActiveWcfStub.UpdateFlashPurchaseState;
import org.tempuri.FlashActiveWcfStub.UpdateFlashPurchaseStateResponse;
import org.tempuri.PlatformActivitiesWcfStub;
import org.tempuri.PlatformActivitiesWcfStub.ActivitiesParameter;
import org.tempuri.PlatformActivitiesWcfStub.AddOrUpdateAppSpecialActivity;
import org.tempuri.PlatformActivitiesWcfStub.AddOrUpdateAppSpecialActivityResponse;
import org.tempuri.PlatformActivitiesWcfStub.Addpeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Addpeople_activitiesResponse;
import org.tempuri.PlatformActivitiesWcfStub.AppSpecialActivityBriefDto;
import org.tempuri.PlatformActivitiesWcfStub.AppSpecialGoodsDto;
import org.tempuri.PlatformActivitiesWcfStub.ArrayOfAppSpecialGoodsDto;
import org.tempuri.PlatformActivitiesWcfStub.ArrayOfconditionModel;
import org.tempuri.PlatformActivitiesWcfStub.ConditionModel;
import org.tempuri.PlatformActivitiesWcfStub.DeleteAppSpecialActivity;
import org.tempuri.PlatformActivitiesWcfStub.DeleteAppSpecialActivityResponse;
import org.tempuri.PlatformActivitiesWcfStub.Deletenewpeople_package;
import org.tempuri.PlatformActivitiesWcfStub.Deletenewpeople_packageResponse;
import org.tempuri.PlatformActivitiesWcfStub.GetAppSpecialActivitiesByPage;
import org.tempuri.PlatformActivitiesWcfStub.GetAppSpecialActivitiesByPageResponse;
import org.tempuri.PlatformActivitiesWcfStub.GetAppSpecialActivityById;
import org.tempuri.PlatformActivitiesWcfStub.GetAppSpecialActivityByIdResponse;
import org.tempuri.PlatformActivitiesWcfStub.GetGetAppSpecialGoodsListByPage;
import org.tempuri.PlatformActivitiesWcfStub.GetGetAppSpecialGoodsListByPageResponse;
import org.tempuri.PlatformActivitiesWcfStub.Getpeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Getpeople_activitiesResponse;
import org.tempuri.PlatformActivitiesWcfStub.Guid;
import org.tempuri.PlatformActivitiesWcfStub.Newuser_activityd_details;
import org.tempuri.PlatformActivitiesWcfStub.Newuser_activityd_detailsResponse;
import org.tempuri.PlatformActivitiesWcfStub.UpdateAppSpecialActivityState;
import org.tempuri.PlatformActivitiesWcfStub.UpdateAppSpecialActivityStateResponse;
import org.tempuri.PlatformActivitiesWcfStub.Update_activitiesState;
import org.tempuri.PlatformActivitiesWcfStub.Update_activitiesStateResponse;
import org.tempuri.PlatformActivitiesWcfStub.Updatepeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Updatepeople_activitiesResponse;
import org.tempuri.model.ActivitiesParameterVo;
import org.tempuri.model.FlashPurchase;
import org.tempuri.model.UpdateFlashPurchaseDto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.ResultEmuns;

/**
 * 平台活动
 * @author Administrator
 *
 */

@RestController
@JsonIgnoreProperties(value = { "handler","hibernateLazyInitializer" })
public class PlatformActivityController {
	
	@Autowired
	private PermissionService perService;
	
	/**
	 * 平台活动列表
	 * @param people
	 * @return
	 */
	@GetMapping("GetPeopleActivities")
	public Message getPeopleActivitiesByPage(Getpeople_activities people){
		//int pageIndex,int pageSize,String activities_name,int activities_state
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("GetPeopleActivities");
			if (per == 1){
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				
				Getpeople_activitiesResponse act = paws.getpeople_activities(people);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(act);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//修改新人礼包活动
	@GetMapping("UpdatePeopleActivities")
	public Message updatePeopleActivities(ActivitiesParameterVo vo){
		
		Message msg = new Message();
		try {
			
			Integer per = perService.getConnector("UpdatePeopleActivities");
			if (per == 1){
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				Updatepeople_activities updatepeople_activities1 = new Updatepeople_activities();
				
				ActivitiesParameter ap = new ActivitiesParameter();
				
				JSONArray jsonArray = JSONArray.parseArray(vo.getModel());
				ArrayOfconditionModel aocm = new ArrayOfconditionModel();
				ConditionModel cm = new ConditionModel();
				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject json = jsonArray.getJSONObject(i);
					cm.setCategory_random(json.getInteger("category_random"));
					cm.setClassify(json.getString("classify"));
					cm.setCoupon_mode(json.getInteger("coupon_mode"));
					cm.setIs_allcate(json.getInteger("is_allcate"));
					cm.setReach(json.getString("reach"));
					cm.setValid_day(json.getInteger("valid_day"));
					cm.setReduction(BigDecimal.valueOf(json.getDouble("reduction")));
					aocm.addConditionModel(cm);
				}
				
				Guid g = new Guid();
				g.setGuid(vo.getActivities_code());
				ap.setMoney_type(vo.getMoney_type());
				ap.setActivities_code(g);
				ap.setCondition(aocm);
				ap.setActivities_name(vo.getActivities_name());
				ap.setSubsidy_moeny(BigDecimal.valueOf(vo.getSubsidy_money()));
				ap.setStart_time(getCalendar(vo.getStart_time()));
				ap.setEnd_time(getCalendar(vo.getEnd_time()));
				updatepeople_activities1.setParameter(ap);
				
				Updatepeople_activitiesResponse ur = paws.updatepeople_activities(updatepeople_activities1);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(ur);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//新增新人礼包
	@GetMapping("AddNewpeoplePackage")
	public Message addNewpeoplePackage(ActivitiesParameterVo vo){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("AddNewpeoplePackage");
			if (per == 1){
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				Addpeople_activities addpeople_activities = new Addpeople_activities();
				ActivitiesParameter ap = new ActivitiesParameter();
				
				ArrayOfconditionModel aocm = new ArrayOfconditionModel();
				
				JSONArray jsonArray = JSONArray.parseArray(vo.getCondition());
				ConditionModel cm = new ConditionModel();
				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject json = jsonArray.getJSONObject(i);
					cm.setCategory_random(json.getInteger("category_random"));
					cm.setClassify(json.getString("classify"));
					cm.setCoupon_mode(json.getInteger("coupon_mode"));
					cm.setIs_allcate(json.getInteger("is_allcate"));
					cm.setReach(json.getString("reach"));
					cm.setValid_day(json.getInteger("valid_day"));
					cm.setReduction(BigDecimal.valueOf(json.getDouble("reduction")));
					aocm.addConditionModel(cm);
				}
				
				ap.setMoney_type(vo.getMoney_type());
				ap.setCondition(aocm);
				ap.setActivities_name(vo.getActivities_name());
				ap.setSubsidy_moeny(BigDecimal.valueOf(vo.getSubsidy_money()));
				ap.setStart_time(getCalendar(vo.getStart_time()));
				ap.setEnd_time(getCalendar(vo.getEnd_time()));
				
				addpeople_activities.setParameter(ap);
				Addpeople_activitiesResponse addpeople_activitiesResponse = paws.addpeople_activities(addpeople_activities);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(addpeople_activitiesResponse);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//修改新人礼包状态
	@GetMapping("UpdateActivitiesState")
	public Message updateActivitiesState(@RequestParam String activities_code,@RequestParam Integer activities_state){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("UpdateActivitiesState");
			if (per == 1){
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				Update_activitiesState us = new Update_activitiesState();
				Guid guid = new Guid();
				guid.setGuid(activities_code);
				us.setActivities_code(guid);
				us.setActivities_state(activities_state);
				Update_activitiesStateResponse usr = paws.update_activitiesState(us);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(usr);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//删除新人礼包
	@GetMapping("DeleteNewpeoplePackage")
	public Message deleteNewpeoplePackage(@RequestParam String activities_code){
		
		Message msg = new Message();
		try {
			
			Integer per = perService.getConnector("DeleteNewpeoplePackage");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				Deletenewpeople_package dp = new Deletenewpeople_package();
				Guid guid = new Guid();
				guid.setGuid(activities_code);
				dp.setActivities_code(guid);
				Deletenewpeople_packageResponse dr = paws.deletenewpeople_package(dp);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(dr);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//新人礼包详情
	@GetMapping("NewUserActivitydDetails")
	public Message newUserActivitydDetails(@RequestParam String activities_code){

		Message msg = new Message();
		try {
			Integer per = perService.getConnector("NewUserActivitydDetails");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				Newuser_activityd_details nad = new Newuser_activityd_details();
				Guid guid = new Guid();
				guid.setGuid(activities_code);
				nad.setActivities_code(guid);         
				Newuser_activityd_detailsResponse nadr =  paws.newuser_activityd_details(nad);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(nadr);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	/////////////////平台闪购///////////////////
	
	//闪购平台活动列表
	@GetMapping("GetFlashPurchaseList")
	public Message getFlashPurchaseList(GetFlashPurchaseList gfpl){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("GetFlashPurchaseList");
			if (per == 1) {
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				GetFlashPurchaseListResponse response = faws.getFlashPurchaseList(gfpl);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {

			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
 
	//添加单品闪购
	@PostMapping("AddPlatformActive")
	public Message addPlatformActive(FlashPurchase fp){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("AddPlatformActive");
			if (per == 1) {
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				AddPlatformActive apa = new AddPlatformActive();
				FlashPurchaseDto fpd = new FlashPurchaseDto();
				
				fpd.setActivities_name(fp.getActivities_name());
				fpd.setStart_time(getCalendar(fp.getStart_time()));
				fpd.setEnd_time(getCalendar(fp.getEnd_time()));
				fpd.setShop_count(fp.getShop_count());
				String purchase = fp.getPurchaseCommodity();
				JSONArray jsonArray = JSONArray.parseArray(purchase);
				PurchaseCommodityDto pcd = new PurchaseCommodityDto();
				ArrayOfPurchaseCommodityDto aopcd = new ArrayOfPurchaseCommodityDto();
				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject json = jsonArray.getJSONObject(i);
					pcd.setShop_id(json.getInteger("shop_id"));
					pcd.setArticle_id(json.getInteger("article_id"));
					pcd.setCategory_id(json.getInteger("category_id"));
					pcd.setSpec_ids(json.getString("spec_ids"));
					pcd.setStock_number(json.getInteger("stock_number"));
					pcd.setPre_stock(json.getInteger("pre_stock"));
					pcd.setSales_volumeinfo(json.getInteger("sales_volumeinfo"));
					pcd.setCommodity_pirce(json.getBigDecimal("commodity_pirce"));
					pcd.setActive_price(json.getBigDecimal("active_price"));
					pcd.setLimit(json.getInteger("limit"));
					pcd.setActive_inventory(json.getInteger("active_inventory"));
					
					aopcd.addPurchaseCommodityDto(pcd);
				}
				
				fpd.setPurchaseCommodityDto(aopcd);
				apa.setDao(fpd);
				
				AddPlatformActiveResponse response = faws.addPlatformActive(apa);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//修改平台闪购活动状态
	@PostMapping("UpdateFlashPurchaseState")
	public Message updateFlashPurchaseState(@RequestParam String purchase_code,@RequestParam Integer state){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("UpdateFlashPurchaseState");
			if (per == 1) {
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				UpdateFlashPurchaseState ufps = new UpdateFlashPurchaseState();
				org.tempuri.FlashActiveWcfStub.Guid guid = new org.tempuri.FlashActiveWcfStub.Guid();
				guid.setGuid(purchase_code);
				ufps.setPurchase_code(guid);
				ufps.setState(state);
				UpdateFlashPurchaseStateResponse response = faws.updateFlashPurchaseState(ufps);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	//删除平台闪购活动
	@PostMapping("DeleteFlashPurchase")
	public Message deleteFlashPurchase(@RequestParam String purchase_code){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("DeleteFlashPurchase");
			if (per == 1) {
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				DeleteFlashPurchase dfp = new DeleteFlashPurchase();
				org.tempuri.FlashActiveWcfStub.Guid guid = new org.tempuri.FlashActiveWcfStub.Guid();
				guid.setGuid(purchase_code);
				dfp.setPurchase_code(guid);
				DeleteFlashPurchaseResponse reponse = faws.deleteFlashPurchase(dfp);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(reponse);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	//查询闪购平台活动商品统计列表 
	@GetMapping("GetActiveArticleList")
	public Message getActiveArticleList(@RequestParam String purchase_code){
		
		Message msg = new Message();
		try {
			
			Integer per = perService.getConnector("GetActiveArticleList");
			if (per == 1) {
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				GetActiveArticleList gaal = new GetActiveArticleList();
				org.tempuri.FlashActiveWcfStub.Guid guid = new org.tempuri.FlashActiveWcfStub.Guid();
				guid.setGuid(purchase_code);
				gaal.setPurchase_code(guid);
				GetActiveArticleListResponse response = faws.getActiveArticleList(gaal);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	//单个闪购详情
	@GetMapping("GetFlashPurchase")
    public Message getFlashPurchase(@RequestParam String purchase_code){//54F21563-E391-4793-B286-9CCE03134236
		
		Message msg = new Message();
		try {
			
			Integer per = perService.getConnector("GetFlashPurchase");
			if (per == 1){
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				GetFlashPurchase gfp = new GetFlashPurchase();
				org.tempuri.FlashActiveWcfStub.Guid guid = new org.tempuri.FlashActiveWcfStub.Guid();
				guid.setGuid(purchase_code);
				gfp.setPurchase_code(guid);
				GetFlashPurchaseResponse response = faws.getFlashPurchase(gfp);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//修改闪购平台闪购活动
	@PostMapping("UpdateFlashPurchase")
	public Message updateFlashPurchase(UpdateFlashPurchaseDto param){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("UpdateFlashPurchase");
			if (per == 1){
				
				FlashActiveWcfStub faws = new FlashActiveWcfStub();
				UpdateFlashPurchase ufp = new UpdateFlashPurchase();
				
				org.tempuri.FlashActiveWcfStub.Guid guid = new org.tempuri.FlashActiveWcfStub.Guid();
				guid.setGuid(param.getPurchase_code());
				
				
				ArrayOfPurchaseCommodityDto aopcd = new ArrayOfPurchaseCommodityDto();
				PurchaseCommodityDto pcd = new PurchaseCommodityDto();
				String purchase = param.getPurchaseCommodityDto();
				JSONArray jsonArray = JSONArray.parseArray(purchase);
				for(int i=0;i<jsonArray.size();i++){
					
					JSONObject json = jsonArray.getJSONObject(i);
					
					pcd.setPurchase_code(guid);
					pcd.setShop_id(json.getInteger("shop_id"));
					pcd.setArticle_id(json.getInteger("article_id"));
					pcd.setCategory_id(json.getInteger("category_id"));
					pcd.setSpec_ids(json.getString("spec_ids"));
					pcd.setStock_number(json.getInteger("stock_number"));
					pcd.setPre_stock(json.getInteger("pre_stock"));
					pcd.setSales_volumeinfo(json.getInteger("sales_volumeinfo"));
					pcd.setCommodity_pirce(BigDecimal.valueOf(json.getDouble("commodity_pirce")));
					pcd.setActive_price(BigDecimal.valueOf(json.getDouble("active_price")));
					pcd.setLimit(json.getInteger("limit"));
					pcd.setActive_inventory(json.getInteger("active_inventory"));
					pcd.setVisit_count(json.getInteger("visit_count"));
					pcd.setOrder_count(json.getInteger("order_count"));
					pcd.setPurchase_money(BigDecimal.valueOf(json.getDouble("purchase_money")));
					pcd.setConversion_rate(Float.valueOf(json.getString("conversion_rate")));
					
					aopcd.addPurchaseCommodityDto(pcd);
				}
				
				ArrayOfActiveArticleListDetailsDto aoa = new ArrayOfActiveArticleListDetailsDto();
				ActiveArticleListDetailsDto aad = new ActiveArticleListDetailsDto();
				String active = param.getActiveDetailsDto();
				JSONArray activeArray = JSONArray.parseArray(active);
				for(int j=0;j<activeArray.size();j++){
					
					JSONObject obj = activeArray.getJSONObject(j);
					String articlePrice = obj.getString("articlePriceDto");
					JSONArray priceArray = JSONArray.parseArray(articlePrice);
					ArrayOfArticlePriceDto aap = new ArrayOfArticlePriceDto();
					ArticlePriceDto apd = new ArticlePriceDto();
					for(int k=0;k<priceArray.size();k++){
						JSONObject priceObj = priceArray.getJSONObject(k);
						apd.setSpec_text(priceObj.getString("spec_text"));
						apd.setSell_price(BigDecimal.valueOf(priceObj.getDouble("sell_price")));
						aap.addArticlePriceDto(apd);
					}
					
					String plat = obj.getString("platArticleDto");
					JSONArray platArray = JSONArray.parseArray(plat);
					org.tempuri.FlashActiveWcfStub.ArrayOfPlatArticlePriceDto aop = new org.tempuri.FlashActiveWcfStub.ArrayOfPlatArticlePriceDto();
					PlatArticlePriceDto pap = new PlatArticlePriceDto();
					for(int x=0;x<platArray.size();x++){
						JSONObject platObj = platArray.getJSONObject(x);
						pap.setActive_price(BigDecimal.valueOf(platObj.getDouble("active_price")));
						pap.setLimit(platObj.getInteger("limit"));
						pap.setActive_inventory(platObj.getInteger("active_inventory"));
						pap.setSpec_ids(platObj.getString("spec_ids"));
						pap.setGoods_id(platObj.getInteger("goods_id"));
						pap.setSpec_text(platObj.getString("spec_text"));
						pap.setSell_price(BigDecimal.valueOf(platObj.getDouble("sell_price")));
						aop.addPlatArticlePriceDto(pap);
					}
					
					aad.setShop_id(obj.getInteger("shop_id"));
					aad.setArticle_id(obj.getInteger("article_id"));
					aad.setImg_url(obj.getString("img_url"));
					aad.setCategory_id(obj.getInteger("category_id"));
					aad.setCategory_title(obj.getString("category_title"));
					aad.setShopName(obj.getString("shopName"));
					aad.setTitle(obj.getString("title"));
					aad.setArticlePriceDto(aap);
					aad.setPlatArticleDto(aop);
					aoa.addActiveArticleListDetailsDto(aad);
					
				}
				
				FlashPurchaseDto fpd = new FlashPurchaseDto();
				fpd.setPurchase_code(guid);
				fpd.setActivities_name(param.getActivities_name());
				fpd.setStart_time(getCalendar(param.getStart_time()));
				fpd.setEnd_time(getCalendar(param.getEnd_time()));
				fpd.setShop_count(param.getShop_count());
				fpd.setVisit_count(param.getVisit_count());
				fpd.setOrder_count(param.getOrder_count());
				fpd.setPurchase_money(BigDecimal.valueOf(param.getPurchase_money()));
				fpd.setPurchase_types(param.getPurchase_types());
				fpd.setState(param.getState());
				fpd.setRemarks(param.getRemarks());
				fpd.setPurchaseCommodityDto(aopcd);
				fpd.setActiveDetailsDto(aoa);
				
				ufp.setDao(fpd);
				
				UpdateFlashPurchaseResponse response = faws.updateFlashPurchase(ufp);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg; 
	}
	
	//////////////特惠商品/////////////
	
	//分页查询活动列表
	@GetMapping("GetAppSpecialActivitiesByPage")
	public Message getAppSpecialActivitiesByPage(@RequestParam Integer pageIndex,@RequestParam Integer pageSize,String activity_name,Integer state){
		
		Message msg = new Message();
		GetAppSpecialActivitiesByPage gas = new GetAppSpecialActivitiesByPage();
		gas.setPage_index(pageIndex);
		gas.setPage_size(pageSize);
		gas.setActivity_name(activity_name);
		if (state != null)
		gas.setState(state);
		
		try {
			Integer per = perService.getConnector("GetAppSpecialActivitiesByPage");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				GetAppSpecialActivitiesByPageResponse response = paws.getAppSpecialActivitiesByPage(gas);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//修改app特惠活动状态
	@PostMapping("UpdateAppSpecialActivityState")
	public Message updateAppSpecialActivityState(@RequestParam String activity_code,@RequestParam Integer state){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("UpdateAppSpecialActivityState");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				UpdateAppSpecialActivityState uaas = new UpdateAppSpecialActivityState();
				Guid guid = new Guid();
				guid.setGuid(activity_code);
				uaas.setActivities_code(guid);
				uaas.setState(state);
				UpdateAppSpecialActivityStateResponse response = paws.updateAppSpecialActivityState(uaas);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//通过活动编码查询活动详情
	@GetMapping("GetAppSpecialActivityByCode")
	public Message getAppSpecialActivityByCode(@RequestParam String activity_code){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("GetAppSpecialActivityByCode");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				GetAppSpecialActivityById gas = new GetAppSpecialActivityById();
				Guid guid = new Guid();
				guid.setGuid(activity_code);
				gas.setActivities_code(guid);
				GetAppSpecialActivityByIdResponse response = paws.getAppSpecialActivityById(gas);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	//分页查询特惠活动商品列表
	@GetMapping("GetAppSpecialGoodsList")
	public Message getAppSpecialGoodsList(@RequestParam String activity_code,@RequestParam Integer pageIndex,@RequestParam Integer pageSize){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("GetAppSpecialGoodsList");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				GetGetAppSpecialGoodsListByPage gga = new GetGetAppSpecialGoodsListByPage();
				Guid guid = new Guid();
				guid.setGuid(activity_code);
				gga.setActivity_code(guid);
				gga.setPage_index(pageIndex);
				gga.setPage_size(pageSize);
				GetGetAppSpecialGoodsListByPageResponse response = paws.getGetAppSpecialGoodsListByPage(gga);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {

			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	//添加或修改app特惠活动信息
	@PostMapping("AddOrUpdateAppSpecielActivity")
	public Message addOrUpdateAppSpecielActivity(@RequestParam Integer type,@RequestParam String dto){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("AddOrUpdateAppSpecielActivity");
			if (per == 1) {
				
				JSONObject jsonObject = JSONObject.parseObject(dto);
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				AddOrUpdateAppSpecialActivity aua = new AddOrUpdateAppSpecialActivity();
				AppSpecialActivityBriefDto asa = new AppSpecialActivityBriefDto();
				ArrayOfAppSpecialGoodsDto aoa = new ArrayOfAppSpecialGoodsDto();
				AppSpecialGoodsDto asg = new AppSpecialGoodsDto();
				Guid guid = new Guid();
				if(type == 0){
					UUID uuid = UUID.randomUUID();
					guid.setGuid(uuid.toString());
				}else if(type == 1){
					guid.setGuid(jsonObject.getString("activities_code"));
				}else{
					msg.setCode(ResultEmuns.PARAM.getCode());
					msg.setMessage(ResultEmuns.PARAM.getMessage());
					return msg;
				}
				JSONArray jsonArray = jsonObject.getJSONArray("goods_list");
				for(int i=0;i<jsonArray.size();i++){
					
					JSONObject obj = jsonArray.getJSONObject(i);
					asg.setActivities_code(guid);
					asg.setShop_id(obj.getInteger("shop_id"));
					asg.setArticle_id(obj.getInteger("article_id"));
					asg.setGoods_id(obj.getInteger("goods_id"));
					asg.setCategory_id(obj.getInteger("category_id"));
					asg.setReal_stock(obj.getInteger("real_stock"));
					asg.setPre_stock(obj.getInteger("pre_stock"));
					asg.setActive_stock(obj.getInteger("active_stock"));
					asg.setLimit_num(obj.getInteger("limit_num"));
					asg.setSell_price(BigDecimal.valueOf(obj.getDouble("sell_price")));
					asg.setActive_price(BigDecimal.valueOf(obj.getDouble("active_price")));
					asg.setVisit_count(obj.getInteger("visit_count"));
					asg.setOrder_times(obj.getInteger("order_times"));
					asg.setOrder_count(obj.getInteger("order_count"));
					asg.setPurchase_money(BigDecimal.valueOf(obj.getDouble("purchase_money")));
					asg.setConversion_rate(BigDecimal.valueOf(obj.getDouble("conversion_rate")));
					aoa.addAppSpecialGoodsDto(asg);
				}
				asa.setActivities_code(guid);
				asa.setActivities_name(jsonObject.getString("activities_name"));
				asa.setStart_time(getCalendar(jsonObject.getString("start_time")));
				asa.setEnd_time(getCalendar(jsonObject.getString("end_time")));
				asa.setGoods_count(jsonObject.getInteger("goods_count"));
				asa.setGoods_list(aoa);
				
				aua.setType(type);
				aua.setDto(asa);
				AddOrUpdateAppSpecialActivityResponse response = paws.addOrUpdateAppSpecialActivity(aua);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	//删除app特惠活动信息
	@GetMapping("DeleteAppSpecialActivity")
	public Message deleteAppSpecialActivity(@RequestParam String activity_code){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("DeleteAppSpecialActivity");
			if (per == 1) {
				
				PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
				DeleteAppSpecialActivity dasa = new DeleteAppSpecialActivity();
				Guid guid = new Guid();
				guid.setGuid(activity_code);
				dasa.setActivities_code(guid);
				DeleteAppSpecialActivityResponse response = paws.deleteAppSpecialActivity(dasa);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(response);
			} else {
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
		
	}
	
	//日历
	public Calendar getCalendar(String time) throws ParseException{
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse(time);
		calendar.setTime(date);
		
		return calendar;
	}
	
}