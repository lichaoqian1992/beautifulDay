package com.manji.cl.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/28.
 */
@Data
public class User {

    private int id;//账号id

    private String name;//账号

    private String password;//密码

    private String nickname;//昵称

    private String type;//用户类型


}
