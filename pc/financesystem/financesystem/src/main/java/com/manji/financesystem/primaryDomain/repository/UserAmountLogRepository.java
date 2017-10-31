package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoDetailDO;
import com.manji.financesystem.requestParam.UserAccountInfoDetailQueryRequestParam;
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

/**
 * Created by pudding on 2017-1-23.
 */
@Repository
public class UserAmountLogRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询用户余额变动交易明细
     *
     * @param page
     * @param param
     * @return
     */
    public List<UserAccountInfoDetailDO>    queryUserAccountInfoDetail(final int page, String userName, UserAccountInfoDetailQueryRequestParam param) {
        final List<UserAccountInfoDetailDO> list = new ArrayList<UserAccountInfoDetailDO>();
        StringBuffer sql = new StringBuffer("select top 15 b.id,a.user_name,b.user_role_type,b.account_id,b.value,b.order_no,b.type,b.old_value,b.new_value,b.transaction_no,b.add_time from dt_users a ");
        sql.append("inner join dt_user_amount_log b ");
        sql.append("on a.id=b.user_id ");
        sql.append("where a.user_name='" + userName + "' and b.user_role_type in ('Shop','Buyer') ");
        if (param != null) {
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and user_role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getOrderNo())) {
                sql.append("and b.order_no like '%" + param.getOrderNo() + "%' ");
            }

            sql.append("and b.id not in( ");
            sql.append("select top (15*(?-1)) b.id from dt_users a inner join dt_user_amount_log b on a.id=b.user_id ");
            sql.append("where a.user_name='" + userName + "' and b.user_role_type in ('Shop','Buyer') ");
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and user_role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getOrderNo())) {
                sql.append("and b.order_no like '%" + param.getOrderNo() + "%' ");
            }
            sql.append("order by b.id ");
            sql.append(")order by b.id ");
        }

        jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, page);
            }
        }, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserAccountInfoDetailDO detailDO = new UserAccountInfoDetailDO();
                detailDO.setUserAccountDetailId(resultSet.getString("id"));
                detailDO.setUserName(resultSet.getString("user_name"));
                UserRoleTypeEnums role_type = UserRoleTypeEnums.getByCode(resultSet.getString("user_role_type"));
                detailDO.setUserRoleType(role_type.getMessage());
                detailDO.setAccountId(resultSet.getString("account_id"));
                detailDO.setValue(resultSet.getDouble("value"));
                detailDO.setOrderNo(resultSet.getString("order_no"));
                detailDO.setType(resultSet.getString("type"));
                detailDO.setOldValue(resultSet.getDouble("old_value"));
                detailDO.setNewValue(resultSet.getDouble("new_value"));
                detailDO.setTransactionNo(resultSet.getString("transaction_no"));
                detailDO.setAddTime(resultSet.getString("add_time"));
                list.add(detailDO);
                return list;
            }
        });
        return list;
    }


    /**
     * 查询用户余额变动明细总个数
     *
     * @param page
     * @param userName
     * @param param
     * @return
     */
    public Integer queryUserAccountInfoDetailCount(final int page, String userName, UserAccountInfoDetailQueryRequestParam param) {

        StringBuffer sql = new StringBuffer("select count(1) from dt_users a ");
        sql.append("inner join dt_user_amount_log b ");
        sql.append("on a.id=b.user_id ");
        sql.append("where a.user_name='" + userName + "' and b.user_role_type in ('Shop','Buyer') ");
        if (param != null) {
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and user_role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getOrderNo())) {
                sql.append("and b.order_no like '%" + param.getOrderNo() + "%' ");
            }
        }
        Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
        return count;
    }

    /**
     * 查询此用户本月期初
     * @param Withdrawals
     * @return
     */
    public double getUserAmountOldValue(UserWithdrawalsDO Withdrawals){
        String sql="select old_value from dt_user_amount_log l where l.add_time=(select min(add_time) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?));";
        Double old_value=jdbcTemplate.queryForObject(sql,new Object[]{Withdrawals.getUserId(),Withdrawals.getAdd_time()},double.class);
        return old_value;
    }

    /**
     * 查询此用户本月期末
     * @param Withdrawals
     * @return
     */
    public double getUserAmountNewValue(UserWithdrawalsDO Withdrawals){
        String sql="select new_value from dt_user_amount_log l where l.add_time=(select max(add_time) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?));";
        Double new_value=jdbcTemplate.queryForObject(sql,new Object[]{Withdrawals.getUserId(),Withdrawals.getAdd_time()},double.class);
        return new_value;
    }

    /**
     * 查询本月消费金额总和
     * @param Withdrawals
     * @return
     */
    public double getUserAmountSumValue(UserWithdrawalsDO Withdrawals){
      String sql="select SUM(value) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?);";
        Double sum_value=jdbcTemplate.queryForObject(sql,new Object[]{Withdrawals.getUserId(),Withdrawals.getAdd_time()},double.class);
        return sum_value;
    }




}
