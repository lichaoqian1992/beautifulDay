package com.manji.sheet.service;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.manji.sheet.base.Message;
import com.manji.sheet.model.reqParam.EvaluateParam;
import com.manji.sheet.model.reqParam.InsertOrder;
import com.manji.sheet.model.reqParam.WorkOrderParam;
import com.manji.sheet.utils.WorkerNumberUtil;
import com.manji.sheet.utils.MD5util;
import com.manji.sheet.utils.ExcelUtils;
import com.manji.sheet.utils.InterfaceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ManageService extends BaseSheetService {
    /**
     * 查询来源/
     *
     * @return
     */
    public Map<String, Object> fromType() {
        List<Record> fromList = Db.find("select * from dt_sheet_source where layer=1 and status=1");
        List<Record> typeList = Db.find("select * from dt_sheet_type where layer=1 and status=1");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fromList", fromList);
        map.put("typeList", typeList);
        return map;
    }

    /**
     * 查询工单状态
     *
     * @param id
     * @return
     */
    public Record sheetStatus(int id) {
        return Db.findFirst("select * from dt_sheets where id=?", id);
    }


    /**
     * 查询部门
     *
     * @return
     */
    public List<Record> dept() {
        return Db.find("select * from dt_base_dept where status=1");
    }

    /**
     * 工单查询
     *
     * @param pageNumber
     * @param pageSize
     * @param workOrder
     * @return
     */
    public Page<Record> Acceptance(int pageNumber, int pageSize, WorkOrderParam workOrder) {
        Page<Record> page = new Page<Record>();
        if (workOrder.getStatus().equals("supermarket")) {
            StringBuffer sql = new StringBuffer();
            sql.append("from dt_sheets ds left join dt_sheet_type dst on ds.type_code=dst.code and dst.status=1 left join dt_sheet_detail dsd on dsd.sheet_id=ds.id left join  dt_sheet_source dss on dss.code=ds.source_code and dss.status=1 left join dt_sheet_overtime dso on dso.priority_id=ds.priority_level where 1=1 and DATEADD(hour,dso.process_time, ds.start_time) < getdate()");
            sql = AcceptanceSQL(sql, workOrder);
            page = Db.paginate(pageNumber, pageSize, "select ds.id,ds.sheet_no,ds.start_time,dst.merge_title typeCode,isnull(ds.sponsor,'') sponsor,dss.merge_title sourceCode,ds.status,isnull(dsd.receiver,'') receiver,isnull(dsd.exe_dept,'') exe_dept,isnull(dsd.sup_dept,'') sup_dept", sql.toString());
        } else {
            StringBuffer sql = new StringBuffer();
            sql.append("from dt_sheets ds left join dt_sheet_type dst on ds.type_code=dst.code and dst.status=1 left join dt_sheet_detail dsd on dsd.sheet_id=ds.id left join  dt_sheet_source dss on dss.code=ds.source_code and dss.status=1 where 1=1");
            sql = AcceptanceSQL(sql, workOrder);
            page = Db.paginate(pageNumber, pageSize, "select ds.id,ds.sheet_no,ds.start_time,dst.merge_title typeCode,isnull(ds.sponsor,'') sponsor,dss.merge_title sourceCode,ds.status,isnull(dsd.receiver,'') receiver,isnull(dsd.exe_dept,'') exe_dept,isnull(dsd.sup_dept,'') sup_dept", sql.toString());
        }

        return page;
    }

    /**
     * 新增工单
     *
     * @return
     */
    @Before(Tx.class)
    public void InsertWorkNumber(InsertOrder insertOrder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String codeType = insertOrder.getCodeType();
        /*工单主表*/
        Record sheet = new Record();
        String short_title = WorkerNumberUtil.getcodeTitle(insertOrder.getChildType()).get("short_title").toString();
        sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(short_title));
        if (codeType.equals("03") || codeType.equals("05")) {
            sheet.set("status", 3);
        } else {
            sheet.set("status", 1);
        }
        sheet.set("type_code", insertOrder.getChildType());
        sheet.set("sponsor_id", 0);
        sheet.set("sponsor", insertOrder.getUsername());
        sheet.set("sponsor_type", Integer.parseInt(insertOrder.getNameType()));
        sheet.set("sponsor_contact", insertOrder.getUsermobile());
        sheet.set("start_time", sdf.format(new Date()));
        if (codeType.equals("03")) {
            sheet.set("source_code", "05_01");
        } else if (codeType.equals("04")) {
            sheet.set("source_code", "05_03");
        } else if (codeType.equals("05")) {
            sheet.set("source_code", "05_02");
        }

        sheet.set("priority_level", Integer.parseInt(insertOrder.getPriorityLevel()));
        sheet.set("is_push", 0);
        Db.save("dt_sheets", sheet);

		/*保存工单资料表*/
        Record sheetData = new Record();
        sheetData.set("sheet_id", sheet.get("id"));
        sheetData.set("descr", insertOrder.getUserexplain());
        sheetData.set("pics", insertOrder.getPath());
        sheetData.set("add_time", sdf1.format(new Date()));
        sheetData.set("submitter", insertOrder.getUsername());
        Db.save("dt_sheet_info", sheetData);

		/*工单业务关系表*/
        Record sheetRelation = new Record();
        sheetRelation.set("sheet_id", sheet.get("id"));
        sheetRelation.set("order_id", 0);
        sheetRelation.set("back_order_id", 0);
        sheetRelation.set("shop_user_id", 0);
        sheetRelation.set("shop_id", 0);
        sheetRelation.set("article_id", 0);
        Db.save("dt_sheet_business", sheetRelation);
		/*工单流程记录*/
        Record flowLog = new Record();
        if (codeType.equals("03") || codeType.equals("05")) {
            flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf2.format(new Date())).set("opr_type", 3).set("result", "处理中").set("descr", insertOrder.getUserexplain()).set("pics", insertOrder.getPath()).set("is_see", 0);
        } else {
            flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf2.format(new Date())).set("opr_type", 1).set("result", "待受理").set("descr", insertOrder.getUserexplain()).set("pics", insertOrder.getPath()).set("is_see", 0);
        }
        Db.save("dt_sheet_flowlog", flowLog);

        if (codeType.equals("03") || codeType.equals("05")) {
            Record sheetDetail = new Record();
            sheetDetail.set("sheet_id", sheet.get("id"));
            sheetDetail.set("receiver", "");
            if (codeType.equals("05")) {
                sheetDetail.set("exe_dept_id", 1);
                sheetDetail.set("exe_dept", "计划部");
            } else if (codeType.equals("03")) {
                sheetDetail.set("exe_dept_id", 2);
                sheetDetail.set("exe_dept", "监察部");
            }
            sheetDetail.set("sup_dept", "监察部");
            sheetDetail.set("sup_dept_id", 2);

            Db.save("dt_sheet_detail", sheetDetail);
        }
    }

    /**
     * 工单交易纠纷
     *
     * @param id
     * @return
     */
    public Map<String, Object> SelectWorkNumber(int id) {
        //工单信息
        Record WorkNumber = Db.findFirst("select ds.id,ds.type_code,ds.sheet_no,do.order_amount,do.express_fee,dsb.article_id,dsb.order_id,convert(varchar(20),ds.status) status,dsd.exe_dept,dsd.sup_dept,do.order_no,CONVERT(varchar(100), ds.start_time, 20) start_time,dst.merge_title typeTitle,durs.name,dss.merge_title fromTitle,ds.sponsor_type,ds.sponsor,ds.sponsor_contact,ds.priority_level from dt_sheets ds left join dt_sheet_type dst on ds.type_code=dst.code and dst.status=1 left join dt_sheet_detail dsd on dsd.sheet_id=ds.id left join  dt_sheet_source dss on dss.code=ds.source_code and dss.status=1 left join dt_sheet_business dsb on dsb.sheet_id=ds.id left join dt_orders do on do.id=dsb.order_id and do.is_del=0 left join dt_user_role_shopinfo durs on dsb.shop_user_id=durs.user_id and  durs.user_id<>0 and durs.is_del=0 and durs.dpkg=1 where 1=1 and ds.id=?", id);
        WorkNumber.set("aClassType", WorkerNumberUtil.getcode(WorkNumber.get("type_code").toString()).get("merge_title"));
        String code = WorkerNumberUtil.getcode(WorkNumber.get("type_code").toString()).get("code");
        if (code.equals("03") || code.equals("04") || code.equals("05") || code.equals("07")) {
            WorkNumber.set("aClasscode", "false");
        } else {
            WorkNumber.set("aClasscode", "true");
        }

        WorkNumber.set("OneCode", code);

        switch (WorkNumber.get("priority_level").toString()) {
            case "1":
                WorkNumber.set("priority_level", "一般");
                break;
            case "2":
                WorkNumber.set("priority_level", "急");
                break;

            case "3":
                WorkNumber.set("priority_level", "加急");
                break;

            default:
                break;
        }

        Record WorkInfoGoods = null;
        if (!WorkNumber.get("article_id").equals("") && !WorkNumber.get("article_id").equals("0")) {
            WorkInfoGoods = Db.findFirst("select * from dt_order_goods where id=?", WorkNumber.get("article_id"));
        }

        List<Record> WorkInfo = Db.find("select submitter,CONVERT(varchar(100), add_time, 20) add_time,descr,id,pics from dt_sheet_info where sheet_id=? order by add_time asc", id);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("WorkInfoGoods", WorkInfoGoods);
        map.put("WorkNumber", WorkNumber);
        map.put("WorkInfo", WorkInfo);
        return map;
    }

    /**
     * 订单详情
     *
     * @param WorkNumber
     * @return
     */
    public Map<String, Object> SelectOrderInfo(Record WorkNumber) {
        //买家详细信息
        Record BuyersInfo = Db.findFirst("select do.is_anonymous,do.status,do.order_no,CONVERT(varchar(100), do.add_time, 20) add_time,du.nick_name,du.mobile dumobile,dogone.accept_name,dogone.mobile dogonemobile,dogone.post_code,dogone.address,dogone.area,do.invoice_title,isnull(do.message,'') message from dt_orders do left join dt_order_goodinfo dog on do.id=dog.order_id left join dt_users du on du.id=do.user_id left join dt_user_personinfo dup on dup.user_id=du.id left join dt_order_goodinfo dogone on dogone.order_id=do.id  where do.is_del=0 and do.id=?", WorkNumber.get("order_id"));
        List<Record> BuyersList = Db.find("select dog.id,dog.img_url,dog.goods_title,dog.spec_text,dog.real_price,dog.quantity,(dog.real_price*dog.quantity) allmoney from dt_order_goods dog where dog.order_id=?", WorkNumber.get("order_id"));
        String[] ary = BuyersInfo.get("area").toString().split(",");
        if (ary.length >= 3) {
            if (ary[0].equals("重庆市")) {
                BuyersInfo.set("address", ary[0] + ary[2] + BuyersInfo.get("address").toString());
            }
        } else {
            String Area = "";
            for (String item : ary) {
                Area += item;
            }
            BuyersInfo.set("address", Area + BuyersInfo.get("address").toString());
        }

        if (BuyersInfo.get("is_anonymous").toString().equals("1")) {
            BuyersInfo.set("person_name", "匿名买家");
        }

        //优惠
        Double money = 0.0;
        for (Record x : BuyersList) {
            Double moneyChild = Integer.valueOf(x.get("quantity").toString()) * Double.valueOf(x.get("real_price").toString());
            money += moneyChild;
        }
        Record rc = new Record();
        rc.set("money", money);
        rc.set("order_amount", WorkNumber.get("order_amount"));
        rc.set("express_fee", WorkNumber.get("express_fee"));
        rc.set("discount", 0.0);

        //发货地址
        Record ShipAddress = Db.findFirst("select dsh.name,dsh.telephone,dsh.address,dsh.code,das.Area from dt_shop_expensesInfo dsh left join dt_apo_site das on das.Area_code=dsh.area_code  where shop_id = (select shop_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))", WorkNumber.get("sheet_no"));

        String[] aryalso = ShipAddress.get("Area").toString().split(",");
        if (aryalso.length >= 3) {
            if (aryalso[0].equals("重庆市")) {
                ShipAddress.set("Area", aryalso[0] + aryalso[2] + ShipAddress.get("address").toString());
            }
        } else {
            String Area = "";
            for (String item : aryalso) {
                Area += item;
            }
            ShipAddress.set("Area", Area + ShipAddress.get("address").toString());
        }

        //物流信息
        Record logistics = Db.findFirst("select isnull(dog.express_no,'') express_no,isnull(de.title,'') title,isnull(CONVERT(varchar(100), dog.express_time, 20),'') express_time from dt_order_goodinfo dog left join  dt_express de on de.id=dog.express_id where dog.order_id=?", WorkNumber.get("order_id"));
        //详情物流信息
        Record remarkRecordAll = new Record();
        List<Record> remarkList = new ArrayList<Record>();
        if (logistics != null) {
            Record logisticsDetails = Db.findFirst("select * from dt_express_info where express_no=?", logistics.get("express_no"));
            if (logisticsDetails != null) {
                String remark = logisticsDetails.get("remark");
                if (remark != null && !remark.equals("")) {
                    JSONObject remarkJson = JSONObject.fromObject(remark);
                    Map<String, Object> map = remarkJson;
                    for (String key : map.keySet()) {
                        Record remarkRecord = new Record();
                        remarkRecord.set("keyTime", key);
                        remarkRecord.set("value", map.get(key));
                        remarkList.add(remarkRecord);
                    }
                }

                //倒序
                Collections.reverse(remarkList);
                String nick_name = Db.findFirst("select nick_name from dt_users where id =(select shop_user_id from dt_orders where id=?)", WorkNumber.get("order_id")).get("nick_name");
                remarkRecordAll.set("nick_name", nick_name);
                remarkRecordAll.set("remarkList", remarkList);
                remarkRecordAll.set("status", logisticsDetails.get("status"));
                remarkRecordAll.set("isOver", logisticsDetails.get("is_over"));
            }

        }


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rc", rc);
        map.put("BuyersList", BuyersList);
        map.put("logistics", logistics);
        map.put("ShipAddress", ShipAddress);
        map.put("BuyersInfo", BuyersInfo);
        map.put("remarkRecordAll", remarkRecordAll);
        return map;
    }

    /**
     * 退款详情
     *
     * @param WorkNumber
     * @return
     */
    public Map<String, Object> SelectRefund(Record WorkNumber) {
        List<Record> RefundList = Db.find("select dob.id,dob.back_return_no,dob.real_back_amount,dob.back_goods,dob.back_category,dob.back_resource from dt_order_backinfo dob where dob.order_id=?", WorkNumber.get("order_id"));
        for (Record x : RefundList) {
            Record logistics = Db.findFirst("select isnull(de.title,'') title from dt_express_info dei left join dt_express de on dei.express_id=de.id where express_no=?", x.get("back_return_no"));
            if (logistics != null) {
                x.set("back_title", logistics.get("title"));
            } else {
                x.set("back_title", "");
            }
            switch (x.get("back_goods").toString()) {
                case "0":
                    x.set("back_goods", "仅退款");
                    break;
                case "1":
                    x.set("back_goods", "退货退款");
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("RefundList", RefundList);
        return map;
    }

    /**
     * 退款商品详情
     *
     * @param id
     * @return
     */
    public List<Record> SelectRefundShop(int id) {
        List<Record> RefundShopList = Db.find("select dog.img_url,dog.goods_title,dog.spec_text,dog.real_price,dog.quantity,(dog.real_price*dog.quantity) allmoney from dt_order_goods dog where back_order_id=?", id);
        return RefundShopList;
    }

    /**
     * 工单查询sql拼装
     *
     * @param sql
     * @param workOrder
     * @return
     */
    public StringBuffer AcceptanceSQL(StringBuffer sql, WorkOrderParam workOrder) {
        if (!workOrder.getStatus().equals("") && workOrder.getStatus() != null) {
            if (workOrder.getStatus().equals("history")) {
                if (workOrder.getUserDeptId().equals("2")) {
                    sql.append(" and ds.status in (2,3,4,5,6)");
                } else if (workOrder.getUserDeptId().equals("3")) {
                    sql.append(" and ((ds.status <> 3 and ds.type_code like '03_%') or (ds.status <> 3 and ds.type_code like '05_%') or (ds.status in (2,3,4,5,6) and ds.type_code not like '05_%' and ds.type_code not like '03_%'))");
                } else {
                    sql.append(" and ds.status in (2,3,4,5,6) and dsd.exe_dept_id=" + workOrder.getUserDeptId());
                }
            } else if (workOrder.getStatus().equals("already")) {
                sql.append(" and ds.status in (3) and dsd.exe_dept_id=" + workOrder.getUserDeptId());
            } else if (workOrder.getStatus().equals("acceptance")) {
                sql.append(" and ds.status = 1");
            } else if (workOrder.getStatus().equals("push")) {
                sql.append(" and ds.status in (4)");
            }
        }
        if (!workOrder.getSourceCode().equals("") && workOrder.getSourceCode() != null) {
            sql.append(" and ds.source_code like '" + workOrder.getSourceCode() + "_%'");
        }
        if (!workOrder.getTypeCode().equals("") && workOrder.getTypeCode() != null) {
            sql.append(" and ds.type_code like '" + workOrder.getTypeCode() + "_%'");
        }
        if (!workOrder.getSearch().equals("") && workOrder.getSearch() != null) {
            sql.append(" and (ds.sheet_no like '%" + workOrder.getSearch() + "%' or ds.sponsor='" + workOrder.getSearch() + "')");
        }
        if (!workOrder.getStartTime().equals("") && !workOrder.getEndTime().equals("")) {
            sql.append(" and ds.start_time BETWEEN '" + workOrder.getStartTime() + "' and '" + workOrder.getEndTime() + "'");
        } else if (!workOrder.getStartTime().equals("") && workOrder.getEndTime().equals("")) {
            sql.append(" and ds.start_time > '" + workOrder.getStartTime() + "'");
        } else if (workOrder.getStartTime().equals("") && !workOrder.getEndTime().equals("")) {
            sql.append(" and ds.start_time < '" + workOrder.getEndTime() + "'");
        }

        sql.append(" order by ds.start_time desc");
        return sql;
    }

    public Integer detilOrderCount(int id) {
        Integer detilOrderCount = Db.find("select * from dt_order_backinfo where order_id=?", id).size();
        return detilOrderCount;
    }

    @Before(Tx.class)
    public String IsAccept(int tance, int implement, int supervision, int sheetId, String explain, JSONObject user) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String stu = "";
        switch (tance) {
            case 0:
			/*工单详情表*/
                Record sheetDetail = new Record().set("sheet_id", sheetId).set("receiver", user.get("username").toString()).set("exe_dept", WorkerNumberUtil.selectDepartment(implement)).set("exe_dept_id", implement).set("sup_dept", WorkerNumberUtil.selectDepartment(supervision)).set("sup_dept_id", supervision).set("content", "").set("pics", "");
                Db.save("dt_sheet_detail", sheetDetail);
			/*工单流程日志表*/
                Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                        .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                        set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 3).set("result", "处理中")
                        .set("descr", "").set("pics", "").set("is_see", 0));
                Db.update("update dt_sheets set status=3 where id=?", sheetId);
                stu = "SUCCESS";
                break;
            case 1:
			/*工单详情表*/
                Record sheetDetai = new Record().set("sheet_id", sheetId).set("receiver", user.get("username").toString()).set("exe_dept", "").set("exe_dept_id", 0).set("sup_dept", "").set("sup_dept_id", 0).set("content", explain).set("pics", "");
                Db.save("dt_sheet_detail", sheetDetai);
			/*工单流程日志表*/
                Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                        .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                        set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 2).set("result", "不受理")
                        .set("descr", explain).set("pics", "").set("is_see", 0));
                Db.update("update dt_sheets set status=2 where id=?", sheetId);

                Record sheetInfo = Db.findFirst("select * from dt_sheets ds left join dt_sheet_business dsb on ds.id= where id=?", sheetId);

                //修改订单状态
                if (sheetInfo.get("type_code").toString().equals("07_01")) {
                    Db.update("update dt_order_comment set intervention_state=2,intervention_result_state=1,intervention_explain=?,intervention_time=? where order_id=? and is_lock =0",explain,date,sheetInfo.get("order_id").toString());
                }

                stu = "SUCCESS";
                break;
        }
        return stu;
    }

    /**
     * 流程日志信息
     *
     * @param status
     * @return
     */
    public Record processLog(String status, int id) {
        Record record = new Record();
        record = Db.findFirst("select * from dt_sheet_detail  where sheet_id=?", id);
        if (record == null) {
            record = Db.findFirst("select descr content,result,pics from dt_sheet_flowlog  where sheet_id=? order by opr_time desc", id);
        }
        return record;
    }

    public List<Record> selectHandle(int id) {
        return Db.find("select convert(varchar,type) type,value from dt_sheet_punish where sheet_id=?", id);
    }

    /**
     * @param id
     * @return
     */
    public Map<String, Object> technologicalProcess(int id) {
        List<Record> recordList = Db.find("select opr_type,CONVERT(varchar(100),opr_time, 20) opr_time from dt_sheet_flowlog where sheet_id=?", id);
        Record timeRecord = new Record();
        timeRecord.set("acceptance", "");
        timeRecord.set("already", "");
        timeRecord.set("processed", "");
        timeRecord.set("completed", "");
        timeRecord.set("entertained", "");
        for (Record x : recordList) {
            if (Integer.parseInt(x.get("opr_type").toString()) == 1) {
                timeRecord.set("acceptance", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 2) {
                timeRecord.set("entertained", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 3) {
                timeRecord.set("already", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 4) {
                timeRecord.set("processed", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 5) {
                timeRecord.set("processed", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 6) {
                timeRecord.set("completed", x.get("opr_time"));
            }
            if (Integer.parseInt(x.get("opr_type").toString()) == 7) {
                timeRecord.set("solve", x.get("opr_time"));
            }
        }

        Record flowlog = Db.findFirst("select * from dt_sheet_detail where sheet_id=?", id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timeRecord", timeRecord);
        map.put("flowlog", flowlog);
        return map;
    }

    /**
     * 处罚(1)
     *
     * @param path
     * @param reputation
     * @param bond
     * @param compensate
     * @param closedShop
     * @param frozen
     * @param shopId
     * @param explain
     * @param user
     * @param sheetId
     */
    @Before(Tx.class)
    public void handle(String path, String reputation, String bond, String compensate, String closedShop, String frozen, String shopId, String explain, JSONObject user, int sheetId) {

        if (!reputation.equals("false")) {
            storagePunish(sheetId, 1, reputation);
        }
        if (!bond.equals("false")) {
            storagePunish(sheetId, 2, bond);
        }
        if (!compensate.equals("false")) {
            storagePunish(sheetId, 3, compensate);
        }
        if (!closedShop.equals("false")) {
            storagePunish(sheetId, 6, closedShop);
        }
        if (!frozen.equals("false")) {
            storagePunish(sheetId, 4, frozen);
        }
        if (!shopId.equals("false")) {
            shopId = shopId.substring(0, shopId.length() - 1);
            storagePunish(sheetId, 5, shopId);
        }

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	/*工单详情表*/
        Db.update("update dt_sheet_detail set receiver=?,content=?,pics=? where sheet_id=?", user.get("username").toString(), explain, path, sheetId);
		/*工单流程日志表*/
        Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 4).set("result", "等待仲裁")
                .set("descr", explain).set("pics", path).set("is_see", 0));
        Db.update("update dt_sheets set status=4 where id=?", sheetId);

    }

    /**
     * 工单处罚(2)
     *
     * @param path
     * @param explain
     * @param user
     * @param sheetId
     */
    @Before(Tx.class)
    public void handlealso(String path, String evaluate, String explain, JSONObject user, int sheetId, String pingjia) {


        if (!evaluate.equals("false") && !evaluate.equals("")) {
            storagePunish(sheetId, 7, evaluate);
        }

        if (pingjia.equals("true")) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	/*工单详情表*/
            Db.update("update dt_sheet_detail set receiver=?,content=?,pics=? where sheet_id=?", user.get("username").toString(), explain, path, sheetId);
    		/*工单流程日志表*/
            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 4).set("result", "等待仲裁")
                    .set("descr", explain).set("pics", path).set("is_see", 0));
            Db.update("update dt_sheets set status=4 where id=?", sheetId);
        } else if (pingjia.equals("punishFalse")) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	/*工单详情表*/
            Db.update("update dt_sheet_detail set receiver=?,content=?,pics=? where sheet_id=?", user.get("username").toString(), explain, path, sheetId);
    		/*工单流程日志表*/
            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 4).set("result", "等待仲裁")
                    .set("descr", explain).set("pics", path).set("is_see", 1));

            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 5).set("result", "等待推送消息")
                    .set("descr", explain).set("pics", path).set("is_see", 0));
            Db.update("update dt_sheets set status=4 where id=?", sheetId);
        } else {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	/*工单详情表*/
            Db.update("update dt_sheet_detail set receiver=?,content=?,pics=? where sheet_id=?", user.get("username").toString(), explain, path, sheetId);
    		/*工单流程日志表*/
            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheetId).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 5).set("result", "等待推送消息")
                    .set("descr", explain).set("pics", path).set("is_see", 0));
            Db.update("update dt_sheets set status=4 where id=?", sheetId);
        }


    }

    /**
     * dt_sheet_punish（工单处罚表）
     */
    public void storagePunish(int sheetId, int type, String value) {
        Db.save("dt_sheet_punish", new Record().set("sheet_id", sheetId).set("type", type).set("value", value).set("status", 0).set("is_done", 0));
    }

    /**
     * 仲裁
     *
     * @param sheet_id
     */
    @Before(Tx.class)
    public String arbitration(int sheet_id, JSONObject user) {
        String stu = "";

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String code = ArbitrationMessage(String.valueOf(sheet_id));
        if (code.equals("0")) {
    		/*工单流程日志表*/
            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheet_id).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 5).set("result", "等待推送消息")
                    .set("descr", "").set("pics", "").set("is_see", 1));
            stu = "success";
        } else {
            stu = "error";
        }

        return stu;
    }


    /**
     * 推送
     *
     * @param sheet_id
     */
    @Before(Tx.class)
    public String Push(int sheet_id, JSONObject user) {
        String stu = "success";
        Record isId = Db.findFirst("select * from dt_sheets where id=?", sheet_id);
        Record sheetInfo = Db.findFirst("select * from dt_sheets ds left join dt_sheet_business dsb on ds.id= where id=?", sheet_id);
        if (Integer.parseInt(isId.get("sponsor_id").toString()) == 0) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						/*工单流程日志表*/
            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheet_id).set("opr_dept_id", user.get("dept_id")
                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 6).set("result", "已完成")
                    .set("descr", "").set("pics", "").set("is_see", 0));
            Db.update("update dt_sheets set status=5 where id=?", sheet_id);
            stu = "successIsId";
        } else {
            String type = "";
            if (isId.get("sponsor_type").toString().equals("1")) {
                type = "Shop";
            } else if (isId.get("sponsor_type").toString().equals("3")) {
                type = "Buyer";
            }
            Record userRecord = Db.findFirst("select * from dt_user_role where user_id=? and role_type=?", isId.get("sponsor_id"), type);
            String contentInfo = Db.findFirst("select * from dt_sheet_detail where sheet_id=?", sheet_id).get("content");
            Record Onecode = WorkerNumberUtil.getcode(isId.get("type_code").toString());
            //举报人
            Message message = this.pushMessage(userRecord, Onecode.get("title").toString(), WorkerNumberUtil.getcodeTitle(isId.get("type_code").toString()).get("title").toString(), isId.get("sheet_no").toString(), contentInfo);


            if (message.getStatus().equals("1")) {
                if (!sheetInfo.get("type_code").toString().equals("07_01")) {
                    //被举报人
                    Record business = Db.findFirst("select * from dt_sheet_business where sheet_id=?", sheet_id);
                    if (Integer.parseInt(business.get("shop_user_id").toString()) != 0 && !isId.get("sponsor_id").toString().equals("1")) {
                        Record shopRecord = Db.findFirst("select * from dt_user_role where role_type='shop' and user_id=?", business.get("shop_user_id"));
                        Message messageshop = this.pushMessage(shopRecord, Onecode.get("title").toString(), WorkerNumberUtil.getcodeTitle(isId.get("type_code").toString()).get("title").toString(), isId.get("sheet_no").toString(), contentInfo);
                        if (messageshop.getStatus().equals("1")) {
                            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						/*工单流程日志表*/
                            Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheet_id).set("opr_dept_id", user.get("dept_id")
                                    .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                                    set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 6).set("result", "已完成")
                                    .set("descr", "").set("pics", "").set("is_see", 0));
                            Db.update("update dt_sheets set status=5 where id=?", sheet_id);
                        } else {
                            stu = "error";
                        }
                    } else {
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						/*工单流程日志表*/
                        Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheet_id).set("opr_dept_id", user.get("dept_id")
                                .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                                set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 6).set("result", "已完成")
                                .set("descr", "").set("pics", "").set("is_see", 0));
                        Db.update("update dt_sheets set status=5 where id=?", sheet_id);
                    }
                } else {
                    Record orderInfo = Db.findFirst("select * from dt_orders where id=?", sheetInfo.get("order_id").toString());
                    Record shopRecord = Db.findFirst("select * from dt_user_role where role_type='Buyer' and user_id=?", orderInfo.get("user_id").toString());

                    Message messageshop = this.pushMessage(shopRecord, Onecode.get("title").toString(), WorkerNumberUtil.getcodeTitle(isId.get("type_code").toString()).get("title").toString(), isId.get("sheet_no").toString(), contentInfo);
                    if (messageshop.getStatus().equals("1")) {
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						/*工单流程日志表*/
                        Db.save("dt_sheet_flowlog", new Record().set("sheet_id", sheet_id).set("opr_dept_id", user.get("dept_id")
                                .toString()).set("opr_dept", user.get("dept_name").toString()).set("opr_user_id", user.get("user_id").toString()).
                                set("opr_user", user.get("username").toString()).set("opr_time", date).set("opr_type", 6).set("result", "已完成")
                                .set("descr", "").set("pics", "").set("is_see", 0));
                        Db.update("update dt_sheets set status=5 where id=?", sheet_id);

                        int punish=1;
                        Record deInfo = Db.findFirst("select * from dt_sheet_detail where sheet_id=?",sheet_id);//查询处罚结果
                        List<Record> punishInfo = Db.find("select * from dt_sheet_punish where sheet_id=?",sheet_id);//查询处罚结果
                        if(punishInfo.size()>0){
                            punish=2;
                        }
                        //修改订单状态
                        if (sheetInfo.get("type_code").toString().equals("07_01")) {
                            Db.update("update dt_order_comment set intervention_state=2,intervention_result_state=?,intervention_explain=?,intervention_time=? where order_id=? and is_lock =0",punish,deInfo.get("content").toString(),date,sheetInfo.get("order_id").toString());
                        }
                    } else {
                        stu = "error";
                    }
                }
            } else {
                stu = "error";
            }
        }
        return stu;
    }

    /**
     * 推送方法
     *
     * @param user
     * @param OnetypeTitle
     * @param TowtypeTitle
     * @param sheet_no
     * @param contentInfo
     * @return
     */
    public Message pushMessage(Record user, String OnetypeTitle, String TowtypeTitle, String sheet_no, String contentInfo) {
        StringBuffer parameterString = new StringBuffer();
        String noncestr = String.valueOf(System.currentTimeMillis());
        String title = OnetypeTitle + "仲裁结果";
        String content = OnetypeTitle + "编号为" + sheet_no + "的" + TowtypeTitle + "仲裁结果为:" + contentInfo;
        //参数拼接
        parameterString.append(noncestr);
        parameterString.append(user.get("user_id").toString());
        parameterString.append(user.get("role_type").toString());
        parameterString.append(user.get("role_value").toString());
        parameterString.append(title);
        parameterString.append(content);
        parameterString.append("");
        TreeMap<String, String> map = new TreeMap<String, String>();

        String round = DigestUtils.md5Hex(parameterString.toString()).toLowerCase();

        map.put("currentDate", noncestr);
        map.put("isResponseJson", "1");
        map.put("loginType", "Third");
        map.put("roundNumber", round);
        map.put("userId", user.get("user_id").toString());
        map.put("userRoleType", user.get("role_type").toString());
        map.put("userRoleValue", user.get("role_value").toString());
        map.put("title", title);
        map.put("content", content);
        map.put("sendType", "");
        String k = null;
        Message msg = new Message();
        try {
            k = InterfaceUtil.GetAPI(InterfaceUtil.ATURL, map);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(k);
            if ("1".equals(jsonObject.getString("State"))) {
                msg.setStatus("1");
                msg.setResult(jsonObject.getString("Message"));
            } else {
                msg.setStatus("0");
                msg.setResult("推送失败");
            }

        } catch (IOException e) {
            msg.setResult("系统错误");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 仲裁方法
     *
     * @param sheet_id
     * @return
     */
    public String ArbitrationMessage(String sheet_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        String sign = DigestUtils.md5Hex("manjiwang" + time + sheet_id).toUpperCase();
        String k = null;
        try {
            k = InterfaceUtil.PostAPI(InterfaceUtil.ArbiURL, sheet_id, sign);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(k);
            k = jsonObject.getString("ErrorCode");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return k;
    }

    /**
     * 查询是否仲裁
     *
     * @param sheet_id
     * @return
     */
    public String IsArbitration(int sheet_id) {
        String stu = "";
        Record record = Db.findFirst("select * from dt_sheet_flowlog where sheet_id=? and opr_type=5", sheet_id);
        if (record != null) {
            stu = "success";
        }
        return stu;
    }

    /**
     * 评价纠纷top
     *
     * @param sheet_no
     * @return
     */
    public Record evaluateTop(String sheet_no) {
        PCService service = new PCService();
        Record userInfo = service.userInfo(sheet_no);
        Record shopInfo = service.shopInfo(sheet_no);
        Record order_no = service.userShopInfo(sheet_no);
        String order_time = service.getCommentTime(Integer.parseInt(order_no.get("id").toString()));
        Record allInfo = new Record();
        allInfo.set("userName", "");
        allInfo.set("userMobile", "");
        allInfo.set("shopName", "");
        allInfo.set("shopMobile", "");
        allInfo.set("order_no", "");
        if (userInfo != null) {
            allInfo.set("userName", userInfo.get("user_name"));
            allInfo.set("userMobile", userInfo.get("mobile"));
        }
        if (shopInfo != null) {
            allInfo.set("shopName", shopInfo.get("name"));
            allInfo.set("shopMobile", shopInfo.get("mobile"));
        }
        if (order_no != null) {
            allInfo.set("order_no", order_no.get("order_no"));
        }
        allInfo.set("order_id", order_no.get("id"));
        allInfo.set("order_time", order_time);
        return allInfo;
    }

    /**
     * 查询最新推送消息
     *
     * @param opr_dept_id
     * @return
     */
    public List<Record> messageNew(int opr_dept_id) {
        List<Record> list = new ArrayList<Record>();
        list = Db.find("");
        return list;
    }

    /**
     * 改变日志阅读状态
     *
     * @param sheetId
     */
    public void flowState(int sheetId, int status) {
        if (status == 4) {
            status = 5;
        } else if (status == 5) {
            status = 6;
        } else if (status == 6) {
            status = 7;
        }
        Db.update("update dt_sheet_flowlog set is_see=1 where id in (select id from dt_sheet_flowlog where sheet_id=? and is_see<=" + status + ")", sheetId);
    }

    /**
     * 查询新增工单
     *
     * @param exeDeptId
     */
    public List<Record> newSheet(int exeDeptId) {
        List<Record> list = new ArrayList<>();
        if (exeDeptId == 3) {
            list = Db.find("select dsf.sheet_id,convert(varchar(20),ds.status) status,ds.sheet_no,dsd.exe_dept_id from dt_sheet_flowlog dsf left join dt_sheet_detail dsd on dsd.sheet_id=dsf.sheet_id left join dt_sheets ds on ds.id=dsf.sheet_id  where is_see=0 and (opr_type in (1,4,5) or (opr_type=3 and dsd.exe_dept_id=3))");
        } else {
            list = Db.find("select dsf.sheet_id,convert(varchar(20),ds.status) status,ds.sheet_no,dsd.exe_dept_id from dt_sheet_flowlog dsf left join dt_sheet_detail dsd on dsd.sheet_id=dsf.sheet_id left join dt_sheets ds on ds.id=dsf.sheet_id  where is_see=0 and opr_type =3 and exe_dept_id=?", exeDeptId);
        }
        return list;
    }

    /**
     * 统计工单
     *
     * @return
     */
    public Map<String, Object> workStatistics() {
        List<Record> recordOne = Db.find("select status,count(1) sheetCount from dt_sheets where status in (1,3,4) group by status");
        Record sheetStatistics = new Record();
        sheetStatistics.set("acceptance", 0);
        sheetStatistics.set("already", 0);
        sheetStatistics.set("push", 0);
        for (Record x : recordOne) {
            if (Integer.parseInt(x.get("status").toString()) == 1) {
                sheetStatistics.set("acceptance", x.getInt("sheetCount"));
            }
            if (Integer.parseInt(x.get("status").toString()) == 3) {
                sheetStatistics.set("already", x.getInt("sheetCount"));
            }
            if (Integer.parseInt(x.get("status").toString()) == 4) {
                sheetStatistics.set("push", x.getInt("sheetCount"));
            }
        }


        List<Record> recordOneNewTime = Db.find("select status,count(1) sheetCount from dt_sheets where status in (1,3,4) and DateDiff(dd,start_time,getdate())=0 group by status");
        Record sheetStatisticsNewTime = new Record();
        sheetStatisticsNewTime.set("acceptance", 0);
        sheetStatisticsNewTime.set("already", 0);
        sheetStatisticsNewTime.set("push", 0);
        for (Record x : recordOneNewTime) {
            if (Integer.parseInt(x.get("status").toString()) == 1) {
                sheetStatisticsNewTime.set("acceptance", x.getInt("sheetCount"));
            }
            if (Integer.parseInt(x.get("status").toString()) == 3) {
                sheetStatisticsNewTime.set("already", x.getInt("sheetCount"));
            }
            if (Integer.parseInt(x.get("status").toString()) == 4) {
                sheetStatisticsNewTime.set("push", x.getInt("sheetCount"));
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetStatistics", sheetStatistics);
        map.put("sheetStatisticsNewTime", sheetStatisticsNewTime);
        return map;
    }

    /**
     * 根据订单号查询商品id
     *
     * @param order_id
     * @return
     */
    public List<Record> getGoodsInfo(String order_id, String article_id) {
        List<Record> record = new ArrayList<Record>();
        if (!article_id.equals("") && !article_id.equals("0") && article_id != null) {
            record = Db.find("select convert(varchar(20),id) id,title,img_url from dt_article where id =?", article_id);
        } else {
            record = Db.find("select convert(varchar(20),id) id,title,img_url from dt_article where id in (select article_id from dt_order_goods where order_id=? group by article_id)", order_id);
        }
        return record;
    }

    /**
     * 根据单号查商品id
     *
     * @param sheet_id
     * @return
     */
    public Record getBusiness(int sheet_id) {
        return Db.findFirst("select * from dt_sheet_business where sheet_id=?", sheet_id);
    }

    public List<Record> comment(String order_id) {
        List<Record> shopRecord = new ArrayList<Record>();
        List<Record> shopCommentInfo = new ArrayList<Record>();
        shopRecord = Db.find("SELECT * FROM dbo.dt_order_goods WHERE order_id = ?", order_id);
        for (Record x : shopRecord) {
            int i = 1;
            shopCommentInfo = Db.find("SELECT CONVERT(varchar(100), add_time, 20) add_time,isnull(content,'') content,isnull(pics,'') pics FROM dt_article_comment WHERE order_id = ? order by add_time", x.get("id"));
            for (Record xx : shopCommentInfo) {
                xx.set("i", Integer.valueOf(i).intValue());
                i++;
            }
            x.set("shopRecord", shopCommentInfo);
        }
        return shopRecord;
    }

    /**
     * 订单评价
     *
     * @param order_id
     * @return
     */
    public String orderComment(String order_id) {
        return Db.findFirst("select id from dt_order_comment where order_id=?", order_id).get("id").toString();
    }

    /**
     * 查询商家保证金类型
     */
    public List<Record> getBusinessUser(String shop_user_id) {
        return Db.find("select id,name from dt_business_user where user_role_type='shop' and getdate()<valid_time and is_del=0 and user_id=?", shop_user_id);
    }

    /**
     * 导出Excel
     *
     * @param workOrder
     */
    public List<Record> toExcel(WorkOrderParam workOrder) {
        List<Record> list = new ArrayList<Record>();
        if (workOrder.getStatus().equals("supermarket")) {
            StringBuffer sql = new StringBuffer();
            sql.append("from dt_sheets ds left join dt_sheet_type dst on ds.type_code=dst.code and dst.status=1 left join dt_sheet_detail dsd on dsd.sheet_id=ds.id left join  dt_sheet_source dss on dss.code=ds.source_code and dss.status=1 left join dt_sheet_overtime dso on dso.priority_id=ds.priority_level where 1=1 and DATEADD(hour,dso.process_time, ds.start_time) < getdate()");
            sql = AcceptanceSQL(sql, workOrder);
            list = Db.find("select ds.id,ds.sheet_no,ds.start_time,dst.merge_title typeCode,isnull(ds.sponsor,'') sponsor,dss.merge_title sourceCode,ds.status,isnull(dsd.receiver,'') receiver,isnull(dsd.exe_dept,'') exe_dept,isnull(dsd.sup_dept,'') sup_dept " + sql.toString());
        } else {
            StringBuffer sql = new StringBuffer();
            sql.append("from dt_sheets ds left join dt_sheet_type dst on ds.type_code=dst.code and dst.status=1 left join dt_sheet_detail dsd on dsd.sheet_id=ds.id left join  dt_sheet_source dss on dss.code=ds.source_code and dss.status=1 where 1=1");
            sql = AcceptanceSQL(sql, workOrder);
            list = Db.find("select ds.id,ds.sheet_no,ds.start_time,dst.merge_title typeCode,isnull(ds.sponsor,'') sponsor,dss.merge_title sourceCode,ds.status,isnull(dsd.receiver,'') receiver,isnull(dsd.exe_dept,'') exe_dept,isnull(dsd.sup_dept,'') sup_dept " + sql.toString());
        }

        return list;
    }
}
