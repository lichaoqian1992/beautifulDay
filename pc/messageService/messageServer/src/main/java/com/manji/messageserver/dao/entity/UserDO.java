package com.manji.messageserver.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by pudding on 2017-1-5.
 */
@Entity
@Table(name = "user")
@Data
public class UserDO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private  String userName;

    @Column(name = "userpassword")
    private String userPassword;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "activated")
    private String activated;

    @Column(name = "status")
    private String status;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "modifytime")
    private Date modifyTime;
}
