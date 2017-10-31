package com.uservice.Dto;

import com.uservice.MybatisUtil.Invisible;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-8-30.(YYR)
 */
@Data
public class Menu {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("页面路径")
    String path;

    @ApiModelProperty("页面名称")
    String title;

    @ApiModelProperty("页面级别")
    String hierarchy;

    @ApiModelProperty("页面排序")
    String sort;

    @ApiModelProperty("操作类型")
    String type_id;

    @ApiModelProperty("项目code")
    String pjt_code;
}
