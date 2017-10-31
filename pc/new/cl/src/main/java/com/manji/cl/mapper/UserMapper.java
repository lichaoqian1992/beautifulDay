package com.manji.cl.mapper;

import com.manji.cl.dao.Ruser;
import com.manji.cl.mybatisUtils.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
@Mapper
public interface UserMapper {


    /**
     * 查询该业务员推荐的用户数量，当月或者当天
     * @param id        业务员id
     * @param stime    开始时间
     * @param etime     结束时间
     * @return
     */
    @Select("select * from cl_ruser where cl_user_id=#{id} and cl_ctime > #{stime} and cl_ctime < #{etime}")
    List<Ruser> findUser(@Param("id") int id,@Param("stime") String stime,@Param("etime") String etime);

    /**
     * 添加注册用户
     * @param ruser
     * @return
     */
    @Insert("insert into cl_ruser (#{ruser})")
    @Lang(SimpleInsertLangDriver.class)
    boolean registerUser(Ruser ruser);
}
