package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.MailService;
import com.manji.cusystem.utils.MailUtils;
import com.manji.cusystem.utils.PicUtils;
import com.manji.cusystem.vo.message.MessageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/9/11.
 *
 * 发送邮件的控制层
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController extends BaseController{

    @Autowired
    private MailService service;

    @Autowired
    JavaMailSender javaMailSender;

    private BaseResult result = new BaseResult();

    /**
     * 新增邮件
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/addMail")
    public String addMail(MessageVo vo, BindingResult bindingResult, @Param("sessionId") String sessionId,@Param("oType") String oType){

        result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            if(null == oType || oType.equals("")){
                result.setCode("404");
                result.setContent("参数oType必须为save或者send");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                result = service.addMail(vo,user,oType);
            }
        }
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 上传图片，返回路径
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public String uploadPic(MultipartFile file,@Param("sessionId") String sessionId){
        String path = "";
        result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            path = new PicUtils().postPic(file);
        }

        return path;
    }

    @RequestMapping(value = "/test")
    public String test(){

        String to = "376775994@qq.com,1193418905@qq.com";

        //list.add("376775994@qq.com");
       //list.add("1193418905@qq.com");
        /* list.add("738393697@qq.com");
        list.add("1336575170@qq.com");
        list.add("1015598423@qq.com");
        list.add("781408983@qq.com");
        list.add("919239554@qq.com");
        list.add("1103184505@qq.com");*/
        String content = "测试邮件。。。。。。。。";
        String theme = "图片";
        //new MailUtils().sendFileMail(to,content,theme,"C:\\Users\\Administrator\\Desktop\\cus_reason.xlsx",javaMailSender);
        //new MailUtils().sendTextMail(to,content,theme,javaMailSender);
        //new MailUtils().sendBatchMail(list,theme,content,"C:\\Users\\Administrator\\Desktop\\cus_reason.xlsx",javaMailSender);

        new MailUtils().sendInlineResourceMail(to,theme,"<html><body>日落<img src='cid:rscId001' ></body></html>","E:\\日落.png","rscId001",javaMailSender);
        return "SUCCESS";
    }
}
