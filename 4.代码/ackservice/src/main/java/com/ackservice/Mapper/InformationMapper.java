package com.ackservice.Mapper;

import com.ackservice.Dto.Information;
import com.ackservice.MybatisUtil.SimpleInsertLangDriver;
import com.ackservice.MybatisUtil.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import com.ackservice.Mapper.Provider.InformationProvider;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-9-13.（YYR）
 */
@Mapper
@Component
public interface InformationMapper {

    /**
     * 通过Id查询信息
     */
    @Select("select * from ainformation where id=#{id}")
    Information findInformationById(Integer id);

    /**
     * 通过栏位查询下面全部信息
     * @param treeId
     * @return
     */
    @Select("select * from ainformation where tree_id=#{treeId}")
    List<Information> findInformationByTree(int treeId);

    /**
     * 查询一个栏位下信息条数
     * @param treeId
     * @return
     */
    @Select("select count(*) from ainformation where tree_id=#{treeId}")
    int findInformationByTreeCount(int treeId);

    /**
     * 添加信息
     * @param information
     * @return
     */
    @Insert("INSERT INTO ainformation (#{information})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertInformation(Information information);

    /**
     * 修改信息状态
     * @param state
     * @param id
     * @return
     */
    @Update("UPDATE ainformation SET state=#{0} where id=#{1}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateInformationState(int state,int id);

    /**
     * 修改信息
     * @param information
     * @return
     */
    @Update("UPDATE ainformation(#{information}) where id=#{id}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateInformation(Information information);

    /**
     * 删除信息
     * @param id
     * @return
     */
    @DeleteProvider(type=InformationProvider.class,method="delectInformation")
    boolean delectInformation(String id);



    /**
     * 通过状态查询信息
     * @param state
     * @param pjt_code
     * @return
     */
    @Select("SELECT ainformation.* from ainformation,atree where ainformation.tree_id=atree.id and ainformation.state=#{0} and atree.pjt_code=#{1}")
    List<Information> findInformationByState(int state,String pjt_code);

    /**
     * 通过栏目查询信息
     * @param treeList
     * @return
     */
    @Select("SELECT * from ainformation where tree_id in(#{treeList})")
    List<Information> findInformationByTreeIdList(String treeList);

    /**
     * 通过标题模糊查询信息
     * @param title
     * @param pjt_code
     * @return
     */
    @Select("SELECT * from ainformation where title like '%'+#{0}+'%' and pjt_code=#{1}")
    List<Information> findInformationBytitle(String title,String pjt_code);

    /**
     * 通过内容模糊查询信息
     * @param content
     * @param pjt_code
     * @return
     */
    @Select("SELECT * from ainformation where content like '%'+#{0}+'%' and pjt_code=#{1}")
    List<Information> findInformationBycontent(String content,String pjt_code);

    /**
     * 查询全部信息多条件
     * @param information
     * @return
     */
    @SelectProvider(type=InformationProvider.class,method="findInfo")
    List<Information> findInfo(Information information,String pjtCode,int pageNum,int pageSize,String idList);

    /**
     * 查询全部信息多条件条数
     * @param information
     * @return
     */
    @SelectProvider(type=InformationProvider.class,method="findInfoCount")
    int findInfoCount(Information information,String pjtCode,int pageNum,int pageSize,String idList);

}
