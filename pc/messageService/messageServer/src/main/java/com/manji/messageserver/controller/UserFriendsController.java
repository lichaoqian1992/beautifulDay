package com.manji.messageserver.controller;

import com.manji.messageserver.requestParam.AddFriendsRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pudding on 2016-12-14.
 */
@RequestMapping("/userFriends")
@RestController
public class UserFriendsController {

    @Autowired
    private UserFriendsService userFriendsService;

    /**
     * 添加用户好友
     * @param addFriendsRequestParam
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult add(AddFriendsRequestParam addFriendsRequestParam) {
        return userFriendsService.add(addFriendsRequestParam);

    }

    /**
     * 删除用户好友
     * @param addFriendsRequestParam
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public BaseResult del(AddFriendsRequestParam addFriendsRequestParam){
        return userFriendsService.del(addFriendsRequestParam);
    }

    @RequestMapping(value = "/get/{ownerUserName}",method = RequestMethod.GET)
    public BaseResult getUserFriend(@PathVariable String ownerUserName){
        return userFriendsService.getUserFriend(ownerUserName);
    }


}
