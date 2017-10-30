package com.manji.datahost.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tempuri.SmsMsgWcfStub;
import org.tempuri.SmsMsgWcfStub.AddSystemMessage;
import org.tempuri.SmsMsgWcfStub.AddSystemMessageResponse;
import org.tempuri.SmsMsgWcfStub.InsertUserMessage;
import org.tempuri.SmsMsgWcfStub.OperationEnumCodeSourceType;
import org.tempuri.SmsMsgWcfStub.OperationEnumMessageCategory;
import org.tempuri.SmsMsgWcfStub.OperationEnumUserAccountType;
import org.tempuri.SmsMsgWcfStub.SendVerifyCode;
import org.tempuri.SmsMsgWcfStub.SendVerifyCodeResponse;
import org.tempuri.SmsMsgWcfStub.UserMessageDto;

import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.ResultEmuns;

/**
 * 消息通知
 * @author Administrator
 *
 */
public class MessageNotificationController {

	@Autowired
	private PermissionService perService;
	
	//发送短信
	@GetMapping("TimingTaskSend")
	public Message messageSend(org.tempuri.SmsMsgWcfStub.TimingTaskSend tts){
		Message msg = new Message();
		try{
			Integer per = perService.getConnector("TimingTaskSend");
			if (per == 1){
				
				SmsMsgWcfStub smf = new SmsMsgWcfStub();
				org.tempuri.SmsMsgWcfStub.TimingTaskSendResponse ttr = smf.timingTaskSend(tts);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(ttr);
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
	
	//站内信息
	@GetMapping("InsertUserMessage")
	public Message InsertUserMessage(UserMessageDto umd){
		
		Message msg = new Message();
		
		try{
			
			Integer per = perService.getConnector("InsertUserMessage");
			if (per == 1){
				
				SmsMsgWcfStub smf = new SmsMsgWcfStub();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = dateFormat.parse(dateFormat.format(new Date()));
				calendar.setTime(date);
				umd.setPost_time(calendar);
				umd.setRead_time(calendar);
				
				
				InsertUserMessage ium = new InsertUserMessage();
				ium.setModel(umd);
				org.tempuri.SmsMsgWcfStub.InsertUserMessageResponse kk = smf.insertUserMessage(ium);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(kk);
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
	
	//推送消息
	@GetMapping("AddSystemMessage")
	public Message addSystemMessage(@Validated AddSystemMessage asm,BindingResult result) {
		
		Message msg = new Message();
		try{
			Integer per = perService.getConnector("AddSystemMessage");
			if (per == 1) {
				
				SmsMsgWcfStub smf = new SmsMsgWcfStub();
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
	
	//发送邮件
	@GetMapping("SendEmailCode")
	public Message sendEmailCode(@RequestParam String typeValue,@RequestParam Integer codeSourceType){
		
		Message msg = new Message();
		try {
			Integer per = perService.getConnector("SendEmailCode");
			if (per == 1) {
				
				SmsMsgWcfStub smf = new SmsMsgWcfStub();
				SendVerifyCode sendVerifyCode = new SendVerifyCode();
				OperationEnumCodeSourceType oecst = null;
				switch(codeSourceType){
				case 0:
					oecst = OperationEnumCodeSourceType.Register;
					break;
				case 1:
					oecst = OperationEnumCodeSourceType.LoginPwd;
					break;
				case 2:
					oecst = OperationEnumCodeSourceType.PayPwd;
					break;
				case 3:
					oecst = OperationEnumCodeSourceType.Binding;
					break;
				case 4:
					oecst = OperationEnumCodeSourceType.InviteRegister;
					break;
				default:
					oecst = OperationEnumCodeSourceType.Usercode;
					break;
				}
				sendVerifyCode.setCodeSourceType(oecst);
				sendVerifyCode.setTypeValue(typeValue);
				sendVerifyCode.setType(OperationEnumUserAccountType.Email);
				SendVerifyCodeResponse response = smf.sendVerifyCode(sendVerifyCode);
				
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
	
	
}
