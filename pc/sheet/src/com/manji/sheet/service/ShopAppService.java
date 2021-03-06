package com.manji.sheet.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.utils.DatesUtils;
import com.manji.sheet.utils.WorkerNumberUtil;

import java.util.List;

/**
 * Created by pudding on 2017-6-19.(YYR 商家APP业务逻辑类)
 */
public class ShopAppService extends  BaseSheetService {

    /**
     * 举报模块接口
     */

    //添加工单业务(发起举报)
    public int  saveSheets(String sheet_on,String status,String type_code,String sponsor_id,String sponsor,String sponsor_type,String sponsor_contoct,String source_code,String priority_level,String is_push)
    {
        return Db.update("insert into dt_sheets values(?,?,?,?,?,?,?,?,?,?,?)",sheet_on,status,type_code,sponsor_id
                ,sponsor,sponsor_type,sponsor_contoct,DatesUtils.time(),source_code,priority_level,is_push);
    }

    //添加新增信息(质料)
    public int saveSheetInfo(String sheet_id,String descr,String pics,String submitter){
        return Db.update("insert into dt_sheet_info values(?,?,?,?,?,?)",null,sheet_id,descr,pics, DatesUtils.time(),submitter);
    }

    //添加工单流程日志表
    public int saveSheetflowlog(String sheet_id,String opr_dept_id,String opr_dept,String opr_user_id,String opr_user,String result,String pics){
        String descr="部分流程走完发起者自己点了已解决";
        return Db.update("insert into dt_sheet_flowlog values(?,?,?,?,?,?,?,?,?,?,?);",null,sheet_id,opr_dept_id,opr_dept,opr_user_id,opr_user,DatesUtils.time(),6,result,descr,pics);
    }

    /**
     * 商家查询我被举报工单(我被举报)
     */
    public List<Record> findbusinessByShopId(String ShopUserId){
        List<Record> sheets=Db.find("select * from dt_sheets where id in (select sheet_id from dt_sheet_business where shop_user_id=?);",ShopUserId);
        return sheets;
    }

    /**
     * 通过工单发起人编号查询工单(我的举报)
     */
    public List<Record> findSheetBySponsorId(String sponsor_id){
        List<Record> recordList=Db.find("select * from dt_sheets where sponsor_id=?",sponsor_id);
        return  recordList;
    }

    /**
     * 通过工单id查询工单
     */
    public Record findSheetById(String sheetId){
        Record record=Db.findFirst("select *,convert(varchar(100),start_time,20) start_time from dt_sheets where id=?",sheetId);
        return record;
    }

    /**
     * 通过工单号查询工单
     */
    public Record findSheetBySheetNo(String sheet_no){
        Record record=Db.findFirst("select * from dt_sheets where sheet_no=?",sheet_no);
        return record;
    }

    /**
     * 通过工单id查询出正式资料
     * @param SheetId
     * @return
     */
    public Record findSInfo(String SheetId){
        Record record= Db.findFirst("select *,convert(varchar(100),add_time,20) add_time from dt_sheet_info where sheet_id=?",SheetId);
        return record;
    }

    /**
     * 通过工单id查询新增详细信息(补充质料)
     * @return
     */
    public List<Record> findSheetInfo(String SheetId){
        List<Record> recordList= Db.find("select *,convert(varchar(100),add_time,20) add_time from dt_sheet_info where sheet_id=? order by dt_sheet_info.add_time desc",SheetId);
        return  recordList;
    }



    /**
     * 获取商家全部二级分类
     * @return
     */
    public List<Record> getShopType(){
        //获取举报类型2级分类
        // (举报员工)
        List<Record> recordList= WorkerNumberUtil.getcodeReport("03");
        //（建议）
        List<Record> recordList1=WorkerNumberUtil.getcodeReport("05");
        //(评价纠纷)
        List<Record> recordList2=WorkerNumberUtil.getcodeReport("07");
        recordList.addAll(recordList1);
        recordList.addAll(recordList2);
        return recordList;
    }


    /**
     * 通过商家userId查询我被举报
     */
    public List<Record> IwasReported(String userId){
        //查询出举报时间和举报类型
        return Db.find("select dt_sheets.id,dt_sheets.status,dt_sheets.start_time,dt_sheet_type.title from dt_sheets left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code  where dt_sheets.id in (select sheet_id from dt_sheet_business where shop_user_id=? and sponsor_type=3 )  and dt_sheets.status in (3,4,5,6) and (dt_sheet_type.code  like '01%' or dt_sheet_type.code  like '02%') and dt_sheets.is_push=0 order by start_time desc",userId);
    }


    /**
     * 通过userId查询我的举报
     */
    public List<Record> MyReported(String userId){
        return  Db.find("select dt_sheets.id,dt_sheets.status,dt_sheets.start_time,dt_sheet_type.title from dt_sheets left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code where sponsor_id =? and sponsor_type=1 and (dt_sheet_type.code  like '03%') order by dt_sheets.start_time desc;",userId);
    }

    /**
     * 通过工单id查询出工单详情表
     */
    public Record findSheetDetail(String sheetId){
        return Db.findFirst("select * from dt_sheet_detail where sheet_id=?",sheetId);
    }

    /**
     * 通过工单id查询流程信息
     */
    public List<Record> findsheetLog(String sheetId){
        return Db.find("select *,convert(varchar(10),opr_type) opr_type,convert(varchar(100),opr_time,20) opr_time from dt_sheet_flowlog where sheet_id=?",sheetId);
    }

    /**
     * 通过工单id查询流程信息
     */
    public List<Record> findsheetLogs(String sheetId){
        return Db.find("select *,convert(varchar(10),opr_type) opr_type,convert(varchar(100),opr_time,20) opr_time from dt_sheet_flowlog where sheet_id=? and opr_type!=5",sheetId);
    }

    /**
     * 通过工单id查询dt_sheet_business
     */
    public Record findBuiness(String sheetId){
        return Db.findFirst("select * from dt_sheet_business where sheet_id=?",sheetId);
    }

    /**
     * 通过类型编码查询类型
     * @return
     */
    public Record findTypeBytypeCode(String type_code){
        String sql="select * from dt_sheet_type where code=?";
        return Db.findFirst(sql,type_code);
    }

    /**
     * 通过类型标示编码查询出举报对象
     */
    public Record findObjectBytypeshortTitle(String short_title){
        String sql="select * from dt_sheet_type where short_title=?";
        return Db.findFirst(sql,short_title);
    }

    /**
     * 添加到信息表
     */
    public int insertSheetInfo(String sheet_id,String descr,String path, Account account){
        String sql="insert into dt_sheet_info values(?,?,?,?,?,?,?)";
        return Db.update(sql,sheet_id,descr,path,DatesUtils.time(),account.getNick_name(),account.getUser_id(),account.getUser_role_type());
    }

    /**
     * 查询举报员工的全部类型
     */
    public List<Record> findSheetTypeByRMJ(){
        String sql="select * from dt_sheet_type where short_title=? and code!='03'";
        return Db.find(sql,"RMJ");
    }

    /**
     * 添加中间表
     */
    public int insertSheetbusiness(String sheet_id){
        return Db.update("insert into dt_sheet_business values(?,0,0,0,0,0)",sheet_id);
    }

    /**
     * 添加部门表审核
     */
    public int insertdetail(String sheet_id){
        return Db.update("insert into dt_sheet_detail values(?,null,'监察部',2,'监察部',2,null,null)",sheet_id);
    }

    /**
     * 添加(处理中)日志
     */
    public int insertlog(String sheet_id,String descr,String path){
        return Db.update("insert into dt_sheet_flowlog values(?,null,null,null,null,?,3,'处理中',?,?,0)",sheet_id,DatesUtils.time(),descr,path);
    }

    /**
     * 交易纠纷模块接口
     */


    /**
     * 查询全部交易纠纷
     */
    public List<Record> findAlldispute(String userId){
        String sql="select dt_sheets.id,dt_sheets.start_time,dt_sheets.sheet_no,dt_sheet_type.title,dt_sheets.status,dt_sheet_business.order_id\n" +
                "from dt_sheets \n" +
                "left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code\n" +
                "left join dt_sheet_business on dt_sheet_business.sheet_id=dt_sheets.id\n" +
                "   \n" +
                "where dt_sheets.id in (select sheet_id from dt_sheet_business where shop_user_id=? and sponsor_type=3) \n" +
                " and dt_sheets.status in(3,4,5,6) and dt_sheet_type.code  like '06%'  and dt_sheets.is_push=0 order by dt_sheets.start_time desc;";
        return Db.find(sql,userId);
    }

    /**
     * 查询全部待处理交易纠纷
     */
    public List<Record> findDdispute(String userId){
        String sql="select dt_sheets.id,dt_sheets.start_time,dt_sheets.sheet_no,dt_sheet_type.title,dt_sheets.status,dt_sheet_business.order_id\n" +
                "from dt_sheets \n" +
                "left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code\n" +
                "left join dt_sheet_business on dt_sheet_business.sheet_id=dt_sheets.id\n" +
                "   \n" +
                "where dt_sheets.id in (select sheet_id from dt_sheet_business where shop_user_id=? and sponsor_type=3) \n" +
                " and dt_sheets.status in(3,4) and dt_sheet_type.code  like '06%'  and dt_sheets.is_push=0 order by dt_sheets.start_time desc;";
        return Db.find(sql,userId);
    }

    /**
     * 查询全部已完成交易纠纷
     */
    public List<Record> findWdispute(String userId){
        String sql="select dt_sheets.id,dt_sheets.start_time,dt_sheets.sheet_no,dt_sheet_type.title,dt_sheets.status,dt_sheet_business.order_id\n" +
                "from dt_sheets \n" +
                "left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code\n" +
                "left join dt_sheet_business on dt_sheet_business.sheet_id=dt_sheets.id\n" +
                "   \n" +
                "where dt_sheets.id in (select sheet_id from dt_sheet_business where shop_user_id=? and sponsor_type=3) \n" +
                " and dt_sheets.status=5 and dt_sheet_type.code  like '06%' and dt_sheets.is_push=0  order by dt_sheets.start_time desc;";
        return Db.find(sql,userId);
    }


    /**
     * 通过订单id查询订单详细信息
     */
    public Record findOrderByid(String order_id){
        String sql="select dt_orders.order_no,dt_order_goods.goods_title,dt_order_goods.img_url from dt_orders left join dt_order_goods on dt_order_goods.order_id=dt_orders.id where dt_orders.id=?;";
        return Db.findFirst(sql,order_id);
    }

    /**
     * 通过订单id查询用户详细信息
     */
    public Record findUsersInfoByOrderId(String order_id){
        String sql=" select dt_users.nick_name,dt_users.user_name,dt_users.mobile,dt_user_addr_book.area,dt_user_addr_book.address,dt_user_addr_book.accept_name,dt_orders.order_no,dt_orders.add_time,dt_orders.invoice_title,dt_orders.message,dt_order_goods.spec_text,dt_order_goods.back_quantity,dt_order_goods.goods_title,dt_order_goods.img_url from dt_users  left join dt_orders on dt_orders.user_id=dt_users.id left join dt_order_goods on dt_order_goods.order_id=dt_orders.id left join dt_user_addr_book on dt_user_addr_book.user_id=dt_users.id where dt_orders.id=?;";
        return  Db.findFirst(sql,order_id);
    }

    /**
     * 通过订单id查询退单信息
     */
    public Record findbackinfoByOrderId(String order_id){
        String sql="select * from dt_order_backinfo where order_id=?;";
        return Db.findFirst(sql,order_id);
    }

    /**
     * 通过订单号模糊查询交易纠纷的工单
     */
     public List<Record> findsheetsByorderNo(String order_no,String user_id){
         String sql="select dt_sheets.id,dt_sheets.start_time,dt_sheet_type.title,dt_sheets.status,dt_sheets.sheet_no,dt_sheet_business.order_id from dt_sheets left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code left join dt_sheet_business on dt_sheet_business.sheet_id=dt_sheets.id where dt_sheets.id in(select dt_sheet_business.sheet_id from dt_sheet_business where  dt_sheet_business.order_id in(select dt_orders.id from dt_orders where dt_orders.order_no like '%"+order_no+"%') and shop_user_id=?) and dt_sheets.status in(3,4,5) and dt_sheet_type.code  like '06%';";
         return Db.find(sql,user_id);
     }

    /**
     * 通过userId查询商家信息
     */
    public Record findShopinfo(String userId){
        String sql="select * from dt_user_role_shopinfo where user_id=?";
        return Db.findFirst(sql,userId);
    }



    /**
     * 建议模块接口
     */

    /**
     * 查询全部我的建议
     */
    public List<Record> findAllopinion(String userId){
        return Db.find("select dt_sheets.id,dt_sheets.status,dt_sheets.start_time,dt_sheet_type.title from dt_sheets left join dt_sheet_type on dt_sheets.type_code=dt_sheet_type.code where sponsor_id =? and sponsor_type=1 and dt_sheet_type.code  like '05%' order by dt_sheets.start_time desc;",userId);
    }

    /**
     * 查询举报员工的全部类型
     */
    public List<Record> findSheetTypeByFEB(){
        String sql="select * from dt_sheet_type where short_title=? and code!='05'";
        return Db.find(sql,"FEB");
    }

    /**
     * 添加部门表审核
     */
    public int insertdetailopinion(String sheet_id){
        return Db.update("insert into dt_sheet_detail values(?,null,'计划部',1,'监察部',2,null,null)",sheet_id);
    }

    /**
     * 根据UserId查询Users(用于联系用户)
     */
    public Record findusers(String userId){
        String sql="select avatar,nick_name from dt_users where id=?";
        return Db.findFirst(sql,userId);
    }

}
