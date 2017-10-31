package com.manji.messageserver.dao.repository;

import com.manji.messageserver.dao.entity.BlackDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface BlackDAO extends JpaRepository<BlackDO,Integer>,JpaSpecificationExecutor<BlackDO>{
    public BlackDO findByUserNameAndBlackName(String userName , String blackName);
}
