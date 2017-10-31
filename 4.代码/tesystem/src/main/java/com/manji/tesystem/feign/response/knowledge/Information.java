package com.manji.tesystem.feign.response.knowledge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Information{
    @ApiModelProperty("ID")
    Integer id;
    @ApiModelProperty("标题")
    String title;
    @ApiModelProperty("内容")
    String content;
    @ApiModelProperty("分类")
    String category;
    @ApiModelProperty("树ID")
    Integer tree_id;
    @ApiModelProperty("状态")
    Integer state;
    @ApiModelProperty("添加时间")
    Date add_time;
}
