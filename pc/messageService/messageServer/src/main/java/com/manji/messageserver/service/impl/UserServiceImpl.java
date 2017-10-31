package com.manji.messageserver.service.impl;

import com.manji.messageserver.requestParam.*;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.UserService;
import java.io.UnsupportedEncodingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.requestParam.BatchRegisterRequestParam;
import com.manji.messageserver.requestParam.RegisterRequestParam;
import com.manji.messageserver.requestParam.modifyUserNicknameRequestParam;
import com.manji.messageserver.requestParam.modifyUserPasswordRequestParam;
import com.manji.messageserver.responseResult.ObjectBaseResult;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.utils.HttpJsonTool;
import com.manji.messageserver.utils.StringUtils;
import com.manji.messageserver.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by pudding on 2016-12-14.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService commonService;

    @Override
    public BaseResult registerIM(RegisterRequestParam requestParam) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", requestParam.getUserName());
        jsonObject.addProperty("password", requestParam.getPassword());
        String token = commonService.getToken(hxConfig);
        String result = HttpJsonTool.sendHttpJsonPost(hxConfig.getRegisterUrl(), jsonObject, token);
        if (StringUtils.isNotEmpty(result)) {
            Gson gson = new Gson();
            UserVO userVO = gson.fromJson(result, UserVO.class);
            logger.info("https Request  -单一注册环信 user={}", userVO);
            return BaseResult.getSuccessResult("注册成功");
        }else{
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    @Override
    public BaseResult registerBatch(List<BatchRegisterRequestParam> requestParams) {
        Gson gson = new Gson();
        String json = gson.toJson(requestParams);
        String token = commonService.getToken(hxConfig);
        String result = HttpJsonTool.sendHttpJsonPost(hxConfig.getRegisterUrl(), json, token);
        if(StringUtils.isNotEmpty(result)){
            UserVO userVO = gson.fromJson(result, UserVO.class);
            logger.info("https Request  -批量注册环信 user={}", userVO);
            return BaseResult.getSuccessResult("批量注册成功");
        }else{
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    @Override
    public ObjectBaseResult<UserVO> queryUser(String userName) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getQueryUserUrl() + userName;
        String result = HttpJsonTool.sendHttpJsonGet(url, token);
        if(StringUtils.isNotEmpty(result)){
            Gson gson = new Gson();
            UserVO userVO = gson.fromJson(result, UserVO.class);
            logger.info("https Request  -用户查询 user={}", userVO);
            return ObjectBaseResult.successResult(userVO);
        }else{
            return  ObjectBaseResult.failResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(),ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    @Override
    public BaseResult modifyUserNickname(modifyUserNicknameRequestParam modifyUserNicknameRequestParam) throws UnsupportedEncodingException {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getModifyNicknameUrl() + modifyUserNicknameRequestParam.getUserName();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nickname", modifyUserNicknameRequestParam.getNickname());
        String result = HttpJsonTool.sendHttpJsonPut(url, jsonObject, token);
        if (StringUtils.isNotEmpty(result)) {
            Gson gson = new Gson();
            UserVO userVO = gson.fromJson(result, UserVO.class);
            logger.info("https Request  -用户昵称修改 user={}", userVO);
            return BaseResult.getSuccessResult("修改昵称成功");
        } else {
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    /**
     * 查询所有的用户(获取的第一页的用户)
     *
     * @return
     */
    @Override
    public BaseResult queryAllUsers(QueryAllUsersRequestParam requestParam) {
        String url;
        //1.获取token
        String token = commonService.getToken(hxConfig);
        //2.获取远程地址
        //根据cursor是否有值判断是否分页
        if(requestParam.getCursor() == "" || null == requestParam.getCursor()){
            url = hxConfig.getQueryAllUserUrl();
        }else{
            url = hxConfig.getQueryAllUserUrl()+"&cursor="+requestParam.getCursor();
        }
        //3.发送请求
        String result = HttpJsonTool.sendHttpJsonGet(url , token);
        if(StringUtils.isNotEmpty(result)){
            //4.处理返回的数据
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            if(userVo == null){
                return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
            }else if(userVo.getCount() == 0){
                return BaseResult.getFailResult(ErrorCodeEnum.DATA_NOT_EXIST.getCode(), "", ErrorCodeEnum.DATA_NOT_EXIST.getMessage());
            }else{
                logger.info("https Request  -所有用户查询 user={}", userVo);
                return ObjectBaseResult.successResult(userVo);
            }
        }else{
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    /**
     * 修改密码
     *
     * @return
     */
    @Override
    public BaseResult modifyUserPassword(modifyUserPasswordRequestParam requestParam) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getModifyUserPasswordUrl() + requestParam.getUserName() + "/password";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newpassword", requestParam.getNewPassword());
        String result = HttpJsonTool.sendHttpJsonPut(url, jsonObject, token);
        if (StringUtils.isNotEmpty(result)) {
            logger.info("https Request  -用户密码查询 result={}", result);
            return BaseResult.getSuccessResult("密码修改成功");
        } else {
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    /**
     * 禁用某一个用户
     * @param requestParam
     * @return
     */
    @Override
    public BaseResult deactivateUser(deactivateUserRequestParam requestParam){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username",requestParam.getUserName());
        //1.获取token
        String token = commonService.getToken(hxConfig);
        //2.获取url
        String url = hxConfig.getQueryUserUrl()+requestParam.getUserName()+"/deactivate";//禁用某个用户的URL
        //3.发送请求
        String result = HttpJsonTool.sendHttpJsonPost(url, jsonObject, token);
        //4.处理返回的数据
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            logger.info("https Request  -禁用用户 user={}", userVo);
            return BaseResult.getSuccessResult("禁用成功");
        }
    }
    /**
     * 解禁某个用户
     * @param requestParam
     * @return
     */
    @Override
    public BaseResult activateUser(deactivateUserRequestParam requestParam){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username",requestParam.getUserName());
        //1.获取token
        String token = commonService.getToken(hxConfig);
        //2.获取url
        String url = hxConfig.getQueryUserUrl()+requestParam.getUserName()+"/activate";//解禁某个用户的URL
        //3.发送请求
        String result = HttpJsonTool.sendHttpJsonPost(url, jsonObject, token);
        //4.处理返回的数据
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            logger.info("https Request  -解禁用户 user={}", userVo);
            return BaseResult.getSuccessResult("解禁成功");
        }
    }
    /**
     * 查看用户的状态，在线、离线
     * @param requestParam
     * @return
     */
    @Override
    public BaseResult getUserStatus(getUserStatusRequestParam requestParam){
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getGetUserStatusUrl()+requestParam.getUserName()+"/status";
        String result = HttpJsonTool.sendHttpJsonGet(url , token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            logger.info("https Request  -查询用户状态 user={}", userVo);
            return ObjectBaseResult.successResult(userVo);
        }
    }
    /**
     * 删除单一用户
     * @param requestParam
     * @return
     */
    @Override
    public BaseResult deleteUser(deleteUserRequestParam requestParam){
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getDeleteUserUrl()+requestParam.getUserName();
        String result = HttpJsonTool.sendHttpJsonDelete(url , token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            logger.info("https Request  - 删除单一用户 user={}", userVo);
            ObjectBaseResult objectBaseResult = new ObjectBaseResult();
            objectBaseResult.setObj(userVo);
            objectBaseResult.setSuccessResult("删除单一用户成功");
            return objectBaseResult;
        }
    }
    /**
     * 批量删除用户
     * @param requestParam
     * @return
     */
    public BaseResult deleteUserBatch(deleteUserBatchRequestParam requestParam){
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getDeleteUserBatchUrl()+"?limit="+requestParam.getLimit();
        String result = HttpJsonTool.sendHttpJsonDelete(url , token);
        if(StringUtils.isEmpty(result)){
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }else{
            Gson gson = new Gson();
            UserVO userVo = gson.fromJson(result , UserVO.class);
            logger.info("https Request  -批量删除用户 user={}", userVo);
            ObjectBaseResult objectBaseResult=new ObjectBaseResult();
            objectBaseResult.setObj(userVo);
            objectBaseResult.setSuccessResult("批量删除成功");
            return objectBaseResult;
        }
    }
}
