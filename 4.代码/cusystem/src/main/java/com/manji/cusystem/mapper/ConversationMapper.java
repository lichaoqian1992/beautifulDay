package com.manji.cusystem.mapper;

import com.manji.cusystem.dao.Conversation;
import com.manji.cusystem.dao.Reason;
import com.manji.cusystem.dao.conversation.ConversationCount;
import com.manji.cusystem.provider.ConversationProvider;
import com.manji.cusystem.utils.SimpleInsertLangDriver;
import com.manji.cusystem.utils.SimpleSelectLangDriver;
import com.manji.cusystem.utils.SimpleUpdateLangDriver;

import com.manji.cusystem.vo.conversation.AddConversationVo;
import com.manji.cusystem.vo.conversation.AddCustomerVo;
import com.manji.cusystem.vo.conversation.ConversationVo;
import com.manji.cusystem.vo.conversation.CustomerVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */
@Mapper
public interface ConversationMapper {

    /**
     * 保存会话信息表
     * @param vo
     * @return
     */
    @Insert("insert into cus_conversation (#{vo})")
    @Options(useGeneratedKeys=true, keyProperty="cus_id", keyColumn="cus_id")
    @Lang(SimpleInsertLangDriver.class)
    boolean addConversation(ConversationVo vo);

    /**
     * 保存客户资料表
     * @param vo
     * @return
     */
    @Insert("insert into cus_info (#{vo})")
    @Options(useGeneratedKeys=true, keyProperty="cus_id", keyColumn="cus_id")
    @Lang(SimpleInsertLangDriver.class)
    boolean addCusInfo(AddCustomerVo vo);

    /**
     * 查询账户信息
     * @param account
     * @return
     */
    @Select("select * from cus_info where cus_account=#{account} LIMIT 0,1")
    Conversation findInfoByAccount(@Param("account") String account);

    /**
     * 修改客户信息表
     * @param vo
     * @return
     */
    @Update("update cus_conversation (#{vo}) where cus_id=#{cus_id}")
    @Lang(SimpleUpdateLangDriver.class)
    int updateCusInfo(ConversationVo vo);

    @Update("update cus_info (#{vo}) where cus_id=#{cus_id}")
    @Lang(SimpleUpdateLangDriver.class)
    int updateCusInfos(AddCustomerVo vo);

    /**
     * 查询我的会话记录
     * @param vo
     * @return
     */
    @SelectProvider(type = ConversationProvider.class,method = "findConversation")
    List<Conversation> findConversation(AddConversationVo vo,String userId);

    @SelectProvider(type=ConversationProvider.class,method = "findConversationCount")
    int findConversationCount(AddConversationVo vo,String userId);


    @SelectProvider(type = ConversationProvider.class,method = "findOrderConversation")
    List<Conversation> findOrderConversation(AddConversationVo vo,String userId);

    @SelectProvider(type=ConversationProvider.class,method = "findOrderConversationCount")
    int findOrderConversationCount(AddConversationVo vo,String userId);

    @Select("select * from cus_conversation where cus_no=#{cus_no}")
    Conversation findByNo(@Param("cus_no") String cus_no);

    @Insert("insert into cus_con_sheet(cus_cid,cus_info_id,cus_type,cus_isdel) values(#{cus_cid},#{cus_info_id},1,1)")
    boolean addConAndSheet(@Param("cus_cid") int cus_cid,@Param("cus_info_id") int cus_info_id);

    @Insert("insert into cus_order_con(cus_order_id,cus_cid,cus_isdel) values(#{cus_order_id},#{cus_cid},1)")
    boolean addConAndOrder(@Param("cus_cid") int cus_cid,@Param("cus_order_id") int cus_order_id);

    @Select("select * from cus_reason where cus_layer=1")
    List<Reason> findFirstReason();

    @Select("select * from cus_reason where cus_layer=2 and cus_code like (#{code}) ")
    List<Reason> findSecondReason(@Param("code") String code);

    @Select("select or_number,or_id,or_sheet_status from or_sheets where or_con_id=#{cus_id}")
    List<Map<String,Object>> findSheet(int cus_id);

    /**
     * 获取今日会话记录
     * @param today
     * @return
     */
    @Select("select DATE_FORMAT(cus_ctime,'%H') as cus_ctime from cus_conversation where cus_ctime like #{today}")
    List<Conversation> getConversationToday(String today);

    @Select("select cus_cid from cus_order_con where cus_order_id=#{type}")
    List<String> findOrderByOrderId(String type);

    @Select("select a.*,b.*,b.cus_id as cus_info_id,c.cus_type as ctype,c.cus_cid,d.cus_code as pcode from cus_conversation a,cus_info b,cus_con_sheet c,cus_reason d where a.cus_id=c.cus_cid and c.cus_info_id=b.cus_id and d.cus_id=(select SUBSTRING_INDEX(cus_code,'_',1) from cus_reason where cus_id=(select cus_reasonid from cus_conversation where cus_id=#{cusId})) and a.cus_id=#{cusId}")
    Conversation findConversationById(String cusId);


    @SelectProvider(type = ConversationProvider.class,method = "findConversationStatistics")
    List<ConversationCount> findConversationStatistics(AddConversationVo vo);

    @SelectProvider(type = ConversationProvider.class,method = "findConversationStatisticsCount")
    int findConversationStatisticsCount(AddConversationVo vo);

    //查询来电一级原因的code
    @Select("select cus_code from cus_reason where cus_id=#{cus_from} and cus_status=1")
    String findFirstReasonById(String cus_from);

    //查询用户信息
    @Select("select * from cus_info where cus_tel=#{0} and cus_type=#{1}")
    List<Conversation> findCusInfo(String mobile, String type);

    //根据会话id查询客户信息
    //select * from cus_info a,cus_conversation b where b.cus_id=(select cus_cid from cus_con_sheet where cus_cid=#{0} and cus_type=1) and a.cus_id=(select cus_info_id from cus_con_sheet where cus_cid=#{0} and cus_type=1
    @Select("select * from cus_info a,cus_conversation b where b.cus_id=(select cus_cid from cus_con_sheet where cus_cid=#{0} and cus_type=1) and a.cus_id=(select cus_info_id from cus_con_sheet where cus_cid=#{0} and cus_type=1)")
    Conversation findCustomerByCid(String cus_cid);
}
