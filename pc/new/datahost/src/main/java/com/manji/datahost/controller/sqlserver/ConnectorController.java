package com.manji.datahost.controller.sqlserver;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tempuri.FlashActiveWcfStub;
import org.tempuri.FlashActiveWcfStub.AddPlatformActive;
import org.tempuri.FlashActiveWcfStub.AddPlatformActiveResponse;
import org.tempuri.FlashActiveWcfStub.ArrayOfPurchaseCommodityDto;
import org.tempuri.FlashActiveWcfStub.FlashPurchaseDto;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchaseList;
import org.tempuri.FlashActiveWcfStub.GetFlashPurchaseListResponse;
import org.tempuri.FlashActiveWcfStub.PurchaseCommodityDto;
import org.tempuri.PlatformActivitiesWcfStub;
import org.tempuri.PlatformActivitiesWcfStub.ActivitiesParameter;
import org.tempuri.PlatformActivitiesWcfStub.Addpeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Addpeople_activitiesResponse;
import org.tempuri.PlatformActivitiesWcfStub.ArrayOfconditionModel;
import org.tempuri.PlatformActivitiesWcfStub.ConditionModel;
import org.tempuri.PlatformActivitiesWcfStub.Deletenewpeople_package;
import org.tempuri.PlatformActivitiesWcfStub.Deletenewpeople_packageResponse;
import org.tempuri.PlatformActivitiesWcfStub.Getpeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Getpeople_activitiesResponse;
import org.tempuri.PlatformActivitiesWcfStub.Guid;
import org.tempuri.PlatformActivitiesWcfStub.Newuser_activityd_details;
import org.tempuri.PlatformActivitiesWcfStub.Newuser_activityd_detailsResponse;
import org.tempuri.PlatformActivitiesWcfStub.Update_activitiesState;
import org.tempuri.PlatformActivitiesWcfStub.Update_activitiesStateResponse;
import org.tempuri.PlatformActivitiesWcfStub.Updatepeople_activities;
import org.tempuri.PlatformActivitiesWcfStub.Updatepeople_activitiesResponse;
import org.tempuri.SmsMsgWcfStub;
import org.tempuri.UserWcfStub;
import org.tempuri.SmsMsgWcfStub.AddSystemMessage;
import org.tempuri.SmsMsgWcfStub.AddSystemMessageResponse;
import org.tempuri.SmsMsgWcfStub.InsertUserMessage;
import org.tempuri.SmsMsgWcfStub.OperationEnumMessageCategory;
import org.tempuri.SmsMsgWcfStub.TimingTaskSend;
import org.tempuri.SmsMsgWcfStub.UserMessageDto;
import org.tempuri.UserWcfStub.UserRePassword;
import org.tempuri.UserWcfStub.UserRePasswordModel;
import org.tempuri.UserWcfStub.UserRePasswordResponse;
import org.tempuri.model.ActivitiesParameterVo;
import org.tempuri.model.FlashPurchase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.datahost.model.mysql.Account;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.Base64Utils;
import com.manji.datahost.utils.GetAccountUtils;
import com.manji.datahost.utils.InterfaceUtil;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.PlatformActivities;
import com.manji.datahost.utils.ResultEmuns;

@RestController
public class ConnectorController {
	
	@Autowired
	private PermissionService perService;
	
	@GetMapping("getSessionInfo")
	public Message getSessionInfo(String sessionId){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getAgentInfo");
		if(per ==1){
			if(sessionId == null){
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
			}else{
				Account account = GetAccountUtils.getAccount(sessionId);
				if(account.getUser_id() != null){
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(account);
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage("用户已下线");
				}
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	@PostMapping("getPics")
	public Message getPics(@RequestParam MultipartFile[] file){
		Message msg = new Message();
		if(file != null && file.length>0){
			String url = "";
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(i == file.length-1){
					url += perService.sendPostReq(fileName, base64Str);
				}else{
					url += perService.sendPostReq(fileName, base64Str)+",";
				}
			}
			msg.setResult(url);
		}
		msg.setCode(ResultEmuns.SUCCESS.getCode());
		msg.setMessage(ResultEmuns.SUCCESS.getMessage());
		
		return msg;
	}
	
	//冻结账户
	@GetMapping("AccountStateFrozen")
	public Message accountStateFrozen(@RequestParam Integer user_id,@RequestParam String content,@RequestParam String role_type,
			@RequestParam Integer role_value,@RequestParam String mobile){
		
		Message msg = new Message();
		
		TreeMap<String,Object> map = new InterfaceUtil().getHttpTreeMap("AccountStateFrozen",content,role_type,role_value,user_id);
		
		try {
			String k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			
			JSONObject jsonObject = JSONObject.parseObject(k);
			int errCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			if(errCode == 0){
				int resultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if(resultCode == 8){
					
					if(mobile != null && !"".equals(mobile)){
						org.tempuri.SmsMsgWcfStub.TimingTaskSend tts = new TimingTaskSend();
						String type="";
						switch(role_type){
							case "Buyer":
								type = "用户";
							case "Shop":
								type = "商家";
							case "Agent":
								type = "代理商";
							default:
								type = "";
						}
						tts.setIp("");
						tts.setPass(1);
						tts.setTitle("冻结账户");
						tts.setContent("亲！您的"+type+"账户,由于"+content+"（原因），平台冻结了您的账户，我们将会尽快查明原因为您解冻。如有疑问致电400-6766-999咨询。");
						tts.setMobiles(mobile);
							
						Message response = messageSend(tts);
						if(response.getCode() == 200){
							msg.setCode(ResultEmuns.SUCCESS.getCode());
							msg.setMessage(ResultEmuns.SUCCESS.getMessage());
						}else{
							msg.setCode(ResultEmuns.ERROR.getCode());
							msg.setMessage("短信发送失败");
						}
					}else{
						msg.setCode(ResultEmuns.SUCCESS.getCode());
						msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					}
						
				}else{
					msg.setCode(ResultEmuns.ERROR.getCode());
					msg.setMessage("冻结失败");
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
	
	
	//解冻账户
	@GetMapping("AccountStateUnFrozen")
	public Message accountStateUnFrozen(@RequestParam Integer user_id,@RequestParam String role_type,
			@RequestParam Integer role_value,@RequestParam String mobile){
		
		Message msg = new Message();
		TreeMap<String,Object> map = new InterfaceUtil().getHttpTreeMap("AccountStateUnFrozen","",role_type,role_value,user_id);
		try {
			String k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
			JSONObject jsonObject = JSONObject.parseObject(k);
			int ErrCode = Integer.parseInt(jsonObject.get("ErrCode").toString());
			if (ErrCode == 0) {
				int ResultCode = Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
				if (ResultCode==8){
					if(mobile != null && !"".equals(mobile)){
						
						org.tempuri.SmsMsgWcfStub.TimingTaskSend tts = new TimingTaskSend();
						String type="";
						switch(role_type){
						case "Buyer":
							type = "用户";
						case "Shop":
							type = "商家";
						case "Agent":
							type = "代理商";
						default:
							type = "";
						}
						tts.setIp("");
						tts.setPass(1);
						tts.setTitle("解冻账户");
						tts.setContent("亲！您的"+type+"账户,平台解冻了您的账户。");
						tts.setMobiles(mobile);
						Message response = messageSend(tts);
						if(response.getCode() == 200){
							msg.setCode(ResultEmuns.SUCCESS.getCode());
							msg.setMessage(ResultEmuns.SUCCESS.getMessage());
						}else{
							msg.setCode(ResultEmuns.ERROR.getCode());
							msg.setMessage("短信发送失败");
						}
					}else{
						msg.setCode(ResultEmuns.SUCCESS.getCode());
						msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					}
				}else{
					msg.setCode(ResultEmuns.ERROR.getCode());
					msg.setMessage("解冻失败");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	
	///////////////////////////平台活动/////////////////////////////////////
	
	@GetMapping("GetPeopleActivitiesByPage")
	public Message getPeopleActivitiesByPage(@RequestParam Integer pageIndex,@RequestParam Integer pageSize,
			String activities_name,Integer activities_state){
		
		String url = "http://192.168.0.48:8050/MJ.BLL.Activities.Api/api/Platform/GetPeopleActivitiesByPage";
		Message msg = new Message();
		//Integer per = perService.getConnector("GetPeopleActivitiesByPage");
		//if(per ==1){
		String param = "pageIndex="+pageIndex+"&pageSize="+pageSize;
		
		if(activities_name != null && !"".equals(activities_name)){
			param += "&activities_name="+activities_name;
		}
		if(activities_state != null && !"".equals(activities_state)){
			param += "&activities_state="+activities_state;
		}
		String result = PlatformActivities.sendGet(url,param);
			if(result != null){
				JSONObject obj = JSONObject.parseObject(result);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(obj);
			}else{
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}
//		}else{
//			msg.setCode(ResultEmuns.NOACCESS.getCode());
//			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
//		}
		return msg;
	}
	
	/////////////////////wcf短信发送///////////////////////////////
	
	
	@GetMapping("TimingTaskSend")
	public Message messageSend(org.tempuri.SmsMsgWcfStub.TimingTaskSend tts){
		Message msg = new Message();
		try{
			SmsMsgWcfStub smf = new SmsMsgWcfStub();
			org.tempuri.SmsMsgWcfStub.TimingTaskSendResponse ttr = smf.timingTaskSend(tts);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(ttr);
		}catch(Exception e){
			
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	
	////////////////////////站内信息////////////////////////////
	
	@GetMapping("InsertUserMessage")
	public Message InsertUserMessage(UserMessageDto umd){
		
		Message msg = new Message();
		
		try{
			
			SmsMsgWcfStub smf = new SmsMsgWcfStub();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String str = "2016-10-19 10:27:30";
			Date date = dateFormat.parse(dateFormat.format(new Date()));
			calendar.setTime(date);
	//		umd.setId(12345);
	//		umd.setAccept_user_id(111);
	//		umd.setAccept_user_role_type("dd");
	//		umd.setAccept_user_role_value(1);
	//		umd.setBusinessNumber("d");
	//		umd.setCategory(1);
	//		umd.setContent("dd");
	//		umd.setIs_del(0);
	//		umd.setIs_read(0);
	//		umd.setPost_user_id(1);
	//		umd.setPost_user_role_type("ddd");
	//		umd.setPost_user_role_value(1);
	//		umd.setRedirect_type("dd");
	//		umd.setRedirect_value("hah");
	//		umd.setTitle("yi");
	//		umd.setType(1);
			umd.setPost_time(calendar);
			umd.setRead_time(calendar);
			
			
			InsertUserMessage ium = new InsertUserMessage();
			ium.setModel(umd);
			org.tempuri.SmsMsgWcfStub.InsertUserMessageResponse kk = smf.insertUserMessage(ium);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(kk);
		}catch(Exception e){
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());  
		}
		
		return msg;
	}
	
	//推送消息
	@GetMapping("AddSystemMessage")
	public Message addSystemMessage(@Validated AddSystemMessage asm,BindingResult result) {
		
		Message msg = new Message();
		try{
			SmsMsgWcfStub smf = new SmsMsgWcfStub();
			//MessageCategory mc = new MessageCategory();
			//AddSystemMessage k = new AddSystemMessage();
//			AddSystemMessage asm = new AddSystemMessage();
//			asm.setAccept_user_id(1);
//			asm.setAccept_user_role_type("");
//			asm.setAccept_user_role_value(1);
//			asm.setBusinessNumber("");
//			asm.setContent("");
//			asm.setIsSms(true);
//			asm.setLoginType(""); 
//			asm.setMobile("");
//			asm.setSmsCotent("");  
//			asm.setTitle("");
//			asm.setU_type(1);
//		    asm.setOperation
			if(result.hasErrors()){
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
			}else{
				
				OperationEnumMessageCategory oec = new OperationEnumMessageCategory("SysMessage",true);
				asm.setMessageCategory(oec);
				
				AddSystemMessageResponse addSystemMessageResponse = smf.addSystemMessage(asm);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(addSystemMessageResponse);
			}
		}catch(Exception e){
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	//重置密码
	@GetMapping("UserRePassword")
	public Message UserRePassword(@RequestParam String account,@RequestParam String newPassword){
		
		Message msg = new Message();
		try {
			
			UserWcfStub uws = new UserWcfStub();
			UserRePassword urp = new UserRePassword();
			UserRePasswordModel model = new UserRePasswordModel();
			
			model.setAccount(account);
			model.setNewPassword(newPassword);
			urp.setModel(model);
			
			UserRePasswordResponse reponse = uws.userRePassword(urp);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(reponse);
		}catch(Exception e){
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	
	//////////////////平台活动////////////////////////
	
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
			
			PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
			Getpeople_activitiesResponse act = paws.getpeople_activities(people);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(act);
		} catch (Exception e) {
			
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	@GetMapping("UpdatePeopleActivities")
	public Message updatePeopleActivities(ActivitiesParameterVo vo){
		
		Message msg = new Message();
		try {
			
			PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
			Updatepeople_activities updatepeople_activities1 = new Updatepeople_activities();
			
			ActivitiesParameter ap = new ActivitiesParameter();
			
			//JSONArray jsonArray = JSONArray.parseArray("[{\"category_random\":1,\"classify\":1,\"coupon_mode\":2,\"is_allcate\":0,\"reach\":10,\"valid_day\":10,\"reduction\":1}]");
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
			
			PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
			Addpeople_activities addpeople_activities = new Addpeople_activities();
			ActivitiesParameter ap = new ActivitiesParameter();
			
			ArrayOfconditionModel aocm = new ArrayOfconditionModel();

			JSONArray jsonArray = JSONArray.parseArray(vo.getModel());
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
			
			PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
			Deletenewpeople_package dp = new Deletenewpeople_package();
			Guid guid = new Guid();
			guid.setGuid(activities_code);
			dp.setActivities_code(guid);
			Deletenewpeople_packageResponse dr = paws.deletenewpeople_package(dp);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(dr);
			
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
			
			PlatformActivitiesWcfStub paws = new PlatformActivitiesWcfStub();
			Newuser_activityd_details nad = new Newuser_activityd_details();
			Guid guid = new Guid();
			guid.setGuid(activities_code);
			nad.setActivities_code(guid);
			Newuser_activityd_detailsResponse nadr =  paws.newuser_activityd_details(nad);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(nadr);
			
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
		//参数：pageIndex,pageSize,activeType,activeName
		Message msg = new Message();
		
		try {
			FlashActiveWcfStub faws = new FlashActiveWcfStub();
			GetFlashPurchaseListResponse response = faws.getFlashPurchaseList(gfpl);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(response);
		} catch (Exception e) {

			e.printStackTrace();
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}
 
	//添加单品闪购
	@GetMapping("AddPlatformActive")
	public Message addPlatformActive(){
		
		Message msg = new Message();
		try {
			
			FlashActiveWcfStub faws = new FlashActiveWcfStub();
//			AddPlatformActive apa = new AddPlatformActive();
//			FlashPurchaseDto fpd = new FlashPurchaseDto();
//			fpd.setActivities_name("超前测试");
//			fpd.setStart_time(getCalendar("2017-10-20 09:27:30"));
//			fpd.setEnd_time(getCalendar("2017-10-22 10:27:30"));
//			fpd.setShop_count(2);
//			
//			ArrayOfPurchaseCommodityDto aopcd = new ArrayOfPurchaseCommodityDto();
//			PurchaseCommodityDto pcd = new PurchaseCommodityDto();
//			pcd.setShop_id(2144402);
//			pcd.setArticle_id(291377);
//			pcd.setCategory_id(4154);
//			pcd.setSpec_ids("7053");
//			pcd.setStock_number(0);
//			pcd.setPre_stock(0);
//			pcd.setSales_volumeinfo(0);
//			pcd.setCommodity_pirce(BigDecimal.valueOf(1.1));
//			pcd.setActive_price(BigDecimal.valueOf(2.2));
//			pcd.setLimit(5);
//			pcd.setActive_inventory(18);
//			
//			aopcd.addPurchaseCommodityDto(pcd);
//			
//			fpd.setPurchaseCommodityDto(aopcd);
//			apa.setDao(fpd);
//			
//			AddPlatformActiveResponse response = faws.addPlatformActive(apa);
			
//			AddPlatformActive apa = new AddPlatformActive();
//			FlashPurchase f = new FlashPurchase();
//			apa.setDao(f);
//			AddPlatformActiveResponse response = faws.addPlatformActive(apa);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(null);
			
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
		Date date = dateFormat.parse(dateFormat.format(new Date()));
		calendar.setTime(date);
		
		return calendar;
	}
	
}