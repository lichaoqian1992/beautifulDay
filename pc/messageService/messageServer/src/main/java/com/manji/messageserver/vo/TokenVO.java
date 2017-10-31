package com.manji.messageserver.vo;

import lombok.Data;
/**
 * Created by pudding on 2016-12-13.
 * 授权token值对象
 */
@Data
public class TokenVO {
    /***token 值*/
    private String access_token;
    /***token 有效时间，以秒为单位，在有效期内不需要重复获取*/
    private String expires_in;
    /***当前 应用 的 UUID 值*/
    private String application;
}
