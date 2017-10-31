package com.manji.mlife.mapper;

import java.util.List;

import com.manji.mlife.model.ChargeOrder;

public interface ChargeOrderMapper {
	
    int deleteByPrimaryKey(String billid);

    int insert(ChargeOrder record);

    int insertSelective(ChargeOrder record);

    ChargeOrder selectByPrimaryKey(String billid);
    
    ChargeOrder selectByOuterId(String outerId);

    int updateByPrimaryKeySelective(ChargeOrder record);

    int updateByPrimaryKey(ChargeOrder record);

	List<ChargeOrder> selectByPrimaryKey2(String outerId);

	void deleteByPrimaryKey2(String tradeno);
}