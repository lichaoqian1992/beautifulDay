package com.manji.lineol.service;

import com.manji.lineol.common.reqparam.ShopConfigAddReqParam;
import com.manji.lineol.common.reqparam.ShopConfigModifyReqParam;
import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.vo.ShopQueueVo;

public interface ShopService {
	
	/**
	 * 商家初始化与排队信息获取
	 * @return
	 */
	public ShopQueueVo shopInit(String shopId);
	
	/**
	 * 查询商户配置信息
	 * @param shopId
	 * @return
	 */
	public BaseResult queryShopConfigInfo(String shopId);
	
	
	/**
	 * 新增商户配置信息
	 * @param shopConfigReqParam
	 * @return
	 */
	public BaseResult addShopConfigInfo(ShopConfigAddReqParam shopConfigReqParam);
	
	
	/**
	 * 修改商户配置信息
	 * @param shopConfigReqParam
	 * @return
	 */
	public BaseResult modifyShopConfigInfo(ShopConfigModifyReqParam shopConfigModifyReqParam);
	
	/**
	 * 删除商户配置信息
	 * @param id
	 * @return
	 */
	public BaseResult removeShopConfigInfo(String id,String shopId);
	
	
	/**
	 * 开启或关闭排队服务
	 * @param serviceType
	 * @param shopId
	 * @return
	 */
	public BaseResult enableOrDisableQueueService(String serviceType,String shopId);
	
	
	/**
	 * 查询商家所有排队信息
	 * @param shopId
	 * @return
	 */
	public BaseResult queryShopQueueInfo(String shopId);
	
	
	/**
	 * 叫号服务
	 * @param shopId
	 * @param typeAlias
	 * @return
	 */
	public BaseResult nextNumber(String shopId,String typeAlias);
	
	/**
	 * 查询商户配置信息ID
	 * @param id
	 * @return
	 */
	public BaseResult queryShopConfigInfoById(String id);

}
