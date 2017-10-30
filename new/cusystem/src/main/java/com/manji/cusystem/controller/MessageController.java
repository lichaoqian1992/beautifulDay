package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.MessageService;
import com.manji.cusystem.utils.PicUtils;
import com.manji.cusystem.vo.message.MessageVo;
import com.manji.cusystem.vo.message.QueryMessageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.File;

/**
 * Created by Administrator on 2017/9/10.
 */
@RestController
@RequestMapping(value = "/mes")
public class MessageController extends BaseController{

    @Autowired
    private MessageService service;

    /**
     * 新增短信
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/addMessage")
    public String addMessage(@Valid MessageVo vo, BindingResult bindingResult,@Param("sessionId") String sessionId,@Param("oType")String oType){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            if(null == oType || oType.equals("")){
                result.setCode("404");
                result.setContent("参数oType不能为空");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{

                Account user = (Account)result.getResult();
                result = service.addMessage(vo,user,oType,"");
            }
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 选择接收对象时查询总数量
     * @param acceptType
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/getCount")
    public String getCount(@Param("acceptType") String acceptType,@Param("sessionId") String sessionId,@Param("cusKind") String cusKind){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){

            result = service.getCount(acceptType,cusKind);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 查询短信
     * @return
     */
    @RequestMapping(value = "/selectMessage")
    public String selectMessage(QueryMessageVo vo, @Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){

            result = service.selectMessage(vo);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }


    /**
     * 查询短信详情
     * @param sessionId
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/selectMessageDetail")
    public String selectMessageDetail(@Param("sessionId") String sessionId,@Param("cusId") String cusId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            if(cusId == null || cusId.equals("")){
                result.setCode("404");
                result.setContent("参数cusId短信id不能为空");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            result = service.selectMessageDetail(cusId);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 短信详情里面的发送短信
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/updateMessage")
    public String updateMessage(@Valid MessageVo vo, BindingResult bindingResult,@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                result = service.updateMessage(vo,user);
            }
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除短信
     * @param sessionId
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/delMessage")
    public String delMessage(@Param("sessionId") String sessionId,@Param("cusId") String cusId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            if(cusId == null || cusId.equals("")){
                result.setCode("404");
                result.setContent("参数短信id cusId不能为空");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            result = service.delMessage(cusId);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }
}
