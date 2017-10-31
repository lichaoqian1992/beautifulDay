package com.manji.backstage.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.dto.login.UserRoleDto;
import com.manji.backstage.mapper.login.AuthMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.vo.login.PermissionVo;
@Service
public class AuthServiceImpl implements AuthService {


	@Autowired
	private AuthMapper mapper;
	
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



	

	

	

	

}
