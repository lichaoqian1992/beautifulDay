package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * 调用接口时传的参数
 * Created by Administrator on 2017/3/8.
 */
@Data
public class HttpRequestParam {
    /**访问接口授权码*/
    private String accessKey;

    /**业务处理单号*/
    private String tranSN;

    /**时间戳*/
    private String nonceStr;

    /**返回的字符串数据*/
    private String rawStr;

    /**请求的接口方法名称*/
    private String action;

    /**签名字符串*/
    private String sign;

    /**充值的类型 6可提现 4不可提现*/
    private String withdraw;

    /**业待充值用户id*/
    private int userId;

    /**待充值用户角色类型*/
    private String roleType;

    /**待充值用户角色值*/
    private int roleValue;

    /**充值金额*/
    private double money;

    /**tranSN业务处理单号*/
    private String rechargeTranSN;
}
