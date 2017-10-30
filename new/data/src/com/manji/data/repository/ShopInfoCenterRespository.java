package com.manji.data.repository;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.service.GoodsPageVo;
import com.manji.data.model.request.service.OrderPageVo;

public class ShopInfoCenterRespository {

	/**
	 * 查询商家信息（客户中心）
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getShopInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select  * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 查询入驻信息（客户中心）
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getEnterInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 查询商品审核信息
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getGoodsInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 公司变更信息
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getCompanyInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 店铺信息变更
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getStoreInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 经营范围更改
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getScopeInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 证件信息
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getCardInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select isnull(o.title,'-') title,isnull(o.file_url,'-') file_url,o.add_time,isnull(s.name,'-') name ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	/**
	 * 订单信息
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Page<Record> getOrderInfo(int pageNumber,int pageSize,String sql) {
		
		String select = "select * ";
		
		Page<Record> page = Db.paginate(pageNumber, pageSize, select, sql);
		
		return page;
	}
	
	
	/**
	 * 导出商品信息（excel）
	 * @param vo
	 * @return
	 */
	public List<Record> outToExcel(GoodsPageVo vo) {
		
		StringBuffer sql = new StringBuffer("select *  from (select isnull(u.user_name,'-') user_name,isnull(x.name,'-') name,isnull(c.class_list,'') class_list,isnull(a.title,'-') as article_title,isnull(a.user_id,'-') user_id,isnull(c.title,'-') title,isnull(a.remark,'-') remark,convert(varchar(19),a.add_time,20) add_time,convert(varchar(19),a.update_time,20) update_time,a.status,"
							+"isnull((select user_name from dt_users where id=l.audit_user_id),'-') as audit_name,"
							+"isnull((select user_name from dt_users where id=l.input_user_id),'-') as input_name "
							+"from dt_article a "
							+"left join dt_article_category c on a.category_id=c.id "
							+"left join dt_users u on a.user_id=u.id "
							+"left join dt_user_role_shopinfo x on a.user_role_value=x.id "
							+"left join dt_user_role_audit_list l on a.user_id=l.add_user_id ) t where 1=1 ");
		
		if (vo.getArticle_title() != null && !"".equals(vo.getArticle_title())) { //商品名称
			sql.append(" and t.article_title like '%"+vo.getArticle_title()+"%'");
		}
		if (vo.getTitle() != null && !"".equals(vo.getTitle())) { //行业类别
			sql.append(" and t.title like '%"+vo.getTitle()+"%'");
		}
		if (vo.getName() != null && !"".equals(vo.getName())) {
			sql.append(" and t.name like '%"+vo.getName()+"%'");
		}
		if (vo.getUser_name() != null && !"".equals(vo.getUser_name())) { //商家名称
			sql.append(" and t.user_name like '%"+vo.getUser_name()+"%'");
		}
		if (vo.getInput_name() != null && !"".equals(vo.getInput_name())) {
			sql.append(" and t.input_name like '%"+vo.getInput_name()+"%'");
		}
		if (vo.getAudit_name() != null && !"".equals(vo.getAudit_name())) {
			sql.append(" and t.remark like '%"+vo.getAudit_name()+"%'");
		}
		if (vo.getStart_time() != null && !"".equals(vo.getStart_time())) {
			sql.append(" and t.update_time >= '" + vo.getStart_time() + "'");
		}
		if (vo.getEnd_time() != null && !"".equals(vo.getEnd_time())) {
			sql.append(" and t.update_time <= '" + vo.getEnd_time() + "'");
		}
		
		sql.append(" order by t.update_time desc");
		
		List<Record> page = Db.find(sql.toString());
		
		return page;
		
	}
	
	
	/**
	 * 导出订单信息
	 * @param vo
	 * @param pageNumber
	 * @return
	 */
	public List<Record> getOrderInfo(OrderPageVo vo){
		
		StringBuffer sql = new StringBuffer("select * from (select isnull((select user_name from dt_users where id=o.user_id),'-') as buyer_name,isnull((select user_name from dt_users where id=o.shop_user_id),'-') as shop_name,"
								+"isnull((select mobile from dt_users where id=o.shop_user_id),'-') as mobile,"
								+"isnull(o.order_no,'-') order_no,isnull(o.order_amount,0) order_amount,isnull(o.add_time,'') add_time,g.express_status,isnull(f.name,'-') name,o.status,o.book_back_status,b.status as back_status from " 
								+"dt_orders o left join dt_users u on o.shop_user_id=o.id "
								+"left join dt_order_goodinfo g on o.id=g.order_id "
								+"left join dt_user_role_shopinfo f on o.shop_user_id=f.user_id and o.shop_user_role_type='Shop' "
								+"left join dt_order_backinfo b on o.id=b.order_id) t where 1=1 ");
		
		if (vo.getShop_name() != null && !"".equals(vo.getShop_name())) {
			
			sql.append(" and t.shop_name like '%"+vo.getShop_name()+"%'");
		}
		if (vo.getName()!= null && !"".equals(vo.getName())) {
			
			sql.append(" and t.name like '%"+vo.getName()+"%'");
		}
		if (vo.getMobile() != null && !"".equals(vo.getMobile())) {
			
			sql.append(" and t.mobile like '%"+vo.getMobile()+"%'");
		}
		if (vo.getBuyer_name() != null && !"".equals(vo.getBuyer_name())) {
			
			sql.append(" and t.buyer_name like '%"+vo.getBuyer_name()+"%'");
		}
		if (vo.getExpress_status() != null && !"".equals(vo.getExpress_status())) {
			
			sql.append(" and t.express_status = '"+vo.getExpress_status()+"'");
		}
		if (vo.getOrder_no() != null && !"".equals(vo.getOrder_no())) {
			
			sql.append(" and t.order_no like '%"+vo.getOrder_no()+"%'");
		}
		if (vo.getStatus() != null && !"".equals(vo.getStatus())) {
			
			sql.append(" and t.status = '"+vo.getStatus()+"'");
		}
		if (vo.getBack_status() != null && !"".equals(vo.getBack_status())) {
			
			sql.append(" and t.back_status = '"+vo.getBack_status()+"'");
		}
		if (vo.getStart_time() != null && !"".equals(vo.getStart_time())) {
			
			sql.append(" and t.add_time >= '"+vo.getStart_time()+"'");
		}
		if (vo.getEnd_time() != null && !"".equals(vo.getEnd_time())) {
			
			sql.append(" and t.add_time <=  '"+vo.getEnd_time()+"'");
		}
		
		sql.append(" order by t.add_time desc");
		
		
		List<Record> page = Db.find(sql.toString());
		
		return page;
	}
	
	
	/**
	 * 导出订单信息
	 * @param vo
	 * @param pageNumber
	 * @return
	 */
	public List<Record> getAuditInfo(String start_time,String end_time,String audit_name,Integer type){
		
		StringBuffer sql = new StringBuffer("select k.*,isnull(p.person_name,'-') person_name from (select a.*,b.id from (select examine_name,count(examine_name) total from dt_audits where 1=1 ");
		
		if(start_time != null && !"".equals(start_time)){
			
			sql.append(" and examine_time >= '" +start_time+ "'");
		}
		
		if(end_time != null && !"".equals(end_time)){
			
			sql.append(" and examine_time < '" +end_time+ "'");
		}
		
		if(audit_name != null && !"".equals(audit_name)){
			
			sql.append(" and examine_name like '%" + audit_name+ "%'");
		}
		
		if(type != null && !"".equals(type)){
			
			sql.append(" and examine_type=" + type);
		}
		
		sql.append(" group by examine_name) a left join (select distinct user_name,id from dt_users) b on a.examine_name =b.user_name   ) k left join (select distinct user_id,person_name from dt_user_personinfo) p on k.id =  p.user_id ");
		
		
		List<Record> page = Db.find(sql.toString());
		
		return page;
	}
}
