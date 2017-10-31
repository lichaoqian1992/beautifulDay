package com.manji.mlife.mapper;

import java.util.List;

import com.manji.mlife.model.TrafficOrder;

public interface TrafficOrderMapper {
	
    int deleteByPrimaryKey(String orderno);

    int insert(TrafficOrder record);

    int insertSelective(TrafficOrder record);

    TrafficOrder selectByPrimaryKey(String orderno);
    
    List<TrafficOrder> selectByPrimaryKey2(String tradeNo);

    int updateByPrimaryKeySelective(TrafficOrder record);

    int updateByPrimaryKey(TrafficOrder record);
    
}