package com.manji.mlife.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.manji.mlife.model.AirAppBean;
import com.manji.mlife.model.Items;
import com.manji.mlife.model.State;
import com.qianmi.open.api.ApiException;

/**
 * 
 * @author gaochao
 * 2016年7月7日下午4:25:22
 * AirService
 * 订购飞机票
 */
public interface AirService {

	/**
	 * //更改交易流水
	  //订单状态中文描述：0：预定中,2：预定完成，1：已完成，9：已取消
	 * @author gaochao
	 * 2016年7月11日下午2:43:21
	 * void
	 * @param tradeNo 
	 * @param state 
	 *
	 */
	void updateTrade(State s);
	
	/**
	 * //查询航线列表
	 * @author gaochao
	 * @param from
	 * @param to
	 * @param date
	 * @param end 
	 * @param start 
	 * @return
	 * 2016年8月18日下午3:58:28
	 * String
	 *
	 */
	String getAirLineslist(String from, String to, String date, String start, String end);
	/**
	 * （飞机票订单生成）
	 * @author gaochao
	 * @param ttems
	 * 2016年8月19日下午4:35:34
	 * void
	 * @param userName 
	 * @param session 
	 * @return 
	 * @throws ApiException 
	 *
	 */
	Map<String, String> getAirOrderCreate(Items ttems, String userName, HttpSession session) throws ApiException;
	
	
	Map<String, String>getAirAppOrderCreate(AirAppBean bean,String userName,HttpSession session)throws ApiException;
	
	
	/**
	 * 取消预定订单
	 * @author gaochao
	 * @param tradeNo
	 * 2016年9月2日下午4:11:51
	 * void
	 * @return 
	 * @throws ApiException 
	 *
	 */
	String getAirOrderCancelRequest(String tradeNo) throws ApiException;
	
	/**
	* @Title: getAirport 
	* @Description: TODO(根据城市查询机场信息) 
	* @param @param city
	* @param @return    入参
	* @return String    返回类型
	* @author （刘英杰） 
	* @throws
	* @date 2016年11月11日 上午9:30:52 
	* @version V1.0
	 */
	String getAirport(String info);
	
	/**
	 * 退订飞机票订单
	 * @author gaochao
	 * @param tradeNo
	 * @param returnType
	 * @param orderNos
	 * 2016年9月13日下午12:14:27
	 * void
	 * @return 
	 * @throws ApiException 
	 *
	 */
	//String AirOrderRefundRequest(String tradeNo, String returnType, String[] orderNos) throws ApiException;

}
