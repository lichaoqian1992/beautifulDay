package com.manji.financesystem.service;

import com.manji.financesystem.requestParam.ExamRechargeParam;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface UserInsideRechargeService {

    //审批订单
    public String approvalOrder(ExamRechargeParam param);

    public String examByAccountor(ExamRechargeParam param);
}
