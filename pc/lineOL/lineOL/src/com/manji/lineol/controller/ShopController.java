package com.manji.lineol.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.lineol.common.reqparam.ShopConfigAddReqParam;
import com.manji.lineol.common.reqparam.ShopConfigModifyReqParam;
import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.service.ShopService;
import com.manji.lineol.vo.ShopQueueVo;

@RequestMapping("/shop")
@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	

	/**
	 * 商家初始化与获取排队信息
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/shopInit/{shopId}")
	public String shopInit(@PathVariable String shopId, HttpServletRequest request) {
		ShopQueueVo shopQueueVo = shopService.shopInit(shopId);
		shopQueueVo.setShopId(shopId);
		request.setAttribute("shopQueueVo", shopQueueVo);
		return "shop_init";
	}

	/**
	 * 跳转到商户配置页面
	 * 
	 * @param shopId
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopQueueConfig/{shopId}")
	public String shopQueueConfig(@PathVariable String shopId, HttpServletRequest request) {
		request.setAttribute("shopId", shopId);
		return "shop_config";
	}

	/**
	 * 查询商家配置信息
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/queryShopConfigInfo/{shopId}")
	@ResponseBody
	public BaseResult queryShopConfigInfo(@PathVariable String shopId) {
		return shopService.queryShopConfigInfo(shopId);
	}

	/**
	 * 新增商家配置信息
	 * 
	 * @param shopConfigReqParam
	 * @return
	 */
	@RequestMapping("/addShopConfigInfo")
	@ResponseBody
	public BaseResult addShopConfigInfo(ShopConfigAddReqParam shopConfigReqParam) {
		return shopService.addShopConfigInfo(shopConfigReqParam);
	}

	/**
	 * 修改商家配置信息
	 * 
	 * @param shopConfigModifyReqParam
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/modifyShopConfigInfo")
	@ResponseBody
	public BaseResult modifyShopConfigInfo(ShopConfigModifyReqParam shopConfigModifyReqParam) throws ParseException {
		return shopService.modifyShopConfigInfo(shopConfigModifyReqParam);
	}

	/**
	 * 删除商家配置信息
	 * 
	 * @param id
	 * @return 
	 */
	@RequestMapping("/removeShopConfigInfo/{id}/{shopId}")
	@ResponseBody
	public BaseResult removeShopConfigInfo(@PathVariable String id,@PathVariable String shopId) {
		return shopService.removeShopConfigInfo(id,shopId);
	}
	
	
	/**
	 * 开启或关闭排队服务
	 * @param serviceType 服务类型 0-开启 1-关闭
	 * @param shopId 商家Id
	 * @return
	 */
	@RequestMapping("/enableOrDisableQueueService/{serviceType}/{shopId}")
	@ResponseBody
	public BaseResult enableOrDisableQueueService(@PathVariable String serviceType,@PathVariable String shopId){
		return shopService.enableOrDisableQueueService(serviceType, shopId);
	}
	
	/**
	 * 查询商家所有排队信息
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/queryShopQueueInfo/{shopId}")
	@ResponseBody
	public BaseResult queryShopQueueInfo(@PathVariable String shopId){
		return shopService.queryShopQueueInfo(shopId);
	}
	
	
	/**
	 * 商家叫号
	 * @param shopId
	 * @param typeAlias
	 * @return
	 */
	@RequestMapping("/nextNumber/{shopId}/{typeAlias}")
	@ResponseBody
	public BaseResult nextNumber(@PathVariable String shopId, @PathVariable String typeAlias){
		return shopService.nextNumber(shopId, typeAlias);
	}
	
	
	/**
	 * 根据Id查询商家配置信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryShopConfigInfoById/{id}")
	@ResponseBody
	public BaseResult queryShopConfigInfoById(@PathVariable String id){
		BaseResult result = shopService.queryShopConfigInfoById(id);
		return result;
		
	}
	
	
	

}
