package com.manji.finance.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.system.requestParams.UserParams;
import com.manji.finance.utils.DButils;

public class SystemRepository extends DButils{

	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public Record findByUserName(String userName){
		if( mysql.find("select * from t_user where USERNAME=?",userName).size()>0){
			return mysql.find("select * from t_user where USERNAME=?",userName).get(0);
		}else{
			return null;
		}
		
	}
	/**
	 * 根据用户名查询用户角色
	 * @param userName
	 * @return
	 */
	public Set<String> findRoleByUserName(String userName){
		Set<String> roles=new HashSet<String>();
		String id = findByUserName(userName).get("T_CONFIG_ID").toString();
		Record list = mysql.find("select * from t_new_role where id=?",id).get(0);
		roles.add(list.get("role_name").toString());
		return roles;
	}
	
	
	/**
	 * 查询交易信息报送人员
	 * @return
	 */
	public List<Record> findSysPush(){
		
		return mysql.find("select * from t_systempush");
	}
	/**
	 * 修改或者保存短信接收人
	 * @return
	 */
	public int saveOrUpdateSys(String a,String b ,String c){
		
		return mysql.update("update t_systempush set USER_NAME='"+b+"',MOBILE='"+c+"' where ID='"+a+"'");
	}
	/**
	 * 新增短信接收人
	 * @return
	 */
	public int save(String b,String c){
		
		return mysql.update("insert into t_systempush(USER_NAME,MOBILE) values('"+b+"','"+c+"')");
	}
	
	public List<Record> findRoleAndConfig(String id){
		StringBuffer sql = new StringBuffer("select a.*,c.REALNAME from t_recharge_config a ,t_user c where a.role_id=c.T_CONFIG_ID and a.TOTAL_MONEY>0");
		if(id != null){
			sql.append(" and a.role_id='"+id+"'");
		}
		sql.append(" order by a.role_id");
		return mysql.find(sql.toString());
	}
	/**
	 * 修改或者新增审批人金额
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public int saveOrUpdate(String a,String b,String c,String d,String e){
		//1.先查询是新增还是修改
		int x=0,y=0;
		if(mysql.find("select * from t_recharge_config where role_id='"+d+"'").size()>0){
			String aa = "update t_recharge_config set SINGLE_MIN_MONEY='"+a+"',TOTAL_MONEY='"+c+"',SINGLE_MAX_MONEY='"+b+"',role_id='"+d+"' where id='"+e+"'";
			//System.out.println(aa);
			x = mysql.update(aa);
			
		}else{
			y = mysql.update("insert into t_recharge_config(role_id,SINGLE_MIN_MONEY,TOTAL_MONEY,SINGLE_MAX_MONEY,CREATE_TIME,MODIFY_TIME) values('"+d+"','"+a+"','"+c+"','"+b+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"')");
		}
		if(x>0 || y>0){
			return 1;
		}else{
			return 0;
		}
		
	}
	/**
	 * 查询角色信息
	 * @return
	 */
	public Page<Record> findRole(){
		return mysql.paginate(1, 20, "select *", "from t_new_role where id in(select role_id from t_menu_role)");
	}
	/**
	 * 查询角色的权限
	 * @param userId
	 * @return
	 */
	public List<Record> findJurisdictionByRole(String roleId){
		return mysql.find("select * from t_menu where id in(select menu_id from t_menu_role where role_id='"+roleId+"')");
	}
	/**
	 * 查询角色是否被使用
	 * @return
	 */
	public List<Record> findByRoleId(String id){
		return mysql.find("select * from t_user where T_CONFIG_ID='"+id+"'");
	}
	/**
	 * 根据角色ID删除角色
	 * @return
	 */
	public boolean delRoleById(String id){
		//删除角色的时候，要同时删除角色拥有的权限
		Boolean b3 = false;
		Boolean b1 = mysql.deleteById("t_new_role", id);
		int b2 = mysql.update("delete from t_menu_role where role_id='"+id+"'");
		if(b2>0){
			b3 = true;
		}
		return b1 && b3;
	}
	/**
	 * 查询员工信息
	 * @param pgeNum
	 * @return
	 */
	public Page<Record> findStaffInfo(int pgeNum,String sql){
		
		return mysql.paginate(pgeNum, 20, "select *", sql);
		
	}
	/**
	 * 根据角色查询用户
	 * @return
	 */
	public List<Record> findUserByRoleId(String id){
		return mysql.find("select * from t_user where T_CONFIG_ID='"+id+"'");
	}
	/**
	 * 修改用户信息
	 * @param u
	 * @return
	 */
	public int updateStaff(String sql){
		
		return mysql.update(sql);
		
	}
	
	/**
	 * 报送记录
	 * @param name
	 * @return
	 */
	public Page<Record> submitRecord(int pageNumber, int pageSize,String userName,String selectTime) {
		StringBuffer sql=new StringBuffer("from t_submit_info where 1=1");
		return mysql.paginate(pageNumber, pageSize, "select *", submitAssembling(sql,userName,selectTime).toString());
	}
	
	/**
	 * 报送记录条数
	 * @param name
	 * @return
	 */
	public Integer submitRecordCount(String userName,String selectTime) {
		StringBuffer sql=new StringBuffer("select count(1) from t_submit_info where 1=1");
		return Integer.parseInt((mysql.queryFirst(submitAssembling(sql,userName,selectTime).toString())).toString());
	}
	
	/**
	 * 操作日志
	 * @param name
	 * @return
	 */
	public Page<Record> systemLogRecord(int pageNumber, int pageSize,String timeSelect) {
		StringBuffer sql=new StringBuffer("from t_system_log where 1=1");
		//System.out.println(systemLogAssembling(sql,timeSelect).toString());
		return mysql.paginate(pageNumber, pageSize, "select *", systemLogAssembling(sql,timeSelect).toString());
	}
	
	/**
	 * 操作日志条数
	 * @param name
	 * @return
	 */
	public Integer systemLogRecordCount(String timeSelect) {
		StringBuffer sql=new StringBuffer("select count(1) from t_system_log where 1=1");
		return Integer.parseInt((mysql.queryFirst(systemLogAssembling(sql,timeSelect).toString())).toString());
	}
	
	/*查询发送汇报人*/
	public List<Record> getReportUserselect(){
		List<Record> ReportUserList=mysql.find("select USER_ID,USER_NAME,MOBILE from t_systempush");
		return ReportUserList;
	}
	
	/*查询提现异常设置*/
	public List<Record> withRulesSettings(){
		List<Record> list =mysql.find("select exception_one,exception_one_tow,exception_tow,exception_three,exception_four from t_exception_setting where id=1");
		return list;
	}
	/*修改提现异常设置*/
	public String withRulesSettingsUpdate(Record record){
		String stu="";
		String sql = "update t_exception_setting SET exception_one=?,exception_one_tow=?,exception_tow=?,exception_three=?,exception_four=? where id=1";
		int a = mysql.update(sql,record.get("exceptionOne"),record.get("exceptionTow"),record.get("exceptionThere"),record.get("exceptionFour"),record.get("exceptionFive"));
		if(a>0){
			stu="SUCCESS";
		}else{
			stu="ERROR";
		}
		return stu;
	}
	
	/*提现金额权限设置*/
	public List<Record> withAmountSettings(){
		List<Record> list =mysql.find("select u.REALNAME,rc.*,r.role_name from t_new_role r inner join t_recharge_config rc on r.id=rc.role_id inner join t_user u on u.T_CONFIG_ID=r.id where rc.role_id in (8,4,2,3) ORDER by rc.role_id desc");
		return list;
	}
	
	/*修改提现金额权限设置*/
	public String withAmountSettingsUpdate(String[] financeDepartmen,String[] financeManager,String[] financeVicePresident,String[] chairman){
		String stu="SUCCESS";
		String sql = "update t_recharge_config set T_SINGLE_MIN_MONEY=?,T_SINGLE_MAX_MONEY=?,T_TOTAL_MONEY=? where id=?";
		int financeDepartmenSum = mysql.update(sql,financeDepartmen[1],financeDepartmen[2],financeDepartmen[3],financeDepartmen[0]);
		int financeManagerSum = mysql.update(sql,financeManager[1],financeManager[2],financeManager[3],financeManager[0]);
		int financeVicePresidentSum = mysql.update(sql,financeVicePresident[1],null,null,financeVicePresident[0]);
		int chairmanSum = mysql.update(sql,chairman[1],chairman[2],chairman[3],chairman[0]);
		if(financeDepartmenSum<=0){
			stu="ERROR";
		}
		if(financeManagerSum<=0){
			stu="ERROR";
		}
		if(financeVicePresidentSum<=0){
			stu="ERROR";
		}
		if(chairmanSum<=0){
			stu="ERROR";
		}
		return stu;
	}
	/*****************sql拼装*******************/
	/*报送记录sql拼装*/
	private StringBuffer submitAssembling(StringBuffer sql,String userName,String selectTime) {
		if(!userName.equals("")){
			sql.append(" and USER_NAME='"+userName+"'");
		}
		
		if(!selectTime.equals("")){
			sql.append(" and to_days(SUBMIT_TIME) = to_days('"+selectTime+"')");
		}
		
		return sql;
	}
	
	/*操作日志sql拼装*/
	private StringBuffer systemLogAssembling(StringBuffer sql,String timeSelect) {
		
		if(timeSelect.equals("0")){
            sql.append(" and create_time>DATE_SUB(CURDATE(), INTERVAL 1 DAY)");
        }
        if(timeSelect.equals("1")){
        	  sql.append(" and create_time>DATE_SUB(CURDATE(), INTERVAL 1 WEEK)");
        }
        if(timeSelect.equals("2")){
        	  sql.append(" and create_time>DATE_SUB(CURDATE(), INTERVAL 1 MONTH)");
        }
		
		return sql;
	}
}
