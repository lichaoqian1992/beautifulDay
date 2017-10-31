package com.manji.financesystem.service;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;

/**
 * Created by pudding on 2017-2-4.
 */
public interface UserNormalRechargeService {

    /***
     * 统计当日当月从中总金额
     */
    public BaseResult statisticsSumAmount();

    public String excel(UserQueryNormalRechargeListParam param);

    public String recharge(int oaNO,String excelPath, String excelNo, String rechargeType, String excelName , String personRelease);


}
