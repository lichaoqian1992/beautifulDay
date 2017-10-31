package com.manji.datahost.service.mysql;


public interface PermissionService {

	Integer getConnector(String interface_name);
	
	//文件传输
	String sendPostReq(String fileName,String base64Str);
	
}
