package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.vo.OrderVo;
import com.manji.cusystem.vo.conversation.AddConversationVo;
import com.manji.cusystem.vo.conversation.AddCustomerVo;
import com.manji.cusystem.vo.conversation.CustomerVo;
import com.manji.cusystem.vo.conversation.OrderAndConVo;


/**
 * Created by Administrator on 2017/8/31.
 */
public interface ConversationService {

    BaseResult findConversation(AddConversationVo vo,Account user);

    BaseResult addConversation(CustomerVo customer,Account user,String type,String cus_id,String orderId);

    BaseResult findCustomer(String cus_name,String mobile,String name,String userType,Account user,String type);

    BaseResult selectCustomerInfo(String mobile,String userName,String tabType,String userType,Account user,String pageSize,String pageNumber);

    BaseResult findCustomerInfo(String user_name,String cus_type,Account user);

    BaseResult findFirstReason();

    BaseResult findSecondReason(String code);

    BaseResult addNewConversation(Account user,String userName,String roleType);

    BaseResult findOrderAndCon(OrderVo vo);

    BaseResult findOrderDetail(String orderId);

    BaseResult findOrderConversation(String type,String mobile,String pageSize,String pageNumber,Account user);

    String toExcel(AddConversationVo vo,Account user);

    BaseResult getConversationToday();

    BaseResult findConversationById(String cusId, Account user);

    BaseResult findConversationCount(String cusTime,String cusWay,String cusFrom,String other,String pageNumber,String pageSize);

    BaseResult getUserNameByPersonName(String personName, Account user);

    BaseResult getOrderBackDetail(String orderNo);

    BaseResult findCusInfo(String mobile, String type);
}
