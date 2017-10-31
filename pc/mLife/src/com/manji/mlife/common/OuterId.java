package com.manji.mlife.common;

import java.util.Random;

/**
 * 
 * @author gaochao
 * 2016年7月8日下午4:10:31
 * OuterId
 * 外部订单编号
 */
public class OuterId {
	/**
	 * 生成订单
	 * @return
	 */
	public static String getOuterId(){
		long m =System.currentTimeMillis();
        Random random = new Random(); 
        int r =random.nextInt(899999);
        r =r+100000;
		return  "MJLIFE"+m+r;
	}
	
//	public static void main(String args[]){
//		System.out.println(getOuterId());
//	}
}
