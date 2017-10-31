package com.manji.financesystem.service.impl;
import com.manji.financesystem.enums.RechargeTypeEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.repository.UserInsideRechargeRepository;
import com.manji.financesystem.requestParam.ExamRechargeParam;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.repository.SystemLogUtilsRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import com.manji.financesystem.service.UserInsideRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@Service
public class UserInsideRechargeServiceImpl implements UserInsideRechargeService {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserInsideRechargeRepository userInsideRechargeRepository;

    @Autowired
    private SystemLogUtilsRepository systemlogRepository;

    /**
     * 审批充值或者撤销充值订单
     * @param param
     * @return
     */
    @Override
    public String approvalOrder(final ExamRechargeParam param){
        String checkStatus = "";
        String status = "";
        final int[] userId2 = new int[1];
        final List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
        String oprationType = "";
        String[] oprationTypeList;
        String orderId = "";
        String userId = "";
        String money = "";
        String rechargeType = "";
        String roleType = "";
        String roleValue = "";
        String transn = "";
        String[] strList;
        String[] userIdList;
        String[] moneyList;
        String[] rechargeTypeList;
        String[] roleTypeList;
        String[] roleValueList;
        String[] transnList;
        if(param != null){
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            //处理订单号
            orderId = param.getIdList();//获取单号
            strList = orderId.split(",");//1,2,3,4,5,
            //处理充值账户Id
            userId = param.getUserIdList();
            userIdList = userId.split(",");
            //处理角色类型
            roleType = param.getRoleTypeList();
            roleTypeList = roleType.split(",");
            //处理角色值
            roleValue = param.getRoleValueList();
            roleValueList = roleValue.split(",");
            //处理充值金额
            money = param.getMoneyList();
            moneyList = money.split(",");
            //处理充值类型
            rechargeType = param.getRechargeTypeList();
            rechargeTypeList = rechargeType.split(",");
            for(int i=0;i<strList.length;i++){
                InteriorRechargeDetailDO ido1 = new InteriorRechargeDetailDO();
                ido1.setOrderNo(strList[i]);
                ido1.setRemark(param.getReason());
                ido1.setCheckTime(date);
                ido1.setUserId(Integer.parseInt(userIdList[i]));
                ido1.setRoleType(UserRoleTypeEnums.getCodeByMsg(roleTypeList[i]));
                ido1.setRoleValue(Integer.parseInt(roleValueList[i]));
                ido1.setRechargeMoney(Double.parseDouble(moneyList[i]));
                ido1.setRechargeType(RechargeTypeEnums.getCodeByMessage(rechargeTypeList[i]));
                list.add(ido1);
            }
            status = userInsideRechargeRepository.examRechargeOrder(list,param.getCreater());

        }
        return status;
    }

    /**
     * 会计确认出纳充值申请(充值订单号、操作类型、原因)
     * @param param
     * @return
     */
    @Override
    public String examByAccountor(final ExamRechargeParam param) {
        /**根据订单号修改订单的状态*/
        String content = "";
        String status = "";
        String[] orderId;//充值订单号
        String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if(param != null){
            //处理订单号
            orderId = param.getIdList().split(",");//获取单号
            final List<InteriorRechargeDetailDO> list = new ArrayList<InteriorRechargeDetailDO>();
            for(int i=0;i<orderId.length;i++){
                InteriorRechargeDetailDO ido1 = new InteriorRechargeDetailDO();
                ido1.setOrderNo(orderId[i]);
                ido1.setCheckTime(date2);
                ido1.setRemark(param.getReason());
                list.add(ido1);
            }
            if(param.getAgreeOrRefuse().equals("同意")){
                status = userInsideRechargeRepository.refuseRechargeOrder(list,"4","2");//充值状态不变，还是预充值,已审核
                if(status == "SUCCESS"){
                    content = "用户"+param.getCreater()+"在"+date2+"同意了一些充值申请,充值订单号:"+param.getIdList();
                }
            }else{
                //改变单据的状态并且显示拒绝原因
                status = userInsideRechargeRepository.refuseRechargeOrder(list,"9","3");//充值状态改变，变成撤销充值成功 已拒绝
                if(status == "SUCCESS"){
                    content = "用户"+param.getCreater()+"在"+date2+"拒绝了一些充值申请,充值订单号:"+param.getIdList();
                }
            }
            //3.记录日志信息
            systemlogRepository.addSystemLogs(param.getCreater() , content);
        }

        return status;
    }
}
