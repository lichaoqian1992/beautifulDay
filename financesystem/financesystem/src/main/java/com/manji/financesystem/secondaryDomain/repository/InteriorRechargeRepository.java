package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.common.StringDO;
import com.manji.financesystem.enums.AllStatusEnum;
import com.manji.financesystem.enums.CheckStatusEnums;
import com.manji.financesystem.enums.RechargeTypeEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.UserAccountInfoDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDMCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.primaryDomain.repository.UserNormalRechargeRepository;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.OaRequestParam;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.secondaryDomain.entity.OaInfoDO;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.extra.RechargeMoneyAndPersonDO;
import com.manji.financesystem.secondaryDomain.entity.extra.UserRechargeDetailDO;
import com.manji.financesystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-2-6.
 */
@Repository
public class InteriorRechargeRepository {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Autowired
    private UserNormalRechargeRepository userNormalRechargeRepository;

    @Autowired
    private SystemLogUtilsRepository systemlogRepository;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");


    /**
     * 新增充值记录
     */
    public void addrechargeRecord(UserRechargeDetailDO detailDO) {




    }
    /**
     * 根据充值金额获取审批人
     * @param singleMoney
     */
    public List<RechargeConfigDO> queryRechargeConfig(double singleMoney){
        final List<RechargeConfigDO> list = new ArrayList<RechargeConfigDO>();
        jdbcTemplate.query("select * from t_recharge_config where single_min_money<? and single_max_money>=?",new Object[]{singleMoney,singleMoney}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                RechargeConfigDO configDO = new RechargeConfigDO();
                configDO.setUserName(rs.getString("username"));
                configDO.setTotalMoney(rs.getDouble("total_money"));
                list.add(configDO);
                return list;
            }
        });

        return list;
    }

    /**
     * 保存充值订单信息    批量保存
     * @param list
     */
    public void addInteriorRechargeDetail(final List<InteriorRechargeDetailDO> list){
        if(list != null) {
            jdbcTemplate.batchUpdate("insert into t_interior_recharge_detail(recharge_order_no,excel_no,user_key,recharge_money,user_id,role_type,role_value,recharge_type,status,person_release,person_approving,check_status,check_time,excel_name,create_time,approval_time,remark,oaid,transn,person_makesure) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, list.get(i).getOrderNo());
                            ps.setString(2, list.get(i).getExcelNo());
                            ps.setString(3, list.get(i).getUserKey());
                            ps.setDouble(4, list.get(i).getRechargeMoney());
                            ps.setInt(5, list.get(i).getUserId());
                            ps.setString(6, list.get(i).getRoleType());
                            ps.setInt(7, list.get(i).getRoleValue());
                            ps.setString(8, list.get(i).getRechargeType());
                            ps.setString(9, list.get(i).getStatus());
                            ps.setString(10,list.get(i).getPersonRelease());
                            ps.setString(11, list.get(i).getPersonApproving());
                            ps.setString(12, list.get(i).getCheckStatus());
                            ps.setString(13, list.get(i).getCheckTime());
                            ps.setString(14, list.get(i).getExcelName());
                            ps.setString(15, list.get(i).getCreateTime());
                            ps.setString(16, list.get(i).getApprovalTime());
                            ps.setString(17, list.get(i).getRemark());
                            ps.setInt(18, list.get(i).getOaNo());
                            ps.setString(19, list.get(i).getTranSN());
                            ps.setString(20, list.get(i).getPersonMakeSure());
                        }
                        public int getBatchSize() {
                            return list.size();
                        }
                    });
        }
    }

    /**
     * 查询我所充值订单
     * @return
     */
    public List<InteriorRechargeDetailDO> getMyRecharge(InteriorRechargeRequestParam param){
        final List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        if(param != null){
            StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where 1=1");
            if(!StringUtils.isEmpty(param.getPeopleRelease())){
                if(!param.getPeopleRelease().equals("admin")){
                    sql.append(" and person_release ='"+param.getPeopleRelease()+"'");
                }
            }
            if(!StringUtils.isEmpty(param.getUserName())){
                sql.append(" and user_key like '%"+param.getUserName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getRechargeType())){
                sql.append(" and recharge_type='"+param.getRechargeType()+"'");
            }
            if(!StringUtils.isEmpty(param.getCheckPeople())){
                sql.append(" and person_approving like '%"+param.getCheckPeople()+"%'");
            }
            if(!StringUtils.isEmpty(param.getStatus())){
                sql.append(" and status='"+param.getStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getCheckStatus())){
                sql.append(" and check_status='"+param.getCheckStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and create_time>'"+param.getStartTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and create_time<='"+param.getEndTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql.append(" order by CHECK_STATUS limit "+20*(param.getPageNum()-1)+","+20*(param.getPageNum()));
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    InteriorRechargeDetailDO ido = new InteriorRechargeDetailDO();
                    ido.setOrderNo(rs.getString("recharge_order_no"));
                    ido.setTranSN(rs.getString("transn"));
                    ido.setOaNo(rs.getInt("oaid"));
                    ido.setExcelNo(rs.getString("excel_no"));
                    ido.setExcelName(rs.getString("excel_name"));
                    ido.setUserKey(rs.getString("user_key"));
                    ido.setRechargeMoney(rs.getDouble("recharge_money"));
                    ido.setUserId(rs.getInt("user_id"));
                    ido.setRoleType(UserRoleTypeEnums.getMsgByCode(rs.getString("role_type")));
                    ido.setRoleValue(rs.getInt("role_value"));
                    ido.setRechargeType(RechargeTypeEnums.getMessageByCode(rs.getString("recharge_type")));
                    ido.setStatus(AllStatusEnum.getMessageByCode(rs.getString("status")));
                    ido.setPersonRelease(rs.getString("person_release"));
                    ido.setPersonApproving(rs.getString("person_approving"));
                    ido.setPersonMakeSure(rs.getString("person_makesure"));
                    ido.setCheckStatus(CheckStatusEnums.getMessageByCode(rs.getString("check_status")));
                    ido.setCheckTime(rs.getString("check_time"));
                    ido.setCreateTime(rs.getString("create_time"));
                    ido.setApprovalTime(rs.getString("approval_time"));
                    ido.setRemark(rs.getString("remark"));
                    list.add(ido);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询总数据
     * @param param
     * @return
     */
    public int getCount(InteriorRechargeRequestParam param){
        final int[] a = new int[1];
        final InteriorRechargeDetailDO udo = new InteriorRechargeDetailDO();
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) count from t_interior_recharge_detail where 1=1");
            if(!StringUtils.isEmpty(param.getUserName())){
                sql.append(" and user_key like '%"+param.getUserName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getPeopleRelease())){
                if(!param.getPeopleRelease().equals("admin")){
                    sql.append(" and person_release ='"+param.getPeopleRelease()+"'");
                }
            }
            if(!StringUtils.isEmpty(param.getRechargeOrderNo())){
                sql.append(" and recharge_order_no like '%"+param.getRechargeOrderNo()+"%'");
            }
            if(!StringUtils.isEmpty(param.getRechargeType())){
                sql.append(" and recharge_type='"+param.getRechargeType()+"'");
            }
            if(!StringUtils.isEmpty(param.getCheckPeople())){
                sql.append(" and person_approving like '%"+param.getCheckPeople()+"%'");
            }
            if(!StringUtils.isEmpty(param.getStatus())){
                sql.append(" and status='"+param.getStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getCheckStatus())){
                sql.append(" and check_status='"+param.getCheckStatus()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and create_time>'"+param.getStartTime()+"%'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and create_time<='"+param.getEndTime()+"%'");
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    a[0] = rs.getInt("count");
                    return a[0];
                }
            });
        }
        return a[0];
    }
    /**
     * 查询当天的当月的充值总和
     * @return
     */
    public UserInsideRechargeDMCountDO getDMCount() throws ParseException {
        //查询当日的总和
        UserInsideRechargeDMCountDO dmCountDO = new UserInsideRechargeDMCountDO();
        final UserInsideRechargeDMCountDO dmCountDO1 = new UserInsideRechargeDMCountDO();
        final UserInsideRechargeDMCountDO dmCountDO2 = new UserInsideRechargeDMCountDO();

        String startTime = DateUtils.getCurrentMonthFirstDayTime();//第一天
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(today).getTime()+(1000*60*60*24)));//第二天
        String endTime = DateUtils.getCurrentMonthLastDayTime();//最后一天
        //今天
        jdbcTemplate.query("select sum(RECHARGE_MONEY) money from t_interior_recharge_detail where STATUS='6' and CHECK_STATUS='2' and CHECK_TIME>= ? and CHECK_TIME<?",new Object[]{today,tomorrow}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                dmCountDO1.setDayCount(rs.getDouble("money"));
                return dmCountDO1;
            }
        });
        //当月
        jdbcTemplate.query("select sum(RECHARGE_MONEY) money from t_interior_recharge_detail where STATUS='6' and CHECK_STATUS='2' and CHECK_TIME>= ? and CHECK_TIME<?",new Object[]{startTime,endTime}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                dmCountDO2.setMonthCount(rs.getDouble("money"));
                return dmCountDO2;
            }
        });
        dmCountDO.setDayCount(dmCountDO1.getDayCount());
        dmCountDO.setMonthCount(dmCountDO2.getMonthCount());
        return dmCountDO;
    }
    /**
     * 批量报批
     * 说明：批量充值根据充值订单号修改订单的状态------充值状态：账户预充值 ，审批状态：待审核
     */
    public String batchApproval(InteriorRechargeRequestParam param){
        //处理参数值
         String status = "";
        final int[] userId = new int[1];
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        StringBuffer sql = new StringBuffer();
        String content = "";//保存操作日志记录的内容
        if(param != null){
            if(param.getOperationType().equals("报批充值")){
                sql = new StringBuffer("update t_interior_recharge_detail set status='4',check_status='1',remark='已申请报批',approval_time='"+date+"' where recharge_order_no in(");
                content = "用户"+param.getPeopleRelease()+"在"+date+"报批了充值订单信息,订单号:"+param.getIdList();
            }else if(param.getOperationType().equals("撤销充值")){
                sql = new StringBuffer("update t_interior_recharge_detail set status='7',check_status='1',remark='申请撤销充值',approval_time='"+date+"' where recharge_order_no in(");
                content = "用户"+param.getPeopleRelease()+"在"+date+"申请撤销充值订单号为:"+param.getIdList()+"的充值订单";
            }
            final String[] rechargeId = param.getIdList().split(",");
            final int m = rechargeId.length;
            for(int i=0;i<m;i++){
                if(i == rechargeId.length-1){
                    sql.append("'"+rechargeId[i]+"')");
                }else{
                    sql.append("'"+rechargeId[i]+"',");
                }
            }
            System.out.println(sql.toString());
            jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {

                }
            });
            status = "SUCCESS";
            if(status.equals("SUCCESS")){
                //3.记录日志信息
                systemlogRepository.addSystemLogs(param.getPeopleRelease() , content);
            }
        }else{
            status = "FAIL";
        }
        return status;
    }
    /**
     * 判断是否是密集充值
     * @param param
     * @return
     */
    public List<StringDO> checkApproval(InteriorRechargeRequestParam param){
        final List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        List<StringDO> list2 = new ArrayList<StringDO>();
        String[] a;
        StringDO sto = new StringDO();
        //1.首先应该判断1-2小时内的报批订单数是否属于密集充值，如果是则不让他报批
        //查询的是2小时以内的某个用户的充值记录
        StringBuffer sql2 = new StringBuffer("select * from t_interior_recharge_detail where CREATE_TIME> DATE_SUB(NOW(),INTERVAL  2 HOUR)");
        if(!StringUtils.isEmpty(param.getUserIdList())){
            a = param.getUserIdList().split(",");
            for(int j=0;j<a.length;j++){
                sql2.append(" and user_id='"+a[j]+"' and check_status='1'");
                jdbcTemplate.query(sql2.toString(), new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        InteriorRechargeDetailDO ido = new InteriorRechargeDetailDO();
                        ido.setOrderNo(rs.getString("recharge_order_no"));
                        list.add(ido);
                        return list;
                    }
                });
                System.out.println(sql2.toString());
                //如果当前2小时内的某个用户的充值记录超过10条，则判定为密集充值，需要等待一段时间再充值
                if(list.size()>=10){
                    sto.setStatus("ERROR");
                    sto.setUserId(a[j]);
                    sto.setDescription("报批失败");
                }else{
                    sto.setStatus("SUCCESS");
                    sto.setUserId(a[j]);
                    sto.setDescription("报批成功");
                }
                list2.add(sto);
            }
        }
        return list2;
    }

    /**
     * 查询某个审批人今天的审批总金额
     * @return
     */
    public double getAcountMoney(InteriorRechargeRequestParam param){
        final double[]  rechargeMoneyAndPersonDO = new double[2];
        if(param != null){
            String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当前日期
            String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime()+24*60*60*1000));
            System.out.println(nowDate+" "+nextDate);
            StringBuffer sql = new StringBuffer("select sum(RECHARGE_MONEY) countMoney from t_interior_recharge_detail  a where 1=1");
            if(!StringUtils.isEmpty(param.getUserName())){
                sql.append(" and a.PERSON_APPROVING='"+param.getUserName()+"'");
            }
            sql.append(" and a.CREATE_TIME>='"+nowDate+"' and a.CREATE_TIME<'"+nextDate+"'");
            jdbcTemplate.query(sql.toString(),new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    rechargeMoneyAndPersonDO[0] = rs.getDouble("countMoney");
                    return rechargeMoneyAndPersonDO[0];
                }
            });

        }
        return rechargeMoneyAndPersonDO[0];
    }

    /**
     * 获取审批人电话
     * @param param
     * @return
     */
    public RechargeMoneyAndPersonDO getMobile(InteriorRechargeRequestParam param){
        final RechargeMoneyAndPersonDO rechargeMoneyAndPersonDO = new RechargeMoneyAndPersonDO();
        if(param != null){
            StringBuffer sql = new StringBuffer("select a.MOBILE from t_user a inner join t_recharge_config b on a.T_CONFIG_ID=b.id");
            if(!StringUtils.isEmpty(param.getUserName())){
                sql.append(" and b.username='"+param.getUserName()+"'");
            }
            jdbcTemplate.query(sql.toString(),new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    rechargeMoneyAndPersonDO.setMobile(rs.getString("mobile"));
                    return rechargeMoneyAndPersonDO;
                }
            });
        }
        return rechargeMoneyAndPersonDO;
    }

    /**
     * 验证验证码是否正确
     * @return
     */
    public List<UserSmsLogDO> checkYzm(){
        final List<UserSmsLogDO> list = new ArrayList<UserSmsLogDO>();
       jdbcTemplate2.query("select content,send_time from dt_user_sms_log where send_status='1' and type like '临时充值%' order by send_time desc", new RowMapper() {
           @Override
           public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
               UserSmsLogDO udo = new UserSmsLogDO();
               udo.setContent(rs.getString("content"));
               udo.setSendTime(rs.getString("send_time"));
               list.add(udo);
               return list;
           }
       });
        return list;
    }

    /**
     * 获取申请人电话号码
     * @param userName
     * @return
     */
    public String getApplicant(String userName){
        final String[] mobile = {""};
        if(!StringUtils.isEmpty(userName)){
            jdbcTemplate.query("select * from t_user where username=?",new Object[]{userName}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    mobile[0] = rs.getString("mobile");
                    return mobile[0];
                }
            });
        }

        return mobile[0];
    }

    /**
     * 临时充值
     * @param param
     * @return
     */
    public String doRecharge(InteriorRechargeRequestParam param){
        String status = "";
        String content = "";
        int userId=0;//用户ID
        int roleValue=0;//角色值
        String roleType = "";//角色类型
        Date date = new Date();
        List<InteriorRechargeDetailDO> list2 = new ArrayList<InteriorRechargeDetailDO>();
        if(param != null){
            //1.根据充值账号获取账号ID,用户类型，和类型值
            if(!StringUtils.isEmpty(param.getUserName())){
                List<UserAccountInfoDO> list = userNormalRechargeRepository.queryUserInfoByUserKey(param.getUserName());
                List<RechargeConfigDO> rechargeConfigDOList = queryRechargeConfig(param.getAccountMoney());
                //生成充值订单
                if(list != null && list.size()>0){
                    userId = list.get(0).getUserId();
                    roleType = list.get(0).getRoleType();
                    roleValue = list.get(0).getRoleValue();
                    //判断是商家还是用户，只能是个人用户才允许充值
                    if(!roleType.equals("Buyer")){
                        status = "NOT BUYER";
                    }else{
                        for(int i=0;i<1;i++){
                            InteriorRechargeDetailDO ido1 = new InteriorRechargeDetailDO();
                            //查询该充值单对应的审核人并且判断审核额度是否已满
                            ido1.setPersonApproving(rechargeConfigDOList.get(0).getUserName());
                            ido1.setOrderNo("MJ"+format.format(date));//充值订单号
                            ido1.setExcelNo(param.getRechargeOrderNo());//批次号
                            ido1.setOaNo(0);
                            ido1.setExcelName("临时充值");
                            ido1.setTranSN(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));//业务单号
                            ido1.setRemark("临时充值");//备注
                            ido1.setCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));//审批时间
                            ido1.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));//创建时间
                            ido1.setApprovalTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));//报批时间
                            ido1.setUserKey(param.getUserName());//用户标识
                            ido1.setUserId(userId);//充值用户ID
                            ido1.setRoleType(roleType);//充值用户角色
                            ido1.setRoleValue(roleValue);//充值用户角色值
                            ido1.setRechargeMoney(param.getAccountMoney());//充值金额
                            ido1.setRechargeType(param.getRechargeType());//充值类型
                            ido1.setStatus("4");//账户预充值
                            ido1.setCheckStatus("0");//未审核
                            //ido1.setOprationType("5");//临时充值
                            ido1.setPersonRelease(param.getPeopleRelease());//发布人
                            ido1.setPersonMakeSure("分管会计");//确认人
                            list2.add(ido1);
                        }
                        //2.生成充值订单
                        addInteriorRechargeDetail(list2);
                        status = "SUCCESS";
                        //记录日志信息
                        content = "用户"+param.getPeopleRelease()+"在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"发布临时充值信息,批次号:"+param.getRechargeOrderNo();
                        systemlogRepository.addSystemLogs(param.getPeopleRelease() , content);
                        //status = userInsideRechargeRepository.examRechargeOrder(list2,param.getPeopleRelease());
                    }
                }else{
                    status = "NOT EXIT";
                }
            }
        }
        return status;
    }

    public String inputOaInfo(final OaRequestParam param){
        String status = "";
        String content = "";
        final int[] userId = new int[1];
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        final List<OaInfoDO> list = new ArrayList<OaInfoDO>();
        if(param != null){
             jdbcTemplate.query("select * from t_oainfo where oaid=?", new Object[]{param.getOaNo()}, new RowMapper() {
                 @Override
                 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                     OaInfoDO oa = new OaInfoDO();
                     oa.setOaNo(rs.getInt("oaid"));
                     list.add(oa);
                     return list;
                 }
             });
            if(list != null && list.size()<=0){
                jdbcTemplate.batchUpdate("insert into t_oainfo(oaid,creater,createtime,remark) values(?,?,?,?)", new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Date d = new Date();
                        ps.setInt(1,Integer.parseInt(param.getOaNo()));
                        ps.setString(2,param.getCreater());
                        ps.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
                        ps.setString(4,"");
                    }
                    @Override
                    public int getBatchSize() {
                        return 1;
                    }
                });
                status = "SUCCESS";
                if(status.equals("SUCCESS")){
                    //3.记录日志信息
                    content = "用户"+param.getCreater()+"在"+date+"录入一条OA文件编号信息,编号:"+param.getOaNo();
                    systemlogRepository.addSystemLogs(param.getCreater() , content);
                }
            }else{
                status = "EXIT";
            }
        }else{
            status = "ERROR";
        }
        return status;
    }

    public List<OaInfoDO> queryOaInfo(OaRequestParam param){
        final List<OaInfoDO> list = new ArrayList<OaInfoDO>();
        if(param != null){
            StringBuffer sql = new StringBuffer("select * from  t_oainfo");
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql.append(" limit "+20*(param.getPageNum()-1)+","+20*(param.getPageNum()));
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OaInfoDO oa = new OaInfoDO();
                    oa.setOaNo(rs.getInt("oaid"));
                    oa.setCreater(rs.getString("creater"));
                    oa.setCreatetime(rs.getString("createtime"));
                    oa.setRemark(rs.getString("remark"));
                    list.add(oa);
                    return list;
                }
            });
        }
        return list;
    }

    public int queryForCount(OaRequestParam param){
        final int[] count = new int[1];
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) countNum from t_oainfo where 1=1");
            if(!StringUtils.isEmpty(param.getOaNo())){
                sql.append(" and oaid='"+param.getOaNo()+"'");
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    count[0] = rs.getInt("countNum");
                    return count[0];
                }
            });
        }
        return count[0];
    }

    /**
     * 获取用户角色值
     * @param userName
     * @return
     */
    public String getUserRole(String userName){
        String status= "";
        final String[] str = {""};
        jdbcTemplate.query("select b.USERNAME from t_user a inner join t_recharge_config b on a.T_CONFIG_ID=b.id and a.USERNAME=?",new Object[]{userName}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                str[0] = rs.getString("username");
                return str[0];
            }
        });
        if(str[0].equals("出纳") || "财务副总裁".equals(str[0])){
            status = "SUCCESS";
        }else{
            status = "FAIL";
        }
        return status;
    }

    public String modifyOrderInfo(final InteriorRechargeRequestParam param){
        String status = "";
        String content = "";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss").format(new Date());
        if(param != null){
            //1.先判断账号是否存在
            if(!StringUtils.isEmpty(param.getUserName())){
                final List<UserAccountInfoDO> list = userNormalRechargeRepository.queryUserInfoByUserKey(param.getUserName());
                if(list.size()>0){
                    //2.更新订单信息
                    jdbcTemplate.update("update t_interior_recharge_detail set user_id=?,user_key=?,recharge_money=? where recharge_order_no=?", new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            ps.setInt(1,list.get(0).getUserId());
                            ps.setString(2,param.getUserName());
                            ps.setDouble(3,param.getAccountMoney());
                            ps.setString(4,param.getRechargeOrderNo());
                        }
                    });
                    status = "SUCCESS";
                    if(status.equals("SUCCESS")){
                        //3.记录日志信息
                        content = "用户"+param.getPeopleRelease()+"在"+date+"修改了一条充值订单信息,订单号:"+param.getRechargeOrderNo();
                        systemlogRepository.addSystemLogs(param.getPeopleRelease() , content);
                    }
                }else{
                    status = "NOT EXIT";
                }
            }
        }
        return status;
    }
}
