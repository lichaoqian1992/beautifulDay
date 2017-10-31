package com.manji.mlife.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.CardVo;
import com.manji.mlife.Vo.GameVo;
import com.manji.mlife.common.OuterId;
import com.manji.mlife.common.TransformUnit;
import com.manji.mlife.mapper.ChargeOrderMapper;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.mapper.TradeMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.SimpleCode;
import com.manji.mlife.model.Trade;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.Game;
import com.qianmi.open.api.domain.elife.Item;
import com.qianmi.open.api.domain.elife.ItemInfo;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.CardPasswordCreateBillRequest;
import com.qianmi.open.api.request.CardPasswordItemListRequest;
import com.qianmi.open.api.request.DirectRechargeGameCreateBillRequest;
import com.qianmi.open.api.request.GameAreasListRequest;
import com.qianmi.open.api.request.GameClassesListRequest;
import com.qianmi.open.api.request.GameItemsListRequest;
import com.qianmi.open.api.request.GamesListRequest;
import com.qianmi.open.api.request.RechargeBaseGetItemInfoRequest;
import com.qianmi.open.api.response.CardPasswordCreateBillResponse;
import com.qianmi.open.api.response.CardPasswordItemListResponse;
import com.qianmi.open.api.response.DirectRechargeGameCreateBillResponse;
import com.qianmi.open.api.response.GameAreasListResponse;
import com.qianmi.open.api.response.GameClassesListResponse;
import com.qianmi.open.api.response.GameItemsListResponse;
import com.qianmi.open.api.response.GamesListResponse;
import com.qianmi.open.api.response.RechargeBaseGetItemInfoResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GameServiceImpl implements GameService {


	@Autowired	
    private DBService service;
	private static final Logger logger =Logger.getLogger(GameServiceImpl.class);
	
//	// 测试使用
//	private static final String url = "http://gw.api.qianmi.com/api";
//	private static final String appKey = "10001127";
//	private static final String appSecret = "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
//	private static final String accessToken = "d03dcdd828be44f68878de940732d785";
//	
	
	@Override
	public String getGame() throws ApiException {
		String json ="";
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		GamesListRequest req = new GamesListRequest();
		GamesListResponse gamesListResponse = client.execute(req, map.get("accessToken"));
		
		logger.info("GamesListResponse"+JSONObject.fromObject(gamesListResponse).toString());
		List<Game> list =gamesListResponse.getGames();
		if(null !=list){
			json =JSONArray.fromObject(list).toString();
		}
		return json;
	}
	
	
	@Override
	public String getCard() throws ApiException {
		
		String cardJson ="";
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		CardPasswordItemListRequest req = new CardPasswordItemListRequest();
		CardPasswordItemListResponse cardPasswordItemListResponse = client.execute(req, map.get("accessToken"));
		
		logger.info("CardPasswordItemListResponse"+JSONObject.fromObject(cardPasswordItemListResponse).toString());
		List<AdminItem> list =cardPasswordItemListResponse.getItems();
		if(null !=list){
			cardJson =JSONArray.fromObject(list).toString();
		}
		return cardJson;
	}


	@Override
	public String getItemList(String gameId) throws ApiException {
		String classId ="";
		String itemListJson ="";
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));		
		GameClassesListRequest req = new GameClassesListRequest();
		req.setGameId(gameId);
		GameClassesListResponse gameClassesListResponse =client.execute(req, map.get("accessToken"));
		
		logger.info("GameClassesListResponse"+JSONObject.fromObject(gameClassesListResponse).toString());
		if(null !=gameClassesListResponse.getClasses()){
		classId =gameClassesListResponse.getClasses().get(0).getClassId();
		
		GameItemsListRequest itemReq = new GameItemsListRequest();
		itemReq.setClassId(classId);
		GameItemsListResponse gameItemsListResponse = client.execute(itemReq, map.get("accessToken"));
		
		logger.info("GameItemsListResponse"+JSONObject.fromObject(gameItemsListResponse).toString());

		if(null != gameItemsListResponse.getItems()){
			
			List<Item> itemList =gameItemsListResponse.getItems();
			itemListJson =JSONArray.fromObject(itemList).toString();

			
		}
		}
		
		return itemListJson;
	}


	@Override
	public String getItem(String itemId) throws ApiException {
		String itemJson ="";
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		RechargeBaseGetItemInfoRequest req = new RechargeBaseGetItemInfoRequest();
		req.setItemId(itemId);
		RechargeBaseGetItemInfoResponse rechargeBaseGetItemInfoResponse = client.execute(req, map.get("accessToken"));
		logger.info("RechargeBaseGetItemInfoResponse"+JSONObject.fromObject(rechargeBaseGetItemInfoResponse).toString());
		
		ItemInfo info =rechargeBaseGetItemInfoResponse.getItemInfo();
		if(null !=info){
			itemJson=JSONObject.fromObject(info).toString();
		}
		
		return itemJson;
	}


	@Override
	public String getServer(String classId, String itemId) throws ApiException {
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		GameAreasListRequest req = new GameAreasListRequest();
		req.setItemId("2205202");
		req.setClassId("766761");
		GameAreasListResponse response =null;
		try {
			 response = client.execute(req, map.get("accessToken"));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String  json=JSONObject.fromObject(response.getGameAreas()).toString();
		
		return json;
	}


	@Override
	public Map<String, String> createGameBill(GameVo vo, String userName) throws ApiException {
		Map<String ,String> billMap =new HashMap<String,String>();
		
		String outerId = OuterId.getOuterId();
		Map<String, String> map = service.getBasePara();
		//生成游戏充值订单
		
		try{
		
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
			DirectRechargeGameCreateBillRequest req = new DirectRechargeGameCreateBillRequest();
			req.setItemId(vo.getItemId());
			req.setItemNum(vo.getItemNum());
			req.setRechargeAccount(vo.getRechargeAccount());
			DirectRechargeGameCreateBillResponse directRechargeGameCreateBillResponse = client.execute(req, map.get("accessToken"));
			
			logger.info("-----生成游戏充值订单:"+JSONObject.fromObject(directRechargeGameCreateBillResponse).toString());
			OrderDetailInfo info =directRechargeGameCreateBillResponse.getOrderDetailInfo();
			if(null !=info){
				info.setSaleAmount(vo.getTotalAmount());
				billMap=service.insertData(info, "Game", userName,outerId);
				billMap.put("errCode", "0");
			}else{
				billMap.put("redirectURL","/errInfo");
				billMap.put("errCode", "1");
				billMap.put("errMsg", directRechargeGameCreateBillResponse.getSubMsg());
			}
		
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			billMap.put("errCode", "1");
			billMap.put("errMsg", a.getErrMsg());
		}
		
		return billMap;
	}

	@Override
	public Map<String, String> createCardBill(CardVo vo,String userName) throws ApiException {
		
		Map<String ,String> billMap =new HashMap<String,String>();
		
		String outerId = OuterId.getOuterId();
		
		Map<String, String> map = service.getBasePara();

		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		CardPasswordCreateBillRequest req = new CardPasswordCreateBillRequest();
		req.setItemId(vo.getItemId());
		req.setItemNum(vo.getItemNum());
		req.setReceiveMobile(vo.getReceiveMobile());
		req.setReceiveEmail(vo.getReceiveEmail());
		CardPasswordCreateBillResponse cardPasswordCreateBillResponse = client.execute(req, map.get("accessToken"));
		logger.info("CardPasswordCreateBillResponse"+JSONObject.fromObject(cardPasswordCreateBillResponse).toString());
		
		OrderDetailInfo info =cardPasswordCreateBillResponse.getOrderDetailInfo();
		
		if(null !=info){
			billMap=service.insertData(info, "Card", userName,outerId);
			
		}
		
		return billMap;
	}

}
