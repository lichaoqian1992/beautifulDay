package com.manji.financesystem.service.impl;

import com.manji.financesystem.primaryDomain.entiity.MonthAndDailySheetExpenditrueDO;
import com.manji.financesystem.primaryDomain.entiity.MonthAndDailySheetIncomeDO;
import com.manji.financesystem.primaryDomain.repository.DailySheetRepository;
import com.manji.financesystem.responseData.DailySheetResult;
import com.manji.financesystem.responseData.MonthAndDailySheetResult;
import com.manji.financesystem.responseData.MonthSheetResult;
import com.manji.financesystem.service.MonthAndDailySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by Administrator on 2017/1/18.
 */
@Service
public class MonthAndDailySheetServiceImpl implements MonthAndDailySheetService {

    @Autowired
    private DailySheetRepository dailySheetRepository;

    /**
     * 日报表
     * @param startTime
     * @return
     * @throws ParseException
     */
    @Override
    public DailySheetResult getAccountInfoResult(String startTime) throws ParseException {
        DailySheetResult accountInfoResult = new DailySheetResult();
        MonthAndDailySheetIncomeDO dailySheetDO;
        MonthAndDailySheetExpenditrueDO monthAndDaily;
        //收入总金额
        dailySheetDO = dailySheetRepository.getDailySheetIncome(startTime);
        //支出总金额
        monthAndDaily = dailySheetRepository.getDailySheetExpenditrue(startTime);
        System.out.println("金额："+dailySheetDO.getIncomeYesterdayAmount()+"昨天支出;"+monthAndDaily.getExpenditureYesterdayAmount());
        accountInfoResult.setBeginAmount(dailySheetDO.getIncomeYesterdayAmount()-monthAndDaily.getExpenditureYesterdayAmount());
        accountInfoResult.setEndAmount(dailySheetDO.getIncomeYesterdayAmount()-monthAndDaily.getExpenditureYesterdayAmount()+dailySheetDO.getIncomeAmount()-monthAndDaily.getExpenditureAmount());
        accountInfoResult.setIncomeAmount(dailySheetDO.getIncomeAmount());
        accountInfoResult.setExpenditureAmount(monthAndDaily.getExpenditureAmount());
        return accountInfoResult;
    }

    /**
     * 月报表
     * @param startTime
     * @return
     * @throws ParseException
     */
    @Override
    public MonthSheetResult getMonthSheetResult(String startTime) throws ParseException {
        MonthSheetResult accountInfoResult = new MonthSheetResult();
        MonthAndDailySheetIncomeDO dailySheetDO;
        MonthAndDailySheetExpenditrueDO monthAndDaily;
        //收入总金额
        dailySheetDO = dailySheetRepository.getMonthSheetIncome(startTime);
        //支出总金额
        monthAndDaily = dailySheetRepository.getMonthSheetExpenditrue(startTime);

        accountInfoResult.setBeginMonthAmount(dailySheetDO.getIncomeYesterdayAmount()-monthAndDaily.getExpenditureYesterdayAmount());
        accountInfoResult.setEndMonthAmount(dailySheetDO.getIncomeYesterdayAmount()-monthAndDaily.getExpenditureYesterdayAmount()+dailySheetDO.getIncomeAmount()-monthAndDaily.getExpenditureAmount());
        accountInfoResult.setIncomeMonthAmount(dailySheetDO.getIncomeAmount());
        accountInfoResult.setExpenditureMonthAmount(monthAndDaily.getExpenditureAmount());
        return accountInfoResult;
    }
}
