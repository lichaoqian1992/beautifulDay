package com.manji.backstage.vo.other;
import com.manji.backstage.model.other.UserRole;

//2.8.4.dt_user_role（用户角色信息表）
public class UserRoleVo extends UserRole{
	int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
