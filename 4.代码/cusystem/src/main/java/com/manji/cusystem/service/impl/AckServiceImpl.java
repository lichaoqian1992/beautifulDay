package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.feign.AckServiceFeignService;
import com.manji.cusystem.service.AckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pudding on 2017-9-18.(YYR)
 */
@Service
public class AckServiceImpl implements AckService{

    @Autowired
    private AckServiceFeignService ackServiceFeignService;

    private final String pjtCode="0001";

    BaseResult baseResult=new BaseResult();

    /**
     * 添加节点
     * @param pid 父节点Id
     * @param menuId 节点级别
     * @param title
     * @return
     */
    @Override
    public BaseResult addTree(int pid, int menuId, String title) {

        boolean isTree;
        try{
            isTree=ackServiceFeignService.addTree(pid,menuId,title,pjtCode);//添加节点

            if (isTree){
                baseResult.setCode("200");
                baseResult.setContent("添加节点成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("添加节点失败");
            }
            baseResult.setResult(isTree);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加节点失败");
        }

        return baseResult;
    }

    /**
     * 修改节点
     * @param title
     * @param id
     * @return
     */
    @Override
    public BaseResult updateTree(String title, int id) {

        boolean isTree;
        try{
            isTree=ackServiceFeignService.updateTree(title,id);
            if (isTree){
                baseResult.setCode("200");
                baseResult.setContent("修改节点成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("修改节点失败");
            }
            baseResult.setResult(isTree);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("修改节点失败");
        }
        return baseResult;
    }

    /**
     * 删除节点
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteTree(int id) {
        boolean isTree;
        try{
            if (ackServiceFeignService.isTree(id,pjtCode)){//判定此节点下是否有子节点如果有删除失败
                baseResult.setCode("500");
                baseResult.setContent("删除节点失败,此节点下有其他子节点");
                baseResult.setResult("");
                return baseResult;
            }
            int count=ackServiceFeignService.findInformationByTreeCount(id);//查询此节点下的信息数量
            if (count>0){
                baseResult.setCode("500");
                baseResult.setContent("删除节点失败,此节点下有未删除的信息");
                baseResult.setResult("");
                return baseResult;
            }
            isTree=ackServiceFeignService.deleteTree(id);//删除节点
            if (isTree){
                baseResult.setCode("200");
                baseResult.setContent("删除节点成功");

            }else{
                baseResult.setCode("500");
                baseResult.setContent("删除节点失败");
            }
            baseResult.setResult(isTree);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("删除节点失败,服务器错误请联系管理员");
            baseResult.setResult(e.getClass().getName().toString());
        }
        return baseResult;
    }

    /**
     * 通过级别查询节点
     * @param menuId
     * @return
     */
    @Override
    public BaseResult findTreeByMenuId(int menuId) {
        try {
            JSONArray jsonArray=ackServiceFeignService.findTreeByMenuId(menuId,pjtCode);
            baseResult.setCode("200");
            baseResult.setContent("查询节点成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询节点失败");
        }

        return baseResult;
    }

    /**
     * 查询此节点下全部子节点
     * @param id
     * @return
     */
    @Override
    public BaseResult findTreeSun(int id) {
        try {
            JSONArray jsonArray = ackServiceFeignService.findTreeSun(id, pjtCode);
            baseResult.setCode("200");
            baseResult.setContent("查询节点成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询节点失败");
        }
        return baseResult;
    }

    /**
     * 添加信息
     * @param title 信息标题
     * @param content 信息内容(富文本）
     * @param category 节点标题
     * @param treeId 节点Id
     * @param state 状态 0:启用  1:禁用
     * @return
     */
    @Override
    public BaseResult insertInformation(String title, String content, String category, int treeId, int state) {
        boolean isInfo;

        try{
            isInfo=ackServiceFeignService.insertInformation(title,content,category,treeId,state);
            if (isInfo){
                baseResult.setCode("200");
                baseResult.setContent("添加信息成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("添加信息失败");
            }
            baseResult.setResult(isInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加信息失败");
        }
        return baseResult;
    }

    /**
     * 修改信息状态
     * @param state
     * @param id
     * @return
     */
    @Override
    public BaseResult updateInformationState(int state, int id) {
        boolean isInfo;
        try {
            isInfo=ackServiceFeignService.updateInformationState(state,id);
            if (isInfo){
                baseResult.setCode("200");
                baseResult.setContent("修改信息状态成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("修改信息状态失败");
            }
            baseResult.setResult(isInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("修改信息状态失败");
        }
        return baseResult;
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
    @Override
    public BaseResult updateInformation(int id, String title, String content, String category, int tree_id, int state) {
        boolean isInfo;
        try{
        isInfo=ackServiceFeignService.updateInformation(id,title,content,category,tree_id,state);
        if (isInfo){
            baseResult.setCode("200");
            baseResult.setContent("修改信息成功");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("修改信息失败");
        }
        baseResult.setResult(isInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("修改信息失败");
        }
        return baseResult;
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
    @Override
    public BaseResult findInfo(Integer state, Integer tree_id, String title, String content,int pageNum, int pageSize) {
        try{
            JSONObject jsonObject=ackServiceFeignService.findInfo(state,tree_id,title,content,pjtCode,pageNum,pageSize);
            baseResult.setCode("200");
            baseResult.setContent("查询信息成功");
            baseResult.setResult(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,查询信息失败");
        }
        return baseResult;
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteInformation(int[] id) {
        boolean isInfo;
        try{
            isInfo=ackServiceFeignService.deleteInformation(id);
            if (isInfo){
                baseResult.setCode("200");
                baseResult.setContent("删除成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("删除失败");
            }
            baseResult.setResult(isInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,删除失败");
        }

        return baseResult;
    }

    /**
     * 获取详细信息
     * @param id
     * @return
     */
    @Override
    public BaseResult findInformationById(int id) {
        try{
            JSONObject jsonObject=ackServiceFeignService.findInformationById(id);
            baseResult.setResult(jsonObject);
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,查询失败");
        }

        return baseResult;
    }

    /**
     * 通过栏位查询此栏位下全部信息
     * @param treeId
     * @return
     */
    @Override
    public BaseResult findInformationByTrees(int treeId) {
        try{
            JSONArray jsonArray=ackServiceFeignService.findInformationByTrees(treeId,pjtCode);
            baseResult.setResult(jsonArray);
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,查询失败");
        }
        return baseResult;
    }

    /**
     * 查询此栏位的全部父节点(不包括根)
     * @param treeId
     * @return
     */
    @Override
    public BaseResult prmentTrees(int treeId) {
        try{
            JSONArray jsonArray=ackServiceFeignService.prmentTrees(treeId,pjtCode);
            baseResult.setResult(jsonArray);
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,查询失败");
        }
        return baseResult;
    }

    /**
     * 通过内容和标题查询信息
     * @param content
     * @return
     */
    @Override
    public BaseResult findInformationByContent(String content) {
        try{
            JSONArray jsonArray=ackServiceFeignService.findInformationByContent(content,pjtCode);
            baseResult.setResult(jsonArray);
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("服务器错误,查询失败");
        }
        return baseResult;
    }


}
