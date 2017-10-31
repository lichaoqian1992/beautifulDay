package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.service.AckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pudding on 2017-9-18.(知识库接口)
 */
@RestController
@RequestMapping("/ack")
public class AckController{

    @Autowired
    private AckService ackService;

    /**
     * 添加节点
     * @param pid
     * @param menuId
     * @param title
     * @return
     */
    @RequestMapping("/addTree")
    public String addTree(@RequestParam("pid")int pid,@RequestParam("menuId") int menuId,@RequestParam("title")String title){

        BaseResult result=ackService.addTree(pid,menuId,title);
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 修改节点
     * @param id
     * @param title
     * @return
     */
    @RequestMapping("/updateTree")
    public String updateTree(@RequestParam("id")int id,@RequestParam("title")String title){

        BaseResult result=ackService.updateTree(title,id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 删除节点
     * @param id
     * @return
     */
    @RequestMapping("/deleteTree")
    public String deleteTree(@RequestParam("id")int id){

        BaseResult result=ackService.deleteTree(id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 通过级别查询节点
     * @param menuId 级别Id
     * @return
     */
    @RequestMapping(value ="/findTreeByMenuId",method = RequestMethod.GET)
    public String findTreeByMenuId(@RequestParam("menuId") int menuId){

        BaseResult result=ackService.findTreeByMenuId(menuId);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 查询此节点下全部子节点
     * @param id
     * @return
     */
    @RequestMapping(value ="/findTreeSun",method = RequestMethod.GET)
    public String findTreeSun(@RequestParam("id") int id){

        BaseResult result=ackService.findTreeSun(id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 添加信息
     * @param title
     * @param content
     * @param category
     * @param tree_id
     * @param state
     * @return
     */
    @RequestMapping(value ="/addInfo",method = RequestMethod.GET)
    public String addInfo(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state){

        BaseResult result=ackService.insertInformation(title,content,category,tree_id,state);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     *获取信息详情
     */
    @RequestMapping(value ="/findInformationById",method = RequestMethod.GET)
    public String findInformationById(@RequestParam("id")int id){

        BaseResult result=ackService.findInformationById(id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 修改信息状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping(value ="/updateState",method = RequestMethod.GET)
    public String updateState(@RequestParam("id")int id,@RequestParam("state")int state){

        BaseResult result=ackService.updateInformationState(state,id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 修改信息状态
     * @return
     */
    @RequestMapping(value ="/prmentTrees",method = RequestMethod.GET)
    public String prmentTrees(@RequestParam("tree_id")int tree_id){

        BaseResult result=ackService.prmentTrees(tree_id);
        return JSONObject.toJSONString(result).toString();
    }




    /**
     * 修改信息
     * @param id
     * @param title
     * @param content
     * @param category
     * @param tree_id
     * @param state
     * @return
     */
    @RequestMapping(value ="/updateInfo",method = RequestMethod.GET)
    public String updateInfo(@RequestParam("id")int id, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state){

        BaseResult result=ackService.updateInformation(id,title,content,category,tree_id,state);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 批量删除信息
     * @param id
     * @return
     */
    @RequestMapping(value ="/deleteInfo",method = RequestMethod.GET)
    public String deleteInfo(@RequestParam("id")int id[]){

        BaseResult result=ackService.deleteInformation(id);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 分页查询信息
     * @param state
     * @param tree_id
     * @param title
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value ="/findInfo",method = RequestMethod.GET)
    public String findInfo(Integer state,Integer tree_id,String title,String content,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){

        BaseResult result=ackService.findInfo(state,tree_id,title,content,pageNum,pageSize);
        return JSONObject.toJSONString(result).toString();
    }

    /**
     * 通过栏位查询栏位下全部信息
     * @return
     */
    @RequestMapping(value ="/findInformationByTrees",method = RequestMethod.GET)
    public String findInformationByTrees(@RequestParam("tree_id")Integer tree_id){

        BaseResult result=ackService.findInformationByTrees(tree_id);
        return JSONObject.toJSONString(result).toString();
    }


    /**
     * 通过内容和标题查询信息
     * @param content
     * @return
     */
    @RequestMapping(value ="/findInformationByContent",method = RequestMethod.GET)
    public String findInformationByContent(@RequestParam("content")String  content){

        BaseResult result=ackService.findInformationByContent(content);
        return JSONObject.toJSONString(result).toString();
    }




}
