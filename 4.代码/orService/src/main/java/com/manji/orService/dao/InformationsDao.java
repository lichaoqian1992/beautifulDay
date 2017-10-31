package com.manji.orService.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class InformationsDao {

    private int orId;
    private int orSheetId;
    private int orPersonId;
    private int orHandleId;
    private String orInformationsTime;
    private String orRelevantContent;

}
