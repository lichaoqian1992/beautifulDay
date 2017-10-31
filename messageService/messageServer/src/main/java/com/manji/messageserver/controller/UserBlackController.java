package com.manji.messageserver.controller;

import com.manji.messageserver.requestParam.addUserBlackRequestParam;
import com.manji.messageserver.requestParam.queryUserBlackRequestParam;
import com.manji.messageserver.requestParam.removeBlackUserRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.UserBlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/12/14.
 */
@RequestMapping("/userBlack")
@RestController
public class UserBlackController {
    @Autowired
    private UserBlackService userBlackService;

    /**
     * 查询用户的黑名单
     * @param requestParam
     * @return
     */
    @RequestMapping("/queryUserBlack")
    public BaseResult queryUserBlack(queryUserBlackRequestParam requestParam){
        return userBlackService.queryUserBlack(requestParam);
    }

    /**
     * 用户添加黑名单,传递的参数是一个数组，数组里面是人员信息
     * @return
     */
    @RequestMapping(value = "/addUserBlack/{userName}",method = RequestMethod.POST)
    public BaseResult addUserBlack(@RequestBody addUserBlackRequestParam requestParam, @PathVariable String userName){
        return userBlackService.addUserBlack(requestParam,userName);
    }

    /**
     * 删除用户的黑名单
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/removeUserBlack" ,method = RequestMethod.DELETE)
    public BaseResult removeUserBlack(removeBlackUserRequestParam requestParam){
        return userBlackService.removeUserBlack(requestParam);
    }
}
