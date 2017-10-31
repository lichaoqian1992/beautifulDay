package com.uservice.Mapper;

import com.uservice.Dto.Operationlog;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2017-8-28.(YYR)
 */
@Mapper
@Component
public interface OperationlogMapper {

    /**
     * 增加日志信息
     * @param operationlog
     * @return
     */
    @Insert("INSERT INTO joperationlog (#{operationlog})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertOperationlog(Operationlog operationlog);

}
