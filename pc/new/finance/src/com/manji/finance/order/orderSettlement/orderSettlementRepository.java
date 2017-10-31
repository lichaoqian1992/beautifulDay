package com.manji.finance.order.orderSettlement;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.util.List;

/**
 * Created by pudding on 2017-8-14.(查询商家最后一笔提现后全部的结算订单)
 */
public class orderSettlementRepository extends DButils{

    /**
     * 查询全部商家最后一次提现时间
     * @return
     */
    public List<Record> findlastByShop(){

        String sql="select user_id,max(add_time) as addTime from dt_user_withdrawals as w where user_role_type='Shop' group by user_id order by user_id asc";
        return sqlserver.find(sql);
    }


    /**
     * 查询全部有结算单的商家
     * @param num
     * @return
     */
    public Page<Record> findAllShopOrder(int num,String ShopName){
        StringBuffer sql=new StringBuffer(" from dt_user_role_shopinfo s left join dt_article_category a on s.main_business=a.id  left join dt_user_accountinfo i on s.user_id=i.user_id and i.role_type='Shop' where s.user_id in(select user_id from dt_user_balance_log b where role_type='Shop' group by user_id) ");
        if (!ShopName.equals("")){
            sql.append(" and name like '%"+ShopName+"%'");
        }
        sql.append(" order by s.id desc");

        return sqlserver.paginate(num,20,"select s.id,s.user_id,s.name,s.area,s.msg_mobile,a.title,s.state,i.amount ",sql.toString());
    }

    /**
     * 查询此商家是否有过提现
     * @param userId
     * @return
     */
    public int findWcount(String userId){
        String sql="select COUNT(*) from dt_user_withdrawals where user_id=?";
        return sqlserver.queryFirst(sql,userId);
    }

    /**
     * 查询此商家最后一次提现后的结算单条数
     */
    public int findOrderByW(String userId){

        String sql="select COUNT(*) from dt_user_balance_log where dt_user_balance_log.add_time>(select max(add_time) from dt_user_withdrawals where user_id=? and user_role_type='Shop' and status=5) and user_id=? and role_type='Shop' and balance_state=2;";
        return sqlserver.queryFirst(sql,userId,userId);
    }

    /**
     * 查询此商家的结算单条数
     */
    public int findOrderByUserid(String userId){

        String sql="select COUNT(*) from dt_user_balance_log where user_id=? and role_type='Shop' and balance_state=2;";
        return sqlserver.queryFirst(sql,userId);
    }


    /**
     * 点击详情时查看此商家最后一笔提现已结算的订单信息
     */
    public Page<Record> findAllSettlement(int num,String userId){

        StringBuffer sql=new StringBuffer(" from dt_user_balance_log b left join dt_orders o on b.user_id=o.shop_user_id and  o.order_no=b.order_no left join dt_user_role_shopinfo s on s.user_id=b.user_id left join dt_payment p on p.id=o.payment_id left join dt_business_user bu on o.shop_user_id=bu.user_id and o.order_type=bu.call_index  where b.add_time>(select max(add_time) from dt_user_withdrawals  where user_id=? and user_role_type='Shop' and status=5) and b.user_id=? and b.role_type='Shop' and b.balance_state=2");
        return sqlserver.paginate(num,10,"select s.name,s.area,s.msg_mobile,o.order_no,o.order_title,p.title,o.real_amount,o.voucher,o.order_amount,bu.user_percentage,(bu.user_percentage*o.order_amount) sxf,b.balance_amount,o.add_time,o.complete_time,b.real_balance_date",sql.toString(),userId,userId);
    }

    /**
     * 点击详情时查看此商家最后一笔提现已结算的订单信息
     */
    public Page<Record> findAllSettlements(int num,String userId){
        StringBuffer sql=new StringBuffer(" from dt_user_balance_log b left join dt_orders o on b.user_id=o.shop_user_id and  o.order_no=b.order_no left join dt_user_role_shopinfo s on s.user_id=b.user_id left join dt_payment p on p.id=o.payment_id left join dt_business_user bu on o.shop_user_id=bu.user_id and o.order_type=bu.call_index  where b.user_id=? and b.role_type='Shop' and b.balance_state=2");
        return sqlserver.paginate(num,10,"select s.name,s.area,s.msg_mobile,o.order_no,o.order_title,p.title,o.real_amount,o.voucher,o.order_amount,bu.user_percentage,(bu.user_percentage*o.order_amount) sxf,b.balance_amount,o.add_time,o.complete_time,b.real_balance_date",sql.toString(),userId);
    }






}
