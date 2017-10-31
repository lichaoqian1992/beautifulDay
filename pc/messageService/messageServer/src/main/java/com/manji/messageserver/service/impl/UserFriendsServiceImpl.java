package com.manji.messageserver.service.impl;

import com.google.gson.*;
import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.requestParam.AddFriendsRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.responseResult.ObjectBaseResult;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.service.UserFriendsService;
import com.manji.messageserver.utils.HttpJsonTool;
import com.manji.messageserver.utils.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pudding on 2016-12-14.
 */
@Service
public class UserFriendsServiceImpl implements UserFriendsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserFriendsService.class);
    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService commonService;

    @Override
    public BaseResult add(AddFriendsRequestParam requestParam) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getAddFriendsUrl().replace("$", requestParam.getOwnerUserName()) + requestParam.getFriendUserName();
        String result = HttpJsonTool.sendHttpJsonPost(url, token);
        if (StringUtils.isEmpty(result)) {
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        } else {
            logger.info("https Request  -用户添加好友 result={}", result);
            return BaseResult.getSuccessResult("用户添加好友成功");
        }
    }
    @Override
    public BaseResult del(AddFriendsRequestParam requestParam) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getAddFriendsUrl().replace("$", requestParam.getOwnerUserName()) + requestParam.getFriendUserName();
        String result = HttpJsonTool.sendHttpJsonDelete(url, token);
        if (StringUtils.isNotEmpty(result)) {
            logger.info("https Request  -用户删除好友 result={}", result);
            return BaseResult.getSuccessResult("用户删除好友成功");
        } else {
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
    @Override
    public BaseResult getUserFriend(String ownerUserName) {
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getAddFriendsUrl().replace("$", ownerUserName);
        String result = HttpJsonTool.sendHttpJsonGet(url, token);
        if (StringUtils.isNotEmpty(result)) {
            logger.info("https Request  -用户查看好友 result={}", result);
            List<String> list = new ArrayList<String>();
            Iterator<JsonElement> it = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("data").iterator();
            while (it.hasNext()) {
                list.add(it.next().getAsString());
            }
            return ObjectBaseResult.successResult(list);
        } else {
            return BaseResult.getFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(), "", ErrorCodeEnum.INVALID_ARGUMENTS.getMessage());
        }
    }
}
