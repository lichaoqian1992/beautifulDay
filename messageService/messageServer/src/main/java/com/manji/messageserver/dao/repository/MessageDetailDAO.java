package com.manji.messageserver.dao.repository;

import com.manji.messageserver.dao.entity.MessageDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by pudding on 2016-12-17.
 */
public interface MessageDetailDAO extends JpaRepository<MessageDetailDO,String>,JpaSpecificationExecutor<MessageDetailDO> {

    public MessageDetailDO findByMsgId(String msgId);







}
