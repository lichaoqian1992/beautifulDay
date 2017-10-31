package com.manji.finance.order;


import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.order.requestParams.OrderParam;
import com.manji.finance.utils.DButils;

public class OrderRepository extends DButils{
	/**
	 * 根据条件分页查询订单信息
	 */
	public Page<Record> findByConditionPage(String sql,int pageNum){
		//return sqlserver.paginate(pageNum, 20, "select ((select IsNull(sum(dpc.subsidy_money),0) subsidy_money from dt_coupon_record dcr left join dt_platform_coupon dpc on dcr.coupon_code=dpc.coupon_code where dcr.order_id=o.id)+(select IsNull(sum(dc.derate_amount),0) derate_amount from dt_coupon_user dcu left join dt_coupon dc on dcu.coupon_id=dc.id where dcu.order_no=o.order_no)) daijin ,o.shop_user_id as u_id ,isnull(u.user_name,'') user_name ,isnull(s.name,'') name,isnull(s.area,'') area,s.mobile ,isnull(b.user_percentage,0) as user_percentage ,isnull(p.payment_name,'') payment_name,o.order_no,o.order_type,o.real_amount,o.voucher,o.order_amount,o.add_time,o.complete_time,o.status,l.real_balance_date,l.balance_state,l.balance_amount", sql);
		return sqlserver.paginate(pageNum, 20, "select  o.shop_user_id as u_id ,isnull(u.user_name,'') user_name ,isnull(uu.id,0) as uuid,isnull(uu.user_name,'') as uuusername,isnull(s.name,'') name,isnull(s.area,'') area,s.mobile ,isnull(b.user_percentage,0) as user_percentage ,isnull(p.payment_name,'') payment_name,o.order_no,o.order_type,o.real_amount,o.voucher,o.order_amount,o.add_time,o.complete_time,o.status,l.real_balance_date,l.balance_state,l.balance_amount", sql);
	}
	
	/**
	 * 根据订单号查询结算信息
	 */
	public List<Record> settleInfo(String order_no){
		return sqlserver.find("select m.user_name,m.real_name,o.order_no,o.order_type,l.role_type,s.name,l.balance_amount,l.balance_voucher,l.balance_point,convert(varchar(19),l.add_time,20) add_time,convert(varchar(19),l.will_balance_date,20) will_balance_date,convert(varchar(19),l.real_balance_date,20) real_balance_date,l.balance_state "
				+ "from dt_user_balance_log l left join dt_orders o on o.order_no=l.order_no "
				+ "left join dt_manager m on l.user_id=m.id "
				+ "left join dt_user_role_shopinfo s on o.shop_user_id=s.user_id where o.order_no=?",order_no);
	}
	
	/**
	 * 根据订单号修改结算状态
	 */
	public int updSettle(String order_no,String role_type){
		int count = sqlserver.update("update dt_user_balance_log set balance_state = 2 where order_no=? and role_type=?",order_no,role_type);
		return count;
	}
	
	/**
	 * 根据商家名模糊查询所有商家
	 */
	public Page<Record> findShopInfo(String sql,int pageNum){
		return sqlserver.paginate(pageNum, 20, "select c.title,s.id,s.user_id,s.name,u.user_name,s.area,s.mobile,s.main_business ", sql);
	}
	
	/**
	 * 商家详细信息
	 */
	
	public Record findBusinessDetails(int id){
		
		return sqlserver.findFirst("select s.user_id,a.title as main_title,c.card_pics,c.card_type,c.card_number,c.type,"
				+ "c.area,c.legal_person,c.legal_person_idcard,c.legal_person_mobile,s.area as storeArea,s.main_business,s.mobile,"
				+ "s.storeName,s.telephone,s.msg_mobile,s.area,s.address,s.name,b.pics,b.usertype,b.bank_type_value,b.bank_user,b.bank_address,o.title,o.file_url  "
				+ "from dt_user_role_shopinfo s left join dt_user_companyinfo c on "
				+ "s.user_id=c.user_id left join dt_article_category a on s.main_business=a.id left join dt_user_role_shopinfo_other o on "
				+ "s.user_id=o.shop_id left join dt_user_banktype b on s.user_id=b.user_id and b.is_del=0 where s.user_id=?",id);
	}
	 
	/**
	 * 商家账户信息
	 */
	
	public Record findAccountInfo(int id){
		return sqlserver.findFirst("select * from dt_user_accountinfo where user_id=? and role_type='shop'",id);
	}
	
	
	/**
	 * 商家图片信息
	 */
	public List<Record> findPictures(int shop_user_id){
		return sqlserver.find("select shop_id,shop_user_id,title,file_url from dt_user_role_shopinfo_other where shop_user_id="+shop_user_id);
	}
	
	/**
	 * 资金流水
	 */
	
	public Page<Record> findAmount(String sql,int pageNum){
		return sqlserver.paginate(pageNum,10,"select *",sql);
	}
	
	/**
	 * 资金流水支付类型
	 */
	
	public List<Record> findType(int id){
		return sqlserver.find("select distinct type from dt_user_amount_log where user_id=?",id);
	}
	/**
	 * 结算订单
	 */
	
	public Page<Record> findSettle(String sql,int pageNum){
		return sqlserver.paginate(pageNum,10,"select o.order_no,o.order_type,o.real_amount,o.voucher,o.order_amount,o.settlement_status,o.complete_time,o.settlement_time,p.title,b.user_percentage",sql);
	}
	
	/**
	 * 业务提点
	 */
	public Page<Record> findPercentage(String sql,int pageNum){
		return sqlserver.paginate(pageNum,10,"select name,content,user_percentage,valid_time",sql);
	}
	
	/**
	 * 转账信息
	 */
	public Page<Record> findTransfer(String sql,Integer pageNum){
		return sqlserver.paginate(pageNum, 10, "select *", sql);
	}
	
	
	/**订单信息EXCEL*/
	public List<Record> ordersEXCEL(OrderParam orderParam) {
		String orderNo = "";
		StringBuffer sql=new StringBuffer("select  o.shop_user_id as u_id ,isnull(u.user_name,'') user_name ,isnull(s.name,'') name,isnull(s.area,'') area,s.mobile ,isnull(b.user_percentage,0) user_percentage ,isnull(p.payment_name,'余额支付') title,o.order_no,o.order_type,o.real_amount,o.voucher,o.order_amount,o.add_time,o.complete_time,o.status,l.real_balance_date,l.balance_state,l.balance_amount"
				+" from dt_orders o left join dt_user_role_shopinfo s on o.shop_user_id=s.user_id " +
				"left join dt_users u on u.id=o.shop_user_id left join dt_users uu on uu.id=o.user_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1" +
				"left join dt_user_balance_log l on o.order_no=l.order_no and o.shop_user_id=l.user_id and o.shop_user_role_type=l.role_type "+               // and o.user_role_type=l.role_type\n" +
				"left join dt_business_user b on b.user_id= o.shop_user_id and b.call_index=o.order_type and o.shop_user_role_type=b.user_role_type where  1=1 ");
		if(orderParam != null) {
			if (orderParam.getStatus().equals(2)) {
				sql.append(" and o.status=3");
			} else if (orderParam.getStatus().equals(3)) {
				sql.append(" and l.balance_state=2");
			}else if (orderParam.getStatus().equals(1)){
				sql.append(" and o.status in(1,2,4,5,6,7)");
			}

			if (orderParam.getName() != null && !orderParam.getName().equals("")) {
				sql.append(" and s.name like '%" + orderParam.getName() + "%'");
			}
			if (null != orderParam.getOrder_no() && !orderParam.getOrder_no().equals("")) {
				if(orderParam.getOrder_no().indexOf(",") != -1){
					String str[] = orderParam.getOrder_no().split(",");//1,2,3,
					for(int m = 0;m<str.length;m++){
						if(m == str.length-1){
							orderNo += str[m];
						}else{
							orderNo += str[m]+",";
						}	
					}
					sql.append(" and o.order_no in("+orderNo+")");
				} else {
					sql.append(" and o.order_no='" + orderParam.getOrder_no() + "'");
				}
			}
			if (null != orderParam.getStartTime() && !orderParam.getStartTime().equals("")) {
				if (orderParam.getStatus().equals(2)) {
					sql.append(" and o.complete_time>='" + orderParam.getStartTime() + "'");
				} else if (orderParam.getStatus().equals(3)) {
					sql.append(" and l.real_balance_date>='" + orderParam.getStartTime() + "'");
				} else {
				}

			}
			if (null != orderParam.getEndTime() && !orderParam.getEndTime().equals("")) {
				if (orderParam.getStatus().equals(2)) {
					sql.append(" and o.complete_time<='" + orderParam.getEndTime() + "'");
				} else if (orderParam.getStatus().equals(3)) {
					sql.append(" and l.real_balance_date<='" + orderParam.getEndTime() + "'");
				} else {
				}
			}
			if (orderParam.getStatus().equals(2)) {
				sql.append(" order by o.complete_time desc");
			} else if (orderParam.getStatus().equals(3)) {
				sql.append(" order by l.real_balance_date desc");
			}
		}
		return sqlserver.find(sql.toString());
	}
	
	
	/**资金流水信息EXCEL*/
	public List<Record> toAmountEXCEL(OrderParam orderParam) {
		StringBuffer sql=new StringBuffer("select *,COALESCE(remark,'') as remark  from dt_user_amount_log where user_id="+orderParam.getUser_id());
		if(null != orderParam.getType() && !orderParam.getType().equals("")){
			sql.append(" and type='"+orderParam.getType()+"'");
		}
		if(null != orderParam.getStartTime() && !orderParam.getStartTime().equals("")){
			sql.append(" and add_time>='"+orderParam.getStartTime()+"'");
		}
		if(null != orderParam.getEndTime() && !orderParam.getEndTime().equals("")){
			sql.append(" and add_time<='"+orderParam.getEndTime()+"'");
		}
			
		return sqlserver.find(sql.toString());
	}
	
	
	/**结算订单信息EXCEL*/
	public List<Record> toAccountEXCEL(OrderParam orderParam) {
		StringBuffer sql=new StringBuffer("select COALESCE(o.order_no,'') as order_no,COALESCE(o.order_type,'') as order_type,o.real_amount,o.voucher,o.order_amount,COALESCE(o.settlement_status,'') as settlement_status,o.complete_time,o.settlement_time,p.title,b.user_percentage from dt_orders o,dt_payment p,dt_business_user b where o.payment_id=p.id and o.shop_user_id=b.user_id and shop_user_id="+orderParam.getUser_id());
		
		if(-1 != orderParam.getSettlement_status() && !"".equals(orderParam.getSettlement_status())){
			if(null != orderParam.getStartTime() && !orderParam.getStartTime().equals("")){
				if(orderParam.getSettlement_status().equals(1)){
					sql.append(" and o.complete_time>='"+orderParam.getStartTime()+"'");
				}else if(orderParam.getSettlement_status().equals(2)){
					sql.append(" and o.settlement_time>='"+orderParam.getStartTime()+"'");
				}else{
				}
				
			}
			if(null != orderParam.getEndTime() && !orderParam.getEndTime().equals("")){
				if(orderParam.getSettlement_status().equals(1)){
					sql.append(" and o.complete_time<='"+orderParam.getEndTime()+"'");
				}else if(orderParam.getSettlement_status().equals(2)){
					sql.append(" and o.settlement_time<='"+orderParam.getEndTime()+"'");
				}else{
				}
			}
			
			sql.append(" and o.settlement_status='"+(orderParam.getSettlement_status()-1)+"'");
			
		}else{
			
		}
		return sqlserver.find(sql.toString());
	}


	/**
	 * 用户信息模块
	 */
	public Page<Record> findUserAccountAll(String username,int number){
		StringBuffer sql=new StringBuffer("from dt_user_accountinfo left join dt_users on dt_user_accountinfo.user_id=dt_users.id left join dt_user_groups on dt_user_groups.id=dt_users.group_id   where role_type='Buyer' ");
		if (!username.equals("")){
			sql.append(" and dt_users.user_name like '%"+username+"%'");
		}
		return sqlserver.paginate(number,20,"select dt_users.user_name,dt_users.nick_name,dt_user_groups.title,dt_users.email,dt_users.mobile,dt_users.qq,dt_users.area,dt_users.status,dt_users.reg_time,dt_user_accountinfo.user_id ",sql.toString());
	}

	/**
	 * 查看用户详情
	 */
	public Record findUserInfo(int userId){
		return sqlserver.findFirst("select dt_user_accountinfo.id as account_id, dt_users.id,dt_users.user_name,dt_users.nick_name,dt_users.status, dt_user_personinfo.person_name,dt_user_personinfo.card_number,dt_user_personinfo.person_area,dt_user_personinfo.person_nation,dt_user_personinfo.person_brithday,dt_user_personinfo.local_area,dt_user_banktype.bank_type_value,dt_user_banktype.bank_address,dt_user_banktype.usertype,dt_user_accountinfo.amount,dt_user_accountinfo.point,dt_user_accountinfo.reputation,dt_user_accountinfo.voucher,dt_user_accountinfo.state from dt_user_accountinfo left join dt_users on dt_users.id=dt_user_accountinfo.user_id left join dt_user_personinfo on dt_user_personinfo.user_id=dt_user_accountinfo.user_id left join dt_user_banktype on  dt_user_accountinfo.user_id=dt_user_banktype.user_id and dt_user_accountinfo.role_type=dt_user_banktype.user_role_type where dt_user_accountinfo.role_type='Buyer'and dt_user_accountinfo.user_id=?;",userId);
	}

	/**
	 * 查询全部支付方式
	 */
	public List<Record> findPaymentAll(){
		return sqlserver.find("select * from dt_payment");
	}

	/**
	 * 查拳全部在线支付记录
	 */
	public Page<Record> findPaymentInfo(int number,String status,String paymentType,String startTime,String endTime,String user_name){

		StringBuffer sql=new StringBuffer("  from dt_order_online_pay left join dt_payment on dt_order_online_pay.payment_id=dt_payment.id left join dt_users on dt_users.id=dt_order_online_pay.user_id  where 1=1");

		if (!status.equals("")){
			sql.append(" and dt_order_online_pay.status="+status);
		}

		if (!paymentType.equals("")){
			sql.append(" and dt_order_online_pay.payment_id="+paymentType);
		}

		if (!startTime.equals("")){
			sql.append(" and dt_order_online_pay.add_time>='"+startTime+"'");
		}

		if (!endTime.equals("")){
			sql.append(" and dt_order_online_pay.add_time<'"+endTime+"'");
		}

		if (!user_name.equals("")){
			sql.append(" and dt_users.user_name like '%"+user_name+"%'");
		}

		sql.append(" order by dt_order_online_pay.id desc");
		return sqlserver.paginate(number,20,"select  dt_order_online_pay.id,dt_order_online_pay.user_id,dt_payment.title,dt_order_online_pay.transaction_money,dt_order_online_pay.status,dt_order_online_pay.transaction_no,dt_users.nick_name,dt_users.user_name,dt_order_online_pay.user_role_type,dt_order_online_pay.add_time,dt_order_online_pay.notify_time,dt_order_online_pay.remark",sql.toString());
	}
	
	
}
	
                 