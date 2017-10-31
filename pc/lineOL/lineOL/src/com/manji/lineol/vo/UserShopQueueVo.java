package com.manji.lineol.vo;

import java.io.Serializable;
import java.util.List;

public class UserShopQueueVo implements Serializable{
	
	private static final long serialVersionUID = 8171477458757011603L;

	private String userName;
	
	private List<UserShopQueueInfoVo> userShopQueueInfoVos;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserShopQueueInfoVo> getUserShopQueueInfoVos() {
		return userShopQueueInfoVos;
	}

	public void setUserShopQueueInfoVos(List<UserShopQueueInfoVo> userShopQueueInfoVos) {
		this.userShopQueueInfoVos = userShopQueueInfoVos;
	}

	@Override
	public String toString() {
		return "UserShopQueueVo [userName=" + userName + ", userShopQueueInfoVos=" + userShopQueueInfoVos + "]";
	}
	
	
	
	
	

}
