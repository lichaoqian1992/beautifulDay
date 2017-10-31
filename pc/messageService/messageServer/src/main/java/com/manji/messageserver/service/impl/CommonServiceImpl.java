package com.manji.messageserver.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.service.CommonService;
import com.manji.messageserver.utils.HttpJsonTool;
import com.manji.messageserver.utils.StringUtils;
import com.manji.messageserver.vo.TokenVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
/**
 * Created by pudding on 2016-12-13.
 */
@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);
    @Autowired
    private RedisTemplate<String,String> tokenRedisTemplate;

    @Override
    public String getToken(final HxConfig hxConfig) {
        String tokenStr = tokenRedisTemplate.opsForValue().get("token");
        if(StringUtils.isEmpty(tokenStr)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("grant_type", "client_credentials");
            jsonObject.addProperty("client_id", hxConfig.getClientId());
            jsonObject.addProperty("client_secret", hxConfig.getClientSecret());
            String result = HttpJsonTool.sendHttpJsonPost(hxConfig.getTokenUrl(), jsonObject);
            Gson gson = new Gson();
            TokenVO tokenVO = gson.fromJson(result, TokenVO.class);
            logger.info("https Request  -获取授权管理员 token={}", tokenVO);
            Long time = Long.valueOf(tokenVO.getExpires_in());
            tokenRedisTemplate.opsForValue().set("token", tokenVO.getAccess_token());
            tokenRedisTemplate.expire("token", time - 20, TimeUnit.SECONDS);
            return tokenVO.getAccess_token();
        }else{
            return tokenStr;
        }
    }
}
