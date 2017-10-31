package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/22.
 */
@Data
public class SatisfactionVoucherRequestParam {

    /**用户ID*/
    private String status;

    /**用户名称*/
    private String userName;

    /**用户类型*/
    private String roleType;

    /**当前页*/
    private int pageNumber;
}
