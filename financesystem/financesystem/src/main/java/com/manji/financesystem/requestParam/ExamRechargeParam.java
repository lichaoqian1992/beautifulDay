package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/8.
 */
@Data
public class ExamRechargeParam {
    /**主要是保存前台传过来的充值订单的数组*/
    private String idList;

    /**判断是同意还是拒绝*/
    private String agreeOrRefuse;

    /**充值类型（可提现还是不可提现）*/
    private String rechargeTypeList;

    /**审批意见*/
    private String reason;

    /**充值用户数组集合*/
    private String userIdList;

    /**充值金额的数组集合*/
    private String moneyList;

    /**角色类型集合*/
    private String roleTypeList;

    /**角色值集合*/
    private String roleValueList;

    private String creater;

    private String faburen;
    /**
     * 业务单号
     */
    private String transnList;
    /**
     * 储存是撤销充值还是充值
     */
    private String oprationType;

}
