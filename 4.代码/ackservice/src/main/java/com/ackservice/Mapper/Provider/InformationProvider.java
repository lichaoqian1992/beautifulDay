package com.ackservice.Mapper.Provider;

import com.ackservice.Dto.Information;
import com.ackservice.Dto.Tree;
import com.ackservice.Mapper.TreeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pudding on 2017-9-18.(YYR)(查询客户系统知识库信息分页)
 */
public class InformationProvider {


    /**
     * 多条件查询信息
     * @param information
     * @param pjtCode
     * @return
     */
    public String findInfo(Information information,String pjtCode,int pageNum,int pageSize,String idList){

        StringBuffer sql=new StringBuffer("select ainformation.* from ainformation left join atree on atree.id=ainformation.tree_id where atree.pjt_code="+pjtCode+" ");

        if (information.getState()!=null){
            sql.append(" and state="+information.getState()+" ");
        }


        if (information.getTree_id()!=null){
            sql.append(" and tree_id in("+idList+") ");
        }

        if (information.getTitle()!=null&&!information.getTitle().equals("")){
            sql.append(" and ainformation.title like '%"+information.getTitle()+"%' ");
        }

        if (information.getContent()!=null&&!information.getContent().equals("")){
            sql.append(" and ainformation.content like '%"+information.getContent()+"%' ");
        }

        sql.append(" limit "+((pageNum-1)*pageSize)+","+(pageNum*pageSize)+"");

        return sql.toString();
    }

    /**
     * 多条件查询信息条数
     * @param information
     * @param pjtCode
     * @return
     */
    public String findInfoCount(Information information,String pjtCode,int pageNum,int pageSize,String idList){

        StringBuffer sql=new StringBuffer("select count(*) from ainformation left join atree on atree.id=ainformation.tree_id where atree.pjt_code="+pjtCode+" ");

        if (information.getState()!=null){
            sql.append("and state="+information.getState()+" ");
        }


        if (information.getTree_id()!=null){
            sql.append("and tree_id in("+idList+") ");
        }

        if (information.getTitle()!=null&&!information.getTitle().equals("")){
            sql.append("and ainformation.title like '%"+information.getTitle()+"%' ");
        }

        if (information.getContent()!=null&&!information.getContent().equals("")){
            sql.append("and ainformation.content like '%"+information.getContent()+"%' ");
        }

        //sql.append("limit "+((pageNum-1)*pageSize)+","+(pageNum*pageSize)+"");

        return sql.toString();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    public String delectInformation(String id){

        String sql="delete from ainformation where ainformation.id in("+id+");";
        return sql;
    }

}
