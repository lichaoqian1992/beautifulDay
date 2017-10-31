package com.manji.messageserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pudding on 2016-12-13.
 */
@Component
@ConfigurationProperties(locations = "classpath:config/hx.properties", prefix = "hx")
@Data
public class HxConfig {

    private String clientId;

    private String clientSecret;

    private String tokenUrl;

    private String registerUrl;

    private String queryUserUrl;

    private String queryAllUserUrl;

    private String modifyNicknameUrl;

    private String modifyUserPasswordUrl;

    private String addFriendsUrl;

    private String queryUserBlackUrl;

    private String addUserBlackUrl;

    private String removeUserBlackUrl;

    private String getHistoryUrl;

    private String getHistoryFileUrl;

    private String getUserStatusUrl;

    private String deleteUserUrl;

    private String deleteUserBatchUrl;

    private String getZipFileUrl;

    private String getUnZipFileUrl;

    private String imgDirectory;

    private String audioDirectory;

    private String videoDirectory;

    private String fileDirectory;

}
