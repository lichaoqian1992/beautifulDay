package com.uservice.Mapper;

import com.uservice.Dto.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2017-8-28.(YYR)
 */
@Mapper
@Component
public interface ProjectMapper {

    /**
     * 通过项目code查询对应项目
     * @param pjt_code
     * @return
     */
    @Select("select * from jproject where pjt_code=#{0}")
    Project findProjectByCode(String pjt_code);
}
