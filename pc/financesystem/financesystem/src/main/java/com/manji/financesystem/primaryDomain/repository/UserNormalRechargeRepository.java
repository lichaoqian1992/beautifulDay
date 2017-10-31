package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.RechargeStatus;
import com.manji.financesystem.primaryDomain.entiity.UserAccountInfoDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserNormalRechargeDO;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pudding on 2017-2-3.
 * 查询正常的充值记录
 */
@Repository
public class UserNormalRechargeRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    public List<UserNormalRechargeDO> queryUserNormalRechargeRecordList(final int page, UserQueryNormalRechargeListParam param) {
        final List<UserNormalRechargeDO> list = new ArrayList<UserNormalRechargeDO>();
        StringBuffer sql = new StringBuffer("select top 20 a.user_id,a.transaction_no,a.payment_name,a.transaction_money,b.user_name,a.status,a.add_time,a.notify_time,a.remark from dt_order_online_pay a inner join dt_users  b on a.user_id=b.id where a.user_role_type='Buyer' and a.is_del=0 ");

        if (!StringUtils.isEmpty(param.getUserName())) {
            sql.append("and b.user_name like '%" + param.getUserName() + "%'");
        }
        if (!StringUtils.isEmpty(param.getStatus())) {
            String code = RechargeStatus.getCodeByMsg(param.getStatus());
            sql.append("and a.status=" + code + " ");
        }
        if (!StringUtils.isEmpty(param.getStartTime())) {
            sql.append("and a.add_time >= '" + param.getStartTime() + "' ");
        }
        if (!StringUtils.isEmpty(param.getEndTime())) {
            sql.append("and a.notify_time <= '" + param.getEndTime() + "' ");
        }
        sql.append("and b.id not in (");
        sql.append("select top (20*(?-1)) b.id from dt_order_online_pay a inner join dt_users  b on a.user_id=b.id where a.user_role_type='Buyer' and a.is_del=0 ");
        if (!StringUtils.isEmpty(param.getUserName())) {
            sql.append("and b.user_name like '%" + param.getUserName() + "%'");
        }
        if (!StringUtils.isEmpty(param.getStatus())) {
            String code = RechargeStatus.getCodeByMsg(param.getStatus());
            sql.append("and a.status=" + code + "");
        }
        if (!StringUtils.isEmpty(param.getStartTime())) {
            sql.append("and a.add_time >= '" + param.getStartTime() + "'");
        }
        if (!StringUtils.isEmpty(param.getEndTime())) {
            sql.append("and a.notify_time <= '" + param.getEndTime() + "'");
        }
        sql.append("order by b.id)");
        sql.append("order by b.id");

        System.out.println(sql);

        //Query
        List<Object> query = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, page);
            }
        }, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserNormalRechargeDO rechargeDO = new UserNormalRechargeDO();
                rechargeDO.setUserId(resultSet.getString("user_id"));
                rechargeDO.setTransactionNo(resultSet.getString("transaction_no"));
                rechargeDO.setPaymentName(resultSet.getString("payment_name"));
                rechargeDO.setTransactionMoney(resultSet.getDouble("transaction_money"));
                rechargeDO.setUserName(resultSet.getString("user_name"));
                String status = RechargeStatus.getMsgByCode(resultSet.getString("status"));
                rechargeDO.setStatus(status);
                rechargeDO.setAddTime(resultSet.getString("add_time"));
                rechargeDO.setNotifyTime(resultSet.getString("notify_time"));
                rechargeDO.setRemark(resultSet.getString("remark"));
                list.add(rechargeDO);
                return list;
            }
        });

        return list;

    }

    /**
     * 查询记录总条数
     * @param param
     * @return
     */
    public int queryUserNormalRechargeRecordListCount(UserQueryNormalRechargeListParam param) {
        StringBuffer sql = new StringBuffer("select Count(1)  from dt_order_online_pay a inner join dt_users  b on a.user_id=b.id where a.user_role_type='Buyer' and a.is_del=0 ");
        if (!StringUtils.isEmpty(param.getUserName())) {
            sql.append("and b.user_name like '%" + param.getUserName() + "%'");
        }
        if (!StringUtils.isEmpty(param.getStatus())) {
            String code = RechargeStatus.getCodeByMsg(param.getStatus());
            sql.append("and a.status=" + code + "");
        }
        if (!StringUtils.isEmpty(param.getStartTime())) {
            sql.append("and a.add_time >= '" + param.getStartTime() + "'");
        }
        if (!StringUtils.isEmpty(param.getEndTime())) {
            sql.append("and a.notify_time <= '" + param.getEndTime() + "'");
        }

        Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
        return count;
    }


    /**
     * 获取当日累计充值总金额
     * @return
     */
    public Double getTodaySumAmount(String today, String toMorrow){
        String sql="select sum(transaction_money) as money from dt_order_online_pay where status=1 and add_time>='"+today+"' and add_time<'"+toMorrow+"'";
        Double amount = jdbcTemplate.queryForObject(sql,Double.class);
        return amount;
    }


    /**
     * 获取当月累计充值总金额
     * @param currentMonthFirstDayTime
     * @param currentMonthLastDayTime
     * @return
     */
    public Double getToMorrowSumAmount(String currentMonthFirstDayTime,String currentMonthLastDayTime){
        String sql="select sum(transaction_money) as money  from dt_order_online_pay where status=1 and add_time>='"+currentMonthFirstDayTime+"' and add_time<='"+currentMonthLastDayTime+"'";
        System.out.println(sql);
        Double amount = jdbcTemplate.queryForObject(sql, Double.class);
        return amount;

    }

    /**
     * 导出 查询充值所有记录
     * @return
     */
    public List<Map<String, Object>> queryUserNormalRechargeAllRecord(UserQueryNormalRechargeListParam param){
        StringBuffer sql=new StringBuffer("select a.transaction_no,a.payment_name,a.transaction_money,b.user_name,a.status,a.add_time,a.notify_time,a.remark from dt_order_online_pay a inner join dt_users  b on a.user_id=b.id where a.user_role_type='Buyer' and a.is_del=0 ");
        if (!StringUtils.isEmpty(param.getUserName())) {
            sql.append("and b.user_name like '%" + param.getUserName() + "%'");
        }
        if (!StringUtils.isEmpty(param.getStatus())) {
            String code = RechargeStatus.getCodeByMsg(param.getStatus());
            sql.append("and a.status=" + code + "");
        }
        if (!StringUtils.isEmpty(param.getStartTime())) {
            sql.append("and a.add_time >= '" + param.getStartTime() + "'");
        }
        if (!StringUtils.isEmpty(param.getEndTime())) {
            sql.append("and a.notify_time <= '" + param.getEndTime() + "'");
        }

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.toString());
        return maps;

    }

    /**
     * 根据提供的用户标识查询用户的  用户编号+用户角色类型
     * @param param
     * @return
     */
    public List<UserAccountInfoDO> queryUserInfoByUserKey(String param){
        final List<UserAccountInfoDO> list = new ArrayList<UserAccountInfoDO>();
        StringBuffer sql = new StringBuffer("select distinct b.user_id,b.role_type,b.role_value from dt_user_accountinfo b where b.user_id in(select a.id from dt_users a where");
            if(!StringUtils.isEmpty(param)){
                sql.append(" a.user_name='"+param+"' or a.mobile='"+param+"' or a.email='"+param+"')");
            }
        System.out.println("sql="+sql.toString());
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserAccountInfoDO userdo = new UserAccountInfoDO();
                    userdo.setUserId(Integer.parseInt(rs.getString("user_id")));
                    userdo.setRoleValue(Integer.parseInt(rs.getString("role_value")));
                    userdo.setRoleType(rs.getString("role_type"));
                    list.add(userdo);
                    return list;
                }
            });
        return list;
    }




}
