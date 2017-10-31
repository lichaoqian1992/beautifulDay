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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pjt_code", value = "项目code", required = true, dataType = "String")
    })
    @RequestMapping("/findMenus")
    @ResponseBody
    public JSONArray findMenus(@RequestParam("pjt_code")String pjt_code){

        List<Menu> menuList=menuService.findMenus(pjt_code);
        String j=JSON.toJSONString(menuList);
        JSONArray jsonArray=JSONObject.parseArray(j);
        return jsonArray;
    }

    /**
     * 角色绑定权限
     * @param roleId
     * @param menuId
     * @return
     */
    @ApiOperation(value="角色绑定权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "menuId", value = "权限Id数组", required = true, dataType = "int")
    })
    @RequestMapping("/insertRoleMenu")
    @ResponseBody
    public boolean insertRoleMenu(@RequestParam("roleId")int roleId,@RequestParam("menuId")int[] menuId){

       boolean isok= menuService.insertRoleMenu(roleId,menuId);
       return isok;
    }


}
