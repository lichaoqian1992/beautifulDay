package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-1-16.
 */
@Data
public class UserDO {

    private int id;
    /***
     * 登录账户
     */
    private String userName;
    private String roleName;
    /***
     * 登录密码
     */
    private String password;
    /***
     * 真实姓名
     */
    private String realName;
    /***
     * 手机号码
     */
    private String mobile;
    /***
     * 电子邮箱
     */
    private String email;
    /***
     * 登录时间
     */
    private Date loginTime;
    /***
     * 创建时间
     */
    private Date createTime;
    /***
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 账户角色类型
     */
    private Integer config_id;
}
