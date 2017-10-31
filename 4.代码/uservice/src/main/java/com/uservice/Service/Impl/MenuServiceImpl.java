package com.uservice.Service.Impl;

import com.uservice.Dto.Menu;
import com.uservice.Mapper.MenuMapper;
import com.uservice.Service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pudding on 2017-9-8.(YYR)
 */
@Service
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询此此项目全部权限
     * @param pjt_code
     * @return
     */
    @Override
    public List<Menu> findMenus(String pjt_code) {

        List<Menu> list=menuMapper.findMenus(pjt_code);
        return list;
    }

    /**
     * 角色绑定权限
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public boolean insertRoleMenu(int roleId, int[] menuId) {
        boolean isok=true;

        for (int i=0;i<menuId.length;i++){
            isok=menuMapper.insertRoleMenu(roleId,menuId[i]);
            if (!isok){//如果添加失败,直接返回错误
                break;
            }

        }

        return isok;
    }

    /**
     * 角色解绑权限
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public boolean deleteRoleMenu(int roleId, int[] menuId) {
        boolean isok=true;

        for (int i=0;i<menuId.length;i++){
            isok=menuMapper.deleteRoleMenu(roleId,menuId[i]);
            if (!isok){//如果删除失败,直接返回错误
                break;
            }
        }

        return isok;
    }

    /**
     * 验证此角色是否已经绑定此权限
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public boolean getRoleMenuCount(int roleId, int menuId) {

        int count=menuMapper.getRoleMenuCount(roleId,menuId);//查询条数
        if (count==0){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 判断是否有此权限
     * @param userId
     * @param path
     * @param pjt_code
     * @return
     */
    @Override
    public boolean isMenuByUserIdAndPath(int userId,String path, String pjt_code) {

        List<Menu> menuList=menuMapper.isMenuByUserIdAndPath(userId,pjt_code);//查出此账户全部权限
        boolean isok=false;
        for (int i=0;i<menuList.size();i++){
            if (menuList.get(i).getPath().equals(path)){
                isok=true;
                break;
            }
        }
        return isok;
    }

    /**
     * 通过角色Id查询权限
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> findMenuByRoleId(int roleId) {
        return menuMapper.findMenuByRoleId(roleId);
    }
}
