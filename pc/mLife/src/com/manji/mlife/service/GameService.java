package com.manji.mlife.service;

import java.util.Map;

import com.manji.mlife.Vo.CardVo;
import com.manji.mlife.Vo.GameVo;
import com.qianmi.open.api.ApiException;

public interface GameService {

	String getGame() throws ApiException;
	
	String getCard() throws ApiException;
	
	String getItemList(String gameId) throws ApiException;
	
	String getItem(String itemId) throws ApiException;
	
	Map<String, String> createGameBill(GameVo vo,String userName) throws ApiException;
	
	Map<String, String> createCardBill(CardVo vo, String userName) throws ApiException;
	
	String getServer(String classId, String itemId) throws ApiException;
	
}
