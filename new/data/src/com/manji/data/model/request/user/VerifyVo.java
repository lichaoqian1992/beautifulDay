package com.manji.data.model.request.user;
//安全验证记录  dt_user_safeprotect
public class VerifyVo {

	int id;					//编号
	int user_id;			//用户ID
	String safe_type;		//安全类别  身份证、手机、邮箱、安全密码、登录密码、密保问题、公司信息  IDCRD COMPANY  PAYPASSWORD EMAIL PASSWORD MOBILE
	String safe_value;		//安全类别更新值
	int safe_state;			//安全状态  0未验证1已验证（已设置）2被冻结(不可继续申请)9等待验证
	String update_time;		//更新时间
	String update_remark;	//更新描述
	String local_area;		//所属地区  用于代理商审核
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSafe_type() {
		return safe_type;
	}
	public void setSafe_type(String safe_type) {
		this.safe_type = safe_type;
	}
	public String getSafe_value() {
		return safe_value;
	}
	public void setSafe_value(String safe_value) {
		this.safe_value = safe_value;
	}
	public int getSafe_state() {
		return safe_state;
	}
	public void setSafe_state(int safe_state) {
		this.safe_state = safe_state;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_remark() {
		return update_remark;
	}
	public void setUpdate_remark(String update_remark) {
		this.update_remark = update_remark;
	}
	public String getLocal_area() {
		return local_area;
	}
	public void setLocal_area(String local_area) {
		this.local_area = local_area;
	}
	
	
	
}
