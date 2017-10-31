package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by pudding on 2017-3-14.
 */
@Repository
public class OrdersDoRepository {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过用户id查询当日订单条数
     * @param userWithdrawals
     * @return
     */
    public int getCountByUser(UserWithdrawalsDO userWithdrawals){
        String sql="select COUNT(*) from dt_Orders where user_id = ? and day(add_time)=day(?) ";
        int count=jdbcTemplate.queryForObject(sql,new Object[]{userWithdrawals.getUserId(),userWithdrawals.getAdd_time()},int.class);
        return count;
    }

    /**
     * 通过用户id查询此用户订单条数
     * @param userid
     * @return
     */
    public int getOSumByUser(int  userid){
        String sql="select COUNT(*) from dt_Orders where user_id = ?";
        int count=jdbcTemplate.queryForObject(sql,new Object[]{userid},int.class);
        return count;
    }

    /**
     * 通过用户id查询消费总金额
     * @param userid
     * @return
     */
    public double getOdersSumByUser(int userid){
        String sql="select SUM(order_amount) from dt_orders where user_id=?";
        double sumAmount=jdbcTemplate.queryForObject(sql,new Object[]{userid},double.class);
        return sumAmount;
    }
}
