package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.vo.message.MessageVo;
import com.manji.cusystem.vo.message.QueryMessageVo;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface MessageService {

    BaseResult addMessage(MessageVo vo, Account user,String oType,String url);

    BaseResult selectMessage(QueryMessageVo vo);

    BaseResult selectMessageDetail(String cusId);

    BaseResult delMessage(String cusId);

    BaseResult getCount(String acceptType,String cusKind);

    BaseResult updateMessage(MessageVo vo, Account user);
}
