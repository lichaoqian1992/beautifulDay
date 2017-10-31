package com.manji.mlife.mapper;

import java.util.List;

import com.manji.mlife.model.SimpleCode;

public interface SimpleCodeMapper {
	
	String getValue(String codename);
	
    int insert(SimpleCode record);

    int insertSelective(SimpleCode record);
    
    void updateToken(String token);
    
    void updateRefreshToken(String refreshToken);

	List<SimpleCode> getAll();

	void updateStstus(SimpleCode sc);
    
    
}