package com.manji.finance.order.accountErrInfo;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-8-9.(YYR)
 */
public class accountErrInfoRepository extends DButils{

    /**
     * 通过账户和账户类型分组(额额额...)
     * @return
     */
    public List<Record> groupByUsers(){

        String sql="select user_id,user_role_type from dt_user_amount_log  group by user_id,user_role_type order by user_id asc;";
        return sqlserver.find(sql);
    }

    /**
     * 通过user_id,user_type查询此账户下全部交易记录信息(id,old_value,value,new_value)
     * @param user_Id
     * @param user_type
     * @return
     */
    public List<Record> getLogInfoByUsersAndUserType(int user_Id,String user_type){

        String sql="select id,old_value,value,new_value from dt_user_amount_log where user_id=? and user_role_type='?' order by id asc;";
        return sqlserver.find(sql,user_Id,user_type);
    }

    /**
     *通过本条日志记录查询上一条日志记录
     * @param id
     * @param user_id
     * @param user_type
     * @return
     */
    public Record getPrveLog(String id,int user_id,String user_type){

        String sql="select TOP 1 id,old_value,value,new_value  from dt_user_amount_log where id<? and  user_id=? and user_role_type=? order by  id desc;";
        return sqlserver.findFirst(sql,id,user_id,user_type);
    }

    /**
     * 查询一段时间里面的全部日志记录(时间格式示例:2017-6-10 00:00:00.000)
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Record> findDayLog(String startTime,String endTime){

        String sql="select id,old_value,value,new_value,user_id,user_role_type,add_time from dt_user_amount_log where add_time>=? and add_time<? and user_role_type in('Shop','Buyer');";
        return sqlserver.find(sql,startTime,endTime);
    }

    /**
     * 添加异常数据id到本地数据库
     * @param log_id
     */
    public void insertLog(int log_id){

        String sql="insert into t_account_exeption values(null,?,?,0)";
        mysql.update(sql,new Date(),log_id);
    }

    /**
     * 添加检测记录表
     * @param startTime//检测开始时间
     * @param endTime//检测结束时间
     * @param number//检测出的异常数量
     */
    public void insertLogLog(String startTime,String endTime,int number){

        String sql="insert into t_account_exeption_log values(null,?,?,?)";
        mysql.update(sql,startTime,endTime,number);
    }


    /**
     * 通过时间查询日志记录的id
     * @param time
     * @return
     */
    public List<Record> findAllLogId(String time){

        StringBuffer sql=new StringBuffer("select log_id from t_account_exeption where is_dispoy=0");
        if (!time.equals("")){
            sql.append(" and date_format(datet,'%Y-%m-%d')='"+time+"'");
        }
        return mysql.find(sql.toString());
    }


    /**
     * 查询金额日志信息(显示到界面)
     * @return
     */
    public Page<Record> findAllLogInfo(String logids,int pageNum){

        if (logids.equals("")){
            logids="-1";
        }

        String sql="from  dt_user_amount_log where id in("+logids+") order by add_time desc";

        return sqlserver.paginate(pageNum,20,"select *",sql);
    }


    /**
     * 修改是否处理状态
     */
    public int updateIs_dipoy(String log_id){
        String sql="update t_account_exeption set is_dispoy=1 where log_id=?";
        return mysql.update(sql,log_id);
    }

    /**
     * 第一次跑批量时需要的方法
     */
    public int findexeptioncount(){
        String sql="SELECT count(*) from t_account_exeption;";
        return Integer.parseInt(mysql.queryFirst(sql).toString());
    }





}
