package com.manji.finance.utils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;

/**
 * Created by pudding on 2017-4-17.YYR(数据库工具类)
 */
public class  DButils {
    public static DbPro sqlserver=Db.use("manji");;

    public static DbPro mysql=Db.use("finance");;
}
