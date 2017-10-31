package com.uservice.Dto;

import com.uservice.MybatisUtil.Invisible;
import lombok.Data;

/**
 * Created by pudding on 2017-9-6.(YYR)组
 */
@Data
public class Group {

    int id;

    String group_name;

    int dept_id;

    @Invisible
    int groupCount;
}
