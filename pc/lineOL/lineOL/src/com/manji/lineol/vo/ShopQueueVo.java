package com.manji.lineol.vo;

import java.io.Serializable;
import java.util.List;

public class ShopQueueVo implements Serializable{
	
	
	private static final long serialVersionUID = 682162883817230521L;
	//状态 0 -开启了服务并配置了信息 1-开启了服务  2,服务是关闭的, 3-没有开启服务 也没有配置
	private int status;
	
	private String shopId;
	
	private List<ShopQueueInfoVo> shopQueueInfoVo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ShopQueueInfoVo> getShopQueueInfoVo() {
		return shopQueueInfoVo;
	}

	public void setShopQueueInfoVo(List<ShopQueueInfoVo> shopQueueInfoVo) {
		this.shopQueueInfoVo = shopQueueInfoVo;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "ShopQueueVo [status=" + status + ", shopId=" + shopId + ", shopQueueInfoVo=" + shopQueueInfoVo + "]";
	}
	
	
	
	
	
	
	
	

}
