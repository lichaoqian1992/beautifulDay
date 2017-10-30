package com.manji.data.utils;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.repository.AuditStatisticsRespository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AuditInput {


	AuditStatisticsRespository asr = new AuditStatisticsRespository();
	

	/**
	 * 店铺审核
	 */
	
	public void enterAudit(){

		List<Record> record = asr.enterAudit();

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
	public void goodsAudit(){

		List<Record> record = asr.goodsAudit();

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
	public void companyAudit(){

		List<Record> record = asr.companyAudit();

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
	public void scopeAudit(){

		List<Record> record = asr.scopeAudit();

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
