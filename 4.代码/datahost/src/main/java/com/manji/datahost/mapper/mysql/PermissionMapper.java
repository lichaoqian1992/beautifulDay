package com.manji.datahost.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {

	//接口权限控制
	Integer getConnector(String interface_name);
}
