package com.uservice.Mapper;

import com.uservice.Dto.Menu;
import com.uservice.Dto.MenuJson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-8-30.(YYR)
 */
@Mapper
@Component
public interface MenuMapper {

    /**
     * 查询此用户在此项目下的全部页面权限
     * @param user_id
     * @param type_id
     * @param pjt_code
     * @return
     */
    @Select("select m.path,m.title,m.hierarchy,m.sort,ot.title as type_title from j_role_menu rm LEFT JOIN jmenu m on rm.menu_id=m.id LEFT JOIN joperationtype ot on ot.id=m.type_id where rm.role_id in(SELECT ur.id from j_user_role ur where ur.user_id=#{0}) and m.type_id=#{1} and pjt_code=#{2}")
    List<MenuJson> findMenuByUserId(int user_id,int type_id,String pjt_code);

    /**
     * 查询此用户在此项目下的全部权限
     * @param pjt_code
     * @return
     */
    @Select("select * from jmenu where pjt_code=(#{pjt_code})")
    List<Menu> findMenus(String pjt_code);

    /**
     * 角色绑定权限
     * @param roleId
     * @param MenuId
     * @return
     */
    @Insert("INSERT INTO j_role_menu VALUES(null,#{0},#{1})")
    boolean insertRoleMenu(int roleId,int MenuId);
}
