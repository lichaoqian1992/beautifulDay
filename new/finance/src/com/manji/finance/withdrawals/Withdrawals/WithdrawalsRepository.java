package com.manji.finance.withdrawals.Withdrawals;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.system.RechargeConfigRepository;
import com.manji.finance.utils.DButils;
import com.manji.finance.withdrawals.Account.UserAccountInfoService;
import com.manji.finance.withdrawals.Param.WithDrawalsParam;
import com.manji.finance.withdrawals.Synchronization.impl.UserWithdrawalsDO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-4-14.
 */
public class WithdrawalsRepository extends DButils{


    /**
     * 权限表数据访问层
     */
    RechargeConfigRepository repository=new RechargeConfigRepository();

    UserAccountInfoService userAccountInfoService=new UserAccountInfoService();
    /**
     * 查询所有提现(条件参数)
     * @return
     */
    public Page<Record> findAllWithdrawals(WithDrawalsParam param) {
        Page<Record> listw = new Page<Record>();
        if (param != null) {
            if (param.getPageNum()==0){
                param.setPageNum(1);
            }

            StringBuffer sql = new StringBuffer(" from  t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");

            if (param.getWithDrawalsId() != null) {
                sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
            }
            if (param.getUsername() != null) {
                Record users=findusersbyusername(param.getUsername());
                if (users!=null){
                    String user_id=users.getInt("id").toString();
                    sql.append(" and user_id = '" + user_id + "'");
                }

            }
            if (param.getAccountType() != null) {
                sql.append(" and user_role_type='" + param.getAccountType() + "'");
            }
            if (param.getStatus() != null) {
                if (param.getStatus().equals("冻结提现账户")){
                    String accountId=userAccountInfoService.findstatcinfo();
                    if (!accountId.equals("")){
                        sql.append("and account_id in("+accountId+")");
                    }
                }else{
                    sql.append(" and status='" + param.getStatus() + "'");
                }

            }
            if (param.getStartTime() != null) {
                sql.append(" and add_time>'" + param.getStartTime() + "'");
            }
            if (param.getEndTime() != null) {
                sql.append(" and add_time<='" + param.getEndTime() + "'");
            }
            if (param.getDetailed() != null) {
                sql.append(" and detailed='" + param.getDetailed() + "'");
            }
            if (param.getExceptions() != null) {
                sql.append("and exceptions='" + param.getExceptions() + "'");
            }
            //查询对应权限
            if (param.getRole() != null) {
                Record RechargeConfig = repository.getRechargeConfig(param.getRole());
                sql.append("and total_money>" + RechargeConfig.getDouble("T_SINGLE_MIN_MONEY") + " and total_money<=" + RechargeConfig.getDouble("T_SINGLE_MAX_MONEY") + "");
            }
            if (param.getConfirmwhether() != null) {
                sql.append(" and confirmwhether='" + param.getConfirmwhether() + "'");
            }
            sql.append("order by id desc");
             listw= mysql.paginate(param.getPageNum(),20,"select *",sql.toString());
        }
        return listw;
    };




    /**
     * 查询所有提现(条件参数)(提现审核页面)(提现确认页面)
     * @return
     */
    public Page<Record> findAllWithdrawalssh(WithDrawalsParam param) {
        Page<Record> listw = new Page<Record>();
        if (param != null) {
            if (param.getPageNum()==0){
                param.setPageNum(1);
            }

            StringBuffer sql = new StringBuffer(" from  t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");

            if (param.getWithDrawalsId() != null) {
                sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
            }
            if (param.getUsername() != null) {
                Record users=findusersbyusername(param.getUsername());
                if (users!=null){
                    String user_id=users.getInt("id").toString();
                    sql.append(" and user_id = '" + user_id + "'");
                }

            }
            if (param.getAccountType() != null) {
                sql.append(" and user_role_type='" + param.getAccountType() + "'");
            }
            if (param.getStatus() != null) {
                if (param.getStatus().equals("冻结提现账户")){
                    String accountId=userAccountInfoService.findstatcinfo();
                    if (!accountId.equals("")){
                        sql.append("and account_id in("+accountId+")");
                    }
                }else{
                    sql.append(" and status='" + param.getStatus() + "'");
                }

            }
            if (param.getStartTime() != null) {
                sql.append(" and add_time>'" + param.getStartTime() + "'");
            }
            if (param.getEndTime() != null) {
                sql.append(" and add_time<='" + param.getEndTime() + "'");
            }
            if (param.getDetailed() != null) {
                sql.append(" and detailed='" + param.getDetailed() + "'");
            }
            if (param.getExceptions() != null) {
                sql.append("and exceptions='" + param.getExceptions() + "'");
            }
            //查询对应权限
            if (param.getRole() != null) {
                Record RechargeConfig = repository.getRechargeConfig(param.getRole());
                sql.append("and total_money>" + RechargeConfig.getDouble("T_SINGLE_MIN_MONEY") + " and total_money<=" + RechargeConfig.getDouble("T_SINGLE_MAX_MONEY") + "");
            }
            if (param.getConfirmwhether() != null) {
                sql.append(" and confirmwhether='" + param.getConfirmwhether() + "'");
            }
            sql.append("order by id desc");
            listw= mysql.paginate(param.getPageNum(),10000,"select *",sql.toString());
        }
        return listw;
    };





    /**
     * 查询所有提现(条件参数)(提现记录页面)
     * @return
     */
    public Page<Record> findAllWithdrawalss(WithDrawalsParam param) {
        Page<Record> listw = new Page<Record>();
        if (param != null) {
            if (param.getPageNum()==0){
                param.setPageNum(1);
            }

            StringBuffer sql = new StringBuffer(" from  t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");

            if (param.getWithDrawalsId() != null) {
                sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
            }
            if (param.getUsername() != null) {
                Record users=findusersbyusername(param.getUsername());
                if (users!=null){
                    String user_id=users.getInt("id").toString();
                    sql.append(" and user_id = '" + user_id + "'");
                }

            }
            if (param.getAccountType() != null) {
                sql.append(" and user_role_type='" + param.getAccountType() + "'");
            }
            if (param.getStatus() != null) {
                if (param.getStatus().equals("冻结提现账户")){
                    String accountId=userAccountInfoService.findstatcinfo();
                    if (!accountId.equals("")){
                        sql.append("and account_id in("+accountId+")");
                    }
                }
            }
            sql.append(" and status in(2,4,5)");
            if (param.getStartTime() != null) {
                sql.append(" and add_time>'" + param.getStartTime() + "'");
            }
            if (param.getEndTime() != null) {
                sql.append(" and add_time<='" + param.getEndTime() + "'");
            }
            if (param.getDetailed() != null) {
                sql.append(" and detailed='" + param.getDetailed() + "'");
            }
            if (param.getExceptions() != null) {
                sql.append("and exceptions='" + param.getExceptions() + "'");
            }
            //查询对应权限
            if (param.getRole() != null) {
                Record RechargeConfig = repository.getRechargeConfig(param.getRole());
                sql.append("and total_money>" + RechargeConfig.getDouble("T_SINGLE_MIN_MONEY") + " and total_money<=" + RechargeConfig.getDouble("T_SINGLE_MAX_MONEY") + "");
            }
            if (param.getConfirmwhether() != null) {
                sql.append(" and confirmwhether='" + param.getConfirmwhether() + "'");
            }
            sql.append("order by id desc");
            listw= mysql.paginate(param.getPageNum(),20,"select *",sql.toString());
        }
        return listw;
    };

    /**
     * 通过账户名称查询对应users
     */
    public Record findusersbyusername(String username){
        String sql="select * from dt_users where user_name=?";
        return sqlserver.findFirst(sql,username);
    }


    /**
     * 通过id查询单条提现记录
     * @return
     */
    public Record getTUserWithdrawals(String id){
        String sql="select * from t_user_withdrawals where id=?";
        Record record=mysql.findFirst(sql,id);
        return  record;
    }

    /**
     * 通过user_id查询此用户的全部提现记录
     */
    public List<Record> getTUserWithdrawalsByuserId(String userId){
        String sql="select * from dt_user_withdrawals where user_id=?";
        List<Record> recordList=sqlserver.find(sql,userId);
        return recordList;
    }




    /**
     * 获取最后一条提现信息的时间   (同步提现记录)
     * @return
     */
    public String getMaxtime(){
        String sql="select MAX(t.add_time) from t_user_withdrawals t;";
        Date data=mysql.queryDate(sql);
        if (data!=null){
            return data.toString();
        }else {
            return null;
        }

    }

    /**
     * 获取最后一条提现记录id(同步提现记录)
     */
    public int getMaxid(){
        String sql="select MAX(t.id) from t_user_withdrawals t;";
        Long id=mysql.queryLong(sql);

        if (id==null){
            return -1;
        }else{
            return  Integer.parseInt(id.toString());
        }

    }


    /**
     * 添加数据
     * @param withdrawals  (同步提现记录)
     */
    public void insertTUserWithdrawals(UserWithdrawalsDO withdrawals, int exceptions, String alert){

        String sql="insert into t_user_withdrawals VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        mysql.update(sql,new Object[]{withdrawals.getId(),withdrawals.getUserId(),withdrawals.getUserRoleTyoe()
                ,withdrawals.getUserRoleValue(),withdrawals.getAccountId(),withdrawals.getWithdrawalsNo(),withdrawals.getBankName()
                ,withdrawals.getBankCard(),withdrawals.getBankArea(),withdrawals.getBankAddress(),withdrawals.getAmount()
                ,withdrawals.getStatus(),withdrawals.getAdd_time(),withdrawals.getComplete_time(),withdrawals.getTransactionNo()
                ,withdrawals.getRemark(),withdrawals.getIsDel(),withdrawals.getCommission(),withdrawals.getBank_user()
                ,withdrawals.getVoucher(),withdrawals.getTotalMoney(),exceptions,alert,0,0,0});
    }


    /**
     * 查询用户详细信息(用于提现详情展示)
     * @param userId
     * @return
     */
    public Record findBuyerInfoDO(String userId){
        String sql="select a.user_name,b.person_name,b.card_number from dt_users a,dt_user_personinfo b where a.id=b.user_id and  a.id=?;";
        Record record=sqlserver.findFirst(sql,userId);
        return record;
    }


    /**
     * 查询商家详细信息(用于提现详情展示)
     * @param userId
     * @return
     */
    public Record findShopInfo(String userId){
        String sql="select a.user_name,b.name,c.title as TAG,b.area ,a.mobile ,b.telephone ,b.msg_mobile,b.mobile as shopmoble,b.area  from dt_users a left join dt_user_role_shopinfo b on a.id=b.user_id left join dt_article_category c on b.main_business=c.id where a.id=?;";
        Record record=sqlserver.findFirst(sql,userId);
        return record;
    }

    /**
     * 查询技术服务费
     */
    public Record findbusiness(String userId){
        String sql="select * from dt_business_user where user_id=?";
        Record record=sqlserver.findFirst(sql,userId);
        return record;
    }

    /**
     * 查询用户公司信息
     */
    public Record findCompanyinfo(String userId){
        String sql="select * from dt_user_companyinfo where user_id=?";
        Record record=sqlserver.findFirst(sql,userId);
        return record;
    }

    /**
     * 查询银行详细信息
     */
    public Record findBankinfo(String userId){
        String sql="select * from dt_user_banktype where user_id=?;";
        Record record=sqlserver.findFirst(sql,userId);
        return record;
    }

    /**
     * 查看资质证件
     */
    public List<Record> findShopinfoOther(String userId){
        String sql="select * from dt_user_role_shopinfo_other where shop_user_id=?;";
        List<Record> record=sqlserver.find(sql,userId);
        return record;
    }




    /**
     * 通过userid查询此账户正在处理提现金额
     */
    public Double findwithdrawalsCL(String userId){
        String sql="select sum(total_money) i from t_user_withdrawals  where status in(0,4) and user_id=?;";
        BigDecimal m =mysql.queryBigDecimal(sql,userId);
        Double moy;
        if (m==null){
            moy=0.00;
        }else{
            moy=m.doubleValue();
        }
        return moy;
    }


    /**
     * 通过userid查询此账户冻结提现金额
     */
    public Double findwithdrawalsDJ(String userId){
        String sql="select sum(total_money) i from t_user_withdrawals  where status =3 and user_id=?;";
        BigDecimal m =mysql.queryBigDecimal(sql,userId);
        Double moy;
        if (m==null){
            moy=0.00;
        }else{
            moy=m.doubleValue();
        }
        return moy;
    }


    /**
     * 通过userid查询此账户提现在途
     */
    public Double findwithdrawalsZT(String userId){
        String sql="select sum(total_money) i from t_user_withdrawals  where status not in (1,2,5) and user_id=?;";
        BigDecimal m =mysql.queryBigDecimal(sql,userId);
        Double moy;
        if (m==null){
            moy=0.00;
        }else{
            moy=m.doubleValue();
        }
        return moy;
    }


    /**
     * 验证此用户是否存在账户
     */
    public int getAccountCount(String id){
        String sql="select COUNT(*) from dt_user_accountinfo where id=?" ;
        int count=sqlserver.queryInt(sql,id);
        return count;
    }

    /**
     * 通过id查询此账户余额
     */
    public Double getAccountinfoAmountSumByid(String id){
        if (getAccountCount(id)==0){
            return 0.00;
        }
        String sql="select amount from dt_user_accountinfo where id=?;";
        BigDecimal m =sqlserver.queryBigDecimal(sql,id);
        Double moy;
        if (m==null){
            moy=0.00;
        }else{
            moy=m.doubleValue();
        }
        return moy;
    }


    /**
     * 通过id查询此账户可提现金额
     */
    public Double getAllowAmountinfoAmountSumByid(String id){
        if (getAccountCount(id)==0){
            return 0.00;
        }
        String sql="select allow_amount from dt_user_accountinfo where id=?;";
        BigDecimal m =sqlserver.queryBigDecimal(sql,id);
        Double moy;
        if (m==null){
            moy=0.00;
        }else{
            moy=m.doubleValue();
        }
        return moy;
    }

    /**
     * 通过userId查询金额变动表
     */
    public Page<Record> findUserAccountLogDOByUserId(String userId,String userType,int page,String startTime,String endTime,String wstatus){
        StringBuffer sql= new StringBuffer("from dt_user_amount_log t where t.user_id="+userId+" ");
        if (userType!=null){
            sql.append( " and t.user_role_type='"+userType+"'");
        }
        if (!startTime.equals("")){
            sql.append("and t.add_time>'"+startTime+"'");
        }
        if (!endTime.equals("")){
            sql.append("and t.add_time<'"+endTime+"'");
        }
        if (!wstatus.equals("0")){
            if (wstatus.equals("1")){
                //查询可以提现的订单类型
                if (userType!=null&&userType.equals("Shop")){
                    sql.append("and t.type in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','OrderBack','OrderSettlement','FixedAccount') and (remark not like '%不能%' and remark not like '%不可%') ");
                }else{
                    sql.append("and t.type in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','FixedAccount') and (remark not like '%不能%' and remark not like '%不可%') ");
                }
            }else {
                if (userType!=null&&userType.equals("Shop")){
                    sql.append("and t.type not in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','OrderBack','OrderSettlement')");
                }else{
                    sql.append("and t.type not in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack') ");
                }

            }
        }
        sql.append(" order by t.add_time desc");
        int size=UserAccountLogDOByUserIdcount(userId).size();
        if (size==0){
            size=1;
        }
        return sqlserver.paginate(page,size,"select t.id,t.add_time,t.value as val,t.old_value,t.new_value,t.type,t.order_no,t.remark,t.user_id",sql.toString());
    }

    public List<Record> UserAccountLogDOByUserIdcount(String user_id){
        return sqlserver.find("select * from dt_user_amount_log where user_id=?",user_id);
    }

    /**
     * 通过userId查询代金券变动表
     */
    public Page<Record> findUserVoucherLogDOByUserId(String userId,int page,String startTime,String endTime){
        StringBuffer sql= new StringBuffer("from dt_user_voucher_log t where 1=1 ");
        sql.append("and t.user_id="+userId+" ");
        if (!startTime.equals("")){
            sql.append("and t.add_time>'"+startTime+"'");
        }
        if (!endTime.equals("")){
            sql.append("and t.add_time<'"+endTime+"'");
        }
        sql.append("order by t.add_time desc");

        int size=findUserVoucherLogDOByUserIdsize(userId).size();
        if (size==0){
            size=1;
        }
        return sqlserver.paginate(page,size,"select  t.add_time,t.value as val,t.old_value,t.new_value,t.type,t.order_no,t.remark,t.user_id",sql.toString());
    }

    public List<Record> findUserVoucherLogDOByUserIdsize(String userId){
        return sqlserver.find("select * from dt_user_voucher_log where user_id=?",userId);
    }



    /**
     * 通过提现单号查询提现操作日志表
     * @param witdrawalsNo
     * @return
     */
    public List<Record> findWithdrawalslog(String  witdrawalsNo) {
        StringBuffer sql = new StringBuffer("select * from t_widrawals_log where witdrawals_no=? ");
            return mysql.find(sql.toString(),witdrawalsNo);
        };

    /**
     * 批量修改异常审核状态
     * @param id
     * @return
     */
    public Integer updateToexamine(String id){
        String sql="update t_user_withdrawals SET exceptions=0,examine =1 WHERE id=?;";
        Integer i=mysql.update(sql,id);
        return i;
    }


    /**
     * 修改提现状态
     * @param id
     * @param statuts
     */
    public void updateStats(String id,String statuts,String remark){
        String sql="update t_user_withdrawals SET status=? ,remark =? WHERE id=?;";
        mysql.update(sql,statuts,remark,id);
    }


    /**
     * 查询对应权限待审核总金额
     * @return
     */
    public Double getSumMony(WithDrawalsParam param) {
        StringBuffer sql = new StringBuffer("select sum(total_money) count from t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");
        if (param.getWithDrawalsId()!=null) {
            sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
        }
        if (param.getAccountType()!=null) {
            sql.append(" and user_role_type='" + param.getAccountType() + "'");
        }
        if (param.getStatus()!=null) {
            sql.append(" and status='" + param.getStatus() + "'");
        }
        if (param.getStartTime()!=null) {
            sql.append(" and add_time>'" + param.getStartTime() + "'");
        }
        if (param.getEndTime()!=null) {
            sql.append(" and add_time<='" + param.getEndTime() + "'");
        }
        if (param.getDetailed()!=null){
            sql.append(" and detailed='" + param.getDetailed() + "'");
        }
        sql.append("and exceptions='" + param.getExceptions() + "'");
        //查询对应权限
        if (param.getRole() != null) {
            Record RechargeConfig = repository.getRechargeConfig(param.getRole());
            sql.append("and total_money>" + RechargeConfig.getDouble("T_SINGLE_MIN_MONEY") + " and total_money<=" + RechargeConfig.getDouble("T_SINGLE_MAX_MONEY") + "");
        }
        BigDecimal b=mysql.queryBigDecimal(sql.toString());
        if (b!=null){
            return b.doubleValue();
        }else{
            return 0.00;
        }
    }


    /**
     * 对应权限审核员通过
     */
    public void updatedetailed(String id){
        String sql="update t_user_withdrawals SET detailed=1 WHERE id=?;";
        mysql.update(sql,id);
    }


    /**
     * 通过id查询(把此订单修改成异常订单)
     */
    public void updateExceptions(String id){
        String sql="update t_user_withdrawals SET exceptions=1 ,detailed=0 WHERE id=?;";
        mysql.update(sql,id);
    }

    /**
     * 正常订单已生成清单
     */
    public void updateStatsAnddetailed(String id,String statuts,String remark){
        String sql="update t_user_withdrawals SET status=? ,remark =?,detailed=1 WHERE id=?;";
        mysql.update(sql,statuts,remark,id);
    }

    /**
     * 修改出纳确认(完成,失败)状态
     */
    public void updateConfirmwhether(String id,String confirmwhether){
        String sql="update t_user_withdrawals SET confirmwhether=? WHERE id=?;";
        mysql.update(sql,confirmwhether,id);
    }

    /**
     * 查询所有提现(EXCL)
     * @return
     */
    public List<Record> getAllWithdrawals(WithDrawalsParam param) {
            StringBuffer sql = new StringBuffer("select * from  t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");

            if (param.getWithDrawalsId() != null) {
                sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
            }
            if (param.getAccountType() != null) {
                sql.append(" and user_role_type='" + param.getAccountType() + "'");
            }
            if (param.getIdlist()!=null&&!param.getIdlist().equals("")){
                sql.append(" and id in (" + param.getIdlist() + ")");
            }
                sql.append(" and status in(2,4,5)");

            if (param.getStartTime() != null) {
                sql.append(" and add_time>'" + param.getStartTime() + "'");
            }
            if (param.getEndTime() != null) {
                sql.append(" and add_time<='" + param.getEndTime() + "'");
            }
            if (param.getDetailed() != null) {
                sql.append(" and detailed='" + param.getDetailed() + "'");
            }
            if (param.getExceptions() != null) {
                sql.append("and exceptions='" + param.getExceptions() + "'");
            }
            //查询对应权限
            if (param.getRole() != null) {
                Record RechargeConfig = repository.getRechargeConfig(param.getRole());
                sql.append("and total_money>" + RechargeConfig.getDouble("T_SINGLE_MIN_MONEY") + " and total_money<=" + RechargeConfig.getDouble("T_SINGLE_MAX_MONEY") + "");
            }
            if (param.getConfirmwhether() != null) {
                sql.append(" and confirmwhether='" + param.getConfirmwhether() + "'");
            }
        List<Record> listw= mysql.find(sql.toString());
        return listw;
    };

    /**
     * 通过userid查询转账
     * @return
     */
    public Record getOutuser(String userId,String orderNo){
        String sql="select * from dt_user_transaction where user_id=? and transaction_no=?";
        return sqlserver.findFirst(sql,userId,orderNo);
    }

    /**
     * 通过userid查询转账
     * @return
     */
    public Record getInuser(String userId,String orderNo){
        String sql="select * from dt_user_transaction where touser_id=? and transaction_no=?";
        return sqlserver.findFirst(sql,userId,orderNo);
    }

    /**
     * 查询退款对方user_id
     */
    public Record getbackinfo(String order_no){
        String sql="select * from dt_order_backinfo where order_no=?";
        return sqlserver.findFirst(sql,order_no);
    }

    /**
     * 通过订单id查询订单
     */
    public Record getOrdersByid(String id){
        String sql="select * from dt_orders where id=?";
        return  sqlserver.findFirst(sql,id);
    }

    /**
     * 通过订单编号查询订单
     */
    public Record getOrdersByorderno(String orderno){
        String sql="select * from dt_orders where order_no=?";
        return  sqlserver.findFirst(sql,orderno);
    }


    /**
     * 账户数据
     */
    public Record userSelect(int userId) {
        Record userSelect=sqlserver.findFirst("select user_name,nick_name from dt_users where id=?",userId);
        return userSelect;
    }

    /**
     * 账户数据
     */
    public List<Record> accountSelect(int userId) {
        List<Record> acSelect=sqlserver.find("select * from dt_user_accountinfo where user_id=?",userId);
        return acSelect;
    }

    /**
     * 在线充值数据
     */
    public Page<Record> accountSelect(int pageNumber, int pageSize,int userId,String resNO) {
        StringBuffer sql=new StringBuffer("from t_interior_recharge_detail where USER_ID=?");
        if(!resNO.equals("") && resNO!=null){
            sql.append(" and RECHARGE_ORDER_NO = '"+resNO+"'");
        }
        return mysql.paginate(pageNumber,pageSize,"select *",sql.toString(),userId);
    }

    /**
     * 提现数据
     */
    public Page<Record> widSelect(int pageNumber, int pageSize,int userId,String widNO) {
        StringBuffer sql=new StringBuffer("from dt_user_withdrawals where user_id=?");
        if(!widNO.equals("") && widNO!=null){
            sql.append(" and withdrawals_no = '"+widNO+"'");
        }
        return sqlserver.paginate(pageNumber,pageSize,"select *",sql.toString(),userId);
    }

    /**
     *通过提现金额查询对应审核权限
     */
    public Record findconfig(String musum){
        String sql="select * from t_recharge_config where T_SINGLE_MIN_MONEY<=? AND T_SINGLE_MAX_MONEY>?;";
        return mysql.findFirst(sql,musum,musum);
    }


    /**
     * 通过本次提现单号查询离本次最近的一次提现
     */
    public Record findJW(String user_id,String w_no){
        String sql="select add_time from dt_user_amount_log where add_time<(select add_time from dt_user_amount_log where type in ('WithDrawals','WithDrawalsAuto') and order_no=? and user_id=? and user_role_type='Shop') and user_id=? and  type in('WithDrawals','WithDrawalsAuto') and user_role_type='Shop' order by add_time desc ;";
        return sqlserver.findFirst(sql,w_no,user_id,user_id);
    }

    /**
     * 查询本次提现单号时间
     */
    public Record findBW(String user_id,String w_no){
        String sql="select add_time from dt_user_amount_log where type in ('WithDrawals','WithDrawalsAuto') and order_no=? and user_id=? and user_role_type='Shop'";
        return sqlserver.findFirst(sql,w_no,user_id);
    }


    /**
     * 查询出中间的订单
     */
    public List<Record> findOrderByWtime(String user_id,String ktime,String jtime){
        StringBuffer sql=new StringBuffer("select * from dt_user_amount_log where user_id=? and user_role_type='Shop' and type='OrderSettlement'");
        if (ktime!=null){
            sql.append(" and add_time>='"+ktime+"'");
        }
        if (jtime!=null){
            sql.append(" and add_time<'"+jtime+"'");
        }

        return sqlserver.find(sql.toString(),user_id);
    }

    /**
     * 查询订单详细信息
     */
    public Record findOrderinfo(String order_no){
        if (order_no.contains("VTL")){ //代金券处理
                order_no= order_no.substring(0,order_no.indexOf("-"));
        }
        String sql="select dt_orders.complete_time,dt_orders.real_amount,dt_business_user.user_percentage,Round(dt_business_user.user_percentage*dt_orders.order_amount,2) as sxf,b.balance_amount,dt_orders.order_amount,dt_orders.voucher as ordervoucher from dt_orders left join dt_business_user on  dt_orders.shop_user_id=dt_business_user.user_id and dt_orders.order_type=dt_business_user.call_index left join dt_user_balance_log b on b.order_no=? and b.role_type=dt_orders.shop_user_role_type  where dt_orders.order_no=?;";
        return Db.findFirst(sql,order_no,order_no);
    }

    /**
     * 查询出中间的订单
     */
    public Record findzhyy(String user_id,String w_no){
        String sql="select * from dt_user_amount_log where user_id=? and order_no=?";
        return sqlserver.findFirst(sql,user_id,w_no);
    }

    /**
     *查询手续费状态
     */
    public Record findsxfStatic(String userid,String roletype){
        String sql="select withdrawals_commission from dt_user_accountinfo where user_id=? and role_type=?";
        return sqlserver.findFirst(sql,userid,roletype);
    }

    /**
     * 用户主动撤销提现
     */
    public int revocationNotification(String withdrawals_no){
        String sql="update t_user_withdrawals SET status=1,exceptions=1 where withdrawals_no=?";
        return mysql.update(sql,withdrawals_no);
    }


}
