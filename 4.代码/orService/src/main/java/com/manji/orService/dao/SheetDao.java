package com.manji.orService.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class SheetDao {

    private int orId;
    private String orNumber;
    private int orFounderId;
    private String orFounderDept;
    private String orTheme;
    private int orPersonId;
    private String orSendList;
    private int orSheetTypeId;
    private String orPriority;
    private String orEstablishTime;
    private String orUpdateTime;
    private String orCompleteTime;
    private String orLabel;
    private int orSheetStatus;
    private String orSheetPics;
    private String orSheetContent;
    private int orConId;

}
