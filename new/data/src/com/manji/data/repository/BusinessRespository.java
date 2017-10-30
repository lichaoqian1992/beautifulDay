package com.manji.data.repository;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.trim;

public class BusinessRespository {


    /**
     * 业务员日志信息查询
     * @param pageNumber
     * @param pageSize
     * @param businessName
     * @param startTime
     * @param endTime
     * @return
     */
    public Page<Record> businessLogData(int pageNumber,int pageSize,String businessName,String startTime,String endTime){

        Page<Record> businessLogPage=new Page<Record>();
        StringBuffer sql = new StringBuffer("from dt_user_role_business durb left join dt_user_sign_log dusl on durb.user_id=dusl.user_id left join dt_user_personinfo dup on dup.user_id=durb.user_id where 1=1");
        if(!businessName.equals("")){
            sql.append(" and dup.person_name = '"+trim(businessName)+"'");
        }
        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and dusl.add_time BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and dusl.add_time > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and dusl.add_time < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,dusl.add_time,'"+startTime+"')=0");
        }
        sql.append(" order by add_time desc");
        businessLogPage= Db.paginate(pageNumber, pageSize,"select IsNull(dup.person_name,'') person_name,IsNull(CONVERT(varchar(100), dusl.add_time, 20),'') add_time,IsNull(dusl.continue_times,0) continue_times,IsNull(dusl.address,'') address,IsNull(DATEPART(hour,dusl.add_time),'') timeSlot",sql.toString());
        return businessLogPage;
    }

    /**
     * 查询商品一级分类
     * @return
     */
    public List<Record> articleCategoryOne(){

        List<Record> articleCategoryOne=new ArrayList<Record>();
        articleCategoryOne= Db.find("select id,title from dt_article_category where parent_id=0 and class_layer=1 and channel_id in (7,12)");
        return articleCategoryOne;
    }

    /**
     * 根据上次分类查下级分类
     * @param screenId
     * @return
     */
    public List<Record> levelData(int screenId){

        List<Record> levelData=new ArrayList<Record>();
        levelData= Db.find("select id,title from dt_article_category where parent_id=? and channel_id in (7,12)",screenId);
        return levelData;
    }


    /**
     * 商家信息(查看详情数据接口)
     * @param userId
     * @return
     */
    public Map<String, Object> shopInfoDetails(int userId){

        Map<String, Object> map=new HashMap<String, Object>();
        Record enclosure=new Record();

        /**
         * 店铺信息
         */
        Record shopDetails=Db.findFirst("select IsNull(durst.name,'') name,IsNull(durst.storeName,'') storeName,IsNull(durst.address,'') address,IsNull(durst.area,'') area ,IsNull(durst.acreage,'') acreage,IsNull(durst.content,'') content,IsNull(durst.pc_logo,'') pc_logo,IsNull(durst.wap_logo,'') wap_logo,IsNull(durst.telephone,'') telephone,IsNull(durst.msg_mobile,'') msg_mobile,IsNull(du.user_name,'') user_name,IsNull(du.mobile,'') mobile from dt_user_role_shopinfo_temp durst left join dt_users du on du.id=durst.from_value where durst.user_id=?",userId); //店铺信息详情查询
        List<Record> shopBusinessList=Db.find("select name,cast(user_percentage*100 as numeric(10,2)) user_percentage from dt_business_user where user_role_type='shop' and getdate()<valid_time and is_del=0 and user_id=?",userId);//业务类型及比例

        /**
         * 公司信息
         */
        Record shopCompany=Db.findFirst("select * from dt_user_companyinfo where user_id=?",userId);

        /**
         * 附件
         */
        List<Record> shopEnclosure=Db.find("select title,file_url from dt_user_role_shopinfo_other where shop_user_id=? and is_del=0 ",userId);
        List<Record> shopTitle=Db.find("select title from dt_user_role_shopinfo_other where shop_user_id=? and is_del=0 group by title",userId);
        enclosure.set("shopEnclosure",shopEnclosure);
        enclosure.set("shopTitle",shopTitle);

        map.put("shopDetails", shopDetails);
        map.put("shopBusinessList",shopBusinessList);
        map.put("shopCompany",shopCompany);
        map.put("shopEnclosure",enclosure);

        return map;
    }


    /**
     * 统计所有的三级分类商品有数量
     * @param pageNumber
     * @param pageSize
     * @param levelOne
     * @param levelTow
     * @param levelThere
     * @return
     */
    public Page<Record> articleCategoryAll(int pageNumber,int pageSize,int levelOne,int levelTow,int levelThere){

        Page<Record> articleCategoryPage=new Page<Record>();
        String levelOneString ="";
        String levelTowString ="";
        String levelThereString ="";

        if(levelOne!=0){
            levelOneString=" and dac.id="+levelOne;
        }
        if(levelTow!=0){
            levelTowString=" and dacAlso.towId="+levelTow;
        }
        if(levelThere!=0){
            levelThereString=" and allTitle.id="+levelThere;
        }
        articleCategoryPage= Db.paginate(pageNumber, pageSize,"select allTitle.oneTitle,allTitle.TowTitle,allTitle.title thereTitle,count(da.id) count ","from (select dacAlso.oneTitle,dacAlso.TowTitle,dacThere.title,dacThere.id from (select dac.title oneTitle,dacTow.title TowTitle,dacTow.id towId from dt_article_category dac left join dt_article_category dacTow on dac.id=dacTow.parent_id where dac.class_layer=1 and dac.parent_id=0 and dac.channel_id in (7,12) "+levelOneString+") dacAlso left join dt_article_category dacThere on dacAlso.towId=dacThere.parent_id where dacThere.channel_id in (7,12) "+levelTowString+") allTitle left join dt_article da on da.category_id=allTitle.id where 1=1 "+levelThereString+" group by allTitle.oneTitle,allTitle.TowTitle,allTitle.title");
        return articleCategoryPage;
    }


    /**
     * 查询商品一二三级分类
     * @return
     */
    public List<Record> shopArticleAll(){

        List<Record> articleCategoryOne=new ArrayList<Record>();
        articleCategoryOne= Db.find("select id,title from dt_article_category dac where class_layer =1 and channel_id in (7,12) order by class_layer");
        return articleCategoryOne;
    }

    /**
     * 商家信息(列表页数据)
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Map<String, Object> shopInfoData(int pageNumber,int pageSize,String userName,String nickName,String startTime,String endTime,String mobile,String shopName,String screen){

        Map<String, Object> map=new HashMap<String, Object>();
        Page<Record> shopInfoPage=new Page<Record>();
        List<Record> shopInfoListAll=new ArrayList<Record>();
        Record shopInfoRecord=new Record();
        StringBuffer sql = new StringBuffer(" from dt_user_role_business durb left join dt_user_role_shopinfo durs on durb.user_id=durs.from_value and durs.is_del=0 left join dt_users du on durb.user_id=du.id left join dt_user_personinfo dup on durb.user_id=dup.user_id left join  dt_article_category dac on dac.id=durs.main_business where durs.name is not null and dup.person_name is not null");

        if(!userName.equals("")){
            sql.append(" and du.user_name like '%"+trim(userName)+"%'");
        }
        if(!nickName.equals("")){
            sql.append(" and dup.person_name like '%"+trim(nickName)+"%'");
        }
        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and durs.add_time BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and durs.add_time > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and durs.add_time < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,durs.add_time,'"+startTime+"')=0");
        }
        if(!mobile.equals("")){
            sql.append(" and durs.msg_mobile like '%"+trim(mobile)+"%'");
        }
        if(!shopName.equals("")){
            sql.append(" and (durs.name like '%"+trim(shopName)+"%' or durs.storeName like '%"+trim(shopName)+"%')");
        }
        if(!screen.equals("")){
            sql.append(" and   dac.id ="+screen);
        }

        sql.append("  group by du.user_name,dup.person_name,du.mobile,durs.name ,durs.storeName,durs.msg_mobile,dac.title,dac.id,durs.add_time,durs.user_id order by durs.add_time desc");
        
        System.out.println(sql);
        
        shopInfoPage=Db.paginate(pageNumber,pageSize,"select dac.id,durs.user_id,IsNull(du.user_name,'') user_name,IsNull(dup.person_name,'') person_name,IsNull(du.mobile,'') mobile,IsNull(durs.name,'') name,IsNull(durs.storeName,'') storeName,IsNull(durs.msg_mobile,'') msg_mobile,IsNull(dac.title,'') title,IsNull(CONVERT(varchar(100), durs.add_time, 20),'') add_time",sql.toString());

        List<Record> shopInfoList=Db.find("select durs.name,IsNull(count(da.status),0) count,IsNull(da.status,0) status from dt_user_role_business durb left join dt_user_role_shopinfo durs on durb.user_id=durs.from_value and durs.is_del=0 left join dt_article da on da.user_role_value=durs.id where da.is_del=0 group by da.status,durs.name");

        for(Record x : shopInfoPage.getList()){
            x.set("msg_mobile",x.get("msg_mobile").toString().replaceAll(",","、"));
            List<Record> shopInfoListalso=new ArrayList<Record>();
            for(Record shopList : shopInfoList){
                if(shopList.get("name").equals(x.get("name"))){
                    shopInfoListalso.add(shopList);
                }
            }
            if(shopInfoListalso!=null){
                int allCount=0;
                int pendingCount=0;
                int notPassCount=0;
                for(Record xx : shopInfoListalso){
                    allCount+=Integer.parseInt(xx.get("count").toString());
                    if(Integer.parseInt(xx.get("status","0").toString())==1){
                        pendingCount+=Integer.parseInt(xx.get("count").toString());
                    }else if(Integer.parseInt(xx.get("status","0").toString())==3){
                        notPassCount+=Integer.parseInt(xx.get("count").toString());
                    }
                }
                shopInfoRecord=x;
                shopInfoRecord.set("allCount",allCount);
                shopInfoRecord.set("pendingCount",pendingCount);
                shopInfoRecord.set("notPassCount",notPassCount);
                shopInfoListAll.add(shopInfoRecord);
            }
        }

        map.put("shopInfoPage",shopInfoPage);
        map.put("shopInfoListAll",shopInfoListAll);
        return map;
    }

    /**
     * 导出业务员日志信息
     * @param businessName
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Record> toBusinessLogExcel(String businessName, String startTime, String endTime){

        List<Record> businessLogList=new ArrayList<Record>();
        StringBuffer sql = new StringBuffer("select IsNull(dup.person_name,'') person_name,IsNull(CONVERT(varchar(100), dusl.add_time, 20),'') add_time,IsNull(dusl.continue_times,0) continue_times,IsNull(dusl.address,'') address,IsNull(DATEPART(hour,dusl.add_time),'') timeSlot from dt_user_role_business durb left join dt_user_sign_log dusl on durb.user_id=dusl.user_id left join dt_user_personinfo dup on dup.user_id=durb.user_id where 1=1");

        if(!businessName.equals("")){
            sql.append(" and dup.person_name = '"+trim(businessName)+"'");
        }
        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and dusl.add_time BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and dusl.add_time > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and dusl.add_time < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,dusl.add_time,'"+startTime+"')=0");
        }
        sql.append(" order by add_time desc");

        businessLogList= Db.find(sql.toString());
        return businessLogList;
    }

    /**
     * 导出统计所有的三级分类商品有数量
     * @param levelOne
     * @param levelTow
     * @param levelThere
     * @return
     */
    public List<Record> toArticleCategoryExcel(int levelOne,int levelTow,int levelThere){

        List<Record> articleCategoryList=new ArrayList<Record>();
        String levelOneString ="";
        String levelTowString ="";
        String levelThereString ="";

        if(levelOne!=0){
            levelOneString=" and dac.id="+levelOne;
        }
        if(levelTow!=0){
            levelTowString=" and dacAlso.towId="+levelTow;
        }
        if(levelThere!=0){
            levelThereString=" and allTitle.id="+levelThere;
        }
        articleCategoryList= Db.find("select allTitle.oneTitle,allTitle.TowTitle,allTitle.title thereTitle,count(da.id) count from (select dacAlso.oneTitle,dacAlso.TowTitle,dacThere.title,dacThere.id from (select dac.title oneTitle,dacTow.title TowTitle,dacTow.id towId from dt_article_category dac left join dt_article_category dacTow on dac.id=dacTow.parent_id where dac.class_layer=1 and dac.parent_id=0 and dac.channel_id in (7,12) "+levelOneString+") dacAlso left join dt_article_category dacThere on dacAlso.towId=dacThere.parent_id where dacThere.channel_id in (7,12) "+levelTowString+") allTitle left join dt_article da on da.category_id=allTitle.id where 1=1 "+levelThereString+" group by allTitle.oneTitle,allTitle.TowTitle,allTitle.title");
        return articleCategoryList;
    }

    /**
     * 导出商家列表
     * @param userName
     * @param nickName
     * @param startTime
     * @param endTime
     * @param mobile
     * @param shopName
     * @param screen
     * @return
     */

    public List<Record> toShopInfoDataExcel(String userName,String nickName,String startTime,String endTime,String mobile,String shopName,String screen){

        List<Record> shopInfoPage=new ArrayList<Record>();
        List<Record> shopInfoListAll=new ArrayList<Record>();
        Record shopInfoRecord=new Record();

        StringBuffer sql = new StringBuffer(" from dt_user_role_business durb left join dt_user_role_shopinfo durs on durb.user_id=durs.from_value and durs.is_del=0 left join dt_users du on durb.user_id=du.id left join dt_user_personinfo dup on durb.user_id=dup.user_id left join  dt_article_category dac on dac.id=durs.main_business where durs.name is not null and dup.person_name is not null");
        if(!userName.equals("")){
            sql.append(" and du.user_name like '%"+trim(userName)+"%'");
        }
        if(!nickName.equals("")){
            sql.append(" and dup.person_name like '%"+trim(nickName)+"%'");
        }
        if(!startTime.equals("") && !endTime.equals("") && !startTime.equals(endTime)){
            sql.append(" and durs.add_time BETWEEN '"+startTime+"' and '"+endTime+"'");
        }else if(!startTime.equals("") && endTime.equals("")){
            sql.append(" and durs.add_time > '"+startTime+"'");
        }else if(startTime.equals("") && !endTime.equals("")){
            sql.append(" and durs.add_time < '"+endTime+"'");
        }else if(startTime.equals(endTime) && !startTime.equals("") && !endTime.equals("")){
            sql.append(" and DATEDIFF(day,durs.add_time,'"+startTime+"')=0");
        }
        if(!mobile.equals("")){
            sql.append(" and durs.msg_mobile like '%"+trim(mobile)+"%'");
        }
        if(!shopName.equals("")){
            sql.append(" and (durs.name = '"+shopName+"' or durs.storeName = '"+shopName+"')");
        }
        if(!screen.equals("")){
            sql.append(" and   dac.id ="+screen);
        }

        sql.append("  group by du.user_name,dup.person_name,du.mobile,durs.name ,durs.storeName,durs.msg_mobile,dac.title,dac.id,durs.add_time,durs.user_id order by durs.add_time desc");

        shopInfoPage=Db.find("select dac.id,durs.user_id,IsNull(du.user_name,'') user_name,IsNull(dup.person_name,'') person_name,IsNull(du.mobile,'') mobile,IsNull(durs.name,'') name,IsNull(durs.storeName,'') storeName,IsNull(durs.msg_mobile,'') msg_mobile,IsNull(dac.title,'') title,IsNull(CONVERT(varchar(100), durs.add_time, 23),'') add_time"+sql.toString());
        List<Record> shopInfoList=Db.find("select durs.name,IsNull(count(da.status),0) count,IsNull(da.status,0) status from dt_user_role_business durb left join dt_user_role_shopinfo durs on durb.user_id=durs.from_value and durs.is_del=0 left join dt_article da on da.user_role_value=durs.id where da.is_del=0 group by da.status,durs.name");

        for(Record x : shopInfoPage){
            x.set("msg_mobile",x.get("msg_mobile").toString().replaceAll(",","、"));
            List<Record> shopInfoListalso=new ArrayList<Record>();
            for(Record shopList : shopInfoList){
                if(shopList.get("name").equals(x.get("name"))){
                    shopInfoListalso.add(shopList);
                }
            }
            if(shopInfoListalso!=null){
                int allCount=0;
                int pendingCount=0;
                int notPassCount=0;
                for(Record xx : shopInfoListalso){
                    allCount+=Integer.parseInt(xx.get("count").toString());
                    if(Integer.parseInt(xx.get("status","0").toString())==1){
                        pendingCount+=Integer.parseInt(xx.get("count").toString());
                    }else if(Integer.parseInt(xx.get("status","0").toString())==3){
                        notPassCount+=Integer.parseInt(xx.get("count").toString());
                    }
                }
                shopInfoRecord=x;
                shopInfoRecord.set("allCount",allCount);
                shopInfoRecord.set("pendingCount",pendingCount);
                shopInfoRecord.set("notPassCount",notPassCount);
                shopInfoListAll.add(shopInfoRecord);
            }
        }

        return shopInfoPage;
    }

}
