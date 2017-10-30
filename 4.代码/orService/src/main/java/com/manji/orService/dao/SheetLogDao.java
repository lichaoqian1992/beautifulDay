package com.manji.orService.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class SheetLogDao {

    private Integer orId;
    private int orSheetId;
    private int orPersonId;
    private String orPerson;
    private String orPersonDept;
    private int orHandleId;
    private String orHandle;
    private String orHandleDept;
    private String orOperationTime;
    private int orOperationTypeId;
    private String orOperationTypeDescr;
    private String orOperationContent;

}
