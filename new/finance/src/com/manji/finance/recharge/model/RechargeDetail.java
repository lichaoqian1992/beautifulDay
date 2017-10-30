package com.manji.finance.recharge.model;

import com.jfinal.plugin.activerecord.Model;

public class RechargeDetail extends Model<RechargeDetail> {
	
	/**
	 * 这个只是方便对于数据库的操作，比如删除、修改、查询
	 */
	public static final RechargeDetail rd = new RechargeDetail().dao();

}
