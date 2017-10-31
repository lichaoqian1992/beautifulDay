package com.manji.finance.withdrawals.Synchronization.impl;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-4-17.  SqlServer
 */
public class UserWithdrawalsRepository extends DButils{

    /**
     * 通过userid获取用户全部提现记录(异常提现)
     * @param userid
     * @return
     */
    public List<Record> findUserWithdrawalsByuserid(String userid){
        String sql="select * from dt_user_withdrawals where user_id=?";
        return sqlserver.find(sql,userid);
    }

    /**
     * 通过用户id查询消费总金额(异常提现)
     * @param userid
     * @return
     */
    public double getOdersSumByUser(int userid,String type){
        String sql="select SUM(order_amount) from dt_orders where user_id=? and user_role_type=?";
        BigDecimal sumAmount=sqlserver.queryBigDecimal(sql,userid,type);
        return sumAmount.doubleValue();
    }

    /**
     * 通过用户id查询消费总金额条数(异常提现)
     * @param userid
     * @param type
     * @return
     */
    public int getgetOdersCountByUser(int userid,String type){
        String sql="select COUNT(*) from dt_orders where user_id=? and user_role_type=?";
        int sumAmount=sqlserver.queryInt(sql,userid,type);
        return sumAmount;
    }


    /**
     * 根据用户id查询用户对象
     * @return
     */
    public Record getUserByUserid(int  userid){
        return  sqlserver.findById("dt_users",userid);
    }


    /**
     *  根据用户id查询用户最近登陆时间
     * @return
     */
    public String getUserLoginDateByUserid(int  userid){
        String sql="select max(login_time) from dt_user_login_log where user_id=? ;";
        Date date=sqlserver.queryDate(sql,userid);
        if (date!=null){
            return date.toString();
        }else{
            return null;
        }


    }


    /**
     *查询在login表有无数据
     */
    public int getMaxCountUserLoginLogIPcount(int userid){
        String sql="select COUNT(*) i from dt_user_login_log where user_id=?";
        return sqlserver.queryInt(sql,userid);
    }


    /**
     * 通过用户id查询此用户登录最多的ip地址
     * @param userid
     * @return
     */
    public String getMaxCountUserLoginLogIP(int userid){
        String sql="select login_ip i,count(*) ccount from dt_user_login_log where user_id=? group by login_ip order by ccount desc;";
        List<Record> record=sqlserver.find(sql,userid);
        if (record!=null){
            return record.get(0).getStr("i");
        }else{
            return null;
        }

    }


    /**
     * 通过提现时间得到最近的一条登录记录
     * @param addTime,userid
     * @return
     */
    public Record getMaxUserLoginLog(int userid,String addTime) {
        String sql = "select * from dt_user_login_log where login_time=(select max(login_time) from dt_user_login_log where user_id=? and login_time<?);";
        return sqlserver.findFirst(sql,userid,addTime);
    }

    /**
     * 通过用户id查询此用户订单条数
     * @param userid
     * @return
     */
    public int getOSumByUser(int  userid){
        String sql="select COUNT(*) from dt_Orders where user_id = ?";
        return sqlserver.queryInt(sql,userid);
    }


    /**
     * 通过用户id查询此用户充值条数
     * @param userid
     * @return
     */
    public int getRSumByUser(int  userid){
        String sql="select COUNT(*) from dt_user_recharge where user_id = ?";
        return sqlserver.queryInt(sql,userid);
    }

    /**
     * 通过用户id查询此用户充值条数
     * @param userid
     * @return
     */
    public int getTSumByUser(int  userid){
        String sql="select COUNT(*) from dt_user_transaction where user_id = ?";
        return sqlserver.queryInt(sql,userid);
    }


    /**
     * 查询此用户本月期末
     * @param Withdrawals
     * @return
     */
    public double getUserAmountNewValue(UserWithdrawalsDO Withdrawals){
        String sql="select new_value from dt_user_amount_log l where l.add_time=(select max(add_time) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?)) and user_id=?;";
        return sqlserver.queryBigDecimal(sql,Withdrawals.getUserId(),Withdrawals.getAdd_time(),Withdrawals.getAdd_time(),Withdrawals.getUserId()).doubleValue();
    }


    /**
     * 查询此用户本月期初
     * @param Withdrawals
     * @return
     */
    public double getUserAmountOldValue(UserWithdrawalsDO Withdrawals){
        String sql="select old_value from dt_user_amount_log l where l.add_time=(select min(add_time) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?))and user_id=?;";
        return   sqlserver.queryBigDecimal(sql,Withdrawals.getUserId(),Withdrawals.getAdd_time(),Withdrawals.getAdd_time(),Withdrawals.getUserId()).doubleValue();
    }

    /**
     * 查询本月消费金额总和
     * @param Withdrawals
     * @return
     */
    public double getUserAmountSumValue(UserWithdrawalsDO Withdrawals){
        String sql="select SUM(value) from dt_user_amount_log where user_id=? and year(add_time)=year(?) and month(add_time)=month(?) and user_id=?;";
        return sqlserver.queryBigDecimal(sql,Withdrawals.getUserId(),Withdrawals.getAdd_time(),Withdrawals.getAdd_time(),Withdrawals.getUserId()).doubleValue();
    }


    WithdrawalsRepository withdrawalsRepository=new WithdrawalsRepository();

    /**
     * 查询新增加的提现数据  (同步提现)
     * @return
     */
    public List<UserWithdrawalsDO> tfindUserWithdrawals(){

         List<UserWithdrawalsDO> lido=new ArrayList<UserWithdrawalsDO>();
        //获取上一次同步到的时间
        String prveTime=withdrawalsRepository.getMaxtime();
        //获取上一次同步到的id
        Integer prveId=withdrawalsRepository.getMaxid();
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        String date=sdf.format(new Date());
        if (prveTime==null||prveTime.equals("null")){
            prveTime="2017-7-10 15:00:00";
        }
        String sql="select * from dt_user_withdrawals where add_time>? and add_time<? and id>?";
        List<Record> list=sqlserver.find(sql,prveTime,date,prveId);
        for (int i=0;i<list.size();i++){
            UserWithdrawalsDO withdrawalsDO = new UserWithdrawalsDO();
            withdrawalsDO.setId(list.get(i).getLong("id"));
            withdrawalsDO.setUserId(list.get(i).getInt("user_id").toString());
            withdrawalsDO.setUserRoleTyoe(list.get(i).getStr("user_role_type"));
            withdrawalsDO.setUserRoleValue(list.get(i).getInt("user_role_value"));
            withdrawalsDO.setAccountId(list.get(i).getInt("account_id"));
            withdrawalsDO.setWithdrawalsNo(list.get(i).getStr("withdrawals_no"));
            withdrawalsDO.setBankName(list.get(i).getStr("bank_name"));
            withdrawalsDO.setBankCard(list.get(i).getStr("bank_card"));
            withdrawalsDO.setBankArea(list.get(i).getStr("bank_area") == null ? "" : list.get(i).getStr("bank_area"));
            withdrawalsDO.setBankAddress(list.get(i).getStr("bank_address") == null ? "" : list.get(i).getStr("bank_address"));
            withdrawalsDO.setAmount(list.get(i).getBigDecimal("amount").doubleValue());
            withdrawalsDO.setStatus(list.get(i).get("status").toString());
            withdrawalsDO.setAdd_time(list.get(i).get("add_time").toString());
            withdrawalsDO.setComplete_time(list.get(i).get("complete_time").toString());
            withdrawalsDO.setTransactionNo(list.get(i).getStr("transaction_no"));
            withdrawalsDO.setRemark(list.get(i).getStr("remark"));
            withdrawalsDO.setIsDel(list.get(i).get("is_del").toString());
            withdrawalsDO.setCommission(list.get(i).getBigDecimal("commission").doubleValue());
            withdrawalsDO.setBank_user(list.get(i).getStr("bank_user") == null ? "" : list.get(i).getStr("bank_user"));
            withdrawalsDO.setVoucher(list.get(i).getBigDecimal("voucher").doubleValue());
            withdrawalsDO.setTotalMoney(list.get(i).getBigDecimal("total_money").doubleValue());
            lido.add(withdrawalsDO);
        }
        return lido;
    }








}
