package com.manji.financesystem.common;

import lombok.Data;

/**
 * 用于密集充值部分使用
 * Created by Administrator on 2017/2/20.
 */
@Data
public class StringDO {
    /**
     * 状态码
     */
    private String status;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 描述
     */
    private String description;
}
