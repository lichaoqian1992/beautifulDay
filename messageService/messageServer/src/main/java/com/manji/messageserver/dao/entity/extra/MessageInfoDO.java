package com.manji.messageserver.dao.entity.extra;

import lombok.Data;
import java.io.Serializable;

/**
 * Created by pudding on 2016-12-19.
 */
@Data
public class MessageInfoDO implements Serializable {

    private String msgId;

    private String type;

    private String url;

    private String time;

}
