package com.manji.orService.vo;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class CcSendVo {

    private String orId;
    private String orCcId;
    private String orCcSheetId;
    private String orCcPersonId;
    private String orCcPersonName;
    private String orCcPersonDept;
    private String orCcTime;
    private String orIsSee="1";
}
