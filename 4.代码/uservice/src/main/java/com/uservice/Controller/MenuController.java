package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Menu;
import com.uservice.Service.IMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pudding on 2017-9-8.(YYR)
 */
@RequestMapping(value="/menu")
@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 通过项目code查询此项目下全部权限
     * @param pjt_code
     * @return
     */
    @ApiOperation(value="通过项目code查询此项目下全部权限")
    @RequestMapping(value = "/findMenus",method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> findMenus(@RequestParam("pjt_code")String pjt_code){

        List<Menu> menuList=menuService.findMenus(pjt_code);

        return menuList;
    }


    @ApiOperation(value="查询此角色下全部权限")
    @RequestMapping(value = "/findMenuByRoleId",method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> findMenuByRoleId(@RequestParam("roleId")int roleId){
        return menuService.findMenuByRoleId(roleId);
    }

    /**
     * 角色绑定权限
     * @param roleId
     * @param menuId
     * @return
     */
    @ApiOperation(value="角色绑定权限")
    @RequestMapping(value = "/insertRoleMenu",method = RequestMethod.GET)
    @ResponseBody
    public boolean insertRoleMenu(@RequestParam("roleId")int roleId,@RequestParam("menuId")int[] menuId){

       boolean isok= menuService.insertRoleMenu(roleId,menuId);
       return isok;
    }

    @ApiOperation(value="角色解绑权限")
    @RequestMapping(value = "/deleteRoleMenu",method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteRoleMenu(@RequestParam("roleId")int roleId,@RequestParam("menuId")int[] menuId){

        boolean isok= menuService.deleteRoleMenu(roleId,menuId);
        return isok;
    }

    @ApiOperation(value="验证此角色是否绑定此权限")
    @RequestMapping(value = "/getRoleMenuCount",method = RequestMethod.GET)
    @ResponseBody
    public boolean getRoleMenuCount(@RequestParam("roleId")int roleId,@RequestParam("menuId")int menuId){

        boolean isok= menuService.getRoleMenuCount(roleId,menuId);
        return isok;
    }

    @ApiOperation(value="验证此用户是否绑定此权限")
    @RequestMapping(value = "/isMenuByUserIdAndPath",method = RequestMethod.GET)
    @ResponseBody
    public boolean isMenuByUserIdAndPath(@RequestParam("userId")int userId,@RequestParam("path")String path,@RequestParam("pjtCode")String pjtCode){

        boolean isok=menuService.isMenuByUserIdAndPath(userId,path,pjtCode);
        return isok;
    }


}
