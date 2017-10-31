package com.manji.messageserver.dao.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by pudding on 2016-12-17.
 */
@Entity
@Table(name = "messagedetail")
@Data
public class MessageDetailDO {

    @Id
    @Column(name = "msg_id")
    private String msgId;

    @Column(name = "msg")
    private String msg;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "filename")
    private String filename;

    @Column(name = "thumb")
    private String thumb;

    @Column(name = "secret")
    private String secret;

    @Column(name = "thumb_secret")
    private String thumbSecret;

    @Column(name = "length")
    private Integer length;

    @Column(name = "addr")
    private String addr;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "file_length")
    private Long  fileLength;
}
