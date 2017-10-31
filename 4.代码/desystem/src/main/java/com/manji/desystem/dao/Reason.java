package com.manji.desystem.dao;

import lombok.Data;

@Data
public class Reason {

    private int cusId;//主键 id，自增长

    private String cusCode;//编码

    private String cusTitle;//标题。描述

    private String cusShortTitle;//简称

    private String cusMergeTitle;//标题

    private String cusLayer;//层级

    private int cusStatus;//状态
}
