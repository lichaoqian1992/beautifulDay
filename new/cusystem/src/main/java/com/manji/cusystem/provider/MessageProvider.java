package com.manji.cusystem.provider;

import com.manji.cusystem.vo.message.QueryMessageVo;

/**
 * Created by Administrator on 2017/9/10.
 */
public class MessageProvider {

    public String selectMessage(QueryMessageVo vo){

        StringBuffer sql = new StringBuffer("select * from cus_message where 1=1");

        if(vo.getCusKind() != null && !"".equals(vo.getCusKind())){
            sql.append(" and cus_kind='"+vo.getCusKind()+"'");
        }
        if(vo.getCusStatus() != null && !"".equals(vo.getCusStatus())){
            sql.append(" and cus_status="+vo.getCusStatus());
        }
        if(vo.getCusType() != null && !"".equals(vo.getCusType())){
            sql.append(" and cus_type="+vo.getCusType());
        }
        if(vo.getStartTime() != null && !"".equals(vo.getStartTime())){
            sql.append(" and cus_time>="+vo.getStartTime());
        }
        if(vo.getEndTime() != null && !"".equals(vo.getEndTime())){
            sql.append(" and cus_time<="+vo.getEndTime());
        }
        if(vo.getCusContent() != null && !"".equals(vo.getCusContent())){
            sql.append(" and cus_content like '%"+vo.getCusContent()+"%'");
        }
        sql.append(" limit "+vo.getPageNum()+","+vo.getPageSize());

        return sql.toString();
    }

    /**
     * 查询总数据
     * @param vo
     * @return
     */
    public String selectMessageCount(QueryMessageVo vo){

        StringBuffer sql = new StringBuffer("select count(*) from cus_message where 1=1 ");

        if(vo.getCusKind() != null && !"".equals(vo.getCusKind())){
            sql.append(" and cus_kind='"+vo.getCusKind()+"'");
        }
        if(vo.getCusStatus() != null && !"".equals(vo.getCusStatus())){
            sql.append(" and cus_status="+vo.getCusStatus());
        }
        if(vo.getCusType() != null && !"".equals(vo.getCusType())){
            sql.append(" and cus_type="+vo.getCusType());
        }
        if(vo.getStartTime() != null && !"".equals(vo.getStartTime())){
            sql.append(" and cus_time>="+vo.getStartTime());
        }
        if(vo.getEndTime() != null && !"".equals(vo.getEndTime())){
            sql.append(" and cus_time<="+vo.getEndTime());
        }
        if(vo.getCusContent() != null && !"".equals(vo.getCusContent())){
            sql.append(" and cus_content like '%"+vo.getCusContent()+"%'");
        }

        return sql.toString();
    }
}
