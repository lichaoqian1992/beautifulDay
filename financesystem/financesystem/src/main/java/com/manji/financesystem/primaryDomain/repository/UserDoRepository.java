package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.UserDO;
import com.manji.financesystem.primaryDomain.entiity.UserLoginLogDO;
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
public class UserDoRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户id查询用户对象
     * @return
     */
    public UserDO getUserByUserid(int  userid){
        final UserDO user=new UserDO();
        String sql="select * from dt_users where id=?;";
        jdbcTemplate.query(sql,new Object[]{userid},new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setGroup_id(rs.getInt("group_id"));
                        user.setUser_name(rs.getString("user_name"));
                        user.setSalt(rs.getString("salt"));
                        user.setPassword(rs.getString("password"));
                        user.setMobile(rs.getString("mobile"));
                        user.setEmail(rs.getString("email"));
                        user.setAvatar(rs.getString("avatar"));
                        user.setNick_name(rs.getString("nick_name"));
                        user.setSex(rs.getString("sex"));
                        user.setBirthday(rs.getDate("birthday"));
                        user.setTelphone(rs.getString("telphone"));
                        user.setArea(rs.getString("area"));
                        user.setAddress(rs.getString("address"));
                        user.setQq(rs.getString("qq"));
                        user.setPay_password(rs.getString("pay_password"));
                        user.setStatus(rs.getInt("status"));
                        user.setRemark(rs.getString("remark"));
                        user.setReg_time(rs.getString("reg_time"));
                        user.setReg_ip(rs.getString("reg_ip"));
                        user.setFrom_value(rs.getString("from_value"));
                        user.setIs_del(rs.getInt("is_del"));
                        return user;
                    }
                });
        return user;
    }

    /**
     *  根据用户id查询用户最近登陆时间
     * @return
     */
    public String getUserLoginDateByUserid(int  userid){
        String sql="select max(login_time) from dt_user_login_log where user_id=? ;";
        return jdbcTemplate.queryForObject(sql,new Object[]{userid},String.class);

    }


    /**
     * 通过提现时间得到最近的一条登录记录
     * @param addTime,userid
     * @return
     */
    public UserLoginLogDO getMaxUserLoginLog(int userid,String addTime){
        final UserLoginLogDO userLoginLog=new UserLoginLogDO();
        String sql="select * from dt_user_login_log where login_time=(select max(login_time) from dt_user_login_log where user_id=? and login_time<?);";
        jdbcTemplate.query(sql,new Object[]{userid,addTime},new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                userLoginLog.setId(rs.getInt("id"));
                userLoginLog.setUser_id(rs.getInt("user_id"));
                userLoginLog.setRemark(rs.getString("remark"));
                userLoginLog.setLogin_ip(rs.getString("login_ip"));
                userLoginLog.setLogin_time(rs.getDate("login_time"));
                userLoginLog.setLogin_from(rs.getString("login_from"));
                return userLoginLog;
                 }
             }
        );
        return userLoginLog;
    };


    /**
     * 通过用户id查询此用户登录最多的ip地址
     * @param userid
     * @return
     */
    public String getMaxCountUserLoginLogIP(int userid){
        String sql="set rowcount 1 select login_ip i,count(*) ccount from dt_user_login_log where user_id=? group by login_ip order by ccount desc;";
        Object u=jdbcTemplate.queryForObject(sql,new Object[]{userid},new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("i");
            }
        }
        );
        return u.toString();
    }


}
