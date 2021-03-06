package com.manji.msgservice.common.utils.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Mapper<R, E, ID> {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(ID id);

    int insert(R record);

    List<R> selectByExample(E example);

    R selectByPrimaryKey(ID id);

    int updateByExampleSelective(@Param("record") R record, @Param("example") E example);

    int updateByPrimaryKeySelective(R record);

}
