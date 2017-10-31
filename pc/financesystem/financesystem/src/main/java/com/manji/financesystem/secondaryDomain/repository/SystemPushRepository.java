package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.requestParam.SystemPushRequestParam;
import com.manji.financesystem.requestParam.UserAndRoleRequestParam;
import com.manji.financesystem.requestParam.UserInfoRequestParam;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.SystemPushDO;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.entity.extra.UserAndRoleDO;
import com.manji.financesystem.secondaryDomain.entity.extra.UserAndRoleInfoDO;
import com.manji.financesystem.utils.DecriptUtil;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/5.
 */
@Repository
public class SystemPushRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Autowired
    private SystemLogUtilsRepository systemlogRepository;
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    /**
     * 获取短信推送的人员信息
     * @return
     */
    public List<SystemPushDO> getSystemInfo(){
        final List<SystemPushDO> list = new ArrayList<SystemPushDO>();
        jdbcTemplate.query("select * from t_systempush", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                SystemPushDO systemPushDO = new SystemPushDO();
                systemPushDO.setUserId(rs.getInt("user_id"));
                systemPushDO.setUserName(rs.getString("user_name"));
                systemPushDO.setRoleType(rs.getString("role_type"));
                systemPushDO.setRoleValue(rs.getInt("role_value"));
                systemPushDO.setMobile(rs.getString("mobile"));
                systemPushDO.setCreateTime(rs.getString("createtime"));
                systemPushDO.setModifyTime(rs.getString("modifytime"));
                list.add(systemPushDO);
                return list;
            }
        });
        return list;
    }
    /**
     * 添加短信接收人
     * @return
     */
    public String saveSystemPush(final SystemPushRequestParam param){
        final List<SystemPushDO> list = new ArrayList<SystemPushDO>();
        final List<SystemPushDO> list2 = new ArrayList<SystemPushDO>();
        Date date = new Date();
        final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //Assert.isNull(param,"param is not null");
        //先根据用户名和电话号码查询用户的相关信息
        if(param != null){
                //查询一下数据库是否存在
                jdbcTemplate.query("select user_id from t_systempush where mobile=?",new Object[]{param.getMobile()}, new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        SystemPushDO pushDO = new SystemPushDO();
                        pushDO.setUserId(rs.getInt("user_id"));
                        list2.add(pushDO);
                        return list2;
                    }
                });
                if(list2.size()<=0){
                    jdbcTemplate.update("insert into t_systempush(user_id,user_name,role_type,role_value,mobile,createtime,modifytime) values(?,?,?,?,?,?,?)", new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            ps.setInt(1,new Random().nextInt(100));
                            ps.setString(2,param.getUserName());
                            ps.setString(3,"用户");
                            ps.setInt(4,0);
                            ps.setString(5,param.getMobile());
                            ps.setString(6,time);
                            ps.setString(7,time);
                        }
                    });
                }else{
                    return "EXIST";
                }
        }
        return "SUCCESS";
    }

    /**
     * 修改用户信息
     * @param param
     */
    public String modifySystemInfo(final SystemPushRequestParam param){
        String status="";
        final Date date = new Date();
        String sql = "update t_systempush set mobile=?,modifytime=? where user_id=?";
        if(param != null){
            try{
                jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1,param.getMobile());
                        ps.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(date));
                        ps.setInt(3,param.getUserId());
                    }
                });
                status =  "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                status = "ERROR";
            }
        }
        return status;
    }

    /**
     * 删除数据
     * @return
     */
    public String deleteSystemInfo(final SystemPushRequestParam param){
        String status = "";
        String sql="";
        StringBuffer sql1 = new StringBuffer("delete from t_systempush where user_id in(");
        if(param != null ){
            try{
                final String[] str = param.getUserIdList().split(",");
                for(int i=0;i<str.length;i++){
                    if(i == str.length-1){
                        sql1.append("?)");
                    }else{
                        sql1.append("?,");
                    }
                }
                sql = sql1.toString();
                jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        for(int j = 0; j<str.length; j++){
                            ps.setInt((j+1), Integer.parseInt(str[j]));
                        }

                    }
                });
                status = "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                status = "ERROR";
            }
        }
        return status;
    }

    /**
     * 向数据库插入短信的内容
     */
    public void addInfo(final List<UserSmsLogDO> param){
        if(param != null){
            System.out.println(param.size());
            jdbcTemplate2.batchUpdate("insert into dt_user_sms_log(user_id,user_role_type,user_role_value,type,content,user_ip,mobile,add_time,send_status,send_time) values (?,?,?,?,?,?,?,?,?,?)",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setInt(1, param.get(i).getUserId());
                            ps.setString(2, param.get(i).getUserRoleType());
                            ps.setInt(3, param.get(i).getUserRoleValue());
                            ps.setString(4, param.get(i).getType());
                            ps.setString(5, param.get(i).getContent());
                            ps.setString(6, param.get(i).getUserIp());
                            ps.setString(7, param.get(i).getMobile());
                            ps.setString(8, param.get(i).getAddTime());
                            ps.setInt(9, param.get(i).getStatus());
                            ps.setString(10, param.get(i).getSendTime());
                        }
                        public int getBatchSize() {
                            return param.size();
                        }
                    });
        }
    }


    public List<UserDO> queryUserInfo(String userName){
        //1.根据userName查询用户的角色，只有超级管理员才可以看到所有的用户
        final String[] role = {""};
        final List<UserDO> list = new ArrayList<UserDO>();
        jdbcTemplate.query("select role_name from t_role where id=(select id from t_user where username=?)",new Object[]{userName}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                role[0] = rs.getString("role_name");
                return role[0];
            }
        });
        StringBuffer sql = new StringBuffer("select a.*,b.USERNAME as 'roleName' from t_user a inner join t_recharge_config b on a.T_CONFIG_ID=b.id");
        if(role[0].equals("manager")){

        }else{
            sql.append(" and a.username='"+userName+"'");
        }
        jdbcTemplate.query(sql.toString(), new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDO userDO = new UserDO();
                userDO.setId(rs.getInt("id"));
                userDO.setUserName(rs.getString("username"));
                userDO.setRoleName(rs.getString("roleName"));
                userDO.setConfig_id(rs.getInt("t_config_id"));
                userDO.setPassword(rs.getString("password"));
                userDO.setRealName(rs.getString("realname"));
                userDO.setMobile(rs.getString("mobile"));
                userDO.setEmail(rs.getString("email"));
                userDO.setLoginTime(rs.getDate("login_time"));
                userDO.setCreateTime(rs.getDate("create_time"));
                userDO.setLoginCount(Integer.parseInt(rs.getString("login_count")));
                list.add(userDO);
                return list;
            }
        });
        return list;
    }

    public String saveUser(final UserInfoRequestParam param){
        String message = "";
        String content = "";
        final List<UserDO> list = new ArrayList<UserDO>();
        if(param != null){
            jdbcTemplate.query("select * from t_user where username=?",new Object[]{param.getUserName()}, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserDO userDo = new UserDO();
                    userDo.setUserName(rs.getString("username"));
                    list.add(userDo);
                    return list;
                }
            });
            if(list.size()>0){
                message = "EXIT";
            }else{
                jdbcTemplate.update("insert into t_user(username,password,realname,mobile,email,login_time,create_time,login_count,t_config_id) values(?,?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1,param.getUserName());
                        ps.setString(2, DecriptUtil.SHA1(param.getPassword()));
                        ps.setString(3,param.getRealName());
                        ps.setString(4,param.getMobile());
                        ps.setString(5,param.getEmail());
                        ps.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        ps.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        ps.setString(8,"0");
                        ps.setInt(9,Integer.parseInt(param.getRoleName()));
                    }
                });
                message = "SUCCESS";
                //3.记录日志信息
                if(message.equals("SUCCESS")){
                    content = "用户"+param.getCreater()+"在"+date+"新增一个用户信息,用户名:"+param.getUserName();
                    systemlogRepository.addSystemLogs(param.getCreater() , content);
                }
            }
        }else{
            message = "ERROR";
        }
        return message;
    }

    public String modifyUser(final UserInfoRequestParam param){
        String message = "";
        String content = "";
        if(param != null){
            if(!StringUtils.isEmpty(param.getPassword())){
                StringBuffer sql = new StringBuffer("update t_user set password=?,realname=?,mobile=?,email=?,t_config_id=? where id=?");
                jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1,DecriptUtil.SHA1(param.getPassword()));
                        ps.setString(2,param.getRealName());
                        ps.setString(3,param.getMobile());
                        ps.setString(4,param.getEmail());
                        ps.setInt(5,Integer.parseInt(param.getRoleName()));
                        ps.setInt(6,param.getId());
                    }
                });
            }else{
                StringBuffer sql2 = new StringBuffer("update t_user set realname=?,mobile=?,email=?,t_config_id=? where id=?");
                jdbcTemplate.update(sql2.toString(), new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1,param.getRealName());
                        ps.setString(2,param.getMobile());
                        ps.setString(3,param.getEmail());
                        ps.setInt(4,Integer.parseInt(param.getRoleName()));
                        ps.setInt(5,param.getId());
                    }
                });
            }
            message = "SUCCESS";
            //3.记录日志信息
            if(message.equals("SUCCESS")){
                content = "用户"+param.getCreater()+"在"+date+"修改用户信息,用户ID:"+param.getId();
                systemlogRepository.addSystemLogs(param.getCreater() , content);
            }
        }else{
            message = "ERROR";
        }

        return message;
    }

    public String resetUserPassword(final UserInfoRequestParam param){
        String status = "";
        final String password = "123456";
        if(param != null){
            if(!StringUtils.isEmpty(param.getId())){
                int a = jdbcTemplate.update("update t_user set password=? where id=?", new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1,DecriptUtil.SHA1(password));
                        ps.setInt(2,param.getId());
                    }
                });
                if(a>0){
                    status = "SUCCESS";
                }else{
                    status = "FAIL";
                }
                //3.记录日志信息
                if(status.equals("SUCCESS")){
                    String content = "用户" + param.getCreater() + "在" + date + "重置用户的密码,用户ID:" + param.getId();
                    systemlogRepository.addSystemLogs(param.getCreater() , content);
                }
            }else{
                status = "ERROR";
            }
        }
        return status;
    }
    /**
     * 获取角色集合
     * @return
     */
    public List<RechargeConfigDO> getRole(){
        final List<RechargeConfigDO> list = new ArrayList<RechargeConfigDO>();
        jdbcTemplate.query("select * from t_recharge_config", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                RechargeConfigDO rdo = new RechargeConfigDO();
                rdo.setId(rs.getInt("id"));
                rdo.setUserName(rs.getString("username"));
                list.add(rdo);
                return list;
            }
        });

        return list;
    }

    /**
     * 获取页面的权限
     * @param
     * @return
     */
    public List<UserAndRoleDO> queryUserAndRole(){
        final List<UserAndRoleDO> list = new ArrayList<UserAndRoleDO>();
        jdbcTemplate.query("select * from t_jurisdiction", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAndRoleDO userAndRoleDO = new UserAndRoleDO();
                userAndRoleDO.setPageUrl(rs.getString("page_url"));
                userAndRoleDO.setPageDescription(rs.getString("page_description"));
                userAndRoleDO.setRoleId(rs.getInt("role_id"));
                userAndRoleDO.setRoleName(rs.getString("role_name"));
                userAndRoleDO.setDesciption(rs.getString("role_description"));
                userAndRoleDO.setCreatetime(rs.getString("createtime"));
                list.add(userAndRoleDO);
                return list;
            }
        });

        return list;
    }

    /**
     * 查询用户的职位、权限和基本信息
     * @return
     */
    public List<UserAndRoleInfoDO> queryUserAndRoleInfo(){
        final List<UserAndRoleInfoDO> list = new ArrayList<UserAndRoleInfoDO>();
        jdbcTemplate.query("select * from (select c.ID,c.USERNAME,d.USERNAME as configName,d.id as configId from t_user c INNER JOIN t_recharge_config d on c.T_CONFIG_ID=d.id) cc left join (select * from(SELECT a.*,b.USER_ID,b.ROLE_ID FROM t_role a INNER JOIN t_user_role b ON a.id = b.ROLE_ID) dd) mm ON cc.ID=mm.user_id", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAndRoleInfoDO userAndRoleInfoDO = new UserAndRoleInfoDO();
                userAndRoleInfoDO.setUserId(rs.getInt("id"));
                userAndRoleInfoDO.setUserName(rs.getString("username"));
                userAndRoleInfoDO.setConfigId(rs.getInt("configId"));
                userAndRoleInfoDO.setConfigName(rs.getString("configName"));
                userAndRoleInfoDO.setRoleId(rs.getString("role_id"));
                userAndRoleInfoDO.setRoleName(rs.getString("role_name"));
                userAndRoleInfoDO.setRoleDescription(rs.getString("desciption"));
                list.add(userAndRoleInfoDO);
                return list;
            }
        });
        for(int i=0;i<list.size();i++){
            String roleId = list.get(i).getRoleId();
            String roleName = list.get(i).getRoleName();
            String description = list.get(i).getRoleDescription();
            for(int j=(i+1);j<list.size();j++){
                if(list.get(i).getUserId() == list.get(j).getUserId()){
                    roleId += ","+list.get(j).getRoleId();
                    roleName += ","+list.get(j).getRoleName();
                    description += ","+list.get(j).getRoleDescription();
                    list.remove(j);
                    j--;
                }
            }
            list.get(i).setRoleId(roleId);
            list.get(i).setRoleName(roleName);
            list.get(i).setRoleDescription(description);
        }
        return list;
    }

    /**
     * 绑定权限
     * @return
     */
    public String binding(final UserAndRoleRequestParam param){
        String status = "";
        String[] roleId = {""};
        String content = "";
        if(param != null){
            try{
                //1.处理权限编号
                roleId = param.getRoleId().split(",");
                for(int i=0;i<roleId.length;i++){
                    //2.根据权限编号和用户编号查询权限是否存在
                    List<UserAndRoleInfoDO> list = queryUserRole(param.getUserId(),roleId[i]);
                    if(list.size()>0){
                        status = "EXIT";
                    }else{
                        final String[] finalRoleId = roleId;
                        final int finalI = i;
                        jdbcTemplate.update("insert into t_user_role(user_id,role_id) values(?,?)", new PreparedStatementSetter() {
                            @Override
                            public void setValues(PreparedStatement ps) throws SQLException {
                                ps.setString(1,param.getUserId());
                                ps.setString(2, finalRoleId[finalI]);
                            }
                        });
                        status = "SUCCESS";
                    }
                }
                if(status.equals("SUCCESS")){
                    //3.记录日志信息
                    content = "用户"+param.getCreater()+"在"+date+"给用户"+param.getUserName()+"增加权限,权限编号:"+param.getRoleId();
                    systemlogRepository.addSystemLogs(param.getCreater() , content);
                }
            }catch(Exception e){
                e.printStackTrace();
                status = "ERROR";
            }
        }else{
            status = "ERROR";
        }
        return status;
    }

    /**
     * 解除绑定
     * @return
     */
    public String unBinding(final UserAndRoleRequestParam param){
        String status = "";
        String[] roleId = {""};
        String content = "";
        if(param != null){
            try{
                //1.处理权限编号
                roleId = param.getRoleId().split(",");
                for(int i=0;i<roleId.length;i++){
                    //2.根据权限编号和用户编号查询权限是否存在
                    List<UserAndRoleInfoDO> list = queryUserRole(param.getUserId(),roleId[i]);
                    if(list.size()<=0){
                        status = "NOTEXIT";
                    }else{
                        final String[] finalRoleId = roleId;
                        final int finalI = i;
                        int temp = jdbcTemplate.update("delete from t_user_role where user_id=? and role_id=?",new Object[]{param.getUserId(),roleId[i]});
                        if(temp > 0){
                            status = "SUCCESS";
                        }else{
                            status = "ERROR";
                        }
                    }
                }
                if(status.equals("SUCCESS")){
                    //3.记录日志信息
                    content = "用户"+param.getCreater()+"在"+date+"解除了用户"+param.getUserName()+"的权限,权限编号:"+param.getRoleId();
                    systemlogRepository.addSystemLogs(param.getCreater() , content);
                }
            }catch(Exception e){
                e.printStackTrace();
                status = "ERROR";
            }
        }
        return status;
    }

    /**
     * 查询用户是否存在权限
     * @param userId
     * @param roleId
     * @return
     */
    public List<UserAndRoleInfoDO> queryUserRole(String userId , String roleId){
        final List<UserAndRoleInfoDO> list = new ArrayList<UserAndRoleInfoDO>();
        jdbcTemplate.query("select * from t_user_role where user_id=? and role_id=?",new Object[]{userId,roleId}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAndRoleInfoDO userAndRoleInfoDO = new UserAndRoleInfoDO();
                userAndRoleInfoDO.setUserId(rs.getInt("user_id"));
                userAndRoleInfoDO.setRoleId(rs.getString("role_id"));
                list.add(userAndRoleInfoDO);
                return list;
            }
        });
        return list;
    }
}
