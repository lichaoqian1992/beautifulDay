package com.manji.finance.base;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.manji.finance.recharge.model.RechargeDetail;

public class _MapKit {

	public static void SqlServermapping(ActiveRecordPlugin activeRecordPlugin){
		
		//activeRecordPlugin.addMapping("f_user","id",User.class);
		
	}

	public static ActiveRecordPlugin Mysqlmapping(ActiveRecordPlugin activeRecordPlugin){

		activeRecordPlugin.addMapping("t_interior_recharge_detail","ID", RechargeDetail.class);
		return null;
	}
	
	
}
