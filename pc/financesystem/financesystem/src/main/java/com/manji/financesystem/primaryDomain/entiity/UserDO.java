package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-3-14.
 */
@Data
public class UserDO {

    private int id;

    private int group_id;

    private String user_name;

    private String salt;

    private String password;

    private String mobile;

    private String email;

    private String avatar;

    private String nick_name;

    private String sex;

    private Date birthday;

    private String telphone;

    private  String area;

    private String address;

    private  String qq;

    private String pay_password;

    private int status;

    private String remark;

    private String reg_time;

    private String reg_ip;

    private String from_value;

    private int is_del;

}
