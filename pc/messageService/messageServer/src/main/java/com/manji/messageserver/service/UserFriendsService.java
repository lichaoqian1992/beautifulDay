package com.manji.messageserver.service;

import com.manji.messageserver.requestParam.AddFriendsRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
/**
 * Created by pudding on 2016-12-14.
 */
public interface UserFriendsService {

    public BaseResult add(AddFriendsRequestParam requestParam);

    public BaseResult del(AddFriendsRequestParam addFriendsRequestParam);

    public BaseResult getUserFriend(String ownerUserName);

}
