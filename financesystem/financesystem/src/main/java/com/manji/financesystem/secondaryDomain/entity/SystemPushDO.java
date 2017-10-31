package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/5.
 */
@Data
public class SystemPushDO {

    /**
     * 用户ID
     */
    private int userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色值
     */
    private int roleValue;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;
}
