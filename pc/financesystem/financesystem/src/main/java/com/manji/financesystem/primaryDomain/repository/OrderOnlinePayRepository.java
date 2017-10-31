package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.OrderOnlinePayDO;
import com.manji.financesystem.primaryDomain.entiity.extra.AccountInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-1-17.
 */
@Repository
public class OrderOnlinePayRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取第三方账号总消费金额
     *
     * @return
     */
    public List<AccountInfoDO> getOnlinePayInfo() {
        final List<AccountInfoDO> accountInfoDOs = new ArrayList<AccountInfoDO>();
        jdbcTemplate.query("select a.title,b.payment_id,b.ye from dt_payment A inner join ( select payment_id, sum(transaction_money)ye from dt_order_online_pay a where a.status='1' group by payment_id ) B  on  a.id=b.payment_id where b.payment_id not in (11,14)", new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                AccountInfoDO accountInfoDO = new AccountInfoDO();
                accountInfoDO.setAccountName(resultSet.getString("title"));
                accountInfoDO.setPaymentId(resultSet.getInt("payment_id"));
                accountInfoDO.setAmount(resultSet.getDouble("ye"));
                accountInfoDOs.add(accountInfoDO);
                return accountInfoDOs;
            }
        });

        return accountInfoDOs;
    }


    /***
     * 查询第三方支付交易明细
     *
     * @param page      当前页
     * @param paymentId 类别ID
     * @return
     */
    public List<OrderOnlinePayDO> getAccountListByPaymentId(final int page, final int paymentId) {
        final List<OrderOnlinePayDO> orderOnlinePayList = new ArrayList<OrderOnlinePayDO>();

        jdbcTemplate.query("select top 20 * from dt_order_online_pay a where a.payment_id=? and a.status='1' and a.id not in ( select top (20*(?-1)) b.id from dt_order_online_pay b where b.payment_id=? and b.status='1' order by b.id ) order by  a.id", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, paymentId);
                preparedStatement.setInt(2, page);
                preparedStatement.setInt(3, paymentId);
            }
        }, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                OrderOnlinePayDO orderDo = new OrderOnlinePayDO();
                orderDo.setId(resultSet.getLong("id"));
                orderDo.setUserId(resultSet.getInt("user_id"));
                orderDo.setUserRoleType(resultSet.getString("user_role_type"));
                orderDo.setUserRoleValue(resultSet.getInt("user_role_value"));
                orderDo.setOrderType(resultSet.getString("order_type"));
                orderDo.setOrderIdList(resultSet.getString("order_id_list"));
                orderDo.setUseVoucher(resultSet.getDouble("use_voucher"));
                orderDo.setPaymentName(resultSet.getString("payment_name"));
                orderDo.setPaymentId(resultSet.getInt("payment_id"));
                orderDo.setAddTime(resultSet.getDate("add_time"));
                orderDo.setNotifyTime(resultSet.getDate("notify_time"));
                orderDo.setTransactionNo(resultSet.getString("transaction_no"));
                orderDo.setTransactionMoney(resultSet.getDouble("transaction_money"));
                orderDo.setTransactionIdentity(resultSet.getString("transaction_Identity"));
                orderDo.setTransactionTxt(resultSet.getString("transaction_txt"));
                orderDo.setStatus(resultSet.getInt("status"));
                orderDo.setIsDel(resultSet.getDouble("is_del"));
                orderDo.setReturnUrl(resultSet.getString("return_url"));
                orderDo.setPaymentType(resultSet.getString("payment_type"));
                orderDo.setPayment_money(resultSet.getDouble("payment_money"));
                orderDo.setNotifyUrl(resultSet.getString("notify_url"));
                orderDo.setPartnerChannel(resultSet.getString("partner_channel"));
                orderDo.setCensorDate(resultSet.getLong("censor_date"));
                orderDo.setCensorUser(resultSet.getString("censor_user"));
                orderDo.setRemark(resultSet.getString("remark"));

                orderOnlinePayList.add(orderDo);
                return orderOnlinePayList;
            }
        });
        return orderOnlinePayList;
    }

    /**
     * 查询第三方支付总记录数
     * @param paymentId 类别Id
     * @return
     */
    public int getAccountListByPaymentIdCount(int paymentId) {
        Integer count = jdbcTemplate.queryForObject("select count(1) from dt_order_online_pay where payment_id=? and status='1'", new Object[]{paymentId}, Integer.class);
        return count;
    }


}
