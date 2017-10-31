package com.manji.lineol.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.manji.lineol.common.errorcode.ErrorCodeEnum;
import com.manji.lineol.common.result.BaseQueryResult;
import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.common.result.ObjectBaseResult;
import com.manji.lineol.mapper.ShopConfigMapper;
import com.manji.lineol.model.ShopConfig;
import com.manji.lineol.util.CollectionsUtils;
import com.manji.lineol.util.HttpUtils;
import com.manji.lineol.util.JsonUtils;
import com.manji.lineol.util.RedisListOpsUtils;
import com.manji.lineol.util.StringUtils;
import com.manji.lineol.vo.LineVo;
import com.manji.lineol.vo.QueryAlUserlQueueInfoVo;
import com.manji.lineol.vo.UserQueueInfoVo;
import com.manji.lineol.vo.UserShopQueueInfoVo;
import com.manji.lineol.vo.UserShopQueueVo;

import net.sf.json.JSONObject;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger=Logger.getLogger(CustomerService.class);

	@Autowired
	private RedisListOpsUtils<LineVo> redisOptList;

	@Autowired
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	


	@Autowired
	private ShopConfigMapper shopConfigMapper;
	
	@Autowired
	private RedisTemplate<String, LineVo> redisTemplateLineVo;
	
	
	@Value("#{configProperties['userInfoBySessionIdUrl']}")  
	private String userInfoBySessionIdUrl;
	
	@Value("#{configProperties['shopNameUrl']}")  
	private String shopNameUrl;
	
	

	public String getUserName(final String sessionId) {
		String json = HttpUtils.doGet(userInfoBySessionIdUrl, new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("action", "GetUserInfoBySessionId");
				put("sessionid", sessionId);
			}
		});

		System.out.println("sendUser-url"+userInfoBySessionIdUrl);
		System.out.println("json="+json);
		JSONObject jsonObject = JsonUtils.tranTObject(json);
		System.out.println("JSONObject="+jsonObject);
		jsonObject = jsonObject.getJSONObject("data");
		String userName = jsonObject.getString("user_name");
		JSONObject loginInfo = jsonObject.getJSONObject("model_login");
		if (StringUtils.isNotEmpty(userName)&&!userName.equals("null")) {
			Map<String, String> mapLoginInfo = new HashMap<>();
			mapLoginInfo.put("user_id", loginInfo.getString("user_id"));
			mapLoginInfo.put("user_role_type", loginInfo.getString("user_role_type"));
			mapLoginInfo.put("user_role_value", loginInfo.getString("user_role_value"));
			// mapLoginInfo.put("user_id", "1133837");
			// mapLoginInfo.put("user_role_type", "Buyer");
			// mapLoginInfo.put("user_role_value", "1133791");
			redisTemplate.opsForValue().set(userName, mapLoginInfo);
		}


		return userName;

	}

	@Override
	public UserShopQueueVo customerQuery(final String sessionId, String shopId) {
		UserShopQueueVo queueVo = new UserShopQueueVo();
		List<UserShopQueueInfoVo> userShopQueueInfoVos = new ArrayList<>();
		List<ShopConfig> shopConfigs = shopConfigMapper.queryShopConfigByShopId(shopId);

		Set<String> keys = redisOptList.keys(shopId + ",*");
		if (CollectionsUtils.isEmptyCollection(keys)) {
			return null;
		}

		for (String str : keys) {
			String[] split = str.split(",");
			String asName = split[1];
			for (ShopConfig shopConfig : shopConfigs) {
				if (shopConfig.getLineTypeAs().equals(asName)) {
					UserShopQueueInfoVo infoVo = new UserShopQueueInfoVo();
					infoVo.setQueueName(shopConfig.getLineTypeName());
					infoVo.setTotalCount(redisOptList.size(str).toString());
					infoVo.setQueueTypeAs(shopConfig.getLineTypeAs());
					infoVo.setShopId(shopId);
					userShopQueueInfoVos.add(infoVo);
					break;
				}

			}

		}
		queueVo.setUserName(getUserName(sessionId));
		queueVo.setUserShopQueueInfoVos(userShopQueueInfoVos);
		return queueVo;
	}

	@Override
	public UserQueueInfoVo customerQueryMyInfo(String sessionId, String shopId) {
		UserQueueInfoVo userQueueInfoVo = null;
		String userName = getUserName(sessionId);
		if (StringUtils.isNotEmpty(userName)&&!userName.equals("null")) {
			Set<String> keys = redisOptList.keys(shopId + ",*");
			for (String str : keys) {
				List<LineVo> lineVos = redisOptList.range(str, 0, -1);
				for (int i = 0; i < lineVos.size(); i++) {
					String userId = lineVos.get(i).getUserId();
					System.out.println(userId);
					if (lineVos.get(i).getUserId().equals(userName)) {
						userQueueInfoVo = new UserQueueInfoVo();
						userQueueInfoVo.setStatus("0");
						userQueueInfoVo.setNo(lineVos.get(i).getNumber());
						userQueueInfoVo.setMyCurrentSize(String.valueOf(i));
						userQueueInfoVo.setUserName(userName);
						userQueueInfoVo.setShopId(shopId);
						userQueueInfoVo.setTypeAs(str.split(",")[1]);
						return userQueueInfoVo;
					}

				}

			}

		}

		return null;
	}

	@Override
	public synchronized BaseResult customerSubscribe(String shopId, String queueTypeAs, String userName) {
		String key = shopId + "," + queueTypeAs;
		Set<String> keys = redisOptList.keys(key);
		if (!CollectionsUtils.isEmptyCollection(keys) && keys.size() == 1) {
			LineVo lineVo = new LineVo();
			LineVo lastLine = redisOptList.index(key, -1);
			lineVo.setNumber(lastLine.getNumber() + 1);
			lineVo.setTime(new Date().getTime());
			lineVo.setUserId(userName);
			redisOptList.rightPush(key, lineVo);
			ObjectBaseResult<LineVo> successResult = ObjectBaseResult.successResult(lineVo);
			successResult.setSuccessResult("预约成功");
			return successResult;
		} else {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getCode(),
					ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getMessage(), ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getMessage());
		}
	}

	@Override
	public BaseResult customerCancelSubscribe(String shopId, String queueTypeAs, String userName) {
		String key = shopId + "," + queueTypeAs;
		Set<String> keys = redisOptList.keys(key);
		if (!CollectionsUtils.isEmptyCollection(keys) && keys.size() == 1) {
			List<LineVo> lineVos = redisOptList.range(key, 0, -1);
			for (LineVo lineVo : lineVos) {
				if (lineVo.getUserId().equals(userName)) {
					redisOptList.remove(key, 0, lineVo);
					redisTemplate.delete(userName);
					return BaseResult.getSuccessResult("用户预约取消成功");
				}

			}
			return BaseResult.getFailResult(ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST_USER_INFO.getCode(),
					ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST_USER_INFO.getMessage(),
					ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST_USER_INFO.getMessage());

		} else {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getCode(),
					ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getMessage(), ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getMessage());
		}

	}


	@Override
	public BaseResult queryUserWhetherBook(String shopId, String userName) {
		final String key=shopId+"-"+userName;
		LineVo lineVo = redisTemplateLineVo.opsForValue().get(key);
		if(lineVo==null){
			return BaseResult.getFailResult(ErrorCodeEnum.NO_RESERVATION_INFORMATION.getCode(), ErrorCodeEnum.NO_RESERVATION_INFORMATION.getMessage());
		}else{
			ObjectBaseResult<LineVo> baseResult=new ObjectBaseResult<>();
			baseResult.setObj(lineVo);
			baseResult.setSuccessResult("用户信息存在");
			return baseResult;
		}
		
	}

	@Override
	public BaseQueryResult<QueryAlUserlQueueInfoVo> queryUserQueueAllInfo(String sessionId) {
		List<QueryAlUserlQueueInfoVo> vos=new ArrayList<QueryAlUserlQueueInfoVo>();
		String userName = getUserName(sessionId);
		if(StringUtils.isEmpty(userName)&&userName.equals("null")){
			return BaseQueryResult.failResult(ErrorCodeEnum.USER_INFO_NOT_EXIST.getCode(), ErrorCodeEnum.USER_INFO_NOT_EXIST.getMessage());
		}else{
			Set<String> keys = redisOptList.keys("*,*");
			for (String str : keys) {
				List<LineVo> lineVos = redisOptList.range(str, 0, -1);
				for (int i=0; i<lineVos.size(); i++) {
					if(lineVos.get(i).getUserId().equals(userName)){
						QueryAlUserlQueueInfoVo vo=new QueryAlUserlQueueInfoVo();
						vo.setCurrentPosition(String.valueOf(i));
						String userId = lineVos.get(i).getUserId();
						System.out.println(userId);
						vo.setUserId(userId);
						vo.setNumber(String.valueOf(lineVos.get(i).getNumber()));
						String[] split = str.split(",");
						vo.setShopId(split[0]);
						vo.setTypeAs(split[1]);
						//获取店铺名
						try {
							String shopName = getShopName(split[0]);
							vo.setShopName(shopName);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("忽略获取店铺名获取异常"+e.getMessage());
						}
						vos.add(vo);
						break;
					}
					
				}
			}
			
			BaseQueryResult<QueryAlUserlQueueInfoVo> result=new BaseQueryResult<>();
			result.setSuccessResult("查询成功");
			result.setResultList(vos);
			return result;
		}
		
	}
	

	/**
	 * 获取店铺名称
	 * @param shopId
	 * @return
	 */
	private String getShopName(final String shopId){
		String shopName = HttpUtils.doGet(shopNameUrl, new HashMap<String,String>(){
			private static final long serialVersionUID = -5351374222810581419L;
			{
				put("userid", shopId);
			}
		});
		
		return shopName;
	}
	
	

}
