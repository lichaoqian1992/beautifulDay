package com.manji.sheet.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.utils.WorkerNumberUtil;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by lcq on 2017/6/15.
 */
public class PCService {

	
	private static Logger logger = Logger.getLogger(PCService.class);
	
    /**
     * 根据对应类型获取页面信息
     *
     * @param short_title
     * @param shop_user_id
     * @return
     */

    public Page<Record> getInformation(int pageNum,String short_title, Integer shop_user_id, String start_time, String end_time, Integer status, String sheet_no) {


        StringBuffer sb = new StringBuffer();

        //根据传入类型显示数据
        if ("FEB".equals(short_title)) {
            sb.append(" from dt_sheets s left join dt_sheet_type t  on  s.type_code = t.code " +
                    " where t.short_title='FEB' and s.status in (3,4,5) and t.layer=2 and s.is_push=0 and s.sponsor_id='" + shop_user_id + "'");
        } else if ("RMJ".equals(short_title)) {
            sb.append(" from dt_sheets s left join dt_sheet_type t  on  s.type_code = t.code " +
                    " where t.short_title='RMJ' and s.status in (3,4,5) and t.layer=2 and s.is_push=0 and s.sponsor_id='" + shop_user_id + "'");
        } else if ("RGD".equals(short_title) || "RSH".equals(short_title)) {
            sb.append(" from dt_sheets s " +
                    "left join dt_sheet_business b on s.id = b.sheet_id " +
                    "left join dt_sheet_type t on s.type_code=t.code " +
                    "where t.short_title in ('RGD','RSH') and s.status in (3,4,5,6) and t.layer=2 and s.is_push=0 and b.shop_user_id= '" + shop_user_id + "'");
        } else if ("EVD".equals(short_title)) {
            sb.append(" from dt_sheets s " +
                    "left join dt_sheet_business b on s.id = b.sheet_id " +
                    "left join dt_sheet_type t on s.type_code=t.code " +
                    "where t.short_title = 'EVD' and s.status in (1,2,3,4,5) and t.layer=2 and s.is_push=0 and b.shop_user_id= '" + shop_user_id + "'");
        } else if ("TRD".equals(short_title)) {
            sb.append(" from dt_sheets s " +
                    "left join dt_sheet_business b on s.id = b.sheet_id " +
                    "left join dt_sheet_type t on s.type_code=t.code " +
                    "where t.short_title = 'TRD' and s.status in (3,4,5,6) and t.layer=2 and s.is_push=0 and b.shop_user_id= '" + shop_user_id + "'");
        }

        //根据条件搜索
        if (start_time != null && !"".equals(start_time)) {
            sb.append(" and s.start_time>='" + start_time + "'");
        }
        if (end_time != null && !"".equals(end_time)) {
            sb.append(" and s.start_time<='" + end_time + "'");
        }
        if (status != null && !"".equals(status) && !"3".equals(status.toString())) {
            sb.append(" and s.status='" + status + "'");
        }
        if (status != null && status == 3) {
        	sb.append(" and (s.status= '3' or s.status= '4' )");
        }
        if (sheet_no != null && !"".equals(sheet_no)) {
            sb.append(" and s.sheet_no like '%" + sheet_no + "%'");
        }

        sb.append(" order by s.start_time desc");
        
        Page<Record> page = paging(pageNum,sb.toString(),short_title);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("pageNum", pageNum);
        para.put("short_title", short_title);
        para.put("shop_user_id", shop_user_id);
        para.put("start_time", start_time);
        para.put("end_time", end_time);
        para.put("status", status);
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = page == null ? null : page.toString();
        
        logger.info("调用接口 : getInformation ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return page;
    }

    /**
     * 商家PC展示页分页
     * @param pageNum
     * @param sql
     * @param short_title
     * @return
     */
    public Page<Record> paging(int pageNum,String sql,String short_title) {
    	
    	StringBuffer sb = new StringBuffer();
    	
    	if ("FEB".equals(short_title) || "RMJ".equals(short_title)) {
    		
            sb.append("select  t.title,s.status,s.sheet_no,convert(varchar(19),s.start_time,20) start_time,t.merge_title ");
        } else {
        	
            sb.append("select b.shop_user_id,s.sheet_no,convert(varchar(19),s.start_time,20) start_time,s.sponsor,s.status,t.title,t.merge_title ");
        } 
    	return Db.paginate(pageNum, 10, sb.toString(), sql);
    }


    /**
     * 获取处理状态和时间ok
     *
     * @param sheet_no
     * @return
     */
    public List<Record> dealTime(String sheet_no) {

        String sql = "select convert(varchar(19),opr_time,20) opr_time,result from dt_sheet_flowlog where sheet_id = (select id from dt_sheets where sheet_no =?)  and opr_type<>4 order by opr_time";

        List<Record> records = Db.find(sql,sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        
        logger.info("调用接口 : dealTime ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return records;
     }

    /**
     * 详情中通用的工单信息
     *
     * @param sheet_no
     * @return
     */
    public Record commonSheet(String sheet_no) {

        String sql = "select s.id,s.sheet_no,convert(varchar(19),s.start_time,20) start_time,t.title from  dt_sheets s,dt_sheet_type t where s.type_code=t.code and s.sheet_no='" + sheet_no + "'";
        Record record = Db.findFirst(sql);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        
        logger.info("调用接口 : commonSheet ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }


    /**
     * 产生纠纷的资料信息
     *
     * @param sheet_no
     * @return
     */
    public List<Record> disputeInfo(String sheet_no) {
    	
    		
		String sql = "select s.id,s.sheet_no,i.descr,i.pics,convert(varchar(19),i.add_time,20) add_time,i.submitter from dt_sheets s,dt_sheet_info i " +
				"where s.id=i.sheet_id and s.sheet_no=? order by i.add_time desc";

        List<Record> records = Db.find(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        
        logger.info("调用接口 : disputeInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return records;
    }

    /**
     * 添加补充资料
     *
     * @param record
     */
    public boolean addInformation(Record record) {

    	boolean flag = Db.save("dt_sheet_info", record);
		Map<String,Object> para = new HashMap<String,Object>();
	    para.put("record", record);
	    JSONObject obj = JSONObject.fromObject(para);
	     
	    logger.info("调用接口 : addInformation ;"+"参数 : "+obj.toString()+"; 返回值  ："+ flag);
	    
        return flag;
    }

    /**
     * 提交建议和举报
     * @param record
     */
    public void addReport(Record record) {

        Db.save("dt_sheet_info",record);
        Db.save("dt_sheets",record);

    }

    /**
     * 仲裁结果
     *
     * @param sheet_no
     * @return
     */
    public Record arbResult(String sheet_no) {

        String sql = "select top 1  s.id,s.sheet_no,d.content,d.pics,f.result,f.opr_time from dt_sheets s,dt_sheet_detail d,dt_sheet_flowlog f "+
                "where s.id=d.sheet_id and s.id=f.sheet_id and s.sheet_no=? order by f.opr_time desc";

        Record record = Db.findFirst(sql, sheet_no);

        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : arbResult ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);
        
        return record;
    }

    /**
     * 处罚结果
     *
     * @param sheet_no
     * @return
     */
    public List<Record> punish(String sheet_no) {

        String sql = "select p.* from dt_sheets s,dt_sheet_punish p where s.id=p.sheet_id and s.sheet_no=?";

        List<Record> records = Db.find(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : punish ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return records;
    }

    /**
     * 根据单号查询商品名
     *
     * @param sheet_no
     * @return
     */
    public Record article(String sheet_no) {

        String sql = "select id,category_id,title,img_url from dt_article where id=(select article_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))";

        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        
        logger.info("调用接口 : article ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 查询商品规格信息
     * @param article_id
     * @return
     */
    public List<Record> goodsSpec (int article_id ) {

        String sql = "select * from dt_article_goods where article_id=?";

        List<Record> records = Db.find(sql,article_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("article_id", article_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : goodsSpec ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return records;
    }

    /**
     * 运输方式
     * @param article_id
     * @return
     */
    public Record tranInfo (int article_id) {

        String sql = "select goods_expenses from dt_article_info where article_id=?";

        Record record = Db.findFirst(sql,article_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("article_id", article_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : tranInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 商家自送
     * @param shop_id
     * @return
     */
    public Record shopZiSong (int shop_id) {

        String sql = "select id,area,convert(varchar(19),update_time,20) update_time,shop_id,remark,free_price,convert(varchar(19),add_time,20) add_time,type,is_free,city from dt_shop_zisong where shop_id=?";

        Record record = Db.findFirst(sql,shop_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("shop_id", shop_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : shopZiSong ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 查询城市
     * @param city
     * @return
     */
    public String getCity (int city) {

        String sql = "select name from dt_apo_city where code=?";

        String record = Db.queryStr(sql,city);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("city", city);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : getCity ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 查询区县
     * @param county
     * @return
     */
    public String getCounty (int county) {

        String sql = "select name from dt_apo_county where code=?";

        String record = Db.queryStr(sql,county);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("county", county);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : getCounty ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 物流模板信息
     * @param shop_id
     * @return
     */
    public Record logisticsInfo (int shop_id) {

        String sql = "select w.name,e.title,e.logo from dt_shop_wuliumuban w,dt_express e where w.expenses_companyId=e.id and w.shop_id=?";

        Record record = Db.findFirst(sql,shop_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("shop_id", shop_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : logisticsInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }

    /**
     * 自定义分类
     * @param article_id
     * @return
     */
    public String category(int article_id) {

        String sql = "select title from dt_article_category_user  where id = (select user_category_id from dt_article where id=?)";

        String title = Db.queryStr(sql,article_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("article_id", article_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        logger.info("调用接口 : category ;"+"参数 : "+obj.toString()+"; 返回值  ："+title);

        return title;
    }

    /**
     * 商品卖点，品牌和属性
     * @param article_id
     * @return
     */
    public Record shopGoods(int article_id) {

        String sql = "select b.name,i.sub_title,i.goods_describe,i.goods_expenses from dt_article_info i left join dt_brand b on i.brand=b.id and i.article_id=?";

        Record record = Db.findFirst(sql,article_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("article_id", article_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : shopGoods ;"+"参数 : "+obj.toString()+"; 返回值  ："+data);

        return record;
    }
    /**
     * 根据分类id查询分类结构
     *
     * @param category_id
     * @return
     */
    public String getList(int category_id) {

        String sql = "select class_list from dt_article_category where id=?";

        String getList = Db.queryStr(sql, category_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("category_id", category_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        logger.info("调用接口 : getList ;"+"参数 : "+obj.toString()+"; 返回值  ："+ getList);
        
        return getList;
    }

    public List<Record> articleCategory(String classList) {

        String sql = "select class_layer,title from dt_article_category where id in " +classList;

        List<Record> records = Db.find(sql);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("classList", classList);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : articleCategory ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);
        
        return records;
    }

    /**
     * 我被举报列表
     *
     * @param shop_user_id
     * @return
     */
    public List<Record> reported(long shop_user_id, String start_time, String end_time, Integer status, String sheet_no) {

        StringBuffer sb = new StringBuffer("select b.shop_user_id,s.sheet_no,convert(varchar(19),s.start_time,20) start_time,s.sponsor,s.status,t.title,t.merge_title from dt_sheet_business b " +
                "left join dt_sheets s on b.sheet_id=s.id " +
                "left join dt_sheet_type t on s.type_code=t.code " +
                "where t.layer=2 and b.shop_user_id=' " + shop_user_id + "'");
        if (start_time != null && !"".equals(start_time)) {
            sb.append(" and s.start_time>'" + start_time + "'");
        }
        if (end_time != null && !"".equals(end_time)) {
            sb.append(" and s.start_time<'" + end_time + "'");
        }
        if (status != null && !"".equals(status)) {
            sb.append(" and s.status='" + status + "'");
        }
        if (sheet_no != null && !"".equals(sheet_no)) {
            sb.append(" and s.sheet_no like '%" + sheet_no + "%'");
        }
        
        List<Record> records = Db.find(sb.toString());
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("shop_user_id", shop_user_id);
        para.put("start_time", start_time);
        para.put("end_time", end_time);
        para.put("status", status);
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : reported ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return records;
    }

    /**
     * 根据sheet_no查询买家详细信息
     *
     * @param sheet_no
     * @return
     */
    public Record buyerInfo(String sheet_no) {

        String sql = "select o.order_no,convert(varchar(19),o.add_time,20) add_time,isnull(u.nick_name,'暂未填写') nick_name,isnull(u.mobile,'暂未填写') as user_mobile,g.accept_name,isnull(g.mobile,'暂未填写') as accept_mobile,isnull(g.post_code,'暂未填写') post_code,isnull(g.address,'暂未填写') address,isnull(o.invoice_title,'暂未填写') invoice_title,isnull(o.message,'暂未填写') message from dt_orders o " +
                "left join dt_users u on o.user_id=u.id " +
                "left join dt_order_goodinfo g on o.id=g.order_id " +
                "where o.id=(select order_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))";

        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : buyerInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 根据sheet_no查询商品总价，运费，优惠和订单总金额
     *
     * @param sheet_no
     * @return
     */
    public Record orderAmount(String sheet_no) {

        String sql = "select isnull(express_fee,0) express_fee,voucher,order_amount from dt_orders " +
                "where id=(select order_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))";

        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : orderAmount ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 查询退单信息
     * @param sheet_no
     * @return
     */
    public List<Record> backInfo(String sheet_no) {

    	
    	
    	String sql = "select g.order_id,g.img_url,g.goods_title,g.spec_text,g.real_price,quantity,g.real_price*quantity as all_money,"
    			+ "b.id,b.order_no,b.order_goods_id,b.back_return_no,b.real_back_amount,b.back_goods,back_category,b.back_resource "
    			+ "from dt_order_goods g,dt_order_backinfo b where g.order_id=b.order_id and g.id=b.order_goods_id and "
    			+ "g.order_id=(select order_id from dt_sheet_business where sheet_id=(select id from dt_sheets where sheet_no=?))";

        List<Record> records = Db.find(sql,sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : backInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return records;
    }

    /**
     * 查询商品规格等信息
     *
     * @param sheet_no
     * @return
     */
    public List<Record> specInfo(String sheet_no) {

        String sql = "select order_id,img_url,goods_title,spec_text,real_price,quantity,real_price*quantity as all_money from dt_order_goods where order_id=(select order_id from dt_sheet_business " +
                "where sheet_id=(select id from dt_sheets where sheet_no=?))";

        List<Record> records = Db.find(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : specInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return records;
    }

    /**
     * 查询发货信息
     *
     * @param sheet_no
     * @return
     */
    public Record shipAddress(String sheet_no) {

        String sql = "select name,telephone,address,code from dt_shop_expensesInfo " +
                "where shop_id = " +
                "(select shop_id from dt_sheet_business where sheet_id=" +
                "(select id from dt_sheets where sheet_no=?))";

        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : shipAddress ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 查询物流信息
     *
     * @param sheet_no
     * @return
     */
    public Record logisticsInfo(String sheet_no) {

        String sql = "select  g.express_no,isnull(e.title,'') title,convert(varchar(19),g.express_time,20) express_time  from  dt_order_goodinfo g,dt_express e where g.express_id=e.id and g.order_id = " +
                "(select order_id from dt_sheet_business where sheet_id =(select id from dt_sheets where sheet_no=?))";

        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : logisticsInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 根据订单id查询订单号，评价时间，买家昵称
     * @param order_id
     * @return
     */
    public Record ordersInfo (Integer order_id) {

        String sql = "select top 1 o.order_no,u.user_name,convert(varchar(19),c.add_time,20) add_time from dt_orders o " +
                "left join dt_users u on o.user_id=u.id left join dt_order_comment c on o.id=c.order_id where o.id=? order by c.add_time desc";

        Record record = Db.findFirst(sql,order_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("order_id", order_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : ordersInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    //根据sheet_no查询出用户id和商家id
    public Record userShopInfo(String sheet_no) {

        String sql = "select id,order_no,user_id,shop_user_id from dt_orders where id= " +
                "(select order_id from dt_sheet_business where sheet_id = " +
                "(select id from dt_sheets where sheet_no = ?))";
        
        Record record = Db.findFirst(sql, sheet_no);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : userShopInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 根据传入的sheet_no查出用户的昵称和电话
     *
     * @param sheet_no
     * @return
     */
    public Record userInfo(String sheet_no) {

        Record rec = userShopInfo(sheet_no);
        String sql = "select user_name,mobile from dt_users where id=? ";
        
        Record record = Db.findFirst(sql, rec.get("user_id"));
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : userInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 根据传入的sheet_no查出商家的名称和电话
     *
     * @param sheet_no
     * @return
     */
    public Record shopInfo(String sheet_no) {

        Record rec = userShopInfo(sheet_no);
        String sql = "select name,mobile from dt_user_role_shopinfo where user_id= ?";
        
        Record record = Db.findFirst(sql, rec.get("shop_user_id"));
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : shopInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 店铺评分
     * @param order_id
     * @return
     */
    public Record shopCommentScore(Integer order_id) {

        String sql = "select top 1 order_id,service_review_score,pack_review_score,distribution_review_score  from dt_order_comment where order_id=? order by add_time desc";

        Record record = Db.findFirst(sql,order_id);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("order_id", order_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : shopCommentScore ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return record;
    }

    /**
     * 查询订单评论时间
     * @param order_id
     * @return
     */
   public String getCommentTime(Integer order_id){

	   String add_time = Db.findFirst("select CONVERT(varchar(100), add_time, 20) add_time from dt_order_comment where order_id=?",order_id).get("add_time").toString();
	   
	   return add_time;
   }

    /**
     * 根据订单号查询商品评分信息
     * @param order_id
     * @return
     */
    public List<Record> comment(String order_id){
        List<Record> shopRecord=new ArrayList<Record>();
        List<Record> shopCommentInfo=new ArrayList<Record>();
        shopRecord=Db.find("SELECT * FROM dbo.dt_order_goods WHERE order_id = ?",order_id);
        for(Record x : shopRecord){
            int i=1;
            shopCommentInfo=Db.find("SELECT * FROM dt_article_comment WHERE order_id = ? order by add_time",x.get("id"));
            for(Record xx : shopCommentInfo){
                xx.set("i",Integer.valueOf(i).intValue());
                i++;
            }
            x.set("shopRecord", shopCommentInfo);
        }

        Map<String,Object> para = new HashMap<String,Object>();
        para.put("order_id", order_id);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = shopRecord == null ? null : shopRecord.toString();
        logger.info("调用接口 : comment ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);
        
        return shopRecord;
    }

    /**
     * 添加举报
     * @param sheetInfo
     * @return
     */
    public String saveShopReport(SheetInfoParam sheetInfo){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stu="";
        try{
		/*工单主表*/
            Record sheet = new Record();
            //WorkerNumberUtil.getcodeReportNo(sheetInfo.getCodeReport()).get(0).get("title");
            sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(sheetInfo.getReportTitle()));
            sheet.set("status",3);
            sheet.set("type_code",sheetInfo.getReportType());
            sheet.set("sponsor_id",sheetInfo.getReportUserId());
            sheet.set("sponsor",sheetInfo.getReportUser());
            sheet.set("sponsor_type",1);
            sheet.set("sponsor_contact",sheetInfo.getSponsor_contact());
            sheet.set("start_time",sdf.format(new Date()));
            sheet.set("source_code","02_01");
            sheet.set("priority_level",1);
            sheet.set("is_push",0);
            Db.save("dt_sheets",sheet);

		/*保存工单资料表*/
            Record sheetData = new Record();
            sheetData.set("sheet_id",sheet.get("id"));
            sheetData.set("descr",sheetInfo.getDescr());
            sheetData.set("pics",sheetInfo.getPics());
            sheetData.set("add_time",sdf1.format(new Date()));
            sheetData.set("submitter",sheetInfo.getReportUser());
            sheetData.set("submitter_id",sheetInfo.getReportUserId());
            sheetData.set("submitter_type","Shop");
            Db.save("dt_sheet_info",sheetData);

		/*工单业务关系表*/
            Record sheetRelation = new Record();
            sheetRelation.set("sheet_id",sheet.get("id"));
            sheetRelation.set("order_id",0);
            sheetRelation.set("back_order_id",0);
            sheetRelation.set("shop_user_id",0);
            sheetRelation.set("shop_id",0);
            sheetRelation.set("article_id",0);
            Db.save("dt_sheet_business",sheetRelation);

		/*工单流程记录*/

            Record flowLog = new Record();
            flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf.format(new Date())).set("opr_type", 3).set("result", "处理中").set("descr", "商家提交举报单").set("is_see", 0);
            Db.save("dt_sheet_flowlog",flowLog);
        
        
        /*监察部门*/   
            
            Record sheetDetail = new Record();
			sheetDetail.set("sheet_id",sheet.get("id"));
			sheetDetail.set("receiver","");
			sheetDetail.set("exe_dept_id",2);
			sheetDetail.set("exe_dept","监察部");
			sheetDetail.set("sup_dept","监察部");
			sheetDetail.set("sup_dept_id",2);
			Db.save("dt_sheet_detail",sheetDetail);
			
            stu = "SUCCESS";
        
	        Map<String,Object> para = new HashMap<String,Object>();
	        para.put("sheet", sheet);
	        para.put("sheetData", sheetData);
	        para.put("sheetRelation", sheetRelation);
	        JSONObject obj = JSONObject.fromObject(para);
	        
	        logger.info("调用接口 : saveShopReport ;"+"参数 : "+obj.toString()+"; 返回值  ："+ stu);
        
        }catch(Exception e){
        	stu = "ERROR";
        	e.printStackTrace();
        }
        
        return stu;
    }

    /**
     * 添加建议
     * @param sheetInfo
     * @return
     */
    public String saveShopSuggestion(SheetInfoParam sheetInfo){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stu="";
        try{
		/*工单主表*/
            Record sheet = new Record();

            sheet.set("sheet_no", WorkerNumberUtil.GeneratWorkerNumber(sheetInfo.getReportTitle()));
            sheet.set("status",3);
            sheet.set("type_code",sheetInfo.getReportType());
            sheet.set("sponsor_id",sheetInfo.getReportUserId());
            sheet.set("sponsor",sheetInfo.getReportUser());
            sheet.set("sponsor_type",1);
            sheet.set("sponsor_contact",sheetInfo.getSponsor_contact());
            sheet.set("start_time",sdf.format(new Date()));
            sheet.set("source_code","02_02");
            sheet.set("priority_level",1);
            sheet.set("is_push",0);
            Db.save("dt_sheets",sheet);

		/*保存工单资料表*/
            Record sheetData = new Record();
            sheetData.set("sheet_id",sheet.get("id"));
            sheetData.set("descr",sheetInfo.getDescr());
            sheetData.set("pics",sheetInfo.getPics());
            sheetData.set("add_time",sdf1.format(new Date()));
            sheetData.set("submitter",sheetInfo.getReportUser());
            sheetData.set("submitter_id",sheetInfo.getReportUserId());
            sheetData.set("submitter_type","Shop");
            Db.save("dt_sheet_info",sheetData);

        /*工单业务关系表*/
            Record sheetRelation = new Record();
            sheetRelation.set("sheet_id",sheet.get("id"));
            sheetRelation.set("order_id",0);
            sheetRelation.set("back_order_id",0);
            sheetRelation.set("shop_user_id",0);
            sheetRelation.set("shop_id",0);
            sheetRelation.set("article_id",0);
            Db.save("dt_sheet_business",sheetRelation);

		/*工单流程记录*/

            Record flowLog = new Record();
            flowLog.set("sheet_id", sheet.get("id")).set("opr_time", sdf.format(new Date())).set("opr_type", 3).set("result", "处理中").set("descr", "商家提交建议单");
            Db.save("dt_sheet_flowlog",flowLog);
            
        /*审核部门*/    
            
            Record sheetDetail = new Record();
			sheetDetail.set("sheet_id",sheet.get("id"));
			sheetDetail.set("receiver","");
			sheetDetail.set("exe_dept_id",1);
			sheetDetail.set("exe_dept","计划部");
			sheetDetail.set("sup_dept","监察部");
			sheetDetail.set("sup_dept_id",2);
			Db.save("dt_sheet_detail",sheetDetail);
			
            stu = "SUCCESS";
            
            Map<String,Object> para = new HashMap<String,Object>();
	        para.put("sheet", sheet);
	        para.put("sheetData", sheetData);
	        para.put("sheetRelation", sheetRelation);
	        JSONObject obj = JSONObject.fromObject(para);
	        
	        logger.info("调用接口 : saveShopSuggestion ;"+"参数 : "+obj.toString()+"; 返回值  ："+ stu);
            
        }catch(Exception e){
            stu = "ERROR";
            e.printStackTrace();
        }
        return stu;
    }


    /**
     * 获取建议或举报的类型
     * @param short_title
     */
    public List<Record> getSheetType(String short_title) {

        String sql = "select id,code,title,short_title from dt_sheet_type where layer=2 and short_title=?";

        List<Record> records = Db.find(sql,short_title);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("short_title", short_title);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : getSheetType ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return records;
    }

    /**
     * 消息通知
     * @param shop_id
     * @return
     */
    public Page<Record> getMessageInfo(Integer shop_id,int pageNum) {

        String sql = " from dt_user_message where  title like '%仲裁结果%' and accept_user_role_type='Shop'  and accept_user_id="+shop_id;

        Page<Record> records = msgPaging(pageNum,sql);
        
        Map<String,Object> para = new HashMap<String,Object>();
        para.put("shop_id", shop_id);
        para.put("pageNum", pageNum);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = records == null ? null : records.toString();
        logger.info("调用接口 : getMessageInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);

        return  records;
    }
    
    /**
     * 消息通知分页
     * @param pageNum
     * @param sql
     * @return
     */
    public Page<Record> msgPaging(int pageNum,String sql) {
    	
    	
    	return Db.paginate(pageNum, 10, "select id,post_user_id,accept_user_id,title,content,convert(varchar(19),post_time,20) post_time,picUrl ", sql);
    }

    

    /**
     * 删除消息通知
     * @param id
     * @return
     */
    public boolean delMessageInfo(Integer id) {

    	boolean flag = Db.deleteById("dt_user_message",id);
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("id", id);
        JSONObject obj = JSONObject.fromObject(para);
        
        logger.info("调用接口 : delMessageInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ flag);

    	
        return flag;
    }

    /**
     * 根据工单号查询对应状态
     * @param sheet_no
     * @return
     */
    public Short getStatus(String sheet_no){
    	
    	Short status = Db.queryShort("select status from dt_sheets where sheet_no='"+sheet_no+"'");
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        logger.info("调用接口 : getStatus ;"+"参数 : "+obj.toString()+"; 返回值  ："+ status);
    	
    	return status;
    }
    
    /**
     * 根据工单号查询
     * @param sheet_no
     * @return
     */
    public String getPunish(String sheet_no){
    	
    	String sql = "select value from dt_sheet_punish where sheet_id=(select id from dt_sheets where sheet_no=?)";
    	
    	String result = Db.queryStr(sql,sheet_no);
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("sheet_no", sheet_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        logger.info("调用接口 : getPunish ;"+"参数 : "+obj.toString()+"; 返回值  ："+ result);
    	
    	return result;
    	
    }
    
    /**
     * 根据订单号查询工单号和工单id，工单状态
     * @param order_id
     * @return
     */
    public Record getSheetStatus(int order_id){
    	
    	String sql = "select ds.id,ds.sheet_no,ds.status from  dt_sheets ds left join dt_sheet_business dsb  on  ds.id=dsb.sheet_id and ds.type_code='07_01' where dsb.order_id=?";
    	
    	Record record = Db.findFirst(sql,order_id);
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("order_id", order_id);
        JSONObject obj = JSONObject.fromObject(para);
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : getSheetStatus ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);
    	
    	return record;
    }
    

    /**
     * 查看物流信息
     * @param express_no
     * @return
     */
    public Record getLogInfo(String express_no){
    	
    	String sql = "select express_no,remark,status from dt_express_info where express_no=?";
    	
    	Record record = Db.findFirst(sql,express_no);
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("express_no", express_no);
        JSONObject obj = JSONObject.fromObject(para);
        
        String data = record == null ? null : record.toString();
        logger.info("调用接口 : getLogInfo ;"+"参数 : "+obj.toString()+"; 返回值  ："+ data);
    	
    	return record;
    	
    }
    
    /**
     * 根据user_id查看店铺名
     * @param user_id
     * @return
     */
    public String getShopName(String user_id) {
    	
    	String shop_name = Db.queryStr("select name from dt_user_role_shopinfo where user_id=?",user_id);
    	
    	Map<String,Object> para = new HashMap<String,Object>();
        para.put("user_id", user_id);
        JSONObject obj = JSONObject.fromObject(para);
        
    	logger.info("调用接口 : getShopName ;"+"参数 : "+obj.toString()+"; 返回值  ："+ shop_name);
    	
    	return shop_name;
    }
    
    
    
}