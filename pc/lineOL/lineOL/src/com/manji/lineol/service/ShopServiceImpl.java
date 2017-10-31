package com.manji.lineol.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manji.lineol.common.converter.ShopConfigInfoConverter;
import com.manji.lineol.common.errorcode.ErrorCodeEnum;
import com.manji.lineol.common.info.ShopConfigInfo;
import com.manji.lineol.common.reqparam.ShopConfigAddReqParam;
import com.manji.lineol.common.reqparam.ShopConfigModifyReqParam;
import com.manji.lineol.common.result.BaseQueryResult;
import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.common.result.ObjectBaseResult;
import com.manji.lineol.mapper.ShopConfigMapper;
import com.manji.lineol.mapper.ShopsMapper;
import com.manji.lineol.model.ShopConfig;
import com.manji.lineol.model.Shops;
import com.manji.lineol.util.CollectionsUtils;
import com.manji.lineol.util.RedisListOpsUtils;
import com.manji.lineol.util.StringUtils;
import com.manji.lineol.vo.LineVo;
import com.manji.lineol.vo.ShopQueueInfoVo;
import com.manji.lineol.vo.ShopQueueVo;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopsMapper shopsMapper;

	@Autowired
	private RedisListOpsUtils<LineVo> redisOptList;

	@Autowired
	private ShopConfigMapper shopConfigMapper;

	@Autowired
	private ShopConfigInfoConverter shopConfigInfoConverter;

	@Autowired
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, LineVo> redisTemplateLineVo;

	@Override
	public ShopQueueVo shopInit(String shopId) {
		ShopQueueVo shopQueueVo = new ShopQueueVo();
		Shops shops = shopsMapper.queryShopsById(shopId);
		// 该商户为第一次初始化
		if (shops == null) {
			Shops initShops = new Shops();
			initShops.setShopId(shopId);
			shopsMapper.insertShops(initShops);
			shopQueueVo.setStatus(3);
			return shopQueueVo;
		} else if (shops.getOnOff() == 1) {
			shopQueueVo.setStatus(2);
			// 该商户未开启排队服务
			return shopQueueVo;
		} else {
			// 从缓存中查询该商家所有的队列
			Set<String> keys = redisOptList.keys(shopId + "," + "*");
			if (CollectionsUtils.isEmptyCollection(keys)) {
				shopQueueVo.setStatus(1);
				return shopQueueVo;
			} else {
				// 查询商户配置信息
				List<ShopConfig> ShopConfigList = shopConfigMapper.queryShopConfigByShopId(shopId);
				List<ShopQueueInfoVo> infoVos = new ArrayList<ShopQueueInfoVo>();
				for (ShopConfig shopConfig : ShopConfigList) {
					ShopQueueInfoVo shopQueueInfoVo = new ShopQueueInfoVo();
					shopQueueInfoVo.setTypeName(shopConfig.getLineTypeName());
					shopQueueInfoVo.setTypeAs(shopConfig.getLineTypeAs());
					for (String key : keys) {
						// 找到别名对应的队列
						if ((shopId + "," + shopConfig.getLineTypeAs()).equals(key)) {
							shopQueueInfoVo.setTotalCount(redisOptList.size(key).toString());
							break;
						}
					}
					infoVos.add(shopQueueInfoVo);
				}
				shopQueueVo.setShopQueueInfoVo(infoVos);
				shopQueueVo.setStatus(0);
				return shopQueueVo;
			}

		}

	}

	@Override
	public BaseQueryResult<ShopConfigInfo> queryShopConfigInfo(String shopId) {
		BaseQueryResult<ShopConfigInfo> queryResult = new BaseQueryResult<ShopConfigInfo>();

		List<ShopConfig> ShopConfigList = shopConfigMapper.queryShopConfigByShopId(shopId);
		List<ShopConfigInfo> listConfig = new ArrayList<>();

		for (ShopConfig shopConfig : ShopConfigList) {
			ShopConfigInfo shopConfigInfo = shopConfigInfoConverter.convert(shopConfig);
			listConfig.add(shopConfigInfo);
		}

		queryResult.setResultList(listConfig);
		queryResult.setSuccessResult("查询配置信息成功");

		return queryResult;
	}

	@Override
	public BaseResult addShopConfigInfo(ShopConfigAddReqParam shopConfigReqParam) {
		if (StringUtils.isEmpty(shopConfigReqParam.getShopId())) {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOPID_ISNULL_ERROR.getCode(),
					ErrorCodeEnum.SHOPID_ISNULL_ERROR.getMessage());
		} else {
			Shops shop = shopsMapper.queryShopsById(shopConfigReqParam.getShopId());
			if (shop != null) {
				if (shop.getOnOff() == 0) {
					return BaseResult.getFailResult(ErrorCodeEnum.SERVICE_IS_DISABLE.getCode(),
							ErrorCodeEnum.SERVICE_IS_DISABLE.getMessage());
				} else if (shop.getOnOff() == 1) {
					shopConfigMapper.addShopConfig(shopConfigReqParam);
					return BaseResult.getSuccessResult("新增配置成功");
				} else {
					return BaseResult.getFailResult(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
							ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
				}

			} else {
				return BaseResult.getFailResult(ErrorCodeEnum.SHOP_NOT_EXIST.getCode(),
						ErrorCodeEnum.SHOP_NOT_EXIST.getMessage());
			}
		}

	}

	@Override
	public BaseResult modifyShopConfigInfo(ShopConfigModifyReqParam shopConfigModifyReqParam) {
		if (StringUtils.isEmpty(shopConfigModifyReqParam.getShopId())) {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOPID_ISNULL_ERROR.getCode(),
					ErrorCodeEnum.SHOPID_ISNULL_ERROR.getMessage());
		} else {
			Shops shop = shopsMapper.queryShopsById(shopConfigModifyReqParam.getShopId());
			if (shop != null) {
				if (shop.getOnOff() == 0) {
					return BaseResult.getFailResult(ErrorCodeEnum.SERVICE_IS_DISABLE.getCode(),
							ErrorCodeEnum.SERVICE_IS_DISABLE.getMessage());
				} else if (shop.getOnOff() == 1) {
					int count = shopConfigMapper.modifyShopConfig(shopConfigModifyReqParam);
					return BaseResult.getSuccessResult("配置修改成功");

				} else {
					return BaseResult.getFailResult(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
							ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
				}

			} else {
				return BaseResult.getFailResult(ErrorCodeEnum.SHOP_NOT_EXIST.getCode(),
						ErrorCodeEnum.SHOP_NOT_EXIST.getMessage());
			}
		}

	}

	@Override
	public BaseResult removeShopConfigInfo(String id, String shopId) {
		if (StringUtils.isEmpty(shopId)) {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOPID_ISNULL_ERROR.getCode(),
					ErrorCodeEnum.SHOPID_ISNULL_ERROR.getMessage());
		} else {
			Shops shop = shopsMapper.queryShopsById(shopId);
			if (shop != null) {
				if (shop.getOnOff() == 0) {
					return BaseResult.getFailResult(ErrorCodeEnum.SERVICE_IS_DISABLE.getCode(),
							ErrorCodeEnum.SERVICE_IS_DISABLE.getMessage());
				} else if (shop.getOnOff() == 1) {
					shopConfigMapper.removeShopConfig(id);
					return BaseResult.getSuccessResult("配置删除成功");

				} else {
					return BaseResult.getFailResult(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
							ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
				}

			} else {
				return BaseResult.getFailResult(ErrorCodeEnum.SHOP_NOT_EXIST.getCode(),
						ErrorCodeEnum.SHOP_NOT_EXIST.getMessage());
			}
		}

	}

	@Override
	@Transactional
	public BaseResult enableOrDisableQueueService(String serviceType, final String shopId) {
		if (StringUtils.isEmpty(shopId)) {
			return BaseResult.getFailResult(ErrorCodeEnum.SHOPID_ISNULL_ERROR.getCode(),
					ErrorCodeEnum.SHOPID_ISNULL_ERROR.getMessage());
		} else {
			Shops shop = shopsMapper.queryShopsById(shopId);
			if (shop != null) {
				if (serviceType.equals("0") && shop.getOnOff() == 1) {
					// 查询该商家配置信息
					List<ShopConfig> shopConfigs = shopConfigMapper.queryShopConfigByShopId(shop.getShopId());
					// 配置项为空
					if (CollectionsUtils.isEmptyCollection(shopConfigs)) {
						return BaseResult.getFailResult(ErrorCodeEnum.SHOP_CONFIG_NOT_EXIST.getCode(),
								ErrorCodeEnum.SHOP_CONFIG_NOT_EXIST.getMessage());
					}

					// 修改配置为开启
					shopsMapper.modfiyShops(new HashMap<String, String>() {

						private static final long serialVersionUID = 1L;

						{
							put("onOrOff", "0");
							put("shopId", shopId);
						}

					});

					// 便利配置创建队列
					for (ShopConfig shopConfig : shopConfigs) {
						String queueNameKey = shopConfig.getShopId() + "," + shopConfig.getLineTypeAs();
						// 创建队列时的初始数据(无业务意义)
						LineVo value = new LineVo();
						value.setUserId("0000");
						value.setTime(new Date().getTime());
						value.setNumber(shopConfig.getInitalValue());
						redisOptList.rightPush(queueNameKey, value);
					}
					return BaseResult.getSuccessResult("开启服务成功");
				} else if (serviceType.equals("1") && shop.getOnOff() == 0) {
					// 修改配置信息为关闭
					// 修改配置为开启
					shopsMapper.modfiyShops(new HashMap<String, String>() {

						private static final long serialVersionUID = 1L;

						{
							put("onOrOff", "1");
							put("shopId", shopId);
						}

					});
					// 查询该商家配置信息缓存名
					List<ShopConfig> shopConfigs = shopConfigMapper.queryShopConfigByShopId(shop.getShopId());
					for (ShopConfig shopConfig : shopConfigs) {
						try {
							List<LineVo> range = redisOptList
									.range(shopConfig.getShopId() + "," + shopConfig.getLineTypeAs(), 0, -1);
							for (LineVo lineVo : range) {
								redisTemplate.delete(lineVo.getUserId());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						redisOptList.del(shopConfig.getShopId() + "," + shopConfig.getLineTypeAs());

					}

					return BaseResult.getSuccessResult("关闭服务成功");
				} else {
					return BaseResult.getFailResult(ErrorCodeEnum.SERVICE_TYPE_ERROR.getCode(),
							ErrorCodeEnum.SERVICE_TYPE_ERROR.getMessage());
				}
			} else {
				return BaseResult.getFailResult(ErrorCodeEnum.SHOP_NOT_EXIST.getCode(),
						ErrorCodeEnum.SHOP_NOT_EXIST.getMessage());
			}
		}

	}

	@Override
	public BaseQueryResult<ShopQueueInfoVo> queryShopQueueInfo(String shopId) {
		boolean flag = false;
		BaseQueryResult<ShopQueueInfoVo> baseQueryResult = new BaseQueryResult<>();
		Set<String> keys = redisOptList.keys(shopId + ",*");
		List<ShopConfig> ShopConfigList = shopConfigMapper.queryShopConfigByShopId(shopId);
		List<ShopQueueInfoVo> infoVos = new ArrayList<ShopQueueInfoVo>();
		for (ShopConfig shopConfig : ShopConfigList) {
			ShopQueueInfoVo shopQueueInfoVo = new ShopQueueInfoVo();
			shopQueueInfoVo.setTypeName(shopConfig.getLineTypeName());
			shopQueueInfoVo.setTypeAs(shopConfig.getLineTypeAs());
			for (String key : keys) {
				// 找到别名对应的队列
				if ((shopId + "," + shopConfig.getLineTypeAs()).equals(key)) {
					shopQueueInfoVo.setTotalCount(redisOptList.size(key).toString());
					flag = true;
					break;
				}
			}
			infoVos.add(shopQueueInfoVo);
		}
		if (flag) {
			baseQueryResult.setResultList(infoVos);
			baseQueryResult.setSuccessResult("查询商家所有排队信息成功");
		} else {
			baseQueryResult.setFailResult(ErrorCodeEnum.QUEUE_INFO_NOT_EXIST.getCode(),
					ErrorCodeEnum.QUEUE_INFO_NOT_EXIST.getMessage(), ErrorCodeEnum.QUEUE_INFO_NOT_EXIST.getMessage());
		}
		return baseQueryResult;
	}

	@Override
	public ObjectBaseResult<LineVoInfo> nextNumber(String shopId, String typeAlias) {
		LineVoInfo info = new LineVoInfo();
		StringBuilder builder = new StringBuilder(shopId);
		builder.append(",");
		builder.append(typeAlias);
		Set<String> keys = redisOptList.keys(builder.toString());
		if (CollectionsUtils.isEmptyCollection(keys)) {
			return ObjectBaseResult.failResult(ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getCode(),
					ErrorCodeEnum.SHOP_QUEUE_NOT_EXIST.getMessage());
		} else {
			// 当队列中只剩下最后一个号了 会默认插入一个初始化值 保证队列不会被删除
			info.setSize(String.valueOf(redisOptList.size(builder.toString())));
			if (redisOptList.size(builder.toString()).toString().equals("1")) {
				LineVo value = new LineVo();
				value.setUserId("0000");
				value.setTime(new Date().getTime());
				List<ShopConfig> shopConfigs = shopConfigMapper.queryShopConfigByShopId(shopId);
				for (ShopConfig shopConfig : shopConfigs) {
					if (shopConfig.getLineTypeAs().equals(typeAlias)) {
						value.setNumber(shopConfig.getInitalValue());
						break;
					}
				}
				redisOptList.rightPush(builder.toString(), value);
			}
			LineVo leftPop = redisOptList.leftPop(builder.toString());

			info.setUserId(leftPop.getUserId());
			info.setTime(leftPop.getTime());
			info.setNumber(leftPop.getNumber());
			//将商家叫到的顾客存入缓存
			//设置失效时间为3600秒
			final String keyIng=shopId+"-"+leftPop.getUserId();
			redisTemplateLineVo.opsForValue().set(keyIng, leftPop);
			redisTemplateLineVo.expire(keyIng, 60, TimeUnit.MINUTES);
			
			return ObjectBaseResult.successResult(info);
		}

	}

	@Override
	public ObjectBaseResult<ShopConfigInfo> queryShopConfigInfoById(String id) {
		ShopConfig shopConfig = shopConfigMapper.queryShopConfigInfoById(id);
		ShopConfigInfo shopConfigInfo = shopConfigInfoConverter.convert(shopConfig);
		return ObjectBaseResult.successResult(shopConfigInfo);
	}

	class LineVoInfo extends LineVo {

		private String size;

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

	}
}
