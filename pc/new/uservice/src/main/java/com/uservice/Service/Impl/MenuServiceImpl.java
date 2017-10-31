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
}
