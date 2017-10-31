package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.*;
import com.manji.financesystem.requestParam.SatisfactionVoucherRequestParam;
import com.manji.financesystem.requestParam.VoucherDetailRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户和商家满意券账户信息
 * Created by Administrator on 2017/1/22.
 */
@Repository
public class SatisfactionVoucherRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询用户和商家的满意券余额
     * @return
     */
    public List<SatisfactionVoucherDO> getSatisfactionVoucher(SatisfactionVoucherRequestParam satisfactionVoucherRequestParam){
        final List<SatisfactionVoucherDO> list = new ArrayList<SatisfactionVoucherDO>();
        StringBuffer sql = new StringBuffer("select  top (20*?) * from dt_users a inner join dt_user_accountinfo b on a.id=b.user_id");
        StringBuffer sql1 = new StringBuffer(" except select top (20*(?-1)) * from dt_users a inner join dt_user_accountinfo b on a.id=b.user_id");
        if(satisfactionVoucherRequestParam != null){
            int pageNumber = satisfactionVoucherRequestParam.getPageNumber();
            String roleType = satisfactionVoucherRequestParam.getRoleType();
            if(StringUtils.isEmpty(satisfactionVoucherRequestParam.getRoleType())){
                roleType = "buyer";
            }
            sql.append(" and b.role_type='"+roleType+"'");
            sql1.append(" and b.role_type='"+roleType+"'");
            if(StringUtils.isEmpty(satisfactionVoucherRequestParam.getPageNumber())){
                pageNumber = 1;
            }
            if(!StringUtils.isEmpty(satisfactionVoucherRequestParam.getUserName())){
                sql.append(" and a.user_name like '%" + satisfactionVoucherRequestParam.getUserName() + "%'");
                sql1.append(" and a.user_name like '%" + satisfactionVoucherRequestParam.getUserName() + "%'");
            }
            if(!StringUtils.isEmpty(satisfactionVoucherRequestParam.getStatus())){
                sql.append(" and b.state='"+satisfactionVoucherRequestParam.getStatus()+"'");
                sql1.append(" and b.state='"+satisfactionVoucherRequestParam.getStatus()+"'");
            }
            sql.append(sql1);
            final String finalRoleType = roleType;
            jdbcTemplate.query(sql.toString(),new Object[]{pageNumber,pageNumber}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SatisfactionVoucherDO satisfactionVoucherDO = new SatisfactionVoucherDO();
                    satisfactionVoucherDO.setUserId(rs.getString("user_id"));
                    satisfactionVoucherDO.setUserName(rs.getString("user_name"));
                    satisfactionVoucherDO.setRoleType(finalRoleType);
                    satisfactionVoucherDO.setVoucher(rs.getDouble("voucher"));
                    satisfactionVoucherDO.setStatus(rs.getString("state"));
                    list.add(satisfactionVoucherDO);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 分页
     * @param
     * @return
     */
    public List<SatisfactionVoucherCountDO> getCount(SatisfactionVoucherRequestParam satisfactionVoucherRequestParam){
       final List<SatisfactionVoucherCountDO> list = new ArrayList<SatisfactionVoucherCountDO>();
        String roleType1 ="buyer";
        StringBuffer sql = new StringBuffer("select count(*) count from dt_users a inner join dt_user_accountinfo b on a.id=b.user_id and b.role_type=?");
        if(satisfactionVoucherRequestParam != null){
            String roleType = satisfactionVoucherRequestParam.getRoleType();
            if(!StringUtils.isEmpty(roleType)){
                roleType1 = roleType;
            }
            if(!StringUtils.isEmpty(satisfactionVoucherRequestParam.getUserName())){
                sql.append(" and a.user_name like '%" + satisfactionVoucherRequestParam.getUserName() + "%'");
            }
            if(!StringUtils.isEmpty(satisfactionVoucherRequestParam.getStatus())){
                sql.append(" and b.state='"+satisfactionVoucherRequestParam.getStatus()+"'");
            }
        }
        jdbcTemplate.query(sql.toString(),new Object[]{roleType1} ,new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                SatisfactionVoucherCountDO satisfactionVoucherCountDO = new SatisfactionVoucherCountDO();
                satisfactionVoucherCountDO.setCount(rs.getInt("count"));
                if(rs.getInt("count")/20 == 0){
                    satisfactionVoucherCountDO.setPageNumber(rs.getInt("count")/20);
                }else{
                    satisfactionVoucherCountDO.setPageNumber(rs.getInt("count")/20+1);
                }
                list.add(satisfactionVoucherCountDO);
                return list;
            }
        });
        return list;
    }

    /**
     * 得到用户的充值记录信息
     * @return
     */
    public List<UserRechargeDO> getRecharge(VoucherDetailRequestParam param){
        final List<UserRechargeDO> list = new ArrayList<UserRechargeDO>();
        if(param != null){
            jdbcTemplate.query("select * from dt_user_recharge where user_id=?",new Object[]{param.getUserId()}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserRechargeDO userRechargeDO = new UserRechargeDO();
                    userRechargeDO.setUserId(rs.getString("user_id"));
                    userRechargeDO.setUserRoleType(rs.getString("user_role_type"));
                    userRechargeDO.setRechargeNo(rs.getString("recharge_no"));
                    userRechargeDO.setVoucher(rs.getDouble("voucher"));
                    userRechargeDO.setAddTime(rs.getString("add_time"));
                    userRechargeDO.setCompleteTime(rs.getString("complete_time"));
                    list.add(userRechargeDO);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询用户提现记录中的满意券
     * @return
     */
   public List<UserWithdrawalsDO> getWithDrawals(VoucherDetailRequestParam param){
        final List<UserWithdrawalsDO> list = new ArrayList<UserWithdrawalsDO>();
       if(param != null){
           jdbcTemplate.query("select * from dt_user_withdrawals where user_id=?",new Object[]{param.getUserId()}, new RowMapper() {
               @Override
               public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                   UserWithdrawalsDO userWithdrawalsDO = new UserWithdrawalsDO();
                   userWithdrawalsDO.setUserId(rs.getString("user_id"));
                   userWithdrawalsDO.setUserRoleTyoe(rs.getString("user_role_type"));
                   userWithdrawalsDO.setWithdrawalsNo(rs.getString("withdrawals_no"));
                   userWithdrawalsDO.setVoucher(rs.getDouble("voucher"));
                   userWithdrawalsDO.setAdd_time(rs.getString("add_time"));
                   userWithdrawalsDO.setComplete_time(rs.getString("complete_time"));
                   list.add(userWithdrawalsDO);
                   return list;
               }
           });
       }
       return list;
   }

    /**
     * 查询订单中的满意券
     * @return
     */
    public List<OrdersDo> getOrderVoucher(VoucherDetailRequestParam param){
        final List<OrdersDo> list = new ArrayList<OrdersDo>();
        if(param != null){
            jdbcTemplate.query("select * from dt_orders where user_id=?",new Object[]{param.getUserId()}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrdersDo ordersDo = new OrdersDo();
                    ordersDo.setUserId(rs.getString("user_id"));
                    ordersDo.setUserRoleType(rs.getString("user_role_type"));
                    ordersDo.setOrderNo(rs.getString("order_no"));
                    ordersDo.setOrderTitle(rs.getString("order_title"));
                    ordersDo.setVoucher(rs.getDouble("voucher"));
                    ordersDo.setAddTime(rs.getString("add_time"));
                    ordersDo.setCompleteTime(rs.getString("complete_time"));
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询退单中的满意券
     * @param param
     * @return
     */
    public List<OrderBackInfoDO> getOrderBackVoucher(VoucherDetailRequestParam param){
        final List<OrderBackInfoDO> list = new ArrayList<OrderBackInfoDO>();
        if(param != null){
            jdbcTemplate.query("select * from dt_orders a inner join dt_order_backinfo b on a.id=b.order_id and a.user_id=?",new Object[]{param.getUserId()}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderBackInfoDO orderBackInfoDO = new OrderBackInfoDO();
                    orderBackInfoDO.setUserId(rs.getString("user_id"));
                    orderBackInfoDO.setUserRoleType(rs.getString("user_role_type"));
                    orderBackInfoDO.setOrderNO(rs.getString("order_no"));
                    orderBackInfoDO.setVoucher(rs.getDouble("voucher"));
                    orderBackInfoDO.setAddTime(rs.getString("add_time"));
                    list.add(orderBackInfoDO);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询在线付款中的满意券
     * @return
     */
    public List<OrderOnlinePayDO> getOnlinePayVoucher(VoucherDetailRequestParam param){
        final List<OrderOnlinePayDO> list = new ArrayList<OrderOnlinePayDO>();
        if(param != null){
            jdbcTemplate.query("select * from dt_order_online_pay where user_id=?",new Object[]{param.getUserId()}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderOnlinePayDO orderOnlinePayDO = new OrderOnlinePayDO();
                    orderOnlinePayDO.setUserId(rs.getInt("user_id"));
                    orderOnlinePayDO.setUserRoleType(rs.getString("user_role_type"));
                    orderOnlinePayDO.setPaymentName(rs.getString("payment_name"));
                    orderOnlinePayDO.setUseVoucher(rs.getDouble("use_voucher"));
                    orderOnlinePayDO.setAddTime(rs.getDate("add_time"));
                    list.add(orderOnlinePayDO);
                    return list;
                }
            });
        }

        return list;
    }
}
