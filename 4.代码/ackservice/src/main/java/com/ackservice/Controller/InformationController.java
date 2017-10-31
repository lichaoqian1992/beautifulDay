package com.ackservice.Controller;

import com.ackservice.Dto.Information;
import com.ackservice.Service.InformationService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by pudding on 2017-9-14.(YYR)
 */
@RequestMapping(value="/info")
@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;

    @ApiOperation(value="查询栏位下的信息数量",notes = "通过栏位id查询此栏位下的数量")
    @RequestMapping(value ="/findInformationByTreeCount",method = RequestMethod.GET)
    @ResponseBody
    public int findInformationByTreeCount(@RequestParam("treeId")int treeId){

        int count=informationService.findInformationByTreeCount(treeId);
        return count;
    }

    @ApiOperation(value="通过信息id查询一条信息详情",notes = "通过信息id查询一条信息详情")
    @RequestMapping(value ="/findInformationById",method = RequestMethod.GET)
    @ResponseBody
    public Information findInformationById(@RequestParam("id")int id){

        return informationService.findInformationById(id);
    }

    @ApiOperation(value="添加信息",notes = "添加知识库信息")
    @RequestMapping(value ="/insertInformation",method = RequestMethod.GET)
    @ResponseBody
    public boolean insertInformation(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state){

        return informationService.insertInformation(title,content,category,tree_id,state);
    }


    @ApiOperation(value="修改信息状态",notes = "修改知识库信息状态(0:启用,1:禁用)")
    @RequestMapping(value ="/updateInformationState",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateInformationState(@RequestParam("state")int state,@RequestParam("id")int id){

        return informationService.updateInformationState(state,id);
    }

    @ApiOperation(value="修改信息",notes = "修改知识库信息")
    @RequestMapping(value ="/updateInformation",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateInformation(@RequestParam("id")int id, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state){

        return informationService.updateInformation(id,title,content,category,tree_id,state);
    }

    @ApiOperation(value="删除信息",notes = "批量删除信息")
    @RequestMapping(value ="/deleteInformation",method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteInformation(@RequestParam("id")int id[]){

        return informationService.delectInformation(id);
    }

    @ApiOperation(value="通过状态查询信息",notes = "通过状态查询信息")
    @RequestMapping(value ="/findInformationByState",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findInformationByState(@RequestParam("state")int state,@RequestParam("pjt_code")String pjt_code){

        return informationService.findInformationByState(state,pjt_code);
    }

    @ApiOperation(value="通过栏位查询信息",notes = "通过栏位Id查询信息")
    @RequestMapping(value ="/findInformationByTrees",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findInformationByTrees(@RequestParam("treeId")int treeId, @RequestParam("pjt_code")String pjt_code){

        return informationService.findInformationByTrees(treeId,pjt_code);
    }

    @ApiOperation(value="通过标题查询信息",notes = "通过标题查询信息")
    @RequestMapping(value ="/findInformationBytitle",method = RequestMethod.GET)
    @ResponseBody
    public  JSONArray findInformationBytitle(@RequestParam("title")String title,@RequestParam("pjt_code")String pjt_code){

        return informationService.findInformationBytitle(title,pjt_code);
    }

    @ApiOperation(value="通过内容查询信息",notes = "通过内容查询信息")
    @RequestMapping(value ="/findInformationByContent",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray findInformationByContent(@RequestParam("content")String content,@RequestParam("pjt_code")String pjt_code){

        return informationService.findInformationBycontent(content,pjt_code);
    }


    @ApiOperation(value="多条件分页查询信息",notes = "多条件分页查询信息")
    @RequestMapping(value ="/findInfo",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject findInfo(@Valid Information information,@RequestParam("pjt_code")String pjt_code,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){

        JSONObject jsonObject=informationService.findInfo(information,pjt_code,pageNum,pageSize);
        return jsonObject;
    }


}
