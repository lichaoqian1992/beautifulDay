package com.manji.finance.recharge;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.manji.finance.recharge.model.RecLogs;
import com.manji.finance.recharge.model.RechargeDetail;
import com.manji.finance.recharge.requestParams.RechargeParams;
import com.manji.finance.utils.DButils;

public class RechargeRepository extends DButils{
	/**
	 * 根据账号查询用户的id
	 */
	public List<Record> findUserByName(String accountName){
		String sql = "select distinct b.user_id,b.role_type,b.role_value from dt_user_accountinfo b where b.user_id in(select a.id from dt_users a where a.user_name='"+accountName+"' or a.mobile='"+accountName+"' or a.email='"+accountName+"')";
		//System.out.println(sql);
		return sqlserver.find(sql);
	}
	/**
	 * 根据账号查询姓名和身份证
	 * @param accountName
	 * @return
	 */
	public List<Record> findUserInfo(String accountName){
		
		String sql = "select a.id,b.role_type,role_value  from dt_users a ,dt_user_accountinfo b where a.id=b.user_id and a.status=0 and b.state=1 and (a.user_name='"+accountName+"' or mobile='"+accountName+"' or email='"+accountName+"')";
		List<Record> list = sqlserver.find(sql);
		
		return list;
	}
	/**
	 * 查询审批人信息
	 * @param money
	 * @return
	 */
	public List<Record> findRoleByMoney(double money){
		
		return mysql.find("select a.id,a.role_alias,b.*,c.REALNAME,c.USERNAME,c.MOBILE from t_new_role a,t_recharge_config b,t_user c where a.id=b.role_id and a.id=c.T_CONFIG_ID and b.SINGLE_MAX_MONEY>="+money+" and b.SINGLE_MIN_MONEY<"+money);	
	}
	/**
	 * 查询审批人今天的额度
	 * @return
	 */
	public List<Record> findTodayMoneyById(String userId,String startTime,String endTime){
		
		List<Record> r = mysql.find("select COALESCE(sum(RECHARGE_MONEY),0)  countMoney from t_interior_recharge_detail  a where check_status !='0' and PERSON_APPROVING_ID='"+userId+"' and CREATE_TIME>='"+startTime+"' and CREATE_TIME<'"+endTime+"'");
		
		return r;
	}
	/**
	 * 查询未提交申请的充值信息
	 * @param type
	 * @return
	 */
	public List<Record> findRechargeInfo(String type){
		
		return mysql.find("select * from t_interior_recharge_detail where APPROVING_TYPE='1' and RECHARGE_WAY='"+type+"' and CHECK_STATUS='0'");
		
	}
	/**
	 * 保存充值信息
	 * @param r
	 */
	public void saveRechargeInfo(RechargeDetail r){
		
		r.save();
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public int batchSaveRechargeInfo(List<RechargeDetail> list){
		int m = 0;
		for(int i=0;i<list.size();i++){
			boolean a = list.get(i).save();
			if(a){
				m++;
			}
		}
		return m;
	}
	/**
	 * 修改充值信息
	 */
	public int updateRechargeInfo(RechargeDetail r){
		int count = 0;
		if(r.get("RECHARGE_TYPE").toString().equals("5")){
			count = mysql.update("update t_interior_recharge_detail set RECHARGE_TYPE=?,USER_KEY=?,USER_NAME=?,IDCARD=?,RECHARGE_MONEY=?,CHECK_STATUS=?,REMARK=? where ID=?",r.get("RECHARGE_TYPE"),r.get("USER_KEY"),r.get("USER_NAME"),r.get("IDCARD"),r.get("RECHARGE_MONEY"),r.get("CHECK_STATUS"),r.get("REMARK"),r.get("ID"));
		}else{
			count = mysql.update("update t_interior_recharge_detail set RECHARGE_TYPE=?,USER_KEY=?,USER_NAME=?,IDCARD=?,RECHARGE_MONEY=?,withDrawls=?,CHECK_STATUS=?,REMARK=? where ID=?",r.get("RECHARGE_TYPE"),r.get("USER_KEY"),r.get("USER_NAME"),r.get("IDCARD"),r.get("RECHARGE_MONEY"),r.get("withDrawls"),r.get("CHECK_STATUS"),r.get("REMARK"),r.get("ID"));
		}
		return count;
	}
	/**
	 * 修改退回的OA充值信息
	 */
	public int updateRechargeInfoOA(RechargeDetail r){
		
		int count = mysql.update("update t_interior_recharge_detail set RECHARGE_TYPE=?,USER_KEY=?,USER_NAME=?,IDCARD=?,RECHARGE_MONEY=?,withDrawls=?,CHECK_STATUS='1' where ID=?",r.get("RECHARGE_TYPE"),r.get("USER_KEY"),r.get("USER_NAME"),r.get("IDCARD"),r.get("RECHARGE_MONEY"),r.get("withDrawls"),r.get("ID"));
		return count;
	}
	/**
	 * 修改退回的充值信息（临时充值）
	 * @return
	 */
	public int updateRecBack(RechargeParams r){
		return mysql.update("update t_interior_recharge_detail set RECHARGE_TYPE=?,USER_KEY=?,USER_NAME=?,IDCARD=?,RECHARGE_MONEY=?,withDrawls=?,CHECK_STATUS=? where ID=?",r.getRechargeType(),r.getAccountName(),r.getUserName(),r.getIdCard(),r.getMoney(),r.getWithdrawlsType(),r.getCheckStatus(),r.getId());
	}
	/**
	 * 作废充值单,撤回充值单,同意充值单
	 * @return
	 */
	public int cancelOrder(String orderNo,String checkStatus){
		return mysql.update("update t_interior_recharge_detail set CHECK_STATUS=? where RECHARGE_ORDER_NO=?",checkStatus,orderNo);
	}
	/**
	 * 删除充值信息
	 */
	public Boolean delRechargeInfoById(String id){
		Record r = findOrderById(id);
		Boolean flag = mysql.deleteById("t_interior_recharge_detail", id);
		int i = mysql.update("delete from t_recharge_logs where ORDER_NO=?", r.get("RECHARGE_ORDER_NO").toString());
		if(flag){
			if(i>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	/**
	 * 查询充值记录
	 * @param sql
	 * @return
	 */
	public List<Record> findByCondition(String sql){
		
		return mysql.find(sql);	
	
	}
	/**
	 * 根据条件分页查询充值信息
	 * @param sql
	 * @return
	 */
	public Page<Record> findByConditionPage(String sql,int pageNum){
		//System.out.println(sql);
		return mysql.paginate(pageNum, 20, "select *", sql);
		
	}
	/**
	 * 根据条件查询总数据
	 * @return
	 */
	public int findCountByCondition(String sql){
		
		return mysql.queryFirst(sql);
	}
	/**
	 * 查询充值订单详情
	 * @param orderNo
	 * @return
	 */
	public List<Record> findOrderDetail(String orderNo){
		
		return mysql.find("select * from t_interior_recharge_detail where RECHARGE_ORDER_NO='"+orderNo+"'");
	}
	/**
	 * 查询充值订单操作日志
	 * @param orderNo
	 * @return
	 */
	public List<Record> findRechargeLogs(String orderNo){
		
		return mysql.find("select * from t_recharge_logs where ORDER_NO='"+orderNo+"'");
	}
	/**
	 * 提交充值申请
	 * @return
	 */
	public int[] updateCheckStatus(List<String> list){
		int[] count = mysql.batch(list, list.size());
		
		return count;
	}
	/**
	 * 查询当日和当月的充值总金额
	 * @param nowDay 今天
	 * @param nextDay 明天
	 * @param nowMonth 当月第一天
	 * @param nextMonth 下个月第一天
	 */
	public String findAcount(String nowDay,String nextDay,String nowMonth,String nextMonth){
		double today = mysql.find("select COALESCE(sum(ARRIVAL_MONEY),0.00) today from t_interior_recharge_detail where ARRIVAL_TIME BETWEEN ? and ?",nowDay,nextDay).get(0).get("today");
		double month = mysql.find("select COALESCE(sum(ARRIVAL_MONEY),0.00) month from t_interior_recharge_detail where ARRIVAL_TIME BETWEEN ? and ?",nowMonth,nextMonth).get(0).get("month");
		return today+";"+month;
	}
	/******************************************************************
	 * 
	 * 可以共用的查询方法
	 *
	 ******************************************************************/
	/**
	 * 根据登录人查询登录人的信息
	 * @param creater
	 * @return
	 */
	public Record findMysqlUserByName(String creater){

		return mysql.find("select * from t_user a,t_new_role b  where a.T_CONFIG_ID=b.id and a.USERNAME='"+creater+"'").get(0);
	}
	/**
	 * 根据主键才查询订单信息
	 * @return
	 */
	public Record findOrderById(String id){
		return mysql.findById("t_interior_recharge_detail", id);
	}
	/**
	 * 根据单号查询订单信息
	 * @return
	 */
	public Record findOrderByNo(String orderNo){
		
		return mysql.find("select * from t_interior_recharge_detail where RECHARGE_ORDER_NO=?",orderNo).get(0);
	}
	/**
	 * 根据单号修改充值状态和审核状态
	 * @return
	 */
	public int updateByOrderNo(String orderNo,String status,double arrivalMoney,String arrivalTime,String remark){
		return mysql.update("update t_interior_recharge_detail set STATUS=?,CHECK_STATUS='5',ARRIVAL_MONEY=?,ARRIVAL_TIME=?,REMARK=? where RECHARGE_ORDER_NO=?",status,arrivalMoney,arrivalTime,remark,orderNo);
	}
	/**
	 * 判断是否密集充值
	 * @return
	 */
	public List<Record> findRecordInTwoHours(String userId){
		
		return mysql.find("select * from t_interior_recharge_detail where ARRIVAL_TIME> DATE_SUB(NOW(),INTERVAL  2 HOUR) and USER_ID=? and STATUS='6'",userId);
	}
	/**
	 * 保存操作日志
	 */
	public int saveRecLogs(RecLogs rl){
		String sql = "insert into t_recharge_logs values('"+rl.getOrderNo()+"','"+rl.getOperator()+"','"+rl.getModifyTime()+"','"+rl.getContent()+"','"+rl.getRemark()+"')";
		
		return mysql.update(sql);
		
	}
}
