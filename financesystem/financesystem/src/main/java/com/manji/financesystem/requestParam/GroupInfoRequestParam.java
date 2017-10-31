package com.manji.financesystem.requestParam;

import lombok.Data;


/**
 * 集团账户信息查询参数
 * Created by Administrator on 2017/3/3.
 */
@Data
public class GroupInfoRequestParam {
    /**集团名称*/
    private String groupName;
    /**管理类型*/
    private String managerType;
    /**集团状态*/
    private String groupStatus;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;

    private int pageNum;
}
