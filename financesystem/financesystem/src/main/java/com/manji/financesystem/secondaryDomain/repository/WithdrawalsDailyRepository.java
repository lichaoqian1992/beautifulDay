package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.secondaryDomain.entity.WithdrawalsDailyDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by pudding on 2017-3-11.
 */

@Repository
public class WithdrawalsDailyRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void insertWithdrawalsDaily(WithdrawalsDailyDo withdrawalsDailyDo){
        String sql="INSERT INTO t_daily_cash_register VALUES(?,?,?,?);";
        jdbcTemplate.update(sql,new Object[]{withdrawalsDailyDo.getWithdrawalsNo(),withdrawalsDailyDo.getUserId(),withdrawalsDailyDo.getCareteTime(),withdrawalsDailyDo.getTotalMoney()});
    }

    


}
