package com.life.service;

import java.text.ParseException;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TaskService {

	/**
	 * 查询(工作组)任务量   任务个数
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findTaskGroupZhu_number(String stime,String etime,String type) throws ParseException{
		String typel = type.substring(0,type.length()-1);
		String sjQj = "(" + 
				"	(estStarted >= '"+stime+"' AND estStarted <=  '"+etime+"') OR " + 
				"	(estStarted  <= '"+stime+"' AND deadline >= '"+etime+"') OR " + 
				"	(deadline >= '"+stime+"' AND deadline <= '"+etime+"') " + 
				")";
		String userIn = "SELECT uer.account from zt_user uer where uer.role = lang.key";
		String sql1 = "SELECT lang.value,lang.key," + 
				"(SELECT count(*) FROM zt_task WHERE "+sjQj+" AND (finishedBy in("+userIn+") or assignedTo in ("+userIn+"))"
				+ ") as count from zt_lang lang where lang.key in("+typel+") ORDER BY  lang.value";
		
		System.out.println("sql:----" + sql1.toString());
		
		List<Record> list = Db.find(sql1.toString());
		
		//子集循环
		for (Record record : list) {
			String zhukey = record.get("key");
			String thisGroupCount = record.get("count").toString();
			String sql2 = "select a.realname,a.count,a.account,CAST(a.count * 100/"+thisGroupCount+" AS DECIMAL(8,5) ) as zhanbi from(" + 
					"SELECT uer.account,uer.realname,uer.deleted," + 
					"(SELECT count(*) FROM zt_task WHERE "+sjQj+" AND (finishedBy = uer.account or assignedTo = uer.account)" + 
					")as count " + 
					"from zt_user uer where uer.role = '"+zhukey+"') a where (a.deleted = '0' or (a.deleted = '1' and a.count > 0)) ORDER BY a.count desc";
			
			System.out.println("sql2:----" + sql2.toString());
			
			List<Record> list2 = Db.find(sql2.toString());
			
			record.set("pserson", list2);
		}
		
		return list;
	}
	/**
	 * 查询(工作组)任务量   任务工时
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findTaskGroupZhu_Time(String stime,String etime,String type) throws ParseException{
		String typel = type.substring(0,type.length()-1);
		String sql1 = "SELECT lang.value,lang.key,\r\n" + 
				"(SELECT SUM(TIMESTAMPDIFF(\r\n" + 
				"		SECOND,\r\n" + 
				"		case when tss.estStarted > '"+stime+"' then tss.estStarted else '"+stime+"' end,\r\n" + 
				"		case when DATE_FORMAT(tss.deadline,'%Y-%m-%d 23:59:59') < '"+etime+"' then DATE_FORMAT(tss.deadline,'%Y-%m-%d 23:59:59') else '"+etime+"' end)) FROM zt_task tss WHERE (	\r\n" + 
				"		(tss.estStarted >= '"+stime+"' AND tss.estStarted <=  '"+etime+"') \r\n" + 
				"		OR 	(tss.estStarted  <= '"+stime+"' AND tss.deadline >= '"+etime+"') \r\n" + 
				"		OR 	(tss.deadline >= '"+stime+"' AND tss.deadline <= '"+etime+"') )\r\n" + 
				"		AND (finishedBy in(SELECT uer.account from zt_user uer where uer.role = lang.key) or assignedTo in (SELECT uer.account from zt_user uer where uer.role = lang.key))) as count\r\n" + 
				"\r\n" + 
				"from zt_lang lang where lang.key in("+typel+") ORDER BY  lang.value";
		
		System.out.println("sql:----" + sql1.toString());
		
		List<Record> list = Db.find(sql1.toString());
		
		//子集循环
		for (Record record : list) {
			String zhukey = record.get("key");
			Object obj = record.get("count");
			String thisGroupCount = "0";
			if(null != obj) {
				thisGroupCount = record.get("count").toString();
			}
			String sql2 = "select a.realname,a.count,a.account,CAST(a.count * 100/"+thisGroupCount+" AS DECIMAL(8,5) ) as zhanbi from(\r\n" + 
					"		SELECT uer.account,uer.realname,uer.deleted,\r\n" + 
					"		(SELECT SUM(TIMESTAMPDIFF(\r\n" + 
					"		SECOND,\r\n" + 
					"		case when ts.estStarted > '"+stime+"' then ts.estStarted else '"+stime+"' end,\r\n" + 
					"		case when DATE_FORMAT(ts.deadline,'%Y-%m-%d 23:59:59') < '"+etime+"' then DATE_FORMAT(ts.deadline,'%Y-%m-%d 23:59:59') else '"+etime+"' end)) FROM zt_task ts WHERE (\r\n" + 
					"			(ts.estStarted >= '"+stime+"' AND ts.estStarted <=  '"+etime+"') \r\n" + 
					"			OR 	(ts.estStarted  <= '"+stime+"' AND ts.deadline >= '"+etime+"') \r\n" + 
					"			OR 	(ts.deadline >= '"+stime+"' AND ts.deadline <= '"+etime+"') \r\n" + 
					"		) AND (ts.finishedBy = uer.account or ts.assignedTo = uer.account))as count\r\n" + 
					"	from zt_user uer where uer.role = '"+zhukey+"') a where (a.deleted = '0' or (a.deleted = '1' and a.count > 0)) ORDER BY a.count desc";
			
			System.out.println("sql2:----" + sql2.toString());
			
			List<Record> list2 = Db.find(sql2.toString());
			
			record.set("pserson", list2);
		}
		
		return list;
	}
	
	
	/**
	 * 查询任务工时  按照需求的
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findTaskxuqiu_time(String stime,String etime,String type) throws ParseException{
		String typel = type.substring(0,type.length()-1);
		
		String sjQj_tss = "(" + 
				"	(tss.estStarted >= '"+stime+"' AND tss.estStarted <=  '"+etime+"') OR " + 
				"	(tss.estStarted  <= '"+stime+"' AND tss.deadline >= '"+etime+"') OR " + 
				"	(tss.deadline >= '"+stime+"' AND tss.deadline <= '"+etime+"') " + 
				")";
		
		String sjQj_ts = "(" + 
				"	(ts.estStarted >= '"+stime+"' AND ts.estStarted <=  '"+etime+"') OR " + 
				"	(ts.estStarted  <= '"+stime+"' AND ts.deadline >= '"+etime+"') OR " + 
				"	(ts.deadline >= '"+stime+"' AND ts.deadline <= '"+etime+"') " + 
				")";
		
		String userIn = "SELECT uer.account from zt_user uer where uer.role in("+typel+")";
		
		String sql1 = "SELECT ts.story , stor.title ,\r\n" + 
				"(SELECT SUM(TIMESTAMPDIFF(\r\n" + 
				"		SECOND,\r\n" + 
				"		case when tss.estStarted > '"+stime+"' then tss.estStarted else '"+stime+"' end,\r\n" + 
				"		case when DATE_FORMAT(tss.deadline,'%Y-%m-%d 23:59:59') < '"+etime+"' then DATE_FORMAT(tss.deadline,'%Y-%m-%d 23:59:59') else '"+etime+"' end)) FROM zt_task tss where tss.story = ts.story  and "+sjQj_tss+") as abc " + 
				"from zt_task ts \r\n" + 
				"LEFT JOIN zt_story stor\r\n" + 
				"ON ts.story = stor.id\r\n" + 
				"where "+sjQj_ts+" and ts.deadline !='0000-00-00' \r\n" + 
				"AND (ts.finishedBy in("+userIn+") or ts.assignedTo in("+userIn+"))" + 
				"GROUP BY ts.story ORDER BY stor.title ASC";
		System.out.println("sqlxuqiu1:----" + sql1.toString());
		
		List<Record> list = Db.find(sql1.toString());
		
		//子集循环
		for (Record record : list) {
			String id = record.get("story").toString();
			if("0".equals(id)) {
				record.set("title", "【空需求】无需求任务");
				//record.set("abc", "0");
			}
			Object aabbcc = record.get("abc");
			String alltime = "0";
			if(null != aabbcc) {
				alltime = record.get("abc").toString();
			}
			String sql2 = "select a.account,a.realname,a.abc,CAST(a.abc * 100/"+alltime+" AS DECIMAL(8,5) ) as zhanbi from(" + 
					"SELECT uer.account,uer.realname,uer.deleted," + 
					"(SELECT SUM(TIMESTAMPDIFF(\r\n" + 
					"		SECOND,\r\n" + 
					"		case when ts.estStarted > '"+stime+"' then ts.estStarted else '"+stime+"' end,\r\n" + 
					"		case when DATE_FORMAT(ts.deadline,'%Y-%m-%d 23:59:59') < '"+etime+"' then DATE_FORMAT(ts.deadline,'%Y-%m-%d 23:59:59') else '"+etime+"' end)) FROM zt_task ts where ts.story = "+id+" and "+sjQj_ts+" AND (ts.finishedBy =  uer.account or ts.assignedTo = uer.account) ) as abc " + 
					"from zt_user uer where uer.role in("+typel+"))a where a.abc is not null and (a.deleted = '0' or (a.deleted = '1' and a.abc > 0)) ORDER BY a.abc desc";
			System.out.println("sql2:----" + sql2.toString());
			List<Record> list2 = Db.find(sql2.toString());
			record.set("pserson", list2);
		}
		return list;
	}
	
	/**
	 * 查询任务数量  按照需求的
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findTaskxuqiu_num(String stime,String etime,String type) throws ParseException{
		String typel = type.substring(0,type.length()-1);
		
		String sjQj_tss = "(" + 
				"	(tss.estStarted >= '"+stime+"' AND tss.estStarted <=  '"+etime+"') OR " + 
				"	(tss.estStarted  <= '"+stime+"' AND tss.deadline >= '"+etime+"') OR " + 
				"	(tss.deadline >= '"+stime+"' AND tss.deadline <= '"+etime+"') " + 
				")";
		
		String sjQj_ts = "(" + 
				"	(ts.estStarted >= '"+stime+"' AND ts.estStarted <=  '"+etime+"') OR " + 
				"	(ts.estStarted  <= '"+stime+"' AND ts.deadline >= '"+etime+"') OR " + 
				"	(ts.deadline >= '"+stime+"' AND ts.deadline <= '"+etime+"') " + 
				")";
		
		String userIn = "SELECT uer.account from zt_user uer where uer.role in("+typel+")";
		
		String sql1 = "SELECT ts.story , stor.title ,\r\n" + 
				"(SELECT count(*) FROM zt_task tss where tss.story = ts.story  and "+sjQj_tss+") as abc " + 
				"from zt_task ts \r\n" + 
				"LEFT JOIN zt_story stor\r\n" + 
				"ON ts.story = stor.id\r\n" + 
				"where "+sjQj_ts+" and ts.deadline !='0000-00-00' \r\n" + 
				"AND (ts.finishedBy in("+userIn+") or ts.assignedTo in("+userIn+"))" + 
				"GROUP BY ts.story ORDER BY stor.title ASC";
		System.out.println("sqlxuqiu2:----" + sql1.toString());
		
		List<Record> list = Db.find(sql1.toString());
		
		//子集循环
		for (Record record : list) {
			String id = record.get("story").toString();
			if("0".equals(id)) {
				record.set("title", "【空需求】无需求任务");
				//record.set("abc", "0");
			}
			Object aabbcc = record.get("abc");
			String alltime = "0";
			if(null != aabbcc) {
				alltime = record.get("abc").toString();
			}
			String sql2 = "select a.account,a.realname,a.abc,CAST(a.abc * 100/"+alltime+" AS DECIMAL(8,5) ) as zhanbi from(" + 
					"SELECT uer.account,uer.realname,uer.deleted," + 
					"(SELECT count(*) FROM zt_task ts where ts.story = "+id+" and "+sjQj_ts+" AND (ts.finishedBy =  uer.account or ts.assignedTo = uer.account) ) as abc " + 
					"from zt_user uer where uer.role in("+typel+"))a where a.abc is not null and a.abc > 0 ORDER BY a.abc desc";
			System.out.println("sql2:----" + sql2.toString());
			List<Record> list2 = Db.find(sql2.toString());
			record.set("pserson", list2);
		}
		return list;
	}
}
