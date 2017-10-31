package com.manji.mlife.mapper;

import com.manji.mlife.model.Card;

public interface CardMapper {
    int deleteByPrimaryKey(String cardno);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(String cardno);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
}