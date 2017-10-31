package com.manji.messageserver.service;

import com.manji.messageserver.config.HxConfig;

/**
 * Created by pudding on 2016-12-13.
 */
public interface CommonService {
    /**
     * 获取授权token
     * @param hxConfig 环信配置
     * @return
     */
    public String getToken(HxConfig hxConfig);
}
