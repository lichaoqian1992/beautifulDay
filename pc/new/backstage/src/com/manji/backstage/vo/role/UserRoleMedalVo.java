package com.manji.backstage.vo.role;
import com.manji.backstage.model.role.UserRoleMedal;

//dt_user_role（用户角色荣誉勋章信息表）
public class UserRoleMedalVo extends UserRoleMedal{
	int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
