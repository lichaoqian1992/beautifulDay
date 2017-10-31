package com.manji.cusystem.vo.conversation;

import com.manji.cusystem.vo.common.PageVo;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/6.
 */
@Data
public class OrderAndConVo extends PageVo{

    private String stime;

    private String etime;

    private String keyword;//手机号、账号、商家名称
}
