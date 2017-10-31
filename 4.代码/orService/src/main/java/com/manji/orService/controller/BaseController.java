package com.manji.orService.controller;

import com.alibaba.fastjson.JSON;
import com.manji.orService.common.Message;
import com.manji.orService.util.WebUtil;
import com.manji.orService.vo.CommonSheetVo;
import com.manji.orService.vo.HandleVo;
import com.manji.orService.vo.InfoVo;
import com.manji.orService.vo.SheetVo;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    int code = 0;
    String message = "";

    //return错误信息
    public Object message(int state, String message, Object object) {

        Message messageReturn = new Message();

        messageReturn.setState(state);
        messageReturn.setMessage(message);
        messageReturn.setDatas(object);

        Object jsonString = JSON.toJSON(messageReturn);

        return jsonString;

    }


    /**
     * 存储数据
     *
     * @param commonSheetVo
     * @return
     */
    public Map<String, Object> IncommonSheetVo(CommonSheetVo commonSheetVo) {

        SimpleDateFormat Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<String, Object>();
        InfoVo infoVo = new InfoVo();
        SheetVo sheetVo = new SheetVo();
        HandleVo handleVo = new HandleVo();

        infoVo.setCusTel(commonSheetVo.getCusTel());
        infoVo.setCusType(commonSheetVo.getCusType());
        infoVo.setCusName(commonSheetVo.getCusName());
        infoVo.setCusAccount(commonSheetVo.getCusAccount());
        infoVo.setCusMobile(commonSheetVo.getCusMobile());
        infoVo.setCusArea(commonSheetVo.getCusArea());
        infoVo.setCusUserTime(commonSheetVo.getCusUserTime());
        infoVo.setCusShopTime(commonSheetVo.getCusShopTime());
        infoVo.setCusStatus(commonSheetVo.getCusStatus());
        infoVo.setCusState(commonSheetVo.getCusState());

        sheetVo.setOrTheme(commonSheetVo.getOrTheme());
        sheetVo.setOrNumber(WebUtil.GeneratWorkerNumber());
        sheetVo.setOrPersonId(String.valueOf(commonSheetVo.getOrHandleId()));
        sheetVo.setOrSendList(commonSheetVo.getOrSendList());
        sheetVo.setOrSheetTypeId(commonSheetVo.getOrSheetTypeId());
        sheetVo.setOrPriority(commonSheetVo.getOrPriority());
        sheetVo.setOrEstablishTime(Time.format(new Date()));
        sheetVo.setOrUpdateTime(Time.format(new Date()));
        sheetVo.setOrCompleteTime(commonSheetVo.getOrCompleteTime());
        sheetVo.setOrSheetStatus("1");
        sheetVo.setOrLabel(commonSheetVo.getOrLabel());
        sheetVo.setOrSheetPics(commonSheetVo.getOrSheetPics());
        sheetVo.setOrSheetContent(commonSheetVo.getOrSheetContent());
        sheetVo.setOrConId(commonSheetVo.getOrConId());

        handleVo.setOrHandleId(String.valueOf(commonSheetVo.getOrHandleId()));
        handleVo.setOrHandle(commonSheetVo.getOrHandle());
        handleVo.setOrHandleDept(commonSheetVo.getOrHandleDept());

        map.put("infoVo", infoVo);
        map.put("sheetVo", sheetVo);
        map.put("handleVo", handleVo);

        return map;
    }

    /**
     * 修改数据
     *
     * @param commonSheetVo
     * @return
     */
    public Map<String, Object> UpcommonSheetVo(CommonSheetVo commonSheetVo) {

        SimpleDateFormat Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<String, Object>();
        InfoVo infoVo = new InfoVo();
        SheetVo sheetVo = new SheetVo();
        HandleVo handleVo = new HandleVo();

        infoVo.setCusTel(commonSheetVo.getCusTel());
        infoVo.setCusType(commonSheetVo.getCusType());
        infoVo.setCusName(commonSheetVo.getCusName());
        infoVo.setCusAccount(commonSheetVo.getCusAccount());
        infoVo.setCusMobile(commonSheetVo.getCusMobile());
        infoVo.setCusArea(commonSheetVo.getCusArea());
        infoVo.setCusUserTime(commonSheetVo.getCusUserTime());
        infoVo.setCusShopTime(commonSheetVo.getCusShopTime());
        infoVo.setCusStatus(commonSheetVo.getCusStatus());
        infoVo.setCusState(commonSheetVo.getCusState());

        sheetVo.setOrId(commonSheetVo.getOrId());
        sheetVo.setOrTheme(commonSheetVo.getOrTheme());
        sheetVo.setOrPersonId(String.valueOf(commonSheetVo.getOrHandleId()));
        sheetVo.setOrSendList(commonSheetVo.getOrSendList());
        sheetVo.setOrSheetTypeId(commonSheetVo.getOrSheetTypeId());
        sheetVo.setOrPriority(commonSheetVo.getOrPriority());
        sheetVo.setOrUpdateTime(Time.format(new Date()));
        sheetVo.setOrCompleteTime(commonSheetVo.getOrCompleteTime());
        sheetVo.setOrLabel(commonSheetVo.getOrLabel());
        sheetVo.setOrSheetPics(commonSheetVo.getOrSheetPics());
        sheetVo.setOrSheetContent(commonSheetVo.getOrSheetContent());

        handleVo.setOrHandleId(String.valueOf(commonSheetVo.getOrHandleId()));
        handleVo.setOrHandle(commonSheetVo.getOrHandle());
        handleVo.setOrHandleDept(commonSheetVo.getOrHandleDept());

        map.put("infoVo", infoVo);
        map.put("sheetVo", sheetVo);
        map.put("handleVo", handleVo);

        return map;
    }
}
