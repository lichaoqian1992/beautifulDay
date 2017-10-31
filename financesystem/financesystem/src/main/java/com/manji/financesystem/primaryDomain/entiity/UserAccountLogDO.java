package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * Created by pudding on 2017-3-17.
 */
//用户余额日志表
@Data
public class UserAccountLogDO {

    private int id;

    private int user_id;

    private String user_role_type;

    private int user_role_value;

    private int account_id;

    private Double value;

    private String order_no;

    private String type;

    private String remark;

    private String add_time;

    private Double old_value;

    private Double new_value;

    private String user_ip;

    private String transaction_no;


}
