package com.manji.backstage.service.shop;

import java.util.List;

import com.manji.backstage.dto.shop.ShopGroupCountsDto;
import com.manji.backstage.model.shop.Group;

public interface ShopGroupService {
	
	List<Group> countShopGroup();

	List<Group> selShopGroups();
	
	List<Group> findShopGroup();
	
	void addShopGroup(Group group);
	
	boolean updShopGroup(Group group);
	
	boolean delShopGroup(int id);
	
	List<ShopGroupCountsDto> shopGroupCounts();
	
	List<ShopGroupCountsDto> lastShopGroupCounts();

	Group getShopGroup(int id);

	List<Group> queryShopById(String id);

	List<Group> getShopGroupList();
	
	
}
