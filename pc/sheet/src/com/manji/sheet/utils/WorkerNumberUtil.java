package com.manji.sheet.utils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/12.
 */
public class WorkerNumberUtil {
    /**
     * 生成订单号
     * @param jobType
     * @return
     */
    public static String GeneratWorkerNumber(String jobType){
        //创建单号(StringBuffer)
        StringBuffer workerNum=new StringBuffer();
        workerNum.append(jobType);

        //生成时间(拼接)
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        workerNum.append(sdf.format(new Date()));

        //随机生成四位数(拼接)
        int randomNumber;//定义两变量
        Random ne=new Random();//实例化一个random的对象ne
        randomNumber=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999

        workerNum.append(randomNumber);
        return workerNum.toString();
    }

    /**
     * 根据来源code获取来源信息
     * @param sheetFromCode
     * @return
     */
    public static Record sheetFrom(String sheetFromCode){
        StringBuffer sql=new StringBuffer("select id,merge_title from dt_sheet_source where");
        sql.append(" code="+sheetFromCode);
        return Db.findFirst(sql.toString());
    }

    /**
     * 根据类型code获取二级类型信息
     * @param codeReport
     * @return
     */
    public static List<Record> getcodeReport(String codeReport){
        StringBuffer sql=new StringBuffer("select *,convert(varchar(10),status) status from dt_sheet_type where");
        sql.append(" code like '"+codeReport+"_%'");
        return Db.find(sql.toString());
    }
    /**
     * 查询未禁用的分类信息
     * @param codeReport
     * @return
     */
    public static List<Record> getcodeReportNo(String codeReport){
        StringBuffer sql=new StringBuffer("select *,convert(varchar(10),status) status from dt_sheet_type where");
        sql.append(" code like '"+codeReport+"_%' and status=1");
        return Db.find(sql.toString());
    }
    /**
     * 根据code二级类型获取一级类型
     * @param codeReport
     * @return
     */
    public static Record getcode(String codeReport){
        codeReport=codeReport.substring(0,2);
        Record getcode=Db.findFirst("select * from dt_sheet_type where code=?",codeReport);
        return getcode;
    }
    
    public static String selectDepartment(int id){
        Record getcode=Db.findFirst("select * from dt_base_dept where id=?",id);
        return getcode.get("name");
    }
    
    /**
     * 根据code获取名字
     * @param codeReport
     * @return
     */
    public static Record getcodeTitle(String codeReport){
        Record getcode=Db.findFirst("select * from dt_sheet_type where code=?",codeReport);
        return getcode;
    }
    
}
