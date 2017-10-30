package com.manji.datahost.controller;

import java.io.IOException;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tempuri.UserWcfStub;
import org.tempuri.SmsMsgWcfStub.TimingTaskSend;
import org.tempuri.UserWcfStub.UserRePassword;
import org.tempuri.UserWcfStub.UserRePasswordModel;
import org.tempuri.UserWcfStub.UserRePasswordResponse;

import com.alibaba.fastjson.JSONObject;
import com.manji.datahost.model.mysql.Account;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.Base64Utils;
import com.manji.datahost.utils.GetAccountUtils;
import com.manji.datahost.utils.InterfaceUtil;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.ResultEmuns;

/**
 * 其他类调用接口
 * @author Administrator
 *
 */

@RestController
public class OtherController {

	@Autowired
	private PermissionService perService;
	MessageNotificationController messageController = new MessageNotificationController();
	
	//根据sessionId获取用户信息
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
	
	//获取图片地址
	@PostMapping("getPics")
	public Message getPics(@RequestParam MultipartFile[] file){
		Message msg = new Message();
		Integer per = perService.getConnector("getPics");
		if(per ==1){
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
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
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
			try {
				
				Integer per = perService.getConnector("AccountStateFrozen");
				
				if(per ==1){
					
					TreeMap<String,Object> map = new InterfaceUtil().getHttpTreeMap("AccountStateFrozen",content,role_type,role_value,user_id);
				
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
									
								Message response = messageController.messageSend(tts);
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
				}else{
					msg.setCode(ResultEmuns.NOACCESS.getCode());
					msg.setMessage(ResultEmuns.NOACCESS.getMessage());
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
			
			try {
				
				Integer per = perService.getConnector("AccountStateUnFrozen");
				if (per == 1) {
					
					TreeMap<String,Object> map = new InterfaceUtil().getHttpTreeMap("AccountStateUnFrozen","",role_type,role_value,user_id);
					
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
								Message response = messageController.messageSend(tts);
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
				} else {
					msg.setCode(ResultEmuns.NOACCESS.getCode());
					msg.setMessage(ResultEmuns.NOACCESS.getMessage());
				}
			} catch (IOException e) {
				e.printStackTrace();
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
				
				Integer per = perService.getConnector("UserRePassword");
				if (per == 1){
					
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
				} else {
					msg.setCode(ResultEmuns.NOACCESS.getCode());
					msg.setMessage(ResultEmuns.NOACCESS.getMessage());
				}
			}catch(Exception e){
				msg.setCode(ResultEmuns.ERROR.getCode());
				msg.setMessage(e.getMessage());
			}
			
			return msg;
		}

}
