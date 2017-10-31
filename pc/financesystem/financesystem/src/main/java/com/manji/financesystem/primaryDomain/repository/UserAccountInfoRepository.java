package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.AccountStateEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoQueryDO;
import com.manji.financesystem.requestParam.UserAccountInfoQueryRequestParam;
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
 * Created by pudding on 2017-1-17.
 */
@Repository
public class UserAccountInfoRepository {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询总页数
     *
     * @param param
     * @return
     */
    public Integer queryUserAccountInfoCount(UserAccountInfoQueryRequestParam param) {
        StringBuffer sql = new StringBuffer("SELECT count(1) FROM dt_users a INNER JOIN dt_user_accountinfo b ON a.id = b.user_id WHERE b.role_type IN ('Shop', 'Buyer') ");
        Integer count = 0;
        if (param != null) {
            if (!StringUtils.isEmpty(param.getUserName())) {
                sql.append("and user_name like '%" + param.getUserName() + "%'");
            }
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getState())) {
                String msgByCode = AccountStateEnums.getCodeByMsg(param.getState());
                sql.append("and state='" + msgByCode + "'");
            }
            count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);


        }
        return count;
    }


    public List<UserAccountInfoQueryDO> queryUserAccountInfoList(UserAccountInfoQueryRequestParam param,
                                                                 final Integer page) {
        final List<UserAccountInfoQueryDO> list = new ArrayList<UserAccountInfoQueryDO>();
        StringBuffer sql = new StringBuffer("select top 20 a.id,a.user_name,b.role_type,b.amount,b.allow_amount,b.state from dt_users a inner join dt_user_accountinfo b on a.id=b.user_id where b.role_type in ('Shop','Buyer')");
        if (param != null) {
            if (!StringUtils.isEmpty(param.getUserName())) {
                sql.append("and user_name like '%" + param.getUserName() + "%'");
            }
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getState())) {
                String msgByCode = AccountStateEnums.getCodeByMsg(param.getState());
                sql.append("and state='" + msgByCode + "'");
            }
            sql.append("and a.id not in (");
            sql.append("select top (20*(?-1)) a.id from dt_users a inner join dt_user_accountinfo b on a.id=b.user_id where b.role_type in ('Shop','Buyer')");
            if (!StringUtils.isEmpty(param.getUserName())) {
                sql.append("and user_name like '%" + param.getUserName() + "%'");
            }
            if (!StringUtils.isEmpty(param.getRoleType())) {
                String codeByMsg = UserRoleTypeEnums.getCodeByMsg(param.getRoleType());
                sql.append("and role_type='" + codeByMsg + "' ");
            }
            if (!StringUtils.isEmpty(param.getState())) {
                String msgByCode = AccountStateEnums.getCodeByMsg(param.getState());
                sql.append("and state='" + msgByCode + "'");
            }
            sql.append("order by a.id)");
            sql.append("order by a.id");

            //Query
            List<Object> query = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setInt(1, page);


                }
            }, new RowMapper<Object>() {
                @Override
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserAccountInfoQueryDO queryDO = new UserAccountInfoQueryDO();
                    queryDO.setUserId(resultSet.getString("id"));
                    queryDO.setUserName(resultSet.getString("user_name"));
                    //System.out.println(resultSet.getString("role_type"));

                    UserRoleTypeEnums role_type = UserRoleTypeEnums.getByCode(resultSet.getString("role_type"));
                    queryDO.setRoleType(role_type.getMessage());
                    queryDO.setAmount(resultSet.getDouble("amount"));
                    queryDO.setAllowAmount(resultSet.getDouble("allow_amount"));
                    queryDO.setState(AccountStateEnums.getByCode(resultSet.getString("state")).getMessage());
                    list.add(queryDO);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 通过user_id查询此用户提现规则     0默认规则执行 1免手续费
     */
    public int finduserAccount(int userid,String type){
        String sql="select withdrawals_commission from dt_user_accountinfo d where d.user_id=? and role_type=?;";
        int i=jdbcTemplate.queryForObject(sql, new Object[]{userid,type}, int.class);
        return i;
    }

}



