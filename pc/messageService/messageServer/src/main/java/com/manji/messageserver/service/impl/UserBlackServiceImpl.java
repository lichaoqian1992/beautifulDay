package com.manji.messageserver.service.impl;

import com.google.gson.Gson;
import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.requestParam.addUserBlackRequestParam;
import com.manji.messageserver.requestParam.queryUserBlackRequestParam;
import com.manji.messageserver.requestParam.removeBlackUserRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.responseResult.ObjectBaseResult;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.service.UserBlackService;
import com.manji.messageserver.service.UserService;
import com.manji.messageserver.utils.HttpJsonTool;
import com.manji.messageserver.utils.StringUtils;
import com.manji.messageserver.vo.UserBlackVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class UserBlackServiceImpl implements UserBlackService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService commonService;

    @Override
    public BaseResult queryUserBlack(queryUserBlackRequestParam requestParam) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getQueryUserBlackUrl()+requestParam.getUserName()+"/blocks/users";
        String result = HttpJsonTool.sendHttpJsonGet(url, token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserBlackVo userVo = gson.fromJson(result , UserBlackVo.class);
            logger.info("https Request  -用户查询黑名单 userBlack={}", userVo);
            return ObjectBaseResult.successResult(userVo);
        }
    }
    @Override
    public BaseResult addUserBlack(addUserBlackRequestParam requestParam,String userName){
        Gson gson=new Gson();
        String json = gson.toJson(requestParam);
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getAddUserBlackUrl()+userName+"/blocks/users";
        String result = HttpJsonTool.sendHttpJsonPost(url ,json , token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            logger.info("https Request  -添加用户黑名单 result={}", result);
            return BaseResult.getSuccessResult("添加黑名单成功");
        }
    }
    @Override
    public BaseResult removeUserBlack(removeBlackUserRequestParam requestParam){
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getRemoveUserBlackUrl().replace("$",requestParam.getOwnerUserName())+requestParam.getBlockedUserName();
        String result = HttpJsonTool.sendHttpJsonDelete(url , token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            logger.info("https Request  -删除用户黑名单 result={}", result);
            return BaseResult.getSuccessResult("删除黑名单成功");
        }
    }
}
