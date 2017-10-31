package com.manji.financesystem.web;

import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoDetailDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoQueryDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserNormalRechargeDO;
import com.manji.financesystem.primaryDomain.repository.*;
import com.manji.financesystem.requestParam.UserAccountInfoDetailQueryRequestParam;
import com.manji.financesystem.requestParam.UserAccountInfoQueryRequestParam;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.repository.RoleRepository;
import com.manji.financesystem.secondaryDomain.repository.UserRepository;
import com.manji.financesystem.utils.VerificationCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by pudding on 2017-1-13.
 */
@RestController
public class TestController {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNormalRechargeRepository userNormalRechargeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrderOnlinePayRepository orderOnlinePayRepository;

    @Autowired
    private UserBalanceLogRepository userBalanceLogRepository;

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;

    @Autowired
    private UserAccountInfoRepository userAccountInfoRepository;

    @Autowired
    private UserAmountLogRepository userAmountLogRepository;


    @RequestMapping("/t1")
    public String testSql() {

        primaryJdbcTemplate.query("select * from dt_order_online_pay", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet.getString("user_id"));
            }
        });
        return "T1";
    }


    @RequestMapping("/t2")
    public String testMysql() {
        secondaryJdbcTemplate.query("select * from `user`", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
            }
        });
        return "T2";
    }


    @RequestMapping("/t3/{page}")
    public List<UserNormalRechargeDO> testquery(@PathVariable Integer page, UserQueryNormalRechargeListParam param){
        List<UserNormalRechargeDO> list = userNormalRechargeRepository.queryUserNormalRechargeRecordList(page, param);
        int i = userNormalRechargeRepository.queryUserNormalRechargeRecordListCount(param);
        System.out.println(i);
        return list;
    }

    @RequestMapping("/t7")
    public String test(){
        new VerificationCodeUtils().getYzm();
        return "111";
    }

    @RequestMapping("/t8")
    public String sql(HttpServletRequest request ){
        HttpSession session=request.getSession();
        UserDO u=(UserDO) session.getAttribute("user");
        System.err.println("----------用户的角色为："+u.getConfig_id()+"-------------");
        return "/t8";
    }


}
