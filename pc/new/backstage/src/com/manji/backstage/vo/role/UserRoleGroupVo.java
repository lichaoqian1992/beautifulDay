package com.manji.backstage.vo.role;
import com.manji.backstage.model.role.UserRoleGroup;

//dt_user_role_group(用户角色分组信息表)
public class UserRoleGroupVo extends UserRoleGroup{
	int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
