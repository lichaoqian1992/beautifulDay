package com.ackservice.Dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-9-13.(YYR)
 */
@Data
public class Information{

    Integer id;

    String title;

    String content;

    String category;

    Integer tree_id;

    Integer state;

    Date add_time;
}
