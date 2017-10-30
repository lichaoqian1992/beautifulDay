package com.manji.finance.withdrawals.Synchronization.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.withdrawals.ExceptionSetting.ExceptionSettingRepository;
import com.manji.finance.withdrawals.Synchronization.AbnormalwithdrawalService;
import com.manji.finance.withdrawals.Account.UserAccountInfoRepository;


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


public class AbnormalwithdrawalServiceImpl implements AbnormalwithdrawalService {

    //异常数据类
    ExceptionSettingRepository exceptionSettingRepository=new ExceptionSettingRepository();


    //用户数据类
    UserAccountInfoRepository userAccountInfoRepository=new UserAccountInfoRepository();


    //SqlServer提现类
    UserWithdrawalsRepository userWithdrawalsRepository=new UserWithdrawalsRepository();


    /**
     * 此方法用来判定异常情况1(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean iswithdrawalone(UserWithdrawalsDO Withdrawals) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        long hour = 0;
        int dk=0;
        //获取此用户全部提现记录
        List<Record> wlist=userWithdrawalsRepository.findUserWithdrawalsByuserid(Withdrawals.getUserId());
        try {
            for (int i=0;i<wlist.size();i++){
                long time1 = df.parse(wlist.get(i).get("add_time").toString()).getTime();
                long time2= df.parse(Withdrawals.getAdd_time()).getTime();
                long diff;
                if (time1>time2){
                    diff=time1-time2;
                }else {
                    diff=time2-time1;
                }
                day = diff / (24 * 60 * 60 * 1000);
                hour = (diff / (60 * 60 * 1000) - day * 24);
                //获取管理员设置的小时数
                double houradmin=exceptionSettingRepository.getException_one();
                if (hour<houradmin){
                    dk+=1;
                }
         }
            //获取管理员设置条数
            double countadmin=exceptionSettingRepository.getException_one_tow();
            if (dk>countadmin){
                //连续提现了
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 此方法用来判定异常情况2(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean GreaterThanTheAmountOfConsumption(UserWithdrawalsDO Withdrawals) {
        if (Withdrawals.getTotalMoney()==null){
            return false;
        }
        //提现金额
        double WithdrawalsMoney=Withdrawals.getTotalMoney();

        //查询条数
        int i=userWithdrawalsRepository.getgetOdersCountByUser(Integer.parseInt(Withdrawals.getUserId()),Withdrawals.getUserRoleTyoe());
        if (i==0){
            return false;
        }
        //消费金额
        double Money=userWithdrawalsRepository.getOdersSumByUser(Integer.parseInt(Withdrawals.getUserId()),Withdrawals.getUserRoleTyoe());
        if (WithdrawalsMoney>Money){
            return false;
        }
        return true;
    }




    /**
     * 此方法用来判定异常情况4(请看提现异常情况需求)
     * @param Withdrawals
     * @return
     */
    @Override
    public boolean WithinHours(UserWithdrawalsDO Withdrawals) {
        Record user= userWithdrawalsRepository.getUserByUserid(Integer.parseInt(Withdrawals.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        try {
            if (Withdrawals.getAdd_time()==null){
                return false;
            }
            if (user==null||user.getDate("reg_time")==null){
                return false;
            }
            Date time1 = sdf.parse(Withdrawals.getAdd_time());
            Date time2 = sdf.parse(user.get("reg_time").toString());
            long cha = time1.getTime() - time2.getTime();
            if (cha<0){
                //提现申请时间大于用户注册时间
                return false;
            }
            double result = cha * 1.0 / (1000 * 60 * 60);
            //获取管理员设置时间
            double timeadmin=exceptionSettingRepository.getException_tow();
            if(result<timeadmin){
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
        String userLoginDate= userWithdrawalsRepository.getUserLoginDateByUserid(Integer.parseInt(Withdrawals.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        try {
            if (Withdrawals.getAdd_time()==null){
                return false;
            }
            Date time1 = sdf.parse(Withdrawals.getAdd_time());
            if (userLoginDate==null){
                return false;
            }
            Date time2=sdf.parse(userLoginDate);

            long cha = time1.getTime() - time2.getTime();

            if (cha<0){
                //用户提现之后登陆过系统
                return true;
            }
            double result = cha * 1.0 / (1000 * 60 * 60);
            //获取用户设置天数
            double dayadmin=exceptionSettingRepository.getException_three();
            if(result>24*dayadmin){
                //用户在提现内7天未登陆过
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
         //查询在login表有无数据
        int i=userWithdrawalsRepository.getMaxCountUserLoginLogIPcount(Integer.parseInt(Withdrawals.getUserId()));
        if (i==0){
            return  false;
        }
        //拿到用户常用IP地址
        String IP=userWithdrawalsRepository.getMaxCountUserLoginLogIP(Integer.parseInt(Withdrawals.getUserId()));
        //通过百度API来取得用户经常登录城市
        String caty=this.getAddressByIP(IP);
        //拿到用户提现时的登录信息
        Record userLoginLog=userWithdrawalsRepository.getMaxUserLoginLog(Integer.parseInt(Withdrawals.getUserId()),Withdrawals.getAdd_time());
        //拿到当时登录地址
        String currentCaty=this.getAddressByIP(userLoginLog.getStr("login_ip"));
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
        int ordersun=userWithdrawalsRepository.getOSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        //取得此提现用户充值条数
        int recharge=userWithdrawalsRepository.getRSumByUser(Integer.parseInt(Withdrawals.getUserId()));
        //取得此提现用户转账条数
        int transaction=userWithdrawalsRepository.getTSumByUser(Integer.parseInt(Withdrawals.getUserId()));
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
        if (Withdrawals.getAdd_time()==null){
            return false;
        }
        double newValue=userWithdrawalsRepository.getUserAmountNewValue(Withdrawals);
        double oldValue=userWithdrawalsRepository.getUserAmountOldValue(Withdrawals);
        double sumValue=userWithdrawalsRepository.getUserAmountSumValue(Withdrawals);
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
