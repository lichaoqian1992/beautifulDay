package com.manji.backstage.mapper.shop;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manji.backstage.dto.shop.ShopGroupCountsDto;
import com.manji.backstage.model.shop.Group;

public interface ShopGroupMapper {

	List<Group> countShopGroup();
	
	List<Group> selShopGroups();
	
	List<Group> findShopGroup();
	
	void addShopGroup(Group group);
	
	int updShopGroup(Group group);
	
	int delShopGroup(int id);
	
	
	
	List<ShopGroupCountsDto> shopGroupCounts();
	
	List<ShopGroupCountsDto> lastShopGroupCounts();


	Group getShopGroup(int id);

	List<Group> queryShopById(int id);

	List<Group> getShopGroupList();	
	
}
