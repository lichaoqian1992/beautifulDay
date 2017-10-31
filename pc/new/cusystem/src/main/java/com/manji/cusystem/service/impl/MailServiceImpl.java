package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.feign.DataHostFeignService;
import com.manji.cusystem.service.MailService;
import com.manji.cusystem.service.MessageService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.PicUtils;
import com.manji.cusystem.vo.message.MessageVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/12.
 */
@Service
public class MailServiceImpl implements MailService{

    private Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Autowired
    private DataHostFeignService service;

    @Autowired
    private MessageService messageService;

    @Autowired
    JavaMailSender javaMailSender;

    private BaseResult result = new BaseResult();
    private static final String path = "e:";
    @Override
    public BaseResult addMail(MessageVo vo, Account user,String oType) {

        logger.info("=====开始调用addMail接口，调用时间："+new DatesUtils().time()+"参数："+ JSONObject.toJSONString(vo));

        //文件上传
        String url = "";
        /*if(file.getSize() != 0){

            url = new PicUtils().uploadPic(file,path);
        }*/

        //先发送邮件，发送成功以后在保存邮件记录
        /*if(vo.getCusAcceptType().equals("Buyer") || vo.getCusAcceptType().equals("Shop") || vo.getCusAcceptType().equals("Agent")){

            //String mails = service.findMail(vo.getCusAcceptType());
        }*/
        /*String list = "376775994@qq.com,1193418905@qq.com";
        vo.setCusKind("email");
        vo.setCusContent("测试邮件");
        vo.setCusCount("2");
        vo.setCusAcceptType(list);
        vo.setCusType("2");
        vo.setCusTheme("测试邮件");*/
        //2.处理获取的邮箱
        result = messageService.addMessage(vo,user,oType,vo.getCusUrl());

        result.setCode("200");
        result.setContent("发送成功");
        result.setResult("");
        return result;
    }
}
