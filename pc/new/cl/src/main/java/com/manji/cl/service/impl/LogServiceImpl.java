package com.manji.cl.service.impl;

import com.manji.cl.base.BaseResult;
import com.manji.cl.dao.Log;
import com.manji.cl.mapper.LogMapper;
import com.manji.cl.service.LogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/29.
 */
@Service
public class LogServiceImpl implements LogService{

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private LogMapper mapper;

    BaseResult result = new BaseResult();

    /**
     * 保存日志
     * @param log
     * @return
     */
    @Override
    public boolean addLog(Log log) {

        return mapper.addLog(log);
    }
}
