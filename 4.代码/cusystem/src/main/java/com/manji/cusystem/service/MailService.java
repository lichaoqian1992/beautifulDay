package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.vo.message.MessageVo;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface MailService {

    BaseResult addMail(MessageVo vo, Account user,String oType);
}
