package com.life.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class SaturationService {
	/**
	 * 饱和度查询
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findSaturation(String stime,String etime,String type) throws ParseException{
		//求两个日期相差多少天 +1 查询的天数
		long xiangcha = getDaysOfTowDiffDate(stime, etime) + 1;
		
		String typel = type.substring(0,type.length()-1);
		String sql1 = "SELECT uer.account,uer.realname " + 
				"from zt_user uer where uer.deleted = '0' and uer.role in("+typel+")";
		
		System.out.println("饱和sqlMain:" + sql1);
		
		List<Record> list = Db.find(sql1.toString());
		for (Record record : list) {
			String account = record.get("account").toString();
			String sqlsb = "SELECT count(*) FROM (\r\n" + 
					"		SELECT count(*) from zt_action ac \r\n" + 
					"		LEFT join zt_task ts \r\n" + 
					"		ON ac.objectID = ts.id \r\n" + 
					"		WHERE (ac.action = 'commented' or ac.action = 'finished')  AND ac.objectType='task' AND ac.date >= '"+stime+"' and ac.date <= '"+etime+"' \r\n" + 
					"		and ac.actor = '"+account+"'\r\n" + 
					"		and(\r\n" + 
					"			(ts.estStarted >= '"+stime+"' AND ts.estStarted <=  '"+etime+"') OR 	\r\n" + 
					"			(ts.estStarted  <= '"+stime+"' AND ts.deadline >= '"+etime+"') OR \r\n" + 
					"			(ts.deadline >= '"+stime+"' AND ts.deadline <= '"+etime+"') \r\n" + 
					"		)\r\n" + 
					"		GROUP BY date_format(ac.date,'%y-%m-%d')\r\n" + 
					") a";
			System.out.println("饱和sql zi:" + sqlsb);
			Long cc = Db.queryLong(sqlsb);
			record.set("num", cc);
			record.set("baifenbi", (cc * 100 / xiangcha)+ "%");
		}
		return list;
	}
	
	/**
	 * 给出日期添加一段时间后的日期
	 * 
	 * @param dateStr
	 * @param plus 天
	 * @return
	 */
	public static String getPlusDays(String format, String dateStr, long plus) {

		Date date = strToDate(dateStr, format);
		long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

		return DateToStr(format, new Date(time));
	}
	
	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate) {

		Date l_startDate = strToDate(p_startDate,"yyyy-MM-dd");
		Date l_endDate = strToDate(p_endDate,"yyyy-MM-dd");
		long l_startTime = l_startDate.getTime();
		long l_endTime = l_endDate.getTime();
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}
	/**
	 * 将字符串时间改成Date类型
	 */
	public static Date strToDate(String dateStr, String format) {

		Date date = null;

		System.out.println("sajkdjasida:" + dateStr + "cccccccccc" + format);
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	/**
	 * 将Date时间转成字符串
	 */
	public static String DateToStr(String format, Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	public static void main(String[] args) throws ParseException {
		String pusData = getPlusDays("yyyy-MM-dd", "2017-08-24", 1) + " 00:00:00";
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse("2017-08-24");
	}

}
