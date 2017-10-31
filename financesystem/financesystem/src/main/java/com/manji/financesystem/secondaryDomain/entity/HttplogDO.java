package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-3-8.
 */
@Data
public class HttplogDO {

    private int id;

    private String Action;

    private String QuerySN;

    private String TranSN;

    private String ResultData;

    private String Createdate;


}
