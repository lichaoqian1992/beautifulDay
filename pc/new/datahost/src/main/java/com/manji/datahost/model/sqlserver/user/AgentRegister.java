package com.manji.datahost.model.sqlserver.user;
/**
 * 用户注册信息
 * @author Administrator
 *
 */
public class AgentRegister {

	private String reg_time;
	private String mobile;
	private Integer status;
	private Integer state;
	private String role_type;
	private String name;
	private String agent_area;
	
	public String getAgent_area() {
		return agent_area;
	}
	public void setAgent_area(String agent_area) {
		this.agent_area = agent_area;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
