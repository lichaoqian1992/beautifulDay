package com.manji.messageserver.service.impl;

import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.service.HistoryService;
import com.manji.messageserver.utils.HttpJsonTool;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
/**
 * Created by pudding on 2016-12-15.
 */
@Service
public class HistoryServiceImpl implements HistoryService{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService commonService;

    @Override
    public String getHistory() {
        String token = commonService.getToken(hxConfig);
        String url= hxConfig.getGetHistoryUrl();
        String param="select+*+where+timestamp<"+new Date().getTime();
        String encode="";
        try {
            encode = URLEncoder.encode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = HttpJsonTool.sendHttpJsonGet(url+encode, token);
        logger.info(result);
        return null;
    }
}
