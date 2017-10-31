package com.ackservice.Service.Impl;

import com.ackservice.Dto.Information;
import com.ackservice.Dto.PageResult;
import com.ackservice.Dto.Tree;
import com.ackservice.Mapper.InformationMapper;
import com.ackservice.Mapper.TreeMapper;
import com.ackservice.Service.InformationService;
import com.ackservice.Util.PageUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-9-14.(YYR)
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private TreeMapper treeMapper;

    /**
     * 通过Id查询信息详情
     * @param id
     * @return
     */
    @Override
    public Information findInformationById(Integer id) {

        return informationMapper.findInformationById(id);
    }

    /**
     * 通过栏位id查询此栏位下的信息数量
     * @param treeId
     * @return
     */
    @Override
    public int findInformationByTreeCount(int treeId) {

        int count=informationMapper.findInformationByTreeCount(treeId);
        return count;
    }

    /**
     * 修改信息状态
     * @param state
     * @param id
     * @return
     */
    @Override
    public boolean updateInformationState(int state, int id) {
        boolean isok;
        try {
            isok=informationMapper.updateInformationState(state,id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public boolean delectInformation(int id[]) {
        boolean isok;
        try{
            String ids="";
            for (int i=0;i<id.length;i++){
                ids+=id[i]+",";
            }
            ids=ids.substring(0,(ids.length()-1));
            isok=informationMapper.delectInformation(ids);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 通过状态查询全部信息
     * @param state
     * @param pjt_code
     * @return
     */
    @Override
    public JSONArray findInformationByState(int state, String pjt_code) {

        List<Information> informationList=informationMapper.findInformationByState(state,pjt_code);
        String j= JSON.toJSONString(informationList);
        JSONArray jsonArray=JSONArray.parseArray(j);
        return jsonArray;
    }

    /**
     * 通过栏目查询此栏目下全部信息(包括子孙栏目)
     * @param treeId
     * @param pjt_code
     * @return
     */
    @Override
    public JSONArray findInformationByTrees(int treeId, String pjt_code) {

        Tree tree=treeMapper.finTreeByid(treeId,pjt_code);
        List<Tree> treeList=treeMapper.findTreeSunsByleft(tree.getLeft(),tree.getRigt(),pjt_code);//查询出全部子孙栏位
        String treeIds="";
        for (int i=0;i<treeList.size();i++){
            treeIds+=treeList.get(i).getId()+",";
        }
        treeIds=treeIds.substring(0,treeIds.length()-1);
        List<Information> informationList=informationMapper.findInformationByTreeIdList(treeIds);

        String j=JSON.toJSONString(informationList);
        JSONArray jsonArray=JSONArray.parseArray(j);
        return jsonArray;
    }

    /**
     * 通过标题查询信息
     * @param title
     * @param pjt_code
     * @return
     */
    @Override
    public JSONArray findInformationBytitle(String title, String pjt_code) {

        List<Information> informationList=informationMapper.findInformationBytitle(title,pjt_code);
        String j=JSON.toJSONString(informationList);
        JSONArray jsonArray= JSONObject.parseArray(j);
        return jsonArray;
    }

    /**
     * 通过内容模糊查询信息
     * @param content
     * @param pjt_code
     * @return
     */
    @Override
    public JSONArray findInformationBycontent(String content, String pjt_code) {

        List<Information> informationList=informationMapper.findInformationBycontent(content,pjt_code);
        String j=JSON.toJSONString(informationList);
        JSONArray jsonArray=JSON.parseArray(j);
        return jsonArray;
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
    public boolean updateInformation(int id, String title, String content, String category, int tree_id, int state) {
        boolean isok;
        Information information=new Information();
        information.setId(id);
        information.setTitle(title);
        information.setContent(content);
        information.setCategory(category);
        information.setTree_id(tree_id);
        information.setState(state);
        information.setAdd_time(new Date());
        try{
            isok=informationMapper.updateInformation(information);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return isok;
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
    @Override
    public boolean insertInformation(String title, String content, String category,int tree_id, int state) {
        boolean isok;
        Information information=new Information();
        information.setId(null);
        information.setTitle(title);
        information.setContent(content);
        information.setCategory(category);
        information.setTree_id(tree_id);
        information.setState(state);
        information.setAdd_time(new Date());
        try{
            isok=informationMapper.insertInformation(information);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }


    /**
     * 查询信息(多条件查询)
     * @param information
     * @param pjtCode
     * @return
     */
    @Override
    public JSONObject findInfo(Information information, String pjtCode,int pageNum,int pageSize) {

        if (pageNum==0){
            pageNum=1;
        }
        if (pageSize==0){
            pageSize=10;
        }
        String idList="";
        if (information.getTree_id()!=null){
            Tree tree=treeMapper.finTreeByid(information.getTree_id(),pjtCode);//查询当前节点
            if (tree!=null){
                List<Tree> treeList=treeMapper.findTreeSunsByleft(tree.getLeft(),tree.getRigt(),pjtCode);//查询当前节点下全部子孙级节点
                for (int i=0;i<treeList.size();i++){
                    idList+=treeList.get(i).getId()+",";//拼接Id
                }
                if (!idList.equals("")){
                    idList=idList.substring(0,idList.length()-1);//去掉最后一个逗号
                }
            }
        }


        List<Information> informationList=informationMapper.findInfo(information,pjtCode,pageNum,pageSize,idList);//查询出的数据

        int count=informationMapper.findInfoCount(information,pjtCode,pageNum,pageSize,idList);//查询出数据的总条数

        PageResult pageResult = new PageUtils().pageInit(pageNum,pageSize,count);

        JSONObject infoJson=new JSONObject();

        infoJson.put("list",informationList);

        infoJson.put("page",pageResult);

        return infoJson;
    }


}
