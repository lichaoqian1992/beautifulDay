package com.manji.financesystem.service;

import com.manji.financesystem.responseData.DailySheetResult;
import com.manji.financesystem.responseData.MonthAndDailySheetResult;
import com.manji.financesystem.responseData.MonthSheetResult;

import java.text.ParseException;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface MonthAndDailySheetService {

    public DailySheetResult getAccountInfoResult(String startTime) throws ParseException;

    public MonthSheetResult getMonthSheetResult(String startTime) throws ParseException;
}
