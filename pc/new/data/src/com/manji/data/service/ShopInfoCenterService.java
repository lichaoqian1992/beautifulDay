package com.manji.data.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.common.PageModel;
import com.manji.data.model.request.service.AuditStatistics;
import com.manji.data.model.request.service.EnterPageVo;
import com.manji.data.model.request.service.GoodsPageVo;
import com.manji.data.model.request.service.OrderPageVo;
import com.manji.data.model.request.service.ShopPageVo;
import com.manji.data.model.request.service.StorePageVo;
import com.manji.data.repository.AuditStatisticsRespository;
import com.manji.data.repository.ShopInfoCenterRespository;

/**
 * 客户中心商品信息查询
 * @author Administrator
 *
 */
public class ShopInfoCenterService {

	private ShopInfoCenterRespository respository = new ShopInfoCenterRespository();
	private AuditStatisticsRespository asr = new AuditStatisticsRespository();
	/**
	 * 商家信息
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> getShopInfo(ShopPageVo vo){
		
		StringBuffer sql = new StringBuffer(" from (select  a.state,a.user_id,isnull(a.name,'') name,isnull(a.storeName,'') storeName,isnull(a.msg_mobile,'') msg_mobile,isnull(a.remark,'') remark,isnull(a.add_time,'') add_time,isnull(c.type,'') type,isnull(c.area,'') area,"
				+" isnull((select user_name from dt_users where id=a.user_id),'') as shop_user_name,"
				+" isnull((select user_name from dt_users where id=b.audit_user_id),'暂未分配审核人员') as audit_name,"
				+" isnull((select user_name from dt_users where id=b.bus_user_id),'暂未分配业务人员') as business_name,"
				+" isnull((select user_name from dt_users where id=b.input_user_id),'暂未分配录入人员') as input_name "
				+" from dt_user_role_shopinfo a	left join dt_user_role_audit_list b on b.add_user_id=a.user_id "
				+" left join dt_user_companyinfo c on a.user_id=c.user_id ) t  where 1=1 ");
		
		if (vo.getStart_time() != null && !"".equals(vo.getStart_time())) {
			sql.append(" and t.add_time >= '" + vo.getStart_time() + "'");
		}
		if (vo.getEnd_time() != null && !"".equals(vo.getEnd_time())) {
			sql.append(" and t.add_time <= '" + vo.getEnd_time() + "'");
		}
		if (vo.getName() != null && !"".equals(vo.getName())) {
			sql.append(" and t.name like '%" +vo.getName()+"%'");
		}
		if (vo.getStoreName() != null && !"".equals(vo.getStoreName())) {
			sql.append(" and t.storeName like '%" +vo.getStoreName()+"%'");
		}
		if (vo.getMsg_mobile() != null && !"".equals(vo.getMsg_mobile())) {
			sql.append(" and t.msg_mobile like '%" +vo.getMsg_mobile()+"%'");
		}
		if (vo.getBusiness_name() != null && !"".equals(vo.getBusiness_name())) {
			sql.append(" and t.business_name like '%" +vo.getBusiness_name()+"%'");
		}
		if (vo.getAudit_name() != null && !"".equals(vo.getAudit_name())) {
			sql.append(" and t.audit_name like '%" +vo.getAudit_name()+"%'");
		}
		if (vo.getRemark() != null && !"".equals(vo.getRemark())) {
			sql.append(" and t.remark like '%" +vo.getRemark()+"%'");
		}
		if (vo.getStatus() != null && !"100".equals(vo.getStatus().toString())) {
			sql.append(" and t.state = '" + vo.getStatus() + "'");
		}
		
		sql.append(" order by t.add_time desc");
		
		Integer pageNumber = vo.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getShopInfo(pageNumber, vo.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 入驻信息
	 * @param vo
	 * @return
	 */
	public Page<Record> getEnterInfo(EnterPageVo vo) {
		
		StringBuffer sql = new StringBuffer(" from (select isnull(u.user_name,'-') user_name,isnull(a.name,'-') name,isnull(a.storeName,'-') storeName,isnull(a.msg_mobile,'-') msg_mobile,isnull(a.remark,'-') remark,isnull(a.update_time,'') update_time,isnull(a.state,'-') state,"
							+"isnull((select title from dt_article_category where id=a.main_business),'-') as shop_type,"
							+"isnull((select mobile from dt_users where id=l.bus_user_id),'-') as bus_mobile "
							+"from dt_user_role_shopinfo_temp a "
							+"left join dt_users u on a.user_id=u.id " 
							+"left join dt_user_role_audit_list l on a.user_id=l.add_user_id) t where t.remark like '%Time%' ");
		
		if (vo.getState() != null && !"".equals(vo.getState())) {
			sql.append(" and t.state = " + vo.getState() );
		}
		if (vo.getUser_name() != null && !"".equals(vo.getUser_name())) {
			sql.append(" and t.user_name like '%" + vo.getUser_name() + "%'");
		}
		if (vo.getStoreName() != null && !"".equals(vo.getStoreName())) {
			sql.append(" and t.storeName like '%" + vo.getStoreName() + "%'");
		}
		if (vo.getRemark() != null && !"".equals(vo.getRemark())) {
			sql.append(" and t.remark like '%" + vo.getRemark() + "%'");
		}
		if (vo.getStart_time() != null && !"".equals(vo.getStart_time())) {
			sql.append(" and t.update_time >= '" + vo.getStart_time() + "'");
		}
		if (vo.getEnd_time() != null && !"".equals(vo.getEnd_time())) {
			sql.append(" and t.update_time <= '" + vo.getEnd_time() + "'");
		}
		if (vo.getShop_name() != null && !"".equals(vo.getShop_name())) {
			sql.append(" and t.name like '%"+vo.getShop_name()+"%'");
		}
		if (vo.getMobile() != null && !"".equals(vo.getMobile())) {
			sql.append(" and t.msg_mobile like '%"+vo.getMobile()+"%'");
		}
		if (vo.getBusiness_mobile() != null && !"".equals(vo.getBusiness_mobile())) {
			sql.append(" and t.bus_mobile like '%"+vo.getBusiness_mobile()+"%'");
		}
		
		sql.append(" order by t.update_time desc");
		
		Integer pageNumber = vo.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getEnterInfo(pageNumber, vo.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 商品审核
	 * @param vo
	 * @return
	 */
	public Page<Record> getGoodsInfo(GoodsPageVo vo){
		
		StringBuffer sql = new StringBuffer(" from (select isnull(u.user_name,'-') user_name,isnull(a.title,'-') as article_title,isnull(a.user_id,'-') user_id,"
							
				
							+"isnull(c.title,'-') title,"
							+"isnull(c.class_list,'') class_list,"
							+"isnull(c.class_layer,'') class_layer,"
							
							/*+" WITH aa AS (SELECT * FROM dt_article_category  WHERE id=a.category_id "
							+" UNION ALL SELECT ss.* FROM dt_article_category AS ss,aa WHERE ss.id=aa.parent_id)"

							+" SELECT STUFF((SELECT ','+ title FROM aa for xml path('')),1,1,'') as category"*/
							/*+"WITH a AS (SELECT * FROM dt_article_category  WHERE id=803"
						    +"UNION ALL SELECT s.* FROM dt_article_category AS s,a WHERE s.id=a.parent_id)"
						    +"select title from a,"*/
							
							+"isnull(a.remark,'-') remark,isnull(a.update_time,'') update_time,isnull(a.add_time,'') add_time,a.status,"
							+"isnull(s.name,'-') as name, "
							+"isnull((select user_name from dt_users where id=l.audit_user_id),'-') as audit_name,"
							+"isnull((select user_name from dt_users where id=l.input_user_id),'-') as input_name "
							+"from dt_article a "
							+"left join dt_article_category c on a.category_id=c.id "
							+"left join dt_users u on a.user_id=u.id "
							+"left join dt_user_role_shopinfo s on a.user_role_value=s.id "
							+"left join dt_user_role_audit_list l on a.user_id=l.add_user_id ) t where 1=1 ");
		
		if (vo.getArticle_title() != null && !"".equals(vo.getArticle_title())) { //商品名称
			sql.append(" and t.article_title like '%"+vo.getArticle_title()+"%'");
		}
		if (vo.getTitle() != null && !"".equals(vo.getTitle())) { //行业类别
			sql.append(" and t.title like '%"+vo.getTitle()+"%'");
		}
		if (vo.getUser_name() != null && !"".equals(vo.getUser_name())) { //商家名称
			sql.append(" and t.user_name like '%"+vo.getUser_name()+"%'");
		}
		if (vo.getName() != null && !"".equals(vo.getName())) { //店铺名称
			sql.append(" and t.name like '%"+vo.getName()+"%'");
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
		
		Integer pageNumber = vo.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getGoodsInfo(pageNumber, vo.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 变更公司信息
	 * @param name
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> getCompanyInfo(String name,String shop_name,String store_name,String audit_name,Integer pageNumber){
		
		StringBuffer sql = new StringBuffer(" from (select isnull(s.safe_state,'-') safe_state,isnull(u.user_name,'-') user_name,isnull(f.name,'-') name,"
								+ " isnull(s.update_time,'') update_time,isnull(s.update_remark,'-') update_remark,isnull(s.safe_value,'-') safe_value,isnull((select user_name from dt_users where id=l.audit_user_id),'-') as audit_name"
								+ " from dt_user_safeprotect s left join dt_user_role_audit_list l on s.user_id=l.add_user_id "
								+ " left join dt_users u on s.user_id=u.id left join dt_user_role_shopinfo f on s.user_id=f.user_id "
								+ " where s.safe_type='COMPANY') t where 1=1 ");
		
		if (audit_name != null && !"".equals(audit_name)) {
			
			sql.append(" and t.audit_name like '%" +audit_name+ "%'");
		}
		if (shop_name != null && !"".equals(shop_name)) {
			
			sql.append(" and t.user_name like '%" +shop_name+ "%'");
		}
		if (store_name != null && !"".equals(store_name)) {
			
			sql.append(" and t.name like '%" +store_name+ "%'");
		}
		if (name != null && !"".equals(name)) {
			
			String company ="'%\"name\"%"+name+"%\"area\"%'";
			sql.append(" and t.safe_value like " + company);
		}
		
		sql.append(" order by t.update_time desc");
		
		PageModel pageModel = new PageModel();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getCompanyInfo(pageNumber, pageModel.getSize(), sql.toString());
		
		return page;
		
	}
	
	/**
	 * 店铺变更信息
	 * @param vo
	 * @return
	 */
	public Page<Record> getStoreInfo(StorePageVo vo){
		
		StringBuffer sql = new StringBuffer(" from (select isnull(a.user_id,'-') user_id,isnull(a.name,'-') name,isnull(content,'-') content,isnull(a.pc_logo,'-') pc_logo,isnull(a.wap_logo,'-') wap_logo,isnull(a.mobile,'-') mobile,isnull(a.address,'-') address,isnull(a.area,'-') area,a.state,isnull(a.remark,'-') remark,isnull(a.update_time,'') update_time,isnull(a.main_business,'-') main_business,isnull(a.storeName,'-') storeName,"
								+ "isnull((select user_name from dt_users where id=l.audit_user_id),'-') as audit_name "
								+ " from dt_user_role_shopinfo_temp a left join dt_user_role_audit_list l on a.user_id=l.add_user_id) t where 1=1 ");
		
		if (vo.getName() != null && !"".equals(vo.getName())) {
			
			sql.append(" and t.name like '%"+vo.getName()+"%'");
		}
		
		if (vo.getAudit_name() != null && !"".equals(vo.getAudit_name())){
			
			sql.append(" and t.audit_name like '%"+vo.getAudit_name()+"%'");
		}
		
		if (vo.getStart_time() != null && !"".equals(vo.getStart_time())){
			
			sql.append(" and t.update_time >= '" +vo.getStart_time()+"'");
		}
		
		if (vo.getEnd_time() != null && !"".equals(vo.getEnd_time())){
			
			sql.append(" and t.update_time <= '" +vo.getEnd_time()+"'");
		}
		sql.append(" order by t.update_time desc");
		
		Integer pageNumber = vo.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getStoreInfo(pageNumber, vo.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 经营范围
	 * @param name
	 * @param audit_name
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> getScopeInfo(String name,String audit_name,Integer pageNumber){
		
		StringBuffer sql = new StringBuffer(" from (select isnull(s.user_id,'-') user_id,isnull(s.user_role_type,'-') user_role_type,isnull(s.user_role_value,'-') user_role_value,isnull(s.scope_values,'-') scope_values,isnull(s.status,'-') status,isnull(s.local_area,'-') local_area,isnull(s.remark,'-') remark,isnull(c.title,'-') title,isnull(f.name,'-') name,isnull((select user_name from dt_users where id=l.audit_user_id),'-') as audit_name "
				+ "from dt_user_role_shopinfo_scope s left join dt_article_category c on s.scope_values=c.id  "
				+ "left join dt_user_role_shopinfo f on s.user_role_value= f.id left join dt_user_role_audit_list l on s.user_id=l.add_user_id) t where 1=1 ");
		
		if (name != null && !"".equals(name)) {
			
			sql.append(" and t.name like '%"+name+"%'");
		}
		
		if (audit_name != null && !"".equals(audit_name)) {
			
			sql.append(" and t.audit_name like '%"+audit_name+"%'");
		}
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getScopeInfo(pageNumber, new PageModel().getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 证件信息
	 * @param name
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> getCardInfo(String name,String title,String start_time,String end_time,Integer pageNumber){
		
		StringBuffer sql = new StringBuffer(" from dt_user_role_shopinfo_other o left join dt_user_role_shopinfo s on o.shop_id = s.user_id where 1=1 ");
			
		if (name != null && !"".equals(name)) {
			
			sql.append(" and s.name like '%"+name+"%'");
		}
		if (title != null && !"".equals(title)) {
			
			sql.append(" and o.title like '%"+title+"%'");
		}
		if (start_time != null && !"".equals(start_time)) {
			
			sql.append(" and o.add_time >= '" +start_time+ "'");
		}
		if (end_time != null && !"".equals(end_time)) {
			
			sql.append(" and o.add_time < '" +end_time+ "'");
		}
		
		sql.append(" order by o.add_time desc ");
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getCardInfo(pageNumber, new PageModel().getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 订单信息
	 * @param vo
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> getOrderInfo(OrderPageVo vo){
		
		StringBuffer sql = new StringBuffer(" from (select isnull((select user_name from dt_users where id=o.user_id),'-') as buyer_name,isnull((select user_name from dt_users where id=o.shop_user_id),'-') as shop_name,"
								+"isnull((select mobile from dt_users where id=o.shop_user_id),'-') as mobile,"
								+"isnull(o.order_no,'-') order_no,isnull(o.order_amount,0) order_amount,isnull(o.add_time,'') add_time,isnull(g.express_status,'-') express_status,"
								+"isnull(o.status,'-') status,isnull(f.name,'-') name,isnull(o.book_back_status,'-') book_back_status,isnull(b.status,'') as back_status from " 
								+"dt_orders o left join dt_users u on o.shop_user_id=u.id "
								+"left join dt_order_goodinfo g on o.id=g.order_id "
								+"left join dt_user_role_shopinfo f on o.shop_user_role_value=f.id and o.shop_user_role_type='Shop' "
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
		if (vo.getOrder_no() != null && !"".equals(vo.getOrder_no())) {
			
			sql.append(" and t.order_no like '%"+vo.getOrder_no()+"%'");
		}
		if (vo.getBuyer_name() != null && !"".equals(vo.getBuyer_name())) {
			
			sql.append(" and t.buyer_name like '%"+vo.getBuyer_name()+"%'");
		}
		if (vo.getExpress_status() != null && !"".equals(vo.getExpress_status())) {
			
			sql.append(" and t.express_status = '"+vo.getExpress_status()+"'");
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
		
		Integer pageNumber = vo.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = respository.getOrderInfo(pageNumber, vo.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 审核信息
	 * @param as
	 * @return
	 */
	public Page<Record> auditInfo(AuditStatistics as){
		
		StringBuffer sql = new StringBuffer(" from dt_audits where 1=1 ");
		
		if(as.getExamine_type() != null && !"".equals(as.getExamine_type())){
			
			sql.append(" and examine_type = '" +as.getExamine_type()+ "'");
		}
		
		if(as.getExamine_name() != null && !"".equals(as.getExamine_name())){
			
			sql.append(" and examine_name like '%"+as.getExamine_name()+"%'");
		}
		
		if(as.getStart_time() != null && !"".equals(as.getStart_time())){
			
			sql.append(" and examine_time >= '" +as.getStart_time()+ "'");
		}
		
		if(as.getEnd_time() != null && !"".equals(as.getEnd_time())){
			
			sql.append(" and examine_time <= '" +as.getEnd_time()+ "'");
		}
		
		sql.append(" order by examine_time desc");
		
		Integer pageNumber = as.getPageNumber();
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = asr.auditInfo(pageNumber, as.getSize(), sql.toString());
		
		return page;
	}
	
	/**
	 * 审核信息
	 * @param start_time
	 * @param end_time
	 * @param pageNumber
	 * @return
	 */
	public Page<Record> auditSta(String start_time,String end_time,String audit_name,Integer type,Integer pageNumber){
		
		StringBuffer sql = new StringBuffer(" from (select k.*,isnull(p.person_name,'-') person_name from (select a.*,b.id from (select examine_name,count(examine_name) total from dt_audits where 1=1 ");
		
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
		
		sql.append(" group by examine_name) a left join (select distinct user_name,id from dt_users) b on a.examine_name =b.user_name   ) k left join (select distinct user_id,person_name from dt_user_personinfo) p on k.id =  p.user_id ) t order by examine_name");
		
		
		pageNumber = pageNumber == null ? 1 : pageNumber;
		
		Page<Record> page = asr.auditSta(pageNumber, new PageModel().getSize(), sql.toString());
		
		return page;
	}
	
	
	/**
	 * 根据id查 分类名称
	 */
	
	public String getCategory(String id){
		
		String sql = "select title from dt_article_category where id ="+id;
		
		String select = Db.queryStr(sql);
		
		return select;
	}
	
	/**
	 * 根据Parent_id查询title
	 */
	
	public List<Record> getTitle(Integer parent_id){
		
		String sql = "select id,title from dt_article_category where parent_id="+parent_id;
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
}
