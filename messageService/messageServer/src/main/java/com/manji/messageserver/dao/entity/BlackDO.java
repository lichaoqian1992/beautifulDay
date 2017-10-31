package com.manji.messageserver.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/5.
 */
@Entity
@Table(name = "black")
@Data
public class BlackDO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "blackname")
    private String blackName;

    @Column(name = "createtime")
    private Date createTime;
}
