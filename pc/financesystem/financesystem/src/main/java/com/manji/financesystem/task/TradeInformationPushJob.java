package com.manji.financesystem.task;

import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.primaryDomain.repository.DailySheetRepository;
import com.manji.financesystem.responseData.DailySheetResult;
import com.manji.financesystem.responseData.MessageSendResult;
import com.manji.financesystem.responseData.OrdersResult;
import com.manji.financesystem.service.MonthAndDailySheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-2-3.
 */
@Component
public class TradeInformationPushJob {

    @Autowired
    private MonthAndDailySheetService monthAndDailySheetService;

    @Autowired
    private DailySheetRepository dailySheetRepository;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(TradeInformationPushJob.class);


    /**
     * 交易信息的定时任务
     */
    @Scheduled(cron = "0 02 10 * * ?")
    public void job() {
        try {
            logger.info("定时任务:交易信息汇总消息推送 time={}", dateFormat.format(new Date()));
            Date date= new Date();
            InetAddress id = null;
            String message = "";
            String myDate = "2017-01-22";
            ObjectBaseResult<MessageSendResult> baseQueryResult = new ObjectBaseResult<MessageSendResult>();
            //查询到的是每天的交易流水金额和支出以及结存
            MessageSendResult messageSendResult = new MessageSendResult();
            DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(myDate);
            OrdersResult ordersDo1 = dailySheetRepository.getAllOrdersMoney(myDate);
            OrdersResult ordersDo2 = dailySheetRepository.getSuccessOrdersMoney(myDate);
            double jiecunMoney = monthAndDailySheet.getIncomeAmount() - monthAndDailySheet.getExpenditureAmount();
            message = "本日产生平台交易流水量" + monthAndDailySheet.getIncomeAmount() + "元，支出" + monthAndDailySheet.getExpenditureAmount() + "元," +
                    "结存" + jiecunMoney + "元。本日平台订单交易" + ordersDo1.getAmountOrders() + "笔，总金额" + ordersDo1.getAmountMoney() + "元，其中成功订单" + ordersDo2.getSuccessOrders() + "笔，金额" + ordersDo2.getSuccessMoney() + "元";
            //把支出和结存金额，以及订单的相关消息存放到一个对象里面，返回消息到前端

            System.out.println("推送消息："+message);

        } catch (Exception e) {
            logger.info("定时任务:error time={} errorMsg={}", dateFormat.format(new Date()), e.getMessage());
        } finally {
            logger.info("定时任务结束time={}", dateFormat.format(new Date()));
        }


    }
}
