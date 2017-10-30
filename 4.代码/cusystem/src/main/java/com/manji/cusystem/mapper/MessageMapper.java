package com.manji.cusystem.mapper;

import com.manji.cusystem.dao.Message.Message;
import com.manji.cusystem.provider.MessageProvider;
import com.manji.cusystem.utils.SimpleInsertLangDriver;
import com.manji.cusystem.utils.SimpleUpdateLangDriver;
import com.manji.cusystem.vo.message.MessageVo;
import com.manji.cusystem.vo.message.QueryMessageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
@Mapper
public interface MessageMapper {

    /**
     * 新增短信
     * @param vo
     * @return
     */
    @Insert("insert into cus_message (#{vo})")
    @Lang(SimpleInsertLangDriver.class)
    boolean addMessage(MessageVo vo);

    /**
     * 查询短信记录
     * @param vo
     * @return
     */
    @SelectProvider(type=MessageProvider.class,method = "selectMessage")
    List<Message> selectMessage(QueryMessageVo vo);

    @SelectProvider(type=MessageProvider.class,method = "selectMessageCount")
    int selectMessageCount(QueryMessageVo vo);
    /**
     * 查询短信详情
     * @param cusId
     * @return
     */
    @Select("select * from cus_message where cus_id=#{cusId}")
    Message selectMessageDetail(String cusId);

    /**
     * 删除短信
     * @param cusId
     * @return
     */
    @Delete("delete from cus_message where cus_id=#{cusId}")
    int delMessage(String cusId);

    /**
     * 修改短信信息
     * @param vo
     * @return
     */
    @Update("update cus_message (#{vo}) where cus_id=#{cusId}")
    @Lang(SimpleUpdateLangDriver.class)
    int updateMessage(MessageVo vo);
}
