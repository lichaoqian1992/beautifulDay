package com.manji.finance.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.system.requestParams.UserParams;
import com.manji.finance.utils.DecriptUtil;

public class SystemService {

	SystemRepository systemRepository = new SystemRepository();
	/**
	 * 查询交易报送人
	 * @return
	 */
	public List<Record> findSysPush(){
		return systemRepository.findSysPush();
	}
	/**
	 * 保存交易报送人
	 * @param a
	 * @param b
	 * @param c
	 */
	public int saveOrUpdateSys(String[] a,String[] b ,String c[]){
		int y = 0,x = 0;
		for(int i=0;i<b.length;i++){
			if(a[i].length()>0){
				int m = systemRepository.saveOrUpdateSys(a[i],b[i],c[i]);
				if(m>0){
					y++;
				}
			}else{
				int n = systemRepository.save(b[i],c[i]);
				if(n>0){
					x++;
				}
			}
		}
		return x+y;
		
	}
	
	/**
	 * 查询角色和对应的审批金额
	 * @return
	 */
	public List<Record> findRoleAndConfig(String id){
		
		return systemRepository.findRoleAndConfig(id);
	}
	/**
	 * 修改或者保存审批人金额
	 */
	public int saveOrUpdate(String[] a,String[] b,String[] c,String[] d,String[] e){
		int y = 0;
		for(int i=0;i<a.length;i++){
			int x = systemRepository.saveOrUpdate(a[i],b[i],c[i],d[i],e[i]);
			if(x > 0){
				y++;
			}
		}
		return y;
	}
	/**
	 * 查询所有角色
	 * @return 
	 */
	public Page<Record> findRole(){
		return systemRepository.findRole();
	}
	/**
	 * 查询角色的权限
	 * @return 
	 */
	public List<Record> findJurisdictionByRole(String roleId){
		return systemRepository.findJurisdictionByRole(roleId);
	}
	/**
	 * 新增或者修改角色权限
	 */
	public int saveJurisdiction(String userId,String url){
		//1.处理链接
		int xx = 1;
		//2.先删除权限，然后绑定
		String sql = "delete from t_menu_role where role_id='"+userId+"'";
		systemRepository.updateStaff(sql);
		if(url.length()>0){
			String[] str = url.split(";");
			
			//3.增加权限
			if(str.length>0){
				for(int i=0;i<str.length;i++){
					String sql2 = "insert into t_menu_role values(null,'"+Integer.parseInt(userId)+"','"+Integer.parseInt(str[i])+"')";
					xx += systemRepository.updateStaff(sql2);
				}
			}
		}
		return xx;
	}
	/**
	 * 删除角色byID
	 * @return
	 */
	public Boolean delRoleById(String id){
		//1.先判断是否绑定了人员
		List<Record> list = systemRepository.findByRoleId(id);
		if(list.size()<=0){
			return systemRepository.delRoleById(id);
		}else{
			return false;
		}
		
	}
	/*
	 * 查询员工信息
	 */
	public Page<Record> findStaffInfo(int pgeNum,String userName){
		String sql = "";
		if(userName.equals("1")){
			 sql = "from t_user a,t_new_role b  where a.T_CONFIG_ID=b.id order by a.T_CONFIG_ID";
		}else{
			 sql = "from t_user a,t_new_role b  where a.T_CONFIG_ID=b.id and a.T_CONFIG_ID='"+userName+"' order by a.T_CONFIG_ID";
		}
		
		return systemRepository.findStaffInfo(pgeNum,sql);
		
	}
	/**
	 * 根据账号查询员工信息
	 * @return
	 */
	public Page<Record> findStaff(String userName){
		String sql = "from t_user a,t_new_role b  where a.T_CONFIG_ID=b.id and a.USERNAME='"+userName+"'";
		return systemRepository.findStaffInfo(1,sql);
	}
	/**
	 * 修改用户信息
	 * @param u
	 * @return
	 */
	public int updateStaff(UserParams u){
		//如果角色没人使用，直接修改
		if(systemRepository.findUserByRoleId(u.getRoleType()).size()==0){
			String sql = "update t_user set REALNAME='"+u.getRealName()+"',USERNAME='"+u.getUserName()+"',MOBILE='"+u.getMobile()+"',EMAIL='"+u.getEmail()+"',T_CONFIG_ID="+Integer.parseInt(u.getRoleType())+" where ID="+Integer.parseInt(u.getId());
			
			return systemRepository.updateStaff(sql);
		}else{
			//如果角色有人使用，判断是不是我在使用，如果是我在使用就直接更新，如果不是
			if(systemRepository.findUserByRoleId(u.getRoleType()).get(0).get("ID").toString().equals(u.getId())){
				String sql = "update t_user set REALNAME='"+u.getRealName()+"',USERNAME='"+u.getUserName()+"',MOBILE='"+u.getMobile()+"',EMAIL='"+u.getEmail()+"',T_CONFIG_ID="+Integer.parseInt(u.getRoleType())+" where ID="+Integer.parseInt(u.getId());
				
				return systemRepository.updateStaff(sql);
			}
		}
		return 0;
		
	}
	/**
	 * 新增员工
	 * @param u
	 * @return
	 */
	public int saveStaff(UserParams u){
		String pass = DecriptUtil.SHA1(u.getPassword());
		//先判断角色是否存在
		if(systemRepository.findUserByRoleId(u.getRoleType()).size()==0){
			String sql = "insert into t_user(USERNAME,PASSWORD,REALNAME,MOBILE,EMAIL,CREATE_TIME,T_CONFIG_ID) values('"+u.getUserName()+"','"+pass+"','"+u.getRealName()+"','"+u.getMobile()+"','"+u.getEmail()+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"',"+u.getRoleType()+")";
			
			return systemRepository.updateStaff(sql);
		}else{
			return 0;
		}
		
	}
	/**
	 * 删除员工
	 * @param name
	 * @return
	 */
	public int delStaff(String name){
		String sql = "delete from t_user where USERNAME='"+name+"'";
		return systemRepository.updateStaff(sql);
	}
	/**
	 * 重置密码
	 * @param name
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public int resetPass(String name,String oldPass,String newPass){
		//1.先判断密码是否正确
		List<Record> list = findStaff(name).getList();
		if(list != null && list.size()>0){
			if(list.get(0).get("PASSWORD").equals(DecriptUtil.SHA1(oldPass))){
				//2.修改密码
				String sql = "update t_user set PASSWORD='"+DecriptUtil.SHA1(newPass)+"' where USERNAME='"+name+"'";
				return systemRepository.updateStaff(sql);
			}else{
				return 2;
			}
		}else{
			return 0;
		}
	}
	
	/**
	 * 报送记录
	 * @param name
	 * @return
	 */
	public Page<Record> submitRecord(int pageNumber, int pageSize,String userName,String selectTime){
		return systemRepository.submitRecord(pageNumber,pageSize,userName,selectTime);
	}
	
	/**
	 * 报送记录条数
	 * @param name
	 * @return
	 */
	public Integer submitRecordCount(String userName,String selectTime){
		return systemRepository.submitRecordCount(userName,selectTime);
	}
	
	/**
	 * 操作日志
	 * @param name
	 * @return
	 */
	public Page<Record> systemLogRecord(int pageNumber, int pageSize,String timeSelect){
		return systemRepository.systemLogRecord(pageNumber,pageSize,timeSelect);
	}
	
	/**
	 * 操作日志条数
	 * @param name
	 * @return
	 */
	public Integer systemLogRecordCount(String timeSelect){
		return systemRepository.systemLogRecordCount(timeSelect);
	}
	
	/*查询发送汇报人*/
	public List<Record> getReportUserselect(){
		return systemRepository.getReportUserselect();
	}
	
	/**
	 *异常提现设置(select)
	 * @param name
	 * @return
	 */
	public List<Record> withRulesSettings(){
		return systemRepository.withRulesSettings();
	}
	
	/**
	 *异常提现设置(update)
	 * @param name
	 * @return
	 */
	public String withRulesSettingsUpdate(Record record){
		return systemRepository.withRulesSettingsUpdate(record);
	}
	
	/**
	 *提现金额权限设置(select)
	 * @param name
	 * @return
	 */
	public List<Record> withAmountSettings(){
		return systemRepository.withAmountSettings();
	}
	
	/**
	 *提现金额权限设置(update)
	 * @param name
	 * @return
	 */
	public String withAmountSettingsUpdate(String[] financeDepartmen,String[] financeManager,String[] financeVicePresident,String[] chairman){
		return systemRepository.withAmountSettingsUpdate(financeDepartmen,financeManager,financeVicePresident,chairman);
	}
}
