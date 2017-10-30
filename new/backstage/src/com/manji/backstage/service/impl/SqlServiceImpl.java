package com.manji.backstage.service.impl;

import com.manji.backstage.service.SqlService;
import com.manji.backstage.utils.SQLUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pudding on 2017-5-9.(YYR)
 */
@Service
public class SqlServiceImpl implements SqlService {

    private final static String SELECT="SELECT";

    private final static String UPDATE="UPDATE";

    private final static String INSERT="INSERT";

    private final static String DELECT="DELECT";


    @Override
    public int sqlstatic(String sql) {
        sql=sql.toUpperCase();
        int is=0;
        int select=sql.indexOf(SELECT);
        if (select>-1){
            is=1;
            return is;
        }
        int update=sql.indexOf(UPDATE);
        if (update>-1){
            is=2;
            return is;
        }
        int insert=sql.indexOf(INSERT);
        if (insert>-1){
            is=3;
            return is;
        }
        int delect=sql.indexOf(DELECT);
        if (delect>-1){
            is=4;
            return is;
        }
        return is;
    }

    @Override
    public Map<String, Object> findsql(String sql) throws SQLException {
        Map<String, Object> map=new HashMap<String, Object>();
        List<Object> coulm=new ArrayList<Object>();


            ResultSet rs=SQLUtils.executeQuery(sql);

            ResultSetMetaData resultsetmetadata = rs.getMetaData();

            String columnName="";
            List<Object> val=new ArrayList<Object>();
                //循环存储列
                for(int i =0;i<resultsetmetadata.getColumnCount();i++) {
                    columnName = resultsetmetadata.getColumnName(i + 1);
                    coulm.add(columnName);
                }
                //循环存储值
                while (rs.next())
                {
                    for(int i =0;i<resultsetmetadata.getColumnCount();i++){
                        columnName=resultsetmetadata.getColumnName(i+1);
                        val.add(rs.getString(columnName));
                    }
                }

        map.put("column",coulm);
        map.put("val",val);
        rs.close();
        return map;
    }



    @Override
    public boolean updatesql(String sql) throws SQLException {
        return SQLUtils.executeUpdate(sql);
    }


}