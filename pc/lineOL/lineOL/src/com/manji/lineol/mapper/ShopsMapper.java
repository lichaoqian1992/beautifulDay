package com.manji.lineol.mapper;

import java.util.List;
import java.util.Map;

import com.manji.lineol.model.Shops;

public interface ShopsMapper {
	
	Shops queryShopsById(String shopId);
	
	void insertShops(Shops shops);
	
	int modfiyShops(Map<String, String> map);
	
	List<Shops> queryShopsByIdAndonoff();
	
	

}
