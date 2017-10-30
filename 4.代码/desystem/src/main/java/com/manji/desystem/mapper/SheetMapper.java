package com.manji.desystem.mapper;

import com.manji.desystem.dao.Reason;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetMapper {

    @Select("select * from cus_reason where cus_layer=1")
    List<Reason> findFirstReason();

    @Select("select * from cus_reason where cus_layer=2 and cus_code like #{code} ")
    List<Reason> findSecondReason(@Param("code") String code);

    @Select("select cus_code from cus_reason where cus_id=#{id} ")
    String findSecondCode(@Param("id") Integer id);
}
