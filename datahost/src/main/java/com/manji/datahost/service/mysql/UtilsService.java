package com.manji.datahost.service.mysql;


import com.manji.datahost.model.mysql.CallRegister;
import com.manji.datahost.model.mysql.SheetInfo;
import com.manji.datahost.model.sqlserver.base.Page;

public interface UtilsService {

	//来电记录
	Page<CallRegister> callRegister(Integer pageNumber,Integer pageSize,String mobile);
	
	//工单信息
	Page<SheetInfo> sheetInfo(String mobile,Integer pageNumber,Integer pageSize);
	
	//客服介入
	Integer isInvolved(Integer order_id);
}
