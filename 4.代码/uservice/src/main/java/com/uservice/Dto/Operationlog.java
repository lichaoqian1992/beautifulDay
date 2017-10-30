package com.uservice.Dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-8-28.(YYR)
 */
@Data
public class Operationlog {

    Integer id;

    int op_type;

    String content;

    int user_id;

    Date add_time;

    String important;
}
