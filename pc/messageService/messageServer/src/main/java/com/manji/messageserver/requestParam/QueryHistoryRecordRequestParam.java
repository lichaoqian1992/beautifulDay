package com.manji.messageserver.requestParam;


import lombok.Data;

/**
 * Created by pudding on 2016-12-20.
 */
@Data
public class QueryHistoryRecordRequestParam extends BaseRequestQueryParam {

    /**
     * 发送消息的用户
     */
    private String sendMessageUser;

    /**
     * 接收消息的用户
     */
    private String receiveMessageUser;

    @Override
    public <T> T click() {
        return (T) validator.validate(this);
    }
}
