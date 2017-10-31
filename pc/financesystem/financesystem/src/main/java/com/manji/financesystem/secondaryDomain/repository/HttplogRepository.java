package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.secondaryDomain.entity.HttplogDO;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-3-3.
 */
@Repository
public class HttplogRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     *添加请求日志
     * @return
     */
    public String setHttplog(final HttplogDO log){
        String sql="INSERT INTO t_Http_log values (NULL,?,?,?,?,?)";
        String status="";
        final Date date = new Date();
        try{
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1,log.getAction());
                    ps.setString(2,log.getQuerySN());
                    ps.setString(3,log.getTranSN());
                    ps.setString(4,log.getResultData());
                    ps.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(date));
                }
            });
            status =  "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
            status="ERROR";
        }
        return status;
    }



}
