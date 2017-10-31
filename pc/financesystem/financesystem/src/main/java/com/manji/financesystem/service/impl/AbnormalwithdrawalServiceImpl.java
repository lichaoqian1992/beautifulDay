package com.manji.financesystem.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.manji.financesystem.primaryDomain.entiity.UserLoginLogDO;
import com.manji.financesystem.primaryDomain.entiity.UserRechargeDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.repository.*;
import com.manji.financesystem.primaryDomain.entiity.UserDO;
import com.manji.financesystem.service.AbnormalwithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-3-13.
 */

//判定提现订单是否异常实现

@Service
public class AbnormalwithdrawalServiceImpl implements AbnormalwithdrawalService{
    @Autowired
    private AbnormalwithdrawalRepository abnormalwithdrawalRepository;

    @Autowired
    private OrdersDoRepository ordersDoRepository;

    @Autowired
    private UserDoRepository userDoRepository;

    @Autowired
    private UserAmountLogRepository userAmountLogRepository;

    @Autowired
    private UserAccountInfoRepository userAccountInfoRepository;

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;


    /**
     * 此方法用来判定异常情况1(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean isAbnormalwithdrawal(UserWithdrawalsDO Withdrawals) {

        boolean isok=true;
        //如果在平台当日有消费记录则直接跳出
        if (ordersDoRepository.getCountByUser(Withdrawals)!=0){
            return isok;
        }
        //查询出此用户当日充值的数据
        List<UserRechargeDO> lir=abnormalwithdrawalRepository.findAbnormalwithdrawalforUserRechargeDO(Withdrawals);
        //当日充值有一                                                                                                                           条以上才可能有连续充值的情况
        if (lir.size()>1){
            //判断出当日充值是否有俩小时内连续充值的
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long day = 0;
            long hour = 0;
            for (int i=0;i<lir.size();i++){
                UserRechargeDO userRechargeDO=lir.get(i);
                for (int j=0;j<lir.size();j++){
                    //当次充值记录不做比较直接结束本次判定，继续下次判定
                    if (j==i){
                        continue;
                    }
                    try {
                        UserRechargeDO userRechargeDOj=lir.get(j);
                        long time1 = df.parse(userRechargeDO.getAddTime()).getTime();
                        long time2 = df.parse(userRechargeDOj.getAddTime()).getTime();
                        long diff;
                        if (time1>time2){
                            diff=time1-time2;
                        }else {
                            diff=time2-time1;
                        }
                        day = diff / (24 * 60 * 60 * 1000);
                        hour = (diff / (60 * 60 * 1000) - day * 24);
                        if (hour<2){
                            //此订单为异常订单,在一时间段内（1-2）小时（！在此判定的是2小时）连续充值，在平台无任何消费记录下当天提现的。
                            isok=false;
                            return isok;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return isok;
    }


    /**
     * 此方法用来判定异常情况2(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean GreaterThanTheAmountOfConsumption(UserWithdrawalsDO Withdrawals) {
        //提现金额
        double WithdrawalsMoney=Withdrawals.getTotalMoney();

        //消费金额
        double Money=ordersDoRepository.getOdersSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        if (WithdrawalsMoney>Money){
            return false;
        }
        return true;
    }



    /**
     * 此方法用来判定异常情况3(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean CashWithdrawalRule(UserWithdrawalsDO Withdrawals) {

        //判断此用户是否是免手续费账户
        int txgz=userAccountInfoRepository.finduserAccount(Integer.parseInt(Withdrawals.getUserId()),Withdrawals.getUserRoleTyoe());
        if (txgz==1){
            return true;
        }

        //用户提现
        if (Withdrawals.getUserRoleTyoe().equals("Buyer")) {
            //1：用户每日单笔最低提现金额不低于50元（含）
            //2：用户提现超过规定金额    用户每日累计提现金额不超过3000；
            //3: 用户每日提现累计次数不超过2次
            //4:用户每月累计提现金额不超过20000元
            //5:用户每月提现累计次数不超过15次；
            if (Withdrawals.getTotalMoney() < 50 ||
                    userWithdrawalsRepository.findWithdrawalsCountmoany(Withdrawals) > 3000 ||
                    userWithdrawalsRepository.findWithdrawalsCount(Withdrawals) > 2 ||
                    userWithdrawalsRepository.findWithdrawalsMonthMoany(Withdrawals) > 20000 ||
                    userWithdrawalsRepository.findWithdrawalsMonthCount(Withdrawals) > 15) {
                    //用户不满足提现标准收取
                    double sx = Withdrawals.getTotalMoney() * 0.6 % +1;
                            if (Withdrawals.getCommission() != sx) {
                                //手续费错误
                                return false;
                            }
             }
            //商家提现
        }else if (Withdrawals.getUserRoleTyoe().equals("Shop")){
            if (Withdrawals.getTotalMoney() < 300){
                double sx = Withdrawals.getTotalMoney() * 0.6 % +1;
                if (Withdrawals.getCommission() != sx) {
                    //手续费错误
                    return false;
                }
            }else{
                if (Withdrawals.getCommission() != 1) {
                    //手续费错误
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 此方法用来判定异常情况4(请看提现异常情况需求)  !!!时间转换错误
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean WithinHours(UserWithdrawalsDO Withdrawals) {
        UserDO user= userDoRepository.getUserByUserid(Integer.parseInt(Withdrawals.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        try {
            Date time1 = sdf.parse(Withdrawals.getAdd_time());
            Date time2 = sdf.parse(user.getReg_time().toString());
            long cha = time1.getTime() - time2.getTime();
            if (cha<0){
                //提现申请时间大于用户注册时间
                return false;
            }
            double result = cha * 1.0 / (1000 * 60 * 60);
            if(result<24){
                //用户是在24小时内注册发起提现的
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 此方法用来判定异常情况5(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean DaysLanding(UserWithdrawalsDO Withdrawals) {
        //获得此用户最近登陆时间
        String userLoginDate= userDoRepository.getUserLoginDateByUserid(Integer.parseInt(Withdrawals.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        try {
            Date time1 = sdf.parse(Withdrawals.getAdd_time());
            Date time2=sdf.parse(userLoginDate);

            long cha = time1.getTime() - time2.getTime();

            if (cha<0){
                //用户提现之后登陆过系统
                return true;
            }
            double result = cha * 1.0 / (1000 * 60 * 60);
            if(result>24*7){
                //用户在提现前7天未登陆过
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 此方法用来判定异常情况6(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean AbnormalAreaLogin(UserWithdrawalsDO Withdrawals) {
        //拿到用户常用IP地址
        String IP=userDoRepository.getMaxCountUserLoginLogIP(Integer.parseInt(Withdrawals.getUserId()));
        //通过百度API来取得用户经常登录城市
        String caty=this.getAddressByIP(IP);
        //拿到用户提现时的登录信息
        UserLoginLogDO userLoginLog=userDoRepository.getMaxUserLoginLog(Integer.parseInt(Withdrawals.getUserId()),Withdrawals.getAdd_time());
        //拿到当时登录地址
        String currentCaty=this.getAddressByIP(userLoginLog.getLogin_ip());
        //把当时登录所在地址和常用地址做比较
        if (!currentCaty.equals(caty)){
            //不在常用登录地区发起的提现
            return false;
        }
        return true;
    }


    /**
     * 此方法用来判定异常情况7(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean ThereHasNeverBeenATransaction(UserWithdrawalsDO Withdrawals) {
        //取得此提现用户订单条数
        int ordersun=ordersDoRepository.getOSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        //取得此提现用户充值条数
        int recharge=abnormalwithdrawalRepository.getRSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        //取得此提现用户转账条数
        int transaction=abnormalwithdrawalRepository.getTSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        if (ordersun+recharge+transaction==0){
            //此用户从未有过交易
            return false;
        }

        return true;
    }



    /**
     * 此方法用来判定异常情况8(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean AbnormalPayment(UserWithdrawalsDO Withdrawals) {
        double newValue=userAmountLogRepository.getUserAmountNewValue(Withdrawals);
        double oldValue=userAmountLogRepository.getUserAmountOldValue(Withdrawals);
        double sumValue=userAmountLogRepository.getUserAmountSumValue(Withdrawals);
        if (oldValue+sumValue!=newValue){
            return false;
        }
        return true;
    }


    /**
     * 此方法调用百度AIP来查询IP所在地域
     * @param strIP
     * @return
     */
    public static String getAddressByIP(String strIP) {
        try {
            URL url = new URL("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            String ipAddr = result.toString();
            try {
                JSONObject obj1= JSON.parseObject(ipAddr);
                if("0".equals(obj1.get("status").toString())){
                    JSONObject obj2= JSON.parseObject(obj1.get("content").toString());
                    JSONObject obj3= JSON.parseObject(obj2.get("address_detail").toString());
                    return obj3.get("city").toString();
                }else{
                    return "读取失败";
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "读取失败";
            }

        } catch (IOException e) {
            return "读取失败";
        }
    }



    /**
     * 测试百度AIP是否调用正常
     * @param args
     */
    //public static void main(String[] args) {
      //  System.out.println(getAddressByIP("117.89.35.58"));
    //}



}
