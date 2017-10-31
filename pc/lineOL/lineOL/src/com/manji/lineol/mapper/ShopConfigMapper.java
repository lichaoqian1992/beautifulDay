package com.manji.lineol.mapper;

import java.util.List;

import com.manji.lineol.common.reqparam.ShopConfigAddReqParam;
import com.manji.lineol.common.reqparam.ShopConfigModifyReqParam;
import com.manji.lineol.model.ShopConfig;

public interface ShopConfigMapper {
	
	List<ShopConfig> queryShopConfigByShopId(String shopId);
	
	void addShopConfig(ShopConfigAddReqParam addReqParam);
	
	int modifyShopConfig(ShopConfigModifyReqParam modifyParam);
	
	boolean removeShopConfig(String id);

	ShopConfig queryShopConfigInfoById(String id);
	
	
	

}
