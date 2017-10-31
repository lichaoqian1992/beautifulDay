package com.manji.msgservice.service;

import com.manji.msgservice.common.utils.mybatis.manager.Manager;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.model.MsgTemplateExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MsgTemplateService extends Manager<MsgTemplate,MsgTemplateExample,Long> {
}
