package com.life.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.DateFormatConverter;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.life.model.Param;
import com.life.model.Result;
import com.life.utils.ExcelUtils;

public class ExcelService {

	
	public List<Record> findLang(){
		
		return Db.find("select `key`,`value` from zt_lang where `key` like 'dev%'");
	}
	/**
	 * 查询数据
	 * @param langL       工作组
	 * @param startTime   开始时间
	 * @param endTime     结束时间
	 * @param typeL       现在/计划
	 * @param columnL     将要显示的字段
	 * @return
	 */
	public List<Record> query(String langL, String startTime, String endTime, String typeL, String columnL){
		
		String decL = "";//工作组
		String ziduan = "";//字段
		String type = "";
		String s = startTime+" 00:00:00";//工作组
		String e = endTime+" 23:59:59";//工作组
		String[] dev = langL.split(";");
		String[] column = columnL.split(";");
		for(int i=0;i<dev.length;i++){
			
			if(i != dev.length-1){
				decL += "'"+dev[i]+"',";
			}else{
				decL += "'"+dev[i]+"'";
			}
		}
		for(int i=0;i<column.length;i++){
			if(!column[i].equals("xuhao")){
				if(i == 0){
					ziduan += "ifnull("+column[i]+",'') as XuQiu"+(i+1);
				}else{
					ziduan += ",ifnull("+column[i]+",'') as XuQiu"+(i+1);
				}
			}
		}
		if(typeL.equals("plane")){
			type = "and t.deadline between '"+s+"' and '"+e+"'";
		}else{
			type = " and t.estStarted <= '"+e+"' and t.deadline >= '"+s+"'";
		}
		
		StringBuffer sql = new StringBuffer("select ").append(ziduan).append(" from zt_task t inner JOIN(select * from(")
			.append("select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u inner join zt_lang l on l.`key`=u.role and l.`key` in (")
			.append(decL).append("))as a)as b on b.account=t.assignedTo LEFT JOIN (select * from(").append("select a.id,a.objectID,a.date,a.`comment` as content,")
			.append("l.realname from zt_action a inner join (select MAX(id) as gid from zt_action where objectType = 'task' and `comment` !=''")
			.append(" group by objectID) g on g.gid=a.id inner join (select * from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname")
			.append(" from zt_user u inner join zt_lang l on l.`key`=u.role and l.`key` in (").append(decL)
			.append("))as a) as l on l.account=a.actor)as m)as a on a.objectID=t.id LEFT JOIN zt_story s on s.id=t.story")
			.append(" LEFT JOIN (select objectID,content from (select * from(select a.id,a.objectID,a.date,a.`comment` as content,l.realname from zt_action a")
			.append(" inner join (select MAX(id) as gid from zt_action where objectType = 'task' and `comment` !='' group by objectID) g on g.gid=a.id")
			.append(" inner join (select * from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u inner join zt_lang l on l.`key`=u.role and l.`key` in (").append(decL)
			.append("))as a) AS l ON l.account = a.actor ) AS m where m.date BETWEEN '"+s+"' AND '"+e+"') AS xx where CURDATE()=date(date))as td ON td.objectID=t.id")
			.append(" where t.deleted='0' and t.`status` not in ('closed','cancel')")
			.append(type)
			.append("or t.id in (select mm.objectID from (select * from(select a.id,a.objectID,a.date,a.`comment` as content,")
			.append("l.realname from zt_action a inner join (select MAX(id) as gid from zt_action where objectType = 'task' and `comment` !=''")
			.append(" group by objectID ) g on g.gid=a.id inner join (select * from(select u.id,u.account,u.realname,u.deleted,")
			.append("l.`value` as groupname from zt_user u inner join zt_lang l on l.`key`=u.role and l.`key` in (").append(decL)
			.append("))as a) as l on l.account=a.actor )as m where m.date between '"+s+"' and '"+e+"')as mm)")
			.append(" order by b.groupname,s.title,t.deadline,a.date desc");

		List<Record> list = Db.find(sql.toString());
		if(list != null){
			int count =1;
			for(int i=0;i<list.size();i++){
				//3、4、7、10都要替换
				if(list.get(i).get("XuQiu3") != null){
					String xuqiu = list.get(i).get("XuQiu3").toString().replaceAll("&amp", "").replaceAll("&nbsp", "").replaceAll("<p>", "").replaceAll("</p>", " ")
								.replaceAll("<span>", "").replaceAll("</span>", " ").replaceAll("<br />", " ").replaceAll("&", "");
					if(xuqiu.indexOf("<") != -1){
						String str = xuqiu.substring(xuqiu.indexOf("<"), xuqiu.indexOf(">")+">".length());
						xuqiu=xuqiu.replaceAll(str,"查看禅道图片");
					}
					list.get(i).set("XuQiu3", xuqiu);
				}
				if(list.get(i).get("XuQiu4") != null){
					String gongneng = list.get(i).get("XuQiu4").toString().replaceAll("&amp", "").replaceAll("&nbsp", "").replaceAll("<p>", "").replaceAll("</p>", " ")
							.replaceAll("<span>", "").replaceAll("</span>", " ").replaceAll("<br />", " ").replaceAll("&", "");
					if(gongneng.indexOf("<") != -1){
						String str2 = gongneng.substring(gongneng.indexOf("<"), gongneng.indexOf(">")+">".length());
						gongneng=gongneng.replaceAll(str2,"查看禅道图片");
					}
					list.get(i).set("XuQiu4", gongneng);
				}
				if(list.get(i).get("XuQiu7") != null){
					String lastFk = list.get(i).get("XuQiu7").toString().replaceAll("&amp", "").replaceAll("&nbsp", "").replaceAll("<p>", "").replaceAll("</p>", " ")
								.replaceAll("<span>", "").replaceAll("</span>", " ").replaceAll("<br />", " ").replaceAll("&", "");
					if(lastFk.indexOf("<") != -1){
						String str3 = lastFk.substring(lastFk.indexOf("<"), lastFk.indexOf(">")+">".length());
						
						lastFk=lastFk.replaceAll(str3,"查看禅道图片");
					}
					list.get(i).set("XuQiu7", lastFk);
				}
				if(list.get(i).get("XuQiu10") != null){
					String tFk = list.get(i).get("XuQiu10").toString().replaceAll("&amp", "").replaceAll("&nbsp", "").replaceAll("<p>", "").replaceAll("</p>", " ")
							.replaceAll("<span>", "").replaceAll("</span>", " ").replaceAll("<br />", " ").replaceAll("&", "");
					if(tFk.indexOf("<") != -1){
						String str4 = tFk.substring(tFk.indexOf("<"), tFk.indexOf(">")+">".length());
						tFk=tFk.replaceAll(str4,"查看禅道图片");
					}
					list.get(i).set("XuQiu10", tFk);
				}
				if(i != list.size()-1){
					for(int j=i+1;j<list.size();j++){
						list.get(i).set("xuqiu",count);
						if(list.get(j).get("XuQiu1").toString().equals(list.get(i).get("XuQiu1").toString())){
							
							count++;
						}else{
							
							count =1;
						}
						break;
					}
				}else{
					list.get(i).set("xuqiu",count);
				}
			}
		}
		
		return list;
		
	}
	
	/**
	 * 导出excel
	 * @param p
	 * @return
	 */
	public String toExcel(Map<String,String> map){
		
		String startTime = map.get("startTime").toString()+" 00:00:00";
		String endTime = map.get("endTime").toString()+" 23:59:59";
		String[] columnL = map.get("columnL").toString().split(";");
		String excelName = "工作记录信息";
		String[] title = new String[columnL.length];
		for(int m=0;m<columnL.length;m++){
			if(columnL[m].equals("b.groupname")){
				title[m] = "工作组";
			}else if(columnL[m].equals("xuhao")){
				title[m] = "序号";
			}else if(columnL[m].equals("s.title")){
				title[m] = "需求";
			}else if(columnL[m].equals("t.name")){
				title[m] = "功能";
			}else if(columnL[m].equals("t.deadline")){
				title[m] = "结束时间";
			}else if(columnL[m].equals("b.realname")){
				title[m] = "负责人";
			}else if(columnL[m].equals("a.content")){
				title[m] = "最后反馈";
			}else if(columnL[m].equals("a.date")){
				title[m] = "反馈时间";
			}else if(columnL[m].equals("a.realname")){
				title[m] = "反馈人";
			}else if(columnL[m].equals("td.content")){
				title[m] = "今日反馈";
			}
		}

		List<Record> list = query(map.get("langL").toString(), startTime, endTime, map.get("typeL").toString(), map.get("columnL").toString());
		String[][] content = new String[list.size()][title.length];
		int count = 1;
		for(int i=0;i<list.size();i++){
			for(int m=0;m<columnL.length;m++){
				if(m == 0){
					content[i][m] = list.get(i).get("XuQiu1").toString();
				}
				if(m == 1){
					if(i != list.size()-1){
						for(int j=i+1;j<list.size();j++){
							content[i][m] = count+"";
							if(list.get(j).get("XuQiu1").toString().equals(list.get(i).get("XuQiu1").toString())){
								
								count++;
							}else{
								
								count =1;
							}
							break;
						}
					}else{
						content[i][m] = count+"";
					}
				}
				if(m == 2){
					content[i][m] = list.get(i).get("XuQiu3").toString();
				}else if(m ==3){
					content[i][m] = list.get(i).get("XuQiu4").toString();
				}else if(m ==4){
					content[i][m] = list.get(i).get("XuQiu5").toString();
				}else if(m ==5){
					content[i][m] = list.get(i).get("XuQiu6").toString();
				}else if(m ==6){
					content[i][m] = list.get(i).get("XuQiu7").toString();
				}else if(m ==7){
					content[i][m] = list.get(i).get("XuQiu8").toString();
				}else if(m ==8){
					content[i][m] = list.get(i).get("XuQiu9").toString();
				}else if(m ==9){
					content[i][m] = list.get(i).get("XuQiu10").toString();
				}
			}
		}
		String path = new ExcelUtils().excel(map, title, excelName, content);
		return path;
	}
	/**
	 * 导出excel
	 * @param map           查询条件
	 * @return
	 * @throws ParseException 
	 */
	public String toExcel2(Map<String,String> map) throws ParseException{
		String[] title = {"责任人","工作计划节点","今日计划内工作情况","预计完成时间","工作检查及备注（含整改意见）"};
		
		//得到的数据
		List<Result> result = queryByTime(map.get("langL"),map.get("startTime"),"导出",map.get("project"));
		String[][] content = new String[result.size()][title.length];
		/*List<Result> result = new ArrayList<Result>();
		for(int i=0;i<list.size();i++){
			String jieshushijian = "";
			String fuzeren = list.get(i).getStr("FanKuiRen");
			String xuqiu = list.get(i).getStr("XuQiu")+"m";
			String gongneng = list.get(i).getStr("GongNeng")+"m";
			if(list.get(i).get("JieShuShiJian") != null && !list.get(i).get("JieShuShiJian").equals("")){
				jieshushijian = list.get(i).get("JieShuShiJian").toString() +"m";
			}
			String fankui = list.get(i).getStr("FanKui")+"m";
			for(int j=i+1;j<list.size();j++){
				if(fuzeren.equals(list.get(j).getStr("FanKuiRen"))){
					i++;
					if(list.get(j).getStr("XuQiu") != null){
						if(!list.get(i).getStr("XuQiu").equals(list.get(j).getStr("XuQiu"))){
							xuqiu += list.get(j).getStr("XuQiu")+"m";
						}
					}
					if(list.get(j).getStr("GongNeng") != null){
						if(!list.get(j).getStr("GongNeng").equals(list.get(i).getStr("GongNeng"))){
							gongneng += list.get(j).getStr("GongNeng")+"m";
						}
					}
					if(list.get(j).get("JieShuShiJian").toString() != null){
						if(!list.get(j).get("JieShuShiJian").toString().equals(jieshushijian)){
							jieshushijian += list.get(j).get("JieShuShiJian").toString()+"m";
						}
					}
					if(list.get(j).getStr("FanKui") != null){
						fankui += list.get(j).getStr("FanKui")+"m";
					}
				}
			}
			Result r = new Result();
			r.setName(fuzeren);
			r.setXuqiu(xuqiu);
			r.setGongneng(gongneng);
			r.setFankui(fankui);
			r.setJieshushijian(jieshushijian);
			
			result.add(r);
		}*/
		for(int x=0;x<result.size();x++){
			content[x][0] = result.get(x).getName();
			String xuqiu = result.get(x).getXuqiu();
			String xuqiu2 = result.get(x).getGongneng();
			String xuqiu3 = result.get(x).getJieshushijian();
			String xuqiu4 = result.get(x).getFankui();
			if(xuqiu != null){
				String[] gongneng = xuqiu.split("m");
				String str = "";
				for(int m=0;m<gongneng.length;m++){
					if(!gongneng[m].equals("null")){
						str += (m+1)+"."+gongneng[m]+"\r\n";
					}
				}
				content[x][1] = str;
			}
			if(xuqiu2 != null){
				String[] gongneng = xuqiu2.split("m");
				String str = "";
				for(int m=0;m<gongneng.length;m++){
					str += (m+1)+"."+gongneng[m]+"\r\n";
				}
				content[x][2] = str;
			}
			if(xuqiu3 != null){
				String[] jieshushijian = xuqiu3.split("m");
				String str = "";
				for(int m=0;m<jieshushijian.length;m++){
					str += (m+1)+"."+jieshushijian[m]+"\r\n";
				}
				content[x][3] = str;
			}
			if(xuqiu4 != null){
				String[] fankui = xuqiu4.split("m");
				String str = "";
				for(int m=0;m<fankui.length;m++){
					str += (m+1)+"."+fankui[m]+"\r\n";
				}
				content[x][4] = str;
			}
		}
		
		String path = new ExcelUtils().excel(map, title, "日志汇总表", content);
		return path;
	}
	/**
	 * 根据时间查询
	 * @param time
	 * @return
	 */
	public List<Result> queryByTime(String langL,String time,String type,String project){
		String decL = "";
		String[] dev = langL.split(";");
		for(int i=0;i<dev.length;i++){
			
			if(i != dev.length-1){
				decL += "'"+dev[i]+"',";
			}else{
				decL += "'"+dev[i]+"'";
			}
		}
		StringBuffer sql = new StringBuffer("select t.project, a.groupname as GongZuoZu,s.title as XuQiu,t.`name` as GongNeng,if(t.deadline='0000-00-00 00:00:00','',t.deadline) as JieShuShiJian,")
				.append("a.content as FanKui,cast(a.date as char(100)) AS FanKuiShiJian,a.realname as FanKuiRen from zt_task t INNER JOIN (")
				.append("select a.id,a.objectID,a.date,a.`comment` as content,l.groupname,l.realname from zt_action a inner join (")
				.append("select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u inner join")
				.append(" zt_lang l on l.`key`=u.role and l.`key` in (").append(decL).append("))as l on l.account=a.actor")
				.append(" where objectType='task' and `comment`!='' and date(a.date)=date('"+time+"'))as a on a.objectID=t.id")
				.append(" LEFT JOIN zt_story s on s.id=t.story where t.deleted='0' and t.project="+project+" order by a.groupname,a.realname,s.title,t.deadline,a.date desc");
					
		List<Record> list = Db.find(sql.toString());
		String regEx_html = "<[^>]+>";//匹配html标签的正则表达式
		// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		String regEx_special = "\\&[a-zA-Z]{1,10};";
		if(list != null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).get("XuQiu") != null){
					String s = list.get(i).getStr("XuQiu").replaceAll(regEx_html, " ").replaceAll(regEx_special, " ");
					list.get(i).set("XuQiu", s);
				}
				if(list.get(i).get("GongNeng") != null){
					String s2 = list.get(i).getStr("GongNeng").replaceAll(regEx_html, " ").replaceAll(regEx_special, " ");
					list.get(i).set("GongNeng", s2);
				}
				if(list.get(i).get("FanKui") != null){
					String s3 = list.get(i).getStr("FanKui").replaceAll(regEx_html, " ").replaceAll(regEx_special, " ");
					list.get(i).set("FanKui", s3);
				}
			}
		}
		//处理数据
		List<Result> result = new ArrayList<Result>();
		for(int i=0;i<list.size();i++){
			String jieshushijian = "",fankuishijian = "";
			String fuzeren = list.get(i).getStr("FanKuiRen");
			String xuqiu = list.get(i).getStr("XuQiu")+"m";
			String gongneng = list.get(i).getStr("GongNeng")+"m";
			if(list.get(i).get("JieShuShiJian") != null && !list.get(i).get("JieShuShiJian").equals("")){
				jieshushijian = list.get(i).get("JieShuShiJian").toString() +"m";
				System.out.println(list.get(i).get("FanKuiShiJian")+"================");
				fankuishijian = list.get(i).get("FanKuiShiJian")+"m";
			}
			String fankui = list.get(i).getStr("FanKui")+"m";
			for(int j=i+1;j<list.size();j++){
				if(fuzeren.equals(list.get(j).getStr("FanKuiRen"))){
					
					if(list.get(j).getStr("GongNeng") != null){
						if(!list.get(j).getStr("GongNeng").equals(list.get(i).getStr("GongNeng"))){

						}else{
							i++;
							if(list.get(j).getStr("FanKui") != null){
								fankui += list.get(j).getStr("FanKui")+"m";
								fankuishijian += list.get(j).getStr("FanKuiShiJian")+"m";
							}
						}
					}
				}
			}
			Result r = new Result();
			r.setProject(list.get(i).get("project").toString());
			r.setName(fuzeren);
			r.setXuqiu(xuqiu);
			r.setGongneng(gongneng);
			r.setFankui(fankui);
			r.setJieshushijian(jieshushijian);
			r.setFankuishijian(fankuishijian);
			
			result.add(r);
		}
			return result;
	}
	/**
	 * 查询产品 信息
	 * @return
	 */
	public List<Record> findProduct(){
		
		List<Record> r = new ArrayList<Record>();
		
		StringBuffer sql = new StringBuffer("select a.*,b.`name` as pname,u.realname,DATE_FORMAT(b.createdDate,'%Y-%m-%d %T')createdDate from(")
					.append("select * from(SELECT s.id,s.title,s.product,s.`status` FROM zt_story s where s.deleted='0' and s.`status`!='closed')as a left JOIN (")
					.append("select t.name,t.story,t.`status` as tstatus from zt_task t where  t.deleted='0')as b on a.id=b.story) AS a")
					.append(" LEFT JOIN zt_product b ON a.product = b.id AND b.deleted = '0' AND b.`status` != 'closed'")
					.append(" LEFT JOIN zt_user u ON b.createdBy = u.account ORDER BY b.name,a.title");
		List<Record> list = Db.find(sql.toString());
		
		if(list != null){
			for(int i=0;i<list.size();i++){
				Record record = new Record();
				int a = 0;
				int b = 0;
				String realName = "",name = "",createdDate = "";
				if(list.get(i).get("story") != null){
					a++;
					for(int j=i+1;j<list.size();j++){
						//计算总的
							if(list.get(i).get("id").equals(list.get(j).get("id"))){
								a += 1;
								//计算未完成的需求
								if(!list.get(j).get("tstatus").toString().equals("closed")){
									b += 1;
								}
								i++;
							}
					}
				}
				if(list.get(i).get("realname") != null){
					realName = list.get(i).get("realname").toString();
				}
				if(list.get(i).get("pname") != null){
					name = list.get(i).get("pname").toString();
				}
				if(list.get(i).get("createdDate") != null){
					createdDate = list.get(i).get("createdDate").toString();
				}
				record.set("title", list.get(i).get("title").toString()).set("pname", name).set("realname", realName)
					.set("createdDate", createdDate).set("count", a).set("fcount", b).set("id", list.get(i).get("id"));
				r.add(record);
			}
		}
		System.out.println(r.size());
		return r;
	}
	/**
	 * 查询完成量
	 * @return
	 */
	public List<Record> findFinished(String stime , String etime , String type){
		
		List<Record> list = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		if(type.equals("accept")){
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in (select `key` from zt_lang where `key` like 'dev%') where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='assigned' and  b.date >= ? and b.date <= ?");
		}else if(type.equals("finished")){
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in (select `key` from zt_lang where `key` like 'dev%') where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='finished' and  b.date >= ? and b.date <= ?");
		}else if(type.equals("task")){
			sql2.append("select a.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in (select `key` from zt_lang where `key` like 'dev%') where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_task b on b.status not in('done','closed') and b.deleted='0' and a.account=b.assignedTo");
		}else if(type.equals("bug")){
			sql2.append("select a.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in (select `key` from zt_lang where `key` like 'dev%') where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_bug b on b.deleted='0' and a.account = b.assignedTo and b.`status`='active'");
		}
		
		if(type.equals("accept") || type.equals("finished")){
			list = Db.find(sql.toString(),stime,etime);
		}else if(type.equals("task") || type.equals("bug")){
			list = Db.find(sql2.toString());
		}
		
		List<Record> info = new ArrayList<Record>();
		for(int i=0;i<list.size();i++){
			Record re = new Record();
			int a = 0;
			Record r = list.get(i);
			if(r.get("bid") != null){
				for(int j=i+1;j<list.size();j++){
					Record m = list.get(j);
					if(m.get("bid") != null){
						if(r.get("account").toString().equals(m.get("account").toString())){
							a++;
							i++;
						}
					}
				}
				a++;
			}
			re.set("account", r.get("account")).set("name", r.get("realname")).set("count", a);
			info.add(re);
		}
		
		return info;
	}
	/**
	 * 查询任务或者bug的各种量
	 * @param stime
	 * @param etime
	 * @param type   要查询的内容类型
	 * @return
	 */
	public List<Record> getTaskOrBug(String stime , String etime , String type,String groupType){
		String typel = groupType.substring(0,groupType.length()-1);
		StringBuffer sql = new StringBuffer();
		
		if(type.equals("accept_task")){
			
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='assigned' and b.objectType='task' and  b.date >= '"+stime+"' and b.date <= '"+etime+"'");
		
		}else if(type.equals("finished_task")){
			
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='finished' and b.objectType='task' and  b.date >= '"+stime+"' and b.date <= '"+etime+"'");
		
		}else if(type.equals("doing_task")){
			
			sql.append("select a.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_task b on b.status in('wait','doing','pause') and b.deleted='0' and a.account=b.assignedTo");
			
		}else if(type.equals("accept_bug")){
			
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
				.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
				.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='assigned' and b.objectType='bug' and  b.date >= '"+stime+"' and b.date <= '"+etime+"'");
			
		}else if(type.equals("finished_bug")){
			
			sql.append("select a.*,b.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
				.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
				.append("as a LEFT JOIN zt_action b on a.account=b.actor and b.action='resolved' and b.objectType='bug' and  b.date >= '"+stime+"' and b.date <= '"+etime+"'");
		
		}else if(type.equals("doing_bug")){
			
			sql.append("select a.*,b.id as bid from(select u.id,u.account,u.realname,u.deleted,l.`value` as groupname from zt_user u")
			.append(" inner join zt_lang l on l.`key`=u.role and l.`key` in ("+typel+") where deleted='0' order by groupname,realname)")
			.append("as a LEFT JOIN zt_bug b on b.deleted='0' and a.account = b.assignedTo and b.`status`='active'");
			
		}
		System.out.println("-------------"+sql.toString());
		return detailResult(sql.toString());
	}
	
	/**
	 * 公共查询并处理查询结果 
	 * @param sql
	 * @return list
	 */
	public List<Record> detailResult(String sql){
		
		List<Record> info = new ArrayList<Record>();//用于存放新的数据
		
		List<Record> list = Db.find(sql.toString());//查询得到的数据集合
		
		//处理数据
		for(int i=0;i<list.size();i++){
			Record re = new Record();
			int a = 0;
			Record r = list.get(i);
			if(r.get("bid") != null){
				for(int j=i+1;j<list.size();j++){
					Record m = list.get(j);
					if(m.get("bid") != null){
						if(r.get("account").toString().equals(m.get("account").toString())){
							a++;
							i++;
						}
					}
				}
				a++;
			}
			re.set("account", r.get("account")).set("name", r.get("realname")).set("count", a);
			info.add(re);
		}
		return info;
	}
	/**
	 * 查询错误率
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 */
	public List<Record> findErrorBug(String stime,String etime,String type){
		
		int bugCount = 0,pcount = 1;
		StringBuffer sql = new StringBuffer();
		if(type.equals("work")){//工作组
			
			sql.append("select a.* from(select l.`value`,u.realname,u.account,u.id,l.`key` from zt_user u,zt_lang l where u.role=l.`key`")
				.append(" and u.deleted='0' and l.`key` in(select `key` from zt_lang where `key` like 'dev%'))as a inner JOIN")
				.append(" zt_bug b on a.account = b.assignedTo and b.openedDate>='"+stime+"' and b.openedDate<='"+etime+"' order by a.value asc,a.realname asc");
		}else{ //需求
			
		}
		List<Record> list = Db.find(sql.toString());
		List<Record> nlist = new ArrayList<Record>();
		//处理数据-00-
		for(int i=0;i<list.size();i++){
			bugCount = 0;
			pcount = 1;
			for(int m=0;m<list.size();m++){
				//处理总量
				if(list.get(i).get("value").equals(list.get(m).get("value"))){
					bugCount +=1;
				}
			}
			for(int j=i+1;j<list.size();j++){
				//处理个人的数量
				if(list.get(i).get("realname").equals(list.get(j).get("realname"))){
					pcount += 1;
					i++;
				}
			}
			float f = (float)pcount/bugCount*100;
			System.out.println(f+"==========================");
			Record r = new Record();
			r.set("bugCount", bugCount).set("pcount",pcount).set("realname", list.get(i).get("realname"))
			.set("value", list.get(i).get("value")).set("percent", new DecimalFormat("0.00").format(f)+"%");
			
			nlist.add(r);
		}
		
		return nlist;
		
	}
	/**
	 * 饱和度
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findSaturation(String stime,String etime,String type) throws ParseException{
		
		String typel = type.substring(0,type.length()-1);
		System.out.println(typel);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(SELECT t.name,t.estStarted,IF (t.deadline = '0000-00-00',now(),t.deadline) as jieshu,IF (t.finishedBy = '',assignedTo,finishedBy) AS peo")
			.append(" FROM zt_task t where estStarted>='"+stime+"' and estStarted<='"+etime+"') as a where 1=1")
			.append(" and a.peo IN (SELECT u.account FROM zt_user u,zt_lang l WHERE u.role = l.`key` AND u.deleted = '0' AND l.`key` IN ("+typel+"))")
			.append(" ORDER BY a.peo,a.`NAME`");
		
		List<Record> list = Db.find(sql.toString());
		
		for(int i=0;i<list.size();i++){
			String s = list.get(i).get("estStarted").toString();
			String e = list.get(i).get("jieshu").toString();
			//计算时间差
			long day = 0l;
			if(new SimpleDateFormat("yyyy-MM-dd").parse(e).getTime()-new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime() >= 0){
				day = (new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime() - new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime())/(24*60*60*1000);
			}else{
				day = (new SimpleDateFormat("yyyy-MM-dd").parse(e).getTime()-new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime())/(24*60*60*1000);
			}
			System.out.println(day+"==============================");
			list.get(i).set("cha", day+1);
			
		}
		//查询一下总人数
		Record u = Db.findFirst(("select count(*) as count from zt_lang l inner join zt_user u on l.`key`=u.role and u.deleted='0' and l.`key` in("+typel+")"));
		//计算查询时间差
		long c = (new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime()-new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime())/(24*60*60*1000);
		long cday = Integer.parseInt(u.get("count").toString()) * (c+1);
		for(int m=0;m<list.size();m++){
			int a = 0;
			for(int n=0;n<list.size();n++){
				if(list.get(n).get("peo").equals(list.get(m).get("peo"))){
					a+=Integer.parseInt(list.get(n).get("cha").toString());
				}
				
				/*if(!list.get(n).get("finishedBy").equals("")){
					if(!list.get(m).get("finishedBy").equals("")){
						if(list.get(n).get("finishedBy").equals(list.get(m).get("finishedBy"))){
							a+=Integer.parseInt(list.get(n).get("cha").toString());
						}else if(list.get(n).get("finishedBy").equals(list.get(m).get("assignedTo"))){
							a+=Integer.parseInt(list.get(n).get("cha").toString());
						}
					}else if(list.get(n).get("finishedBy").equals(list.get(m).get("assignedTo"))){
						a+=Integer.parseInt(list.get(n).get("cha").toString());
					}
				}else if(!list.get(n).get("assignedTo").equals("")){
					if(list.get(n).get("assignedTo").equals(list.get(m).get("assignedTo")) || list.get(n).get("finishedBy").equals(list.get(m).get("assignedTo"))
							|| list.get(n).get("assignedTo").equals(list.get(m).get("finishedBy"))){
						a+=Integer.parseInt(list.get(n).get("cha").toString());
					}
				}*/
			}
			list.get(m).set("pday", a);
			list.get(m).set("cday", cday);
		}
		//计算每个人的天数的总数
		int ppday = 0;
		for(int x=0;x<list.size();x++){
			ppday += Integer.parseInt(list.get(x).get("cha").toString());
		}
		int kday = (int)cday - ppday;
		for(int y=0;y<list.size();y++){
			list.get(y).set("kday", kday).set("ctask", list.size()).set("gshi", ppday);
		}
		List<Record> l = new ArrayList<Record>();
		for(int y=0;y<list.size();y++){
			Record r = new Record();
			int pc = Integer.parseInt(list.get(y).get("cha").toString());
			String peo = "";
			for(int x=y+1;x<list.size();x++){
				if(list.get(x).get("peo").equals(list.get(y).get("peo"))){
					list.remove(x);
					x--;
				}
			}
			r.set("cha", list.get(y).get("pday")).set("cday", list.get(y).get("cday")).set("ctask", list.get(y).get("ctask")).set("kday", list.get(y).get("kday")).set("peo", list.get(y).get("peo")).set("gshi", list.get(y).get("gshi"));
			l.add(r);
		}
		if(l != null && l.size() > 0){
			for(int i=0;i<l.size();i++){
				if(!l.get(i).get("peo").toString().equals("")){
					String name = Db.findFirst("select realname from zt_user where account=?",l.get(i).get("peo")).get("realname");
					l.get(i).set("peo", name);
				}
				
			}
		}
		return l;
	}
	
	
	
	/**
	 * 查询任务量
	 * @param stime
	 * @param etime
	 * @param type
	 * @return
	 * @throws ParseException 
	 */
	public List<Record> findTask(String stime,String etime,String type) throws ParseException{
		
		StringBuffer sql = new StringBuffer();
		String typel = type.substring(0,type.length()-1);
		sql.append("select * from(SELECT story,DATE_FORMAT(estStarted,'%Y-%m-%d') AS kaishi,assignedTo,IF (finishedBy = '',assignedTo,finishedBy) AS peo,DATE_FORMAT(deadline,'%Y-%m-%d') AS jieshu FROM zt_task )")
			.append("as a where a.peo in(select u.account from zt_user u,zt_lang l where u.role=l.`key` and u.deleted ='0' and l.`key` in("+typel+")) and a.kaishi != '0000-00-00' and a.jieshu != '0000-00-00' order by a.peo,a.jieshu");
		List<Record> list = Db.find(sql.toString());
		List<Record> result = new ArrayList<Record>();
		//处理数据 
		for(int i=0;i<list.size();i++){
			Record r = new Record();
			//时间
			long kaishi = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).getStr("kaishi")).getTime();
			long jieshu = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).getStr("jieshu")).getTime();
			long start = new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime();
			long end = new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime();
			//只要数据的时间段有一部分在我选择的时间段内就可以
			if(kaishi <= start){
				//如果开始时间小于选择的开始时间,并且结束时间小于选择的结束时间，那么就以选择的时间作为开始时间
				if(start<=jieshu && jieshu <= end){
					r.set("story", list.get(i).get("story")).set("kaishi", list.get(i).get("kaishi")).set("peo", list.get(i).get("peo")).set("jieshu", list.get(i).get("jieshu"));
					result.add(r);
				}else if(jieshu > end){
					r.set("story", list.get(i).get("story")).set("kaishi", list.get(i).get("kaishi")).set("peo", list.get(i).get("peo")).set("jieshu", list.get(i).get("jieshu"));
					result.add(r);
				}
			}else if(kaishi > start){
				if(jieshu <= end){
					r.set("story", list.get(i).get("story")).set("kaishi", list.get(i).get("kaishi")).set("peo", list.get(i).get("peo")).set("jieshu", list.get(i).get("jieshu"));
					result.add(r);
				}else if(kaishi<= end && jieshu > end){
					r.set("story", list.get(i).get("story")).set("kaishi", list.get(i).get("kaishi")).set("peo", list.get(i).get("peo")).set("jieshu", list.get(i).get("jieshu"));
					result.add(r);
				}
			}else{
				i++;
			}
			
		}
		System.out.println(result.size()+"======================");
		int count = 0;
		for(int i=0;i<result.size();i++){
			//计算工时
			long days = 0;
			if(new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("kaishi")).getTime() <= new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime()){
				//如果开始时间小于选择的开始时间,并且结束时间小于选择的结束时间，那么就以选择的时间作为开始时间
				if(new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime()<=new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() && new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() <= new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime()){
					days = (new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() -new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime())/(24*60*60*1000)+1;
				}else if(new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime()){
					days = (new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime() -new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime())/(24*60*60*1000)+1;
				}
			}else if(new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("kaishi")).getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(stime).getTime()){
				if(new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() <= new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime()){
					days = (new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() -new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("kaishi")).getTime())/(24*60*60*1000)+1;
				}else if(new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("kaishi")).getTime()<= new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime() && new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("jieshu")).getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime()){
					days = (new SimpleDateFormat("yyyy-MM-dd").parse(etime).getTime() -new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getStr("kaishi")).getTime())/(24*60*60*1000)+1;
				}
			}
			//计算总天数
			result.get(i).set("days", days);
			count += Integer.parseInt(result.get(i).get("days").toString());
			result.get(i).set("countNum", result.size());
		}
		List<Record> result2 = new ArrayList<Record>();
		for(int i=0;i<result.size();i++){
			int pcount = 1;
			int pdays = Integer.parseInt(result.get(i).get("days").toString());
			Record r = new Record();
			for(int j=i+1;j<result.size();j++){
				if(result.get(i).get("peo").equals(result.get(j).get("peo"))){
					pcount ++;
					pdays += Integer.parseInt(result.get(j).get("days").toString());
					i++;
				}
			}
			r.set("count", count).set("pcount", pcount).set("countNum", result.get(i).get("countNum")).set("days", result.get(i).get("days")).set("peo", result.get(i).get("peo")).set("pdays", pdays);
			result.get(i).set("pcount", pcount);
			result.get(i).set("count", count);
			result2.add(r);
		}
		for(int i=0;i<result2.size();i++){
			//计算百分比
			String dp = new DecimalFormat("0.00").format((float)result2.get(i).getInt("pdays")/result2.get(i).getInt("count")*100)+"%";
			String tp = new DecimalFormat("0.00").format((float)result2.get(i).getInt("pcount")/result2.get(i).getInt("countNum")*100)+"%";
			result2.get(i).set("dpercent", dp).set("tpercent", tp);
			String name = Db.findFirst("select realname from zt_user where account=?",result2.get(i).get("peo")).get("realname");
			result2.get(i).set("peo", name);
		}
		return result2;
	}
	
	public List<Record> findDelay(String stime,String etime,String type,String group){
		
		StringBuffer sql = new StringBuffer();
		String typel = type.substring(0,type.length()-1);
		
		if(group.equals("dev")){
			sql.append("SELECT * FROM(SELECT estStarted,DATE_FORMAT(now(), '%Y-%m-%d') AS now,deadline,TIMESTAMPDIFF(DAY, deadline, now()) AS days,`status`,")
			.append(" IF (assignedTo = '',openedBy,assignedTo) AS peo FROM zt_task WHERE deadline < DATE_FORMAT(now(), '%Y-%m-%d') AND `status` NOT IN ('closed', 'done')")
			.append(" AND deadline != '0000-00-00') AS a WHERE a.peo IN (SELECT u.account FROM zt_user u,zt_lang l WHERE u.role = l.`key` AND u.deleted = '0'")
			.append(" AND l.`key` IN ("+typel+")) AND a.estStarted >= '"+stime+"' AND a.estStarted <= '"+etime+"' ORDER BY a.peo");
		}else if(group.equals("xuqiu")){
			
		}
		
		
		List<Record> list = Db.find(sql.toString());
		List<Record> l = new ArrayList<Record>();
		for(int i=0;i<list.size();i++){
			Record r = new Record();
			int count = 1;
			int days = Integer.parseInt(list.get(i).getLong("days").toString());
			for(int j=i+1;j<list.size();j++){
				if(list.get(j).get("peo").equals(list.get(i).get("pao"))){
					count++;
					i++;
					days += list.get(j).getInt("days");
				}
			}
			String name = Db.findFirst("select realname from zt_user where account=?",list.get(i).get("peo")).get("realname");
			r.set("count", count).set("peo", name).set("days", days);
			l.add(r);
		}
		return l;
		
	}
	
	/**
	 * 查询日报统计
	 * @param stime
	 * @return
	 */
	public Record findDailyCount(String stime,String project){
		
		Record result = new Record();
		//1.统计新开的任务
		List<Record> nt = Db.find("select name from zt_task where DATE_FORMAT(openedDate,'%Y-%m-%d')=DATE_FORMAT('"+stime+"','%Y-%m-%d') and project='"+project+"' ORDER BY name asc");
		//2.统计进行中的任务
		List<Record> dt = Db.find("select name from zt_task where '"+stime+"' BETWEEN estStarted and deadline and project='"+project+"' ORDER BY name asc");
		//3.统计完成的任务
		List<Record> ft = Db.find("select name from zt_task where DATE_FORMAT(finishedDate,'%Y-%m-%d')=DATE_FORMAT('"+stime+"','%Y-%m-%d') and project='"+project+"' ORDER BY name asc");
		//4.统计任务的总量
		int jtcount = nt.size()+dt.size();
		int ftcount = ft.size();
		//5.统计BUG的总量
		int jbcount = Integer.parseInt(Db.findFirst("select count(*) jcount from zt_bug where DATE_FORMAT(openedDate,'%Y-%m-%d')='"+stime+"'").get("jcount").toString());
		int fbcount = Integer.parseInt(Db.findFirst("select count(*) fcount from zt_bug where DATE_FORMAT(resolvedDate,'%Y-%m-%d')='"+stime+"'").get("fcount").toString());
		
		List<Record> listxinkai = new ArrayList<Record>();
		List<Record> listjinxing = new ArrayList<Record>();
		List<Record> listwancheng = new ArrayList<Record>();
		List<Record> listjinzongji = new ArrayList<Record>();
		List<Record> listbug = new ArrayList<Record>();
		
		for(int i=0;i<(jtcount+ftcount+2);i++){
			Record r = new Record();
			if(i<nt.size()){
				r.set("type", "新开任务").set("name", nt.get(i).get("name"));
				listxinkai.add(r);
			}else if(i>=nt.size() && i<dt.size()+nt.size()){
				r.set("type", "进行中任务").set("name", dt.get(i-nt.size()).get("name"));
				listjinxing.add(r);
			}else if(i>=jtcount && i<jtcount+ftcount){
				r.set("type", "完成任务").set("name", ft.get(i-jtcount).get("name"));
				listwancheng.add(r);
			}else if(i>=jtcount+ftcount && i<jtcount+ftcount+1){
				r.set("type", "总计").set("name", (jtcount-dt.size())+":"+ftcount);
				listjinzongji.add(r);
			}else if(i == jtcount+ftcount+1){
				r.set("type", "BUG").set("name", jbcount+":"+fbcount);
				listbug.add(r);
			}
		}
		result.set("listxinkai", listxinkai);
		result.set("listjinxing", listjinxing);
		result.set("listwancheng", listwancheng);
		result.set("listjinzongji", listjinzongji);
		result.set("listbug", listbug);
		return result;
	}
	
	/**
	 * 查询日报统计2
	 * @param stime
	 * @return
	 */
	public Object findDailyCount1(String stime,String project){
		
		List<Record> list = new ArrayList<Record>();
		//1.统计新开的任务
		List<Record> nt = Db.find("select name from zt_task where DATE_FORMAT(openedDate,'%Y-%m-%d')=DATE_FORMAT('"+stime+"','%Y-%m-%d') and project='"+project+"' ORDER BY name");
		//2.统计进行中的任务
		List<Record> dt = Db.find("select name from zt_task where '"+stime+"' BETWEEN estStarted and deadline and project='"+project+"'");
		//3.统计完成的任务
		List<Record> ft = Db.find("select name from zt_task where DATE_FORMAT(finishedDate,'%Y-%m-%d')=DATE_FORMAT('"+stime+"','%Y-%m-%d') and project='"+project+"'");
		//4.统计任务的总量
		int jtcount = nt.size()+dt.size();
		int ftcount = ft.size();
		//5.统计BUG的总量
		int jbcount = Integer.parseInt(Db.findFirst("select count(*) jcount from zt_bug where DATE_FORMAT(openedDate,'%Y-%m-%d')='"+stime+"'").get("jcount").toString());
		int fbcount = Integer.parseInt(Db.findFirst("select count(*) fcount from zt_bug where DATE_FORMAT(resolvedDate,'%Y-%m-%d')='"+stime+"'").get("fcount").toString());
		
		for(int i=0;i<(jtcount+ftcount+2);i++){
			Record r = new Record();
			if(i<nt.size()){
				r.set("type", "新开任务").set("name", nt.get(i).get("name"));
			}else if(i>=nt.size() && i<dt.size()+nt.size()){
				r.set("type", "进行中任务").set("name", dt.get(i-nt.size()).get("name"));
			}else if(i>=jtcount && i<jtcount+ftcount){
				r.set("type", "完成任务").set("name", ft.get(i-jtcount).get("name"));
			}else if(i>=jtcount+ftcount && i<jtcount+ftcount+1){
				r.set("type", "总计").set("name", (jtcount-dt.size())+":"+ftcount);
			}else if(i == jtcount+ftcount+1){
				r.set("type", "BUG").set("name", jbcount+":"+fbcount);
			}
			list.add(r);
			
		}
		return list;
	}
	
}
