package com.manji.data.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 该类用于手动导入所有审核数据
 * @author Administrator
 *
 */

public class InputData {

	/**
	 * 当天时间
	 * @return
	 */
	public String calendarDate(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,0);
		String today = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		return today;
	}
	
	/**
	 * 统计商家审核信息
	 * @return
	 */
	public List<Record> shopAudit(){
		
		String sql = "select remark,update_time from dt_user_role_shopinfo where remark like '%Time%' ";

		List<Record> record	= Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计入驻和店铺审核信息
	 * @return
	 */
	public List<Record> enterAudit(){
		
		String sql = "select remark,update_time from dt_user_role_shopinfo_temp where remark like '%Time%' ";
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计商品审核信息
	 * @return
	 */
	public List<Record> goodsAudit(){
		
		String sql = "select remark,update_time from dt_article  where remark like '%Time%' ";
		
		List<Record> record	= Db.find(sql);
		
		return record;
	}
	
	/**
	 * 统计公司审核信息
	 * @return
	 */
	public List<Record> companyAudit(){
		
		String sql = "select update_remark,update_time from dt_user_safeprotect where safe_type='COMPANY' and update_remark like '%Time%' ";
		
		List<Record> record = Db.find(sql);
		
		return record; 
	}
	
	/**
	 * 统计经营范围  
	 * @return
	 */
	public List<Record> scopeAudit(){
		
		String sql = "select remark from dt_user_role_shopinfo_scope  where remark like '%Time%' ";
		
		List<Record> record = Db.find(sql);
		
		return record;
	}
	
	
	
	
	/*****************************************************处理数据***************************************************/
	
	
	/**
	 * 店铺审核
	 */
	
	public void enterInput(){

		List<Record> record = enterAudit();

		for(Record xx:record){

			if (xx.get("remark") != null 
					&& "[".equals(xx.get("remark").toString().substring(0,1))
					&& "]".equals(xx.get("remark").toString().substring(xx.get("remark").toString().length()-1))
					){	

				JSONArray  jsonArray= JSONArray.fromObject(xx.get("remark"));

				for(int i=0;i<jsonArray.size();i++){

					Record auditRecord = new Record();
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
					String status = jsonObject.getString("Status");
					int state;
					switch(status){

					case "同意":
						state = 1;
					case "通过":
						state = 1;
						break;
					case "不通过":
						state = 2;
					default:
						state = 2;
					}

					String audit_name = jsonObject.getString("Audit");

					if(audit_name.indexOf("[") != -1){

						audit_name = audit_name.substring(5);
					}else{
						audit_name = audit_name.substring(3);
					}

					auditRecord.set("examine_name", audit_name);
					auditRecord.set("examine_time", jsonObject.get("Time"));
					auditRecord.set("is_adopt", state);
					auditRecord.set("examine_type", 1);
					
					Db.save("dt_audits", auditRecord);
					
				}
			}
		}
	}	
	
	/**
	 * 商品审核
	 */
	public void goodsInput(){

		List<Record> record = goodsAudit();

		for(Record xx:record){

			if (xx.get("remark") != null 
					&& "[".equals(xx.get("remark").toString().substring(0,1))
					&& "]".equals(xx.get("remark").toString().substring(xx.get("remark").toString().length()-1))
					){	

				JSONArray  jsonArray= JSONArray.fromObject(xx.get("remark"));

				for(int i=0;i<jsonArray.size();i++){

					Record auditRecord = new Record();
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));

					String audit_name = jsonObject.getString("Audit");

					if(audit_name.indexOf("[") != -1){

						audit_name = audit_name.substring(5);
					}

					auditRecord.set("examine_name", audit_name);
					auditRecord.set("examine_time", jsonObject.get("Time"));
					auditRecord.set("examine_type", 2);
					
					Db.save("dt_audits", auditRecord);

				}
			}
		}
	}	
	
	/**
	 * 公司信息变更审核
	 */
	public void companyInput(){

		List<Record> record = companyAudit();

		for(Record xx:record){

			if (xx.get("update_remark") != null 
					&& "[".equals(xx.get("update_remark").toString().substring(0,1))
					&& "]".equals(xx.get("update_remark").toString().substring(xx.get("update_remark").toString().length()-1))
					){	

				JSONArray  jsonArray= JSONArray.fromObject(xx.get("update_remark"));

				for(int i=0;i<jsonArray.size();i++){

					Record auditRecord = new Record();
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));

					String audit_name = jsonObject.getString("Audit");

					if(audit_name.indexOf("[") != -1){

						audit_name = audit_name.substring(5);
					}

					String status = jsonObject.getString("Status");
					int state;
					switch(status){

					case "同意":
						state = 1;
					case "通过":
						state = 1;
						break;
					case "不通过":
						state = 2;
					default:
						state = 2;
					}

					auditRecord.set("examine_name", audit_name);
					auditRecord.set("examine_time", jsonObject.get("Time"));
					auditRecord.set("is_adopt", state);
					auditRecord.set("examine_type", 3);
					
					Db.save("dt_audits", auditRecord);

				}

			}
		}
	}	
	
	/**
	 * 经营范围
	 */
	public void scopeInput(){

		List<Record> record = scopeAudit();

		for(Record xx:record){

			if (xx.get("remark") != null 
					&& "[".equals(xx.get("remark").toString().substring(0,1))
					&& "]".equals(xx.get("remark").toString().substring(xx.get("remark").toString().length()-1))
					){	

				JSONArray  jsonArray= JSONArray.fromObject(xx.get("remark"));

				for(int i=0;i<jsonArray.size();i++){

					Record auditRecord = new Record();
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));

					String audit_name = jsonObject.getString("Audit");

					if(audit_name.indexOf("[") != -1){

						audit_name = audit_name.substring(5);
					}

					String status = jsonObject.getString("Status");
					int state;
					switch(status){

					case "同意":
						state = 1;
					case "通过":
						state = 1;
						break;
					case "不通过":
						state = 2;
					default:
						state = 2;
					}

					auditRecord.set("examine_name", audit_name);
					auditRecord.set("examine_time", jsonObject.get("Time"));
					auditRecord.set("is_adopt", state);
					auditRecord.set("examine_type", 4);
					
					Db.save("dt_audits", auditRecord);
					
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
