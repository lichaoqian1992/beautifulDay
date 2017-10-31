package com.manji.msgservice.service.impl;

import com.manji.msgservice.mapper.MsgBizTypeMapper;
import com.manji.msgservice.model.MsgBizType;
import com.manji.msgservice.model.MsgBizTypeExample;
import com.manji.msgservice.service.MsgBizTypeService;
import com.manji.msgservice.common.utils.mybatis.manager.ManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgBizTypeServiceImpl extends ManagerImpl<MsgBizType,MsgBizTypeExample,Long> implements MsgBizTypeService {
    private MsgBizTypeMapper thisMapper;
    @Autowired
    public MsgBizTypeServiceImpl(MsgBizTypeMapper thisMapper){
        this.mapper = thisMapper;
        this.thisMapper = thisMapper;
    }
}
