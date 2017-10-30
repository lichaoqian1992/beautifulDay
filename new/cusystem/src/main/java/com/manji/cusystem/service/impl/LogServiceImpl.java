package com.manji.cusystem.service.impl;

import com.manji.cusystem.mapper.LogMapper;
import com.manji.cusystem.service.LogService;
import com.manji.cusystem.vo.common.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/29.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper mapper;

    /**
     * 保存日志
     * @param log
     * @return
     */
    @Override
    public boolean addLog(LogVo log) {

        return mapper.addLog(log);
    }
}
