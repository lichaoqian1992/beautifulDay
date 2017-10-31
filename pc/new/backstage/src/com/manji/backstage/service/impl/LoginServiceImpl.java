package com.manji.backstage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.mapper.LoginMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.BacMenu;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.service.LoginService;
import com.manji.backstage.vo.login.LoggersVo;
import com.manji.backstage.vo.login.PermissionVo;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper mapper;
	
	
	@Override
	public User checkUser(String userName, String password) {
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
	    User u =mapper.checkUser(userName, password);
	    DataSourceContextHolder.setDSType(DataSourceType.MANJI);
		
		return u;
		
	}


	@Override
	public List<String> getAuth(String userName) {
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
		List<String> authList =mapper.getAuth(userName);
		 DataSourceContextHolder.setDSType(DataSourceType.MANJI);
		
		return authList;
	}
	
	
	public static void setDataSource(){
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
	}
	public static void setManjiSource(){
		DataSourceContextHolder.setDSType(DataSourceType.MANJI);
	}


	@Override
	public List<User> getLocalUserAndRole() {
		setDataSource();
		
		List<User> userList =mapper.getLocalUsers();
		
		for(int i=0;i<userList.size();i++){
			
			List<Role> roleList =mapper.getLocalRoleByUser(userList.get(i).getId());
			if(roleList!=null){
				userList.get(i).setRoles(roleList);
			}
		}
		
		
		setManjiSource();
		return userList;
	}


	@Override
	public List<Role> getLocalRoleByUser(int userId) {
		setDataSource();
		List<Role> roleList =mapper.getLocalRoleByUser(userId);
		setManjiSource();
		return roleList;
	}


	@Override
	public User getLocalUser(int userId) {
		setDataSource();
		User u =mapper.getLocalUser(userId);
		setManjiSource();
		return u;
	}


	@Override
	public boolean updLocalUser(User user) {
		setDataSource();
		if(mapper.updLocalUser(user)>0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}
	
	
	@Override
	public Role getLocalRoleList(int id) {
		setDataSource();
		Role roleList =mapper.getLocalRoleList(id);
		setManjiSource();
		return roleList;
	}

	@Override
	public List<RoleToUser> getURRelation(int userId) {
		setDataSource();
		List<RoleToUser> rl =mapper.getURRelation(userId);
		setManjiSource();
		
		return rl;
	}
	
	
	
	
	
	
	
	
	@Override
	public boolean delLocalRoleToUser(int id) {
		setDataSource();
		if(mapper.delLocalRoleToUser(id)>0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}


	@Override
	public void addLocalRoleToUser(RoleToUser rtu) {
		setDataSource();
		mapper.addLocalRoleToUser(rtu);
		setManjiSource();
		
	}


	

	@Override
	public void addLocalUser(User u) {
		setDataSource();
		mapper.addLocalUser(u);
		setManjiSource();
	}


	@Override
	public List<Role> getLocalRole() {
		setDataSource();
		List<Role> roleList =mapper.getLocalRole();
		setManjiSource();
		return roleList;
	}


	@Override
	public boolean checkRoleName(String roleName) {
		setDataSource();
		if(mapper.checkRoleName(roleName)==0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}


	@Override
	public void addLocalRole(Role r) {
		setDataSource();
		mapper.addLocalRole(r);
		setManjiSource();
	}


	@Override
	public boolean delLocalRole(int id) {
		setDataSource();
		if(mapper.delLocalRole(id)>0){
			setManjiSource();
			return true;
		}
		
		setManjiSource();
		return false;
	}


	@Override
	public Page<Permission> queryLocalPermission(PermissionVo vo) {
		setDataSource();
		Page<Permission> page = new Page<Permission>();
		if(vo.getIndex()==0 || "".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countPermission(vo);
		List<Permission> perList =mapper.queryLocalPermission(vo);
		page.transform(count, perList);
		setManjiSource();
		return page;
	}
	
	@Override
	public Permission getPermission(int id) {
		setDataSource();
		Permission perList =mapper.getPermission(id);
		setManjiSource();
		return perList;
	}
	
	@Override
	public void addPermission(Permission per){
		setDataSource();
		mapper.addPermission(per);
		setManjiSource();
	}

	@Override
	public boolean updPermission(Permission per){
		setDataSource();
		if(mapper.updPermission(per)>0){
			return true;
		}
		setManjiSource();
		return false;
	}
	
	@Override
	public boolean delPermission(int id){
		setDataSource();
		if(mapper.delPermission(id)>0){
			return true;
		}
		setManjiSource();
		return false;
	}
	
	@Override
	public Role getLocalRole(int roleId) {
		
//		Role r =mapper.getLocalRole(roleId);
//		return r;
		return null;
	}




	@Override
	public Role getLocalRoleByUserId(int userId) {
		setDataSource();
		Role  r =getLocalRoleByUserId(userId);
		setManjiSource();
		return r;
	}


	@Override
	public boolean checkUserName(String username) {
		setDataSource();
		if(mapper.checkUserName(username)==0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}


	@Override
	public boolean delLocalUser(int user_id) {
		setDataSource();
		if(mapper.delLocalUser(user_id)>0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}


	@Override
	public RoleToUser getLocalRoleToUser(int id) {
		setDataSource();
		RoleToUser rtu =mapper.getLocalRoleToUser(id);
		setManjiSource();
		return rtu;
	}

	@Override
	public boolean updLocalRole(Role role) {
		setDataSource();
		if(mapper.updLocalRole(role)>0){
			setManjiSource();
			return true;
		}
		setManjiSource();
		return false;
	}

	@Override
	public boolean checkAuth(String userid,String url) {
		setDataSource();
		int id =Integer.valueOf(userid);
		
		List<Role> roleList =mapper.getLocalRoleByUser(id);
		
		List<String > moduleList =new ArrayList<String>();
		
		for(int i=0;i<roleList.size();i++){
			Role tr =roleList.get(i);
			String moduleStr =tr.getRemark();
			if(null!= moduleStr&&!"".equals(moduleStr)){
				String[] arr =moduleStr.split(",");
				for(int j=0;j<arr.length;j++){
					moduleList.add(arr[j]);
				}
			}
		}
		
		String model =mapper.getPermissionModel(url);
		
		setManjiSource();
		for(String str :moduleList){
			if(model.equals(str)){
				return true;
			}
			
		}
		
		
		return false;
	}

	@Override
	public void addLoggers(Loggers log) {
		setDataSource();
		mapper.addLoggers(log);
		setManjiSource();
	}

	@Override
	public Loggers getLoggers(int id) {

		setDataSource();
		
		Loggers log =mapper.getLoggers(id);
		setManjiSource();
		return log;
	}

	@Override
	public Page<Loggers> queryLoggers(LoggersVo vo) {
		
		Page<Loggers> page =new Page<Loggers>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		setDataSource();
		List<Loggers> dataList =mapper.queryLoggers(vo);
		int totalCount =mapper.countLoggers(vo);
		
		page.transform(totalCount, dataList);
		setManjiSource();
		
		return page;
	}

	@Override
	public Data addData(String json) {
		setDataSource();
		Data d =new Data();
		d.setJson(json);
		mapper.addData(d);
		setManjiSource();
		return d;
	}

	@Override
	public Data getData(int id) {
		setDataSource();
		Data d =mapper.getData(id);
		setManjiSource();
		return d;
	}


	@Override
	public List<BacMenu> getLoginMenu(int userid) {
		
		
		
		setDataSource();
		List<Role> roleList =mapper.getLocalRoleByUser(userid);
		
		String per =getPers(roleList);
		System.out.println(per);
		BacMenu b =new BacMenu();
		b.setModel(per);
		List<BacMenu> tempList =mapper.queryMenuAddress(b);
		
		
		
		
		
		
		
		
		
		
		
		
		
		setManjiSource();
		return tempList;
	}
	public static String getPers(List<Role> list){
			
			
			String str ="";
			if(list!=null){
					
					for(int i=0;i<list.size();i++){
						String remark =list.get(i).getRemark();
						if(!"".equals(remark)){
							str +=remark;
							str+=",";
						}
						
					}
					
			}
			
			String[] pers =str.split(",");
			for(int k =0;k<pers.length;k++){
				String temp =pers[k];
				
				if(!"".equals(temp)){
					
					for(int m =k+1;m<pers.length;m++){
						
						if(temp.equals(pers[m])){
							pers[m]="";
						}
					}
				}
			}
			String perStr ="";
			for(int n=0;n<pers.length;n++){
				
				if(!"".equals(pers[n])){
					
					perStr+="'"+pers[n]+"',";
					
				}
			}
			System.out.println(perStr+" "+perStr.length());
			
			perStr=perStr.substring(0,perStr.length()-1);
			
			System.out.println(perStr);
			
			return perStr;
		}
		
}
