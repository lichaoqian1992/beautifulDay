package com.manji.lineol.service;

import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.vo.UserQueueInfoVo;
import com.manji.lineol.vo.UserShopQueueVo;

public interface CustomerService {

	/**
	 * 用户查看商家排队信息
	 * 
	 * @param sessionId
	 * @param shopId
	 * @return
	 */
	public UserShopQueueVo customerQuery(String sessionId, String shopId);

	/**
	 * 查看用户的排队信息
	 * @param sessionId
	 * @param shopId
	 * @return
	 */
	public UserQueueInfoVo customerQueryMyInfo(String sessionId, String shopId);
	
	
	/**
	 * 用户预约
	 * @param shopId
	 * @param queueTypeAs
	 * @param userName
	 * @return
	 */
	public BaseResult customerSubscribe(String shopId, String queueTypeAs, String userName);
	
	
	/**
	 * 用户取消预约
	 * @param shopId
	 * @param queueTypeAs
	 * @param userName
	 * @return
	 */
	public BaseResult customerCancelSubscribe(String shopId, String queueTypeAs, String userName);
	
	
	/**
	 * 查询用户是否预约
	 * @param userName
	 * @return
	 */
	public BaseResult queryUserWhetherBook(String shopId,String userName);
	
	
	/**
	 * 查询用户的所有排队信息
	 * @param sessionId
	 * @return
	 */
	public BaseResult queryUserQueueAllInfo(String sessionId);

}
