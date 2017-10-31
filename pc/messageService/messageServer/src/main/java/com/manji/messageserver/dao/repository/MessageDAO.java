package com.manji.messageserver.dao.repository;

import com.manji.messageserver.dao.entity.MessageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by pudding on 2016-12-17.
 */
public interface MessageDAO extends JpaRepository<MessageDO,String>,JpaSpecificationExecutor<MessageDO> {
}
