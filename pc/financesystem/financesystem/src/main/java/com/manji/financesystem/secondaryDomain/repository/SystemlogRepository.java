package com.manji.financesystem.secondaryDomain.repository;

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
public class SystemlogRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 获取日志表记录
     * @return
     */
    public List<Systemlog> getSystemInfo(final int page){
        final List<Systemlog> list = new ArrayList<Systemlog>();
        final int pagen=(page-1)* 20;
        final int pageg=20;
        jdbcTemplate.query("select  * from t_system_log  LIMIT ? ,? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, pagen);
                        preparedStatement.setInt(2, pageg);
                    }
                }
              ,new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Systemlog systemlog = new Systemlog();
                systemlog.setId(rs.getInt("id"));
                systemlog.setUserId(rs.getInt("user_id"));
                systemlog.setLog_info(rs.getString("log_info"));
                systemlog.setCreate_time(rs.getString("Create_time"));
                systemlog.setUser_name(rs.getString("User_name"));
                list.add(systemlog);
                return list;
            }
        });
        return list;
    }


    /**
     * 获取总条数
     * @return
     */
    public int getcount(){
        String sql="SELECT COUNT(*) from t_system_log;";
        return jdbcTemplate.queryForObject(sql,int.class);
    }


    /**
     *添加系统日志
     * @return
     */
    public String setSysteminfo(final Systemlog log){
        String sql="INSERT INTO t_system_log values (NULL,?,?,?,?)";
        String status="";
        SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
         final String date=s.format(new Date());

        try{
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1,log.getUserId());
                    ps.setString(2,log.getLog_info());
                    ps.setString(3,date);
                    ps.setString(4,log.getUser_name());
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
