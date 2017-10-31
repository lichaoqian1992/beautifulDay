package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.PayStatusEnums;
import com.manji.financesystem.enums.WithdrawalsStatus;
import com.manji.financesystem.primaryDomain.entiity.MonthAndDailySheetExpenditrueDO;
import com.manji.financesystem.primaryDomain.entiity.MonthAndDailySheetIncomeDO;
import com.manji.financesystem.primaryDomain.entiity.OrdersDo;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.primaryDomain.entiity.extra.DayOrMonthDetailDO;
import com.manji.financesystem.responseData.OrdersResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
@Repository
public class DailySheetRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询的是今天的收入总金额（收入仅仅只有订单金额）
     * @param startTime
     * @return
     * @throws ParseException
     */
    public MonthAndDailySheetIncomeDO getDailySheetIncome(final String startTime) throws ParseException {
        final List<MonthAndDailySheetIncomeDO> list = new ArrayList<MonthAndDailySheetIncomeDO>();
        final List<MonthAndDailySheetIncomeDO> list2 = new ArrayList<MonthAndDailySheetIncomeDO>();
        MonthAndDailySheetIncomeDO dailySheetDO1 = new MonthAndDailySheetIncomeDO();
        final double[] todayBefore = new double[1];
        final double[] today = new double[1];
        //查询24号的收支就应该是24<=a<25
        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime()+24*60*60*1000));//结束时间


        //查询今天以前的所有收入//select sum(b.balance_amount) money from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and a.status='3' and b.balance_state='2' and a.complete_time < ?
        jdbcTemplate.query("select sum(a.real_amount) money from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and b.balance_state='2' and b.real_balance_date<?",new Object[]{startTime}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                MonthAndDailySheetIncomeDO dailySheetDO = new MonthAndDailySheetIncomeDO();
                todayBefore[0] = rs.getDouble("money");
                return todayBefore[0];
            }
        });

        //查询的是今天的收入
        jdbcTemplate.query("select sum(a.real_amount) money from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and b.balance_state='2'and b.real_balance_date >=? and b.real_balance_date < ?",new Object[]{startTime,endTime}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                MonthAndDailySheetIncomeDO dailySheetDO = new MonthAndDailySheetIncomeDO();
                today[0] = rs.getDouble("money");
                return today[0];
            }
        });

        /*//今天之前的所有充值金额
        jdbcTemplate.query("select sum(amount) money from dt_user_recharge where complete_time<?",new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                chongzhiMoney[0] = rs.getDouble("money");
                return chongzhiMoney[0];
            }
        });
        //今天的所有充值金额
        jdbcTemplate.query("select sum(amount) money from dt_user_recharge where complete_time<? and complete_time>=?",new Object[]{endTime,startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                chongzhiMoney1[0] = rs.getDouble("money");
                return chongzhiMoney1[0];
            }
        });

        //今天以前的所有提现收取的手续费
        jdbcTemplate.query("select sum(commission) money from dt_user_withdrawals where status='5' and complete_time<?",new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                tixianMoney[0] = rs.getDouble("money");
                return tixianMoney[0];
            }
        });
        //今天的所有提现收取的手续费
        jdbcTemplate.query("select sum(commission) money from dt_user_withdrawals where status='5' and complete_time>=? and complete_time<?",new Object[]{startTime,endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                tixianMoney1[0] = rs.getDouble("money");
                return tixianMoney1[0];
            }
        });
        for(int i=0;i<list.size();i++){
            account+=list.get(i).getIncomeAmount();
        }
        for(int i=0;i<list2.size();i++){
            account2+=list2.get(i).getIncomeAmount();
        }*/
        dailySheetDO1.setIncomeAmount(today[0]);//今天的收入
        dailySheetDO1.setIncomeYesterdayAmount(todayBefore[0]);//今天之前的收入
        return dailySheetDO1;
    }

    /**
     * 支出的总额,提现才有支出
     * @param startTime
     * @return
     * @throws ParseException
     */
    public MonthAndDailySheetExpenditrueDO getDailySheetExpenditrue(String startTime) throws ParseException {
        final List<MonthAndDailySheetExpenditrueDO> list = new ArrayList<MonthAndDailySheetExpenditrueDO>();
        final List<MonthAndDailySheetExpenditrueDO> list2 = new ArrayList<MonthAndDailySheetExpenditrueDO>();
        MonthAndDailySheetExpenditrueDO monthAndDaily = new MonthAndDailySheetExpenditrueDO();
        double amount=0;
        double amount2=0;
        //查询24号的收支就应该是24<=a<25
        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime()+24*60*60*1000));//结束时间
        jdbcTemplate.query("select sum(total_money) money from dt_user_withdrawals where status='5' and complete_time>=? AND complete_time<?", new Object[]{startTime, endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                MonthAndDailySheetExpenditrueDO monthAndDailySheetExpenditrueDO = new MonthAndDailySheetExpenditrueDO();
                monthAndDailySheetExpenditrueDO.setExpenditureAmount(rs.getDouble("money"));
                list.add(monthAndDailySheetExpenditrueDO);
                return list;
            }
        });
        //以前的支出
        jdbcTemplate.query("select sum(total_money) money from dt_user_withdrawals where status='5' and complete_time<?", new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                MonthAndDailySheetExpenditrueDO monthAndDailySheetExpenditrueDO = new MonthAndDailySheetExpenditrueDO();
                monthAndDailySheetExpenditrueDO.setExpenditureAmount(rs.getDouble("money"));
                list2.add(monthAndDailySheetExpenditrueDO);
                return list2;
            }
        });
        for(int i=0;i<list.size();i++){
            amount+=list.get(i).getExpenditureAmount();
        }
        for(int i=0;i<list2.size();i++){
            amount2+=list2.get(i).getExpenditureAmount();
        }
        monthAndDaily.setExpenditureAmount(amount);
        monthAndDaily.setExpenditureYesterdayAmount(amount2);
        return monthAndDaily;
    }

    /**
     * 查询当月的金额
     * @param startTime
     * @return
     * @throws ParseException
     */
    public MonthAndDailySheetIncomeDO getMonthSheetIncome(String startTime) throws ParseException {
        startTime = startTime+"-01";
        final List<MonthAndDailySheetIncomeDO> list = new ArrayList<MonthAndDailySheetIncomeDO>();
        final List<MonthAndDailySheetIncomeDO> list2 = new ArrayList<MonthAndDailySheetIncomeDO>();
        MonthAndDailySheetIncomeDO dailySheetDO1 = new MonthAndDailySheetIncomeDO();
        final double[] todayBefore = new double[1];
        final double[] today = new double[1];
        //查询24号的收支就应该是24<=a<25
        int year = Integer.parseInt(startTime.split("-")[0]);
        int month = (new SimpleDateFormat("yyyy-MM-dd").parse(startTime)).getMonth()+2;//月份
        if(month>11){
            year = year+1;
            month = 1;
        }
        String endTime = year+"-"+month+"-01";//结束时间
        //查询当月前面的所有收入
        jdbcTemplate.query("select sum(a.real_amount) money from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and b.balance_state='2' and b.real_balance_date<?",new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                MonthAndDailySheetIncomeDO dailySheetDO = new MonthAndDailySheetIncomeDO();
                todayBefore[0] = rs.getDouble("money");

                return todayBefore[0];
            }
        });
        //查询的是当月的收入
        jdbcTemplate.query("select sum(a.real_amount) money from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and b.balance_state='2'and b.real_balance_date >=? and b.real_balance_date < ?",new Object[]{startTime,endTime}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                MonthAndDailySheetIncomeDO dailySheetDO = new MonthAndDailySheetIncomeDO();
                today[0] = rs.getDouble("money");

                return today[0];
            }
        });
        /*//当月的充值金额
        jdbcTemplate.query("select sum(amount) money from dt_user_recharge where complete_time<? and complete_time>=?",new Object[]{endTime,startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                chongzhiMoney1[0] = rs.getDouble("money");
                return chongzhiMoney1[0];
            }
        });
        //前面月份的充值金额
        jdbcTemplate.query("select sum(amount) money from dt_user_recharge where complete_time<?",new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                chongzhiMoney[0] = rs.getDouble("money");
                return chongzhiMoney[0];
            }
        });
        //当月的提现手续费
        jdbcTemplate.query("select sum(commission) money from dt_user_withdrawals where status='5' and complete_time>=? and complete_time<?",new Object[]{startTime,endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                tixianMoney1[0] = rs.getDouble("money");
                return tixianMoney1[0];
            }
        });
        //前面的提现手续费
        jdbcTemplate.query("select sum(commission) money from dt_user_withdrawals where status='5' and complete_time<?",new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                tixianMoney[0] = rs.getDouble("money");
                return tixianMoney[0];
            }
        });

        for(int i=0;i<list.size();i++){
            account+=list.get(i).getIncomeAmount();
        }
        for(int i=0;i<list2.size();i++){
            account2+=list2.get(i).getIncomeAmount();
        }*/
        dailySheetDO1.setIncomeAmount(today[0]);//今天的收入
        dailySheetDO1.setIncomeYesterdayAmount(todayBefore[0]);//今天之前的收入
        return dailySheetDO1;
    }

    /**
     * 查询的是月支出
     * @param startTime
     * @return
     * @throws ParseException
     */
    public MonthAndDailySheetExpenditrueDO getMonthSheetExpenditrue(String startTime) throws ParseException {
        startTime = startTime+"-01";
        final List<MonthAndDailySheetExpenditrueDO> list = new ArrayList<MonthAndDailySheetExpenditrueDO>();
        final List<MonthAndDailySheetExpenditrueDO> list2 = new ArrayList<MonthAndDailySheetExpenditrueDO>();
        MonthAndDailySheetExpenditrueDO monthAndDaily = new MonthAndDailySheetExpenditrueDO();
        double amount=0;
        double amount2=0;

        //查询24号的收支就应该是24<=a<25
        int year = Integer.parseInt(startTime.split("-")[0]);
        int month = (new SimpleDateFormat("yyyy-MM-dd").parse(startTime)).getMonth()+2;//月份
        if(month>11){
            year = year+1;
            month = 1;
        }
        String endTime = year+"-"+month+"-01";//结束时间
        //当月支出
        jdbcTemplate.query(" select total_money,complete_time from dt_user_withdrawals where status='5' and complete_time>=? AND complete_time<?", new Object[]{startTime, endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                MonthAndDailySheetExpenditrueDO monthAndDailySheetExpenditrueDO = new MonthAndDailySheetExpenditrueDO();
                monthAndDailySheetExpenditrueDO.setExpenditureAmount(rs.getDouble("total_money"));
                list.add(monthAndDailySheetExpenditrueDO);
                return list;
            }
        });
        //当月前一月支出
        jdbcTemplate.query(" select total_money,complete_time from dt_user_withdrawals where status='5' and complete_time<?", new Object[]{startTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                MonthAndDailySheetExpenditrueDO monthAndDailySheetExpenditrueDO = new MonthAndDailySheetExpenditrueDO();
                monthAndDailySheetExpenditrueDO.setExpenditureAmount(rs.getDouble("total_money"));
                list2.add(monthAndDailySheetExpenditrueDO);
                return list2;
            }
        });
        for(int i=0;i<list.size();i++){
            amount+=list.get(i).getExpenditureAmount();
        }
        for(int i=0;i<list2.size();i++){
            amount2+=list2.get(i).getExpenditureAmount();
        }
        monthAndDaily.setExpenditureAmount(amount);
        monthAndDaily.setExpenditureYesterdayAmount(amount2);
        return monthAndDaily;
    }

    /**
     * 查询今日的订单数和金额
     * @param myDate
     * @return
     */
    public OrdersResult getAllOrdersMoney(String myDate) throws ParseException {
        final OrdersResult ordersResult = new OrdersResult();
        if(myDate != null){
            String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(myDate).getTime()+(24*60*60*1000)));
            String sql = "select count(order_no) count,sum(order_amount) money from dt_orders where  add_time >='"+myDate+"' and add_time < '"+endDate+"'";
            System.out.println(sql);
            jdbcTemplate.query(sql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ordersResult.setAmountOrders(rs.getDouble("count"));
                    ordersResult.setAmountMoney(rs.getDouble("money"));
                    return ordersResult;
                }
            });
        }
        return ordersResult;
    }

    /**
     * 查询成功交易的订单和金额
     * @param myDate
     * @return
     */
    public OrdersResult getSuccessOrdersMoney(String myDate) throws ParseException {
        final OrdersResult ordersResult = new OrdersResult();
        if(myDate != null){
            String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(myDate).getTime()+(24*60*60*1000)));
            String sql = "select count(order_no) count,sum(order_amount) money from dt_orders where status='2' and confirm_time >='"+myDate+"' and confirm_time < '"+endDate+"'";
            System.out.println(sql);
            jdbcTemplate.query(sql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ordersResult.setSuccessOrders(rs.getDouble("count"));
                    ordersResult.setSuccessMoney(rs.getDouble("money"));
                    return ordersResult;
                }
            });
        }
        return ordersResult;
    }
    /**
     * 查询收支交易明细
     * @param startTime
     * @return
     */
    public List<DayOrMonthDetailDO> queryDayOrMonthDetail(String startTime,String excelType) throws ParseException {
        String endTime = "";
        final List<DayOrMonthDetailDO> list = new ArrayList<DayOrMonthDetailDO>();
        //根据不同的类型，查询不同的交易明细
        if(startTime.equals("")){
            startTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        if(excelType.equals("day")){
            //查询日报表明细
            endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime()+24*60*60*1000));//结束时间
        }else if(excelType.equals("month")){
            //查询月报表明细
            int year = Integer.parseInt(startTime.split("-")[0]);
            int month = (new SimpleDateFormat("yyyy-MM-dd").parse(startTime)).getMonth()+2;//月份
            if(month>11){
                year = year+1;
                month = 1;
            }
            startTime = startTime.split("-")[0]+"-"+startTime.split("-")[1]+"-01";//2017-03-17
            endTime = year+"-"+month+"-01";//结束时间
        }
        System.out.println(startTime+"  "+endTime);
        //根据开始时间和结束时间查询收入和支出的明细          收入=订单+充值?  支出=提现
        //1.订单的收入
        final String finalStartTime1 = startTime;
        jdbcTemplate.query("select * from (select mm.*,nn.title as payType from(select a.*,b.balance_amount,b.balance_voucher,b.real_balance_date from dt_orders a inner join dt_user_balance_log b on a.order_no=b.order_no and b.balance_state='2' and b.real_balance_date>=? and b.real_balance_date<?) mm inner join dt_payment nn on mm.payment_id=nn.id) mm inner join (select a.title,b.* from dt_article_category a inner join( select a.user_id,a.name,a.main_business,a.percentage,b.user_name from dt_user_role_shopinfo a inner join dt_users b on a.user_id=b.id) b on a.id=b.main_business) nn on mm.shop_user_id=nn.user_id",new Object[]{startTime, endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                DayOrMonthDetailDO do2 = new DayOrMonthDetailDO();
                do2.setDate(finalStartTime1);
                do2.setJyTime(rs.getString("real_balance_date"));
                do2.setOrderNo(rs.getString("order_no"));
                do2.setShopName(rs.getString("name"));
                do2 .setGoodType(rs.getString("title"));
                do2.setPercent(rs.getString("percentage"));
                do2.setUserName(rs.getString("user_name"));
                do2.setOrderType(rs.getString("order_title"));
                do2.setPayType(rs.getString("payType"));
                do2.setBankName("");
                do2.setOrderStatus(PayStatusEnums.getMsgByCode(rs.getString("payment_status")));
                do2.setOrderMoney(rs.getString("order_amount"));
                do2.setRealMoney(rs.getString("real_amount"));
                do2.setVoucher(rs.getString("voucher"));
                do2.setCommission(rs.getDouble("payment_fee"));
                list.add(do2);
                return list;
            }
        });
        //支出
        //1.商家提现
        String sql1 = "select mm.*,nn.name as nickname,nn.percentage,nn.title from(select a.complete_time,a.withdrawals_no,a.bank_card,a.user_id,a.status,a.amount,a.voucher,a.commission,b.user_name from dt_user_withdrawals a inner join dt_users b on a.user_id=b.id and a.status='5' and complete_time>=? AND complete_time<?) mm inner join(select a.title,b.* from dt_article_category a inner join( select a.user_id,a.name,a.main_business,a.percentage from dt_user_role_shopinfo a inner join dt_users b on a.user_id=b.id) b on a.id=b.main_business) nn on mm.user_id=nn.user_id";
        List<DayOrMonthDetailDO> list1 = together(sql1,startTime,endTime,"商家");
        //2.用户提现
        String sql2 = "select mm.*,nn.nick_name as nickname from(select a.complete_time,a.withdrawals_no,a.bank_card,a.user_id,a.status,a.amount,a.voucher,a.commission,b.user_name from dt_user_withdrawals a inner join dt_users b on a.user_id=b.id and a.status='5' and complete_time>=? AND complete_time<?) mm inner join(select a.id,a.user_name,a.nick_name from dt_users a) nn on mm.user_id=nn.id";
        List<DayOrMonthDetailDO> list2 = together(sql2,startTime,endTime,"用户");
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }
    public List<DayOrMonthDetailDO> together(String sql,String startTime,String endTime,final String type){
        final List<DayOrMonthDetailDO> list = new ArrayList<DayOrMonthDetailDO>();
        final String finalStartTime = startTime;
        final java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
        jdbcTemplate.query(sql, new Object[]{startTime, endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                String goodType = "";
                String percentage = "";
                DayOrMonthDetailDO do2 = new DayOrMonthDetailDO();
                do2.setDate(finalStartTime);
                do2.setJyTime(rs.getString("complete_time"));
                do2.setOrderNo(rs.getString("withdrawals_no"));
                do2.setShopName(rs.getString("nickname"));
                if("商家".equals(type)){
                    goodType = rs.getString("title");
                    percentage = rs.getString("percentage");
                }else if("用户".equals(type)){
                    goodType = "用户提现";
                    percentage = df.format((rs.getDouble("commission")/(Double.parseDouble(rs.getString("amount"))+Double.parseDouble(rs.getString("voucher"))))*100)+"%";
                }
                do2 .setGoodType(goodType);
                do2.setPercent(percentage);
                do2.setUserName(rs.getString("user_name"));
                do2.setOrderType("提现");
                do2.setPayType("提现");
                do2.setBankName(rs.getString("bank_card"));
                do2.setOrderStatus(WithdrawalsStatus.getMsgByCode(rs.getString("status")));
                do2.setOrderMoney("-"+rs.getString("amount"));
                do2.setRealMoney("-"+rs.getString("amount"));
                do2.setVoucher("-"+rs.getString("voucher"));
                do2.setCommission(rs.getDouble("commission"));
                list.add(do2);
                return list;
            }
        });
        return list;
    }
}
