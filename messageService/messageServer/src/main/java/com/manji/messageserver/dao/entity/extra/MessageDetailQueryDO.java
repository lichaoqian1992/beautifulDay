package com.manji.messageserver.dao.entity.extra;

import lombok.Data;
import java.io.Serializable;

/**
 * Created by pudding on 2016-12-20.
 */
@Data
public class MessageDetailQueryDO implements Serializable {

    private String msgId;

    private String come;

    private String toperson;

    private String chatType;

    private String type;

    private String msg;

    private String url;

    private String fileName;

    private String addr;

    private Double lat;

    private Double lng;

    private String timestamp;
}
