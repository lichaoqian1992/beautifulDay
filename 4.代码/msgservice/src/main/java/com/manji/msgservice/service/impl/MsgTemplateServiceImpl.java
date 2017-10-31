package com.manji.msgservice.service.impl;

import com.manji.msgservice.common.utils.mybatis.manager.ManagerImpl;
import com.manji.msgservice.mapper.MsgTemplateMapper;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.model.MsgTemplateExample;
import com.manji.msgservice.service.MsgTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgTemplateServiceImpl extends ManagerImpl<MsgTemplate,MsgTemplateExample,Long> implements MsgTemplateService {
    private MsgTemplateMapper thisMapper;
    @Autowired
    public MsgTemplateServiceImpl(MsgTemplateMapper thisMapper){
        this.mapper = thisMapper;
        this.thisMapper = thisMapper;
    }
}
