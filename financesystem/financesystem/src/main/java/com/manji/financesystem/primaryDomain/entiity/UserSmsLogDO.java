package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/4.
 */
@Data
public class UserSmsLogDO {
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 用户角色类型
     */
    private String userRoleType;
    /**
     * 用户角色值
     */
    private int userRoleValue;
    /**
     * 短信类别
     */
    private String type;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 申请IP
     */
    private String userIp;
    /**
     * 接收手机号
     */
    private String mobile;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 发送状态
     */
    private int status;

}
