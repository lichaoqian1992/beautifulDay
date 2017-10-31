package com.manji.financesystem.service.impl;

import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.primaryDomain.repository.DailySheetRepository;
import com.manji.financesystem.responseData.DailySheetResult;
import com.manji.financesystem.responseData.MessageSendResult;
import com.manji.financesystem.responseData.OrdersResult;
import com.manji.financesystem.service.MonthAndDailySheetService;
import com.manji.financesystem.service.SystemPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/5.
 */
@Service
public class SystemPushServiceImpl implements SystemPushService{

    @Autowired
    private DailySheetRepository dailySheetRepository;

    @Autowired
    private MonthAndDailySheetService monthAndDailySheetService;

    @Override
    public String getMessage() throws ParseException {
        Date date = new Date();
        String  myDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String message = "";
        //查询到的是每天的交易流水金额和支出以及结存
        DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(myDate);
        OrdersResult ordersDo1 = dailySheetRepository.getAllOrdersMoney(myDate);
        OrdersResult ordersDo2 = dailySheetRepository.getSuccessOrdersMoney(myDate);
        double jiecunMoney = monthAndDailySheet.getIncomeAmount() - monthAndDailySheet.getExpenditureAmount();
        message = "本日产生平台交易流水量"+monthAndDailySheet.getIncomeAmount()+"元，支出"+monthAndDailySheet.getExpenditureAmount()+"元," +
                "结存"+jiecunMoney+"元。本日平台订单交易"+ordersDo1.getAmountOrders()+"笔，总金额"+ordersDo1.getAmountMoney()+"元，其中成功订单"+ordersDo2.getSuccessOrders()+"笔，金额"+ordersDo2.getSuccessMoney()+"元";
        //把支出和结存金额，以及订单的相关消息存放到一个对象里面，返回消息到前端
        //向数据库插入两条数据即可
        return message;
    }
}

