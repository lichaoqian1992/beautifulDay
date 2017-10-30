package com.manji.orService.mapper;


import com.manji.orService.dao.*;
import com.manji.orService.mapper.Driver.SimpleInsertLangDriver;
import com.manji.orService.mapper.Driver.SimpleSelectLangDriver;
import com.manji.orService.mapper.Driver.SimpleUpdateLangDriver;
import com.manji.orService.vo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

@Repository
public interface SheetMapper {

    /**
     * 新增工单
     * @param sheetVo
     * @return
     */
    @Insert("INSERT INTO or_sheets (#{sheetVo})")
    @Options(useGeneratedKeys=true, keyProperty="orId", keyColumn="or_id")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertSheet(SheetVo sheetVo);

    @Insert("INSERT INTO cus_con_sheet (#{conSheetVo})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertConSheet(ConSheetVo conSheetVo);

    @Insert("INSERT INTO cus_info (#{infoVo})")
    @Options(useGeneratedKeys=true, keyProperty="cusId", keyColumn="cus_id")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertInfo(InfoVo infoVo);

    /**
     * 修改工单
     * @param sheetVo
     * @return
     */
    @Update("UPDATE or_sheets (#{sheetVo}) WHERE or_id = #{orId}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateSheet(SheetVo sheetVo);

    @Update("UPDATE cus_info (#{infoVo}) WHERE cus_id = #{cusId}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateInfo(InfoVo infoVo);

    /**
     * 查询工单
     * @param selectSheetVo
     * @return
     */
    List<HashMap<String,Object>> selectSheet(SelectSheetVo selectSheetVo);

    /**
     * 查询所有工单
     * @param juris
     * @return
     */
    List<HashMap<String,Object>> selectSheetAll(JurisdictionVo juris);

    /**
     * 查询是否有此工单
     * @param conSheetVo
     * @return
     */
    @Select("SELECT * FROM cus_con_sheet (#{conSheetVo})")
    @Lang(SimpleSelectLangDriver.class)
    ConSheetVo slConSheetDao(ConSheetVo conSheetVo);

    /**
     * 储存日志信息
     * @param sheetLogVo
     * @return
     */
    @Insert("INSERT INTO or_sheet_log (#{sheetLogVo})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertSheetLog(SheetLogVo sheetLogVo);

    /**
     * 新增抄送人信息
     * @param ccSendVo
     * @return
     */
    @Insert("INSERT INTO or_cc_send (#{ccSend})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertCcSend(CcSendVo ccSendVo);

    /**
     * 查询工单详情
     * @param id
     * @return
     */
    HashMap<String,Object> selectSheetDetails(int id);

    /**
     * 查询抄送的工单
     * @param selectSheetVo
     * @return
     */
    List<HashMap<String,Object>> selectSend(SelectSheetVo selectSheetVo);

    /**
     * 修改抄送信息状态(查看)
     * @param sendId
     * @return
     */
    @Update("UPDATE or_cc_send set or_is_see=0 WHERE or_id = #{sendId}")
    boolean upSendStatus1(int sendId);

    /**
     * 查询工单详情
     * @param id
     * @return
     */
    @Select("select * from or_sheets where or_id=#{id}")
    SheetDao selectSheetId(int id);

    /**
     * 删除工单（回收站）
     * @param conSheetInfo
     * @return
     */
    @Update("UPDATE cus_con_sheet (#{conSheetInfo}) WHERE cus_id = #{cusId}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean delSheetUp(ConSheetVo conSheetInfo);

    /**
     * 删除工单（实际删除）
     * @param
     * @return
     */
    @Update("delete from or_sheets where or_id=#{id}")
    boolean delSheet(int id);

    @Update("delete from cus_info where cus_id=#{id}")
    boolean delInfo(int id);

    @Update("delete from cus_con_sheet where cus_id=#{id}")
    boolean delCon(int id);

    /**
     * 查询所有工单的条数
     * @return
     */
    List<SheetStatusVo> countSheet(int orFounderId);
    List<SheetStatusVo> countSheet2(int orFounderId);
    List<SheetStatusVo> countSheet3(int orFounderId);
    /**
     * 查询最后一次修改工单的记录
     * @param id
     * @return
     */
    SheetLogDao selectLog(int id);


    /**
     * 查询工单详情的工单记录
     */
    List<HashMap<String,Object>> detailsSheetRecord(@Param("id")int id,@Param("type")String type);


    /**
     * 查询工单日志记录
     * @param id
     * @param typeId
     * @return
     */
    @Select("select * from or_sheet_log where or_sheet_id=#{id} and or_operation_type_id=#{typeId} order by or_operation_time desc limit 1")
    SheetLogDao selectSheetlog(@Param("id") int id,@Param("typeId") int typeId);

    /**
     * 新增站内信息
     * @param informationsVo
     * @return
     */
    @Insert("INSERT INTO or_informations (#{informationsVo})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertInformations(InformationsVo informationsVo);

    /**
     * 查看站内信息
     * @param userId
     * @return
     */
    List<HashMap<String,Object>> selInformations(int isSee,int  userId);

    /**
     * 推送内容
     * @param id
     * @return
     */
    HashMap<String,Object> pushInfo(int id);

    /**
     * 清除站内信息
     * @param userId
     * @return
     */
    @Update("update or_informations set is_see=0,is_push=0 where or_handle_id=#{userId}")
    boolean clearInformations(int userId);

    /**
     * 关闭推送信息
     * @param id
     * @return
     */
    @Update("update or_informations set is_push=0 where or_id=#{id}")
    boolean clearInformationsOne(int id);

    /**
     * 清除站内信息根据sheetId
     * @param sheetId
     * @return
     */
    @Update("update or_informations set is_see=0,is_push=0 where or_sheet_id=#{sheetId}")
    boolean clearInformationsSheetId(int sheetId);

    /**
     * 查询自己的工单
     * @param userId
     * @return
     */
    List<HashMap<String,Object>> sheetSelf(int userId,String dept);

    /**
     * 恢复工单
     * @param id
     * @return
     */
    @Update("update cus_con_sheet set cus_isdel=1 where cus_type=0 and cus_cid=#{id}")
    boolean recovery(int id);

    /******************************************************************************************/

    /**
     * 查询工单是否可以编辑
     * @param sheetId
     * @param dept
     * @param userId
     * @return
     */
    @Select("SELECT count(1) FROM or_sheets WHERE (or_id=#{0} and or_founder_id=#{2} and or_founder_dept=#{1} and or_sheet_status=1) or (or_id=#{0} and or_founder_id=#{2} and or_founder_dept=#{1} and or_sheet_status=5 and or_founder_id=#{2})")
    int isEdit(int sheetId,String dept,int userId);


    /**
     * 查询工单是否可以删除
     * @param sheetId
     * @param dept
     * @param userId
     * @return
     */
    @Select("SELECT count(1) FROM or_sheets WHERE or_id=#{0} and (or_sheet_status=1 or or_sheet_status=6) and or_founder_id=#{2} and or_founder_dept=#{1}")
    int isDel(int sheetId,String dept,int userId);

    /**
     * 判断工单操作
     * @param sheetId
     * @param dept
     * @param userId
     * @return
     */
    @Select("select count(1) from or_sheets where or_id=#{0} and or_founder_id=#{2} and or_founder_dept=#{1} and or_sheet_status=#{3}")
    int isOperation(int sheetId,String dept,int userId,int status);

    /**
     * 是否可以指派
     * @param sheetId
     * @param userId
     * @return
     */
    @Select("select count(1) from or_sheets where or_id=#{0} and or_person_id=#{1} and or_sheet_status in (2,3,5)")
    int isAssign(int sheetId,int userId);

    /**
     * 查询工单是否可以关闭
     * @param sheetId
     * @param dept
     * @param userId
     * @return
     */
    @Select("SELECT count(1) FROM or_sheets WHERE or_id=#{0} and or_founder_id=#{2} and or_founder_dept=#{1} and or_person_id=#{2} and or_sheet_status=5")
    int isClose(int sheetId,String dept,int userId);

    /**
     * 是否重置工单
     * @param sheetId
     * @param userId
     * @return
     */
    @Select("SELECT count(1) FROM or_sheets WHERE or_id=#{0} and or_person_id=#{1} and or_sheet_status=3")
    int isReset(int sheetId,int userId);

    /**
     * 是否驳回
     * @param sheetId
     * @param userId
     * @return
     */
    @Select("SELECT count(1) FROM or_sheets WHERE or_id=#{0} and or_person_id=#{1} and or_sheet_status=2")
    int isReject(int sheetId,int userId);

}
