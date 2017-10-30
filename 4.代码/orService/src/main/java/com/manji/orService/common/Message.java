package com.manji.orService.common;

import lombok.Data;

/**
 * 信息返回列表
 * Created by Administrator on 2017/8/31.
 */

@Data
public class Message {
    int  state;
    String  message;
    Object  datas;

    public Message() {}

    public Message(int state, String message, Object datas) {
        this.state = state;
        this.message = message;
        this.datas = datas;
    }
}
