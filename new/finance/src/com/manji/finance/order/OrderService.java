package com.manji.finance.order;


import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.order.model.OrderDetail;
import com.manji.finance.withdrawals.Account.UserAccountInfoRepository;
import com.manji.finance.withdrawals.Enums.OrderPaymentTypeEnums;
import com.manji.finance.withdrawals.Enums.OrderTypeEnums;
import com.manji.finance.withdrawals.Enums.UserRoleTypeEnums;

import com.manji.finance.withdrawals.Withdrawals.WithdrawalsRepository;
import net.sf.json.JSONArray;

public class OrderService {

	//提现数据类
	WithdrawalsRepository withdrawalsRepository=new WithdrawalsRepository();
	
	OrderRepository orderRepository = new OrderRepository();
	//账户数据类
    UserAccountInfoRepository userAccountInfoRepository=new UserAccountInfoRepository();
	
	/**
	 * 根据传的条件查询订单信息
	 */
	public Page<Record> findByCondition(OrderDetail o,String startTime,String endTime,int pageNum){
		Page<Record> page = null;
		StringBuffer sql = new StringBuffer(" from dt_orders o left join dt_user_role_shopinfo s on o.shop_user_id=s.user_id  "
				+ " left join dt_users u on u.id=o.shop_user_id left join dt_users uu on uu.id=o.user_id left join dt_order_online_pay p on cast(o.id as nvarchar) = p.order_id_list and p.status=1 "
				+ " left join dt_user_balance_log l on o.order_no=l.order_no and o.shop_user_id=l.user_id and o.shop_user_role_type=l.role_type "               // and o.user_role_type=l.role_type
				+ " left join dt_business_user b on b.user_id= o.shop_user_id and b.call_index=o.order_type and o.shop_user_role_type=b.user_role_type where  1=1 ");
		if(o != null){
			if(o.get("STATUS").equals(2)){
				sql.append(" and o.status=3");
			}else if(o.get("STATUS").equals(3)){
				sql.append(" and l.balance_state=2");
			}else if (o.get("STATUS").equals(1)){
				sql.append(" and o.status in(1,2,4,5,6,7)");
			}
			
			if(o.get("NAME") != null && !o.get("NAME").equals("")){
				sql.append(" and s.name like '%"+o.get("NAME")+"%'" );
			}
			if(null != o.get("ORDER_NO") && !o.get("ORDER_NO").equals("")){
				sql.append(" and o.order_no like '%"+o.get("ORDER_NO")+"%'");
			}
			if(null != startTime && !startTime.equals("")){
				if(o.get("STATUS").equals(2)){
					sql.append(" and o.complete_time>='"+startTime+"'");
				}else if(o.get("STATUS").equals(3)){
					sql.append(" and l.real_balance_date>='"+startTime+"'");
				}
			}
			if(null != endTime && !endTime.equals("")){
				if(o.get("STATUS").equals(2)){
					sql.append(" and o.complete_time<='"+endTime+"'");
				}else if(o.get("STATUS").equals(3)){
					sql.append(" and l.real_balance_date<='"+endTime+"'");
				}else{
				}
			}
			if(o.get("STATUS").equals(2)){
				sql.append(" order by o.complete_time desc");
			}else if(o.get("STATUS").equals(3)){
				sql.append(" order by l.real_balance_date desc");
			}

		}
		//查询到的是page对象
		page = orderRepository.findByConditionPage(sql.toString(), pageNum);

		//订单类型枚举
		for (int i=0;i<page.getList().size();i++){
			page.getList().get(i).set("order_type", OrderPaymentTypeEnums.getMsgByCode(page.getList().get(i).getStr("order_type")));
		}
		return page;
	}
	
	
	/**
	 * 查询订单信息
	 * @return
	 */
	public Page<Record> findOrders(String name,String orderNo,int status,String startTime,String endTime,int pageNum){
		OrderDetail o = new OrderDetail();
		o.set("NAME", name);
		o.set("ORDER_NO", orderNo);
		o.set("STATUS", status);
		
		
		Page<Record> page = findByCondition(o,startTime,endTime,pageNum);
		return page;
	}
	
	/**
	 * 根据订单号查询结算信息
	 */
	public List<Record> settleInfo(String order_no){
		List<Record> page = orderRepository.settleInfo(order_no);
		return page;
	}
	
	/**
	 * 根据订单号修改结算状态
	 */
	public int updSettle(String order_no,String role_type){
		int count = orderRepository.updSettle(order_no, role_type);
		return count;
	}
	
	/**
	 * 根据条件模糊查询所有商家信息
	 */
	public Page<Record> findBusiness(String name,int pageNum){
		String sql = "from dt_article_category c,dt_users u,dt_user_role_shopinfo s where u.id = s.user_id and s.main_business = c.id and s.name like '%"+name+"%'";
		Page<Record> page = orderRepository.findShopInfo(sql,pageNum);
		return page;
	}
	
	/**
	 * 商家详细信息
	 */
	public Record findBusinessDetails(int id){
		Record page = orderRepository.findBusinessDetails(id);
		return page;
	}
	
	/**
	 *商家图片信息 
	 */
	public List<Record> findPictures(int shop_user_id){
		List<Record> page = orderRepository.findPictures(shop_user_id);
		return page;
	}
	
	/**
	 * 商家账户
	 */
	
	public Record findAccountInfo(int id){
		Record page = orderRepository.findAccountInfo(id);
		return page;
	}
	

	/**
	 * 资金流水
	 */
	
	public Page<Record> findAmount(int id,String type,String startTime,String endTime,int pageNum,String wstatus){
		String userType="Shop";
		StringBuffer sql = new StringBuffer(" from dt_user_amount_log where user_id="+id+" and user_role_type='Shop'");
		if(null != type && !type.equals("")){
			sql.append(" and type='"+type+"'");
		}
		if(null != startTime && !startTime.equals("")){
			sql.append(" and add_time>='"+startTime+"'");
		}
		if(null != endTime && !endTime.equals("")){
			sql.append(" and add_time<='"+endTime+"'");
		}
		if (!wstatus.equals("0")){
			if (wstatus.equals("1")){
				//查询可以提现的订单类型
				if (userType!=null&&userType.equals("Shop")){
					sql.append("and type in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','OrderBack','OrderSettlement','FixedAccount') and (remark not like '%不能%' and remark not like '%不可%') ");
				}else{
					sql.append("and type in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','FixedAccount') and (remark not like '%不能%' and remark not like '%不可%') ");
				}
			}else {
				if (userType!=null&&userType.equals("Shop")){
					sql.append("and type not in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack','OrderBack','OrderSettlement')");
				}else{
					sql.append("and type not in('WithDrawals','Recharge','WithDrawalsAuto','WithDrawalsBack') ");
				}
			}
		}

		sql.append(" order by add_time desc");
		Page<Record> pageRecord=orderRepository.findAmount(sql.toString(),pageNum);
		for (int i=0;i<pageRecord.getList().size();i++){
			Record record=pageRecord.getList().get(i);
			//处理存入对方账户id
			switch(record.getStr("type")){

				//转账
				case "Transaction":
					Record trecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
					if (trecord==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						pageRecord.getList().get(i).set("order_user_id",trecord.get("touser_id").toString());
					}
					break;

				//转出
				case "TransferOut":
					Record toutrecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
					if (toutrecord==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						pageRecord.getList().get(i).set("order_user_id",toutrecord.get("touser_id").toString());
					}
					break;

				//转入
				case "TransferIn":
					Record tinrecord=withdrawalsRepository.getInuser(record.get("user_id").toString(),record.getStr("order_no"));
					if (tinrecord==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						pageRecord.getList().get(i).set("order_user_id",tinrecord.get("user_id").toString());
					}
					break;

				//订单退款
				case "OrderBack":
					//获取退回订单
						//获取订单
						Record OrderBack=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
						if (OrderBack==null){
							pageRecord.getList().get(i).set("order_user_id","-1");
						}else{
							//如果退款金额大于0说明此条记录是用户记录
							if (record.getBigDecimal("value").doubleValue()>0){
								pageRecord.getList().get(i).set("order_user_id",OrderBack.get("user_id"));
								//如果退款金额大于0说明此条记录是商家记录
							}else{
								pageRecord.getList().get(i).set("order_user_id",OrderBack.get("shop_user_id"));
							}
						}
					break;

				//购买商品
				case "BuyGoods":
					//获取订单
					Record orders=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
					if (orders==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						//判断用户id
						if (record.get("user_id").toString().equals(orders.get("user_id").toString())){
							pageRecord.getList().get(i).set("order_user_id",orders.get("shop_user_id"));
						}else{
							pageRecord.getList().get(i).set("order_user_id",orders.get("user_id"));
						}
					}
					break;

				//订单预结算
				case "OrderPreSettlement":
					//获取订单
					Record OrderPreSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
					if (OrderPreSettlement==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						//判断用户id
						if (record.get("user_id").toString().equals(OrderPreSettlement.get("user_id").toString())){
							pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("shop_user_id"));
						}else{
							pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("user_id"));
						}
					}

					break;

				//订单结算
				case "OrderSettlement":
					//获取订单
					Record orderSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
					if (orderSettlement==null){
						pageRecord.getList().get(i).set("order_user_id","-1");
					}else{
						//判断用户id
						if (record.get("user_id").toString().equals(orderSettlement.get("user_id").toString())){
							pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("shop_user_id"));
						}else{
							pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("user_id"));
						}
					}

					break;


				default:
					pageRecord.getList().get(i).set("order_user_id","-1");
					break;
			}

			//处理订单类型
			String t= OrderTypeEnums.getMsgByCode(record.getStr("type"));
			pageRecord.getList().get(i).set("type",t);
		}
		return pageRecord;
    }
	
	/**
	 * 资金流水支付类型
	 */
	
	public List<Record> findType(int id){
		List<Record> page = orderRepository.findType(id);
		return page;
	}
	
	/**
	 * 结算订单
	 */
	
	public Page<Record> findSettle(int id,String type,String startTime,String endTime,int pageNum){
		
		StringBuffer sql = new StringBuffer(" from dt_orders o,dt_payment p,dt_business_user b where o.payment_id=p.id and o.shop_user_id=b.user_id and shop_user_id="+id);

		if(null != type && !type.equals("")){
			if(null != startTime && !startTime.equals("")){
				if(type.equals("1")){
					sql.append(" and o.complete_time>='"+startTime+"'");
				}else if(type.equals("2")){
					sql.append(" and o.settlement_time>='"+startTime+"'");
				}else{
				}
				
			}
			if(null != endTime && !endTime.equals("")){
				if(type.equals("1")){
					sql.append(" and o.complete_time<='"+endTime+"'");
				}else if(type.equals("2")){
					sql.append(" and o.settlement_time<='"+endTime+"'");
				}else{
				}
			}
			
			sql.append(" and o.settlement_status='"+(Integer.parseInt(type)-1)+"'");
			
		}
		
		return orderRepository.findSettle(sql.toString(),pageNum);
	}
	
	/**
	 * 业务提点
	 */
	
	public Page<Record> findPercentage(int id,int pageNum){
		String sql = " from dt_business_user where user_id="+id;
		return orderRepository.findPercentage(sql,pageNum);
	}
	
	/**
	 * 转账信息
	 */
	
	public Page<Record> findTransfer(String transaction_no,String startTime,String endTime,Integer pageNum){
		StringBuffer sql =new StringBuffer( " from dt_user_transaction where  1=1 ");
		if(transaction_no != null && !"".equals(transaction_no)){
			sql.append("and transaction_no="+transaction_no);
		}
		if(startTime != null && !"".equals(startTime)){
			sql.append(" and complete_time>='"+startTime+"'");
		}
		if(endTime != null && !"".equals(endTime)){
			sql.append(" and complete_time<='"+endTime+"'");
		}
		if(pageNum == null && "".equals(pageNum)){
			pageNum = 1;
		}
		return orderRepository.findTransfer(sql.toString(), pageNum);
	}

	/**
	 * 用户信息模块
	 */
	public Page<Record> findUserAccountAll(String username,int number){
		return orderRepository.findUserAccountAll(username,number);
	}

	/**
	 * 查看用户详情
	 */
	public Record findUserInfo(int userId){
		return orderRepository.findUserInfo(userId);
	}

	/**
	 * 查询全部支付方式
	 */
	public List<Record> findPaymentAll(){
		return orderRepository.findPaymentAll();
	}

	/**
	 * 查拳全部在线支付记录
	 */
	public Page<Record> findPaymentInfo(int number,String status,String paymentType,String startTime,String endTime,String user_name){
		return  orderRepository.findPaymentInfo(number,status,paymentType,startTime,endTime,user_name);
	}

}
