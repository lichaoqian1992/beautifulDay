package com.uservice.Mapper;

import com.uservice.Dto.Menu;
import com.uservice.Dto.MenuJson;
import org.apache.ibatis.annotations.*;
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

    @Select("select m.path,m.title,m.hierarchy,m.sort,ot.title as type_title from j_role_menu rm LEFT JOIN jmenu m on rm.menu_id=m.id LEFT JOIN joperationtype ot on ot.id=m.type_id where rm.role_id in(SELECT ur.role_id from j_user_role ur where ur.user_id=#{0}) and m.type_id=#{1} and pjt_code=#{2} order by sort ")
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

    /**
     * 角色解绑权限
     * @param roleId
     * @param MenuId
     * @return
     */
    @Delete("DELETE FROM j_role_menu where role_id=#{0} and menu_id=#{1}")
    boolean deleteRoleMenu(int roleId,int MenuId);

    /**
     * 查询此角色是否绑定此权限
     * @param roleId
     * @param MenuId
     * @return
     */
    @Select("SELECT count(*) FROM j_role_menu where role_id=#{0} and menu_id=#{1}")
    int getRoleMenuCount(int roleId,int MenuId);

    @Select("select jmenu.* from juser LEFT JOIN j_user_role on juser.id=j_user_role.user_id LEFT JOIN jrole on jrole.id=j_user_role.role_id LEFT JOIN j_role_menu on j_role_menu.role_id=jrole.id LEFT JOIN jmenu on jmenu.id=j_role_menu.menu_id  where juser.id=#{0} and jmenu.pjt_code=#{1}")
    List<Menu> isMenuByUserIdAndPath(int userId,String pjt_code);

    /**
     * 通过角色查询权限
     * @return
     */
    @Select("select jmenu.* from jrole left join j_role_menu on j_role_menu.role_id=jrole.id left join jmenu on jmenu.id=j_role_menu.menu_id where jrole.id=#{roleId} ORDER BY sort")
    List<Menu> findMenuByRoleId(Integer roleId);

}
