package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.GroupStatusEnums;
import com.manji.financesystem.enums.ManagerType;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.extra.GroupInfoAndCountDO;
import com.manji.financesystem.requestParam.GroupInfoRequestParam;
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
 * Created by Administrator on 2017/3/3.
 */
@Repository
public class GroupInfoRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询集团账户信息
     * @param param
     * @return
     */
    public List<GroupInfoAndCountDO> queryGroupInfo(GroupInfoRequestParam param){
        final List<GroupInfoAndCountDO> list = new ArrayList<GroupInfoAndCountDO>();
        final GroupInfoAndCountDO groupInfoAndCountDO = new GroupInfoAndCountDO();
        final int[] countNum = new int[1];
        final int[] settledCount = new int[1];
        if(param != null){
            //1.先查询集团账户信息
            StringBuffer sql = new StringBuffer("select * from(select *,ROW_NUMBER() over(order by ID)as 'userID'from dt_user_role_shopinfo_group where status='1'");
            StringBuffer sql2 = new StringBuffer(")as a where 1=1");
            if(!StringUtils.isEmpty(param.getGroupName())){
                sql.append(" and group_name like '%"+param.getGroupName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getManagerType())){
                sql.append(" and manager_type='"+param.getManagerType()+"'");
            }
            if(!StringUtils.isEmpty(param.getStartTime())){
                sql.append(" and add_time >='"+param.getStartTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getEndTime())){
                sql.append(" and add_time <'"+param.getEndTime()+"'");
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append(" and a.userID between ((20*"+(param.getPageNum()-1)+")+1)"+" and (20*"+param.getPageNum()+")");
            }
            sql.append(sql2);
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupInfoAndCountDO groupInfoAndCountDO = new GroupInfoAndCountDO();
                    groupInfoAndCountDO.setUserId(rs.getInt("user_id"));
                    groupInfoAndCountDO.setUserName(rs.getString("group_name"));
                    groupInfoAndCountDO.setRoleType(UserRoleTypeEnums.getMsgByCode(rs.getString("user_role_type")));
                    groupInfoAndCountDO.setRoleValue(rs.getInt("user_role_value"));
                    groupInfoAndCountDO.setManagerType(ManagerType.getMessageByCode(rs.getString("manager_type")));
                    groupInfoAndCountDO.setGroupStatus(GroupStatusEnums.getMessageByCode(rs.getString("status")));
                    groupInfoAndCountDO.setAddTime(rs.getString("add_time"));
                    groupInfoAndCountDO.setUpdateTime(rs.getString("update_time"));
                    groupInfoAndCountDO.setUpdateStatus(rs.getString("remark"));
                    list.add(groupInfoAndCountDO);
                    return list;
                }
            });
            for(int i=0;i<list.size();i++){
                //2.查询集团商家总数
                jdbcTemplate.query("select count(*) countNum from dt_user_role_shopinfo_grouprelation a where a.groupshop_id=? and a.status='1'",new Object[]{list.get(i).getRoleValue()}, new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        countNum[0] = rs.getInt("countNum");
                        return countNum[0];
                    }
                });
                list.get(i).setShopCount(countNum[0]);
                //3.查询集团账户下入驻商家数量
                jdbcTemplate.query("select count(*) settledCount,(sum(a.amount)-sum(a.allow_amount)) notAllowAmount from dt_user_accountinfo a inner join dt_user_role_shopinfo_grouprelation b on a.user_id=b.shop_id and b.status='1' and b.groupshop_id=?",new Object[]{list.get(i).getRoleValue()}, new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        groupInfoAndCountDO.setSettledCount(rs.getInt("settledCount"));
                        groupInfoAndCountDO.setNotAllowAmount(rs.getDouble("notAllowAmount"));
                        return groupInfoAndCountDO;
                    }
                });
                list.get(i).setSettledCount(groupInfoAndCountDO.getSettledCount());
                list.get(i).setNotAllowAmount(groupInfoAndCountDO.getNotAllowAmount());
            }
        }
        return list;
    }
}
