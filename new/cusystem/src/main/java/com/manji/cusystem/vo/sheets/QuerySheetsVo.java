package com.manji.cusystem.vo.sheets;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class QuerySheetsVo {

    private String orSheetTypeId;//工单类型id

    private String orPriority;//紧急程度

    private String startTime="";//开始时间

    private String endTime="";//结束时间

    private String search;//工单编号、主题、标签

    /**
     * 所有工单 alshet
     待提交工单 pendshet
     待我处理 dealshet
     处理完毕待确认工单 ovfishet
     驳回 rejeshet
     抄送 sendshet
     回收站 deleshet
     */
    @NotNull(message = "工单状态orPlate不能为空")
    private String orPlate;
}
