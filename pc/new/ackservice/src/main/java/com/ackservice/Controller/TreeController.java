package com.ackservice.Controller;

import com.ackservice.Service.TreeService;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pudding on 2017-9-13.(YYR)
 */
@RequestMapping(value="/tree")
@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    @ApiOperation(value="添加栏位")
    @RequestMapping(value ="/add",method = RequestMethod.GET)
    @ResponseBody
    public boolean add(@RequestParam("id")int pid, @RequestParam("menuId")int menuId, @RequestParam("title")String title, @RequestParam("pjtCode")String pjtCode){

        return treeService.insertTree(pid,menuId,title,pjtCode);
    }

    @ApiOperation(value="修改栏位")
    @RequestMapping(value ="/updateTitle",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateTitle(@RequestParam("title")String title,@RequestParam("id")int id){

        return  treeService.updateTitle(title,id);
    }


    @ApiOperation(value = "删除栏位",notes = "通过id删除栏位")
    @RequestMapping(value ="/del",method = RequestMethod.GET)
    @ResponseBody
    public boolean del(@RequestParam("id") int id){

        return treeService.delectTree(id);
    }


    @ApiOperation(value = "通过级别查询全部栏位")
    @RequestMapping(value ="/findTreeByMenuId",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findTreeByMenuId(@RequestParam("menuId") int menuId,@RequestParam("pjt_code")String pjt_code){

        return treeService.findTreeByMenuId(menuId,pjt_code);
    }


    @ApiOperation(value = "查询此栏位下的全部子栏位")
    @RequestMapping(value ="/findTreeSun",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findTreeSun(@RequestParam("id") int id,@RequestParam("pjt_code")String pjt_code){
        return treeService.findTreeSun(id,pjt_code);
    }

    @ApiOperation(value = "查询此栏位下是否有子栏位",notes = "查询此栏位下是否有子栏位")
    @RequestMapping(value ="/isTree",method = RequestMethod.GET)
    @ResponseBody
    public  boolean isTree(@RequestParam("id") int id,@RequestParam("pjt_code")String pjt_code){

        return treeService.isTree(id,pjt_code);
    }

    @ApiOperation(value = "查询全部栏位",notes = "通过项目code查询全部栏目")
    @RequestMapping(value ="/findAllTree",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findAllTree(@RequestParam("pjt_code")String pjt_code){

        return treeService.findAllTree(pjt_code);
    }




}
