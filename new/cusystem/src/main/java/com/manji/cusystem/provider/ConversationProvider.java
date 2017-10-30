package com.manji.cusystem.provider;

import com.manji.cusystem.vo.conversation.AddConversationVo;

/**
 * Created by Administrator on 2017/9/8.
 * 动态拼接sql
 */
public class ConversationProvider {

    /**
     * 查询会话信息
     * @return
     */
    public String findConversation(AddConversationVo vo,String userId){

        StringBuffer sql = new StringBuffer("select a.*,b.*,c.cus_type as ctype,c.cus_cid from cus_conversation a,cus_info b,cus_con_sheet c where a.cus_id=c.cus_cid and c.cus_info_id=b.cus_id");

        if(vo.getCus_way() != null && !vo.getCus_way().equals("")){
            sql.append(" and a.cus_way="+vo.getCus_way());
        }
        if(vo.getCus_from() != null && !vo.getCus_from().equals("")){
            sql.append(" and a.cus_from='"+vo.getCus_from()+"'");
        }
        if(vo.getCus_type() != null && !vo.getCus_type().equals("")){
            sql.append(" and b.cus_type='"+vo.getCus_type()+"'");
        }
        if(vo.getStime() != null && !vo.getStime().equals("")){
            sql.append(" and a.cus_ltime>='"+vo.getStime()+"'");
        }
        if(vo.getEtime() != null && !vo.getEtime().equals("")){
            sql.append(" and a.cus_ltime<='"+vo.getEtime()+"'");
        }
        if(userId != null && !"".equals(userId)){
            sql.append(" and a.cus_user_id="+userId);
        }
        if(vo.getOther() != null && !"".equals(vo.getOther())){
            sql.append(" and (a.cus_code='"+vo.getOther()+"' or b.cus_tel='"+vo.getOther()+"' or b.cus_mobile='"+vo.getOther()+"' or b.cus_account='"+vo.getOther()+"'or b.cus_name='"+vo.getOther()+"')");
        }
        sql.append(" limit "+vo.getPageNum()+","+vo.getPageSize());
        return sql.toString();
    }

    /**
     * 查询会话记录总数
     * @param vo
     * @param userId
     * @return
     */
    public String findConversationCount(AddConversationVo vo,String userId){

        StringBuffer sql = new StringBuffer("select count(*) from cus_conversation a,cus_info b,cus_con_sheet c where a.cus_id=c.cus_cid and c.cus_info_id=b.cus_id");
        if(vo.getCus_way() != null && !vo.getCus_way().equals("")){
            sql.append(" and a.cus_way="+vo.getCus_way());
        }
        if(vo.getCus_from() != null && !vo.getCus_from().equals("")){
            sql.append(" and a.cus_from='"+vo.getCus_from()+"'");
        }
        if(vo.getCus_type() != null && !vo.getCus_type().equals("")){
            sql.append(" and b.cus_type='"+vo.getCus_type()+"'");
        }
        if(vo.getStime() != null && !vo.getStime().equals("")){
            sql.append(" and a.cus_ltime>='"+vo.getStime()+"'");
        }
        if(vo.getEtime() != null && !vo.getEtime().equals("")){
            sql.append(" and a.cus_ltime<='"+vo.getEtime()+"'");
        }
        if(userId != null && !"".equals(userId)){
            sql.append(" and a.cus_user_id="+userId);
        }
        if(vo.getOther() != null && !"".equals(vo.getOther())){
            sql.append(" and (a.cus_code='"+vo.getOther()+"' or b.cus_tel='"+vo.getOther()+"' or b.cus_mobile='"+vo.getOther()+"' or b.cus_account='"+vo.getOther()+"'or b.cus_name='"+vo.getOther()+"')");
        }
        return sql.toString();

    }

    /**
     * 查询呼出的订单的会话记录
     * @param vo
     * @param userId
     * @return
     */
    public String findOrderConversation(AddConversationVo vo,String userId){

        StringBuffer sql = new StringBuffer("select a.*,b.* from cus_conversation a,cus_info b ,cus_con_sheet c,cus_order_con d where a.cus_id=c.cus_cid and a.cus_id=d.cus_cid and b.cus_id=c.cus_info_id");

        sql.append(" and b.cus_mobile='"+vo.getOther()+"' and a.cus_user_id="+userId+" limit "+vo.getPageNum()+","+vo.getPageSize());

        return sql.toString();
    }

    /**
     * 查询数量
     * @param vo
     * @param userId
     * @return
     */
    public String findOrderConversationCount(AddConversationVo vo,String userId){

        StringBuffer sql = new StringBuffer("select count(*) from cus_conversation a,cus_info b ,cus_con_sheet c,cus_order_con d where a.cus_id=c.cus_cid and a.cus_id=d.cus_cid and b.cus_id=c.cus_info_id");

        sql.append(" and b.cus_mobile='"+vo.getOther()+"' and a.cus_user_id="+userId);

        return sql.toString();
    }
}
