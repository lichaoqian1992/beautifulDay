package com.manji.finance.recharge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.recharge.enums.AllStatusEnum;
import com.manji.finance.recharge.enums.CheckStatusEnums;
import com.manji.finance.recharge.enums.RechargeTypeEnums;
import com.manji.finance.recharge.model.ExcelVO;
import com.manji.finance.recharge.model.RecLogs;
import com.manji.finance.recharge.model.RechargeDetail;
import com.manji.finance.recharge.requestParams.HttpRequestParam;
import com.manji.finance.recharge.requestParams.HttpResponseParam;
import com.manji.finance.recharge.requestParams.RechargeParams;
import com.manji.finance.system.HttplogDO;
import com.manji.finance.system.SystemLogsRepository;
import com.manji.finance.system.UserSmsLogDO;
import com.manji.finance.system.httplogRepository;
import com.manji.finance.utils.DButils;
import com.manji.finance.utils.ExcelReadUtil;
import com.manji.finance.utils.ExcelUtils;
import com.manji.finance.utils.SmsLogsUtils;
import com.manji.finance.utils.TimeUtils;
import com.manji.finance.utils.VerificationCodeUtils;

public class RechargeService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	RechargeRepository rechargeRepository = new RechargeRepository();
	
	SystemLogsRepository systemLogsRepository = new SystemLogsRepository();
	
	public String findUserInfo(String accountName){
		
		String user_id = "",message = "",role_type = "",role_value = "",person_info = "";
		List<Record> list = rechargeRepository.findUserInfo(accountName);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				user_id = list.get(0).get("id").toString();
				if(i != list.size()-1){
					role_type += list.get(i).get("role_type").toString()+",";
					role_value += list.get(i).get("role_value").toString()+",";
				}else{
					role_type += list.get(i).get("role_type").toString();
					role_value += list.get(i).get("role_value").toString();
				}
			}
			
			List<Record> person = DButils.sqlserver.find("select person_name,card_number from dt_user_personinfo where user_id="+user_id);
			if(person != null && person.size()>0){
				person_info = person.get(0).get("person_name").toString()+","+person.get(0).get("card_number").toString();
			}
			message = role_type+";"+role_value+";"+person_info+";"+user_id;
		}
		return message;
		
	}
	/**
	 * 查询还未提交申请的充值信息
	 * @param type
	 * @return
	 */
	public List<Record> findRechargeInfo(String type){
		//System.out.println(type);
		List<Record> list = rechargeRepository.findRechargeInfo(type);
		for(int i=0;i<list.size();i++){
			list.get(i).set("RECHARGE_TYPE", RechargeTypeEnums.getMessageByCode(list.get(i).get("RECHARGE_TYPE").toString()));
			list.get(i).set("withDrawls", RechargeTypeEnums.getMessageByCode(list.get(i).get("withDrawls").toString()));
		}
		return list;
	}
	/**
	 * 根据充值单号删除充值信息
	 * @return
	 */
	public Boolean delRechargeInfoByOrderNo(String id){
		
		RechargeDetail r = new RechargeDetail();
		r.set("ID", id);
		//执行删除的逻辑
		Boolean flag = rechargeRepository.delRechargeInfoById(id);
		
		return flag;
	}
	/**
	 * 根据金额查询审批人
	 * @param money
	 * @return
	 */
	public List<Record> findRoleByMoney(double money){
		
		List<Record> list = rechargeRepository.findRoleByMoney(money);
		
		return list;
		
	}
	/**
	 * 保存充值数据
	 * @param r
	 * @throws ParseException 
	 */
	public int saveRechargeInfo(RechargeDetail r,String personRelease) throws ParseException{
		//在这里处理数据
		int num = 0;
		Date d = new Date();
		String orderNo = "MJ"+new SimpleDateFormat("yyyyMMddHHmmss").format(d)+"1";
		String transN = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(d)+"1";
		r.set("RECHARGE_ORDER_NO", orderNo);
		//System.out.println(r.get("RECHARGE_WAY"));
		if(r.get("RECHARGE_WAY").equals("临时充值")){
			r.set("OANO", "--");
		}
		r.set("tranSN", transN);
		r.set("ARRIVAL_MONEY", 0);
		
		//查询审批人信息    如果是  扣减，并且金额大于0 ，那么审批人有值，否则没有值
		if(r.get("RECHARGE_TYPE").toString().equals("5")){
			if(Double.parseDouble(r.get("RECHARGE_MONEY").toString()) > 0){
				List<Record> aa = rechargeRepository.findRoleByMoney(Double.parseDouble(r.get("RECHARGE_MONEY").toString()));
				r.set("PERSON_APPROVING", aa.get(0).get("role_alias"));
				r.set("PERSON_APPROVING_REALNAME", aa.get(0).get("REALNAME"));
				r.set("PERSON_APPROVING_ID", aa.get(0).get("USERNAME"));
			}else{
				r.set("PERSON_APPROVING", "无");
				r.set("PERSON_APPROVING_ID", "无");
				r.set("PERSON_APPROVING_REALNAME", "无");
			}
			r.set("withDrawls", 7);
		}else{
			List<Record> aa = rechargeRepository.findRoleByMoney(Double.parseDouble(r.get("RECHARGE_MONEY").toString()));
			r.set("PERSON_APPROVING", aa.get(0).get("role_alias"));
			r.set("PERSON_APPROVING_REALNAME", aa.get(0).get("REALNAME"));
			r.set("PERSON_APPROVING_ID", aa.get(0).get("USERNAME"));
		}
		//调用查询充值账号的Id,type,value
		String[] info = r.get("ROLE_TYPE").toString().split(",");//Shop,1166789,382763
		r.set("USER_ID", info[2]);
		r.set("ROLE_TYPE", info[0]);
		r.set("ROLE_VALUE", info[1]);
		//List<Record> list = rechargeRepository.findUserByName(r.get("USER_KEY").toString());
		/*if(list != null && list.size()>0){
			
		}else{
			return num;
		}*/
		r.set("STATUS", 3);
		//r.set("PERSON_RELEASE", personRelease);//发布人,相当于创建人
		r.set("APPROVING_TYPE", 1);//1表示还没有提交
		r.set("CHECK_STATUS", 0);//1 未审核 待审核 被撤回 已作废 已完成
		r.set("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
		rechargeRepository.saveRechargeInfo(r);
		String startTime = "";
		String endTime = "";
		num = findByCondition(r,startTime,endTime,1).getList().size();
		if(num > 0){
			Record u = rechargeRepository.findMysqlUserByName(r.get("PERSON_RELEASE").toString());
			RecLogs rl = new RecLogs();
			rl.setOrderNo(orderNo);
			rl.setModifyTime(sdf2.format(d));
			rl.setContent("创建充值单");
			rl.setOperator(u.get("role_alias")+"/"+u.get("REALNAME"));
			rl.setRemark("创建");
			//保存充值单操作日志
			rechargeRepository.saveRecLogs(rl);
			
			//保存系统操作日志
			Record record = new Record();
			String content = u.get("REALNAME")+"在"+sdf2.format(d)+"创建了充值单号为"+orderNo+"的充值单";
			record.set("account_name", r.get("PERSON_RELEASE").toString());
			record.set("log_info", content);
			record.set("create_time", sdf2.format(d));
			record.set("real_name", u.get("REALNAME"));
			systemLogsRepository.saveSysLogs(record);
		}
		return num;
	}
	/**
	 * 修改充值信息
	 * @return
	 */
	public int updateRechargeInfo(RechargeDetail r){
		int count = rechargeRepository.updateRechargeInfo(r);
		if(count > 0){
			Record u = rechargeRepository.findMysqlUserByName(r.get("PERSON_RELEASE").toString());
			Record order = rechargeRepository.findOrderById(r.get("ID").toString());
			//保存系统操作日志
			Date d = new Date();
			Record record = new Record();
			String content = u.get("REALNAME")+"在"+sdf2.format(d)+"修改了充值单号为"+order.get("RECHARGE_ORDER_NO")+"的充值单";
			record.set("account_name", r.get("PERSON_RELEASE").toString());
			record.set("log_info", content);
			record.set("create_time", sdf2.format(d));
			record.set("real_name", u.get("REALNAME"));
			systemLogsRepository.saveSysLogs(record);
		}
		return count;
	}
	/**
	 * 发送短信验证码
	 * @throws UnknownHostException 
	 */
	public String getYzm(RechargeParams param,String yzm) throws UnknownHostException{
		String status = "";
		InetAddress id=null;//获取IP地址
		List<UserSmsLogDO> list = new ArrayList<UserSmsLogDO>();
		double todayMoney = 0;
		Date d = new Date();
		Date t = new Date(d.getTime()+24*60*60*1000);
		String startTime = sdf.format(d);
		String endTime = sdf.format(t);
		
		//2.拼接短信内容
		Record u = rechargeRepository.findMysqlUserByName(param.getCreater());
		String message = u.get("REALNAME")+"通过财务系统向您发出充值审批请求，本次充值共"+param.getNumber()+"笔，总金额"+param.getMoney()+"元，单笔最大金额"+param.getMaxMoney()+"元，验证码为:"+yzm+"。如果您同意此次充值请将该短信验证码回复至"+u.get("REALNAME")+"。如有疑问请向"+u.get("REALNAME")+"咨询，联系电话"+u.get("MOBILE");
		//3.查询对应的审批人，并且判断审批人额度是否已满
		Record shenpi = rechargeRepository.findRoleByMoney(Double.parseDouble(param.getMaxMoney())).get(0);//查询的审批人信息
		//System.out.println(shenpi.get("role_alias").toString());
		List<Record> listRecord = rechargeRepository.findTodayMoneyById(shenpi.get("role_alias").toString(),startTime,endTime);
		if(listRecord != null && listRecord.size()>0){
			todayMoney = Double.parseDouble(listRecord.get(0).get("countMoney").toString());
		}
		if(Double.parseDouble(shenpi.get("TOTAL_MONEY").toString())<(todayMoney+Double.parseDouble(param.getMoney()))){
			//额度已满
			status = "FULL";
		}else{
			//接收短信的人的电话
			String mobile = rechargeRepository.findMysqlUserByName(shenpi.get("USERNAME").toString()).get("MOBILE");
			//发送消息
			UserSmsLogDO userSmsLogDO = new UserSmsLogDO();
            userSmsLogDO.setUserId(1);
            userSmsLogDO.setUserRoleType("用户");
            userSmsLogDO.setUserRoleValue(1);
            userSmsLogDO.setType("临时充值验证码推送");
            userSmsLogDO.setUserIp(id.getLocalHost().getHostAddress());
            userSmsLogDO.setContent(message);
            userSmsLogDO.setStatus(0);
            userSmsLogDO.setMobile(mobile);
            userSmsLogDO.setAddTime(sdf2.format(d));
            userSmsLogDO.setSendTime(sdf2.format(d));
            //调用发送短信的接口
            list.add(userSmsLogDO);
            int a = SmsLogsUtils.sendMessage(list);
            if(a > 0){
            	status = "SUCCESS";
            }else{
            	status = "ERROR";
            }
		}
		return status;
	}
	/**
	 * 提交充值申请
	 * @return
	 */
	public int updateCheckStatus(String idList,String operator){
		String d = sdf2.format(new Date());
		int num = 0;
		//拆分idList
		String[] str = idList.split(";");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<str.length;i++){
			String sql = "update t_interior_recharge_detail set CHECK_STATUS='1',STATUS='4',MODIFY_TIME='"+d+"' WHERE ID='"+str[i]+"'";
			list.add(i, sql);
		}
		int[] count = rechargeRepository.updateCheckStatus(list);
		for(int i=0;i<count.length;i++){
			if(count[i] > 0){
				Record r = rechargeRepository.findMysqlUserByName(operator);
				Record r2 = rechargeRepository.findOrderById(str[i]);
				RecLogs rl = new RecLogs();
				rl.setOrderNo(r2.get("RECHARGE_ORDER_NO").toString());
				rl.setModifyTime(d);
				rl.setContent("提交充值单");
				rl.setOperator(r.get("role_alias")+"/"+r.get("REALNAME"));
				rl.setRemark("提交");
				//保存操作日志
				rechargeRepository.saveRecLogs(rl);
				
				//保存系统操作日志
				Record record = new Record();
				String content = r.get("REALNAME")+"在"+d+"提交了充值单号为"+r2.get("RECHARGE_ORDER_NO")+"的充值单";
				record.set("account_name", r.get("USERNAME").toString());
				record.set("log_info", content);
				record.set("create_time", d);
				record.set("real_name", r.get("REALNAME"));
				systemLogsRepository.saveSysLogs(record);
			}
			num +=count[i];
		}
		return num;
	}
	/**
	 * 修改退回的充值申请（临时充值）
	 * @return
	 */
	public int updateRecBack(RechargeParams param){
		int a = rechargeRepository.updateRecBack(param);
		
		return a;
	}
	/**
	 * 作废充值单
	 * @return
	 */
	public int cancelOrder(String orderNo,String reason,String creater){
		int a = rechargeRepository.cancelOrder(orderNo,"4");//已作废
		if(a>0){
			String d = sdf2.format(new Date());
			Record r = rechargeRepository.findMysqlUserByName(creater);
			RecLogs rl = new RecLogs();
			rl.setOrderNo(orderNo);
			rl.setModifyTime(d);
			rl.setContent("作废充值单");
			rl.setOperator(r.get("role_alias")+"/"+r.get("REALNAME"));
			rl.setRemark(reason);
			//保存操作日志
			rechargeRepository.saveRecLogs(rl);
			
			//保存系统操作日志
			Record record = new Record();
			String content = r.get("REALNAME")+"在"+d+"作废了充值单号为"+orderNo+"的充值单，作废原因："+reason;
			record.set("account_name", r.get("USERNAME").toString());
			record.set("log_info", content);
			record.set("create_time", d);
			record.set("real_name", r.get("REALNAME")); 
			systemLogsRepository.saveSysLogs(record);
		}
		return a;
	}
	/**
	 * 撤回充值单
	 * @return
	 */
	public int backOrder(String orderNo,String reason,String creater){
		int a = rechargeRepository.cancelOrder(orderNo,"3");//被撤回
		if(a>0){
			String d = sdf2.format(new Date());
			Record r = rechargeRepository.findMysqlUserByName(creater);
			RecLogs rl = new RecLogs();
			rl.setOrderNo(orderNo);
			rl.setModifyTime(d);
			rl.setContent("撤回充值单");
			rl.setOperator(r.get("role_alias")+"/"+r.get("REALNAME"));
			rl.setRemark(reason);
			//保存操作日志
			rechargeRepository.saveRecLogs(rl);
			
			//保存系统操作日志
			Record record = new Record();
			String content = r.get("REALNAME")+"在"+d+"撤回了充值单号为"+orderNo+"的充值单，撤回原因："+reason;
			record.set("account_name", r.get("USERNAME").toString());
			record.set("log_info", content);
			record.set("create_time", d);
			record.set("real_name", r.get("REALNAME")); 
			systemLogsRepository.saveSysLogs(record);
		}
		return a;
	}
	/**
	 * 批量同意或者同意充值申请
	 * @param orderList
	 * @param creater
	 * @return
	 */
	public int batchAgree(String orderList,String creater){
		//1.处理单号
		int count = 0;
		String reason = "同意";
		String[] str = orderList.split(";");
		for(int i=0;i<str.length;i++){
			int a = rechargeRepository.cancelOrder(str[i],"2");//同意之后变成待确认
			if(a>0){
				String d = sdf2.format(new Date());
				Record r = rechargeRepository.findMysqlUserByName(creater);
				RecLogs rl = new RecLogs();
				rl.setOrderNo(str[i]);
				rl.setModifyTime(d);
				rl.setContent("同意充值单");
				rl.setOperator(r.get("role_alias")+"/"+r.get("REALNAME"));
				rl.setRemark(reason);
				//保存操作日志
				rechargeRepository.saveRecLogs(rl);
				
				//保存系统操作日志
				Record record = new Record();
				String content = r.get("REALNAME")+"在"+d+"同意了充值单号为"+str[i]+"的充值单";
				record.set("account_name", r.get("USERNAME").toString());
				record.set("log_info", content);
				record.set("create_time", d);
				record.set("real_name", r.get("REALNAME")); 
				systemLogsRepository.saveSysLogs(record);
				count++;
			}
		}
		
		return count;
	}
	/**
	 * 批量和单个充值金额
	 * @throws IOException 
	 */
	public String confirmRec(String orderList,String accountList,String creater) throws IOException{
		int s = 0,f = 0,option=0;//记录成功失败的数据条数
		String message = "",danhao = "";
		String czStatus = "";//充值状态
		String status = "",remark = "";//返回参数
		HttpResponseParam result = null;
		double arrivalMoney = 0;
		String arrivalTime = "";
		String[] orderNo = orderList.split(";");//更新本地数据的状态
		String[] accountName = accountList.split(";");//实现充值必须
		for(int i=0;i<orderNo.length;i++){
			Record r = rechargeRepository.findOrderByNo(orderNo[i]);
			//1.得到用户id,type,value
			Date d = new Date();
			//Record user = rechargeRepository.findUserByName(accountName[i]).get(0);
			//2.判断该用户是否属于密集充值（1-2小时内）
			List<Record> list = rechargeRepository.findRecordInTwoHours(r.get("USER_ID").toString());
			if(list.size()>10){
				status += "密集充值;";//属于密集充值
				danhao += orderNo[i]+";";
				f++;
			}else{
				HttpRequestParam param = new HttpRequestParam();
				if(r.get("RECHARGE_TYPE").toString().equals("5")){
					//1.判断是增加金额还是减少金额
					if(Double.parseDouble(r.get("RECHARGE_MONEY").toString()) > 0){
						//增加金额   操作：6增加 4扣除
						option = 6;
					}else{
						//减少金额   4
						option = 4;
					}
					//2.调用充值的接口
					String timeStamp = d.getTime()+1+"";
					param.setAccessKey("javamanager");
		            param.setAction("AccountAmountSet");
		            param.setMoney(Math.abs(Double.parseDouble(r.get("RECHARGE_MONEY").toString())));
		            param.setNonceStr(timeStamp);
		            param.setOption(option);
		            param.setRemark(r.get("REMARK").toString());
		            param.setRoleType(r.get("ROLE_TYPE").toString());
		            param.setRoleValue(Integer.parseInt(r.get("ROLE_VALUE").toString()));
		            param.setTranSN(format2.format(d));//业务单号
		            param.setUserId(Integer.parseInt(r.get("USER_ID").toString()));
		            result = HttpClientUtils.getReturnMessage(param);
				}else{
					String timeStamp = d.getTime()+1+"";
					param.setAccessKey("javamanager");
		            param.setAction("UserFixedRecharge");
		            param.setMoney(Double.parseDouble(r.get("RECHARGE_MONEY").toString()));
		            param.setNonceStr(timeStamp);
		            param.setRoleType(r.get("ROLE_TYPE").toString());
		            param.setRoleValue(Integer.parseInt(r.get("ROLE_VALUE").toString()));
		            param.setTranSN(format2.format(d));//业务单号
		            param.setUserId(Integer.parseInt(r.get("USER_ID").toString()));
		            param.setWithdraw(r.get("withDrawls").toString());
		            result = HttpClientUtils.getReturnMessage(param);
				}
	            if(result != null){
	                if(result.getErrCode().equals("0")){
	                    if(result.getResultCode().equals("8")){//处理成功
	                        czStatus = "6";
	                        status = "SUCCESS";//充值成功
	                        /**记录调用接口返回的数据*/
	                        HttplogDO httplogDO = new HttplogDO();
	                        httplogDO.setAction(result.getAction());
	                        httplogDO.setQuerySN(result.getQuerySN());
	                        httplogDO.setTranSN(result.getTranSN());
	                        httplogDO.setResultData(result.getResultData());
	                        httplogDO.setCreatedate(sdf2.format(d));
	                        httplogRepository.addHttplog(httplogDO);
	                        s++;
	                        if(r.get("withDrawls").toString().equals("7")){
	                        	arrivalMoney = Double.parseDouble(r.get("RECHARGE_MONEY").toString());
	                        }else{
	                        	arrivalMoney = Double.parseDouble(result.getResultData());
	                        }
	                        arrivalTime = sdf2.format(d);
	                        remark = r.get("REMARK");
	                    }else if(result.getResultCode().equals("4")){//处理失败
	                        czStatus = "5";
	                        status += result.getResultData()+";";//充值失败
	                        danhao += orderNo[i]+";";
	                        f++;
	                        arrivalMoney = 0.00;
	                        arrivalTime = sdf2.format(d);
	                        remark = result.getResultData();
	                    }
	                }else{
	                	czStatus = "5";
	                	f++;
	                	danhao += orderNo[i]+";";
	                	status += result.getMessage()+";";
	                	remark = result.getMessage();
	                }
	            }else{
	            	f++;
	            	czStatus = "5";
	            	status = "没有可调用的接口";//充值失败
                    danhao += orderNo[i]+";";
                    f++;
                    arrivalMoney = 0.00;
                    arrivalTime = sdf2.format(d);
                    remark = status;
	            }
	          //更改数据库数据的状态（修改充值状态和审核状态）
                int mm = rechargeRepository.updateByOrderNo(orderNo[i],czStatus,arrivalMoney,arrivalTime,remark);
                if(mm > 0){
                	//A.记录充值单操作日志
                	Record xx = rechargeRepository.findMysqlUserByName(creater);
                	RecLogs rl = new RecLogs();
    				rl.setOrderNo(orderNo[i]);
    				rl.setModifyTime(sdf2.format(d));
    				rl.setContent("确认充值单");
    				rl.setOperator(xx.get("role_alias")+"/"+xx.get("REALNAME"));
    				rl.setRemark("确认");
    				//保存充值单操作日志
    				rechargeRepository.saveRecLogs(rl);
    				//B.记录系统操作日志
    				Record record = new Record();
    				String content = xx.get("REALNAME")+"在"+sdf2.format(d)+"为用户"+accountName[i]+"充值"+r.get("RECHARGE_MONEY").toString()+"元，充值单号："+orderNo[i];
    				record.set("account_name", xx.get("USERNAME").toString());
    				record.set("log_info", content);
    				record.set("create_time", sdf.format(d));
    				record.set("real_name", xx.get("REALNAME")); 
    				systemLogsRepository.saveSysLogs(record);
    				
                }
			}
		}//成功5条，失败1条，失败单号：，失败原因：密集充值
		if(f==0){
			message = "成功"+s+"条。";
		}else if(f > 0){
			message = "成功"+s+"条，失败"+f+"条，失败单号："+danhao+"失败原因："+status;
		}
		
		return message;
		
	}
	/**
	 * 查询充值记录
	 * @return
	 * @throws ParseException 
	 */
	public Page<Record> findRecord(String orderNo,String accountName,String status,String rechargeWay,String startTime,String endTime,int pageNum) throws ParseException{
		
		RechargeDetail r = new RechargeDetail();
		r.set("RECHARGE_ORDER_NO", orderNo);
		r.set("USER_KEY", accountName);
		r.set("STATUS", status);
		try {
			r.set("RECHARGE_WAY", new String(rechargeWay.getBytes("iso8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.set("CHECK_STATUS", 5);
		
		Page<Record> page= findByCondition(r,startTime,endTime,pageNum);
		List<Record> list = page.getList();
		for(int i=0;i<list.size();i++){
			list.get(i).set("RECHARGE_TYPE", RechargeTypeEnums.getMessageByCode(list.get(i).get("RECHARGE_TYPE").toString()));
			list.get(i).set("STATUS", AllStatusEnum.getMessageByCode(list.get(i).get("STATUS").toString()));
		}
		return page;
	}
	/**
	 * 查询当日和当月的充值总金额
	 */
	 public String findAcount(){
		 //得到当日和当月的时间
		 String nowDay = TimeUtils.getTimeUtilDay();
		 String nextDay = sdf.format(new Date(new Date().getTime()+24*60*60*1000));
		 String nowMonth = TimeUtils.getFirstDayInMonth();
		 String nextMonth = TimeUtils.getFirstDayNextMonth();
		 return rechargeRepository.findAcount(nowDay,nextDay,nowMonth,nextMonth);
	 }
	
	
	
	/**************************************************
	 * 
	 * 公用的一些方法
	 * 
	 **************************************************/
	/**
	 * 根据传的条件查询充值信息
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	public Page<Record> findByCondition(RechargeDetail r,String startTime,String endTime,int pageNum) throws ParseException{
		Page<Record> page = null;
		StringBuffer sql = new StringBuffer(" from t_interior_recharge_detail where 1=1");
		if(r != null){
			if(null != r.get("ID") && !r.get("ID").equals("")){
				sql.append(" and ID='"+r.get("ID")+"'");
			}
			if(null != r.get("RECHARGE_ORDER_NO") && !r.get("RECHARGE_ORDER_NO").equals("")){
				sql.append(" and RECHARGE_ORDER_NO='"+r.get("RECHARGE_ORDER_NO")+"'");
			}
			if(null != r.get("USER_KEY") && !r.get("USER_KEY").equals("")){
				sql.append(" and USER_KEY='"+r.get("USER_KEY")+"'");
			}
			if(null != r.get("CHECK_STATUS") && !r.get("CHECK_STATUS").equals("")){
				sql.append(" and CHECK_STATUS='"+r.get("CHECK_STATUS")+"'");
			}
			if(null != r.get("RECHARGE_TYPE") && !r.get("RECHARGE_TYPE").equals("")){
				sql.append(" and RECHARGE_TYPE='"+r.get("RECHARGE_TYPE")+"'");
			}
			if(null != r.get("RECHARGE_WAY") && !r.get("RECHARGE_WAY").equals("")){
					
				sql.append(" and RECHARGE_WAY ='"+r.get("RECHARGE_WAY")+"'");
			}
			if(null != r.get("STATUS") && !r.get("STATUS").equals("")){
				sql.append(" and STATUS='"+r.get("STATUS")+"'");
			}
			if(null != startTime && !startTime.equals("")){
				sql.append(" and CREATE_TIME>='"+startTime+"'");
			}
			if(null != endTime && !endTime.equals("")){
				String aa = sdf.format(new Date(sdf.parse(endTime).getTime()+24*60*60*1000));
				
				sql.append(" and CREATE_TIME<'"+aa+"'");
			}
			//查询到的是page对象
			page = rechargeRepository.findByConditionPage(sql.toString(),pageNum);
		}
		return page;
	}
	/**
	 * 查询充值订单详情
	 * @param orderNO
	 * @return
	 */
	public List<Record> findOrderDetail(String orderNo){
		
		List<Record> list = rechargeRepository.findOrderDetail(orderNo);
		for(int i=0;i<list.size();i++){
			list.get(i).set("RECHARGE_TYPE", RechargeTypeEnums.getMessageByCode(list.get(i).get("RECHARGE_TYPE").toString()));
			list.get(i).set("CHECK_STATUS", CheckStatusEnums.getMessageByCode(list.get(i).get("CHECK_STATUS").toString()));
			list.get(i).set("withDrawls", RechargeTypeEnums.getMessageByCode(list.get(i).get("withDrawls").toString()));
		}
		return list;
		
	}
	/**
	 * 查询充值订单操作日志
	 * @param orderNO
	 * @return
	 */
	public List<Record> findRechargeLogs(String orderNo){
		
		return rechargeRepository.findRechargeLogs(orderNo);
	}
	/**
	 * 上传文件
	 * @return
	 */
	public String uploadExcel(String excelPath,Record user){
		String  status="";
        final int[] userId2 = new int[1];
        String content = "";
        String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            String czStatus = "";//充值状态
            String userKeyStr = "";//用户标识
            int userId=0;//用户ID
            int roleValue=0;//角色值
            String userName = "";//用户名称
            String roleType = "";//角色类型
            String remark = "";//备注
            String personApproving = "";//审核人
            String personApprovingId = "";//审核人账号
            String personApprovingRealName = "";//审核人姓名
            double singleMoney = 0;
            List<RechargeDetail> interiorRechargeDetailDOList = new ArrayList<RechargeDetail>();
            //读取excel的内容，充值金额+用户标识；根据用户标识查询用户的用户ID和用户角色类型
            List<ExcelVO> vos = ExcelReadUtil.readExcel(excelPath);
            //System.out.println(vos.size());
            for(int i=0;i<vos.size();i++){
                if(StringUtils.isEmpty(vos.get(i).getOaNo()) && StringUtils.isEmpty(vos.get(i).getRechargeType()+"") && StringUtils.isEmpty(vos.get(i).getUserName())){
                    break;
                }
                userKeyStr = vos.get(i).getUserKey();
                singleMoney = vos.get(i).getMoney();
                //获得充值用户的ID+角色类型
                List<Record> list = rechargeRepository.findUserInfo(userKeyStr);
                //生成充值订单
                if(list != null && list.size()>0){
                    userId = list.get(0).get("id");
                    roleType = list.get(0).get("role_type");
                    roleValue = list.get(0).get("role_value");
                    //查询该充值单对应的审核人
                    List<Record> rechargeConfigDOList = rechargeRepository.findRoleByMoney(singleMoney);
                    personApproving = rechargeConfigDOList.get(0).get("role_alias");//审批人角色
                    personApprovingId = rechargeConfigDOList.get(0).get("USERNAME");//审批人账号
                    personApprovingRealName = rechargeConfigDOList.get(0).get("REALNAME");//审批人姓名
                    czStatus = "3";
                    remark = vos.get(i).getRemark();
                    
                    RechargeDetail detailDO = new RechargeDetail();
                    Date date = new Date();
                    if(vos.get(i).getIdCard() == null || "".equals(vos.get(i).getIdCard())){
                    	detailDO.set("IDCARD","未知");
                    	detailDO.set("USER_NAME","未知");
                    }else{
                    	detailDO.set("USER_NAME",vos.get(i).getUserName());
                    	detailDO.set("IDCARD",vos.get(i).getIdCard());
                    }
                    detailDO.set("RECHARGE_ORDER_NO","MJ"+format.format(date)+i);
                    detailDO.set("OANO",vos.get(i).getOaNo());
                    detailDO.set("tranSN",new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)+i);
                    
                    detailDO.set("USER_KEY",vos.get(i).getUserKey());
                    
                    detailDO.set("RECHARGE_MONEY",vos.get(i).getMoney());
                    detailDO.set("ARRIVAL_MONEY",0.0);
                    detailDO.set("withDrawls",vos.get(i).getWithDrawls()+"");
                    detailDO.set("USER_ID",userId);
                    detailDO.set("ROLE_TYPE",roleType);
                    detailDO.set("ROLE_VALUE",roleValue);
                    detailDO.set("RECHARGE_TYPE",vos.get(i).getRechargeType()+"");//充值类型
                    detailDO.set("RECHARGE_WAY","OA充值");
                    detailDO.set("STATUS",czStatus);//充值状态
                    detailDO.set("PERSON_RELEASE",user.get("USERNAME"));//发布人
                    detailDO.set("PERSON_APPROVING",personApproving);//审核人
                    detailDO.set("PERSON_APPROVING_ID",personApprovingId);//审核人
                    detailDO.set("PERSON_APPROVING_REALNAME",personApprovingRealName);//审核人
                    detailDO.set("APPROVING_TYPE",1);
                    detailDO.set("CHECK_STATUS","0");
                    detailDO.set("CREATE_TIME",sdf2.format(date));
                    detailDO.set("MODIFY_TIME",sdf2.format(date));
                    detailDO.set("REMARK", remark);
                    interiorRechargeDetailDOList.add(detailDO);
                }else{
                    czStatus = "2";
                    remark = "充值用户不存在";
                }
            }
            int num = rechargeRepository.batchSaveRechargeInfo(interiorRechargeDetailDOList);
            if(num > 0){
                status = "SUCCESS";
                for(int i=0;i<num;i++){
                	Record r = rechargeRepository.findMysqlUserByName(user.get("USERNAME").toString());
                    //记录充值单的操作日志
    				RecLogs rl = new RecLogs();
    				rl.setOrderNo(interiorRechargeDetailDOList.get(i).get("RECHARGE_ORDER_NO").toString());
    				rl.setModifyTime(date2);
    				rl.setContent("审核充值金额");
    				rl.setOperator(r.get("role_alias")+"/"+r.get("REALNAME"));
                    rl.setRemark("审核");
                    rechargeRepository.saveRecLogs(rl);
                    
                  //记录系统日志信息
                    Record record = new Record();
    				content = user.get("REALNAME")+"在"+date2+"创建了充值单号为"+interiorRechargeDetailDOList.get(i).get("RECHARGE_ORDER_NO").toString()+"的充值单";
    				record.set("account_name", user.get("USERNAME").toString());
    				record.set("log_info", content);
    				record.set("create_time", date2);
    				record.set("real_name", user.get("REALNAME")); 
    				systemLogsRepository.saveSysLogs(record);
                }
                
            }
        } catch (FileNotFoundException e) {
            //logger.error("读取Excel错误 errorMsg={}", e.getMessage());
        	status = "NOTFOUND";
            return status;
        } catch (FileFormatException e) {
            //logger.error("读取Excel错误 errorMsg={}", e.getMessage());
        	status = "ERROR";
            return status;
        }
        return status;
		
	}
	/**
	 * 查询充值信息（根据充值条件）
	 * @return
	 * @throws ParseException 
	 */
	public Page<Record> findRecByCondition(RechargeParams r) throws ParseException{
		String aa = "";
		Page<Record> page = null;
		StringBuffer sql = new StringBuffer("from t_interior_recharge_detail where 1=1");
		if(r != null){
			
			if(null != r.getOrderNo() && !r.getOrderNo().equals("")){
				sql.append(" and RECHARGE_ORDER_NO='"+r.getOrderNo()+"'");
			}
			if(null != r.getAccountName() && !r.getAccountName().equals("")){
				sql.append(" and USER_KEY='"+r.getAccountName()+"'");
			}
			if(null != r.getRecType() && !r.getRecType().equals("")){
				if(r.getRecType().equals("充值申请")){
					if(null != r.getCheckStatus() && !r.getCheckStatus().equals("")){
						sql.append(" and CHECK_STATUS='"+r.getCheckStatus()+"'");
					}else{
						sql.append(" and CHECK_STATUS in('1','3','4')");
					}
				}else if(r.getRecType().equals("充值审核")){
					sql.append(" and CHECK_STATUS='1'");
				}else if(r.getRecType().equals("充值确认")){
					sql.append(" and CHECK_STATUS='2'");
				}else if(r.getRecType().equals("充值记录")){
					if(null != r.getStatus() && !r.getStatus().equals("")){
						sql.append(" and STATUS='"+r.getStatus()+"'");
					}else{
						sql.append(" and STATUS in ('6','5')");
					}
				}
			}
			if(null != r.getRechargeType() && !r.getRechargeType().equals("")){
				sql.append(" and RECHARGE_TYPE='"+r.getRechargeType()+"'");
			}
			if(null != r.getRechargeWay() && !r.getRechargeWay().equals("")){
				if(r.getRechargeWay().equals("OA充值")){
					sql.append(" and RECHARGE_WAY !='临时充值'");
				}else{
					sql.append(" and RECHARGE_WAY ='临时充值'");
				}	
			}
			if(r.getRecType().equals("充值记录")){
				if(null != r.getStartTime() && !r.getStartTime().equals("")){
					sql.append(" and ARRIVAL_TIME>='"+r.getStartTime()+"'");
				}
				if(null != r.getEndTime() && !r.getEndTime().equals("")){
					aa = sdf.format(new Date(sdf.parse(r.getEndTime()).getTime()+24*60*60*1000));
					sql.append(" and ARRIVAL_TIME<'"+aa+"'");
				}
			}else{
				if(null != r.getStartTime() && !r.getStartTime().equals("")){
					sql.append(" and CREATE_TIME>='"+r.getStartTime()+"'");
				}
				if(null != r.getEndTime() && !r.getEndTime().equals("")){
					aa = sdf.format(new Date(sdf.parse(r.getEndTime()).getTime()+24*60*60*1000));
					sql.append(" and CREATE_TIME<'"+aa+"'");
				}
			}
			if(null != r.getRemark() && !r.getRemark().equals("")){
				sql.append(" and remark like '%"+r.getRemark()+"%'");
			}
			//查询到的是page对象
			page = rechargeRepository.findByConditionPage(sql.toString(),r.getPageNum());
			for(int i=0;i<page.getList().size();i++){
				page.getList().get(i).set("RECHARGE_TYPE", RechargeTypeEnums.getMessageByCode(page.getList().get(i).get("RECHARGE_TYPE").toString()));
				page.getList().get(i).set("CHECK_STATUS", CheckStatusEnums.getMessageByCode(page.getList().get(i).get("CHECK_STATUS").toString()));
				page.getList().get(i).set("withDrawls", RechargeTypeEnums.getMessageByCode(page.getList().get(i).get("withDrawls").toString()));
				page.getList().get(i).set("STATUS", AllStatusEnum.getMessageByCode(page.getList().get(i).get("STATUS").toString()));
			}
		}
		return page;
	}
	/**
	 * 导出excel
	 * @param p
	 * @return
	 * @throws ParseException 
	 */
	public String excelRecRecord(RechargeParams p) throws ParseException{
		
		String excelName = "充值记录明细";
		String[] title = {"编号","交易流水号","充值单号","充值方式","充值类型","姓名","充值账户","充值金额（元）","到账金额（元）","状态","到账时间"};
		//查询数据
		StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where 1=1");
        if(p.getOrderNo() != null && !"".equals(p.getOrderNo())){
        	sql.append(" and RECHARGE_ORDER_NO='"+p.getOrderNo()+"'");
        }
        if(p.getAccountName() != null && !"".equals(p.getAccountName())){
        	sql.append(" and USER_KEY='"+p.getAccountName()+"'");
        }
        if(p.getStatus() != null && !"".equals(p.getStatus())){
        	sql.append(" and STATUS='"+p.getStatus()+"'");
        }else{
        	sql.append(" and STATUS in ('6','5')");
        }
        if(p.getRechargeWay() != null && !"".equals(p.getRechargeWay())){
        	if(p.getRechargeWay().equals("OA充值")){
        		sql.append(" and RECHARGE_WAY in('OA充值','自主添加')");
        	}else{
        		sql.append(" and RECHARGE_WAY='"+p.getRechargeWay()+"'");
        	}
        }
        if(p.getStartTime() != null && !"".equals(p.getStartTime()) && p.getEndTime() != null && !"".equals(p.getEndTime())){
        	String a = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(p.getEndTime()).getTime()+24*60*60*1000));
        	sql.append(" and ARRIVAL_TIME>='"+p.getStartTime()+"' and ARRIVAL_TIME<'"+a+"' ");
        }
        List<Record> list = rechargeRepository.findByCondition(sql.toString());//查询到的数据
        String[][] content = new String[list.size()][title.length];
		for(int i=0;i<list.size();i++){
			content[i][0] = i+1+"";
			content[i][1] = list.get(i).get("tranSN").toString();
			content[i][2] = list.get(i).get("RECHARGE_ORDER_NO").toString();
			content[i][3] = list.get(i).get("RECHARGE_WAY").toString();
			content[i][4] = RechargeTypeEnums.getMessageByCode(list.get(i).get("RECHARGE_TYPE").toString());
			content[i][5] = list.get(i).get("USER_NAME").toString();
			content[i][6] = list.get(i).get("USER_KEY").toString();
			content[i][7] = list.get(i).get("RECHARGE_MONEY").toString();
			content[i][8] = list.get(i).get("ARRIVAL_MONEY").toString();
			content[i][9] = AllStatusEnum.getMessageByCode(list.get(i).get("STATUS").toString());
			content[i][10] = list.get(i).get("ARRIVAL_TIME").toString();
		}
		String path = new ExcelUtils().excel(p, title, excelName, content);
		return path;
	}
}
