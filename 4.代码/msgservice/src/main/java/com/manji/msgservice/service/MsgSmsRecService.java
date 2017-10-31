package com.manji.msgservice.service;

import com.manji.msgservice.common.utils.mybatis.manager.Manager;
import com.manji.msgservice.controller.sms.SmsRquestBody;
import com.manji.msgservice.model.*;

public interface MsgSmsRecService extends Manager<MsgSmsRec,MsgSmsRecExample,Long> {
    /**
     * 发送短息逻辑，处理是否及时发送，以及录入库，等到定时任务发送
     * @param body
     * @param content
     * @throws Exception
     */
    void sendSmsBiz(MsgTemplate msgTemplate,SmsRquestBody body, String content)throws Exception;
    /**
     * 定时任务执行业务处理
     * @throws Exception
     */
    void sendSmsTask()throws Exception;
    /**
     * 发短信(直接发--及时)
     * @param type
     * @param mobile
     * @param content
     * @throws Exception
     */
    void sendSms(String type, String mobile, String content)throws Exception;
}
