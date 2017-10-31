package com.manji.orService.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class CommonSheetVo {


    /*Sheet*/
    private String orId;
    @NotBlank(message = "参数[orTheme]不能为空")
    private String orTheme;
    private String orSendList;
    @NotBlank(message = "参数[orSheetTypeId]不能为空")
    private String orSheetTypeId;
    @NotBlank(message = "参数[orPriority]不能为空")
    private String orPriority;
    private String orUpdateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private String orCompleteTime;
    private String orLabel;
    private String orSheetContent;
    private String orSheetPics;
    private String orConId;

    /*ConSheet*/
    private String cusTel;
    private String cusType;
    private String cusName;
    private String cusAccount;
    private String cusMobile;
    private String cusArea;
    private String cusUserTime;
    private String cusShopTime;
    private String cusStatus;
    private String cusState;

    @NotNull(message = "参数[orHandleId]不能为空")
    private int orHandleId;
    @NotBlank(message = "参数[orHandle]不能为空")
    private String orHandle;
    @NotBlank(message = "参数[orHandleDept]不能为空")
    private String orHandleDept;

}
