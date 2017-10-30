package com.manji.backstage.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by pudding on 2017-5-9.(YYR)
 */
public interface SqlService {

    /**
     * 判断sql执行的什么操作
     * @param sql
     * @return
     */
    int sqlstatic(String sql);

    /**
     *查询
     * @param sql
     * @return
     */
    Map<String, Object> findsql(String sql) throws SQLException;


    /**
     * 修改
     * @param sql
     * @return
     */
    boolean updatesql(String sql) throws SQLException;


}
