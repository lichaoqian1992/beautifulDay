package com.manji.tesystem.feign.response.knowledge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Tree {
    @ApiModelProperty("ID")
    int id;
    @ApiModelProperty("菜单ID")
    int menu_id;
    @ApiModelProperty("标题")
    String title;
    @ApiModelProperty("左")
    String left;
    @ApiModelProperty("右")
    String rigt;
}
