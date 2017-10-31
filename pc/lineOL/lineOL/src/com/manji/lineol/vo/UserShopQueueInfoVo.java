package com.manji.lineol.vo;

import java.io.Serializable;

public class UserShopQueueInfoVo implements Serializable {

	private static final long serialVersionUID = -6063897451161010578L;

	private String totalCount;

	private String queueName;

	private String queueTypeAs;

	private String shopId;

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueTypeAs() {
		return queueTypeAs;
	}

	public void setQueueTypeAs(String queueTypeAs) {
		this.queueTypeAs = queueTypeAs;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "UserShopQueueInfoVo [totalCount=" + totalCount + ", queueName=" + queueName + ", queueTypeAs="
				+ queueTypeAs + ", shopId=" + shopId + "]";
	}

	

}
