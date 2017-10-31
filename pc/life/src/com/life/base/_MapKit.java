package com.life.base;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

public class _MapKit {

	//保存数据的时候，以record的方式保存
	public static void SqlServermapping(ActiveRecordPlugin activeRecordPlugin){
		
		//activeRecordPlugin.addMapping("f_user","id",User.class);
		
	}

	public static void Mysqlmapping(ActiveRecordPlugin activeRecordPlugin){

		//activeRecordPlugin.addMapping("t_interior_recharge_detail","ID", RechargeDetail.class);
	}
}
