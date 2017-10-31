package com.manji.financesystem.secondaryDomain.entity.extra;

import lombok.Data;

/**
 * 页面权限DO
 * Created by Administrator on 2017/3/11.
 */
@Data
public class UserAndRoleDO {
    /**页面链接*/
    private String pageUrl;
    /**页面描述*/
    private String pageDescription;
    /**权限编号*/
    private int roleId;
    /**权限名称*/
    private String roleName;
    /**权限描述*/
    private String desciption;
    /**创建时间*/
    private String createtime;
}
