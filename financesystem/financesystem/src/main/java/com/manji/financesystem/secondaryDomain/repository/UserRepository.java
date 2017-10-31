package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.secondaryDomain.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-1-16.
 */
@Repository
public class UserRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public UserDO findByUserName(String userName) {
        UserDO userDO = (UserDO) jdbcTemplate.queryForObject("select * from t_user where USERNAME=?", new Object[]{userName}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserDO user = new UserDO();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRealName(resultSet.getString("realname"));
                user.setMobile(resultSet.getString("mobile"));
                user.setLoginTime(resultSet.getDate("login_time"));
                user.setCreateTime(resultSet.getDate("create_time"));
                user.setLoginCount(resultSet.getInt("login_count"));
                user.setConfig_id(resultSet.getInt("t_config_id"));
                return user;
            }
        });
        return userDO;
    }


    public UserDO modifyUserLoginInfo(final String userName) {
        /*UPDATE t_user a set a.LOGIN_TIME=?,a.LOGIN_COUNT=? where USERNAME=?*/
        UserDO userDO = findByUserName(userName);
        if (userDO != null) {
            int count = userDO.getLoginCount() == null ? 0 : userDO.getLoginCount();
            count++;
            final int finalCount = count;
            int update = jdbcTemplate.update("UPDATE t_user a set a.LOGIN_TIME=?,a.LOGIN_COUNT=? where USERNAME=?", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    preparedStatement.setInt(2, finalCount);
                    preparedStatement.setString(3, userName);
                }
            });
            if(update>0){
                userDO = findByUserName(userName);
                return userDO;
            }
            return null;
        }
        return null;
    }


}
