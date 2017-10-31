package com.manji.cl.mapper;

import com.manji.cl.dao.Log;
import com.manji.cl.mybatisUtils.SimpleInsertLangDriver;
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
    @Insert("insert into cl_logs (#{log})")
    @Lang(SimpleInsertLangDriver.class)
    boolean addLog(Log log);
}
