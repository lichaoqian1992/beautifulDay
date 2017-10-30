package com.manji.msgservice.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTools {
	
	/**
	 * 获取当前时间
	 * 时间格式为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTime() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(new Date());
	}
	
	/**
	 * 判断当前时间是否在时间段内
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public static boolean isBetween(String sTime, String eTime) {

		String[] startTime = sTime.split(":");
		String[] endTime = eTime.split(":");

		int begin = Integer.valueOf(startTime[0]) * 60 + Integer.valueOf(startTime[1]);
		int end = Integer.valueOf(endTime[0]) * 60 + Integer.valueOf(endTime[1]);

		SimpleDateFormat df = new SimpleDateFormat("HH:mm");

		String currentTime[] = df.format(new Date()).split(":");
		int cur = Integer.valueOf(currentTime[0]) * 60 + Integer.valueOf(currentTime[1]);

		if (begin <= end) {

			if (cur >= begin && cur <= end) {
				System.out.println("true");
				return true;
			}
		} else {

			if (cur >= begin || cur <= end) {
				System.out.println("true");
				return true;
			}
		}
		System.out.println("false");
		return false;
	}

	public static void main(String[] args) {

		// System.out.println(currentTime());

		// isBetween("17:06","02:06");

	}

}
