package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.ConversationService;
import com.manji.cusystem.service.SheetService;
import com.manji.cusystem.service.SystemsettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/14.
 */
@RestController
@RequestMapping(value = "/main")
//@Api("主页相关接口")
public class MainController extends BaseController{

    @Autowired
    private ConversationService service;

    @Autowired
    private SheetService sheetService;

    @Autowired
    private SystemsettingsService systemsettingsService;
    /**
     * 获取今日会话数据
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/getConversation",method = RequestMethod.GET)
    /*@ApiOperation(value = "获取今日会话数据",notes = "获取今日会话数据")
    @ApiImplicitParam(name = "sessionId",value = "登录校验参数",required = true,dataType = "String",paramType = "path")*/
    public String getConversation(@Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            result = service.getConversationToday();
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }

    /**
     * 我创建的工单
     */
    @RequestMapping(value = "/sheetSelf",method = RequestMethod.GET)
    /*@ApiOperation(value = "获取我创建的工单",notes = "获取我创建的工单")
    @ApiImplicitParam(name = "sessionId",value = "登录校验参数",required = true,dataType = "String",paramType = "path")*/
    public String sheetSelf(@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account) result.getResult();
            user.setSessionId(sessionId);
            result = sheetService.sheetSelf(user);
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 消息通知
     * @return
     */
    @RequestMapping(value = "/selInformations",method = RequestMethod.GET)
    /*@ApiOperation(value = "获取消息通知",notes = "获取消息通知")
    @ApiImplicitParam(name = "sessionId",value = "登录校验参数",required = true,dataType = "String",paramType = "path")*/
    public String selInformations(@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account) result.getResult();
            user.setSessionId(sessionId);
            result = sheetService.selInformations(user,"select");
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }

    /**
     * 清除消息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/clearInformations",method = RequestMethod.GET)
    /*@ApiOperation(value = "清除消息",notes = "清除消息")
    @ApiImplicitParam(name = "sessionId",value = "登录校验参数",required = true,dataType = "String",paramType = "path")*/
    public String clearInformations(@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account) result.getResult();
            user.setSessionId(sessionId);
            result = sheetService.selInformations(user,"clear");
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);


    }

    /**
     * 查询菜单信息
     * @param userId
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/findMenuByRole",method = RequestMethod.GET)
    @ApiOperation(value = "查询菜单信息",notes = "查询菜单信息")
    /*@ApiImplicitParams({@ApiImplicitParam(name = "sessionId",value = "登录校验参数",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "userId",value = "登录人id",required = true,dataType = "int",paramType = "path")})*/
    public String findMenuByRole(@RequestParam("userId") int userId,@RequestParam("sessionId") String sessionId){


        BaseResult result=systemsettingsService.findMenu(userId);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 推送消息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/pushInfo",method = RequestMethod.GET)
    public String pushInfo(@RequestParam(value = "sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account) result.getResult();
            user.setSessionId(sessionId);
            result = sheetService.pushInfo(user);
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }

    /**
     * 关闭推送消息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/clearPushInfo",method = RequestMethod.GET)
    public String clearPushInfo(@RequestParam(value = "infoId") String infoId,@RequestParam(value = "sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account) result.getResult();
            user.setSessionId(sessionId);
            result = sheetService.clearPushInfo(user,infoId);
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }
}
