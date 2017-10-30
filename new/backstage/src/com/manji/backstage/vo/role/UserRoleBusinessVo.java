package com.manji.backstage.vo.role;
import com.manji.backstage.model.role.UserRoleBusiness;

//2.8.18.dt_user_role_business（用户商务角色信息表）
public class UserRoleBusinessVo extends UserRoleBusiness{
	int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
