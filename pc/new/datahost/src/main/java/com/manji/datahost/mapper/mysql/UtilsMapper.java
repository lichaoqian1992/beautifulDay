package com.manji.datahost.mapper.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.manji.datahost.model.mysql.CallRegister;
import com.manji.datahost.model.mysql.SheetInfo;
@Mapper
public interface UtilsMapper {

	//来电记录
	List<CallRegister> callRegister(@Param("pageNumber") Integer pageNumber,@Param("pageSize") Integer pageSize,@Param("mobile") String mobile);
	int countCallRegister(String mobile);
	
	//工单信息
	List<SheetInfo> sheetInfo(@Param("mobile") String mobile,@Param("pageNumber") Integer pageNumber,@Param("pageSize") Integer pageSize);
	int countSheetInfo(String mobile);
	
	//客服介入
	Integer isInvolved(Integer order_id);
}
