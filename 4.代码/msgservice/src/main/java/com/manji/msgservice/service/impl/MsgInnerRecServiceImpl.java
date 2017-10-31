package com.manji.msgservice.service.impl;

import com.manji.msgservice.common.utils.mybatis.manager.ManagerImpl;
import com.manji.msgservice.mapper.MsgInnerRecMapper;
import com.manji.msgservice.model.MsgInnerRec;
import com.manji.msgservice.model.MsgInnerRecExample;
import com.manji.msgservice.service.MsgInnerRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgInnerRecServiceImpl extends ManagerImpl<MsgInnerRec,MsgInnerRecExample,Long> implements MsgInnerRecService {
    private MsgInnerRecMapper thisMapper;
    @Autowired
    public MsgInnerRecServiceImpl(MsgInnerRecMapper thisMapper){
        this.mapper = thisMapper;
        this.thisMapper = thisMapper;
    }
}
