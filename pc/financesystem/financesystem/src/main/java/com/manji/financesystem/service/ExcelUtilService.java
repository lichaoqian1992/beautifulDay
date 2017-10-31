package com.manji.financesystem.service;

import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.ShopInfoParam;
import com.manji.financesystem.requestParam.WithDrawalsParam;

import java.text.ParseException;

/**
 * Created by Administrator on 2017/2/25.
 */
public interface ExcelUtilService {

    /**导出的是正常充值记录的数据*/
    public String excelInside(InteriorRechargeRequestParam param);

    /**导出的是提现记录的数据*/
    public String excelWithDraw(WithDrawalsParam param);

    /**导出的是订单信息的数据*/
    public String excelOrders(ShopInfoParam param);

    /**导出待处理提现明细**/
    public String excelPendingprocessing();

    /**导出的是当日或者当月的收支交易明细*/
    public String excelDayOrMonthDetail(String startTime,String excelType) throws ParseException;
}
