package com.manji.messageserver.requestParam;

import lombok.Data;

/**
 * Created by pudding on 2016-12-12.
 */
@Data
public abstract class BaseRequestQueryParam extends BaseRequestParam {

    private static final long	serialVersionUID	= 8547031838770232168L;

    private int					page				= 1;

    private int					pageSize			= 12;

}
