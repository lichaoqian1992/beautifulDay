package com.manji.financesystem.secondaryDomain.entity.extra;

import lombok.Data;

/**
 * 用户的基本信息和权限、职位信息
 * Created by Administrator on 2017/3/13.
 */
@Data
public class UserAndRoleInfoDO {
    /**用户编号*/
    private int UserId;
    /**用户账号*/
    private String userName;
    /**职位编号*/
    private int configId;
    /**职位名称*/
    private String configName;
    /**权限编号*/
    private String roleId;
    /**权限名称*/
    private String roleName;
    /**权限描述*/
    private String roleDescription;
}
