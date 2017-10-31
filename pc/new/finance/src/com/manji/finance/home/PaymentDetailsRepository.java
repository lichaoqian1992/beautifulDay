package com.manji.finance.home;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.utils.DButils;
import com.manji.finance.home.enums.AccountStateEnums;
import com.manji.finance.utils.InterfaceUtil;
import net.sf.json.JSONObject;

/**
 * 首页支付详情
 * @author Administrator
 *
 */
public class PaymentDetailsRepository extends DButils{
	
	/*售后退款*/
	public Page<Record> refoundDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer("from dt_order_backinfo b inner join dt_orders o on o.id=b.order_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1  left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo g on g.user_id=o.shop_user_id and g.user_id<>0 and g.is_del=0 where b.status=3");
		return sqlserver.paginate(pageNumber, pageSize, "select b.id,p.payment_name title,b.update_time,b.back_return_no,o.order_no,o.order_title,g.name gname,u.user_name uname,b.back_goods_amount alltk,b.real_back_amount tkxianjin,b.voucher tuikuandj", refoundDetailAssembling(scree,sql).toString());
	}

	/*满意卷支出*/
	public Page<Record> voucherDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer("from dt_orders a left join dt_users u on u.id=a.user_id left join  dt_user_role_shopinfo g on g.user_id=a.shop_user_id and g.user_id<>0 and g.is_del=0 left join dt_order_online_pay p on cast(a.id as nvarchar) = p.order_id_list and p.status=1 where a.status=3");
		return sqlserver.paginate(pageNumber, pageSize, "select a.id,u.user_name uname,g.name gname,a.complete_time,a.order_no,a.order_title,p.payment_name title,a.order_amount,a.voucher,a.real_amount", voucherAssembling(scree,sql).toString());
	}
	
	/*金融手续费*/
	public Page<Record> financeDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer("from dt_user_withdrawals a left join dt_users u on a.user_id=u.id where a.status=5");
		return sqlserver.paginate(pageNumber, pageSize, "select a.id,a.complete_time,a.withdrawals_no,u.user_name nick_name,a.bank_user,a.bank_name,a.bank_card,a.total_money,a.commission,(a.total_money-a.commission) arrival", financeAssembling(scree,sql).toString());
	}
	
	/*用户账户余额*/
	public Page<Record> userDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer("from dt_users u left join dt_user_accountinfo a on a.user_id=u.id left join dt_user_personinfo p on p.user_id=u.id Group By a.amount,a.user_id,a.role_type,a.allow_amount,p.person_name,a.state,u.user_name  HAVING a.role_type='Buyer'");
		
		Page<Record> page=sqlserver.paginate(pageNumber, pageSize, "select a.user_id,a.amount,a.role_type,a.allow_amount,p.person_name,a.state,u.user_name ", userAssembling(scree,sql).toString());
		for(Record x : page.getList()){
			String msgByCode = AccountStateEnums.getByCode(x.get("state").toString()).getMessage();
			x.set("state",msgByCode);
		}
		return page;
	}
	
	/*商家账户余额*/
	public Page<Record> shopDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer("from dt_users u left join dt_user_accountinfo a on a.user_id=u.id left join dt_user_role_shopinfo p on p.user_id=u.id left join dt_article_category ac on ac.id=p.main_business Group By a.amount,a.user_id,a.role_type,a.allow_amount,a.state,u.user_name,p.name,p.mobile,ac.title,p.area HAVING a.role_type='shop'");
		
		Page<Record> page=sqlserver.paginate(pageNumber, pageSize, "select a.user_id,a.amount,a.role_type,a.allow_amount,a.state,u.user_name,p.name,p.mobile,ac.title TAG,p.area", shopAssembling(scree,sql).toString());
		for(Record x : page.getList()){
			String msgByCode = AccountStateEnums.getByCode(x.get("state").toString()).getMessage();
			x.set("state",msgByCode);
		}
		return page;
	}
	
	/*微信*/
	public Page<Record> weixinDetail(int pageNumber, int pageSize,ScreenDo scree) {
		String payment="8,12";
		List<String> stringList=selectOrder(payment,scree);
		Page<Record> page=new Page<Record>();
		page=selectOrderxiangxi(stringList,pageNumber,pageSize,scree);
        return page;
	}
	
	/*支付宝*/
	public Page<Record> zhifubaoDetail(int pageNumber, int pageSize,ScreenDo scree) {
		String payment="13";
		List<String> stringList=selectOrder(payment,scree);
		Page<Record> page=new Page<Record>();
		page=selectOrderxiangxi(stringList,pageNumber,pageSize,scree);
        return page;
	}
	
	/*银行卡*/
	public Page<Record> cardDetail(int pageNumber, int pageSize,ScreenDo scree) {
		String payment="11";
		List<String> stringList=selectOrder(payment,scree);
		Page<Record> page=new Page<Record>();
		page=selectOrderxiangxi(stringList,pageNumber,pageSize,scree);
        return page;
	}
	
	/*技术服务费汇总*/
	public Page<Record> techServiceDetail(int pageNumber, int pageSize,ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index and b.user_role_type='shop' and b.status=2 and b.is_del=0 and b.ywkg=1 left join dt_users u on u.id=o.user_id left join dt_users g on g.id=o.shop_user_id left join dt_user_role_shopinfo s on s.user_id=o.shop_user_id and o.user_id<>0 and o.is_del=0 left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_article_category ac on ac.id=rs.main_business left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1 where o.status=3 ");
		return sqlserver.paginate(pageNumber, pageSize, "select isnull(p.payment_name,'') title,o.order_title,o.id,o.complete_time,o.order_no,g.user_name gname,s.name gnick,ac.title TAG,u.user_name uname,o.real_amount,o.voucher,o.order_amount, cast(cast(b.user_percentage*100 as numeric(10,2)) as  varchar(50))+'%' user_percentage,cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2)) technical,(real_amount-cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2))) real_shop_money", techServiceDetailAssembling(scree,sql).toString());
	}
	
	/*资金流水明细*/
	public Page<Record> flowDetails(int pageNumber, int pageSize,ScreenDo scree,int userId,String type) {
		StringBuffer sql=new StringBuffer("from dt_user_amount_log where user_id=? and user_role_type=?");
		return sqlserver.paginate(pageNumber, pageSize, "select *",flowAssembling(scree,sql).toString(),userId,type);
	}
	
	/*资金流水明细总页数*/
	public Integer flowDetailCount(ScreenDo scree,int userId,String type) {
		StringBuffer sql=new StringBuffer("select count(1) from dt_user_amount_log where user_id=? and user_role_type=?");
		return sqlserver.queryFirst(flowAssembling(scree,sql).toString(),userId,type);
	}
	
	/*余额支出汇总*/
	public Map<String, Object> amountDetail(int pageNumber, int pageSize,ScreenDo scree) {
		String sql="select transaction_money,order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%'";
		List<Record> list=sqlserver.find(sql);
		String sql1="select real_amount,id from dt_orders o where o.id in (select order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%')";
		List<Record> list1=sqlserver.find(sql1);
		
		List<ScreenDo> listall=new ArrayList<ScreenDo>();
		
		for(Record x : list){
			for(Record g : list1){
				if((x.get("order_id_list")).toString().equals((g.get("id")).toString()) && Double.parseDouble((x.get("transaction_money")).toString())!=Double.parseDouble((g.get("real_amount")).toString())){
					ScreenDo sc=new ScreenDo();
					sc.setOrderNo((x.get("order_id_list")).toString());
					listall.add(sc);
				}
			}
		}
		String sql2="select id from dt_orders where id not in (select order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%') and status=3 ";
		
		List<Record> list2=sqlserver.find(sql2);
		for(Record x : list2){
			ScreenDo sc=new ScreenDo();
			sc.setOrderNo((x.get("id")).toString());
			listall.add(sc);
		}
		
		StringBuffer sql3=new StringBuffer("from dt_orders a left join dt_users u on u.id=a.user_id left join dt_user_role_shopinfo g on g.user_id=a.shop_user_id and g.user_id<>0 and g.is_del=0 left join dt_payment p on p.id=a.payment_id where 1=1");
		
		sql3.append(" and a.id in (");
		int listsum=1;
        for(ScreenDo s: listall){
            if(listsum==listall.size()){
            	sql3.append(s.getOrderNo()+")");
            }else{
            	sql3.append(s.getOrderNo()+",");
            }
            listsum++;
        }
        
        Page<Record> listPage=sqlserver.paginate(pageNumber, pageSize, "select a.id,a.order_no,a.complete_time,a.order_title,a.real_amount,a.voucher,g.name gname,u.user_name uname,p.title,a.order_amount", yueAssembling(scree,sql3).toString());
             
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("listPage", listPage);
        map.put("listPageSum",listPage.getTotalRow());
        return map;	
	}

	/*预支出总额*/
	public Record advanceDetail(int pageNumber, int pageSize,ScreenDo scree,String activeType) {

		Record record=new Record();
		record.set("list",null);
		record.set("totalRow",0);
		record.set("pageNumber",pageNumber);
		record.set("totalPage",0);

		try{
			TreeMap<String, String> map = new TreeMap<String, String>();
			map.put("page_index",String.valueOf(pageNumber));
			map.put("page_size","20");

			if(activeType!="-1" && !activeType.equals("")){
				map.put("active_type",activeType);
			}
			if(!scree.getStartTime().equals("")){
				map.put("start_time",scree.getStartTime());
			}
			if(!scree.getEndTime().equals("")){
				map.put("end_time",scree.getEndTime());
			}
			String resultData="";
			try {
				resultData = InterfaceUtil.IndexGetAPI(InterfaceUtil.INDEXATURL+"/api/CouponRecord/GetPlatformAdvanceExpenditureList", map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultData);
				if ("1".equals(jsonObject.getString("Code"))) {

					Map<String, Object> mapJsonObjectStrval1 = JSONObject.fromObject(jsonObject.getString("Data"));

					int totalPage;
					int totalRow=Integer.parseInt(mapJsonObjectStrval1.get("totalCount").toString());
					totalPage=totalRow%pageSize==0 ? totalRow/pageSize : (totalRow/pageSize)+1;

					List<Record> list = (List<Record>)mapJsonObjectStrval1.get("list");
					if(list.size()<=0){
						record.set("list",null);
					}else{
						record.set("list",list);
					}
					record.set("totalRow",totalRow);
					record.set("totalPage",totalPage);

					System.out.println("成功"+resultData);
				} else {
					System.out.println("失败"+resultData);
				}

			} catch (IOException e) {
				System.out.println("系统错误"+resultData);
				e.printStackTrace();
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return record;
	}


	/*已支出总额*/
	public Record actualDetail(int pageNumber, int pageSize,ScreenDo scree,String activeType) {

		Record record=new Record();
		record.set("list",null);
		record.set("totalRow",0);
		record.set("pageNumber",pageNumber);
		record.set("totalPage",0);

		try{
			TreeMap<String, String> map = new TreeMap<String, String>();
			map.put("page_index",String.valueOf(pageNumber));
			map.put("page_size","20");

			if(!scree.getOrderNo().equals("")){
				map.put("order_no",scree.getOrderNo());
			}

			if(!scree.getOrderType().equals("")){
				map.put("order_type",scree.getOrderType());
			}

			if(activeType!="-1" && !activeType.equals("")){
				map.put("active_type",activeType);
			}
			if(!scree.getStartTime().equals("")){
				map.put("start_time",scree.getStartTime());
			}
			if(!scree.getEndTime().equals("")){
				map.put("end_time",scree.getEndTime());
			}
			String resultData="";
			try {
				resultData = InterfaceUtil.IndexGetAPI(InterfaceUtil.INDEXATURL+"/api/CouponRecord/GetPlatformExpenditureList", map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultData);
				if ("1".equals(jsonObject.getString("Code"))) {

					Map<String, Object> mapJsonObjectStrval1 = JSONObject.fromObject(jsonObject.getString("Data"));

					int totalPage;
					int totalRow=Integer.parseInt(mapJsonObjectStrval1.get("totalCount").toString());
					totalPage=totalRow%pageSize==0 ? totalRow/pageSize : (totalRow/pageSize)+1;

					List<Record> list = (List<Record>)mapJsonObjectStrval1.get("list");
					if(list.size()<=0){
						record.set("list",null);
					}else{
						record.set("list",list);
					}
					record.set("totalRow",totalRow);
					record.set("totalPage",totalPage);

					System.out.println("成功"+resultData);
				} else {
					System.out.println("失败"+resultData);
				}

			} catch (IOException e) {
				System.out.println("系统错误"+resultData);
				e.printStackTrace();
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return record;
	}
	
	/****************sql拼装****************/
	
	/*售后退款sql拼装*/
	private StringBuffer refoundDetailAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getCanceOrderNo().equals("")){
			sql.append(" and b.back_return_no='"+scree.getCanceOrderNo()+"'");
		}
		if(!scree.getOrderNo().equals("")){
			sql.append(" and o.order_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getOrderType().equals("")){
			sql.append(" and o.order_title='"+scree.getOrderType()+"'");
		}
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and b.update_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			sql.append(" and b.update_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and b.update_time < '"+scree.getEndTime()+"'");
		}
		return sql;
	}
	
	/*满意卷退款sql拼装*/
	private StringBuffer voucherAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and a.order_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getOrderType().equals("")){
			sql.append(" and a.order_title='"+scree.getOrderType()+"'");
		}
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			sql.append(" and a.complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time < '"+scree.getEndTime()+"'");
		}
		return sql;
	}
	
	/*金融手续费sql拼装*/
	private StringBuffer  financeAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and a.withdrawals_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			sql.append(" and a.complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time < '"+scree.getEndTime()+"'");
		}
		return sql;
	}
	
	/*用户账户sql拼装*/
	private StringBuffer  userAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getNickName().equals("")){
			sql.append(" and u.user_name like '"+scree.getNickName()+"%'");
		}
		if(!scree.getUserType().equals("")){
			sql.append(" and a.state='"+scree.getUserType()+"'");
		}
		sql.append(" order by a.user_id asc ");
		return sql;
	}
	
	/*商家账户sql拼装*/
	private StringBuffer  shopAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getNickName().equals("")){
			sql.append(" and p.name like '"+scree.getNickName()+"%'");
		}
		if(!scree.getUserType().equals("")){
			sql.append(" and a.state='"+scree.getUserType()+"'");
		}
		sql.append(" order by a.user_id asc ");
		return sql;
	}
	
	/*技术服务费sql拼装*/
	private StringBuffer techServiceDetailAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and o.order_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getOrderType().equals("")){
			sql.append(" and o.order_title='"+scree.getOrderType()+"'");
		}
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and o.complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			sql.append(" and o.complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and o.complete_time < '"+scree.getEndTime()+"'");
		}
		return sql;
	}
	
	/*资金流水sql拼装*/
	private StringBuffer  flowAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and order_no='"+scree.getOrderNo()+"'");
		}
		return sql;
	}
	
	/*第三方sql拼装*/
	private StringBuffer weixinAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and a.order_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getOrderType().equals("")){
			sql.append(" and a.order_title='"+scree.getOrderType()+"'");
		}
		return sql;
	}
	
	/*余额sql拼装*/
	private StringBuffer yueAssembling(ScreenDo scree,StringBuffer sql) {
		if(!scree.getOrderNo().equals("")){
			sql.append(" and a.order_no='"+scree.getOrderNo()+"'");
		}
		if(!scree.getOrderType().equals("")){
			sql.append(" and a.order_title='"+scree.getOrderType()+"'");
		}
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			sql.append(" and a.complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			sql.append(" and a.complete_time < '"+scree.getEndTime()+"'");
		}
		return sql;
	}
	
	/*获取支付订单id*/
	private List<String> selectOrder(String payment,ScreenDo scree) {
		StringBuffer se=new StringBuffer();
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and a.notify_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			se.append(" and a.notify_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and a.notify_time < '"+scree.getEndTime()+"'");
		}
		String sql="select a.order_id_list from dt_order_online_pay a where a.payment_id in ("+payment+") and a.status=1 and a.order_id_list <> ''"+se.toString();
		List<Record> allOrderList=sqlserver.find(sql);
		String[] temp=null;
        List<String> stringList = new ArrayList<String>();
        for(Record order : allOrderList){
            String str = order.get("order_id_list");
            if(!str.equals("")) {
                temp = str.split("[,]");
                for (int a = 0; a <= temp.length - 1; a++) {
                    stringList.add(temp[a]);
                }
            }
        }
        
        /*去重*/
        for  ( int  i  =   0 ; i  <  stringList.size()  -   1 ; i ++ )  {
            for  ( int  j  =  stringList.size()  -   1 ; j  >  i; j -- )  {
                if  (stringList.get(j).equals(stringList.get(i)))  {
                    stringList.remove(j);
                }
            }
        }
        
		return stringList;
	}
	
	/*获取支付详细*/
	private Page<Record> selectOrderxiangxi(List<String> stringList,int pageNumber,int pageSize,ScreenDo scree) {
		Page<Record> page=new Page<Record>();
		StringBuffer sql=new StringBuffer(" from dt_orders a left join dt_users u on u.id=a.user_id left join dt_user_role_shopinfo g on g.user_id=a.shop_user_id and g.user_id<>0 and g.is_del=0 left join dt_payment p on p.id=a.payment_id where 1=1");
		if(stringList.size()>0){
			sql.append(" and a.id in (");
			int listsum=1;
	        for(String s: stringList){
	            if(listsum==stringList.size()){
	                sql.append(s+")");
	            }else{
	                sql.append(s+",");
	            }
	            listsum++;
	        }
	        page=sqlserver.paginate(pageNumber, pageSize, "select (case IsNull(a.status,-1)when 1 then '生成订单'when 2 then '确认订单'when 3 then '完成订单'when 4 then '取消订单' when 5 then '锁定订单' when 6 then '卖家取消订单' when 7 then '投诉冻结中' when -1 then ''end) status,a.id,a.order_no,a.payment_time complete_time,a.order_title,a.real_amount,a.voucher,g.name gname,u.user_name uname,p.title,a.order_amount", weixinAssembling(scree,sql).toString());
		}else{
			page=null;
		}
		return page;
	}
	
	/*获取支付页数*/
	private Integer selectOrderxiangxiCount(List<String> stringList,ScreenDo scree) {
		Integer yeshu=0;
		StringBuffer sql=new StringBuffer("select count(1) from dt_orders a left join dt_users u on u.id=a.user_id left join dt_users g on g.id=a.shop_user_id left join dt_payment p on p.id=a.payment_id where 1=1");
		if(stringList.size()>0){
			sql.append(" and a.id in (");
			int listsum=1;
	        for(String s: stringList){
	            if(listsum==stringList.size()){
	                sql.append(s+")");
	            }else{
	                sql.append(s+",");
	            }
	            listsum++;
	        }
	        yeshu=sqlserver.queryFirst(weixinAssembling(scree,sql).toString());
	    }else{
	    	yeshu=0;
		}
		return yeshu;
	}
	
	/**************************EXCEL***************************/
	/*导出第三方EXCEL*/
	public List<Record> ThirdPayDetailExcel(ScreenDo scree,String type) {
		String payment="";
		if(type.equals("zhifubao")){
			 payment="13";
		}else if(type.equals("card")){
			 payment="11";
		}else if(type.equals("weixin")){
			 payment="8,12";
		}
		
		List<String> stringList=selectOrder(payment,scree);
		List<Record> list=new ArrayList<Record>();
		list=selectOrderxiangxiExcel(stringList,scree);
        return list;
	}
	
	
	/*获取EXCEL支付详细*/
	private List<Record> selectOrderxiangxiExcel(List<String> stringList,ScreenDo scree) {
		List<Record> list=new ArrayList<Record>();
		StringBuffer sql=new StringBuffer("from dt_orders a left join dt_users u on u.id=a.user_id left join dt_user_role_shopinfo g on g.user_id=a.shop_user_id and g.user_id<>0 and g.is_del=0 left join dt_payment p on p.id=a.payment_id where 1=1");
		if(stringList.size()>0){
			sql.append(" and a.id in (");
			int listsum=1;
	        for(String s: stringList){
	            if(listsum==stringList.size()){
	                sql.append(s+")");
	            }else{
	                sql.append(s+",");
	            }
	            listsum++;
	        }
	       list=sqlserver.find("select (case IsNull(a.status,-1)when 1 then '生成订单'when 2 then '确认订单'when 3 then '完成订单'when 4 then '取消订单' when 5 then '锁定订单' when 6 then '卖家取消订单' when 7 then '投诉冻结中' when -1 then ''end) status,a.id,COALESCE(a.order_no,'') order_no,a.payment_time complete_time,COALESCE(a.order_title,'') order_title,COALESCE(a.real_amount,'') real_amount,COALESCE(a.voucher,'') voucher,COALESCE(g.name,'') gname,COALESCE(u.user_name,'') uname,COALESCE(p.title,'') title,COALESCE(a.order_amount,'') order_amount "+weixinAssembling(scree,sql).toString());
		}else{
		   list=null;
		}
		return list;
	}
	
	/*余额支出EXCEL*/
	public List<Record> BalanceExcel(ScreenDo scree,String type) {		
		String sql="select transaction_money,order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%'";
		List<Record> list=sqlserver.find(sql);
		String sql1="select real_amount,id from dt_orders o where o.id in (select order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%')";
		List<Record> list1=sqlserver.find(sql1);
		
		List<ScreenDo> listall=new ArrayList<ScreenDo>();
		
		for(Record x : list){
			for(Record g : list1){
				if((x.get("order_id_list")).toString().equals((g.get("id")).toString()) && Double.parseDouble((x.get("transaction_money")).toString())!=Double.parseDouble((g.get("real_amount")).toString())){
					ScreenDo sc=new ScreenDo();
					sc.setOrderNo((x.get("order_id_list")).toString());
					listall.add(sc);
				}
			}
		}
		String sql2="select id from dt_orders where id not in (select order_id_list from dt_order_online_pay where payment_id in (8,11,12,13) and order_id_list <> '' and status=1 and order_id_list not like '%,%') and status=3 ";
		
		List<Record> list2=sqlserver.find(sql2);
		for(Record x : list2){
			ScreenDo sc=new ScreenDo();
			sc.setOrderNo((x.get("id")).toString());
			listall.add(sc);
		}
		
		StringBuffer sql3=new StringBuffer(" from dt_orders a left join dt_users u on u.id=a.user_id left join dt_user_role_shopinfo g on g.user_id=a.shop_user_id and g.user_id<>0 and g.is_del=0 left join dt_payment p on p.id=a.payment_id where 1=1");
		
		sql3.append(" and a.id in (");
		int listsum=1;
        for(ScreenDo s: listall){
            if(listsum==listall.size()){
            	sql3.append(s.getOrderNo()+")");
            }else{
            	sql3.append(s.getOrderNo()+",");
            }
            listsum++;
        }
        StringBuffer sqlSum=new StringBuffer("select a.id,COALESCE(a.order_no,'') order_no,a.payment_time complete_time,COALESCE(a.order_title,'') order_title,COALESCE(a.real_amount,'') real_amount,COALESCE(a.voucher,'') voucher,COALESCE(g.name,'') gname,COALESCE(u.user_name,'') uname,COALESCE(p.title,'') title,COALESCE(a.order_amount,'') order_amount "+sql3.toString());

        return sqlserver.find((weixinAssembling(scree,sqlSum)).toString());
	}
	
	/*技术服务费EXCEL*/
	public List<Record> techServiceDetailExcel(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index and b.user_role_type='shop' and b.status=2 and b.is_del=0 and b.ywkg=1 left join dt_users u on u.id=o.user_id left join dt_users g on g.id=o.shop_user_id left join dt_user_role_shopinfo s on s.user_id=o.shop_user_id and o.user_id<>0 and o.is_del=0 left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_article_category ac on ac.id=rs.main_business left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1 where o.status=3 ");
		return sqlserver.find("select COALESCE(o.order_title,'') order_title,o.id,o.confirm_time,COALESCE(o.order_no,'') order_no,COALESCE(s.name,'') gnick,COALESCE(g.user_name,'') gname,COALESCE(ac.title,'') TAG,COALESCE(u.user_name,'') uname,COALESCE(p.payment_name,'') title,COALESCE(o.real_amount,'') real_amount,COALESCE(o.voucher,'') voucher,COALESCE(o.order_amount,'') order_amount, cast(cast(b.user_percentage*100 as numeric(10,2)) as  varchar(50))+'%' user_percentage,cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2)) technical,(real_amount-cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2))) real_shop_money "+techServiceDetailAssembling(scree,sql).toString());
	}
	
	/*金融服务费EXCEL*/
	public List<Record> financeDetailExcel(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_user_withdrawals a left join dt_users u on a.user_id=u.id where a.status=5");
		return sqlserver.find("select a.id,a.complete_time,COALESCE(a.withdrawals_no,'') withdrawals_no,COALESCE(u.user_name,'') nick_name,COALESCE(a.bank_user,'') bank_user,COALESCE(a.bank_name,'') bank_name,COALESCE(a.bank_card,'') bank_card,COALESCE(a.total_money,0) total_money,COALESCE(a.commission,0) commission,(a.total_money-a.commission) arrival"+financeAssembling(scree,sql).toString());
	}
	
	/*满意卷支出EXCEL*/
	public List<Record> voucherDetailExcel(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_orders a left join dt_users u on u.id=a.user_id left join  dt_user_role_shopinfo g on g.user_id=a.shop_user_id left join dt_order_online_pay p on cast(a.id as nvarchar) = p.order_id_list and p.status=1 where a.status=3");
		return sqlserver.find("select a.id,COALESCE(u.user_name,'') uname,COALESCE(g.name,'') gname,a.complete_time,COALESCE(a.order_no,'') order_no,COALESCE(a.order_title,'') order_title,COALESCE(p.payment_name,'') title,COALESCE(a.order_amount,0) order_amount,COALESCE(a.voucher,0) voucher,COALESCE(a.real_amount,0) real_amount"+voucherAssembling(scree,sql).toString());
	}
	
	/*售后退款EXCEL*/
	public List<Record> refoundDetailExcel(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_order_backinfo b inner join dt_orders o on o.id=b.order_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1 left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo g on g.user_id=o.shop_user_id where b.status=3");
		return sqlserver.find("select b.id,COALESCE(p.payment_name,'') title,b.update_time add_time,COALESCE(b.back_return_no,'') back_return_no,COALESCE(o.order_no,'') order_no,COALESCE(o.order_title,'') order_title,COALESCE(g.name,'') gname,COALESCE(u.user_name,'') uname,COALESCE(b.back_goods_amount,0) alltk,COALESCE(b.real_back_amount,0) tkxianjin,COALESCE(b.voucher,0) tuikuandj "+refoundDetailAssembling(scree,sql).toString());
	}
	
	/*用户账户余额EXCEL*/
	public List<Record> userDetailEXCEL(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_users u left join dt_user_accountinfo a on a.user_id=u.id left join dt_user_personinfo p on p.user_id=u.id Group By a.amount,a.user_id,a.role_type,a.allow_amount,p.person_name,a.state,u.user_name  HAVING a.role_type='Buyer'");
		
		List<Record> List=sqlserver.find( "select a.user_id,COALESCE(a.amount,'') amount,COALESCE(a.role_type,'') role_type,COALESCE(a.allow_amount,0) allow_amount,COALESCE(p.person_name,'') person_name,a.state,COALESCE(u.user_name,'') user_name "+ userAssembling(scree,sql).toString());
		for(Record x : List){
			String msgByCode = AccountStateEnums.getByCode(x.get("state").toString()).getMessage();
			x.set("state",msgByCode);
		}
		return List;
	}
	
	
	/*商家账户余额EXCEL*/
	public List<Record> shopDetailEXCEL(ScreenDo scree) {
		StringBuffer sql=new StringBuffer(" from dt_users u left join dt_user_accountinfo a on a.user_id=u.id left join dt_user_role_shopinfo p on p.user_id=u.id left join dt_article_category ac on ac.id=p.main_business Group By a.amount,a.user_id,a.role_type,a.allow_amount,a.state,u.user_name,p.name,p.mobile,ac.title,p.area HAVING a.role_type='shop'");
		
		List<Record> List=sqlserver.find("select a.user_id,COALESCE(a.amount,'') amount,COALESCE(a.role_type,'') role_type,COALESCE(a.allow_amount,0) allow_amount,a.state,COALESCE(u.user_name,'') user_name,COALESCE(p.name,'') name,COALESCE(p.mobile,'') mobile,COALESCE(ac.title,'') TAG,COALESCE(p.area,'') area "+ shopAssembling(scree,sql).toString());
		for(Record x : List){
			String msgByCode = AccountStateEnums.getByCode(x.get("state").toString()).getMessage();
			x.set("state",msgByCode);
		}
		return List;
	}
	
	/*资金流水明细EXCEL*/
	public List<Record> flowDetailsEXCEL(ScreenDo scree,int userId,String type) {
		StringBuffer sql=new StringBuffer(" from dt_user_amount_log where user_id=? and user_role_type=?");
		return sqlserver.find("select add_time,COALESCE(old_value,0) old_value,COALESCE(value,0) value,COALESCE(new_value,0) new_value,COALESCE(type,'') type,COALESCE(order_no,'') order_no,COALESCE(remark,'') remark "+flowAssembling(scree,sql).toString(),userId,type);
	}
	
	/*日报月报详情查看*/
	public Page<Record> riyueselect(int pageNumber, int pageSize,ScreenDo scree) {

		StringBuffer se=new StringBuffer();
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			se.append(" and complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and complete_time < '"+scree.getEndTime()+"'");
		}
		if(!scree.getOrderType().equals("")){
			se.append(" and type='"+scree.getOrderType()+"'");
		}
		if(!scree.getOrderNo().equals("")){
			se.append(" and order_no='"+scree.getOrderNo()+"'");
		}
		
		Page<Record> page=sqlserver.paginate(pageNumber, pageSize, "select *", " from (select o.id,o.complete_time,o.payment_time,o.order_no,o.order_title,isnull(s.name,'') name,isnull(u.user_name,'') user_name,p.payment_name,o.order_amount,cast(cast(isNull(b.user_percentage,0)*100 as numeric(10,2)) as  varchar(50))+'%' user_percentage,cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2)) technical,o.voucher,o.real_amount,null commission,(real_amount-cast(round((o.order_amount*isNull(b.user_percentage,0)),2) as numeric(20,2))) real_shop_money,'dingdan' type from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index and b.user_role_type='shop' and b.status=2 and b.is_del=0 and b.ywkg=1 left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo s on s.user_id=o.shop_user_id and o.user_id<>0 and o.is_del=0 left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1  where o.status=3  UNION all select 0,a.complete_time ,null,a.withdrawals_no,'提现单','',isnull(u.user_name,'') user_name,'',a.total_money,'',null,null,(a.total_money-a.commission) arrival,a.commission,(a.total_money-a.commission) arrival,'tixian' from dt_user_withdrawals a left join dt_users u on a.user_id=u.id where a.status=5  Union all select b.order_id,b.update_time,null,o.order_no,o.order_title+'(退款单)',isnull(g.name,'') name,u.user_name,p.payment_name,b.back_goods_amount,null,null,b.voucher,b.real_back_amount,null,b.real_back_amount,'tuikuan' from dt_order_backinfo b inner join dt_orders o on o.id=b.order_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1  left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo g on g.user_id=o.shop_user_id and g.user_id<>0 and g.is_del=0 where b.status=3) b where 1=1  "+se.toString());
		if(page!=null){
			for(Record x : page.getList()){
				Record a=sqlserver.findFirst("select payment_name from dt_order_online_pay where order_id_list like '%,"+x.get("id")+",%' or order_id_list like '"+x.get("id")+",%' or order_id_list like ',"+x.get("id")+"%' or order_id_list like '"+x.get("id")+"'");
				if(a!=null){
					x.set("payment_name",a.get("payment_name"));
				}
			}
		}
		return page;
	}
	
	/*日报月报详情查看EXCEL*/
	public List<Record> riyueselectEXCEL(ScreenDo scree) {

		StringBuffer se=new StringBuffer();
		if(!scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and complete_time BETWEEN '"+scree.getStartTime()+"' and '"+scree.getEndTime()+"'");
		}else if(!scree.getStartTime().equals("") && scree.getEndTime().equals("")){
			se.append(" and complete_time > '"+scree.getStartTime()+"'");
		}else if(scree.getStartTime().equals("") && !scree.getEndTime().equals("")){
			se.append(" and complete_time < '"+scree.getEndTime()+"'");
		}
		if(!scree.getOrderType().equals("")){
			se.append(" and type='"+scree.getOrderType()+"'");
		}
		if(!scree.getOrderNo().equals("")){
			se.append(" and order_no='"+scree.getOrderNo()+"'");
		}
		
		List<Record> re=sqlserver.find("select id,isNull(complete_time,'') complete_time,isNull(payment_time,'') payment_time,isNull(order_no,'') order_no,isNull(order_title,'') order_title,isNull(name,'') name,isNull(user_name,'') user_name,isNull(payment_name,'') payment_name,isNull(order_amount,0.00) order_amount,isNull(user_percentage,0.00) user_percentage,isNull(technical,0.00) technical,isNull(voucher,0.00) voucher,isNull(real_amount,0.00) real_amount,isNull(commission,0.00) commission,isNull(real_shop_money,0.00) real_shop_money from (select o.id,o.complete_time,o.payment_time,o.order_no,o.order_title,isnull(s.name,'') name,isnull(u.user_name,'') user_name,p.payment_name,o.order_amount,cast(cast(isNull(b.user_percentage,0)*100 as numeric(10,2)) as  varchar(50))+'%' user_percentage,cast(round((o.order_amount*b.user_percentage),2) as numeric(20,2)) technical,o.voucher,o.real_amount,null commission,(real_amount-cast(round((o.order_amount*isNull(b.user_percentage,0)),2) as numeric(20,2))) real_shop_money,'dingdan' type from dt_orders o left join dt_business_user b on o.shop_user_id=b.user_id and o.order_type=b.call_index and b.user_role_type='shop' and b.status=2 and b.is_del=0 and b.ywkg=1 left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo s on s.user_id=o.shop_user_id and o.user_id<>0 and o.is_del=0 left join dt_user_role_shopinfo rs on rs.user_id=o.shop_user_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1  where o.status=3 UNION all select 0,a.complete_time ,'',a.withdrawals_no,'提现单','',isnull(u.user_name,'') user_name,'',a.total_money,'',null,null,(a.total_money-a.commission) arrival,a.commission,(a.total_money-a.commission) arrival,'tixian' from dt_user_withdrawals a left join dt_users u on a.user_id=u.id where a.status=5  Union all select b.order_id,b.update_time,'',o.order_no,o.order_title+'(退款单)',isnull(g.name,'') name,u.user_name,p.payment_name,b.back_goods_amount,null,null,b.voucher,b.real_back_amount,null,b.real_back_amount,'tuikuan' from dt_order_backinfo b inner join dt_orders o on o.id=b.order_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1  left join dt_users u on u.id=o.user_id left join dt_user_role_shopinfo g on g.user_id=o.shop_user_id and g.user_id<>0 and g.is_del=0 where b.status=3) b where 1=1 "+se.toString());
		
		for(Record x : re){
			Record a=sqlserver.findFirst("select payment_name from dt_order_online_pay where order_id_list like '%,"+x.get("id")+",%' or order_id_list like '"+x.get("id")+",%' or order_id_list like ',"+x.get("id")+"%' or order_id_list like '"+x.get("id")+"'");
			if(a!=null){
				x.set("payment_name",a.get("payment_name"));
			}
		}
		
		return re;
	}

	/*已支出总额导出Excel*/
	public List<Record> actualDetailExcel(ScreenDo scree,String activeType,String allcount) {

		List<Record> recordList=new ArrayList<Record>();

		try{
			TreeMap<String, String> map = new TreeMap<String, String>();
			map.put("page_index","1");
			map.put("page_size",allcount);

			if(!scree.getOrderNo().equals("")){
				map.put("order_no",scree.getOrderNo());
			}

			if(!scree.getOrderType().equals("")){
				map.put("order_type",scree.getOrderType());
			}

			if(activeType!="-1" && !activeType.equals("")){
				map.put("active_type",activeType);
			}
			if(!scree.getStartTime().equals("")){
				map.put("start_time",scree.getStartTime());
			}
			if(!scree.getEndTime().equals("")){
				map.put("end_time",scree.getEndTime());
			}
			String resultData="";
			try {
				resultData = InterfaceUtil.IndexGetAPI(InterfaceUtil.INDEXATURL+"/api/CouponRecord/GetPlatformExpenditureList", map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultData);
				if ("1".equals(jsonObject.getString("Code"))) {

					Map<String, Object> mapJsonObjectStrval1 = JSONObject.fromObject(jsonObject.getString("Data"));

					recordList = (List<Record>)mapJsonObjectStrval1.get("list");
					if(recordList.size()<=0){
						recordList=null;
					}

					System.out.println("成功"+resultData);
				} else {
					System.out.println("失败"+resultData);
				}

			} catch (IOException e) {
				System.out.println("系统错误"+resultData);
				e.printStackTrace();
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return recordList;
	}

	/*预支出总额导出Excel*/
	public List<Record> advanceDetailExcel(ScreenDo scree,String activeType,String allcount) {

		List<Record> recordList=new ArrayList<Record>();

		try{
			TreeMap<String, String> map = new TreeMap<String, String>();
			map.put("page_index","1");
			map.put("page_size",allcount);

			if(activeType!="-1" && !activeType.equals("")){
				map.put("active_type",activeType);
			}
			if(!scree.getStartTime().equals("")){
				map.put("start_time",scree.getStartTime());
			}
			if(!scree.getEndTime().equals("")){
				map.put("end_time",scree.getEndTime());
			}
			String resultData="";
			try {
				resultData = InterfaceUtil.IndexGetAPI(InterfaceUtil.INDEXATURL+"/api/CouponRecord/GetPlatformAdvanceExpenditureList", map);
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultData);
				if ("1".equals(jsonObject.getString("Code"))) {

					Map<String, Object> mapJsonObjectStrval1 = JSONObject.fromObject(jsonObject.getString("Data"));

					recordList = (List<Record>)mapJsonObjectStrval1.get("list");
					if(recordList.size()<=0){
						recordList=null;
					}

					System.out.println("成功"+resultData);
				} else {
					System.out.println("失败"+resultData);
				}

			} catch (IOException e) {
				System.out.println("系统错误"+resultData);
				e.printStackTrace();
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return recordList;
	}
}
