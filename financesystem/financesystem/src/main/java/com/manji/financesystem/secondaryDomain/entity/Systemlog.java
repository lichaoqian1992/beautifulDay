package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

/**
 * Created by pudding on 2017-3-3.
 */

@Data
public class Systemlog {

    private int id;
    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户账号名称
     */
    private String user_name;

    /**
     * 日志信息
     */
    private String log_info;

    /**
     * 记录时间
     */
    private String Create_time;


}
