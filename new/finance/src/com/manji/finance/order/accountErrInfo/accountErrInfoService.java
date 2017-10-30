package com.manji.finance.order.accountErrInfo;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.withdrawals.Account.UserAccountInfoRepository;
import com.manji.finance.withdrawals.Enums.OrderPaymentTypeEnums;
import com.manji.finance.withdrawals.Enums.OrderTypeEnums;
import com.manji.finance.withdrawals.Enums.UserRoleTypeEnums;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-8-9.（YYR）
 */
public class accountErrInfoService {

    private accountErrInfoRepository accountErrInfoRepository=new accountErrInfoRepository();

    private WithdrawalsService withdrawalsService=new WithdrawalsService();

    private UserAccountInfoRepository userAccountInfoRepository=new UserAccountInfoRepository();

    /**
     * 开始检测.....
     */
    public void runExepion(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String logstartTime=formatter.format(new Date());
        int number=0;
        //获取今日0点时间
        String endTime=getendTimeTime();

        //获取昨日0点时间
        String startTime=getstartTime();

        //第一次跑批量时的操作
        if (accountErrInfoRepository.findexeptioncount()==0){
            //开始执行检测账户方法
             number=isExepion("",endTime);
        }else{
            //开始执行检测账户方法
             number=isExepion(startTime,endTime);
        }
        accountErrInfoRepository.insertLogLog(logstartTime,formatter.format(new Date()),number);
    }

    /**
     * 判定日志是否异常(boom，正式进入业务逻辑....boom.boom.boom)
     * @return
     */
    public int  isExepion(String startTime,String endTime){

        int number=0;
        List<Record> PLog=accountErrInfoRepository.findDayLog(startTime,endTime);

        for (int i=0;i<PLog.size();i++){
           Record prveLog= accountErrInfoRepository.getPrveLog(PLog.get(i).get("id").toString(),Integer.parseInt(PLog.get(i).get("user_id").toString()),PLog.get(i).getStr("user_role_type"));
            //如果没有查询出有上一条日志记录则不做验证
            if (prveLog==null){
                continue;
            }
            //验证...是否异常
            if (PLog.get(i).getBigDecimal("old_value")==null||prveLog.getBigDecimal("new_value").doubleValue()!=PLog.get(i).getBigDecimal("old_value").doubleValue()){
                accountErrInfoRepository.insertLog(Integer.parseInt(PLog.get(i).get("id").toString()));
                number++;
            }

        }
        //返回异常记录数
        return number;
    }


    /**
     * 处理日志id(用于展示)
      * @return
     */
   public String formatlogId(String time){
        //查询出所需日志数据
       List<Record> idlist=accountErrInfoRepository.findAllLogId(time);
       StringBuffer logIds=new StringBuffer();
       for (int i=0;i<idlist.size();i++){
            logIds.append(idlist.get(i).getInt("log_id")+",");
       }

       String ids=logIds.toString();
       //这里去掉最后一个逗号
       if (!ids.equals("")){
           ids=ids.substring(0,logIds.toString().length()-1);
       }

       return ids;
   }

    /**
     * 查询展示日志
     * @return
     */
    public Page<Record> findAllLogInfo(String time,int pageNum){

        String idlist=formatlogId(time);//处理需查询的日志id..
        Page<Record> page=accountErrInfoRepository.findAllLogInfo(idlist,pageNum);

        //让我们一起来处理数据类型让使用者更清晰的了解数据吧 ....
        List<Record> list=page.getList();
        //循环日志集合，走起来
        for (int i=0;i<list.size();i++){
            //处理账户信息
            if (list.get(i).getStr("user_role_type").equals("Shop")){
                //查询对应商家
                Record shopinfo=withdrawalsService.findShopInfo(list.get(i).get("user_id").toString());
                if (shopinfo==null){
                    list.get(i).set("user_name","");
                }else{
                    list.get(i).set("user_name",shopinfo.getStr("name"));
                }
            }else{
                //查询对应User信息
                Record users=userAccountInfoRepository.findUsersAll(list.get(i).get("user_id").toString());
                if (users!=null){
                    list.get(i).set("user_name",users.getStr("user_name"));
                }else{
                    list.get(i).set("user_name","");
                }
            }
            //处理账户类型
            list.get(i).set("user_role_type", UserRoleTypeEnums.getMsgByCode(list.get(i).getStr("user_role_type")));
            //处理订单类型
            list.get(i).set("type", OrderTypeEnums.getMsgByCode(list.get(i).getStr("type")));
        }
        return page;
    }

    public int updateisdispoy(String log_id){
        return accountErrInfoRepository.updateIs_dipoy(log_id);
    }


    /**
     * 获取今天0点时间
     */
    public String getendTimeTime(){

        //时间格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //开始生成时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return formatter.format(zero);
    };


    /**
     * 获取昨天0点时间
     */
    public String getstartTime(){

        //时间格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //开始生成时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //这里-1就是减一，今天减一就是昨天
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return  formatter.format(cal.getTime());
    };

}
