package com.manji.backstage.model.shop;

//dt_user_role_shopinfo_grouprelation(集团托管商家店铺关联表)
public class UserRoleShopinfoGrouprelation {
	int id;
	int shop_id;
	int manager_type;
	int groupshop_id;
	int status;
	String add_time;
	String update_time;
	String remark;
	String auditinfo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getManager_type() {
		return manager_type;
	}
	public void setManager_type(int manager_type) {
		this.manager_type = manager_type;
	}
	public int getGroupshop_id() {
		return groupshop_id;
	}
	public void setGroupshop_id(int groupshop_id) {
		this.groupshop_id = groupshop_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditinfo() {
		return auditinfo;
	}
	public void setAuditinfo(String auditinfo) {
		this.auditinfo = auditinfo;
	}
	
	
}         
          
          
          
          
          
          
          