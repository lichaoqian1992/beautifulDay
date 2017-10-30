package com.manji.tesystem.feign.response.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class Menu {
    @ApiModelProperty("链接地址")
    private String path;//链接地址
    @ApiModelProperty("页面名称")
    private String title;//页面名称
    @ApiModelProperty("页面描述")
    private String type_title;//页面描述
    @ApiModelProperty("排序")
    private String sort;//排序
    @ApiModelProperty("层级")
    private String hierarchy;//层级
}
