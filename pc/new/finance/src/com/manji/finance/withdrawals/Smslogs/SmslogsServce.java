package com.manji.finance.withdrawals.Smslogs;

import com.manji.finance.system.UserSmsLogDO;
import com.manji.finance.utils.SmsLogsUtils;
import com.manji.finance.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-4-21.(发送短信)
 */
public class SmslogsServce {

    /**
     * 推送短信给用户
     * @param UserId
     * @param UserRoleType
     * @param UserRoleValue
     * @param UserIp
     * @param Type
     * @param content
     * @param Status
     * @param Mobile
     * @return
     */
    public int sendMessage(int UserId,String UserRoleType,int UserRoleValue,String UserIp,String Type,String content,int Status,String Mobile){

        List<UserSmsLogDO> list=new ArrayList<UserSmsLogDO>();

        UserSmsLogDO UserSmsLog=new UserSmsLogDO();

        UserSmsLog.setAddTime(TimeUtils.getTimeUtilSecond());
        UserSmsLog.setSendTime(TimeUtils.getTimeUtilSecond());
        UserSmsLog.setUserId(UserId);
        UserSmsLog.setUserRoleType(UserRoleType);
        UserSmsLog.setUserRoleValue(UserRoleValue);
        UserSmsLog.setUserIp(UserIp);
        UserSmsLog.setType(Type);
        UserSmsLog.setContent(content);
        UserSmsLog.setStatus(Status);
        UserSmsLog.setMobile(Mobile);

        list.add(UserSmsLog);

        return SmsLogsUtils.sendMessage(list);
    }



}
