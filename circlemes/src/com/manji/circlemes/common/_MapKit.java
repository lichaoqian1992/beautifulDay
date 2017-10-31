package com.manji.circlemes.common;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.manji.circlemes.model.local.Batch;
import com.manji.circlemes.model.manji.User;

public class _MapKit {

	public static void mapping(ActiveRecordPlugin activeRecordPlugin){
		
//		activeRecordPlugin.addMapping("f_user","id",User.class);
		
		activeRecordPlugin.addMapping("dt_users","id",User.class);
		activeRecordPlugin.addMapping("dt_user_role_shopinfo","id",User.class);
	}
	
	
//	public static void localMapping(ActiveRecordPlugin activeRecordPlugin){
//		
//		activeRecordPlugin.addMapping("cir_batch","id",Batch.class);
//		
//	}
	
	
	
	
}
