package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.enums.IsDeleteEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.enums.WithdrawalsStatus;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.TUserWithdrawals;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
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
 * Created by pudding on 2017-3-16.
 */
@Repository
public class TUserWithdrawalsRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WithDrawalsRepository withDrawalsRepository;

    /**
     * 添加数据
     * @param withdrawals  (同步提现记录)
     */
    public void insertTUserWithdrawals(UserWithdrawalsDO withdrawals,int exceptions,String alert){

        String sql="insert into t_user_withdrawals VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,new Object[]{withdrawals.getId(),withdrawals.getUserId(),withdrawals.getUserRoleTyoe()
                ,withdrawals.getUserRoleValue(),withdrawals.getAccountId(),withdrawals.getWithdrawalsNo(),withdrawals.getBankName()
                ,withdrawals.getBankCard(),withdrawals.getBankArea(),withdrawals.getBankAddress(),withdrawals.getAmount()
                ,withdrawals.getStatus(),withdrawals.getAdd_time(),withdrawals.getComplete_time(),withdrawals.getTransactionNo()
                ,withdrawals.getRemark(),withdrawals.getIsDel(),withdrawals.getCommission(),withdrawals.getBank_user()
                ,withdrawals.getVoucher(),withdrawals.getTotalMoney(),exceptions,alert,null});
    }

    /**
     * 通过id查询单条提现记录
     * @return
     */
    public TUserWithdrawals getTUserWithdrawals(String id){
        final TUserWithdrawals wdo=new TUserWithdrawals();
        String sql="select * from t_user_withdrawals where id=?";
        jdbcTemplate.queryForObject(sql,new Object[]{id},new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                wdo.setId(rs.getLong("id"));
                wdo.setUserId(rs.getString("user_id"));
                wdo.setUserRoleTyoe(UserRoleTypeEnums.getMsgByCode(rs.getString("user_role_type")));
                wdo.setUserRoleValue(rs.getInt("user_role_value"));
                wdo.setWithdrawalsNo(rs.getString("withdrawals_no"));
                wdo.setAccountId(rs.getInt("account_id"));
                wdo.setBank_user(rs.getString("bank_user"));
                wdo.setBankCard(rs.getString("bank_card"));
                wdo.setBankName(rs.getString("bank_name"));
                wdo.setBankArea(rs.getString("bank_area"));
                wdo.setBankAddress(rs.getString("bank_address"));
                wdo.setAmount(rs.getDouble("amount"));
                wdo.setVoucher(rs.getDouble("voucher"));
                wdo.setCommission(rs.getDouble("commission"));
                wdo.setTotalMoney(rs.getDouble("total_money"));
                wdo.setStatus(WithdrawalsStatus.getMsgByCode(rs.getString("status")));
                wdo.setIsDel(IsDeleteEnums.getMsgByCode(rs.getString("is_del")));
                wdo.setAdd_time(rs.getString("add_time"));
                wdo.setComplete_time(rs.getString("complete_time"));
                wdo.setRemark(rs.getString("remark"));
                wdo.setExceptions(rs.getInt("exceptions"));
                wdo.setAlter(rs.getString("alter"));
                wdo.setExamine(rs.getInt("examine"));
                return wdo;}
        });
        return wdo;
    }


    /**
     * 获取最后一条提现信息的时间   (同步提现记录)
     * @return
     */
    public String getMaxtime(){
        String sql="select MAX(t.add_time) from t_user_withdrawals t;";

        return jdbcTemplate.queryForObject(sql,String.class);
    }




    /**
     * 查询提现记录  (页面展示)
     * @return
     */
    public List<TUserWithdrawals> getWithDrawals(WithDrawalsParam param, UserDO user){
        final List<TUserWithdrawals> list = new ArrayList<TUserWithdrawals>();
        if(param != null){
            int kpage=(param.getPageNum()-1)*20;
            int page=20;
            StringBuffer sql = new StringBuffer("select * from  t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");
            StringBuffer sql2 = new StringBuffer();
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append("order by id desc LIMIT "+kpage+","+page+"");
            }
            if(!StringUtils.isEmpty(param.getWithDrawalsId())){
                sql.append(" and withdrawals_no like '"+param.getWithDrawalsId()+"'");
            }
            if(!StringUtils.isEmpty(param.getAccountType())){
                sql.append(" and user_role_type='"+param.getAccountType()+"'");
            }
            if(!StringUtils.isEmpty(param.getStatus())){
                sql.append(" and status='"+param.getStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and add_time>'"+param.getStartTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and add_time<='"+param.getEndTime()+"'");
            }
            sql.append("and exceptions='"+param.getExceptions()+"'");
            //查询对应权限
            //RechargeConfigDO RechargeConfig=withDrawalsRepository.getRechargeConfig(user);
            //if (RechargeConfig.getT_singleMaxMoney()!=0){
             //   sql.append("and total_money>="+RechargeConfig.getT_singleMinMoney()+" and total_money<="+RechargeConfig.getT_singleMaxMoney()+"");
            //}
            sql.append(sql2);
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TUserWithdrawals wdo = new TUserWithdrawals();
                    wdo.setId(rs.getLong("id"));
                    wdo.setUserId(rs.getString("user_id"));
                    wdo.setUserRoleTyoe(UserRoleTypeEnums.getMsgByCode(rs.getString("user_role_type")));
                    wdo.setUserRoleValue(rs.getInt("user_role_value"));
                    wdo.setWithdrawalsNo(rs.getString("withdrawals_no"));
                    wdo.setAccountId(rs.getInt("account_id"));
                    wdo.setBank_user(rs.getString("bank_user"));
                    wdo.setBankCard(rs.getString("bank_card"));
                    wdo.setBankName(rs.getString("bank_name"));
                    wdo.setBankArea(rs.getString("bank_area"));
                    wdo.setBankAddress(rs.getString("bank_address"));
                    wdo.setAmount(rs.getDouble("amount"));
                    wdo.setVoucher(rs.getDouble("voucher"));
                    wdo.setCommission(rs.getDouble("commission"));
                    wdo.setTotalMoney(rs.getDouble("total_money"));
                    wdo.setStatus(WithdrawalsStatus.getMsgByCode(rs.getString("status")));
                    wdo.setIsDel(IsDeleteEnums.getMsgByCode(rs.getString("is_del")));
                    wdo.setAdd_time(rs.getString("add_time"));
                    wdo.setComplete_time(rs.getString("complete_time"));
                    wdo.setRemark(rs.getString("remark"));
                    wdo.setExceptions(rs.getInt("exceptions"));
                    wdo.setAlter(rs.getString("alter"));
                    wdo.setExamine(rs.getInt("examine"));
                    list.add(wdo);
                    return list;
                }
            });
        }
        return list;
    }


    /**
     * 查询总数据(页面展示)
     * @return
     */
    public TUserWithdrawals getCount(WithDrawalsParam param,UserDO user) {
        final TUserWithdrawals udo = new TUserWithdrawals();
        if (param != null) {
            StringBuffer sql = new StringBuffer("select count(*) count from t_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");
            if (!StringUtils.isEmpty(param.getWithDrawalsId())) {
                sql.append(" and withdrawals_no like '" + param.getWithDrawalsId() + "'");
            }
            if (!StringUtils.isEmpty(param.getAccountType())) {
                sql.append(" and user_role_type='" + param.getAccountType() + "'");
            }
            if (!StringUtils.isEmpty(param.getStatus())) {
                sql.append(" and status='" + param.getStatus() + "'");
            }
            if (!StringUtils.isEmpty(param.getStartTime())) {
                sql.append(" and add_time>'" + param.getStartTime() + "'");
            }
            if (!StringUtils.isEmpty(param.getEndTime())) {
                sql.append(" and add_time<='" + param.getEndTime() + "'");
            }
            sql.append("and exceptions='" + param.getExceptions() + "'");
            //查询对应权限
            //RechargeConfigDO RechargeConfig = withDrawalsRepository.getRechargeConfig(user);
            //if (RechargeConfig.getT_singleMaxMoney() != 0) {
                //sql.append("and total_money>=" + RechargeConfig.getT_singleMinMoney() + " and total_money<=" + RechargeConfig.getT_singleMaxMoney() + "");
           //}
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    udo.setRemark(rs.getString("count"));
                    return udo;
                }
            });

        }
        return udo;

    }

    /**
     * 查询待提现用户数量和总金额(页面展示)
     * @return
     */
    public TUserWithdrawals getCountAndMoney(){

        final TUserWithdrawals list = new TUserWithdrawals();

        jdbcTemplate.query("select count(*) count,sum(amount) money from t_user_withdrawals where status = '0' and exceptions='0' and user_role_type in ('Shop','Buyer')", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                list.setRemark(rs.getString("count"));
                list.setAmount(rs.getDouble("money"));
                return list;
            }
        });
        return list;
    }

    /**
     * 批量修改异常审核状态
     * @param idArry
     * @return
     */
   public void updateToexamine(String []idArry){
       String sql="update t_user_withdrawals SET exceptions=0 WHERE id=?;";
       for (int i=0;i<idArry.length;i++){
           String id=idArry[i];
           jdbcTemplate.update(sql,new Object[]{id});
       }

   }


    /**
     * 修改提现状态
     * @param id
     * @param statuts
     */
     public void updateStats(String id,String statuts,String remark){
         String sql="update t_user_withdrawals SET status=? ,remark =? WHERE id=?;";
             jdbcTemplate.update(sql,new Object[]{statuts,remark,id});
     }


}
