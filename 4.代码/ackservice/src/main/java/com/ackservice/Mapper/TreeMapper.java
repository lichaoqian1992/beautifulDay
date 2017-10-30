package com.ackservice.Mapper;

import com.ackservice.Dto.Tree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-9-13.（YYR）
 */
@Mapper
@Component
public interface TreeMapper {

    @Select("select * from atree where menu_id=0 and pjt_code=#{pjt_code}")
    Tree findGen(String pjt_code);

    /**
     * 通过id查询栏位
     * @param id
     * @param pjt_code
     * @return
     */
    @Select("select * from atree where id=#{0} and pjt_code=#{1}")
    Tree finTreeByid(int id,String pjt_code);

    /**
     * 通过级别查询栏位
     * @return
     */
    @Select("select * from atree where menu_id=#{0} and pjt_code=#{1}")
    List<Tree> findTreeByMenuId(int menu_id,String pjt_code);


    /**
     * 通过项目code查询此项目下全部栏位
     * @param pjt_code
     * @return
     */
    @Select("select * from atree where `left`>1 and pjt_code=#{pjt_code} ORDER BY `left` ASC")
    List<Tree> findAllTree(String pjt_code);

    /**
     * 通过左查询出此栏位下全部子孙级栏位
     * @param left
     * @param rigt
     * @return
     */
    @Select("SELECT * FROM atree WHERE `left` BETWEEN #{0} AND #{1} AND pjt_code = #{2} ORDER BY `left` ASC ")
    List<Tree> findTreeSunsByleft(String left,String rigt,String pjt_code);

    /**
     * 通过左查询出此栏位下全部子级栏位
     * @param left
     * @param rigt
     * @return
     */
    @Select("SELECT * FROM atree WHERE `left` BETWEEN #{0} AND #{1} AND pjt_code= #{2} AND menu_id=#{3} ORDER BY `left` ASC ")
    List<Tree> findTreeSunByleft(String left,String rigt,String pjt_code,int menuId);


    /**
     * 查询此栏位的全部父级栏位
     * @param left
     * @param rigt
     * @param pjt_code
     * @return
     */
    @Select("SELECT * FROM atree WHERE `left` < #{0} AND rigt > #{1} AND #{2}")
    List<Tree> prmentTree(String left,String rigt,String pjt_code);

    /**
     *修改栏位信息
     * @param title
     * @param id
     * @return
     */
    @Update("update atree set title=#{0} where id=#{1}")
    boolean updateTitle(String title,int id);

    /**
     * 插入栏位
     * @param id  父栏位的id
     * @param menuId 栏位级别
     * @param title
     * @param pjt_code
     */
    @Select("CALL addTree(#{0},#{1},#{2},#{3})")
    void insertTree(int id,int menuId,String title,String pjt_code);

    /**
     * 删除栏位
     * @param id
     */
    @Select("CALL delTree(#{0})")
    void delectTree(int id);


}
