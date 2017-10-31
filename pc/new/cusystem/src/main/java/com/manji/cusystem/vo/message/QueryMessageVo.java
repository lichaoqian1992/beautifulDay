package com.manji.cusystem.vo.message;

import com.manji.cusystem.vo.common.PageVo;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/11.
 * 查询短信的参数
 */
@Data
public class    QueryMessageVo extends PageVo{

    private String cusStatus;//发送状态   0未发送  1已发送
    private String cusType;//短信类型 1：验证码 2：广告营销
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String cusContent;//短信内容
    private String cusKind;//短信还是邮件
}
