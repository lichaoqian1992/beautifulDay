package com.manji.cusystem.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by pudding on 2017-9-19.
 */
@FeignClient(name="ack" ,url = "${ackservice.service.url}")//name:表示调用的是那个服务，后面的url表示服务地址
public interface AckServiceFeignService {
    /**
     * 添加节点
     * @param pid 父节点Id
     * @param menuId 节点等级
     * @param title
     * @param pjtCode 项目标识
     * @return
     */
    @RequestMapping(value = "/tree/add")
    boolean addTree(@RequestParam("id")int pid, @RequestParam("menuId")int menuId, @RequestParam("title")String title, @RequestParam("pjtCode")String pjtCode);

    @RequestMapping(value = "/tree/findAllTree")
    JSONArray findAllTree(@RequestParam("pjt_code")String pjt_code);

    /**
     * 修改节点名称
     * @param title
     * @param id
     * @return
     */
    @RequestMapping(value = "/tree/updateTitle")
    boolean updateTree(@RequestParam("title")String title,@RequestParam("id")int id);

    /**
     * 删除节点
     * @param id
     * @return
     */
    @RequestMapping(value = "/tree/del")
    boolean deleteTree(@RequestParam("id")int id);

    /**
     * 通过级别查询节点
     * @param menuId
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/findTreeByMenuId",method = RequestMethod.GET)
    JSONArray findTreeByMenuId(@RequestParam("menuId") int menuId, @RequestParam("pjt_code")String pjt_code);

    /**
     * 查询此节点下全部子节点
     * @param id
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/findTreeSun",method = RequestMethod.GET)
    JSONArray findTreeSun(@RequestParam("id") int id,@RequestParam("pjt_code")String pjt_code);

    /**
     * 判定此节点下是否有子节点
     * @param id
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/isTree",method = RequestMethod.GET)
    boolean isTree(@RequestParam("id") int id,@RequestParam("pjt_code")String pjt_code);

    /**
     * 获取节点信息数量
     * @param treeId
     * @return
     */
    @RequestMapping(value ="/info/findInformationByTreeCount",method = RequestMethod.GET)
    int findInformationByTreeCount(@RequestParam("treeId")int treeId);

    /**
     * 添加信息
     * @param title
     * @param content
     * @param category
     * @param tree_id
     * @param state
     * @return
     */
    @RequestMapping(value ="/info/insertInformation",method = RequestMethod.GET)
    boolean insertInformation(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state);

    /**
     * 修改信息状态
     * @param state
     * @param id
     * @return
     */
    @RequestMapping(value ="/info/updateInformationState",method = RequestMethod.GET)
    boolean updateInformationState(@RequestParam("state")int state,@RequestParam("id")int id);

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
    @RequestMapping(value ="/info/updateInformation",method = RequestMethod.GET)
    boolean updateInformation(@RequestParam("id")int id, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("category")String category, @RequestParam("tree_id")int tree_id, @RequestParam("state")int state);

    /**
     * 批量删除信息
     * @param id
     * @return
     */
    @RequestMapping(value ="/info/deleteInformation",method = RequestMethod.GET)
    boolean deleteInformation(@RequestParam("id")int id[]);

    /**
     * 查询信息
     * @param state
     * @param tree_id
     * @param title
     * @param content
     * @param pjt_code
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value ="/info/findInfo",method = RequestMethod.GET)
    JSONObject findInfo(@RequestParam("state")Integer state, @RequestParam("tree_id")Integer tree_id, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("pjt_code")String pjt_code, @RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize);

}

