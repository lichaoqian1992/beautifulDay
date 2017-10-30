package com.manji.cusystem.mapper;

import com.manji.cusystem.utils.SimpleInsertLangDriver;
import com.manji.cusystem.vo.common.LogVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/8/29.
 */
@Mapper
public interface LogMapper {

    /**
     * 添加日志
     * @param log
     * @return
     */
    @Insert("insert into cus_logs (#{log})")
    @Lang(SimpleInsertLangDriver.class)
    boolean addLog(LogVo log);
}
