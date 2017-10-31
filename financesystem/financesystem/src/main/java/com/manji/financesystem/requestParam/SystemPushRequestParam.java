package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/5.
 */
@Data
public class SystemPushRequestParam {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 用户角色类型
     */
    private String userRoleType;
    /**
     * 用户角色值
     */
    private String userRoleValue;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 批量删除用
     */
    private String  userIdList;
}
