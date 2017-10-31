package com.manji.messageserver.dao.repository;

import com.manji.messageserver.dao.entity.FriendsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface FriendsDAO extends JpaRepository<FriendsDO,Integer>,JpaSpecificationExecutor<FriendsDO>{
    public FriendsDO findByUserNameAndFriendsName(String userName,String friendsName);
}
