package com.manji.financesystem.secondaryDomain.repository;

import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统日志新增
 * Created by Administrator on 2017/3/13.
 */
@Repository
public class SystemLogUtilsRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SystemlogRepository systemlogRepository;

    public void addSystemLogs(String creater, String content){
        final int[] userId = new int[1];
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //3.记录日志信息
        jdbcTemplate.query("select id from t_user where username=?",new Object[]{creater}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                userId[0] = rs.getInt("id");
                return userId[0];
            }
        });
        Systemlog systemlog = new Systemlog();
        systemlog.setUser_name(creater);
        systemlog.setUserId(userId[0]);
        systemlog.setLog_info(content);
        systemlog.setCreate_time(date);
        systemlogRepository.setSysteminfo(systemlog);
    }
}
