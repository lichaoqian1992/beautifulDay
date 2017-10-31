package com.manji.messageserver.service;

import com.manji.messageserver.requestParam.*;
import com.manji.messageserver.responseResult.BaseResult;
import java.io.UnsupportedEncodingException;
import java.util.List;
/**
 * Created by pudding on 2016-12-13.
 */
public interface UserService {
    /**
     * 单个注册IM用户
     * @return
     */
    public BaseResult registerIM(RegisterRequestParam requestParam);

    public BaseResult registerBatch(List<BatchRegisterRequestParam> requestParams);

    public BaseResult queryUser(String userName);

    public BaseResult modifyUserNickname(modifyUserNicknameRequestParam modifyUserNicknameRequestParam) throws UnsupportedEncodingException;
    /**
     * 查询所有的用户（默认一页20条）
     * @return
     */
    public BaseResult queryAllUsers(QueryAllUsersRequestParam requestParam);
    /**
     * 修改IM密码
     * @param requestParam
     * @return
     */
    public BaseResult modifyUserPassword(modifyUserPasswordRequestParam requestParam);
    /**
     * 禁用某一个用户
     * @param requestParam
     * @return
     */
    public BaseResult deactivateUser(deactivateUserRequestParam requestParam);
    /**
     * 解禁某个用户
     * @param requestParam
     * @return
     */
    public BaseResult activateUser(deactivateUserRequestParam requestParam);
    /**
     * 查看用户的状态，在线、离线
     * @param requestParam
     * @return
     */
    public BaseResult getUserStatus(getUserStatusRequestParam requestParam);
    /**
     * 删除单一用户
     * @param requestParam
     * @return
     */
    public BaseResult deleteUser(deleteUserRequestParam requestParam);
    /**
     * 批量删除用户
     * @param requestParam
     * @return
     */
    public BaseResult deleteUserBatch(deleteUserBatchRequestParam requestParam);
}
