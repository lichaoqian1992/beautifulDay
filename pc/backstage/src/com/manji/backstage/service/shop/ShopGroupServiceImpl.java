package com.manji.backstage.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.dto.shop.ShopGroupCountsDto;
import com.manji.backstage.mapper.shop.ShopGroupMapper;
import com.manji.backstage.model.shop.Group;
@Service
public class ShopGroupServiceImpl implements ShopGroupService {

	@Autowired
	private ShopGroupMapper mapper;
	
	@Override
	public List<Group> countShopGroup(){
		List<Group> list =  mapper.countShopGroup();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<Group> selShopGroups() {

		return mapper.selShopGroups();
	}

	@Override
	public List<Group> findShopGroup() {

		return mapper.findShopGroup();
	}
	
	@Override
	public void addShopGroup(Group group) {

		mapper.addShopGroup(group);
		
	}


	@Override
	public boolean updShopGroup(Group group) {

		if(mapper.updShopGroup(group)>0){
			return true;
		}
		 
		return false;
	}


	@Override
	public boolean delShopGroup(int id) {
		
		if(mapper.delShopGroup(id)>0){
			return true;
		}

		return false;
	}


	@Override
	public List<ShopGroupCountsDto> shopGroupCounts() {

		return mapper.shopGroupCounts();
	}


	@Override
	public List<ShopGroupCountsDto> lastShopGroupCounts() {

		return mapper.lastShopGroupCounts();
	}


	


	@Override
	public Group getShopGroup(int id) {
		// TODO Auto-generated method stub
		return mapper.getShopGroup(id);
	}


	@Override
	public List<Group> queryShopById(String id) {
		// TODO Auto-generated method stub
		return mapper.queryShopById(Integer.valueOf(id));
	}


	@Override
	public List<Group> getShopGroupList() {
		return mapper.getShopGroupList();
	}


	
	
	
}
