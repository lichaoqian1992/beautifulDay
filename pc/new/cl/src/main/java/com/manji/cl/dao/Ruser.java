package com.manji.cl.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public class Ruser {

    private int cl_buyer_id;//注册用户id

    private int cl_user_id;//推荐人id(业务员id)

    private String cl_ctime;//推荐时间

    private int cl_isdel;//是否删除
}
