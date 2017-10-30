package com.manji.finance.system;

import com.manji.finance.utils.TimeUtils;
import com.mysql.jdbc.TimeUtil;

/**
 * Created by pudding on 2017-4-21.（Http日志）
 */
public class httplogService {

    httplogRepository httplogRepository=new httplogRepository();

    public int addHttplog(String Action,String QuerySN,String TranSN,String ResultData){
        HttplogDO logs=new HttplogDO();
        String Createdate= TimeUtils.getTimeUtilSecond();
        logs.setAction(Action);
        logs.setQuerySN(QuerySN);
        logs.setTranSN(TranSN);
        logs.setResultData(ResultData);
        logs.setCreatedate(Createdate);
       return httplogRepository.addHttplog(logs);
    }
}
