package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.UserRechargeDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-3-13.
 */
/**
 * 判定是否异常提现订单
 */
@Repository
public class AbnormalwithdrawalRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据提现记录时间查询出此用户当天的充值记录
     * @return
     */
    public List<UserRechargeDO> findAbnormalwithdrawalforUserRechargeDO(UserWithdrawalsDO userWithdrawals){
        final List<UserRechargeDO> li=new ArrayList<UserRechargeDO>();
        String sql="SELECT * from dt_user_recharge where user_id=? and  day(add_time)=day(?)";
        jdbcTemplate.query(sql,new Object[]{userWithdrawals.getUserId(),userWithdrawals.getAdd_time()},new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserRechargeDO userRecharge=new UserRechargeDO();
                        userRecharge.setAddTime(rs.getString("add_time"));
                        userRecharge.setUserId(rs.getString("user_id"));
                        return li;
                    }
                });
        return li;
    }

    /**
     * 通过用户id查询此用户充值条数
     * @param userid
     * @return
     */
    public int getRSumByUser(int  userid){
        String sql="select COUNT(*) from dt_user_recharge where user_id = ?";
        int count=jdbcTemplate.queryForObject(sql,new Object[]{userid},int.class);
        return count;
    }


    /**
     * 通过用户id查询此用户充值条数
     * @param userid
     * @return
     */
    public int getTSumByUser(int  userid){
        String sql="select COUNT(*) from dt_user_transaction where user_id = ?";
        int count=jdbcTemplate.queryForObject(sql,new Object[]{userid},int.class);
        return count;
    }





}
