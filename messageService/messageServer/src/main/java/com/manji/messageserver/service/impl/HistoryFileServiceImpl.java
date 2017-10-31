package com.manji.messageserver.service.impl;

import com.google.gson.*;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.service.HistoryFileService;
import com.manji.messageserver.service.HistoryService;
import com.manji.messageserver.utils.HttpJsonTool;
import com.manji.messageserver.utils.StringUtils;
import com.manji.messageserver.vo.HistoryFileVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Administrator on 2016/12/15.
 */
@Service
public class HistoryFileServiceImpl implements HistoryFileService{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private CommonService commonService;

    @Override
    public List<HistoryFileVO> getHistoryFile(String timeInterval){
        String token = commonService.getToken(hxConfig);
        String url = hxConfig.getGetHistoryFileUrl()+timeInterval;
        String result = HttpJsonTool.sendHttpJsonGet(url,token);
        if(StringUtils.isNotEmpty(result) && result.indexOf("error")==-1) {
            List<HistoryFileVO> urls = new ArrayList<HistoryFileVO>();
            Iterator<JsonElement> iterator = new JsonParser().parse(result).getAsJsonObject().get("data").getAsJsonArray().iterator();
            Gson gson = new Gson();
            while (iterator.hasNext()) {
                urls.add(gson.fromJson(iterator.next(), HistoryFileVO.class));
            }
            System.out.println(urls);
            logger.info("https Request  -获取历史记录time={}, result={}", timeInterval, result);
            return urls;
        }else{
            logger.info("https Request  -没有获取到历史记录time={}, result={}", timeInterval, result);
            return null;
        }
    }
}
