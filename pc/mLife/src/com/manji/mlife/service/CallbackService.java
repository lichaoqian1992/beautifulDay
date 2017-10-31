package com.manji.mlife.service;

import com.qianmi.open.api.ApiException;

public interface CallbackService {

	//充值
	boolean payChargeBill(String billId);
	
	//票务
	void payTrafficOrder(String tradNo, String type) throws ApiException;
	
	
	 String getUserUrl();
	 
	 String getPayUrl();
	 
	 String getSignKey();
	 
	 boolean PartnerOrderBack(String  outerId);
}
