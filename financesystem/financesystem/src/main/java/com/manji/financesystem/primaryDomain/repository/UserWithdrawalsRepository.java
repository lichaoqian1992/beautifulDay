package com.manji.financesystem.primaryDomain.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.manji.financesystem.enums.IsDeleteEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.enums.WithdrawalsStatus;
import com.manji.financesystem.primaryDomain.entiity.UserOrdersDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.repository.TUserWithdrawalsRepository;
import com.manji.financesystem.secondaryDomain.repository.WithDrawalsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pudding on 2017-1-17.
 */
@Repository
public class UserWithdrawalsRepository {

    @Autowired
    private WithDrawalsRepository WithdrawalsRepository;

    @Autowired
    private TUserWithdrawalsRepository TUserWithdrawalsRepository;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 获取平台收取的提现手续费
     *
     * @return
     */
    public Double getWithdrawalFee() {
        Double withdrawalFee = jdbcTemplate.queryForObject("select sum(commission) amount from dt_user_withdrawals where status=5", Double.class);
        return withdrawalFee;
    }

    /**
     * 获取提现的满意券总金额
     * @return
     */
    public Double getVoucher(){
        Double voucher = jdbcTemplate.queryForObject("select sum(voucher) voucher from dt_user_withdrawals where status='5'",Double.class);
        return voucher;
    }

    /**
     * 查询提现手续费明细
     *
     * @param page
     * @return
     */
    public List<UserWithdrawalsDO> getUserWithdrawalsList(final int page,String code) {
        final List<UserWithdrawalsDO> userWithdrawalsList = new ArrayList<UserWithdrawalsDO>();
        StringBuffer sql1 = new StringBuffer("select top 20 * from dt_user_withdrawals a where a.id not in (select top (20*(?-1)) b.id from dt_user_withdrawals b  order by b.id )");
        StringBuffer sql2 = new StringBuffer(" and status='5' order by a.id");
        if(code.equals("WITHDRAWALS_FEE")){
            sql1.append(" and commission>0");
        }else if(code.equals("VOUCHER")){
            sql1.append(" and voucher>0");
        }
        sql1.append(sql2);
        jdbcTemplate.query(sql1.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, page);
            }
        }, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserWithdrawalsDO withdrawalsDO = new UserWithdrawalsDO();
                withdrawalsDO.setId(resultSet.getLong("id"));
                withdrawalsDO.setUserId(resultSet.getString("user_id"));
                withdrawalsDO.setUserRoleTyoe(resultSet.getString("user_role_type"));
                withdrawalsDO.setUserRoleValue(resultSet.getInt("user_role_value"));
                withdrawalsDO.setAccountId(resultSet.getInt("account_id"));
                withdrawalsDO.setWithdrawalsNo(resultSet.getString("withdrawals_no"));
                withdrawalsDO.setBankName(resultSet.getString("bank_name"));
                withdrawalsDO.setBankCard(resultSet.getString("bank_card"));
                withdrawalsDO.setBankArea(resultSet.getString("bank_area")==null?"":resultSet.getString("bank_area"));
                withdrawalsDO.setBankAddress(resultSet.getString("bank_address")==null?"":resultSet.getString("bank_address"));
                withdrawalsDO.setAmount(resultSet.getDouble("amount"));
                withdrawalsDO.setStatus(WithdrawalsStatus.getMsgByCode(resultSet.getString("status")));
                withdrawalsDO.setAdd_time(resultSet.getString("add_time"));
                withdrawalsDO.setComplete_time(resultSet.getString("complete_time"));
                withdrawalsDO.setTransactionNo(resultSet.getString("transaction_no"));
                withdrawalsDO.setRemark(resultSet.getString("remark"));
                withdrawalsDO.setIsDel(IsDeleteEnums.getMsgByCode(resultSet.getString("is_del")));
                withdrawalsDO.setCommission(resultSet.getDouble("commission"));
                withdrawalsDO.setBank_user(resultSet.getString("bank_user")==null?"":resultSet.getString("bank_user"));
                withdrawalsDO.setVoucher(resultSet.getDouble("voucher"));
                withdrawalsDO.setTotalMoney(resultSet.getDouble("total_money"));
                userWithdrawalsList.add(withdrawalsDO);

                return userWithdrawalsList;
            }
        });
        return userWithdrawalsList;

    }

    /**
     * 获取总条数
     * @return
     */
    public Integer getUserWithdrawalsAllCount(String code){
        StringBuffer sql1 = new StringBuffer("select count(1) from dt_user_withdrawals a where a.status='5'");
        if(code.equals("WITHDRAWALS_FEE")){
            sql1.append(" and commission>0");
        }else if(code.equals("VOUCHER")){
            sql1.append(" and voucher>0");
        }
        Integer count = jdbcTemplate.queryForObject(sql1.toString(), Integer.class);
        return count;
    }

    /**
     * 查询提现记录
     * @return
     */
    public List<UserWithdrawalsDO> getWithDrawals(WithDrawalsParam param,UserDO user){
        final List<UserWithdrawalsDO> list = new ArrayList<UserWithdrawalsDO>();
        if(param != null){
            StringBuffer sql = new StringBuffer("select * from(select *,ROW_NUMBER() over(order by ID)as 'userID' from dt_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");
            StringBuffer sql2 = new StringBuffer(") as a where  1=1");
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append(" and a.userID between ((20*"+(param.getPageNum()-1)+")+1)"+" and (20*"+param.getPageNum()+")");
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
            //查询对应权限
            RechargeConfigDO RechargeConfig=WithdrawalsRepository.getRechargeConfig(user);
            if (RechargeConfig.getT_singleMaxMoney()!=0){
                sql.append("and total_money>="+RechargeConfig.getT_singleMinMoney()+" and total_money<="+RechargeConfig.getT_singleMaxMoney()+"");
            }
            sql.append(sql2);
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserWithdrawalsDO wdo = new UserWithdrawalsDO();
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
                    list.add(wdo);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询总数据
     * @return
     */
    public UserWithdrawalsDO getCount(WithDrawalsParam param,UserDO user){
        final UserWithdrawalsDO udo = new UserWithdrawalsDO();
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) count from dt_user_withdrawals where 1=1 and user_role_type in ('Shop','Buyer')");
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
            //查询对应权限
            RechargeConfigDO RechargeConfig=WithdrawalsRepository.getRechargeConfig(user);
            if (RechargeConfig.getT_singleMaxMoney()!=0){
                sql.append("and total_money>="+RechargeConfig.getT_singleMinMoney()+" and total_money<="+RechargeConfig.getT_singleMaxMoney()+"");
            }
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
     * 查询待提现用户数量和总金额
     * @return
     */
    public UserWithdrawalsDO getCountAndMoney(){

        final UserWithdrawalsDO list = new UserWithdrawalsDO();

        jdbcTemplate.query("select count(*) count,sum(amount) money from dt_user_withdrawals where status = '0' and user_role_type in ('Shop','Buyer')", new RowMapper() {
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
     * 点击商家查询商家订单详情
     * @param param
     * @return
     */
    public List<UserOrdersDO> getOrderDetails(WithDrawalsParam param){
        final List<UserOrdersDO> list = new ArrayList<UserOrdersDO>();
        if(param != null){
            StringBuffer sql = new StringBuffer("select * from(select *,ROW_NUMBER() over(order by ID)as 'userID' from dt_orders where status='3'");
            StringBuffer sql2 = new StringBuffer(") as a where  1=1");
            if(!StringUtils.isEmpty(param.getAccountType())){
                sql.append(" and shop_user_role_type='"+UserRoleTypeEnums.getCodeByMsg(param.getAccountType())+"'");
            }
            if(!StringUtils.isEmpty(param.getAccountId())){
                sql.append(" and shop_user_id='"+param.getAccountId()+"'");
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append(" and a.userID between ((20*"+(param.getPageNum()-1)+")+1)"+" and (20*"+param.getPageNum()+")");
            }
            sql.append(sql2);
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserOrdersDO userOrdersDO = new UserOrdersDO();
                    userOrdersDO.setOrderNo(rs.getString("order_no"));
                    userOrdersDO.setOrderType(rs.getString("order_type"));
                    userOrdersDO.setOrderTitle(rs.getString("order_title"));
                    userOrdersDO.setShopUserId(rs.getString("shop_user_id"));
                    userOrdersDO.setShopUserRoleType(UserRoleTypeEnums.getMsgByCode(rs.getString("shop_user_role_type")));
                    userOrdersDO.setUserId(rs.getString("user_id"));
                    userOrdersDO.setUserRoleType(UserRoleTypeEnums.getMsgByCode(rs.getString("user_role_type")));
                    userOrdersDO.setExpressType(rs.getString("express_type"));
                    userOrdersDO.setExpressFee(rs.getString("express_fee"));
                    userOrdersDO.setMessage(rs.getString("message"));
                    userOrdersDO.setRemark(rs.getString("remark"));
                    userOrdersDO.setIsInvoice(rs.getString("is_invoice"));
                    userOrdersDO.setInvoiceTitle(rs.getString("invoice_title"));
                    userOrdersDO.setOrderAmount(rs.getString("order_amount"));
                    userOrdersDO.setVoucher(rs.getString("voucher"));
                    userOrdersDO.setPayableAmount(rs.getString("payable_amount"));
                    userOrdersDO.setRealAmount(rs.getString("real_amount"));
                    userOrdersDO.setStatus(rs.getString("status"));
                    userOrdersDO.setAddTime(rs.getString("add_time"));
                    userOrdersDO.setConfirmTime(rs.getString("confirm_time"));
                    userOrdersDO.setInvalidTime(rs.getString("invalid_time"));
                    userOrdersDO.setCompleteTime(rs.getString("complete_time"));
                    userOrdersDO.setPaymentId(rs.getString("payment_id"));
                    userOrdersDO.setPaymentFee(rs.getString("payment_fee"));
                    userOrdersDO.setPaymentStatus(rs.getString("payment_status"));
                    userOrdersDO.setPaymentTime(rs.getString("payment_time"));
                    userOrdersDO.setSettlementStatus(rs.getString("settlement_status"));
                    userOrdersDO.setSettlementTime(rs.getString("settlement_time"));
                    userOrdersDO.setIsDelete(rs.getString("is_del"));
                    userOrdersDO.setRejectRemark(rs.getString("reject_remark"));
                    list.add(userOrdersDO);
                    return list;
                }
            });
        }
        return list;
    }

    public List<Map<String,Object>> getOrderDetails2(WithDrawalsParam param){
        StringBuffer sql = new StringBuffer("select * from(select *,ROW_NUMBER() over(order by ID)as 'userID' from dt_orders where status='3'");
        StringBuffer sql2 = new StringBuffer(") as a where  1=1");
        if(param != null){
            if(!StringUtils.isEmpty(param.getAccountType())){
                sql.append(" and shop_user_role_type='"+UserRoleTypeEnums.getCodeByMsg(param.getAccountType())+"'");
            }
            if(!StringUtils.isEmpty(param.getAccountId())){
                sql.append(" and shop_user_id='"+param.getAccountId()+"'");
            }
        }
        sql.append(sql2);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.toString());
        return maps;
    }


    public String getCountDetail(WithDrawalsParam param){
        String count;
        final String[] num = new String[1];
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) count from dt_orders where status='3'");
            if(!StringUtils.isEmpty(param.getAccountType())){
                sql.append(" and shop_user_role_type='"+UserRoleTypeEnums.getCodeByMsg(param.getAccountType())+"'");
            }
            if(!StringUtils.isEmpty(param.getAccountId())){
                sql.append(" and shop_user_id='"+param.getAccountId()+"'");
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    num[0] = rs.getString("count");
                    return num[0];
                }
            });
        }
        count = num[0];
        return count;
    }

    /**
     * 判断当前用户当日提现金额是否超过3000
     * @param userWithdrawalsDO
     * @return
     */
    public double findWithdrawalsCountmoany(UserWithdrawalsDO userWithdrawalsDO){
        String sql="select sum(total_money) from dt_user_withdrawals  where user_id=? and user_role_type=? and year(add_time)=year(?) and month(add_time)=month(?) and day(add_time)=day(?);";
        double count = jdbcTemplate.queryForObject(sql,new Object[]{userWithdrawalsDO.getUserId(),userWithdrawalsDO.getUserRoleTyoe(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time()},double.class);
        return count;
    }


    /**
     * 判断当前用户当日提现条数是否大于2条
     * @param userWithdrawalsDO
     * @return
     */
    public int findWithdrawalsCount(UserWithdrawalsDO userWithdrawalsDO){
        String sql="select  count(*) from dt_user_withdrawals  where user_id=? and user_role_type=? and year(add_time)=year(?) and month(add_time)=month(?) and day(add_time)=day(?);";
        int count = jdbcTemplate.queryForObject(sql,new Object[]{userWithdrawalsDO.getUserId(),userWithdrawalsDO.getUserRoleTyoe(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time()},int.class);
        return count;
    }


    /**
     * 判断当前用户当月提现金额是否超过20000
     * @param userWithdrawalsDO
     * @return
     */
    public double findWithdrawalsMonthMoany(UserWithdrawalsDO userWithdrawalsDO){
        String sql="select sum(total_money) from dt_user_withdrawals  where user_id=? and user_role_type=? and year(add_time)=year(?) and month(add_time)=month(?);";
        double count = jdbcTemplate.queryForObject(sql,new Object[]{userWithdrawalsDO.getUserId(),userWithdrawalsDO.getUserRoleTyoe(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time()},double.class);
        return count;
    }


    /**
     * 判断当前用户当月提现条数是否大于15条
     * @param userWithdrawalsDO
     * @return
     */
    public int findWithdrawalsMonthCount(UserWithdrawalsDO userWithdrawalsDO){
        String sql="select  count(*) from dt_user_withdrawals  where user_id=? and user_role_type=? and year(add_time)=year(?) and month(add_time)=month(?);";
        int count = jdbcTemplate.queryForObject(sql,new Object[]{userWithdrawalsDO.getUserId(),userWithdrawalsDO.getUserRoleTyoe(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time(),userWithdrawalsDO.getAdd_time()},int.class);
        return count;
    }

    /**
     * 查询待处理提现明细
     * @return
     */
    public List<UserWithdrawalsDO> findWithdrawalsinfo(){
         final List<UserWithdrawalsDO> wli=new ArrayList<UserWithdrawalsDO>();
        String sql="select * from dt_user_withdrawals where status=?";
        jdbcTemplate.query(sql, new Object[]{4}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                UserWithdrawalsDO withdrawalsDO = new UserWithdrawalsDO();
                withdrawalsDO.setId(resultSet.getLong("id"));
                withdrawalsDO.setUserId(resultSet.getString("user_id"));
                withdrawalsDO.setUserRoleTyoe(resultSet.getString("user_role_type"));
                withdrawalsDO.setUserRoleValue(resultSet.getInt("user_role_value"));
                withdrawalsDO.setAccountId(resultSet.getInt("account_id"));
                withdrawalsDO.setWithdrawalsNo(resultSet.getString("withdrawals_no"));
                withdrawalsDO.setBankName(resultSet.getString("bank_name"));
                withdrawalsDO.setBankCard(resultSet.getString("bank_card"));
                withdrawalsDO.setBankArea(resultSet.getString("bank_area")==null?"":resultSet.getString("bank_area"));
                withdrawalsDO.setBankAddress(resultSet.getString("bank_address")==null?"":resultSet.getString("bank_address"));
                withdrawalsDO.setAmount(resultSet.getDouble("amount"));
                withdrawalsDO.setStatus(WithdrawalsStatus.getMsgByCode(resultSet.getString("status")));
                withdrawalsDO.setAdd_time(resultSet.getString("add_time"));
                withdrawalsDO.setComplete_time(resultSet.getString("complete_time"));
                withdrawalsDO.setTransactionNo(resultSet.getString("transaction_no"));
                withdrawalsDO.setRemark(resultSet.getString("remark"));
                withdrawalsDO.setIsDel(IsDeleteEnums.getMsgByCode(resultSet.getString("is_del")));
                withdrawalsDO.setCommission(resultSet.getDouble("commission"));
                withdrawalsDO.setBank_user(resultSet.getString("bank_user")==null?"":resultSet.getString("bank_user"));
                withdrawalsDO.setVoucher(resultSet.getDouble("voucher"));
                withdrawalsDO.setTotalMoney(resultSet.getDouble("total_money"));
                wli.add(withdrawalsDO);
            return wli;
            };
        });

        return  wli;
    }


    /**
     * 通过条件查询并且分页待处理提现明细
     * @param param
     * @return
     */
    public List<UserWithdrawalsDO> findpageWithdrawalsinfo(WithDrawalsParam param){
        final List<UserWithdrawalsDO> lido=new ArrayList<UserWithdrawalsDO>();
        StringBuffer sql=new StringBuffer("select   *   from      \n" +
                "(select   *,   ROW_NUMBER()   OVER   (order   by   id)   AS   ROWNUM   from   dt_user_withdrawals  where status=4");

        if (!param.getWithDrawalsId().isEmpty()){
            sql.append(" and withdrawals_no='"+param.getWithDrawalsId()+"'");
        }
        int kpage=(param.getPageNum()-1)*20+1;
        int jpage=param.getPageNum()*20;
        sql.append(")t " + "where   ROWNUM   between   "+kpage+"   and   "+jpage+" ;") ;



        jdbcTemplate.query(sql.toString(),new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                UserWithdrawalsDO withdrawalsDO = new UserWithdrawalsDO();
                withdrawalsDO.setId(resultSet.getLong("id"));
                withdrawalsDO.setUserId(resultSet.getString("user_id"));
                withdrawalsDO.setUserRoleTyoe(resultSet.getString("user_role_type"));
                withdrawalsDO.setUserRoleValue(resultSet.getInt("user_role_value"));
                withdrawalsDO.setAccountId(resultSet.getInt("account_id"));
                withdrawalsDO.setWithdrawalsNo(resultSet.getString("withdrawals_no"));
                withdrawalsDO.setBankName(resultSet.getString("bank_name"));
                withdrawalsDO.setBankCard(resultSet.getString("bank_card"));
                withdrawalsDO.setBankArea(resultSet.getString("bank_area")==null?"":resultSet.getString("bank_area"));
                withdrawalsDO.setBankAddress(resultSet.getString("bank_address")==null?"":resultSet.getString("bank_address"));
                withdrawalsDO.setAmount(resultSet.getDouble("amount"));
                withdrawalsDO.setStatus(WithdrawalsStatus.getMsgByCode(resultSet.getString("status")));
                withdrawalsDO.setAdd_time(resultSet.getString("add_time"));
                withdrawalsDO.setComplete_time(resultSet.getString("complete_time"));
                withdrawalsDO.setTransactionNo(resultSet.getString("transaction_no"));
                withdrawalsDO.setRemark(resultSet.getString("remark"));
                withdrawalsDO.setIsDel(IsDeleteEnums.getMsgByCode(resultSet.getString("is_del")));
                withdrawalsDO.setCommission(resultSet.getDouble("commission"));
                withdrawalsDO.setBank_user(resultSet.getString("bank_user")==null?"":resultSet.getString("bank_user"));
                withdrawalsDO.setVoucher(resultSet.getDouble("voucher"));
                withdrawalsDO.setTotalMoney(resultSet.getDouble("total_money"));
                lido.add(withdrawalsDO);
                return lido;
            }
        } );
        return lido;
    }


    /**
     * 待处理明细总数
     * @return
     */
    public int findpageWithdrawalsinfocount(){
        String sql="select count(*) from dt_user_withdrawals where status=4";
        int count=jdbcTemplate.queryForObject(sql,int.class);
        return count;
    }


    /**
     * 查询新增加的提现数据  (同步提现)
     * @return
     */
    public List<UserWithdrawalsDO> tfindUserWithdrawals(){

        final List<UserWithdrawalsDO> lido=new ArrayList<UserWithdrawalsDO>();
        //获取上一次同步到的时间
        String prveTime=TUserWithdrawalsRepository.getMaxtime();
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        String date=sdf.format(new Date());
        if (prveTime==null||prveTime.equals("null")){
            prveTime="2010-3-16 14:21:25";
        }
        String sql="select * from dt_user_withdrawals where add_time>? and add_time<?";
        jdbcTemplate.query(sql,new Object[]{prveTime,date},new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                UserWithdrawalsDO withdrawalsDO = new UserWithdrawalsDO();
                withdrawalsDO.setId(resultSet.getLong("id"));
                withdrawalsDO.setUserId(resultSet.getString("user_id"));
                withdrawalsDO.setUserRoleTyoe(resultSet.getString("user_role_type"));
                withdrawalsDO.setUserRoleValue(resultSet.getInt("user_role_value"));
                withdrawalsDO.setAccountId(resultSet.getInt("account_id"));
                withdrawalsDO.setWithdrawalsNo(resultSet.getString("withdrawals_no"));
                withdrawalsDO.setBankName(resultSet.getString("bank_name"));
                withdrawalsDO.setBankCard(resultSet.getString("bank_card"));
                withdrawalsDO.setBankArea(resultSet.getString("bank_area") == null ? "" : resultSet.getString("bank_area"));
                withdrawalsDO.setBankAddress(resultSet.getString("bank_address") == null ? "" : resultSet.getString("bank_address"));
                withdrawalsDO.setAmount(resultSet.getDouble("amount"));
                withdrawalsDO.setStatus(resultSet.getString("status"));
                withdrawalsDO.setAdd_time(resultSet.getString("add_time"));
                withdrawalsDO.setComplete_time(resultSet.getString("complete_time"));
                withdrawalsDO.setTransactionNo(resultSet.getString("transaction_no"));
                withdrawalsDO.setRemark(resultSet.getString("remark"));
                withdrawalsDO.setIsDel(IsDeleteEnums.getMsgByCode(resultSet.getString("is_del")));
                withdrawalsDO.setCommission(resultSet.getDouble("commission"));
                withdrawalsDO.setBank_user(resultSet.getString("bank_user") == null ? "" : resultSet.getString("bank_user"));
                withdrawalsDO.setVoucher(resultSet.getDouble("voucher"));
                withdrawalsDO.setTotalMoney(resultSet.getDouble("total_money"));
                lido.add(withdrawalsDO);
                return lido;
            }
        });
        return lido;
    }




}
