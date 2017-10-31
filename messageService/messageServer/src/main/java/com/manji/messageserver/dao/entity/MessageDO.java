package com.manji.messageserver.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by pudding on 2016-12-17.
 */
@Entity
@Table(name = "message")
@Data
public class MessageDO {

    @Id
    @Column(name = "msg_id")
    private String msgId;

    @Column(name = "come")
    private String come;

    @Column(name = "toperson")
    private String to;

    @Column(name = "chat_type")
    private String chatType;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "type")
    private String type;
}
