package com.uservice.Service;

import com.uservice.Dto.Menu;

import java.util.List;

/**
 * Created by pudding on 2017-9-8.(YYR)
 */
public interface IMenuService {

    List<Menu> findMenus(String pjt_code);

    boolean insertRoleMenu(int roleId,int[] menuId);
}
