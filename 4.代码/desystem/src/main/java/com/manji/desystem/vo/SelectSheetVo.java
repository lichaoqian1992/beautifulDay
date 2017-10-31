package com.manji.desystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class SelectSheetVo {

    @ApiModelProperty(value = "工单类型ID")
    private String orSheetTypeId;

    @ApiModelProperty(value = "紧急程度")
    private String orPriority;

    @ApiModelProperty(value = "工单类型")
    private String orSheetTypeString;

    @ApiModelProperty(value = "开始时间")
    private String startTime="";

    @ApiModelProperty(value = "结束时间")
    private String endTime="";

    @ApiModelProperty(value = "搜索框")
    private String search;

    @ApiModelProperty(value = "查询板块",required = true)
    @NotBlank(message = "参数[orPlate]不能为空")
    private String orPlate;

    @ApiModelProperty(value = "当前页",required = true)
    @NotBlank(message = "参数[pageNum]不能为空")
    private String pageNum;

    @ApiModelProperty(value = "每页条数",required = true)
    @NotBlank(message = "参数[pageSize]不能为空")
    private String pageSize;

}
