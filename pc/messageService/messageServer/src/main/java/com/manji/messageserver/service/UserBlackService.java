package com.manji.messageserver.service;

import com.manji.messageserver.requestParam.addUserBlackRequestParam;
import com.manji.messageserver.requestParam.queryUserBlackRequestParam;
import com.manji.messageserver.requestParam.removeBlackUserRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
/**
 * Created by Administrator on 2016/12/14.
 */
public interface UserBlackService {
    /**
     * 查询用户黑名单
     * @param requestParam
     * @return
     */
    public BaseResult queryUserBlack(queryUserBlackRequestParam requestParam);

    public BaseResult addUserBlack(addUserBlackRequestParam requestParam,String userName);

    public BaseResult removeUserBlack(removeBlackUserRequestParam requestParam);
}
