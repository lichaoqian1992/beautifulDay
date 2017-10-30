package com.manji.orService.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class CcSend {

    private Integer orId;
    private int orCcId;
    private int orCcSheetId;
    private int orCcPersonId;
    private String orCcPersonName;
    private String orCcPersonDept;
    private String orCcTime;
    private String orIsSee;
}
