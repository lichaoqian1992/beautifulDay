package com.manji.messageserver.controller;

import com.manji.messageserver.requestParam.*;
import com.manji.messageserver.requestParam.modifyUserPasswordRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by pudding on 2016-12-13.
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册IM单一用户
     *
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResult registerIM(RegisterRequestParam requestParam) {
        return userService.registerIM(requestParam);
    }

    /**
     * 注册IM-批量
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/registerBatch", method = RequestMethod.POST)
    public BaseResult registerBatch(@RequestBody List<BatchRegisterRequestParam> params) {
        return userService.registerBatch(params);
    }

    /**
     * 查询单个用户
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/queryUser/{userName}")
    public BaseResult queryUser(@PathVariable String userName) {
        return userService.queryUser(userName);
    }

    /**
     * 查询所有的用户
     *
     * @return
     */
    @RequestMapping(value = "/queryAllUsers")
    public BaseResult queryAllUsers(QueryAllUsersRequestParam requestParam) {
        return userService.queryAllUsers(requestParam);
    }

    /**
     * 修改用户昵称
     *
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/modifyUserNickname", method = RequestMethod.PUT)
    public BaseResult modifyUserNickname(modifyUserNicknameRequestParam requestParam) throws UnsupportedEncodingException {
        return userService.modifyUserNickname(requestParam);
    }

    /**
     * 修改密码
     *
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/modifyUserPassword", method = RequestMethod.PUT)
    public BaseResult modifyUserPassword(modifyUserPasswordRequestParam requestParam) {
        return userService.modifyUserPassword(requestParam);
    }
    /**
     * 禁用某个用户
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/deactivateUser",method = RequestMethod.POST)
    public BaseResult deactivateUser(deactivateUserRequestParam requestParam){
        return userService.deactivateUser(requestParam);
    }

    /**
     * 解禁某个用户
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/activateUser",method = RequestMethod.POST)
    public BaseResult activateUser(deactivateUserRequestParam requestParam){
        return userService.activateUser(requestParam);
    }

    /**
     * 得到用户的状态，在线、离线
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/getUserStatus")
    public BaseResult getUserStatus(getUserStatusRequestParam requestParam){
        return userService.getUserStatus(requestParam);
    }

    /**
     * 删除单一用户
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public BaseResult deleteUser(deleteUserRequestParam requestParam){
        return userService.deleteUser(requestParam);
    }

    /**
     * 批量删除用户
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/deleteUserBatch",method = RequestMethod.DELETE)
    public BaseResult deleteUserBatch(deleteUserBatchRequestParam requestParam){
        return userService.deleteUserBatch(requestParam);
    }
}
