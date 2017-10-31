package com.manji.mlife.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manji.mlife.model.Airport;

public interface AirMapper {
	/**
	* @Title: getInfoName 
	* @Description: TODO(根据机场的名称查询机场信息) 
	* @param @param info
	* @param @return    入参
	* @return List<Airport>    返回类型
	* @author （刘英杰） 
	* @throws
	* @date 2016年11月14日 上午10:23:24 
	* @version V1.0
	 */
	public List<Airport> getInfoName(String info);
	/**
	* @Title: getInfoId 
	* @Description: TODO(根据机场的简码查询机场信息) 
	* @param @param info
	* @param @return    入参
	* @return List<Airport>    返回类型
	* @author （刘英杰） 
	* @throws
	* @date 2016年11月14日 上午10:23:53 
	* @version V1.0
	 */
	public List<Airport> getInfoId(String info);
	
}
