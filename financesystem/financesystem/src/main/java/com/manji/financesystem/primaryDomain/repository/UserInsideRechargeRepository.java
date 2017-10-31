package com.manji.financesystem.primaryDomain.repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.financesystem.enums.AllStatusEnum;
import com.manji.financesystem.enums.CheckStatusEnums;
import com.manji.financesystem.enums.RechargeTypeEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.MyDealDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDMCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDO;
import com.manji.financesystem.requestParam.ExamRechargeParam;
import com.manji.financesystem.requestParam.HttpRequestParam;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.UserInsideRechargeParam;
import com.manji.financesystem.secondaryDomain.entity.HttplogDO;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.entity.extra.HttpResponseDO;
import com.manji.financesystem.secondaryDomain.repository.HttplogRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemLogUtilsRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import com.manji.financesystem.utils.DateUtils;
import com.manji.financesystem.utils.HttpClientUtils;
import com.manji.financesystem.utils.InterfaceUtil;
import com.manji.financesystem.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/3.
 */
@Repository
public class UserInsideRechargeRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Autowired
    private SystemLogUtilsRepository systemlogRepository;
    @Autowired
    private HttplogRepository httplogRepository;

    /**
     * 查询内部充值记录,分页，每页20条
     * @param param
     * @return
     */
    public List<UserInsideRechargeDO> getUserInsideRecharge(UserInsideRechargeParam param){
        final List<UserInsideRechargeDO> list = new ArrayList<UserInsideRechargeDO>();
        int pageNum = param.getPageNum();
        StringBuffer sql = new StringBuffer("select top (20*?) a.*,b.user_name from dt_user_fixed a inner join dt_users b on a.user_id=b.id and a.role_type='buyer' and a.is_del='0'");
        StringBuffer sql2 = new StringBuffer(" except select top (20*(?-1)) a.*,b.user_name from dt_user_fixed a inner join dt_users b on a.user_id=b.id and a.role_type='buyer' and a.is_del='0'");
        String sql1="";
        if(!StringUtils.isEmpty(param.getUserName())){
            sql.append(" and b.user_name like '%"+param.getUserName()+"%'");
            sql2.append(" and b.user_name like '%"+param.getUserName()+"%'");
        }
        if(!StringUtils.isEmpty(param.getStatus())){
            sql.append(" and a.state='"+param.getStatus()+"'");
            sql2.append(" and a.state='"+param.getStatus()+"'");
        }
        if(!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime())){
            sql.append(" and a.add_time>='"+param.getStartTime()+"' and a.add_time<'"+param.getEndTime()+"'");
            sql2.append(" and a.add_time>='"+param.getStartTime()+"' and a.add_time<'"+param.getEndTime()+"'");
        }
        sql.append(sql2);
        sql1 = sql.toString();
        jdbcTemplate.query(sql1, new Object[]{pageNum,pageNum},new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserInsideRechargeDO userInsideRechargeDO = new UserInsideRechargeDO();
                userInsideRechargeDO.setUserId(rs.getString("user_id"));
                userInsideRechargeDO.setUserName(rs.getString("user_name"));
                userInsideRechargeDO.setAmount(rs.getDouble("amount"));
                userInsideRechargeDO.setStatus(rs.getString("state"));
                userInsideRechargeDO.setCategory(rs.getString("category"));
                userInsideRechargeDO.setAddTime(rs.getString("add_time"));
                userInsideRechargeDO.setIsDel(rs.getString("is_del"));
                userInsideRechargeDO.setRemark(rs.getString("remark"));
                list.add(userInsideRechargeDO);
                return list;
            }
        });
        return list;
    }

    /**
     * 查询数据的总条数
     * @return
     */
    public UserInsideRechargeCountDO getCount(UserInsideRechargeParam param){
        final UserInsideRechargeCountDO userInsideRechargeCountDO = new UserInsideRechargeCountDO();
        StringBuffer sql = new StringBuffer("select count(a.user_id) count from dt_user_fixed a inner join dt_users b on a.user_id=b.id and a.role_type='buyer' and a.is_del='0'");
        if(!StringUtils.isEmpty(param.getUserName())){
            sql.append(" and b.user_name like '%"+param.getUserName()+"%'");
        }
        if(!StringUtils.isEmpty(param.getStatus())){
            sql.append(" and a.state='"+param.getStatus()+"'");
        }
        if(!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime())){
            sql.append(" and a.add_time>='"+param.getStartTime()+"' and a.add_time<'"+param.getEndTime()+"'");
        }
        jdbcTemplate.query(sql.toString(), new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                userInsideRechargeCountDO.setCount(rs.getInt("count"));
                return userInsideRechargeCountDO;
            }
        });
        return userInsideRechargeCountDO;
    }

    /**
     * 导出数据
     * @return
     */
    public List<Map<String,Object>> getUserInsideRechargeByParam(InteriorRechargeRequestParam param){
        StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where 1=1");
        if(param != null){
            if(!StringUtils.isEmpty(param.getUserName())){
                sql.append(" and user_key like '%"+param.getUserName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getStatus())){
                sql.append(" and status='"+param.getStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and create_time>'"+param.getStartTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and create_time<='"+param.getEndTime()+"%'");
            }
        }
        List<Map<String, Object>> maps = jdbcTemplate2.queryForList(sql.toString());
        return maps;
    }

    /**
     * 查询内部充值订单
     * @return
     */
    public List<InteriorRechargeDetailDO>  queryInsideRechargeOrder(InteriorRechargeRequestParam param){
        List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        String checkPeople = "";
        if(param != null){
            //先要根据登录人，查询出登录人的角色，然后根据角色查询数据
            StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where check_status='1'");//PERSON_APPROVING=(select username from t_recharge_config where id=(select T_CONFIG_ID from t_user where username='"+param.getCheckPeople()+"')) and
            if(!StringUtils.isEmpty(param.getCheckPeople())){
                if(!param.getCheckPeople().equals("admin")){
                    checkPeople = param.getCheckPeople();
                    sql.append(" and person_makesure=(select b.username as congifName from t_user a inner join t_recharge_config b on a.T_CONFIG_ID=b.id where a.USERNAME='"+checkPeople+"')");
                }
            }
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getPeopleRelease())){
                sql.append(" and person_release like '%"+param.getPeopleRelease()+"%'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and create_time > '"+param.getStartTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and create_time <= '"+param.getEndTime()+"%'");
            }
            /*if(!StringUtils.isEmpty(param.getCheckStatus())){
                sql.append(" and check_status = '"+param.getCheckStatus()+"'");
            }*/
            sql.append(" order by approval_time limit "+20*(param.getPageNum()-1)+","+20*(param.getPageNum()));
            list = getTogether(sql.toString());

        }
        return list;
    }
    public List<InteriorRechargeDetailDO> getTogether(String sql){
        final List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        jdbcTemplate2.query(sql.toString(), new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                InteriorRechargeDetailDO detailDO = new InteriorRechargeDetailDO();
                detailDO.setOrderNo(rs.getString("recharge_order_no"));
                detailDO.setTranSN(rs.getString("transn"));
                detailDO.setExcelNo(rs.getString("excel_no"));
                detailDO.setExcelName(rs.getString("excel_name"));
                detailDO.setUserKey(rs.getString("user_key"));
                detailDO.setUserId(rs.getInt("user_id"));
                detailDO.setRoleType(UserRoleTypeEnums.getMsgByCode(rs.getString("role_type")));
                detailDO.setRoleValue(rs.getInt("role_value"));
                detailDO.setRechargeMoney(rs.getDouble("recharge_money"));
                detailDO.setRechargeType(RechargeTypeEnums.getMessageByCode(rs.getString("recharge_type")));
                detailDO.setPersonRelease(rs.getString("person_release"));
                detailDO.setPersonApproving(rs.getString("person_approving"));
                detailDO.setStatus(AllStatusEnum.getMessageByCode(rs.getString("status")));
                detailDO.setCheckStatus(CheckStatusEnums.getMessageByCode(rs.getString("check_status")));
                detailDO.setCheckTime(rs.getString("check_time"));
                detailDO.setCreateTime(rs.getString("create_time"));
                detailDO.setApprovalTime(rs.getString("approval_time"));
                detailDO.setRemark(rs.getString("remark"));

                list.add(detailDO);
                return list;
            }
        });
        return list;
    }
    public int getCountInsideRechargeOrder(InteriorRechargeRequestParam param){
        final int[] countNum = new int[1];
        String checkPeople = "";
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) countNum from t_interior_recharge_detail where check_status='1'");
            if(!StringUtils.isEmpty(param.getCheckPeople())){
                if(!param.getCheckPeople().equals("admin")){
                    checkPeople = param.getCheckPeople();
                    sql.append(" and PERSON_APPROVING=(select b.username as congifName from t_user a inner join t_recharge_config b on a.T_CONFIG_ID=b.id where a.USERNAME='"+checkPeople+"')");
                }
            }
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getPeopleRelease())){
                sql.append(" and person_release like '%"+param.getPeopleRelease()+"%'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and create_time > '"+param.getStartTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and create_time <= '"+param.getEndTime()+"%'");
            }
            jdbcTemplate2.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    countNum[0] = rs.getInt("countNum");
                    return countNum[0];
                }
            });
        }
        return countNum[0];
    }
    public List<InteriorRechargeDetailDO> queryMyDealOrder(InteriorRechargeRequestParam param){

        List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        if(param != null){
            StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where 1=1");
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getCheckStatus())){
                sql.append(" and check_status='"+param.getCheckStatus()+"'");
            }else{
                sql.append(" and check_status in(2,3)");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and check_time>'"+param.getStartTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and check_time <= '"+param.getEndTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql.append(" order by check_time limit "+20*(param.getPageNum()-1)+","+20*(param.getPageNum()));
            }
            list = getTogether(sql.toString());
        }
        return list;
    }
    public int getCountMyDealOrder(InteriorRechargeRequestParam param){
        final int[] countNum = new int[1];
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) countNum from t_interior_recharge_detail where 1=1");
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getCheckStatus())){
                sql.append(" and check_status='"+param.getCheckStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and check_time>'"+param.getStartTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and check_time <= '"+param.getEndTime()+"'");
            }
            jdbcTemplate2.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    countNum[0] = rs.getInt("countNum");
                    return countNum[0];
                }
            });
        }
        return countNum[0];
    }
    /**
     * 审批内部充值订单：审批的时候传过来的是充值订单的单号 根据ID修改订单的状态 然后调用充值接口实现内部充值
     * @param list
     * @return
     */
    public String examRechargeOrder(final List<InteriorRechargeDetailDO> list,final String creater){
        String status= "";
        String czStatus = "";
        String content = "";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try{
            if(list != null && list.size()>0){
                for(int i=0;i<list.size();i++){
                    //1.调用充值的接口
                    //接口需要的参数
                    HttpRequestParam param = new HttpRequestParam();
                    HttpResponseDO httpResponseDO = new HttpResponseDO();
                    String timeStamp = new Date().getTime()+1+"";
                    param.setAccessKey("javamanager");
                    param.setAction("UserFixedRecharge");
                    param.setMoney(list.get(i).getRechargeMoney());
                    param.setNonceStr(timeStamp);
                    param.setRoleType(list.get(i).getRoleType());
                    param.setRoleValue(list.get(i).getRoleValue());
                    param.setTranSN(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));//业务单号
                    param.setUserId(list.get(i).getUserId());
                    param.setWithdraw(list.get(i).getRechargeType());
                    httpResponseDO = HttpClientUtils.getReturnMessage(param);
                    if(httpResponseDO != null){
                        if(httpResponseDO.getErrCode().equals("0")){
                            if(httpResponseDO.getResultCode().equals("8")){
                                czStatus = "6";
                                status = "SUCCESS";//充值成功
                                /**记录调用接口返回的数据*/
                                HttplogDO httplogDO = new HttplogDO();
                                httplogDO.setAction(httpResponseDO.getAction());
                                httplogDO.setQuerySN(httpResponseDO.getQuerySN());
                                httplogDO.setTranSN(httpResponseDO.getTranSN());
                                httplogDO.setResultData(httpResponseDO.getResultData());
                                httplogDO.setCreatedate(new SimpleDateFormat("yyyy_MM-dd HH:mm:ss").format(new Date()));
                                httplogRepository.setHttplog(httplogDO);
                            }else if(httpResponseDO.getResultCode().equals("4")){
                                czStatus = "5";
                                status = "FAIL";//充值失败
                            }
                        }
                    }
                    //2.修改本地数据的充值状态
                    final String finalCzStatus = czStatus;
                    jdbcTemplate2.batchUpdate("update t_interior_recharge_detail set status=? where recharge_order_no=?", new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, finalCzStatus);//充值状态
                            ps.setString(2,list.get(i).getOrderNo());
                        }
                        @Override
                        public int getBatchSize() {
                            return list.size();
                        }
                    });
                    //3.记录日志信息
                    content = "用户"+creater+"在"+date+"给用户编号为"+list.get(i).getUserId()+"的用户充值"+list.get(i).getRechargeMoney()+"元,充值订单号:"+list.get(i).getOrderNo();
                    systemlogRepository.addSystemLogs(creater , content);
                }
            }else{
                status = "EMPTY";//数据为空
            }
        }catch(Exception e){
            e.printStackTrace();
            status = "ERROR";//出现异常
        }
        return status;
    }

    /**
     * 批量拒绝充值订单
     * 说明：拒绝充值以后，需要变更充值状态为撤销充值成功，审核状态为已审核，显示拒绝充值原因
     * @param list
     * @return
     */
    public String refuseRechargeOrder(final List<InteriorRechargeDetailDO> list, final String czStatus, final String checkStatus){
        String status= "";
        if(list != null && list.size()>0){
            jdbcTemplate2.batchUpdate("update t_interior_recharge_detail set status=?,check_status=?,check_time=?,remark=? where recharge_order_no=?", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1,czStatus);
                    ps.setString(2,checkStatus);
                    ps.setString(3,list.get(i).getCheckTime());
                    ps.setString(4,list.get(i).getRemark());
                    ps.setString(5,list.get(i).getOrderNo());
                }
                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });
            status = "SUCCESS";
        }else{
            status = "FAIL";
        }
        return status;
    }
    /**
     * 撤销充值
     * @param list
     * @param creater
     * @return
     */
    public String rollBackRecharge(final List<InteriorRechargeDetailDO> list,final String creater){

        if(list != null && list.size()>0){
            for(int i=0;i<list.size();i++){

            }
        }


        return "";
    }
}
