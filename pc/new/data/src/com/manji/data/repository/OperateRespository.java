package com.manji.data.repository;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.List;

public class OperateRespository {

    /**
     * 商家销售信息
     * @param pageNumber
     * @param pageSize
     * @param shopName
     * @param personName
     * @param mobile
     * @param startTime
     * @param endTime
     * @return
     */
    public Page<Record> shopInformationData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String startTime, String endTime){
        Page<Record> shopInformationPage=new Page<Record>();
        String condition="";

        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            condition=" and add_time BETWEEN '"+startTime+"' and '"+endTime+"'";
        }else if(!startTime.equals("") && endTime.equals("")){
            condition=" and add_time > '"+startTime+"'";
        }else if(startTime.equals("") && !endTime.equals("")){
            condition=" and add_time < '"+endTime+"'";
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            condition=" and DATEDIFF(day,add_time,'"+startTime+"')=0";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull(convert(varchar(20),(select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%'),120),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id "+condition+"),'') orderSum,(select COALESCE(SUM(order_amount),0) from dt_orders do where do.shop_user_id=durs.user_id "+condition+") orderMoneySum,IsNull(convert(varchar(20),(select max(add_time) add_time from dt_orders do where do.shop_user_id=durs.user_id),120),'') lastOrderTime from dt_user_role_shopinfo durs) data where 1=1");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and data.lastOrderTime BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and data.lastOrderTime > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and data.lastOrderTime < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,data.lastOrderTime,'"+startTime+"')=0");
        }

        sql.append(" order by data.settledTime desc");
        shopInformationPage= Db.paginate(pageNumber, pageSize,"select *",sql.toString());
        return shopInformationPage;
    }

    /**
     * 商家销售信息数据（未销售）
     * @param pageNumber
     * @param pageSize
     * @param shopName
     * @param personName
     * @param mobile
     * @param time
     * @return
     */
    public Page<Record> shopNoSaleData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String time){
        Page<Record> shopNoSalePage=new Page<Record>();
        String condition="";

        if(!time.equals("")){
            condition=" and add_time <= '"+time+"'";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull((select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%'),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id "+condition+"),'') orderSum from dt_user_role_shopinfo durs) data where orderSum=0");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        sql.append(" order by data.settledTime desc");
        shopNoSalePage= Db.paginate(pageNumber, pageSize,"select *",sql.toString());
        return shopNoSalePage;
    }

    /**
     * 商家入驻信息查询
     * @param pageNumber
     * @param pageSize
     * @param shopName
     * @param personName
     * @param mobile
     * @param time
     * @return
     */
    public Page<Record> shopSettledData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String time){
        Page<Record> shopNoSalePage=new Page<Record>();
        String condition="";

        if(!time.equals("")){
            condition=" and DATEDIFF(day,audit_end_time ,'"+time+"')=0";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull(convert(varchar(20),(select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%' "+condition+" ),120),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id),'') orderSum from dt_user_role_shopinfo durs) data where 1=1 ");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        if(!time.equals("")){
            sql.append(" and DATEDIFF(day,data.settledTime ,'"+time+"')=0");
        }

        sql.append(" order by data.settledTime desc");
        shopNoSalePage= Db.paginate(pageNumber, pageSize,"select *",sql.toString());
        return shopNoSalePage;
    }


    /**
     * 导出商家销售信息
     * @param shopName
     * @param personName
     * @param mobile
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Record> toShopInformationExcel(String shopName, String personName, String mobile, String startTime, String endTime){
        List<Record> shopInformationPage=new ArrayList<Record>();
        String condition="";

        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            condition=" and add_time BETWEEN '"+startTime+"' and '"+endTime+"'";
        }else if(!startTime.equals("") && endTime.equals("")){
            condition=" and add_time > '"+startTime+"'";
        }else if(startTime.equals("") && !endTime.equals("")){
            condition=" and add_time < '"+endTime+"'";
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            condition=" and DATEDIFF(day,add_time,'"+startTime+"')=0";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull((select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%'),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id "+condition+"),'') orderSum,(select COALESCE(SUM(order_amount),0) from dt_orders do where do.shop_user_id=durs.user_id "+condition+") orderMoneySum,IsNull(convert(varchar(20),(select max(add_time) add_time from dt_orders do where do.shop_user_id=durs.user_id),120),'') lastOrderTime from dt_user_role_shopinfo durs) data where 1=1");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and data.lastOrderTime BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and data.lastOrderTime > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and data.lastOrderTime < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,data.lastOrderTime,'"+startTime+"')=0");
        }

        sql.append(" order by data.settledTime desc");
        shopInformationPage= Db.find("select * "+sql.toString());
        return shopInformationPage;
    }

    /**
     * 导出商家销售信息数据（未销售）
     * @param shopName
     * @param personName
     * @param mobile
     * @param time
     * @return
     */
    public List<Record> toshopNoSaleDataExcel(String shopName, String personName, String mobile, String time){
        List<Record> shopNoSaleData=new ArrayList<Record>();
        String condition="";

        if(!time.equals("")){
            condition=" and add_time <= '"+time+"'";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull((select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%'),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id "+condition+"),'') orderSum from dt_user_role_shopinfo durs) data where orderSum=0");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        sql.append(" order by data.settledTime desc");
        shopNoSaleData= Db.find("select * "+sql.toString());
        return shopNoSaleData;
    }

    /**
     * 导出商家入驻信息查询
     * @param shopName
     * @param personName
     * @param mobile
     * @param time
     * @return
     */
    public List<Record> toShopSettledDataExcel(String shopName, String personName, String mobile, String time){
        List<Record> shopNoSaleList=new ArrayList<Record>();
        String condition="";

        if(!time.equals("")){
            condition=" and DATEDIFF(day,audit_end_time ,'"+time+"')=0";
        }

        StringBuffer sql = new StringBuffer("from (select durs.id,IsNull(durs.name,'') name,IsNull(durs.address,'') address,IsNull(convert(varchar(20),(select max(audit_end_time) from dt_user_role_audit_list dural where dural.add_user_id=durs.user_id and audit_result like '%同意%' "+condition+" ),120),'') settledTime,(case IsNull(durs.state,-1)when 0 then '待审核'when 1 then '已通过'when 2 then '不通过'when 3 then '已冻结'when -1 then ''end ) stateName,(case IsNull(durs.is_del,-1) when 0 then '正常' when 1 then '删除'when -1 then ''end ) delName,(case IsNull(durs.is_sign_up,-1) when 0 then '否'when 1 then '是'when -1 then ''end ) signUp,(case IsNull(durs.dpkg,-1)when 0 then '关闭'when 1 then '开启'when -1 then ''end ) dpkg,IsNull((select top 1 title from dt_article_category where id=durs.main_business and is_del=0),'') title,IsNull((select top 1 mobile from dt_users du where du.id=durs.user_id and is_del=0),'') mobile,IsNull((select top 1 person_name from dt_user_personinfo dup where durs.user_id=dup.user_id),'') personName,IsNull((select count(1) from dt_article da where da.user_role_value=durs.id),'') shopSum,IsNull(convert(varchar(20),(select  min(add_time) from dt_article where user_role_value=durs.id),120),'') inputShopTime,IsNull((select count(1) from dt_orders do where do.shop_user_id=durs.user_id),'') orderSum from dt_user_role_shopinfo durs) data where 1=1 ");

        if(!shopName.equals("")){
            sql.append(" and data.name like '%"+shopName+"%'");
        }
        if(!personName.equals("")){
            sql.append(" and data.personName like '%"+personName+"%'");
        }
        if(!mobile.equals("")){
            sql.append(" and data.mobile like '%"+mobile+"%'");
        }

        if(!time.equals("")){
            sql.append(" and DATEDIFF(day,data.settledTime ,'"+time+"')=0");
        }

        sql.append(" order by data.settledTime desc");
        shopNoSaleList= Db.find("select * "+sql.toString());
        return shopNoSaleList;
    }
}
