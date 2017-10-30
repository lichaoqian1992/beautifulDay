package com.uservice.Dto;

import com.uservice.MybatisUtil.Invisible;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-9-6.(YYR)组
 */
@Data
public class Group {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("组名")
    String group_name;

    @ApiModelProperty("部门ID")
    int dept_id;

    @ApiModelProperty("此小组人数")
    @Invisible
    int groupCount;
}
