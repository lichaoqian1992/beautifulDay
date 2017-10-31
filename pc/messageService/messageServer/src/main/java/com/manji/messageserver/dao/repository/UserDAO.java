package com.manji.messageserver.dao.repository;

import com.manji.messageserver.dao.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by pudding on 2017-1-5.
 */
public interface UserDAO extends JpaRepository<UserDO,Integer>,JpaSpecificationExecutor<UserDO>{
    public UserDO findByUserName(String userName);
}
