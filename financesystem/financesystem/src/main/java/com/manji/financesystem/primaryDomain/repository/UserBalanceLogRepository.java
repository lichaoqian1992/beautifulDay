package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.BalanceStatusEnums;
import com.manji.financesystem.enums.IsDeleteEnums;
import com.manji.financesystem.primaryDomain.entiity.UserBalanceLogDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserBalanceInfoDO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-1-17.
 */
@Repository
public class UserBalanceLogRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取技术服务费总额
     *
     * @return
     */
    public UserBalanceInfoDO getUserBalanceInfo() {
        UserBalanceInfoDO userBalanceInfoDO = (UserBalanceInfoDO) jdbcTemplate.queryForObject("select role_type ,sum(balance_amount) amount from dt_user_balance_log group by role_type having role_type='manager'", new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserBalanceInfoDO userBalanceInfoDO = new UserBalanceInfoDO();
                userBalanceInfoDO.setRoleName(resultSet.getString("role_type"));
                userBalanceInfoDO.setAmount(resultSet.getDouble("amount"));
                return userBalanceInfoDO;
            }
        });
        return userBalanceInfoDO;
    }

    /**
     * 查询结算交易明细
     *
     * @param page
     * @return
     */
    public List<UserBalanceLogDO> getBalanceLogList(final int page) {
        final List<UserBalanceLogDO> userBalanceLogList = new ArrayList<UserBalanceLogDO>();
        jdbcTemplate.query("select top 20 * from dt_user_balance_log a where a.role_type='Manager' and a.id not in ( select top (20*(?-1)) b.id from dt_user_balance_log b where b.role_type='Manager' order by b.id) order by a.id", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, page);
            }
        }, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserBalanceLogDO userBalanceLogDO = new UserBalanceLogDO();
                userBalanceLogDO.setId(resultSet.getLong("id"));
                userBalanceLogDO.setUserId(resultSet.getInt("user_id"));
                userBalanceLogDO.setRoleType(resultSet.getString("role_type"));
                userBalanceLogDO.setRoleValue(resultSet.getInt("role_value"));
                userBalanceLogDO.setOrderPlatform(resultSet.getInt("order_platform"));
                userBalanceLogDO.setOrderType(resultSet.getString("order_type"));
                userBalanceLogDO.setOrderNo(resultSet.getString("order_no"));
                userBalanceLogDO.setBalanceHash(resultSet.getString("balance_hash"));
                userBalanceLogDO.setOrderTitle(resultSet.getString("order_title"));
                userBalanceLogDO.setOrderUrl(resultSet.getString("order_url"));
                userBalanceLogDO.setBalanceAmount(resultSet.getDouble("balance_amount"));
                userBalanceLogDO.setBalanceVoucher(resultSet.getDouble("balance_voucher"));
                userBalanceLogDO.setBalancePoint(resultSet.getDouble("balance_point"));
                userBalanceLogDO.setWillBalanceDate(resultSet.getDate("will_balance_date"));
                userBalanceLogDO.setRealBalanceDate(resultSet.getDate("real_balance_date"));
                userBalanceLogDO.setBalanceState(BalanceStatusEnums.getMsgByCode(resultSet.getString("balance_state")));
                userBalanceLogDO.setAddTime(resultSet.getDate("add_time"));
                userBalanceLogDO.setIsDel(IsDeleteEnums.getMsgByCode(resultSet.getString("is_del")));

                userBalanceLogList.add(userBalanceLogDO);
                return userBalanceLogList;
            }
        });

        return userBalanceLogList;


    }

    /**
     * 获取总条数
     * @return
     */
    public Integer getBalanceLogAllCount(){
        Integer count = jdbcTemplate.queryForObject("select count(1) from dt_user_balance_log a  where a.role_type='Manager'", Integer.class);
        return count;
    }


}
