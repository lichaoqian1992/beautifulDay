package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository
public class WithDrawalsRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 通過用戶獲取提現權限對象
     * @param user
     * @return
     */
    public RechargeConfigDO getRechargeConfig(UserDO user){
        String sql="SELECT * from t_recharge_config t where t.id="+user.getConfig_id()+"";
        final RechargeConfigDO RechargeConfig=new RechargeConfigDO();
       jdbcTemplate.queryForObject(sql,new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                RechargeConfig.setT_singleMaxMoney(rs.getDouble("T_SINGLE_MAX_MONEY"));
                RechargeConfig.setT_singleMinMoney(rs.getDouble("T_SINGLE_MIN_MONEY"));
                RechargeConfig.setT_totalMoney(rs.getDouble("T_TOTAL_MONEY"));
                return RechargeConfig;
            }
        });
        return RechargeConfig;
    }


}
