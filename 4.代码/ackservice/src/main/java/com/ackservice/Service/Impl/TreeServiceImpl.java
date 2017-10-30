package com.ackservice.Service.Impl;

import com.ackservice.Dto.Information;
import com.ackservice.Dto.Tree;
import com.ackservice.Mapper.InformationMapper;
import com.ackservice.Mapper.TreeMapper;
import com.ackservice.Service.TreeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pudding on 2017-9-13.(YYR)
 */
@Service
public class TreeServiceImpl implements TreeService{

    @Autowired
    private TreeMapper treeMapper;

    /**
     * 添加栏位
     * @param id
     * @param menuId
     * @param title
     * @param pjt_code
     * @return
     */
    @Override
    public boolean insertTree(int id, int menuId, String title, String pjt_code) {

        try{
            if (id==0){
                Tree tree=treeMapper.findGen(pjt_code);
                id=tree.getId();
            }
            treeMapper.insertTree(id,menuId,title,pjt_code);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 删除栏位
     * @param id
     * @return
     */
    @Override
    public boolean delectTree(int id) {
        try {
            treeMapper.delectTree(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 通过级别查询栏位
     * @param mentId
     * @return
     */
    @Override
    public JSONArray findTreeByMenuId(int mentId, String pjt_id) {

        List<Tree> treeList =treeMapper.findTreeByMenuId(mentId,pjt_id);//查询某个级别的全部栏目
        String j= JSON.toJSONString(treeList);
        JSONArray jsonArray=JSON.parseArray(j);
        return jsonArray;
    }

    /**
     * 查询此项目下全部子栏目
     * @param pid
     * @param pjt_code
     * @return
     */
    public JSONArray findTreeSun(int pid,String pjt_code){

        Tree tree=treeMapper.finTreeByid(pid,pjt_code);//找到此栏目
        List<Tree> treeList=treeMapper.findTreeSunByleft(tree.getLeft(),tree.getRigt(),pjt_code,(tree.getMenu_id()+1));//查询此栏目下全部子栏目

        String j=JSON.toJSONString(treeList);
        JSONArray jsonArray=JSON.parseArray(j);
        return jsonArray;
    }

    /**
     * 判断此栏位下是否有子栏位
     * @param id
     * @return
     */
    @Override
    public boolean isTree(int id,String pjt_code) {

        Tree tree=treeMapper.finTreeByid(id,pjt_code);
        if (tree==null){
            return true;
        }
        int is=Integer.parseInt(tree.getRigt())-Integer.parseInt(tree.getLeft());
        if (is==1){
            return false;
        }
        return true;
    }

    /**
     * 查询全部栏位
     * @param pjt_code
     * @return
     */
    @Override
    public JSONArray findAllTree(String pjt_code) {

        List<Tree> treeList=treeMapper.findAllTree(pjt_code);
        String j=JSON.toJSONString(treeList);
        JSONArray jsonArray=JSON.parseArray(j);
        return jsonArray;
    }

    /**
     * 修改栏位名称
     * @param title
     * @param id
     * @return
     */
    @Override
    public boolean updateTitle(String title, int id) {
        boolean isok;
        try {
            isok=treeMapper.updateTitle(title,id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }


}
