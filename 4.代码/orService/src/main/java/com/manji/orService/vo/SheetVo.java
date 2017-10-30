package com.manji.orService.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class SheetVo {



    /*Sheet*/
    private String orId;
    private String orNumber;
    private String orFounderId;
    private String orFounderDept;
    private String orTheme;
    private String orPersonId;
    private String orSendList;
    private String orSheetTypeId;
    private String orPriority;
    private String orEstablishTime;
    private String orUpdateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private String orCompleteTime;
    private String orLabel;
    private String orSheetPics;
    private String orSheetContent;
    private String orSheetStatus;
    private String orConId;

}
