package com.manji.mlife.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.QueryVo;
import com.manji.mlife.mapper.ChargeOrderMapper;
import com.manji.mlife.mapper.TradeMapper;
import com.manji.mlife.mapper.TrafficOrderMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.PageBean;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.request.AirOrderRefundRequest;
import com.qianmi.open.api.request.TrainOrderRefundRequest;
import com.qianmi.open.api.response.AirOrderRefundResponse;
import com.qianmi.open.api.response.TrainOrderRefundResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private ChargeOrderMapper chargeMapper;
	@Autowired
	private TrafficOrderMapper trafficMapper;
	//平台公共service
	@Autowired	
	private DBService service;
	
	private static final Logger logger =Logger.getLogger(OrderServiceImpl.class);

	
	
	@Override
	public String getChargeDetail(String outerId) {
		
//		Trade trade =tradeMapper.selectByPrimaryKey(billId);
		ChargeOrder bill =chargeMapper.selectByOuterId(outerId);
		
//		logger.info("getChargeDetail"+);
		return JSONObject.fromObject(bill).toString();
	}


	@Override
	public String getTrafficDetail(String outerId) {
		
		List<TrafficOrder> list =trafficMapper.selectByPrimaryKey2(outerId);
		
		
		return JSONArray.fromObject(list).toString();
	}
	
	@Override
	public List<Trade> getChargeBills(QueryVo vo, int begin, int limit) {
		
		String billsJson ="";
		
		List<Trade> chargeBills =tradeMapper.queryChargeBills(vo,begin,limit);
		
		
		return chargeBills;
	}
	
	
	@Override
	public String getTrafficBills(QueryVo vo , int begin , int limit,PageBean<Trade> pageBean) {
		
		String billsJson ="";
		
		List<Trade> trafficBills =tradeMapper.queryTrafficBills(vo,begin,limit);
		pageBean.setList(trafficBills);
		billsJson =pageBean.toString();
		return billsJson;
	}
	
	/**
	 * 充值订单详情
	 * @author gaochao
	 * @param vo
	 * @return
	 * 2016年9月23日下午5:11:25
	 *
	 */
	@Override
	public String getChargeBillsShowDetail(QueryVo vo) {
		String billsJson ="";
		
		List<Trade> chargeBills =tradeMapper.queryChargeBillsShowDetail(vo);
		
		billsJson =JSONArray.fromObject(chargeBills).toString();
		
		logger.info("getChargeBillsShowDetail"+billsJson);
		
		return billsJson;
	}


	/**
	 * 查询订单详细信息根据tradeno
	 */
	@Override
	public String getbillDetails(String tradeno){
		List<TrafficOrder> list =trafficMapper.selectByPrimaryKey2(tradeno);
		String name = "";
		String idcardid = "";
		String orderid = "";
		String paycash = "";
		String seatstate = "";
		if(list.size()!=0){
			
		//无非就是乘客、身份证号码、订单号不一样,支付金额
		for(int i=0;i<list.size();i++){
			name += list.get(i).getPassengername()+",";
			idcardid += list.get(i).getIdcardno()+",";
			orderid += list.get(i).getOrderno()+",";
			paycash += list.get(i).getTotalpaycash()+",";
			seatstate += list.get(i).getSeatname()+",";
		}
		
		list.get(0).setPassengername(name);
		list.get(0).setIdcardno(idcardid);
		list.get(0).setOrderno(orderid);
		list.get(0).setPaycash(paycash);
		list.get(0).setSeatname(seatstate);
		
		}
		return JSONArray.fromObject(list).toString();
	}

	/**
	 *退款；tradeNo:主订单的id，orderId:子订单的id
	 */
	@Override
	public String getairPayBack(String tradeNo, String orderId,String returnType){
		/**
		 * 当用户发起退款请求的时候，订单的状态就变成退款中，当千米那边成功的把金额退还的时候，订单的状态就变成已退款。。。并且还要把对应账户的金额还回去
		 * 因为退款是把主订单的状态改成退款中，成功以后直接变成已退款
		 */
		List<TrafficOrder> too = trafficMapper.selectByPrimaryKey2(tradeNo);//查询出子订单的数据，是一个集合
		
		String state = null;
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		AirOrderRefundRequest req = new AirOrderRefundRequest();
		req.setOrderNos(orderId);//子订单的ID
		req.setTradeNo(tradeNo);//主订单的id
		
		if(returnType.equals("3")){
			
			req.setReturnType("3");//3表示退票的类型是完好的退票，不是退废票
		}else{
			req.setReturnType("1");//退废票
		}
		
		AirOrderRefundResponse response;
		try {
			response = client.execute(req, map.get("accessToken"));
			String result = response.getResult();//返回的结果
			String message = response.getSubMsg();//得到错误信息
			if("1".equals(result)){
				state = "1";
			}else{
				state = message;
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	/**
	 * //火车退票
	 * @author gaochao
	 * @param newOrderId
	 * @return
	 * 2016年9月14日下午2:03:27
	 *
	 */
	@Override
	public String gettrainPayBack(String tradeNo , String newOrderId) {
		
		Trade to = tradeMapper.selectByPrimaryKeyTradeNo(tradeNo);//查询出主订单的数据，只有一条数据
		
		List<TrafficOrder> too = trafficMapper.selectByPrimaryKey2(tradeNo);//查询出子订单的数据，是一个集合
		
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		TrainOrderRefundRequest req = new TrainOrderRefundRequest();
		req.setOrderNos(newOrderId);
		TrainOrderRefundResponse response=null;
		String state = null;
		try {
			response = client.execute(req, map.get("accessToken"));
			String result = response.getResult();//返回的结果
			String message = response.getSubMsg();//得到错误信息
			if("1".equals(result)){
				state = "1";
			}else{
				state = message;
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
		
	}
	
	

	@Override
	public Trade queryBytradeNo(String tradeNo) {
		return tradeMapper.selectByPrimaryKeyTradeNo(tradeNo);//查询出主订单的数据，只有一条数据
	}


	@Override
	public void updateStatus(Trade to) {
		tradeMapper.updateByPrimaryKey(to);
	}


	@Override
	public List<TrafficOrder> queryBychild(String tradeno) {
		return trafficMapper.selectByPrimaryKey2(tradeno);//查询出子订单的数据，是一个集合
	}


	@Override
	public void updatechild(TrafficOrder trafficOrder) {
		trafficMapper.updateByPrimaryKey(trafficOrder);
	}

	/**
	 * 根据用户名查询相应的充值订单总数
	 * @author gaochao
	 * @param userName
	 * @return
	 * 2016年9月22日上午10:07:52
	 *
	 */
	@Override
	public int findCountCid(QueryVo vo) {
		
		int findCountCid = tradeMapper.findCountCid(vo);
		
		if(findCountCid!=0){
			
			return findCountCid;
		}
		return 0;
	}
	/**
	 * 查询票务订单的总条数
	 * @param userName
	 * @return
	 */
	public int findCountCid2(QueryVo vo) {
		
		int findCountCid = tradeMapper.findCountCid2(vo);
		
		if(findCountCid!=0){
			
			return findCountCid;
		}
		return 0;
	}
	
	/**
	 * 根据outetId查询详细信息
	 */
	@Override
	public List<ChargeOrder> queryByOuterId(String outerId) {
		List<ChargeOrder> list = chargeMapper.selectByPrimaryKey2(outerId);
		return list;
	}

	/**
	 * 删除chargeOrder表中的数据
	 */
	@Override
	public void deleteByKey(String tradeno) {
		chargeMapper.deleteByPrimaryKey2(tradeno);
	}

	/**
	 * 删除trade表中的数据
	 */
	@Override
	public void deleteByKey2(String orderId) {
		tradeMapper.deleteByPrimaryKey(orderId);
	}
	/**
	 * 删除trafficOrder表中的数据
	 */
	@Override
	public void deleteByKey3(String tradeNo){
		int a = trafficMapper.deleteByPrimaryKey(tradeNo);
		System.out.println(a);
	}

	@Override
	public List<Trade> queryByOuterId2(String outerId) {
		List<Trade> list = tradeMapper.selectByPrimaryKey2(outerId);
		return list;
	}




}
