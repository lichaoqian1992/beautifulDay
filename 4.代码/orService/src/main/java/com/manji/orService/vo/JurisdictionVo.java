package com.manji.orService.vo;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class JurisdictionVo {

    //待提交工单
    private boolean pendshetBoo = false;

    //待我处理
    private boolean dealshetBoo = false;

    //处理完毕待确认工单
    private boolean ovfishetBoo = false;

    //驳回
    private boolean rejeshetBoo = false;


    private SelectSheetVo selectSheetVo;
}
