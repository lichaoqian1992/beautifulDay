package com.manji.financesystem.secondaryDomain.entity.extra;

import lombok.Data;

/**
 * 接口返回的参数
 * Created by Administrator on 2017/3/8.
 */
@Data
public class HttpResponseDO {
    /**错误代码*/
    private String errCode;
    /**错误消息*/
    private String message;
    /**请求接口名*/
    private String action;
    /**返回的业务单号*/
    private String querySN;
    /**发送的业务处理单号*/
    private String tranSN;
    /**返回代码*/
    private String resultCode;
    /**返回数据*/
    private String resultData;

    private String rawStr;

}
