package com.manji.finance.system;
/**
 * 发送短信model
 * @author Administrator
 *
 */
public class UserSmsLogDO {

	/**
     * 用户ID
     */
    private int userId;
    /**
     * 用户角色类型
     */
    private String userRoleType;
    /**
     * 用户角色值
     */
    private int userRoleValue;
    /**
     * 短信类别
     */
    private String type;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 申请IP
     */
    private String userIp;
    /**
     * 接收手机号
     */
    private String mobile;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 发送状态
     */
    private int status;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserRoleType() {
		return userRoleType;
	}
	public void setUserRoleType(String userRoleType) {
		this.userRoleType = userRoleType;
	}
	public int getUserRoleValue() {
		return userRoleValue;
	}
	public void setUserRoleValue(int userRoleValue) {
		this.userRoleValue = userRoleValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
}
