package com.uservice.Mapper;

import com.uservice.Dto.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2017-8-25.(YYR)
 */
@Mapper
@Component
public interface ResultMapper {

    @Select("select * from jresult where id = #{id}")
    Result findResultByid(@Param("id") String id);
}
