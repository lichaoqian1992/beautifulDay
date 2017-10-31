package com.manji.backstage.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.order.OrderMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.order.OrderAdvert;
import com.manji.backstage.model.order.OrderBackInfo;
import com.manji.backstage.model.order.OrderBookInfo;
import com.manji.backstage.model.order.OrderBusiness;
import com.manji.backstage.model.order.OrderCallback;
import com.manji.backstage.model.order.OrderComment;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.model.order.OrderDistributioninfo;
import com.manji.backstage.model.order.OrderGoodInfo;
import com.manji.backstage.model.order.OrderGoods;
import com.manji.backstage.model.order.OrderOnlinePay;
import com.manji.backstage.model.order.OrderPartner;
import com.manji.backstage.model.order.OrderSettlement;
import com.manji.backstage.model.order.OrderSms;
import com.manji.backstage.model.order.OrderVirtualinfo;
import com.manji.backstage.model.order.Orders;
import com.manji.backstage.vo.order.OrderAdvertVo;
import com.manji.backstage.vo.order.OrderBackInfoVo;
import com.manji.backstage.vo.order.OrderBookInfoVo;
import com.manji.backstage.vo.order.OrderBusinessVo;
import com.manji.backstage.vo.order.OrderCallbackVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;
import com.manji.backstage.vo.order.OrderCommentVo;
import com.manji.backstage.vo.order.OrderDistributioninfoVo;
import com.manji.backstage.vo.order.OrderGoodInfoVo;
import com.manji.backstage.vo.order.OrderGoodsVo;
import com.manji.backstage.vo.order.OrderOnlinePayVo;
import com.manji.backstage.vo.order.OrderPartnerVo;
import com.manji.backstage.vo.order.OrderSettlementVo;
import com.manji.backstage.vo.order.OrderSmsVo;
import com.manji.backstage.vo.order.OrderVirtualinfoVo;
import com.manji.backstage.vo.order.OrdersVo;




@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper mapper;
	
	@Override
	public Page<OrderGoods> queryOrderGoods(OrderGoodsVo vo) {
		Page<OrderGoods> page =new Page<OrderGoods>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderGoods(vo);
		List<OrderGoods> list =mapper.queryOrderGoods(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addOrderGoods(OrderGoods og) {
		mapper.addOrderGoods(og);
	}

	@Override
	public OrderGoods getOrderGoods(long id) {
		return mapper.getOrderGoods(id);
	}

	@Override
	public boolean updOrderGoods(OrderGoods og) {
		if(mapper.updOrderGoods(og)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delOrderGoods(long id) {
		if(mapper.delOrderGoods(id)>0){
			return true;
		}
		return false;
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderBookInfo> queryOrderBookInfo(OrderBookInfoVo vo) {
		Page<OrderBookInfo> page =new Page<OrderBookInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderBookInfo(vo);
		List<OrderBookInfo> list =mapper.queryOrderBookInfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderBookInfo(OrderBookInfo obi) {
		mapper.addOrderBookInfo(obi);
	}
	
	@Override
	public OrderBookInfo getOrderBookInfo(long id) {
		return mapper.getOrderBookInfo(id);
	}
	
	@Override
	public boolean updOrderBookInfo(OrderBookInfo obi) {
		if(mapper.updOrderBookInfo(obi)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderBookInfo(long id) {
		if(mapper.delOrderBookInfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderDistributioninfo> queryOrderDistributioninfo(OrderDistributioninfoVo vo) {
		Page<OrderDistributioninfo> page =new Page<OrderDistributioninfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderDistributioninfo(vo);
		List<OrderDistributioninfo> list =mapper.queryOrderDistributioninfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderDistributioninfo(OrderDistributioninfo od) {
		mapper.addOrderDistributioninfo(od);
	}
	
	@Override
	public OrderDistributioninfo getOrderDistributioninfo(long id) {
		return mapper.getOrderDistributioninfo(id);
	}
	
	@Override
	public boolean updOrderDistributioninfo(OrderDistributioninfo od) {
		if(mapper.updOrderDistributioninfo(od)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderDistributioninfo(long id) {
		if(mapper.delOrderDistributioninfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderVirtualinfo> queryOrderVirtualinfo(OrderVirtualinfoVo vo) {
		Page<OrderVirtualinfo> page =new Page<OrderVirtualinfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderVirtualinfo(vo);
		List<OrderVirtualinfo> list =mapper.queryOrderVirtualinfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderVirtualinfo(OrderVirtualinfo ov) {
		mapper.addOrderVirtualinfo(ov);
	}
	
	@Override
	public OrderVirtualinfo getOrderVirtualinfo(long id) {
		return mapper.getOrderVirtualinfo(id);
	}
	
	@Override
	public boolean updOrderVirtualinfo(OrderVirtualinfo ov) {
		if(mapper.updOrderVirtualinfo(ov)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderVirtualinfo(long id) {
		if(mapper.delOrderVirtualinfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderGoodInfo> queryOrderGoodInfo(OrderGoodInfoVo vo) {
		Page<OrderGoodInfo> page =new Page<OrderGoodInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderGoodInfo(vo);
		List<OrderGoodInfo> list =mapper.queryOrderGoodInfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderGoodInfo(OrderGoodInfo ogi) {
		mapper.addOrderGoodInfo(ogi);
	}
	
	@Override
	public OrderGoodInfo getOrderGoodInfo(long id) {
		return mapper.getOrderGoodInfo(id);
	}
	
	@Override
	public boolean updOrderGoodInfo(OrderGoodInfo ogi) {
		if(mapper.updOrderGoodInfo(ogi)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderGoodInfo(long id) {
		if(mapper.delOrderGoodInfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderSms> queryOrderSms(OrderSmsVo vo) {
		Page<OrderSms> page =new Page<OrderSms>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderSms(vo);
		List<OrderSms> list =mapper.queryOrderSms(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderSms(OrderSms os) {
		mapper.addOrderSms(os);
	}
	
	@Override
	public OrderSms getOrderSms(long id) {
		return mapper.getOrderSms(id);
	}
	
	@Override
	public boolean updOrderSms(OrderSms os) {
		if(mapper.updOrderSms(os)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderSms(long id) {
		if(mapper.delOrderSms(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderAdvert> queryOrderAdvert(OrderAdvertVo vo) {
		Page<OrderAdvert> page =new Page<OrderAdvert>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderAdvert(vo);
		List<OrderAdvert> list =mapper.queryOrderAdvert(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderAdvert(OrderAdvert oa) {
		mapper.addOrderAdvert(oa);
	}
	
	@Override
	public OrderAdvert getOrderAdvert(long id) {
		return mapper.getOrderAdvert(id);
	}
	
	@Override
	public boolean updOrderAdvert(OrderAdvert oa) {
		if(mapper.updOrderAdvert(oa)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderAdvert(long id) {
		if(mapper.delOrderAdvert(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderBusiness> queryOrderBusiness(OrderBusinessVo vo) {
		Page<OrderBusiness> page =new Page<OrderBusiness>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderBusiness(vo);
		List<OrderBusiness> list =mapper.queryOrderBusiness(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderBusiness(OrderBusiness ob) {
		mapper.addOrderBusiness(ob);
	}
	
	@Override
	public OrderBusiness getOrderBusiness(long id) {
		return mapper.getOrderBusiness(id);
	}
	
	@Override
	public boolean updOrderBusiness(OrderBusiness ob) {
		if(mapper.updOrderBusiness(ob)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderBusiness(long id) {
		if(mapper.delOrderBusiness(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderBackInfo> queryOrderBackInfo(OrderBackInfoVo vo) {
		Page<OrderBackInfo> page =new Page<OrderBackInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderBackInfo(vo);
		List<OrderBackInfo> list =mapper.queryOrderBackInfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderBackInfo(OrderBackInfo obi) {
		mapper.addOrderBackInfo(obi);
	}
	
	@Override
	public OrderBackInfo getOrderBackInfo(long id) {
		return mapper.getOrderBackInfo(id);
	}
	
	@Override
	public boolean updOrderBackInfo(OrderBackInfo obi) {
		if(mapper.updOrderBackInfo(obi)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderBackInfo(long id) {
		if(mapper.delOrderBackInfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<Orders> queryOrders(OrdersVo vo) {
		Page<Orders> page =new Page<Orders>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrders(vo);
		List<Orders> list =mapper.queryOrders(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrders(Orders orders) {
		mapper.addOrders(orders);
	}
	
	@Override
	public Orders getOrders(long id) {
		return mapper.getOrders(id);
	}
	
	@Override
	public boolean updOrders(Orders orders) {
		if(mapper.updOrders(orders)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrders(long id) {
		if(mapper.delOrders(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderCallback> queryOrderCallback(OrderCallbackVo vo) {
		Page<OrderCallback> page =new Page<OrderCallback>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderCallback(vo);
		List<OrderCallback> list =mapper.queryOrderCallback(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderCallback(OrderCallback oc) {
		mapper.addOrderCallback(oc);
	}
	
	@Override
	public OrderCallback getOrderCallback(long id) {
		return mapper.getOrderCallback(id);
	}
	
	@Override
	public boolean updOrderCallback(OrderCallback oc) {
		if(mapper.updOrderCallback(oc)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderCallback(long id) {
		if(mapper.delOrderCallback(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderComment> queryOrderComment(OrderCommentVo vo) {
		Page<OrderComment> page =new Page<OrderComment>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderComment(vo);
		List<OrderComment> list =mapper.queryOrderComment(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderComment(OrderComment oc) {
		mapper.addOrderComment(oc);
	}
	
	@Override
	public OrderComment getOrderComment(int id) {
		return mapper.getOrderComment(id);
	}
	
	@Override
	public boolean updOrderComment(OrderComment oc) {
		if(mapper.updOrderComment(oc)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderComment(int id) {
		if(mapper.delOrderComment(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderSettlement> queryOrderSettlement(OrderSettlementVo vo) {
		Page<OrderSettlement> page =new Page<OrderSettlement>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderSettlement(vo);
		List<OrderSettlement> list =mapper.queryOrderSettlement(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderSettlement(OrderSettlement os) {
		mapper.addOrderSettlement(os);
	}
	
	@Override
	public OrderSettlement getOrderSettlement(long id) {
		return mapper.getOrderSettlement(id);
	}
	
	@Override
	public boolean updOrderSettlement(OrderSettlement os) {
		if(mapper.updOrderSettlement(os)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderSettlement(long id) {
		if(mapper.delOrderSettlement(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderOnlinePay> queryOrderOnlinePay(OrderOnlinePayVo vo) {
		Page<OrderOnlinePay> page =new Page<OrderOnlinePay>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderOnlinePay(vo);
		List<OrderOnlinePay> list =mapper.queryOrderOnlinePay(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderOnlinePay(OrderOnlinePay oop) {
		mapper.addOrderOnlinePay(oop);
	}
	
	@Override
	public OrderOnlinePay getOrderOnlinePay(int id) {
		return mapper.getOrderOnlinePay(id);
	}
	
	@Override
	public boolean updOrderOnlinePay(OrderOnlinePay oop) {
		if(mapper.updOrderOnlinePay(oop)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderOnlinePay(int id) {
		if(mapper.delOrderOnlinePay(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderPartner> queryOrderPartner(OrderPartnerVo vo) {
		Page<OrderPartner> page =new Page<OrderPartner>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderPartner(vo);
		List<OrderPartner> list =mapper.queryOrderPartner(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderPartner(OrderPartner op) {
		mapper.addOrderPartner(op);
	}
	
	@Override
	public OrderPartner getOrderPartner(int id) {
		return mapper.getOrderPartner(id);
	}
	
	@Override
	public boolean updOrderPartner(OrderPartner op) {
		if(mapper.updOrderPartner(op)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderPartner(int id) {
		if(mapper.delOrderPartner(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo) {
		Page<OrderCommentFalse> page =new Page<OrderCommentFalse>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderCommentFalse(vo);
		List<OrderCommentFalse> list =mapper.queryOrderCommentFalse(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderCommentFalse(OrderCommentFalse ocf) {
		mapper.addOrderCommentFalse(ocf);
	}
	
	@Override
	public OrderCommentFalse getOrderCommentFalse(int id) {
		return mapper.getOrderCommentFalse(id);
	}
	
	@Override
	public boolean updOrderCommentFalse(OrderCommentFalse ocf) {
		if(mapper.updOrderCommentFalse(ocf)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderCommentFalse(int id) {
		if(mapper.delOrderCommentFalse(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo) {
		Page<OrderCommentTem> page =new Page<OrderCommentTem>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderCommentTem(vo);
		List<OrderCommentTem> list =mapper.queryOrderCommentTem(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderCommentTem(OrderCommentTem oct) {
		mapper.addOrderCommentTem(oct);
	}
	
	@Override
	public OrderCommentTem getOrderCommentTem(int id) {
		return mapper.getOrderCommentTem(id);
	}
	
	@Override
	public boolean updOrderCommentTem(OrderCommentTem oct) {
		if(mapper.updOrderCommentTem(oct)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderCommentTem(int id) {
		if(mapper.delOrderCommentTem(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	
	
}
