package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-3-14.
 */
@Data
public class UserLoginLogDO {

    private int id ;

    private int user_id;

    private String remark;

    private Date login_time;

    private  String login_ip;

    private String login_from;

}
